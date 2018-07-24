package com.emprendesoftcr.service.impl;

import static com.emprendesoftcr.fisco.Keys.ERROR;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.SemaforoBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.components.EnvioHaciendaComponent;
import com.emprendesoftcr.components.OpenIDConnectHaciendaComponent;
import com.emprendesoftcr.fisco.EmisorHacienda;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.emprendesoftcr.fisco.Recepcion;
import com.emprendesoftcr.fisco.ReceptorHacienda;
import com.emprendesoftcr.fisco.RespuestaHaciendaXML;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.Semaforo;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.service.RespuestaHaciendaXMLService;
import com.emprendesoftcr.type.RespuestaHacienda;
import com.emprendesoftcr.type.json.RespuestaHaciendaJson;

/**
 * Servicio de envio de los documentos de hacienda
 **/
@Service("procesoHaciendaService")
@Transactional
public class ProcesoHaciendaServiceImpl implements ProcesoHaciendaService {

	private Logger													log	= LoggerFactory.getLogger(this.getClass());

	@Lazy
	@Autowired
	private HaciendaBo											haciendaBo;
	
	@Lazy
	@Autowired
	private RespuestaHaciendaXMLService											respuestaHaciendaXMLService;

	@Lazy
	@Autowired
	private SemaforoBo											semaforoBo;

	@Lazy
	@Autowired
	private OpenIDConnectHaciendaComponent	openIDConnect;

	@Lazy
	@Autowired
	private EnvioHaciendaComponent					envioHaciendaComponent;

	/**
	 * Proceso automatico para ejecutar el envio de los documentos de hacienda documentos xml ya firmados
	 */
	// @Scheduled(cron = "*/5 * * * * ?")
	@Scheduled(cron = "0 0/1 * * * ?")
	@Override
	public synchronized void taskHaciendaEnvio() throws Exception {
		try {
			Semaforo semaforo = null;
			// Listado de los documentos Pendientes de enviar hacienda
			Collection<Hacienda> listaHacienda = haciendaBo.findByEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML, Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR);
			for (Hacienda hacienda : listaHacienda) {
				semaforo = semaforo == null ? semaforo = semaforoBo.findByEmpresa(hacienda.getEmpresa(), Constantes.SEMAFORO_ESTADO_ACTIVO) : semaforo;
				if (semaforo != null) {
					// Si esta activo el semaforo y el numero de reintentos es menor al permitido
					if (semaforo.getEstado().equals(Constantes.SEMAFORO_ESTADO_ACTIVO)) {
						if (hacienda.getReintentos() < semaforo.getMaximoReintentosEnviar()) {
							envioHacienda(hacienda);
						} else {// Si alcanza el maximo reintentos no se envia mas a la hacienda
							hacienda.setEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_TOPE_REINTENTOS);
							hacienda.setUpdated_at(new Date());
							haciendaBo.modificar(hacienda);
						}

					}
				}

			}

		} catch (Exception e) {
			log.info("** Error  taskHaciendaEnvio: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Comunicacion con Hacienda para aceptar el documento
	 * @param hacienda
	 * @throws Exception
	 */
	private void envioHacienda(Hacienda hacienda) throws Exception {
		try {
			OpenIDConnectHacienda openIDConnectHacienda = null;
			// Obtener el token en hacienda para enviar los documentos
			openIDConnectHacienda = openIDConnect.getToken(hacienda.getEmpresa());
			// Se obtuvo el token de accienda
			if (openIDConnectHacienda.getAccess_token().length() > 0) {

				envioHaciendaFacturas(hacienda, openIDConnectHacienda);

			}
			// Desconectar token de hacienda
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(hacienda.getEmpresa(), openIDConnectHacienda);
				}

			}

		} catch (

		Exception e) {
			log.info("** Error  ejecutarEnvio: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Este proceso realiza la comunicacion con hacienda 1. Obtener el token de la comunicacion con hacienda del cliente
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#envioHaciendaFacturas()
	 */
	private void envioHaciendaFacturas(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception {
		try {

			// Crea el objeto recepción que se enviará a los APIs.
			Recepcion recepcion = new Recepcion();
			ReceptorHacienda receptor = null;
			if(hacienda.getCedulaReceptor()!=null) {
				receptor = new ReceptorHacienda(hacienda.getTipoReceptor() == null ? Constantes.EMPTY : hacienda.getTipoReceptor(), hacienda.getCedulaReceptor() == null ? Constantes.EMPTY : hacienda.getCedulaReceptor());
			}
				
		
			EmisorHacienda emisor = new EmisorHacienda(hacienda.getTipoEmisor(), hacienda.getCedulaEmisor());

			recepcion.setClave(hacienda.getClave().trim());

			String date = FacturaElectronicaUtils.toISO8601String(hacienda.getFechaEmisor());
			recepcion.setFecha(date);

			// Emisor
			recepcion.setEmisor(emisor);
			// Receptor (condicional)
			if (receptor != null) {
				recepcion.setReceptor(receptor);
			}

			// XML se convierte en base 64
			String valor = FacturaElectronicaUtils.convertirBlodToString(hacienda.getComprobanteXML());
			if (valor.length() > 0) {
				String base64 = FacturaElectronicaUtils.base64Encode(valor.trim().getBytes("UTF-8"));
				recepcion.setComprobanteXml(base64);

				ObjectMapper mapperObj = new ObjectMapper();
				String jsonStr = mapperObj.writeValueAsString(recepcion);

				envioHaciendaComponent.enviarDocumentoElectronico(jsonStr, openIDConnectHacienda, hacienda);

			}

		} catch (Exception e) {
			log.info("** Error  envioHaciendaFacturas: " + e.getMessage() + " fecha " + new Date() + " Hacienta:" + hacienda.getEmpresa().getNombre());
			throw e;
		}

	}

	/**
	 * Retorna el status de la respuesta de hacienda
	 * @param indEstado Elemento ind-estado de la respuesta de hacienda
	 * @return Estado de la respuesta de hacienda OK o ERROR
	 */
	private static String getHaciendaStatus(String indEstado) {
		return MapEnums.ENUM_CODIGO_RESPUESTA_HACIENDA.containsKey(indEstado) ? MapEnums.ENUM_CODIGO_RESPUESTA_HACIENDA.get(indEstado) : ERROR;
	}

	/**
	 * http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html Proceso automatico para ejecutar aceptacion del documento
	 */
	@Scheduled(cron = "0 0/2 * * * ?")
	@Override
	public synchronized void taskHaciendaComprobacionDocumentos() throws Exception {
		try {
			log.info("The time is now {}", new Date());
			Semaforo semaforo = null;
			// Listado de los documentos Pendientes de aceptar por hacienda
			Collection<Hacienda> listaHacienda = haciendaBo.findByEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA, Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA);
			for (Hacienda hacienda : listaHacienda) {
				semaforo = semaforo == null ? semaforo = semaforoBo.findByEmpresa(hacienda.getEmpresa(), Constantes.SEMAFORO_ESTADO_ACTIVO) : semaforo;
				if (semaforo != null) {
					// Si esta activo el semaforo y el numero de reintentos es menor al permitido
					if (semaforo.getEstado().equals(Constantes.SEMAFORO_ESTADO_ACTIVO)) {
						if (hacienda.getReintentosAceptacion() < semaforo.getMaximoReintentosEnviar()) {
							aceptarDocumento(hacienda);
						} else {// Si alcanza el maximo reintentos de aceptacion mas a la hacienda
							hacienda.setEstado(Constantes.HACIENDA_ESTADO_ACEPTACION_HACIENDA_TOPE_REINTENTOS);
							hacienda.setUpdated_at(new Date());
							haciendaBo.modificar(hacienda);
						}

					}
				}

			}

		} catch (Exception e) {
			log.info("** Error  taskHaciendaComprobacionDocumentos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Aceptar documentos
	 * @param hacienda
	 */
	private void aceptarDocumento(Hacienda hacienda) throws Exception {
		try {
			OpenIDConnectHacienda openIDConnectHacienda = null;
			// Obtener el token en hacienda para enviar los documentos
			openIDConnectHacienda = openIDConnect.getToken(hacienda.getEmpresa());
			// Se obtuvo el token de accienda
			if (openIDConnectHacienda.getAccess_token().length() > 0) {
				Map response = envioHaciendaComponent.comprobarDocumentoElectronico(hacienda.getClave(), openIDConnectHacienda);
				String body = (String) response.get(POST_RESPONSE);
				if (body != null && body != "") {
					RespuestaHacienda respuestaHacienda = RespuestaHaciendaJson.from(body);
					
					
					String status = getHaciendaStatus(respuestaHacienda.indEstado());
					hacienda.setUpdated_at(new Date());
					RespuestaHaciendaXML respuesta = new RespuestaHaciendaXML();	
					respuesta.setClave(respuestaHacienda.clave());
					respuesta.setFecha(respuestaHacienda.fecha());
					respuesta.setIndEstado(respuestaHacienda.indEstado());
					respuesta.setMensaje(respuestaHacienda.mensajeHacienda().mensaje());
					respuesta.setDetalleMensaje(respuestaHacienda.mensajeHacienda().detalleMensaje());
					respuesta.setMontoTotalImpuesto(respuestaHacienda.mensajeHacienda().montoTotalImpuesto());
					respuesta.setNombreEmisor(respuestaHacienda.mensajeHacienda().nombreEmisor());
					respuesta.setNombreReceptor(respuestaHacienda.mensajeHacienda().nombreReceptor());
					respuesta.setNumeroCedulaEmisor(respuestaHacienda.mensajeHacienda().numeroCedulaEmisor());
					respuesta.setNumeroCedulaReceptor(respuestaHacienda.mensajeHacienda().numeroCedulaReceptor());
					respuesta.setTipoIdentificacionEmisor(respuestaHacienda.mensajeHacienda().tipoIdentificacionEmisor());
					respuesta.setTipoIdentificacionReceptor(respuestaHacienda.mensajeHacienda().tipoIdentificacionReceptor());
					respuesta.setTotalFactura(respuestaHacienda.mensajeHacienda().totalFactura());
					String xmlSinFirmarRespuesta  = respuestaHaciendaXMLService.getCrearXMLSinFirma(respuesta);
					String xmlFirmadoRespuesta    = respuestaHaciendaXMLService.getFirmarXML(xmlSinFirmarRespuesta, hacienda.getEmpresa());
					 hacienda.setMensajeHacienda(FacturaElectronicaUtils.convertirStringToblod(xmlFirmadoRespuesta));
					if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR)) {
						hacienda.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA);
					}
					if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO_STR)) {
						hacienda.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
					}
					haciendaBo.modificar(hacienda);
				} else {// sumar reintententos
					hacienda.setReintentosAceptacion(hacienda.getReintentosAceptacion() == null ? 1 : hacienda.getReintentosAceptacion() + 1);
					haciendaBo.modificar(hacienda);
				}
			}
			// Desconectar token de hacienda
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(hacienda.getEmpresa(), openIDConnectHacienda);
				}

			}

		} catch (Exception e) {
			log.info("** Error  aceptarDocumento: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

}

package com.emprendesoftcr.service.impl;

import static com.emprendesoftcr.fisco.Keys.CLAVE;
import static com.emprendesoftcr.fisco.Keys.DETALLE_MENSAJE;
import static com.emprendesoftcr.fisco.Keys.ERROR;
import static com.emprendesoftcr.fisco.Keys.ESTADO;
import static com.emprendesoftcr.fisco.Keys.FECHA;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.components.EnvioHaciendaComponent;
import com.emprendesoftcr.components.OpenIDConnectHaciendaComponent;
import com.emprendesoftcr.fisco.EmisorHacienda;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.emprendesoftcr.fisco.Recepcion;
import com.emprendesoftcr.fisco.ReceptorHacienda;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.type.RespuestaHacienda;
import com.emprendesoftcr.type.json.RespuestaHaciendaJson;
import com.google.common.collect.ImmutableMap;

/**
 * Servicio de envio de los documentos de hacienda
 **/
@Service("procesoHaciendaService")
@Transactional
public class ProcesoHaciendaServiceImpl implements ProcesoHaciendaService {

	private Logger													log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HaciendaBo											haciendaBo;

	@Autowired
	private CertificadoBo										certificadoBo;

	@Autowired
	private OpenIDConnectHaciendaComponent	openIDConnect;

	@Autowired
	private EnvioHaciendaComponent					envioHaciendaComponent;

	/**
	 * Proceso automatico para ejecutar el llamado de hacienda de los documentos xml ya firmados
	 */
//	@Scheduled(cron = "*/1 * * * * ?")
	public synchronized void taskHacienda() throws Exception {
		try {

			// Listado de las empresas que estan asociadas a tributacion
			Collection<Certificado> listaCertificados = certificadoBo.findByAll();
			for (Certificado certificado : listaCertificados) {
				ejecutarEnvio(certificado);
			}

		} catch (Exception e) {
			log.info("** Error  findByConsecutivoAndEmpresa: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	private void ejecutarEnvio(Certificado certificado) throws Exception {
		try {
			OpenIDConnectHacienda openIDConnectHacienda = null;
			Collection<Hacienda> listaFacturas = haciendaBo.findByEmpresaAndEstado(certificado.getEmpresa(), Constantes.HACIENDA_ESTADO_FIRMARDO_XML);
			for (Hacienda hacienda : listaFacturas) {
				// Obtener el token para enviar los documentos
				if (openIDConnectHacienda == null) {
					openIDConnectHacienda = openIDConnect.getToken(certificado.getEmpresa());
				}

				// Se obtuvo el token de accienda
				if (openIDConnectHacienda.getAccess_token().length() > 0) {
					// Enviar xml a hacienda
					envioHaciendaFacturas(hacienda, openIDConnectHacienda);

					Map response = envioHaciendaComponent.comprobarDocumentoElectronico(hacienda.getClave(), openIDConnectHacienda);
					String body = (String) response.get(POST_RESPONSE);
					if (body != null && body != "") {
						RespuestaHacienda ra = RespuestaHaciendaJson.from(body);
						String status = getHaciendaStatus(ra.indEstado());
						ImmutableMap<String, String> arguments = ImmutableMap.of(CLAVE, ra.clave(), FECHA, ra.fecha(), ESTADO, status, DETALLE_MENSAJE, status.equals(ERROR) ? ra.mensajeHacienda().detalleMensaje() : "");
					}

				}
			}
			// Desconectar token de hacienda
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(certificado.getEmpresa(),openIDConnectHacienda);
				}

			}

		} catch (Exception e) {
			log.info("** Error  ejecutarEnvio: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Retorna el status de la respuesta de hacienda
	 * @param indEstado Elemento ind-estado de la respuesta de hacienda
	 * @return Estado de la respuesta de hacienda OK o ERROR
	 */
	public static String getHaciendaStatus(String indEstado) {
		return MapEnums.ENUM_CODIGO_RESPUESTA_HACIENDA.containsKey(indEstado) ? MapEnums.ENUM_CODIGO_RESPUESTA_HACIENDA.get(indEstado) : ERROR;
	}

	/**
	 * Este proceso realiza la comunicacion con hacienda 1. Obtener el token de la comunicacion con hacienda del cliente
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#envioHaciendaFacturas()
	 */
	private void envioHaciendaFacturas(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception {
		try {

			// Crea el objeto recepción que se enviará a los APIs.
			Recepcion recepcion = new Recepcion();

			ReceptorHacienda receptor = new ReceptorHacienda(hacienda.getTipoReceptor(), hacienda.getCedulaReceptor());

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

				// HaciendaDocJson.Builder hib = new HaciendaDocJson.Builder();
				// HaciendaDoc hd = hib.callbackUrl(Constantes.EMPTY).clave(hacienda.getClave().trim()).
				// comprobanteXml(length).
				// fecha(date).
				// identificacion(documentoFirmado.identificacion()).build();
				//
				ObjectMapper mapperObj = new ObjectMapper();
				String jsonStr = mapperObj.writeValueAsString(recepcion);
				System.out.println(jsonStr);

				Map response = envioHaciendaComponent.enviarDocumentoElectronico(jsonStr, openIDConnectHacienda);
				String body = (String) response.get(POST_RESPONSE);

			}

			//
			// Client client = ClientBuilder.newClient();
			// WebTarget target = client.target(URI + "recepcion/");
			// Invocation.Builder request = target.request();
			// // Se deberá brindar una cabecera (header) "Authorization" con el valor del access token obtenido anteriormente.
			// request.header("Accept" + " application/json" + " Authorization=Bearer " , openIDConnectHacienda.getAccess_token()+
			// " User-Agent" + " Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			//

			// // Se envía un POST. con los datos del documento que deseamos registrar, observe que colocamos como
			// // atributo el objeto que configuramos en el apartado de 'Preparación'
			// Response response = request.post(Entity.json(jsonStr));
			// switch (response.getStatus()) {
			// case 202:
			// // Éste código de retorno se da por recibido a la plataforma el documento. Posteriormente
			// // debe validarse su estado de aceptación o rechazo. Es importante hacer notar que se
			// // regresa un header "Location" que corresponde a un URL. donde se puede validar el
			// // estado del documento, por ejemplo:
			// // https://api.comprobanteselectronicos.go.cr/recepcion-sandbox/v1/recepcion/50601011600310112345600100010100000000011999999999/
			//
			// hacienda.setStatus(response.getStatus());
			// haciendaBo.modificar(hacienda);
			//
			// break;
			// case 400:
			// // Se da si se detecta un error en las validaciones, por ejemplo: si intento enviar más de una
			// // vez un documento. El encabezado "X-Error-Cause" indica la causa del problema.
			// String xErrorCause = response.getHeaderString("X-Error-Cause");
			// hacienda.setStatus(response.getStatus());
			// hacienda.setxErrorCause(FacturaElectronicaUtils.convertirStringToblod(xErrorCause));
			// haciendaBo.modificar(hacienda);
			//
			// break;
			// }

		} catch (Exception e) {
			log.info("** Error  envioHaciendaFacturas: " + e.getMessage() + " fecha " + new Date() + " Hacienta:" + hacienda.getEmpresa().getNombre());
			throw e;
		}

	}

}

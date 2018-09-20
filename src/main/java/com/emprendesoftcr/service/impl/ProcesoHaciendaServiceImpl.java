package com.emprendesoftcr.service.impl;

import static com.emprendesoftcr.fisco.Keys.ERROR;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;
import static java.util.stream.Collectors.toList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.util.ByteArrayDataSource;
import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.RecepcionFacturaBo;
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
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.pdf.App;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.service.FacturaXMLServices;
import com.emprendesoftcr.service.NotaCreditoXMLServices;
import com.emprendesoftcr.service.NotaDebitoXMLService;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.service.RecepcionFacturaXMLServices;
import com.emprendesoftcr.service.RespuestaHaciendaXMLService;
import com.emprendesoftcr.service.TiqueteXMLService;
import com.emprendesoftcr.type.RespuestaHacienda;
import com.emprendesoftcr.type.json.RespuestaHaciendaJson;
import com.google.common.base.Function;

/**
 * Servicio de envio de los documentos de hacienda
 **/

@Transactional
@EnableTransactionManagement
@Service("procesoHaciendaService")
public class ProcesoHaciendaServiceImpl implements ProcesoHaciendaService {

	private static final Function<String, String> BIND_CONDICION_VENTA = (id) -> id.equals("01") ? "Contado" : id.equals("02") ? "Credito" : id.equals("03") ? "Consignacion" : id.equals("04") ? "Apartado" : id.equals("05") ? "Arrendamiento con opcion de compra" : id.equals("06") ? "Arrendamiento en funcion financiera" : "Otros";

	private static final Function<Detalle, DetalleFacturaElectronica>	TO_DETALLE											= (d) -> {
																																																			//
																																																			DetalleFacturaElectronica detalleFacturaElectronica = new DetalleFacturaElectronica();
																																																			detalleFacturaElectronica.setLinea(Integer.parseInt(d.getNumeroLinea().toString()));
																																																			detalleFacturaElectronica.setCodigo(d.getCodigo());
																																																			detalleFacturaElectronica.setUnidad(d.getUnidadMedida());
																																																			detalleFacturaElectronica.setCantidad(d.getCantidad());
																																																			detalleFacturaElectronica.setDescripcion(d.getDescripcion());
																																																			detalleFacturaElectronica.setPrecioU(d.getPrecioUnitario());
																																																			detalleFacturaElectronica.setMonto(d.getMontoTotal());
																																																			detalleFacturaElectronica.setDescuento(d.getMontoDescuento());
																																																			detalleFacturaElectronica.setSubtotal(detalleFacturaElectronica.getMonto() - (d.getMontoDescuento()));
																																																			detalleFacturaElectronica.setTarifaIva(d.getImpuesto());
																																																			detalleFacturaElectronica.setImpuesto(d.getMontoImpuesto());
																																																			// detalleFacturaElectronica.setExento(Constantes.EMPTY);
																																																			detalleFacturaElectronica.setTotal(d.getMontoTotalLinea());
																																																			//
																																																			return detalleFacturaElectronica;
																																																		};
	private static final Function<Factura, FacturaElectronica>				DOCUMENTO_TO_FACTURAELECTRONICA	= (d) -> {
																																																			FacturaElectronica facturaElectronica = new FacturaElectronica();
																																																			// Emisor

																																																			facturaElectronica.setEmisorNombre(!d.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? d.getEmpresa().getNombreComercial() : d.getEmpresa().getNombre());
																																																			facturaElectronica.setEmisorCedula(d.getEmpresa().getCedula());
																																																			facturaElectronica.setEmisorTelefono(d.getEmpresa().getCodigoPais() + "-" + d.getEmpresa().getTelefono().toString());
																																																			facturaElectronica.setEmisorCorreo(d.getEmpresa().getCorreoElectronico());
																																																			// Cliente
																																																			// if (!d.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
																																																			facturaElectronica.setClienteNombre(d.getCliente().getNombreCompleto());
																																																			facturaElectronica.setClienteNombreComercial(d.getCliente().getNombreComercial());
																																																			facturaElectronica.setClienteCorreo(d.getCliente().getCorreoElectronico());
																																																			facturaElectronica.setClienteCedula(d.getCliente().getTipoCedula() + "-" + d.getCliente().getCedula());
																																																			facturaElectronica.setClienteTelefono(d.getCliente().getTelefono() != null ? d.getCliente().getTelefono().toString() : Constantes.EMPTY);

																																																			// }
																																																			// facturaElectronica.setClienteMesCobro(TO_MESCOBRO.apply(d.getMesCobro()));
																																																			// Ubicacion
																																																			facturaElectronica.set_logo(d.getEmpresa().getLogo());
																																																			facturaElectronica.set_clienteDireccion(d.getDireccion());
																																																			// Otros
																																																			facturaElectronica.setTipoDocumento(FacturaElectronicaUtils.getTipoDocumento(d.getTipoDoc()));
																																																			facturaElectronica.setClave(d.getClave());
																																																			facturaElectronica.setConsecutivo(d.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) ? d.getId().toString() : d.getNumeroConsecutivo());
																																																			facturaElectronica.setFechaEmision(d.getFechaEmision().toString());
																																																			facturaElectronica.setPlazoCredito(d.getPlazoCredito().toString());
																																																			facturaElectronica.setCondicionVenta(BIND_CONDICION_VENTA.apply(d.getCondicionVenta()));
																																																			facturaElectronica.setMedioBanco(d.getMedioBanco() != null ? Constantes.FACTURA_MEDIO_PAGO_TRANSFERENCIA_STR : Constantes.EMPTY);
																																																			facturaElectronica.setMedioEfectivo(d.getMedioEfectivo() != null ? Constantes.FACTURA_MEDIO_PAGO_EFECTIVO_STR : Constantes.EMPTY);
																																																			facturaElectronica.setMedioTarjeta(d.getMedioTarjeta() != null ? Constantes.FACTURA_MEDIO_PAGO_TARJETA_STR : Constantes.EMPTY);

																																																			facturaElectronica.setMoneda(FacturaElectronicaUtils.getMoneda(d.getCodigoMoneda()));
																																																			facturaElectronica.setTipoCambio(d.getTipoCambio().toString());

																																																			// Nota Credito y Nota Debito
																																																			if (d.getReferenciaCodigo() != null) {
																																																				if (!d.getReferenciaCodigo().equals(Constantes.EMPTY)) {
																																																					facturaElectronica.setReferenciaCodigo(MapEnums.ENUM_CODIGO_REFERENCIA.get(d.getReferenciaCodigo()));
																																																					facturaElectronica.setReferenciaNumero(d.getReferenciaNumero());
																																																					facturaElectronica.setReferenciaRazon(d.getReferenciaRazon());
																																																					facturaElectronica.setReferenciaTipoDoc(MapEnums.ENUM_TIPO_DOC.get(d.getReferenciaTipoDoc()));
																																																					facturaElectronica.setReferenciaFechaEmision(d.getReferenciaFechaEmision() != null ? d.getReferenciaFechaEmision().toString() : Constantes.EMPTY);

																																																				}

																																																			} else {
																																																				facturaElectronica.setReferencia(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaCodigo(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaNumero(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaRazon(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaTipoDoc(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaFechaEmision(Constantes.EMPTY);

																																																			}
																																																			// Agrega sus detalles
																																																			List<DetalleFacturaElectronica> detalles = d.getDetalles().stream().map(TO_DETALLE).collect(toList());
																																																			facturaElectronica.setDetalleFacturaElectronica(detalles);
																																																			return facturaElectronica;
																																																		};

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HaciendaBo haciendaBo;

	@Autowired
	RespuestaHaciendaXMLService respuestaHaciendaXMLService;

	@Autowired
	SemaforoBo semaforoBo;

	@Autowired
	CorreosBo correosBo;

	@Autowired
	OpenIDConnectHaciendaComponent openIDConnect;

	@Autowired
	EnvioHaciendaComponent envioHaciendaComponent;

	@Autowired
	FacturaBo facturaBo;

	@Autowired
	RecepcionFacturaBo recepcionFacturaBo;

	@Autowired
	FacturaXMLServices facturaXMLServices;

	@Autowired
	TiqueteXMLService tiqueteXMLService;

	@Autowired
	NotaCreditoXMLServices notaCreditoXMLServices;

	@Autowired
	NotaDebitoXMLService notaDebitoXMLService;

	@Autowired
	RecepcionFacturaXMLServices recepcionFacturaXMLServices;

	/**
	 * Proceso automatico para ejecutar el envio de los documentos de hacienda documentos xml ya firmados
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	@Override
	public synchronized void taskHaciendaEnvio() throws Exception {
		try {
			log.info("Inicio Proceso de Envio de documentos  {}", new Date());
			// Listado de los documentos Pendientes de enviar hacienda
 			Collection<Hacienda> listaHacienda = haciendaBo.findByEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML, Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR);
			for (Hacienda hacienda : listaHacienda) {
				Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
				envioHacienda(haciendaBD);
			}
			log.info("Finaliza Proceso de Envio de documentos  {}", new Date());

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
	@Override
	public void envioHacienda(Hacienda hacienda) throws Exception {
		try {
			OpenIDConnectHacienda openIDConnectHacienda = null;
			// Obtener el token en hacienda para enviar los documentos
			openIDConnectHacienda = openIDConnect.getToken(hacienda.getEmpresa());
			// Se obtuvo el token de accienda
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getAccess_token().length() > 0) {
					envioHaciendaFacturas(hacienda, openIDConnectHacienda);
				}
			} else {
				log.info("** Error no se encontro el token   " + "Empresa:" + hacienda.getEmpresa().getNombre() + " fecha " + new Date());
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
			if (hacienda.getCedulaReceptor() != null) {
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
	@Scheduled(cron = "0 0/5 * * * ?")
	@Override
	public synchronized void taskHaciendaComprobacionDocumentos() throws Exception {
		try {
			log.info("Inicio Comprobacion de documentos  {}", new Date());
			// Semaforo semaforo = semaforoBo.findByEstado(Constantes.SEMAFORO_ESTADO_COMPROBAR_DOCUMENTOS);
			// Listado de los documentos Pendientes de aceptar por hacienda
			Collection<Hacienda> listaHacienda = haciendaBo.findByEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA, Constantes.HACIENDA_ESTADO_ERROR);
			for (Hacienda hacienda : listaHacienda) {
				Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
				aceptarDocumento(haciendaBD);
			}
			log.info("Fin Comprobacion de documentos  {}", new Date());

		} catch (Exception e) {
			log.info("** Error  ComprobacionDocumentos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Aceptar documentos
	 * @param hacienda
	 */
	@Override
	public void aceptarDocumento(Hacienda hacienda) throws Exception {
		try {
			OpenIDConnectHacienda openIDConnectHacienda = null;
			// Obtener el token en hacienda para enviar los documentos
			openIDConnectHacienda = openIDConnect.getToken(hacienda.getEmpresa());
			if (openIDConnectHacienda != null) {
				// Se obtuvo el token de accienda
				if (openIDConnectHacienda.getAccess_token().length() > 0) {
					String idp_uri_documentos = Constantes.EMPTY;
					if (hacienda.getEmpresa().getEstadoProduccion() != null) {
						if (hacienda.getEmpresa().getEstadoProduccion().equals(Constantes.ESTADO_ACTIVO)) {
							idp_uri_documentos = Constantes.IDP_URI_DOCUMENTOS_PRODUCCION;
						}
					}
					Map response = envioHaciendaComponent.comprobarDocumentoElectronico(idp_uri_documentos, hacienda.getClave(), openIDConnectHacienda);

					String body = (String) response.get(POST_RESPONSE);
					if (body != null && body != "") {

						RespuestaHacienda respuestaHacienda = RespuestaHaciendaJson.from(body);

						String status = getHaciendaStatus(respuestaHacienda.indEstado());
						hacienda.setUpdated_at(new Date());
						RespuestaHaciendaXML respuesta = new RespuestaHaciendaXML();
						respuesta.setClave(respuestaHacienda.clave());
						respuesta.setFecha(respuestaHacienda.fecha());
						respuesta.setIndEstado(respuestaHacienda.indEstado());
						respuesta.setMensaje(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().mensaje() : Constantes.EMPTY);
						respuesta.setDetalleMensaje(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().detalleMensaje() : Constantes.EMPTY);
						respuesta.setMontoTotalImpuesto(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().montoTotalImpuesto() : Constantes.ZEROS_DOUBLE);
						respuesta.setNombreEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().nombreEmisor() : Constantes.EMPTY);
						respuesta.setNombreReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().nombreReceptor() : Constantes.EMPTY);
						respuesta.setNumeroCedulaEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().numeroCedulaEmisor() : Constantes.EMPTY);
						respuesta.setNumeroCedulaReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().numeroCedulaReceptor() : Constantes.EMPTY);
						respuesta.setTipoIdentificacionEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().tipoIdentificacionEmisor() : Constantes.EMPTY);
						respuesta.setTipoIdentificacionReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().tipoIdentificacionReceptor() : Constantes.EMPTY);
						respuesta.setTotalFactura(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().totalFactura() : Constantes.ZEROS_DOUBLE);
						String xmlSinFirmarRespuesta = Constantes.EMPTY;
						String xmlFirmadoRespuesta = Constantes.EMPTY;
						if (!status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECIBIDO)) {
							xmlSinFirmarRespuesta = respuestaHaciendaXMLService.getCrearXMLSinFirma(respuesta);
							xmlFirmadoRespuesta = respuestaHaciendaXMLService.getFirmarXML(xmlSinFirmarRespuesta, hacienda.getEmpresa());
						} else {
							if (respuestaHacienda.mensajeHacienda() != null) {
								if (respuestaHacienda.mensajeHacienda().mensaje() != null) {
									if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO)) {
										xmlSinFirmarRespuesta = respuestaHaciendaXMLService.getCrearXMLSinFirma(respuesta);
										xmlFirmadoRespuesta = respuestaHaciendaXMLService.getFirmarXML(xmlSinFirmarRespuesta, hacienda.getEmpresa());
									}
								}
							}
						}
						Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
						if (xmlFirmadoRespuesta != Constantes.EMPTY) {
							haciendaBD.setMensajeHacienda(FacturaElectronicaUtils.convertirStringToblod(xmlFirmadoRespuesta));
						}
						/**
						 * Esperar el correo FE para saber que ese estado de recibido
						 */
						if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR)) {
							haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA);
						}
						if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO_STR)) {
							haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
						}
						// Hacienda no envia mensaje
						if (respuestaHacienda.mensajeHacienda() != null) {
							if (respuestaHacienda.mensajeHacienda().mensaje() != null) {
								if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO)) {
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA);
								} else if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_RECHAZADO)) {
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
								} else if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO_PARCIAL)) {
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_PARCIAL);
								}
							}
						} else {
							if (!status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR)) {
								if (hacienda.getReintentosAceptacion() == Constantes.MAXIMO_REINTENTOS_ACEPTACION) {
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ERROR);
								} else {
									hacienda.setReintentosAceptacion(hacienda.getReintentosAceptacion() == null ? 1 : hacienda.getReintentosAceptacion() + 1);
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA);
								}
							}
						}
						haciendaBo.modificar(haciendaBD);
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
			}
		} catch (Exception e) {
			log.info("** Error  aceptarDocumento: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre());
			throw e;
		}

	}

	/**
	 * Enviar correos a los clientes que Tributacion acepto documento
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#taskHaciendaEnvioDeCorreos()
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	@Override
	public synchronized void taskHaciendaEnvioDeCorreos() throws Exception {
		try {
			log.info("Inicio Envios de correos  {}", new Date());
			// Listado de los documentos Pendientes de aceptar por hacienda
			Collection<Hacienda> listaHacienda = haciendaBo.findByEstadoAndNotificacion(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA, Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
			for (Hacienda hacienda : listaHacienda) {

				Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
				ArrayList<String> listaCorreos = new ArrayList<String>();
				// Se determina si es una recepcion de factura
				if (hacienda.getTipoDoc().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO) || hacienda.getTipoDoc().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL) || hacienda.getTipoDoc().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_RECHAZADO)) {
					RecepcionFactura recepcionFactura = recepcionFacturaBo.findByConsecutivoAndEmpresa(hacienda.getConsecutivo(), hacienda.getEmpresa());
					if(recepcionFactura != null) {
						listaCorreos.add(recepcionFactura.getEmpresa().getCorreoElectronico());
					}				
					enviarCorreosRecepcion(recepcionFactura, hacienda, listaCorreos);
				} else {
					Factura factura = facturaBo.findByConsecutivoAndEmpresa(hacienda.getConsecutivo(), hacienda.getEmpresa());
					if (factura != null) {
						if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {

							if (factura.getCorreoAlternativo() != null) {
								if (!factura.getCorreoAlternativo().equals(Constantes.EMPTY)) {
									listaCorreos.add(factura.getCorreoAlternativo());
								}
							}
							if (factura.getCliente().getCorreoElectronico() != null) {
								listaCorreos.add(factura.getCliente().getCorreoElectronico());
							}
							if (factura.getCliente().getCorreoElectronico1() != null) {
								if (!factura.getCliente().getCorreoElectronico1().equals(Constantes.EMPTY)) {
									listaCorreos.add(factura.getCliente().getCorreoElectronico1());
								}

							}
							if (factura.getCliente().getCorreoElectronico2() != null) {
								if (!factura.getCliente().getCorreoElectronico2().equals(Constantes.EMPTY)) {
									listaCorreos.add(factura.getCliente().getCorreoElectronico2());
								}

							}
							if (factura.getCliente().getCorreoElectronico3() != null) {
								if (!factura.getCliente().getCorreoElectronico3().equals(Constantes.EMPTY)) {
									listaCorreos.add(factura.getCliente().getCorreoElectronico3());
								}
							}
						}
						listaCorreos.add(factura.getEmpresa().getCorreoElectronico());
						enviarCorreos(factura, hacienda, listaCorreos);
					}
				}
				haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
				haciendaBo.modificar(haciendaBD);
			}
			log.info("Fin Envios de correos  {}", new Date());
		} catch (Exception e) {
			log.info("** Error  taskHaciendaEnvioDeCorreos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Envios de correos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#enviarCorreos(com.emprendesoftcr.modelo.Factura, com.emprendesoftcr.modelo.Hacienda, java.util.ArrayList)
	 */
	@Override
	public void enviarCorreos(Factura factura, Hacienda hacienda, ArrayList<String> listaCorreos) throws Exception {
		try {
			String xmlFactura = FacturaElectronicaUtils.convertirBlodToString(hacienda.getComprobanteXML());
			String xmlRespuesta = FacturaElectronicaUtils.convertirBlodToString(hacienda.getMensajeHacienda());
			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			String clave = getConsecutivo(factura.getTipoDoc(), factura.getNumeroConsecutivo());
			Collection<Attachment> attachments = createAttachments(XML_Attach(clave, factura.getEmpresa().getCedula(), asText(xmlFactura)), PDF_Attach(clave, factura.getEmpresa().getCedula(), asPDF(namePDF)), XML_AttachRespuestaHacienda(clave, factura.getEmpresa().getCedula(), asText(xmlRespuesta)));
			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("clave", clave);
			modelEmail.put("nombreEmpresa", factura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? factura.getEmpresa().getNombre() : factura.getEmpresa().getNombreComercial());
			modelEmail.put("correo", factura.getEmpresa().getCorreoElectronico());
			modelEmail.put("telefono", factura.getEmpresa().getTelefono());
			String from = "FISCO_No_Reply@emprendesoftcr.com";
			String nombre = factura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? factura.getEmpresa().getNombre() : factura.getEmpresa().getNombreComercial();
			String subject = "Documento Electrónico N° " + clave + " del Emisor: " + nombre;
			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailHacienda.vm", modelEmail);
		} catch (Exception e) {
			log.info("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre());
			throw e;
		}
	}
	
	/**
	 * Envios de correos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#enviarCorreos(com.emprendesoftcr.modelo.Factura, com.emprendesoftcr.modelo.Hacienda, java.util.ArrayList)
	 */
	@Override
	public void enviarCorreosRecepcion(RecepcionFactura recepcionFactura, Hacienda hacienda, ArrayList<String> listaCorreos) throws Exception {
		try {
			String xmlFactura = FacturaElectronicaUtils.convertirBlodToString(hacienda.getComprobanteXML());
			String xmlRespuesta = FacturaElectronicaUtils.convertirBlodToString(hacienda.getMensajeHacienda());
			
			String clave = getConsecutivo(hacienda.getTipoDoc(), hacienda.getConsecutivo());

			Collection<Attachment> attachments = createAttachments(
					XML_Attach(clave, recepcionFactura.getEmpresa().getCedula(), asText(xmlFactura)), 
					XML_AttachRespuestaHacienda(clave, recepcionFactura.getEmpresa().getCedula(), asText(xmlRespuesta)));
			
			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("clave", clave);
			modelEmail.put("cedulaEmisor", recepcionFactura.getCedulaEmisor());
			String tipoMensajeTitulo = "";
			if (hacienda.getTipoDoc().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO)) {
				modelEmail.put("tipoMensaje", "aceptación");
				tipoMensajeTitulo = "Aceptación";
			}else if (hacienda.getTipoDoc().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL)){
				modelEmail.put("tipoMensaje", "aceptación parcial");
				tipoMensajeTitulo = "Aceptación Parcial";
			}else if (hacienda.getTipoDoc().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_RECHAZADO)) {
				modelEmail.put("tipoMensaje", "rechazo");
				tipoMensajeTitulo = "Rechazo";
			}
			
			modelEmail.put("tipoMensajeTitulo", tipoMensajeTitulo);
			String from = "FISCO_No_Reply@emprendesoftcr.com";
			String subject = tipoMensajeTitulo + " Documento Electrónico N° " + clave + " del emisor con cédula: " + recepcionFactura.getCedulaEmisor();
			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailHaciendaRecepcionFactura.vm", modelEmail);
		} catch (Exception e) {
			log.info("** Error  enviarCorreosRecepcion: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre());
			throw e;
		}
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private Attachment PDF_Attach(String name, String cedula, ByteArrayDataSource data) {
		return attachment("Factura_PDF_" + cedula + "_" + name, ".pdf", data);
	}

	private String getConsecutivo(String tipoDoc, String consecutivo) {
		return tipoDoc + "-" + consecutivo;
	}

	private ByteArrayDataSource asText(String text) throws IOException {
		return new ByteArrayDataSource(text, "text/plain");
	}

	private Attachment XML_Attach(String name, String cedula, ByteArrayDataSource data) {
		return attachment("Factura_XML_" + cedula + "_" + name, ".xml", data);
	}

	private Attachment XML_AttachRespuestaHacienda(String name, String cedula, ByteArrayDataSource data) {
		return attachment("Respuesta_XML_" + cedula + "_" + name, ".xml", data);
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	private ByteArrayDataSource asPDF(ByteArrayOutputStream stream) {
		return new ByteArrayDataSource(stream.toByteArray(), "text/pdf");
	}

	/**
	 * Firmado de documentos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#procesoFirmado()
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	@Override
	public synchronized void procesoFirmado() throws Exception {
		try {
			log.info("Inicio el proceso de firmado  {}", new Date());
			// Semaforo semaforo = semaforoBo.findByEstado(Constantes.SEMAFORO_ESTADO_FIRMADO);
			// if (semaforo != null) {
			Collection<Factura> lista = facturaBo.findByEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE, Constantes.FACTURA_ESTADO_REFIRMAR_DOCUMENTO);
			for (Factura factura : lista) {
				if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
					String comprobanteXML = Constantes.EMPTY;
					if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
						// Crear XMl sin firma
						comprobanteXML = facturaXMLServices.getCrearXMLSinFirma(factura);
						// firmar el documento
						comprobanteXML = facturaXMLServices.getFirmarXML(comprobanteXML, factura.getEmpresa());
					} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
						// Crear XMl sin firma
						comprobanteXML = tiqueteXMLService.getCrearXMLSinFirma(factura);
						// firmar el documento
						comprobanteXML = tiqueteXMLService.getFirmarXML(comprobanteXML, factura.getEmpresa());
					} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
						// Crear XMl sin firma
						comprobanteXML = notaCreditoXMLServices.getCrearXMLSinFirma(factura);
						// firmar el documento
						comprobanteXML = notaCreditoXMLServices.getFirmarXML(comprobanteXML, factura.getEmpresa());

					} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
						// Crear XMl sin firma
						comprobanteXML = notaDebitoXMLService.getCrearXMLSinFirma(factura);
						// firmar el documento
						comprobanteXML = notaDebitoXMLService.getFirmarXML(comprobanteXML, factura.getEmpresa());
					}
					Hacienda hacienda = new Hacienda();
					hacienda.setCedulaEmisor(factura.getEmpresa().getCedula());
					hacienda.setTipoEmisor(factura.getEmpresa().getTipoCedula());
					// no se graba el cliente si es frecuente
					if (factura.getCliente() != null) {
						if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
							hacienda.setCedulaReceptor(factura.getCliente().getCedula());
							hacienda.setTipoReceptor(factura.getCliente().getTipoCedula());
						}

					}
					hacienda.setEmpresa(factura.getEmpresa());
					hacienda.setClave(factura.getClave());
					hacienda.setFechaEmisor(factura.getFechaEmision());
					Blob b = FacturaElectronicaUtils.convertirStringToblod(comprobanteXML);
					hacienda.setComprobanteXML(b);
					hacienda.setCreated_at(new Date());
					hacienda.setUpdated_at(new Date());
					hacienda.setStatus(Constantes.ZEROS);
					hacienda.setEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML);
					hacienda.setConsecutivo(factura.getNumeroConsecutivo());
					hacienda.setReintentos(Constantes.ZEROS);
					hacienda.setReintentosAceptacion(Constantes.ZEROS);
					hacienda.setTipoDoc(factura.getTipoDoc());
					hacienda.setNombreReceptor(factura.getCliente().getNombreCompleto());
					hacienda.setCorreoReceptor(factura.getCliente().getCorreoElectronico());
					hacienda.setTotalReceptor(factura.getTotalComprobante());
					hacienda.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
					haciendaBo.agregar(hacienda);
					factura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
					factura = factura.getId() == null || factura.getId() == Constantes.ZEROS_LONG ? null : facturaBo.findById(factura.getId());
					if (factura != null) {
						facturaBo.modificar(factura);
					}

				}

			}
			log.info("Fin el proceso de firmado  {}", new Date());

		} catch (Exception e) {
			log.info("** Error  proceso de firmado: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Firmado de documentos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#procesoFirmado()
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	@Override
	public synchronized void procesoFirmadoRecepcionFactura() throws Exception {
		try {
			log.info("Inicio el proceso de firmado de las facturas aceptadas  {}", new Date());
			// Semaforo semaforo = semaforoBo.findByEstado(Constantes.SEMAFORO_ESTADO_FIRMADO);
			// if (semaforo != null) {
			Collection<RecepcionFactura> lista = recepcionFacturaBo.findByEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE, Constantes.FACTURA_ESTADO_REFIRMAR_DOCUMENTO);
			for (RecepcionFactura recepcionFactura : lista) {
					String comprobanteXML = Constantes.EMPTY;
					
					// Crear XMl sin firma
					comprobanteXML = recepcionFacturaXMLServices.getCrearXMLSinFirma(recepcionFactura);
					comprobanteXML = recepcionFacturaXMLServices.getFirmarXML(comprobanteXML,recepcionFactura.getEmpresa());

					
					//Se cargan los datos de la factura, el emisor es el que envia la factura para su aprobacion
					Hacienda hacienda = new Hacienda();
					hacienda.setCedulaEmisor(recepcionFactura.getCedulaEmisor());
					hacienda.setTipoEmisor(recepcionFactura.getTipoCedulaEmisor());
					hacienda.setClave(recepcionFactura.getClave());
					hacienda.setFechaEmisor(recepcionFactura.getFechaEmision());

					
					//Se cargan los datos del emisor, empresa que recibe la factura
					hacienda.setComprobanteXML(FacturaElectronicaUtils.convertirStringToblod(comprobanteXML));
					hacienda.setCreated_at(new Date());
					hacienda.setUpdated_at(new Date());
					hacienda.setStatus(Constantes.ZEROS);
					hacienda.setEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML);
					hacienda.setConsecutivo(recepcionFactura.getNumeroConsecutivoReceptor());
					hacienda.setReintentos(Constantes.ZEROS);
					hacienda.setReintentosAceptacion(Constantes.ZEROS);
					
					String tipoDoc = "";
					hacienda.setTipoDoc(tipoDoc);
					hacienda.setEmpresa(recepcionFactura.getEmpresa());					
					hacienda.setNombreReceptor(recepcionFactura.getEmpresa().getNombreComercial());
					hacienda.setCorreoReceptor(recepcionFactura.getEmpresa().getCorreoElectronico());
					hacienda.setTotalReceptor(recepcionFactura.getTotalFactura());
					hacienda.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
					haciendaBo.agregar(hacienda);
					recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
					recepcionFactura = recepcionFactura.getId() == null || recepcionFactura.getId() == Constantes.ZEROS_LONG ? null : recepcionFacturaBo.findById(recepcionFactura.getId());
					if (recepcionFactura != null) {
						recepcionFacturaBo.modificar(recepcionFactura);
					}					
				}
			log.info("Fin el proceso de firmado  {}", new Date());

		} catch (Exception e) {
			log.info("** Error  proceso de firmado: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

}

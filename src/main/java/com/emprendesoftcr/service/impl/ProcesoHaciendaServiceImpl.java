package com.emprendesoftcr.service.impl;

import static com.emprendesoftcr.fisco.Keys.ERROR;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;
import static java.util.stream.Collectors.toList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.FacturaBo;
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
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.Semaforo;
import com.emprendesoftcr.pdf.App;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.service.RespuestaHaciendaXMLService;
import com.emprendesoftcr.type.RespuestaHacienda;
import com.emprendesoftcr.type.json.RespuestaHaciendaJson;
import com.google.common.base.Function;

/**
 * Servicio de envio de los documentos de hacienda
 **/

@Transactional(rollbackOn = Exception.class)
@Service("procesoHaciendaService")
public class ProcesoHaciendaServiceImpl implements ProcesoHaciendaService {

	private static final Function<String, String>											BIND_CONDICION_VENTA						= (id) -> id.equals("01") ? "Contado" : id.equals("02") ? "Credito" : id.equals("03") ? "Consignacion" : id.equals("04") ? "Apartado" : id.equals("05") ? "Arrendamiento con opcion de compra" : id.equals("06") ? "Arrendamiento en funcion financiera" : "Otros";

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

																																																			facturaElectronica.setEmisorNombre(d.getEmpresa().getNombre());
																																																			facturaElectronica.setEmisorCedula(d.getEmpresa().getCedula());
																																																			facturaElectronica.setEmisorTelefono(d.getEmpresa().getCodigoPais() + "-" + d.getEmpresa().getTelefono().toString());
																																																			facturaElectronica.setEmisorCorreo(d.getEmpresa().getCorreoElectronico());
																																																			// Cliente
																																																			if (!d.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
																																																				facturaElectronica.setClienteNombre(d.getCliente().getNombreCompleto());
																																																				facturaElectronica.setClienteNombreComercial(d.getCliente().getNombreComercial());
																																																				facturaElectronica.setClienteCorreo(d.getCliente().getCorreoElectronico());
																																																				facturaElectronica.setClienteCedula(d.getCliente().getTipoCedula() + "-" + d.getCliente().getCedula());
																																																				facturaElectronica.setClienteTelefono(String.format("%s-%s", d.getCliente().getTelefono().toString().substring(0, 4), d.getCliente().getTelefono().toString().substring(4, 8)));

																																																			}
																																																			// facturaElectronica.setClienteMesCobro(TO_MESCOBRO.apply(d.getMesCobro()));
																																																			// Ubicacion
																																																			facturaElectronica.set_logo(d.getEmpresa().getLogo());
																																																			facturaElectronica.set_clienteDireccion(d.getDireccion());
																																																			// Otros
																																																			facturaElectronica.setTipoDocumento(FacturaElectronicaUtils.getTipoDocumento(d.getTipoDoc()));
																																																			facturaElectronica.setClave(d.getClave());
																																																			facturaElectronica.setConsecutivo(d.getNumeroConsecutivo());
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
																																																					facturaElectronica.setReferencia(d.getReferenciaCodigo());
																																																				}

																																																			} else {
																																																				facturaElectronica.setReferencia(Constantes.EMPTY);
																																																			}
																																																			// Agrega sus detalles
																																																			List<DetalleFacturaElectronica> detalles = d.getDetalles().stream().map(TO_DETALLE).collect(toList());
																																																			facturaElectronica.setDetalleFacturaElectronica(detalles);
																																																			return facturaElectronica;
																																																		};

	private Logger																										log															= LoggerFactory.getLogger(this.getClass());

	@Lazy
	@Autowired
	HaciendaBo																												haciendaBo;

	@Lazy
	@Autowired
	RespuestaHaciendaXMLService																				respuestaHaciendaXMLService;

	@Lazy
	@Autowired
	SemaforoBo																												semaforoBo;

	@Lazy
	@Autowired
	CorreosBo																													correosBo;

	@Lazy
	@Autowired
	OpenIDConnectHaciendaComponent																		openIDConnect;

	@Lazy
	@Autowired
	EnvioHaciendaComponent																						envioHaciendaComponent;

	@Autowired
	FacturaBo																													facturaBo;

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
						Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
						if (haciendaBD.getReintentos() < semaforo.getMaximoReintentosEnviar()) {
							envioHacienda(haciendaBD);
						} else {// Si alcanza el maximo reintentos no se envia mas a la hacienda
							haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_TOPE_REINTENTOS);
							haciendaBD.setUpdated_at(new Date());
							haciendaBo.modificar(haciendaBD);
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
						Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
						if (haciendaBD.getReintentosAceptacion() < semaforo.getMaximoReintentosEnviar()) {
							aceptarDocumento(hacienda);
						} else {// Si alcanza el maximo reintentos de aceptacion mas a la hacienda
							haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTACION_HACIENDA_TOPE_REINTENTOS);
							haciendaBD.setUpdated_at(new Date());
							haciendaBo.modificar(haciendaBD);
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
					String xmlSinFirmarRespuesta = respuestaHaciendaXMLService.getCrearXMLSinFirma(respuesta);
					String xmlFirmadoRespuesta = respuestaHaciendaXMLService.getFirmarXML(xmlSinFirmarRespuesta, hacienda.getEmpresa());
					Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
					haciendaBD.setMensajeHacienda(FacturaElectronicaUtils.convertirStringToblod(xmlFirmadoRespuesta));
					if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR)) {
						haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA);
					}
					if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO_STR)) {
						haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
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

		} catch (Exception e) {
			log.info("** Error  aceptarDocumento: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Enviar correos a los clientes que Tributacion acepto documento
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#taskHaciendaEnvioDeCorreos()
	 */
	//@Scheduled(cron = "0 0/2 * * * ?")
	@Override
	public synchronized void taskHaciendaEnvioDeCorreos() throws Exception {
		try {

			// Listado de los documentos Pendientes de aceptar por hacienda
			Collection<Hacienda> listaHacienda = haciendaBo.findByEstadoAndNotificacion(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA, Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
			for (Hacienda hacienda : listaHacienda) {
					Factura factura = facturaBo.findByConsecutivoAndEmpresa(hacienda.getConsecutivo(), hacienda.getEmpresa());
					Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
					if (factura != null) {
						enviarCorreos(factura, hacienda);
						haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
						haciendaBo.modificar(haciendaBD);
					}

			}

		} catch (Exception e) {
			log.info("** Error  taskHaciendaEnvioDeCorreos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	private void enviarCorreos(Factura factura, Hacienda hacienda) throws Exception {
		try {
			String xmlFactura = FacturaElectronicaUtils.convertirBlodToString(hacienda.getComprobanteXML());
			String xmlRespuesta = FacturaElectronicaUtils.convertirBlodToString(hacienda.getMensajeHacienda());

			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);

			String clave = getConsecutivo(factura.getTipoDoc(), factura.getNumeroConsecutivo());
			Collection<Attachment> attachments = createAttachments(XML_Attach(clave, factura.getEmpresa().getCedula(), asText(xmlFactura)), PDF_Attach(clave,factura.getEmpresa().getCedula(), asPDF(namePDF)), XML_AttachRespuestaHacienda(clave, factura.getEmpresa().getCedula(),asText(xmlRespuesta)));

			Map<String, Object> modelEmail = new HashMap<>();

			modelEmail.put("clave", clave);
			modelEmail.put("nombreEmpresa", factura.getEmpresa().getNombre());
			modelEmail.put("correo", factura.getEmpresa().getCorreoElectronico());
			modelEmail.put("telefono", factura.getEmpresa().getTelefono());
			String to = "josehernandezchaverri@gmail.com";
			String from = "FISCO_No_Reply@emprendesoftcr.com";
			String subject = "Factura Electrónica N° " + clave + " del Emisor: " + factura.getEmpresa().getNombre();

			//
			correosBo.enviarConAttach(attachments, to, from, subject, "email/emailHacienda.vm", modelEmail);
			//
		} catch (Exception e) {
			log.info("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private Attachment PDF_Attach(String name,String cedula, ByteArrayDataSource data) {
		return attachment("Factura_PDF_"+cedula+"_"+name, ".pdf", data);
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

	private Attachment XML_AttachRespuestaHacienda(String name,String cedula, ByteArrayDataSource data) {
		return attachment("Respuesta_XML_" + cedula + "_" + name  , ".xml", data);
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	private ByteArrayDataSource asPDF(ByteArrayOutputStream stream) {
		return new ByteArrayDataSource(stream.toByteArray(), "text/pdf");
	}

}

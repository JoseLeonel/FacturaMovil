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

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.DetalleBo;
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
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.pdf.ReportePdfView;
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
@EnableTransactionManagement
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
																																																			facturaElectronica.setEmisorNombreComercial(!d.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? d.getEmpresa().getNombreComercial() : Constantes.EMPTY);
																																																			facturaElectronica.setEmisorNombre(!d.getEmpresa().getNombre().equals(Constantes.EMPTY) ? d.getEmpresa().getNombre() : d.getEmpresa().getNombre());
																																																			facturaElectronica.setEmisorCedula(d.getEmpresa().getCedula());
																																																			facturaElectronica.setEmisorDireccion(d.getEmpresa().getOtraSenas());
																																																			facturaElectronica.setEmisorTelefono(d.getEmpresa().getCodigoPais() + "-" + d.getEmpresa().getTelefono().toString());
																																																			facturaElectronica.setEmisorCorreo(d.getEmpresa().getCorreoElectronico());
																																																			facturaElectronica.set_nota(d.getNota() == null ? Constantes.EMPTY : d.getNota());

																																																			facturaElectronica.setClienteNombre(d.getCliente().getNombreCompleto());
																																																			facturaElectronica.setClienteNombreComercial(d.getCliente().getNombreComercial());
																																																			
																																																			facturaElectronica.setClienteCorreo(d.getCliente().getCorreoElectronico());
																																																			facturaElectronica.setClienteCedula(d.getCliente().getCedula());
																																																			if(d.getCliente().getTelefono() != null ) {
																																																				if(d.getCliente().getTelefono() != Constantes.ZEROS ) {
																																																					facturaElectronica.setClienteTelefono(d.getCliente().getTelefono().toString());  		
																																																				}else {
																																																					facturaElectronica.setClienteTelefono(Constantes.EMPTY);
																																																				}
																																																			}

																																																			if(d.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_FRECUENTE)) {
																																																				if(d.getNombreFactura() !=null) {
																																																					if(!d.getNombreFactura().equals(Constantes.EMPTY)) {
																																																						facturaElectronica.setClienteNombre(d.getNombreFactura());
																																																					}
																																																				}
																																																			}
																																																			facturaElectronica.setFooterTotalDescuento(d.getTotalDescuentos());
																																																			facturaElectronica.setFooterTotalServiciosGravados(d.getTotalServGravados());
																																																			facturaElectronica.setFooterTotalMercanciasGravadas(d.getTotalMercanciasGravadas());
																																																			//Total Factura
																																																			facturaElectronica.setFooterTotalServiciosExentos(d.getTotalServExentos());
																																																			facturaElectronica.setFooterTotalGravado(d.getTotalGravado());
																																																			facturaElectronica.setFooterTotalExento(d.getTotalExento());
																																																			facturaElectronica.setFooterTotalVenta(d.getTotalVenta());
																																																			facturaElectronica.setFooterTotalDescuento(d.getTotalDescuentos());
																																																			facturaElectronica.setFooterTotalImpuesto(d.getTotalImpuesto());
																																																			facturaElectronica.setFooterTotalVentaNeta(d.getTotalVentaNeta());
																																																			facturaElectronica.setFooterTotalComprobante(d.getTotalComprobante());
//																																													
																																																			// }
																																																			// facturaElectronica.setClienteMesCobro(TO_MESCOBRO.apply(d.getMesCobro()));
																																																			// Ubicacion
																																																			facturaElectronica.set_logo(d.getEmpresa().getLogo());
																																																			facturaElectronica.set_clienteDireccion(d.getDireccion());
																																																			// Otros
																																																			facturaElectronica.setTipoDocumento(FacturaElectronicaUtils.getTipoDocumento(d.getTipoDoc()));
																																																			facturaElectronica.setClave(d.getClave() == null ? Constantes.EMPTY : d.getClave());
																																																			facturaElectronica.setConsecutivo(d.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) ? d.getId().toString() : d.getNumeroConsecutivo());
																																																			facturaElectronica.setFechaEmision(d.getFechaEmision().toString());
																																																			facturaElectronica.setPlazoCredito(d.getPlazoCredito() != null ? d.getPlazoCredito().toString() : Constantes.EMPTY);
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
																																																			// List<DetalleFacturaElectronica> detalles = d.getDetalles().stream().map(TO_DETALLE).collect(toList());
																																																			// facturaElectronica.setDetalleFacturaElectronica(detalles);
																																																			return facturaElectronica;
																																																		};

	private Logger																										log															= LoggerFactory.getLogger(this.getClass());

	@Autowired
	HaciendaBo																												haciendaBo;

	@Autowired
	RespuestaHaciendaXMLService																				respuestaHaciendaXMLService;

	@Autowired
	SemaforoBo																												semaforoBo;

	@Autowired
	CorreosBo																													correosBo;

	@Autowired
	OpenIDConnectHaciendaComponent																		openIDConnect;

	@Autowired
	EnvioHaciendaComponent																						envioHaciendaComponent;

	@Autowired
	FacturaBo																													facturaBo;

	@Autowired
	DetalleBo																													detalleBo;

	@Autowired
	RecepcionFacturaBo																								recepcionFacturaBo;

	@Autowired
	CertificadoBo																											certificadoBo;

	@Autowired
	FacturaXMLServices																								facturaXMLServices;

	@Autowired
	TiqueteXMLService																									tiqueteXMLService;

	@Autowired
	NotaCreditoXMLServices																						notaCreditoXMLServices;

	@Autowired
	NotaDebitoXMLService																							notaDebitoXMLService;

	@Autowired
	RecepcionFacturaXMLServices																				recepcionFacturaXMLServices;

	/**
	 * Proceso automatico para ejecutar el envio de los documentos de hacienda documentos xml ya firmados
	 */
	@Scheduled(cron = "0 0/12 * * * ?")
	@Override
	public synchronized void taskHaciendaEnvio() throws Exception {

		ArrayList<Hacienda> facturasConProblemas = new ArrayList<Hacienda>();
		OpenIDConnectHacienda openIDConnectHacienda = null;
		Hacienda haciendaBD = null;
		try {
			log.info("Inicio Proceso de Envio de documentos  {}", new Date());
			// Listado de los documentos Pendientes de enviar hacienda
			Collection<Hacienda> listaHacienda = haciendaBo.findByEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML, Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR);
			if (listaHacienda != null) {
				if (!listaHacienda.isEmpty()) {
					for (Hacienda hacienda : listaHacienda) {
						try {
							log.info("Documentos hacienda:" + hacienda.getConsecutivo() + " Empresa" + hacienda.getEmpresa().getNombre());
							haciendaBD = haciendaBo.findById(hacienda.getId());
							openIDConnectHacienda = envioHacienda(haciendaBD, openIDConnectHacienda);
						} catch (Exception e) {
							// Se modifica el registros
							if (hacienda.getReintentos() == null) {
								hacienda.setReintentos(Constantes.ZEROS);
							}
							if (hacienda.getReintentos() > Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_TOPE_REINTENTOS) {
								hacienda.setNotificacion(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA);
								hacienda.setEstado(Constantes.HACIENDA_ESTADO_PROBLEMA_ENVIO_NO_TRASABLE);
								facturasConProblemas.add(hacienda);
							} else {
								hacienda.setReintentos(hacienda.getReintentos() == null ? 1 : hacienda.getReintentos() + 1);
							}
							haciendaBo.modificar(hacienda);
							log.info("** Error1  taskHaciendaEnvio: " + e.getMessage() + " fecha :" + new Date() + "Empresa:" + hacienda.getEmpresa().getNombre() + " Consecutivo :" + hacienda.getConsecutivo());
						}

					}

				}
			}
			log.info("Finaliza Proceso de Envio de documentos  {}", new Date());
		} catch (Exception e) {
			log.info("** Error2  taskHaciendaEnvio: " + e.getMessage() + " fecha " + new Date());
			throw e;
		} finally {
			// Desconectar token de hacienda anterior
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(openIDConnectHacienda.getEmpresa(), openIDConnectHacienda);
				}
			}
		}
		if (facturasConProblemas != null) {
			if (!facturasConProblemas.isEmpty()) {
				soporteProblemaConHacienda(facturasConProblemas);
			}
		}

	}

	/**
	 * Para cuando hay problemas de conexion con hacienda
	 * @param FacturasConProblemas
	 * @throws Exception
	 */
	private void soporteProblemaConHacienda(ArrayList<Hacienda> FacturasConProblemas) throws Exception {
		try {
			for (Hacienda hacienda : FacturasConProblemas) {
				String subject = "EmpredesoftSoporte  Empresa :" + hacienda.getEmpresa().getNombre() + " Problemas de conexion";
				String texto = "Empresa :" + hacienda.getEmpresa().getNombre() + " tiene  Problemas de conexion" + " Consecutivo de Factura : " + hacienda.getConsecutivo();
				correosBo.sendSimpleMessage("josehernandezchaverri@gmail.com", subject, texto);
				correosBo.sendSimpleMessage("vivianamartinezgranados@gmail.com", subject, texto);
				correosBo.sendSimpleMessage("jcisneroscr@gmail.com", subject, texto);

			}
		} catch (Exception e) {
			log.info("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Comunicacion con Hacienda para aceptar el documento
	 * @param hacienda
	 * @throws Exception
	 */

	@Override
	public OpenIDConnectHacienda envioHacienda(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception {

		// Se verifica si es la misma empresa de la conexion anterior o bien si es null
		if ((openIDConnectHacienda == null) || (openIDConnectHacienda != null && !hacienda.getEmpresa().getId().equals(openIDConnectHacienda.getEmpresa().getId()))) {

			// Desconectar token de hacienda anterior
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(hacienda.getEmpresa(), openIDConnectHacienda);
				}
			}

			// Obtener el token en hacienda para enviar los documentos
			openIDConnectHacienda = openIDConnect.getToken(hacienda.getEmpresa());
		}

		try {
			// Se obtuvo el token de accienda
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getAccess_token().length() > 0) {
					envioHaciendaFacturas(hacienda, openIDConnectHacienda);
				}
			} else {
				log.info("** Error no se encontro el token   " + "Empresa:" + hacienda.getEmpresa().getNombre() + " fecha " + new Date() + "Consecutivo " + hacienda.getConsecutivo());
			}
		} catch (Exception e) {
			log.info("** Error  ejecutarEnvio: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return openIDConnectHacienda;
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

			valor = valor.replaceAll("\n", "");

			if (valor.length() > 0) {

				String base64 = FacturaElectronicaUtils.base64Encode(valor.getBytes("UTF-8"));

				recepcion.setComprobanteXml(base64);

				// Ambiente de pruebas
				// recepcion.setCallbackUrl(Constantes.URL_PRUEBAS_CALLBACK);

				// San Ana
				// recepcion.setCallbackUrl(Constantes.URL_SANTA_ANA_CALLBACK);

				// Guanacaste
				 recepcion.setCallbackUrl(Constantes.URL_GUANACASTE_CALLBACK);
				// JacoDos
			//	recepcion.setCallbackUrl(Constantes.URL_JACODOS_CALLBACK);

				// Jaco
				 //recepcion.setCallbackUrl(Constantes.URL_JACO_CALLBACK);
				// Inventario
				// recepcion.setCallbackUrl(Constantes.URL_INVENTARIO_CALLBACK);
				
				

				// Alajuela
				// recepcion.setCallbackUrl(Constantes.URL_ALAJUELA_CALLBACK);

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
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#taskHaciendaComprobacionDocumentos()
	 */
	@Scheduled(cron = "0 0/40 * * * ?")
	@Override
	public synchronized void taskHaciendaComprobacionDocumentos() throws Exception {
		OpenIDConnectHacienda openIDConnectHacienda = null;
		Hacienda haciendaBD = null;
		try {
			log.info("Inicio Comprobacion de documentos  {}", new Date());
			// Semaforo semaforo = semaforoBo.findByEstado(Constantes.SEMAFORO_ESTADO_COMPROBAR_DOCUMENTOS);
			// Listado de los documentos Pendientes de aceptar por hacienda
			Collection<Hacienda> listaHacienda = haciendaBo.findByEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA, Constantes.HACIENDA_ESTADO_ERROR);
			if (listaHacienda != null) {
				if (!listaHacienda.isEmpty()) {
					for (Hacienda hacienda : listaHacienda) {
						try {

							Date fecha = new Date();
							long tiempoInicial = hacienda.getCreated_at().getTime();
							long tiempoFinal = fecha.getTime();
							long resta = tiempoFinal - tiempoInicial;
							// el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
							if (resta > 0) {
								resta = resta / (1000 * 60);
							}
							if (resta > 120 || hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ERROR) || hacienda.getTipoDoc().equals(Constantes.HACIENDA_TIPODOC_COMPRAS)) {
								log.info("Comprobando Documentos hacienda:" + hacienda.getConsecutivo() + " Empresa" + hacienda.getEmpresa().getNombre());
								if (hacienda.getReintentosAceptacion() != null) {
									if (hacienda.getReintentosAceptacion() <= Constantes.MAXIMO_REINTENTOS_ACEPTACION) {
										haciendaBD = haciendaBo.findById(hacienda.getId());
										openIDConnectHacienda = aceptarDocumento(haciendaBD, openIDConnectHacienda);

									} else {
										haciendaBD = haciendaBo.findById(hacienda.getId());
										haciendaBD.setObservacion(FacturaElectronicaUtils.convertirStringToblod(Constantes.MAXIMO_REINTENTOS_ACEPTACION_STR));
										haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
										haciendaBD.setCallBack(Constantes.CALLBACKURL_NO);
										haciendaBo.modificar(haciendaBD);
									}
								} else {
									haciendaBD = haciendaBo.findById(hacienda.getId());
									haciendaBD.setReintentosAceptacion(Constantes.ZEROS);
									haciendaBo.modificar(haciendaBD);
								}

							}

						} catch (Exception e) {
							log.info("** Error1  ComprobacionDocumentos: " + e.getMessage() + " fecha " + new Date());
						}
					}
				}
			}

			log.info("Fin Comprobacion de documentos  {}", new Date());

		} catch (Exception e) {
			log.info("** Error2  ComprobacionDocumentos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		} finally {
			// Desconectar token de hacienda anterior
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(openIDConnectHacienda.getEmpresa(), openIDConnectHacienda);
				}
			}
		}
	}

	/**
	 * Aceptar documentos
	 * @param hacienda
	 */
	@Override
	public OpenIDConnectHacienda aceptarDocumento(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception {
		try {

			// Se verifica si es la misma empresa de la conexion anterior o bien si es null
			if ((openIDConnectHacienda == null) || (openIDConnectHacienda != null && !hacienda.getEmpresa().getId().equals(openIDConnectHacienda.getEmpresa().getId()))) {

				// Desconectar token de hacienda anterior
				if (openIDConnectHacienda != null) {
					if (openIDConnectHacienda.getRefresh_token().length() > 0) {
						openIDConnect.desconectarToken(hacienda.getEmpresa(), openIDConnectHacienda);
					}
				}

				// Obtener el token en hacienda para enviar los documentos
				openIDConnectHacienda = openIDConnect.getToken(hacienda.getEmpresa());
			}

			if (openIDConnectHacienda != null) {

				// Se obtuvo el token de accienda
				if (openIDConnectHacienda.getAccess_token().length() > 0) {
					String idp_uri_documentos = Constantes.EMPTY;
					if (hacienda.getEmpresa().getEstadoProduccion() != null) {
						if (hacienda.getEmpresa().getEstadoProduccion().equals(Constantes.ESTADO_ACTIVO)) {
							idp_uri_documentos = Constantes.IDP_URI_DOCUMENTOS_PRODUCCION;
						}
					}
					@SuppressWarnings("rawtypes")
					Map response = envioHaciendaComponent.comprobarDocumentoElectronico(idp_uri_documentos, hacienda.getClave(), openIDConnectHacienda);
					String body = (String) response.get(POST_RESPONSE);
					if (body != null && body != "" && body != "{}" && !body.contains("El comprobante") && !body.contains("no ha sido recibido")) {

						RespuestaHacienda respuestaHacienda = RespuestaHaciendaJson.from(body);

						String status = getHaciendaStatus(respuestaHacienda.indEstado());
						hacienda.setUpdated_at(new Date());
						RespuestaHaciendaXML respuesta = new RespuestaHaciendaXML();
						// hacienda.setxErrorCause(FacturaElectronicaUtils.convertirStringToblod(respuesta.getDetalleMensaje()==null?Constantes.EMPTY:respuesta.getDetalleMensaje()));
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
								if (haciendaBD.getReintentosAceptacion() == Constantes.MAXIMO_REINTENTOS_ACEPTACION) {
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ERROR);
								} else {
									haciendaBD.setReintentosAceptacion(haciendaBD.getReintentosAceptacion() == null ? 1 : haciendaBD.getReintentosAceptacion() + 1);
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA);
								}
							}
						}
						haciendaBD.setObservacion(respuestaHacienda.mensajeHacienda() != null ? FacturaElectronicaUtils.convertirStringToblod(respuestaHacienda.mensajeHacienda().detalleMensaje()) : null);
						haciendaBD.setCallBack(Constantes.CALLBACKURL_NO);
						haciendaBo.modificar(haciendaBD);
					} else {// sumar reintententos
						if (body.contains("El comprobante") && body.contains("no ha sido recibido")) {
							hacienda.setxErrorCause(FacturaElectronicaUtils.convertirStringToblod(body));
							hacienda.setObservacion(FacturaElectronicaUtils.convertirStringToblod(body));
							hacienda.setEstado(Constantes.HACIENDA_ESTADO_ERROR);

						} else {
							hacienda.setReintentosAceptacion(hacienda.getReintentosAceptacion() == null ? 1 : hacienda.getReintentosAceptacion() + 1);
						}
						hacienda.setCallBack(Constantes.CALLBACKURL_NO);
						haciendaBo.modificar(hacienda);

					}
				}
			}
		} catch (Exception e) {
			log.info("** Error  aceptarDocumento: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre());
			throw e;
		}

		return openIDConnectHacienda;
	}

	/**
	 * Solo se van enviar correos a la empresa cuando es un cliente o correo alternativo los tiquetes de clientes frecuentes no lo vamos enviar para ver el comportamiento de rendimiento Enviar correos a los clientes que Tributacion acepto documento
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#taskHaciendaEnvioDeCorreos()
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	@Override
	public synchronized void taskHaciendaEnvioDeCorreos() throws Exception {
		try {
			log.info("Inicio Envios de correos  {}", new Date());
			Boolean noEnviarCorreoClienteFrecuente = false;
			// Listado de los documentos Pendientes de aceptar por hacienda
			Collection<Hacienda> listaHacienda = haciendaBo.findByEstadoAndNotificacion(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA, Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
			for (Hacienda hacienda : listaHacienda) {
				try {
					noEnviarCorreoClienteFrecuente = false;
					Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
					ArrayList<String> listaCorreos = new ArrayList<String>();
					// Se determina si es una recepcion de factura
					if (!haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) && !haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
						RecepcionFactura recepcionFactura = recepcionFacturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());
						if (recepcionFactura != null) {
							listaCorreos.add(recepcionFactura.getEmpresa().getCorreoElectronico());
							if (recepcionFactura.getEmisorCorreo() != null) {
								if (!recepcionFactura.getEmisorCorreo().equals(Constantes.EMPTY)) {
									listaCorreos.add(recepcionFactura.getEmisorCorreo());
								}
							}
						}
						if (listaCorreos != null) {
							if (!listaCorreos.isEmpty()) {
								enviarCorreosRecepcion(recepcionFactura, haciendaBD, listaCorreos);
								haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
								haciendaBo.modificar(haciendaBD);
							}
						}
					} else {
						Factura factura = facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());
						if (factura != null) {
							if (factura.getCorreoAlternativo() != null) {
								if (!factura.getCorreoAlternativo().equals(Constantes.EMPTY)) {
									listaCorreos.add(factura.getCorreoAlternativo());
								}
							}
							if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
								if (factura.getCliente().getCorreoElectronico() != null) {
									if (!factura.getCliente().getCorreoElectronico().equals(Constantes.EMPTY)) {
										listaCorreos.add(factura.getCliente().getCorreoElectronico());
									}
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
								listaCorreos.add(factura.getEmpresa().getCorreoElectronico());
							} else {// Cuando un cliente quiere que le llegue un correo alternativo
								haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_FRECUENTE_NO_ENVIADO);
								haciendaBo.modificar(haciendaBD);
								noEnviarCorreoClienteFrecuente = true;

							}

						}
						if (listaCorreos != null) {
							if (!listaCorreos.isEmpty()) {
								enviarCorreos(factura, haciendaBD, listaCorreos);
								if (noEnviarCorreoClienteFrecuente == false) {
									haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
									haciendaBo.modificar(haciendaBD);

								}

							}
						}

					}

				} catch (Exception e) {
					// Se modifica el registros
					Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
					if (haciendaBD != null) {
						haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_PROBLEMA_ENVIO_CORREO);
						haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
						haciendaBo.modificar(haciendaBD);
						soporteProblemaEnvioCorreos(haciendaBD.getEmpresa(), haciendaBD.getConsecutivo(), e);

					}

					log.info("** Error1  taskHaciendaEnvioDeCorreos: " + e.getMessage() + " fecha " + new Date());
				}
			}
			log.info("Fin Envios de correos  {}", new Date());
		} catch (Exception e) {
			log.info("** Error2  taskHaciendaEnvioDeCorreos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	private void soporteProblemaEnvioCorreos(Empresa empresa, String consecutivo, Exception error) throws Exception {
		try {
			String subject = "EmpredesoftSoporte  Empresa :" + empresa.getNombre() + " Problemas de conexion del correo";
			String texto = "Empresa :" + empresa.getNombre() + " tiene  Problemas de conexion" + " Consecutivo de Factura : " + consecutivo + error.getMessage();
			correosBo.sendSimpleMessage("josehernandezchaverri@gmail.com", subject, texto);
			correosBo.sendSimpleMessage("vivianamartinezgranados@gmail.com", subject, texto);
			correosBo.sendSimpleMessage("jcisneroscr@gmail.com", subject, texto);

		} catch (Exception e) {
			log.info("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date());
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
			Collection<Detalle> detalles = detalleBo.findByFactura(factura);
			List<DetalleFacturaElectronica> detallesFactura = detalles.stream().map(TO_DETALLE).collect(toList());
			facturaElectronica.setDetalleFacturaElectronica(detallesFactura);

			
			// ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			ByteArrayOutputStream namePDF = ReportePdfView.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			String clave = getConsecutivo(factura.getTipoDoc(), factura.getNumeroConsecutivo());
			Collection<Attachment> attachments = createAttachments(XML_Attach(clave, factura.getEmpresa().getCedula(), asText(xmlFactura), factura.getTipoDoc()), PDF_Attach(clave, factura.getEmpresa().getCedula(), asPDF(namePDF), factura.getTipoDoc()), XML_AttachRespuestaHacienda(clave, factura.getEmpresa().getCedula(), asText(xmlRespuesta)));
			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("clave", clave);
			modelEmail.put("nombreEmpresa", factura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? factura.getEmpresa().getNombre() : factura.getEmpresa().getNombreComercial());
			modelEmail.put("correo", factura.getEmpresa().getCorreoElectronico());
			modelEmail.put("telefono", factura.getEmpresa().getTelefono());
			String from = "FISCO_No_Reply@emprendesoftcr.com";
			if (factura.getEmpresa().getAbreviaturaEmpresa() != null) {
				if (!factura.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
					from = factura.getEmpresa().getAbreviaturaEmpresa() + "_Doc_Electronico" + "_No_Reply@emprendesoftcr.com";
				}
			}

			String nombre = factura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? factura.getEmpresa().getNombre() : factura.getEmpresa().getNombreComercial();
			String subject = "Documento Electrónico N° " + clave + " del Emisor: " + nombre;
			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailHacienda.vm", modelEmail);
		} catch (Exception e) {
			log.info("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre() + " Consecutivo" + hacienda.getConsecutivo());
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
			String tipoDoc = "compra";
			if (hacienda != null) {
				if (!hacienda.getTipoDoc().equals(Constantes.EMPTY)) {
					tipoDoc = hacienda.getTipoDoc();
				}
			}
			String consecutivoGenerado = getConsecutivo(tipoDoc, recepcionFactura.getNumeroConsecutivoReceptor());

			Collection<Attachment> attachments = createAttachments(XML_Attach(consecutivoGenerado, recepcionFactura.getEmpresa().getCedula(), asText(xmlFactura), tipoDoc), XML_AttachRespuestaHacienda(consecutivoGenerado, recepcionFactura.getEmpresa().getCedula(), asText(xmlRespuesta)));

			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("clave", recepcionFactura.getFacturaClave());
			modelEmail.put("cedulaEmisor", recepcionFactura.getEmisorCedula());
			String tipoMensajeTitulo = "";
			if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO) || recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_1)) {
				modelEmail.put("tipoMensaje", "aceptado");
				tipoMensajeTitulo = "Aceptacion";
			} else if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL) || recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL_1)) {
				modelEmail.put("tipoMensaje", "aceptación parcial");
				tipoMensajeTitulo = "Aceptacion_Parcial";
			} else if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_RECHAZADO) || recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_RECHAZADO_1)) {
				modelEmail.put("tipoMensaje", "rechazo");
				tipoMensajeTitulo = "Rechazo";
			}

			modelEmail.put("tipoMensajeTitulo", tipoMensajeTitulo);
			String nombreProveedor = Constantes.EMPTY;
			String nombreEmpresa = Constantes.EMPTY;
			nombreProveedor = recepcionFactura.getEmisorNombre() != null ? recepcionFactura.getEmisorNombre() : Constantes.EMPTY;
			if (recepcionFactura.getEmpresa().getNombreComercial() != null) {
				if (!recepcionFactura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY)) {
					nombreEmpresa = recepcionFactura.getEmpresa().getNombreComercial();
				}
			}
			nombreEmpresa = nombreEmpresa.equals(Constantes.EMPTY) ? recepcionFactura.getEmpresa().getNombre() : nombreEmpresa;
			modelEmail.put("nombreProveedor", nombreProveedor);
			modelEmail.put("empresa", nombreEmpresa);
			modelEmail.put("telefono", recepcionFactura.getEmpresa().getTelefono());
			modelEmail.put("correo", recepcionFactura.getEmpresa().getCorreoElectronico());
			modelEmail.put("consecutivoCompra", recepcionFactura.getFacturaConsecutivo() != null ? recepcionFactura.getFacturaConsecutivo() : Constantes.EMPTY);
			modelEmail.put("consecutivoGenerado", consecutivoGenerado);
			String from = tipoMensajeTitulo + "_Reply@emprendesoftcr.com";

			if (hacienda.getEmpresa().getAbreviaturaEmpresa() != null) {
				if (!hacienda.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
					from = hacienda.getEmpresa().getAbreviaturaEmpresa() + "_" + tipoMensajeTitulo + "_No_Reply@emprendesoftcr.com";
				}
			}

			String subject = nombreEmpresa + " Aceptando o Rechazando Compras documento Electrónico N° " + recepcionFactura.getNumeroConsecutivoReceptor() + " del emisor con cédula: " + recepcionFactura.getEmpresa().getCedula();
			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailHaciendaRecepcionFactura.vm", modelEmail);
		} catch (Exception e) {
			log.info("** Error  enviarCorreosRecepcion: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre());
			throw e;
		}
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		Collection<Attachment> resultado = null;
		try {
			resultado = Arrays.asList(attachments);
		} catch (Exception e) {
			log.info("Error al adjuntar attachments" + e);
			throw e;
		}
		return resultado;
	}

	private Attachment PDF_Attach(String name, String cedula, ByteArrayDataSource data, String tipoDoc) {
		String resultado = Constantes.EMPTY;
		resultado = "Factura_PDF_";
		if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
			resultado = "Factura_PDF_";
		} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			resultado = "NOTA_CREDITO_PDF_";
		} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
			resultado = "NOTA_DEBITO_PDF_";
		} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
			resultado = "PROFORMA_PDF_";
		} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
			resultado = "TIQUETE_PDF_";
		}
		return attachment(resultado + cedula + "_" + name, ".pdf", data);
	}

	private String getConsecutivo(String tipoDoc, String consecutivo) {
		return tipoDoc + "-" + consecutivo;
	}

	private ByteArrayDataSource asText(String text) throws IOException {
		return new ByteArrayDataSource(text, "text/plain");
	}
	
	

	private Attachment XML_Attach(String name, String cedula, ByteArrayDataSource data, String tipoDoc) {
		String resultado = Constantes.EMPTY;
		Attachment attachment = null;
		try {
			resultado = tipoDoc.equals("compra") ? tipoDoc + "_XML_" : "Factura_XML_";
			if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				resultado = "Factura_XML_";
			} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				resultado = "NOTA_CREDITO_XML_";
			} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				resultado = "NOTA_DEBITO_XML_";
			} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				resultado = "PROFORMA_XML_";
			} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				resultado = "TIQUETE_XML_";
			}
			attachment = attachment(resultado + cedula + "_" + name, ".xml", data);
		} catch (Exception e) {
			log.info("Error al adjuntar XML_Attach" + e.getCause());
			throw e;
		}

		return attachment;
	}

	private Attachment XML_AttachRespuestaHacienda(String name, String cedula, ByteArrayDataSource data) {
		Attachment attachment = null;
		try {
			attachment = attachment("Respuesta_XML_" + cedula + "_" + name, ".xml", data);
		} catch (Exception e) {
			log.info("Error al adjuntar XML_AttachRespuestaHacienda" + e.getCause());
			throw e;
		}

		return attachment;
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}
	


	private ByteArrayDataSource asPDF(ByteArrayOutputStream stream) {
		return new ByteArrayDataSource(stream.toByteArray(), "application/pdf");
	}

	/**
	 * Firmado de documentos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#procesoFirmado()
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	@Override
	public synchronized void procesoFirmado() throws Exception {
		try {
			log.info("Inicio el proceso de firmado  {}", new Date());

			Collection<Factura> lista = facturaBo.findByEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE, Constantes.FACTURA_ESTADO_REFIRMAR_DOCUMENTO);
			for (Factura factura : lista) {
				try {
					log.info("Factura id	:  {}", factura.getId() + " Factura proceso de firmado:  " + factura.getNumeroConsecutivo().toString() + " Empresa:" + factura.getEmpresa().getNombre());

					if (factura != null) {
						Collection<Detalle> detalles = detalleBo.findByFactura(factura);
						if (detalles != null) {
							if (!detalles.isEmpty()) {
								if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
									String comprobanteXML = Constantes.EMPTY;
									if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
										// Crear XMl sin firma
										comprobanteXML = facturaXMLServices.getCrearXMLSinFirma(factura);
										// firmar el documento
										comprobanteXML = facturaXMLServices.getFirmarXML(comprobanteXML.replaceAll("\n", ""), factura.getEmpresa());
									} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
										// Crear XMl sin firma
										comprobanteXML = tiqueteXMLService.getCrearXMLSinFirma(factura);
										// firmar el documento
										comprobanteXML = tiqueteXMLService.getFirmarXML(comprobanteXML.replaceAll("\n", ""), factura.getEmpresa());
									} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
										// Crear XMl sin firma
										comprobanteXML = notaCreditoXMLServices.getCrearXMLSinFirma(factura);
										// firmar el documento
										comprobanteXML = notaCreditoXMLServices.getFirmarXML(comprobanteXML.replaceAll("\n", ""), factura.getEmpresa());

									} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
										// Crear XMl sin firma
										comprobanteXML = notaDebitoXMLService.getCrearXMLSinFirma(factura);
										// firmar el documentofactura
										comprobanteXML = notaDebitoXMLService.getFirmarXML(comprobanteXML, factura.getEmpresa());
									}
									if (comprobanteXML != null) {
										if (!comprobanteXML.equals(Constantes.EMPTY)) {
											Hacienda haciendaVerificar = haciendaBo.findByEmpresaAndClave(factura.getEmpresa(), factura.getClave());
											if (haciendaVerificar == null) {
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

											}

											if (factura != null) {
												Factura facturaBD = facturaBo.findById(factura.getId());
												if (facturaBD != null) {
													facturaBD.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
													facturaBo.modificar(facturaBD);

												}
											}
										}

									}
								}

							}
						}
					}
				} catch (Exception e) {
					factura.setEstadoFirma(Constantes.FACTURA_ESTADO_PROBLEMA_AL_FIRMAR);
					facturaBo.modificar(factura);
					log.info("** Error1 proceso de firmado: " + e.getMessage() + " fecha " + new Date());
				}
			}
			log.info("** Fin  proceso de firmad" + " fecha " + new Date());

		} catch (Exception e) {
			log.info("** Error2  proceso de firmado: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Firmado de documentos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#procesoFirmado()
	 */
	@Scheduled(cron = "0 0/35 * * * ?")
	@Override
	public synchronized void procesoFirmadoRecepcionFactura() throws Exception {
		try {
			log.info("Inicio el proceso de firmado de las facturas aceptadas  {}", new Date());
			Collection<RecepcionFactura> lista = recepcionFacturaBo.findByEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE, Constantes.FACTURA_ESTADO_REFIRMAR_DOCUMENTO);
			if (!lista.isEmpty()) {
				for (RecepcionFactura recepcionFactura : lista) {
					try {
						String comprobanteXMLSinFirma = Constantes.EMPTY;
						String comprobanteXMLConFirma = Constantes.EMPTY;

						// Crear XMl sin firma
						comprobanteXMLSinFirma = recepcionFacturaXMLServices.getCrearXMLSinFirma(recepcionFactura);

						if (comprobanteXMLSinFirma != null) {
							if (!comprobanteXMLSinFirma.equals(Constantes.EMPTY)) {
								comprobanteXMLConFirma = recepcionFacturaXMLServices.getFirmarXML(comprobanteXMLSinFirma, recepcionFactura.getEmpresa());
							}
						}

						if (!comprobanteXMLConFirma.equals(Constantes.EMPTY)) {
							// Se cargan los datos de la factura, el emisor es el que envia la factura para su aprobacion
							Hacienda hacienda = new Hacienda();
							hacienda.setCedulaEmisor(recepcionFactura.getEmisorCedula());
							hacienda.setTipoEmisor(recepcionFactura.getEmisorTipoCedula());
							hacienda.setClave(recepcionFactura.getFacturaClave());
							hacienda.setFechaEmisor(recepcionFactura.getFacturaFechaEmision());

							// Se cargan los datos del emisor, empresa que recibe la factura
							hacienda.setComprobanteXML(FacturaElectronicaUtils.convertirStringToblod(comprobanteXMLConFirma));
							hacienda.setCreated_at(new Date());
							hacienda.setUpdated_at(new Date());
							hacienda.setStatus(Constantes.ZEROS);
							hacienda.setEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML);
							hacienda.setConsecutivo(recepcionFactura.getNumeroConsecutivoReceptor());
							hacienda.setReintentos(Constantes.ZEROS);
							hacienda.setReintentosAceptacion(Constantes.ZEROS);

							String tipoDoc = Constantes.FACTURA_TIPO_DOC_COMPRAS;
							hacienda.setTipoDoc(tipoDoc);
							hacienda.setEmpresa(recepcionFactura.getEmpresa());
							hacienda.setNombreReceptor(recepcionFactura.getEmpresa().getNombreComercial());
							hacienda.setCorreoReceptor(recepcionFactura.getEmpresa().getCorreoElectronico());
							hacienda.setTotalReceptor(recepcionFactura.getFacturaTotalComprobante());
							hacienda.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
							haciendaBo.agregar(hacienda);

							recepcionFactura = recepcionFactura.getId() == null || recepcionFactura.getId() == Constantes.ZEROS_LONG ? null : recepcionFacturaBo.findById(recepcionFactura.getId());
							recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
							if (recepcionFactura != null) {
								recepcionFacturaBo.modificar(recepcionFactura);
							}

						}

					} catch (Exception e) {
						recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_PROBLEMA_AL_FIRMAR);
						recepcionFacturaBo.modificar(recepcionFactura);
						log.info("** Error1 proceso de firmado: " + e.getMessage() + " fecha " + new Date());
					}

				}
			}

			log.info("Fin el proceso de firmado  {}", new Date());

		} catch (Exception e) {
			log.info("** Error2  proceso de firmado: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

}

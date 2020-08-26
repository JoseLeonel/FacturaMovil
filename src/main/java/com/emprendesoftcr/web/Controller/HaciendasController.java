package com.emprendesoftcr.web.Controller;

import static com.emprendesoftcr.fisco.Keys.ERROR;
import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.ArchivoXMLBo;
import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.fisco.RespuestaHaciendaXML;
import com.emprendesoftcr.modelo.ArchivoXML;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.pdf.ReportePdfView;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.service.RespuestaHaciendaXMLService;
import com.emprendesoftcr.type.RespuestaHacienda;
import com.emprendesoftcr.type.json.RespuestaHaciendaJson;
import com.emprendesoftcr.web.command.AgregarClienteArticuloCommand;
import com.emprendesoftcr.web.command.HaciendaCommand;
import com.emprendesoftcr.web.command.HaciendaNativeCommand;
import com.emprendesoftcr.web.propertyEditor.FechaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
import com.itextpdf.text.DocumentException;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class HaciendasController {

	private static final Function<String, String>											BIND_CONDICION_VENTA						= (id) -> id.equals("01") ? "Contado" : id.equals("02") ? "Credito" : id.equals("03") ? "Consignacion" : id.equals("04") ? "Apartado" : id.equals("05") ? "Arrendamiento con opcion de compra" : id.equals("06") ? "Arrendamiento en funcion financiera" : "Otros";

	private static final Function<Detalle, DetalleFacturaElectronica>	TO_DETALLE											= (d) -> {
																																																			//
																																																			DetalleFacturaElectronica detalleFacturaElectronica = new DetalleFacturaElectronica();
																																																			detalleFacturaElectronica.setLinea(Integer.parseInt(d.getNumeroLinea().toString()));
																																																			detalleFacturaElectronica.setCodigo(d.getCodigo());
																																																			detalleFacturaElectronica.setUnidad(d.getUnidadMedida());
																																																			detalleFacturaElectronica.setCantidad(d.getCantidad() != null ? d.getCantidad() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setDescripcion(d.getDescripcion());
																																																			detalleFacturaElectronica.setPrecioU(d.getPrecioUnitario() != null ? d.getPrecioUnitario() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setMonto(d.getMontoTotal() != null ? d.getMontoTotal() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setDescuento(d.getMontoDescuento() != null ? d.getMontoDescuento() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setSubtotal(detalleFacturaElectronica.getMonto() - (d.getMontoDescuento()));
																																																			detalleFacturaElectronica.setTarifaIva(d.getImpuesto() != null ? d.getImpuesto() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.set_impuesto1(Constantes.ZEROS_DOUBLE);
																																																			Double resultado = d.getMontoImpuesto() != null ? d.getMontoImpuesto() : Constantes.ZEROS_DOUBLE;
																																																			detalleFacturaElectronica.setImpuesto(resultado);
																																																			detalleFacturaElectronica.setTipoImpuesto(d.getTipoImpuesto() == null ? Constantes.EMPTY : d.getTipoImpuesto());
																																																			detalleFacturaElectronica.setTotal(d.getMontoTotalLinea());
																																																			detalleFacturaElectronica.setMontoExoneracion(d.getMontoExoneracion() != null ? d.getMontoExoneracion() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setTipoDocumentoExoneracion(d.getTipoDocumentoExoneracion() == null ? Constantes.EMPTY : d.getTipoDocumentoExoneracion());
																																																			detalleFacturaElectronica.setFechaEmisionExoneracion(d.getFechaEmisionExoneracion() != null ? Utils.getFechaGeneraReporte(d.getFechaEmisionExoneracion()) : Constantes.EMPTY);
																																																			detalleFacturaElectronica.setNumeroDocumentoExoneracion(d.getNumeroDocumentoExoneracion() != null ? d.getNumeroDocumentoExoneracion() : Constantes.EMPTY);
																																																			//
																																																			return detalleFacturaElectronica;
																																																		};

	private static final Function<Factura, FacturaElectronica>				DOCUMENTO_TO_FACTURAELECTRONICA	= (d) -> {
																																																			FacturaElectronica facturaElectronica = new FacturaElectronica();
																																																			if (d.getCodigoActividad() == null) {
																																																				facturaElectronica.set_codigoActividadComercial(d.getEmpresa().getCodigoActividad());
																																																			} else {
																																																				facturaElectronica.set_codigoActividadComercial(d.getCodigoActividad());
																																																			}
																																																			facturaElectronica.setAplicaFacturaElectronica(d.getEmpresa().getNoFacturaElectronica());

																																																			// Emisor
																																																			facturaElectronica.setEsquemaXML(d.getVersionEsquemaXML());
																																																			facturaElectronica.setEmisorNombreComercial(d.getEmpresa().getNombreComercial());
																																																			facturaElectronica.setFooterTotalServiciosGravados(d.getTotalServGravados());
																																																			facturaElectronica.setFooterTotalMercanciasGravadas(d.getTotalMercanciasGravadas());
																																																			facturaElectronica.setTotalOtrosCargos(d.getTotalOtrosCargos());
																																																			// Total Factura
																																																			facturaElectronica.setFooterTotalServiciosExentos(d.getTotalServExentos());
																																																			facturaElectronica.setFooterTotalGravado(d.getTotalGravado());
																																																			facturaElectronica.setFooterTotalExento(d.getTotalMercanciasExentas());
																																																			facturaElectronica.setFooterTotalVenta(d.getTotalVenta());
																																																			facturaElectronica.setFooterTotalDescuento(d.getTotalDescuentos());
																																																			facturaElectronica.setFooterTotalImpuesto(d.getTotalImpuesto());
																																																			facturaElectronica.setFooterTotalVentaNeta(d.getTotalVentaNeta());
																																																			facturaElectronica.setFooterTotalComprobante(d.getTotalComprobante());
//		
																																																			facturaElectronica.setEmisorNombre(!d.getEmpresa().getNombre().equals(Constantes.EMPTY) ? d.getEmpresa().getNombre() : d.getEmpresa().getNombre());
																																																			facturaElectronica.setEmisorCedula(d.getEmpresa().getCedula());
																																																			facturaElectronica.setEmisorDireccion(d.getEmpresa().getOtraSenas());
																																																			facturaElectronica.setEmisorTelefono(d.getEmpresa().getCodigoPais() + "-" + d.getEmpresa().getTelefono().toString());
																																																			String correo = Constantes.EMPTY;
																																																			if (d.getEmpresa().getCorreoPDF() != null) {
																																																				if (!d.getEmpresa().getCorreoPDF().equals(Constantes.EMPTY)) {
																																																					correo = d.getEmpresa().getCorreoPDF();
																																																				}
																																																			}
																																																			if (d.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
																																																				if (d.getEmpresa().getCorreoCredito() != null) {
																																																					if (!d.getEmpresa().getCorreoCredito().equals(Constantes.EMPTY)) {
																																																						correo = d.getEmpresa().getCorreoCredito();
																																																					}
																																																				}
																																																			}
																																																			if (correo.equals(Constantes.EMPTY)) {
																																																				facturaElectronica.setEmisorCorreo(d.getEmpresa().getCorreoElectronico());
																																																			} else {
																																																				facturaElectronica.setEmisorCorreo(correo);
																																																			}
																																																			facturaElectronica.setCuenta1(d.getEmpresa().getCuenta1() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta1());
																																																			facturaElectronica.setCuenta2(d.getEmpresa().getCuenta2() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta2());
																																																			facturaElectronica.setCuenta3(d.getEmpresa().getCuenta3() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta3());
																																																			facturaElectronica.setCuenta4(d.getEmpresa().getCuenta4() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta4());
																																																			facturaElectronica.setCuenta5(d.getEmpresa().getCuenta5() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta5());
																																																			facturaElectronica.setCuenta6(d.getEmpresa().getCuenta6() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta6());
																																																			facturaElectronica.setCuenta7(d.getEmpresa().getCuenta7() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta7());
																																																			facturaElectronica.setCuenta8(d.getEmpresa().getCuenta8() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta8());

																																																			facturaElectronica.set_nota(d.getNota() == null ? Constantes.EMPTY : d.getNota());
																																																			facturaElectronica.setClienteNombre(d.getCliente().getNombreCompleto());
																																																			if (d.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_FRECUENTE)) {
																																																				if (d.getNombreFactura() != null) {
																																																					if (!d.getNombreFactura().equals(Constantes.EMPTY)) {
																																																						facturaElectronica.setClienteNombre(d.getNombreFactura());
																																																					}
																																																				}
																																																			}
																																																			facturaElectronica.setClienteNombreComercial(d.getCliente().getNombreComercial());
																																																			facturaElectronica.setClienteCorreo(d.getCliente().getCorreoElectronico());

																																																			facturaElectronica.setClienteCedula(d.getCliente().getCedula());
																																																			if (facturaElectronica.getClienteCedula() != null) {
																																																				if (facturaElectronica.getClienteCedula().equals(Constantes.EMPTY)) {
																																																					facturaElectronica.setClienteCedula(d.getCliente().getIdentificacionExtranjero());
																																																				}

																																																			}
																																																			if (d.getCliente().getTelefono() != null) {
																																																				if (d.getCliente().getTelefono() != Constantes.ZEROS) {
																																																					facturaElectronica.setClienteTelefono(d.getCliente().getTelefono().toString());
																																																				} else {
																																																					facturaElectronica.setClienteTelefono(Constantes.EMPTY);
																																																				}
																																																			}
																																																			if (d.getCliente().getLibreImpuesto() != null) {
																																																				if (d.getCliente().getLibreImpuesto().equals(Constantes.LIBRE_IMPUESTOS_ACTIVO)) {
																																																					facturaElectronica.setNumeroDocumentoExoneracion(Constantes.DOCUMENTO_LIBRE_IVA);
																																																				} else {
																																																					facturaElectronica.setNumeroDocumentoExoneracion(Constantes.EMPTY);
																																																				}

																																																			} else {
																																																				facturaElectronica.setNumeroDocumentoExoneracion(Constantes.EMPTY);
																																																			}

																																																			facturaElectronica.setFooterTotalDescuento(d.getTotalDescuentos());
																																																			facturaElectronica.set_logo(d.getEmpresa().getLogo());
																																																			facturaElectronica.set_clienteDireccion(d.getDireccion());
																																																			// Otros
																																																			facturaElectronica.setTipoDocumento(FacturaElectronicaUtils.getTipoDocumento(d.getTipoDoc()));
																																																			facturaElectronica.setClave(d.getClave() == null ? Constantes.EMPTY : d.getClave());
																																																			facturaElectronica.setConsecutivo(d.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) ? Constantes.EMPTY : d.getNumeroConsecutivo());
																																																			facturaElectronica.setConsecutivoProforma(d.getConsecutivoProforma());
																																																			facturaElectronica.setFechaEmision(Utils.getFechaGeneraReporte(d.getFechaEmision()));
																																																			facturaElectronica.setPlazoCredito(d.getPlazoCredito() != null ? d.getPlazoCredito().toString() : Constantes.EMPTY);
																																																			facturaElectronica.setCondicionVenta(BIND_CONDICION_VENTA.apply(d.getCondicionVenta()));
																																																			facturaElectronica.setMedioEfectivo(FacturaElectronicaUtils.medioPago(d));
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
																																																			return facturaElectronica;
																																																		};

	private static final Function<Object, HaciendaCommand>						TO_COMMAND											= new Function<Object, HaciendaCommand>() {

																																																			@Override
																																																			public HaciendaCommand apply(Object f) {
																																																				return new HaciendaCommand((Hacienda) f);
																																																			};
																																																		};

	private Logger																										log															= LoggerFactory.getLogger(this.getClass());

	@Autowired
	DataTableBo																												dataTableBo;

	@Autowired
	private FacturaBo																									facturaBo;

	@Autowired
	private ArchivoXMLBo																							archivoXMLBo;

	@Autowired
	private DetalleBo																									detalleBo;

	@Autowired
	private ConsultasNativeBo																					haciendaNativeBo;

	@Autowired
	private ProcesoHaciendaService																		procesoHaciendaService;

	@Autowired
	private HaciendaBo																								haciendaBo;

	@Autowired
	private UsuarioBo																									usuarioBo;

	@Autowired
	private FechaPropertyEditor																				fechaPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@Autowired
	private RespuestaHaciendaXMLService																respuestaHaciendaXMLService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, stringPropertyEditor);
		binder.registerCustomEditor(Date.class, fechaPropertyEditor);
	}

	@RequestMapping(value = "/ListarHacienda", method = RequestMethod.GET)
	public String listaFacturas(ModelMap model) {
		return "views/hacienda/ListarHacienda";
	}

	/**
	 * CallBack de hacienda para el retorno de los xml de respuesta
	 * @param httpEntity
	 * @throws Exception
	 */
	private final ReentrantLock lock = new ReentrantLock();

	/**
	 * No se aplica rechazo para verificar con el de comprobacion de datos
	 * @param httpEntity
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/service/callback.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	@Transactional
	public synchronized RespuestaServiceValidator callBack(HttpEntity<String> httpEntity) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		// Hacienda hacienda = null;
		Integer estadoHacienda = Constantes.ZEROS;
		String mensajeHacienda = Constantes.EMPTY;
		String xmlFirmado = Constantes.EMPTY;
		String xmlSinFirmarRespuesta = Constantes.EMPTY;
		String xmlFirmadoRespuesta = Constantes.EMPTY;
		lock.lock();
		RespuestaHaciendaXML respuesta = new RespuestaHaciendaXML();
		try {
			long id = Thread.currentThread().getId();
			System.out.println(String.format("--start transaccion CallBACK--> Thread=%d %s", id, "Fecha:" + new Date()));

			String body = httpEntity.getBody();
			// body= FacturaElectronicaUtils.procesarTexto(body);
			if (body != null && body != "" && body != "{}" && !body.contains("El comprobante") && !body.contains("no ha sido recibido")) {
				RespuestaHacienda respuestaHacienda = RespuestaHaciendaJson.from(body);
				Hacienda hacienda = haciendaBo.findByClave(respuestaHacienda.clave());
				log.info("** callBack: " + respuestaHacienda.clave() + " fecha " + new Date());

				log.info("** Estado hacienda: " + estadoHacienda.toString());

				if (hacienda != null) {
					String resputaStatusHacienda = getHaciendaStatus(respuestaHacienda.indEstado());
					log.info("** Status dentro: " + resputaStatusHacienda);
					log.info("** Estado hacienda dentro: " + estadoHacienda.toString());
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
					log.info("** Respuesta Estado-->: " + resputaStatusHacienda);
					log.info("** Status dentro 1: " + resputaStatusHacienda);
					Factura facturaConsultada = facturaBo.findByConsecutivoAndEmpresa(hacienda.getConsecutivo(), hacienda.getEmpresa());
					if (!resputaStatusHacienda.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECIBIDO)) {
						xmlSinFirmarRespuesta = respuestaHaciendaXMLService.getCrearXMLSinFirma(respuesta, facturaConsultada);
						xmlFirmadoRespuesta = respuestaHaciendaXMLService.getFirmarXML(xmlSinFirmarRespuesta, hacienda.getEmpresa(), facturaConsultada);
						log.info("** Status firmado 1: " + resputaStatusHacienda);
					} else {
						if (respuestaHacienda.mensajeHacienda() != null) {
							if (respuestaHacienda.mensajeHacienda().mensaje() != null) {
								if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO)) {
									xmlSinFirmarRespuesta = respuestaHaciendaXMLService.getCrearXMLSinFirma(respuesta, facturaConsultada);
									xmlFirmadoRespuesta = respuestaHaciendaXMLService.getFirmarXML(xmlSinFirmarRespuesta, hacienda.getEmpresa(), facturaConsultada);
								}
							}
						}
					}
					if (xmlFirmadoRespuesta != null) {
						if (!xmlFirmadoRespuesta.equals(Constantes.EMPTY)) {
							xmlFirmado = xmlFirmadoRespuesta;
						}

					}
					if (resputaStatusHacienda.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR)) {
						estadoHacienda = Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA;
					} else if (resputaStatusHacienda.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO_STR)) {
						estadoHacienda = Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA;
					} else if (resputaStatusHacienda.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_ACEPTADO_HACIENDA_STR)) {
						estadoHacienda = Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA;
					}
					// Hacienda no envia mensaje
					if (respuestaHacienda.mensajeHacienda() != null) {
						if (respuestaHacienda.mensajeHacienda().mensaje() != null) {
							if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO)) {
								estadoHacienda = Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA;
							} else if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_RECHAZADO)) {
								estadoHacienda = Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA;
							} else if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO_PARCIAL)) {
								estadoHacienda = Constantes.HACIENDA_ESTADO_ACEPTADO_PARCIAL;
							}
						}
					} else {
						if (!resputaStatusHacienda.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR)) {
							estadoHacienda = Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA;
						}
					}

					mensajeHacienda = respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().detalleMensaje() : Constantes.EMPTY;
					log.info("** Respuesta Estado-1->: " + resputaStatusHacienda);

					if (xmlFirmado != null) {
						if (xmlFirmado.length() > 0) {
							log.info("llamado procedimiento callback:{}", estadoHacienda.toString());
							haciendaBo.findByClaveSP(hacienda.getId(), hacienda.getNumeroFactura() == null ? Constantes.ZEROS_LONG : hacienda.getNumeroFactura(), respuestaHacienda.clave(), estadoHacienda, xmlFirmado, mensajeHacienda);
						} else {
							log.info("No llamado procedimiento callback: {}", estadoHacienda);
						}

					}

				}
			}
			log.info("Estado para actualizar Factura: {}", estadoHacienda);
			log.info("Finaliza callBack {}", new Date());
		} catch (Exception e) {

			log.info("** Error  callBack: " + e.getMessage() + " fecha " + new Date());
			return RespuestaServiceValidator.ERROR(e);
		} finally {
			lock.unlock();
		}
		respuestaServiceValidator.setStatus(HttpStatus.OK.value());
		respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.callback.exitoso"));
		return respuestaServiceValidator;

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
	 * Listado de hacienda
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarHaciendasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String cedulaReceptor) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

		Date fechaIniciot = Utils.parseDate(fechaInicio);
		Date fechaFinalt = Utils.parseDate(fechaFin);
		if (fechaIniciot == null) {
			fechaIniciot = new Date(System.currentTimeMillis());
		}
		if (fechaFinalt != null) {
			fechaFinalt = Utils.sumarDiasFecha(fechaFinalt, 1);
		}

		DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		Collection<HaciendaNative> objetos = cedulaReceptor.equals(Constantes.COMBO_TODOS) ? haciendaNativeBo.findByEmpresaAndEstado(usuarioSesion.getEmpresa(), dateFormat.format(fechaIniciot), dateFormat.format(fechaFinalt)) : null;
		Collection<HaciendaNativeByEmpresaAndFechaAndCliente> objetos_1 = objetos == null ? haciendaNativeBo.findByEmpresaAndFechaAndCliente(usuarioSesion.getEmpresa(), dateFormat.format(fechaIniciot), dateFormat.format(fechaFinalt), cedulaReceptor) : null;

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (HaciendaNative haciendaNative : objetos) {

				// no se carga el usuario del sistema el id -1
				if (haciendaNative.getId().longValue() > 0L) {
					solicitudList.add(new HaciendaNativeCommand(haciendaNative));
				}
			}

		}
		if (objetos_1 != null) {
			for (HaciendaNativeByEmpresaAndFechaAndCliente haciendaNativeByEmpresaAndFechaAndCliente : objetos_1) {

				// no se carga el usuario del sistema el id -1
				if (haciendaNativeByEmpresaAndFechaAndCliente.getId().longValue() > 0L) {
					solicitudList.add(new HaciendaNativeCommand(haciendaNativeByEmpresaAndFechaAndCliente));
				}
			}

		}

		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}

	/**
	 * Env
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @param cedulaReceptor
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnviarHaciendasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable enviarHaciendaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String cedulaReceptor) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, cedulaReceptor, usuarioSesion.getEmpresa(), Constantes.EMPTY);
		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Enviar aceptar el documento hacienda
	 * @param request
	 * @param response
	 * @param idFactura
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnviarAceptarHaciendaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			procesoHaciendaService.envioHacienda(haciendaBD, null);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.enviado.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AceptarHaciendaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrarAceptar(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			procesoHaciendaService.aceptarDocumento(haciendaBD, null);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.aceptar.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	/**
	 * Enviar el correo al emisor y al cliente si es una factura(nota credito o debito) , tiquete solo al emisor
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnviarCorreoClienteAndEmisorAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoEmisorAndReceptor(HttpServletRequest request, HttpServletResponse response,ModelMap model, @ModelAttribute String datos,BindingResult result, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			Factura factura = facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());

			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.factura.no.existe");
			}
			ArrayList<String> listaCorreos = new ArrayList<String>();
			listaCorreos = facturaBo.listaCorreosAsociadosFactura(factura);

			if (listaCorreos != null) {
				if (listaCorreos.size() == 0) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.factura.no.tiene.correo.asociado");
				}
			}
			if (haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ANULADA) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_PROBLEMA_ENVIO_CORREO)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.xml.con.error");

			}
			if (!haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.no.aceptado");

			}

			listaCorreos.add(factura.getEmpresa().getCorreoElectronico());
			Boolean resultado = procesoHaciendaService.enviarCorreos(factura, haciendaBD, listaCorreos);
			if (resultado.equals(Boolean.TRUE)) {
				log.info("Enviado correctamente el correo {}", new Date());
				System.out.println("Enviado correctamente el correo");
			} else {
				log.error("** Error  Enviado correo: " + " fecha " + new Date());
				System.out.println("No enviado correctamente el correo");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
			}
			

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	/**
	 * Envios de correos alternativos
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @param correo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnviarCorreoAlternativoAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoAlternativo(HttpServletRequest request, HttpServletResponse response,ModelMap model, @ModelAttribute String datos,BindingResult result,  @RequestParam Long idHacienda, @RequestParam String correo) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			Factura factura = facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());

			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.factura.no.existe");
			}
			ArrayList<String> listaCorreos = new ArrayList<String>();
			listaCorreos.add(correo);
			if (listaCorreos != null) {
				if (listaCorreos.size() == 0) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.vacio.enviar");
				}

			}

			if (haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ANULADA) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_PROBLEMA_ENVIO_CORREO)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.xml.con.error");

			}
			if (!haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.no.aceptado");

			}
			Boolean resultado = procesoHaciendaService.enviarCorreos(factura, haciendaBD, listaCorreos);
			if (resultado.equals(Boolean.TRUE)) {
				log.info("Enviado correctamente el correo {}", new Date());
				System.out.println("Enviado correctamente el correo");
			} else {
				log.error("** Error  Enviado correo: " + " fecha " + new Date());
				System.out.println("No enviado correctamente el correo");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
			}

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	/**
	 * Baja Comprobante XML
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bajarXMLComprobanteAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarXMLComprobante(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) throws Exception {
		try {
			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			String fileName = haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				fileName = "TiqueteXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				fileName = "FacturaXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				fileName = "NotaCreditoXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				fileName = "NotaDebitoXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_COMPRA_SIMPLIFICADA)) {
				fileName = "CompraSimplificadaXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}
			String xmlFactura = Constantes.EMPTY;
			ArchivoXML archivoXMLCorreos = archivoXMLBo.findByClave(haciendaBD.getEmpresa(), haciendaBD.getClave());

			if (archivoXMLCorreos != null) {
				xmlFactura = Utils.leerXMLServidor(archivoXMLCorreos.getPathMigracion());

			} else {
				xmlFactura = FacturaElectronicaUtils.convertirBlodToString(haciendaBD.getComprobanteXML());

			}
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xml");
			ServletOutputStream out = response.getOutputStream();
			// out.println(FacturaElectronicaUtils.convertirBlodToString(haciendaBD.getComprobanteXML()));
			out.println(xmlFactura);
			out.flush();
			out.close();

		} catch (Exception e) {
			log.info("** Error  bajarXMLComprobante: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Respuesta de hacienda del documento aceptado o rechazado
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @throws Exception
	 */
	@RequestMapping(value = "/bajarXMLRespuestaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarXMLRespuesta(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) throws Exception {
		try {
			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			String fileName = haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			fileName = "RespuestaXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				fileName = "RespuestaXML_TiqueteXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				fileName = "RespuestaXML_FacturaXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				fileName = "RespuestaXML_Nota_CreditoXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				fileName = "RespuestaXML_Nota_DebitoXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_COMPRA_SIMPLIFICADA)) {
				fileName = "RespuestaXML_Compra_SimplificadaXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			String xmlFactura = Constantes.EMPTY;
			ArchivoXML archivoXMLCorreos = archivoXMLBo.findByClave(haciendaBD.getEmpresa(), haciendaBD.getClave());

			if (archivoXMLCorreos != null) {
				xmlFactura = Utils.leerXMLServidor(archivoXMLCorreos.getPathMigracionRespuesta());
			} else {
				xmlFactura = FacturaElectronicaUtils.convertirBlodToString(haciendaBD.getMensajeHacienda());

			}

			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xml");
			ServletOutputStream out = response.getOutputStream();
			out.println(xmlFactura);

			out.flush();
			out.close();

		} catch (Exception e) {
			log.info("** Error  bajarXMLComprobante: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@RequestMapping(value = "/bajarPDFComprobanteAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarPDFComprobante(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) throws Exception {
		try {
			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			Factura factura = facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());
			String fileName = haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				fileName = "Tiquete_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				fileName = "Factura_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				fileName = "NotaCredito_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				fileName = "NotaDebito_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			Collection<Detalle> detalles = detalleBo.findByFactura(factura);

			List<DetalleFacturaElectronica> detallesFactura = detalles.stream().sorted(Comparator.comparingInt(Detalle::getNumeroLinea)).map(TO_DETALLE).collect(toList());
			facturaElectronica.setDetalleFacturaElectronica(detallesFactura);

			ByteArrayOutputStream namePDF = ReportePdfView.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			byte[] bytes  =  namePDF.toByteArray();
			if (bytes != null && bytes.length > 0) {
				response.setContentType("application/pdf");
				//response.setHeader("Content-Disposition", "attachment;filename=etiquetas.pdf");
				ServletOutputStream outputstream = response.getOutputStream();
				outputstream.write(bytes, 0, bytes.length);
				outputstream.flush();
				outputstream.close();

			} else {
				System.out.println("NO trae nada");
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (com.google.zxing.WriterException ex) {
			throw ex;
		}

	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, String cedula, Empresa empresa, String tipoDocumento) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Hacienda");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (cedula != null) {
				if (!cedula.equals("0")) {
					if (cedula.equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
						delimitador.addFiltro(new JqGridFilter("nombreReceptor", Constantes.NOMBRE_CLIENTE_FRECUENTE, "like"));
					} else {
						delimitador.addFiltro(new JqGridFilter("cedulaReceptor", cedula, "="));
					}

				}

			}
			if (tipoDocumento != null) {
				if (!tipoDocumento.equals(Constantes.EMPTY)) {
					if (tipoDocumento.equals("88")) {
						delimitador.addFiltro(new JqGridFilter("tipoDoc", "'" + " " + "'", "="));
					} else {
						if (!tipoDocumento.equals("0")) {
							delimitador.addFiltro(new JqGridFilter("tipoDoc", tipoDocumento, "="));
						}

					}

				}

			}
			if (!inicio.equals(Constantes.EMPTY) && !fin.equals(Constantes.EMPTY)) {
				fechaInicio = Utils.parseDate(inicio);
				fechaFinal = Utils.parseDate(fin);
				if (fechaFinal == null) {
					fechaFinal = new Date(System.currentTimeMillis());
				}
				if (fechaFinal != null && fechaFinal != null) {
					fechaFinal = Utils.sumarDiasFecha(fechaFinal, 1);
				}

				DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT7);

				inicio = dateFormat.format(fechaInicio);
				fin = dateFormat.format(fechaFinal);

				delimitador.addFiltro(new JqGridFilter("fechaEmisor", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("fechaEmisor", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

	static class RESPONSES {

		@SuppressWarnings("unused")
		private static class OK {

			private static class FACTURA {

					@SuppressWarnings("rawtypes")
					private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente");
				@SuppressWarnings("rawtypes")
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.modificado.correctamente");
			}
		}

		@SuppressWarnings("unused")
		private static class ERROR {

			private static class FACTURA {

				@SuppressWarnings("rawtypes")
				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.noExiste");
			}
		}
	}

}

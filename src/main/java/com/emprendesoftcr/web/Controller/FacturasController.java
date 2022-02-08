package com.emprendesoftcr.web.Controller;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.MesaBo;
import com.emprendesoftcr.Bo.TipoCambioBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Bo.VendedorBo;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.Mesa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.Vendedor;
import com.emprendesoftcr.modelo.sqlNativo.CompraIVA;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaGananciaNative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaIVANative;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaUtilidadNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturaIDNativa;
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;
import com.emprendesoftcr.modelo.sqlNativo.FacturasEsperaNativa;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.ListaNotasNative;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturaMesaNative;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturaNCNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasImpuestoServicioNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasTableNativa;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstado;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturada;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.pdf.ReportePdfView;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ConsultaFacturaGananciasNativeCommand;
import com.emprendesoftcr.web.command.ConsultaIvaCommand;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.emprendesoftcr.web.command.FacturaAnulacionCommand;
import com.emprendesoftcr.web.command.FacturaCommand;
import com.emprendesoftcr.web.command.FacturaDiaCommand;
import com.emprendesoftcr.web.command.FacturaEsperaCommand;
import com.emprendesoftcr.web.command.FacturaImpuestoServicioCommand;
import com.emprendesoftcr.web.command.ParametrosPaginacionMesa;
import com.emprendesoftcr.web.command.ProformasByEmpresaAndEstadoCommand;
import com.emprendesoftcr.web.command.ProformasSQLNativeCommand;
import com.emprendesoftcr.web.command.RecepcionFacturaCommand;
import com.emprendesoftcr.web.command.TikectImprimir;
import com.emprendesoftcr.web.command.TotalFacturaCommand;
import com.emprendesoftcr.web.command.TotalbyImpuestosCommand;
import com.emprendesoftcr.web.command.TotalbyResumenImpuestosCommand;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.FechaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MesaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.VendedorPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.itextpdf.text.DocumentException;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JsonDataSource;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class FacturasController {

	private static final Function<String, String>											BIND_CONDICION_VENTA						= (id) -> id.equals("01") ? "Contado" : id.equals("02") ? "Credito" : id.equals("03") ? "Consignacion" : id.equals("04") ? "Apartado" : id.equals("05") ? "Arrendamiento con opcion de compra" : id.equals("06") ? "Arrendamiento en funcion financiera" : "Otros";

	private static final Function<Object, FacturaEsperaCommand>				TO_COMMAND											= new Function<Object, FacturaEsperaCommand>() {

																																																			@Override
																																																			public FacturaEsperaCommand apply(Object f) {
																																																				return new FacturaEsperaCommand((Factura) f);
																																																			};
																																																		};

	private static final Function<Object, RecepcionFacturaCommand>		TO_COMMAND_RECEPCION						= new Function<Object, RecepcionFacturaCommand>() {

																																																			@Override
																																																			public RecepcionFacturaCommand apply(Object f) {
																																																				return new RecepcionFacturaCommand((RecepcionFactura) f);
																																																			};
																																																		};

	private static final Function<Detalle, DetalleFacturaElectronica>	TO_DETALLE											= (d) -> {
																																																			//
																																																			DetalleFacturaElectronica detalleFacturaElectronica = new DetalleFacturaElectronica();
																																																			detalleFacturaElectronica.set_precioSugerido(d.getPrecioSugerido() == null ? Constantes.ZEROS_DOUBLE : d.getPrecioSugerido());
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
																																																			facturaElectronica.setEmisorCorreo(d.getEmpresa().getCorreoElectronico());
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
																																																			if (d.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_FRECUENTE) || d.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_CREDITO)) {
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

																																																			facturaElectronica.setNumeroDocumentoExoneracion(Constantes.EMPTY);

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

	@Autowired
	private DetalleBo																									detalleBo;

	@Autowired
	private HaciendaBo																								haciendaBo;

	@Autowired
	private CuentaCobrarBo																						cuentaCobrarBo;

	@Autowired
	private CorreosBo																									correosBo;

	@Autowired
	private DataTableBo																								dataTableBo;

	@Autowired
	private UsuarioBo																									usuarioBo;

	@Autowired
	private TipoCambioBo																							tipoCambioBo;

	@Autowired
	private UsuarioCajaBo																							usuarioCajaBo;

	@Autowired
	private VendedorBo																								vendedorBo;

	@Autowired
	private ClienteBo																									clienteBo;

	@Autowired
	private FacturaBo																									facturaBo;

	@Autowired
	private ConsultasNativeBo																					consultasNativeBo;

	@Autowired
	private MesaBo																										mesaBo;

	@Autowired
	private EmpresaPropertyEditor																			empresaPropertyEditor;

	@Autowired
	private ClientePropertyEditor																			clientePropertyEditor;

	@Autowired
	private VendedorPropertyEditor																		vendedorPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@Autowired
	private FechaPropertyEditor																				fechaPropertyEditor;

	@Autowired
	private MesaPropertyEditor																				mesaPropertyEditor;

	@Autowired
	private ProcesoHaciendaService																		procesoHaciendaService;

	
	private Logger																										log															= LoggerFactory.getLogger(this.getClass());

	@Autowired
	public DataSource																									dataSource;

	private JdbcTemplate																							jdbcTemplate;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
		binder.registerCustomEditor(Date.class, fechaPropertyEditor);
		binder.registerCustomEditor(Mesa.class, mesaPropertyEditor);
	}

	@RequestMapping(value = "/recepcionFactura", method = RequestMethod.GET)
	public String recepcionFactura(ModelMap model) {
		return "views/facturas/recepcionFactura";
	}

	@RequestMapping(value = "/ListaNostas.do", method = RequestMethod.GET)
	public String listaNotas(ModelMap model) {
		return "views/facturas/listasNotas";
	}

	@RequestMapping(value = "/proformas.do", method = RequestMethod.GET)
	public String listaProformas(ModelMap model) {
		return "views/facturas/proformas";
	}

	@RequestMapping(value = "/postVenta", method = RequestMethod.GET)
	public String postVenta(ModelMap model) {
		return "views/facturas/postVenta";
	}

	@RequestMapping(value = "/postRestaurante", method = RequestMethod.GET)
	public String postpostRestaurante(ModelMap model) {
		return "views/facturas/postRestaurante";
	}

	@RequestMapping(value = "/Saloneros.do", method = RequestMethod.GET)
	public String meseros(ModelMap model) {
		return "views/facturas/saloneros";
	}

	@RequestMapping(value = "/ventaDolares", method = RequestMethod.GET)
	public String crearVentaDolares(ModelMap model) {
		return "views/facturas/ventaDolares";
	}

	@RequestMapping(value = "/ventaComprasTotal", method = RequestMethod.GET)
	public String ventaComprasTotal(ModelMap model) {
		return "views/facturas/VentasComprasTotal";
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/GenerarTikect1.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public void GenerarTikete(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam(value = "subTotalGeneralSTR", required = false) String subTotalGeneralSTR, @RequestParam(value = "totalImpuestoRestSTR", required = false) String totalImpuestoRestSTR, @RequestParam("idFactura") Long idFactura, @RequestParam(value = "impServicioTotalSTR", required = false) String impServicioTotalSTR, @RequestParam(value = "totalComprobanteSTR", required = false) String totalComprobanteSTR, @RequestParam(value = "totalDescuentosProformaREstSTR", required = false) String totalDescuentosProformaREstSTR, @RequestParam("tipoFactura") Integer tipoFactura, @ModelAttribute TikectImprimir tikectImprimir1, BindingResult result, SessionStatus status) throws Exception {
		List<TikectImprimir> lista = new ArrayList<>();
		Factura factura = facturaBo.findById(idFactura);
		Collection<Detalle> detalles = detalleBo.findbyIdFactura(idFactura);
		for (Detalle detalle : detalles) {
			TikectImprimir tikectImprimir = new TikectImprimir();
			tikectImprimir.setId(detalle.getId());
			tikectImprimir.setDescripcion(detalle.getDescripcion());
			tikectImprimir.setCantidad(detalle.getCantidadSTR());
			tikectImprimir.setPrecio(detalle.getPrecioUnitarioSTR());
			tikectImprimir.setTotal(detalle.getMontoTotalLineaSTR());
			tikectImprimir.setImpuesto(detalle.getImpuestoSTR() + "%");
			lista.add(tikectImprimir);

		}
		Map<String, Object> parametros = getParametroReportes(factura, tipoFactura, subTotalGeneralSTR, totalImpuestoRestSTR, impServicioTotalSTR, totalComprobanteSTR, totalDescuentosProformaREstSTR);

		InputStream reportFile = getObtienePDF(factura, tipoFactura);
		ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(new Gson().toJson(lista).getBytes());
		JsonDataSource ds = new JsonDataSource(jsonDataStream);
		byte[] bytes = JasperRunManager.runReportToPdf(reportFile, parametros, ds);
		if (bytes != null && bytes.length > 0) {
			response.setContentType("application/pdf");
			// response.setHeader("Content-Disposition", "attachment;filename=etiquetas.pdf");
			ServletOutputStream outputstream = response.getOutputStream();
			outputstream.write(bytes, 0, bytes.length);
			outputstream.flush();
			outputstream.close();

		} else {
			System.out.println("NO trae nada");
		}

	}

	private InputStream getObtienePDF(Factura factura, Integer tipoFactura) {
		InputStream reportFile = null;
		try {
			if (tipoFactura.equals(1)) {
				reportFile = getClass().getResourceAsStream("/reportes/factura/tikect.jasper");
			}
			if (tipoFactura.equals(2)) {
				reportFile = getClass().getResourceAsStream("/reportes/factura/proformaRestaurante.jasper");
			}

		} catch (Exception e) {
			log.info("** Error  getObtienePDF: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}
		return reportFile;
	}

	private String obtenerTipoDocumento(Factura factura) {
		String resultado = Constantes.EMPTY;
		if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
			resultado = "Factura Electronica 4.3";
		}
		if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
			resultado = "Tiquete Electronico 4.3";
		}

		if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
			resultado = "Nota credito";
		}
		if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO)) {
			resultado = "Nota debito";
		}
		if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
			resultado = "Nota debito Electronico 4.3";
		}
		if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			resultado = "Nota credito Electronico 4.3";
		}
		if (factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA)) {
			resultado = "Tiquete";
		}

		return resultado;
	}

	private Map<String, Object> getParametroReportes(Factura factura, Integer tipoFactura, String subTotalGeneralSTR, String totalImpuestoRestSTR, String impServicioTotalSTR, String totalComprobanteSTR, String totalDescuentosProformaREstSTR) {
		Map<String, Object> parametros;
		parametros = new HashMap<>();
		try {

			Integer nofactura_elec = factura.getEmpresa().getNoFacturaElectronica() != null ? factura.getEmpresa().getNoFacturaElectronica() : Constantes.ZEROS;

			parametros.put("P_titulo_electronico", obtenerTipoDocumento(factura));
			parametros.put("P_documento_electronico", MapEnums.ENUM_TIPO_DOC.get(factura.getTipoDoc()));
			parametros.put("P_regimen", nofactura_elec.equals(Constantes.ZEROS) ? "Régimen Simplificado" : "Régimen Tradicional");
			parametros.put("P_actividad", "Codigo Actividad : " + factura.getCodigoActividad());
			parametros.put("P_comercial", factura.getEmpresa().getNombreComercial() == null ? Constantes.EMPTY : factura.getEmpresa().getNombreComercial());
			parametros.put("P_nombre", factura.getEmpresa().getNombre());

			parametros.put("P_cedula", "Cedula:" + factura.getEmpresa().getCedula() + " Telefono(506):" + factura.getEmpresa().getTelefono());
			parametros.put("P_otra_sena", factura.getEmpresa().getOtraSenas());
			parametros.put("P_fecha_emision", "Fecha Emision:" + factura.getFechaEmisionSTR());
			parametros.put("P_cond_venta", "C.venta: " + factura.getCondicionVentaSTR().trim());
			String medioPago = factura.getMedioEfectivo() != null && !factura.getMedioEfectivo().equals(Constantes.EMPTY) ? factura.getMedioEfectivo() : Constantes.EMPTY;
			if (medioPago.equals(Constantes.EMPTY)) {
				medioPago = factura.getMedioTarjeta() != null && !factura.getMedioTarjeta().equals(Constantes.EMPTY) ? factura.getMedioTarjeta() : Constantes.EMPTY;
			}
			if (medioPago.equals(Constantes.EMPTY)) {
				medioPago = factura.getMedioBanco() != null && !factura.getMedioBanco().equals(Constantes.EMPTY) ? factura.getMedioBanco() : Constantes.EMPTY;
			}
			parametros.put("P_medio_pago", "Medio Pago: " + MapEnums.ENUM_MEDIO_PAGO.get(medioPago));
			parametros.put("P_usuario", "Usuario: " + factura.getUsuarioCreacion().getNombreUsuario());
			parametros.put("P_moneda", "Moneda: " + MapEnums.ENUM_MONEDA.get(factura.getCodigoMoneda()));
			parametros.put("P_documento", factura.getNumeroConsecutivo());
			String clave1 = factura.getClave() != null && !factura.getClave().equals(Constantes.EMPTY) ? factura.getClave() : Constantes.EMPTY;
			parametros.put("P_clave1", clave1);
			parametros.put("P_nota", factura.getNota() != null && !factura.getNota().equals(Constantes.EMPTY) ? factura.getNota() : Constantes.EMPTY);

			if (tipoFactura.equals(2)) {

				parametros.put("P_NOMBRE_FACTURA", factura.getNombreFactura() != null ? factura.getNombreFactura() : Constantes.EMPTY);
				parametros.put("P_PROFORMA", "#" + factura.getId());

				parametros.put("P_total_general", "Total:" + totalComprobanteSTR);
				parametros.put("P_total_impuesto", "              IVA:" + totalImpuestoRestSTR);
				parametros.put("P_impuesto_servicio", "	Servicio(10%):" + impServicioTotalSTR);
				parametros.put("P_descuento", "                Desc:" + totalDescuentosProformaREstSTR);
				parametros.put("P_subTotal", "         SubTotal:" + subTotalGeneralSTR);
			} else {
				parametros.put("P_subTotal", "         SubTotal:" + factura.getSubTotalSTR());
				parametros.put("P_total_general", "               Total:" + factura.getTotalComprobanteSTR());
				String impuesto = factura.getTotalImpuesto() != null && factura.getTotalImpuesto() > Constantes.ZEROS_DOUBLE ? "              IVA:" + factura.getTotalImpuestoSTR() : Constantes.EMPTY;
				parametros.put("P_total_impuesto", impuesto);
				parametros.put("P_impuesto_servicio", factura.getTotalImpuestoServicio() != null && factura.getTotalImpuestoServicio() > Constantes.ZEROS_DOUBLE ? "	Servicio(10%):" + factura.getTotalImpuestoServicioSTR() : Constantes.EMPTY);
				String descuento = factura.getTotalDescuentos() != null && factura.getTotalDescuentos() > Constantes.ZEROS_DOUBLE ? "                Desc:" + factura.getTotalDescuentoSTR() : Constantes.EMPTY;
				parametros.put("P_descuento", descuento);

			}

			parametros.put("P_cambio", "           Cambio:" + factura.getMontoCambioSTR());
			parametros.put("P_correo", factura.getEmpresa().getCorreoElectronico());
			parametros.put("P_telefono", factura.getEmpresa().getTelefono().toString());
			parametros.put("P_nombreFactura", obtenerNombreCliente(factura));

			parametros.put("P_cedula_cliente", obtenerCedulaCliente(factura));
			parametros.put("P_correoCliente", factura.getCliente().getCorreoElectronico() != null ? factura.getCliente().getCorreoElectronico() : Constantes.EMPTY);
			String exoneracion = factura.getTotalExonerado() != null && factura.getTotalExonerado() > Constantes.ZEROS_DOUBLE ? "Exoneracion(-):" + factura.getTotalExoneradoSTR() : Constantes.EMPTY;
			parametros.put("P_total_exoneracion", exoneracion);
			parametros.put("P_ref_documento", obtenerReferenciaDocumento(factura));

			parametros.put("P_parrafoOficialHacienda", obtenerParrafoOficial(factura));

			parametros.put("P_cuenta_titulo", factura.getEmpresa().getCuenta1() != null && !factura.getEmpresa().getCuenta1().equals(Constantes.EMPTY) ? "---Transferencias Bancarias---" : Constantes.EMPTY);
			parametros.put("P_cuenta_uno", factura.getEmpresa().getCuenta1() != null ? factura.getEmpresa().getCuenta1() : Constantes.EMPTY);
			parametros.put("P_cuenta_dos", factura.getEmpresa().getCuenta2() != null ? factura.getEmpresa().getCuenta2() : Constantes.EMPTY);
			parametros.put("P_cuenta_tres", factura.getEmpresa().getCuenta3() != null ? factura.getEmpresa().getCuenta3() : Constantes.EMPTY);
			parametros.put("P_cuenta_cuatro", factura.getEmpresa().getCuenta4() != null ? factura.getEmpresa().getCuenta4() : Constantes.EMPTY);
			parametros.put("P_cuenta_cinco", factura.getEmpresa().getCuenta5() != null ? factura.getEmpresa().getCuenta5() : Constantes.EMPTY);
			parametros.put("P_cuenta_seis", factura.getEmpresa().getCuenta6() != null ? factura.getEmpresa().getCuenta6() : Constantes.EMPTY);
			parametros.put("P_cuenta_siete", factura.getEmpresa().getCuenta7() != null ? factura.getEmpresa().getCuenta7() : Constantes.EMPTY);
			parametros.put("P_titulo_clave", factura.getClave() != null && !factura.getClave().equals(Constantes.EMPTY) ? "Clave" : Constantes.EMPTY);

			parametros.put("P_Mesa", factura.getMesa() != null ? factura.getMesa().getDescripcion() : Constantes.EMPTY);

		} catch (Exception e) {
			log.info("** Error  getParametroReportes: " + e.getMessage() + " fecha " + new Date());

			throw e;
		}
		return parametros;

	}

	private String obtenerParrafoOficial(Factura factura) {

		StringBuilder parrafoOficialHacienda = new StringBuilder();
		if (factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA)) {
			parrafoOficialHacienda.append("Emitida conforme lo establecido en la resolución");
			parrafoOficialHacienda.append(" de Facturación Electrónica,");
			parrafoOficialHacienda.append("No DGT-R-033-2019 del 20-06-2019 ");
			parrafoOficialHacienda.append("de la Direccion General de Tributación  ");
		} else {
			parrafoOficialHacienda.append("Emitida conforme lo establecido en la resolución de Facturación Electrónica,");
			parrafoOficialHacienda.append(" No DGT-R-033-2019 del 20-06-2019 de la Direccion General de Tributación.");

		}

		return parrafoOficialHacienda.toString();

	}

	private String obtenerReferenciaDocumento(Factura factura) {
		String resultado = Constantes.EMPTY;
		if (factura.getReferenciaNumero() != null && !factura.getReferenciaNumero().equals(Constantes.EMPTY)) {
			resultado = factura.getReferenciaNumero();
		}
		return resultado;
	}

	private String obtenerNombreCliente(Factura factura) {
		String nombreFactura = Constantes.EMPTY;
		if (factura.getCliente().getCedula() != null) {
			if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
				nombreFactura = factura.getCliente().getNombreCompleto();
			}
		}
		if (nombreFactura.equals(Constantes.EMPTY)) {
			nombreFactura = factura.getNombreFactura() != null && !factura.getNombreFactura().equals(Constantes.EMPTY) ? factura.getNombreFactura() : Constantes.EMPTY;

		}

		return nombreFactura;

	}

	private String obtenerCedulaCliente(Factura factura) {
		String cedula = Constantes.EMPTY;
		if (factura.getCliente().getCedula() != null) {
			if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
				cedula = "Cedula:" + factura.getCliente().getCedula();
			}

		}
		if (cedula.equals(Constantes.EMPTY)) {
			if (factura.getCliente().getIdentificacionExtranjero() != null && !factura.getCliente().getIdentificacionExtranjero().equals(Constantes.EMPTY)) {
				cedula = "Cedula:" + factura.getCliente().getIdentificacionExtranjero();
			}
		}
		return cedula;
	}

	/**
	 * Ventas por Mini super
	 * @param model
	 * @return
	 */
//
//	@Autowired
//	private CertificadoBo certificadoBo;

	@RequestMapping(value = "/puntoVenta", method = RequestMethod.GET)
	public String crearCompras(ModelMap model, HttpServletRequest request) {
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
//		 Se ejecuta este comando pero antes se ejecutan el comando para sacar la llave
//		 criptografica desde linux
//		certificadoBo.agregar(usuario.getEmpresa(), "", "");
		if (usuarioBo.isUsuario_Condominio(usuario) || usuarioBo.isAdministrador_sistema(usuario) || usuarioBo.isAdministrador_empresa(usuario) || usuarioBo.isAdministrador_restaurante(usuario)) {
			model.addAttribute("rolAdminitrador", 1);
		} else {
			model.addAttribute("rolAdminitrador", 0);
		}
		return "views/facturas/puntoVenta";
	}

	/**
	 * Ventas por servicios por profeccionates
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ventasPorServicio", method = RequestMethod.GET)
	public String ventasPorServicios(Device device) {
//		
//    String deviceType = "browser";
//    String platform = "browser";
		String viewName = "views/facturacionProfesionales/ventasPorServiciosNormal.html";

//    if (device.isNormal()) {
//        deviceType = "browser";
//    } else if (device.isMobile()) {
//        deviceType = "mobile";
//        viewName = "views/facturacionProfesionales/ventasPorServiciosMobile.html";
//    } else if (device.isTablet()) {
//        deviceType = "tablet";
//        viewName = "views/facturacionProfesionales/ventasPorServiciosTable.html";
//    }
//    
//    platform = device.getDevicePlatform().name();
//    
//    if (platform.equalsIgnoreCase("UNKNOWN")) {
//        platform = "browser";
//    }

		return viewName;

	}

	/**
	 * Crear Notas de Credito y debito
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/creditoDebito", method = RequestMethod.GET)
	public String creditoDebito(ModelMap model) {
		return "views/facturas/creditoDebito";
	}

	@RequestMapping(value = "/notaDebito.do", method = RequestMethod.GET)
	public String notaDebito(ModelMap model) {
		return "views/facturas/notaDebito";
	}

	@RequestMapping(value = "/ListaFacturasAnulacion", method = RequestMethod.GET)
	public String listaFacturasAnulacion(ModelMap model) {
		return "views/facturas/listaFacturasAnulacion";
	}

	/**
	 * Listado de facturas anuladas y facturadas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListaFacturas", method = RequestMethod.GET)
	public String listaFacturas(ModelMap model) {
		return "views/facturas/listaFacturas";
	}

	@RequestMapping(value = "/ventaByCategoria", method = RequestMethod.GET)
	public String ventaByCategoria(ModelMap model) {
		return "views/facturas/vetasbycategoria";
	}
	
	@RequestMapping(value = "/ventaByArticulosDetallados", method = RequestMethod.GET)
	public String ventaByArticulos(ModelMap model) {
		return "views/facturas/ventasbyArticulos";
	}
	


	/**
	 * Listado de facturas anuladas y facturadas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/TotalFacturas", method = RequestMethod.GET)
	public String totalFacturas(ModelMap model) {
		return "views/facturas/totalFacturas";
	}

	@RequestMapping(value = "/TotalImpuestoVentasMensuales", method = RequestMethod.GET)
	public String totalImpuestoVentasMensuales(ModelMap model) {
		return "views/facturas/totalImpuestoVentasMensuales";
	}

	/**
	 * Busca el total de facturas por rango de fechas
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/TotalFacturasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public TotalFacturaCommand totalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Integer estado, String actividadEconomica) {

		Date fechaInicio = Utils.parseDate(fechaInicioParam);

		Date fechaFinalP = Utils.parseDate(fechaFinParam);
		if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		return usuario.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA_REINTEGRO_GASTOS) || usuario.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA) ? facturaBo.sumarFacturasNoElectronica(fechaInicio, fechaFinalP, usuario.getEmpresa().getId(), estado, actividadEconomica) : facturaBo.sumarFacturas(fechaInicio, fechaFinalP, usuario.getEmpresa().getId(), estado, actividadEconomica);
	}

	@RequestMapping(value = "/EnvioDetalleTotalFacturasAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator<?> envioDetalleTotalFacturasAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute String datos, BindingResult result, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String correoAlternativo, @RequestParam Integer estado, String actividadEconomica) throws IOException {
		RespuestaServiceValidator<?> respuestaServiceValidator = new RespuestaServiceValidator<Object>();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			// Se obtiene los totales
			Date fechaInicio = Utils.parseDate(fechaInicioParam);
			Date fechaFinalP = Utils.parseDate(fechaFinParam);
			if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
				if (fechaFinalP != null) {
					fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
				}
			}
			TotalFacturaCommand facturaCommand = facturaBo.sumarFacturas(fechaInicio, fechaFinalP, usuario.getEmpresa().getId(), estado, actividadEconomica);

			DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
			String inicio1 = dateFormat1.format(fechaInicio);
			String fin1 = dateFormat1.format(fechaFinalP);
			List<Map<String, Object>> listaObjetos = detalleBo.totalbyImpuestos(inicio1, fin1, estado, usuario.getEmpresa().getId(), actividadEconomica);

			@SuppressWarnings("rawtypes")
			ArrayList arrayList = new ArrayList();
			arrayList = (ArrayList<?>) listaObjetos;
			JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
			ArrayList<TotalbyImpuestosCommand> detallesFacturaCommand = new ArrayList<>();
			Gson gson = new Gson();
			if (jsonArray1 != null) {
				for (int i = 0; i < jsonArray1.size(); i++) {
					TotalbyImpuestosCommand totalbyImpuestosCommand = gson.fromJson(jsonArray1.get(i).toString(), TotalbyImpuestosCommand.class);
					detallesFacturaCommand.add(totalbyImpuestosCommand);
				}
			}

			// Se prepara el excell
			ByteArrayOutputStream baos = Utils.convertirOutStream(detalleBo.createExcelVentasXCodigo(detallesFacturaCommand, fechaInicioParam, fechaFinParam, usuario.getEmpresa(), actividadEconomica));
			Collection<Attachment> attachments = createAttachments(attachment("FacturasMensuales", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

			// Se prepara el correo
			String from = "factura@facturaemprendesoftcr.com";
			String nombre = usuario.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? usuario.getEmpresa().getNombre() : usuario.getEmpresa().getNombreComercial();
			nombre = nombre.length() > 50 ? nombre.substring(0, 50) : nombre;
			String subject = nombre + " Facturas Rango de fechas: " + fechaInicioParam + " al " + fechaFinParam;

			ArrayList<String> listaCorreos = new ArrayList<>();
			if (correoAlternativo != null && correoAlternativo.length() > 0) {
				listaCorreos.add(correoAlternativo);
			} else {
				listaCorreos.add(usuario.getEmpresa().getCorreoElectronico());
			}
			String estadoDesc = Constantes.EMPTY;
			if (estado == 2) {
				estadoDesc = "Facturados";
			}
			if (estado == 5) {
				estadoDesc = "Anulados";
			}
			if (estado == 6) {
				estadoDesc = "Aceptados";
			}
			if (estado == 7) {
				estadoDesc = "Rechazados";
			}

			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("nombreEmpresa", usuario.getEmpresa().getNombre());
			modelEmail.put("cedula", usuario.getEmpresa().getCedula());
			modelEmail.put("estadoDesc", "Documentos con Estado:" + estadoDesc);
			modelEmail.put("actividad", actividadEconomica.equals(Constantes.COMBO_TODOS) ? Constantes.EMPTY : "Actividad Economica:" + actividadEconomica);
			modelEmail.put("fechaInicial", Utils.getFechaStr(fechaInicio));
			modelEmail.put("fechaFinal", Utils.getFechaStr(fechaFinalP));
			modelEmail.put("total", facturaCommand.getTotal() != null ? Utils.formateadorMiles(facturaCommand.getTotal()) : Constantes.ZEROS);
			modelEmail.put("totalDescuentos", facturaCommand.getTotalDescuentos() != null ? facturaCommand.getTotalDescuentosSTR() : Constantes.ZEROS);
			modelEmail.put("totalOtrosCargos", facturaCommand.getTotalOtrosCargos() != null ? facturaCommand.getTotalOtrosCargosSTR() : Constantes.ZEROS);
			modelEmail.put("totalEfectivo", facturaCommand.getTotalEfectivo() != null ? facturaCommand.getTotalEfectivoSTR() : Constantes.ZEROS);
			modelEmail.put("totalTarjeta", facturaCommand.getTotalTarjeta() != null ? facturaCommand.getTotalTarjetaSTR() : Constantes.ZEROS);
			modelEmail.put("totalBanco", facturaCommand.getTotalBanco() != null ? facturaCommand.getTotalBancoSTR() : Constantes.ZEROS);
			modelEmail.put("totalCredito", facturaCommand.getTotalCredito() != null ? facturaCommand.getTotalCreditoSTR() : Constantes.ZEROS);
			modelEmail.put("totalDinero", facturaCommand.getTotalPagosSTR());
			modelEmail.put("totalImpuestos", facturaCommand.getTotalImpuestos() != null ? facturaCommand.getTotalImpuestosSTR() : Constantes.ZEROS);
			modelEmail.put("totalVentasNetas", facturaCommand.getTotalVentasNetas() != null ? facturaCommand.getTotalVentasNetasSTR() : Constantes.ZEROS);
			modelEmail.put("totalVentasExentas", facturaCommand.getTotalVentasExentas() != null ? facturaCommand.getTotalVentasExentasSTR() : Constantes.ZEROS);
			modelEmail.put("totalVentasGravadas", facturaCommand.getTotalVentasGravadas() != null ? facturaCommand.getTotalVentasGravadasSTR() : Constantes.ZEROS);
			modelEmail.put("totalMenosNotas", Utils.formateadorMiles(facturaCommand.getTotal() + facturaCommand.getTotal_n()));

			modelEmail.put("total_n", facturaCommand.getTotal_n() != null ? facturaCommand.getTotalNC() : Constantes.ZEROS);
			modelEmail.put("totalDescuentos_n", facturaCommand.getTotalDescuentos_n() != null ? facturaCommand.getTotalDescuentosNC() : Constantes.ZEROS);
			modelEmail.put("totalOtrosCargos_n", facturaCommand.getTotalOtrosCargos_n() != null ? facturaCommand.getTotalOtrosCargosNC() : Constantes.ZEROS);
			modelEmail.put("totalEfectivo_n", facturaCommand.getTotalEfectivo_n() != null ? facturaCommand.getTotalEfectivoNC() : Constantes.ZEROS);
			modelEmail.put("totalTarjeta_n", facturaCommand.getTotalTarjeta_n() != null ? facturaCommand.getTotalTarjetaNC() : Constantes.ZEROS);
			modelEmail.put("totalBanco_n", facturaCommand.getTotalBanco_n() != null ? facturaCommand.getTotalBancoNC() : Constantes.ZEROS);
			modelEmail.put("totalCredito_n", facturaCommand.getTotalCredito_n() != null ? facturaCommand.getTotalCreditoNC() : Constantes.ZEROS);
			modelEmail.put("totalImpuestos_n", facturaCommand.getTotalImpuestos_n() != null ? facturaCommand.getTotalImpuestosNC() : Constantes.ZEROS);
			modelEmail.put("totalVentasNetas_n", facturaCommand.getTotalVentasNetas_n() != null ? facturaCommand.getTotalVentasNetasNC() : Constantes.ZEROS);
			modelEmail.put("totalVentasExentas_n", facturaCommand.getTotalVentasExentas_n() != null ? facturaCommand.getTotalVentasExentasNC() : Constantes.ZEROS);
			modelEmail.put("totalVentasGravadas_n", facturaCommand.getTotalVentasGravadas_n() != null ? facturaCommand.getTotalVentasGravadasNC() : Constantes.ZEROS);
			modelEmail.put("totalDinero_n", facturaCommand.getTotalPagosNC());

			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_RESUMEN_VENTAS_RANGO_FECHA, modelEmail);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			log.error("** Error  Enviado correo: " + " fecha " + new Date() + "" + e.getMessage());
			System.out.println("No enviado correctamente el correo");
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());

		}

		return respuestaServiceValidator;

	}

	/**
	 * Resumen pdf
	 * @param request
	 * @param response
	 * @param model
	 * @param datos
	 * @param result
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param correoAlternativo
	 * @param estado
	 * @param actividadEconomica
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ResumenPDFIVAFacturasAndCompras.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public void ResumenPDFIVAFacturasAndCompras(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute String datos, BindingResult result, @RequestParam(value = "fechaInicioParam", required = false) String fechaInicioParam, @RequestParam(value = "fechaFinParam", required = false) String fechaFinParam, @RequestParam(value = "correoAlternativo", required = false) String correoAlternativo, @RequestParam(value = "estado", required = false) Integer estado, @RequestParam(value = "actividadEconomica", required = false) String actividadEconomica) throws IOException {

		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			// Se obtiene los totales
			Date fechaInicio = Utils.parseDate(fechaInicioParam);
			Date fechaFinalP = Utils.parseDate(fechaFinParam);
			if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
				if (fechaFinalP != null) {
					fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
				}
			}

//
			DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
			String inicio1 = dateFormat1.format(fechaInicio);
			String fin1 = dateFormat1.format(fechaFinalP);
			List<Map<String, Object>> listaObjetos = detalleBo.totalbyImpuestos(inicio1, fin1, estado, usuario.getEmpresa().getId(), actividadEconomica);

			@SuppressWarnings("rawtypes")
			ArrayList arrayList = new ArrayList();
			arrayList = (ArrayList<?>) listaObjetos;
			JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
			ArrayList<TotalbyImpuestosCommand> listaDetallada = new ArrayList<>();
			Gson gson = new Gson();
			if (jsonArray1 != null) {
				for (int i = 0; i < jsonArray1.size(); i++) {
					TotalbyImpuestosCommand totalbyImpuestosCommand = gson.fromJson(jsonArray1.get(i).toString(), TotalbyImpuestosCommand.class);
					listaDetallada.add(totalbyImpuestosCommand);
				}
			}
			Map<String, Object> parametros = new HashMap<>();
			File file = new File("/opt/appjava/data/logos/" + usuario.getEmpresa().getLogo());
			parametros.put("P_logo", file != null ? file.getPath() : Constantes.EMPTY);
			parametros.put("P_nombre_comercial", usuario.getEmpresa().getNombreComercial());
			parametros.put("P_nombreCompleto", usuario.getEmpresa().getNombre());
			parametros.put("P_cedula", "Ced: " + usuario.getEmpresa().getCedula());
			parametros.put("p_telefono", "Telf: " + usuario.getEmpresa().getTelefono() + "");
			parametros.put("P_correo_electronico", usuario.getEmpresa().getCorreoElectronico());
			parametros.put("P_direccion", usuario.getEmpresa().getOtraSenas());
			parametros.put("P_contable", "Resumen de ventas y  compras para el periodo de " + fechaInicioParam + " al " + fechaFinParam);

			// Ventas de Facturas resumen detallado iva

			TotalbyResumenImpuestosCommand totalbyResumenImpuestosCommand = new TotalbyResumenImpuestosCommand(listaDetallada);

			parametros = totalResumenVentaIVA(parametros, totalbyResumenImpuestosCommand);

			Collection<CompraIVA> recepcionFacturas = consultasNativeBo.findBySumComprasIVAResumen(usuario.getEmpresa(), inicio1, fin1);
			TotalbyResumenImpuestosCommand totalbyResumenImpuestosComprasCommand = new TotalbyResumenImpuestosCommand(recepcionFacturas);

			parametros = totalResumenCompraIVA(parametros, totalbyResumenImpuestosComprasCommand);

			String nombreArchivo = "ResumenIVAComprasAndVentas" + UUID.randomUUID().toString() + ".pdf";
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

			URL url = Thread.currentThread().getContextClassLoader().getResource(Constantes.REPORTE_MENSUAL_COMPRAS_Y_VENTAS);
			byte[] bytes = GenerarReporte.jasperPDFBytes(parametros, url.getPath(), null);
			if (bytes != null && bytes.length > 0) {
				response.setContentType("application/pdf");
				// response.setHeader("Content-Disposition", "attachment;filename=etiquetas.pdf");
				ServletOutputStream outputstream = response.getOutputStream();
				outputstream.write(bytes, 0, bytes.length);
				outputstream.flush();
				outputstream.close();

			} else {
				System.out.println("NO trae nada");
			}
		} catch (Exception e) {
			log.error("** Error  generar PDF " + " fecha " + new Date() + "" + e.getMessage());

		}

	}

	private Map<String, Object> totalResumenCompraIVA(Map<String, Object> parametros, TotalbyResumenImpuestosCommand totalbyResumenImpuestosCommand) {
		// Sumas de ventas y desglose del iva

		// 0%
		parametros.put("P_iva_compra_cero", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_01()));
		// 1%
		parametros.put("P_iva_compra_1", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_02()));
		// 2%
		parametros.put("P_iva_compra_2", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_03()));
		parametros.put("P_iva_compra_4", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_04()));

		// 4%
		parametros.put("P_iva_compra_trans_cero", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_05()));
		// 0 transitorio%
		parametros.put("P_iva_compra_trans_4", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_06()));
		// 4 transitorio%
		parametros.put("P_iva_compra_13", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_08()));
		// 8 transitorio%
		parametros.put("P_cemento_iva_c", Utils.formateadorMiles(Constantes.ZEROS_DOUBLE));
		// Total del iva agravado
		parametros.put("P_compra_iva_total", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_01() + totalbyResumenImpuestosCommand.getImp_02() + totalbyResumenImpuestosCommand.getImp_03() + totalbyResumenImpuestosCommand.getImp_04() + totalbyResumenImpuestosCommand.getImp_04() + totalbyResumenImpuestosCommand.getImp_05() + totalbyResumenImpuestosCommand.getImp_07() + totalbyResumenImpuestosCommand.getImp_06() + totalbyResumenImpuestosCommand.getImp_08()));
		// Resumen de total venta 0%
		parametros.put("P_compra_0", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_01()));
		// Resumen de total venta 1%
		parametros.put("P_compra_1", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_02()));
		// Resumen de total venta 2%
		parametros.put("P_compra_2", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_03()));
		// Resumen de total venta 4%
		parametros.put("P_compra_4", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_04()));
		// Resumen de total venta trans 0%
		parametros.put("P_c_trans_0", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_05()));

		// Resumen de total venta trans 4%
		parametros.put("P_c_trans_4", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_06()));
		// Resumen de total venta trans 8%
		parametros.put("P_c_trans_8", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_07()));

		// Resumen de total venta trans 13
		parametros.put("P_compra_13", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_08()));
		// Resumen de total venta cemento
		parametros.put("P_total_cemento_c", Utils.formateadorMiles(Constantes.ZEROS_DOUBLE));
		// Resumen de total otros
		parametros.put("P_total_otros_c", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_otros_impuestos_v()));
		// Resumen de total Gravado
		parametros.put("P_compra_gravada", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_gravado()));
		// Resumen de total de impuesto
		parametros.put("P_exentos_total_compras", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_exento()));
		// Resumen de total de exentos
		parametros.put("P_compra_impuesto_total", Utils.formateadorMiles(Constantes.ZEROS_DOUBLE));

		// Resumen de total de Descuentos
		parametros.put("P_total_compra", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_comprobante()));

		// Resumen de total de ventas
		parametros.put("P_total_exoneracion_compra", Utils.formateadorMiles(Constantes.ZEROS_DOUBLE));

		// Resumen de total de ventas de exoneraciones
		parametros.put("P_total_exoneracion_venta", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_exo()));
		// Resumen de total venta del servicio del mesero
		parametros.put("P_total_por_restaurante", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_otros_cargos()));
		parametros.put("P_total_descuento_compras", Utils.formateadorMiles(Constantes.ZEROS_DOUBLE));

		return parametros;
	}

	private Map<String, Object> totalResumenVentaIVA(Map<String, Object> parametros, TotalbyResumenImpuestosCommand totalbyResumenImpuestosCommand) {
		// Sumas de ventas y desglose del iva

		// 0%
		parametros.put("p_iva_cero_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_01()));
		// 1%
		parametros.put("P_iva_1_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_02()));
		// 2%
		parametros.put("P_iva_2_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_03()));
		// 4%
		parametros.put("P_iva_4_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_04()));
		// 0 transitorio%
		parametros.put("P_iva_trans_0_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_05()));
		// 4 transitorio%
		parametros.put("P_iva_trans_4_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_06()));
		// 8 transitorio%
		parametros.put("P_iva_trans_8_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_07()));
		// iva 13
		parametros.put("P_iva_13_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_08()));
		// Iva cemento
		parametros.put("P_cemento_iva_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_imp_cemento()));
		// Iva otros
		parametros.put("P_iva_otros_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_otros_impuestos()));
		// Total del iva agravado
		parametros.put("P_iva_total_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getImp_01() + totalbyResumenImpuestosCommand.getImp_02() + totalbyResumenImpuestosCommand.getImp_03() + totalbyResumenImpuestosCommand.getImp_04() + totalbyResumenImpuestosCommand.getImp_04() + totalbyResumenImpuestosCommand.getImp_05() + totalbyResumenImpuestosCommand.getImp_07() + totalbyResumenImpuestosCommand.getImp_06() + totalbyResumenImpuestosCommand.getImp_08()));
		// Resumen de total venta 0%
		parametros.put("P_venta_cero", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_01()));
		// Resumen de total venta 1%
		parametros.put("P_venta_1", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_02()));
		// Resumen de total venta 2%
		parametros.put("P_venta_2", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_03()));
		// Resumen de total venta 4%
		parametros.put("P_venta_4", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_04()));
		// Resumen de total venta trans 0%
		parametros.put("P_v_trans_0", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_05()));
		// Resumen de total venta trans 4%
		parametros.put("P_v_trans_4", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_06()));
		// Resumen de total venta trans 8%
		parametros.put("P_v_trans_8", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_07()));
		// Resumen de total venta trans 13
		parametros.put("P_venta_13", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getVenta_imp_08()));
		// Resumen de total venta cemento
		parametros.put("P_total_cemento_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_imp_cemento_v()));
		// Resumen de total otros
		parametros.put("P_total_otros_v", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_otros_impuestos_v()));
		// Resumen de total Gravado
		parametros.put("p_venta_gravada", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_gravado()));
		// Resumen de total de impuesto
		parametros.put("P_total_impuestos", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_impuesto()));
		// Resumen de total de exentos
		parametros.put("P_total_exentos", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_exento()));

		// Resumen de total de Descuentos
		parametros.put("P_total_descuento", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_descuentos()));

		// Resumen de total de ventas
		parametros.put("P_total_ventas", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_comprobante()));

		// Resumen de total de ventas de exoneraciones
		parametros.put("P_total_exoneracion_venta", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_exo()));
		// Resumen de total venta del servicio del mesero
		parametros.put("P_total_por_restaurante", Utils.formateadorMiles(totalbyResumenImpuestosCommand.getTotal_otros_cargos()));

		return parametros;
	}

	// Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarDetalleTotalFacturasAjax.do", method = RequestMethod.GET)
	public void descargarDetalleTotalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Integer estado, @RequestParam String actividadEconomica) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFinalP = Utils.parseDate(fechaFinParam);
		if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		Collection<Factura> facturas = facturaBo.facturasRangoEstado(estado, fechaInicio, fechaFinalP, usuario.getEmpresa().getId(), actividadEconomica);

		String nombreArchivo = "FacturasMensuales.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayInputStream inputStream = facturaBo.createExcelFacturasTotalMensual(facturas, usuario.getEmpresa(), fechaInicioParam, fechaFinParam, estado, actividadEconomica);

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	@RequestMapping(value = "/DescargarPorDetalleTotalFacturasAjax.do", method = RequestMethod.GET)
	public void descargarPorDetalleTotalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Integer estado, @RequestParam String actividadEconomica) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);

		Date fechaFinalP = Utils.parseDate(fechaFinParam);
		if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		Collection<Factura> facturas = facturaBo.facturasRangoEstado(estado, fechaInicio, fechaFinalP, usuario.getEmpresa().getId(), actividadEconomica);

		String nombreArchivo = "FacturasMensuales.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = Utils.convertirOutStream(facturaBo.createExcelFacturasTotalMensual(facturas, usuario.getEmpresa(), fechaInicioParam, fechaFinParam, estado, actividadEconomica));
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	/**
	 * i Enviar coreo de la consulta listar factura
	 * @param request
	 * @param response
	 * @param correoAlternativo
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param estado
	 * @param actividadEconomica
	 */

	/**
	 * PDF de las proformas realizadas por empresa
	 * @param request
	 * @param response
	 * @param model
	 * @param idFactura
	 * @throws Exception
	 */
	@RequestMapping(value = "/generaProformasPDF.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public void generarProformasPDF(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura) throws Exception {
		try {
			Factura factura = facturaBo.findById(idFactura);

			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			Collection<Detalle> detalles = detalleBo.findByFactura(factura);

			List<DetalleFacturaElectronica> detallesFactura = detalles.stream().sorted(Comparator.comparingInt(Detalle::getNumeroLinea)).map(TO_DETALLE).collect(toList());
			facturaElectronica.setDetalleFacturaElectronica(detallesFactura);

			ByteArrayOutputStream namePDF = ReportePdfView.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			byte[] bytes = namePDF.toByteArray();
			if (bytes != null && bytes.length > 0) {
				response.setContentType("application/pdf");
				ServletOutputStream outputstream = response.getOutputStream();
				outputstream.write(bytes, 0, bytes.length);
				outputstream.flush();
				outputstream.close();

			} else {
				System.out.println("NO trae nada");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (com.google.zxing.WriterException ex) {

		}

	}

	/**
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/generaFacturaPDF", method = RequestMethod.GET, headers = "Accept=application/json")
	public void generarFacturaPDF(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam(value = "idFactura", required = false) Long idFactura, @RequestParam(value = "consecutivo", required = false) String consecutivo) throws Exception {
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Factura factura = idFactura != null ? facturaBo.findById(idFactura) : facturaBo.findByConsecutivoAndEmpresa(consecutivo, usuarioSesion.getEmpresa());
			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			Collection<Detalle> detalles = detalleBo.findByFactura(factura);

			List<DetalleFacturaElectronica> detallesFactura = detalles.stream().sorted(Comparator.comparingInt(Detalle::getNumeroLinea)).map(TO_DETALLE).collect(toList());
			facturaElectronica.setDetalleFacturaElectronica(detallesFactura);

			ByteArrayOutputStream namePDF = ReportePdfView.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);

			byte[] bytes = namePDF.toByteArray();
			if (bytes != null && bytes.length > 0) {
				response.setContentType("application/pdf");
				ServletOutputStream outputstream = response.getOutputStream();
				outputstream.write(bytes, 0, bytes.length);
				outputstream.flush();
				outputstream.close();

			} else {
				System.out.println("NO trae nada");
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (com.google.zxing.WriterException ex) {

		}

	}

	/**
	 * Facturas En espera de convertirse en factura oficial
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/ListarFacturasEsperaActivasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		return facturaBo.listarEsperaActivasAjax(request, response);
	}

	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarFacturasEsperaActivasCajeraAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasCajeroAjax(HttpServletRequest request, HttpServletResponse response) {
		RespuestaServiceDataTable respuestaServiceDataTable = new RespuestaServiceDataTable();
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (!usuarioBo.isAdministrador_cajero(usuarioSesion)) {
			return respuestaServiceDataTable;

		}
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<FacturasEsperaNativa> objetos = consultasNativeBo.findByVentaEspera(usuarioSesion.getEmpresa());
		for (FacturasEsperaNativa facturasEsperaNativa : objetos) {
			solicitudList.add(facturasEsperaNativa);
		}
		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;
	}

	/**
	 * Facturas En espera de convertirse en factura oficial
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarFacturasEsperaPorMesaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasEsperaPorMesaAjax(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ParametrosPaginacionMesa parametrosPaginacionMesa) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<ListarFacturaMesaNative> objetos = consultasNativeBo.findByFacturaPorMesas(usuarioSesion.getEmpresa(), parametrosPaginacionMesa.getMesa());
		for (ListarFacturaMesaNative facturasEsperaNativa : objetos) {
			solicitudList.add(facturasEsperaNativa);
		}
		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;
	}

	/**
	 * Solo facturas de credito y debito en espera
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarNotasCreditoAndDebitoEsperaActivasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarNotasDebidoAndCreditoActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Factura");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_TIQUETE.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		if (usuarioBo.isAdministrador_vendedor(usuarioSesion)) {
			dataTableFilter = new JqGridFilter("usuarioCreacion.id", "'" + usuarioSesion.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListaFacturasImpuestoServicioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasImpuestoServicioAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Integer estado, @RequestParam String actividadEconomica) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Date fechaInicioP = Utils.parseDate(fechaInicioParam);

		Date fechaFinalP = Utils.parseDate(fechaFinParam);
		if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}

		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		Collection<ListarFacturasImpuestoServicioNativa> facturas = consultasNativeBo.findByFacturasImpuestoServicio(usuarioSesion.getEmpresa(), usuarioSesion.getId(), estado, inicio1, fin1, actividadEconomica);
		List<Object> solicitudList = new ArrayList<Object>();
		for (ListarFacturasImpuestoServicioNativa listarFacturasImpuestoServicioNativa : facturas) {
			// no se carga el usuario del sistema el id -1
			if (listarFacturasImpuestoServicioNativa.getId().longValue() > 0L) {
				solicitudList.add(new FacturaImpuestoServicioCommand(listarFacturasImpuestoServicioNativa));
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

	@Secured({ "ROLE_SUPER_ADMIN", "ROLE_ADMIN", "ROLE_USER", "ROLE_INVENTARIO", "ROLE_INVENTARIO_READ", "ROLE_INVENTARIO_READ_WRITER_CREAR_PEDIDOS" })
	@PostMapping(value = "/obtener-pedidos-activos")
	public @ResponseBody List<Map<String, Object>> obtenerPedidosActivos(HttpSession session) {
		if (session.getAttribute("SESSION_EMPRESA_ID") != null) {
			jdbcTemplate = new JdbcTemplate(dataSource);
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("emisorId", Long.parseLong(session.getAttribute("SESSION_EMPRESA_ID").toString()));
			String sql = "SELECT id, secuencia_factura_compra, codigo_proveedor, nombre_completo, observaciones, proveedor_id FROM v_orden_compra WHERE estado_orden_compra = 'A' AND emisor_id=:emisorId";
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
			List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);
			return listaObjetos;
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListaFacturasGananciaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasGananciaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Integer estado, @RequestParam String actividadEconomica, @RequestParam Cliente cliente, @RequestParam Integer idCategoria, @RequestParam String codigo) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		idCategoria = idCategoria == null ? Constantes.ZEROS : idCategoria;
		Date fechaInicioP = Utils.parseDate(fechaInicioParam);
		Date fechaFinalP = Utils.parseDate(fechaFinParam);
		if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		Collection<ConsultaGananciaNative> facturas = consultasNativeBo.findByDetallesGanancia(usuarioSesion.getEmpresa(), cliente, estado, inicio1, fin1, actividadEconomica, idCategoria, codigo);
		List<Object> solicitudList = new ArrayList<Object>();
		for (ConsultaGananciaNative consultaGananciaNative : facturas) {
			solicitudList.add(new ConsultaFacturaGananciasNativeCommand(consultaGananciaNative));
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
	 * EnvioUtilidadXCCorreoAjax Utilidad de ventas
	 * @param request
	 * @param response
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param estado
	 * @param actividadEconomica
	 * @param cliente
	 * @param idCategoria
	 * @param codigo
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListaUtilidadAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarUtilidadAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Integer estado, @RequestParam String actividadEconomica, @RequestParam Long idCliente, @RequestParam Integer idCategoria, @RequestParam String codigo, @RequestParam String tipoDoc, @RequestParam String numeroFactura, @RequestParam Integer idUsuario) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		idCategoria = idCategoria == null ? Constantes.ZEROS : idCategoria;
		Date fechaInicioP = Utils.parseDate(fechaInicioParam);
		Date fechaFinalP = Utils.parseDate(fechaFinParam);
		if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		Cliente cliente = clienteBo.buscar(idCliente);
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		Collection<ConsultaUtilidadNative> facturas = consultasNativeBo.findByUtilidad(usuarioSesion.getEmpresa(), cliente, estado, inicio1, fin1, actividadEconomica, idCategoria, codigo, tipoDoc, numeroFactura, idUsuario);

		List<Object> solicitudList = new ArrayList<Object>();
		for (ConsultaUtilidadNative consultaUtilidadNative : facturas) {
			solicitudList.add(consultaUtilidadNative);
		}
		respuestaService.setRecordsTotal((long) facturas.size());
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;
	}

	// Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarUtilidadAjax.do", method = RequestMethod.GET)
	public void descargarUtilidadAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Integer estado, @RequestParam String actividadEconomica, @RequestParam Long idCliente, @RequestParam Integer idCategoria, @RequestParam String codigo, @RequestParam String tipoDoc, @RequestParam String numeroFactura, @RequestParam Integer idUsuario) throws IOException, Exception {

		fechaInicioParam = fechaInicioParam == null ? Constantes.EMPTY : fechaInicioParam;
		fechaFinParam = fechaFinParam == null ? Constantes.EMPTY : fechaFinParam;
		numeroFactura = numeroFactura == null ? Constantes.EMPTY : numeroFactura;

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		idCategoria = idCategoria == null ? Constantes.ZEROS : idCategoria;
		Date fechaInicioP = Utils.parseDate(fechaInicioParam);
		Date fechaFinalP = Utils.parseDate(fechaFinParam);
		if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		Cliente cliente = clienteBo.buscar(idCliente);
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		Collection<ConsultaUtilidadNative> facturas = consultasNativeBo.findByUtilidad(usuarioSesion.getEmpresa(), cliente, estado, inicio1, fin1, actividadEconomica, idCategoria, codigo, tipoDoc, numeroFactura, idUsuario);

		String nombreArchivo = "Utilidad_" + fechaInicioParam + "_al_" + fechaFinParam + ".xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		// ByteArrayOutputStream baos = createExcelUtilidad(facturas);
//		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
		ByteArrayInputStream inputStream = detalleBo.createExcelUtilidad(facturas, usuarioSesion.getEmpresa(), fechaInicioParam, fechaFinParam);

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnvioUtilidadXCCorreoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator envioUtilidadXCorreoAjax(HttpServletRequest request, HttpServletResponse response, BindingResult result, ModelMap model, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Integer estado, @RequestParam String actividadEconomica, @RequestParam Long idCliente, @RequestParam Integer idCategoria, @RequestParam String codigo, @RequestParam String tipoDoc, @RequestParam String correoAlternativo, @RequestParam String totalVenta, @RequestParam String totalCosto, @RequestParam String totalUtilidad, @RequestParam String numeroFactura, @RequestParam Integer idUsuario) throws IOException, Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();

		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			idCategoria = idCategoria == null ? Constantes.ZEROS : idCategoria;
			Date fechaInicioP = Utils.parseDate(fechaInicioParam);
			Date fechaFinalP = Utils.parseDate(fechaFinParam);
			if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
				if (fechaFinalP != null) {
					fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
				}
			}

			Cliente cliente = clienteBo.buscar(idCliente);
			DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
			String inicio1 = dateFormat1.format(fechaInicioP);
			String fin1 = dateFormat1.format(fechaFinalP);
			Collection<ConsultaUtilidadNative> facturas = consultasNativeBo.findByUtilidad(usuarioSesion.getEmpresa(), cliente, estado, inicio1, fin1, actividadEconomica, idCategoria, codigo, tipoDoc, numeroFactura, idUsuario);

			// Se prepara el excell
			ByteArrayOutputStream baos = Utils.convertirOutStream(detalleBo.createExcelUtilidad(facturas, usuarioSesion.getEmpresa(), fechaInicioParam, fechaFinParam));

			Collection<Attachment> attachments = createAttachments(attachment("utilidad", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

			// Se prepara el correo
			String from = "factura@facturaemprendesoftcr.com";
//			if (usuarioSesion.getEmpresa().getAbreviaturaEmpresa() != null) {
//				if (!usuarioSesion.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
//					from = usuarioSesion.getEmpresa().getAbreviaturaEmpresa() + "_Ventas_Utilidad" + "_No_Reply@facturaemprendesoftcr.com";
//				}
//			}
			String nombre = usuarioSesion.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? usuarioSesion.getEmpresa().getNombre() : usuarioSesion.getEmpresa().getNombreComercial();
			nombre = nombre.length() > 50 ? nombre.substring(0, 50) : nombre;

			String subject = nombre + " Utilidad Rango de fechas: " + fechaInicioParam + " al " + fechaFinParam;
			ArrayList<String> listaCorreos = new ArrayList<>();
			listaCorreos.add(correoAlternativo);
			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("fechaInicial", fechaInicioParam);
			modelEmail.put("fechaFinal", fechaFinParam);
			modelEmail.put("totalVenta", totalVenta);
			modelEmail.put("totalCosto", totalCosto);
			modelEmail.put("totalUtilidad", totalUtilidad);
			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_UTILIDAD, modelEmail);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			log.error("** Error  Enviado correo: " + " fecha " + new Date() + " " + e.getMessage());
			System.out.println("No enviado correctamente el correo");

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
		}
		return respuestaServiceValidator;
	}

	/**
	 * Lista de las Proformas activas
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarProformasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarProformasActivasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "estado", required = false) Integer estado) {
		Boolean administrador = Boolean.FALSE;
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		estado = estado == null ? Constantes.FACTURA_ESTADO_PROFORMAS : estado;
		Collection<ProformasByEmpresaAndEstado> objetoProformaAdmin = null;
		Collection<ProformasByEmpresaAndEstado> objetoAnuladoAdmin = null;
		Collection<ProformasByEmpresaAndFacturada> objetoFacturasAdmin = null;
		objetoProformaAdmin = estado.equals(Constantes.FACTURA_ESTADO_PROFORMAS) ? consultasNativeBo.findByProformasByEmpresaAndEstado(usuarioSesion.getEmpresa(), estado) : null;
		objetoAnuladoAdmin = estado.equals(Constantes.FACTURA_ESTADO_ANULADA_PROFORMA) ? consultasNativeBo.findByProformasByEmpresaAndEstado(usuarioSesion.getEmpresa(), estado) : null;
		objetoFacturasAdmin = objetoProformaAdmin == null && objetoAnuladoAdmin == null ? consultasNativeBo.findByProformasByEmpresaFacturada(usuarioSesion.getEmpresa()) : null;
		administrador = Boolean.TRUE;
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		if (administrador) {
			if (objetoProformaAdmin != null) {
				for (ProformasByEmpresaAndEstado proformasByEmpresa : objetoProformaAdmin) {

					// no se carga el usuario del sistema el id -1
					if (proformasByEmpresa.getId().longValue() > 0L) {
						solicitudList.add(new ProformasByEmpresaAndEstadoCommand(proformasByEmpresa));
					}
				}

			}
			if (objetoAnuladoAdmin != null) {
				for (ProformasByEmpresaAndEstado proformasByEmpresaAndEstado : objetoAnuladoAdmin) {

					// no se carga el usuario del sistema el id -1
					if (proformasByEmpresaAndEstado.getId().longValue() > 0L) {
						solicitudList.add(new ProformasByEmpresaAndEstadoCommand(proformasByEmpresaAndEstado));
					}
				}

			}
			if (objetoFacturasAdmin != null) {
				for (ProformasByEmpresaAndFacturada proformasByEmpresaAndFacturada : objetoFacturasAdmin) {

					// no se carga el usuario del sistema el id -1
					if (proformasByEmpresaAndFacturada.getId().longValue() > 0L) {
						solicitudList.add(new ProformasByEmpresaAndEstadoCommand(proformasByEmpresaAndFacturada));
					}
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

	@SuppressWarnings({ "unused", "null" })
	private ArrayList<ProformasSQLNativeCommand> formaDetallesCommand(Collection<ProformasSQLNativeCommand> objetos) throws Exception {
		// Detalles, se forma el detalle de la factura, se contabiliza los totales para evitar problemas con el tema de los decimales en el front
		JSONObject json = null;
		ArrayList<ProformasSQLNativeCommand> detallesFacturaCommand = new ArrayList<>();
		// Agregar Lineas de Detalle

		JSONArray jsonArrayDetalleFactura = (JSONArray) json.get(objetos);
		Gson gson = new Gson();
		if (jsonArrayDetalleFactura != null) {
			Integer numeroLinea = 1;
			for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
				ProformasSQLNativeCommand detalleFacturaCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), ProformasSQLNativeCommand.class);
				detallesFacturaCommand.add(detalleFacturaCommand);
				numeroLinea += 1;
			}
		}
		return detallesFacturaCommand;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/ListarNotasCreditoDebitosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasNotasCreditoDebitosAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente, @RequestParam String tipoDocumento, String actividadEconomica, Integer estado, Integer idUsuario) {
		Cliente cliente = clienteBo.buscar(idCliente);
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		Date fechaFinalP = Utils.parseDate(fechaFin);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}

		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<ListaNotasNative> objetos = consultasNativeBo.findByNotasCreditoAndDebito(usuarioSesion.getEmpresa(), idUsuario, estado, inicio1, fin1, cliente, tipoDocumento, actividadEconomica);
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (ListaNotasNative facturasDelDia : objetos) {
				if (facturasDelDia.getId().longValue() > 0L) {
					solicitudList.add(new FacturaAnulacionCommand(facturasDelDia));
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarFacturasNotasCreditoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasNotasCreditoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente, @RequestParam String tipoDocumento, String actividadEconomica, Integer estado, Integer idUsuario) {
		Cliente cliente = clienteBo.buscar(idCliente);
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		Date fechaFinalP = Utils.parseDate(fechaFin);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}

		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<ListarFacturaNCNativa> objetos = consultasNativeBo.findByFacturasSinNotasCreditos(usuarioSesion.getEmpresa(), idUsuario, estado, inicio1, fin1, cliente, tipoDocumento, actividadEconomica);
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (ListarFacturaNCNativa facturasDelDia : objetos) {
				if (facturasDelDia.getId().longValue() > 0L) {
					solicitudList.add(new FacturaAnulacionCommand(facturasDelDia));
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarFacturasActivasAndAnuladasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente, @RequestParam String tipoDocumento, String actividadEconomica, Integer estado, Integer idUsuario) {
		Cliente cliente = clienteBo.buscar(idCliente);
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		Date fechaFinalP = Utils.parseDate(fechaFin);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}

		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<ListarFacturasTableNativa> objetos = consultasNativeBo.findByFacturasTableAndFechaAndTipoDocAndUsuario(usuarioSesion.getEmpresa(), idUsuario, estado, inicio1, fin1, cliente, tipoDocumento, actividadEconomica);
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (ListarFacturasTableNativa facturasDelDia : objetos) {
				if (facturasDelDia.getId().longValue() > 0L) {
					solicitudList.add(facturasDelDia);
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
	 * Listar Facturas
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idCliente
	 * @param tipoDocumento
	 * @param actividadEconomica
	 * @param estado
	 * @param idUsuario
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/DescargarFacturasExcelAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public void listarFacturasExcelAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente, @RequestParam String tipoDocumento, String actividadEconomica, Integer estado, Integer idUsuario) throws IOException {
		Cliente cliente = clienteBo.buscar(idCliente);
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		Date fechaFinalP = Utils.parseDate(fechaFin);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}

		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);
		Collection<ListarFacturasNativa> objetos = consultasNativeBo.findByFacturasAndFechaAndTipoDocAndUsuario(usuarioSesion.getEmpresa(), idUsuario, estado, inicio1, fin1, cliente, tipoDocumento, actividadEconomica);
		String nombreArchivo = "FacturasMensuales.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");
		// Se prepara el excell
		// ByteArrayOutputStream baos = createExcelListar(objetos);
		ByteArrayInputStream inputStream = facturaBo.createExcelFacturas(objetos, usuarioSesion.getEmpresa(), fechaInicio, fechaFin, estado, cliente);
		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}

	}

//	private ByteArrayOutputStream createExcelListar(Collection<ListarFacturasNativa> facturas) {
//		// Se prepara el excell
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		List<String> headers = Arrays.asList("Fecha Emision", "Cedula", "Cliente", "A nombre", "Usuaro Responsable", "Actividad Economica", "Tipo Documento", "Condicion Venta", "# Documento", "#Proforma", "Exonerado", "Gravados", "Exentos", "Venta neta", "Impuesto", "Descuento", "Otros Cargos", "Total", "Tipo Moneda", "Tipo Cambio", "Total Colones", "Total efectivo", "Total Tarjeta ", "Total Banco", "Total Credito", "Nota");
	// List<String> headers = Arrays.asList("Fecha Emision", "Cedula", "Cliente", "A nombre", "Actividad Economica", "Tipo Documento", "Condicion Venta", "Fecha Credito", "# Documento", "#Proforma", "Gravados", "Exentos", "Venta neta", "Impuesto", "Descuento", "Otros Cargos", "Total", "Tipo Moneda", "Tipo Cambio", "Total Colones", "Total efectivo", "Total Tarjeta ", "Total Banco", "Total Credito", "Nota");
//		new SimpleExporter().gridExport(headers, facturas, "fechaEmisionSTR,cedulaPrin, nombreCompleto,nombreFactura, nombreUsuario,codigoActividad,tipoDocSTR,condicionVentaSTR,numeroConsecutivo,consecutivoProforma,totalExonerado, totalGravado, totalExento, totalVentaNeta, totalImpuesto, totalDescuentos,totalOtrosCargos, totalComprobante,codigoMoneda, tipoCambioSTR, totalColonesNC,totalEfectivo,totalTarjeta,totalBanco,totalCredito,nota", baos);
//		return baos;
//	}

	@RequestMapping(value = "/envioCorreoListarFacturasAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator<?> envioCorreoListarFacturasAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute String datos, BindingResult result, @RequestParam String correoAlternativo, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente, @RequestParam String tipoDocumento, String actividadEconomica, Integer estado, Integer idUsuario) throws IOException {
		RespuestaServiceValidator<?> respuestaServiceValidator = new RespuestaServiceValidator<Object>();
		try {
			Cliente cliente = clienteBo.buscar(idCliente);
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Date fechaInicioP = Utils.parseDate(fechaInicio);
			Date fechaFinalP = Utils.parseDate(fechaFin);
			if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
				if (fechaFinalP != null) {
					fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
				}

			}
			DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
			String inicio1 = dateFormat1.format(fechaInicioP);
			String fin1 = dateFormat1.format(fechaFinalP);
			Collection<ListarFacturasNativa> objetos = consultasNativeBo.findByFacturasAndFechaAndTipoDocAndUsuario(usuarioSesion.getEmpresa(), idUsuario, estado, inicio1, fin1, cliente, tipoDocumento, actividadEconomica);
			// Se prepara el excell
			ByteArrayOutputStream baos = Utils.convertirOutStream(facturaBo.createExcelFacturas(objetos, usuarioSesion.getEmpresa(), fechaInicio, fechaFin, estado, cliente));

			Collection<Attachment> attachments = createAttachments(attachment("FacturasMensuales", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

			// Se prepara el correo
			String from = "factura@facturaemprendesoftcr.com";
//			if (usuarioSesion.getEmpresa().getAbreviaturaEmpresa() != null) {
//				if (!usuarioSesion.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
//					from = usuarioSesion.getEmpresa().getAbreviaturaEmpresa() + "_FacturasEmitidas" + "_No_Reply@facturaemprendesoftcr.com";
//				}
//			}
			String nombre = usuarioSesion.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? usuarioSesion.getEmpresa().getNombre() : usuarioSesion.getEmpresa().getNombreComercial();
			nombre = nombre.length() > 50 ? nombre.substring(0, 50) : nombre;
			String subject = nombre + " Facturas Rango de fechas: " + fechaInicio + " al " + fechaFin;

			ArrayList<String> listaCorreos = new ArrayList<>();
			if (correoAlternativo != null && correoAlternativo.length() > 0) {
				listaCorreos.add(correoAlternativo);
			} else {
				listaCorreos.add(usuarioSesion.getEmpresa().getCorreoElectronico());
			}

			Double totalVentasGravadas = Constantes.ZEROS_DOUBLE;
			Double totalVentasExentas = Constantes.ZEROS_DOUBLE;
			Double totalVentasNetas = Constantes.ZEROS_DOUBLE;
			Double totalImpuestos = Constantes.ZEROS_DOUBLE;
			Double totalDescuentos = Constantes.ZEROS_DOUBLE;
			Double totalOtrosCargos = Constantes.ZEROS_DOUBLE;
			Double total = Constantes.ZEROS_DOUBLE;
			Double totalVentasGravadas_n = Constantes.ZEROS_DOUBLE;
			Double totalVentasExentas_n = Constantes.ZEROS_DOUBLE;
			Double totalVentasNetas_n = Constantes.ZEROS_DOUBLE;
			Double totalImpuestos_n = Constantes.ZEROS_DOUBLE;
			Double totalDescuentos_n = Constantes.ZEROS_DOUBLE;
			Double totalOtrosCargos_n = Constantes.ZEROS_DOUBLE;
			Double total_n = Constantes.ZEROS_DOUBLE;
			if (objetos.size() > 0) {
				for (ListarFacturasNativa factura : objetos) {
					totalVentasGravadas = totalVentasGravadas + factura.getTotalGravado();
					totalVentasExentas = totalVentasExentas + factura.getTotalExento();
					totalVentasNetas = totalVentasNetas + factura.getTotalVentaNeta();
					totalImpuestos = totalImpuestos + factura.getTotalImpuesto();
					totalDescuentos = totalDescuentos + factura.getTotalDescuentos();
					totalOtrosCargos = totalOtrosCargos + factura.getTotalOtrosCargos();
					total = total + factura.getTotalComprobante();
					if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
						totalVentasGravadas_n = totalVentasGravadas_n + factura.getTotalGravado();
						totalVentasExentas_n = totalVentasExentas_n + factura.getTotalExento();
						totalVentasNetas_n = totalVentasNetas_n + factura.getTotalVentaNeta();
						totalImpuestos_n = totalImpuestos_n + factura.getTotalImpuesto();
						totalDescuentos_n = totalDescuentos_n + factura.getTotalDescuentos();
						totalOtrosCargos_n = totalOtrosCargos_n + factura.getTotalOtrosCargos();
						total_n = total_n + factura.getTotalComprobante();

					}
				}
			}

			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("nombreEmpresa", usuarioSesion.getEmpresa().getNombre());
			modelEmail.put("cedula", usuarioSesion.getEmpresa().getCedula());
			modelEmail.put("fechaInicial", fechaInicio);
			modelEmail.put("fechaFinal", fechaFin);
			modelEmail.put("totalVentasGravadas", Utils.formateadorMiles(totalVentasGravadas));
			modelEmail.put("totalVentasExentas", Utils.formateadorMiles(totalVentasExentas));
			modelEmail.put("totalVentasNetas", Utils.formateadorMiles(totalVentasNetas));
			modelEmail.put("totalImpuestos", Utils.formateadorMiles(totalImpuestos));
			modelEmail.put("totalDescuentos", Utils.formateadorMiles(totalDescuentos));
			modelEmail.put("totalOtrosCargos", Utils.formateadorMiles(totalOtrosCargos));
			modelEmail.put("total", Utils.formateadorMiles(total));
			modelEmail.put("totalVentasGravadas_n", Utils.formateadorMiles(totalVentasGravadas_n));
			modelEmail.put("totalVentasExentas_n", Utils.formateadorMiles(totalVentasExentas_n));
			modelEmail.put("totalVentasNetas_n", Utils.formateadorMiles(totalVentasNetas_n));
			modelEmail.put("totalImpuestos_n", Utils.formateadorMiles(totalImpuestos_n));
			modelEmail.put("totalDescuentos_n", Utils.formateadorMiles(totalDescuentos_n));
			modelEmail.put("totalOtrosCargos_n", Utils.formateadorMiles(totalOtrosCargos_n));
			modelEmail.put("total_n", Utils.formateadorMiles(total_n));

			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_LISTAR_FACTURAS, modelEmail);
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			log.error("** Error  Enviado correo: " + " fecha " + new Date() + " " + e.getMessage());
			System.out.println("No enviado correctamente el correo");
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
		}

		return respuestaServiceValidator;
	}

	/**
	 * Facturas sin notas de creditos de anulacion completa
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idCliente
	 * @param tipoDocumento
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listarFacturasActivasSinNotasCreditosCompletasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasActivasSinNotasCreditosCompletasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente, @RequestParam String estado, @RequestParam String codigo) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idCliente);
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		Date fechaFinalP = Utils.parseDate(fechaFin);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);

		Integer idUsuario = Constantes.ZEROS;
		if (usuarioBo.isAdministrador_cajero(usuarioSesion) || usuarioBo.isAdministrador_empresa(usuarioSesion) || usuarioBo.isAdministrador_restaurante(usuarioSesion)) {
			idUsuario = Constantes.ZEROS;
		} else {
			idUsuario = usuarioSesion.getId();
		}
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<FacturasSinNotaCreditoNative> objetos = null;
		if (estado.equals(Constantes.COMBO_TODOS)) {
			objetos = consultasNativeBo.findByFacturasAnulacion(usuarioSesion.getEmpresa(), idUsuario, "(" + Constantes.FACTURA_ESTADO_FACTURADO + "," + Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO + "," + Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA + ")", inicio1, fin1, cliente != null ? cliente.getId() : Constantes.ZEROS_LONG, codigo);
		} else {
			objetos = consultasNativeBo.findByFacturasAnulacion(usuarioSesion.getEmpresa(), idUsuario, "(" + estado + ")", inicio1, fin1, cliente != null ? cliente.getId() : Constantes.ZEROS_LONG, codigo);
		}
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (FacturasSinNotaCreditoNative facturasDelDia : objetos) {
				if (facturasDelDia.getId().longValue() > 0L) {
					solicitudList.add(new FacturaAnulacionCommand(facturasDelDia));
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

	/***
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @param estado
	 * @param selectActividadComercial
	 * @return
	 */

	/**
	 * Facturas sin notas de creditos de anulacion completa
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idCliente
	 * @param tipoDocumento
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listarConsutaIvaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarConsutaIvaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Integer estado, @RequestParam Integer selectActividadComercial) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		Date fechaFinalP = Utils.parseDate(fechaFin);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicioP);
		String fin1 = dateFormat1.format(fechaFinalP);

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<ConsultaIVANative> objetos = consultasNativeBo.findByEmpresaAndEstadoAndFechasAndActividadComercial(usuarioSesion.getEmpresa(), inicio1, fin1, estado, selectActividadComercial);
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (ConsultaIVANative consultaIVANative : objetos) {
				solicitudList.add(new ConsultaIvaCommand(consultaIVANative));
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

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarRecepcionFacturasActivasAndAnuladasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarRecepcionFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String cedulaEmisor, @RequestParam String estado, @RequestParam String actividadEconomica, @RequestParam Integer tipoGasto) {

		// Usuario de la session
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Consulta por fechas
		DataTableDelimitador delimitador = new DataTableDelimitador(request, "RecepcionFactura");
		delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "="));

		if (cedulaEmisor != null && cedulaEmisor.length() > 0) {
			delimitador.addFiltro(new JqGridFilter("emisorCedula", "'" + cedulaEmisor + "'", "="));
		}

		if (!fechaInicioParam.equals(Constantes.EMPTY) && !fechaFinParam.equals(Constantes.EMPTY)) {
			Date fechaInicio = Utils.parseDate(fechaInicioParam);
			Date fechaFinal = Utils.parseDate(fechaFinParam);
			if (fechaFinal == null) {
				fechaFinal = new Date(System.currentTimeMillis());
			}
			if (fechaFinal != null) {
				fechaFinal = Utils.sumarDiasFecha(fechaFinal, 1);
			}

			DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT7);
			delimitador.addFiltro(new JqGridFilter("facturaFechaEmision", dateFormat.format(fechaInicio), "date>="));
			delimitador.addFiltro(new JqGridFilter("facturaFechaEmision", dateFormat.format(fechaFinal), "dateFinal<="));
			if (!estado.equals(Constantes.COMBO_TODOS)) {
				delimitador.addFiltro(new JqGridFilter("estado", estado.toString(), "="));
			}
			if (!actividadEconomica.equals(Constantes.COMBO_TODOS)) {
				delimitador.addFiltro(new JqGridFilter("codigoActividad", actividadEconomica.toString(), "="));
			}
			if (tipoGasto > Constantes.ZEROS) {
				delimitador.addFiltro(new JqGridFilter("tipoGasto", tipoGasto.toString(), "="));
			}

		}
		return UtilsForControllers.process(request, dataTableBo, delimitador, TO_COMMAND_RECEPCION);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarFacturasDelDiaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasDiaAjax(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Integer idUsuario = Constantes.ZEROS;
		if (usuarioBo.isAdministrador_cajero(usuarioSesion) || usuarioBo.isAdministrador_empresa(usuarioSesion) || usuarioBo.isAdministrador_restaurante(usuarioSesion) || usuarioBo.isAdministrador_vendedor(usuarioSesion)) {
			idUsuario = Constantes.ZEROS;
		} else {
			idUsuario = usuarioSesion.getId();
		}

		Date fechahoy = new Date();
		DateFormat df = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String reportDate = df.format(fechahoy);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<FacturasDelDiaNative> objetos = consultasNativeBo.findByFacturasDelDia(usuarioSesion.getEmpresa(), idUsuario, "(" + Constantes.FACTURA_ESTADO_FACTURADO + "," + Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA + "," + Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO + ")", reportDate);
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (FacturasDelDiaNative facturasDelDia : objetos) {
				if (facturasDelDia.getId().longValue() > 0L) {
					solicitudList.add(new FacturaDiaCommand(facturasDelDia));
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
	 * Crear la Factura
	 * @param request
	 * @param model
	 * @param facturaCommand
	 * @param result
	 * @param status
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CrearFacturaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator crearFactura(HttpServletRequest request, ModelMap model, @ModelAttribute FacturaCommand facturaCommand, BindingResult result, SessionStatus status) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

			ArrayList<DetalleFacturaCommand> detallesFacturaCommand = facturaBo.formaDetallesCommand(facturaCommand);
			ArrayList<DetalleFacturaCommand> detallesNotaCredito = new ArrayList<DetalleFacturaCommand>();
			return this.crearFactura(facturaCommand, result, usuario, detallesFacturaCommand, detallesNotaCredito);
		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CrearNotaCreditoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator crearNotaCredito(HttpServletRequest request, ModelMap model, @ModelAttribute FacturaCommand facturaCommand, BindingResult result, SessionStatus status) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Factura facturaBD = facturaBo.findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa());
			if (facturaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.notaCredito.consecutivo.no.existe", result.getAllErrors());
			}
			if (facturaBD.getEstado().equals(Constantes.FACTURA_ESTADO_ANULADA) || facturaBD.getAnuladaCompleta().equals(Constantes.FACTURA_ANULACION_COMPLETA_SI)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.notaCredito.factura.limite.notas.credito", result.getAllErrors());
			}

			if (facturaCommand.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO)) {
				if (Utils.roundFactura(facturaBD.getTotalComprobante(), 2) != Utils.roundFactura(facturaCommand.getTotalComprobante(), 2)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("nota.anula.documento.monto.menor", result.getAllErrors());
				}
			}

			if (facturaCommand.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_CORRIJE_MONTO)) {
				if (Utils.roundFactura(facturaCommand.getTotalComprobante(), 2) > Utils.roundFactura(facturaBD.getTotalComprobante(), 2)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("nota.documento.monto.mayor", result.getAllErrors());
				}
			}

			if (facturaCommand.getReferenciaCodigo() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("nota.codigo.anulacion.vacio", result.getAllErrors());

			}

			FacturaCommand facturaAplicarCommand = new FacturaCommand();
			facturaAplicarCommand.setMesa(facturaBD.getMesa());
			facturaAplicarCommand.setCliente(facturaBD.getCliente());
			facturaAplicarCommand.setEmpresa(facturaBD.getEmpresa());
			facturaAplicarCommand.setId(null);
			facturaAplicarCommand.setFechaCredito(null);
			facturaAplicarCommand.setNumeroConsecutivo(Constantes.ZEROS);
			facturaAplicarCommand.setFechaEmision(null);
			facturaAplicarCommand.setCondicionVenta(facturaBD.getCondicionVenta());
			facturaAplicarCommand.setPlazoCredito(facturaBD.getPlazoCredito());

			facturaAplicarCommand.setMedioPago(Constantes.EMPTY);
			facturaAplicarCommand.setNombreFactura(facturaBD.getNombreFactura());
			facturaAplicarCommand.setDireccion(Constantes.EMPTY);
			facturaAplicarCommand.setCorreoAlternativo(facturaBD.getCorreoAlternativo());
			facturaAplicarCommand.setNota(Constantes.EMPTY);
			facturaAplicarCommand.setComanda(Constantes.EMPTY);
			facturaAplicarCommand.setSubTotal(facturaCommand.getSubTotal());
			facturaAplicarCommand.setTotalTransporte(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotal(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalServGravados(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalServExentos(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalMercanciasGravadas(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalMercanciasExentas(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalGravado(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalExento(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalVenta(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalDescuentos(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalVentaNeta(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalImpuesto(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalComprobante(Constantes.ZEROS_DOUBLE);
			if (facturaCommand.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO)) {
				facturaAplicarCommand.setTotalEfectivo(facturaBD.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : facturaBD.getTotalEfectivo());
				facturaAplicarCommand.setTotalTarjeta(facturaBD.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : facturaBD.getTotalTarjeta());
				facturaAplicarCommand.setTotalBanco(facturaBD.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : facturaBD.getTotalBanco());

			} else {
				facturaAplicarCommand.setTotalEfectivo(facturaCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalEfectivo());
				facturaAplicarCommand.setTotalTarjeta(facturaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTarjeta());
				facturaAplicarCommand.setTotalBanco(facturaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalBanco());

			}
			facturaAplicarCommand.setTotalCredito(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setMontoCambio(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalCambio(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalCambioPagar(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalImpuesto(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTotalImpuestoServ(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setTipoCambioMoneda(facturaBD.getCambioMoneda());
			facturaAplicarCommand.setPesoTransporteTotal(Constantes.ZEROS_DOUBLE);
			facturaAplicarCommand.setCodigoMoneda(facturaBD.getCodigoMoneda());
			facturaAplicarCommand.setCreated_at(new Date());
			facturaAplicarCommand.setUpdated_at(new Date());
			facturaAplicarCommand.setVersionEsquemaXML(facturaBD.getVersionEsquemaXML());
			facturaAplicarCommand.setDetalleFactura(Constantes.EMPTY);

			facturaAplicarCommand.setEstado(Constantes.FACTURA_ESTADO_FACTURADO);
			if (!facturaBD.getEstado().equals(Constantes.FACTURA_ESTADO_ACEPTADA)) {
				if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
					facturaAplicarCommand.setTipoDoc(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO);
				}
				if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
					facturaAplicarCommand.setTipoDoc(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_INTERNO);
				}

			}
			if (facturaBD.getEstado().equals(Constantes.FACTURA_ESTADO_ACEPTADA)) {
				facturaAplicarCommand.setTipoDoc(facturaCommand.getTipoDoc());

			}
			facturaAplicarCommand.setNota(facturaCommand.getNota());
			facturaAplicarCommand.setNombreFactura(facturaBD.getNombreFactura());
			// Datos de la Nota de Credito
			facturaAplicarCommand.setReferenciaCodigo(facturaCommand.getReferenciaCodigo());
			DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT7);
			facturaAplicarCommand.setReferenciaFechaEmision(dateFormat.format(facturaBD.getFechaEmision()));
			facturaAplicarCommand.setReferenciaNumero(facturaBD.getNumeroConsecutivo());
			facturaAplicarCommand.setTotalComprobante(facturaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalComprobante());
			facturaAplicarCommand.setReferenciaRazon(facturaCommand.getNota());
			facturaAplicarCommand.setPlazoCredito(Constantes.ZEROS);
			facturaAplicarCommand.setReferenciaTipoDoc(facturaBD.getTipoDoc());

			facturaAplicarCommand.setMesa(facturaBD.getMesa());
			facturaAplicarCommand.setMontoCambio(facturaBD.getMontoCambio());
			facturaAplicarCommand.setPesoTransporteTotal(facturaBD.getPesoTransporteTotal());
			facturaAplicarCommand.setTipoCambioMoneda(facturaBD.getTipoCambio());
			facturaAplicarCommand.setUsuario(usuario.getNombreUsuario());
			facturaAplicarCommand.setCodigoActividad(facturaBD.getCodigoActividad());
			facturaAplicarCommand.setRebajaInventario(facturaCommand.getRebajaInventario() == null ? Constantes.NO_APLICA_REBAJO_INVENTARIO_POR_NOTA : facturaCommand.getRebajaInventario());
			ArrayList<DetalleFacturaCommand> detallesFacturaCommand = facturaBo.formaDetallesCommand(facturaCommand);
			ArrayList<DetalleFacturaCommand> detallesNotaCredito = new ArrayList<DetalleFacturaCommand>();
			return this.crearFactura(facturaAplicarCommand, result, usuario, detallesFacturaCommand, detallesNotaCredito);
		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}
	}


	@SuppressWarnings("rawtypes")
	private RespuestaServiceValidator<?> crearFactura(FacturaCommand facturaCommand, BindingResult result, Usuario usuario, ArrayList<DetalleFacturaCommand> detallesFacturaCommand, ArrayList<DetalleFacturaCommand> detallesNotaCredito) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			facturaCommand.setTotalBanco(facturaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalBanco());
			facturaCommand.setTotalEfectivo(facturaCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalEfectivo());
			facturaCommand.setTotalTarjeta(facturaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTarjeta());
			facturaCommand.setTotalTransporte(facturaCommand.getTotalTransporte() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTransporte());
			facturaCommand.setTotalDescuentos(facturaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalDescuentos());
			facturaCommand.setTotalExento(facturaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalExento());
			facturaCommand.setTotalGravado(facturaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalGravado());
			facturaCommand.setTotalCambioPagar(facturaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambioPagar());
			facturaCommand.setTotalMercanciasExentas(facturaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasExentas());
			facturaCommand.setTotalMercanciasGravadas(facturaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasGravadas());
			facturaCommand.setTotalCredito(facturaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCredito());
			facturaCommand.setTotalServExentos(facturaCommand.getTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServExentos());
			facturaCommand.setTotalServGravados(facturaCommand.getTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServGravados());
			facturaCommand.setTotalVenta(facturaCommand.getTotalVenta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalVenta());
			facturaCommand.setTotalVentaNeta(facturaCommand.getTotalVentaNeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalVentaNeta());
			facturaCommand.setTipoDoc(facturaCommand.getTipoDoc() != null ? facturaCommand.getTipoDoc() : Constantes.EMPTY);
			facturaCommand.setCondicionVenta(facturaCommand.getCondicionVenta() == null ? Constantes.EMPTY : facturaCommand.getCondicionVenta());
			UsuarioCaja usuarioCajaBd = null;
			// Si esta en estado facturada en base de datos se retornae un mensaje que ya fue procesada
			if (facturaCommand != null) {
				if (facturaCommand.getId() != null) {
					if (facturaCommand.getId() > Constantes.ZEROS_LONG) {
						FacturaIDNativa facturaRevision = consultasNativeBo.findIdFactura(facturaCommand.getId());
						if (facturaRevision != null) {
							if (facturaRevision.getEstado() != null) {
								if (facturaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || facturaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_ACEPTADA) || facturaRevision.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO) || facturaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_ANULADA)) {
									return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.ya.esta.procesada", result.getAllErrors());
								}
							}

						}

					}
				}
			}
			if (facturaCommand.getNota() != null) {
				if (facturaCommand.getNota().length() > 250) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.nota.lenght", result.getAllErrors());
				}
			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				if (facturaCommand.getCorreoAlternativo() != null) {
					if (!facturaCommand.getCorreoAlternativo().equals(Constantes.EMPTY)) {
						if (!Utils.validarCorreo(facturaCommand.getCorreoAlternativo())) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.correo.alternativo.invalido", result.getAllErrors());
						}
					}
				}

			}

			if (facturaCommand.getCondicionVenta().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.condicion.venta.vacia", result.getAllErrors());
			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				if (facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
					if (facturaCommand.getCliente() == null) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.condicion.venta.credito.cliente.vacio", result.getAllErrors());
					} else {
						if (facturaCommand.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.condicion.venta.credito.cliente.frecuente", result.getAllErrors());
						}
					}
					if (facturaCommand.getCliente() != null) {
						Double limite = facturaCommand.getCliente().getLimiteCredito() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getCliente().getLimiteCredito();
						if (limite > Constantes.ZEROS_DOUBLE) {
							// validar si tiene disponible
							Double saldo = cuentaCobrarBo.getDisponible(usuario.getEmpresa().getId(), facturaCommand.getCliente());
							saldo = saldo == null ? Constantes.ZEROS_DOUBLE : saldo;
							Double comprobante = facturaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalComprobante();
							if (comprobante > saldo) {
								return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.condicion.venta.credito.cliente.sinlimiteCredito", result.getAllErrors());
							}

						}
					}
				}
			}
			if (facturaCommand.getTipoDoc() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}
			if (facturaCommand.getTipoDoc() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}

			if (facturaCommand.getTipoDoc().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}

			if (facturaCommand.getCodigoActividad() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.actividad.comercial.no.existe", result.getAllErrors());
			} else if (facturaCommand.getCodigoActividad().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.actividad.comercial.no.existe", result.getAllErrors());
			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				usuarioCajaBd = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);

				if (usuarioCajaBd == null) {
					if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {

						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
					}
				}

			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) && !facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
					if (facturaCommand.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && facturaCommand.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && facturaCommand.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.ingreso.dinero", result.getAllErrors());
					}

				}
			}
			TipoCambio tipoCambio = null;
			facturaCommand.setTipoCambioMoneda(facturaCommand.getTipoCambioMoneda() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTipoCambioMoneda());
			facturaCommand.setCodigoMoneda(facturaCommand.getCodigoMoneda() == null ? Constantes.CODIGO_MONEDA_COSTA_RICA : facturaCommand.getCodigoMoneda());
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				if (!facturaCommand.getCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA)) {
					if (facturaCommand.getTipoCambioMoneda() == Constantes.ZEROS_DOUBLE) {
						tipoCambio = tipoCambioBo.findByEstadoAndEmpresa(Constantes.ESTADO_ACTIVO, usuario.getEmpresa());
						if (tipoCambio == null) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.tipo.cambio.dolar.activo", result.getAllErrors());

						}
						facturaCommand.setTipoCambioMoneda(tipoCambio.getTotal());

					}

				}
			}
			if (!usuario.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA)) {
				if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
					if (facturaCommand.getCliente() == null) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.incluir.cliente", result.getAllErrors());
					}
				}

			}
			if (facturaCommand.getCliente() == null) {
				Cliente cliente = clienteBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_CLIENTE_FRECUENTE, usuario.getEmpresa());
				cliente = cliente == null ? clienteBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_CLIENTE_CREDITO, usuario.getEmpresa()) : cliente;

				if (cliente == null) {
					cliente = clienteBo.crearClienteFrecuente(usuario.getEmpresa(), usuario);
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.intente.nuevo", result.getAllErrors());
				} else {
					facturaCommand.setCliente(cliente);
				}

			}
			if (facturaCommand.getCliente() != null) {
				if (facturaCommand.getCliente().getIdentificacionExtranjero() != null) {
					if (!facturaCommand.getCliente().getIdentificacionExtranjero().equals(Constantes.EMPTY)) {
						if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA))
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.cedula.extrajera.only.tiquete", result.getAllErrors());
					}
				}

			}

			if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !usuario.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA)) {
				if (facturaCommand.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_FRECUENTE) || facturaCommand.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_CREDITO)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.tipo.documento.factura", result.getAllErrors());
				}
				if (facturaCommand.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) || facturaCommand.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.tipo.documento.factura", result.getAllErrors());
				}
			}

			if (facturaCommand.getCliente().getTipoDocumentoExoneracion() != null) {
				if (!facturaCommand.getCliente().getTipoDocumentoExoneracion().equals(Constantes.EMPTY)) {
					if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && facturaCommand.getCliente().getTipoDocumentoExoneracion().equals(Constantes.TIPO_EXONERACION_DIPLOTAMATICO)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.exoneracion.diplomatico", result.getAllErrors());
					}
					if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE) && !facturaCommand.getCliente().getTipoDocumentoExoneracion().equals(Constantes.TIPO_EXONERACION_DIPLOTAMATICO)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.exoneracion.libre.impuesto", result.getAllErrors());
					}
				}
			}

			if (facturaCommand.getVendedor() == null) {
				Vendedor vendedor = vendedorBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_VENDEDOR_FRECUENTE, usuario.getEmpresa());
				if (vendedor == null) {
					vendedorBo.crearVendedorFrecuente(usuario.getEmpresa(), usuario);
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.intente.nuevo", result.getAllErrors());
				}
				facturaCommand.setVendedor(vendedor);

			}
			Factura facturaReferenciaValidar = null;
			// Validar el codigo de factura que se le va aplicar una nota de credito
			if (facturaCommand.getReferenciaNumero() != null) {
				if (!facturaCommand.getReferenciaNumero().equals(Constantes.EMPTY)) {
					facturaReferenciaValidar = facturaBo.findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa());
					if (facturaReferenciaValidar != null) {
						if (facturaReferenciaValidar.getEmpresa().getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
							Hacienda hacienda = haciendaBo.findByEmpresaAndClave(facturaReferenciaValidar.getEmpresa(), facturaReferenciaValidar.getClave());
							if (hacienda != null) {
								if (!hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA) && !hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
									return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.pendiente.comprobacion.hacienda", result.getAllErrors());
								}
							} else {
								return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.pendiente.comprobacion.hacienda", result.getAllErrors());
							}

						}

					}
					if (facturaReferenciaValidar == null) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.aplicar.nota.credito.o.debito.no.existe", result.getAllErrors());
					} else {
						facturaCommand.setReferenciaTipoDoc(facturaReferenciaValidar.getTipoDoc());

					}
				}
			}

			facturaCommand.setEmpresa(usuario.getEmpresa());
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (!facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				facturaCommand.setFechaCredito(null);
			}
			Factura facturaBD = facturaCommand.getId() == null || facturaCommand.getId() == Constantes.ZEROS_LONG ? null : facturaBo.findById(facturaCommand.getId());
			// Eliminar detalles si existe
			if (facturaBD != null) {
				facturaBo.eliminarDetalleFacturaPorSP(facturaBD);
				Collection<Detalle> detalles = detalleBo.findByFactura(facturaBD);
				for (Detalle detalle : detalles) {
					detalleBo.eliminar(detalle);
				}

			}

			Factura factura = facturaBo.crearFactura(facturaCommand, usuario, usuarioCajaBd, tipoCambio, detallesFacturaCommand, detallesNotaCredito);
			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (usuarioCajaBd != null) {
				usuarioCajaBo.actualizarCaja(usuarioCajaBd);
			}
			List<Object> solicitudList = new ArrayList<Object>();
			if (factura.getEmpresa().getImprimirSiempre() == 1) {
				Collection<Detalle> objetos = detalleBo.findbyIdFactura(factura.getId());
				for (Detalle detalle : objetos) {
					solicitudList.add(new DetalleFacturaCommand(detalle));
				}

			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente", solicitudList.size() > 0 ? solicitudList : factura);

		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}

	}

	/**
	 * Mostrar una Factura
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarFacturaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idFactura) {
		try {
			Factura facturaBD = facturaBo.findById(idFactura);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", facturaBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarFacturaPorConsecutivoAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator MostrarFacturaPorConsecutivo(HttpServletRequest request, HttpServletResponse response, @RequestParam String consecutivo) {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Factura facturaBD = facturaBo.findByConsecutivoAndEmpresa(consecutivo, usuario.getEmpresa());

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", facturaBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Cambiar el nombre del tiquete
	 * @param request
	 * @param response
	 * @param idFactura
	 * @param nombreFactura
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ModificarNombreTiquteAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator ModificarNombreTiquete(HttpServletRequest request, ModelMap model, @ModelAttribute Factura factura, HttpServletResponse response, @RequestParam Long idFactura, @RequestParam String nombreFactura, BindingResult result, SessionStatus status) {
		try {

			Factura facturaBD = facturaBo.findById(idFactura);
			facturaBD.setNombreFactura(nombreFactura);
			facturaBo.modificar(facturaBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.tiquete.modificado.correctamente", facturaBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param consecutivo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ConsultarConsecutivoAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator consultarConsecutivo(HttpServletRequest request, ModelMap model, @ModelAttribute Factura factura, HttpServletResponse response, @RequestParam String consecutivo, BindingResult result, SessionStatus status) {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Factura facturaBD = facturaBo.findByConsecutivoAndEmpresa(consecutivo, usuario.getEmpresa());
			if (facturaBD == null) {
				result.rejectValue("referenciaNumero", "mensajes.no.existe.consecutivo");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", facturaBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param model
	 * @param idFactura
	 * @param estado
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CambiarEstadoProformaAPedienteAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cambiarEstadoProforma(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura, @RequestParam Integer estado) throws Exception {
		try {

			Factura facturaBD = facturaBo.findById(idFactura);
			if (facturaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.no.existe");
			}

			if (estado.equals(Constantes.FACTURA_ESTADO_PENDIENTE)) {
				if (facturaBD.getCliente() != null) {
					if (!facturaBD.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !facturaBD.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
						facturaBD.setTipoDoc(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA);
					} else {
						facturaBD.setTipoDoc(Constantes.FACTURA_TIPO_DOC_TIQUETE);
					}
				} else {
					facturaBD.setTipoDoc(Constantes.FACTURA_TIPO_DOC_TIQUETE);
				}

				facturaBD.setTotalCambioPagar(Constantes.ZEROS_DOUBLE);
				facturaBD.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
				facturaBD.setTotalBanco(Constantes.ZEROS_DOUBLE);
			}
			facturaBD.setEstado(estado);
			facturaBD.setUpdated_at(new Date());
			facturaBo.modificar(facturaBD);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.modificado.correctamente", facturaBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	/**
	 * Cambia la factura de mesa
	 * @param request
	 * @param model
	 * @param idFactura
	 * @param idMesa
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CambiarFacturaMesa.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator CambiarFacturaMesa(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura, @RequestParam Long idMesa) throws Exception {
		try {
			Factura facturaBD = facturaBo.findById(idFactura);
			if (facturaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.no.existe");
			}
			facturaBD.setMesa(mesaBo.buscar(idMesa));
			facturaBD.setUpdated_at(new Date());
			facturaBo.modificar(facturaBD);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.modificado.correctamente", facturaBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	/**
	 * Correo Alternativo
	 * @param request
	 * @param response
	 * @param model
	 * @param idFactura
	 * @param correo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnviarCorreoAlternativoProformaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoAlternativo(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute String datos, BindingResult result, @RequestParam Long idFactura, @RequestParam String correo) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");
			Factura facturaBD = facturaBo.findById(idFactura);
			if (facturaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.no.existe");
			}
			ArrayList<String> listaCorreos = new ArrayList<String>();
			if (!correo.equals(Constantes.EMPTY)) {
				listaCorreos.add(correo);
			}
			listaCorreos.add(facturaBD.getCliente().getCorreoElectronico());

			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(facturaBD);
			Collection<Detalle> detalles = detalleBo.findByFactura(facturaBD);

			List<DetalleFacturaElectronica> detallesFactura = detalles.stream().map(TO_DETALLE).collect(toList());
			facturaElectronica.setDetalleFacturaElectronica(detallesFactura);

			if (facturaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {

			}
			ByteArrayOutputStream namePDF = ReportePdfView.main(facturaBD.getNumeroConsecutivo(), facturaBD.getTipoDoc(), facturaElectronica);

			Collection<Attachment> attachments = createAttachments(PDF_Attach(facturaBD.getId().toString(), facturaBD.getEmpresa().getCedula(), asPDF(namePDF), facturaBD.getTipoDoc()));

			Map<String, Object> modelEmail = new HashMap<>();

			modelEmail.put("clave", facturaBD.getId());
			modelEmail.put("nombreEmpresa", facturaBD.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? facturaBD.getEmpresa().getNombre() : facturaBD.getEmpresa().getNombreComercial());
			modelEmail.put("correo", facturaBD.getEmpresa().getCorreoElectronico());
			modelEmail.put("telefono", facturaBD.getEmpresa().getTelefono());

			String nombre = facturaBD.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? facturaBD.getEmpresa().getNombre() : facturaBD.getEmpresa().getNombreComercial();
			String from = "proforma@facturaemprendesoftcr.com";
//			if (facturaBD.getEmpresa().getAbreviaturaEmpresa() != null) {
//				if (!facturaBD.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
//					from = facturaBD.getEmpresa().getAbreviaturaEmpresa() + "_Proforma" + "_No_Reply@facturaemprendesoftcr.com";
//				}
//			}

			String subject = "Proforma N° " + facturaBD.getId().toString() + " del Emisor: " + nombre;

			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailProforma.vm", modelEmail);
			//
			if (facturaBD.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA_REINTEGRO_GASTOS)) {
				correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emaiNoElectronico.vm", modelEmail);
			} else {
				correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailProforma.vm", modelEmail);
			}
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));

		} catch (Exception e) {
			log.error("** Error  Enviado correo: " + " fecha " + new Date());
			System.out.println("No enviado correctamente el correo");
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
		}
		return respuestaServiceValidator;
	}

	/**
	 * Enviar el PDF por correo
	 * @param request
	 * @param response
	 * @param model
	 * @param idFactura
	 * @param correo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnviarCorreoAlternativoFacturaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoAlternativoFactura(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute String datos, BindingResult result, @RequestParam(value = "idFactura", required = false) Long idFactura, @RequestParam String correo, @RequestParam(value = "consecutivo", required = false) String consecutivo) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");
			consecutivo = consecutivo == null ? Constantes.EMPTY : consecutivo;
			idFactura = idFactura == null ? Constantes.ZEROS_LONG : idFactura;

			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Factura factura = consecutivo.length() > 0 ? facturaBo.findByConsecutivoAndEmpresa(consecutivo, usuario.getEmpresa()) : null;
			factura = factura == null ? facturaBo.findById(idFactura) : factura;
			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.no.existe");
			}
			Hacienda haciendaBD = haciendaBo.findByClave(factura.getClave());
			if (!factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA_REINTEGRO_GASTOS)) {

				if (haciendaBD == null) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
				}

			}

			ArrayList<String> listaCorreos = new ArrayList<String>();
			if (correo != null) {
				if (!correo.equals(Constantes.EMPTY)) {
					listaCorreos.add(correo);
				}
			}
			if (listaCorreos != null) {
				if (listaCorreos.size() == 0) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.vacio.enviar");
				}
			}
			if (factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA_REINTEGRO_GASTOS)) {
				procesoHaciendaService.enviarCorreosNoElectronicos(factura, listaCorreos);
			} else {
				if (haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ANULADA) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_PROBLEMA_ENVIO_CORREO)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.xml.con.error");
				}
				if (!haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.no.aceptado");
				}
				procesoHaciendaService.enviarCorreos(factura, haciendaBD, listaCorreos);

			}
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());

		} catch (Exception e) {
			log.error("** Error  Enviado correo: " + " fecha " + new Date());
			System.out.println("No enviado correctamente el correo");
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
		}
		return respuestaServiceValidator;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnviarCorreoClienteAsociadosFacturaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoClienteAsociadosFacturaAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute String datos, BindingResult result, @RequestParam(value = "idFactura", required = false) Long idFactura, @RequestParam(value = "correo", required = false) String correo, @RequestParam(value = "consecutivo", required = false) String consecutivo) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");
			consecutivo = consecutivo == null ? Constantes.EMPTY : consecutivo;
			idFactura = idFactura == null ? Constantes.ZEROS_LONG : idFactura;
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Factura factura = consecutivo.length() > 0 ? facturaBo.findByConsecutivoAndEmpresa(consecutivo, usuario.getEmpresa()) : null;
			factura = factura == null ? facturaBo.findById(idFactura) : factura;
			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.no.existe");
			}
			Hacienda haciendaBD = haciendaBo.findByClave(factura.getClave());
			if (!factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA_REINTEGRO_GASTOS)) {
				if (haciendaBD == null) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
				}

			}
			ArrayList<String> listaCorreos = new ArrayList<String>();
			if (correo != null) {
				if (!correo.equals(Constantes.EMPTY)) {
					listaCorreos.add(correo);
				}
			}
			// Correo Oficial
			if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
				if (factura.getCliente().getCorreoElectronico() != null) {
					if (!factura.getCliente().getCorreoElectronico().equals(Constantes.EMPTY)) {
						listaCorreos.add(factura.getCliente().getCorreoElectronico());
					}
				}
			}
			// Correo Alternativo 1
			if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
				if (factura.getCliente().getCorreoElectronico1() != null) {
					if (!factura.getCliente().getCorreoElectronico1().equals(Constantes.EMPTY)) {
						listaCorreos.add(factura.getCliente().getCorreoElectronico1());
					}
				}
			}
			// Correo Alternativo 2
			if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
				if (factura.getCliente().getCorreoElectronico2() != null) {
					if (!factura.getCliente().getCorreoElectronico2().equals(Constantes.EMPTY)) {
						listaCorreos.add(factura.getCliente().getCorreoElectronico2());
					}
				}
			}
			// Correo Alternativo 3
			if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
				if (factura.getCliente().getCorreoElectronico3() != null) {
					if (!factura.getCliente().getCorreoElectronico3().equals(Constantes.EMPTY)) {
						listaCorreos.add(factura.getCliente().getCorreoElectronico3());
					}
				}
			}
			// Correo indicado en la factura
			if (factura.getCorreoAlternativo() != null) {
				if (!factura.getCorreoAlternativo().equals(Constantes.EMPTY)) {
					listaCorreos.add(factura.getCorreoAlternativo());
				}
			}

			if (listaCorreos != null) {
				if (listaCorreos.size() == 0) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.factura.no.tiene.correo.asociado");
				}

			}
			if (!factura.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA_REINTEGRO_GASTOS)) {
				if (haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ANULADA) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR) || haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_PROBLEMA_ENVIO_CORREO)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.xml.con.error");

				}
				if (!haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.correo.no.aceptado");

				}

				procesoHaciendaService.enviarCorreos(factura, haciendaBD, listaCorreos);

			} else {
				procesoHaciendaService.enviarCorreosNoElectronicos(factura, listaCorreos);
			}
			//
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());

		} catch (Exception e) {
			log.error("** Error  Enviado correo: " + " fecha " + new Date() + "" + e.getMessage());
			System.out.println("No enviado correctamente el correo");
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
		}
		return respuestaServiceValidator;
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

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	private ByteArrayDataSource asPDF(ByteArrayOutputStream stream) {
		return new ByteArrayDataSource(stream.toByteArray(), "text/pdf");
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
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

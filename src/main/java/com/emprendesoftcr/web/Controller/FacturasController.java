package com.emprendesoftcr.web.Controller;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.jxls.template.SimpleExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Bo.TipoCambioBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Bo.VendedorBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.components.OpenIDConnectHaciendaComponent;
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
import com.emprendesoftcr.pdf.App;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.pdf.Proformas;
import com.emprendesoftcr.validator.FacturaFormValidator;
import com.emprendesoftcr.web.command.FacturaCommand;
import com.emprendesoftcr.web.command.FacturaEsperaCommand;
import com.emprendesoftcr.web.command.ParametrosPaginacionMesa;
import com.emprendesoftcr.web.command.RecepcionFacturaCommand;
import com.emprendesoftcr.web.command.TotalFacturaCommand;
import com.emprendesoftcr.web.command.TurismoCommand;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.FechaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MesaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.VendedorPropertyEditor;
import com.google.common.base.Function;
import com.itextpdf.text.DocumentException;

import net.sf.jasperreports.engine.JRException;

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
																																																			facturaElectronica.setEmisorNombreComercial( d.getEmpresa().getNombreComercial() );
																																																			facturaElectronica.setEmisorNombre(!d.getEmpresa().getNombre().equals(Constantes.EMPTY) ? d.getEmpresa().getNombre() : d.getEmpresa().getNombre());
																																																			facturaElectronica.setEmisorCedula(d.getEmpresa().getCedula());
																																																			facturaElectronica.setEmisorTelefono(d.getEmpresa().getCodigoPais() + "-" + d.getEmpresa().getTelefono().toString());
																																																			facturaElectronica.setEmisorCorreo(d.getEmpresa().getCorreoElectronico());
																																																			facturaElectronica.set_nota(d.getNota() == null ? Constantes.EMPTY : d.getNota());
																																																			// Cliente
																																																			// if (!d.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE))
																																																			// {

																																																			facturaElectronica.setClienteNombre(d.getCliente().getNombreCompleto());
																																																			facturaElectronica.setClienteNombreComercial(d.getCliente().getNombreComercial());
																																																			facturaElectronica.setClienteCorreo(d.getCliente().getCorreoElectronico());
																																																			facturaElectronica.setClienteCedula(d.getCliente().getCedula());
																																																			facturaElectronica.setClienteTelefono(d.getCliente().getTelefono() != null ? d.getCliente().getTelefono().toString() : Constantes.EMPTY);
																																																			facturaElectronica.setFooterTotalDescuento(d.getTotalDescuentos());
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
																																																			List<DetalleFacturaElectronica> detalles = d.getDetalles().stream().map(TO_DETALLE).collect(toList());
																																																			facturaElectronica.setDetalleFacturaElectronica(detalles);
																																																			return facturaElectronica;
																																																		};

	@Autowired
	private DetalleBo																									detalleBo;

	@Autowired
	private HaciendaBo																									haciendaBo;

	
	@Autowired
	private CorreosBo																									correosBo;

	@Autowired
	private DataTableBo																								dataTableBo;

	@Autowired
	private CertificadoBo																							certificadoBo;

	@Autowired
	private UsuarioBo																									usuarioBo;

	@Autowired
	private TipoCambioBo																							tipoCambioBo;

	@Autowired
	private UsuarioCajaBo																							usuarioCajaBo;

	@Autowired
	private VendedorBo																								vendedorBo;

	@Autowired
	private EmpresaBo																									empresaBo;

	@Autowired
	private RecepcionFacturaBo																				recepcionFacturaBo;

	@Autowired
	private ClienteBo																									clienteBo;

	@Autowired
	private FacturaBo																									facturaBo;

	@Autowired
	private EmpresaPropertyEditor																			empresaPropertyEditor;

	@Autowired
	private ClientePropertyEditor																			clientePropertyEditor;

	@Autowired
	private VendedorPropertyEditor																		vendedorPropertyEditor;

	@Autowired
	private FacturaFormValidator																			facturaFormValidator;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@Autowired
	private FechaPropertyEditor																				fechaPropertyEditor;

	@Autowired
	private MesaPropertyEditor																				mesaPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
		binder.registerCustomEditor(Date.class, fechaPropertyEditor);
		binder.registerCustomEditor(Mesa.class, mesaPropertyEditor);
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

	@RequestMapping(value = "/recepcionFactura", method = RequestMethod.GET)
	public String recepcionFactura(ModelMap model) {
		return "views/facturas/recepcionFactura";
	}

	@RequestMapping(value = "/ventaDolares", method = RequestMethod.GET)
	public String crearVentaDolares(ModelMap model) {
		return "views/facturas/ventaDolares";
	}
	
	/**
	 * Ventas por Mini super
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/puntoVenta", method = RequestMethod.GET)
	public String crearCompras(ModelMap model) {

		return "views/facturas/puntoVenta";
	}

	/**
	 * Ventas por servicios por profeccionates
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ventasPorServicio", method = RequestMethod.GET)
	public String ventasPorServicios(ModelMap model) {
		return "views/facturas/ventasPorServicios";
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

	/**
	 * Listado de facturas anuladas y facturadas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListaRecepcionFacturas", method = RequestMethod.GET)
	public String listaRecepcionFacturas(ModelMap model) {
		return "views/facturas/listaRecepcionFacturas";
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
	



	/**
	 * Busca el total de facturas por rango de fechas
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/TotalFacturasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public TotalFacturaCommand totalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam) {
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFinal = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		return facturaBo.sumarFacturas(fechaInicio, fechaFinal, usuario.getEmpresa().getId());
	}

	@RequestMapping(value = "/EnvioDetalleTotalFacturasAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public void envioDetalleTotalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String correoAlternativo) {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		// Se obtiene los totales
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFinal = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		TotalFacturaCommand facturaCommand = facturaBo.sumarFacturas(fechaInicio, fechaFinal, usuario.getEmpresa().getId());

		// Se buscan las facturas
		Collection<Factura> facturas = facturaBo.facturasRangoEstado(Constantes.FACTURA_ESTADO_FACTURADO, fechaInicio, fechaFinal, usuario.getEmpresa().getId());

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelFacturas(facturas);
		Collection<Attachment> attachments = createAttachments(attachment("FacturasMensuales", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

		// Se prepara el correo
		String from = "FacturasEmitidas@emprendesoftcr.com";
		if (usuario.getEmpresa().getAbreviaturaEmpresa() != null) {
			if (!usuario.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
				from = usuario.getEmpresa().getAbreviaturaEmpresa() + "_FacturasEmitidas" + "_No_Reply@emprendesoftcr.com";
			}
		}
		String subject = "Facturas dentro del rango de fechas: " + fechaInicioParam + " al " + fechaFinParam;

		ArrayList<String> listaCorreos = new ArrayList<>();
		if (correoAlternativo != null && correoAlternativo.length() > 0) {
			listaCorreos.add(correoAlternativo);
		} else {
			listaCorreos.add(usuario.getEmpresa().getCorreoElectronico());
		}

		Map<String, Object> modelEmail = new HashMap<>();
		modelEmail.put("nombreEmpresa", usuario.getEmpresa().getNombre());
		modelEmail.put("fechaInicial", Utils.getFechaStr(fechaInicio));
		modelEmail.put("fechaFinal", Utils.getFechaStr(fechaFinal));
		modelEmail.put("total", facturaCommand.getTotal());
		modelEmail.put("totalDescuentos", facturaCommand.getTotalDescuentos() == null ? Constantes.ZEROS : facturaCommand.getTotalDescuentos());
		modelEmail.put("totalImpuestos", facturaCommand.getTotalImpuestos() == null ? Constantes.ZEROS : facturaCommand.getTotalImpuestos());
		modelEmail.put("totalVentasNetas", facturaCommand.getTotalVentasNetas() == null ? Constantes.ZEROS : facturaCommand.getTotalVentasNetas());
		modelEmail.put("totalVentasExentas", facturaCommand.getTotalVentasExentas() == null ? Constantes.ZEROS : facturaCommand.getTotalVentasExentas());
		modelEmail.put("totalVentasGravadas", facturaCommand.getTotalVentasGravadas() == null ? Constantes.ZEROS : facturaCommand.getTotalVentasGravadas());

		correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailResumenFactura.vm", modelEmail);
	}

	// Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarDetalleTotalFacturasAjax.do", method = RequestMethod.GET)
	public void descargarDetalleTotalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<Factura> facturas = facturaBo.facturasRangoEstado(Constantes.FACTURA_ESTADO_FACTURADO, fechaInicio, fechaFin, usuario.getEmpresa().getId());

		String nombreArchivo = "FacturasMensuales.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelFacturas(facturas);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelFacturas(Collection<Factura> facturas) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Id", "Fecha Emision", "# Documento", "Cliente", "Gravados", "Exentos", "Venta neta", "Impuesto", "Descuento", "Total", "Tipo Moneda", "Tipo Cambio", "Total Colones");
		new SimpleExporter().gridExport(headers, facturas, "id, fechaEmisionSTR, numeroConsecutivo, nombreCliente, totalGravado, totalExento, totalVentaNeta, totalImpuesto, totalDescuentos, totalComprobante,codigoMoneda, tipoCambio, totalColones", baos);
		return baos;
	}

	/**
	 * PDF de las proformas realizadas por empresa
	 * @param request
	 * @param response
	 * @param model
	 * @param idFactura
	 * @throws IOException
	 * @throws JRException 
	 */
	@RequestMapping(value = "/generaProformasPDF.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public void generarProformasPDF(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura) throws IOException, JRException {
		try {
//			JasperReport jasperReport;
//
//
//			jasperReport = JasperCompileManager.compileReport("reportes/ejemplo.jrxml");
//			JRDataSource vacio = new JREmptyDataSource(1);
//			 
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			parameters.put("nombreEmpresa", factura.getEmpresa().getNombre().toString());
//			
//			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters,vacio);
//      
//      JasperExportManager.exportReportToPdfFile(print, "reportes/ejemplo.pdf");
			
      Factura factura = facturaBo.findById(idFactura);
			
			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			ByteArrayOutputStream namePDF = Proformas.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(namePDF.toByteArray());
			response.setContentType("application/octet-stream");
			response.setContentLength((int) namePDF.toByteArray().length);
			String fileName = Constantes.EMPTY;

			fileName = "Proforma_" + factura.getId().toString();

			int BUFFER_SIZE = 4096;
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName + ".pdf");
			response.setHeader(headerKey, headerValue);
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (com.google.zxing.WriterException ex) {

		}

	}

	/**
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value = "/generaFacturaPDF", method = RequestMethod.GET, headers = "Accept=application/json")
	public void generarFacturaPDF(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura) throws IOException {
		try {
			Factura factura = facturaBo.findById(idFactura);
			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			int BUFFER_SIZE = 4096;
			ByteArrayInputStream inputStream = new ByteArrayInputStream(namePDF.toByteArray());
			response.setContentType("application/octet-stream");
			response.setContentLength((int) namePDF.toByteArray().length);
			String fileName = Constantes.EMPTY;

			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				fileName = "TiquetePDF_" + factura.getTipoDoc() + "-" + factura.getNumeroConsecutivo();
			}

			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				fileName = "FacturaPDF_" + factura.getTipoDoc() + "-" + factura.getNumeroConsecutivo();
			}

			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				fileName = "NotaCreditoPDF_" + factura.getTipoDoc() + "-" + factura.getNumeroConsecutivo();
			}
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				fileName = "NotaDebitoPDF_" + factura.getTipoDoc() + "-" + factura.getNumeroConsecutivo();
			}

			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName + ".pdf");
			response.setHeader(headerKey, headerValue);
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
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
	@RequestMapping(value = "/ListarFacturasEsperaActivasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Factura");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_PROFORMAS + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
			dataTableFilter = new JqGridFilter("usuarioCreacion.id", "'" + usuarioSesion.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@RequestMapping(value = "/ListarFacturasEsperaActivasCajeraAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasCajeroAjax(HttpServletRequest request, HttpServletResponse response) {
		RespuestaServiceDataTable respuestaServiceDataTable = new RespuestaServiceDataTable();
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_CAJERO)) {
			return respuestaServiceDataTable;

		}
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Factura");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_PROFORMAS + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
			dataTableFilter = new JqGridFilter("usuarioCreacion.id", "'" + usuarioSesion.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Facturas En espera de convertirse en factura oficial
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarFacturasEsperaPorMesaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasEsperaPorMesaAjax(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ParametrosPaginacionMesa parametrosPaginacionMesa) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Factura");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoDoc", "'" + Constantes.FACTURA_TIPO_DOC_PROFORMAS + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("mesa.id", "'" + parametrosPaginacionMesa.getMesa().getId() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		delimitadores.setLength(parametrosPaginacionMesa.getCantidadPorPagina());
		delimitadores.setStart(parametrosPaginacionMesa.getPaginaActual());
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Solo facturas de credito y debito en espera
	 * @param request
	 * @param response
	 * @return
	 */
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

		if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
			dataTableFilter = new JqGridFilter("usuarioCreacion.id", "'" + usuarioSesion.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Lista de las Proformas activas
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarProformasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarProformasActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Factura");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PROFORMAS + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
			dataTableFilter = new JqGridFilter("usuarioCreacion.id", "'" + usuarioSesion.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@RequestMapping(value = "/ListarFacturasActivasAndAnuladasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente, @RequestParam String tipoDocumento) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idCliente);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, cliente, usuarioSesion.getEmpresa(), usuarioBo, tipoDocumento);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
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
	@RequestMapping(value = "/listarFacturasActivasSinNotasCreditosCompletasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasActivasSinNotasCreditosCompletasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente, @RequestParam String tipoDocumento) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idCliente);
		DataTableDelimitador query = DelimitadorBuilder.getAnulacion(request, fechaInicio, fechaFin, cliente, usuarioSesion.getEmpresa(), usuarioBo, tipoDocumento);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarRecepcionFacturasActivasAndAnuladasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarRecepcionFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String cedulaEmisor) {

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
		}
		return UtilsForControllers.process(request, dataTableBo, delimitador, TO_COMMAND_RECEPCION);
	}

	@RequestMapping(value = "/ListarFacturasDelDiaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasDiaAjax(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = null;

		Date fechahoy = new Date();
		DateFormat df = new SimpleDateFormat(Constantes.DATE_FORMAT7);
		String reportDate = df.format(fechahoy);
		String tipoDocumento = Constantes.COMBO_TODOS;
		DataTableDelimitador query = DelimitadorBuilder.get(request, reportDate, reportDate, cliente, usuarioSesion.getEmpresa(), usuarioBo, tipoDocumento);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
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
			return this.crearFactura(facturaCommand, result, usuario);
		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}
	}

	@RequestMapping(value = "/service/CrearFacturaServiceAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public RespuestaServiceValidator crearFactura(@RequestBody TurismoCommand turismoCommand, BindingResult result) throws ParseException {

		try {

			Usuario usuario = null;
			FacturaCommand facturaCommand = null;

			return this.crearFactura(facturaCommand, result, usuario);
		} catch (Exception e) {

			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private RespuestaServiceValidator<?> crearFactura(FacturaCommand facturaCommand, BindingResult result, Usuario usuario) {
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
			UsuarioCaja usuarioCajaBd = null;
			// Si esta en estado facturada en base de datos se retorna un mensaje que ya fue procesada
			if (facturaCommand != null) {
				if (facturaCommand.getId() != null) {
					if (facturaCommand.getId() > Constantes.ZEROS_LONG) {
						Factura facturaRevision = facturaBo.findById(facturaCommand.getId());
						if (facturaRevision != null) {
							if (facturaRevision.getEstado() != null) {
								if (facturaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
									return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.ya.esta.procesada", result.getAllErrors());
								}
							}
							
						}
						
					}
				}
			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				usuarioCajaBd = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);

				if (usuarioCajaBd == null) {
					if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {

						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
					}
				}

			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) && !facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
					if (facturaCommand.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && facturaCommand.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && facturaCommand.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.ingreso.dinero", result.getAllErrors());
					}

				}
			}
			TipoCambio tipoCambio = null;
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				tipoCambio = tipoCambioBo.findByEstadoAndEmpresa(Constantes.ESTADO_ACTIVO, usuario.getEmpresa());
				if (tipoCambio == null) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.tipo.cambio.dolar.activo", result.getAllErrors());

				}
				facturaCommand.setTipoCambioMoneda(tipoCambio.getTotal());
			}
			if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				if (facturaCommand.getCliente() == null) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.incluir.cliente", result.getAllErrors());
				}

			}
			if (facturaCommand.getCliente() == null) {
				Cliente cliente = clienteBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_CLIENTE_FRECUENTE, usuario.getEmpresa());
				if (cliente == null) {
					cliente = clienteBo.crearClienteFrecuente(usuario.getEmpresa(), usuario);
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.intente.nuevo", result.getAllErrors());
				} else {
					facturaCommand.setCliente(cliente);
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
			// Validar el codigo de factura que se le va aplicar una nota de credito
			if (facturaCommand.getReferenciaNumero() != null) {
				if (!facturaCommand.getReferenciaNumero().equals(Constantes.EMPTY)) {
					Factura facturaReferenciaValidar = facturaBo.findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa());
					if(facturaReferenciaValidar.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && facturaReferenciaValidar.getReferenciaCodigo().equals(Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO) ) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.nota.credito.con.anulacion.completa", result.getAllErrors());
					}
					if (facturaReferenciaValidar == null) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.aplicar.nota.credito.o.debito.no.existe", result.getAllErrors());
					}else {
						facturaCommand.setReferenciaTipoDoc(facturaReferenciaValidar.getTipoDoc());
						Hacienda hacienda = haciendaBo.findByEmpresaAndClave(usuario.getEmpresa(), facturaReferenciaValidar.getClave());
						if(hacienda !=null) {
							if(hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA) || hacienda.equals(Constantes.HACIENDA_ESTADO_FIRMARDO_XML)) {
								return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.pendiente.comprobacion.hacienda", result.getAllErrors());
							}
						}else {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.pendiente.comprobacion.hacienda", result.getAllErrors());
						}
						
					}
				}
			}
			facturaCommand.setEmpresa(usuario.getEmpresa());
			facturaFormValidator.validate(facturaCommand, result);
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
				for (Detalle detalle : facturaBD.getDetalles()) {
					detalleBo.eliminar(detalle);
				}

			}
			Factura factura = facturaBo.crearFactura(facturaCommand, usuario, usuarioCajaBd, tipoCambio);
			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Factura facturaCreada = facturaBo.findById(factura.getId());
			if (!factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO) && !factura.getEstado().equals(Constantes.FACTURA_ESTADO_PENDIENTE) && !factura.getEstado().equals(Constantes.FACTURA_ESTADO_PROFORMAS) && !factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				// usuarioCajaDao.actualizarCaja(usuarioCaja, factura.getTotalEfectivo(), factura.getTotalTarjeta(), factura.getTotalBanco(), factura.getTotalCredito(), Constantes.ZEROS_DOUBLE, factura.getTotalImpuestoServicio());
				usuarioCajaBo.actualizarCaja(usuarioCajaBd);
			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente", facturaCreada);

		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}

	}

	/**
	 * Recibir factura de otro emisor
	 * @param request
	 * @param model
	 * @param recepcionFactura
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarRecepcionFacturaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarRecepcionFactura(HttpServletRequest request, ModelMap model, @ModelAttribute RecepcionFactura recepcionFactura, BindingResult result, SessionStatus status) throws Exception {
		try {

			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			// Se validan los datos
			if (recepcionFactura.getMensaje() != null && (!recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_ACEPTADO) && !recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_ACEPTADO_PARCIAL) && !recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_RECHAZADO))) {
				result.rejectValue("mensaje", "error.recepcionFactura.mensaje.requerido");
			} else if (!usuarioSesion.getEmpresa().getCedula().trim().toUpperCase().equals(recepcionFactura.getReceptorCedula().trim().toUpperCase())) {
				result.rejectValue("receptorCedula", "error.recepcionFactura.factura.otro.receptor");
			} else {
				Collection<RecepcionFactura> resultado = recepcionFacturaBo.findByClave(recepcionFactura.getEmisorCedula(), recepcionFactura.getFacturaClave());
				if (resultado != null && resultado.size() > 0) {
					result.rejectValue("facturaClave", "error.recepcionFactura.ya.exite");
				}
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			// Se prepara el objeto para almacenarlo
			recepcionFactura.setNumeroConsecutivoReceptor(empresaBo.generarConsecutivoRecepcionFactura(usuarioSesion.getEmpresa(), usuarioSesion, recepcionFactura));
			recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
			recepcionFactura.setEmpresa(usuarioSesion.getEmpresa());
			recepcionFactura.setCreated_at(new Date());
			recepcionFactura.setUpdated_at(new Date());
			recepcionFacturaBo.agregar(recepcionFactura);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("recepcionFactura.agregar.correctamente", recepcionFactura);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	/**
	 * Mostrar una Factura
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
//	@Autowired
//	private OpenIDConnectHaciendaComponent openIDConnectHaciendaComponent;

	@RequestMapping(value = "/MostrarFacturaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idFactura) {
		try {
			Factura facturaBD = facturaBo.findById(idFactura);
			

			 //Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

			// Se ejecuta este comando pero antes se ejecutan el comando para sacar la llave
			// criptografica desde linux
			// certificadoBo.agregar(usuario.getEmpresa(),"","");
			// usuario.getEmpresa().getClaveLlaveCriptografica().toString(),
			// usuario.getEmpresa().getNombreLlaveCriptografica());
			// String xml = facturaXMLServices.getCrearXMLSinFirma(facturaBD);
			// facturaXMLServices.getFirmarXML(xml, facturaBD.getEmpresa());

			// KeyStore keyStore = null;
			// LlaveCriptografica llaveCriptografica = new LlaveCriptografica();
			//
			// llaveCriptografica.setPassSignature(usuario.getEmpresa().getClaveLlaveCriptografica().toString());
			// llaveCriptografica.setPathSignature(usuario.getEmpresa().getNombreLlaveCriptografica());
			// XadesSigner xadesSigner =
			// llaveCriptograficaService.getSigner(usuario.getEmpresa().getNombreLlaveCriptografica(),usuario.getEmpresa().getClaveLlaveCriptografica().toString());
			// keyStore = llaveCriptograficaService.getKeyStore(llaveCriptografica);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", facturaBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

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
	 * Cambia la proforma a pendiente para que sea procesada
	 * @param request
	 * @param model
	 * @param idFactura
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/CambiarEstadoProformaAPedienteAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cambiarEstadoProforma(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura) throws Exception {
		try {

			Factura facturaBD = facturaBo.findById(idFactura);
			if (facturaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.no.existe");
			}

			facturaBD.setEstado(Constantes.FACTURA_ESTADO_PENDIENTE);
			facturaBD.setTipoDoc(Constantes.FACTURA_TIPO_DOC_TIQUETE);
			facturaBD.setTotalCambioPagar(Constantes.ZEROS_DOUBLE);
			facturaBD.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
			facturaBD.setTotalBanco(Constantes.ZEROS_DOUBLE);
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
	@RequestMapping(value = "/EnviarCorreoAlternativoProformaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoAlternativo(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura, @RequestParam String correo) {
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
			if (facturaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {

			}
			ByteArrayOutputStream namePDF = Proformas.main(facturaBD.getNumeroConsecutivo(), facturaBD.getTipoDoc(), facturaElectronica);

			Collection<Attachment> attachments = createAttachments(PDF_Attach(facturaBD.getId().toString(), facturaBD.getEmpresa().getCedula(), asPDF(namePDF), facturaBD.getTipoDoc()));

			Map<String, Object> modelEmail = new HashMap<>();

			modelEmail.put("clave", facturaBD.getId());
			modelEmail.put("nombreEmpresa", facturaBD.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? facturaBD.getEmpresa().getNombre() : facturaBD.getEmpresa().getNombreComercial());
			modelEmail.put("correo", facturaBD.getEmpresa().getCorreoElectronico());
			modelEmail.put("telefono", facturaBD.getEmpresa().getTelefono());

			String nombre = facturaBD.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? facturaBD.getEmpresa().getNombre() : facturaBD.getEmpresa().getNombreComercial();
			String from = "Proforma_No_Reply@emprendesoftcr.com";
			if (facturaBD.getEmpresa().getAbreviaturaEmpresa() != null) {
				if (!facturaBD.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
					from = facturaBD.getEmpresa().getAbreviaturaEmpresa() + "_Proforma" + "_No_Reply@emprendesoftcr.com";
				}
			}

			String subject = "Proforma N° " + facturaBD.getId().toString() + " del Emisor: " + nombre;

			//
			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailProforma.vm", modelEmail);
			//

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
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
	@RequestMapping(value = "/EnviarCorreoAlternativoFacturaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoAlternativoFactura(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura, @RequestParam String correo) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Factura factura = facturaBo.findById(idFactura);
			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.no.existe");
			}

			ArrayList<String> listaCorreos = new ArrayList<String>();
			if (!correo.equals(Constantes.EMPTY)) {
				listaCorreos.add(correo);
			}
			if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {

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
			if (listaCorreos != null) {
				if (!listaCorreos.isEmpty()) {
					FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
					ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);

					String clave = getConsecutivo(factura.getTipoDoc(), factura.getNumeroConsecutivo());
					Collection<Attachment> attachments = createAttachments(PDF_Attach(clave, factura.getEmpresa().getCedula(), asPDF(namePDF), factura.getTipoDoc()));

					Map<String, Object> modelEmail = new HashMap<>();

					modelEmail.put("clave", clave);
					modelEmail.put("nombreEmpresa", factura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? factura.getEmpresa().getNombre() : factura.getEmpresa().getNombreComercial());
					modelEmail.put("correo", factura.getEmpresa().getCorreoElectronico());
					modelEmail.put("telefono", factura.getEmpresa().getTelefono());

					String from = "Documentos_No_Reply@emprendesoftcr.com";

					if (factura.getEmpresa().getAbreviaturaEmpresa() != null) {
						if (!factura.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
							from = factura.getEmpresa().getAbreviaturaEmpresa() + "_Doc_Electronico" + "_No_Reply@emprendesoftcr.com";
						}
					}

					String nombre = factura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? factura.getEmpresa().getNombre() : factura.getEmpresa().getNombreComercial();
					String subject = "Documento Electrónico N° " + clave + " del Emisor: " + nombre;

					//
					correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailHacienda.vm", modelEmail);
					//

					respuestaServiceValidator.setStatus(HttpStatus.OK.value());
					respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
					return respuestaServiceValidator;

				}
			}
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("factura.no.tiene.correos.asociado"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
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

	private String getConsecutivo(String tipoDoc, String consecutivo) {
		return tipoDoc + "-" + consecutivo;
	}

	private ByteArrayDataSource asPDF(ByteArrayOutputStream stream) {
		return new ByteArrayDataSource(stream.toByteArray(), "text/pdf");
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Cliente cliente, Empresa empresa, UsuarioBo usuarioBo, String tipoDocumento) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Factura");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PROFORMAS.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_ANULADA.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (cliente != null) {
				delimitador.addFiltro(new JqGridFilter("cliente.id", "'" + cliente.getId().toString() + "'", "="));
			}
			if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
				Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
				delimitador.addFiltro(new JqGridFilter("usuarioCreacion.id", "'" + usuario.getId().toString() + "'", "="));
			}
			if (tipoDocumento != null) {
				if (!tipoDocumento.equals(Constantes.EMPTY)) {
					if (!tipoDocumento.equals(Constantes.COMBO_TODOS)) {
						delimitador.addFiltro(new JqGridFilter("tipoDoc", "'" + tipoDocumento.toString() + "'", "="));
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

				delimitador.addFiltro(new JqGridFilter("fechaEmision", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("fechaEmision", fin, "dateFinal<="));
			}
			return delimitador;
		}
		
		static DataTableDelimitador getAnulacion(HttpServletRequest request, String inicio, String fin, Cliente cliente, Empresa empresa, UsuarioBo usuarioBo, String tipoDocumento) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Factura");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PROFORMAS.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_ANULADA.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("referenciaCodigo", "'" + Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (cliente != null) {
				delimitador.addFiltro(new JqGridFilter("cliente.id", "'" + cliente.getId().toString() + "'", "="));
			}
			if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
				Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
				delimitador.addFiltro(new JqGridFilter("usuarioCreacion.id", "'" + usuario.getId().toString() + "'", "="));
			}
			if (tipoDocumento != null) {
				if (!tipoDocumento.equals(Constantes.EMPTY)) {
					if (!tipoDocumento.equals(Constantes.COMBO_TODOS)) {
						delimitador.addFiltro(new JqGridFilter("tipoDoc", "'" + tipoDocumento.toString() + "'", "="));
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

				delimitador.addFiltro(new JqGridFilter("fechaEmision", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("fechaEmision", fin, "dateFinal<="));
			}
			return delimitador;
		}
		
	}

	static class RESPONSES {

		private static class OK {

			private static class FACTURA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class FACTURA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.noExiste");
			}
		}
	}

}

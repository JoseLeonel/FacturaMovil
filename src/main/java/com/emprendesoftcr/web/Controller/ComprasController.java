package com.emprendesoftcr.web.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import org.jxls.template.SimpleExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CompraBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.ProveedorBo;
import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.CompraCommand;
import com.emprendesoftcr.web.command.CompraEsperaCommand;
import com.emprendesoftcr.web.command.DetalleCompraEsperaCommand;
import com.emprendesoftcr.web.command.TotalComprasAceptadasCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class ComprasController {

	private static final Function<Object, CompraEsperaCommand>				TO_COMMAND					= new Function<Object, CompraEsperaCommand>() {

																																													@Override
																																													public CompraEsperaCommand apply(Object f) {
																																														return new CompraEsperaCommand((Compra) f);
																																													};
																																												};

	private static final Function<Object, DetalleCompraEsperaCommand>	TO_COMMAND_DETALLE	= new Function<Object, DetalleCompraEsperaCommand>() {

																																													@Override
																																													public DetalleCompraEsperaCommand apply(Object f) {
																																														return new DetalleCompraEsperaCommand((DetalleCompra) f);
																																													};
																																												};

	@Autowired
	private DataTableBo																								dataTableBo;

	@Autowired
	private RecepcionFacturaBo																				recepcionFacturaBo;

	@Autowired
	private UsuarioBo																									usuarioBo;

	@Autowired
	private ProveedorBo																								proveedorBo;

	@Autowired
	private CorreosBo																									correosBo;

	@Autowired
	private CompraBo																									compraBo;

	@Autowired
	private EmpresaPropertyEditor																			empresaPropertyEditor;

	@Autowired
	private ProveedorPropertyEditor																		proveedorPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Proveedor.class, proveedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListaCompras", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/compras/ListarCompras";
	}

	@RequestMapping(value = "/ListaComprasAnular", method = RequestMethod.GET)
	public String listarComprasAnular(ModelMap model) {
		return "views/compras/ListarComprasAnulacion";
	}

	@RequestMapping(value = "/totalesComprasAceptadas", method = RequestMethod.GET)
	public String totalesAceptacionCompras(ModelMap model) {
		return "views/compras/totalesComprasAceptadas";
	}

	@RequestMapping(value = "/TotalComprasAceptadasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public TotalComprasAceptadasCommand totalComprasAceptadasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Integer estado, @RequestParam Integer tipoGasto,String actividadEconocimica) {
		Date inicio = Utils.parseDate(fechaInicio);
		Date finalDate = Utils.dateToDate(Utils.parseDate(fechaFin), true);
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		return compraBo.sumarComprasAceptadas(inicio, finalDate, usuario.getEmpresa().getId(), estado,actividadEconocimica,tipoGasto);
	}

	@RequestMapping(value = "/CorreoTotalComprasAceptadasAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public void envioTotalComprasAceptadasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String correoAlternativo, @RequestParam Integer estado, @RequestParam Integer tipoGasto) {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		// Se obtiene los totales
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFinal = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		TotalComprasAceptadasCommand totalComprasAceptadasCommand = compraBo.sumarComprasAceptadas(fechaInicio, fechaFinal, usuario.getEmpresa().getId(), estado);

		Collection<RecepcionFactura> recepcionFacturas = recepcionFacturaBo.findByFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFinal, usuario.getEmpresa(), Constantes.EMPTY, estado,tipoGasto);

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelRecepcionCompras(recepcionFacturas);
		Collection<Attachment> attachments = createAttachments(attachment("FacturasMensuales", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

		// Se prepara el correo
		String from = "ComprasEmitidas@emprendesoftcr.com";
		if (usuario.getEmpresa().getAbreviaturaEmpresa() != null) {
			if (!usuario.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
				from = usuario.getEmpresa().getAbreviaturaEmpresa() + "_ComprasEmitidas" + "_No_Reply@emprendesoftcr.com";
			}
		}
		String subject = "Compras dentro del rango de fechas: " + fechaInicioParam + " al " + fechaFinParam;

		ArrayList<String> listaCorreos = new ArrayList<>();
		if (correoAlternativo != null && correoAlternativo.length() > 0) {
			listaCorreos.add(correoAlternativo);
		} else {
			listaCorreos.add(usuario.getEmpresa().getCorreoElectronico());
		}

		Map<String, Object> modelEmail = new HashMap<>();
		modelEmail.put("nombreEmpresa", usuario.getEmpresa().getNombre());
		if (estado.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
			modelEmail.put("estado", "Aceptadas");
		}
		if (estado.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
			modelEmail.put("estado", "No Aceptadas");
		}
		modelEmail.put("fechaInicial", Utils.getFechaStr(fechaInicio));
		modelEmail.put("fechaFinal", Utils.getFechaStr(fechaFinal));
		modelEmail.put("total", totalComprasAceptadasCommand.getTotal() != null ? totalComprasAceptadasCommand.getTotalSTR() : Constantes.ZEROS);
		modelEmail.put("totalImpuesto", totalComprasAceptadasCommand.getTotalImpuesto() != null ? totalComprasAceptadasCommand.getTotalImpuestoSTR() : Constantes.ZEROS);

		correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_COMPRAS_ACEPTADAS, modelEmail);
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	/**
	 * @param request
	 * @param response
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param cedulaEmisor
	 * @param estado
	 * @throws IOException
	 */
	@RequestMapping(value = "/DescargarComprasAceptadasAjax.do", method = RequestMethod.GET)
	public void descargarComprasAceptadasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String cedulaEmisor,@RequestParam Integer estado,@RequestParam Integer tipoGasto) throws IOException {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<RecepcionFactura> recepcionFacturas = recepcionFacturaBo.findByFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFin, usuario.getEmpresa(), cedulaEmisor, estado,tipoGasto);

		String nombreArchivo = "comprasAceptadas.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelRecepcionCompras(recepcionFacturas);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	/**
	 * Modulo de compras
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/compras", method = RequestMethod.GET)
	public String crearCompras(ModelMap model) {
		return "views/compras/crearCompra";
	}

	@RequestMapping(value = "/CrearCompraAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute CompraCommand compraCommand, BindingResult result, SessionStatus status) {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			if (!compraCommand.getFormaPago().equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
				compraCommand.setFechaCredito(null);
			}
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (!compraCommand.getConsecutivo().equals(Constantes.EMPTY)) {
				Compra compraBD = compraBo.findByConsecutivoAndEmpresa(compraCommand.getConsecutivo(), usuarioSesion.getEmpresa());
				if (compraBD != null) {
					if (!compraBD.getId().equals(compraCommand.getId())) {
						result.rejectValue("consecutivo", "error.compra.existe.consecutivo");
					}
				}
			}

			if (compraCommand.getId() != null) {
				if (compraCommand.getId() > 0) {
					Compra combraVerificar = compraBo.findById(compraCommand.getId());
					if (combraVerificar != null) {
						if (combraVerificar.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("compra.error.ya.se.encuentra.ingresada", result.getAllErrors());
						}
					}
				}
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			compraCommand.setEmpresa(usuarioSesion.getEmpresa());
			compraCommand.setUsuarioCreacion(usuarioSesion);
			compraBo.crearCompra(compraCommand, usuarioSesion);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.agregar.correctamente", compraCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private ByteArrayOutputStream createExcelRecepcionCompras(Collection<RecepcionFactura> recepcionFacturas) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Id", "Fecha Ingreso", "Fecha Emision", "Clave", "# Documento Receptor", "Cedula Emisor", "Nombre Emisor", "# Compra", "Total Impuestos", "Total", "Tipo Moneda", "Tipo Cambio", "Tipo Documento");
		new SimpleExporter().gridExport(headers, recepcionFacturas, "id, created_atSTR,fechaEmisionSTR,facturaClave, numeroConsecutivoReceptor, emisorCedula, emisorNombre, facturaConsecutivo, totalImpuestosSTR,totalFacturaSTR, facturaCodigoMoneda, facturaTipoCambio, tipoDocumentoStr", baos);
		return baos;
	}

	@RequestMapping(value = "/DescargarDetalladaAceptadasAjax.do", method = RequestMethod.GET)
	public void descargarDetalladasAceptadasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String cedulaEmisor,@RequestParam Integer estado,@RequestParam Integer tipoGasto) throws IOException {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<RecepcionFacturaDetalle> recepcionFacturas = recepcionFacturaBo.findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFin, usuario.getEmpresa(), cedulaEmisor, estado,tipoGasto);

		String nombreArchivo = "comprasPorDetalleAceptadas.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelDetalleRecepcionCompras(recepcionFacturas);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelDetalleRecepcionCompras(Collection<RecepcionFacturaDetalle> recepcionFacturas) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Fecha Ingreso", "Fecha Emision", "Clave", "# Documento Receptor", "Cedula Emisor", "Nombre Emisor", "# Compra", "Tipo Moneda", "Tipo Cambio", "Tipo Documento", "IVA", "Tarifa", "Total Impuesto");
		new SimpleExporter().gridExport(headers, recepcionFacturas, "recepcionFactura.created_atSTR,recepcionFactura.fechaEmisionSTR,recepcionFactura.facturaClave, recepcionFactura.numeroConsecutivoReceptor, recepcionFactura.emisorCedula, recepcionFactura.emisorNombre, recepcionFactura.facturaConsecutivo,recepcionFactura.facturaCodigoMoneda, recepcionFactura.facturaTipoCambio, recepcionFactura.tipoDocumentoStr,impuestoCodigoSTR,impuestoCodigoTarifaSTR,impuestoNeto", baos);
		return baos;
	}

	/**
	 * Descargar Compras
	 * @param request
	 * @param response
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param idProveedor
	 * @throws IOException
	 */
	@RequestMapping(value = "/DescargarComprasIngresadasAlmacenAjax.do", method = RequestMethod.GET)
	public void descargarComprasIngresadasAlmacenAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Long idProveedor) throws IOException {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = null;
		if (idProveedor != null) {
			proveedor = proveedorBo.buscar(idProveedor);

		}
		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<Compra> compras = compraBo.findByFechaInicioAndFechaFinalAndProveedor(fechaInicio, fechaFin, usuario.getEmpresa(), proveedor);

		String nombreArchivo = "comprasIngresadasAlmacen.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelComprasIngresadasAlmacen(compras);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelComprasIngresadasAlmacen(Collection<Compra> compras) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Id", "Fecha Ingreso", "# Documento Receptor", "Proveedor", "Total Impuestos", "Total", "usuario");
		new SimpleExporter().gridExport(headers, compras, "id, fechaIngresoSTR,consecutivo, proveedorSTR, totalImpuestoSTR,totalCompraSTR,usuarioIngresoInventario.nombreUsuario ", baos);
		return baos;
	}

	/**
	 * Lista las compras pendientes de ingresar al inventario
	 * @param request
	 * @param response
	 * @param idEmpresa
	 * @return
	 */
	@RequestMapping(value = "/ListarComprasEsperaActivasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Compra");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_PENDIENTE.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Lista las compras ingresadas al inventario y que no estan pendiente
	 * @param request
	 * @param response
	 * @param idEmpresa
	 * @return
	 */
	@RequestMapping(value = "/ListarComprasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarComprasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idProveedor, @RequestParam String estado) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, proveedor, usuarioSesion.getEmpresa(), estado);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	@RequestMapping(value = "/ListarComprasNoAnuladasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarComprasNoAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idProveedor) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		DataTableDelimitador query = DelimitadorBuilderAnuladas.get(request, fechaInicio, fechaFin, proveedor, usuarioSesion.getEmpresa());

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Mostrar la compra
	 * @param request
	 * @param model
	 * @param compra
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/MostrarCompraEsperaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) {
		try {
			Compra compraBD = compraBo.findById(id);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", compraBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Anular un Compra
	 * @param request
	 * @param response
	 * @param result
	 * @param idCompra
	 * @return
	 */
	@RequestMapping(value = "/AnularCompraAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator anularCompra(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam Long idCompra, BindingResult result, SessionStatus status) {
		try {
			Compra compra = compraBo.findById(idCompra);
			if (compra == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			compraBo.anularCompra(compra);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.anulado.correctamente", compra);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarDetlleByCompraAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idCompra) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "DetalleCompra");

		JqGridFilter dataTableFilter = new JqGridFilter("compra.id", "'" + idCompra + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_DETALLE);
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Proveedor proveedor, Empresa empresa, String estado) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Compra");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_PENDIENTE.toString() + "'", "<>"));

			if (!estado.equals(Constantes.COMBO_TODOS)) {
				delimitador.addFiltro(new JqGridFilter("estado", "'" + estado + "'", "="));
			}

			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (proveedor != null) {
				delimitador.addFiltro(new JqGridFilter("proveedor.id", "'" + proveedor.getId().toString() + "'", "="));
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

				delimitador.addFiltro(new JqGridFilter("created_at", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("created_at", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

	private static class DelimitadorBuilderAnuladas {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Proveedor proveedor, Empresa empresa) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Compra");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_ANULADA.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (proveedor != null) {
				delimitador.addFiltro(new JqGridFilter("proveedor.id", "'" + proveedor.getId().toString() + "'", "="));
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

				delimitador.addFiltro(new JqGridFilter("created_at", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("created_at", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

}

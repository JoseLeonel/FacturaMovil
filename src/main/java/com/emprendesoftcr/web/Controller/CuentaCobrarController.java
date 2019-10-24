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

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.Vendedor;
import com.emprendesoftcr.modelo.sqlNativo.ArticuloMinimoNative;
import com.emprendesoftcr.modelo.sqlNativo.GraficoCuentasPorCobrarNative;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.command.CuentaCobrarCommand;
import com.emprendesoftcr.web.command.TotalCuentaPorCobrarCommand;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.CuentaCobrarPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.VendedorPropertyEditor;
import com.google.common.base.Function;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class CuentaCobrarController {

	private static final Function<Object, CuentaCobrarCommand>	TO_COMMAND	= new Function<Object, CuentaCobrarCommand>() {

																																						@Override
																																						public CuentaCobrarCommand apply(Object f) {
																																							return new CuentaCobrarCommand((CuentaCobrar) f);
																																						};
																																					};
	@Autowired
	private UsuarioBo																						usuarioBo;

	@Autowired
	private CorreosBo																						correosBo;

	@Autowired
	private DataTableBo																					dataTableBo;

	@Autowired
	private ClienteBo																						clienteBo;

	@Autowired
	private CuentaCobrarBo																			cuentaCobrarBo;

	@Autowired
	ConsultasNativeBo																						consultasNativeBo;

	@Autowired
	private EmpresaPropertyEditor																empresaPropertyEditor;
	@Autowired
	private CuentaCobrarPropertyEditor													cuentaCobrarPropertyEditor;
	@Autowired
	private VendedorPropertyEditor															vendedorPropertyEditor;
	@Autowired
	private ClientePropertyEditor																clientePropertyEditor;
	@Autowired
	private StringPropertyEditor																stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(CuentaCobrar.class, cuentaCobrarPropertyEditor);
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarCuentaCobrar", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/cuentasxcobrar/ListarCuentasXCobrar";
	}

	@RequestMapping(value = "/ListarCuentasXCobrarConsulta", method = RequestMethod.GET)
	public String listarConsulta(ModelMap model) {
		return "views/cuentasxcobrar/ListarCuentasXCobrarConsulta";
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/GraficoCuentasXCobrarAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable graficoCuentasXCobrarAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<GraficoCuentasPorCobrarNative>  objetos = consultasNativeBo.findByGraficoCuentasXCobrar(usuarioSesion.getEmpresa());
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (GraficoCuentasPorCobrarNative graficoCuentasPorCobrarNative : objetos) {
				if (graficoCuentasPorCobrarNative.getId().longValue() > 0L) {
					solicitudList.add(new CuentaCobrarCommand(graficoCuentasPorCobrarNative));
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
	
////Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarDetalleTotalCuentasXCobrarAjax.do", method = RequestMethod.GET)
	public void descargarDetalleTotalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Long idClienteParam, @RequestParam String estadoParam) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idClienteParam);

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<CuentaCobrar> cuentaCobras = cuentaCobrarBo.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(fechaInicio, fechaFin, usuario.getEmpresa(), cliente, estadoParam);

		String nombreArchivo = "cuentaxCobrar.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelCuentaCobrar(cuentaCobras);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

////Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarDetalleTotalCuentasXCobrarEstadoAjax.do", method = RequestMethod.GET)
	public void descargarDetalleTotalFacturasEstadoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idClienteParam, @RequestParam String estadoParam) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idClienteParam);

		// Se buscan las facturas
		Collection<CuentaCobrar> cuentaCobras = cuentaCobrarBo.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(usuario.getEmpresa(), cliente, estadoParam);

		String nombreArchivo = "cuentaxCobrar.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelCuentaCobrar(cuentaCobras);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

//Enviar Correo de las cuentas por cobrar
	@RequestMapping(value = "/EnvioDetalleCuentasXCobrarCorreoAjax.do", method = RequestMethod.GET)
	public void enviarCorreoCuentasXCobrarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Long idClienteParam, @RequestParam String estadoParam, @RequestParam String correoAlternativo, @RequestParam String total, @RequestParam String saldo, @RequestParam String abono) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idClienteParam);

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<CuentaCobrar> cuentaCobras = cuentaCobrarBo.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(fechaInicio, fechaFin, usuario.getEmpresa(), cliente, estadoParam);
//Se prepara el excell
		ByteArrayOutputStream baos = createExcelCuentaCobrar(cuentaCobras);

		Collection<Attachment> attachments = createAttachments(attachment("FacturaPendientes", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

		// Se prepara el correo
		String from = "FacturasEmitidas@emprendesoftcr.com";
		if (usuario.getEmpresa().getAbreviaturaEmpresa() != null) {
			if (!usuario.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
				from = usuario.getEmpresa().getAbreviaturaEmpresa() + "_FacturasPendientes" + "_No_Reply@emprendesoftcr.com";
			}
		}
		String subject = "Facturas dentro del rango de fechas: " + fechaInicioParam + " al " + fechaFinParam;

		ArrayList<String> listaCorreos = new ArrayList<>();

		listaCorreos.add(correoAlternativo);

		Map<String, Object> modelEmail = new HashMap<>();
		modelEmail.put("nombreEmpresa", usuario.getEmpresa().getNombre());
		modelEmail.put("fechaInicial", Utils.getFechaStr(fechaInicio));
		modelEmail.put("fechaFinal", Utils.getFechaStr(fechaFin));
		modelEmail.put("total", total);
		modelEmail.put("abono", abono);
		modelEmail.put("saldo", saldo);

		correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/cuentasxcobrar.vm", modelEmail);
	}

//Enviar Correo de las cuentas por cobrar
	@RequestMapping(value = "/EnvioDetalleCuentasXCobrarCorreoEstadoAjax.do", method = RequestMethod.GET)
	public void enviarCorreoCuentasXCobrarEstadoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idClienteParam, @RequestParam String estadoParam, @RequestParam String correoAlternativo, @RequestParam String total, @RequestParam String saldo, @RequestParam String abono) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idClienteParam);

		Collection<CuentaCobrar> cuentaCobras = cuentaCobrarBo.cuentasPorCobrarbyFechasAndEmpresaAndClienteAndEstado(usuario.getEmpresa(), cliente, estadoParam);
//Se prepara el excell
		ByteArrayOutputStream baos = createExcelCuentaCobrar(cuentaCobras);

		Collection<Attachment> attachments = createAttachments(attachment("FacturaPendientes", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

		// Se prepara el correo
		String from = "FacturasEmitidas@emprendesoftcr.com";
		if (usuario.getEmpresa().getAbreviaturaEmpresa() != null) {
			if (!usuario.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
				from = usuario.getEmpresa().getAbreviaturaEmpresa() + "_FacturasPendientes" + "_No_Reply@emprendesoftcr.com";
			}
		}
		String subject = "Facturas Pendientes de cancelar ";

		ArrayList<String> listaCorreos = new ArrayList<>();

		listaCorreos.add(correoAlternativo);

		Map<String, Object> modelEmail = new HashMap<>();
		modelEmail.put("nombreEmpresa", usuario.getEmpresa().getNombre());
		modelEmail.put("total", total);
		modelEmail.put("abono", abono);
		modelEmail.put("saldo", saldo);

		correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/cuentasxcobrar.vm", modelEmail);
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	private ByteArrayOutputStream createExcelCuentaCobrar(Collection<CuentaCobrar> cuentaCobrar) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("#cuenta", "Fecha Emision", "# Documento", "Cliente", "Moneda", "Total", "Saldo", "Abono");
		new SimpleExporter().gridExport(headers, cuentaCobrar, "id, created_atSTR, factura, nombreClienteSTR,codigoMoneda, total,totalSaldo,totalAbono", baos);
		return baos;
	}

	/**
	 * Total de Cuentas por cobrar
	 * @param request
	 * @param response
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param idCedula
	 * @return
	 */
	@RequestMapping(value = "/TotalCuentasPorCobrarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public TotalCuentaPorCobrarCommand totalCuentasPorCobrarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Long idCliente) {
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFinal = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idCliente);

		return cuentaCobrarBo.sumarCuentasPorCobrar(fechaInicio, fechaFinal, usuario.getEmpresa().getId(), cliente);
	}

	/**
	 * Listado de las cuentas por cobrar
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarCuentaCobrarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFinal, @RequestParam Long idCliente, @RequestParam String estado) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idCliente);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFinal, cliente, usuarioSesion.getEmpresa(), estado);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarCuentaCobrarPorEstadoCedulaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarEstadoAndCedulaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idCliente, @RequestParam String estado) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idCliente);
		DataTableDelimitador query = DelimitadorBuilderEstado.get(request, cliente, usuarioSesion.getEmpresa(), estado);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Agregar una Cuenta Manual cuando no se realiza por medio de una factura creada desde del sistema
	 * @param request
	 * @param model
	 * @param cuentaCobrar
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarCuentaCobrarManualAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute CuentaCobrar cuentaCobrar, BindingResult result, SessionStatus status) throws Exception {
		try {

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			Double totalCuotas = cuentaCobrar.getMontoCouta() * cuentaCobrar.getCantidadPagos();

			if (totalCuotas < cuentaCobrar.getTotal() || totalCuotas > cuentaCobrar.getTotal()) {
				result.rejectValue("montoCouta", "error.cuentaCobrar.montoCuota.menor.total");
			}
			if (cuentaCobrar.getCantidadPagos() == null) {
				result.rejectValue("cantidadPagos", "error.cuentaCobrar.cantidadPagos.requerido");
			} else if (cuentaCobrar.getCantidadPagos() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("cantidadPagos", "error.cuentaCobrar.cantidadPagos.requerido");
			}
			if (cuentaCobrar.getMontoCouta() == null) {
				result.rejectValue("montoCuota", "error.cuentaCobrar.montoCuota.requerido");
			} else if (cuentaCobrar.getMontoCouta() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("montoCuota", "error.cuentaCobrar.montoCuota.requerido");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			cuentaCobrar.setCreated_at(new Date());
			cuentaCobrar.setUpdated_at(new Date());
			cuentaCobrar.setTipo(Constantes.CUENTA_POR_COBRAR_TIPO_MANUAL);
			cuentaCobrar.setEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE);
			cuentaCobrar.setTotalAbono(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setTotalSaldo(cuentaCobrar.getTotal());
			cuentaCobrar.setUsuario(usuarioSesion);
			cuentaCobrar.setEmpresa(usuarioSesion.getEmpresa());
			cuentaCobrarBo.agregar(cuentaCobrar);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.agregar.correctamente", cuentaCobrar);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * modificar una cuenta por cobrar
	 * @param request
	 * @param model
	 * @param cuentaCobrar
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarCuentaCobrarjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute CuentaCobrar cuentaCobrar, BindingResult result, SessionStatus status) throws Exception {
		try {

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			CuentaCobrar cuentaCobrarBD = cuentaCobrarBo.buscar(cuentaCobrar.getId());

			if (cuentaCobrarBD == null) {
				return RESPONSES.ERROR.CUENTACOBRAR.NO_EXISTE;
			}

			Double totalCuotas = cuentaCobrar.getMontoCouta() * cuentaCobrar.getCantidadPagos();
			if (totalCuotas < cuentaCobrar.getTotal() || totalCuotas > cuentaCobrar.getTotal()) {
				result.rejectValue("montoCouta", "error.cuentaCobrar.montoCuota.menor.total");
			}
			if (cuentaCobrar.getCantidadPagos() == null) {
				result.rejectValue("cantidadPagos", "error.cuentaCobrar.cantidadPagos.requerido");
			} else if (cuentaCobrar.getCantidadPagos() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("cantidadPagos", "error.cuentaCobrar.cantidadPagos.requerido");
			}
			if (cuentaCobrar.getMontoCouta() == null) {
				result.rejectValue("montoCuota", "error.cuentaCobrar.montoCuota.requerido");
			} else if (cuentaCobrar.getMontoCouta() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("montoCuota", "error.cuentaCobrar.montoCuota.requerido");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			cuentaCobrarBD.setUpdated_at(new Date());
			cuentaCobrarBD.setCantidadPagos(cuentaCobrar.getCantidadPagos());
			cuentaCobrarBD.setDescripcionArticulo(cuentaCobrar.getDescripcionArticulo());
			cuentaCobrarBD.setDescuento(cuentaCobrar.getDescuento());
			cuentaCobrarBD.setFactura(cuentaCobrar.getFactura());
			cuentaCobrarBD.setEstado(cuentaCobrar.getEstado());
			cuentaCobrarBD.setFechaEntrega(cuentaCobrar.getFechaEntrega());
			cuentaCobrarBD.setFechaPlazo(cuentaCobrar.getFechaPlazo());
			cuentaCobrarBD.setLetraCambio(cuentaCobrar.getLetraCambio());
			cuentaCobrarBD.setMontoCouta(cuentaCobrar.getMontoCouta());
			cuentaCobrarBD.setNota(cuentaCobrar.getNota());
			cuentaCobrarBD.setRecibo(cuentaCobrar.getRecibo());
			cuentaCobrarBD.setTotal(cuentaCobrar.getTotal());
			cuentaCobrarBD.setVendedor(cuentaCobrar.getVendedor());
			cuentaCobrarBD.setCliente(cuentaCobrar.getCliente());
			cuentaCobrarBD.setUsuario(usuarioSesion);
			cuentaCobrarBo.modificar(cuentaCobrarBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.mensaje.alert.modificar", cuentaCobrarBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar una cuenta por cobrar
	 * @param request
	 * @param model
	 * @param cuentaCobrar
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarCuentaCobrarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute CuentaCobrar cuentaCobrar, BindingResult result, SessionStatus status) throws Exception {
		try {
			CuentaCobrarCommand cuentaCobrarCommand = new CuentaCobrarCommand(cuentaCobrarBo.buscar(cuentaCobrar.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", cuentaCobrarCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Cliente cliente, Empresa empresa, String estado) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "CuentaCobrar");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

//			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.cuen.FACTURA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (cliente != null) {

				delimitador.addFiltro(new JqGridFilter("cliente.id", "'" + cliente.getId().toString() + "'", "="));
			}
			if (estado != null) {
				if (!estado.equals(Constantes.EMPTY)) {
					if (!estado.equals(Constantes.COMBO_TODOS)) {
						delimitador.addFiltro(new JqGridFilter("estado", "'" + estado + "'", "="));
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

				delimitador.addFiltro(new JqGridFilter("created_at", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("created_at", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

	private static class DelimitadorBuilderEstado {

		static DataTableDelimitador get(HttpServletRequest request, Cliente cliente, Empresa empresa, String estado) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "CuentaCobrar");

//			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.cuen.FACTURA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (cliente != null) {

				delimitador.addFiltro(new JqGridFilter("cliente.id", "'" + cliente.getId().toString() + "'", "="));
			}
			if (estado != null) {
				if (!estado.equals(Constantes.EMPTY)) {
					if (!estado.equals(Constantes.COMBO_TODOS)) {
						delimitador.addFiltro(new JqGridFilter("estado", "'" + estado + "'", "="));
					}

				}
			}

			return delimitador;
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CUENTACOBRAR {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CUENTACOBRAR {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.cuentaCobrar.noExiste");
			}
		}
	}

}

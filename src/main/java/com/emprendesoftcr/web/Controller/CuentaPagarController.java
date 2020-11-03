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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.CuentaPagarBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.ProveedorBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.CuentaPagarCommand;
import com.emprendesoftcr.web.propertyEditor.CuentaPagarPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class CuentaPagarController {

	private static final Function<Object, CuentaPagarCommand>	TO_COMMAND	= new Function<Object, CuentaPagarCommand>() {

																																					@Override
																																					public CuentaPagarCommand apply(Object f) {
																																						return new CuentaPagarCommand((CuentaPagar) f);
																																					};
																																				};
	@Autowired
	private UsuarioBo																					usuarioBo;

	@Autowired
	private CorreosBo																					correosBo;

	@Autowired
	private DataTableBo																				dataTableBo;

	@Autowired
	private ProveedorBo																				proveedorBo;

	private Logger																						log					= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CuentaPagarBo																			cuentaPagarBo;

	@Autowired
	private EmpresaPropertyEditor															empresaPropertyEditor;
	@Autowired
	private CuentaPagarPropertyEditor													cuentaPagarPropertyEditor;

	private ProveedorPropertyEditor														proveedorPropertyEditor;
	@Autowired
	private StringPropertyEditor															stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(CuentaPagar.class, cuentaPagarPropertyEditor);
		binder.registerCustomEditor(Proveedor.class, proveedorPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarCuentaPagar", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/cuentasxpagar/ListarCuentasXPagar";
	}

	@RequestMapping(value = "/ListarCuentaPagarConsulta", method = RequestMethod.GET)
	public String listarConsulta(ModelMap model) {
		return "views/cuentasxpagar/ListarCuentasXPagarConsulta";
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarCuentaPagarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFinal, @RequestParam Long idProveedor, @RequestParam String estado) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFinal, proveedor, usuarioSesion.getEmpresa(), estado);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarCuentaPagarEstadoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarEstadoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idProveedor, @RequestParam String estado) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		DataTableDelimitador query = DelimitadorBuilderEstado.get(request, proveedor, usuarioSesion.getEmpresa(), estado);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	private static class DelimitadorBuilderEstado {

		static DataTableDelimitador get(HttpServletRequest request, Proveedor proveedor, Empresa empresa, String estado) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "CuentaPagar");

			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (proveedor != null) {
				delimitador.addFiltro(new JqGridFilter("proveedor.id", "'" + proveedor.getId().toString() + "'", "="));
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

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Proveedor proveedor, Empresa empresa, String estado) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "CuentaPagar");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (proveedor != null) {
				delimitador.addFiltro(new JqGridFilter("proveedor.id", "'" + proveedor.getId().toString() + "'", "="));
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

	/**
	 * Descarga de manuales de usuario de acuerdo con su perfil
	 * @param request
	 * @param response
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param idProveedorParam
	 * @param estadoParam
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/DescargarDetalleTotalCuentasXPagarAjax.do", method = RequestMethod.GET)
	public void descargarDetalleTotalFacturasPagarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Long idProveedorParam, @RequestParam String estadoParam) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedorParam);

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<CuentaPagar> cuentaPagars = cuentaPagarBo.cuentasPorPagarbyFechasAndEmpresaAndClienteAndEstado(fechaInicio, fechaFin, usuario.getEmpresa(), proveedor, estadoParam);

		String nombreArchivo = "cuentaxPagar.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelCuentaPagar(cuentaPagars);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	@RequestMapping(value = "/DescargarDetalleTotalCuentasXPagarEstadoAjax.do", method = RequestMethod.GET)
	public void descargarDetalleTotalFacturasPagarEstadoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idProveedorParam, @RequestParam String estadoParam) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedorParam);

		// Se buscan las facturas
		Collection<CuentaPagar> cuentaPagars = cuentaPagarBo.cuentasPorPagarbyEmpresaAndClienteAndEstado(usuario.getEmpresa(), proveedor, estadoParam);

		String nombreArchivo = "cuentaxPagar.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelCuentaPagar(cuentaPagars);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelCuentaPagar(Collection<CuentaPagar> cuentaPagar) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("#cuenta", "Fecha Emision", "# Documento", "Proveedor", "Total", "Saldo", "Abono");
		new SimpleExporter().gridExport(headers, cuentaPagar, "id, created_atSTR, consecutivo, proveedorSTR, total,totalSaldo,totalAbono", baos);
		return baos;
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarCuentaPagarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute CuentaCobrar cuentaPagar, BindingResult result, SessionStatus status) throws Exception {
		try {
			CuentaPagarCommand cuentaPagarCommand = new CuentaPagarCommand(cuentaPagarBo.buscar(cuentaPagar.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", cuentaPagarCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Enviar Correo de las cuentas por cobrar
	 * @param request
	 * @param response
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param idProveedorParam
	 * @param estadoParam
	 * @param correoAlternativo
	 * @param total
	 * @param saldo
	 * @param abono
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnvioDetalleCuentasXPagarCorreoAjax.do", method = RequestMethod.GET)
	@ResponseBody
	public RespuestaServiceValidator<?> enviarCorreoCuentasXPagarAjax(HttpServletRequest request, HttpServletResponse response,ModelMap model, @ModelAttribute String datos,BindingResult result,@RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Long idProveedorParam, @RequestParam String estadoParam, @RequestParam String correoAlternativo, @RequestParam String total, @RequestParam String saldo, @RequestParam String abono) throws IOException, Exception {
		RespuestaServiceValidator<?> respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Proveedor proveedor = proveedorBo.buscar(idProveedorParam);

			// Se buscan las facturas
			Date fechaInicio = Utils.parseDate(fechaInicioParam);
			Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
			Collection<CuentaPagar> cuentaPagars = cuentaPagarBo.cuentasPorPagarbyFechasAndEmpresaAndClienteAndEstado(fechaInicio, fechaFin, usuario.getEmpresa(), proveedor, estadoParam);
			// Se prepara el excell
			ByteArrayOutputStream baos = createExcelCuentaPagar(cuentaPagars);

			Collection<Attachment> attachments = createAttachments(attachment("ComprasPendientes", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

			// Se prepara el correo
			String from = "cuentasxpagar@facturaemprendesoftcr.com";
//			if (usuario.getEmpresa().getAbreviaturaEmpresa() != null) {
//				if (!usuario.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
//					from = usuario.getEmpresa().getAbreviaturaEmpresa() + "_ComprasPendientes" + "_No_Reply@facturaemprendesoftcr.com";
//				}
//			}
			String nombre = usuario.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? usuario.getEmpresa().getNombre() : usuario.getEmpresa().getNombreComercial();
			nombre = nombre.length() > 50 ?nombre.substring(0,50):nombre;

			String subject = nombre + "Compras Pendientes de cancelar dentro del rango de fechas: " + fechaInicioParam + " al " + fechaFinParam ;

			ArrayList<String> listaCorreos = new ArrayList<>();

			listaCorreos.add(correoAlternativo);

			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("nombreEmpresa", usuario.getEmpresa().getNombre());
			modelEmail.put("fechaInicial", Utils.getFechaStr(fechaInicio));
			modelEmail.put("fechaFinal", Utils.getFechaStr(fechaFin));
			modelEmail.put("total", total);
			modelEmail.put("abono", abono);
			modelEmail.put("saldo", saldo);

			Boolean resultado = correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/cuentasxpagar.vm", modelEmail);
			if (resultado.equals(Boolean.TRUE)) {
				log.info("Enviado correctamente el correo {}", new Date());
				System.out.println("Enviado correctamente el correo");
			} else {
				log.error("** Error  Enviado correo: " + " fecha " + new Date());
				System.out.println("No enviado correctamente el correo");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
			}

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

		return respuestaServiceValidator;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/EnvioDetalleCuentasXPagarCorreoEstadoAjax.do", method = RequestMethod.GET)
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoCuentasXPagarEstadoAjax(HttpServletRequest request, HttpServletResponse response,ModelMap model, @ModelAttribute String datos,BindingResult result, @RequestParam Long idProveedorParam, @RequestParam String estadoParam, @RequestParam String correoAlternativo, @RequestParam String total, @RequestParam String saldo, @RequestParam String abono) throws IOException, Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Proveedor proveedor = proveedorBo.buscar(idProveedorParam);

			// Se buscan las facturas
			Collection<CuentaPagar> cuentaPagars = cuentaPagarBo.cuentasPorPagarbyEmpresaAndClienteAndEstado(usuario.getEmpresa(), proveedor, estadoParam);
			// Se prepara el excell
			ByteArrayOutputStream baos = createExcelCuentaPagar(cuentaPagars);

			Collection<Attachment> attachments = createAttachments(attachment("ComprasPendientes", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

			// Se prepara el correo
			String from = "cuentasxpagar@facturaemprendesoftcr.com";
//			if (usuario.getEmpresa().getAbreviaturaEmpresa() != null) {
//				if (!usuario.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
//					from = usuario.getEmpresa().getAbreviaturaEmpresa() + "_ComprasPendientes" + "_No_Reply@facturaemprendesoftcr.com";
//				}
//			}
			String nombre = usuario.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? usuario.getEmpresa().getNombre() : usuario.getEmpresa().getNombreComercial();
			nombre = nombre.length() > 50 ?nombre.substring(0,50):nombre;
			String subject = nombre+ " Compras Pendientes de cancelar ";

			ArrayList<String> listaCorreos = new ArrayList<>();

			listaCorreos.add(correoAlternativo);

			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("nombreEmpresa", usuario.getEmpresa().getNombre());
			modelEmail.put("total", total);
			modelEmail.put("abono", abono);
			modelEmail.put("saldo", saldo);

			Boolean resultado = correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/cuentasxpagar.vm", modelEmail);
			if (resultado.equals(Boolean.TRUE)) {
				log.info("Enviado correctamente el correo {}", new Date());
				System.out.println("Enviado correctamente el correo");
			} else {
				log.error("** Error  Enviado correo: " + " fecha " + new Date());
				System.out.println("No enviado correctamente el correo");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
			}

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
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

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.Vendedor;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.emprendesoftcr.web.command.FacturaEsperaCommand;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.FechaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.VendedorPropertyEditor;
import com.google.common.base.Function;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class DetalleController {

	private static final Function<Object, FacturaEsperaCommand>		TO_COMMAND					= new Function<Object, FacturaEsperaCommand>() {

																																											@Override
																																											public FacturaEsperaCommand apply(Object f) {
																																												return new FacturaEsperaCommand((Detalle) f);
																																											};
																																										};
	private static final Function<Object, DetalleFacturaCommand>	TO_COMMAND_DETALLE	= new Function<Object, DetalleFacturaCommand>() {

																																											@Override
																																											public DetalleFacturaCommand apply(Object f) {
																																												return new DetalleFacturaCommand((Detalle) f);
																																											};
																																										};

	@Autowired
	private DataTableBo																						dataTableBo;

	@Autowired
	private DetalleBo																							detalleBo;

	@Autowired
	private UsuarioBo																							usuarioBo;

	@Autowired
	private ClienteBo																							clienteBo;

	@Autowired
	private CorreosBo																							correosBo;

	@Autowired
	private EmpresaPropertyEditor																	empresaPropertyEditor;

	@Autowired
	private ClientePropertyEditor																	clientePropertyEditor;

	@Autowired
	private VendedorPropertyEditor																vendedorPropertyEditor;

	@Autowired
	private StringPropertyEditor																	stringPropertyEditor;

	@Autowired
	private FechaPropertyEditor																		fechaPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
		binder.registerCustomEditor(Date.class, fechaPropertyEditor);
	}

	/**
	 * Listado de facturas anuladas y facturadas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListaDetallesImpuestoServicio", method = RequestMethod.GET)
	public String listaFacturas(ModelMap model) {
		return "views/detalle/ListaDetallesImpuestoServicio";
	}

	@RequestMapping(value = "/ListaDetallesxArticulo", method = RequestMethod.GET)
	public String listaFacturasxCodigo(ModelMap model) {
		return "views/detalle/ListaDetallesxCodigo";
	}

	/**
	 * Listado de los impuestos de servicio 10%
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@RequestMapping(value = "/ListaDetallesImpuestoServicioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, usuarioSesion.getEmpresa());

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Enviar Correo
	 * @param request
	 * @param response
	 * @param fechaInicialParam
	 * @param fechaFinalParam
	 * @param codigoParam
	 * @param tipoDocumentoParam
	 * @param idClienteParam
	 * @param correoAlternativo
	 * @param totalDescuentoGeneral
	 * @param totalImpuestoGeneral
	 * @param totalGeneral
	 * @param codigo
	 * @param descripcion
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/EnvioDetalleFacturasXCodigoCorreoAjax.do", method = RequestMethod.GET)
	public void envioDetalleFacturasXCodigoCorreoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicialParam, @RequestParam String fechaFinalParam, @RequestParam String codigoParam, @RequestParam String tipoDocumentoParam, @RequestParam String idClienteParam, @RequestParam String correoAlternativo, @RequestParam String totalDescuentoGeneral, @RequestParam String totalImpuestoGeneral, @RequestParam String totalGeneral,  @RequestParam String descripcion) throws IOException, Exception {
		Boolean isVededor = false;
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
			isVededor = true;
		}
		Cliente cliente = clienteBo.buscarPorCedulaYEmpresa(idClienteParam, usuario.getEmpresa());
		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicialParam);
		Date fechaFinal = Utils.parseDate(fechaFinalParam);
		if (fechaFinal == null) {
			fechaFinal = new Date(System.currentTimeMillis());
		}
		if (fechaFinal != null && fechaFinal != null) {
			fechaFinal = Utils.sumarDiasFecha(fechaFinal, 1);
		}
		Collection<Detalle> detalles = detalleBo.facturasRangoEstado(Constantes.FACTURA_ESTADO_FACTURADO, fechaInicio, fechaFinal, codigoParam, tipoDocumentoParam, cliente, usuario.getEmpresa(), isVededor == true ? usuario : null);
		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelVentasXCodigo(detalles);
		Collection<Attachment> attachments = createAttachments(attachment("ventasXCodigo", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));
		// Se prepara el correo
		String from = "FacturasEmitidas@emprendesoftcr.com";
		if (usuario.getEmpresa().getAbreviaturaEmpresa() != null) {
			if (!usuario.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
				from = usuario.getEmpresa().getAbreviaturaEmpresa() + "_FacturasCodigo" + "_No_Reply@emprendesoftcr.com";
			}
		}
		String subject = "Ventas por articulo dentro del rango de fechas: " + fechaInicialParam + " al " + fechaFinalParam;
		ArrayList<String> listaCorreos = new ArrayList<>();
		listaCorreos.add(correoAlternativo);
		Map<String, Object> modelEmail = new HashMap<>();
		modelEmail.put("fechaInicial", fechaInicialParam);
		modelEmail.put("fechaFinal", fechaFinalParam);
		modelEmail.put("total", totalGeneral);
		modelEmail.put("impuesto", totalImpuestoGeneral);
		modelEmail.put("descuento", totalDescuentoGeneral);
		modelEmail.put("codigo", codigoParam);
		modelEmail.put("descripcion", descripcion);
		correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_VENTA_POR_CODIGO, modelEmail);
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	/**
	 * Descarga de manuales de usuario de acuerdo con su perfil
	 * @param request
	 * @param response
	 * @param fechaInicialParam
	 * @param fechaFinalParam
	 * @param codigoParam
	 * @param tipoDocumentoParam
	 * @param idClienteParam
	 * @throws IOException
	 */
	@RequestMapping(value = "/DescargarDetallexCodigoAjax.do", method = RequestMethod.GET)
	public void descargarDetallexCodigoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicialParam, @RequestParam String fechaFinalParam, @RequestParam String codigoParam, @RequestParam String tipoDocumentoParam, @RequestParam String idClienteParam) throws IOException {
		Boolean isVededor = false;
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
			isVededor = true;
		}
		Cliente cliente = clienteBo.buscarPorCedulaYEmpresa(idClienteParam, usuario.getEmpresa());
		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicialParam);
		Date fechaFinal = Utils.parseDate(fechaFinalParam);
		if (fechaFinal == null) {
			fechaFinal = new Date(System.currentTimeMillis());
		}
		if (fechaFinal != null && fechaFinal != null) {
			fechaFinal = Utils.sumarDiasFecha(fechaFinal, 1);
		}

	
		Collection<Detalle> detalles = detalleBo.facturasRangoEstado(Constantes.FACTURA_ESTADO_FACTURADO, fechaInicio, fechaFinal, codigoParam, tipoDocumentoParam, cliente, usuario.getEmpresa(), isVededor == true ? usuario : null);
		String nombreArchivo = "VentasXProductos.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");
		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelVentasXCodigo(detalles);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelVentasXCodigo(Collection<Detalle> detalles) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Usuario", "Fecha Emision", "Codigo", "Descripcion", "Clave", "# Documento", "Cedula", "Cliente", "Cantidad", "Precio Unitario", "Monto Total", "Descuento", "%Impuesto", "Impuestos", "Total", "Tipo Moneda", "Tipo Cambio");
		new SimpleExporter().gridExport(headers, detalles, "factura.usuarioCreacion.nombreUsuario, factura.fechaEmisionSTR,codigo,descripcion,factura.clave, factura.numeroConsecutivo,factura.cliente.cedula, factura.nombreCliente, cantidadSTR, precioUnitarioSTR, montoTotalSTR, montoDescuentoSTR,impuestoSTR, montoImpuestoSTR, montoTotalLinea,factura.codigoMoneda, factura.tipoCambio", baos);
		return baos;
	}

	@RequestMapping(value = "/ListaDetallesxCodigoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable ListaDetallesxCodigo(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String codigo, @RequestParam String tipoDocumento, @RequestParam String idCliente) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscarPorCedulaYEmpresa(idCliente, usuarioSesion.getEmpresa());
		DataTableDelimitador query = DelimitadorBuilderXCodigo.get(request, fechaInicio, fechaFin, usuarioSesion.getEmpresa(), codigo, tipoDocumento, cliente, usuarioBo);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND_DETALLE);
	}

	private static class DelimitadorBuilderXCodigo {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Empresa empresa, String codigo, String tipoDocumento, Cliente cliente, UsuarioBo usuarioBo) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Detalle");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_PROFORMAS.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_ANULADA.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("factura.empresa.id", "'" + empresa.getId().toString() + "'", "="));
			delimitador.addFiltro(new JqGridFilter("codigo", "'" + codigo.toString() + "'", "="));

			if (cliente != null) {
				delimitador.addFiltro(new JqGridFilter("factura.cliente.id", "'" + cliente.getId().toString() + "'", "="));
			}
			if (request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
				Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
				delimitador.addFiltro(new JqGridFilter("factura.usuarioCreacion.id", "'" + usuario.getId().toString() + "'", "="));
			}
			if (tipoDocumento != null) {
				if (!tipoDocumento.equals(Constantes.EMPTY)) {
					if (!tipoDocumento.equals(Constantes.COMBO_TODOS)) {
						delimitador.addFiltro(new JqGridFilter("factura.tipoDoc", "'" + tipoDocumento.toString() + "'", "="));
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

				delimitador.addFiltro(new JqGridFilter("factura.fechaEmision", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("factura.fechaEmision", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Empresa empresa) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Detalle");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_PROFORMAS.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_ANULADA.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("factura.empresa.id", "'" + empresa.getId().toString() + "'", "="));
			delimitador.addFiltro(new JqGridFilter("codigo", "'" + Constantes.CODIGO_ARTICULO_IMPUESTO_SERVICIO.toString() + "'", "="));

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

				delimitador.addFiltro(new JqGridFilter("factura.fechaEmision", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("factura.fechaEmision", fin, "dateFinal<="));
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

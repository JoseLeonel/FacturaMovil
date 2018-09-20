package com.emprendesoftcr.web.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.ProveedorBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.validator.CompraFormValidator;
import com.emprendesoftcr.web.command.CompraCommand;
import com.emprendesoftcr.web.command.CompraEsperaCommand;
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

	private static final Function<Object, CompraEsperaCommand>	TO_COMMAND	= new Function<Object, CompraEsperaCommand>() {

																																						@Override
																																						public CompraEsperaCommand apply(Object f) {
																																							return new CompraEsperaCommand((Compra) f);
																																						};
																																					};

	@Autowired
	private DataTableBo																					dataTableBo;

	@Autowired
	private UsuarioBo																						usuarioBo;

	@Autowired
	private ProveedorBo																					proveedorBo;

	@Autowired
	private CompraBo																						compraBo;

	@Autowired
	private EmpresaPropertyEditor																empresaPropertyEditor;

	@Autowired
	private ProveedorPropertyEditor															proveedorPropertyEditor;

	@Autowired
	private CompraFormValidator																	compraFormValidator;

	@Autowired
	private StringPropertyEditor																stringPropertyEditor;

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
			compraFormValidator.validate(compraCommand, result);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (!compraCommand.getFormaPago().equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
				compraCommand.setFechaCredito(null);
			}
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (compraCommand.getConsecutivo().equals(Constantes.EMPTY)) {
				result.rejectValue("consecutivo", "error.compra.existe.consecutivo");
			}
			Compra compraBD = compraBo.findByConsecutivoAndEmpresa(compraCommand.getConsecutivo(), usuarioSesion.getEmpresa());
			if (compraBD != null) {
				if (!compraBD.getId().equals(compraCommand.getId())) {
					result.rejectValue("consecutivo", "error.compra.existe.consecutivo");
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
	public RespuestaServiceDataTable listarComprasAjax(HttpServletRequest request, HttpServletResponse response,@RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idProveedor) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, proveedor, usuarioSesion.getEmpresa());

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

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Proveedor proveedor, Empresa empresa) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Compra");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_PENDIENTE.toString() + "'", "<>"));
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

				DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT5);

				inicio = dateFormat.format(fechaInicio);
				fin = dateFormat.format(fechaFinal);

				delimitador.addFiltro(new JqGridFilter("created_at", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("created_at", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

	static class RESPONSES {

		private static class OK {

			private static class COMPRA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class COMPRA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.categoria.noExiste");
			}
		}
	}

}

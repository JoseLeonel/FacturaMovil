package com.emprendesoftcr.web.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.ProveedorArticuloBo;
import com.emprendesoftcr.Bo.ProveedorBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.command.ArticuloProveedorCommand;
import com.emprendesoftcr.web.command.ArticuloVendidoCommand;
import com.emprendesoftcr.web.command.ProveedorArticuloCommand;
import com.emprendesoftcr.web.propertyEditor.ArticuloPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorArticuloPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Control de los departamentos de cada articulo CategoriasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class ProveedorArticuloController {

	private static final Function<Object, ProveedorArticuloCommand>	TO_COMMAND	= new Function<Object, ProveedorArticuloCommand>() {

																																								@Override
																																								public ProveedorArticuloCommand apply(Object f) {
																																									return new ProveedorArticuloCommand((ProveedorArticulo) f);
																																								};
																																							};

	@Autowired
	private DataTableBo																							dataTableBo;

	@Autowired
	private ArticuloBo																							articuloBo;

	@Autowired
	private UsuarioBo																								usuarioBo;

	@Autowired
	private ProveedorBo																							proveedorBo;

	@Autowired
	private ProveedorArticuloBo																			proveedorArticuloBo;

	@Autowired
	private ProveedorArticuloPropertyEditor													proveedorArticuloPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor																		empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor																		stringPropertyEditor;

	@Autowired
	private ProveedorPropertyEditor																	proveedorPropertyEditor;

	@Autowired
	private ArticuloPropertyEditor																	articuloPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(ProveedorArticulo.class, proveedorArticuloPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(ProveedorArticulo.class, proveedorPropertyEditor);
		binder.registerCustomEditor(Articulo.class, articuloPropertyEditor);
		binder.registerCustomEditor(Proveedor.class, proveedorPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarProveedorArticulo.do", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/proveedorArticulo/ListarProveedorArticulo";
	}

	@RequestMapping(value = "/ArticuloPorProveedores", method = RequestMethod.GET)
	public String articuloPorProveedores(ModelMap model) {
		return "views/articulos/ArticulosXProveedor";
	}

	/**
	 * Lista de articulos de un proveedor
	 * @param request
	 * @param response
	 * @param idProveedor
	 * @return
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ArticulosPorOtrosProveedorCodigo.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable ArticulosPorProveedorCodigo(HttpServletRequest request, HttpServletResponse response, @RequestParam("idProveedor") Integer idProveedor, @RequestParam("codigo") String codigo) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		@SuppressWarnings("unused")
		RespuestaServiceDataTable respuestaServiceDataTable = new RespuestaServiceDataTable();

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Map<String, Object>> listaObjetos = proveedorArticuloBo.articuloPorProveedor(idProveedor, codigo, usuarioSesion.getEmpresa().getId());

		ArrayList<?> arrayList = new ArrayList<Object>();
		arrayList = (ArrayList<?>) listaObjetos;
		JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
		ArrayList<ArticuloProveedorCommand> lista = new ArrayList<>();
		Gson gson = new Gson();
		if (jsonArray1 != null) {
			for (int i = 0; i < jsonArray1.size(); i++) {
				ArticuloProveedorCommand articuloProveedorCommand = gson.fromJson(jsonArray1.get(i).toString(), ArticuloProveedorCommand.class);
				lista.add(articuloProveedorCommand);
			}
		}

		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(lista);
		return respuestaService;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ArticulosCantidadVendido.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable ArticulosCantidadVendido(HttpServletRequest request, HttpServletResponse response, @RequestParam("fechaInicial") String fechaInicial, @RequestParam("fechaFinal") String fechaFinal, @RequestParam("idCodigo") String idCodigo) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		@SuppressWarnings("unused")
		RespuestaServiceDataTable respuestaServiceDataTable = new RespuestaServiceDataTable();
		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicial);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinal), true);
		if (fechaInicio != null) {
			if (fechaFin != null) {
				fechaFin = Utils.sumarDiasFecha(fechaFin, 0);
			}
		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		DateFormat dateFormat2 = new SimpleDateFormat(Constantes.DATE_FORMAT8);
		fechaInicial = dateFormat1.format(fechaInicio);
		fechaFinal = dateFormat2.format(fechaFin);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Map<String, Object>> listaObjetos = proveedorArticuloBo.articuloCantidadVendido(idCodigo, usuarioSesion.getEmpresa().getId(), fechaInicial, fechaFinal);

		ArrayList<?> arrayList = new ArrayList<Object>();
		arrayList = (ArrayList<?>) listaObjetos;
		JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
		ArrayList<ArticuloVendidoCommand> lista = new ArrayList<>();
		Gson gson = new Gson();
		if (jsonArray1 != null) {
			for (int i = 0; i < jsonArray1.size(); i++) {
				ArticuloVendidoCommand articuloVendidoCommand = gson.fromJson(jsonArray1.get(i).toString(), ArticuloVendidoCommand.class);
				lista.add(articuloVendidoCommand);
			}
		}

		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(lista);
		return respuestaService;

	}

	/**
	 * Lista de los archivos que no son del proveedor
	 * @param request
	 * @param response
	 * @param idProveedor
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarArticulosPorProveedor.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable ListarArticulosPorProveedor(HttpServletRequest request, HttpServletResponse response, @RequestParam("idProveedor") Integer idProveedor) {

		@SuppressWarnings("unused")
		RespuestaServiceDataTable respuestaServiceDataTable = new RespuestaServiceDataTable();

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Map<String, Object>> listaObjetos = proveedorArticuloBo.articuloPorProveedor(idProveedor);

		ArrayList<?> arrayList = new ArrayList<Object>();
		arrayList = (ArrayList<?>) listaObjetos;
		JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
		ArrayList<ArticuloProveedorCommand> lista = new ArrayList<>();
		Gson gson = new Gson();
		if (jsonArray1 != null) {
			for (int i = 0; i < jsonArray1.size(); i++) {
				ArticuloProveedorCommand articuloProveedorCommand = gson.fromJson(jsonArray1.get(i).toString(), ArticuloProveedorCommand.class);
				lista.add(articuloProveedorCommand);
			}
		}

		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(lista);
		return respuestaService;

	}

	/**
	 * Listar por medio de json los articulos de un proveedor
	 * @param request
	 * @param response
	 * @param idProveedor
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarProveedorArticuloAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idProveedor) {

		DataTableDelimitador delimitadores = null;
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		delimitadores = new DataTableDelimitador(request, "ProveedorArticulo");
		delimitadores.addFiltro(new JqGridFilter("proveedor.id", "'" + proveedor.getId() + "'", "="));
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar un nuevo articulo a un proveedor
	 * @param request
	 * @param model
	 * @param proveedorArticulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarProveedorArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarProveedorArticuloAjax(HttpServletRequest request, ModelMap model, @ModelAttribute ProveedorArticulo proveedorArticulo, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			if (proveedorArticulo.getArticulo() == null) {
				result.rejectValue("codigo", "error.proveedorArticulo.codigo..viene.vacio");
			}

			if (proveedorArticulo.getProveedor() == null) {

				result.rejectValue("codigo", "error.proveedorArticulo.proveedor.esta.vacio");

			}

			if (proveedorArticulo.getCosto() == null) {

				result.rejectValue("costo", "error.proveedorArticulo.costo.ceros");

			} else {
				if (proveedorArticulo.getCosto() == Constantes.ZEROS_DOUBLE) {
					result.rejectValue("costo", "error.proveedorArticulo.costo.ceros");

				}
			}

			ProveedorArticulo proveedorArticuloBd = null;
			proveedorArticuloBd = proveedorArticuloBo.findByCodigo(proveedorArticulo.getArticulo().getCodigo(), proveedorArticulo.getProveedor());
			if (proveedorArticuloBd != null) {
				result.rejectValue("codigo", "error.proveedorArticulo.codigo.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			proveedorArticulo.setCreated_at(new Date());
			proveedorArticulo.setUpdated_at(new Date());
			proveedorArticuloBo.agregar(proveedorArticulo);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorArticulo.agregar.correctamente", proveedorArticulo);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar el costo de articulo
	 * @param request
	 * @param model
	 * @param proveedorArticulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/modificarProveedorArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificarProveedorArticuloAjax(HttpServletRequest request, ModelMap model, @ModelAttribute ProveedorArticulo proveedorArticulo, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			if (proveedorArticulo.getId() == null) {
				result.rejectValue("codigo", "error.proveedorArticulo.codigo.nullo.existe");
			}

			if (proveedorArticulo.getArticulo() == null) {
				result.rejectValue("codigo", "error.proveedorArticulo.codigo..viene.vacio");
			}

			if (proveedorArticulo.getProveedor() == null) {

				result.rejectValue("codigo", "error.proveedorArticulo.proveedor.esta.vacio");

			}

			if (proveedorArticulo.getCosto() == null) {

				result.rejectValue("costo", "error.proveedorArticulo.costo.ceros");

			} else {
				if (proveedorArticulo.getCosto() == Constantes.ZEROS_DOUBLE) {
					result.rejectValue("costo", "error.proveedorArticulo.costo.ceros");

				}
			}

			ProveedorArticulo proveedorArticuloBd = null;
			proveedorArticuloBd = proveedorArticuloBo.findByCodigo(proveedorArticulo.getArticulo().getCodigo(), proveedorArticulo.getProveedor());
			if (proveedorArticuloBd == null) {
				result.rejectValue("codigo", "error.proveedorArticulo.codigo.no.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			proveedorArticuloBd.setUpdated_at(new Date());
			proveedorArticuloBd.setCosto(proveedorArticulo.getCosto());
			proveedorArticuloBo.modificar(proveedorArticulo);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorArticulo.agregar.correctamente", proveedorArticulo);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Eliminar el articulo de un proveedor
	 * @param request
	 * @param response
	 * @param result
	 * @param status
	 * @param idProveedorArticulo
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/EliminarProveedorArticuloAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator eliminarAjax(HttpServletRequest request, ModelMap model, @ModelAttribute ProveedorArticulo proveedorArticulo, BindingResult result, SessionStatus status, @RequestParam Long idProveedorArticulo) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			ProveedorArticulo proveedorArticuloBd = null;
			proveedorArticuloBd = proveedorArticuloBo.findById(idProveedorArticulo);
			if (proveedorArticuloBd == null) {
				result.rejectValue("codigo", "error.proveedorArticulo.codigo.no.existe");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			proveedorArticuloBo.eliminar(proveedorArticuloBd);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorArticulo.eliminado.correctamente", null);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findArticuloProveedorByCodigojax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator listarAjax(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, HttpServletResponse response, @RequestParam String codigoArticulo, @RequestParam Long idProveedor, BindingResult result, SessionStatus status) {
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBD = articuloBo.buscarPorCodigoYEmpresa(codigoArticulo, usuarioSesion.getEmpresa());
			Proveedor proveedor = proveedorBo.buscar(idProveedor);
			ProveedorArticulo proveedorArticuloBD = null;
			if (proveedor != null && articuloBD != null) {
				proveedorArticuloBD = proveedorArticuloBo.findByCodigo(articuloBD.getCodigo(), proveedor);
			}
			ArticuloCommand articuloCommand = articuloBD == null ? null : new ArticuloCommand(articuloBD);
			if (articuloCommand == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.codigo.no.existe", result.getAllErrors());
			}
			if (proveedorArticuloBD != null) {
				articuloCommand.setCosto(proveedorArticuloBD.getCosto() != null ? proveedorArticuloBD.getCosto() : articuloBD.getCosto());
			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", articuloCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CATEGORIA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorArticulo.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("proveedorArticulo.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CATEGORIA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.categoria.noExiste");
			}
		}
	}

}

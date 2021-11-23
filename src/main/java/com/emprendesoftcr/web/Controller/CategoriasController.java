package com.emprendesoftcr.web.Controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CategoriaBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.CategoriaCommand;
import com.emprendesoftcr.web.command.ParametrosPaginacion;
import com.emprendesoftcr.web.propertyEditor.CategoriaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Control de los departamentos de cada articulo CategoriasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@CrossOrigin
@Controller
public class CategoriasController {

	private static final Function<Object, CategoriaCommand>	TO_COMMAND	= new Function<Object, CategoriaCommand>() {

																																				@Override
																																				public CategoriaCommand apply(Object f) {
																																					return new CategoriaCommand((Categoria) f);
																																				};
																																			};

	@Autowired
	private DataTableBo																			dataTableBo;

	@Autowired
	private CategoriaBo																			categoriaBo;

	@Autowired
	private UsuarioBo																				usuarioBo;

	@Autowired
	private CategoriaPropertyEditor													categoriaPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor														empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor														stringPropertyEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Categoria.class, categoriaPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/TotalesCategoriaXArticulo.do", method = RequestMethod.GET)
	public String totalesCategoriaXArticulo(ModelMap model) {
		return "views/categoria/TotalesCategoriaXArticulos";
	}

	/**
	 * Listar de las categorias
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarCategorias", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/categoria/ListarCategorias";
	}

	@RequestMapping(value = "/ListarCategoriasRestaurante.do", method = RequestMethod.GET)
	public String listarRestaurante(ModelMap model) {
		return "views/categoria/ListarCategoriasRestaurante";
	}

	/**
	 * Listar Ajax de las categorias
	 * @param request
	 * @param responselistarCategoriasAjax
	 * @return
	 */
	@SuppressWarnings("all")
	@Cacheable(value = "categoriaCache")
	@RequestMapping(value = "/ListarCategoriasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		return listarCategoriasAjax(request, response);
	}

	private RespuestaServiceDataTable<?> listarCategoriasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Categoria");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}



	@RequestMapping(value = "/movil/ListarCategoriasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Collection<Categoria> listarMovilAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Integer idEmpresa) {

		return categoriaBo.findByEmpresaAll(idEmpresa);
	}

	@SuppressWarnings("all")
	@Cacheable(value = "categoriaCache")
	@RequestMapping(value = "/ListarPaginacionCategoriasAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarArticulosAjax(HttpServletRequest request, ModelMap model, @ModelAttribute ParametrosPaginacion parametrosPaginacion) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Categoria");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (delimitadores.getColumnData() == null && usuarioSesion.getEmpresa().getOrdenaCategoriaArticulos().equals(1)) {
			// Se ordena por prioridad por defecto se crearon en 9999
			delimitadores.setColumnData("prioridad, id");
			delimitadores.setColumnOrderDir("asc");
		}

		JqGridFilter categoriaFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(categoriaFilter);

		delimitadores.setLength(parametrosPaginacion.getCantidadPorPagina());

		delimitadores.setStart(parametrosPaginacion.getPaginaActual());

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Categorias activas por empresa
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@Cacheable(value = "categoriaCache")
	@RequestMapping(value = "/ListarCategoriasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Categoria");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		String nombreUsuario = request.getUserPrincipal().getName();
		dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Incluir una categoria a una empresa , las categorias es la division de los articulos
	 * @param request
	 * @param model
	 * @param idEmpresa
	 * @param categoria
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@CacheEvict(value = "categoriaCache", allEntries = true)
	@RequestMapping(value = "/AgregarCategoriaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

			return categoriaBo.agregar(request, categoria, result, usuario);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	
	/**
	 * Modificar una categoria
	 */
	@SuppressWarnings("all")
	@CacheEvict(value = "categorialocalCache", allEntries = true)
	@RequestMapping(value = "/ModificarCategoriaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("categoria.no.modificado", result.getAllErrors());
			}
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			return categoriaBo.modificar(request, categoria, result, usuario);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	

	/**
	 * Mostrar la categoria
	 * @param request
	 * @param model
	 * @param categoria
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarCategoriaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) throws Exception {
		try {
		
			return categoriaBo.mostrar(request, categoria, result);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	
	
	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CATEGORIA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CATEGORIA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.categoria.noExiste");
			}
		}
	}

}

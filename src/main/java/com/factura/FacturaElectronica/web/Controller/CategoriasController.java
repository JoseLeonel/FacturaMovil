package com.factura.FacturaElectronica.web.Controller;

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

import com.factura.FacturaElectronica.Bo.CategoriaBo;
import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Bo.EmpresaBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.DataTableFilter;
import com.factura.FacturaElectronica.Utils.JqGridFilter;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Categoria;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.web.command.CategoriaCommand;
import com.factura.FacturaElectronica.web.componentes.CategoriaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.EmpresaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Control de los departamentos de cada articulo CategoriasController.
 * 
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class CategoriasController {

	private static final Function<Object, CategoriaCommand> TO_COMMAND = new Function<Object, CategoriaCommand>() {

		@Override
		public CategoriaCommand apply(Object f) {
			return new CategoriaCommand((Categoria) f);
		};
	};

	
	@Autowired
	private DataTableBo dataTableBo;

	@Autowired
	private CategoriaBo categoriaBo;

	@Autowired
	private EmpresaBo empresaBo;

	
	@Autowired
	private UsuarioBo usuarioBo;

	@Autowired
	private CategoriaPropertyEditor categoriaPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Categoria.class, categoriaPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Listar de las categorias
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarCategorias", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/categoria/ListarCategorias";
	}

	/**
	 * Listar Ajax de las categorias
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarCategoriasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Categoria");
		  if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			    String nombreUsuario=request.getUserPrincipal().getName();
			    JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario); 
				delimitadores.addFiltro(dataTableFilter);
		    }
		

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Categorias activas por empresa
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarCategoriasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer idEmpresa) {

		Empresa empresa = idEmpresa != null ? empresaBo.buscar(idEmpresa) : null;
		DataTableDelimitador delimitadores = null;
		if (empresa != null) {
			delimitadores = new DataTableDelimitador(request, "Categoria");
			JqGridFilter dataTableFilter = new JqGridFilter("estado",
					"'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
			dataTableFilter = new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);

		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Incluir una categoria a una empresa , las categorias es la division de los
	 * articulos
	 * 
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
	@RequestMapping(value = "/AgregarCategoriaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model,
			@ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Categoria categoriaBd = categoriaBo.buscarPorDescripcionYEmpresa(categoria.getDescripcion(),
					categoria.getEmpresa());
			if (categoriaBd != null) {
				result.rejectValue("descripcion", "error.categoria.descripcion.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion",
						result.getAllErrors());
			}
			categoria.setCreated_at(new Date());
			categoria.setUpdated_at(new Date());
			categoria.setEstado(Constantes.ESTADO_ACTIVO);
			categoriaBo.agregar(categoria);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.agregar.correctamente", categoria);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una categoria
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarCategoriaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model,
			@ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("categoria.no.modificado",
						result.getAllErrors());
			}
			Categoria categoriaBD = categoriaBo.buscar(categoria.getId());

			if (categoriaBD == null) {
				return RESPONSES.ERROR.CATEGORIA.NO_EXISTE;
			} else {
				Categoria categoriaValidar = null;
				if (!categoria.getDescripcion().equals(categoriaBD.getDescripcion())) {
					categoriaValidar = categoriaBo.buscarPorDescripcionYEmpresa(categoria.getDescripcion(),
							categoria.getEmpresa());
					if (categoriaValidar != null) {
						result.rejectValue("descripcion", "error.categoria.descripcion.existe");
					}
				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion",
							result.getAllErrors());
				}
				categoriaBD.setDescripcion(categoria.getDescripcion());
				categoriaBD.setUpdated_at(new Date());
				categoriaBo.modificar(categoriaBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.modificado.correctamente",
						categoriaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar la categoria
	 * 
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
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model,
			@ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) throws Exception {
		try {
			CategoriaCommand categoriaCommand = new CategoriaCommand(categoriaBo.buscar(categoria.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", categoriaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CATEGORIA {

				private static final RespuestaServiceValidator AGREGADO = RespuestaServiceValidator.BUNDLE_MSG_SOURCE
						.OK("categoria.agregar.correctamente");
				private static final RespuestaServiceValidator MODIFICADO = RespuestaServiceValidator.BUNDLE_MSG_SOURCE
						.OK("categoria.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CATEGORIA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE
						.ERROR("error.categoria.noExiste");
			}
		}
	}

}

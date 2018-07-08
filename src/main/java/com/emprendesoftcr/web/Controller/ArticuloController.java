package com.emprendesoftcr.web.Controller;

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

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Inventario;
import com.emprendesoftcr.modelo.Marca;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.componentes.ArticuloPropertyEditor;
import com.emprendesoftcr.web.componentes.CategoriaPropertyEditor;
import com.emprendesoftcr.web.componentes.EmpresaPropertyEditor;
import com.emprendesoftcr.web.componentes.InventarioPropertyEditor;
import com.emprendesoftcr.web.componentes.MarcaPropertyEditor;
import com.emprendesoftcr.web.componentes.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Control de los articulos de una empresa ArticuloController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class ArticuloController {

	private static final Function<Object, ArticuloCommand>	TO_COMMAND	= new Function<Object, ArticuloCommand>() {

																																				@Override
																																				public ArticuloCommand apply(Object f) {
																																					return new ArticuloCommand((Articulo) f);
																																				};
																																			};

	@Autowired
	private DataTableBo																			dataTableBo;

	@Autowired
	private ArticuloBo																			articuloBo;

	@Autowired
	private UsuarioBo																				usuarioBo;

	@Autowired
	private ArticuloPropertyEditor													articuloPropertyEditor;
	@Autowired
	private InventarioPropertyEditor												inventarioPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor														empresaPropertyEditor;

	@Autowired
	private MarcaPropertyEditor															marcaPropertyEditor;

	@Autowired
	private CategoriaPropertyEditor													categoriaPropertyEditor;

	@Autowired
	private StringPropertyEditor														stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Articulo.class, articuloPropertyEditor);
		binder.registerCustomEditor(Inventario.class, inventarioPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);

		binder.registerCustomEditor(Marca.class, marcaPropertyEditor);
		binder.registerCustomEditor(Categoria.class, categoriaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Listar JSP de los articulos
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarArticulos", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/articulos/ListarArticulos";
	}
	
	@RequestMapping(value = "/ListarKardex", method = RequestMethod.GET)
	public String listarKardex(ModelMap model) {
		return "views/articulos/ListarKardex";
	}

	/**
	 * Listar Ajax de los articulos de una empresa
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticuloAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Crear un articulo
	 * @param request
	 * @param model
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			Articulo articuloBd = null;
			articuloBd = articuloBo.buscarPorDescripcionYEmpresa(articulo.getDescripcion(), articulo.getEmpresa());
			if (articuloBd != null) {
				result.rejectValue("descripcion", "error.articulo.descripcion.existe");
			}

			articuloBd = articuloBo.buscarPorCodigoYEmpresa(articulo.getCodigo(), articulo.getEmpresa());
			if (articuloBd != null) {
				result.rejectValue("codigo", "error.articulo.codigo.existe");
			}

			if (articulo.getCosto() == null) {
				result.rejectValue("costo", "error.articulo.costo.mayorCero");
			}
			if (articulo.getCosto() == 0) {
				result.rejectValue("costo", "error.articulo.costo.mayorCero");
			}
			if (articulo.getPrecioPublico() == null) {
				result.rejectValue("costo", "error.articulo.precioPublico.mayorCero");
			}
			if (articulo.getPrecioPublico() == 0) {
				result.rejectValue("costo", "error.articulo.precioPublico.mayorCero");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			articulo.setCreated_at(new Date());
			articulo.setEmpresa(usuarioSesion.getEmpresa());
			articulo.setUpdated_at(new Date());
			articulo.setEstado(Constantes.ESTADO_ACTIVO);
			articulo.setGananciaPrecioPublico(articuloBo.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), articulo.getPrecioPublico()));
			articulo.setGananciaPrecioMayorista(articuloBo.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), articulo.getPrecioMayorista()));
			articulo.setGananciaPrecioEspecial(articuloBo.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), articulo.getPrecioEspecial()));
			articulo.setPrecioEspecial(articulo.getPrecioEspecial() == null ?Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articulo.setPrecioMayorista(articulo.getPrecioMayorista() == null ?Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articulo.setImpuesto(articulo.getImpuesto()==null?Constantes.ZEROS_DOUBLE:articulo.getImpuesto());
			articulo.setUsuario(usuarioSesion);
			articuloBo.agregar(articulo);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.agregar.correctamente", articulo);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar Articulo
	 * @param request
	 * @param model
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {
		try {
			articulo.setImpuesto(articulo.getImpuesto() == null ?Constantes.ZEROS_DOUBLE : articulo.getImpuesto());

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBd = articuloBo.buscar(articulo.getId());
			Articulo articuloValidar = null;
			if (!articuloBd.getDescripcion().equals(articulo.getDescripcion())) {
				articuloValidar = articuloBo.buscarPorDescripcionYEmpresa(articulo.getDescripcion(), articulo.getEmpresa());
				if (articuloValidar != null) {
					result.rejectValue("descripcion", "error.articulo.descripcion.existe");
				}

			}
			if (!articuloBd.getCodigo().equals(articulo.getCodigo())) {
				articuloValidar = articuloBo.buscarPorCodigoYEmpresa(articulo.getCodigo(), articulo.getEmpresa());
				if (articuloValidar != null) {
					result.rejectValue("codigo", "error.articulo.codigo.existe");
				}

			}

			if (articulo.getCosto() == null) {
				result.rejectValue("costo", "error.articulo.costo.mayorCero");
			}
			if (articulo.getCosto() == 0) {
				result.rejectValue("costo", "error.articulo.costo.mayorCero");
			}
			if (articulo.getPrecioPublico() == null) {
				result.rejectValue("costo", "error.articulo.precioPublico.mayorCero");
			}
			if (articulo.getPrecioPublico() == 0) {
				result.rejectValue("costo", "error.articulo.precioPublico.mayorCero");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			articuloBd.setCreated_at(new Date());
			articuloBd.setUpdated_at(new Date());
			articuloBd.setMarca(articulo.getMarca());
			articuloBd.setCategoria(articulo.getCategoria());
			articuloBd.setUnidadMedida(articulo.getUnidadMedida());
			
			articuloBd.setEstado(Constantes.ESTADO_ACTIVO);
			articuloBd.setGananciaPrecioPublico(articuloBo.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), articulo.getPrecioPublico()));
			articuloBd.setGananciaPrecioMayorista(articuloBo.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), articulo.getPrecioMayorista()));
			articuloBd.setGananciaPrecioEspecial(articuloBo.porcentanjeDeGanancia(articulo.getCosto(), articulo.getImpuesto(), articulo.getPrecioEspecial()));
			articuloBd.setPrecioEspecial(articulo.getPrecioEspecial() == null ?Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articuloBd.setPrecioMayorista(articulo.getPrecioMayorista() == null ?Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articuloBd.setUsuario(usuarioSesion);
			articuloBd.setTipoImpuesto(articulo.getTipoImpuesto());
			articuloBd.setImpuesto(articulo.getImpuesto()==null?Constantes.ZEROS_DOUBLE:articulo.getImpuesto());
			articuloBo.modificar(articuloBd);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente", articuloBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar articulo por id
	 * @param request
	 * @param model
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarArticuloAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {
		try {
			ArticuloCommand articuloCommand = new ArticuloCommand(articuloBo.buscar(articulo.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", articuloCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Buscar Articulo por id del inventario
	 * @param request
	 * @param model
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findArticuloByCodigojax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator listarAjax(HttpServletRequest request, ModelMap model,@ModelAttribute Articulo articulo,HttpServletResponse response, @RequestParam String codigoArticulo, BindingResult result, SessionStatus status) {
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBD = articuloBo.buscarPorCodigoYEmpresa(codigoArticulo,usuarioSesion.getEmpresa());
			ArticuloCommand articuloCommand = articuloBD == null ?null:new ArticuloCommand(articuloBD);
			
			if(articuloCommand == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.codigo.no.existe", result.getAllErrors());
			}
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", articuloCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class ARTICULO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class ARTICULO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.noExiste");
			}
		}
	}

}

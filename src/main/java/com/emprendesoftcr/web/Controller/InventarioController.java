package com.emprendesoftcr.web.Controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.InventarioBo;
import com.emprendesoftcr.Bo.KardexBo;
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
import com.emprendesoftcr.web.command.InventarioCommand;
import com.emprendesoftcr.web.componentes.ArticuloPropertyEditor;
import com.emprendesoftcr.web.componentes.CategoriaPropertyEditor;
import com.emprendesoftcr.web.componentes.EmpresaPropertyEditor;
import com.emprendesoftcr.web.componentes.InventarioPropertyEditor;
import com.emprendesoftcr.web.componentes.MarcaPropertyEditor;
import com.emprendesoftcr.web.componentes.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Controlar el inventario de los articulos por almacen InventarioController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class InventarioController {

	private static final Function<Object, InventarioCommand>	TO_COMMAND	= new Function<Object, InventarioCommand>() {

																																					@Override
																																					public InventarioCommand apply(Object f) {
																																						return new InventarioCommand((Inventario) f);
																																					};
																																				};

	@Autowired
	private DataTableBo																				dataTableBo;

	@Autowired
	private ArticuloBo																				articuloBo;
	
	@Autowired
	private UsuarioBo																				usuarioBo;

	@Autowired
	private KardexBo																					kardexBo;

	@Autowired
	private InventarioBo																			inventarioBo;

	@Autowired
	private ArticuloPropertyEditor														articuloPropertyEditor;
	@Autowired
	private InventarioPropertyEditor													inventarioPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor															empresaPropertyEditor;

	@Autowired
	private MarcaPropertyEditor																marcaPropertyEditor;

	@Autowired
	private CategoriaPropertyEditor														categoriaPropertyEditor;

	@Autowired
	private StringPropertyEditor															stringPropertyEditor;

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
	 * Listado de inventario de las sucursales por id de articulo
	 * @param request
	 * @param model
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarInventarioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Inventario");
		JqGridFilter dataTableFilter = new JqGridFilter("articulo.id", "'" + articulo.getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar un inventario a una sucursal
	 * @param request
	 * @param model
	 * @param IdArticulo
	 * @param inventario
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarInventarioAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @RequestParam("IdArticulo") Integer IdArticulo, @ModelAttribute Inventario inventario, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articulo = articuloBo.buscar(IdArticulo);
			if (articulo == null) {
				respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
				respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.inventario.articulo.no.existe"));
				return respuestaServiceValidator;
			}
			if (inventario.getCantidad() == null) {
				result.rejectValue("cantidad", "error.inventario.cantidad.cero");
			}

			if (inventario.getCantidad() != null) {
				if (inventario.getCantidad() == Constantes.ZEROS_DOUBLE) {
					result.rejectValue("cantidad", "error.inventario.cantidad.cero");
				}
			}
			Inventario inventarioBd = inventarioBo.buscarPorArticulo(articulo);
			if (inventarioBd != null) {
				result.rejectValue("cantidad", "error.inventario.ya.existe");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			inventario.setCreated_at(new Date());
			inventario.setUpdated_at(new Date());
			inventario.setEstado(Constantes.ESTADO_ACTIVO);
			inventario.setArticulo(articulo);
			inventario.setUsuario(usuarioSesion);

			inventarioBo.agregar(inventario);
			kardexBo.entrada(inventario, Constantes.ZEROS_DOUBLE,inventario.getCantidad(), Constantes.OBSERVACION_INICIAL_INVENTARIO_NUEVO, Constantes.CONSECUTIVO_INICIAL_INVENTARIO_NUEVO, Constantes.KARDEX_TIPO_ENTRADA, Constantes.MOTIVO_INICIAL_INVENTARIO_NUEVO, usuarioSesion);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("inventario.agregar.correctamente", inventario);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar un invnetario
	 * @param request
	 * @param model
	 * @param inventario
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarInventarioAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @RequestParam("IdArticulo") Integer IdArticulo, @ModelAttribute Inventario inventario, BindingResult result, SessionStatus status) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			Articulo articulo = articuloBo.buscar(IdArticulo);
			if (articulo == null) {
				respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
				respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.inventario.articulo.no.existe"));
				return respuestaServiceValidator;
			}

			Inventario inventarioBD = inventarioBo.buscar(inventario.getId());
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			inventarioBD.setMaximo(inventario.getMaximo());
			inventarioBD.setMinimo(inventario.getMinimo());
			inventarioBD.setUpdated_at(new Date());
			inventarioBD.setUsuario(usuarioSesion);
			inventarioBo.modificar(inventarioBD);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("inventario.modificado.correctamente", inventarioBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class INVENTARIO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("inventario.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("inventario.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class INVENTARIO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.inventario.noExiste");
			}
		}
	}

}

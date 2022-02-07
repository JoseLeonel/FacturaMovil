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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UbicacionBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Marca;
import com.emprendesoftcr.modelo.Ubicacion;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.UbicacionCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UbicacionPropertyEditor;
import com.google.common.base.Function;

/**
 * Marcas de los articulos MarcasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class UbicacionController {

	private static final Function<Object, UbicacionCommand>	TO_COMMAND	= new Function<Object, UbicacionCommand>() {

																																		@Override
																																		public UbicacionCommand apply(Object f) {
																																			return new UbicacionCommand((Ubicacion) f);
																																		};
																																	};

	@Autowired
	private DataTableBo																	dataTableBo;

	@Autowired
	private UbicacionBo																			ubicacionBo;

	
	@Autowired
	private UsuarioBo																		usuarioBo;

	@Autowired
	private UbicacionPropertyEditor													ubicacionPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor												empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor												stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Ubicacion.class, ubicacionPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	
	@RequestMapping(value = "/ListarUbicaciones", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/ubicacion/ListarUbicaciones";
	}

	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarUbicacionAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Ubicacion");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Listar metodo ajax para mostrar las marcas activas
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarUbicacionActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Ubicacion");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		String nombreUsuario = request.getUserPrincipal().getName();
		dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
		delimitadores.addFiltro(dataTableFilter);
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar una marca
	 * @param request
	 * @param model
	 * @param marca
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AgregarUbicacionAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Ubicacion ubicacion, BindingResult result, SessionStatus status) throws Exception {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Ubicacion ubicacionBD = ubicacionBo.buscarPorDescripcionYEmpresa(ubicacion.getDescripcion(), usuario.getEmpresa());
			if (ubicacionBD != null) {
				result.rejectValue("descripcion", "error.marca.descripcion.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			
			ubicacion.setEmpresa(usuario.getEmpresa());
			ubicacion.setCreated_at(new Date());
			ubicacion.setUpdated_at(new Date());
	
			ubicacionBo.agregar(ubicacion);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("ubicacion.agregar.correctamente", ubicacion);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una Marca
	 * @param request
	 * @param model
	 * @param marca
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ModificarUbicacionAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Ubicacion ubicacion, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("marca.no.modificado", result.getAllErrors());
			}
			Ubicacion ubicacionBD = ubicacionBo.buscar(ubicacion.getId());
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (ubicacionBD == null) {
				return RESPONSES.ERROR.UBICACION.NO_EXISTE;
			} else {
				Ubicacion ubicacionValidar = null;
				if (!ubicacion.getDescripcion().equals(ubicacionBD.getDescripcion())) {
					ubicacionValidar = ubicacionBo.buscarPorDescripcionYEmpresa(ubicacion.getDescripcion(), usuario.getEmpresa());
					if (ubicacionValidar != null) {
						result.rejectValue("descripcion", "error.ubicacion.descripcion.existe");
					}
				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				ubicacionBD.setDescripcion(ubicacion.getDescripcion());
				ubicacionBD.setUpdated_at(new Date());
				ubicacionBD.setEstado(ubicacion.getEstado());
				ubicacionBo.modificar(ubicacionBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("ubicacion.modificado.correctamente", ubicacionBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarUbicacionAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Marca marca, BindingResult result, SessionStatus status) throws Exception {
		try {
			UbicacionCommand UbicacionCommand = new UbicacionCommand(ubicacionBo.buscar(marca.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", UbicacionCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class UBICACION {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("ubicacion.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("ubicacion.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class UBICACION {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.ubicacion.noExiste");
			}
		}
	}

}

package com.emprendesoftcr.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.RolAdministradorCommand;
import com.emprendesoftcr.web.command.UsuarioCommand;
import com.emprendesoftcr.web.command.ValidarRolCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioPropertyEditor;
import com.google.common.base.Function;

/**
 * Mantenimiento de usuarios
 * @author jose
 */
@Controller
public class UsuarioController {

	private static final Function<Object, UsuarioCommand>	TO_COMMAND	= new Function<Object, UsuarioCommand>() {

																																			@Override
																																			public UsuarioCommand apply(Object f) {
																																				return new UsuarioCommand((Usuario) f);
																																			};
																																		};

	@Autowired
	private DataTableBo																		dataTableBo;

	@Autowired
	private UsuarioBo																			usuarioBo;

	@Autowired
	private UsuarioPropertyEditor													usuarioPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor													empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor													stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Usuario.class, usuarioPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Cambio de clave del usuario
	 * @param model
	 * @return
	 */
	@GetMapping("/CambioClave")
	public String cambioClavePagina(ModelMap model) {
		return "views/usuario/CambioClave";
	}

	/**
	 * Cambiar contrasena
	 * @param request
	 * @param model
	 * @param usuario
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CambioAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cambioclave(HttpServletRequest request, ModelMap model, @ModelAttribute Usuario usuario, BindingResult result, SessionStatus status) throws Exception {
		try {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(usuario.getPassword());
			if (!usuario.getPassword().equals(usuario.getPasswordConfirm())) {
				result.rejectValue("password", "error.usuario.clave.no.iguales");
			}
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);

			if (encoder.matches(usuario.getPassword(), usuarioSesion.getPassword())) {
				result.rejectValue("password", "error.usuario.clave.igual.actual");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			usuarioSesion.setPassword(encodedPassword);
			usuarioSesion.setPasswordConfirm(encodedPassword);
			usuarioBo.modificar(usuarioSesion);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuario.modificado.correctamente", usuarioSesion);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	/**
	 * Verifica si es rol administrador
	 * @param request
	 * @param model
	 * @param validarRolCommand
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/validarRolAdministradorAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator validarRolAdministrador(HttpServletRequest request, ModelMap model, @ModelAttribute ValidarRolCommand validarRolCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			
			Usuario usuario = usuarioBo.buscar(validarRolCommand.getUsuarioSistema());
			if(usuario == null) {
				result.rejectValue("usuarioSistema", "usuario.validar.no.existe");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
				
			if (!encoder.matches(validarRolCommand.getClaveSistema(),usuario.getPassword() )) {
				result.rejectValue("claveSistema", "validar.usuario.clave.incorrecta");
			}
//			if (BCrypt.checkpw(validarRolCommand.getClaveSistema(),usuario.getPassword() ))
//
//				System.out.println("The password matches.");
//
//				else
//
//				System.out.println("The password does not match.");
			if(usuarioBo.isAdministrador_sistema(usuario) || usuarioBo.isAdministrador_empresa(usuario) || usuarioBo.isAdministrador_restaurante(usuario)   ) {
				validarRolCommand.setAceptacion(1);
			}else {
				result.rejectValue("claveSistema", "validar.usuario.rol.invalido");
			}
			
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			
			validarRolCommand.setAceptacion(1);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuario.validar",validarRolCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/RolUsuarioAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator rolUsuario(HttpServletRequest request,  ModelMap model,RolAdministradorCommand rolAdministradorCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			if(usuario == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
		
				
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if(usuarioBo.isUsuario_SuperDario(usuario) || usuarioBo.isAdministrador_sistema(usuario) || usuarioBo.isAdministrador_empresa(usuario) || usuarioBo.isAdministrador_restaurante(usuario) || usuarioBo.isAdministrador_cajero(usuario)   ) {
				rolAdministradorCommand.setRolAdministrador(1);
			}else {
				rolAdministradorCommand.setRolAdministrador(0);
			}
			
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuario.validar",rolAdministradorCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar la pagina html listar usuarios
	 * @param model
	 * @return
	 */
	@GetMapping("/ListarUsuario")
	public String listar(ModelMap model) {

		return "views/usuario/ListarUsuarios";
	}

	/**
	 * Listar los usuarios activos o inactivos
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarUsuariosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Usuario");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarUsuariosByEmpresaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarByEmpresaAjax(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Usuario");
		delimitadores.addFiltro(new JqGridFilter("empresa.id", "'" + usuario.getEmpresa().getId().toString() + "'", "="));
		delimitadores.addFiltro(new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO_PROVEEDOR_SIMPLIFICADO + "'", "="));
		if (request.isUserInRole(Constantes.ROL_ADMINISTRADOR_CAJERO) || request.isUserInRole(Constantes.ROL_USUARIO_VENDEDOR)) {
			delimitadores.addFiltro(new JqGridFilter("nombreUsuario", "'" + usuario.getNombreUsuario() + "'", "="));
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar usuario
	 * @param request
	 * @param model
	 * @param usuario
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AgregarUsuarioAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Usuario usuario, BindingResult result, SessionStatus status) throws Exception {
		try {

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Usuario usuarioBD = usuarioBo.buscar(usuario.getNombreUsuario());
			if (usuarioBD != null) {
				result.rejectValue("nombreUsuario", "error.usuario.modificar.existe.nombreUsuario");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());

			}

			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(usuario.getPassword());
			usuario.setPassword(encodedPassword);
			usuario.setPasswordConfirm(encodedPassword);

			usuarioBo.agregar(usuario);
			usuarioBD = usuarioBo.buscar(usuario.getNombreUsuario());

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuario.agregar.correctamente", usuarioBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar usuarios
	 * @param request
	 * @param model
	 * @param usuario
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ModificarUsuarioAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Usuario usuario, BindingResult result, SessionStatus status) throws Exception {
		try {

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("usuario.no.modificado", result.getAllErrors());
			}
			Usuario usuarioBD = usuarioBo.buscar(usuario.getId());

			if (usuarioBD == null) {
				return RESPONSES.ERROR.USUARIO.NO_EXISTE;
			} else {

				if (!usuarioBD.getNombreUsuario().equals(usuario.getNombreUsuario())) {
					Usuario usuarioValidar = usuarioBo.buscar(usuario.getNombreUsuario());
					if (usuarioValidar != null) {
						result.rejectValue("nombreUsuario", "error.usuario.modificar.existe.nombreUsuario");
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
					}
				}
				usuarioBD.setEstado(usuario.getEstado());
				usuarioBD.setNombre(usuario.getNombre());
				usuarioBD.setNombreUsuario(usuario.getNombreUsuario());
				usuarioBD.setPrimerApellido(usuario.getPrimerApellido());
				usuarioBD.setSegundoApellido(usuario.getSegundoApellido());
				PasswordEncoder encoder = new BCryptPasswordEncoder();
				String encodedPassword = encoder.encode(usuario.getPassword());
				usuarioBD.setPassword(encodedPassword);
				usuarioBD.setPasswordConfirm(encodedPassword);
				usuarioBo.modificar(usuarioBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuario.modificado.correctamente", usuarioBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar los usuarios
	 * @param request
	 * @param model
	 * @param usuario
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarUsuarioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Usuario usuario, BindingResult result, SessionStatus status) throws Exception {
		try {
			UsuarioCommand usuarioCommand = new UsuarioCommand(usuarioBo.buscar(usuario.getNombreUsuario()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", usuarioCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	
	private static class RESPONSES {

		@SuppressWarnings("unused")
		private static class OK {

			private static class USUARIO {

				@SuppressWarnings("rawtypes")
				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuario.agregar.correctamente");
				@SuppressWarnings("rawtypes")
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuario.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class USUARIO {

				@SuppressWarnings("rawtypes")
				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.usuario.noExiste");
			}
		}
	}

}

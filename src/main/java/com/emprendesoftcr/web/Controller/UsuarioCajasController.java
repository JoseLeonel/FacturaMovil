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

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.UsuarioCajaCommand;
import com.emprendesoftcr.web.propertyEditor.CajaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioCajaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioPropertyEditor;
import com.google.common.base.Function;

/**
 * Abrir caja para facturar UsuarioCajasController.
 * @author jose.
 * @since 11 jun. 2018
 */
@Controller
public class UsuarioCajasController {

	private static final Function<Object, UsuarioCajaCommand>	TO_COMMAND_CAJAS_ABIERTAS_CERRADAS	= new Function<Object, UsuarioCajaCommand>() {

																																																	@Override
																																																	public UsuarioCajaCommand apply(Object f) {
																																																		return new UsuarioCajaCommand((UsuarioCaja) f);
																																																	};
																																																};

	@Autowired
	private DataTableBo																				dataTableBo;

	@Autowired
	private UsuarioCajaBo																			usuarioCajaBo;

	@Autowired
	private UsuarioBo																					usuarioBo;

	@Autowired
	private EmpresaPropertyEditor															empresaPropertyEditor;

	@Autowired
	private CajaPropertyEditor																cajaPropertyEditor;

	@Autowired
	UsuarioCajaPropertyEditor																	usuarioCajaPropertyEditor;

	@Autowired
	UsuarioPropertyEditor																			usuarioPropertyEditor;

	@Autowired
	private StringPropertyEditor															stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Caja.class, cajaPropertyEditor);
		binder.registerCustomEditor(Usuario.class, usuarioPropertyEditor);
		binder.registerCustomEditor(UsuarioCaja.class, usuarioCajaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Pantalla para abrir la caja un usuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/AbrirCajas", method = RequestMethod.GET)
	public String abrirCajas(ModelMap model) {
		return "views/caja/abrirCajas";
	}

	@RequestMapping(value = "/ListarCajasInactivas", method = RequestMethod.GET)
	public String liasCajas(ModelMap model) {
		return "views/caja/ListarCajasInactivas";
	}

	/**
	 * Lista de las cajas creadas por usuario
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarUsuariosCajasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarUsuariosCajasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "UsuarioCaja");
		JqGridFilter dataTableFilter = null;
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		dataTableFilter = new JqGridFilter("usuario.id", "'" + usuario.getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_CAJAS_ABIERTAS_CERRADAS);
	}

	@RequestMapping(value = "/ListarUsuariosCajasCerradasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarUsuariosCajasCerradasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "UsuarioCaja");
		JqGridFilter dataTableFilter = null;
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_EMPRESA) && !request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			dataTableFilter = new JqGridFilter("usuario.id", "'" + usuario.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);

		}
		dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_INACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_CAJAS_ABIERTAS_CERRADAS);
	}

	@RequestMapping(value = "/AgregarUsuarioCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute UsuarioCaja usuarioCaja, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);
			if (usuarioCajaBd != null) {
				result.rejectValue("totalFondoInicial", "error.usuarioCaja.totalFondoInicial.existe.activo");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			usuarioCaja.setCreated_at(new Date());
			usuarioCaja.setUsuario(usuario);
			usuarioCaja.setUpdated_at(new Date());
			usuarioCaja.setEstado(Constantes.ESTADO_ACTIVO);
			usuarioCaja.setUsuario(usuario);
			usuarioCaja.setTotalBanco(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalEfectivo(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalNeto(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalTarjeta(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalAbono(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalCredito(Constantes.ZEROS_DOUBLE);
			usuarioCaja.setTotalServicio(Constantes.ZEROS_DOUBLE);
			usuarioCajaBo.agregar(usuarioCaja);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.agregar.correctamente", usuarioCaja);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Cerrar la cja
	 * @param request
	 * @param model
	 * @param usuarioCaja
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/CerrarUsuarioCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cerrarCaja(HttpServletRequest request, ModelMap model, @ModelAttribute UsuarioCaja usuarioCaja, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.buscar(usuarioCaja.getId());

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			//Se acutalizan los registros
			usuarioCajaBo.actualizarCaja(usuarioCajaBd);
			
			//Se cierra la caja
			usuarioCajaBo.cierreCaja(usuarioCajaBd);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.cierre.correctamente", usuarioCajaBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar el usuario caja
	 * @param request
	 * @param model
	 * @param usuarioCaja
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarUsuarioCajaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute UsuarioCaja usuarioCaja, BindingResult result, SessionStatus status) throws Exception {
		try {
			UsuarioCajaCommand usuarioCajaCommand = new UsuarioCajaCommand(usuarioCajaBo.buscar(usuarioCaja.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", usuarioCajaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	
	@RequestMapping(value = "/AgrupaArticulosCategoriaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	@SuppressWarnings({ "rawtypes"})
	public RespuestaServiceValidator agrupaArticulosCategoria(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idUsuarioCaja) {
		UsuarioCaja usuarioCaja =  usuarioCajaBo.buscar(idUsuarioCaja);
		return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", usuarioCajaBo.agrupaArticulosCategoria(usuarioCaja.getCaja().getEmpresa().getId(), usuarioCaja.getId()));
	}

	/**
	 * RESPONSES.
	 * @author jose.
	 * @since 11 jun. 2018
	 */
	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class USUARIOCAJA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class USUARIOCAJA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.usuarioCaja.noExiste");
			}
		}
	}

}

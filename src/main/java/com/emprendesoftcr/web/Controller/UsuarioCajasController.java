package com.emprendesoftcr.web.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
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

import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ConteoManualCommand;
import com.emprendesoftcr.web.command.DenominacionCommand;
import com.emprendesoftcr.web.command.UsuarioCajaCommand;
import com.emprendesoftcr.web.propertyEditor.CajaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioCajaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;

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
	CorreosBo																									correosBo;

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
	public String abrirCajas(Device device) {
  String deviceType = "browser";
  String platform = "browser";
	String viewName = "views/caja/abrirCajas";

  if (device.isNormal()) {
      deviceType = "browser";
  } else if (device.isMobile()) {
      deviceType = "mobile";
      viewName = "views/caja/abrirCajasMovil.html";
  } else if (device.isTablet()) {
      deviceType = "tablet";
      viewName = "views/caja/abrirCajas";
  }
  
  platform = device.getDevicePlatform().name();
  
  if (platform.equalsIgnoreCase("UNKNOWN")) {
      platform = "browser";
  }
		return viewName;
	}

	@RequestMapping(value = "/Configuracion.do", method = RequestMethod.GET)
	public String configuracion(ModelMap model) {
		return "views/caja/configuracion";
	}

	@RequestMapping(value = "/CerrarCaja.do", method = RequestMethod.GET)
	public String cerrarCajas(ModelMap model) {
		return "views/caja/cerrarCajas";
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
	 * @throws Exception
	 */

	@RequestMapping(value = "/ListarUsuariosCajasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable<?> listarUsuariosCajasAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return usuarioCajaBo.listarUsuariosCajasActivasAjax(request, response);

	}

	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarUsuariosCajasCerradasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarUsuariosCajasCerradasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String inicio, @RequestParam String fin, @RequestParam Integer idUsuario) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "UsuarioCaja");
		JqGridFilter dataTableFilter = null;
		Usuario usuario = usuarioBo.buscar(idUsuario);
		if (usuarioBo.isUsuario_Vendedor(usuario) || usuarioBo.isUsuario_Cajero(usuario) || usuarioBo.isUsuario_Mesero(usuario) || usuarioBo.isUsuario_SuperDario(usuario)) {
			dataTableFilter = new JqGridFilter("usuario.id", "'" + usuario.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}
		// Se incluye la empresa
		dataTableFilter = new JqGridFilter("caja.empresa.id", "'" + usuario.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		Date fechaInicio = new Date();
		Date fechaFinal = new Date();
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

			delimitadores.addFiltro(new JqGridFilter("created_at", inicio, "date>="));
			delimitadores.addFiltro(new JqGridFilter("created_at", fin, "dateFinal<="));
		}

		dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_INACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_CAJAS_ABIERTAS_CERRADAS);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AgregarUsuarioCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute ConteoManualCommand conteoManualCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			return usuarioCajaBo.agregarCaja(request, conteoManualCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}



	public ArrayList<DenominacionCommand> denominacionCommand(ConteoManualCommand conteoManualCommand) throws Exception {
		JSONObject json = null;
		ArrayList<DenominacionCommand> denominacionCommands = new ArrayList<>();
		try {
			json = (JSONObject) new JSONParser().parse(conteoManualCommand.getDenominacion());
			// Agregar Lineas
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleFactura != null) {
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					DenominacionCommand denominacionCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), DenominacionCommand.class);
					denominacionCommands.add(denominacionCommand);
				}
			}
		} catch (org.json.simple.parser.ParseException e) {
			throw e;
		}
		return denominacionCommands;
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
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CerrarUsuarioCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cerrarCaja(HttpServletRequest request, ModelMap model, @ModelAttribute ConteoManualCommand conteoManualCommand, BindingResult result, SessionStatus status) throws Exception {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			return usuarioCajaBo.cerrarCajaCajero(request, conteoManualCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}



	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ActualizarUsuarioCajaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator actualizarCaja(HttpServletRequest request, ModelMap model, @ModelAttribute UsuarioCaja usuarioCaja, BindingResult result, SessionStatus status, @RequestParam Long idUsuarioCaja) throws Exception {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.buscar(idUsuarioCaja);

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			// Se acutalizan los registros
			usuarioCajaBo.actualizarCaja(usuarioCajaBd);

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
	@RequestMapping(value = "/MostrarUsuarioCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute UsuarioCaja usuarioCaja, BindingResult result, SessionStatus status) throws Exception {
		try {
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.buscar(usuarioCaja.getId());
			usuarioCajaBo.actualizarCaja(usuarioCajaBd);
			UsuarioCajaCommand usuarioCajaCommand = new UsuarioCajaCommand(usuarioCajaBo.buscar(usuarioCaja.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", usuarioCajaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@RequestMapping(value = "/AgrupaArticulosCategoriaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	@SuppressWarnings({ "rawtypes" })
	public RespuestaServiceValidator agrupaArticulosCategoria(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idUsuarioCaja) {
		UsuarioCaja usuarioCaja = usuarioCajaBo.buscar(idUsuarioCaja);
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

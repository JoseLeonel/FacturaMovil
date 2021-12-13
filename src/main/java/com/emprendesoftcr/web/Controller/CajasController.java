package com.emprendesoftcr.web.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.emprendesoftcr.Bo.CajaBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.SalidaEntradaDineroBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.CajaCommand;
import com.emprendesoftcr.web.command.SalidaEntradaDineroCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Cajas por empresa CajasController.
 * @author jose.
 * @since 11 jun. 2018
 */
@CrossOrigin
@Controller
public class CajasController {

	private static final Function<Object, CajaCommand>								TO_COMMAND								= new Function<Object, CajaCommand>() {

																																																@Override
																																																public CajaCommand apply(Object f) {
																																																	return new CajaCommand((Caja) f);
																																																};
																																															};
	private static final Function<Object, SalidaEntradaDineroCommand>	TO_COMMAND_ENTRADA_SALIDA	= new Function<Object, SalidaEntradaDineroCommand>() {

																																																@Override
																																																public SalidaEntradaDineroCommand apply(Object f) {
																																																	return new SalidaEntradaDineroCommand((SalidaEntradaDinero) f);
																																																};
																																															};

	@Autowired
	private DataTableBo																								dataTableBo;

	@Autowired
	private CajaBo																										cajaBo;

	@Autowired
	private UsuarioCajaBo																							usuarioCajaBo;
	@Autowired
	private SalidaEntradaDineroBo																			salidaEntradaDineroBo;

	@Autowired
	private UsuarioBo																									usuarioBo;
	@Autowired
	private ValidateTokenBo																						validateTokenBo;

	@Autowired
	private EmpresaPropertyEditor																			empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Mostrar el JSP de las marcas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarCajas", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/caja/ListarCajas";
	}

	/**
	 * Listar metodo ajax para mostrar las lista de las masrcas
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarCajasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Caja");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Listar las cajas activas por empresa
	 * @param request
	 * @param response
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/listarEntradasOrSalidas.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarEntradasOrSalidas(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idTipoEntrada, @RequestParam(value = "idEntradaSalida", required = false) Long idEntradaSalida) {

		return listarEntradasOrSalidasT(request, response, idTipoEntrada, idEntradaSalida);
	}

	private RespuestaServiceDataTable<?> listarEntradasOrSalidasT(HttpServletRequest request, HttpServletResponse response, Integer idTipoEntrada, Long idEntradaSalida) {
		DataTableDelimitador delimitadores = null;
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (idEntradaSalida != null) {
			SalidaEntradaDinero salidaEntradaDinero = salidaEntradaDineroBo.findById(idEntradaSalida);
			if (salidaEntradaDinero != null) {
				salidaEntradaDineroBo.eliminar(salidaEntradaDinero);
			}
		}
		UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);
		if (usuarioCaja != null) {
			delimitadores = new DataTableDelimitador(request, "SalidaEntradaDinero");

			JqGridFilter dataTableFilter = new JqGridFilter("tipo", "'" + idTipoEntrada.toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);

			dataTableFilter = new JqGridFilter("usuariocaja.id", "'" + usuarioCaja.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);

		}

		return usuarioCaja == null ? null : UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_ENTRADA_SALIDA);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarCajasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarCajasActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		return listarCajasActivasAjaxT(request, response);
	}

	public RespuestaServiceDataTable<?> listarCajasActivasAjaxT(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		JqGridFilter dataTableFilter = null;
		delimitadores = new DataTableDelimitador(request, "Caja");
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (usuario != null) {
			dataTableFilter = usuarioBo.filtroPorEmpresa(usuario.getNombreUsuario());
		}

		delimitadores.addFiltro(dataTableFilter);
		if (!usuarioBo.isUsuario_Condominio(usuario) && !usuarioBo.isAdministrador_sistema(usuario) && !usuarioBo.isAdministrador_empresa(usuario) && !usuarioBo.isAdministrador_restaurante(usuario)&& !usuarioBo.isAdministrador_cajero(usuario)) {

		

			dataTableFilter = new JqGridFilter("usuario.id", "'" + usuario.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}
		dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AgregarCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Caja caja, BindingResult result, SessionStatus status) throws Exception {

		try {

			return cajaBo.agregar(request, caja, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/local/AgregarCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Caja caja, BindingResult result, SessionStatus status) throws Exception {

		try {

			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return cajaBo.agregar(request, caja, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ModificarCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Caja caja, BindingResult result, SessionStatus status) throws Exception {
		try {

			return cajaBo.modificar(request, caja, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/local/ModificarCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Caja caja, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}

			return cajaBo.modificar(request, caja, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar una caja
	 * @param request
	 * @param model
	 * @param caja
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarCajaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Caja caja, BindingResult result, SessionStatus status) throws Exception {
		try {

			return cajaBo.mostrar(request, caja);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/local/MostrarCajaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Caja caja, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return cajaBo.mostrar(request, caja);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CAJA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("caja.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("caja.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CAJA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.caja.noExiste");
			}
		}
	}

}

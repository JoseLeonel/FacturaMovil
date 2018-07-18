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
import com.emprendesoftcr.Bo.TipoCambioBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.TipoCambiosCommand;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Cajas por empresa CajasController.
 * @author jose.
 * @since 11 jun. 2018
 */
@Controller
public class TipoCambiosController {

	private static final Function<Object, TipoCambiosCommand>	TO_COMMAND	= new Function<Object, TipoCambiosCommand>() {

																																		@Override
																																		public TipoCambiosCommand apply(Object f) {
																																			return new TipoCambiosCommand((TipoCambio) f);
																																		};
																																	};

	@Autowired
	private DataTableBo																	dataTableBo;

	@Autowired
	private TipoCambioBo																			tipoCambioBo;

	@Autowired
	private UsuarioBo																		usuarioBo;


	@Autowired
	private StringPropertyEditor												stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Mostrar el JSP de las marcas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarTipoCambios", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/tipoCambios/ListarTipoCambios";
	}

	/**
	 * Listar metodo ajax para mostrar las lista de las masrcas
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarTipoCambioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "TipoCambio");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@RequestMapping(value = "/AgregarTipoCambioAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute TipoCambio tipoCambio, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			TipoCambio tipoCambioBd = tipoCambioBo.findByEstadoAndEmpresa(Constantes.ESTADO_ACTIVO,usuario.getEmpresa());
			if (tipoCambioBd != null) {
				result.rejectValue("total", "error.tipoCambio.total.existe.activo");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			tipoCambio.setCreated_at(new Date());
			tipoCambio.setUsuario(usuario);
			tipoCambio.setUpdated_at(new Date());
			tipoCambio.setEstado(Constantes.ESTADO_ACTIVO);
			tipoCambio.setEmpresa(usuario.getEmpresa());
			tipoCambio.setMoneda(Constantes.CODIGO_MONEDA_DOLAR);

			
			tipoCambioBo.agregar(tipoCambio);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tipoCambio.agregar.correctamente", tipoCambio);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@RequestMapping(value = "/DesactivarTipoCambioAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cerrarCaja(HttpServletRequest request, ModelMap model, @ModelAttribute TipoCambio tipoCambio, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			TipoCambio tipoCambioBd = tipoCambioBo.buscar(tipoCambio.getId());
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			tipoCambioBd.setUpdated_at(new Date());
			tipoCambioBd.setUsuario(usuario);
			tipoCambioBd.setEstado(Constantes.ESTADO_INACTIVO);
			tipoCambioBo.modificar(tipoCambioBd);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tipoCambio.desactivado.correctamente", tipoCambioBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@RequestMapping(value = "/MostrarTipoCambioActivoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute TipoCambio tipoCambio, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			TipoCambio tipoCambioBD = tipoCambioBo.findByEstadoAndEmpresa(Constantes.ESTADO_ACTIVO,usuario.getEmpresa());
			if (tipoCambioBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.tipo.cambio.dolar.activo", result.getAllErrors());

			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
		
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tipoCambio.consulto.correctamente", tipoCambioBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class TIPOCAMBIO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tipoCambio.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tipoCambio.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class TIPOCAMBIO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.tipoCambio.noExiste");
			}
		}
	}

}

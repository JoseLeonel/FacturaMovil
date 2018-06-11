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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Bo.UsuarioCajaBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Caja;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;
import com.factura.FacturaElectronica.web.command.CajaCommand;
import com.factura.FacturaElectronica.web.command.UsuarioCajaCommand;
import com.factura.FacturaElectronica.web.componentes.CajaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.EmpresaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.UsuarioCajaPropertyEditor;
import com.google.common.base.Function;

/**
 * Abrir caja para facturar
 * UsuarioCajasController.
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
	private StringPropertyEditor															stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Caja.class, cajaPropertyEditor);
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
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
		}

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
			usuarioCajaBo.agregar(usuarioCaja);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.agregar.correctamente", usuarioCaja);

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

	/**
	 * 
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

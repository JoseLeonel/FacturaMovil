package com.emprendesoftcr.web.Controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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

import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.SalidaEntradaDineroCommand;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;

@Controller
public class SalidaEntradaDineroController {

	@Autowired
	private UsuarioBo							usuarioBo;

	@Autowired
	private UsuarioCajaBo					usuarioCajaBo;

	
	@Autowired
	private StringPropertyEditor	stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarSalidaEntradaDineroAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute SalidaEntradaDineroCommand salidaEntradaDineroCommand, BindingResult result, SessionStatus status) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuarioSesion, Constantes.ESTADO_ACTIVO);
			if (usuarioCaja == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			SalidaEntradaDinero salidaEntradaDinero = new SalidaEntradaDinero();
			
			salidaEntradaDinero.setCreated_at(new Date());
			salidaEntradaDinero.setTipo(salidaEntradaDineroCommand.getTipo());
			salidaEntradaDinero.setDescripcion(salidaEntradaDineroCommand.getDescripcion());
			salidaEntradaDinero.setTotal(salidaEntradaDineroCommand.getTotal());
			salidaEntradaDinero.setUsuariocaja(usuarioCaja);
			salidaEntradaDinero.setUsuarioResponsable(usuarioSesion);
			usuarioCaja.addSalidaEntradaDinero(salidaEntradaDinero);
			usuarioCajaBo.agregar(usuarioCaja);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("salidaEntradaDinero.agregar.correctamente", salidaEntradaDinero);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class ABONO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("salidaEntradaDinero.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("salidaEntradaDinero.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class ABONO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.vendedor.noExiste");
			}
		}
	}

}

package com.emprendesoftcr.web.Controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.SalidaEntradaDineroBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.SalidaEntradaDineroCommand;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
@CrossOrigin
@Controller
public class SalidaEntradaDineroController {

	@Autowired
	private ValidateTokenBo validateTokenBo;
	@Autowired
	private StringPropertyEditor	stringPropertyEditor;
	@Autowired
	private SalidaEntradaDineroBo salidaEntradaDineroBo;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarSalidaEntradaDineroAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute SalidaEntradaDineroCommand salidaEntradaDineroCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			

			return salidaEntradaDineroBo.agregar(request, salidaEntradaDineroCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@SuppressWarnings("all")
	@RequestMapping(value = "/local/AgregarSalidaEntradaDineroAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarlocal(HttpServletRequest request, ModelMap model, @ModelAttribute SalidaEntradaDineroCommand salidaEntradaDineroCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad",
						result.getAllErrors());
			}

			return salidaEntradaDineroBo.agregar(request, salidaEntradaDineroCommand, result);

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

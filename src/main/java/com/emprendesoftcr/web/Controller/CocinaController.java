package com.emprendesoftcr.web.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CocinaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.ComandaNuevaCommand;

@Controller
public class CocinaController {
	
	@Autowired
	private CocinaBo cocinaBo;
	
	@Autowired
	private UsuarioBo usuarioBo;
	
	@Autowired
	private UsuarioCajaBo usuarioCajaBo;


	
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarComandaCocina.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model,  @ModelAttribute ComandaNuevaCommand comandaNuevaCommand, BindingResult result, SessionStatus status) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuarioSesion, Constantes.ESTADO_ACTIVO);
			if (usuarioCaja == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
			}
			//Buscar la cocina activa de caja abierta
		
//		cocinaBo.agregarNuevaComandaDia(usuarioCaja, comandaNuevaCommand);
			
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abono.agregar.correctamente", null);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

}

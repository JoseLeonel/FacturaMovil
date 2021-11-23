package com.emprendesoftcr.apis;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.ConteoManualCommand;
import com.emprendesoftcr.web.command.UsuarioCajaCommand;
import com.emprendesoftcr.web.propertyEditor.CajaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioCajaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioPropertyEditor;
@CrossOrigin
@RestController
@RequestMapping("/api/usuarioCajero")
public class UsuarioCajaRest {


	@Autowired
	private UsuarioCajaBo																			usuarioCajaBo;

	
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
	@Autowired
	private ValidateTokenBo																		validateTokenBo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Caja.class, cajaPropertyEditor);
		binder.registerCustomEditor(Usuario.class, usuarioPropertyEditor);
		binder.registerCustomEditor(UsuarioCaja.class, usuarioCajaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/ListarUsuariosCajasAjax.do")
	public RespuestaServiceDataTable<?> listarUsuariosCajasAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (validateTokenBo.validarTokenApis(request) == false) {
			List<Object> solicitudList = new ArrayList<Object>();
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();

			respuestaService.setRecordsTotal((long) solicitudList.size());
			respuestaService.setRecordsFiltered((long) solicitudList.size());
			if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
				respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
			}
			respuestaService.setAaData(solicitudList);
			return respuestaService;

		}

		return usuarioCajaBo.listarUsuariosCajasActivasAjax(request, response);

	}


	@SuppressWarnings("rawtypes")
	@PostMapping("/aperturaCaja.do")
	public RespuestaServiceValidator aperturaCaja(HttpServletRequest request, ModelMap model, @ModelAttribute ConteoManualCommand conteoManualCommand, BindingResult result, SessionStatus status) throws Exception {

		try {
			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());

			}
			return usuarioCajaBo.agregarCaja(request, conteoManualCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/CerrarUsuarioCajaAjax.do")
	public RespuestaServiceValidator cerrarCajaLocal(HttpServletRequest request, ModelMap model, @ModelAttribute ConteoManualCommand conteoManualCommand, BindingResult result, SessionStatus status) throws Exception {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());

			}

			return usuarioCajaBo.cerrarCajaCajero(request, conteoManualCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/MostrarUsuarioCajaAjax.do")
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
}

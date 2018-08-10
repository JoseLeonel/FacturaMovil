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
import com.emprendesoftcr.Bo.MotivoSalidaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.MotivoSalida;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.MotivoSalidaCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MotivoSalidaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Controlar los motivos de salidas por almacen MotivoSalidasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class MotivoSalidasController {

	private static final Function<Object, MotivoSalidaCommand>	TO_COMMAND	= new Function<Object, MotivoSalidaCommand>() {

																																						@Override
																																						public MotivoSalidaCommand apply(Object f) {
																																							return new MotivoSalidaCommand((MotivoSalida) f);
																																						};
																																					};

	@Autowired
	private DataTableBo																					dataTableBo;

	@Autowired
	private MotivoSalidaBo																			motivoSalidaBo;

	

	@Autowired
	private UsuarioBo																						usuarioBo;

	@Autowired
	private MotivoSalidaPropertyEditor													motivoSalidaPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor																empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor																stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(MotivoSalida.class, motivoSalidaPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Listar JSP para mostrar las salidas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarMotivoSalidas", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/motivoSalida/ListarMotivoSalidas";
	}

	/**
	 * Listar metodo ajax para mostrar los motivos de salidas del inventario
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarMotivoSalidasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "MotivoSalida");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Lista de motivos de salidas por empresa
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarMotivoSalidasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listaraActivasAjax(HttpServletRequest request, HttpServletResponse response) {
		DataTableDelimitador delimitadores = null;
			delimitadores = new DataTableDelimitador(request, "MotivoSalida");
			JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
			String nombreUsuario = request.getUserPrincipal().getName();
			 dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar metodo ajax para los motivos de salidas
	 * @param request
	 * @param model
	 * @param motivoSalida
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AgregarMotivoSalidaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoSalida motivoSalida, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			MotivoSalida motivoSalidaBd = motivoSalidaBo.buscarPorDescripcionYEmpresa(motivoSalida.getDescripcion(), usuarioSesion.getEmpresa());
			if (motivoSalidaBd != null) {
				result.rejectValue("descripcion", "error.motivoSalida.descripcion.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			motivoSalida.setEmpresa(usuarioSesion.getEmpresa());
			motivoSalida.setCreated_at(new Date());
			motivoSalida.setUpdated_at(new Date());
		
			motivoSalida.setUsuario(usuarioSesion);
			motivoSalidaBo.agregar(motivoSalida);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoSalida.agregar.correctamente", motivoSalida);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una motivoSalida
	 * @param request
	 * @param model
	 * @param motivoSalida
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ModificarMotivoSalidaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoSalida motivoSalida, BindingResult result, SessionStatus status) throws Exception {
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("motivoSalida.no.modificado", result.getAllErrors());
			}
			MotivoSalida motivoSalidaBD = motivoSalidaBo.buscar(motivoSalida.getId());

			if (motivoSalidaBD == null) {
				return RESPONSES.ERROR.MOTIVOSALIDA.NO_EXISTE;
			} else {
				MotivoSalida motivoSalidaValidar = null;
				if (!motivoSalida.getDescripcion().equals(motivoSalidaBD.getDescripcion())) {
					motivoSalidaValidar = motivoSalidaBo.buscarPorDescripcionYEmpresa(motivoSalida.getDescripcion(), usuarioSesion.getEmpresa());
					if (motivoSalidaValidar != null) {
						result.rejectValue("descripcion", "error.motivoSalida.descripcion.existe");
					}
				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				motivoSalidaBD.setDescripcion(motivoSalida.getDescripcion());
				motivoSalidaBD.setUpdated_at(new Date());
				motivoSalidaBD.setUsuario(usuarioSesion);
				motivoSalidaBD.setEstado(motivoSalida.getEstado());
				motivoSalidaBo.modificar(motivoSalidaBD);
				
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoSalida.modificado.correctamente", motivoSalidaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar la motivoSalida
	 * @param request
	 * @param model
	 * @param motivoSalida
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/MostrarMotivoSalidaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoSalida motivoSalida, BindingResult result, SessionStatus status) throws Exception {
		try {
			MotivoSalidaCommand motivoSalidaCommand = new MotivoSalidaCommand(motivoSalidaBo.buscar(motivoSalida.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", motivoSalidaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private static class RESPONSES {

		private static class OK {

			private static class MOTIVOSALIDA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoSalida.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoSalida.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class MOTIVOSALIDA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.motivoSalida.noExiste");
			}
		}
	}

}

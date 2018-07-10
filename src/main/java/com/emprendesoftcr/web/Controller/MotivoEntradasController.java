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
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Bo.MotivoEntradaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.MotivoEntrada;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.MotivoEntradaCommand;
import com.emprendesoftcr.web.componentes.EmpresaPropertyEditor;
import com.emprendesoftcr.web.componentes.MotivoEntradaPropertyEditor;
import com.emprendesoftcr.web.componentes.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Controlar los motivos de entrada por almacen MotivoEntradasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class MotivoEntradasController {

	private static final Function<Object, MotivoEntradaCommand>	TO_COMMAND	= new Function<Object, MotivoEntradaCommand>() {

																																						@Override
																																						public MotivoEntradaCommand apply(Object f) {
																																							return new MotivoEntradaCommand((MotivoEntrada) f);
																																						};
																																					};

	@Autowired
	private DataTableBo																					dataTableBo;

	@Autowired
	private MotivoEntradaBo																			motivoEntradaBo;

	@Autowired
	private UsuarioBo																						usuarioBo;

	@Autowired
	private MotivoEntradaPropertyEditor													motivoEntradaPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor																empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor																stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(MotivoEntrada.class, motivoEntradaPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarMotivoEntradas", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/motivoEntrada/ListarMotivoEntradas";
	}

	/**
	 * Buscar los motivos de salidas activas por empresa
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarMotivoActivasEntradasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "MotivoEntrada");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		String nombreUsuario = request.getUserPrincipal().getName();
		dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Listar de los motivos de entradas
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarMotivoEntradasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "MotivoEntrada");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarMotivoEntradaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoEntrada motivoEntrada, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			MotivoEntrada motivoEntradaBd = motivoEntradaBo.buscarPorDescripcionYEmpresa(motivoEntrada.getDescripcion(), usuarioSesion.getEmpresa());
			if (motivoEntradaBd != null) {
				result.rejectValue("descripcion", "error.motivoEntrada.descripcion.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			motivoEntrada.setCreated_at(new Date());
			motivoEntrada.setUpdated_at(new Date());
			
			motivoEntrada.setUsuario(usuarioSesion);
			motivoEntrada.setEmpresa(usuarioSesion.getEmpresa());
			motivoEntradaBo.agregar(motivoEntrada);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoEntrada.agregar.correctamente", motivoEntrada);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una motivoEntrada
	 * @param request
	 * @param model
	 * @param motivoEntrada
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarMotivoEntradaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoEntrada motivoEntrada, BindingResult result, SessionStatus status) throws Exception {
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("motivoEntrada.no.modificado", result.getAllErrors());
			}
			MotivoEntrada motivoEntradaBD = motivoEntradaBo.buscar(motivoEntrada.getId());

			if (motivoEntradaBD == null) {
				return RESPONSES.ERROR.MOTIVOENTRADA.NO_EXISTE;
			} else {
				MotivoEntrada motivoEntradaValidar = null;
				if (!motivoEntrada.getDescripcion().equals(motivoEntradaBD.getDescripcion())) {
					motivoEntradaValidar = motivoEntradaBo.buscarPorDescripcionYEmpresa(motivoEntrada.getDescripcion(), usuarioSesion.getEmpresa());
					if (motivoEntradaValidar != null) {
						result.rejectValue("descripcion", "error.motivoEntrada.descripcion.existe");
					}
				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				motivoEntradaBD.setDescripcion(motivoEntrada.getDescripcion());
				motivoEntradaBD.setUpdated_at(new Date());
				motivoEntradaBD.setUsuario(usuarioSesion);
				motivoEntradaBD.setEstado(motivoEntrada.getEstado());
				motivoEntradaBo.modificar(motivoEntradaBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoEntrada.modificado.correctamente", motivoEntradaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar la motivoEntrada
	 * @param request
	 * @param model
	 * @param motivoEntrada
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarMotivoEntradaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute MotivoEntrada motivoEntrada, BindingResult result, SessionStatus status) throws Exception {
		try {
			MotivoEntradaCommand motivoEntradaCommand = new MotivoEntradaCommand(motivoEntradaBo.buscar(motivoEntrada.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", motivoEntradaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class MOTIVOENTRADA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoEntrada.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("motivoEntrada.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class MOTIVOENTRADA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.motivoEntrada.noExiste");
			}
		}
	}

}

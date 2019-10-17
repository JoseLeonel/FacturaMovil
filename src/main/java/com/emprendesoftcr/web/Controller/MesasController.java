package com.emprendesoftcr.web.Controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import com.emprendesoftcr.Bo.MesaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Mesa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.MesaCommand;
import com.emprendesoftcr.web.command.ParametrosPaginacionMesa;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MesaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Control de mesas MesasController.
 * @author jose.
 * @since 4 sep. 2018
 */
@Controller
public class MesasController {

	private static final Function<Object, MesaCommand>	TO_COMMAND	= new Function<Object, MesaCommand>() {

																																		@Override
																																		public MesaCommand apply(Object f) {
																																			return new MesaCommand((Mesa) f);
																																		};
																																	};

	@Autowired
	private DataTableBo																	dataTableBo;

	@Autowired
	private MesaBo																			mesaBo;

	@Autowired
	private UsuarioBo																		usuarioBo;

	@Autowired
	private EmpresaPropertyEditor												empresaPropertyEditor;

	@Autowired
	private MesaPropertyEditor													mesaPropertyEditor;

	@Autowired
	private StringPropertyEditor												stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Mesa.class, mesaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Mostrar el JSP de las marcas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarMesas", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/mesas/ListarMesas";
	}

	/**
	 * Listar metodo ajax para mostrar las lista de las mesas
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = "/ListarMesasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Mesa");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarPaginacionMesasAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarPaginacionMesasAjax(HttpServletRequest request, ModelMap model, @ModelAttribute ParametrosPaginacionMesa parametrosPaginacionMesa) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Mesa");
		
		if(delimitadores.getColumnData() == null) {
			delimitadores.setColumnData("prioridad");
			delimitadores.setColumnOrderDir("asc");			
		}
		
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter mesaFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(mesaFilter);
		delimitadores.setLength(parametrosPaginacionMesa.getCantidadPorPagina());
		delimitadores.setStart(parametrosPaginacionMesa.getPaginaActual());
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	} 


	/**
	 * Listar las mesas activas por empresa
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value = "/ListarMesasActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarMesasActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Mesa");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
			dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}
	
	@RequestMapping(value = "/AgregarMesaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Mesa mesa, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Mesa mesaBD = mesaBo.findByDescripcionAndEmpresa(mesa.getDescripcion(), usuario.getEmpresa());
			if (mesaBD != null) {
				result.rejectValue("descripcion", "error.mesa.descripcion.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			mesa.setEmpresa(usuario.getEmpresa());
			mesa.setCreated_at(new Date());
			mesa.setUpdated_at(new Date());

			mesaBo.agregar(mesa);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mesa.agregar.correctamente", mesa);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@RequestMapping(value = "/ModificarMesaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Mesa mesa, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("caja.no.modificado", result.getAllErrors());
			}
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Mesa mesaBD = mesaBo.buscar(mesa.getId());

			if (mesaBD == null) {
				return RESPONSES.ERROR.CAJA.NO_EXISTE;
			} else {
				Mesa mesaValidar = null;
				if (!mesa.getDescripcion().equals(mesaBD.getDescripcion())) {
					mesaValidar = mesaBo.findByDescripcionAndEmpresa(mesa.getDescripcion(), usuario.getEmpresa());
					if (mesaValidar != null) {
						result.rejectValue("descripcion", "error.mesa.descripcion.existe");
					}
				}

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				mesaBD.setDescripcion(mesa.getDescripcion());
				mesaBD.setUpdated_at(new Date());
				mesaBD.setEstado(mesa.getEstado());
				mesaBD.setPrioridad(mesa.getPrioridad());
				mesaBD.setImpuestoServicio(mesa.getImpuestoServicio());
				mesaBo.modificar(mesaBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mesa.modificado.correctamente", mesaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@RequestMapping(value = "/MostrarMesaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Mesa mesa, BindingResult result, SessionStatus status) throws Exception {
		try {
			MesaCommand mesaCommand = new MesaCommand(mesaBo.buscar(mesa.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", mesaCommand);
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

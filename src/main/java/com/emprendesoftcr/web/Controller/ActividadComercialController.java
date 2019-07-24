package com.emprendesoftcr.web.Controller;

import java.util.Collection;

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
import com.emprendesoftcr.Bo.EmpresaActividadComercialBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.EmpresaActividadComercial;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.EmpresaActividadComercialCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaActividadComercialPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Marcas de los articulos MarcasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class ActividadComercialController {

	private static final Function<Object, EmpresaActividadComercialCommand>	TO_COMMAND_POR_EMPRESA	= new Function<Object, EmpresaActividadComercialCommand>() {

																																																		@Override
																																																		public EmpresaActividadComercialCommand apply(Object f) {
																																																			return new EmpresaActividadComercialCommand((EmpresaActividadComercial) f);
																																																		};
																																																	};

	@Autowired
	private DataTableBo																											dataTableBo;

	@Autowired
	private UsuarioBo																												usuarioBo;

	@Autowired
	private EmpresaActividadComercialBo																			empresaActividadComercialBo;

	@Autowired
	private EmpresaActividadComercialPropertyEditor													empresaActividadComercialPropertyEditor;

	@Autowired
	private StringPropertyEditor																						stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(EmpresaActividadComercial.class, empresaActividadComercialPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarActividadComercial", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/actividadComercial/ListarActividadComercial";
	}

	@RequestMapping(value = "/ListarActividadComercialPorEmpresa", method = RequestMethod.GET)
	public String listarPorEmpresa(ModelMap model) {
		return "views/actividadComercial/ListarActividadComercialPorEmpresa";
	}

	@RequestMapping(value = "/ListarEmpresaActividadComercialAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable<?> listarEmpresaAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		delimitadores = new DataTableDelimitador(request, "EmpresaActividadComercial");
		if (delimitadores.getColumnData() == null) {
			// Se ordena por prioridad por defecto se crearon en 9999
			delimitadores.setColumnData("principal");
			delimitadores.setColumnOrderDir("desc");
		}
		JqGridFilter dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_POR_EMPRESA);
	}

	@RequestMapping(value = "/ListaEmpresaActividadComercialPorPricipalAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarEmpresa(HttpServletRequest request, HttpServletResponse response) throws Exception {

			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Collection<EmpresaActividadComercial> listar = empresaActividadComercialBo.findAll(usuario.getEmpresa());
			
			respuestaService.setRecordsTotal(listar != null ? (long) listar.size() : Constantes.ZEROS_LONG);
			respuestaService.setRecordsFiltered(listar != null ? (long) listar.size() : Constantes.ZEROS_LONG);
			respuestaService.setAaData(listar);
			return respuestaService;
		
	}

	@RequestMapping(value = "/AgregarEmpresaActividadComercialAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator<?> agregarEmpresa(HttpServletRequest request, ModelMap model, @ModelAttribute EmpresaActividadComercialCommand empresaActividadComercialCommand, BindingResult result, SessionStatus status) throws Exception {

		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			EmpresaActividadComercial empresaActividadComercialValida = empresaActividadComercialBo.findByCodigo(empresaActividadComercialCommand.getCodigo(), usuario.getEmpresa());
			if (empresaActividadComercialValida != null) {
				result.rejectValue("codigo", "error.empresaActividadComercial.codigo.existe");
			}
			if (empresaActividadComercialCommand.getPrincipal().equals(Constantes.ACTIVIDAD_COMERCIAL_PRINCIPAL)) {
				empresaActividadComercialValida = null;
				empresaActividadComercialValida = empresaActividadComercialBo.findByPrincipal(empresaActividadComercialCommand.getPrincipal(), usuario.getEmpresa());
				if (empresaActividadComercialValida != null) {
					result.rejectValue("codigo", "error.empresaActividadComercial.principal.existe");
				}

			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			EmpresaActividadComercial empresaActividadComercial = new EmpresaActividadComercial();
			if (empresaActividadComercialCommand.getDescripcion() != null) {
				if (empresaActividadComercialCommand.getDescripcion().length() > 180) {
					empresaActividadComercial.setDescripcion(empresaActividadComercialCommand.getDescripcion().substring(0, 180));
				} else {
					empresaActividadComercial.setDescripcion(empresaActividadComercialCommand.getDescripcion());
				}
			}

			empresaActividadComercial.setId(null);
			empresaActividadComercial.setCodigo(empresaActividadComercialCommand.getCodigo());
			empresaActividadComercial.setEmpresa(usuario.getEmpresa());
			empresaActividadComercial.setPrincipal(empresaActividadComercialCommand.getPrincipal());
			empresaActividadComercialBo.agregar(empresaActividadComercial);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("actividadComercial.agregar.correctamente", empresaActividadComercial);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@RequestMapping(value = "/ModificarEmpresaActividadComercialAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator<?> modificarEmpresa(HttpServletRequest request, ModelMap model, @ModelAttribute EmpresaActividadComercialCommand empresaActividadComercialCommand, BindingResult result, SessionStatus status) throws Exception {

		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			EmpresaActividadComercial empresaActividadComercialBd = empresaActividadComercialBo.findByCodigo(empresaActividadComercialCommand.getCodigo(), usuario.getEmpresa());
			if (empresaActividadComercialBd == null) {
				result.rejectValue("codigo", "error.empresaActividadComercial.codigo.existe");
			}
			if (empresaActividadComercialCommand.getPrincipal().equals(Constantes.ACTIVIDAD_COMERCIAL_PRINCIPAL)) {

				EmpresaActividadComercial empresaActividadComercialPrincipal = empresaActividadComercialBo.findByPrincipal(empresaActividadComercialCommand.getPrincipal(), usuario.getEmpresa());
				if (empresaActividadComercialPrincipal != null) {
					result.rejectValue("codigo", "error.empresaActividadComercial.principal.existe");
				}

			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			empresaActividadComercialBd.setPrincipal(empresaActividadComercialCommand.getPrincipal());
			empresaActividadComercialBo.agregar(empresaActividadComercialBd);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("actividadComercial.modificado.correctamente", empresaActividadComercialBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class MARCA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("actividadComercial.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("actividadComercial.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class TARIFA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.actividadComercial.noExiste");
			}
		}
	}

}

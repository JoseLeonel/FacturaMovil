package com.emprendesoftcr.web.Controller;

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
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.EmpresaActividadComercial;
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
		delimitadores = new DataTableDelimitador(request, "EmpresaActividadComercial");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_POR_EMPRESA);
	}

	@RequestMapping(value = "/AgregarEmpresaActividadComercialAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator<?> agregarEmpresa(HttpServletRequest request, ModelMap model, @ModelAttribute EmpresaActividadComercialCommand empresaActividadComercialCommand, BindingResult result, SessionStatus status) throws Exception {

		try {

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			EmpresaActividadComercial empresaActividadComercial = new EmpresaActividadComercial();
			empresaActividadComercial.setId(null);
			empresaActividadComercial.setCodigo(empresaActividadComercialCommand.getCodigo());
			empresaActividadComercial.setDescripcion(empresaActividadComercialCommand.getDescripcion());
			empresaActividadComercial.setPrincipal(empresaActividadComercialCommand.getPrincipal());
			empresaActividadComercialBo.agregar(empresaActividadComercial);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("actividadComercial.agregar.correctamente", empresaActividadComercial);

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

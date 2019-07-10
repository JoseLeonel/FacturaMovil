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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.ActividadComercialBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.ActividadComercial;
import com.emprendesoftcr.modelo.Tarifa;
import com.emprendesoftcr.web.command.ActividadComercialCommand;
import com.emprendesoftcr.web.propertyEditor.ActividadComercialPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Marcas de los articulos MarcasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class ActividadComercialController {

	private static final Function<Object, ActividadComercialCommand>	TO_COMMAND	= new Function<Object, ActividadComercialCommand>() {

																																									@Override
																																									public ActividadComercialCommand apply(Object f) {
																																										return new ActividadComercialCommand((ActividadComercial) f);
																																									};
																																								};

	@Autowired
	private DataTableBo																								dataTableBo;

	@Autowired
	private ActividadComercialBo																			actividadComercialBo;

	@Autowired
	private ActividadComercialPropertyEditor													actividadComercialPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ActividadComercial.class, actividadComercialPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarActividadComercial", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/actividadComercial/ListarActividadComercial";
	}

	@RequestMapping(value = "/ListarActividadComercialAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "ActividadComercial");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@RequestMapping(value = "/AgregarActividadComercialAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute ActividadComercial actividadComercial, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			actividadComercial.setId(null);

			actividadComercialBo.agregar(actividadComercial);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("actividadComercial.agregar.correctamente", actividadComercial);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@RequestMapping(value = "/MostrarActividadComercialAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute ActividadComercial actividadComercial, BindingResult result, SessionStatus status) throws Exception {
		try {
			ActividadComercialCommand actividadComercialCommand = new ActividadComercialCommand(actividadComercialBo.buscar(actividadComercial.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", actividadComercialCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@RequestMapping(value = "/ModificarActividadComercialAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute ActividadComercial actividadComercial, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("actividadComercial.no.modificado", result.getAllErrors());
			}
			ActividadComercial actividadComercialBD = actividadComercialBo.buscar(actividadComercial.getId());

			if (actividadComercialBD == null) {
				return RESPONSES.ERROR.TARIFA.NO_EXISTE;
			} else {

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				actividadComercialBD.setCodigoActividadComercial(actividadComercial.getCodigoActividadComercial());
				actividadComercialBD.setDescripcion(actividadComercial.getDescripcion());
				actividadComercialBo.modificar(actividadComercialBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("actividadComercial.modificado.correctamente", actividadComercialBD);
			}

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

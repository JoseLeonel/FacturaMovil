package com.emprendesoftcr.web.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.TarifaBo;
import com.emprendesoftcr.modelo.Tarifa;
import com.emprendesoftcr.modelo.TarifaIVAI;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.TarifaCommand;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.TarifaIVAIPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.TarifaPropertyEditor;
import com.google.common.base.Function;

/**
 * Marcas de los articulos MarcasController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class TarifaController {

	private static final Function<Object, TarifaCommand>	TO_COMMAND	= new Function<Object, TarifaCommand>() {

																																			@Override
																																			public TarifaCommand apply(Object f) {
																																				return new TarifaCommand((Tarifa) f);
																																			};
																																		};

	@Autowired
	private DataTableBo																		dataTableBo;

	@Autowired
	private TarifaBo																			tarifaBo;



	@Autowired
	private TarifaPropertyEditor													tarifaPropertyEditor;

	@Autowired
	private TarifaIVAIPropertyEditor											tarifaIVAIPropertyEditor;

	@Autowired
	private StringPropertyEditor													stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(TarifaIVAI.class, tarifaIVAIPropertyEditor);
		binder.registerCustomEditor(Tarifa.class, tarifaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Mostrar el JSP de las marcas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarTarifas", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/tarifas/ListarTarifas";
	}

	/**
	 * Listar metodo ajax para mostrar las lista de las masrcas
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Cacheable(value="tarifasCache")
	@RequestMapping(value = "/ListarTarifasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Tarifa");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}
	@SuppressWarnings("rawtypes")
	@CacheEvict(value="tarifasCache",allEntries=true)
	@RequestMapping(value = "/AgregarTarifaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Tarifa tarifa, BindingResult result, SessionStatus status) throws Exception {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			tarifa.setId(null);
      tarifa.setMonto(tarifa.getTarifaIVAI().getMonto());
      
			tarifaBo.agregar(tarifa);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tarifa.agregar.correctamente", tarifa);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarTarifaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Tarifa tarifa, BindingResult result, SessionStatus status) throws Exception {
		try {
			TarifaCommand tarifaCommand = new TarifaCommand(tarifaBo.buscar(tarifa.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", tarifaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ModificarTarifaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Tarifa tarifa, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("tarifa.no.modificado", result.getAllErrors());
			}
			Tarifa tarifaBD = tarifaBo.buscar(tarifa.getId());

			if (tarifaBD == null) {
				return RESPONSES.ERROR.TARIFA.NO_EXISTE;
			} else {

				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				tarifaBD.setTarifaIVAI(tarifa.getTarifaIVAI());
				tarifaBD.setTipoImpuesto(tarifa.getTipoImpuesto());
				tarifaBD.setMonto(tarifa.getTarifaIVAI().getMonto());
				tarifaBo.modificar(tarifaBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tarifa.modificado.correctamente", tarifaBD);
			}

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarTarifasByTipoImpuestoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarByTipoImpuestoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String tipoImpuesto) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Tarifa");

		JqGridFilter dataTableFilter = new JqGridFilter("tipoImpuesto", "'" + tipoImpuesto + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class MARCA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tarifa.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("tarifa.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class TARIFA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.tarifa.noExiste");
			}
		}
	}

}

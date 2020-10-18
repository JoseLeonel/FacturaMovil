package com.emprendesoftcr.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.ProvinciaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.modelo.Barrio;
import com.emprendesoftcr.modelo.Canton;
import com.emprendesoftcr.modelo.Distrito;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Provincia;
import com.emprendesoftcr.web.command.BarriosCommand;
import com.emprendesoftcr.web.command.CantonCommand;
import com.emprendesoftcr.web.command.DistritoCommand;
import com.emprendesoftcr.web.command.ProvinciaCommand;
import com.emprendesoftcr.web.propertyEditor.ProvinciaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Cajas por empresa CajasController.
 * @author jose.
 * @since 11 jun. 2018
 */
@Controller
public class ProvinciasController {

	private static final Function<Object, ProvinciaCommand>	TO_COMMAND					= new Function<Object, ProvinciaCommand>() {

																																								@Override
																																								public ProvinciaCommand apply(Object f) {
																																									return new ProvinciaCommand((Provincia) f);
																																								};
																																							};

	private static final Function<Object, CantonCommand>		TO_COMMAND_CANTON		= new Function<Object, CantonCommand>() {

																																								@Override
																																								public CantonCommand apply(Object f) {
																																									return new CantonCommand((Canton) f);
																																								};
																																							};

	private static final Function<Object, DistritoCommand>	TO_COMMAND_DISTRITO	= new Function<Object, DistritoCommand>() {

																																								@Override
																																								public DistritoCommand apply(Object f) {
																																									return new DistritoCommand((Distrito) f);
																																								};

																																							};

	private static final Function<Object, BarriosCommand>		TO_COMMAND_BARRIO		= new Function<Object, BarriosCommand>() {

																																								@Override
																																								public BarriosCommand apply(Object f) {
																																									return new BarriosCommand((Barrio) f);
																																								};
																																							};

	@Autowired
	private DataTableBo																			dataTableBo;

	@Autowired
	private ProvinciaBo																			provinciaBo;

	@Autowired
	private ProvinciaPropertyEditor													provinciaPropertyEditor;

	@Autowired
	private StringPropertyEditor														stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Empresa.class, provinciaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Lista de provincias
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarProvinciasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Provincia");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Busca provincia
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/BuscaProvinciaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Provincia buscaProvinciaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer codigoProvincia) {
		return provinciaBo.findByCodigo(codigoProvincia);
	}

	/**
	 * Busca canton
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/BuscaCantonAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Canton buscaCantonAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer codigoProvincia, @RequestParam Integer codigoCanton) {
		return provinciaBo.findCantonByCodigo(codigoProvincia, codigoCanton);
	}

	/**
	 * Busca distrito
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/BuscaDistritoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Distrito buscaDistritoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer codigoProvincia, @RequestParam Integer codigoCanton, @RequestParam Integer codigoDistrito) {
		return provinciaBo.findDistritoByCodigo(codigoProvincia, codigoCanton, codigoDistrito);
	}

	/**
	 * Lista de los cantones por provincia
	 * @param request
	 * @param response
	 * @param model
	 * @param provincia
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarCantonesAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarCatonesAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Provincia provincia) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Canton");
		JqGridFilter dataTableFilter = null;
		if (provincia != null) {
			dataTableFilter = new JqGridFilter("codigo_provincia", "'" + provincia.getCodigo() !=null?provincia.getCodigo().toString():Constantes.EMPTY + "'", "=");
		}

		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_CANTON);
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarCantonesTodosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarCatonesTodosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Canton");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_CANTON);
	}

	/**
	 * Distritos
	 * @param request
	 * @param response
	 * @param model
	 * @param canton
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarDistritosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarDistritosAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Canton canton) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Distrito");
		JqGridFilter dataTableFilter = null;
		if (canton != null) {
			dataTableFilter = new JqGridFilter("codigoCanton", "'" + canton.getCodigo() !=null?canton.getCodigo().toString():Constantes.EMPTY + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
			dataTableFilter = new JqGridFilter("codigoProvincia", "'" + canton.getCodigo_provincia() !=null?canton.getCodigo_provincia().toString():Constantes.EMPTY + "'", "=");
			delimitadores.addFiltro(dataTableFilter);

		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_DISTRITO);
	}

	/**
	 * @param request
	 * @param response
	 * @param model
	 * @param canton
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarDistritosTodosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarDistritosTodosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Distrito");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_DISTRITO);
	}

	/**
	 * Barrios
	 * @param request
	 * @param response
	 * @param model
	 * @param distrito
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarBarriosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarBarriosAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Distrito distrito) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Barrio");

		JqGridFilter dataTableFilter = new JqGridFilter("codigoCanton", "'" + distrito.getCodigoCanton().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("codigoProvincia", "'" + distrito.getCodigoProvincia().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("codigoDistrito", "'" + distrito.getCodigo() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_BARRIO);
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarBarriosTodosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarBarriosTodosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Barrio");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_BARRIO);
	}
}

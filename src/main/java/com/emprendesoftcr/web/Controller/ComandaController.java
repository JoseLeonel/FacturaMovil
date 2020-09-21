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

import com.emprendesoftcr.Bo.ComandaEmpresaBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.ComandaEmpresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

@Controller
public class ComandaController {

	@Autowired
	private DataTableBo dataTableBo;

	@Autowired
	private ComandaEmpresaBo comandaEmpresaBo; 

	@Autowired
	private UsuarioBo usuarioBo;

	@Autowired
	private StringPropertyEditor stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	private static final Function<Object, ComandaEmpresa> TO_COMMAND_COMANDA_EMPRESA = new Function<Object, ComandaEmpresa>() {

		@Override
		public ComandaEmpresa apply(Object f) {
			return (ComandaEmpresa) f;
		};
	};

	/**
	 * Listar metodo ajax para mostrar la lista de comandas pendientes
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarComandasPendientesAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idEmpresa, @RequestParam Integer estado) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "ComandaEmpresa");

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		JqGridFilter dataTableFilter = new JqGridFilter("idEmpresa", "'" + usuario.getEmpresa().getId() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, ComandaController.TO_COMMAND_COMANDA_EMPRESA);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarEmpresaComandaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute ComandaEmpresa comandEmpresa, BindingResult result, SessionStatus status) throws Exception {
		try {
			ComandaEmpresa comandaEmpresa = comandaEmpresaBo.buscar(comandEmpresa.getId());
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", comandaEmpresa);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
}

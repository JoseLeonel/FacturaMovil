package com.emprendesoftcr.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.ComandaBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.ComandaMesa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

@Controller
public class ComandaController {

	@Autowired
	private DataTableBo dataTableBo;

	@Autowired
	private ComandaBo comandaBo;

	@Autowired
	private UsuarioBo usuarioBo;

	@Autowired
	private StringPropertyEditor stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	private static final Function<Object, ComandaMesa> TO_COMMAND_COMANDA_MESA = new Function<Object, ComandaMesa>() {

		@Override
		public ComandaMesa apply(Object f) {
			return (ComandaMesa) f;
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
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idMesa, @RequestParam Integer estado) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "ComandaMesa");

		JqGridFilter dataTableFilter = new JqGridFilter("mesa.id", "'" + idMesa + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		dataTableFilter = new JqGridFilter("idEmpresa", "'" + usuario.getEmpresa().getId() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		if (estado > 0) {
			// Estado pendientes de enviar
			dataTableFilter = new JqGridFilter("estado", "'" + estado + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, ComandaController.TO_COMMAND_COMANDA_MESA);
	}

	/**
	 * Crear la Factura
	 * @param request
	 * @param model
	 * @param facturaCommand
	 * @param result
	 * @param status
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ActualizarOrdenesComandaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator actualizarOrdenesComandaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idMesa) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			comandaBo.actualizarComandaMesaFacturas(idMesa);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("actualizar.ordenes.comanda.correctamente");
		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}
	}
}

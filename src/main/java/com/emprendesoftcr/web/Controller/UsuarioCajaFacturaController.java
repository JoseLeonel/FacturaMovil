package com.emprendesoftcr.web.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.UsuarioCajaFactura;
import com.emprendesoftcr.web.command.UsuarioCajaFacturaCommand;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioCajaFacturaPropertyEditor;
import com.google.common.base.Function;

@Controller
public class UsuarioCajaFacturaController {

	private static final Function<Object, UsuarioCajaFacturaCommand>	TO_COMMAND_CAJAS__FACTURAS	= new Function<Object, UsuarioCajaFacturaCommand>() {

																																																	@Override
																																																	public UsuarioCajaFacturaCommand apply(Object f) {
																																																		return new UsuarioCajaFacturaCommand((UsuarioCajaFactura) f);
																																																	};
																																																};

	@Autowired
	private DataTableBo																								dataTableBo;

	
	@Autowired
	private UsuarioCajaFacturaPropertyEditor													usuarioCajaFacturaPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(UsuarioCajaFactura.class, usuarioCajaFacturaPropertyEditor);

		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarUsuariosCajasFacturasNoAnuladasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarUsuariosCajasNoAnuladasAjax(HttpServletRequest request, ModelMap model, @ModelAttribute UsuarioCaja usuarioCaja) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "UsuarioCajaFactura");
		JqGridFilter dataTableFilter = null;
		dataTableFilter = new JqGridFilter("usuarioCaja.id", "'" + usuarioCaja.getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "!=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_PROFORMAS.toString() + "'", "!=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_ANULADA.toString() + "'", "!=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("factura.estado", "'" + Constantes.FACTURA_ESTADO_ANULADA_PROFORMA.toString() + "'", "!=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_CAJAS__FACTURAS);
	}

}

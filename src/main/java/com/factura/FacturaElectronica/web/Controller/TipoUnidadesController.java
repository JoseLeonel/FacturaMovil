package com.factura.FacturaElectronica.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.factura.FacturaElectronica.modelo.TipoUnidad;
import com.factura.FacturaElectronica.web.command.TipoUnidadCommand;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Controlar los tipos de unidades TipoUnidadesController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class TipoUnidadesController {

	private static final Function<Object, TipoUnidadCommand>	TO_COMMAND	= new Function<Object, TipoUnidadCommand>() {

																																					@Override
																																					public TipoUnidadCommand apply(Object f) {
																																						return new TipoUnidadCommand((TipoUnidad) f);
																																					};
																																				};

	@Autowired
	private DataTableBo																				dataTableBo;

	@Autowired
	private StringPropertyEditor															stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Metodo ajax para retornar la lista de tipos de unidades
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarTipoUnidadesAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "TipoUnidad");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

}

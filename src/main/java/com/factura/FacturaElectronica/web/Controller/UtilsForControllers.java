package com.factura.FacturaElectronica.web.Controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * UtilsForControllers.
 * @author Leonel Hernandez Chaverri.
 * @since 12 ene. 2018
 */
class UtilsForControllers {

	@SuppressWarnings("all")
	static RespuestaServiceDataTable process(HttpServletRequest request, DataTableBo bo, DataTableDelimitador delimitadores, Function f) {
		Long total = bo.contar(delimitadores);
		Collection<Object> objetos = new ArrayList<Object>();
		objetos = Collections2.transform(bo.listar(delimitadores), f);
		return new RespuestaServiceDataTable.Builder(request, objetos, total).build();
	}
}

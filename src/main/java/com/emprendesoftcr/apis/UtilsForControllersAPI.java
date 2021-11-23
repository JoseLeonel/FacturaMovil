package com.emprendesoftcr.apis;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * UtilsForControllers.
 * @author Leonel Hernandez Chaverri.
 * @since 12 ene. 2018
 */
class UtilsForControllersAPI {

	@SuppressWarnings("all")
	static RespuestaServiceDataTable process(HttpServletRequest request, DataTableBo bo, DataTableDelimitador delimitadores, Function f) {
		Long total = bo.contar(delimitadores);
		Collection<Object> objetos = new ArrayList<Object>();
		objetos = Collections2.transform(bo.listar(delimitadores), f);
		
		return new RespuestaServiceDataTable.Builder(request, objetos, total).build();
	}
}

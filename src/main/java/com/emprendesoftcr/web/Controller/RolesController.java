package com.emprendesoftcr.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.modelo.Rol;
import com.emprendesoftcr.web.command.RolCommand;
import com.google.common.base.Function;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class RolesController {

	private static final Function<Object, RolCommand>	TO_COMMAND	= new Function<Object, RolCommand>() {

																																	@Override
																																	public RolCommand apply(Object f) {
																																		return new RolCommand((Rol) f);
																																	};
																																};

	@Autowired
	private DataTableBo																dataTableBo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	}

	@RequestMapping(value = "/ListarRolesAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idCuentaCobrar) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Rol");

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

}

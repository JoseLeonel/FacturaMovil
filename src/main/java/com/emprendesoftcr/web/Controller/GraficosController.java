package com.emprendesoftcr.web.Controller;

import java.time.Year;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.modelo.GraficoVenta;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.GraficoCommand;
import com.google.common.base.Function;

@Controller

public class GraficosController {

	private static final Function<Object, GraficoCommand>	TO_COMMAND	= new Function<Object, GraficoCommand>() {

																																			@Override
																																			public GraficoCommand apply(Object f) {
																																				return new GraficoCommand((GraficoVenta) f);
																																			};
																																		};

	@Autowired
	private DataTableBo																		dataTableBo;

	@Autowired
	private UsuarioBo																			usuarioBo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}



	@SuppressWarnings("all")
	@Cacheable(value="GraficosVentas")
	@RequestMapping(value = "/GraficoVentasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable graficoVentasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		JqGridFilter dataTableFilter = new JqGridFilter("empresa.id", "'" + usuario.getEmpresa().getId() + "'", "=");
		if (usuarioBo.isAdministrador_sistema(usuario) || usuarioBo.isAdministrador_empresa(usuario) || usuarioBo.isAdministrador_restaurante(usuario)) {
			delimitadores = new DataTableDelimitador(request, "GraficoVenta");

			delimitadores.addFiltro(dataTableFilter);
			Year anno = Year.now(); 
			dataTableFilter = new JqGridFilter("anno", "'" + anno.getValue() + "'", "=");
			
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}


}

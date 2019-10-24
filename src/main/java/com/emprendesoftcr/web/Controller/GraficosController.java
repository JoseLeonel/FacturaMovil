package com.emprendesoftcr.web.Controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.modelo.GraficoVenta;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.sqlNativo.ArticuloMinimoNative;
import com.emprendesoftcr.modelo.sqlNativo.GraficoArticuloMasVendidoNative;
import com.emprendesoftcr.modelo.sqlNativo.GraficoCuentasPorCobrarNative;
import com.emprendesoftcr.modelo.sqlNativo.GraficoCuentasPorPagarNative;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.command.CuentaCobrarCommand;
import com.emprendesoftcr.web.command.CuentaPagarCommand;
import com.emprendesoftcr.web.command.GraficoArticuloMasVendidoCommand;
import com.emprendesoftcr.web.command.GraficoCommand;
import com.google.common.base.Function;

/**
 * Consulta de los graficos
 * GraficosController.
 * @author jose.
 * @since 24 oct. 2019
 */
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
	
	@Autowired
	ConsultasNativeBo																						consultasNativeBo;


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


	@SuppressWarnings("all")
	@RequestMapping(value = "/GraficoCuentasXCobrarAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable graficoCuentasXCobrarAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<GraficoCuentasPorCobrarNative>  objetos = consultasNativeBo.findByGraficoCuentasXCobrar(usuarioSesion.getEmpresa());
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (GraficoCuentasPorCobrarNative graficoCuentasPorCobrarNative : objetos) {
				if (graficoCuentasPorCobrarNative.getId().longValue() > 0L) {
					solicitudList.add(new CuentaCobrarCommand(graficoCuentasPorCobrarNative));
				}
			}
		}
		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}
	

	@SuppressWarnings("all")
	@RequestMapping(value = "/GraficoCuentasXPagarAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable graficoCuentasXPagarAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<GraficoCuentasPorPagarNative>  objetos = consultasNativeBo.findByGraficoCuentasXPagar(usuarioSesion.getEmpresa());
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (GraficoCuentasPorPagarNative graficoCuentasPorPagarNative : objetos) {
				if (graficoCuentasPorPagarNative.getId().longValue() > 0L) {
					solicitudList.add(new CuentaPagarCommand(graficoCuentasPorPagarNative));
				}
			}
		}
		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}
	
	@SuppressWarnings("all")
	@RequestMapping(value = "/GraficoArticuloMasVendidoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable graficoArticuloMasVendiddoAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<GraficoArticuloMasVendidoNative>  objetos = consultasNativeBo.findByGraficoArticuloMasVendido(usuarioSesion.getEmpresa());
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (GraficoArticuloMasVendidoNative graficoArticuloMasVendidoNative : objetos) {
					solicitudList.add(new GraficoArticuloMasVendidoCommand(graficoArticuloMasVendidoNative));
			}
		}
		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}
	
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticuloMinimoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarArticulosMinimoAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<ArticuloMinimoNative>  objetos = consultasNativeBo.findByAllArticulosMinimo(usuarioSesion.getEmpresa());
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (ArticuloMinimoNative articuloMinimoNative : objetos) {
				if (articuloMinimoNative.getId().longValue() > 0L) {
					solicitudList.add(new ArticuloCommand(articuloMinimoNative));
				}
			}
		}
		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}
	
}

package com.emprendesoftcr.apis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNative;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaNativeByEmpresaAndFechaAndCliente;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.HaciendaNativeCommand;
import com.emprendesoftcr.web.propertyEditor.FechaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;

@CrossOrigin
@RestController
@RequestMapping("/api/hacienda")
public class HaciendaRest {

	
	
	
	@Autowired
	DataTableBo																												dataTableBo;

	
	@Autowired
	private ConsultasNativeBo																					haciendaNativeBo;

	
	@Autowired
	private UsuarioBo																									usuarioBo;

	@Autowired
	private FechaPropertyEditor																				fechaPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, stringPropertyEditor);
		binder.registerCustomEditor(Date.class, fechaPropertyEditor);
	}

	
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/ListarHaciendasAjax.do")
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String cedulaReceptor) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

		Date fechaIniciot = Utils.parseDate(fechaInicio);
		Date fechaFinalt = Utils.parseDate(fechaFin);
		if (fechaIniciot == null) {
			fechaIniciot = new Date(System.currentTimeMillis());
		}
		if (fechaFinalt != null) {
			fechaFinalt = Utils.sumarDiasFecha(fechaFinalt, 1);
		}

		DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		Collection<HaciendaNative> objetos = cedulaReceptor.equals(Constantes.COMBO_TODOS) ? haciendaNativeBo.findByEmpresaAndEstado(usuarioSesion.getEmpresa(), dateFormat.format(fechaIniciot), dateFormat.format(fechaFinalt)) : null;
		Collection<HaciendaNativeByEmpresaAndFechaAndCliente> objetos_1 = objetos == null ? haciendaNativeBo.findByEmpresaAndFechaAndCliente(usuarioSesion.getEmpresa(), dateFormat.format(fechaIniciot), dateFormat.format(fechaFinalt), cedulaReceptor) : null;

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (HaciendaNative haciendaNative : objetos) {

				// no se carga el usuario del sistema el id -1
				if (haciendaNative.getId().longValue() > 0L) {
					solicitudList.add(new HaciendaNativeCommand(haciendaNative));
				}
			}

		}
		if (objetos_1 != null) {
			for (HaciendaNativeByEmpresaAndFechaAndCliente haciendaNativeByEmpresaAndFechaAndCliente : objetos_1) {

				// no se carga el usuario del sistema el id -1
				if (haciendaNativeByEmpresaAndFechaAndCliente.getId().longValue() > 0L) {
					solicitudList.add(new HaciendaNativeCommand(haciendaNativeByEmpresaAndFechaAndCliente));
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

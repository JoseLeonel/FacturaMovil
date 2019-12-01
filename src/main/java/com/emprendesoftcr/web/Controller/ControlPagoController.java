package com.emprendesoftcr.web.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.ControlPago;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.web.command.ControlPagoCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;

/**
 * Cajas por empresa CajasController.
 * @author jose.
 * @since 11 jun. 2018
 */
@Controller
public class ControlPagoController {

	
	@Autowired
	private DataTableBo																	dataTableBo;

	

	@Autowired
	private EmpresaPropertyEditor												empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor												stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Mostrar el JSP de las marcas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarControlPago", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/controlPago/ListarControlPago";
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarControlPagoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "codigoArt", required = false) String codigoArt) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "ControlPago");

		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		for (Iterator<Object> iterator = objetos.iterator(); iterator.hasNext();) {
			ControlPago object = (ControlPago) iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				solicitudList.add(new ControlPagoCommand(object));
			}
		}

		respuestaService.setRecordsTotal(total);
		respuestaService.setRecordsFiltered(total);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AgregarControlPagoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute ControlPagoCommand controlPagoCommand, BindingResult result, SessionStatus status) throws Exception {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
		
//			Caja cajaBd = cajaBo.findByDescripcionAndEmpresa(caja.getDescripcion(), usuario.getEmpresa());
//			if (cajaBd != null) {
//				result.rejectValue("descripcion", "error.caja.descripcion.existe");
//			}

		
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			ControlPago controlPago = new ControlPago();
			controlPago.setEmpresa(controlPagoCommand.getEmpresa());
			controlPago.setEstado(controlPagoCommand.getEstado());
			controlPago.setTipoCambio(controlPagoCommand.getTipoCambio());
			controlPago.setTipoPago(controlPago.getTipoPago());
			controlPago.setTotalColones(controlPagoCommand.getTotalColones());
			controlPago.setTotalDolar(controlPagoCommand.getTotalDolar());
			controlPago.setCantidadNotificacion(Constantes.ZEROS);
			
			
			
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("controlPago.agregar.correctamente", controlPago);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CONTROLPAGO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("caja.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("caja.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CONTROLPAGO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.caja.noExiste");
			}
		}
	}

}

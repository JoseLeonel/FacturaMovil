package com.emprendesoftcr.web.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import com.emprendesoftcr.Bo.ControlPagoBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.ControlPago;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ControlPagoCommand;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;

/**
 * 
 * ControlPagoController.
 * @author jose.
 * @since 4 dic. 2019
 */
@Controller
public class ControlPagoController {

	@Autowired
	private DataTableBo						dataTableBo;

	@Autowired
	private UsuarioBo						usuarioBo;

	@Autowired
	private ControlPagoBo					controlPagoBo;

	@Autowired
	private EmpresaPropertyEditor	empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor	stringPropertyEditor;

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
	@CacheEvict(value="controlPagoCache",allEntries=true)
	@RequestMapping(value = "/AgregarControlPagoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute ControlPagoCommand controlPagoCommand, BindingResult result, SessionStatus status) throws Exception {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
      ControlPago controlPagoBd = controlPagoBo.findByEstadoAndEmpresa(Constantes.CONTROL_PAGO_ESTADO_ACTIVO,controlPagoCommand.getEmpresa());
      if(controlPagoBd != null) {
      	
      	return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("controlPago.error.existe.activo", result.getAllErrors());
      }
			Date fechaLimite = Utils.parseDate(controlPagoCommand.getFechaLimiteT());
			Date fechaPago = Utils.parseDate(controlPagoCommand.getFechaPagoT());
			
			
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			ControlPago controlPago = new ControlPago();
			controlPago.setFechaLimite(fechaLimite);
			controlPago.setFechaPago(fechaPago);
			controlPago.setEmpresa(controlPagoCommand.getEmpresa());
			controlPago.setEstado(controlPagoCommand.getEstado());
			controlPago.setTipoCambio(controlPagoCommand.getTipoCambio());
			controlPago.setTipoPago(controlPagoCommand.getTipoPago());
			controlPago.setTotalColones(controlPagoCommand.getTotalColones());
			controlPago.setTotalDolar(controlPagoCommand.getTotalDolar());
			controlPago.setCantidadNotificacion(Constantes.ZEROS);
			controlPago.setEstado(Constantes.CONTROL_PAGO_ESTADO_ACTIVO);
			controlPago.setMensaje(controlPagoCommand.getMensaje());
			controlPago.setBloqueo(controlPagoCommand.getBloqueo());
			
			controlPagoBo.agregar(controlPago);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("controlPago.agregar.correctamente", controlPago);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@CacheEvict(value="controlPagoCache",allEntries=true)
	@RequestMapping(value = "/ModificarControlPagoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute ControlPagoCommand controlPagoCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("caja.no.modificado", result.getAllErrors());
			}
			ControlPago controlPagoBD = controlPagoBo.buscar(controlPagoCommand.getId());
			Date fechaLimite = Utils.parseDate(controlPagoCommand.getFechaLimiteT());
			Date fechaPago = Utils.parseDate(controlPagoCommand.getFechaPagoT());
	
			if (controlPagoBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("controlPago.error.no.existe", result.getAllErrors());
			} 
				if (result.hasErrors()) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
				}
				controlPagoBD.setFechaLimite(fechaLimite);
				controlPagoBD.setFechaPago(fechaPago);
				controlPagoBD.setEmpresa(controlPagoCommand.getEmpresa());
				controlPagoBD.setEstado(controlPagoCommand.getEstado());
				controlPagoBD.setTipoCambio(controlPagoCommand.getTipoCambio());
				controlPagoBD.setTipoPago(controlPagoCommand.getTipoPago());
				controlPagoBD.setTotalColones(controlPagoCommand.getTotalColones());
				controlPagoBD.setTotalDolar(controlPagoCommand.getTotalDolar());
				controlPagoBD.setCantidadNotificacion(Constantes.ZEROS);
				controlPagoBD.setEstado(controlPagoCommand.getEstado());
				controlPagoBD.setMensaje(controlPagoCommand.getMensaje());
				controlPagoBD.setBloqueo(controlPagoCommand.getBloqueo());
				controlPagoBo.modificar(controlPagoBD);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("controlPago.modificado.correctamente", controlPagoBD);
			

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@Cacheable(value="controlPagoCache")
	@RequestMapping(value = "/ControlPagoEmpresaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Cliente cliente, BindingResult result, SessionStatus status) throws Exception {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			ControlPago controlPago = null;
			if (usuarioBo.isAdministrador_cajero(usuario) || usuarioBo.isAdministrador_empresa(usuario) || usuarioBo.isAdministrador_restaurante(usuario)) {
				   controlPago = controlPagoBo.findByEstadoAndEmpresa(Constantes.CONTROL_PAGO_ESTADO_ACTIVO,usuario.getEmpresa());	
			}
			
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", controlPago);
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

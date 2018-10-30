package com.emprendesoftcr.web.Controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.emprendesoftcr.Bo.AbonoPagarBo;
import com.emprendesoftcr.Bo.CuentaPagarBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Abono;
import com.emprendesoftcr.modelo.AbonoPagar;
import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.AbonoPagarCommand;
import com.emprendesoftcr.web.propertyEditor.AbonoPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.CuentaPagarPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class AbonoPagarController {

	private static final Function<Object, AbonoPagarCommand>	TO_COMMAND	= new Function<Object, AbonoPagarCommand>() {

																																					@Override
																																					public AbonoPagarCommand apply(Object f) {
																																						return new AbonoPagarCommand((AbonoPagar) f);
																																					};
																																				};

	@Autowired
	private DataTableBo																				dataTableBo;

	@Autowired
	private CuentaPagarBo																			cuentaPagarBo;

	@Autowired
	private UsuarioBo																					usuarioBo;

	@Autowired
	private UsuarioCajaBo																			usuarioCajaBo;

	@Autowired
	private AbonoPagarBo																			abonoPagarBo;

	@Autowired
	private CuentaPagarPropertyEditor													cuentaPagarPropertyEditor;
	@Autowired
	private AbonoPropertyEditor																abonoPropertyEditor;
	@Autowired
	private StringPropertyEditor															stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(CuentaPagar.class, cuentaPagarPropertyEditor);
		binder.registerCustomEditor(Abono.class, abonoPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarAbonosPorCuentaPagarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idCuentaPagar) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "AbonoPagar");
		JqGridFilter dataTableFilter = new JqGridFilter("cuentaPagar.id", "'" + idCuentaPagar.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarAbonoPagarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @RequestParam("idCuentaPagar") Long idCuentaPagar, @ModelAttribute AbonoPagarCommand abonoPagarCommand, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();

		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuarioSesion, Constantes.ESTADO_ACTIVO);
			if (usuarioCaja == null) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.factura.no.hay.cajas.abierta", result.getAllErrors());
			}

			CuentaPagar cuentaPagar = cuentaPagarBo.buscar(idCuentaPagar);

			if (cuentaPagar == null) {
				respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
				respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.abono.cuentaPagar.no.existe"));
				return respuestaServiceValidator;
			}
			cuentaPagar.setTotal(cuentaPagar.getTotal() == null ? Constantes.ZEROS_DOUBLE : cuentaPagar.getTotal());
			cuentaPagar.setTotalSaldo(cuentaPagar.getTotalSaldo() == null ? Constantes.ZEROS_DOUBLE : cuentaPagar.getTotalSaldo());
			cuentaPagar.setTotalAbono(cuentaPagar.getTotalAbono() == null ? Constantes.ZEROS_DOUBLE : cuentaPagar.getTotalAbono());

			// abono a crear no puede ser mayor al saldo de la cuenta por cobrar
			if (abonoPagarCommand.getTotal() > cuentaPagar.getTotalSaldo()) {
				result.rejectValue("total", "error.abonoPagar.total.mayor.totalSaldo");
			}
			if (abonoPagarCommand.getTotal() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("total", "error.abonoPagar.total.cero");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			AbonoPagar abonoPagar = new AbonoPagar();
			abonoPagar.setFechaPago(Utils.pasarADate(abonoPagarCommand.getFechaPago(), "yyyy-MM-dd"));
			abonoPagar.setTotalBanco(abonoPagarCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : abonoPagarCommand.getTotalBanco());
			abonoPagar.setTotalEfectivo(abonoPagarCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : abonoPagarCommand.getTotalEfectivo());
			abonoPagar.setTotalTarjeta(abonoPagarCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : abonoPagarCommand.getTotalTarjeta());
			abonoPagar.setTotalTarjeta(abonoPagarCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : abonoPagarCommand.getTotal());
			abonoPagar.setTotal(abonoPagarCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : abonoPagarCommand.getTotal());

			abonoPagar.setCreated_at(new Date());
			abonoPagar.setUpdated_at(new Date());
			abonoPagar.setUsuario(usuarioSesion);

			abonoPagar.setEstado(Constantes.ABONO_ESTADO_PAGADO);
			abonoPagar.setCuentaPagar(cuentaPagar);
			abonoPagarBo.agregar(abonoPagar);
			cuentaPagar.setUpdated_at(new Date());
			cuentaPagar.setTotalAbono(abonoPagar.getTotal() + cuentaPagar.getTotalAbono());
			cuentaPagar.setTotalSaldo(cuentaPagar.getTotalSaldo() - abonoPagar.getTotal());
			if (cuentaPagar.getTotalSaldo().equals(Constantes.ZEROS_DOUBLE)) {
				cuentaPagar.setEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_CERRADO);
			}
			cuentaPagarBo.modificar(cuentaPagar);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abonoPagar.agregar.correctamente", abonoPagar);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Anular abono debe retornar el dinero entregado y incrementar el saldo
	 * @param request
	 * @param model
	 * @param abono
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/anularAbonoPagarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator anular(HttpServletRequest request, ModelMap model, @ModelAttribute AbonoPagar abonoPagar, BindingResult result, SessionStatus status) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			AbonoPagar abonoPagarBD = abonoPagarBo.buscar(abonoPagar.getId());
			if (abonoPagarBD == null) {
				respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
				respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.abono.noExiste"));
				return respuestaServiceValidator;
			}

			abonoPagarBD.setUpdated_at(new Date());
			abonoPagarBD.setEstado(Constantes.ABONO_ESTADO_ANULADO);
			abonoPagarBD.setUsuario(usuarioSesion);
			abonoPagarBo.modificar(abonoPagarBD);
			CuentaPagar cuentaPagar = cuentaPagarBo.buscar(abonoPagarBD.getCuentaPagar().getId());
			cuentaPagar.setTotalAbono(cuentaPagar.getTotalAbono() - abonoPagar.getTotal());
			cuentaPagar.setTotalSaldo(cuentaPagar.getTotalSaldo() + abonoPagar.getTotal());
			cuentaPagarBo.modificar(cuentaPagar);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abonoPagar.anulado.correctamente", abonoPagarBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar un abono
	 * @param request
	 * @param model
	 * @param abono
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarAbonoPagarAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute AbonoPagar abonoPagar, BindingResult result, SessionStatus status) throws Exception {
		try {
			AbonoPagarCommand abonoPagarCommand = new AbonoPagarCommand(abonoPagarBo.buscar(abonoPagar.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", abonoPagarCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class ABONO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abonoPagar.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abonoPagar.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class ABONO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.abonoPagar.noExiste");
			}
		}
	}

}

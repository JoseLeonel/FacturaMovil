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

import com.emprendesoftcr.Bo.AbonoBo;
import com.emprendesoftcr.Bo.CuentaCobrarBo;
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
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.AbonoCommand;
import com.emprendesoftcr.web.propertyEditor.AbonoPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.CuentaCobrarPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class AbonoController {

	private static final Function<Object, AbonoCommand>	TO_COMMAND	= new Function<Object, AbonoCommand>() {

																																		@Override
																																		public AbonoCommand apply(Object f) {
																																			return new AbonoCommand((Abono) f);
																																		};
																																	};

	@Autowired
	private DataTableBo																	dataTableBo;

	@Autowired
	private CuentaCobrarBo															cuentaCobrarBo;

	@Autowired
	private UsuarioBo																		usuarioBo;

	@Autowired
	private UsuarioCajaBo																usuarioCajaBo;

	@Autowired
	private AbonoBo																			abonoBo;

	@Autowired
	private CuentaCobrarPropertyEditor									cuentaCobrarPropertyEditor;
	@Autowired
	private AbonoPropertyEditor													abonoPropertyEditor;
	@Autowired
	private StringPropertyEditor												stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(CuentaCobrar.class, cuentaCobrarPropertyEditor);
		binder.registerCustomEditor(Abono.class, abonoPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * lista de los abonos asociados a una cuenta cobrar
	 * @param request
	 * @param response
	 * @param idCuentaCobrar
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarAbonosPorCuentaCobrarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idCuentaCobrar) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Abono");
		JqGridFilter dataTableFilter = new JqGridFilter("cuentaCobrar.id", "'" + idCuentaCobrar.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar abono a la cuenta por cobrar asociada
	 * @param request
	 * @param model
	 * @param abono
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarAbonoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @RequestParam("idCuentaCobrar") Long idCuentaCobrar, @ModelAttribute AbonoCommand abonoCommand, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();

		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuarioSesion, Constantes.ESTADO_ACTIVO);
			if (usuarioCaja == null) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
			}

			CuentaCobrar cuentaCobrar = cuentaCobrarBo.buscar(idCuentaCobrar);
			if (cuentaCobrar == null) {
				respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
				respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.abono.cuentaCobrar.no.existe"));
				return respuestaServiceValidator;
			}
				// abono a crear no puede ser mayor al saldo de la cuenta por cobrar
			if (Utils.roundFactura(abonoCommand.getTotal(), 2) > Utils.roundFactura(cuentaCobrar.getTotalSaldo(), 2)) {
				result.rejectValue("total", "error.abono.total.mayor.totalSaldo");
			}
			if (abonoCommand.getTotal() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("total", "error.abono.total.cero");
			}
			if (abonoCommand.getTotal() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("total", "error.abono.total.cero");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Abono abono = new Abono();
			abono.setFechaPago(Utils.pasarADate(abonoCommand.getFechaPago(), "yyyy-MM-dd"));
			abono.setTotalBanco(abonoCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : abonoCommand.getTotalBanco());
			abono.setTotalEfectivo(abonoCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : abonoCommand.getTotalEfectivo());
			abono.setTotalTarjeta(abonoCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : abonoCommand.getTotalTarjeta());
			abono.setTotal(abonoCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : abonoCommand.getTotal());

			abono.setCreated_at(new Date());
			abono.setUpdated_at(new Date());
			abono.setUsuario(usuarioSesion);

			abono.setEstado(Constantes.ABONO_ESTADO_PAGADO);
			abono.setCuentaCobrar(cuentaCobrar);
			abonoBo.modificar(abono);
			cuentaCobrar.setUpdated_at(new Date());
			cuentaCobrar.setTotalAbono(abono.getTotal() + cuentaCobrar.getTotalAbono());
			cuentaCobrar.setTotalSaldo(cuentaCobrar.getTotalSaldo() - abono.getTotal());
			if (cuentaCobrar.getTotalSaldo().equals(Constantes.ZEROS_DOUBLE)) {
				cuentaCobrar.setEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_CERRADO);
			}
			// cuentaCobrar.addAbono(abono);
			cuentaCobrarBo.modificar(cuentaCobrar);

			usuarioCajaBo.actualizarCaja(usuarioCaja, Constantes.ZEROS_DOUBLE, Constantes.ZEROS_DOUBLE, Constantes.ZEROS_DOUBLE, Constantes.ZEROS_DOUBLE, abono.getTotal(), Constantes.ZEROS_DOUBLE);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abono.agregar.correctamente", abono);

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
	@RequestMapping(value = "/anularAbonoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator anular(HttpServletRequest request, ModelMap model, @ModelAttribute Abono abono, BindingResult result, SessionStatus status) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Abono abonoBD = abonoBo.buscar(abono.getId());
			if (abonoBD == null) {
				respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
				respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.abono.noExiste"));
				return respuestaServiceValidator;
			}

			abonoBD.setUpdated_at(new Date());
			abonoBD.setEstado(Constantes.ABONO_ESTADO_ANULADO);
			abonoBD.setUsuario(usuarioSesion);
			abonoBo.modificar(abonoBD);
			CuentaCobrar cuentaCobrar = cuentaCobrarBo.buscar(abonoBD.getCuentaCobrar().getId());
			cuentaCobrar.setTotalAbono(cuentaCobrar.getTotalAbono() - abono.getTotal());
			cuentaCobrar.setTotalSaldo(cuentaCobrar.getTotalSaldo() + abono.getTotal());
			cuentaCobrarBo.modificar(cuentaCobrar);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abono.anulado.correctamente", abonoBD);

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
	@RequestMapping(value = "/MostrarAbonoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Abono abono, BindingResult result, SessionStatus status) throws Exception {
		try {
			AbonoCommand abonoCommand = new AbonoCommand(abonoBo.buscar(abono.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", abonoCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class ABONO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("vendedor.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abono.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class ABONO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.vendedor.noExiste");
			}
		}
	}

}

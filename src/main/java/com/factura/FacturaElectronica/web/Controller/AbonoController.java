package com.factura.FacturaElectronica.web.Controller;

import java.math.BigDecimal;
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

import com.factura.FacturaElectronica.Bo.AbonoBo;
import com.factura.FacturaElectronica.Bo.CuentaCobrarBo;
import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Bo.UsuarioCajaBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.DataTableFilter;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Abono;
import com.factura.FacturaElectronica.modelo.CuentaCobrar;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;
import com.factura.FacturaElectronica.web.command.AbonoCommand;
import com.factura.FacturaElectronica.web.componentes.AbonoPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.CuentaCobrarPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
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
		DataTableFilter dataTableFilter = new DataTableFilter("cuentaCobrar.id", "'" + idCuentaCobrar.toString() + "'", "=");
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
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @RequestParam("idCuentaCobrar") Integer idCuentaCobrar, @ModelAttribute Abono abono, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();

		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuarioSesion, Constantes.ESTADO_ACTIVO);
			if (usuarioCaja == null) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.factura.no.hay.cajas.abierta", result.getAllErrors());
			}

			CuentaCobrar cuentaCobrar = cuentaCobrarBo.buscar(idCuentaCobrar);
			if (cuentaCobrar == null) {
				respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
				respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.abono.cuentaCobrar.no.existe"));
				return respuestaServiceValidator;
			}

			// abono a crear no puede ser mayor al saldo de la cuenta por cobrar
			if (abono.getTotal().compareTo(cuentaCobrar.getTotalSaldo()) == -1) {
				result.rejectValue("montoCouta", "error.abono.total.mayor.totalSaldo");
			}
			if (abono.getTotal() == BigDecimal.ZERO) {
				result.rejectValue("montoCouta", "error.abono.total.cero");
			}
			if (abono.getTotal().compareTo(BigDecimal.ZERO) ==-1) {
				result.rejectValue("montoCouta", "error.abono.total.cero");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			abono.setTotalBanco(abono.getTotalBanco() == null ? BigDecimal.ZERO : abono.getTotalBanco());
			abono.setTotalEfectivo(abono.getTotalEfectivo() == null ? BigDecimal.ZERO : abono.getTotalEfectivo());
			abono.setTotalTarjeta(abono.getTotalTarjeta() == null ? BigDecimal.ZERO : abono.getTotalTarjeta());

			abono.setCreated_at(new Date());
			abono.setUpdated_at(new Date());
			abono.setUsuario(usuarioSesion);

			abono.setEstado(Constantes.ABONO_ESTADO_PAGADO);
			abono.setCuentaCobrar(cuentaCobrar);
			abonoBo.modificar(abono);
			cuentaCobrar.setTotalAbono(abono.getTotal().add(cuentaCobrar.getTotalAbono()));
			cuentaCobrar.setTotalSaldo(cuentaCobrar.getTotalSaldo().subtract(abono.getTotal()));
			if (cuentaCobrar.getTotalSaldo().equals(BigDecimal.ZERO)) {
				cuentaCobrar.setEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_CERRADO);
			}
			// cuentaCobrar.addAbono(abono);
			cuentaCobrarBo.modificar(cuentaCobrar);

			usuarioCajaBo.actualizarCaja(usuarioCaja, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, abono.getTotal());
			
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
			cuentaCobrar.setTotalAbono(cuentaCobrar.getTotalAbono().subtract(abono.getTotal()));
			cuentaCobrar.setTotalSaldo(cuentaCobrar.getTotalSaldo().add(abono.getTotal()));
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
	@RequestMapping(value = "/MostrarAbonoAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
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

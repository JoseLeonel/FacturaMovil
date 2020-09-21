package com.emprendesoftcr.web.Controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import com.emprendesoftcr.modelo.Abono;
import com.emprendesoftcr.modelo.AbonoPagar;
import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.AbonoPagarCommand;
import com.emprendesoftcr.web.command.GrupalCuentasCommand;
import com.emprendesoftcr.web.propertyEditor.AbonoPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.CuentaPagarPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;

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
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
			}
			CuentaPagar cuentaPagar = cuentaPagarBo.buscar(idCuentaPagar);
			if (cuentaPagar == null) {
				respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
				respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.abono.cuentaPagar.no.existe"));
				return respuestaServiceValidator;
			}
			cuentaPagar.setTotal(cuentaPagar.getTotal() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(cuentaPagar.getTotal(), 5));
			cuentaPagar.setTotalSaldo(cuentaPagar.getTotalSaldo() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(cuentaPagar.getTotalSaldo(), 5));
			cuentaPagar.setTotalAbono(cuentaPagar.getTotalAbono() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(cuentaPagar.getTotalAbono(), 5));
			// abono a crear no puede ser mayor al saldo de la cuenta por cobrar
			if (Utils.roundFactura(abonoPagarCommand.getTotal(), 2) > Utils.roundFactura(cuentaPagar.getTotalSaldo(), 2)) {
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
			abonoPagar.setTotalBanco(abonoPagarCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(abonoPagarCommand.getTotalBanco(), 5));
			abonoPagar.setTotalEfectivo(abonoPagarCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(abonoPagarCommand.getTotalEfectivo(), 5));
			abonoPagar.setTotalTarjeta(abonoPagarCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(abonoPagarCommand.getTotalTarjeta(), 5));
			abonoPagar.setTotal(abonoPagarCommand.getTotal() == null ? Constantes.ZEROS_DOUBLE : Utils.roundFactura(abonoPagarCommand.getTotal(), 5));
			abonoPagar.setRecibo(abonoPagarCommand.getRecibo());
			abonoPagar.setTransferencia(abonoPagarCommand.getTransferencia() ==null?Constantes.EMPTY:abonoPagarCommand.getTransferencia());
			abonoPagar.setCreated_at(new Date());
			abonoPagar.setUpdated_at(new Date());
			abonoPagar.setUsuario(usuarioSesion);
			abonoPagar.setEstado(Constantes.ABONO_ESTADO_PAGADO);
			abonoPagar.setNombreBanco(abonoPagarCommand.getNombreBanco()== null?Constantes.EMPTY:abonoPagarCommand.getNombreBanco());
			abonoPagar.setNota(abonoPagarCommand.getNota() == null?Constantes.EMPTY:abonoPagarCommand.getNota());
			abonoPagar.setCuentaPagar(cuentaPagar);
			abonoPagarBo.agregar(abonoPagar);
			cuentaPagar.setUpdated_at(new Date());
			cuentaPagar.setTotalAbono(Utils.roundFactura(abonoPagar.getTotal() + cuentaPagar.getTotalAbono(), 2));
			cuentaPagar.setTotalSaldo(Utils.roundFactura(cuentaPagar.getTotalSaldo() - abonoPagar.getTotal(), 2));
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
	 * Agregar abonos a lista de cuentas por pagar seleccionadas
	 * @param request
	 * @param model
	 * @param listaCuentasGrupales
	 * @param cantidadCuentasPorCobrar
	 * @param abonoPagarCommand
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarAbonoPagarGrupalAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarGrupalPagar(HttpServletRequest request, ModelMap model, @RequestParam("listaCuentasGrupales") String listaCuentasGrupales, @RequestParam("cantidadCuentasPorCobrar") Long cantidadCuentasPorCobrar, @ModelAttribute AbonoPagarCommand abonoPagarCommand, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		AbonoPagar abonoPagarTemp = new AbonoPagar();
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuarioSesion, Constantes.ESTADO_ACTIVO);
			if (usuarioCaja == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
			}
			JSONObject json = null;
			try {
				json = (JSONObject) new JSONParser().parse(listaCuentasGrupales);
				// Agregar Lineas de Detalle
				JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
				Gson gson = new Gson();
				if (jsonArrayDetalleFactura != null) {
					Double totalBancoGeneral = abonoPagarCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : abonoPagarCommand.getTotalBanco();
					Double totalEfectivoGeneral = abonoPagarCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : abonoPagarCommand.getTotalEfectivo();
					Double totalTarjetaGeneral = abonoPagarCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : abonoPagarCommand.getTotalTarjeta();
					Double totalAPagar = Constantes.ZEROS_DOUBLE;
					totalBancoGeneral = Utils.roundFactura(totalBancoGeneral, 2);
					totalEfectivoGeneral = Utils.roundFactura(totalEfectivoGeneral, 2);
					totalTarjetaGeneral = Utils.roundFactura(totalTarjetaGeneral, 2);
					Double totalBanco = Constantes.ZEROS_DOUBLE;
					Double totalEfectivo = Constantes.ZEROS_DOUBLE;
					Double totalTarjeta = Constantes.ZEROS_DOUBLE;
					for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
						totalBanco = Constantes.ZEROS_DOUBLE;
						totalEfectivo = Constantes.ZEROS_DOUBLE;
						totalTarjeta = Constantes.ZEROS_DOUBLE;
						totalAPagar = Constantes.ZEROS_DOUBLE;
						GrupalCuentasCommand grupalCuentasCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), GrupalCuentasCommand.class);
						CuentaPagar cuentaPagar = cuentaPagarBo.buscar(grupalCuentasCommand.getId());
						if (cuentaPagar == null) {
							respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
							respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.abono.cuentaPagar.no.existe"));
							return respuestaServiceValidator;
						}
						totalAPagar =  Utils.roundFactura(cuentaPagar.getTotalSaldo(),2);
						if (totalEfectivoGeneral > Constantes.ZEROS_DOUBLE) {
							if (totalEfectivoGeneral > totalAPagar) {
								totalEfectivo = totalAPagar;
								totalEfectivoGeneral = totalEfectivoGeneral - totalAPagar;
							} else {
								totalEfectivo = totalEfectivoGeneral;
								totalAPagar = totalAPagar - totalEfectivoGeneral;
								totalEfectivoGeneral = Constantes.ZEROS_DOUBLE;
							}
						}
						if (totalTarjetaGeneral > Constantes.ZEROS_DOUBLE) {
							if (totalTarjetaGeneral > totalAPagar) {
								totalTarjeta = totalAPagar;
								totalTarjetaGeneral = totalTarjetaGeneral - totalAPagar;
							} else {
								totalTarjeta = totalTarjetaGeneral;
								totalAPagar = totalAPagar - totalTarjetaGeneral;
								totalTarjetaGeneral = Constantes.ZEROS_DOUBLE;
							}
						}
						if (totalBancoGeneral > Constantes.ZEROS_DOUBLE) {
							if (totalBancoGeneral > totalAPagar) {
								totalBanco = totalAPagar;
								totalBancoGeneral = totalBancoGeneral - totalAPagar;
							} else {
								totalBanco = totalBancoGeneral;
								totalAPagar = totalAPagar - totalBancoGeneral;
								totalBancoGeneral = Constantes.ZEROS_DOUBLE;
							}
						}
						if (abonoPagarCommand.getTotal() == Constantes.ZEROS_DOUBLE) {
							result.rejectValue("total", "error.abono.total.cero");
						}
						if (result.hasErrors()) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
						}
						AbonoPagar abonoPagar = new AbonoPagar();
						abonoPagar.setFechaPago(Utils.pasarADate(abonoPagarCommand.getFechaPago(), "yyyy-MM-dd"));
						abonoPagar.setTotalBanco(totalBanco);
						abonoPagar.setTotalEfectivo(totalEfectivo);
						abonoPagar.setTotalTarjeta(totalTarjeta);
						abonoPagar.setTotal(cuentaPagar.getTotalSaldo() ==null?Constantes.ZEROS_DOUBLE:cuentaPagar.getTotalSaldo());
						abonoPagar.setRecibo(abonoPagarCommand.getRecibo() == null?Constantes.EMPTY:abonoPagarCommand.getRecibo());
						abonoPagar.setTransferencia(abonoPagarCommand.getTransferencia() ==null?Constantes.EMPTY:abonoPagarCommand.getTransferencia());
						abonoPagar.setCreated_at(new Date());
						abonoPagar.setUpdated_at(new Date());
						abonoPagar.setUsuario(usuarioSesion);
						abonoPagar.setEstado(Constantes.ABONO_ESTADO_PAGADO);
						abonoPagar.setCuentaPagar(cuentaPagar);
						abonoPagar.setNombreBanco(abonoPagarCommand.getNombreBanco()==null?Constantes.EMPTY:abonoPagarCommand.getNombreBanco());
						abonoPagar.setNota(abonoPagarCommand.getNota() == null?Constantes.EMPTY:abonoPagarCommand.getNota());
						abonoPagarBo.agregar(abonoPagar);
						abonoPagarTemp = abonoPagar;
						cuentaPagar.setUpdated_at(new Date());
						cuentaPagar.setTotalAbono(totalBanco + totalEfectivo + totalTarjeta);
						cuentaPagar.setTotalSaldo(Constantes.ZEROS_DOUBLE);
						cuentaPagar.setEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_CERRADO);
						cuentaPagarBo.modificar(cuentaPagar);
					}
				}
			} catch (org.json.simple.parser.ParseException e) {
				throw e;
			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abonoPagar.agregar.correctamente", abonoPagarTemp);

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
	public RespuestaServiceValidator anularAbonoApagar(HttpServletRequest request, ModelMap model, @ModelAttribute AbonoPagar abonoPagar, BindingResult result, SessionStatus status) throws Exception {
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

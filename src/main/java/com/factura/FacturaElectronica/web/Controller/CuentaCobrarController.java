package com.factura.FacturaElectronica.web.Controller;

import java.util.Date;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.factura.FacturaElectronica.Bo.CuentaCobrarBo;
import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.DataTableFilter;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.CuentaCobrar;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.Vendedor;
import com.factura.FacturaElectronica.web.command.CuentaCobrarCommand;
import com.factura.FacturaElectronica.web.componentes.ClientePropertyEditor;
import com.factura.FacturaElectronica.web.componentes.CuentaCobrarPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.EmpresaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.VendedorPropertyEditor;
import com.google.common.base.Function;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class CuentaCobrarController {

	private static final Function<Object, CuentaCobrarCommand>	TO_COMMAND	= new Function<Object, CuentaCobrarCommand>() {

																																						@Override
																																						public CuentaCobrarCommand apply(Object f) {
																																							return new CuentaCobrarCommand((CuentaCobrar) f);
																																						};
																																					};
	@Autowired
	private UsuarioBo																						usuarioBo;

	@Autowired
	private DataTableBo																					dataTableBo;

	@Autowired
	private CuentaCobrarBo																			cuentaCobrarBo;

	@Autowired
	private EmpresaPropertyEditor																empresaPropertyEditor;
	@Autowired
	private CuentaCobrarPropertyEditor													cuentaCobrarPropertyEditor;
	@Autowired
	private VendedorPropertyEditor															vendedorPropertyEditor;
	@Autowired
	private ClientePropertyEditor																clientePropertyEditor;
	@Autowired
	private StringPropertyEditor																stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(CuentaCobrar.class, cuentaCobrarPropertyEditor);
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarCuentaCobrar", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/cuentasxcobrar/ListarCuentasXCobrar";
	}

	/**
	 * Listado de las cuentas por cobrar
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarCuentaCobrarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "CuentaCobrar");
		
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			DataTableFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Agregar una Cuenta Manual cuando no se realiza por medio de una factura creada desde del sistema
	 * @param request
	 * @param model
	 * @param cuentaCobrar
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarCuentaCobrarManualAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute CuentaCobrar cuentaCobrar, BindingResult result, SessionStatus status) throws Exception {
		try {

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			Double totalCuotas = cuentaCobrar.getMontoCouta() * cuentaCobrar.getCantidadPagos();
			if (totalCuotas < cuentaCobrar.getTotal() || totalCuotas > cuentaCobrar.getTotal()) {
				result.rejectValue("montoCouta", "error.cuentaCobrar.montoCuota.menor.total");
			}
			if (cuentaCobrar.getCantidadPagos() == null) {
				result.rejectValue("cantidadPagos", "error.cuentaCobrar.cantidadPagos.requerido");
			} else if (cuentaCobrar.getCantidadPagos() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("cantidadPagos", "error.cuentaCobrar.cantidadPagos.requerido");
			}
			if (cuentaCobrar.getMontoCouta() == null) {
				result.rejectValue("montoCuota", "error.cuentaCobrar.montoCuota.requerido");
			} else if (cuentaCobrar.getMontoCouta() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("montoCuota", "error.cuentaCobrar.montoCuota.requerido");
			}
			CuentaCobrar cuentaCobrarBD = cuentaCobrarBo.buscarPorLetraCambio(cuentaCobrar.getLetraCambio());
			if (cuentaCobrarBD != null) {
				result.rejectValue("letraCambio", "error.cuentaCobrar.letraCambio.existe");
			}
			CuentaCobrar cuentaCobrarValidar = cuentaCobrarBo.buscarPorFacturaManual(cuentaCobrar.getFacturaManual());
			if (cuentaCobrarValidar != null) {
				result.rejectValue("facturaManual", "error.cuentaCobrar.facturaManual.existe");
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			cuentaCobrar.setCreated_at(new Date());
			cuentaCobrar.setUpdated_at(new Date());
			cuentaCobrar.setTipo(Constantes.CUENTA_POR_COBRAR_TIPO_MANUAL);
			cuentaCobrar.setEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE);
			cuentaCobrar.setTotalAbono(Constantes.ZEROS_DOUBLE);
			cuentaCobrar.setTotalSaldo(cuentaCobrar.getTotal());
			cuentaCobrar.setUsuario(usuarioSesion);
			cuentaCobrar.setEmpresa(usuarioSesion.getEmpresa());
			cuentaCobrarBo.agregar(cuentaCobrar);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.agregar.correctamente", cuentaCobrar);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * modificar una cuenta por cobrar
	 * @param request
	 * @param model
	 * @param cuentaCobrar
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarCuentaCobrarjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute CuentaCobrar cuentaCobrar, BindingResult result, SessionStatus status) throws Exception {
		try {

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			CuentaCobrar cuentaCobrarBD = cuentaCobrarBo.buscar(cuentaCobrar.getId());

			if (cuentaCobrarBD == null) {
				return RESPONSES.ERROR.CUENTACOBRAR.NO_EXISTE;
			}

			Double totalCuotas = cuentaCobrar.getMontoCouta() * cuentaCobrar.getCantidadPagos();
			if (totalCuotas < cuentaCobrar.getTotal() || totalCuotas > cuentaCobrar.getTotal()) {
				result.rejectValue("montoCouta", "error.cuentaCobrar.montoCuota.menor.total");
			}
			if (cuentaCobrar.getCantidadPagos() == null) {
				result.rejectValue("cantidadPagos", "error.cuentaCobrar.cantidadPagos.requerido");
			} else if (cuentaCobrar.getCantidadPagos() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("cantidadPagos", "error.cuentaCobrar.cantidadPagos.requerido");
			}
			if (cuentaCobrar.getMontoCouta() == null) {
				result.rejectValue("montoCuota", "error.cuentaCobrar.montoCuota.requerido");
			} else if (cuentaCobrar.getMontoCouta() == Constantes.ZEROS_DOUBLE) {
				result.rejectValue("montoCuota", "error.cuentaCobrar.montoCuota.requerido");
			}
			if (!cuentaCobrarBD.getLetraCambio().equals(cuentaCobrar.getLetraCambio())) {
				CuentaCobrar cuentaCobrarValidar = cuentaCobrarBo.buscarPorLetraCambio(cuentaCobrar.getLetraCambio());
				if (cuentaCobrarValidar != null) {
					result.rejectValue("letraCambio", "error.cuentaCobrar.letraCambio.existe");
				}

			}
			if (!cuentaCobrarBD.getFacturaManual().equals(cuentaCobrar.getFacturaManual())) {
				CuentaCobrar cuentaCobrarValidar = cuentaCobrarBo.buscarPorFacturaManual(cuentaCobrar.getFacturaManual());
				if (cuentaCobrarValidar != null) {
					result.rejectValue("facturaManual", "error.cuentaCobrar.facturaManual.existe");
				}

			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			cuentaCobrarBD.setUpdated_at(new Date());
			cuentaCobrarBD.setCantidadPagos(cuentaCobrar.getCantidadPagos());
			cuentaCobrarBD.setDescripcionArticulo(cuentaCobrar.getDescripcionArticulo());
			cuentaCobrarBD.setDescuento(cuentaCobrar.getDescuento());
			cuentaCobrarBD.setFactura(cuentaCobrar.getFactura());
			cuentaCobrarBD.setEstado(cuentaCobrar.getEstado());
			cuentaCobrarBD.setFechaEntrega(cuentaCobrar.getFechaEntrega());
			cuentaCobrarBD.setFechaPlazo(cuentaCobrar.getFechaPlazo());
			cuentaCobrarBD.setLetraCambio(cuentaCobrar.getLetraCambio());
			cuentaCobrarBD.setMontoCouta(cuentaCobrar.getMontoCouta());
			cuentaCobrarBD.setNota(cuentaCobrar.getNota());
			cuentaCobrarBD.setRecibo(cuentaCobrar.getRecibo());
			cuentaCobrarBD.setTotal(cuentaCobrar.getTotal());
			cuentaCobrarBD.setVendedor(cuentaCobrar.getVendedor());
			cuentaCobrarBD.setCliente(cuentaCobrar.getCliente());
			cuentaCobrarBD.setUsuario(usuarioSesion);
			cuentaCobrarBo.modificar(cuentaCobrarBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.mensaje.alert.modificar", cuentaCobrarBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar una cuenta por cobrar
	 * @param request
	 * @param model
	 * @param cuentaCobrar
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarCuentaCobrarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute CuentaCobrar cuentaCobrar, BindingResult result, SessionStatus status) throws Exception {
		try {
			CuentaCobrarCommand cuentaCobrarCommand = new CuentaCobrarCommand(cuentaCobrarBo.buscar(cuentaCobrar.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", cuentaCobrarCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CUENTACOBRAR {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CUENTACOBRAR {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.cuentaCobrar.noExiste");
			}
		}
	}

}

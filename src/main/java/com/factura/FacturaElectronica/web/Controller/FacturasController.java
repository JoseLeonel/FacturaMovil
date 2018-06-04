package com.factura.FacturaElectronica.web.Controller;

import javax.servlet.http.HttpServletRequest;

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

import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Bo.FacturaBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Compra;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Vendedor;
import com.factura.FacturaElectronica.validator.FacturaFormValidator;
import com.factura.FacturaElectronica.web.command.CompraEsperaCommand;
import com.factura.FacturaElectronica.web.command.FacturaCommand;
import com.factura.FacturaElectronica.web.componentes.ClientePropertyEditor;
import com.factura.FacturaElectronica.web.componentes.EmpresaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.VendedorPropertyEditor;
import com.google.common.base.Function;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class FacturasController {

	private static final Function<Object, CompraEsperaCommand>	TO_COMMAND	= new Function<Object, CompraEsperaCommand>() {

																																						@Override
																																						public CompraEsperaCommand apply(Object f) {
																																							return new CompraEsperaCommand((Compra) f);
																																						};
																																					};

	@Autowired
	private DataTableBo																					dataTableBo;

	@Autowired
	private UsuarioBo																						usuarioBo;

	@Autowired
	private FacturaBo																						facturaBo;

	@Autowired
	private EmpresaPropertyEditor																empresaPropertyEditor;

	@Autowired
	private ClientePropertyEditor																clientePropertyEditor;

	@Autowired
	private VendedorPropertyEditor															vendedorPropertyEditor;

	@Autowired
	private FacturaFormValidator																facturaFormValidator;

	@Autowired
	private StringPropertyEditor																stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Ventas por Mini super
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/puntoVenta", method = RequestMethod.GET)
	public String crearCompras(ModelMap model) {
		return "views/facturas/puntoVenta";
	}

	@RequestMapping(value = "/CrearFacturaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator crearFactura(HttpServletRequest request, ModelMap model, @ModelAttribute FacturaCommand facturaCommand, BindingResult result, SessionStatus status) {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			facturaFormValidator.validate(facturaCommand, result);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			// if (compraCommand.getFormaPago().equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
			// compraCommand.setFechaCredito(null);
			// }
			// Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			// if(compraCommand.getConsecutivo().equals(Constantes.EMPTY)) {
			// result.rejectValue("consecutivo", "error.compra.existe.consecutivo");
			// }
			// Compra compraBD = compraBo.findByConsecutivoAndEmpresa(compraCommand.getConsecutivo(), usuarioSesion.getEmpresa());
			// if (compraBD != null) {
			// if(!compraBD.getId().equals(compraCommand.getId())) {
			// result.rejectValue("consecutivo", "error.compra.existe.consecutivo");
			// }
			//
			//
			// }
			// if (result.hasErrors()) {
			// return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			// }
			// compraCommand.setEmpresa(usuarioSesion.getEmpresa());
			// compraCommand.setUsuarioCreacion(usuarioSesion);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente", facturaCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	static class RESPONSES {

		private static class OK {

			private static class FACTURA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class FACTURA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.noExiste");
			}
		}
	}

}

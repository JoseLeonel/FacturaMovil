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

import com.factura.FacturaElectronica.Bo.CompraBo;
import com.factura.FacturaElectronica.Bo.EmpresaBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.modelo.Compra;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Proveedor;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.validator.CompraFormValidator;
import com.factura.FacturaElectronica.web.command.CompraCommand;
import com.factura.FacturaElectronica.web.componentes.EmpresaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.ProveedorPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class ComprasController {

	@Autowired
	private EmpresaBo								empresaBo;

	@Autowired
	private UsuarioBo								usuarioBo;
	
	@Autowired
	private CompraBo								compraBo;

	@Autowired
	private EmpresaPropertyEditor		empresaPropertyEditor;

	@Autowired
	private ProveedorPropertyEditor	proveedorPropertyEditor;
	
	@Autowired
	private CompraFormValidator	compraFormValidator;
	
	

	@Autowired
	private StringPropertyEditor		stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Proveedor.class, proveedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Modulo de compras
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/compras", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/compras/crearCompra";
	}

	@RequestMapping(value = "/CrearCompraAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute CompraCommand compraCommand, BindingResult result, SessionStatus status) {

		
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			compraFormValidator.validate(compraCommand, result);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
		  if(compraCommand.getFormaPago().equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
		  	 compraCommand.setFechaCredito(null);
		  }
		  Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		  Compra compraBD = compraBo.findByConsecutivoAndEmpresa(compraCommand.getConsecutivo(),usuarioSesion.getEmpresa());
		  if(compraBD != null) {
		  	result.rejectValue("consecutivo", "error.compra.existe.consecutivo");
		  	
		  }
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			compraCommand.setEmpresa(usuarioSesion.getEmpresa());
			compraCommand.setUsuarioCreacion(usuarioSesion);
		  compraBo.crearCompra(compraCommand);	
			
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.agregar.correctamente", compraCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
 static class RESPONSES {

		private static class OK {

			private static class COMPRA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class COMPRA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.categoria.noExiste");
			}
		}
	}

}

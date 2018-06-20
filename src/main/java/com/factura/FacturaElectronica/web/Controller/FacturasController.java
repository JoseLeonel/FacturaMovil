package com.factura.FacturaElectronica.web.Controller;

import java.io.FileWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

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

import com.factura.FacturaElectronica.Bo.ClienteBo;
import com.factura.FacturaElectronica.Bo.DataTableBo;
import com.factura.FacturaElectronica.Bo.FacturaBo;
import com.factura.FacturaElectronica.Bo.UsuarioBo;
import com.factura.FacturaElectronica.Bo.UsuarioCajaBo;
import com.factura.FacturaElectronica.Bo.VendedorBo;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.DataTableDelimitador;
import com.factura.FacturaElectronica.Utils.DataTableFilter;
import com.factura.FacturaElectronica.Utils.RespuestaServiceDataTable;
import com.factura.FacturaElectronica.Utils.RespuestaServiceValidator;
import com.factura.FacturaElectronica.Utils.Utils;
import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;
import com.factura.FacturaElectronica.modelo.Vendedor;
import com.factura.FacturaElectronica.validator.FacturaFormValidator;
import com.factura.FacturaElectronica.web.command.FacturaCommand;
import com.factura.FacturaElectronica.web.command.FacturaEsperaCommand;
import com.factura.FacturaElectronica.web.componentes.ClientePropertyEditor;
import com.factura.FacturaElectronica.web.componentes.EmpresaPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.StringPropertyEditor;
import com.factura.FacturaElectronica.web.componentes.VendedorPropertyEditor;
import com.factura.FacturaElectronica.xml.FacturaElectronica;
import com.google.common.base.Function;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class FacturasController {

	private static final Function<Object, FacturaEsperaCommand>	TO_COMMAND	= new Function<Object, FacturaEsperaCommand>() {

																																						@Override
																																						public FacturaEsperaCommand apply(Object f) {
																																							return new FacturaEsperaCommand((Factura) f);
																																						};
																																					};

	@Autowired
	private DataTableBo																					dataTableBo;

	@Autowired
	private UsuarioBo																						usuarioBo;
	@Autowired
	UsuarioCajaBo																								usuarioCajaBo;

	@Autowired
	private VendedorBo																					vendedorBo;

	@Autowired
	private ClienteBo																						clienteBo;

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

	/**
	 * Listado de facturas anuladas y facturadas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListaFacturas", method = RequestMethod.GET)
	public String listaFacturas(ModelMap model) {
		return "views/facturas/listaFacturas";
	}
	/**
	 * Facturas En espera de convertirse en factura oficial
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarFacturasEsperaActivasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Factura");
		DataTableFilter dataTableFilter = new DataTableFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new DataTableFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}


	@RequestMapping(value = "/ListarFacturasActivasAndAnuladasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Factura");
		DataTableFilter dataTableFilter = new DataTableFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new DataTableFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	
	
	/**
	 * Crear la Factura
	 * @param request
	 * @param model
	 * @param facturaCommand
	 * @param result
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/CrearFacturaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator crearFactura(HttpServletRequest request, ModelMap model, @ModelAttribute FacturaCommand facturaCommand, BindingResult result, SessionStatus status) {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);
			if(usuarioCajaBd ==null) {
				if(facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
					
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.factura.no.hay.cajas.abierta", result.getAllErrors());
				}
			}

			if (facturaCommand.getCliente() == null) {
				Cliente cliente = clienteBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_CLIENTE_FRECUENTE, usuario.getEmpresa());
				if (cliente == null) {
					cliente = clienteBo.crearClienteFrecuente(usuario.getEmpresa(), usuario);
				} else {
					facturaCommand.setCliente(cliente);
				}
			}
			if (facturaCommand.getVendedor() == null) {
				Vendedor vendedor = vendedorBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_VENDEDOR_FRECUENTE, usuario.getEmpresa());
				if (vendedor == null) {
					vendedorBo.crearVendedorFrecuente(usuario.getEmpresa(), usuario);
				}
				facturaCommand.setVendedor(vendedor);

			}
			facturaCommand.setEmpresa(usuario.getEmpresa());
			facturaFormValidator.validate(facturaCommand, result);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (!facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				facturaCommand.setFechaCredito(null);
			}
			Factura factura = facturaBo.crearFactura(facturaCommand, usuario,usuarioCajaBd);
			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente", factura);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar una Factura
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/MostrarFacturaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idFactura) {
		try {
			Factura facturaBD = facturaBo.findById(idFactura);
			// necesito hacer un contexto se crea la instancia
			JAXBContext context = JAXBContext.newInstance(FacturaElectronica.class);

			// Escribir el xml se utiliza el objete marshaller
			Marshaller marshaller = context.createMarshaller();
			FacturaElectronica facturaElectronica = Utils.crearFacturaElectronica(facturaBD);
		//	facturaElectronica.setNumeroConsecutivo("0012");
	    //tabular el documento xml 
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			//salida
			marshaller.marshal(facturaElectronica, System.out);
			
			marshaller.marshal(facturaElectronica, new FileWriter("leo.xml"));
			
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", facturaBD);
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

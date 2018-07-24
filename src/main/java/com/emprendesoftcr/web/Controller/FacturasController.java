package com.emprendesoftcr.web.Controller;

import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.TipoCambioBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Bo.VendedorBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.components.OpenIDConnectHaciendaComponent;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.Vendedor;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.service.FacturaXMLServices;
import com.emprendesoftcr.service.NotaCreditoXMLServices;
import com.emprendesoftcr.service.NotaDebitoXMLService;
import com.emprendesoftcr.service.TiqueteXMLService;
import com.emprendesoftcr.validator.FacturaFormValidator;
import com.emprendesoftcr.web.command.FacturaCommand;
import com.emprendesoftcr.web.command.FacturaEsperaCommand;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.VendedorPropertyEditor;
import com.google.common.base.Function;

import antlr.collections.List;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class FacturasController {

	private static final Function<Object, FacturaEsperaCommand>				TO_COMMAND											= new Function<Object, FacturaEsperaCommand>() {

																																																			@Override
																																																			public FacturaEsperaCommand apply(Object f) {
																																																				return new FacturaEsperaCommand((Factura) f);
																																																			};
																																																		};
	private static final Function<Detalle, DetalleFacturaElectronica>	TO_DETALLE											= (d) -> {
																																																			//
																																																			DetalleFacturaElectronica detalleFacturaElectronica = new DetalleFacturaElectronica();
																																																			detalleFacturaElectronica.setLinea(Integer.parseInt(d.getNumeroLinea().toString()));
																																																			detalleFacturaElectronica.setCodigo(d.getArticulo().getCodigo());
																																																			detalleFacturaElectronica.setUnidad(d.getArticulo().getUnidadMedida());
																																																			detalleFacturaElectronica.setCantidad(d.getCantidad());
																																																			detalleFacturaElectronica.setDescripcion(d.getArticulo().getDescripcion());
																																																			detalleFacturaElectronica.setPrecioU(d.getPrecioUnitario());
																																																			detalleFacturaElectronica.setMonto(d.getMontoTotal()));
																																																			detalleFacturaElectronica.setDescuento(d.getMontoDescuento());
																																																			detalleFacturaElectronica.setSubtotal(detalleFacturaElectronica.getMonto() - (d.getMontoDescuento()));
																																																			detalleFacturaElectronica.setTarifaIva(d.getTarifaImpuesto());
																																																			detalleFacturaElectronica.setImpuesto(d.getMontoImpuesto());
																																																			detalleFacturaElectronica.setExento(Constantes.EMPTY);
																																																			detalleFacturaElectronica.setTotal(d.getMontoTotalLinea());
																																																			//
																																																			return detalleFacturaElectronica;
																																																		};
//	private static final Function<Factura, FacturaElectronica>			DOCUMENTO_TO_FACTURAELECTRONICA	= (d) -> {
//																																																			FacturaElectronica facturaElectronica = new FacturaElectronica();
//																																																			// Emisor
//																																																			facturaElectronica.setEmisorNombre(d.getEmpresa().getNombre());
//																																																			facturaElectronica.setEmisorCedula(d.getEmpresa().getCedula());
//																																																			facturaElectronica.setEmisorTelefono(d.getEmpresa().getTelCodPais() + "-" + d.getEmpresa().getTelefono().toString());
//																																																			facturaElectronica.setEmisorFax(d.getEmpresa().getFaxCodPais() + "-" + d.getEmpresa().getFax().toString());
//																																																			facturaElectronica.setEmisorCorreo(d.getEmpresa().getEmail());
//																																																			// Cliente
//																																																			facturaElectronica.setClienteNombre(d.getReceptor().getNombre());
//																																																			facturaElectronica.setClienteNombreComercial(d.getReceptor().getNombreComercial());
//																																																			facturaElectronica.setClienteCorreo(d.getReceptor().getEmail());
//																																																			facturaElectronica.setClienteCedula(d.getReceptor().getTipo() + "-" + d.getReceptor().getCedula());
//																																																			facturaElectronica.setClienteTelefono(String.format("%s-%s", d.getReceptor().getTelefono().toString().substring(0, 4), d.getReceptor().getTelefono().toString().substring(4, 8)));
//																																																			facturaElectronica.setClienteCtaGrupo("620102151015-0002");
////																																																			facturaElectronica.setClienteMesCobro(TO_MESCOBRO.apply(d.getMesCobro()));
//																																																			facturaElectronica.setClientePeriodoCobro("01 ABRIL 2018 AL 01 MAYO 2018");
//																																																			facturaElectronica.setClienteFechaVence("07 MAYO 2018");
//																																																			facturaElectronica.setClientePaqueteComercial("KOLBI PROFESIONAL IV, QUE INCLUYE: 60 MINUTOS VOZ, 100 MSG, INTERNET DE MG");
//																																																			// Otros
//																																																//			facturaElectronica.setTipoDocumento(BIND_TIPO_DOCUMENTO.apply(d.getIdTipoDoc()));
//																																																			facturaElectronica.setClave(d.getClave());
//																																																			facturaElectronica.setConsecutivo(d.getConsecutivo());
//																																																			facturaElectronica.setFechaEmision(d.getFechaRegistro().toString());
//																																																			facturaElectronica.setPlazoCredito(d.getPlazoCredito());
//																																																	//		facturaElectronica.setCondicionVenta(BIND_CONDICION_VENTA.apply(d.getCondicionVenta()));
//																																																		//	facturaElectronica.setMedioPago(BIND_MEDIO_PAGO.apply(d.getMedioPago()));
//																																																			facturaElectronica.setMoneda("CRC-Colones Costa Rica");
//																																																			facturaElectronica.setTipoCambio("567.65");
//																																																			// Nota Credito y Nota Debito
//																																																			if (!d.getIdTipoDoc().equals("FE")) {
//																																																				facturaElectronica.setReferencia(d.getIdDocRef());
//																																																			}
//																																																			// Agrega sus detalles
//																																																			List<DetalleFacturaElectronica> detalles = d .stream().map(TO_DETALLE).collect(toList());
//																																																			facturaElectronica.setDetalleFacturaElectronica(detalles);
//																																																			return facturaElectronica;
//																																																		};
	@Lazy
	@Autowired
	private FacturaXMLServices																				facturaXMLServices;

	@Lazy
	@Autowired
	TiqueteXMLService																									tiqueteXMLService;

	@Lazy
	@Autowired
	NotaCreditoXMLServices																						notaCreditoXMLServices;

	@Lazy
	@Autowired
	NotaDebitoXMLService																							notaDebitoXMLService;

	@Lazy
	@Autowired
	private HaciendaBo																								haciendaBo;

	@Autowired
	private DataTableBo																								dataTableBo;

	// @Autowired
	// private CertificadoBo certificadoBo;

	@Autowired
	private UsuarioBo																									usuarioBo;

	@Autowired
	private TipoCambioBo																							tipoCambioBo;

	@Autowired
	UsuarioCajaBo																											usuarioCajaBo;

	@Lazy
	@Autowired
	private VendedorBo																								vendedorBo;

	@Lazy
	@Autowired
	private ClienteBo																									clienteBo;

	@Lazy
	@Autowired
	private FacturaBo																									facturaBo;

	@Lazy
	@Autowired
	private EmpresaPropertyEditor																			empresaPropertyEditor;

	@Autowired
	private ClientePropertyEditor																			clientePropertyEditor;

	@Autowired
	private VendedorPropertyEditor																		vendedorPropertyEditor;

	@Autowired
	private FacturaFormValidator																			facturaFormValidator;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

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
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@RequestMapping(value = "/ListarFacturasActivasAndAnuladasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Integer idCliente) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idCliente);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, cliente, usuarioSesion.getEmpresa());

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
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
			if (usuarioCajaBd == null) {
				if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {

					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
				}
			}
			TipoCambio tipoCambio = tipoCambioBo.findByEstadoAndEmpresa(Constantes.ESTADO_ACTIVO, usuario.getEmpresa());
			if (tipoCambio == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.tipo.cambio.dolar.activo", result.getAllErrors());

			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				if (facturaCommand.getCliente() == null) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.incluir.cliente", result.getAllErrors());
				}

			}

			if (facturaCommand.getCliente() == null) {
				Cliente cliente = clienteBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_CLIENTE_FRECUENTE, usuario.getEmpresa());
				if (cliente == null) {
					cliente = clienteBo.crearClienteFrecuente(usuario.getEmpresa(), usuario);
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.intente.nuevo", result.getAllErrors());
				} else {
					facturaCommand.setCliente(cliente);
				}
			}
			if (facturaCommand.getVendedor() == null) {
				Vendedor vendedor = vendedorBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_VENDEDOR_FRECUENTE, usuario.getEmpresa());
				if (vendedor == null) {
					vendedorBo.crearVendedorFrecuente(usuario.getEmpresa(), usuario);
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.intente.nuevo", result.getAllErrors());
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
			Factura factura = facturaBo.crearFactura(facturaCommand, usuario, usuarioCajaBd, tipoCambio);
			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			String comprobanteXML = Constantes.EMPTY;
			// Si es Facturada se crea pendiente de firmar
			if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {

				if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
					// Crear XMl sin firma
					comprobanteXML = facturaXMLServices.getCrearXMLSinFirma(factura);
					// firmar el documento
					comprobanteXML = facturaXMLServices.getFirmarXML(comprobanteXML, factura.getEmpresa());
				} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
					// Crear XMl sin firma
					comprobanteXML = tiqueteXMLService.getCrearXMLSinFirma(factura);
					// firmar el documento
					comprobanteXML = tiqueteXMLService.getFirmarXML(comprobanteXML, factura.getEmpresa());
				} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
					// Crear XMl sin firma
					comprobanteXML = notaCreditoXMLServices.getCrearXMLSinFirma(factura);
					// firmar el documento
					comprobanteXML = notaCreditoXMLServices.getFirmarXML(comprobanteXML, factura.getEmpresa());

				} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
					// Crear XMl sin firma
					comprobanteXML = notaDebitoXMLService.getCrearXMLSinFirma(factura);
					// firmar el documento
					comprobanteXML = notaDebitoXMLService.getFirmarXML(comprobanteXML, factura.getEmpresa());
				}
				Hacienda hacienda = new Hacienda();
				hacienda.setCedulaEmisor(factura.getEmpresa().getCedula());
				hacienda.setTipoEmisor(factura.getEmpresa().getTipoCedula());
				// no se graba el cliente si es frecuente
				if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
					hacienda.setCedulaReceptor(factura.getCliente().getCedula());
					hacienda.setTipoReceptor(factura.getCliente().getTipoCedula());
				}
				hacienda.setEmpresa(factura.getEmpresa());
				hacienda.setClave(factura.getClave());
				hacienda.setFechaEmisor(factura.getFechaEmision());
				Blob b = FacturaElectronicaUtils.convertirStringToblod(comprobanteXML);
				hacienda.setComprobanteXML(b);
				hacienda.setCreated_at(new Date());
				hacienda.setUpdated_at(new Date());
				hacienda.setStatus(Constantes.ZEROS);
				hacienda.setEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML);
				hacienda.setConsecutivo(factura.getNumeroConsecutivo());
				hacienda.setReintentos(Constantes.ZEROS);
				hacienda.setReintentosAceptacion(Constantes.ZEROS);

				haciendaBo.agregar(hacienda);

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
	@Autowired
	private OpenIDConnectHaciendaComponent openIDConnectHaciendaComponent;

	@RequestMapping(value = "/MostrarFacturaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idFactura) {
		try {
			Factura facturaBD = facturaBo.findById(idFactura);

			// Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

			// certificadoBo.agregar(usuario.getEmpresa(),usuario.getEmpresa().getClaveLlaveCriptografica().toString(),usuario.getEmpresa().getNombreLlaveCriptografica());
			// String xml = facturaXMLServices.getCrearXMLSinFirma(facturaBD);
			// facturaXMLServices.getFirmarXML(xml, facturaBD.getEmpresa());

			// KeyStore keyStore = null;

			// llaveCriptografica.setPassSignature(usuario.getEmpresa().getClaveLlaveCriptografica().toString());
			// llaveCriptografica.setPathSignature(usuario.getEmpresa().getNombreLlaveCriptografica());
			// XadesSigner xadesSigner = llaveCriptograficaService.getSigner(usuario.getEmpresa().getNombreLlaveCriptografica(),usuario.getEmpresa().getClaveLlaveCriptografica().toString());
			// keyStore = llaveCriptograficaService.getKeyStore(llaveCriptografica);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", facturaBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Cliente cliente, Empresa empresa) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Factura");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.FACTURA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (cliente != null) {
				delimitador.addFiltro(new JqGridFilter("cliente.id", "'" + cliente.getId().toString() + "'", "="));
			}
			if (!inicio.equals(Constantes.EMPTY) && !fin.equals(Constantes.EMPTY)) {
				fechaInicio = Utils.parseDate(inicio);
				fechaFinal = Utils.parseDate(fin);
				if (fechaFinal == null) {
					fechaFinal = new Date(System.currentTimeMillis());
				}
				if (fechaFinal != null && fechaFinal != null) {
					fechaFinal = Utils.sumarDiasFecha(fechaFinal, 1);
				}

				DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT5);

				inicio = dateFormat.format(fechaInicio);
				fin = dateFormat.format(fechaFinal);

				delimitador.addFiltro(new JqGridFilter("fechaEmision", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("fechaEmision", fin, "dateFinal<="));
			}
			return delimitador;
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

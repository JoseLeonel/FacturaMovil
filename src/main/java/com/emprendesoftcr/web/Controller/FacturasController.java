package com.emprendesoftcr.web.Controller;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.emprendesoftcr.Bo.ProvinciaBo;
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
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.Vendedor;
import com.emprendesoftcr.pdf.App;
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
import com.itextpdf.text.DocumentException;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class FacturasController {

	private static final Function<String, String>											BIND_CONDICION_VENTA						= (id) -> id.equals("01") ? "Contado" : id.equals("02") ? "Credito" : id.equals("03") ? "Consignacion" : id.equals("04") ? "Apartado" : id.equals("05") ? "Arrendamiento con opcion de compra" : id.equals("06") ? "Arrendamiento en funcion financiera" : "Otros";

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
																																																			detalleFacturaElectronica.setCodigo(d.getCodigo());
																																																			detalleFacturaElectronica.setUnidad(d.getUnidadMedida());
																																																			detalleFacturaElectronica.setCantidad(d.getCantidad());
																																																			detalleFacturaElectronica.setDescripcion(d.getDescripcion());
																																																			detalleFacturaElectronica.setPrecioU(d.getPrecioUnitario());
																																																			detalleFacturaElectronica.setMonto(d.getMontoTotal());
																																																			detalleFacturaElectronica.setDescuento(d.getMontoDescuento());
																																																			detalleFacturaElectronica.setSubtotal(detalleFacturaElectronica.getMonto() - (d.getMontoDescuento()));
																																																			detalleFacturaElectronica.setTarifaIva(d.getImpuesto());
																																																			detalleFacturaElectronica.setImpuesto(d.getMontoImpuesto());
																																																			// detalleFacturaElectronica.setExento(Constantes.EMPTY);
																																																			detalleFacturaElectronica.setTotal(d.getMontoTotalLinea());
																																																			//
																																																			return detalleFacturaElectronica;
																																																		};
	private static final Function<Factura, FacturaElectronica>				DOCUMENTO_TO_FACTURAELECTRONICA	= (d) -> {
																																																			FacturaElectronica facturaElectronica = new FacturaElectronica();
																																																			// Emisor
																																																			
																																																			facturaElectronica.setEmisorNombre(d.getEmpresa().getNombre());
																																																			facturaElectronica.setEmisorCedula(d.getEmpresa().getCedula());
																																																			facturaElectronica.setEmisorTelefono(d.getEmpresa().getCodigoPais() + "-" + d.getEmpresa().getTelefono().toString());
																																																			facturaElectronica.setEmisorCorreo(d.getEmpresa().getCorreoElectronico());
																																																			// Cliente
																																																			if (!d.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
																																																				facturaElectronica.setClienteNombre(d.getCliente().getNombreCompleto());
																																																				facturaElectronica.setClienteNombreComercial(d.getCliente().getNombreComercial());
																																																				facturaElectronica.setClienteCorreo(d.getCliente().getCorreoElectronico());
																																																				facturaElectronica.setClienteCedula(d.getCliente().getTipoCedula() + "-" + d.getCliente().getCedula());
																																																				facturaElectronica.setClienteTelefono(String.format("%s-%s", d.getCliente().getTelefono().toString().substring(0, 4), d.getCliente().getTelefono().toString().substring(4, 8)));

																																																			}
																																																			// facturaElectronica.setClienteMesCobro(TO_MESCOBRO.apply(d.getMesCobro()));
																																																			//Ubicacion
																																																			facturaElectronica.set_logo(d.getEmpresa().getLogo());
																																																			facturaElectronica.set_clienteDireccion(d.getDireccion());
																																																			// Otros
																																																			facturaElectronica.setTipoDocumento(FacturaElectronicaUtils.getTipoDocumento(d.getTipoDoc()));
																																																			facturaElectronica.setClave(d.getClave());
																																																			facturaElectronica.setConsecutivo(d.getNumeroConsecutivo());
																																																			facturaElectronica.setFechaEmision(d.getFechaEmision().toString());
																																																			facturaElectronica.setPlazoCredito(d.getPlazoCredito().toString());
																																																			facturaElectronica.setCondicionVenta(BIND_CONDICION_VENTA.apply(d.getCondicionVenta()));
																																																			facturaElectronica.setMedioBanco(d.getMedioBanco() != null ? Constantes.FACTURA_MEDIO_PAGO_TRANSFERENCIA_STR : Constantes.EMPTY);
																																																			facturaElectronica.setMedioEfectivo(d.getMedioEfectivo() != null ? Constantes.FACTURA_MEDIO_PAGO_EFECTIVO_STR : Constantes.EMPTY);
																																																			facturaElectronica.setMedioTarjeta(d.getMedioTarjeta() != null ? Constantes.FACTURA_MEDIO_PAGO_TARJETA_STR : Constantes.EMPTY);

																																																			facturaElectronica.setMoneda(FacturaElectronicaUtils.getMoneda(d.getCodigoMoneda()));
																																																			facturaElectronica.setTipoCambio(d.getTipoCambio().toString());
																																																			// Nota Credito y Nota Debito
																																																			 if (d.getReferenciaCodigo() !=null) {
																																																				 if(!d.getReferenciaCodigo().equals(Constantes.EMPTY)) {
																																																					 facturaElectronica.setReferencia(d.getReferenciaCodigo());	 
																																																				 }
																																																			 
																																																			 }else {
																																																				 facturaElectronica.setReferencia(Constantes.EMPTY);
																																																			 }
																																																			// Agrega sus detalles
																																																			List<DetalleFacturaElectronica> detalles = d.getDetalles().stream().map(TO_DETALLE).collect(toList());
																																																			facturaElectronica.setDetalleFacturaElectronica(detalles);
																																																			return facturaElectronica;
																																																		};
	

	@Lazy
	@Autowired
	private FacturaXMLServices																				facturaXMLServices;

	@Lazy
	@Autowired
	TiqueteXMLService																									tiqueteXMLService;

	@Lazy
	@Autowired
	ProvinciaBo																												provinciaBo;

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
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value = "/generaFacturaPDF", method = RequestMethod.GET, headers = "Accept=application/json")
	public void generarFacturaPDF(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idFactura) throws IOException {
		try {
			Factura factura = facturaBo.findById(idFactura);
			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			int BUFFER_SIZE = 4096;
			ByteArrayInputStream inputStream = new ByteArrayInputStream(namePDF.toByteArray());
			response.setContentType("application/octet-stream");
			response.setContentLength((int) namePDF.toByteArray().length);
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", factura.getTipoDoc() + "-" + factura.getNumeroConsecutivo() + ".pdf");
			response.setHeader(headerKey, headerValue);
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (com.google.zxing.WriterException ex) {

		}

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
	public RespuestaServiceDataTable listarFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idCliente) {
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
			if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
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
			
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				if(facturaCommand.getReferenciaNumero() !=null) {
					Factura facturaTem = facturaBo.findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(),usuario.getEmpresa());
					if(facturaTem ==null) {
						result.rejectValue("referenciaNumero", "mensajes.no.existe.consecutivo");	
					}else {
						// El tipo documento fecha emision  y  el cliente se asignan automatico para la factura
							facturaCommand.setReferenciaTipoDoc(facturaTem.getTipoDoc());
							facturaCommand.setReferenciaFechaEmision(facturaTem.getFechaEmision().toString());
							facturaCommand.setCliente(facturaTem.getCliente());
						
						
					}
					
				}
				
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
				hacienda.setTipoDoc(factura.getTipoDoc());
				hacienda.setNombreReceptor(factura.getCliente().getNombreCompleto());
				hacienda.setCorreoReceptor(factura.getCliente().getCorreoElectronico());
				hacienda.setTotalReceptor(factura.getTotalComprobante());
				hacienda.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
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
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idFactura) {
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
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param consecutivo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ConsultarConsecutivoAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator consultarConsecutivo(HttpServletRequest request, ModelMap model,@ModelAttribute Factura factura,HttpServletResponse response, @RequestParam String consecutivo, BindingResult result, SessionStatus status) {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Factura facturaBD = facturaBo.findByConsecutivoAndEmpresa(consecutivo,usuario.getEmpresa());
			if(facturaBD == null) {
				result.rejectValue("referenciaNumero", "mensajes.no.existe.consecutivo");
			}
		  if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

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

package com.emprendesoftcr.web.Controller;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.pdf.App;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.web.command.HaciendaCommand;
import com.google.common.base.Function;
import com.itextpdf.text.DocumentException;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class HaciendasController {

	private static final Function<String, String>											BIND_CONDICION_VENTA						= (id) -> id.equals("01") ? "Contado" : id.equals("02") ? "Credito" : id.equals("03") ? "Consignacion" : id.equals("04") ? "Apartado" : id.equals("05") ? "Arrendamiento con opcion de compra" : id.equals("06") ? "Arrendamiento en funcion financiera" : "Otros";

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
																																																		
																																																			facturaElectronica.setEmisorNombre(!d.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ?d.getEmpresa().getNombreComercial(): d.getEmpresa().getNombre());
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
																																																			// Ubicacion
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
																																																					 facturaElectronica.setReferenciaCodigo( MapEnums.ENUM_CODIGO_REFERENCIA.get(d.getReferenciaCodigo()));
																																																					 facturaElectronica.setReferenciaNumero(d.getReferenciaNumero());
																																																					 facturaElectronica.setReferenciaRazon(d.getReferenciaRazon());
																																																					 facturaElectronica.setReferenciaTipoDoc(MapEnums.ENUM_TIPO_DOC.get(d.getReferenciaTipoDoc()));
																																																					 facturaElectronica.setReferenciaFechaEmision(d.getReferenciaFechaEmision() != null?d.getReferenciaFechaEmision().toString():Constantes.EMPTY);
																																																					 
																																																				 }
																																																			 
																																																			 }else {
																																																				 
																																																				 facturaElectronica.setReferencia(Constantes.EMPTY);
																																																				 facturaElectronica.setReferenciaCodigo( Constantes.EMPTY);
																																																				 facturaElectronica.setReferenciaNumero(Constantes.EMPTY);
																																																				 facturaElectronica.setReferenciaRazon(Constantes.EMPTY);
																																																				 facturaElectronica.setReferenciaTipoDoc(Constantes.EMPTY);
																																																				 facturaElectronica.setReferenciaFechaEmision(Constantes.EMPTY);
																																																				 
																																																			 }
																																																			// Agrega sus detalles
																																																			List<DetalleFacturaElectronica> detalles = d.getDetalles().stream().map(TO_DETALLE).collect(toList());
																																																			facturaElectronica.setDetalleFacturaElectronica(detalles);
																																																			return facturaElectronica;
																																																		};

	private static final Function<Object, HaciendaCommand>						TO_COMMAND											= new Function<Object, HaciendaCommand>() {

																																																			@Override
																																																			public HaciendaCommand apply(Object f) {
																																																				return new HaciendaCommand((Hacienda) f);
																																																			};
																																																		};

	private Logger																										log															= LoggerFactory.getLogger(this.getClass());

	@Autowired
	DataTableBo																												dataTableBo;

	@Autowired
	private FacturaBo																													facturaBo;

	@Autowired
	private ProcesoHaciendaService																						procesoHaciendaService;

	@Autowired
	private HaciendaBo																												haciendaBo;

	@Autowired
	private UsuarioBo																													usuarioBo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@RequestMapping(value = "/ListarHacienda", method = RequestMethod.GET)
	public String listaFacturas(ModelMap model) {
		return "views/hacienda/ListarHacienda";
	}

	/**
	 * Listado de hacienda
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ListarHaciendasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String cedulaReceptor) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, cedulaReceptor, usuarioSesion.getEmpresa());
		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Env
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @param cedulaReceptor
	 * @return
	 */
	@RequestMapping(value = "/EnviarHaciendasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable enviarHaciendaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String cedulaReceptor) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, cedulaReceptor, usuarioSesion.getEmpresa());
		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Enviar aceptar el documento hacienda
	 * @param request
	 * @param response
	 * @param idFactura
	 * @return
	 */
	@RequestMapping(value = "/EnviarAceptarHaciendaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			procesoHaciendaService.envioHacienda(haciendaBD);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.enviado.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	@RequestMapping(value = "/AceptarHaciendaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrarAceptar(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			procesoHaciendaService.aceptarDocumento(haciendaBD);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.aceptar.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	/**
	 * Enviar el correo al emisor y al cliente si es una factura(nota credito o debito) , tiquete solo al emisor
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @return
	 */
	@RequestMapping(value = "/EnviarCorreoClienteAndEmisorAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoEmisorAndReceptor(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			Factura factura = facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());

			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.factura.no.existe");
			}
			ArrayList<String> listaCorreos = new ArrayList<String>();
			if(!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
				listaCorreos.add(factura.getCliente().getCorreoElectronico());	
			}
			listaCorreos.add(factura.getEmpresa().getCorreoElectronico());
			procesoHaciendaService.enviarCorreos(factura, haciendaBD,listaCorreos);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	/**
	 * Envios de correos alternativos
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @param correo
	 * @return
	 */
	@RequestMapping(value = "/EnviarCorreoAlternativoAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator enviarCorreoAlternativo(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda,@RequestParam String correo) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");

			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			if (haciendaBD == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.no.existe");
			}

			Factura factura = facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());

			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.error.factura.no.existe");
			}
			ArrayList<String> listaCorreos = new ArrayList<String>();
			listaCorreos.add(correo);
			procesoHaciendaService.enviarCorreos(factura, haciendaBD,listaCorreos);

			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
		return respuestaServiceValidator;
	}

	/**
	 * Baja Comprobante XML
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @return
	 * @throws SQLException
	 * @throws SerialException
	 */
	@RequestMapping(value = "/bajarXMLComprobanteAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarXMLComprobante(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) throws IOException, SerialException, SQLException {
		try {
			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			String fileName = haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				fileName = "TiqueteXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				fileName = "FacturaXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				fileName = "NotaCreditoXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				fileName = "NotaDebitoXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xml");
			ServletOutputStream out = response.getOutputStream();
			out.println(FacturaElectronicaUtils.convertirBlodToString(haciendaBD.getComprobanteXML()));
			out.flush();
			out.close();

		} catch (Exception e) {
			log.info("** Error  bajarXMLComprobante: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Respuesta de hacienda del documento aceptado o rechazado
	 * @param request
	 * @param response
	 * @param model
	 * @param idHacienda
	 * @throws IOException
	 * @throws SerialException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/bajarXMLRespuestaAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarXMLRespuesta(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) throws IOException, SerialException, SQLException {
		try {
			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			String fileName = haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			fileName = "RespuestaXML_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();

			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xml");
			ServletOutputStream out = response.getOutputStream();
			out.println(FacturaElectronicaUtils.convertirBlodToString(haciendaBD.getMensajeHacienda()));
			out.flush();
			out.close();

		} catch (Exception e) {
			log.info("** Error  bajarXMLComprobante: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@RequestMapping(value = "/bajarPDFComprobanteAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarPDFComprobante(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idHacienda) throws Exception {
		try {
			Hacienda haciendaBD = haciendaBo.findById(idHacienda);
			Factura factura = facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());
			String fileName = haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				fileName = "Tiquete_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				fileName = "Factura_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				fileName = "NotaCredito_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}
			if (haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				fileName = "NotaDebito_" + haciendaBD.getTipoDoc() + "-" + haciendaBD.getConsecutivo();
			}

			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			int BUFFER_SIZE = 4096;
			ByteArrayInputStream inputStream = new ByteArrayInputStream(namePDF.toByteArray());
			response.setContentType("application/octet-stream");
			response.setContentLength((int) namePDF.toByteArray().length);
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName + ".pdf");
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

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, String cedula, Empresa empresa) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Hacienda");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (cedula != null) {
				if (!cedula.equals("0")) {
					delimitador.addFiltro(new JqGridFilter("cedulaReceptor", "'" + cedula.toString() + "'", "="));
				}

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

				delimitador.addFiltro(new JqGridFilter("fechaEmisor", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("fechaEmisor", fin, "dateFinal<="));
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

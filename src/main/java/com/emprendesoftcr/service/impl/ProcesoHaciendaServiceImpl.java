package com.emprendesoftcr.service.impl;

import static com.emprendesoftcr.fisco.Keys.ERROR;
import static com.emprendesoftcr.fisco.Keys.POST_RESPONSE;
import static java.util.stream.Collectors.toList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.util.ByteArrayDataSource;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Bo.CompraSimplificadaBo;
import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.GraficoVentasBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Bo.SemaforoBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.components.EnvioHaciendaComponent;
import com.emprendesoftcr.components.OpenIDConnectHaciendaComponent;
import com.emprendesoftcr.fisco.EmisorHacienda;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.emprendesoftcr.fisco.Recepcion;
import com.emprendesoftcr.fisco.ReceptorHacienda;
import com.emprendesoftcr.fisco.RespuestaHaciendaXML;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.Semaforo;
import com.emprendesoftcr.modelo.sqlNativo.HaciendaComprobarNative;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstado;
import com.emprendesoftcr.pdf.DetalleFacturaElectronica;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.pdf.ReportePdfView;
import com.emprendesoftcr.service.CompraSimplificadaXMLServices;
import com.emprendesoftcr.service.FacturaXMLServices;
import com.emprendesoftcr.service.NotaCreditoXMLIVAServices;
import com.emprendesoftcr.service.NotaCreditoXMLServices;
import com.emprendesoftcr.service.NotaDebitoXMLIVAService;
import com.emprendesoftcr.service.NotaDebitoXMLService;
import com.emprendesoftcr.service.ProcesoHaciendaService;
import com.emprendesoftcr.service.RecepcionFacturaXMLServices;
import com.emprendesoftcr.service.RespuestaHaciendaXMLService;
import com.emprendesoftcr.service.TiqueteXMLService;
import com.emprendesoftcr.type.RespuestaHacienda;
import com.emprendesoftcr.type.json.RespuestaHaciendaJson;
import com.google.common.base.Function;

/**
 * Servicio de envio de los documentos de hacienda
 **/
@EnableTransactionManagement
@Service("procesoHaciendaService")
public class ProcesoHaciendaServiceImpl implements ProcesoHaciendaService {

	private static final Function<String, String>											BIND_CONDICION_VENTA						= (id) -> id.equals("01") ? "Contado" : id.equals("02") ? "Credito" : id.equals("03") ? "Consignacion" : id.equals("04") ? "Apartado" : id.equals("05") ? "Arrendamiento con opcion de compra" : id.equals("06") ? "Arrendamiento en funcion financiera" : "Otros";

	private static final Function<Detalle, DetalleFacturaElectronica>	TO_DETALLE											= (d) -> {
																																																			//
																																																			DetalleFacturaElectronica detalleFacturaElectronica = new DetalleFacturaElectronica();
																																																			detalleFacturaElectronica.setLinea(Integer.parseInt(d.getNumeroLinea().toString()));
																																																			detalleFacturaElectronica.setCodigo(d.getCodigo());
																																																			detalleFacturaElectronica.setUnidad(d.getUnidadMedida());
																																																			detalleFacturaElectronica.setCantidad(d.getCantidad() != null ? d.getCantidad() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setDescripcion(d.getDescripcion());
																																																			detalleFacturaElectronica.setPrecioU(d.getPrecioUnitario() != null ? d.getPrecioUnitario() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setMonto(d.getMontoTotal() != null ? d.getMontoTotal() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setDescuento(d.getMontoDescuento() != null ? d.getMontoDescuento() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setSubtotal(detalleFacturaElectronica.getMonto() - (d.getMontoDescuento()));
																																																			detalleFacturaElectronica.setTarifaIva(d.getImpuesto() != null ? d.getImpuesto() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.set_impuesto1(d.getImpuesto1() != null ? d.getImpuesto1() : Constantes.ZEROS_DOUBLE);
																																																			Double resultado = d.getMontoImpuesto() != null ? d.getMontoImpuesto() : Constantes.ZEROS_DOUBLE;
																																																			resultado += d.getMontoImpuesto1() != null ? d.getMontoImpuesto1() : Constantes.ZEROS_DOUBLE;
																																																			detalleFacturaElectronica.setImpuesto(resultado);
																																																			detalleFacturaElectronica.setTipoImpuesto(d.getTipoImpuesto() == null ? Constantes.EMPTY : d.getTipoImpuesto());
																																																			detalleFacturaElectronica.setTotal(d.getMontoTotalLinea());
																																																			detalleFacturaElectronica.setMontoExoneracion(d.getMontoExoneracion() != null ? d.getMontoExoneracion() : Constantes.ZEROS_DOUBLE);
																																																			detalleFacturaElectronica.setTipoDocumentoExoneracion(d.getTipoDocumentoExoneracion() == null ? Constantes.EMPTY : d.getTipoDocumentoExoneracion());
																																																			detalleFacturaElectronica.setFechaEmisionExoneracion(d.getFechaEmisionExoneracion() != null ? Utils.getFechaGeneraReporte(d.getFechaEmisionExoneracion()) : Constantes.EMPTY);
																																																			detalleFacturaElectronica.setNumeroDocumentoExoneracion(d.getNumeroDocumentoExoneracion() != null ? d.getNumeroDocumentoExoneracion() : Constantes.EMPTY);
																																																			//
																																																			return detalleFacturaElectronica;
																																																		};
	private static final Function<Factura, FacturaElectronica>				DOCUMENTO_TO_FACTURAELECTRONICA	= (d) -> {
																																																			FacturaElectronica facturaElectronica = new FacturaElectronica();
																																																			if (d.getCodigoActividad() == null) {
																																																				facturaElectronica.set_codigoActividadComercial(d.getEmpresa().getCodigoActividad());
																																																			} else {
																																																				facturaElectronica.set_codigoActividadComercial(d.getCodigoActividad());
																																																			}

																																																			// Emisor
																																																			facturaElectronica.setEsquemaXML(d.getVersionEsquemaXML());
																																																			facturaElectronica.setEmisorNombreComercial(d.getEmpresa().getNombreComercial());
																																																			facturaElectronica.setFooterTotalServiciosGravados(d.getTotalServGravados());
																																																			facturaElectronica.setFooterTotalMercanciasGravadas(d.getTotalMercanciasGravadas());
																																																			facturaElectronica.setTotalOtrosCargos(d.getTotalOtrosCargos());
																																																			// Total Factura
																																																			facturaElectronica.setFooterTotalServiciosExentos(d.getTotalServExentos());
																																																			facturaElectronica.setFooterTotalGravado(d.getTotalGravado());
																																																			facturaElectronica.setFooterTotalExento(d.getTotalMercanciasExentas());
																																																			facturaElectronica.setFooterTotalVenta(d.getTotalVenta());
																																																			facturaElectronica.setFooterTotalDescuento(d.getTotalDescuentos());
																																																			facturaElectronica.setFooterTotalImpuesto(d.getTotalImpuesto());
																																																			facturaElectronica.setFooterTotalVentaNeta(d.getTotalVentaNeta());
																																																			facturaElectronica.setFooterTotalComprobante(d.getTotalComprobante());
//		
																																																			facturaElectronica.setEmisorNombre(!d.getEmpresa().getNombre().equals(Constantes.EMPTY) ? d.getEmpresa().getNombre() : d.getEmpresa().getNombre());
																																																			facturaElectronica.setEmisorCedula(d.getEmpresa().getCedula());
																																																			facturaElectronica.setEmisorDireccion(d.getEmpresa().getOtraSenas());
																																																			facturaElectronica.setEmisorTelefono(d.getEmpresa().getCodigoPais() + "-" + d.getEmpresa().getTelefono().toString());
																																																			String correo = Constantes.EMPTY;

																																																			if (d.getEmpresa().getCorreoPDF() != null) {
																																																				if (!d.getEmpresa().getCorreoPDF().equals(Constantes.EMPTY)) {
																																																					correo = d.getEmpresa().getCorreoPDF();
																																																				}
																																																			}
																																																			if (d.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
																																																				if (d.getEmpresa().getCorreoCredito() != null) {
																																																					if (!d.getEmpresa().getCorreoCredito().equals(Constantes.EMPTY)) {
																																																						correo = d.getEmpresa().getCorreoCredito();
																																																					}
																																																				}
																																																			}
																																																			if (correo.equals(Constantes.EMPTY)) {
																																																				facturaElectronica.setEmisorCorreo(d.getEmpresa().getCorreoElectronico());
																																																			} else {
																																																				facturaElectronica.setEmisorCorreo(correo);
																																																			}
																																																			facturaElectronica.setCuenta1(d.getEmpresa().getCuenta1() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta1());
																																																			facturaElectronica.setCuenta2(d.getEmpresa().getCuenta2() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta2());
																																																			facturaElectronica.setCuenta3(d.getEmpresa().getCuenta3() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta3());
																																																			facturaElectronica.setCuenta4(d.getEmpresa().getCuenta4() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta4());
																																																			facturaElectronica.setCuenta5(d.getEmpresa().getCuenta5() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta5());
																																																			facturaElectronica.setCuenta6(d.getEmpresa().getCuenta6() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta6());
																																																			facturaElectronica.setCuenta7(d.getEmpresa().getCuenta7() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta7());
																																																			facturaElectronica.setCuenta8(d.getEmpresa().getCuenta8() == null ? Constantes.EMPTY : d.getEmpresa().getCuenta8());

																																																			facturaElectronica.set_nota(d.getNota() == null ? Constantes.EMPTY : d.getNota());
																																																			facturaElectronica.setClienteNombre(d.getCliente().getNombreCompleto());
																																																			if (d.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_FRECUENTE)) {
																																																				if (d.getNombreFactura() != null) {
																																																					if (!d.getNombreFactura().equals(Constantes.EMPTY)) {
																																																						facturaElectronica.setClienteNombre(d.getNombreFactura());
																																																					}
																																																				}
																																																			}
																																																			facturaElectronica.setClienteNombreComercial(d.getCliente().getNombreComercial());
																																																			facturaElectronica.setClienteCorreo(d.getCliente().getCorreoElectronico());

																																																			facturaElectronica.setClienteCedula(d.getCliente().getCedula());
																																																			if (facturaElectronica.getClienteCedula() != null) {
																																																				if (facturaElectronica.getClienteCedula().equals(Constantes.EMPTY)) {
																																																					facturaElectronica.setClienteCedula(d.getCliente().getIdentificacionExtranjero());
																																																				}

																																																			}
																																																			if (d.getCliente().getTelefono() != null) {
																																																				if (d.getCliente().getTelefono() != Constantes.ZEROS) {
																																																					facturaElectronica.setClienteTelefono(d.getCliente().getTelefono().toString());
																																																				} else {
																																																					facturaElectronica.setClienteTelefono(Constantes.EMPTY);
																																																				}
																																																			}
																																																			if (d.getCliente().getLibreImpuesto() != null) {
																																																				if (d.getCliente().getLibreImpuesto().equals(Constantes.LIBRE_IMPUESTOS_ACTIVO)) {
																																																					facturaElectronica.setNumeroDocumentoExoneracion(Constantes.DOCUMENTO_LIBRE_IVA);
																																																				} else {
																																																					facturaElectronica.setNumeroDocumentoExoneracion(Constantes.EMPTY);
																																																				}

																																																			} else {
																																																				facturaElectronica.setNumeroDocumentoExoneracion(Constantes.EMPTY);
																																																			}

																																																			facturaElectronica.setFooterTotalDescuento(d.getTotalDescuentos());
																																																			facturaElectronica.set_logo(d.getEmpresa().getLogo());
																																																			facturaElectronica.set_clienteDireccion(d.getDireccion());
																																																			// Otros
																																																			facturaElectronica.setTipoDocumento(FacturaElectronicaUtils.getTipoDocumento(d.getTipoDoc()));
																																																			facturaElectronica.setClave(d.getClave() == null ? Constantes.EMPTY : d.getClave());
																																																			facturaElectronica.setConsecutivo(d.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) ? Constantes.EMPTY : d.getNumeroConsecutivo());
																																																			facturaElectronica.setConsecutivoProforma(d.getConsecutivoProforma());
																																																			facturaElectronica.setFechaEmision(Utils.getFechaGeneraReporte(d.getFechaEmision()));
																																																			facturaElectronica.setPlazoCredito(d.getPlazoCredito() != null ? d.getPlazoCredito().toString() : Constantes.EMPTY);
																																																			facturaElectronica.setCondicionVenta(BIND_CONDICION_VENTA.apply(d.getCondicionVenta()));
																																																			facturaElectronica.setMedioEfectivo(FacturaElectronicaUtils.medioPago(d));
																																																			facturaElectronica.setMoneda(FacturaElectronicaUtils.getMoneda(d.getCodigoMoneda()));
																																																			facturaElectronica.setTipoCambio(d.getTipoCambio().toString());

																																																			// Nota Credito y Nota Debito
																																																			if (d.getReferenciaCodigo() != null) {
																																																				if (!d.getReferenciaCodigo().equals(Constantes.EMPTY)) {
																																																					facturaElectronica.setReferenciaCodigo(MapEnums.ENUM_CODIGO_REFERENCIA.get(d.getReferenciaCodigo()));
																																																					facturaElectronica.setReferenciaNumero(d.getReferenciaNumero());
																																																					facturaElectronica.setReferenciaRazon(d.getReferenciaRazon());
																																																					facturaElectronica.setReferenciaTipoDoc(MapEnums.ENUM_TIPO_DOC.get(d.getReferenciaTipoDoc()));
																																																					facturaElectronica.setReferenciaFechaEmision(d.getReferenciaFechaEmision() != null ? d.getReferenciaFechaEmision().toString() : Constantes.EMPTY);

																																																				}

																																																			} else {
																																																				facturaElectronica.setReferencia(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaCodigo(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaNumero(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaRazon(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaTipoDoc(Constantes.EMPTY);
																																																				facturaElectronica.setReferenciaFechaEmision(Constantes.EMPTY);

																																																			}
																																																			return facturaElectronica;
																																																		};

	private Logger																										log															= LoggerFactory.getLogger(this.getClass());

	@Autowired
	HaciendaBo																												haciendaBo;

	@Autowired
	RespuestaHaciendaXMLService																				respuestaHaciendaXMLService;

	@Autowired
	SemaforoBo																												semaforoBo;

	@Autowired
	CorreosBo																													correosBo;

	@Autowired
	OpenIDConnectHaciendaComponent																		openIDConnect;

	@Autowired
	EnvioHaciendaComponent																						envioHaciendaComponent;

	@Autowired
	FacturaBo																													facturaBo;

	@Autowired
	CompraSimplificadaBo																							compraSimplificadaBo;

	@Autowired
	EmpresaBo																													empresaBo;

	@Autowired
	DetalleBo																													detalleBo;

	@Autowired
	CuentaCobrarBo																										cuentaCobrarBo;

	@Autowired
	ConsultasNativeBo																									consultasNativeBo;

	@Autowired
	RecepcionFacturaBo																								recepcionFacturaBo;

	@Autowired
	CertificadoBo																											certificadoBo;

	@Autowired
	GraficoVentasBo																										graficoVentasBo;

	@Autowired
	FacturaXMLServices																								facturaXMLServices;

	@Autowired
	CompraSimplificadaXMLServices																			compraSimplificadaXMLServices;

	@Autowired
	TiqueteXMLService																									tiqueteXMLService;

	@Autowired
	NotaCreditoXMLServices																						notaCreditoXMLServices;

	@Autowired
	NotaCreditoXMLIVAServices																					notaCreditoXMLIVAServices;

	@Autowired
	NotaDebitoXMLService																							notaDebitoXMLService;

	@Autowired
	NotaDebitoXMLIVAService																						notaDebitoXMLIVAService;

	@Autowired
	RecepcionFacturaXMLServices																				recepcionFacturaXMLServices;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


//	@Scheduled(cron = "0 0/10 04 * * ?")
	@Override
	public synchronized void graficoVenta() throws Exception {
		log.info("inicio Totales de Grafico  {}", new Date());
		Year anno = Year.now();
		
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue();
		
		Integer annno = new Integer(anno.getValue());
		graficoVentasBo.actualizarGraficoVenta(annno,month);
		log.info("fin Totales de Grafico  {}", new Date());

	}

//	 @Scheduled(cron = "0 0/1 * * * ?")
		@Override
		public synchronized void procesoCambiarConsecutivo() throws Exception {

			Collection<Factura> listaHacienda = facturaBo.findByEstadoFirma(2, 2);
			log.info("Inicio de actualizar estado - {}", formatter.format(LocalDateTime.now()));
			Integer cont =0;
			for (Factura factura : listaHacienda) {

				Hacienda hacienda  = haciendaBo.findByEmpresaAndClave(factura.getEmpresa(),factura.getClave());
				
				if (cont >= 100) {
					log.info(cont + "contador - {}", formatter.format(LocalDateTime.now()));
					cont = 0;
					
				}

				if (hacienda != null) {
					if(hacienda.getEstado() == 7 || hacienda.getEstado() == 6  ) {
						cont +=1;
						factura.setEstado(hacienda.getEstado());
						facturaBo.modificar(factura);
						
					}

				}
			}
			log.info("fin de actualizar estado - {}", formatter.format(LocalDateTime.now()));

		}
//	@Scheduled(cron = "0 0/50 22 * 6 ?")
	@Override
	public synchronized void taskCuentasPorCobrarVencidas() throws Exception {
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Collection<CuentaCobrar> objetoCuentasPorCobrar = cuentaCobrarBo.cuentasPorCobrarbyEstado(Constantes.CUENTA_POR_COBRAR_ESTADO_PENDIENTE);
			for (CuentaCobrar cuentaCobrar : objetoCuentasPorCobrar) {
				log.info("Inicio Proceso de cuentas por cobrar de empresas con criterio de dias  {}", new Date());
				DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT7);
				Date fecha = new Date();
				String inicio = dateFormat1.format(cuentaCobrar.getFechaPlazo());
				String fin = dateFormat1.format(fecha);
				Date fechaInicial = dateFormat.parse(inicio);
				Date fechaFinal = dateFormat.parse(fin);
				int dias = (int) ((fechaInicial.getTime() - fechaFinal.getTime()) / 86400000);
				// dias = cuentaCobrar.getPlazoCredito() != null?cuentaCobrar.getPlazoCredito().intValue() - dias:0;
				if (dias <= -64) {
					if (!cuentaCobrar.getEmpresa().getCorreoCredito().equals(Constantes.EMPTY)) {
						enviarCorreoCuentasPorCobrar(cuentaCobrar, dias);
					}
				}
				log.info("Fin Proceso de cuentas por cobrar de la empresas con criterio de dias  {}", new Date());
			}

		} catch (Exception e) {
			log.error("** Error2  Proceso cuentas por cobrar: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	private void enviarCorreoCuentasPorCobrar(CuentaCobrar cuentaCobrar, int dias) throws Exception {
		try {

			Map<String, Object> modelEmail = new HashMap<>();

			modelEmail.put("nombreEmpresa", cuentaCobrar.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? cuentaCobrar.getEmpresa().getNombre() : cuentaCobrar.getEmpresa().getNombreComercial());
			modelEmail.put("correo", cuentaCobrar.getEmpresa().getCorreoElectronico());
			modelEmail.put("telefono", cuentaCobrar.getEmpresa().getTelefono());
			String from = cuentaCobrar.getEmpresa().getCorreoCredito();
			if (cuentaCobrar.getEmpresa().getAbreviaturaEmpresa() != null) {
				if (!cuentaCobrar.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
					from = "Creditos_" + cuentaCobrar.getEmpresa().getAbreviaturaEmpresa() + "_" + "_No_Reply@emprendesoftcr.com";
				}
			}

			ArrayList<String> listaCorreos = new ArrayList<String>();
			Factura factura = facturaBo.findByConsecutivoAndEmpresa(cuentaCobrar.getFactura(), cuentaCobrar.getEmpresa());
			String subject = "#Factura con el Credito vencido #: " + cuentaCobrar.getFactura().trim();
			String plantillaEmail = "email/cuentasPorCobrarVendidas.vm";
			modelEmail.put("totalSaldo", Utils.formateadorMiles(cuentaCobrar.getTotalSaldo()));
			modelEmail.put("cliente", cuentaCobrar.getCliente().getNombreCompleto());
			modelEmail.put("factura", cuentaCobrar.getFactura());
			modelEmail.put("plazoDias", dias < 0 ? dias * -1 : dias);
			modelEmail.put("fechaEmision", cuentaCobrar.getCreated_atSTR());
			modelEmail.put("fechaPlazo", cuentaCobrar.getFechaPlazoSTR());
			if (factura != null) {

				FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
				Collection<Detalle> detalles = detalleBo.findByFactura(factura);
				List<DetalleFacturaElectronica> detallesFactura = detalles.stream().sorted(Comparator.comparingInt(Detalle::getNumeroLinea)).map(TO_DETALLE).collect(toList());
				facturaElectronica.setDetalleFacturaElectronica(detallesFactura);
				// ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
				ByteArrayOutputStream namePDF = ReportePdfView.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
				String clave = getConsecutivo(factura.getTipoDoc(), factura.getNumeroConsecutivo());
				Collection<Attachment> attachments = createAttachments(PDF_Attach(clave, factura.getEmpresa().getCedula(), asPDF(namePDF), factura.getTipoDoc()));
				if (cuentaCobrar.getEmpresa().getCorreoCredito() != null) {
					if (!cuentaCobrar.getEmpresa().getCorreoCredito().equals(Constantes.EMPTY)) {
						listaCorreos.add(cuentaCobrar.getEmpresa().getCorreoCredito());
						correosBo.enviarConAttach(attachments, listaCorreos, from, subject, plantillaEmail, modelEmail);
					}
				}

			}

		} catch (Exception e) {
			log.error("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + cuentaCobrar.getEmpresa().getNombre() + " Consecutivo" + cuentaCobrar.getFactura());
			throw e;
		}
	}

	/**
	 * Anulacion automatico de proformas mas o igual a 30 dias.
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#taskAnularProformas()
	 */
	@Scheduled(cron = "0 0/50 14 * * ?")
	@Override
	public synchronized void taskAnularProformas() throws Exception {
		try {
			log.info("Inicio Proceso de Anulacion de Proformas de empresas con criterio de dias  {}", new Date());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Collection<ProformasByEmpresaAndEstado> objetoFacturas = consultasNativeBo.findByProformasByEmpresaAndEstado(null, Constantes.FACTURA_ESTADO_PROFORMAS);
			for (ProformasByEmpresaAndEstado proformasByEmpresaAndEstado : objetoFacturas) {
				Factura factura = facturaBo.findById(proformasByEmpresaAndEstado.getId());
				if (factura != null) {
					DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT7);
					Date fecha = new Date();
					String inicio = dateFormat1.format(factura.getCreated_at());
					String fin = dateFormat1.format(fecha);
					Date fechaInicial = dateFormat.parse(inicio);
					Date fechaFinal = dateFormat.parse(fin);
					int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
					if (dias >= 28) {
						factura.setEstado(Constantes.FACTURA_ESTADO_ANULADA_PROFORMA);
						factura.setNota(Constantes.ANULACION_AUTOMATICA_PROFORMAS);
						facturaBo.modificar(factura);
					}
				}
				log.info("Fin Proceso de Anulacion de Proformas de empresas con criterio de dias  {}", new Date());

			}
		} catch (Exception e) {
			log.error("** Error2  Proceso de anulacion de proformas: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Proceso automatico para ejecutar el envio de los documentos de hacienda documentos xml ya firmados
	 */
//	@Scheduled(cron = "0 0/12 * * * ?")
	@Override
	public synchronized void taskHaciendaEnvio() throws Exception {

		ArrayList<Hacienda> facturasConProblemas = new ArrayList<Hacienda>();
		OpenIDConnectHacienda openIDConnectHacienda = null;
		Hacienda haciendaBD = null;
		try {
  		Semaforo semaforoEnvio = semaforoBo.findByEstadoAndID(Constantes.SEMAFORO_ESTADO_ACTIVO, Constantes.SEMAFORO_ESTADO_ENVIO);
			if (semaforoEnvio != null) {
				// Listado de los documentos Pendientes de enviar hacienda
				Collection<Hacienda> listaHacienda = haciendaBo.findByEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML, Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_ERROR);
				if (listaHacienda != null) {

					if (!listaHacienda.isEmpty()) {
						log.info("Inicio Proceso de Envio de documentos - {}", formatter.format(LocalDateTime.now()));
						
						for (Hacienda hacienda : listaHacienda) {
							try {
								log.info("Documentos hacienda:" + hacienda.getConsecutivo() + " Empresa" + hacienda.getEmpresa().getNombre());
								haciendaBD = haciendaBo.findById(hacienda.getId());
								openIDConnectHacienda = envioHacienda(haciendaBD, openIDConnectHacienda);

							} catch (Exception e) {
								// Se modifica el registros
								if (hacienda.getReintentos() == null) {
									hacienda.setReintentos(Constantes.ZEROS);
								}
								if (hacienda.getReintentos() > Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA_TOPE_REINTENTOS) {
									hacienda.setNotificacion(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA);
									hacienda.setEstado(Constantes.HACIENDA_ESTADO_PROBLEMA_ENVIO_NO_TRASABLE);
									facturasConProblemas.add(hacienda);
								} else {
									hacienda.setReintentos(hacienda.getReintentos() == null ? 1 : hacienda.getReintentos() + 1);
								}
								haciendaBo.modificar(hacienda);
								log.info("** Error1  taskHaciendaEnvio: " + e.getMessage() + " hora :" + formatter.format(LocalDateTime.now()) + "Empresa:" + hacienda.getEmpresa().getNombre() + " Consecutivo :" + hacienda.getConsecutivo());
							}

						}

					} else {
						log.info("Finaliza Proceso de Envio de documentos - {}", formatter.format(LocalDateTime.now()));
						
					}

				}

			} else {
				log.info("Semaforo de envio documentos INACTIVO  {}", formatter.format(LocalDateTime.now()));
			}
		} catch (Exception e) {
			log.error("** Error2  taskHaciendaEnvio: " + e.getMessage() + " hora " + formatter.format(LocalDateTime.now()));
			throw e;
		} finally {
			// Desconectar token de hacienda anterior
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(openIDConnectHacienda.getEmpresa(), openIDConnectHacienda);
				}
			}
		}
		if (facturasConProblemas != null) {
			if (!facturasConProblemas.isEmpty()) {
				soporteProblemaConHacienda(facturasConProblemas);
			}
		}

	}

	/**
	 * Para cuando hay problemas de conexion con hacienda
	 * @param FacturasConProblemas
	 * @throws Exception
	 */
	private void soporteProblemaConHacienda(ArrayList<Hacienda> FacturasConProblemas) throws Exception {
		try {
			for (Hacienda hacienda : FacturasConProblemas) {
				String subject = "EmpredesoftSoporte  Empresa :" + hacienda.getEmpresa().getNombre() + " Problemas de conexion";
				String texto = "Empresa :" + hacienda.getEmpresa().getNombre() + " tiene  Problemas de conexion" + " Consecutivo de Factura : " + hacienda.getConsecutivo();
				correosBo.sendSimpleMessage("josehernandezchaverri@gmail.com", subject, texto);
				correosBo.sendSimpleMessage("vivianamartinezgranados@gmail.com", subject, texto);
				correosBo.sendSimpleMessage("jcisneroscr@gmail.com", subject, texto);

			}
		} catch (Exception e) {
			log.error("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Comunicacion con Hacienda para aceptar el documento
	 * @param hacienda
	 * @throws Exception
	 */

	@Override
	public OpenIDConnectHacienda envioHacienda(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception {

		// Se verifica si es la misma empresa de la conexion anterior o bien si es null
		if ((openIDConnectHacienda == null) || (openIDConnectHacienda != null && !hacienda.getEmpresa().getId().equals(openIDConnectHacienda.getEmpresa().getId()))) {

			// Desconectar token de hacienda anterior
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(hacienda.getEmpresa(), openIDConnectHacienda);
				}
			}

			// Obtener el token en hacienda para enviar los documentos
			openIDConnectHacienda = openIDConnect.getToken(hacienda.getEmpresa());
		}

		try {
			// Se obtuvo el token de accienda
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getAccess_token().length() > 0) {
					envioHaciendaFacturas(hacienda, openIDConnectHacienda);
				}
			} else {
				log.info("** Error no se encontro el token   " + "Empresa:" + hacienda.getEmpresa().getNombre() + " fecha " + new Date() + "Consecutivo " + hacienda.getConsecutivo());
			}
		} catch (Exception e) {
			log.error("** Error  ejecutarEnvio: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return openIDConnectHacienda;
	}

	/**
	 * Este proceso realiza la comunicacion con hacienda 1. Obtener el token de la comunicacion con hacienda del cliente
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#envioHaciendaFacturas()
	 */
	private void envioHaciendaFacturas(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception {
		try {
			// Crea el objeto recepción que se enviará a los APIs.
			Recepcion recepcion = new Recepcion();
			ReceptorHacienda receptor = null;
			if (hacienda.getCedulaReceptor() != null) {
				if (!hacienda.getCedulaReceptor().equals(Constantes.EMPTY)) {
					receptor = new ReceptorHacienda(hacienda.getTipoReceptor() == null ? Constantes.EMPTY : hacienda.getTipoReceptor(), hacienda.getCedulaReceptor() == null ? Constantes.EMPTY : hacienda.getCedulaReceptor());
				}

			}
			EmisorHacienda emisor = new EmisorHacienda(hacienda.getTipoEmisor(), hacienda.getCedulaEmisor());
			recepcion.setClave(hacienda.getClave().trim());
			String date = FacturaElectronicaUtils.toISO8601String(hacienda.getFechaEmisor());
			recepcion.setFecha(date);
			// Emisor
			recepcion.setEmisor(emisor);
			// Receptor (condicional)
			if (receptor != null) {
				recepcion.setReceptor(receptor);
			}
			// XML se convierte en base 64
			String valor = FacturaElectronicaUtils.convertirBlodToString(hacienda.getComprobanteXML());

			// valor = valor.replaceAll("\n", "");

			if (valor.length() > 0) {

				String base64 = FacturaElectronicaUtils.base64Encode(valor);
				recepcion.setComprobanteXml(base64);
				Semaforo semaforoCallback = semaforoBo.findByEstadoAndID(Constantes.SEMAFORO_ESTADO_ACTIVO, Constantes.SEMAFORO_ESTADO_CALLBACK);

				if (semaforoCallback != null) {
					// Ambiente de pruebas
					// recepcion.setCallbackUrl(Constantes.URL_PRUEBAS_CALLBACK);

					// San Ana
					//recepcion.setCallbackUrl(Constantes.URL_SANTA_ANA_CALLBACK);

					// Guanacaste
				//	 recepcion.setCallbackUrl(Constantes.URL_GUANACASTE_CALLBACK);

					// JacoDos
					//recepcion.setCallbackUrl(Constantes.URL_JACODOS_CALLBACK);

					// Jaco
				//	 recepcion.setCallbackUrl(Constantes.URL_JACO_CALLBACK);

					// Inventario
					 recepcion.setCallbackUrl(Constantes.URL_INVENTARIO_CALLBACK);

					// Alajuela
					// recepcion.setCallbackUrl(Constantes.URL_ALAJUELA_CALLBACK);

				} else {
					recepcion.setCallbackUrl(Constantes.EMPTY);
					log.info("Semaforo de envio callback INACTIVO  {}", new Date());
				}
				ObjectMapper mapperObj = new ObjectMapper();
				String jsonStr = mapperObj.writeValueAsString(recepcion);
				envioHaciendaComponent.enviarDocumentoElectronico(jsonStr, openIDConnectHacienda, hacienda);

			}
		} catch (Exception e) {
			log.error("** Error  envioHaciendaFacturas: " + e.getMessage() + " fecha " + new Date() + " Hacienta:" + hacienda.getEmpresa().getNombre());
			throw e;
		}

	}

	/**
	 * Retorna el status de la respuesta de hacienda
	 * @param indEstado Elemento ind-estado de la respuesta de hacienda
	 * @return Estado de la respuesta de hacienda OK o ERROR
	 */
	private static String getHaciendaStatus(String indEstado) {
		return MapEnums.ENUM_CODIGO_RESPUESTA_HACIENDA.containsKey(indEstado) ? MapEnums.ENUM_CODIGO_RESPUESTA_HACIENDA.get(indEstado) : ERROR;
	}

	/**
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#taskHaciendaComprobacionDocumentos()
	 */
//	@Scheduled(cron = "0 0/15 * * * ?")
	@Override
	public synchronized void taskHaciendaComprobacionDocumentos() throws Exception {
		OpenIDConnectHacienda openIDConnectHacienda = null;
		Hacienda haciendaBD = null;
		try {
			Semaforo semaforoComprobarDocumento = semaforoBo.findByEstadoAndID(Constantes.SEMAFORO_ESTADO_ACTIVO, Constantes.SEMAFORO_ESTADO_COMPROBAR_DOCUMENTOS);
			if (semaforoComprobarDocumento != null) {
				// Semaforo semaforo = semaforoBo.findByEstado(Constantes.SEMAFORO_ESTADO_COMPROBAR_DOCUMENTOS);
				// Listado de los documentos Pendientes de aceptar por hacienda
				Collection<HaciendaComprobarNative> listaHacienda = consultasNativeBo.findByComprabarDocumentoPendienteaceptar();
				if (listaHacienda != null) {
					if (!listaHacienda.isEmpty()) {
						for (HaciendaComprobarNative hacienda : listaHacienda) {
							try {
								log.info("Comprobacion de documentos - {}", formatter.format(LocalDateTime.now()));
								
								Date fecha = new Date();
								long tiempoInicial = hacienda.getCreated_at().getTime();
								long tiempoFinal = fecha.getTime();
								long resta = tiempoFinal - tiempoInicial;
								// el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
								if (resta > 0) {
									resta = resta / (1000 * 60);
								}
								log.info("Comprobando Documentos:" + hacienda.getConsecutivo() + " Empresa :" + hacienda.getNombreEmpresa());
								if (resta >= 6 || hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ERROR) || hacienda.getTipoDoc().equals(Constantes.HACIENDA_TIPODOC_COMPRAS)) {
									log.info("Documento cumplio 30 minutos:" + hacienda.getConsecutivo() + " Empresa :" + hacienda.getNombreEmpresa());
									if (hacienda.getReintentosAceptacion() != null) {
										if (hacienda.getReintentosAceptacion() <= Constantes.MAXIMO_REINTENTOS_ACEPTACION) {
											haciendaBD = haciendaBo.findById(hacienda.getId());
											openIDConnectHacienda = aceptarDocumento(haciendaBD, openIDConnectHacienda);
										} else {
											haciendaBD = haciendaBo.findById(hacienda.getId());
											haciendaBD.setObservacion(FacturaElectronicaUtils.convertirStringToblod(Constantes.MAXIMO_REINTENTOS_ACEPTACION_STR));
											haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
											haciendaBD.setCallBack(Constantes.CALLBACKURL_NO);
											haciendaBo.modificar(haciendaBD);
											cambiarEstado(haciendaBD);
										}
									} else {
										haciendaBD = haciendaBo.findById(hacienda.getId());
										haciendaBD.setReintentosAceptacion(Constantes.ZEROS);
										haciendaBo.modificar(haciendaBD);
									}

								}

							} catch (Exception e) {
								log.info("** Error1  ComprobacionDocumentos: " + e.getMessage() + " fecha " + new Date());
							}
						}
						log.info("Fin Comprobacion de documentos - {}", formatter.format(LocalDateTime.now()));
						
					}
				}

			} else {
				log.info("Semaforo comprabar documentos INACTIVO  {}", new Date());
			}

		} catch (Exception e) {
			log.error("** Error2  ComprobacionDocumentos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		} finally {
			// Desconectar token de hacienda anterior
			if (openIDConnectHacienda != null) {
				if (openIDConnectHacienda.getRefresh_token().length() > 0) {
					openIDConnect.desconectarToken(openIDConnectHacienda.getEmpresa(), openIDConnectHacienda);
				}
			}
		}
	}

	/**
	 * Aceptar documentos
	 * @param hacienda
	 */
	@Override
	public OpenIDConnectHacienda aceptarDocumento(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception {
		try {
			Boolean aplicarCambioEstadoFactura = Boolean.FALSE;
			// Se verifica si es la misma empresa de la conexion anterior o bien si es null
			if ((openIDConnectHacienda == null) || (openIDConnectHacienda != null && !hacienda.getEmpresa().getId().equals(openIDConnectHacienda.getEmpresa().getId()))) {

				// Desconectar token de hacienda anterior
				if (openIDConnectHacienda != null) {
					if (openIDConnectHacienda.getRefresh_token().length() > 0) {
						openIDConnect.desconectarToken(hacienda.getEmpresa(), openIDConnectHacienda);
					}
				}
				// Obtener el token en hacienda para enviar los documentos
				openIDConnectHacienda = openIDConnect.getToken(hacienda.getEmpresa());
			}
			if (openIDConnectHacienda != null) {
				// Se obtuvo el token de accienda
				if (openIDConnectHacienda.getAccess_token().length() > 0) {
					String idp_uri_documentos = Constantes.EMPTY;
					if (hacienda.getEmpresa().getEstadoProduccion() != null) {
						if (hacienda.getEmpresa().getEstadoProduccion().equals(Constantes.ESTADO_ACTIVO)) {
							idp_uri_documentos = Constantes.IDP_URI_DOCUMENTOS_PRODUCCION;
						}
					}
					@SuppressWarnings("rawtypes")
					Map response = envioHaciendaComponent.comprobarDocumentoElectronico(idp_uri_documentos, hacienda.getClave(), openIDConnectHacienda);
					String body = (String) response.get(POST_RESPONSE);

					if (body != null && body != "" && body != "{}" && !body.contains("El comprobante") && !body.contains("no ha sido recibido")) {
						RespuestaHacienda respuestaHacienda = RespuestaHaciendaJson.from(body);
						String status = getHaciendaStatus(respuestaHacienda.indEstado());
						hacienda.setUpdated_at(new Date());
						RespuestaHaciendaXML respuesta = new RespuestaHaciendaXML();
						respuesta.setClave(respuestaHacienda.clave());
						respuesta.setFecha(respuestaHacienda.fecha());
						respuesta.setIndEstado(respuestaHacienda.indEstado());
						respuesta.setMensaje(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().mensaje() : Constantes.EMPTY);
						respuesta.setDetalleMensaje(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().detalleMensaje() : Constantes.EMPTY);
						respuesta.setMontoTotalImpuesto(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().montoTotalImpuesto() : Constantes.ZEROS_DOUBLE);
						respuesta.setNombreEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().nombreEmisor() : Constantes.EMPTY);
						respuesta.setNombreReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().nombreReceptor() : Constantes.EMPTY);
						respuesta.setNumeroCedulaEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().numeroCedulaEmisor() : Constantes.EMPTY);
						respuesta.setNumeroCedulaReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().numeroCedulaReceptor() : Constantes.EMPTY);
						respuesta.setTipoIdentificacionEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().tipoIdentificacionEmisor() : Constantes.EMPTY);
						respuesta.setTipoIdentificacionReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().tipoIdentificacionReceptor() : Constantes.EMPTY);
						respuesta.setTotalFactura(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().totalFactura() : Constantes.ZEROS_DOUBLE);
						String xmlSinFirmarRespuesta = Constantes.EMPTY;
						String xmlFirmadoRespuesta = Constantes.EMPTY;
						Factura facturaConsultada = facturaBo.findByConsecutivoAndEmpresa(hacienda.getConsecutivo(), hacienda.getEmpresa());
						if (!status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECIBIDO)) {
							xmlSinFirmarRespuesta = respuestaHaciendaXMLService.getCrearXMLSinFirma(respuesta, facturaConsultada);
							xmlFirmadoRespuesta = respuestaHaciendaXMLService.getFirmarXML(xmlSinFirmarRespuesta, hacienda.getEmpresa(), facturaConsultada);
						} else {
							if (respuestaHacienda.mensajeHacienda() != null) {
								if (respuestaHacienda.mensajeHacienda().mensaje() != null) {
									if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO)) {
										xmlSinFirmarRespuesta = respuestaHaciendaXMLService.getCrearXMLSinFirma(respuesta, facturaConsultada);
										xmlFirmadoRespuesta = respuestaHaciendaXMLService.getFirmarXML(xmlSinFirmarRespuesta, hacienda.getEmpresa(), facturaConsultada);
									}
								}
							}
						}
						Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
						if (xmlFirmadoRespuesta != Constantes.EMPTY) {
							haciendaBD.setMensajeHacienda(FacturaElectronicaUtils.convertirStringToblod(xmlFirmadoRespuesta));
						}
						/**
						 * Esperar el correo FE para saber que ese estado de recibido
						 */
						log.info("*status: " + status);
						if (haciendaBD.getNumeroFactura() == null) {
							Factura facturaAplicar = haciendaBD.getNumeroFactura() != null ? facturaBo.findById(haciendaBD.getNumeroFactura()) : null;
							if (facturaAplicar == null) {
								facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());
							}
							haciendaBD.setNumeroFactura(facturaAplicar != null ? facturaAplicar.getId() : null);
						}

						haciendaBD.setEstado(getEstado(status, respuestaHacienda));

						if (!haciendaBD.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
							if (haciendaBD.getReintentosAceptacion() != null) {
								if (haciendaBD.getReintentosAceptacion() > Constantes.MAXIMO_REINTENTOS_ACEPTACION) {
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
									haciendaBD.setObservacion(FacturaElectronicaUtils.convertirStringToblod(Constantes.MAXIMO_REINTENTOS_ACEPTACION_STR));
								} else {
									haciendaBD.setReintentosAceptacion(haciendaBD.getReintentosAceptacion() == null ? 1 : haciendaBD.getReintentosAceptacion() + 1);
									haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA);
								}
							} else {
								haciendaBD.setReintentosAceptacion(haciendaBD.getReintentosAceptacion() == null ? 1 : haciendaBD.getReintentosAceptacion() + 1);
								haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA);
							}
						} else {
							aplicarCambioEstadoFactura = true;
						}

						haciendaBD.setObservacion(respuestaHacienda.mensajeHacienda() != null ? FacturaElectronicaUtils.convertirStringToblod(respuestaHacienda.mensajeHacienda().detalleMensaje()) : null);
						haciendaBD.setCallBack(Constantes.CALLBACKURL_NO);
						haciendaBo.modificar(haciendaBD);
						if (aplicarCambioEstadoFactura) {
							cambiarEstado(haciendaBD);
						}
					} else {// sumar reintententos
						Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
						if (haciendaBD != null) {
							if (body.contains("El comprobante") && body.contains("no ha sido recibido")) {
								haciendaBD.setxErrorCause(FacturaElectronicaUtils.convertirStringToblod(body));
								haciendaBD.setObservacion(FacturaElectronicaUtils.convertirStringToblod(body));
								haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_ERROR);

							} else {
								haciendaBD.setReintentosAceptacion(hacienda.getReintentosAceptacion() == null ? 1 : hacienda.getReintentosAceptacion() + 1);
							}
							haciendaBD.setCallBack(Constantes.CALLBACKURL_NO);
							haciendaBo.modificar(haciendaBD);
							if (aplicarCambioEstadoFactura) {
								cambiarEstado(haciendaBD);
							}

						}

					}
				}
			}
		} catch (Exception e) {
			log.error("** Error  aceptarDocumento: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre());
			throw e;
		}

		return openIDConnectHacienda;
	}

	/**
	 * Devolver estado
	 * @return
	 */
	private Integer getEstado(String status, RespuestaHacienda respuesta) {
		Integer resultado = Constantes.HACIENDA_ESTADO_ENVIADO_HACIENDA;

		if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA_STR) || status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_ACEPTADO_HACIENDA_STR)) {
			return Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA;
		}

		if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO_STR)) {
			return Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO;
		}

		if (respuesta == null) {
			return resultado;
		}
		if (respuesta.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO) || respuesta.mensajeHacienda().mensaje().contains(Constantes.HACIENDA_ESTADO_ACEPTADO_ACEPTADO_HACIENDA_STR)) {
			return Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA;
		}

		if (respuesta.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO_PARCIAL)) {
			return Constantes.HACIENDA_ESTADO_ACEPTADO_PARCIAL;
		}

		if (respuesta.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_RECHAZADO)) {
			return Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO;
		}

		return resultado;
	}

	private void cambiarEstado(Hacienda hacienda) throws Exception {
		try {
			if (hacienda != null) {
				if (hacienda.getTipoDoc().equals(Constantes.HACIENDA_TIPODOC_COMPRAS)) {
					RecepcionFactura recepcionFactura = recepcionFacturaBo.findByConsecutivoAndEmpresa(hacienda.getConsecutivo(), hacienda.getEmpresa());
					if (recepcionFactura != null) {
						if (hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
							recepcionFactura.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA);
						} else if (hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
							recepcionFactura.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
						}
						recepcionFacturaBo.modificar(recepcionFactura);
					}

				} else if (hacienda.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_COMPRA_SIMPLIFICADA)) {
					CompraSimplificada compraSimplificada = compraSimplificadaBo.findByConsecutivoAndEmpresa(hacienda.getConsecutivo(), hacienda.getEmpresa());
					if (compraSimplificada != null) {
						if (compraSimplificada != null) {
							if (hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
								compraSimplificada.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA);
							} else if (hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
								compraSimplificada.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
							}

							compraSimplificadaBo.modificar(compraSimplificada);
						}
					}
				} else {

					Factura factura = hacienda.getNumeroFactura() != null ? facturaBo.findById(hacienda.getNumeroFactura()) : null;
					if (factura == null) {
						facturaBo.findByConsecutivoAndEmpresa(hacienda.getConsecutivo(), hacienda.getEmpresa());
					}
					if (factura != null) {
						if (hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
							factura.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA);
						} else if (hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
							factura.setEstado(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO);
						}
						facturaBo.modificar(factura);

					}
				}
			}

		} catch (Exception e) {
			log.error("** Error  cambiar el estado Factura: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre());
			throw e;
		}
	}

	/**
	 * Verifica si un mensaje por clave fue aceptado y recibido por hacienda
	 * @param empresa
	 * @param clave del documento
	 */
	public Boolean verificaRecepcionFactura(Empresa empresa, String clave) throws Exception {

		boolean resultado = false;

		try {

			OpenIDConnectHacienda openIDConnectHacienda = openIDConnect.getToken(empresa);
			if (openIDConnectHacienda != null) {

				// Se obtuvo el token de accienda
				if (openIDConnectHacienda.getAccess_token().length() > 0) {
					String idp_uri_documentos = Constantes.EMPTY;
					if (empresa.getEstadoProduccion() != null) {
						if (empresa.getEstadoProduccion().equals(Constantes.ESTADO_ACTIVO)) {
							idp_uri_documentos = Constantes.IDP_URI_DOCUMENTOS_PRODUCCION;
						}
					}

					@SuppressWarnings("rawtypes")
					Map response = envioHaciendaComponent.comprobarDocumentoElectronico(idp_uri_documentos, clave, openIDConnectHacienda);
					String body = (String) response.get(POST_RESPONSE);
					if (body != null && body != "" && body != "{}" && !body.contains("El comprobante") && !body.contains("no ha sido recibido")) {

						RespuestaHacienda respuestaHacienda = RespuestaHaciendaJson.from(body);
						String status = getHaciendaStatus(respuestaHacienda.indEstado());
						RespuestaHaciendaXML respuesta = new RespuestaHaciendaXML();
						respuesta.setClave(respuestaHacienda.clave());
						respuesta.setFecha(respuestaHacienda.fecha());
						respuesta.setIndEstado(respuestaHacienda.indEstado());
						respuesta.setMensaje(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().mensaje() : Constantes.EMPTY);
						respuesta.setDetalleMensaje(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().detalleMensaje() : Constantes.EMPTY);
						respuesta.setMontoTotalImpuesto(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().montoTotalImpuesto() : Constantes.ZEROS_DOUBLE);
						respuesta.setNombreEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().nombreEmisor() : Constantes.EMPTY);
						respuesta.setNombreReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().nombreReceptor() : Constantes.EMPTY);
						respuesta.setNumeroCedulaEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().numeroCedulaEmisor() : Constantes.EMPTY);
						respuesta.setNumeroCedulaReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().numeroCedulaReceptor() : Constantes.EMPTY);
						respuesta.setTipoIdentificacionEmisor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().tipoIdentificacionEmisor() : Constantes.EMPTY);
						respuesta.setTipoIdentificacionReceptor(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().tipoIdentificacionReceptor() : Constantes.EMPTY);
						respuesta.setTotalFactura(respuestaHacienda.mensajeHacienda() != null ? respuestaHacienda.mensajeHacienda().totalFactura() : Constantes.ZEROS_DOUBLE);
						if (status.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECIBIDO)) {
							if (respuestaHacienda.mensajeHacienda() != null) {
								if (respuestaHacienda.mensajeHacienda().mensaje() != null) {
									if (respuestaHacienda.mensajeHacienda().mensaje().contains(Constantes.ESTADO_HACIENDA_ACEPTADO)) {
										resultado = true;
									}
								}
							}
						}

					}
				}
			}
		} catch (Exception e) {
			log.info("** Error  aceptarDocumento: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + empresa.getNombre());
			throw e;
		}
		return resultado;
	}

	/**
	 * Solo se van enviar correos a la empresa cuando es un cliente o correo alternativo los tiquetes de clientes frecuentes no lo vamos enviar para ver el comportamiento de rendimiento Enviar correos a los clientes que Tributacion acepto documento
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#taskHaciendaEnvioDeCorreos()
	 */
//	@Scheduled(cron = "0 0/16 * * * ?")
	@Override
	public synchronized void taskHaciendaEnvioDeCorreos() throws Exception {
		try {
			Semaforo semaforoEnvioCorreo = semaforoBo.findByEstadoAndID(Constantes.SEMAFORO_ESTADO_ACTIVO, Constantes.SEMAFORO_ESTADO_ENVIAR_CORREOS);
			if (semaforoEnvioCorreo != null) {
				Boolean noEnviarCorreoClienteFrecuente = false;
				// Listado de los documentos Pendientes de aceptar por hacienda
				Collection<Hacienda> listaHacienda = haciendaBo.findByEstadoAndNotificacion(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA, Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
				if (listaHacienda != null) {
					if (!listaHacienda.isEmpty()) {
						for (Hacienda hacienda : listaHacienda) {
							try {
								log.info("Inicio Envios de correos - {}", formatter.format(LocalDateTime.now()));
								
								noEnviarCorreoClienteFrecuente = false;
								Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
								ArrayList<String> listaCorreos = new ArrayList<String>();
								// Se determina si es una recepcion de factura
								if (!haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO) && !haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !haciendaBD.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
									RecepcionFactura recepcionFactura = recepcionFacturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());
									if (recepcionFactura != null) {
										listaCorreos.add(recepcionFactura.getEmpresa().getCorreoElectronico());
										if (recepcionFactura.getEmisorCorreo() != null) {
											if (!recepcionFactura.getEmisorCorreo().equals(Constantes.EMPTY)) {
												listaCorreos.add(recepcionFactura.getEmisorCorreo());
											}
										}
									}
									if (listaCorreos != null) {
										if (!listaCorreos.isEmpty()) {
											enviarCorreosRecepcion(recepcionFactura, haciendaBD, listaCorreos);
											haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
											haciendaBo.modificar(haciendaBD);
										}
									}
								} else {
									Factura factura = haciendaBD.getNumeroFactura() != null ? facturaBo.findById(haciendaBD.getNumeroFactura()) : null;
									if (factura == null) {
										facturaBo.findByConsecutivoAndEmpresa(haciendaBD.getConsecutivo(), haciendaBD.getEmpresa());
									}
									if (factura != null) {
										if (factura.getCorreoAlternativo() != null) {
											if (!factura.getCorreoAlternativo().equals(Constantes.EMPTY)) {
												listaCorreos.add(factura.getCorreoAlternativo());
											}
										}
										if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
											if (factura.getCliente().getCorreoElectronico() != null) {
												if (!factura.getCliente().getCorreoElectronico().equals(Constantes.EMPTY)) {
													listaCorreos.add(factura.getCliente().getCorreoElectronico());
												}
											}
											if (factura.getCliente().getCorreoElectronico1() != null) {
												if (!factura.getCliente().getCorreoElectronico1().equals(Constantes.EMPTY)) {
													listaCorreos.add(factura.getCliente().getCorreoElectronico1());
												}
											}
											if (factura.getCliente().getCorreoElectronico2() != null) {
												if (!factura.getCliente().getCorreoElectronico2().equals(Constantes.EMPTY)) {
													listaCorreos.add(factura.getCliente().getCorreoElectronico2());
												}

											}
											if (factura.getCliente().getCorreoElectronico3() != null) {
												if (!factura.getCliente().getCorreoElectronico3().equals(Constantes.EMPTY)) {
													listaCorreos.add(factura.getCliente().getCorreoElectronico3());
												}
											}
											String correo = Constantes.EMPTY;

											if (factura.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
												if (factura.getEmpresa().getCorreoCredito() != null) {
													if (!factura.getEmpresa().getCorreoCredito().equals(Constantes.EMPTY)) {
														correo = factura.getEmpresa().getCorreoCredito();
													}
												}
											}
											if (correo.equals(Constantes.EMPTY)) {
												listaCorreos.add(factura.getEmpresa().getCorreoElectronico());
											} else {
												listaCorreos.add(correo);
											}
										} else {// Cuando un cliente quiere que le llegue un correo alternativo
											haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_FRECUENTE_NO_ENVIADO);
											haciendaBo.modificar(haciendaBD);
											noEnviarCorreoClienteFrecuente = true;
										}
									}
									if (listaCorreos != null) {
										if (!listaCorreos.isEmpty()) {

											enviarCorreos(factura, haciendaBD, listaCorreos);
											if (noEnviarCorreoClienteFrecuente == false) {
												haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
												haciendaBo.modificar(haciendaBD);
											}
										}
									}

								}

							} catch (Exception e) {
								// Se modifica el registros
								Hacienda haciendaBD = haciendaBo.findById(hacienda.getId());
								if (haciendaBD != null) {
									// haciendaBD.setEstado(Constantes.HACIENDA_ESTADO_PROBLEMA_ENVIO_CORREO);
									haciendaBD.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
									haciendaBo.modificar(haciendaBD);
									soporteProblemaEnvioCorreos(haciendaBD.getEmpresa(), haciendaBD.getConsecutivo(), e);

								}

								log.info("** Error1  taskHaciendaEnvioDeCorreos: " + e.getMessage() + " fecha " + new Date());
							}
						}
						log.info("Fin Envios de correos - {}", formatter.format(LocalDateTime.now()));
						

					}
				}

			} else {
				log.info("Semaforo envio de correos INACTIVO  {}", new Date());
			}
		} catch (Exception e) {
			log.error("** Error2  taskHaciendaEnvioDeCorreos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	private void soporteProblemaEnvioCorreos(Empresa empresa, String consecutivo, Exception error) throws Exception {
		try {
			String subject = "EmpredesoftSoporte  Empresa :" + empresa.getNombre() + " Problemas de conexion del correo";
			String texto = "Empresa :" + empresa.getNombre() + " tiene  Problemas de conexion" + " Consecutivo de Factura : " + consecutivo + error.getMessage();
			correosBo.sendSimpleMessage("josehernandezchaverri@gmail.com", subject, texto);
			correosBo.sendSimpleMessage("vivianamartinezgranados@gmail.com", subject, texto);

		} catch (Exception e) {
			log.error("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Envios de correos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#enviarCorreos(com.emprendesoftcr.modelo.Factura, com.emprendesoftcr.modelo.Hacienda, java.util.ArrayList)
	 */
	@Override
	public void enviarCorreos(Factura factura, Hacienda hacienda, ArrayList<String> listaCorreos) throws Exception {
		try {
			String xmlFactura = FacturaElectronicaUtils.convertirBlodToString(hacienda.getComprobanteXML());
			String xmlRespuesta = FacturaElectronicaUtils.convertirBlodToString(hacienda.getMensajeHacienda());
			FacturaElectronica facturaElectronica = DOCUMENTO_TO_FACTURAELECTRONICA.apply(factura);
			Collection<Detalle> detalles = detalleBo.findByFactura(factura);
			List<DetalleFacturaElectronica> detallesFactura = detalles.stream().sorted(Comparator.comparingInt(Detalle::getNumeroLinea)).map(TO_DETALLE).collect(toList());
			facturaElectronica.setDetalleFacturaElectronica(detallesFactura);

			// ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			ByteArrayOutputStream namePDF = ReportePdfView.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			String clave = getConsecutivo(factura.getTipoDoc(), factura.getNumeroConsecutivo());
			Collection<Attachment> attachments = createAttachments(XML_Attach(clave, factura.getEmpresa().getCedula(), asText(xmlFactura), factura.getTipoDoc()), PDF_Attach(clave, factura.getEmpresa().getCedula(), asPDF(namePDF), factura.getTipoDoc()), XML_AttachRespuestaHacienda(clave, factura.getEmpresa().getCedula(), asText(xmlRespuesta)));
			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("clave", clave);
			modelEmail.put("nombreEmpresa", factura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? factura.getEmpresa().getNombre() : factura.getEmpresa().getNombreComercial());
			modelEmail.put("correo", factura.getEmpresa().getCorreoElectronico());
			modelEmail.put("telefono", factura.getEmpresa().getTelefono());
			String from = "FISCO_No_Reply@emprendesoftcr.com";
			if (factura.getEmpresa().getAbreviaturaEmpresa() != null) {
				if (!factura.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
					from = factura.getEmpresa().getAbreviaturaEmpresa() + "_Doc_Electronico" + "_No_Reply@emprendesoftcr.com";
				}
			}

			String nombre = factura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY) ? factura.getEmpresa().getNombre() : factura.getEmpresa().getNombreComercial();
			String subject = "Documento Electrónico N° " + clave + " del Emisor: " + nombre;
			String plantillaEmail = "email/emailHacienda.vm";
			if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				subject = "Documento Nota Credito Electrónico N°: " + clave + " del Emisor: " + nombre;
				modelEmail.put("facturaReferencia", factura.getReferenciaNumero());
				modelEmail.put("tituloDocumento", "Nota de credito Electronico:");
				plantillaEmail = "email/emailHaciendaNotaCredito.vm";
			}
			log.info("Documento enviado al correo: " + hacienda.getNumeroFactura() + " Empresa:" + hacienda.getEmpresa().getNombre());
			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, plantillaEmail, modelEmail);
		} catch (Exception e) {
			log.error("** Error  enviarCorreos: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre() + " Consecutivo" + hacienda.getConsecutivo());
			throw e;
		}
	}

	/**
	 * Envios de correos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#enviarCorreos(com.emprendesoftcr.modelo.Factura, com.emprendesoftcr.modelo.Hacienda, java.util.ArrayList)
	 */
	@Override
	public void enviarCorreosRecepcion(RecepcionFactura recepcionFactura, Hacienda hacienda, ArrayList<String> listaCorreos) throws Exception {
		try {
			String xmlFactura = FacturaElectronicaUtils.convertirBlodToString(hacienda.getComprobanteXML());
			String xmlRespuesta = FacturaElectronicaUtils.convertirBlodToString(hacienda.getMensajeHacienda());
			String tipoDoc = "compra";
			if (hacienda != null) {
				if (!hacienda.getTipoDoc().equals(Constantes.EMPTY)) {
					tipoDoc = hacienda.getTipoDoc();
				}
			}
			String consecutivoGenerado = getConsecutivo(tipoDoc, recepcionFactura.getNumeroConsecutivoReceptor());

			Collection<Attachment> attachments = createAttachments(XML_Attach(consecutivoGenerado, recepcionFactura.getEmpresa().getCedula(), asText(xmlFactura), tipoDoc), XML_AttachRespuestaHacienda(consecutivoGenerado, recepcionFactura.getEmpresa().getCedula(), asText(xmlRespuesta)));

			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("clave", recepcionFactura.getFacturaClave());
			modelEmail.put("cedulaEmisor", recepcionFactura.getEmisorCedula());
			String tipoMensajeTitulo = "";
			if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO) || recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_1)) {
				modelEmail.put("tipoMensaje", "aceptado");
				tipoMensajeTitulo = "Aceptacion";
			} else if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL) || recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_ACEPTADO_PARCIAL_1)) {
				modelEmail.put("tipoMensaje", "aceptación parcial");
				tipoMensajeTitulo = "Aceptacion_Parcial";
			} else if (recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_RECHAZADO) || recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_TIPO_DOC_RECHAZADO_1)) {
				modelEmail.put("tipoMensaje", "rechazo");
				tipoMensajeTitulo = "Rechazo";
			}

			modelEmail.put("tipoMensajeTitulo", tipoMensajeTitulo);
			String nombreProveedor = Constantes.EMPTY;
			String nombreEmpresa = Constantes.EMPTY;
			nombreProveedor = recepcionFactura.getEmisorNombre() != null ? recepcionFactura.getEmisorNombre() : Constantes.EMPTY;
			if (recepcionFactura.getEmpresa().getNombreComercial() != null) {
				if (!recepcionFactura.getEmpresa().getNombreComercial().equals(Constantes.EMPTY)) {
					nombreEmpresa = recepcionFactura.getEmpresa().getNombreComercial();
				}
			}
			nombreEmpresa = nombreEmpresa.equals(Constantes.EMPTY) ? recepcionFactura.getEmpresa().getNombre() : nombreEmpresa;
			modelEmail.put("nombreProveedor", nombreProveedor);
			modelEmail.put("empresa", nombreEmpresa);
			modelEmail.put("telefono", recepcionFactura.getEmpresa().getTelefono());
			modelEmail.put("correo", recepcionFactura.getEmpresa().getCorreoElectronico());
			modelEmail.put("consecutivoCompra", recepcionFactura.getFacturaConsecutivo() != null ? recepcionFactura.getFacturaConsecutivo() : Constantes.EMPTY);
			modelEmail.put("consecutivoGenerado", consecutivoGenerado);
			String from = tipoMensajeTitulo + "_Reply@emprendesoftcr.com";

			if (hacienda.getEmpresa().getAbreviaturaEmpresa() != null) {
				if (!hacienda.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
					from = hacienda.getEmpresa().getAbreviaturaEmpresa() + "_" + tipoMensajeTitulo + "_No_Reply@emprendesoftcr.com";
				}
			}

			String subject = nombreEmpresa + " Aceptando o Rechazando Compras documento Electrónico N° " + recepcionFactura.getNumeroConsecutivoReceptor() + " del emisor con cédula: " + recepcionFactura.getEmpresa().getCedula();
			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, "email/emailHaciendaRecepcionFactura.vm", modelEmail);
		} catch (Exception e) {
			log.error("** Error  enviarCorreosRecepcion: " + e.getMessage() + " fecha " + new Date() + " Empresa :" + hacienda.getEmpresa().getNombre());
			throw e;
		}
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		Collection<Attachment> resultado = null;
		try {
			resultado = Arrays.asList(attachments);
		} catch (Exception e) {
			log.info("Error al adjuntar attachments" + e);
			throw e;
		}
		return resultado;
	}

	private Attachment PDF_Attach(String name, String cedula, ByteArrayDataSource data, String tipoDoc) {
		String resultado = Constantes.EMPTY;
		resultado = "Factura_PDF_";
		if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
			resultado = "Factura_PDF_";
		} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			resultado = "NOTA_CREDITO_PDF_";
		} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
			resultado = "NOTA_DEBITO_PDF_";
		} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
			resultado = "PROFORMA_PDF_";
		} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
			resultado = "TIQUETE_PDF_";
		}
		return attachment(resultado + cedula + "_" + name, ".pdf", data);
	}

	private String getConsecutivo(String tipoDoc, String consecutivo) {
		return tipoDoc + "-" + consecutivo;
	}

	private ByteArrayDataSource asText(String text) throws IOException {
		return new ByteArrayDataSource(text, "text/plain");
	}

	private Attachment XML_Attach(String name, String cedula, ByteArrayDataSource data, String tipoDoc) {
		String resultado = Constantes.EMPTY;
		Attachment attachment = null;
		try {
			resultado = tipoDoc.equals("compra") ? tipoDoc + "_XML_" : "Factura_XML_";
			if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
				resultado = "Factura_XML_";
			} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				resultado = "NOTA_CREDITO_XML_";
			} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				resultado = "NOTA_DEBITO_XML_";
			} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				resultado = "PROFORMA_XML_";
			} else if (tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
				resultado = "TIQUETE_XML_";
			}
			attachment = attachment(resultado + cedula + "_" + name, ".xml", data);
		} catch (Exception e) {
			log.info("Error al adjuntar XML_Attach" + e.getCause());
			throw e;
		}

		return attachment;
	}

	private Attachment XML_AttachRespuestaHacienda(String name, String cedula, ByteArrayDataSource data) {
		Attachment attachment = null;
		try {
			attachment = attachment("Respuesta_XML_" + cedula + "_" + name, ".xml", data);
		} catch (Exception e) {
			log.error("Error al adjuntar XML_AttachRespuestaHacienda" + e.getCause());
			throw e;
		}

		return attachment;
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	private ByteArrayDataSource asPDF(ByteArrayOutputStream stream) {
		return new ByteArrayDataSource(stream.toByteArray(), "application/pdf");
	}

	/**
	 * Firmado de documentos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#procesoFirmado()
	 */
//	@Scheduled(cron = "0 0/10 * * * ?")
	@Override
	public synchronized void procesoFirmado() throws Exception {
		try {
			Semaforo semaforoFirmado = semaforoBo.findByEstadoAndID(Constantes.SEMAFORO_ESTADO_ACTIVO, Constantes.SEMAFORO_ESTADO_ENVIAR_CORREOS);
			if (semaforoFirmado != null) {
				Collection<Factura> listaHacienda = facturaBo.findByEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE, Constantes.FACTURA_ESTADO_REFIRMAR_DOCUMENTO);

				if (listaHacienda != null) {
					if (!listaHacienda.isEmpty()) {

						procesoFirmadoComprasSimplificadas();

						for (Factura factura : listaHacienda) {
							try {
								log.info("Inicio el proceso de firmado - {}", formatter.format(LocalDateTime.now()));
								
								log.info("Factura id	:  {}", factura.getId() + " Factura proceso de firmado:  " + factura.getNumeroConsecutivo().toString() + " Empresa:" + factura.getEmpresa().getNombre());

								if (factura != null) {
									Collection<Detalle> detalles = detalleBo.findByFactura(factura);
									if (detalles != null) {
										if (!detalles.isEmpty()) {
											if (factura.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
												String comprobanteXML = Constantes.EMPTY;
												if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
													// Crear XMl sin firma
													comprobanteXML = facturaXMLServices.getCrearXMLSinFirma(factura);
													// firmar el documento
													comprobanteXML = facturaXMLServices.getFirmarXML(comprobanteXML, factura.getEmpresa(), factura.getFechaEmision());
												} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
													// Crear XMl sin firma
													comprobanteXML = tiqueteXMLService.getCrearXMLSinFirma(factura);

													// firmar el documentoSELECT * FROM facturas WHERE facturas.estado_firma = 8
													comprobanteXML = tiqueteXMLService.getFirmarXML(comprobanteXML, factura.getEmpresa(), factura.getFechaEmision());
												} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
													if (factura.getVersionEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2)) {
														// Crear XMl sin firma
														comprobanteXML = notaCreditoXMLServices.getCrearXMLSinFirma(factura);
														// firmar el documento
														comprobanteXML = notaCreditoXMLServices.getFirmarXML(comprobanteXML, factura.getEmpresa(), factura.getFechaEmision());
													} else {
														comprobanteXML = notaCreditoXMLIVAServices.getCrearXMLSinFirma(factura);
														comprobanteXML = notaCreditoXMLIVAServices.getFirmarXML(comprobanteXML, factura.getEmpresa(), factura.getFechaEmision());
													}

												} else if (factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
													if (factura.getVersionEsquemaXML().equals(Constantes.ESQUEMA_XML_4_2)) {
														// Crear XMl sin firma
														comprobanteXML = notaDebitoXMLService.getCrearXMLSinFirma(factura);
														// firmar el documentofactura
														comprobanteXML = notaDebitoXMLService.getFirmarXML(comprobanteXML, factura.getEmpresa(), factura.getFechaEmision());

													} else {
														// Crear XMl sin firma
														comprobanteXML = notaDebitoXMLIVAService.getCrearXMLSinFirma(factura);
														// firmar el documentofactura
														comprobanteXML = notaDebitoXMLIVAService.getFirmarXML(comprobanteXML, factura.getEmpresa(), factura.getFechaEmision());

													}

												}
												if (comprobanteXML != null) {
													if (!comprobanteXML.equals(Constantes.EMPTY)) {
														Hacienda haciendaVerificar = haciendaBo.findByEmpresaAndClave(factura.getEmpresa(), factura.getClave());
														if (haciendaVerificar == null) {
															Hacienda hacienda = new Hacienda();
															hacienda.setCedulaEmisor(factura.getEmpresa().getCedula());
															hacienda.setTipoEmisor(factura.getEmpresa().getTipoCedula());
															// no se graba el cliente si es frecuente
															if (factura.getCliente() != null) {
																if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
																	hacienda.setCedulaReceptor(factura.getCliente().getCedula());
																	hacienda.setTipoReceptor(factura.getCliente().getTipoCedula());
																}
															}
															hacienda.setNumeroFactura(factura != null ? factura.getId() : null);
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
															hacienda.setNotificacion(siEnviarCorreo(factura) ? Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE : Constantes.HACIENDA_NOTIFICAR_CLIENTE_ENVIADO);
															haciendaBo.agregar(hacienda);

														}

														if (factura != null) {
															Factura facturaBD = facturaBo.findById(factura.getId());
															if (facturaBD != null) {
																facturaBD.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
																facturaBo.modificar(facturaBD);

															}
														}
													}

												}
											}

										}
									}
								}

							} catch (Exception e) {
								factura.setEstadoFirma(Constantes.FACTURA_ESTADO_PROBLEMA_AL_FIRMAR);
								facturaBo.modificar(factura);
								log.info("** Error1 proceso de firmado: " + e.getMessage() + " fecha " + new Date());
							}
						}
						log.info("Fin  proceso de firmado - {}", formatter.format(LocalDateTime.now()));
						
					}
				}

			} else {
				log.info("Semaforo procesos firmado INACTIVO  {}", new Date());
			}

		} catch (Exception e) {
			log.error("** Error2  proceso de firmado: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Verifica si debe enviar correo al cliente en las facturas
	 * @param factura
	 * @return
	 */
	private Boolean siEnviarCorreo(Factura factura) {
		Boolean siEnviar = Boolean.FALSE;
		if (factura.getCorreoAlternativo() != null) {
			if (!factura.getCorreoAlternativo().equals(Constantes.EMPTY)) {
				siEnviar = Boolean.TRUE;
			}
		}
		if (!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
			if (factura.getCliente().getCorreoElectronico() != null) {
				if (!factura.getCliente().getCorreoElectronico().equals(Constantes.EMPTY)) {
					siEnviar = Boolean.TRUE;
				}
			}
			if (factura.getCliente().getCorreoElectronico1() != null) {
				if (!factura.getCliente().getCorreoElectronico1().equals(Constantes.EMPTY)) {
					siEnviar = Boolean.TRUE;
				}
			}
			if (factura.getCliente().getCorreoElectronico2() != null) {
				if (!factura.getCliente().getCorreoElectronico2().equals(Constantes.EMPTY)) {
					siEnviar = Boolean.TRUE;
				}

			}
			if (factura.getCliente().getCorreoElectronico3() != null) {
				if (!factura.getCliente().getCorreoElectronico3().equals(Constantes.EMPTY)) {
					siEnviar = Boolean.TRUE;
				}
			}
		}
		return siEnviar;
	}

	private void procesoFirmadoComprasSimplificadas() throws Exception {
		try {

			String xmlString = Constantes.EMPTY;
			Collection<CompraSimplificada> lista = compraSimplificadaBo.findByEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE, Constantes.FACTURA_ESTADO_REFIRMAR_DOCUMENTO);
			if (lista != null) {
				for (CompraSimplificada compraSimplificada : lista) {
					try {
						log.info("Inicio Proceso de firmado compra Simplificado - {}", formatter.format(LocalDateTime.now()));
						
						log.info("Compra simplificada id	:  {}", compraSimplificada.getId() + " Compra Simplificada proceso de firmado:  " + compraSimplificada.getNumeroConsecutivo().toString() + " Empresa:" + compraSimplificada.getEmpresa().getNombre());
						xmlString = Constantes.EMPTY;
						xmlString = compraSimplificadaXMLServices.getCrearXMLSinFirma(compraSimplificada);
						xmlString = compraSimplificadaXMLServices.getFirmarXML(xmlString, compraSimplificada.getEmpresa(), compraSimplificada.getFechaEmision());
						if (xmlString != null) {
							if (!xmlString.equals(Constantes.EMPTY)) {
								Hacienda haciendaVerificar = haciendaBo.findByEmpresaAndClave(compraSimplificada.getEmpresa(), compraSimplificada.getClave());
								if (haciendaVerificar == null) {
									Hacienda hacienda = new Hacienda();
									hacienda.setCedulaEmisor(compraSimplificada.getEmpresa().getCedula());
									hacienda.setTipoEmisor(compraSimplificada.getEmpresa().getTipoCedula());
									// no se graba el cliente si es frecuente
									if (compraSimplificada.getProveedorSimplificado() != null) {
										if (!compraSimplificada.getProveedorSimplificado().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
											hacienda.setCedulaReceptor(compraSimplificada.getProveedorSimplificado().getCedula());
											hacienda.setTipoReceptor(compraSimplificada.getProveedorSimplificado().getTipoCedula());
										}
									}
									hacienda.setEmpresa(compraSimplificada.getEmpresa());
									hacienda.setClave(compraSimplificada.getClave());
									hacienda.setFechaEmisor(compraSimplificada.getFechaEmision());
									Blob b = FacturaElectronicaUtils.convertirStringToblod(xmlString);
									hacienda.setComprobanteXML(b);
									hacienda.setCreated_at(new Date());
									hacienda.setUpdated_at(new Date());
									hacienda.setStatus(Constantes.ZEROS);
									hacienda.setEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML);
									hacienda.setConsecutivo(compraSimplificada.getNumeroConsecutivo());
									hacienda.setReintentos(Constantes.ZEROS);
									hacienda.setReintentosAceptacion(Constantes.ZEROS);
									hacienda.setTipoDoc(compraSimplificada.getTipoDoc());
									hacienda.setNombreReceptor(compraSimplificada.getProveedorSimplificado().getNombreCompleto());
									hacienda.setCorreoReceptor(compraSimplificada.getProveedorSimplificado().getCorreoElectronico());
									hacienda.setTotalReceptor(compraSimplificada.getTotalComprobante());
									hacienda.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
									haciendaBo.agregar(hacienda);
									if (compraSimplificada != null) {
										CompraSimplificada compraSimplificadaBD = compraSimplificadaBo.findById(compraSimplificada.getId());
										if (compraSimplificadaBD != null) {
											compraSimplificadaBD.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
											compraSimplificadaBo.modificar(compraSimplificadaBD);

										}
									}

								}
							}
						}
						log.info("Finaliza proceso de firmado compra Simplificado - {}", formatter.format(LocalDateTime.now()));
						
					} catch (Exception e) {
						compraSimplificada.setEstadoFirma(Constantes.FACTURA_ESTADO_PROBLEMA_AL_FIRMAR);
						compraSimplificadaBo.modificar(compraSimplificada);
						log.error("** Error1 proceso de firmado compra simplificada: " + e.getMessage() + " fecha " + new Date());
					}

				}

			}

		} catch (Exception e) {
			log.error("** Error2  proceso de firmado compra Simplificada: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Firmado de documentos
	 * @see com.emprendesoftcr.service.ProcesoHaciendaService#procesoFirmado()
	 */
//	@Scheduled(cron = "0 0/30 * * * ?")
	@Override
	public synchronized void procesoFirmadoRecepcionFactura() throws Exception {
		try {
			Semaforo semaforoFirmadoCompra = semaforoBo.findByEstadoAndID(Constantes.SEMAFORO_ESTADO_ACTIVO, Constantes.SEMAFORO_ESTADO_FIRMADO_COMPRAS);
			if (semaforoFirmadoCompra != null) {
				Collection<RecepcionFactura> lista = recepcionFacturaBo.findByEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE, Constantes.FACTURA_ESTADO_REFIRMAR_DOCUMENTO);
				if (!lista.isEmpty()) {
					for (RecepcionFactura recepcionFactura : lista) {
						try {
							log.info("Inicio el proceso de firmado de compras aceptadas - {}", formatter.format(LocalDateTime.now()));
							
							String comprobanteXMLSinFirma = Constantes.EMPTY;
							String comprobanteXMLConFirma = Constantes.EMPTY;

							// Crear XMl sin firma
							comprobanteXMLSinFirma = recepcionFacturaXMLServices.getCrearXMLSinFirma(recepcionFactura);

							if (comprobanteXMLSinFirma != null) {
								if (!comprobanteXMLSinFirma.equals(Constantes.EMPTY)) {
									comprobanteXMLConFirma = recepcionFacturaXMLServices.getFirmarXML(comprobanteXMLSinFirma, recepcionFactura.getEmpresa(), recepcionFactura.getFacturaFechaEmision());
								}
							}

							if (!comprobanteXMLConFirma.equals(Constantes.EMPTY)) {
								// Se cargan los datos de la factura, el emisor es el que envia la factura para su aprobacion
								Hacienda hacienda = new Hacienda();
								hacienda.setCedulaEmisor(recepcionFactura.getEmisorCedula());
								hacienda.setTipoEmisor(recepcionFactura.getEmisorTipoCedula());
								hacienda.setClave(recepcionFactura.getFacturaClave());
								hacienda.setFechaEmisor(recepcionFactura.getFacturaFechaEmision());

								// Se cargan los datos del emisor, empresa que recibe la factura
								hacienda.setComprobanteXML(FacturaElectronicaUtils.convertirStringToblod(comprobanteXMLConFirma));
								hacienda.setCreated_at(new Date());
								hacienda.setUpdated_at(new Date());
								hacienda.setStatus(Constantes.ZEROS);
								hacienda.setEstado(Constantes.HACIENDA_ESTADO_FIRMARDO_XML);
								hacienda.setConsecutivo(recepcionFactura.getNumeroConsecutivoReceptor());
								hacienda.setReintentos(Constantes.ZEROS);
								hacienda.setReintentosAceptacion(Constantes.ZEROS);

								String tipoDoc = Constantes.FACTURA_TIPO_DOC_COMPRAS;
								hacienda.setTipoDoc(tipoDoc);
								hacienda.setEmpresa(recepcionFactura.getEmpresa());
								hacienda.setNombreReceptor(recepcionFactura.getEmpresa().getNombreComercial());
								hacienda.setCorreoReceptor(recepcionFactura.getEmpresa().getCorreoElectronico());
								hacienda.setTotalReceptor(recepcionFactura.getFacturaTotalComprobante());
								hacienda.setNotificacion(Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE);
								haciendaBo.agregar(hacienda);

								recepcionFactura = recepcionFactura.getId() == null || recepcionFactura.getId() == Constantes.ZEROS_LONG ? null : recepcionFacturaBo.findById(recepcionFactura.getId());
								recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_COMPLETO);
								if (recepcionFactura != null) {
									recepcionFacturaBo.modificar(recepcionFactura);
								}

							}
							log.info(" Fin el proceso de firmado compras- {}", formatter.format(LocalDateTime.now()));
							
						} catch (Exception e) {
							recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_PROBLEMA_AL_FIRMAR);
							recepcionFacturaBo.modificar(recepcionFactura);
							log.error("** Error1 proceso de firmado: " + e.getMessage() + " fecha " + new Date());
						}

					}
				}

			} else {
				log.info("Semaforo firmado de compras INACTIVO  {}", new Date());
			}

		} catch (Exception e) {
			log.error("** Error2  proceso de firmado: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

}

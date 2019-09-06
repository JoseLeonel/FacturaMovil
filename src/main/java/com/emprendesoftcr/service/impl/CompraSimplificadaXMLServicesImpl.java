package com.emprendesoftcr.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Bo.DetalleCompraSimplificadaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.service.CompraSimplificadaXMLServices;
import com.emprendesoftcr.service.FirmaElectronicaService;

/**
 * Proceso de armado del XML Firmado del XML FacturaXMLServicesImpl.
 * @author jose.
 * @since 13 jul. 2018
 */
@Service("compraSimplificadaXMLServices")
@Transactional
@EnableTransactionManagement
public class CompraSimplificadaXMLServicesImpl implements CompraSimplificadaXMLServices {

	private Logger									log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CertificadoBo						certificadoBo;

	@Autowired
	private FirmaElectronicaService	firmaElectronicaService;



	@Autowired
	private DetalleCompraSimplificadaBo								detalleCompraSimplificadaBo;

	/**
	 * @see com.emprendesoftcr.service.FacturaXMLServices#getCrearXML(com.emprendesoftcr.modelo.Factura)
	 */
	@Transactional
	@Override
	public String getCrearXMLSinFirma(CompraSimplificada compraSimplificada) throws Exception {
		String xml = Constantes.EMPTY;
		try {
//			Date fecha = new Date();
//			long tiempoInicial = compraSimplificada.getCreated_at().getTime();
//			long tiempoFinal = fecha.getTime();
//			long resta = tiempoFinal - tiempoInicial;
			// el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
//			if (resta > 0) {
//				resta = resta / (1000 * 60);
//			}
//
//			if (resta > 80) {
//				compraSimplificada.setFechaEmision(fecha);
//				compraSimplificadaBo.modificar(compraSimplificada);
//			} else {
//				fecha = compraSimplificada.getCreated_at();
//			}
			String observacion = Constantes.EMPTY;
			String datereferenciaEmision = FacturaElectronicaUtils.rfc3339(compraSimplificada.getFechaEmision());

			String date = FacturaElectronicaUtils.rfc3339(compraSimplificada.getFechaEmision());
			xml = "<FacturaElectronicaCompra xmlns=\"" + Constantes.DOCXMLS_COMPRA_SIMPLIFICADA_4_3 + "\" " + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" 
			     + "<Clave>" + compraSimplificada.getClave() + "</Clave>" 
					+ "<CodigoActividad>" + actividadComercial(compraSimplificada) + "</CodigoActividad>" 
			     + "<NumeroConsecutivo>" + compraSimplificada.getNumeroConsecutivo() + "</NumeroConsecutivo>" 
					+ "<FechaEmision>" + date + "</FechaEmision>" 
			     + "<Emisor>" 
					       + "<Nombre>" + FacturaElectronicaUtils.procesarTexto(compraSimplificada.getProveedorSimplificado().getNombreCompleto()) + "</Nombre>" 
			           + "<Identificacion>" 
					              + "<Tipo>" + Utils.zeroPad(compraSimplificada.getProveedorSimplificado().getTipoCedula(), 2) + "</Tipo>" 
			                  + "<Numero>" + compraSimplificada.getProveedorSimplificado().getCedula() + "</Numero>" 
					       + "</Identificacion>"
					       + "<Ubicacion>" 
	 			         + "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(compraSimplificada.getEmpresa().getProvincia(), Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
	 					      "<Canton>" + FacturaElectronicaUtils.replazarConZeros(compraSimplificada.getEmpresa().getCanton(), Constantes.FORMATO_CANTON) + "</Canton>" 
	 			         + "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(compraSimplificada.getEmpresa().getDistrito(), Constantes.FORMATO_DISTRITO) + "</Distrito>" 
	 					     +  "<OtrasSenas>" + FacturaElectronicaUtils.procesarTexto(compraSimplificada.getEmpresa().getOtraSenas()) + "</OtrasSenas>" 
	 			         + "</Ubicacion>" +
	 			        "<CorreoElectronico>" + FacturaElectronicaUtils.procesarTexto(compraSimplificada.getEmpresa().getCorreoElectronico()) + "</CorreoElectronico>"+
					 "</Emisor>" + xmlReceptor(compraSimplificada) 
					+ "<CondicionVenta>" + compraSimplificada.getCondicionVenta() + "</CondicionVenta>" 
					+ "<PlazoCredito>" + FacturaElectronicaUtils.replazarConZeros(compraSimplificada.getPlazoCredito() != null ? compraSimplificada.getPlazoCredito().toString() : Constantes.ZEROS.toString(), Constantes.FORMATO_PLAZO_CREDITO) + "</PlazoCredito>" 
					+ getMedioPago(compraSimplificada) 
					+ "<DetalleServicio>" + xmlDetalleServicio(compraSimplificada) + "</DetalleServicio>" 
//					+ getOtrosCargos(compraSimplificada) 
					+ "<ResumenFactura>" + getCodigoMoneda(compraSimplificada.getCodigoMoneda(), compraSimplificada.getTipoCambio())
					      + "<TotalServGravados>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalServGravados(), 5)+ "</TotalServGravados>" 
					      + "<TotalServExentos>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalServExentos(), 5) + "</TotalServExentos>"
								+ "<TotalMercanciasGravadas>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalMercanciasGravadas(), 5) + "</TotalMercanciasGravadas>" 
								+ "<TotalMercanciasExentas>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalMercanciasExentas(), 5) + "</TotalMercanciasExentas>" 
//								+ "<TotalMercExonerada>" + FacturaElectronicaUtils.truncateDecimal(Constantes.ZEROS_DOUBLE, 5) + "</TotalMercExonerada>" 
								+ "<TotalGravado>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalGravado(), 5)+ "</TotalGravado>" 
								+ "<TotalExento>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalExento(), 5) + "</TotalExento>" 
//								+ "<TotalExonerado>" + FacturaElectronicaUtils.truncateDecimal(Constantes.ZEROS_DOUBLE, 5) + "</TotalExonerado>" 
								+ "<TotalVenta>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalVenta(), 5) + "</TotalVenta>" 
								+ "<TotalDescuentos>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalDescuentos(), 5) + "</TotalDescuentos>" 
								+ "<TotalVentaNeta>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalVentaNeta(), 5) + "</TotalVentaNeta>" 
								+ "<TotalImpuesto>" + FacturaElectronicaUtils.truncateDecimal(impuestoTotal(compraSimplificada.getTotalImpuesto()), 5) + "</TotalImpuesto>" 
//								+ "<TotalOtrosCargos>" + FacturaElectronicaUtils.truncateDecimal(Constantes.ZEROS_DOUBLE, 5) + "</TotalOtrosCargos>" 
								+ "<TotalComprobante>" + FacturaElectronicaUtils.truncateDecimal(compraSimplificada.getTotalComprobante(), 5) + "</TotalComprobante>" 
						+ "</ResumenFactura>" +
				     "<InformacionReferencia>" +
	             "<TipoDoc>" + Utils.zeroPad(FacturaElectronicaUtils.procesarTexto(compraSimplificada.getReferenciaTipoDoc()),2) + "</TipoDoc>" +
	             "<Numero>" + compraSimplificada.getReferenciaNumero() + "</Numero>" +
	             "<FechaEmision>" + datereferenciaEmision + "</FechaEmision>" +
	             "<Codigo>" + compraSimplificada.getReferenciaCodigo() + "</Codigo>" +
	             "<Razon>" + FacturaElectronicaUtils.procesarTexto(compraSimplificada.getReferenciaRazon()) + "</Razon>" +
	             "</InformacionReferencia>" +
								
					   "<Otros>" 
						     + "<OtroTexto codigo=\"obs\">" + FacturaElectronicaUtils.procesarTexto(observacion) + "</OtroTexto>" 
					       + Utils.oTroContenido() + "</Otros>" 
					+ "</FacturaElectronicaCompra>";

		} catch (Exception e) {
			log.error("** Error  getCrearXMLSinFirma: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return xml;
	}

	
	
	private Double impuestoTotal(Double impuestoTotal) {
		Double resultado = Constantes.ZEROS_DOUBLE;
			resultado = impuestoTotal;

		return resultado;
	}

	private String actividadComercial(CompraSimplificada compraSimplificada) {
		String resultado = Constantes.EMPTY;
		if (compraSimplificada.getCodigoActividad() != null) {
			resultado = compraSimplificada.getCodigoActividad();
		}
		if (resultado.equals(Constantes.EMPTY)) {
			resultado = compraSimplificada.getEmpresa().getCodigoActividad();
		}

		return FacturaElectronicaUtils.replazarConZeros(resultado, Constantes.FORMATO_CODIGO_ACTIVIDAD);
	}
	private String getTelefono(Integer telefono,Integer codigoPais) throws Exception{
		String resultado = Constantes.EMPTY;
		try {
	    if(telefono > Constantes.ZEROS) {
	  		 resultado = "<Telefono>" +
	          "<CodigoPais>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(codigoPais.toString()).toString(),Constantes.FORMATO_CODIGO_PAIS) + "</CodigoPais>" +
	 	        "<NumTelefono>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(telefono.toString()).toString(),Constantes.FORMATO_TELEFONO) + "</NumTelefono>";
	 	     resultado += "</Telefono>";
	     }
			
		} catch (Exception e) {
			log.error("** Error  getTelefono: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		
		return resultado;
	}
	

	private String getCodigoMoneda(String codigoMoneda, Double tipoCambio) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			if (tipoCambio > Constantes.ZEROS_DOUBLE) {
				resultado = "<CodigoTipoMoneda>" 
			                      + "<CodigoMoneda>" + codigoMoneda + "</CodigoMoneda>" 
						                + "<TipoCambio>" + FacturaElectronicaUtils.getConvertirBigDecimal(tipoCambio) + "</TipoCambio>";
				resultado += "</CodigoTipoMoneda>";
			}

		} catch (Exception e) {
			log.error("** Error  getCodigoMoneda: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}

	/**
	 * Firma de la Factura electronica
	 * @see com.emprendesoftcr.service.FacturaXMLServices#getFirmarXML(java.lang.String, com.emprendesoftcr.modelo.Factura)
	 */
	@Override
	public String getFirmarXML(String xmlString, Empresa empresa, Date fecha) throws Exception {
		String resultado = Constantes.EMPTY;
		try {

			Certificado certificado = certificadoBo.findByEmpresa(empresa);
			if (certificado != null) {
				resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_FACTURA_4_3, fecha);
			} else {
				log.info("** Error  Empresa no se encuentra el certificado: " + empresa.getNombre());
			}

		} catch (Exception e) {
			log.error("** Error  getFirmarXML: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}

	/**
	 * @param factura
	 * @return
	 */
	private String getMedioPago(CompraSimplificada compraSimplificada) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			String valor = Constantes.MEDIO_PAGO_EFECTIVO;
			if (compraSimplificada.getTotalTarjeta() > Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalEfectivo() == Constantes.ZEROS_DOUBLE) {
				valor = Constantes.MEDIO_PAGO_TARJETA;
			}
			if (compraSimplificada.getTotalBanco() > Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalEfectivo() == Constantes.ZEROS_DOUBLE && compraSimplificada.getTotalTarjeta() == Constantes.ZEROS_DOUBLE) {
				valor = Constantes.MEDIO_PAGO_BANCO;
			}

			resultado = "<MedioPago>" + FacturaElectronicaUtils.replazarConZeros(valor, Constantes.FORMATO_MEDIOPAGO);

			resultado += "</MedioPago>";

		} catch (Exception e) {
			log.error("** Error  getMedioPago: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return resultado;
	}



	

	/**
	 * @param factura
	 * @return
	 */
	private String xmlDetalleServicio(CompraSimplificada compraSimplificada) throws Exception {
		String lineas = Constantes.EMPTY;
		String tipoCodigo = Constantes.EMPTY;
		try {
			Collection<DetalleCompraSimplificada> detalles = detalleCompraSimplificadaBo.findByCompraSimplificada(compraSimplificada);
			for (DetalleCompraSimplificada detalle : detalles) {
				tipoCodigo = Constantes.EMPTY;
				if (detalle.getTipoCodigo() != null) {
					if (detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_USO_INTERNO)) {
						tipoCodigo = detalle.getTipoCodigo();
					}
					if (detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
						tipoCodigo = detalle.getTipoCodigo();
					}
					if (detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_CODIGO_VENDEDOR)) {
						tipoCodigo = detalle.getTipoCodigo();
					}
					if (detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_CODIGO_COMPRADOR)) {
						tipoCodigo = detalle.getTipoCodigo();
					}
					if (detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_CODIGO_ASIGNADO_POR_INDUSTRIAS)) {
						tipoCodigo = detalle.getTipoCodigo();
					}
					if (detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_CODIGO_OTROS)) {
						tipoCodigo = detalle.getTipoCodigo();
					}

				}
				if (tipoCodigo.equals(Constantes.EMPTY)) {
					tipoCodigo = Constantes.TIPO_CODIGO_ARTICULO_CODIGO_VENDEDOR;
				}
				String unidadMedida = Constantes.UNIDAD_MEDIDA;
				if (detalle.getUnidadMedida() != null) {
					if (!detalle.getUnidadMedida().equals(Constantes.EMPTY)) {
						unidadMedida = detalle.getUnidadMedida();
					}
				}
				lineas += "<LineaDetalle>" 
				           + "<NumeroLinea>" + new BigInteger(detalle.getNumeroLinea().toString()) + "</NumeroLinea>" 
						       + "<CodigoComercial>" + "<Tipo>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(tipoCodigo, 2)) + "</Tipo>" 
				           + "<Codigo>" + FacturaElectronicaUtils.procesarTexto(detalle.getCodigo()) + "</Codigo>" 
						       + "</CodigoComercial>" 
				           				+ "<Cantidad>" + FacturaElectronicaUtils.getConvertirBigDecimalFortmato3Decimales(detalle.getCantidad()) + "</Cantidad>" 
						              + "<UnidadMedida>" + unidadMedida + "</UnidadMedida>" +
						       "<Detalle>" + FacturaElectronicaUtils.procesarTexto(detalle.getDescripcion().trim()) + "</Detalle>" 
				           + "<PrecioUnitario>" + FacturaElectronicaUtils.truncateDecimal(detalle.getPrecioUnitario(), 5) + "</PrecioUnitario>" 
						       + "<MontoTotal>" + FacturaElectronicaUtils.truncateDecimal(detalle.getMontoTotal(), 5) + "</MontoTotal>" 
				           + getDescuento(detalle.getMontoDescuento()) 
				           + "<SubTotal>" + FacturaElectronicaUtils.truncateDecimal(detalle.getSubTotal(), 5) + "</SubTotal>" 
						       + xmlImpuestos(detalle.getCompraSimplificada().getId(), detalle.getCodigoTarifa(), detalle.getTipoImpuesto(), detalle.getMontoImpuesto(), detalle.getImpuesto(), detalle) + xmlImpuestosNeto(detalle.getCompraSimplificada().getId(), detalle.getMontoImpuesto() == null ? Constantes.ZEROS_DOUBLE : detalle.getMontoImpuesto(),  detalle.getImpuestoNeto()) + "<MontoTotalLinea>" + FacturaElectronicaUtils.truncateDecimal(detalle.getMontoTotalLinea(), 5) + "</MontoTotalLinea>" + "</LineaDetalle>";
			}
		} catch (Exception e) {
			log.error("** Error  xmlDetalleServicio: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

		return lineas;
	}


	private String xmlImpuestosNeto(Long idFactura, Double montoImpuesto,  Double montoImpuestoNeto) throws Exception {
		String resultado = Constantes.EMPTY;
		if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE) ) {
			return resultado;
		}

		try {

			if (montoImpuestoNeto > 0) {
				montoImpuestoNeto = Utils.roundFactura(montoImpuestoNeto, 5);
				resultado = "<ImpuestoNeto>" + FacturaElectronicaUtils.truncateDecimal(montoImpuestoNeto, 5) + "</ImpuestoNeto>";

			} else {
				resultado = "<ImpuestoNeto>0</ImpuestoNeto>";
			}

		} catch (Exception e) {
			log.error("** Error  xmlImpuestos Factura :" + idFactura + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}

	private String xmlImpuestos(Long idFactura, String codigoTarifa, String tipoImpuesto, Double montoImpuesto, Double impuesto, DetalleCompraSimplificada detalle) throws Exception {
		String resultado = Constantes.EMPTY;
		String nodoCodigoTarifa = Constantes.EMPTY;
		String nodoTarifa = Constantes.EMPTY;
		try {
			if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
				return resultado;
			}
			if (codigoTarifa != null) {
				if (!codigoTarifa.equals(Constantes.EMPTY)) {
					nodoCodigoTarifa = "<CodigoTarifa>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(codigoTarifa, 2)) + "</CodigoTarifa>";

				}

			}

			nodoTarifa = "<Tarifa>" + FacturaElectronicaUtils.truncateDecimal(impuesto, 2) + "</Tarifa>";
			if (montoImpuesto != null && tipoImpuesto != null) {
				if (montoImpuesto > Constantes.ZEROS_DOUBLE) {
					resultado = "<Impuesto>" + "<Codigo>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(tipoImpuesto, 2)) + "</Codigo>" + nodoCodigoTarifa + nodoTarifa + "<Monto>" + FacturaElectronicaUtils.truncateDecimal(montoImpuesto, 5) + "</Monto>" ;
					resultado += "</Impuesto>";
				}
			}

		} catch (Exception e) {
			log.error("** Error  xmlImpuestos Factura :" + idFactura + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}

	
	/**
	 * @param descuento
	 * @return
	 */
	private String getDescuento(Double descuento) throws Exception {
		String resultado = Constantes.EMPTY;
		if (descuento == null) {
			return resultado;
		}
		if (descuento.equals(Constantes.ZEROS_DOUBLE)) {
			return resultado;
		}
		try {
			resultado = "<Descuento>" 
		               			+ "<MontoDescuento>" + FacturaElectronicaUtils.truncateDecimal(descuento, 5) + "</MontoDescuento>" 
		               			+ "<NaturalezaDescuento>" + Constantes.FORMATO_NATURALEZA_DESCUENTO + "</NaturalezaDescuento>" 
		               + "</Descuento>";

		} catch (Exception e) {
			log.error("** Error  getDescuento: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}

	/**
	 * @param factura
	 * @return
	 */
	private String xmlReceptor(CompraSimplificada compraSimplificada) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			if (compraSimplificada.getEmpresa() != null) {
				if (!compraSimplificada.getEmpresa().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
					
						resultado = "<Receptor>" 
					                       + "<Nombre>" + FacturaElectronicaUtils.procesarTexto(compraSimplificada.getEmpresa().getNombre()) + "</Nombre>" 
								                 + xmlIdentificacion(compraSimplificada)+ 
								                 "<NombreComercial>" +  FacturaElectronicaUtils.procesarTexto(compraSimplificada.getEmpresa().getNombreComercial() !=null?compraSimplificada.getEmpresa().getNombreComercial():Constantes.EMPTY) + "</NombreComercial>"
								               +  "<Ubicacion>" 
								 			         + "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(compraSimplificada.getEmpresa().getProvincia(), Constantes.FORMATO_PROVINCIA) + "</Provincia>" 
								 					     + "<Canton>" + FacturaElectronicaUtils.replazarConZeros(compraSimplificada.getEmpresa().getCanton(), Constantes.FORMATO_CANTON) + "</Canton>" 
								 			         + "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(compraSimplificada.getEmpresa().getDistrito(), Constantes.FORMATO_DISTRITO) + "</Distrito>" 
								 					     +  "<OtrasSenas>" + FacturaElectronicaUtils.procesarTexto(compraSimplificada.getEmpresa().getOtraSenas()) + "</OtrasSenas>" 
								 			         + "</Ubicacion>" + 
								 			        getTelefono(compraSimplificada.getEmpresa().getTelefono(),compraSimplificada.getEmpresa().getCodigoPais())+
								 			        "<CorreoElectronico>" + FacturaElectronicaUtils.procesarTexto(compraSimplificada.getEmpresa().getCorreoElectronico()) + "</CorreoElectronico>"
					               + "</Receptor>";

				}
			}
		} catch (Exception e) {
			log.error("** Error  xmlReceptor: " + e.getMessage() + " fecha " + new Date());
			throw e;

		}
		return resultado;
	}


	private String xmlIdentificacion(CompraSimplificada compraSimplificada) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			if (compraSimplificada.getProveedorSimplificado() != null) {
				resultado = "<Identificacion>" 
			                    + "<Tipo>" + Utils.zeroPad(compraSimplificada.getEmpresa().getTipoCedula(), 2) + "</Tipo>" 
						               + "<Numero>" + compraSimplificada.getEmpresa().getCedula() + "</Numero>" 
			               + "</Identificacion>";
			}
		} catch (Exception e) {
			log.error("** Error  xmlIdentificacion: " + e.getMessage() + " fecha " + new Date());
			throw e;

		}
		return resultado;
	}

	
}

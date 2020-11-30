package com.emprendesoftcr.service.impl;



import java.io.StringReader;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.service.FacturaXMLServices;
import com.emprendesoftcr.service.FirmaElectronicaService;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

/**
 * Proceso de armado del XML
 * Firmado del XML
 * FacturaXMLServicesImpl.
 * @author jose.
 * @since 13 jul. 2018
 */
@Service("facturaXMLServices")
@Transactional
@EnableTransactionManagement
public class FacturaXMLServicesImpl implements FacturaXMLServices {
	private Logger	log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CertificadoBo																					certificadoBo;
	
	@Autowired
	private FirmaElectronicaService firmaElectronicaService;
	
	

	@Autowired
	private DetalleBo							detalleBo;
	
	@Autowired
	private ArticuloBo							articuloBo;

	/**
	 * 
	 * @see com.emprendesoftcr.service.FacturaXMLServices#getCrearXML(com.emprendesoftcr.modelo.Factura)
	 */
	@Transactional
	@Override
	public String getCrearXMLSinFirma(Factura factura) throws Exception{
		String xml = Constantes.EMPTY;
		try {
			Date fecha = new Date();
			long tiempoInicial = factura.getCreated_at().getTime();
			long tiempoFinal = fecha.getTime();
			long resta = tiempoFinal - tiempoInicial;
			// el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
			if(resta > 0) {
				resta = resta / (1000 * 60);	
			}
			
//			if (resta > 80) {
//				factura.setFechaEmision(fecha);
//				facturaBo.modificar(factura);
//			}else {
//				fecha = factura.getCreated_at();
//			}
			String observacion = Constantes.EMPTY;
			if(factura.getCliente().getObservacionVenta() !=null) {
				if(!factura.getCliente().getObservacionVenta().equals(Constantes.EMPTY)) {
					observacion = factura.getCliente().getObservacionVenta();
				}else {
					observacion = factura.getNota();
				}
			}else {
				observacion = factura.getNota();
			}
			String detalllesFactura = xmlDetalleServicio(factura);
      if(detalllesFactura.equals(Constantes.EMPTY)&& detalllesFactura != null) {
     	 return Constantes.EMPTY;
      }
      
			String date = FacturaElectronicaUtils.rfc3339(factura.getFechaEmision());
			 xml = "<FacturaElectronica xmlns=\"" + Constantes.DOCXMLS_FACTURA_4_3 + "\" " +
	         "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
	 "<Clave>" + factura.getClave() + "</Clave>" +
	 "<CodigoActividad>" + actividadComercial(factura) + "</CodigoActividad>" +        
	 "<NumeroConsecutivo>" + factura.getNumeroConsecutivo() + "</NumeroConsecutivo>" +
	 "<FechaEmision>" + date + "</FechaEmision>" +
	 "<Emisor>" +
	     "<Nombre>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getNombre()) + "</Nombre>" +
	     "<Identificacion>" +
	         "<Tipo>" + Utils.zeroPad(factura.getEmpresa().getTipoCedula(),2) + "</Tipo>" +
	         "<Numero>" + factura.getEmpresa().getCedula() + "</Numero>" +
	     "</Identificacion>" +
	     "<Ubicacion>" +
	         "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
	         "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
	         "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
	         "<OtrasSenas>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getOtraSenas()) + "</OtrasSenas>" +
	     "</Ubicacion>" +
	     "<CorreoElectronico>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getCorreoElectronico()) + "</CorreoElectronico>" +
	 "</Emisor>" +
	 xmlReceptor(factura) +
	     "<CondicionVenta>" + factura.getCondicionVenta() + "</CondicionVenta>" +
	     "<PlazoCredito>" + FacturaElectronicaUtils.replazarConZeros(factura.getPlazoCredito() !=null?factura.getPlazoCredito().toString():Constantes.ZEROS.toString(),Constantes.FORMATO_PLAZO_CREDITO) + "</PlazoCredito>"  
	      + getMedioPago(factura) +
	     "<DetalleServicio>" + detalllesFactura + "</DetalleServicio>" 
	     + getOtrosCargos(factura) +
	     "<ResumenFactura>" +
		       getCodigoMoneda(factura.getCodigoMoneda(),factura.getTipoCambio())+
		       "<TotalServGravados>" +  FacturaElectronicaUtils.truncateDecimal(factura.getTotalServGravados(),5) + "</TotalServGravados>" +
		       "<TotalServExentos>" +  FacturaElectronicaUtils.truncateDecimal(factura.getTotalServExentos(),5) + "</TotalServExentos>" +
		       "<TotalServExonerado>" +  FacturaElectronicaUtils.truncateDecimal(factura.getTotalServExonerado(),5) + "</TotalServExonerado>" +
		       "<TotalMercanciasGravadas>" +  FacturaElectronicaUtils.truncateDecimal(factura.getTotalMercanciasGravadas(),5) + "</TotalMercanciasGravadas>" +
		       "<TotalMercanciasExentas>" +  FacturaElectronicaUtils.truncateDecimal(factura.getTotalMercanciasExentas(),5) + "</TotalMercanciasExentas>" +
		       "<TotalMercExonerada>" +  FacturaElectronicaUtils.truncateDecimal(factura.getTotalMercExonerada(),5) + "</TotalMercExonerada>" +
		       "<TotalGravado>" +      FacturaElectronicaUtils.truncateDecimal(factura.getTotalGravado(),5) + "</TotalGravado>" +
		       "<TotalExento>" +       FacturaElectronicaUtils.truncateDecimal(factura.getTotalExento(),5) + "</TotalExento>" +
		       "<TotalExonerado>" +    FacturaElectronicaUtils.truncateDecimal(factura.getTotalExonerado(),5) + "</TotalExonerado>" +
		       "<TotalVenta>" +        FacturaElectronicaUtils.truncateDecimal(factura.getTotalVenta(),5) + "</TotalVenta>" +
		       "<TotalDescuentos>" +   FacturaElectronicaUtils.truncateDecimal(factura.getTotalDescuentos(),5) + "</TotalDescuentos>" +
		       "<TotalVentaNeta>" +    FacturaElectronicaUtils.truncateDecimal(factura.getTotalVentaNeta(),5) + "</TotalVentaNeta>" +
		       "<TotalImpuesto>" +     FacturaElectronicaUtils.truncateDecimal(impuestoTotal(factura.getTotalImpuesto(),factura.getTotalExonerado()),5) + "</TotalImpuesto>" +
		       "<TotalIVADevuelto>" +  FacturaElectronicaUtils.truncateDecimal(factura.getTotalIVADevuelto(),5) + "</TotalIVADevuelto>" +
		       getTotalOtrosCargos(factura.getTotalOtrosCargos()) +
		       "<TotalComprobante>" +  FacturaElectronicaUtils.truncateDecimal(factura.getTotalComprobante(),5) + "</TotalComprobante>" +
			     "</ResumenFactura>" +
           informacionFerencia(factura)+
           "<Otros>" +
	     		"<OtroTexto codigo=\"obs\">" + FacturaElectronicaUtils.procesarTexto(observacion) + "</OtroTexto>" +
	     		Utils.oTroContenido()+
	     "</Otros>" +    
	 "</FacturaElectronica>";
				
		} catch (Exception e) {
			log.error("** Error  getCrearXMLSinFirma: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		 return xml;
	}
	
	private String getTotalOtrosCargos(Double cargos) {
		String resultado = Constantes.EMPTY;
		if (cargos == null) {
			return resultado;
		}
		if(cargos.equals(Constantes.ZEROS_DOUBLE)) {
			return resultado;
		}
		
		resultado  = "<TotalOtrosCargos>" +  FacturaElectronicaUtils.truncateDecimal(cargos,5) + "</TotalOtrosCargos>"; 
		
		return resultado;
	}	
	
	private Double impuestoTotal(Double impuestoTotal,Double totalExonerado) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if(totalExonerado > Constantes.ZEROS_DOUBLE) {
			if(impuestoTotal.equals(Constantes.ZEROS_DOUBLE)) {
				resultado = Utils.roundFactura(impuestoTotal, 0);
			}else {
				resultado = impuestoTotal;
			}
		}else {
			resultado = impuestoTotal;
		}
		
		
		return resultado;
	}
	private String actividadComercial(Factura factura) {
		String resultado = Constantes.EMPTY;
		if(factura.getCodigoActividad() != null) {
			resultado = factura.getCodigoActividad();
		}
		if(resultado.equals(Constantes.EMPTY)) {
			resultado = factura.getEmpresa().getCodigoActividad();
		}
		
		
		return FacturaElectronicaUtils.replazarConZeros(resultado,Constantes.FORMATO_CODIGO_ACTIVIDAD);
	}
	
	private String informacionFerencia(Factura factura) {	
	  String resultado = Constantes.EMPTY;
	  
	  if (factura.getReferenciaNumero() ==null) {
	  	return resultado;
	  }
	  if (factura.getReferenciaNumero().equals(Constantes.EMPTY)) {
	  	return resultado;
	  }
	  
	  
		String datereferenciaEmision = FacturaElectronicaUtils.rfc3339(factura.getReferenciaFechaEmision());
		
		resultado =  "<InformacionReferencia>" +
	  "<TipoDoc>" + Utils.zeroPad(FacturaElectronicaUtils.procesarTexto(factura.getReferenciaTipoDoc()),2) + "</TipoDoc>" +
	  "<Numero>" + factura.getReferenciaNumero() + "</Numero>" +
	  "<FechaEmision>" + datereferenciaEmision + "</FechaEmision>" +
	  "<Codigo>" + factura.getReferenciaCodigo() + "</Codigo>" +
	  "<Razon>" + FacturaElectronicaUtils.procesarTexto(factura.getReferenciaRazon()) + "</Razon>" +
	"</InformacionReferencia>" ;
		
		return resultado;

	}

	private String getOtrosCargos(Factura factura) {
		String resultado = Constantes.EMPTY;
		
		try {
			if(factura.getTotalOtrosCargos() ==null) {
				return resultado;
			}
			if(factura.getTotalOtrosCargos() > Constantes.ZEROS_DOUBLE) {
	  		 resultado = "<OtrosCargos>" +
	                       "<TipoDocumento>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(factura.getTipoDocumentoOtroCargo().trim(),2)) + "</TipoDocumento>" +
	                        "<Detalle>" + FacturaElectronicaUtils.procesarTexto(factura.getDetalleOtroCargo().trim()) + "</Detalle>" +
	 	                     "<MontoCargo>" + FacturaElectronicaUtils.truncateDecimal(factura.getTotalOtrosCargos(),5) +"</MontoCargo>";
	 	     resultado += "</OtrosCargos>";
	     }
			
		} catch (Exception e) {
			log.error("** Error  getTelefono: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}
	
	private String getCodigoMoneda(String codigoMoneda,Double tipoCambio) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
	    if(tipoCambio > Constantes.ZEROS_DOUBLE) {
	  		 resultado = "<CodigoTipoMoneda>" +
	          "<CodigoMoneda>" + codigoMoneda + "</CodigoMoneda>" +
	 	        "<TipoCambio>" + FacturaElectronicaUtils.getConvertirBigDecimal(tipoCambio) +"</TipoCambio>";
	 	     resultado += "</CodigoTipoMoneda>";
	     }
			
		} catch (Exception e) {
			log.error("** Error  getTelefono: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}
	
	/**
   * Firma de la Factura electronica
   * @see com.emprendesoftcr.service.FacturaXMLServices#getFirmarXML(java.lang.String, com.emprendesoftcr.modelo.Factura)
   */
@Override
	public String getFirmarXML(String xmlString,Empresa empresa,Date fecha) throws Exception{
		String resultado = Constantes.EMPTY;
		try {
		
			Certificado certificado  = certificadoBo.findByEmpresa(empresa);
			if(certificado !=null) {
				resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_FACTURA_4_3,fecha);	
			}else {
				log.info("** Error  Empresa no se encuentra el certificado: " + empresa.getNombre());
			}
		
      
		} catch (Exception e) {
			log.error("** Error  getFirmarXML: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}
	
	/**
	 * 
	 * @param factura
	 * @return
	 */
	private String getMedioPago(Factura factura) throws Exception{
		String resultado = Constantes.EMPTY;
		try {
			String valor = Constantes.MEDIO_PAGO_EFECTIVO;
			if(factura.getTotalTarjeta() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() == Constantes.ZEROS_DOUBLE) {
				valor = Constantes.MEDIO_PAGO_TARJETA;
			}
			if(factura.getTotalBanco() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() == Constantes.ZEROS_DOUBLE && factura.getTotalTarjeta() == Constantes.ZEROS_DOUBLE) {
				valor = Constantes.MEDIO_PAGO_BANCO;
			}

			resultado = "<MedioPago>" + FacturaElectronicaUtils.replazarConZeros(valor,Constantes.FORMATO_MEDIOPAGO) ;         
	   
	       resultado += "</MedioPago>";
			
		} catch (Exception e) {
			log.error("** Error  getMedioPago: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
			
       return resultado;
	}
	
	/**
	 * 
	 * @param factura
	 * @return
	 */
	private String xmlDetalleServicio(Factura factura) throws Exception{
	 String lineas = Constantes.EMPTY;
	String tipoCodigo = Constantes.EMPTY;
	Boolean noaplicaFacturaNotieneCodigoCabys = Boolean.TRUE;
	try {
    Collection<Detalle> detalles = detalleBo.findByFactura(factura);
    for(Detalle detalle : detalles) {
    	tipoCodigo = Constantes.EMPTY; 
    	if(detalle.getTipoCodigo() !=null) {
    		if(detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_USO_INTERNO)) {
    			tipoCodigo=detalle.getTipoCodigo(); 
    		}
    		if(detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_POR_SERVICIO)) {
    			tipoCodigo=detalle.getTipoCodigo(); 
    		}
    		if(detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_CODIGO_VENDEDOR)) {
    			tipoCodigo=detalle.getTipoCodigo(); 
    		}
    		if(detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_CODIGO_COMPRADOR)) {
    			tipoCodigo=detalle.getTipoCodigo(); 
    		}
    		if(detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_CODIGO_ASIGNADO_POR_INDUSTRIAS)) {
    			tipoCodigo=detalle.getTipoCodigo(); 
    		}
    		if(detalle.getTipoCodigo().equals(Constantes.TIPO_CODIGO_ARTICULO_CODIGO_OTROS)) {
    			tipoCodigo=detalle.getTipoCodigo(); 
    		}
    		
    	}
    	if(tipoCodigo.equals(Constantes.EMPTY)) {
    		tipoCodigo = Constantes.TIPO_CODIGO_ARTICULO_CODIGO_VENDEDOR;
    	}
    	String unidadMedida =Constantes.UNIDAD_MEDIDA;
    	if(detalle.getUnidadMedida() !=null) {
    		if(!detalle.getUnidadMedida().equals(Constantes.EMPTY)) {
    			unidadMedida = detalle.getUnidadMedida();
    		}
    	}
    	Articulo articulo = articuloBo.buscarPorCodigoYEmpresa(detalle.getCodigo(), factura.getEmpresa());
    	if(articulo.getCodigoCabys() != null && articulo.getCodigoCabys().equals(Constantes.EMPTY)) {
    		noaplicaFacturaNotieneCodigoCabys = Boolean.FALSE;
    	}
    	
    	String codigoCabys = articulo.getCodigoCabys() != null && !articulo.getCodigoCabys().equals(Constantes.EMPTY)?articulo.getCodigoCabys().trim():Constantes.EMPTY; 
    	codigoCabys =articulo.getCodigoCabys() != null && articulo.getCodigoCabys().length() <= 13 ?articulo.getCodigoCabys().trim():Constantes.EMPTY;
    	
    	lineas += "<LineaDetalle>" +
          "<NumeroLinea>" + new BigInteger(detalle.getNumeroLinea().toString()) + "</NumeroLinea>" +
          "<Codigo>" + codigoCabys + "</Codigo>" +
          "<CodigoComercial>" +
              "<Tipo>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(tipoCodigo,2)) + "</Tipo>" +
              "<Codigo>" + FacturaElectronicaUtils.procesarTexto(detalle.getCodigo()) + "</Codigo>" +
          "</CodigoComercial>" +
          "<Cantidad>" + FacturaElectronicaUtils.getConvertirBigDecimalFortmato3Decimales(detalle.getCantidad()) + "</Cantidad>" +
          "<UnidadMedida>" + unidadMedida + "</UnidadMedida>" +
          "<Detalle>" + FacturaElectronicaUtils.procesarTexto(detalle.getDescripcion().trim()) + "</Detalle>" +
          "<PrecioUnitario>" +  FacturaElectronicaUtils.truncateDecimal(detalle.getPrecioUnitario(),5) + "</PrecioUnitario>" +
          "<MontoTotal>" +  FacturaElectronicaUtils.truncateDecimal(detalle.getMontoTotal(),5) + "</MontoTotal>" +
          getDescuento(detalle.getMontoDescuento())+
          "<SubTotal>" +  FacturaElectronicaUtils.truncateDecimal(detalle.getSubTotal(),5) + "</SubTotal>" +
          xmlBaseImponible(detalle.getFactura().getId(),detalle.getBaseImponible())+  
          xmlImpuestos(detalle.getFactura().getId(),detalle.getCodigoTarifa(),detalle.getTipoImpuesto(),detalle.getMontoImpuesto(),detalle.getImpuesto(),detalle) +
          xmlImpuestosNeto(detalle.getFactura().getId() ,detalle.getMontoImpuesto() ==null?Constantes.ZEROS_DOUBLE:detalle.getMontoImpuesto(),Constantes.ZEROS_DOUBLE,detalle.getImpuestoNeto()) +
          "<MontoTotalLinea>" +  FacturaElectronicaUtils.truncateDecimal(detalle.getMontoTotalLinea(),5) + "</MontoTotalLinea>" +
          "</LineaDetalle>";
    }
	} catch (Exception e) {
		log.error("** Error  xmlDetalleServicio: " + e.getMessage() + " fecha " + new Date());
		throw e;
	}finally {
		if(noaplicaFacturaNotieneCodigoCabys.equals(Boolean.FALSE)) {
			lineas = Constantes.EMPTY;
		}
	}
    
   
    return lineas;
}
	
	private String xmlExoneracion(String tipoDocumentoExoneracion,Date fechaEmisionExoneracion,String numeroDocumentoExoneracion,String nombreInstitucionExoneracion,Integer porcentajeExoneracion,Double montoExoneracion) {
  	String resultado = Constantes.EMPTY;
  	if(tipoDocumentoExoneracion == null) {
  		return resultado;
  	}else if(tipoDocumentoExoneracion.equals(Constantes.EMPTY)) {
  		return resultado;
  	}
  	String date = FacturaElectronicaUtils.rfc3339( fechaEmisionExoneracion);
  	 resultado = "<Exoneracion>" +
				  "<TipoDocumento>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(tipoDocumentoExoneracion,2)) + "</TipoDocumento>" +
	        "<NumeroDocumento>" + FacturaElectronicaUtils.procesarTexto(numeroDocumentoExoneracion) + "</NumeroDocumento>" +
	        "<NombreInstitucion>" + FacturaElectronicaUtils.procesarTexto(nombreInstitucionExoneracion) + "</NombreInstitucion>" +
	        "<FechaEmision>" + date+ "</FechaEmision>" +
	        "<PorcentajeExoneracion>" + porcentajeExoneracion + "</PorcentajeExoneracion>" +
	        "<MontoExoneracion>" + FacturaElectronicaUtils.truncateDecimal(montoExoneracion,5) + "</MontoExoneracion>" +
	        "</Exoneracion>" ;
  	return resultado;
  }
	 private String xmlBaseImponible(Long idFactura,Double baseImponible) throws Exception {
	  	String resultado = Constantes.EMPTY;
	  	
	  	
	  	try {
	  	 	if(baseImponible == null) {
	    		return resultado;
	    	}else if(baseImponible.equals(Constantes.ZEROS_DOUBLE)) {
	    		return resultado;
	    	}
	   
	    	
		    resultado = "<BaseImponible>" +  FacturaElectronicaUtils.truncateDecimal(baseImponible,5) + "</BaseImponible>" ;
				
			} catch (Exception e) {
				log.error("** Error  xmlImpuestos Factura :" + idFactura  + e.getMessage() + " fecha " + new Date());
				throw e;
			}
	    return resultado;
	}
	  
	private String xmlImpuestosNeto(Long idFactura ,Double montoImpuesto,Double montompuesto1,Double montoImpuestoNeto) throws Exception {
	  	String resultado = Constantes.EMPTY;
	  	if(montoImpuesto.equals(Constantes.ZEROS_DOUBLE) && montompuesto1.equals(Constantes.ZEROS_DOUBLE)) {
	  		return resultado;
	  	}
	  	
	  	try {
	  		
	  		if(montoImpuestoNeto > 0) {
	  			montoImpuestoNeto= Utils.roundFactura(montoImpuestoNeto, 5);
	  			resultado = "<ImpuestoNeto>" + FacturaElectronicaUtils.truncateDecimal(montoImpuestoNeto ,5)+ "</ImpuestoNeto>";
	  			
	  		}else {
	  			resultado = "<ImpuestoNeto>0</ImpuestoNeto>";
	  		}
		        
				
			} catch (Exception e) {
				log.error("** Error  xmlImpuestos Factura :" + idFactura  + e.getMessage() + " fecha " + new Date());
				throw e;
			}
	    return resultado;
	}
	
	private String xmlImpuestos(Long idFactura,String codigoTarifa,String tipoImpuesto,Double montoImpuesto,Double impuesto,Detalle detalle) throws Exception {
  	String resultado = Constantes.EMPTY;
  	String nodoCodigoTarifa = Constantes.EMPTY;
  	String nodoTarifa = Constantes.EMPTY;
  	tipoImpuesto = tipoImpuesto == null?Constantes.EMPTY:tipoImpuesto;
  	try {
  		
  		if(montoImpuesto.equals(Constantes.ZEROS_DOUBLE) && tipoImpuesto.equals(Constantes.EMPTY)) {
  			return resultado;
  			}
  		
  		if (codigoTarifa !=null) {
  			if(!codigoTarifa.equals(Constantes.EMPTY)) {
  				nodoCodigoTarifa ="<CodigoTarifa>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(codigoTarifa, 2)) + "</CodigoTarifa>";
  				
  			}
  			
  		}
  		
  		nodoTarifa = "<Tarifa>" + FacturaElectronicaUtils.truncateDecimal(impuesto,2 ) + "</Tarifa>" ;
  		if(montoImpuesto != null && tipoImpuesto !=null) {
  			if(montoImpuesto > Constantes.ZEROS_DOUBLE || !tipoImpuesto.equals(Constantes.EMPTY) ) {
	        resultado = "<Impuesto>" +
	            "<Codigo>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(tipoImpuesto, 2)) + "</Codigo>" +
	            nodoCodigoTarifa +
	            nodoTarifa+
	            "<Monto>" +  FacturaElectronicaUtils.truncateDecimal(montoImpuesto,5) + "</Monto>"
	        +  xmlExoneracion(detalle.getTipoDocumentoExoneracion(),detalle.getFechaEmisionExoneracion(),detalle.getNumeroDocumentoExoneracion(),detalle.getNombreInstitucionExoneracion(),detalle.getPorcentajeExoneracion(),detalle.getMontoExoneracion());
	        resultado += "</Impuesto>";
	    	}
  		}
			
		} catch (Exception e) {
			log.error("** Error  xmlImpuestos Factura :" + idFactura  + e.getMessage() + " fecha " + new Date());
			throw e;
		}
    return resultado;
}
	
	

/**
 *   
 * @param descuento
 * @return
 */
	private String getDescuento(Double descuento) throws Exception {
		String resultado = Constantes.EMPTY;
		if(descuento == null) {
			return resultado;
		}
		if(descuento.equals(Constantes.ZEROS_DOUBLE)) {
			return resultado;
		}
		try {
			 resultado = "<Descuento>" +
					  "<MontoDescuento>" + FacturaElectronicaUtils.truncateDecimal(descuento,5) + "</MontoDescuento>" +
		        "<NaturalezaDescuento>" + Constantes.FORMATO_NATURALEZA_DESCUENTO + "</NaturalezaDescuento>" + 
		        "</Descuento>" ;
			
		} catch (Exception e) {
			log.error("** Error  getDescuento: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}
 
	/**
	 * 
	 * @param factura
	 * @return
	 */
  private String xmlReceptor(Factura factura) throws Exception {
    String resultado = Constantes.EMPTY;
  	try {
      if (factura.getCliente() != null) {
       	if(!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) && !factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
      		String cedulaExtrangera = factura.getCliente().getIdentificacionExtranjero() !=null ?factura.getCliente().getIdentificacionExtranjero():Constantes.EMPTY;
       		
          if(cedulaExtrangera.equals(Constantes.EMPTY)) {
         		resultado = "<Receptor>" +
                "<Nombre>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getNombreCompleto()) + "</Nombre>" +
                xmlIdentificacion(factura) + 
                "<CorreoElectronico>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getCorreoElectronico()) + "</CorreoElectronico>" +
            "</Receptor>";
          	
          } else {
         		resultado = "<Receptor>" +
                "<Nombre>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getNombreCompleto()) + "</Nombre>" +
                "<IdentificacionExtranjero>" + factura.getCliente().getIdentificacionExtranjero() + "</IdentificacionExtranjero>" +
                "<CorreoElectronico>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getCorreoElectronico()) + "</CorreoElectronico>" +
            "</Receptor>";
          	
          }    		
 
       	}
       	}
		} catch (Exception e) {
			log.error("** Error  xmlReceptor: " + e.getMessage() + " fecha " + new Date());
			throw e;

		}
  	return resultado;
}
  
 
  
private String xmlIdentificacion (Factura factura) throws Exception {
	String resultado = Constantes.EMPTY;
	try {
    if (factura.getCliente() != null) {
      resultado = "<Identificacion>" +
                  "<Tipo>" + Utils.zeroPad(factura.getCliente().getTipoCedula(),2) + "</Tipo>" +
                  "<Numero>" + factura.getCliente().getCedula() + "</Numero>" +
              "</Identificacion>";
  } 
	} catch (Exception e) {
		log.error("** Error  xmlIdentificacion: " + e.getMessage() + " fecha " + new Date());
		throw e;

	}
	return resultado;
}
  
  
/**
 * Leer un String y transformarlo en un XML Factura
 * @see com.emprendesoftcr.service.FacturaXMLServices#getFacturaAPDF(java.lang.String)
 */
  @Override
	public  FacturaElectronica getFacturaAPDF(String xmlString) throws Exception{
		FacturaElectronica facturaElectronica = null;
		if(xmlString ==null) {
			return facturaElectronica;
		}
		if(xmlString.equals(Constantes.EMPTY)) {
			return facturaElectronica;
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder;
		try {
			 builder = factory.newDocumentBuilder();  
		   @SuppressWarnings("unused")
			Document document = builder.parse(new InputSource(new StringReader(xmlString)));
		} catch (Exception e) {
			log.error("** Error  getFacturaAPDF: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		
		return facturaElectronica;
		
		
	}

	

	
}

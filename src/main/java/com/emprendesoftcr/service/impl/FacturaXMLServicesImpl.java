package com.emprendesoftcr.service.impl;



import java.io.StringReader;
import java.math.BigInteger;
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

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.service.FacturaXMLServices;
import com.emprendesoftcr.service.FirmaElectronicaService;

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





	
	/**
	 * 
	 * @see com.emprendesoftcr.service.FacturaXMLServices#getCrearXML(com.emprendesoftcr.modelo.Factura)
	 */
	@Override
	public String getCrearXMLSinFirma(Factura factura) throws Exception{
		String xml = Constantes.EMPTY;
		try {
			String date = FacturaElectronicaUtils.toISO8601String(factura.getFechaEmision());
			 xml = "<FacturaElectronica xmlns=\"" + Constantes.DOCXMLS_FACTURA + "\" " +
	         "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
	 "<Clave>" + factura.getClave() + "</Clave>" +
	 "<NumeroConsecutivo>" + factura.getNumeroConsecutivo() + "</NumeroConsecutivo>" +
	 "<FechaEmision>" + date + "</FechaEmision>" +
	 "<Emisor>" +
	     "<Nombre>" + factura.getEmpresa().getNombre() + "</Nombre>" +
	     "<Identificacion>" +
	         "<Tipo>" + factura.getEmpresa().getTipoCedula() + "</Tipo>" +
	         "<Numero>" + factura.getEmpresa().getCedula() + "</Numero>" +
	     "</Identificacion>" +
	//     "<NombreComercial>" + factura.getEmpresa().getNombreComercial() + "</NombreComercial>" +
	     "<Ubicacion>" +
	         "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
	         "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
	         "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
//	         "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
	         "<OtrasSenas>" + factura.getEmpresa().getOtraSenas() + "</OtrasSenas>" +
	     "</Ubicacion>" +
	//     getTelefono(factura.getEmpresa().getTelefono(),factura.getEmpresa().getCodigoPais())+
	//     getFax(0,factura.getEmpresa().getCodigoPais()) +
	     "<CorreoElectronico>" + factura.getEmpresa().getCorreoElectronico() + "</CorreoElectronico>" +
	 "</Emisor>" +
	 xmlReceptor(factura) +
	     "<CondicionVenta>" + factura.getCondicionVenta() + "</CondicionVenta>" +
	     "<PlazoCredito>" + FacturaElectronicaUtils.replazarConZeros(factura.getPlazoCredito().toString(),Constantes.FORMATO_PLAZO_CREDITO) + "</PlazoCredito>"  
	      + getMedioPago(factura) +
	     "<DetalleServicio>" + xmlDetalleServicio(factura) + "</DetalleServicio>" +
	     "<ResumenFactura>" +
	         "<CodigoMoneda>" + factura.getCodigoMoneda() + "</CodigoMoneda>" +
	         "<TipoCambio>" + FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTipoCambio()) + "</TipoCambio>" +
	         "<TotalServGravados>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalServGravados()) + "</TotalServGravados>" +
	         "<TotalServExentos>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalServExentos()) + "</TotalServExentos>" +
	         "<TotalMercanciasGravadas>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalMercanciasGravadas()) + "</TotalMercanciasGravadas>" +
	         "<TotalMercanciasExentas>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalMercanciasExentas()) + "</TotalMercanciasExentas>" +
	         "<TotalGravado>" +      FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalGravado()) + "</TotalGravado>" +
	         "<TotalExento>" +       FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalExento()) + "</TotalExento>" +
	         "<TotalVenta>" +        FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalVenta()) + "</TotalVenta>" +
	         "<TotalDescuentos>" +   FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalDescuentos()) + "</TotalDescuentos>" +
	         "<TotalVentaNeta>" +    FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalVentaNeta()) + "</TotalVentaNeta>" +
	         "<TotalImpuesto>" +     FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalImpuesto()) + "</TotalImpuesto>" +
	         "<TotalComprobante>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalComprobante()) + "</TotalComprobante>" +
	     "</ResumenFactura>" +
	     "<Normativa>" +
	         "<NumeroResolucion>" + Constantes.NUMERO_RESOLUCION + "</NumeroResolucion>" +
	         "<FechaResolucion>" + Constantes.FECHA_RESOLUCION + "</FechaResolucion>" +
	     "</Normativa>" +
	     "<Otros>" +
	     		"<OtroTexto codigo=\"obs\">" + factura.getNota() + "</OtroTexto>" +
	     "</Otros>" +    
	 "</FacturaElectronica>";
				
		} catch (Exception e) {
			log.info("** Error  getCrearXMLSinFirma: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		
    	
		 return xml;
		
	}
	
	/**
   * Firma de la Factura electronica
   * @see com.emprendesoftcr.service.FacturaXMLServices#getFirmarXML(java.lang.String, com.emprendesoftcr.modelo.Factura)
   */
@Override
	public String getFirmarXML(String xmlString,Empresa empresa) throws Exception{
		String resultado = Constantes.EMPTY;
		try {
		
			Certificado certificado  = certificadoBo.findByEmpresa(empresa);
		
      resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_FACTURA);
		} catch (Exception e) {
			log.info("** Error  getFirmarXML: " + e.getMessage() + " fecha " + new Date());
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
			log.info("** Error  getMedioPago: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
			
       return resultado;
	}
	/**
	 * 
	 * @param telefono
	 * @param codigoPais
	 * @return
	 */
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
			log.info("** Error  getTelefono: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		
		return resultado;
	}
	
/**
 * 
 * @param telefono
 * @param codigoPais
 * @return
 */
	private String getFax(Integer telefono,Integer codigoPais) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			if(telefono > Constantes.ZEROS) {
				 resultado = "<Fax>" +
		          "<CodigoPais>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(codigoPais.toString()).toString(),Constantes.FORMATO_CODIGO_PAIS) + "</CodigoPais>" +
			        "<NumTelefono>" +FacturaElectronicaUtils.replazarConZeros(new BigInteger(telefono.toString()).toString(),Constantes.FORMATO_TELEFONO)  + "</NumTelefono>";
		        resultado += "</Fax>";
			}
			
		} catch (Exception e) {
			log.info("** Error  getFax: " + e.getMessage() + " fecha " + new Date());
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
	
	try {

    for(Detalle detalle : factura.getDetalles()) {
    	lineas += "<LineaDetalle>" +
          "<NumeroLinea>" + new BigInteger(detalle.getNumeroLinea().toString()) + "</NumeroLinea>" +
          "<Codigo>" +
          "<Tipo>" + Constantes.CODIGO_PRODUCTO_VENDEDOR + "</Tipo>" +
          "<Codigo>" + detalle.getCodigo() + "</Codigo>" +
          "</Codigo>" +
          "<Cantidad>" + FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getCantidad()) + "</Cantidad>" +
          "<UnidadMedida>" + detalle.getUnidadMedida() + "</UnidadMedida>" +
   //       "<UnidadMedidaComercial>" + detalle.getUnidadMedida() + "</UnidadMedidaComercial>" +
          "<Detalle>" + detalle.getDescripcion().trim() + "</Detalle>" +
          "<PrecioUnitario>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getPrecioUnitario()) + "</PrecioUnitario>" +
          "<MontoTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotal()) + "</MontoTotal>" +
          getDescuento(detalle.getMontoDescuento())+
          "<SubTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getSubTotal()) + "</SubTotal>" +
          xmlImpuestos(detalle) +
          "<MontoTotalLinea>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotalLinea()) + "</MontoTotalLinea>" +
          "</LineaDetalle>";
    }
	} catch (Exception e) {
		log.info("** Error  xmlDetalleServicio: " + e.getMessage() + " fecha " + new Date());
		throw e;
	}
    
   
    return lineas;
}
/**
 *   
 * @param descuento
 * @return
 */
private String getDescuento(Double descuento) throws Exception{
		String resultado = Constantes.EMPTY;
		if(descuento ==null) {
			return resultado;
		}
		try {
			 resultado ="<MontoDescuento>" + FacturaElectronicaUtils.getConvertirBigDecimal(descuento) + "</MontoDescuento>" +
		        "<NaturalezaDescuento>" + Constantes.FORMATO_NATURALEZA_DESCUENTO + "</NaturalezaDescuento>";
			
		} catch (Exception e) {
			log.info("** Error  getDescuento: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}
  /**
   * 
   * @param detalle
   * @return
   */
private String xmlImpuestos(Detalle detalle)throws Exception {
  	String resultado = Constantes.EMPTY;
  	try {
  		if(detalle.getMontoImpuesto() != null && detalle.getTipoImpuesto() !=null) {
    	if(detalle.getMontoImpuesto()>Constantes.ZEROS_DOUBLE) {
        resultado = "<Impuesto>" +
            "<Codigo>" + Utils.zeroPad(detalle.getTipoImpuesto(), 2) + "</Codigo>" +
            "<Tarifa>" + FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getImpuesto() ) + "</Tarifa>" +
            "<Monto>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoImpuesto()) + "</Monto>";
        resultado += "</Impuesto>";
    		
    	}
  		}
			
		} catch (Exception e) {
			log.info("** Error  xmlImpuestos: " + e.getMessage() + " fecha " + new Date());
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
       	if(!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
          resultado =  "<Receptor>" +
                  "<Nombre>" + factura.getCliente().getNombreCompleto() + "</Nombre>" +
                  xmlIdentificacion(factura) +
                  "<IdentificacionExtranjero>" + factura.getCliente().getIdentificacionExtranjero() + "</IdentificacionExtranjero>" +
 //                 "<NombreComercial>" + factura.getCliente().getNombreComercial() + "</NombreComercial>" +
                  ubicacionReceptor(factura)+
//                  getTelefono(factura.getCliente().getTelefono(),factura.getCliente().getCodigoPais())+
   //               getFax(0,factura.getCliente().getCodigoPais()) +    
                  "<CorreoElectronico>" + factura.getCliente().getCorreoElectronico() + "</CorreoElectronico>" +
              "</Receptor>";
      
       	}
       	}
		} catch (Exception e) {
			log.info("** Error  xmlReceptor: " + e.getMessage() + " fecha " + new Date());
			throw e;

		}
  	return resultado;
}
  
  private String ubicacionReceptor(Factura factura) {
  	
  	String resultado = Constantes.EMPTY;
  	if(factura.getCliente().getProvincia() !=null ) {
  		if(!factura.getCliente().getProvincia().equals(Constantes.EMPTY)) {
  	    resultado = "<Ubicacion>" +
  	        "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
  	        "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
  	        "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
  //	        "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
  	        "<OtrasSenas>" + factura.getCliente().getOtraSena() + "</OtrasSenas>" +
  	        "</Ubicacion>" ;
  		}
  	}

  	return resultado;
  }
  
private String xmlIdentificacion (Factura factura) throws Exception {
	String resultado = Constantes.EMPTY;
	try {
    if (factura.getCliente() != null) {
      resultado = "<Identificacion>" +
                  "<Tipo>" + factura.getCliente().getTipoCedula() + "</Tipo>" +
                  "<Numero>" + factura.getCliente().getCedula() + "</Numero>" +
              "</Identificacion>";
  } 
	} catch (Exception e) {
		log.info("** Error  xmlIdentificacion: " + e.getMessage() + " fecha " + new Date());
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
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder;
		try {
			 builder = factory.newDocumentBuilder();  
		   Document document = builder.parse(new InputSource(new StringReader(xmlString)));
		} catch (Exception e) {
			log.info("** Error  getFacturaAPDF: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		
		return facturaElectronica;
		
		
	}

	

	
}

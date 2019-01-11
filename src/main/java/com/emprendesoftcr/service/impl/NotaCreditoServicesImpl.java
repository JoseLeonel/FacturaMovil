package com.emprendesoftcr.service.impl;

import java.math.BigInteger;
import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.service.FirmaElectronicaService;
import com.emprendesoftcr.service.NotaCreditoXMLServices;

@Service("notaCreditoXMLServices")
@Transactional
@EnableTransactionManagement
public class NotaCreditoServicesImpl implements NotaCreditoXMLServices {
	
	private Logger	log= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CertificadoBo																					certificadoBo;
	
	@Autowired
	private FirmaElectronicaService firmaElectronicaService;
	
	@Autowired
	private FacturaBo							facturaBo;
	
	
	/**
   * Firma de la Factura electronica
   * @see com.emprendesoftcr.service.FacturaXMLServices#getFirmarXML(java.lang.String, com.emprendesoftcr.modelo.Factura)
   */
@Override
	public String getFirmarXML(String xmlString,Empresa empresa)  throws Exception{
		String resultado = Constantes.EMPTY;
		try {
			Certificado certificado  = certificadoBo.findByEmpresa(empresa);
			if(certificado !=null) {
				resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_NOTA_CREDITO); 
			}else {
				log.info("** Error  Empresa no se encuentra el certificado: " + empresa.getNombre());
			}
			 
		
     
		} catch (Exception e) {
			log.info("** Error  getFirmarXML: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}
	


  @Override
	public String getCrearXMLSinFirma(Factura factura) throws Exception {
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
			
			if (resta > 80) {
				factura.setFechaEmision(fecha);
				facturaBo.modificar(factura);
			}else {
				fecha = factura.getCreated_at();
			}
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
			
			String date = FacturaElectronicaUtils.toISO8601String(factura.getFechaEmision());
			String datereferenciaEmision = FacturaElectronicaUtils.toISO8601String(factura.getReferenciaFechaEmision());
		  
	     xml = "<NotaCreditoElectronica xmlns=\"" + Constantes.DOCXMLS_NOTA_CREDITO + "\" " +
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
	      //      "<NombreComercial>" + factura.getEmpresa().getNombreComercial() + "</NombreComercial>" +    
	            "<Ubicacion>" +
			            "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
			            "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
			            "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
			//            "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
			            "<OtrasSenas>" + factura.getEmpresa().getOtraSenas() + "</OtrasSenas>" +
	           "</Ubicacion>" +
	 //          getTelefono(factura.getEmpresa().getTelefono(),factura.getEmpresa().getCodigoPais())+
	//           getFax(0,factura.getEmpresa().getCodigoPais()) +
	            "<CorreoElectronico>" + factura.getEmpresa().getCorreoElectronico() + "</CorreoElectronico>" +
	        "</Emisor>" +
	        xmlReceptor(factura) +
	        "<CondicionVenta>" + factura.getCondicionVenta() + "</CondicionVenta>" +
	        "<PlazoCredito>" + FacturaElectronicaUtils.replazarConZeros(factura.getPlazoCredito() !=null?factura.getPlazoCredito().toString():Constantes.ZEROS.toString(),Constantes.FORMATO_PLAZO_CREDITO) + "</PlazoCredito>"  
	         + getMedioPago(factura) +
	        "<DetalleServicio>" + xmlDetalleServicio(factura) + "</DetalleServicio>" +
	        "<ResumenFactura>" +
			        "<CodigoMoneda>" + factura.getCodigoMoneda() + "</CodigoMoneda>" +
			        "<TipoCambio>" + FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTipoCambio()) + "</TipoCambio>" +
			        "<TotalServGravados>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalServGravados()) + "</TotalServGravados>" +
			        "<TotalServExentos>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalServExentos()) + "</TotalServExentos>" +
			        "<TotalMercanciasGravadas>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalMercanciasGravadas()) + "</TotalMercanciasGravadas>" +
			        "<TotalMercanciasExentas>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalMercanciasExentas()) + "</TotalMercanciasExentas>" +
			        "<TotalGravado>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalGravado()) + "</TotalGravado>" +
			        "<TotalExento>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalExento()) + "</TotalExento>" +
			        "<TotalVenta>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalVenta()) + "</TotalVenta>" +
			        "<TotalDescuentos>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalDescuentos()) + "</TotalDescuentos>" +
			        "<TotalVentaNeta>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalVentaNeta()) + "</TotalVentaNeta>" +
			        "<TotalImpuesto>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalImpuesto()) + "</TotalImpuesto>" +
			        "<TotalComprobante>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalComprobante()) + "</TotalComprobante>" +
	        "</ResumenFactura>" +
	        "<InformacionReferencia>" +
	                "<TipoDoc>" + factura.getReferenciaTipoDoc() + "</TipoDoc>" +
	                "<Numero>" + factura.getReferenciaNumero() + "</Numero>" +
	                "<FechaEmision>" + datereferenciaEmision + "</FechaEmision>" +
	                "<Codigo>" + factura.getReferenciaCodigo() + "</Codigo>" +
	                "<Razon>" + factura.getReferenciaRazon() + "</Razon>" +
	        "</InformacionReferencia>" +
	        
	            "<Normativa>" +
	            "<NumeroResolucion>" + Constantes.NUMERO_RESOLUCION + "</NumeroResolucion>" +
	            "<FechaResolucion>" + Constantes.FECHA_RESOLUCION + "</FechaResolucion>" +
	        "</Normativa>" +
	        "<Otros>" +
	        		"<OtroTexto codigo=\"obs\">" + observacion + "</OtroTexto>" +
	        "</Otros>" +    
	        "</NotaCreditoElectronica>";
			
		} catch (Exception e) {
			log.info("** Error  getCrearXMLSinFirma: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		
				
		
		return xml;
	}
	
	
	/**
	 * 
	 * @param factura
	 * @return
	 */
  private String xmlDetalleServicio(Factura factura)  throws Exception {
    String tipoCodigo = Constantes.EMPTY;
    String lineas = "";
    try {

      for(Detalle detalle : factura.getDetalles()) {
      	tipoCodigo = Constantes.EMPTY; 
      	if(detalle.getTipoCodigo() !=null)  {
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
      	lineas += "<LineaDetalle>" +
            "<NumeroLinea>" + new BigInteger(detalle.getNumeroLinea().toString()) + "</NumeroLinea>" +
            "<Codigo>" +
            "<Tipo>" + tipoCodigo + "</Tipo>" +
            "<Codigo>" + tipoCodigo + "</Codigo>" +
            "</Codigo>" +
            "<Cantidad>" + FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getCantidad()) + "</Cantidad>" +
            "<UnidadMedida>" + unidadMedida + "</UnidadMedida>" +
      //      "<UnidadMedidaComercial>" + detalle.getUnidadMedida() + "</UnidadMedidaComercial>" +
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
  
  
  private String xmlImpuestos(Detalle detalle) throws Exception {
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
	 * @param Nota Credito
	 * @return
	 */
	private String getMedioPago(Factura factura) throws Exception {
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
	private String getTelefono(Integer telefono,Integer codigoPais) throws Exception {
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
  private String xmlReceptor(Factura factura) throws Exception  {
  	String resultado = Constantes.EMPTY;
  	try {
      if (factura.getCliente() != null) {
      	if(!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
      		 resultado = "<Receptor>" +
                  "<Nombre>" + factura.getCliente().getNombreCompleto() + "</Nombre>" +
                  xmlIdentificacion(factura) +
                  "<IdentificacionExtranjero>" + factura.getCliente().getIdentificacionExtranjero() + "</IdentificacionExtranjero>" +
      //            "<NombreComercial>" + factura.getCliente().getNombreComercial() + "</NombreComercial>" +
                  ubicacionReceptor(factura)+
    //              getTelefono(factura.getCliente().getTelefono(),factura.getCliente().getCodigoPais())+
    //              getFax(0,factura.getCliente().getCodigoPais()) +    
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
 // 	        "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
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
	
 


}

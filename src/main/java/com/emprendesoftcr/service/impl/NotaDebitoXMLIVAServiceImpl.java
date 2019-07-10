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
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.service.FirmaElectronicaService;
import com.emprendesoftcr.service.NotaDebitoXMLIVAService;

/**
 * Realiza la creacion del xml y proceso de firma
 * NotaDebitoXMLServiceImpl.
 * @author jose.
 * @since 27 jul. 2018
 */
@Service("NotaDebitoXMLIVAService")
@Transactional
@EnableTransactionManagement
public class NotaDebitoXMLIVAServiceImpl implements NotaDebitoXMLIVAService {

	private Logger	log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CertificadoBo																					certificadoBo;
	
	@Autowired
	private FacturaBo							facturaBo;
	
	@Autowired
	private DetalleBo							detalleBo;
	
	
	@Autowired
	private FirmaElectronicaService firmaElectronicaService;

	@Override
	public String getFirmarXML(String xmlString, Empresa empresa,Date fecha) throws Exception{
		String resultado = Constantes.EMPTY;
		try {
			Certificado certificado  = certificadoBo.findByEmpresa(empresa);
			if(certificado !=null) {
				resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_NOTA_DEBITO_4_3,fecha);	
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
		
			String datereferenciaEmision = FacturaElectronicaUtils.rfc3339(factura.getReferenciaFechaEmision());
			 String date = FacturaElectronicaUtils.rfc3339(factura.getFechaEmision());
	    xml = "<NotaDebitoElectronica xmlns=\"" + Constantes.DOCXMLS_NOTA_DEBITO_4_3 + "\" " +
	                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
	        "<Clave>" + factura.getClave() + "</Clave>" +
	        "<NumeroConsecutivo>" + factura.getNumeroConsecutivo() + "</NumeroConsecutivo>" +
	        "<FechaEmision>" + date + "</FechaEmision>" +
	        "<Emisor>" +
	            "<Nombre>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getNombre()) + "</Nombre>" +
	            "<Identificacion>" +
	                "<Tipo>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getTipoCedula()) + "</Tipo>" +
	                "<Numero>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getCedula()) + "</Numero>" +
	            "</Identificacion>" +
	         //   "<NombreComercial>" + factura.getEmpresa().getNombreComercial() + "</NombreComercial>" +    
	            "<Ubicacion>" +
			            "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
			            "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
			            "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
			   //         "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
			            "<OtrasSenas>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getOtraSenas()) + "</OtrasSenas>" +
	           "</Ubicacion>" +
	     //      getTelefono(factura.getEmpresa().getTelefono(),factura.getEmpresa().getCodigoPais())+
	     //      getFax(0,factura.getEmpresa().getCodigoPais()) +
	            "<CorreoElectronico>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getCorreoElectronico()) + "</CorreoElectronico>" +
	        "</Emisor>" +
	        xmlReceptor(factura) +
	        "<CondicionVenta>" + FacturaElectronicaUtils.procesarTexto(factura.getCondicionVenta()) + "</CondicionVenta>" +
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
	                "<TipoDoc>" + FacturaElectronicaUtils.procesarTexto(factura.getReferenciaTipoDoc()) + "</TipoDoc>" +
	                "<Numero>" + FacturaElectronicaUtils.procesarTexto(factura.getReferenciaNumero()) + "</Numero>" +
	                "<FechaEmision>" + datereferenciaEmision + "</FechaEmision>" +
	                "<Codigo>" + FacturaElectronicaUtils.procesarTexto(factura.getReferenciaCodigo()) + "</Codigo>" +
	                "<Razon>" + FacturaElectronicaUtils.procesarTexto(factura.getReferenciaRazon()) + "</Razon>" +
	            "</InformacionReferencia>" +
	            "<Normativa>" +
	            "<NumeroResolucion>" + Constantes.NUMERO_RESOLUCION + "</NumeroResolucion>" +
	            "<FechaResolucion>" + Constantes.FECHA_RESOLUCION + "</FechaResolucion>" +
	        "</Normativa>" +
	        "<Otros>" +
	        		"<OtroTexto codigo=\"obs\">" + FacturaElectronicaUtils.procesarTexto(observacion) + "</OtroTexto>" +
	        "</Otros>" +   
	        "</NotaDebitoElectronica>";
			
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
      Collection<Detalle> detalles = detalleBo.findByFactura(factura);
      for(Detalle detalle : detalles) {
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
            "<Tipo>" + Constantes.CODIGO_PRODUCTO_VENDEDOR + "</Tipo>" +
            "<Codigo>" + detalle.getCodigo() + "</Codigo>" +
            "</Codigo>" +
            "<Cantidad>" + FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getCantidad()) + "</Cantidad>" +
            "<UnidadMedida>" + unidadMedida + "</UnidadMedida>" +
    //        "<UnidadMedidaComercial>" + detalle.getUnidadMedida() + "</UnidadMedidaComercial>" +
            "<Detalle>" + FacturaElectronicaUtils.procesarTexto(detalle.getDescripcion().trim()) + "</Detalle>" +
            "<PrecioUnitario>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getPrecioUnitario()) + "</PrecioUnitario>" +
            "<MontoTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotal()) + "</MontoTotal>" +
            getDescuento(detalle.getMontoDescuento())+
            "<SubTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getSubTotal()) + "</SubTotal>" +
            xmlImpuestos(detalle.getFactura().getId(),detalle.getTipoImpuesto1(),detalle.getMontoImpuesto1(),detalle.getImpuesto1()) +
            xmlImpuestos(detalle.getFactura().getId(),detalle.getTipoImpuesto(),detalle.getMontoImpuesto(),detalle.getImpuesto()) +
            "<MontoTotalLinea>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotalLinea()) + "</MontoTotalLinea>" +
            "</LineaDetalle>";
      }
  	} catch (Exception e) {
  		log.info("** Error  xmlDetalleServicio: " + e.getMessage() + " fecha " + new Date());
  		throw e;
  	}
    return lineas;
}
  
  private String getDescuento(Double descuento) throws Exception {
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
  
  
  private String xmlImpuestos(Long idFactura,String tipoImpuesto,Double montoImpuesto,Double impuesto) throws Exception {
  	String resultado = Constantes.EMPTY;
  	try {
  		if(montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
  			return resultado;
  		}
  		if(montoImpuesto != null && tipoImpuesto !=null) {
    		if(montoImpuesto > Constantes.ZEROS_DOUBLE) {
          resultado = "<Impuesto>" +
              "<Codigo>" + Utils.zeroPad(FacturaElectronicaUtils.procesarTexto(tipoImpuesto), 2) + "</Codigo>" +
              "<Tarifa>" + FacturaElectronicaUtils.getConvertirBigDecimal(impuesto ) + "</Tarifa>" +
              "<Monto>" +  FacturaElectronicaUtils.getConvertirBigDecimal(montoImpuesto) + "</Monto>";
          resultado += "</Impuesto>";
      	}
  		}
  		
  	} catch (Exception e) {
  		log.info("** Error  xmlImpuestos Factura :" + idFactura  + e.getMessage() + " fecha " + new Date());
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
	private String getFax(Integer telefono,Integer codigoPais) throws Exception  {
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
  private String xmlReceptor(Factura factura) throws Exception {
  	String resultado = Constantes.EMPTY;
  	try {
      if (factura.getCliente() != null) {
       	if(!factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
      		String cedulaExtrangera = factura.getCliente().getIdentificacionExtranjero() !=null ?factura.getCliente().getIdentificacionExtranjero():Constantes.EMPTY;
       		
          if(cedulaExtrangera.equals(Constantes.EMPTY)) {
         		resultado = "<Receptor>" +
                "<Nombre>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getNombreCompleto()) + "</Nombre>" +
                xmlIdentificacion(factura) + 
//                "<NombreComercial>" + factura.getCliente().getNombreComercial() + "</NombreComercial>" +
//                "<Ubicacion>" +
//                    "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
//                    "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
//                    "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
//                    "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
//                    "<OtrasSenas>" + factura.getCliente().getOtraSena() + "</OtrasSenas>" +
//                "</Ubicacion>" +
//                getTelefono(factura.getCliente().getTelefono(),factura.getCliente().getCodigoPais())+
              //  getFax(0,factura.getCliente().getCodigoPais()) +    
                "<CorreoElectronico>" + factura.getCliente().getCorreoElectronico() + "</CorreoElectronico>" +
            "</Receptor>";
          	
          } else {
         		resultado = "<Receptor>" +
                "<Nombre>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getNombreCompleto()) + "</Nombre>" +
                xmlIdentificacion(factura) + 
                "<IdentificacionExtranjero>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getIdentificacionExtranjero()) + "</IdentificacionExtranjero>" +
//                "<NombreComercial>" + factura.getCliente().getNombreComercial() + "</NombreComercial>" +
//                "<Ubicacion>" +
//                    "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
//                    "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
//                    "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
//                    "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
//                    "<OtrasSenas>" + factura.getCliente().getOtraSena() + "</OtrasSenas>" +
//                "</Ubicacion>" +
//                getTelefono(factura.getCliente().getTelefono(),factura.getCliente().getCodigoPais())+
              //  getFax(0,factura.getCliente().getCodigoPais()) +    
                "<CorreoElectronico>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getCorreoElectronico()) + "</CorreoElectronico>" +
            "</Receptor>";
          	
          }    		
 
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
                    "<Tipo>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getTipoCedula()) + "</Tipo>" +
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

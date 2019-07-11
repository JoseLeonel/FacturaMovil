package com.emprendesoftcr.service.impl;

import java.math.BigInteger;
import java.util.Calendar;
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
import com.emprendesoftcr.service.TiqueteXMLService;

@Service("tiqueteXMLService")
@EnableTransactionManagement
public class TiqueteXMLServiceImpl implements TiqueteXMLService {
	private Logger	log= LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CertificadoBo							certificadoBo;
	
	@Autowired
	private FacturaBo							facturaBo;
	
	@Autowired
	 private FirmaElectronicaService firmaElectronicaService;

	@Autowired
	private DetalleBo							detalleBo;


	@Override
	public String getFirmarXML(String xmlString, Empresa empresa,Date fecha) throws Exception {
		String resultado = Constantes.EMPTY;
		try			{
			Certificado certificado  = certificadoBo.findByEmpresa(empresa);
			if(certificado !=null) {
				resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_TIQUETE_4_3,fecha);	
			}else {
				log.info("** Error  Empresa no se encuentra el certificado: " + empresa.getNombre());
			}
			
		} catch (Exception e) {
			log.info("** Error  getFirmarXML: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}
	
	@Transactional
	@Override
	public String getCrearXMLSinFirma(Factura factura) throws Exception{
		String resultado = Constantes.EMPTY;
	

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
				fecha = factura.getFechaEmision();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+ 2);
			
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
		
	
		
					 String date = FacturaElectronicaUtils.rfc3339(factura.getFechaEmision());
		   resultado = "<TiqueteElectronico xmlns=\"" + Constantes.DOCXMLS_TIQUETE_4_3 + "\" " +
		                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
		        "<Clave>" + factura.getClave() + "</Clave>" +
		        "<CodigoActividad>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getCodigoActividad(),Constantes.FORMATO_CODIGO_ACTIVIDAD) + "</CodigoActividad>" + 
		          
		        "<NumeroConsecutivo>" + factura.getNumeroConsecutivo() + "</NumeroConsecutivo>" +
		        "<FechaEmision>" + date + "</FechaEmision>" +
		        "<Emisor>" +
		        "<Nombre>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getNombre()) + "</Nombre>" +
		        "<Identificacion>" +
		            "<Tipo>" + factura.getEmpresa().getTipoCedula() + "</Tipo>" +
		            "<Numero>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getCedula()) + "</Numero>" +
		        "</Identificacion>" +
//		        "<NombreComercial>" + factura.getEmpresa().getNombreComercial() + "</NombreComercial>" +
		        "<Ubicacion>" +
		            "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
		            "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
		            "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
//		            "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
		            "<OtrasSenas>" + FacturaElectronicaUtils.procesarTexto(factura.getEmpresa().getOtraSenas()) + "</OtrasSenas>" +
		        "</Ubicacion>" +
		        getTelefono(factura.getEmpresa().getTelefono(),factura.getEmpresa().getCodigoPais())+
//		        getFax(0,factura.getEmpresa().getCodigoPais()) +
		        "<CorreoElectronico>" + factura.getEmpresa().getCorreoElectronico() + "</CorreoElectronico>" +
		        "</Emisor>" +
		        xmlReceptor(factura) +
		        "<CondicionVenta>" +  FacturaElectronicaUtils.procesarTexto(factura.getCondicionVenta()) + "</CondicionVenta>" +
		        "<PlazoCredito>" + FacturaElectronicaUtils.replazarConZeros(factura.getPlazoCredito() !=null?factura.getPlazoCredito().toString():Constantes.ZEROS.toString(),Constantes.FORMATO_PLAZO_CREDITO) + "</PlazoCredito>"  
		         + getMedioPago(factura) +
		        "<DetalleServicio>" + xmlDetalleServicio(factura) + "</DetalleServicio>" 
		        + getOtrosCargos(factura) +"<ResumenFactura>" +
		            getCodigoMoneda(factura.getCodigoMoneda(),factura.getTipoCambio())+
		            "<TotalServGravados>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalServGravados()) + "</TotalServGravados>" +
		            "<TotalServExentos>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalServExentos()) + "</TotalServExentos>" +
		            "<TotalServExonerado>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalServExonerado()) + "</TotalServExonerado>" +
		            "<TotalMercanciasGravadas>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalMercanciasGravadas()) + "</TotalMercanciasGravadas>" +
		            "<TotalMercanciasExentas>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalMercanciasExentas()) + "</TotalMercanciasExentas>" +
		            "<TotalMercExonerada>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalMercExonerada()) + "</TotalMercExonerada>" +
		            "<TotalGravado>" +      FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalGravado()) + "</TotalGravado>" +
		            "<TotalExento>" +       FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalExento()) + "</TotalExento>" +
		            "<TotalExonerado>" +    FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalExonerado()) + "</TotalExonerado>" +
		            "<TotalVenta>" +        FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalVenta()) + "</TotalVenta>" +
		            "<TotalDescuentos>" +   FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalDescuentos()) + "</TotalDescuentos>" +
		            "<TotalVentaNeta>" +    FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalVentaNeta()) + "</TotalVentaNeta>" +
		            "<TotalImpuesto>" +     FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalImpuesto()) + "</TotalImpuesto>" +
		            "<TotalIVADevuelto>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalIVADevuelto()) + "</TotalIVADevuelto>" +
		            "<TotalOtrosCargos>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalOtrosCargos()) + "</TotalOtrosCargos>" +
		            "<TotalComprobante>" +  FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalComprobante()) + "</TotalComprobante>" +
		        "</ResumenFactura>" +
		        informacionFerencia(factura) +
//			      "<Normativa>" +
//			        "<NumeroResolucion>" + Constantes.NUMERO_RESOLUCION + "</NumeroResolucion>" +
//			        "<FechaResolucion>" + Constantes.FECHA_RESOLUCION + "</FechaResolucion>" +
//		        "</Normativa>" +
				    "<Otros>" + oTroTexto(observacion) +
				    Utils.oTroContenido()+"</Otros>" +    
		        "</TiqueteElectronico>";
			
		} catch (Exception e) {
			log.info("** Error  getCrearXMLSinFirma: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
    return resultado;
		
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

	private String oTroTexto(String observacion) {
		
		String resultado = observacion == null?Constantes.EMPTY:observacion.trim();
		if(!observacion.equals(Constantes.EMPTY)) {
			 resultado = 	"<OtroTexto codigo=\"obs\">" + FacturaElectronicaUtils.procesarTexto(observacion) + "</OtroTexto>" ;
		}
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
	 	                     "<MontoCargo>" + FacturaElectronicaUtils.getConvertirBigDecimal(factura.getTotalOtrosCargos()) +"</MontoCargo>";
	 	     resultado += "</OtrosCargos>";
	     }
			
		} catch (Exception e) {
			log.info("** Error  getTelefono: " + e.getMessage() + " fecha " + new Date());
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
			log.info("** Error  getTelefono: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}

	
	/**
	 * 
	 * @param factura
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
	private String getFax(Integer telefono,Integer codigoPais) throws Exception{
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
      String tipoCodigo = Constantes.EMPTY;
    	String lineas = "";
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
            	
            	lineas += "<LineaDetalle>" +
                  "<NumeroLinea>" + new BigInteger(detalle.getNumeroLinea().toString()) + "</NumeroLinea>" +
                  "<CodigoComercial>" +
                      "<Tipo>" + FacturaElectronicaUtils.procesarTexto(Utils.zeroPad(tipoCodigo,2)) + "</Tipo>" +
                      "<Codigo>" + FacturaElectronicaUtils.procesarTexto(detalle.getCodigo()) + "</Codigo>" +
                  "</CodigoComercial>" +
                  "<Cantidad>" + FacturaElectronicaUtils.getConvertirBigDecimalFortmato3Decimales(detalle.getCantidad()) + "</Cantidad>" +
                  "<UnidadMedida>" + unidadMedida + "</UnidadMedida>" +
             //     "<UnidadMedidaComercial>" + detalle.getUnidadMedida() + "</UnidadMedidaComercial>" +
                  "<Detalle>" + FacturaElectronicaUtils.procesarTexto(detalle.getDescripcion().trim()) + "</Detalle>" +
                  "<PrecioUnitario>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getPrecioUnitario()) + "</PrecioUnitario>" +
                  "<MontoTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotal()) + "</MontoTotal>" +
                  getDescuento(detalle.getMontoDescuento())+
                  "<SubTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getSubTotal()) + "</SubTotal>" +
                  xmlBaseImponible(detalle.getFactura().getId(),detalle.getBaseImponible())+  
                  xmlImpuestos(detalle.getFactura().getId(),detalle.getCodigoTarifa(),detalle.getTipoImpuesto(),detalle.getMontoImpuesto(),detalle.getImpuesto()) +
                  xmlImpuestos(detalle.getFactura().getId(),detalle.getCodigoTarifa1(),detalle.getTipoImpuesto1(),detalle.getMontoImpuesto1(),detalle.getImpuesto1()) +
                  xmlImpuestosNeto(detalle.getFactura().getId() ,detalle.getMontoImpuesto() ==null?Constantes.ZEROS_DOUBLE:detalle.getMontoImpuesto(),detalle.getMontoImpuesto1() ==null?Constantes.ZEROS_DOUBLE:detalle.getMontoImpuesto(),detalle.getImpuestoNeto()) +
                  "<MontoTotalLinea>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotalLinea()) + "</MontoTotalLinea>" +
                  "</LineaDetalle>";
        }
        
    		    
			} catch (Exception e) {
				log.info("** Error  xmlDetalleServicio: " + e.getMessage() + " fecha " + new Date());
				throw e;
			}
      return lineas;
}
  private String xmlBaseImponible(Long idFactura,Double baseImponible) throws Exception {
  	String resultado = Constantes.EMPTY;
  	
  	
  	try {
  		if(baseImponible.equals(Constantes.ZEROS_DOUBLE)) {
    		return resultado;
    	}
    	if(baseImponible == null) {
    		return resultado;
    	}
    	
	        resultado = "<BaseImponible>" +  FacturaElectronicaUtils.getConvertirBigDecimal(baseImponible) + "</BaseImponible>" ;
			
		} catch (Exception e) {
			log.info("** Error  xmlImpuestos Factura :" + idFactura  + e.getMessage() + " fecha " + new Date());
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
  		if(montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
  			return resultado;
  		}
	        resultado = "<ImpuestoNeto>" + FacturaElectronicaUtils.getConvertirBigDecimal(montoImpuestoNeto )+ "</ImpuestoNeto>";
			
		} catch (Exception e) {
			log.info("** Error  xmlImpuestos Factura :" + idFactura  + e.getMessage() + " fecha " + new Date());
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
					  "<MontoDescuento>" + FacturaElectronicaUtils.getConvertirBigDecimal(descuento) + "</MontoDescuento>" +
		        "<NaturalezaDescuento>" + FacturaElectronicaUtils.procesarTexto(Constantes.FORMATO_NATURALEZA_DESCUENTO) + "</NaturalezaDescuento>" + 
		        "</Descuento>" ;
			
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
private String xmlImpuestos(Long idFactura,String codigoTarifa,String tipoImpuesto,Double montoImpuesto,Double impuesto) throws Exception {
  	String resultado = Constantes.EMPTY;
  	String nodoCodigoTarifa = Constantes.EMPTY;
  	String nodoTarifa = Constantes.EMPTY;
  	try {
  		if(montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
  			return resultado;
  		}
  		if (codigoTarifa !=null) {
  			if(!codigoTarifa.equals(Constantes.EMPTY)) {
  				nodoCodigoTarifa ="<CodigoTarifa>" + Utils.zeroPad( FacturaElectronicaUtils.procesarTexto(codigoTarifa), 2) + "</CodigoTarifa>";
  				
  			}
  			
  		}
  		nodoTarifa = "<Tarifa>" + FacturaElectronicaUtils.getConvertirBigDecimalFortmato2Decimales(impuesto ) + "</Tarifa>" ;
  		if(montoImpuesto != null && tipoImpuesto !=null) {
	  		if(montoImpuesto > Constantes.ZEROS_DOUBLE) {
	        resultado = "<Impuesto>" +
	            "<Codigo>" + Utils.zeroPad(FacturaElectronicaUtils.procesarTexto(tipoImpuesto), 2) + "</Codigo>" +
	            nodoCodigoTarifa +
	            nodoTarifa+
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
                "<Nombre>" +  FacturaElectronicaUtils.procesarTexto(factura.getCliente().getNombreCompleto()) + "</Nombre>" +
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
                "<CorreoElectronico>" + FacturaElectronicaUtils.procesarTexto(factura.getCliente().getCorreoElectronico()) + "</CorreoElectronico>" +
            "</Receptor>";
          	
          } else {
         		resultado = "<Receptor>" +
                "<Nombre>" +  FacturaElectronicaUtils.procesarTexto(factura.getCliente().getNombreCompleto()) + "</Nombre>" +
                xmlIdentificacion(factura) + 
                "<IdentificacionExtranjero>" + factura.getCliente().getIdentificacionExtranjero() + "</IdentificacionExtranjero>" +
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
private String xmlIdentificacion (Factura factura) throws Exception {
	String resultado = Constantes.EMPTY;
	try {
    if (factura.getCliente() != null) {
        return "<Identificacion>" +
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

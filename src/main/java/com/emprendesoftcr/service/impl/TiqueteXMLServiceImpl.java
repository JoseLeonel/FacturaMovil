package com.emprendesoftcr.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.service.FirmaElectronicaService;
import com.emprendesoftcr.service.TiqueteXMLService;

@Lazy
@Service("tiqueteXMLService")
@Transactional
public class TiqueteXMLServiceImpl implements TiqueteXMLService {

	@Lazy
	@Autowired
	private CertificadoBo							certificadoBo;
	
	@Lazy
	@Autowired
	FirmaElectronicaService firmaElectronicaService;

	@Override
	public String getFirmarXML(String xmlString, Empresa empresa) {
		String resultado = Constantes.EMPTY;
		try {
			Certificado certificado  = certificadoBo.findByEmpresa(empresa);
			resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_TIQUETE);
		} catch (Exception e) {
			
		}
		return resultado;
	}

	@Override
	public String getCrearXMLSinFirma(Factura factura) {
		 String date = FacturaElectronicaUtils.toISO8601String(factura.getFechaEmision());
    String xml = "<TiqueteElectronico xmlns=\"" + Constantes.DOCXMLS_TIQUETE + "\" " +
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
        "<NombreComercial>" + factura.getEmpresa().getNombreComercial() + "</NombreComercial>" +
        "<Ubicacion>" +
            "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
            "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
            "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
            "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
            "<OtrasSenas>" + factura.getEmpresa().getOtraSenas() + "</OtrasSenas>" +
        "</Ubicacion>" +
        getTelefono(factura.getEmpresa().getTelefono(),factura.getEmpresa().getCodigoPais())+
        getFax(0,factura.getEmpresa().getCodigoPais()) +
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
        "</TiqueteElectronico>";
    return xml;
		
	}
	
	/**
	 * 
	 * @param factura
	 * @return
	 */
	private String getMedioPago(Factura factura) {
		String resultado = Constantes.EMPTY;
		String valor = Constantes.MEDIO_PAGO_EFECTIVO;
		if(factura.getTotalTarjeta() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() == Constantes.ZEROS_DOUBLE) {
			valor = Constantes.MEDIO_PAGO_TARJETA;
		}
		if(factura.getTotalBanco() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() == Constantes.ZEROS_DOUBLE && factura.getTotalTarjeta() == Constantes.ZEROS_DOUBLE) {
			valor = Constantes.MEDIO_PAGO_BANCO;
		}

		resultado = "<MedioPago>" + FacturaElectronicaUtils.replazarConZeros(valor,Constantes.FORMATO_MEDIOPAGO) ;         
   
       resultado += "</MedioPago>";
       return resultado;
	}
	/**
	 * 
	 * @param telefono
	 * @param codigoPais
	 * @return
	 */
	private String getTelefono(Integer telefono,Integer codigoPais) {
		String resultado = Constantes.EMPTY;
    if(telefono > 0) {
 		 resultado = "<Telefono>" +
         "<CodigoPais>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(codigoPais.toString()).toString(),Constantes.FORMATO_CODIGO_PAIS) + "</CodigoPais>" +
	        "<NumTelefono>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(telefono.toString()).toString(),Constantes.FORMATO_TELEFONO) + "</NumTelefono>";
	     resultado += "</Telefono>";
    }
		return resultado;
	}
	
/**
 * 
 * @param telefono
 * @param codigoPais
 * @return
 */
	private String getFax(Integer telefono,Integer codigoPais) {
		String resultado = Constantes.EMPTY;
		if(telefono > 0) {
			 resultado = "<Fax>" +
	          "<CodigoPais>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(codigoPais.toString()).toString(),Constantes.FORMATO_CODIGO_PAIS) + "</CodigoPais>" +
		        "<NumTelefono>" +FacturaElectronicaUtils.replazarConZeros(new BigInteger(telefono.toString()).toString(),Constantes.FORMATO_TELEFONO)  + "</NumTelefono>";
	        resultado += "</Fax>";
		}
		return resultado;
	}
	
	/**
	 * 
	 * @param factura
	 * @return
	 */
  private String xmlDetalleServicio(Factura factura) {
    
    String lineas = "";
    for(Detalle detalle : factura.getDetalles()) {
    	lineas += "<LineaDetalle>" +
          "<NumeroLinea>" + new BigInteger(detalle.getNumeroLinea().toString()) + "</NumeroLinea>" +
          "<Codigo>" +
          "<Tipo>" + Constantes.CODIGO_PRODUCTO_VENDEDOR + "</Tipo>" +
          "<Codigo>" + detalle.getArticulo().getCodigo() + "</Codigo>" +
          "</Codigo>" +
          "<Cantidad>" + FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getCantidad()) + "</Cantidad>" +
          "<UnidadMedida>" + detalle.getArticulo().getUnidadMedida() + "</UnidadMedida>" +
          "<UnidadMedidaComercial>" + detalle.getArticulo().getUnidadMedida() + "</UnidadMedidaComercial>" +
          "<Detalle>" + detalle.getArticulo().getDescripcion().trim() + "</Detalle>" +
          "<PrecioUnitario>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getPrecioUnitario()) + "</PrecioUnitario>" +
          "<MontoTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotal()) + "</MontoTotal>" +
          getDescuento(detalle.getMontoDescuento())+
          "<SubTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getSubTotal()) + "</SubTotal>" +
          xmlImpuestos(detalle) +
          "<MontoTotalLinea>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotalLinea()) + "</MontoTotalLinea>" +
          "</LineaDetalle>";
    }
    return lineas;
}
/**
 *   
 * @param descuento
 * @return
 */
private String getDescuento(Double descuento) {
		String resultado = Constantes.EMPTY;
			 resultado ="<MontoDescuento>" + FacturaElectronicaUtils.getConvertirBigDecimal(descuento) + "</MontoDescuento>" +
		        "<NaturalezaDescuento>" + Constantes.FORMATO_NATURALEZA_DESCUENTO + "</NaturalezaDescuento>";
		return resultado;
	}
  /**
   * 
   * @param detalle
   * @return
   */
private String xmlImpuestos(Detalle detalle) {
  	String resultado = Constantes.EMPTY;
  	if(detalle.getMontoImpuesto()>0) {
      resultado = "<Impuesto>" +
          "<Codigo>" + Utils.zeroPad(detalle.getArticulo().getTipoImpuesto(), 2) + "</Codigo>" +
          "<Tarifa>" + FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getArticulo().getImpuesto() ) + "</Tarifa>" +
          "<Monto>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoImpuesto()) + "</Monto>";
      resultado += "</Impuesto>";
  		
  	}
    return resultado;
}

	/**
	 * 
	 * @param factura
	 * @return
	 */
  private String xmlReceptor(Factura factura) {
    if (factura.getCliente() != null) {
     	if(factura.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
    		return "";
    	}
        return "<Receptor>" +
                "<Nombre>" + factura.getCliente().getNombreCompleto() + "</Nombre>" +
                xmlIdentificacion(factura) +
                "<IdentificacionExtranjero>" + factura.getCliente().getIdentificacionExtranjero() + "</IdentificacionExtranjero>" +
                "<NombreComercial>" + factura.getCliente().getNombreComercial() + "</NombreComercial>" +
                "<Ubicacion>" +
                    "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getProvincia(),Constantes.FORMATO_PROVINCIA) + "</Provincia>" +
                    "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getCanton(),Constantes.FORMATO_CANTON) + "</Canton>" +
                    "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getDistrito(),Constantes.FORMATO_DISTRITO) + "</Distrito>" +
                    "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getBarrio(),Constantes.FORMATO_BARRIO) + "</Barrio>" +
                    "<OtrasSenas>" + factura.getCliente().getOtraSena() + "</OtrasSenas>" +
                "</Ubicacion>" +
                getTelefono(factura.getCliente().getTelefono(),factura.getCliente().getCodigoPais())+
                getFax(0,factura.getCliente().getCodigoPais()) +    
                "<CorreoElectronico>" + factura.getCliente().getCorreoElectronico() + "</CorreoElectronico>" +
            "</Receptor>";
    } else {
        return "";
    }
}
private String xmlIdentificacion (Factura factura) {
      if (factura.getCliente() != null) {
          return "<Identificacion>" +
                      "<Tipo>" + factura.getCliente().getTipoCedula() + "</Tipo>" +
                      "<Numero>" + factura.getCliente().getCedula() + "</Numero>" +
                  "</Identificacion>";
      } else {
          return "";
      }
}
  
  

}

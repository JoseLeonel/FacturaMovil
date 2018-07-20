package com.emprendesoftcr.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.error.SignException;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.P12Utils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.service.FacturaXMLServices;
import com.emprendesoftcr.type.CertificateInfo;
import com.google.common.collect.ImmutableList;

/**
 * Proceso de armado del XML
 * Firmado del XML
 * FacturaXMLServicesImpl.
 * @author jose.
 * @since 13 jul. 2018
 */
@Service("facturaXMLServices")
@Transactional
public class FacturaXMLServicesImpl implements FacturaXMLServices {
	

	@Autowired
	private CertificadoBo																					certificadoBo;


	public static final String CODIGO_PRODUCTO_VENDEDOR = "01";
	public static final Integer PLAZO_CREDITO = 15;
	public static final String  OTROS_TEXTOS = "Obs";
	public static final String VENTAS_PRODUCTOS = "Ventas de productos";
	public static final String  MEDIO_PAGO_EFECTIVO = "01";
	public static final String  MEDIO_PAGO_TARJETA = "02";
	public static final String  MEDIO_PAGO_BANCO = "04";
	
	public static final String NUMERO_RESOLUCION = "DGT-R-48-2016";
	public static final String FECHA_RESOLUCION = "07-10-2016 00:00:00";
	public static final String LLAVES_CRIPTOGRAFICAS = "empresas/";
	public static final String DOCXMLNS_FACTURA_ELECTRONICA = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica";
	public static final String FORMATO_PROVINCIA = "0";
	public static final String FORMATO_CANTON = "00";
	public static final String FORMATO_DISTRITO = "00";
	public static final String FORMATO_BARRIO = "00";
	public static final String FORMATO_TELEFONO = "00000000";
  public static final String FORMATO_CODIGO_PAIS = "000";
  public static final String FORMATO_MEDIOPAGO = "00";
  public static final String FORMATO_PLAZO_CREDITO = "00";
  
  public static final String  FORMATO_NATURALEZA_DESCUENTO = "Descuento Comercial";


	
	/**
	 * 
	 * @see com.emprendesoftcr.service.FacturaXMLServices#getCrearXML(com.emprendesoftcr.modelo.Factura)
	 */
	@Override
	public String getCrearXMLSinFirma(Factura factura){
		
    String date = FacturaElectronicaUtils.toISO8601String(factura.getFechaEmision());
		 String xml = "<FacturaElectronica xmlns=\"" + Constantes.DOCXMLS + "\" " +
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
         "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getProvincia(),FORMATO_PROVINCIA) + "</Provincia>" +
         "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getCanton(),FORMATO_CANTON) + "</Canton>" +
         "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getDistrito(),FORMATO_DISTRITO) + "</Distrito>" +
         "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getEmpresa().getBarrio(),FORMATO_BARRIO) + "</Barrio>" +
         "<OtrasSenas>" + factura.getEmpresa().getOtraSenas() + "</OtrasSenas>" +
     "</Ubicacion>" +
     getTelefono(factura.getEmpresa().getTelefono(),factura.getEmpresa().getCodigoPais())+
     getFax(0,factura.getEmpresa().getCodigoPais()) +
     "<CorreoElectronico>" + factura.getEmpresa().getCorreoElectronico() + "</CorreoElectronico>" +
 "</Emisor>" +
 xmlReceptor(factura) +
     "<CondicionVenta>" + factura.getCondicionVenta() + "</CondicionVenta>" +
     "<PlazoCredito>" + FacturaElectronicaUtils.replazarConZeros(factura.getPlazoCredito().toString(),FORMATO_PLAZO_CREDITO) + "</PlazoCredito>"  
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
     "<Normativa>" +
         "<NumeroResolucion>" + NUMERO_RESOLUCION + "</NumeroResolucion>" +
         "<FechaResolucion>" + FECHA_RESOLUCION + "</FechaResolucion>" +
     "</Normativa>" +
     "<Otros>" +
     		"<OtroTexto codigo=\"obs\">" + factura.getNota() + "</OtroTexto>" +
     "</Otros>" +    
 "</FacturaElectronica>";
			
		 return xml;
		
	}
	
	
	
	/**
   * Firma de la Factura electronica
   * @see com.emprendesoftcr.service.FacturaXMLServices#getFirmarXML(java.lang.String, com.emprendesoftcr.modelo.Factura)
   */
@Override
	public String getFirmarXML(String xmlString,Empresa empresa) {
		String resultado = Constantes.EMPTY;
		try {

			Certificado certificado  = certificadoBo.findByEmpresa(empresa);
			
			
			String firmadoFactura = sign(xmlString,certificado); 
			firmadoFactura = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + firmadoFactura;
//			String valor = FacturaElectronicaUtils.base64Encode(firmadoFactura.getBytes("UTF-8"));
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      DocumentBuilder builder;
      builder = factory.newDocumentBuilder();
          // Use String reader
          Document document = builder.parse( new InputSource(
                  new StringReader( firmadoFactura ) ) );

          TransformerFactory tranFactory = TransformerFactory.newInstance();
          Transformer aTransformer = tranFactory.newTransformer();
          Source src = new DOMSource( document );
          Result dest = new StreamResult( new File( "prueba.xml" ) );
          aTransformer.transform( src, dest );
     
			
     resultado = firmadoFactura;
			
			        
		} catch (Exception e) {
			
		}
		
		return resultado;
	}
	
	/**
	 * 
	 * @param factura
	 * @return
	 */
	private String getMedioPago(Factura factura) {
		String resultado = Constantes.EMPTY;
		
		
		String valor = MEDIO_PAGO_EFECTIVO;
		if(factura.getTotalTarjeta() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() == Constantes.ZEROS_DOUBLE) {
			valor = MEDIO_PAGO_TARJETA;
		}
		if(factura.getTotalBanco() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() == Constantes.ZEROS_DOUBLE && factura.getTotalTarjeta() == Constantes.ZEROS_DOUBLE) {
			valor = MEDIO_PAGO_BANCO;
		}

		resultado = "<MedioPago>" + FacturaElectronicaUtils.replazarConZeros(valor,FORMATO_MEDIOPAGO) ;         
   
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
         "<CodigoPais>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(codigoPais.toString()).toString(),FORMATO_CODIGO_PAIS) + "</CodigoPais>" +
	        "<NumTelefono>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(telefono.toString()).toString(),FORMATO_TELEFONO) + "</NumTelefono>";
	     
   
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
	          "<CodigoPais>" + FacturaElectronicaUtils.replazarConZeros(new BigInteger(codigoPais.toString()).toString(),FORMATO_CODIGO_PAIS) + "</CodigoPais>" +
		        "<NumTelefono>" +FacturaElectronicaUtils.replazarConZeros(new BigInteger(telefono.toString()).toString(),FORMATO_TELEFONO)  + "</NumTelefono>";
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
          "<Tipo>" + CODIGO_PRODUCTO_VENDEDOR + "</Tipo>" +
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
  
  private String getDescuento(Double descuento) {
		String resultado = Constantes.EMPTY;
			 resultado ="<MontoDescuento>" + FacturaElectronicaUtils.getConvertirBigDecimal(descuento) + "</MontoDescuento>" +
		        "<NaturalezaDescuento>" + FORMATO_NATURALEZA_DESCUENTO + "</NaturalezaDescuento>";
			
	     

		
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
//        imp += "<Exoneracion>" +
//            "<TipoDocumento>" + Constantes.ZEROS + "</TipoDocumento>" +
//            "<NumeroDocumento>" + Constantes.ZEROS + "</NumeroDocumento>" +
//            "<NombreInstitucion>" + Constantes.ZEROS + "</NombreInstitucion>" +
//            "<FechaEmision>" + Constantes.ZEROS + "</FechaEmision>" +
//            "<MontoImpuesto>" + FacturaElectronicaUtils.getConvertirBigDecimal(Constantes.ZEROS_DOUBLE) + "</MontoImpuesto>" +
//            "<PorcentajeCompra>" + FacturaElectronicaUtils.getConvertirBigDecimal(Constantes.ZEROS_DOUBLE) + "</PorcentajeCompra>" +
//        "</Exoneracion>";
    
    return resultado;
}

	/**
	 * 
	 * @param factura
	 * @return
	 */
  private String xmlReceptor(Factura factura) {
    if (factura.getCliente() != null) {
        return "<Receptor>" +
                "<Nombre>" + factura.getCliente().getNombreCompleto() + "</Nombre>" +
                xmlIdentificacion(factura) +
                "<IdentificacionExtranjero>" + factura.getCliente().getIdentificacionExtranjero() + "</IdentificacionExtranjero>" +
                "<NombreComercial>" + factura.getCliente().getNombreComercial() + "</NombreComercial>" +
                "<Ubicacion>" +
                    "<Provincia>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getProvincia(),FORMATO_PROVINCIA) + "</Provincia>" +
                    "<Canton>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getCanton(),FORMATO_CANTON) + "</Canton>" +
                    "<Distrito>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getDistrito(),FORMATO_DISTRITO) + "</Distrito>" +
                    "<Barrio>" + FacturaElectronicaUtils.replazarConZeros(factura.getCliente().getBarrio(),FORMATO_BARRIO) + "</Barrio>" +
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
  
  
  
	

	/**
	 * 
	 * @param keyPath
	 * @param password
	 * @return
	 * @throws CertificateException
	 * @throws IOException
	 */
	private  CertificateInfo getCertificateInfo(String keyPath, String password) throws CertificateException, IOException {
		CertificateInfo certificateInfo = null;
		
		
		try {
			ClassPathResource cpr = new ClassPathResource("/home/jose/dev/workspaceMaven/FacturaElectronica/src/main/resources/static/llaves/011001097813.p12" );
		  String path =  new ClassPathResource("/home/jose/dev/workspaceMaven/FacturaElectronica/tmp/").getPath();
			
				certificateInfo = P12Utils.dataFromP12(path,cpr.getPath(), password);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return certificateInfo;
	}
	
	

	/**
	 * Proceso de Firmado
	 * @param docType
	 * @param content
	 * @param certificateInfo
	 * @return
	 * @throws SignException
	 */
	 private String sign( String content, Certificado certificado) throws SignException {
     String signature = "";
     String docXmlns = DOCXMLNS_FACTURA_ELECTRONICA;
     String qualifyingProperties = generateQualifyingProperties(certificado, docXmlns);
     String _signInfo = createInfo(content, qualifyingProperties, docXmlns);
     signature += _signInfo;
     signature += signInfo(_signInfo, certificado.getPrivateKey());
     signature += x509(ImmutableList.<String>of(certificado.getCertificate())) + qualifyingProperties;
     return envelope(content, signature);
 }

	/**
	 * 
	 * @param certificado
	 * @param docXmlns
	 * @return
	 */
	 private String generateQualifyingProperties (Certificado certificado, String docXmlns) {
     Timestamp timestamp = new Timestamp(System.currentTimeMillis());
     String date = FacturaElectronicaUtils.toISO8601String(new Date(timestamp.getTime() - 21600000));
     return "<ds:Object>" +
         "<xades:QualifyingProperties Target=\"#id-b950d386377a\" " +
         "xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\">" +				
         "<xades:SignedProperties xmlns=\"" + DOCXMLNS_FACTURA_ELECTRONICA + "\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Id=\"xades-id-b950d386377a\">" +
         "<xades:SignedSignatureProperties>" +
         "<xades:SigningTime>" + date + "</xades:SigningTime>" +
         "<xades:SigningCertificate>" +
         "<xades:Cert>" +
         "<xades:CertDigest>" +
         "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"></ds:DigestMethod>" +
         "<ds:DigestValue>" + certificado.getCerthash() + "</ds:DigestValue>" +
         "</xades:CertDigest>" +
         "<xades:IssuerSerial>" +
         "<ds:X509IssuerName>" + certificado.getX509issuerName() + "</ds:X509IssuerName>" +
         "<ds:X509SerialNumber>" + Long.parseLong(certificado.getX509serialNumber(), 16) + "</ds:X509SerialNumber>" +
         "</xades:IssuerSerial>" +
         "</xades:Cert>" +
         "</xades:SigningCertificate>" +
         "<xades:SignaturePolicyIdentifier>" +
         "<xades:SignaturePolicyImplied></xades:SignaturePolicyImplied>" +
         "</xades:SignaturePolicyIdentifier>" +
         "</xades:SignedSignatureProperties>" +
         "</xades:SignedProperties>" +
         "</xades:QualifyingProperties>" +
         "</ds:Object>";
 }
 /**
  * 
  * @param content
  * @param qualifyingProperties
  * @param docXmlns
  * @return
  */
 private String createInfo(String content, String qualifyingProperties, String docXmlns) {
     return "<ds:SignedInfo xmlns=\"" + DOCXMLNS_FACTURA_ELECTRONICA + "\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
         "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"></ds:CanonicalizationMethod>" +
         "<ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"></ds:SignatureMethod>" +
         "<ds:Reference Id=\"r-id-1\" URI=\"\">" +
         "<ds:Transforms>" +
         "<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></ds:Transform>" +
         "</ds:Transforms>" +
         "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"></ds:DigestMethod>" +
         "<ds:DigestValue>" + FacturaElectronicaUtils.digest(content) + "</ds:DigestValue>" +
         "</ds:Reference>" +				
         "<ds:Reference Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#xades-id-b950d386377a\">" +
         "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"></ds:DigestMethod>" +
         "<ds:DigestValue>" + FacturaElectronicaUtils.digest(FacturaElectronicaUtils.match(qualifyingProperties, "(<xades:SignedProperties.*xades:SignedProperties>)")) + "</ds:DigestValue>" +
         "</ds:Reference>" +				
         "</ds:SignedInfo>";
 }
 
 /**
  * 
  * @param info
  * @param privateKey
  * @return
  * @throws SignException
  */
 private String signInfo(String info, String privateKey) throws SignException {
     String signInf = FacturaElectronicaUtils.signSHA256RSA(info, privateKey);
     return "<ds:SignatureValue>" + signInf + "</ds:SignatureValue>";
 }
 
 /**
  * 
  * @param certificates
  * @return
  */
 private String x509(ImmutableList<String> certificates) {
     String cerout = "";
     for (String certificate : certificates) {
         cerout += "<ds:X509Certificate>" + certificate + "</ds:X509Certificate>";
     }
     return "<ds:KeyInfo><ds:X509Data>" + cerout + "</ds:X509Data></ds:KeyInfo>";
 }
 
 /**
  * 
  * @param content
  * @param signature
  * @return
  */
 private String envelope(String content, String signature) {
     String sign = "<ds:Signature Id=\"id-b950d386377a\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">" +
         signature + "</ds:Signature>" + "$1";
     return content.replaceAll("(<\\/(?:.(?!<))+>)$", sign);
 }

	
}

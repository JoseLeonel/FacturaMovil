package com.emprendesoftcr.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
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

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.error.SignException;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.fisco.P12Utils;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.service.FacturaXMLServices;
import com.emprendesoftcr.type.CertificateInfo;
import com.google.common.collect.ImmutableList;

@Service("facturaXMLServices")
@Transactional
public class FacturaXMLServicesImpl implements FacturaXMLServices {

	public static final String CODIGO_PRODUCTO_VENDEDOR = "01";
	public static final Integer PLAZO_CREDITO = 15;
	public static final String  OTROS_TEXTOS = "Obs";
	public static final String VENTAS_PRODUCTOS = "Ventas de productos";
	public static final String  MEDIO_PAGO_EFECTIVO = "01";
	public static final String  MEDIO_PAGO_TARJETA = "02";
	public static final String  MEDIO_PAGO_BANCO = "04";
	
	public static final String NUMERO_RESOLUCION = "DGT-R-48-2016";
	public static final String FECHA_RESOLUCION = "20-02-2017 13:22:22";
	public static final String LLAVES_CRIPTOGRAFICAS = "empresas/";
	public static final String DOCXMLNS_FACTURA_ELECTRONICA = "https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica";
	
	@Override
	public String generarFacturaElectronicaXML(Factura factura){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
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
         "<Provincia>" + factura.getEmpresa().getProvincia() + "</Provincia>" +
         "<Canton>" + factura.getEmpresa().getCanton() + "</Canton>" +
         "<Distrito>" + factura.getEmpresa().getDistrito() + "</Distrito>" +
         "<Barrio>" + factura.getEmpresa().getBarrio() + "</Barrio>" +
         "<OtrasSenas>" + factura.getEmpresa().getOtraSenas() + "</OtrasSenas>" +
     "</Ubicacion>" +
     getTelefono(factura.getEmpresa().getTelefono(),factura.getEmpresa().getCodigoPais())+
     getFax(0,factura.getEmpresa().getCodigoPais()) +
     "<CorreoElectronico>" + factura.getEmpresa().getCorreoElectronico() + "</CorreoElectronico>" +
 "</Emisor>" +
 xmlReceptor(factura) +
     "<CondicionVenta>" + factura.getCondicionVenta() + "</CondicionVenta>" +
     "<PlazoCredito>" + new BigInteger(factura.getPlazoCredito().toString()) + "</PlazoCredito>"  +
     "<MedioPago>" + getMedioPago(factura) + "</MedioPago>" +
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
         "<NumeroResolucion>" + FECHA_RESOLUCION + "</NumeroResolucion>" +
         "<FechaResolucion>" + NUMERO_RESOLUCION + "</FechaResolucion>" +
     "</Normativa>" +
     "<Otros>" +
     		"<OtroTexto codigo=\"obs\">" + factura.getNota() + "</OtroTexto>" +
     "</Otros>" +    
 "</FacturaElectronica>";
			
		 return xml;
		
	}
	
	private String getMedioPago(Factura factura) {
		String resultado = Constantes.EMPTY;
		
		
		String valor = MEDIO_PAGO_EFECTIVO;
		if(factura.getTotalTarjeta() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() == Constantes.ZEROS_DOUBLE) {
			valor = MEDIO_PAGO_TARJETA;
		}
		if(factura.getTotalBanco() > Constantes.ZEROS_DOUBLE && factura.getTotalEfectivo() == Constantes.ZEROS_DOUBLE && factura.getTotalTarjeta() == Constantes.ZEROS_DOUBLE) {
			valor = MEDIO_PAGO_BANCO;
		}

		resultado = "<MedioPago>" + valor ;         
   
       resultado += "</MedioPago>";
       return resultado;
	}
	
	private String getTelefono(Integer telefono,Integer codigoPais) {
		String resultado = Constantes.EMPTY;
		 resultado = "<Telefono>" +
	          "<CodigoPais>" + new BigInteger(codigoPais.toString()) + "</CodigoPais>" +
		        "<NumTelefono>" + new BigInteger(telefono.toString())  + "</NumTelefono>";
		     
	    
	        resultado += "</Telefono>";
		
		return resultado;
	}
	

	private String getFax(Integer telefono,Integer codigoPais) {
		String resultado = Constantes.EMPTY;
		 resultado = "<Fax>" +
	          "<CodigoPais>" + new BigInteger(codigoPais.toString()) + "</CodigoPais>" +
		        "<NumTelefono>" + new BigInteger(telefono.toString())  + "</NumTelefono>";
		     
	    
	        resultado += "</Fax>";
		
		return resultado;
	}
	
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
          "<MontoDescuento>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoDescuento()) + "</MontoDescuento>" +
          "<NaturalezaDescuento>" +  detalle.getNaturalezaDescuento().trim() + "</NaturalezaDescuento>" +
          "<SubTotal>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getSubTotal()) + "</SubTotal>" +
          xmlImpuestos(detalle) +
          "<MontoTotalLinea>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoTotalLinea()) + "</MontoTotalLinea>" +
          "</LineaDetalle>";
    }
    
    return lineas;
}
  
  private String xmlImpuestos(Detalle detalle) {
    String imp = "<Impuesto>" +
        "<Codigo>" + Utils.zeroPad(detalle.getArticulo().getTipoImpuesto(), 2) + "</Codigo>" +
        "<Tarifa>" + FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getArticulo().getImpuesto()) + "</Tarifa>" +
        "<Monto>" +  FacturaElectronicaUtils.getConvertirBigDecimal(detalle.getMontoImpuesto()) + "</Monto>";
        imp += "<Exoneracion>" +
            "<TipoDocumento>" + Constantes.ZEROS + "</TipoDocumento>" +
            "<NumeroDocumento>" + Constantes.ZEROS + "</NumeroDocumento>" +
            "<NombreInstitucion>" + Constantes.ZEROS + "</NombreInstitucion>" +
            "<FechaEmision>" + Constantes.ZEROS + "</FechaEmision>" +
            "<MontoImpuesto>" + FacturaElectronicaUtils.getConvertirBigDecimal(Constantes.ZEROS_DOUBLE) + "</MontoImpuesto>" +
            "<PorcentajeCompra>" + FacturaElectronicaUtils.getConvertirBigDecimal(Constantes.ZEROS_DOUBLE) + "</PorcentajeCompra>" +
        "</Exoneracion>";
    
    imp += "</Impuesto>";
    return imp;
}

	
  private String xmlReceptor(Factura factura) {
    if (factura.getCliente() != null) {
        return "<Receptor>" +
                "<Nombre>" + factura.getCliente().getNombreCompleto() + "</Nombre>" +
                xmlIdentificacion(factura) +
                "<IdentificacionExtranjero>" + factura.getCliente().getIdentificacionExtranjero() + "</IdentificacionExtranjero>" +
                "<NombreComercial>" + factura.getCliente().getNombreComercial() + "</NombreComercial>" +
                "<Ubicacion>" +
                    "<Provincia>" + factura.getCliente().getProvincia() + "</Provincia>" +
                    "<Canton>" + factura.getCliente().getCanton() + "</Canton>" +
                    "<Distrito>" + factura.getCliente().getDistrito() + "</Distrito>" +
                    "<Barrio>" + factura.getCliente().getDistrito() + "</Barrio>" +
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
  
  

	public void generarFacturaElectronicaTributacionXML(String xmlString,Factura factura) {
		try {
//			String xmlString = generarFacturaElectronicaXML(factura);
			
			CertificateInfo certificateInfo  = getCertificateInfo(factura.getEmpresa().getNombreLlaveCriptografica(),factura.getEmpresa().getClaveLlaveCriptografica().toString()); 
			String firmadoFactura = sign(xmlString,certificateInfo); 
			firmadoFactura = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + firmadoFactura;
			FacturaElectronicaUtils.base64Encode(firmadoFactura.getBytes("UTF-8"));
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      DocumentBuilder builder;
      builder = factory.newDocumentBuilder();
          // Use String reader
          Document document = builder.parse( new InputSource(
                  new StringReader( firmadoFactura ) ) );

          TransformerFactory tranFactory = TransformerFactory.newInstance();
          Transformer aTransformer = tranFactory.newTransformer();
          Source src = new DOMSource( document );
          Result dest = new StreamResult( new File( "xmlFileName.xml" ) );
          aTransformer.transform( src, dest );
     
			
//			// necesito hacer un contexto se crea la instancia
//			JAXBContext context = JAXBContext.newInstance(FacturaElectronica.class);
//
//			// Escribir el xml se utiliza el objete marshaller
//			Marshaller marshaller = context.createMarshaller();
//			FacturaElectronica facturaElectronica = crearFacturaElectronica(factura);
//			// tabular el documento xml
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			// salida
//			marshaller.marshal(facturaElectronica, System.out);
//
//			marshaller.marshal(facturaElectronica, new FileWriter("leo.xml"));
//			Document sourceDoc =    (Document) facturaElectronica;
			
			        
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private  CertificateInfo getCertificateInfo(String keyPath, String password) throws CertificateException, IOException {
		CertificateInfo certificateInfo = null;
		
		
		try {
			ClassPathResource cpr = new ClassPathResource("static/llaves/"+keyPath);
		
			
				certificateInfo = P12Utils.dataFromP12(cpr.getPath(), password);
			

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
	 private String sign( String content, CertificateInfo certificateInfo) throws SignException {
     String signature = "";
     String docXmlns = DOCXMLNS_FACTURA_ELECTRONICA;
     String qualifyingProperties = generateQualifyingProperties(certificateInfo, docXmlns);
     String _signInfo = createInfo(content, qualifyingProperties, docXmlns);
     signature += _signInfo;
     signature += signInfo(_signInfo, certificateInfo.privateKey());
     signature += x509(ImmutableList.<String>of(certificateInfo.certificate())) + qualifyingProperties;
     return envelope(content, signature);
 }

	
	 private String generateQualifyingProperties (CertificateInfo certificateInfo, String docXmlns) {
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
         "<ds:DigestValue>" + certificateInfo.certHash() + "</ds:DigestValue>" +
         "</xades:CertDigest>" +
         "<xades:IssuerSerial>" +
         "<ds:X509IssuerName>" + certificateInfo.x509IssuerName() + "</ds:X509IssuerName>" +
         "<ds:X509SerialNumber>" + Long.parseLong(certificateInfo.x509SerialNumber(), 16) + "</ds:X509SerialNumber>" +
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
 
 private String signInfo(String info, String privateKey) throws SignException {
     String signInf = FacturaElectronicaUtils.signSHA256RSA(info, privateKey);
     return "<ds:SignatureValue>" + signInf + "</ds:SignatureValue>";
 }
 
 private String x509(ImmutableList<String> certificates) {
     String cerout = "";
     for (String certificate : certificates) {
         cerout += "<ds:X509Certificate>" + certificate + "</ds:X509Certificate>";
     }
     return "<ds:KeyInfo><ds:X509Data>" + cerout + "</ds:X509Data></ds:KeyInfo>";
 }
 
 private String envelope(String content, String signature) {
     String sign = "<ds:Signature Id=\"id-b950d386377a\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">" +
         signature + "</ds:Signature>" + "$1";
     return content.replaceAll("(<\\/(?:.(?!<))+>)$", sign);
 }

	
}

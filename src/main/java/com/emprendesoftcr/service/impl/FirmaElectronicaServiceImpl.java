package com.emprendesoftcr.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.error.SignException;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.service.FirmaElectronicaService;
import com.google.common.collect.ImmutableList;

@Lazy
@Service("firmaElectronicaService")
@Transactional
public class FirmaElectronicaServiceImpl implements FirmaElectronicaService {
	
	private Logger				log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public String getFirmarDocumento(Certificado certificado,String xmlSinFimar ,String urlXMLNS) throws Exception {
		String resultado = Constantes.EMPTY;
		try {
			String firmadoFactura = sign(xmlSinFimar,certificado,urlXMLNS); 
			firmadoFactura = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + firmadoFactura;
			 
			resultado = firmadoFactura;
//      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//
//      DocumentBuilder builder;
//      builder = factory.newDocumentBuilder();
//          Document document = builder.parse( new InputSource(
//                  new StringReader( firmadoFactura ) ) );
//
//          TransformerFactory tranFactory = TransformerFactory.newInstance();
//          Transformer aTransformer = tranFactory.newTransformer();
//          Source src = new DOMSource( document );
//          Result dest = new StreamResult( new File( "prueba.xml" ) );
//          aTransformer.transform( src, dest );
//     
//			
     
			
			        
		} catch (Exception e) {
			log.info("** Error  getFirmarDocumento: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		
		return resultado;
	}
	
	/**
	 * Proceso de Firmado
	 * @param docType
	 * @param content
	 * @param certificateInfo
	 * @return
	 * @throws SignException
	 */
	 private String sign( String xmlSinFirmar, Certificado certificado,String urlXMLNS) throws Exception {
     String signature = "";

		 try {
	     String qualifyingProperties = generateQualifyingProperties(certificado, urlXMLNS);
	     String _signInfo = createInfo(xmlSinFirmar, qualifyingProperties, urlXMLNS);
	     signature += _signInfo;
	     signature += signInfo(_signInfo, certificado.getPrivateKey());
	     signature += x509(ImmutableList.<String>of(certificado.getCertificate())) + qualifyingProperties;
			
		} catch (Exception e) {
			log.info("** Error  sign: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
   
     return envelope(xmlSinFirmar, signature);
 }
	 


	/**
	 * 
	 * @param certificado
	 * @param docXmlns
	 * @return
	 */
	 private String generateQualifyingProperties (Certificado certificado, String urlXMLNS) throws Exception{
		 String resultado = Constantes.EMPTY;
		 try {
	     Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	     String date = FacturaElectronicaUtils.toISO8601String(new Date(timestamp.getTime() - 21600000));
	     
	     resultado = "<ds:Object>" +
	         "<xades:QualifyingProperties Target=\"#id-b950d386377a\" " +
	         "xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\">" +				
	         "<xades:SignedProperties xmlns=\"" + urlXMLNS + "\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Id=\"xades-id-b950d386377a\">" +
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
			
		} catch (Exception e) {
			log.info("** Error  generateQualifyingProperties: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		 return resultado;
 }
 /**
  * 
  * @param content
  * @param qualifyingProperties
  * @param docXmlns
  * @return
  */
 private String createInfo(String xmlSinFirmar, String qualifyingProperties, String urlXMLNS) throws Exception {
	 String resultado = Constantes.EMPTY;
	 try {
		 resultado =  "<ds:SignedInfo xmlns=\"" + urlXMLNS + "\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
         "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"></ds:CanonicalizationMethod>" +
         "<ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"></ds:SignatureMethod>" +
         "<ds:Reference Id=\"r-id-1\" URI=\"\">" +
         "<ds:Transforms>" +
         "<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></ds:Transform>" +
         "</ds:Transforms>" +
         "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"></ds:DigestMethod>" +
         "<ds:DigestValue>" + FacturaElectronicaUtils.digest(xmlSinFirmar) + "</ds:DigestValue>" +
         "</ds:Reference>" +				
         "<ds:Reference Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#xades-id-b950d386377a\">" +
         "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"></ds:DigestMethod>" +
         "<ds:DigestValue>" + FacturaElectronicaUtils.digest(FacturaElectronicaUtils.match(qualifyingProperties, "(<xades:SignedProperties.*xades:SignedProperties>)")) + "</ds:DigestValue>" +
         "</ds:Reference>" +				
         "</ds:SignedInfo>";
	} catch (Exception e) {
		log.info("** Error  createInfo: " + e.getMessage() + " fecha " + new Date());
		throw e;
	}
	 return resultado;
    
 }
 
 /**
  * 
  * @param info
  * @param privateKey
  * @return
  * @throws SignException
  */
 private String signInfo(String info, String privateKey) throws Exception {
	 String resultado = Constantes.EMPTY;
	 try {
     String signInf = FacturaElectronicaUtils.signSHA256RSA(info, privateKey);
     resultado =  "<ds:SignatureValue>" + signInf + "</ds:SignatureValue>";
		
	} catch (Exception e) {
		log.info("** Error  createInfo: " + e.getMessage() + " fecha " + new Date());
		throw e;
	}
	 return resultado;
 }
 
 /**
  * 
  * @param certificates
  * @return
  */
 private String x509(ImmutableList<String> certificates) throws Exception{
	 String resultado = Constantes.EMPTY;
	 try {
     String cerout = "";
     for (String certificate : certificates) {
         cerout += "<ds:X509Certificate>" + certificate + "</ds:X509Certificate>";
     }
     resultado = "<ds:KeyInfo><ds:X509Data>" + cerout + "</ds:X509Data></ds:KeyInfo>";
		
	} catch (Exception e) {
		log.info("** Error  x509: " + e.getMessage() + " fecha " + new Date());
		throw e;
	}
	 return resultado;
 }
 
 /**
  * 
  * @param content
  * @param signature
  * @return
  */
 private String envelope(String content, String signature) throws Exception{
	 String resultado = Constantes.EMPTY;
	 try {
     String sign = "<ds:Signature Id=\"id-b950d386377a\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">" +
         signature + "</ds:Signature>" + "$1";
     resultado = content.replaceAll("(<\\/(?:.(?!<))+>)$", sign);
		
	} catch (Exception e) {
		log.info("** Error  envelope: " + e.getMessage() + " fecha " + new Date());
		throw e;
	}
	 return resultado;
 }

}

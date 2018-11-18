package com.emprendesoftcr.service.impl;



import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.pdf.FacturaElectronica;
import com.emprendesoftcr.service.FirmaElectronicaService;
import com.emprendesoftcr.service.RecepcionFacturaXMLServices;

@Service("recepcionFacturaXMLServices")
@EnableTransactionManagement
public class RecepcionFacturaXMLServicesImpl implements RecepcionFacturaXMLServices {
	private Logger	log= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CertificadoBo	certificadoBo;
	
	@Autowired
	private FirmaElectronicaService firmaElectronicaService;

	
	@Override
	public String getCrearXMLSinFirma(RecepcionFactura recepcionFactura) throws Exception{
		
		String xml = Constantes.EMPTY;
			
		try {
			String date = FacturaElectronicaUtils.toISO8601String(recepcionFactura.getFacturaFechaEmision());
			String impuestos  = (recepcionFactura.getFacturaTotalImpuestos() != null && recepcionFactura.getFacturaTotalImpuestos() > Constantes.ZEROS_DOUBLE) ?  "<MontoTotalImpuesto>" + recepcionFactura.getFacturaTotalImpuestos().toString() + "</MontoTotalImpuesto>" : "";

			xml = "<MensajeReceptor xmlns=\"" + Constantes.DOCXMLS_RECEPCION_FACTURA + "\" " +
	          "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
	          "<Clave>" + recepcionFactura.getFacturaClave() + "</Clave>" +
	          "<NumeroCedulaEmisor>" + recepcionFactura.getEmisorCedula() + "</NumeroCedulaEmisor>" +
	          "<FechaEmisionDoc>" + date + "</FechaEmisionDoc>" +
	          "<Mensaje>" + recepcionFactura.getMensaje() + "</Mensaje>" +	          
	           impuestos  +
	          "<TotalFactura>" + recepcionFactura.getFacturaTotalComprobante() + "</TotalFactura>" +
	          "<NumeroCedulaReceptor>" + recepcionFactura.getReceptorCedula() + "</NumeroCedulaReceptor>" +
	          "<NumeroConsecutivoReceptor>" + recepcionFactura.getNumeroConsecutivoReceptor() + "</NumeroConsecutivoReceptor>" + 
			  "</MensajeReceptor>";		
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
	public String getFirmarXML(String xmlString, Empresa empresa) throws Exception{
		String resultado = Constantes.EMPTY;
		try {
		
			Certificado certificado  = certificadoBo.findByEmpresa(empresa);		
      resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_RECEPCION_FACTURA);
		} catch (Exception e) {
			log.info("** Error  getFirmarXML: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}
	
  
  
	/**
	 * Leer un String y transformarlo en un XML Factura
	 * @see com.emprendesoftcr.service.FacturaXMLServices#getFacturaAPDF(java.lang.String)
	 */
  @SuppressWarnings("unused")
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

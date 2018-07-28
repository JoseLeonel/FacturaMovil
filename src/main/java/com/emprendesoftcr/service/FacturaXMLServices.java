package com.emprendesoftcr.service;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.pdf.FacturaElectronica;

public interface FacturaXMLServices {
	
	
	String getFirmarXML(String xmlString,Empresa empresa) throws Exception ;
	
	 String  getCrearXMLSinFirma(Factura factura) throws Exception;
	 
	 FacturaElectronica getFacturaAPDF(String xml) throws Exception;
	 
	 
	 ;

}

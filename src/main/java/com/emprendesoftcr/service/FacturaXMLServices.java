package com.emprendesoftcr.service;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;

public interface FacturaXMLServices {
	
	
	String getFirmarXML(String xmlString,Empresa empresa);
	
	 String  getCrearXMLSinFirma(Factura factura);

}

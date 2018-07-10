package com.emprendesoftcr.service;

import com.emprendesoftcr.modelo.Factura;

public interface FacturaXMLServices {
	
	
	void generarFacturaElectronicaTributacionXML(String xmlString,Factura factura);
	
	 String  generarFacturaElectronicaXML(Factura factura);

}

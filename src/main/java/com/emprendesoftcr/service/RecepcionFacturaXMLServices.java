package com.emprendesoftcr.service;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.pdf.FacturaElectronica;

public interface RecepcionFacturaXMLServices {

	String getFirmarXML(String xmlString, Empresa empresa) throws Exception;

	String getCrearXMLSinFirma(RecepcionFactura recepcionFactura) throws Exception;

	FacturaElectronica getFacturaAPDF(String xml) throws Exception;

}

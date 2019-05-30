package com.emprendesoftcr.service;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.pdf.FacturaElectronica;

public interface FacturaXMLServices {

	String getFirmarXML(String xmlString, Empresa empresa,Date fecha) throws Exception;

	String getCrearXMLSinFirma(Factura factura) throws Exception;

	FacturaElectronica getFacturaAPDF(String xml) throws Exception;

	;

}

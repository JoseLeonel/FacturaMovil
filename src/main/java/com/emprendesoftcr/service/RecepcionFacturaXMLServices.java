package com.emprendesoftcr.service;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.pdf.FacturaElectronica;

public interface RecepcionFacturaXMLServices {

	String getFirmarXML(String xmlString, Empresa empresa,Date fecha) throws Exception;

	String getCrearXMLSinFirma(RecepcionFactura recepcionFactura) throws Exception;

	FacturaElectronica getFacturaAPDF(String xml) throws Exception;

}

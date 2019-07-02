package com.emprendesoftcr.service;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;

public interface NotaCreditoXMLServices {

	String getFirmarXML(String xmlString,Empresa empresa,Date fecha) throws Exception;
	String getCrearXMLSinFirma(Factura factura) throws Exception;;

}

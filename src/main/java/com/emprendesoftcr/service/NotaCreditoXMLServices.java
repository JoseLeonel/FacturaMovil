package com.emprendesoftcr.service;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;

public interface NotaCreditoXMLServices {

	String getFirmarXML(String xmlString,Empresa empresa) throws Exception;;
	String getCrearXMLSinFirma(Factura factura) throws Exception;;

}

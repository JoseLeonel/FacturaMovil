package com.emprendesoftcr.service;

import com.emprendesoftcr.fisco.RespuestaHaciendaXML;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;

public interface RespuestaHaciendaXMLService {
	String getFirmarXML(String xmlString,Empresa empresa,Factura factura) throws Exception;;
	String getCrearXMLSinFirma(RespuestaHaciendaXML respuestaHacienda,Factura factura) throws Exception;;
}

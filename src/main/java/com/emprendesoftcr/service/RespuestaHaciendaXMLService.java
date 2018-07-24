package com.emprendesoftcr.service;

import com.emprendesoftcr.fisco.RespuestaHaciendaXML;
import com.emprendesoftcr.modelo.Empresa;

public interface RespuestaHaciendaXMLService {
	String getFirmarXML(String xmlString,Empresa empresa);
	String getCrearXMLSinFirma(RespuestaHaciendaXML respuestaHacienda);
}

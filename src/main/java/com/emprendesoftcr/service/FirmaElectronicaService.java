package com.emprendesoftcr.service;

import java.util.Date;

import com.emprendesoftcr.modelo.Certificado;

public interface FirmaElectronicaService {
	
	String getFirmarDocumento(Certificado certificado,String xmlSinFimar,String urlXMLNS,Date fecha) throws Exception;

}

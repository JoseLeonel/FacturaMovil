package com.emprendesoftcr.service;

import com.emprendesoftcr.modelo.Certificado;

public interface FirmaElectronicaService {
	
	String getFirmarDocumento(Certificado certificado,String xmlSinFimar,String urlXMLNS) throws Exception;

}

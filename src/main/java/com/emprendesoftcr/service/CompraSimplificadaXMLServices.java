package com.emprendesoftcr.service;

import java.util.Date;

import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;

public interface CompraSimplificadaXMLServices {

	String getFirmarXML(String xmlString, Empresa empresa, Date fecha) throws Exception;

	String getCrearXMLSinFirma(CompraSimplificada compraSimplificada) throws Exception;

}

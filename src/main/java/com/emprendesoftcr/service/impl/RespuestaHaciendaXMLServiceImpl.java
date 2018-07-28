package com.emprendesoftcr.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.CertificadoBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.fisco.RespuestaHaciendaXML;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.service.FirmaElectronicaService;
import com.emprendesoftcr.service.RespuestaHaciendaXMLService;

@Lazy
@Service("respuestaHaciendaXMLService")
@Transactional
public class RespuestaHaciendaXMLServiceImpl implements RespuestaHaciendaXMLService {
	private Logger	log= LoggerFactory.getLogger(this.getClass());
	@Lazy
	@Autowired
	CertificadoBo																					certificadoBo;
	
	@Lazy
	@Autowired
	FirmaElectronicaService firmaElectronicaService;
	
	@Override
	public String getFirmarXML(String xmlString, Empresa empresa) throws Exception{
		String resultado = Constantes.EMPTY;
		try {
		
			Certificado certificado  = certificadoBo.findByEmpresa(empresa);
		
      resultado = firmaElectronicaService.getFirmarDocumento(certificado, xmlString, Constantes.DOCXMLS_RESPUESTA_HACIENDA);
		} catch (Exception e) {
			log.info("** Error  findbyAcceso: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return resultado;
	}

	@Override
	public String getCrearXMLSinFirma(RespuestaHaciendaXML respuestaHacienda) throws Exception {
		String xml = Constantes.EMPTY;
		try {
			xml = "<MensajeHacienda xmlns=\"" + Constantes.DOCXMLS_RESPUESTA_HACIENDA + "\" " +
	        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
	         "<Clave>" + respuestaHacienda.getClave()                                           + "</Clave>" +
	        "<NombreEmisor>" + respuestaHacienda.getNombreEmisor()                              + "</NombreEmisor>" +
	         "<TipoIdentificacionEmisor>" + respuestaHacienda.getTipoIdentificacionEmisor()     + "</TipoIdentificacionEmisor>" +
	         "<NumeroCedulaEmisor>" + respuestaHacienda.getNumeroCedulaEmisor()                 + "</NumeroCedulaEmisor>"       +
	         "<NombreReceptor>" + respuestaHacienda.getNombreReceptor()                         + "</NombreReceptor>"+
	         "<TipoIdentificacionReceptor>" + respuestaHacienda.getTipoIdentificacionReceptor() +  "</TipoIdentificacionReceptor>" + 
	         "<NumeroCedulaReceptor>" + respuestaHacienda.getNumeroCedulaReceptor()             + "</NumeroCedulaReceptor>"+
	         "<Mensaje>" + respuestaHacienda.getMensaje()                                       + "</Mensaje>" + 
	         "<DetalleMensaje>" + respuestaHacienda.getDetalleMensaje()                         + "</DetalleMensaje>" + 
	         "<MontoTotalImpuesto>" +respuestaHacienda.getMontoTotalImpuesto()                  + "</MontoTotalImpuesto>" +
	         "<TotalFactura>" + respuestaHacienda.getTotalFactura()                             + "</TotalFactura>" +
	         "</MensajeHacienda>";     
			
		} catch (Exception e) {
			log.info("** Error  findbyAcceso: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
		return xml;
	}

}

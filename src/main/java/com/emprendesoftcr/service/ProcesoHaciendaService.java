package com.emprendesoftcr.service;

public interface ProcesoHaciendaService {
	

	
	//Hacienda Firmado de Documento XML y Envio hacia hacienda
	void taskHaciendaEnvio()throws Exception;
	
	
	//Verificar Documentos firmados
	void taskHaciendaComprobacionDocumentos()throws Exception;
	
	
	//Envios de correos Electronicos de los documentos aceptados por Hacienda
	void taskHaciendaEnvioDeCorreos()throws Exception;
	

	
}

package com.emprendesoftcr.service;

import java.util.ArrayList;

import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;

public interface ProcesoHaciendaService {
	

	
	//Hacienda Firmado de Documento XML y Envio hacia hacienda
	void taskHaciendaEnvio()throws Exception;
	
	
	//Verificar Documentos firmados
	void taskHaciendaComprobacionDocumentos()throws Exception;
	
	
	//Envios de correos Electronicos de los documentos aceptados por Hacienda
	void taskHaciendaEnvioDeCorreos()throws Exception;
	
//Enviar aceptar el documento hacia hacienda
	void aceptarDocumento(Hacienda hacienda) throws Exception;
	
	//Enviar documento hacienda
	 void envioHacienda(Hacienda hacienda) throws Exception;
	 
	 void enviarCorreos(Factura factura, Hacienda hacienda,ArrayList<String> listaCorreos) throws Exception;
	 
	 //Proceso de firmar las facturas  y crear el registro de hacienda
	 void procesoFirmado()throws Exception;
}

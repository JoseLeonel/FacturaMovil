package com.emprendesoftcr.service;

import java.util.ArrayList;

import com.emprendesoftcr.fisco.OpenIDConnectHacienda;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.RecepcionFactura;

public interface ProcesoHaciendaService {

	// Hacienda Firmado de Documento XML y Envio hacia hacienda
	void taskHaciendaEnvio() throws Exception;

	// Verificar Documentos firmados
	void taskHaciendaComprobacionDocumentos() throws Exception;

	// Envios de correos Electronicos de los documentos aceptados por Hacienda
	void taskHaciendaEnvioDeCorreos() throws Exception;

	// Enviar aceptar el documento hacia hacienda
	OpenIDConnectHacienda aceptarDocumento(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception;

	// Enviar documento hacienda
	OpenIDConnectHacienda envioHacienda(Hacienda hacienda, OpenIDConnectHacienda openIDConnectHacienda) throws Exception;

	void enviarCorreos(Factura factura, Hacienda hacienda, ArrayList<String> listaCorreos) throws Exception;

	void enviarCorreosRecepcion(RecepcionFactura recepcionFactura, Hacienda hacienda, ArrayList<String> listaCorreos) throws Exception;

	// Proceso de firmar las facturas y crear el registro de hacienda
	void procesoFirmado() throws Exception;

	// Proceso para firmar las facturas recibidas y aceptadas en el sistema
	void procesoFirmadoRecepcionFactura() throws Exception;

	Boolean verificaRecepcionFactura(Empresa empresa, String clave) throws Exception;

	
}

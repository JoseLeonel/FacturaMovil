package com.factura.FacturaElectronica.Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.xml.FacturaElectronica;

public class GenerarFacturaXML {

	/**
	 * Generar el XML de la Factura Electronica
	 * @param factura
	 */
	void generarXML(Factura factura) {

		try {
			// necesito hacer un contexto se crea la instancia
			JAXBContext context = JAXBContext.newInstance(FacturaElectronica.class);
			
			//Encabezado Factura 
			
			
			

			// Escribir el xml se utiliza el objete marshaller
			Marshaller marshaller = context.createMarshaller();

		} catch (Exception e) {
		}

	}

	private FacturaElectronica crearFacturaElectronica(Factura factura) {
		FacturaElectronica facturaElectronica =new FacturaElectronica();
		
		//código QR
		facturaElectronica.setClave(Constantes.BLANK);
		// Numero consecutivo de la factura
		facturaElectronica.setNumeroConsecutivo(factura.getNumeroConsecutivo());
		//Fecha Emision
		facturaElectronica.setFechaEmision(Utils.formatoXMLGregorianCalendar(factura.getFechaEmision()));
    
		
		//Encabezado
		
		
		
		
		return null;
	}
	
	
}

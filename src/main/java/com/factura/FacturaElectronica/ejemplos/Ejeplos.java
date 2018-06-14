package com.factura.FacturaElectronica.ejemplos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.factura.FacturaElectronica.xml.FacturaElectronica;

public class Ejeplos {

	public static void main(String[] args) throws JAXBException, IOException {
		File file = new File("ejemplo.xml");
		// necesito hacer un contexto se crea la instancia
		JAXBContext context = JAXBContext.newInstance(FacturaElectronica.class);

		// Escribir el xml se utiliza el objete marshaller
		Marshaller marshaller = context.createMarshaller();

		FacturaElectronica facturaElectronica = new FacturaElectronica();
		facturaElectronica.setNumeroConsecutivo("0012");
    //tabular el documento xml 
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		//salida
		marshaller.marshal(facturaElectronica, System.out);
		
		marshaller.marshal(facturaElectronica, new FileWriter("leo.xml"));
	}

}

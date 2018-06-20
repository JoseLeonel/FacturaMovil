package com.factura.FacturaElectronica.ejemplos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;

import com.factura.FacturaElectronica.Bo.FacturaBo;
import com.factura.FacturaElectronica.Utils.GenerarFacturaXML;
import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.xml.FacturaElectronica;

public class Ejeplos {

	@Autowired
	private FacturaBo facturaBo;

	public static void main(String[] args) throws JAXBException, IOException {
		File file = new File("ejemplo.xml");
	
	}

}

package com.emprendesoftcr.ejemplos;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.emprendesoftcr.Bo.FacturaBo;

public class Ejeplos {

	@Autowired
	private FacturaBo facturaBo;

	public static void main(String[] args) throws JAXBException, IOException {
		File file = new File("ejemplo.xml");
	
	}

}

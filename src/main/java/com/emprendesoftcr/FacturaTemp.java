package com.emprendesoftcr;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name="FacturaElectronica",namespace="https://cdn.comprobanteselectronicos.go.cr/xml-schemas/v4.3/facturaElectronica")
public class FacturaTemp {
  
	private String clave;

	@javax.xml.bind.annotation.XmlElement(name="Clave")
	public String getClave() {
		return clave;
	}

	
	public void setClave(String clave) {
		this.clave = clave;
	}

  
  
	
	
}

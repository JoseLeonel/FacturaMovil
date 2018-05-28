package com.factura.FacturaElectronica.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.factura.FacturaElectronica.Bo.ClienteBo;
import com.factura.FacturaElectronica.modelo.Cliente;

@Component
public class ClientePropertyEditor extends PropertyEditorSupport {

	@Autowired
	ClienteBo clienteBo;

	@Override
	public String getAsText() {
		Cliente cliente = (Cliente) getValue();
		return (cliente == null ? "" : cliente.getId().toString());
	}

	@Override
	public void setAsText(String idCliente) throws IllegalArgumentException {
		if ((idCliente == null) || !StringUtils.hasLength(idCliente)) {
			setValue(null);
		} else {

			setValue(clienteBo.buscar(Integer.parseInt(idCliente)));

		}
	}

	
	public ClienteBo getClienteBo() {
		return clienteBo;
	}

	
	public void setClienteBo(ClienteBo clienteBo) {
		this.clienteBo = clienteBo;
	}
	
	
}

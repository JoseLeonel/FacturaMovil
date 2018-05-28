package com.factura.FacturaElectronica.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.factura.FacturaElectronica.Bo.VendedorBo;
import com.factura.FacturaElectronica.modelo.Cliente;

@Component
public class VendedorPropertyEditor extends PropertyEditorSupport {

	@Autowired
	VendedorBo vendedorBo;

	@Override
	public String getAsText() {
		Cliente cliente = (Cliente) getValue();
		return (cliente == null ? "" : cliente.getId().toString());
	}

	@Override
	public void setAsText(String idVendedor) throws IllegalArgumentException {
		if ((idVendedor == null) || !StringUtils.hasLength(idVendedor)) {
			setValue(null);
		} else {

			setValue(vendedorBo.buscar(Integer.parseInt(idVendedor)));

		}
	}

	public VendedorBo getVendedorBo() {
		return vendedorBo;
	}

	public void setVendedorBo(VendedorBo vendedorBo) {
		this.vendedorBo = vendedorBo;
	}

}

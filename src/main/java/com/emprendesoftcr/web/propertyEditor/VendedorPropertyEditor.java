package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.VendedorBo;
import com.emprendesoftcr.modelo.Vendedor;

@Component
public class VendedorPropertyEditor extends PropertyEditorSupport {

	@Autowired
	VendedorBo vendedorBo;

	@Override
	public String getAsText() {
		Vendedor vendedor = (Vendedor) getValue();
		return (vendedor == null ? "" : vendedor.getId().toString());
	}

	@Override
	public void setAsText(String idVendedor) throws IllegalArgumentException {
		if ((idVendedor == null) || !StringUtils.hasLength(idVendedor)) {
			setValue(null);
		} else {

			setValue(vendedorBo.buscar(Long.parseLong(idVendedor)));

		}
	}

	public VendedorBo getVendedorBo() {
		return vendedorBo;
	}

	public void setVendedorBo(VendedorBo vendedorBo) {
		this.vendedorBo = vendedorBo;
	}

}

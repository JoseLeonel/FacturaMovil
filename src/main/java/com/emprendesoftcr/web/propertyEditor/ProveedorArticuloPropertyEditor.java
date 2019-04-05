package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.ProveedorArticuloBo;
import com.emprendesoftcr.modelo.ProveedorArticulo;

@Component
public class ProveedorArticuloPropertyEditor extends PropertyEditorSupport {

	@Autowired
	ProveedorArticuloBo proveedorArticuloBo;

	@Override
	public String getAsText() {
		ProveedorArticulo proveedorArticulo = (ProveedorArticulo) getValue();
		return (proveedorArticulo == null ? "" : proveedorArticulo.getId().toString());
	}

	@Override
	public void setAsText(String idProveedorArticulo) throws IllegalArgumentException {
		if ((idProveedorArticulo == null) || !StringUtils.hasLength(idProveedorArticulo)) {
			setValue(null);
		} else {

			setValue(proveedorArticuloBo.findById(Long.parseLong(idProveedorArticulo)));

		}
	}

	
	public ProveedorArticuloBo getProveedorArticuloBo() {
		return proveedorArticuloBo;
	}

	
	public void setProveedorArticuloBo(ProveedorArticuloBo proveedorArticuloBo) {
		this.proveedorArticuloBo = proveedorArticuloBo;
	}


}

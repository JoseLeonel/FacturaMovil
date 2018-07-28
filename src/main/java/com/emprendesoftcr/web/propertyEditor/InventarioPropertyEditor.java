package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.InventarioBo;
import com.emprendesoftcr.modelo.Articulo;

@Component
public class InventarioPropertyEditor extends PropertyEditorSupport {

	@Autowired
	InventarioBo inventarioBo;

	@Override
	public String getAsText() {
		Articulo articulo = (Articulo) getValue();
		return (articulo == null ? "" : articulo.getId().toString());
	}

	@Override
	public void setAsText(String idInventario) throws IllegalArgumentException {
		if ((idInventario == null) || !StringUtils.hasLength(idInventario)) {
			setValue(null);
		} else {

			setValue(inventarioBo.buscar(Long.parseLong(idInventario)));

		}
	}

	public InventarioBo getInventarioBo() {
		return inventarioBo;
	}

	public void setInventarioBo(InventarioBo inventarioBo) {
		this.inventarioBo = inventarioBo;
	}

}

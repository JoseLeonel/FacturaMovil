package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.MarcaBo;
import com.emprendesoftcr.modelo.Marca;

@Component
public class MarcaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	MarcaBo marcaBo;

	@Override
	public String getAsText() {
		Marca marca = (Marca) getValue();
		return (marca == null ? "" : marca.getId().toString());
	}

	@Override
	public void setAsText(String idMarca) throws IllegalArgumentException {
		if ((idMarca == null) || !StringUtils.hasLength(idMarca)) {
			setValue(null);
		} else {

			setValue(marcaBo.buscar(Long.parseLong(idMarca)));

		}
	}

	public MarcaBo getMarcaBo() {
		return marcaBo;
	}

	public void setMarcaBo(MarcaBo marcaBo) {
		this.marcaBo = marcaBo;
	}

}

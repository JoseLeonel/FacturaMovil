package com.factura.FacturaElectronica.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.factura.FacturaElectronica.Bo.MarcaBo;
import com.factura.FacturaElectronica.modelo.Marca;

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

			setValue(marcaBo.buscar(Integer.parseInt(idMarca)));

		}
	}

	public MarcaBo getMarcaBo() {
		return marcaBo;
	}

	public void setMarcaBo(MarcaBo marcaBo) {
		this.marcaBo = marcaBo;
	}

}

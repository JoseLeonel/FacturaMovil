package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.MesaBo;
import com.emprendesoftcr.modelo.Mesa;

/**
 * UsuarioPropertyEditor. Objetivo Obtener el id en el modiifcar
 * @author Leonel Hernandez Chaverri.
 * @since 6 feb. 2018
 */
@Component
public class MesaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	MesaBo mesaBo;
 
	@Override
	public String getAsText() {
		Mesa mesa = (Mesa) getValue();
		return (mesa == null ? "" : mesa.getId().toString());
	}
	

	@Override
	public void setAsText(String idMesa) throws IllegalArgumentException {
		if ((idMesa == null) || !StringUtils.hasLength(idMesa)) {
			setValue(null);
		} else {

			setValue(mesaBo.buscar(Long.parseLong(idMesa)));

		}
	}

}

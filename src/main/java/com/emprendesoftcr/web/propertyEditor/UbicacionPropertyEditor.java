package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.UbicacionBo;
import com.emprendesoftcr.modelo.Ubicacion;

@Component
public class UbicacionPropertyEditor extends PropertyEditorSupport {

	@Autowired
	UbicacionBo ubicacionBo;

	@Override
	public String getAsText() {
		Ubicacion ubicacion = (Ubicacion) getValue();
		return (ubicacion == null ? "" : ubicacion.getId().toString());
	}

	@Override
	public void setAsText(String idUbicacion) throws IllegalArgumentException {
		if ((idUbicacion == null) || !StringUtils.hasLength(idUbicacion)) {
			setValue(null);
		} else {

			setValue(ubicacionBo.buscar(Long.parseLong(idUbicacion)));

		}
	}

	
	public UbicacionBo getUbicacionBo() {
		return ubicacionBo;
	}

	
	public void setUbicacionBo(UbicacionBo ubicacionBo) {
		this.ubicacionBo = ubicacionBo;
	}

	

}

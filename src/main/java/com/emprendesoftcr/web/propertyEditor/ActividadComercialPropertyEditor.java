package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.ActividadComercialBo;
import com.emprendesoftcr.modelo.ActividadComercial;

@Component
public class ActividadComercialPropertyEditor extends PropertyEditorSupport {

	@Autowired
	ActividadComercialBo actividadComercialBo;

	@Override
	public String getAsText() {
		ActividadComercial actividadComercial = (ActividadComercial) getValue();
		return (actividadComercial == null ? "" : actividadComercial.getId().toString());
	}

	@Override
	public void setAsText(String idActividadComercial) throws IllegalArgumentException {
		if ((idActividadComercial == null) || !StringUtils.hasLength(idActividadComercial)) {
			setValue(null);
		} else {

			setValue(actividadComercialBo.buscar(Integer.parseInt(idActividadComercial)));

		}
	}

	public ActividadComercialBo getActividadComercialBo() {
		return actividadComercialBo;
	}

	public void setActividadComercialBo(ActividadComercialBo actividadComercialBo) {
		this.actividadComercialBo = actividadComercialBo;
	}

}

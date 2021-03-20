package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.CabysBo;
import com.emprendesoftcr.modelo.Cabys;

@Component
public class CabysPropertyEditor extends PropertyEditorSupport {

	@Autowired
	CabysBo cabysBo;

	@Override
	public String getAsText() {
		Cabys cabys = (Cabys) getValue();
		return (cabys == null ? "" : cabys.getId().toString());
	}

	@Override
	public void setAsText(String idCabys) throws IllegalArgumentException {
		if ((idCabys == null) || !StringUtils.hasLength(idCabys)) {
			setValue(null);
		} else {

			setValue(cabysBo.buscar(Long.parseLong(idCabys)));

		}
	}

	
	public CabysBo getCabysBo() {
		return cabysBo;
	}

	
	public void setCabysBo(CabysBo cabysBo) {
		this.cabysBo = cabysBo;
	}

	
	
}

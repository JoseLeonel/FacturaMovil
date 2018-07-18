package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.AbonoBo;
import com.emprendesoftcr.modelo.Abono;

@Component
public class AbonoPropertyEditor extends PropertyEditorSupport {

	@Autowired
	AbonoBo abonoBo;

	@Override
	public String getAsText() {
		Abono abono = (Abono) getValue();
		return (abono == null ? "" : abono.getId().toString());
	}

	@Override
	public void setAsText(String idAbono) throws IllegalArgumentException {
		if ((idAbono == null) || !StringUtils.hasLength(idAbono)) {
			setValue(null);
		} else {

			setValue(abonoBo.buscar(Integer.parseInt(idAbono)));

		}
	}

	public AbonoBo getAbonoBo() {
		return abonoBo;
	}

	public void setAbonoBo(AbonoBo abonoBo) {
		this.abonoBo = abonoBo;
	}

}

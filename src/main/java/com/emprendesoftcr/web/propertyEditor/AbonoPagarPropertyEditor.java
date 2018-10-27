package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.AbonoPagarBo;
import com.emprendesoftcr.modelo.AbonoPagar;

@Component
public class AbonoPagarPropertyEditor extends PropertyEditorSupport {

	@Autowired
	AbonoPagarBo abonoPagarBo;

	@Override
	public String getAsText() {
		AbonoPagar abonoPagar = (AbonoPagar) getValue();
		return (abonoPagar == null ? "" : abonoPagar.getId().toString());
	}

	@Override
	public void setAsText(String idAbono) throws IllegalArgumentException {
		if ((idAbono == null) || !StringUtils.hasLength(idAbono)) {
			setValue(null);
		} else {

			setValue(abonoPagarBo.buscar(Long.parseLong(idAbono)));

		}
	}

	
	public AbonoPagarBo getAbonoPagarBo() {
		return abonoPagarBo;
	}

	
	public void setAbonoPagarBo(AbonoPagarBo abonoPagarBo) {
		this.abonoPagarBo = abonoPagarBo;
	}



}

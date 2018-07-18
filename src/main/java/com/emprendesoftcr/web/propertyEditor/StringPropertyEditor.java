package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class StringPropertyEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		return getValue().toString();
	}

	@Override
	public void setAsText(String texto) throws IllegalArgumentException {
		if ((texto == null) || !StringUtils.hasLength(texto)) {
			setValue(texto);
		} else {
			setValue(texto.trim());
		}
	}

}
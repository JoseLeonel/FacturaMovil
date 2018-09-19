package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.fisco.FacturaElectronicaUtils;

@Component
public class FechaPropertyEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		return FacturaElectronicaUtils.toISO8601String((Date) getValue());
	}

	@Override
	public void setAsText(String texto) throws IllegalArgumentException {
		if ((texto == null) || !StringUtils.hasLength(texto)) {
			setValue(texto);
		} else {
			try {
				setValue(FacturaElectronicaUtils.parseStringtoISO8601Date(texto));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
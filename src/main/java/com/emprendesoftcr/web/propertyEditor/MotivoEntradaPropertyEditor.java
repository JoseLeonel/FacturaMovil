package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.MotivoEntradaBo;
import com.emprendesoftcr.modelo.MotivoEntrada;

@Component
public class MotivoEntradaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	MotivoEntradaBo motivoEntradaBo;

	@Override
	public String getAsText() {
		MotivoEntrada motivoEntrada = (MotivoEntrada) getValue();
		return (motivoEntrada == null ? "" : motivoEntrada.getId().toString());
	}

	@Override
	public void setAsText(String idMotivoEntrada) throws IllegalArgumentException {
		if ((idMotivoEntrada == null) || !StringUtils.hasLength(idMotivoEntrada)) {
			setValue(null);
		} else {

			setValue(motivoEntradaBo.buscar(Integer.parseInt(idMotivoEntrada)));

		}
	}

	public MotivoEntradaBo getMotivoEntradaBo() {
		return motivoEntradaBo;
	}

	public void setMotivoEntradaBo(MotivoEntradaBo motivoEntradaBo) {
		this.motivoEntradaBo = motivoEntradaBo;
	}

}

package com.emprendesoftcr.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.MotivoSalidaBo;
import com.emprendesoftcr.modelo.MotivoSalida;

@Component
public class MotivoSalidaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	MotivoSalidaBo motivoSalidaBo;

	@Override
	public String getAsText() {
		MotivoSalida motivoSalida = (MotivoSalida) getValue();
		return (motivoSalida == null ? "" : motivoSalida.getId().toString());
	}

	@Override
	public void setAsText(String idMotivoSalida) throws IllegalArgumentException {
		if ((idMotivoSalida == null) || !StringUtils.hasLength(idMotivoSalida)) {
			setValue(null);
		} else {

			setValue(motivoSalidaBo.buscar(Integer.parseInt(idMotivoSalida)));

		}
	}

	public MotivoSalidaBo getMotivoSalidaBo() {
		return motivoSalidaBo;
	}

	public void setMotivoSalidaBo(MotivoSalidaBo motivoSalidaBo) {
		this.motivoSalidaBo = motivoSalidaBo;
	}

}

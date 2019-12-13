package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.TarifaBo;
import com.emprendesoftcr.modelo.Tarifa;

@Component
public class TarifaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	TarifaBo tarifaBo;

	@Override
	public String getAsText() {
		Tarifa tarifa = (Tarifa) getValue();
		return (tarifa == null ? "" : tarifa.getId().toString());
	}

	@Override
	public void setAsText(String idTarifa) throws IllegalArgumentException {
		if ((idTarifa == null) || !StringUtils.hasLength(idTarifa)) {
			setValue(null);
		} else {

			setValue(tarifaBo.buscar(Long.parseLong(idTarifa)));

		}
	}

	public TarifaBo getTarifaBo() {
		return tarifaBo;
	}

	public void setTarifaBo(TarifaBo tarifaBo) {
		this.tarifaBo = tarifaBo;
	}

}

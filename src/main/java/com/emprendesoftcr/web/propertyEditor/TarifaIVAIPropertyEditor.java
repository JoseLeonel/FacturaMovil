package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.TarifaIVAIBo;
import com.emprendesoftcr.modelo.TarifaIVAI;

@Component
public class TarifaIVAIPropertyEditor extends PropertyEditorSupport {

	@Autowired
	TarifaIVAIBo tarifaIVAIBo;

	@Override
	public String getAsText() {
		TarifaIVAI tarifaIVAI = (TarifaIVAI) getValue();
		return (tarifaIVAI == null ? "" : tarifaIVAI.getId().toString());
	}

	@Override
	public void setAsText(String idTarifaIVAI) throws IllegalArgumentException {
		if ((idTarifaIVAI == null) || !StringUtils.hasLength(idTarifaIVAI)) {
			setValue(null);
		} else {

			setValue(tarifaIVAIBo.buscar(Integer.parseInt(idTarifaIVAI)));

		}
	}

	
	public TarifaIVAIBo getTarifaIVAIBo() {
		return tarifaIVAIBo;
	}

	
	public void setTarifaIVAIBo(TarifaIVAIBo tarifaIVAIBo) {
		this.tarifaIVAIBo = tarifaIVAIBo;
	}

	

}

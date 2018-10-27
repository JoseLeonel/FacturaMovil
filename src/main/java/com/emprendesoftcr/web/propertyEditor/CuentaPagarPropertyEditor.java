package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.CuentaPagarBo;
import com.emprendesoftcr.modelo.CuentaPagar;

@Component
public class CuentaPagarPropertyEditor extends PropertyEditorSupport {

	@Autowired
	CuentaPagarBo cuentaPagarBo;

	@Override
	public String getAsText() {
		CuentaPagar cuentaPagar = (CuentaPagar) getValue();
		return (cuentaPagar == null ? "" : cuentaPagar.getId().toString());
	}

	@Override
	public void setAsText(String idCobrar) throws IllegalArgumentException {
		if ((idCobrar == null) || !StringUtils.hasLength(idCobrar)) {
			setValue(null);
		} else {

			setValue(cuentaPagarBo.buscar(Long.parseLong(idCobrar)));

		}
	}

	public CuentaPagarBo getCuentaPagarBo() {
		return cuentaPagarBo;
	}

	public void setCuentaPagarBo(CuentaPagarBo cuentaPagarBo) {
		this.cuentaPagarBo = cuentaPagarBo;
	}

}

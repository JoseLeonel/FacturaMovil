package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.modelo.CuentaCobrar;

@Component
public class CuentaCobrarPropertyEditor extends PropertyEditorSupport {

	@Autowired
	CuentaCobrarBo cuentaCobrarBo;

	@Override
	public String getAsText() {
		CuentaCobrar cuentaCobrar = (CuentaCobrar) getValue();
		return (cuentaCobrar == null ? "" : cuentaCobrar.getId().toString());
	}

	@Override
	public void setAsText(String idCobrar) throws IllegalArgumentException {
		if ((idCobrar == null) || !StringUtils.hasLength(idCobrar)) {
			setValue(null);
		} else {

			setValue(cuentaCobrarBo.buscar(Long.parseLong(idCobrar)));

		}
	}

	
	public CuentaCobrarBo getCuentaCobrarBo() {
		return cuentaCobrarBo;
	}

	
	public void setCuentaCobrarBo(CuentaCobrarBo cuentaCobrarBo) {
		this.cuentaCobrarBo = cuentaCobrarBo;
	}

}

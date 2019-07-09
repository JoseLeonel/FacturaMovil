package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.EmpresaActividadComercialBo;
import com.emprendesoftcr.modelo.EmpresaActividadComercial;

@Component
public class EmpresaActividadComercialPropertyEditor extends PropertyEditorSupport {

	@Autowired
	EmpresaActividadComercialBo empresaActividadComercialBo;

	@Override
	public String getAsText() {
		EmpresaActividadComercial empresaActividadComercial = (EmpresaActividadComercial) getValue();
		return (empresaActividadComercial == null ? "" : empresaActividadComercial.getId().toString());
	}

	@Override
	public void setAsText(String idEmpresaActividadComercial) throws IllegalArgumentException {
		if ((idEmpresaActividadComercial == null) || !StringUtils.hasLength(idEmpresaActividadComercial)) {
			setValue(null);
		} else {

			setValue(empresaActividadComercialBo.buscar(Integer.parseInt(idEmpresaActividadComercial)));

		}
	}

	public EmpresaActividadComercialBo getEmpresaActividadComercialBo() {
		return empresaActividadComercialBo;
	}

	public void setEmpresaActividadComercialBo(EmpresaActividadComercialBo empresaActividadComercialBo) {
		this.empresaActividadComercialBo = empresaActividadComercialBo;
	}

}

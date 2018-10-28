package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.modelo.Empresa;


@Component
public class EmpresaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	EmpresaBo empresaBo;

	@Override
	public String getAsText() {
		Empresa empresa = (Empresa) getValue();
		return (empresa == null ? "" : empresa.getId().toString());
	}

	@Override
	public void setAsText(String idUsuario) throws IllegalArgumentException {
		if ((idUsuario == null) || !StringUtils.hasLength(idUsuario)) {
			setValue(null);
		} else {

			setValue(empresaBo.buscar(Integer.parseInt(idUsuario)));

		}
	}

	
	public EmpresaBo getEmpresaBo() {
		return empresaBo;
	}

	
	public void setEmpresaBo(EmpresaBo empresaBo) {
		this.empresaBo = empresaBo;
	}
	
	
}

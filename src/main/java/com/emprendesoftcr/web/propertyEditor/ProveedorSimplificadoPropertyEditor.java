package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.ProveedorSimplificadoBo;
import com.emprendesoftcr.modelo.ProveedorSimplificado;

@Component
public class ProveedorSimplificadoPropertyEditor extends PropertyEditorSupport {

	@Autowired
	ProveedorSimplificadoBo proveedorSimplificadoBo;

	@Override
	public String getAsText() {
		ProveedorSimplificado proveedorSimplificado = (ProveedorSimplificado) getValue();
		return (proveedorSimplificado == null ? "" : proveedorSimplificado.getId().toString());
	}

	@Override
	public void setAsText(String idProveedorSimplificado) throws IllegalArgumentException {
		if ((idProveedorSimplificado == null) || !StringUtils.hasLength(idProveedorSimplificado)) {
			setValue(null);
		} else {

			setValue(proveedorSimplificadoBo.buscar(Long.parseLong(idProveedorSimplificado)));

		}
	}

	public ProveedorSimplificadoBo getProveedorSimplificadoBo() {
		return proveedorSimplificadoBo;
	}

	public void setProveedorSimplificadoBo(ProveedorSimplificadoBo proveedorSimplificadoBo) {
		this.proveedorSimplificadoBo = proveedorSimplificadoBo;
	}

}

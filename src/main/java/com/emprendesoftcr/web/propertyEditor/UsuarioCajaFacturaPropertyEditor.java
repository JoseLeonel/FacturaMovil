package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.UsuarioCajaFacturaBo;
import com.emprendesoftcr.modelo.UsuarioCajaFactura;


@Component
public class UsuarioCajaFacturaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	UsuarioCajaFacturaBo usuarioCajaFacturaBo;

	@Override
	public String getAsText() {
		UsuarioCajaFactura usuarioCajaFactura = (UsuarioCajaFactura) getValue();
		return (usuarioCajaFactura == null ? "" : usuarioCajaFactura.getId().toString());
	}

	@Override
	public void setAsText(String idUsuarioCajaFactura) throws IllegalArgumentException {
		if ((idUsuarioCajaFactura == null) || !StringUtils.hasLength(idUsuarioCajaFactura)) {
			setValue(null);
		} else {

			setValue(usuarioCajaFacturaBo.findById(Long.parseLong(idUsuarioCajaFactura)));

		}
	}

	
	public UsuarioCajaFacturaBo getUsuarioCajaFacturaBo() {
		return usuarioCajaFacturaBo;
	}

	
	public void setUsuarioCajaFacturaBo(UsuarioCajaFacturaBo usuarioCajaFacturaBo) {
		this.usuarioCajaFacturaBo = usuarioCajaFacturaBo;
	}

	

	
	
	
}

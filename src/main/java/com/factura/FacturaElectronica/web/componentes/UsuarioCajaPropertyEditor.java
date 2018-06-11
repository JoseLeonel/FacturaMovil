package com.factura.FacturaElectronica.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.factura.FacturaElectronica.Bo.UsuarioCajaBo;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;

/**
 * UsuarioPropertyEditor. Objetivo Obtener el id en el modiifcar
 * @author Leonel Hernandez Chaverri.
 * @since 6 feb. 2018
 */
@Component
public class UsuarioCajaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	UsuarioCajaBo usuarioCajaBo;

	@Override
	public String getAsText() {
		UsuarioCaja usuarioCaja = (UsuarioCaja) getValue();
		return (usuarioCaja == null ? "" : usuarioCaja.getId().toString());
	}

	@Override
	public void setAsText(String idUsuarioCaja) throws IllegalArgumentException {
		if ((idUsuarioCaja == null) || !StringUtils.hasLength(idUsuarioCaja)) {
			setValue(null);
		} else {

			setValue(usuarioCajaBo.buscar(Integer.parseInt(idUsuarioCaja)));

		}
	}

	
	public UsuarioCajaBo getUsuarioCajaBo() {
		return usuarioCajaBo;
	}

	
	public void setUsuarioCajaBo(UsuarioCajaBo usuarioCajaBo) {
		this.usuarioCajaBo = usuarioCajaBo;
	}

	
	
	
}

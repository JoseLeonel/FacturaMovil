package com.factura.FacturaElectronica.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.factura.FacturaElectronica.Bo.CajaBo;
import com.factura.FacturaElectronica.modelo.Caja;

/**
 * UsuarioPropertyEditor. Objetivo Obtener el id en el modiifcar
 * @author Leonel Hernandez Chaverri.
 * @since 6 feb. 2018
 */
@Component
public class CajaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	CajaBo cajaBo;

	@Override
	public String getAsText() {
		Caja caja = (Caja) getValue();
		return (caja == null ? "" : caja.getId().toString());
	}

	@Override
	public void setAsText(String idCaja) throws IllegalArgumentException {
		if ((idCaja == null) || !StringUtils.hasLength(idCaja)) {
			setValue(null);
		} else {

			setValue(cajaBo.buscar(Integer.parseInt(idCaja)));

		}
	}

	public CajaBo getCajaBo() {
		return cajaBo;
	}

	public void setCajaBo(CajaBo cajaBo) {
		this.cajaBo = cajaBo;
	}

}

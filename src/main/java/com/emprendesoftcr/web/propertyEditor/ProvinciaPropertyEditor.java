package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.CajaBo;
import com.emprendesoftcr.Bo.ProvinciaBo;
import com.emprendesoftcr.modelo.Caja;

/**
 * 
 * ProvinciaPropertyEditor.
 * @author jose.
 * @since 7 jul. 2018
 */
@Component
public class ProvinciaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	ProvinciaBo provinciaBo;

	@Override
	public String getAsText() {
		Caja caja = (Caja) getValue();
		return (caja == null ? "" : caja.getId().toString());
	}

	@Override
	public void setAsText(String idProvincia) throws IllegalArgumentException {
		if ((idProvincia == null) || !StringUtils.hasLength(idProvincia)) {
			setValue(null);
		} else {

			setValue(provinciaBo.findByCodigo(Integer.parseInt(idProvincia)));

		}
	}

	
	public ProvinciaBo getProvinciaBo() {
		return provinciaBo;
	}

	
	public void setProvinciaBo(ProvinciaBo provinciaBo) {
		this.provinciaBo = provinciaBo;
	}

	

}

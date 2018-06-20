package com.factura.FacturaElectronica.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Factura;

@Component
public class FacturaFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return Factura.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "condicionVenta", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoDoc", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		

	}

}

package com.emprendesoftcr.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.utils.Constantes;

@Component
public class CompraSimplificadaFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return CompraSimplificada.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "condicionVenta", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoDoc", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		

	}

}

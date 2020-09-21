package com.emprendesoftcr.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.utils.Constantes;

@Component
public class ValidarRolFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return Factura.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuarioSistema", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "claveSistema", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		

	}

}

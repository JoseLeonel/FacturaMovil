package com.emprendesoftcr.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Cliente;

@Component
public class ValidarClienteFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return Cliente.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cedula", Constantes.KEY_REQUERIDO);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreCompleto", Constantes.KEY_REQUERIDO);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correoElectronico", Constantes.KEY_REQUERIDO);
		Utils.rejectIfNotValidEmail(errors,"correoElectronico");

	}

}

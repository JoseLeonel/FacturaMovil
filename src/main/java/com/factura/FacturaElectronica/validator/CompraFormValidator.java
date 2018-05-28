package com.factura.FacturaElectronica.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Compra;

@Component
public class CompraFormValidator implements Validator {

	

	@Override
	public boolean supports(Class<?> clase) {
		return Compra.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "consecutivo", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formaPago", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoDocumento", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_REQUERIDO));
		AbstractValidationUtils.rejectIfNonNumeric(errors, "totalImpuesto", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_NO_NUMERICO));
		AbstractValidationUtils.rejectIfNonNumeric(errors, "totalDescuento", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_NO_NUMERICO));
		AbstractValidationUtils.rejectIfNonNumeric(errors, "subTotal", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_NO_NUMERICO));
		AbstractValidationUtils.rejectIfNonNumeric(errors, "totalCompra", Constantes.RESOURCE_BUNDLE.getString(Constantes.KEY_NO_NUMERICO));
		
	}

	
	

	
	
	
	

}

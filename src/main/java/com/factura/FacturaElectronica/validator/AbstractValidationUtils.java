package com.factura.FacturaElectronica.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.factura.FacturaElectronica.Utils.Constantes;

/**
 * Validador para los formularios de la aplicación.
 * @author David Alvarez Guerrero
 * @since Jan 20, 2007
 */
public abstract class AbstractValidationUtils extends ValidationUtils {

	public static boolean equals(String lValue, String rValue) {
		char[] charsToReplace = { 'A', 'E', 'I', 'O', 'U', 'N', 'U' };  // FIXME Arreglar esta con caracteres unicode
		char[] charsToUser = { 'A', 'E', 'I', 'O', 'U', 'N', 'U' };
		if (lValue == null) {
			lValue = "";
		}
		if (rValue == null) {
			rValue = "";
		}
		lValue = lValue.toUpperCase();
		rValue = rValue.toUpperCase();
		for (Integer i = 0; i < charsToReplace.length; i++) {
			lValue = lValue.replace(charsToReplace[i], charsToUser[i]);
			rValue = rValue.replace(charsToReplace[i], charsToUser[i]);
		}
		return lValue.equals(rValue);
	}

	/**
	 * Este método valida que el campo en cuestión esté presente (porque es requerido) y que sea de la longitud indicada. Este método funciona si el campo es de tipo numerico (entero) o de tipo string, pues según los property editors, si un numero viene vacío lo interpreta como un 0, en cuyo caso este método lo reportará como un error. El único inconveniente, que es el caso donde hay que tener cuidado, sería que este metodo va a reportar error para un campo de tipo string de longitud 1 y que venga con valor 0.
	 * @param errors El objeto errors para reportar los errores.
	 * @param campo El campo que se va a validar.
	 * @param longitud La longitud que debe tener el valor del campo.
	 * @param mensajeRequerido El mensaje que se muestra cuando el campo no tiene valor.
	 * @param mensajeLongitud El mensaje que se muestra cuando el campo tiene valor, pero la longitud no es la indicada. Este método envía como parametro a este mensaje la longitud, así que se espera que el mensaje sea algo como "La propiedad X debe ser de longitud {0}."
	 */
	public static void rejectRequiredIfLengthIsNot(Errors errors, String campo, Integer longitud, String mensajeRequerido, String mensajeLongitud) {
		Object objeto = errors.getFieldValue(campo);
		if (objeto == null || StringUtils.isEmpty(objeto.toString()) || "0".equals(objeto.toString())) {
			errors.rejectValue(campo, mensajeRequerido);
		} else if (longitud != objeto.toString().length()) {
			errors.rejectValue(campo, mensajeLongitud, new Object[] { longitud }, mensajeLongitud);
		}
	}

	/**
	 * @param errors
	 * @param campo
	 * @param mensajeRequerido El mensaje que se muestra cuando el campo no tiene valor.
	 */
	/**
	 * Este método valida el campo en cuestión si este está presente (porque no es requerido). Este método funciona si el campo es de tipo numerico (entero) o de tipo string, pues según los property editors, si un numero viene vacío lo interpreta como un 0, en cuyo caso este método no lo reportará como un error. El único inconveniente, que es el caso donde hay que tener cuidado, sería que este metodo no va va a reportar error para un campo de tipo string de longitud 1 y que venga con valor 0.
	 * @param errors El objeto errors para reportar los errores.
	 * @param campo El campo que se va a validar.
	 * @param longitud La longitud que debe tener el valor del campo.
	 * @param mensajeLongitud El mensaje que se muestra cuando el campo tiene valor, pero la longitud no es la indicada. Este método envía como parametro a este mensaje la longitud, así que se espera que el mensaje sea algo como "La propiedad X debe ser de longitud {0}."
	 */
	public static void rejectIfLengthIsNot(Errors errors, String campo, Integer longitud, String mensajeLongitud) {
		Object objeto = errors.getFieldValue(campo);
		if (!StringUtils.isEmpty(objeto.toString()) && !"0".equals(objeto.toString()) && longitud != objeto.toString().length()) {
			errors.rejectValue(campo, mensajeLongitud, new Object[] { longitud }, mensajeLongitud);
		}
	}

	/**
	 * Valida que la pripiedad sea solo numérica. Se asume que la propiedad no es requerida, entonces si viene nula no habría error.
	 * @param errors El objeto para poner los errores.
	 * @param campo El campo que se va a validar.
	 */
	public static void rejectIfNonNumeric(Errors errors, String campo, String mensaje) {
		if (errors.getFieldError(campo) != null && errors.getFieldError(campo).isBindingFailure()) {
			errors.rejectValue(campo, mensaje);
		}
	}

	/**
	 * Verifica que el campo que se envía contenga una dirección de email válida. Válida significa que tenga sintaxis correcta, no que exista. Este método supone que el email es requerido, así que si el valor es null reportará el error.
	 * @param errors El objeto con el contexto de binding.
	 * @param campo El campo que se supone tienen un email.
	 */
	public static void rejectIfNotValidEmailOrEmpty(Errors errors, String campo) {
		String email = (String) errors.getFieldValue(campo);
		if (StringUtils.isEmpty(email)) {
			errors.rejectValue(campo, "error.correo.erroneoBlanco");
		} else {
			Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*$");
			Matcher m = p.matcher(email);
			if (!m.matches() || email.indexOf('@') < 1) {
				errors.rejectValue(campo, "emailIncorrecto", new Object[] { email }, "El formato del correo electr\u00F3nico es inv\u00E1lido.");
			}
		}
	}

	/**
	 * Verifica que el campo que se envía contenga una dirección de email válida. Válida significa que tenga sintaxis correcta, no que exista. Este método supone que el email no es requerido, así que si el valor es null no reportará el error.
	 * @param errors El objeto con el contexto de binding.
	 * @param campo El campo que se supone tienen un email.
	 */
	public static void rejectIfNotValidEmail(Errors errors, String campo) {
		String email = (String) errors.getFieldValue(campo);
		if (!StringUtils.isEmpty(email)) {
			Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*$");
			Matcher m = p.matcher(email);
			if (email.indexOf('@') < 1 || !m.matches()) {
				errors.rejectValue(campo, "emailIncorrecto", new Object[] { email }, "");
			}
		}
	}
	
	/**
	 * Verifica que el campo que se env�a contenga una direcci�n de email v�lida. V�lida significa que tenga sintaxis correcta, no que exista. Este m�todo supone que el email es requerido, as� que si el valor es null reportar� el error.
	 * @param errors El objeto con el contexto de binding.
	 * @param campo El campo que se supone tienen un email.
	 */
	public static void rejectIfNotValidEmailOrEmpty(Errors errors, String campo, String mensaje) {
		String email = (String) errors.getFieldValue(campo);
		if (!StringUtils.isEmpty(email)) {
			errors.rejectValue(campo, "error.informacion.requerido.correo");
		} else {
			Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*$");
			Matcher m = p.matcher(email);
			if (!m.matches() || email.indexOf('@') < 1) {
				errors.rejectValue(campo, mensaje, new Object[] { email }, "");
			}
		}
	}

	/**
	 * Verifica que el campo que se envía contenga una dirección de email válida en SIMO. Válida significa que tenga sintaxis correcta, no que exista. Este método supone que el email no es requerido, así que si el valor es null no reportará el error.
	 * @param errors El objeto con el contexto de binding.
	 * @param campo El campo que se supone tienen un email.
	 */
	public static void rejectIfNotValidSimoUsuarioEmailOrEmpty(Errors errors, String campo) {
		String email = (String) errors.getFieldValue(campo);
		if (StringUtils.isEmpty(email)) {
			errors.rejectValue(campo, "requerido.correo");
		} else {
			Pattern p = Pattern.compile("[A-Za-z0-9]+");
			Matcher m = p.matcher(email);
			if (!m.matches()) {
				errors.rejectValue(campo, "usuarioEmailIncorrecto", new Object[] { email }, "");
			}
		}
	}

	/**
	 * Verifica que el campo que se envía contenga una dirección de email válida en SIMO. Válida significa que tenga sintaxis correcta, no que exista. Este método supone que el email no es requerido, así que si el valor es null reportará el error.
	 * @param errors El objeto con el contexto de binding.
	 * @param campo El campo que se supone tienen un email.
	 */
	public static void rejectIfNotValidSimoUsuarioEmail(Errors errors, String campo) {
		String email = (String) errors.getFieldValue(campo);
		if (!StringUtils.isEmpty(email)) {
			Pattern p = Pattern.compile("[A-Za-z0-9]+");
			Matcher m = p.matcher(email);
			if (!m.matches()) {
				errors.rejectValue(campo, "usuarioEmailIncorrecto", new Object[] { email }, "");
			}
		}
	}


	/**
	 * Valida que la longitud del campo se se encuentre en el rango especificado. Se asume que la propiedad es requerida (si no no estaría en el rango de la longitud), entonces si viene nula se reporta el error.
	 * @param errors El objeto para poner los errores.
	 * @param campo El campo que se va a validar.
	 * @param limiteInferior El límite inferior del rango de la longitud a validar.
	 * @param limiteSuperior El límite superior del rango de la longitud a validar.
	 * @param mensajeRequerido Mensaje que se utiliza cuando el campo viene vacio.
	 * @param mensajeFueraRango Mensaje que se utiliza cuando el campo esta fuera de rango.
	 */
	public static void rejectIfNotInLenghtRangeIsNotOrEmpty(Errors errors, String campo, Integer limiteInferior, Integer limiteSuperior, String mensajeRequerido, String mensajeFueraRango) {
		Object objeto = errors.getFieldValue(campo);
		if (objeto == null || StringUtils.isEmpty(objeto.toString())) {
			errors.rejectValue(campo, mensajeRequerido);
		}
		Integer valorInt = objeto.toString().length();
		if (!(valorInt >= limiteInferior) || !(valorInt <= limiteSuperior)) {
			errors.rejectValue(campo, mensajeFueraRango, new Object[] { limiteInferior, limiteSuperior }, mensajeFueraRango);
		}
	}

	/**
	 * Valida que el campo sea numérico asi como la longitud del campo se se encuentre en el rango especificado.
	 * @param errors El objeto para poner los errores.
	 * @param campo El campo que se va a validar.
	 * @param limiteInferior El límite inferior del rango de la longitud a validar.
	 * @param limiteSuperior El límite superior del rango de la longitud a validar.
	 * @param mensajeFueraRango Mensaje que se utiliza cuando el campo esta fuera de rango.
	 */
	public static void rejectIfIsNotOrNonNumericOrIfNotInLenghtRange(Errors errors, String campo, Integer limiteInferior, Integer limiteSuperior, String mensajeNoNumerico, String mensajeFueraRango) {
		Object objeto = errors.getFieldValue(campo);
		if (objeto != null && !StringUtils.isEmpty(objeto.toString())) {
			if (errors.getFieldError(campo) != null && errors.getFieldError(campo).isBindingFailure()) {
				errors.rejectValue(campo, mensajeNoNumerico);
			} else {
				Integer valorInt = objeto.toString().length();
				if (!(valorInt >= limiteInferior) || !(valorInt <= limiteSuperior)) {
					errors.rejectValue(campo, mensajeFueraRango, new Object[] { limiteInferior, limiteSuperior }, mensajeFueraRango);
				}
			}
		}
	}

	/**
	 * Valida que la pripiedad sea solo numérica y tenga la longitud especificada. Se asume que la propiedad es requerida (si no, no tendría el tamaño especificado, entonces si viene nula se reporta el error.
	 * @param errors El objeto para poner los errores.
	 * @param campo El campo que se va a validar.
	 * @param length La longitud que se va a validar el campo.
	 */
	public static void rejectIfLenghtIsNotOrNonNumericOrEmpty(Errors errors, String campo, Integer length, String mensajeRequerido, String mensajeNoNumerico) {
		if (errors.getFieldError(campo) != null && errors.getFieldError(campo).isBindingFailure()) {
			errors.rejectValue(campo, mensajeNoNumerico);
		} else {
			Object objeto = errors.getFieldValue(campo);
			if (objeto == null) {
				errors.rejectValue(campo, mensajeRequerido);
			} else if (objeto.toString().length() != length) {
				errors.rejectValue(campo, "longitudIncorrectaTelefono", new Object[] { length }, "longitudIncorrectaTelefono");
			}
		}
	}

	public static void rejectIfNotValidPhoneNumber(Errors errors, String campo, String mensajeRequerido, String mensajeFueraRango) {
		Object objeto = errors.getFieldValue(campo);
		if ((objeto == null) || StringUtils.isEmpty(objeto.toString().trim())) {
			errors.rejectValue(campo, mensajeRequerido);
			return;
		} else {
			String valor = objeto.toString();
			for (Integer i = 0; i < valor.length(); ++i) {
				if ((Character.isDigit(valor.charAt(i))) == false) {
					errors.rejectValue(campo, mensajeRequerido);
					return;
				}
			}
		}
		Integer digitos = objeto.toString().length();
		if (!digitos.equals(Constantes.LONGITUD_TELEFONO)) {
			errors.rejectValue(campo, mensajeFueraRango, new Object[] { Constantes.LONGITUD_TELEFONO }, mensajeFueraRango);
		}
	}

	public static void rejectIfNotValidCedulaFisica(Errors errors, String campo, String mensaje) {
		String cedula = (String) errors.getFieldValue(campo);
		if (!(cedula.matches(Constantes.PATRON_CEDULA_FISICA_NACIONALES) || cedula.matches(Constantes.PATRON_CEDULA_FISICA_OTROS))) {
			errors.rejectValue(campo, mensaje);
		}
	}
	
	public static void rejectIfNotValidCedulaJuridica(Errors errors, String campo, String mensaje) {
		String cedula = (String) errors.getFieldValue(campo);
		if (!(cedula.matches(Constantes.PATRON_CEDULA_JURIDICA_AUTONOMA) || cedula.matches(Constantes.PATRON_CEDULA_JURIDICA_GOBIERNO) || cedula.matches(Constantes.PATRON_CEDULA_JURIDICA_PERSONERIA))) {
			errors.rejectValue(campo, mensaje);
		}
	}

	public static void rejectIfNotValidCedulaOtra(Errors errors, String campo, String mensaje) {
		String cedula = (String) errors.getFieldValue(campo);
		if (!(cedula.matches(Constantes.PATRON_CEDULA_EXTRANJEROS_PASAPORTE) || cedula.matches(Constantes.PATRON_CEDULA_EXTRANJEROS_RESIDENCIA))) {
			errors.rejectValue(campo, mensaje);
		}
	}

	public static void rejectRequiredNumericWithFixedLength(Errors errors, String campo, Integer length) {
		Object objeto = errors.getFieldValue(campo);
		if ((objeto == null) || (objeto.toString().length() != length)) {
			errors.rejectValue(campo, Constantes.KEY_LONGITUD_INCORRECTA, new Object[] { length }, Constantes.MENSAJE_LONGITUD_INCORRECTA_GENERICO);
		} else if ((objeto != null) && (objeto.toString().length() == length)) {
			AbstractValidationUtils.rejectIfNonNumericOrEmpty(errors, campo);
		}
	}

	public static void rejectIfNonNumericOrEmpty(Errors errors, String campo) {
		Object objeto = errors.getFieldValue(campo);
		if ((objeto == null) || StringUtils.isEmpty(objeto.toString())) {
			errors.rejectValue(campo, Constantes.KEY_REQUERIDO);
		} else {
			String valor = objeto.toString();			
			if (!valor.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
				errors.rejectValue(campo, Constantes.KEY_NO_NUMERICO);
			}			
//			for (Integer i = 0; i < valor.length(); ++i) {
//				if ((Character.isDigit(valor.charAt(i))) == false) {
//					errors.rejectValue(campo, Constantes.KEY_NO_NUMERICO);
//					break;
//				}
//			}
		}
	}

	public static void rejectIfNonNumericOrEmptyOrZero(Errors errors, String campo) {
		Object objeto = errors.getFieldValue(campo);
		if ((objeto == null) || StringUtils.isEmpty(objeto.toString())) {
			errors.rejectValue(campo, Constantes.KEY_REQUERIDO);
		} else {
			String valor = objeto.toString();
			for (Integer i = 0; i < valor.length(); ++i) {
				if ((Character.isDigit(valor.charAt(i))) == false) {
					errors.rejectValue(campo, Constantes.KEY_NO_NUMERICO);
					break;
				}
			}
			if(!errors.hasErrors() && Integer.parseInt(valor) == 0){
				errors.rejectValue(campo, Constantes.KEY_REQUERIDO);
			}
		}
	}
	
	public static void rejectIfNoChoice(Errors errors, String campo, String mensaje){
		Object objeto = errors.getFieldValue(campo);
		if(objeto == null){
			errors.rejectValue(campo, mensaje);
		}
	}

}

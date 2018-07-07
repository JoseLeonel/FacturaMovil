package com.factura.FacturaElectronica.fisco;

import java.util.Calendar;

public final class FacturaElectronicaUtils {

	public static final Integer	CODIGO_PAIS														= 506;
	// Comprobante electronico para formar la clave
	public static final Integer	COMPROBANTE_ELECTRONICO_NORMAL				= 1;
	public static final Integer	COMPROBANTE_ELECTRONICO_CONTIGENCIA		= 2;
	public static final Integer	COMPROBANTE_ELECTRONICO_SIN_INTERNET	= 3;

	// Formatos
	public static final String	FORMATO_DIA														= "00";
	public static final String	FORMATO_MES														= "00";
	public static final String	FORMATO_ANO														= "0000";

	public static final String	FORMATO_CEDULA												= "000000000000";

	public static final String	FORMATO_CODIGO_SEGURIDAD							= "00000000";

	/**
	 * Clave de la Factura para tributacion directa
	 * Codigo del Pais :Los primeros tres dígitos corresponden al código del país (506). Del cuarto al quinto dígito, corresponde al día en que se genere el comprobante electrónico. Del sexto al séptimo dígito, corresponde al mes en que se genere el comprobante electrónico. Del octavo al noveno dígito, corresponde al año en que se genere el comprobante electrónico. Del décimo al vigésimo primero dígito, corresponde al número de cédula del emisor. Del vigésimo segundo al cuadragésimo primero dígito, corresponde a la numeración consecutiva del comprobante electrónico. El cuadragésimo segundo le corresponde a la situación del comprobante electrónico para el cual se debe de utilizar la siguiente codificación: 1= Normal 2=Contigencia 3= Sin Internet
	 * @param cedulaEmisor
	 * @param consecutivoFactura
	 * @param comprobanteElectronico
	 * @return
	 */
	public static String claveFactura(String cedulaEmisor, String consecutivoFactura, Integer comprobanteElectronico, Integer codigoSeguridad) {
		// Fecha actual desglosada:
		Calendar fecha = Calendar.getInstance();
		Integer ano = fecha.get(Calendar.YEAR);
		Integer mes = fecha.get(Calendar.MONTH)+1 ;
		Integer dia = fecha.get(Calendar.DAY_OF_MONTH);

		String primeroTress = FacturaElectronicaUtils.CODIGO_PAIS.toString().toString();
		String cuartoQuito = replazarConZeros(dia.toString(), FORMATO_DIA);
		String sextoAlSeptimo = replazarConZeros(mes.toString(), FORMATO_MES);
		String octavoNoveno = replazarConZeros(ano.toString(), FORMATO_ANO);
		String decimoVegesimo = replazarConZeros(cedulaEmisor.trim(), FORMATO_CEDULA);
		String cuadraGesimoSegundo = consecutivoFactura;
		String cuadraGesimoTerceroQuicuegimo = comprobanteElectronico.toString();
		String cuadraGesimoQuincuagesimo = replazarConZeros(codigoSeguridad.toString(), FORMATO_CODIGO_SEGURIDAD);

		return primeroTress + cuartoQuito + sextoAlSeptimo + octavoNoveno + decimoVegesimo + cuadraGesimoSegundo + cuadraGesimoTerceroQuicuegimo + cuadraGesimoQuincuagesimo;

	}

	/**
	 * Formateador de numeros en string rellena los campos con el formato indicado
	 * @param valor
	 * @param formato
	 * @return
	 */
	public static String replazarConZeros(String valor, String formato) {
		return formato.substring(valor.toString().length()) + valor;
	}

}

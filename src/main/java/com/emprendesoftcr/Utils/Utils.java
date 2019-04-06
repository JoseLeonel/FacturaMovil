package com.emprendesoftcr.Utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.NestedRuntimeException;
import org.springframework.web.context.ContextLoader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;

public final class Utils {

	public static Date sumarDiasFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}

	public static String formateadorMiles(Double valor) {
		String resultado = Constantes.EMPTY;
		
		if(valor == null) {
			valor = 0d;
			
		}
		
		DecimalFormat formateador = new DecimalFormat("###,###,##0.00");
		resultado = formateador.format (valor);
		
		return resultado;
	}
	public static String formateadorMilesSinDecimales(Double valor) {
		String resultado = Constantes.EMPTY;
		
		if(valor == null) {
			valor = 0d;
			
		}
		
		DecimalFormat formateador = new DecimalFormat("###,###,##0");
		resultado = formateador.format (valor);
		
		return resultado;
	}
	
	
  /**
   * A�ade ceros a la izquierda de un valor para que este alcance n longitud
   * @param num Valor a ser completado con ceros a la izquierda
   * @param places Longitud que tendr� el valor una vez completado
   * @return String con el nuevo valor
   */
  public static String zeroPad(int num, int places) {
      return String.format("%0" + Integer.toString(places) + "d", num);
  }

  /**
   * A�ade ceros a la izquierda de un valor para que este alcance n longitud
   * @param num Valor a ser completado con ceros a la izquierda
   * @param places Longitud que tendr� el valor una vez completado
   * @return String con el nuevo valor
   */
  public static String zeroPad(long num, int places) {
      return Strings.padStart(Long.toString(num), places, '0');
  }

  /**
   * A�ade ceros a la izquierda de un valor para que este alcance n longitud
   * @param num Valor a ser completado con ceros a la izquierda
   * @param places Longitud que tendr� el valor una vez completado
   * @return String con el nuevo valor
   */
  public static String zeroPad(String num, int places) {
      return Strings.padStart(num, places, '0');
  }


	/**
	 * @param dateString An input String, presumably from a user or a database table.
	 * @param formats An array of date formats that we have allowed for.
	 * @return A Date (java.util.Date) reference. The reference will be null if we could not match any of the known formats.
	 */
	public static Date parseDate(String dateString) {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT7);

		try {
			// parse() will throw an exception if the given dateString doesn't
			// match
			// the current format
			date = dateFormat.parse(dateString);
			date = new SimpleDateFormat("ddMMyyyyhhmmss").parse(dateString);

		} catch (ParseException e) {
			// don't do anything. just let the loop continue.
			// we may miss on 99 format attempts, but match on one format,
			// but that's all we need.
		}

		return date;
	}

	public static Date parseDate2(String dateString) {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT6);

		try {
			// parse() will throw an exception if the given dateString doesn't
			// match
			// the current format
			date = dateFormat.parse(dateString);
			date = new SimpleDateFormat(Constantes.DATE_FORMAT1).parse(dateString);

		} catch (ParseException e) {
			// don't do anything. just let the loop continue.
			// we may miss on 99 format attempts, but match on one format,
			// but that's all we need.
		}

		return date;
	}
	
	public static Boolean stringIsNullOrEmpty(String valor) {

		if (valor == null) {
			return true;
		} else if (valor.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Date sumaRestaDias(Date date, Integer dias) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}

	/**
	 * Retorna la fecha en el formato especificado. Pueden ser: yyyyMMdd, yyyy-MM-dd, dd-MM-yyyy, etc
	 * @param date
	 * @return
	 */
	public static String getFecha(Date date, String format) {
		SimpleDateFormat sd1 = new SimpleDateFormat(format);

		return sd1.format(date);
	}

	// /////////////////////////////////////////////////////////

	private static Queue<String>	queueMessage	= new LinkedList<String>();

	private static Utils					instance			= new Utils();

	private Utils() {
	}

	/* Metodos utilitarios globales */

	/**
	 * Metodo utilizado para normalizar el nombre de un property durante el proceso de Sort en un reporte. Si una propiedad 'name' de tipo numerico se quiere mostrar como una descripcion de la misma, se crea un metodo 'getStrName' y se usa en la tabla del reporte. Al usar ordenamiento, el component retorna el nombre de la propiedad como 'strName'. Este metodo se encarga de pasar el 'strName' a 'name'
	 */
	public static String normalizarNombreProperty(String property, String prefijo) {
		if (property.startsWith(prefijo)) {
			property = property.substring(prefijo.length());
			property = property.replaceFirst(property.substring(0, 1), property.substring(0, 1).toLowerCase());
		}
		return property;
	}

	/**
	 * Devuelve una fecha con la presentacion definida en el parametro 'formato'
	 * @param fecha La fecha a ser presentada con el nuevo formato
	 * @param formato El formato a ser aplicado para la visualizacion de la fecha
	 * @return Una cadena de caracteres para visualizar la fecha en el formato requerido
	 */
	public static String formatearFecha(Date fecha, String formato) {
		String resultado = " ";
		if (fecha != null) {
			try {
				DateFormat dateFormat = new SimpleDateFormat(formato);
				resultado = dateFormat.format(fecha);
			} catch (Exception e) {
				Utils.imprimirINFO(e.getMessage(), Utils.class);
			}
		}
		return resultado;
	}

	/**
	 * Devuelve una fecha con la presentacion definida en el parametro 'formato'
	 * @param fecha La fecha a ser presentada con el nuevo formato
	 * @param formato El formato a ser aplicado para la visualizacion de la fecha
	 * @return la fecha formato long
	 */
	public static Long formatearFechaToLong(Date fecha, String formato) {
		return Long.parseLong(formatearFecha(fecha, formato));
	}

	/**
	 * Retorna una instancia de una clase Tabulador para el padding de strings a mostrar en un reporte
	 * @param largoLinea Longitud de la linea en la que se van a poner los strings
	 * @param numeroColumnas Numero de columnas en las que se organiza la informacion
	 * @param margenDerecho El margen a la derecha
	 * @return Una instancia de la clase Tabulador
	 */
	public static Tabulador getNewInstance(int largoLinea, int numeroColumnas, int margenDerecho) {
		return instance.new Tabulador(largoLinea, numeroColumnas, margenDerecho);
	}

	public static Tabulador getNewInstance(int largoLinea, int numeroColumnas, int margenDerecho, boolean isHTMLbreakLine) {
		return instance.new Tabulador(largoLinea, numeroColumnas, margenDerecho, isHTMLbreakLine);
	}

	/**
	 * Procesa un numero para la generacion de un resultado redondeado segun el numero de digitos decimales definido
	 * @param number El numero a redondear
	 * @param digits La cantidad de digitos decimales del resultado
	 * @return El numero redondeado al decimal dado por el parametro digits
	 */
	public static Double formatearNumero(double number, int digits) {
		NumberFormat formater = NumberFormat.getInstance(Locale.US);
		formater.setMaximumFractionDigits(digits);
		String strNumber = formater.format(number);
		strNumber = strNumber.replace(",", "");
		return Double.parseDouble(strNumber);
	}

	/**
	 * Procesa un numero para la generacion de un resultado redondeado segun el numero de digitos decimales definido
	 * @param number El numero a redondear
	 * @param digits La cantidad de digitos decimales del resultado
	 * @return El numero redondeado al decimal dado por el parametro digits
	 */
	public static int formatearNumeroParaEntero(double number, int digits) {
		NumberFormat formater = NumberFormat.getInstance();
		formater.setMaximumFractionDigits(digits);
		String strNumber = formater.format(number);
		strNumber = strNumber.replace(",", "");
		return Integer.parseInt(strNumber);
	}

	/**
	 * Procesa un numero para la generacion de un resultado redondeado segun el numero de digitos decimales definido
	 * @param number El numero a redondear
	 * @param digits La cantidad de digitos decimales del resultado
	 * @return El numero redondeado al decimal dado por el parametro digits
	 */
	public static String formatearNumeroParaOrdenServicio(double number, int digits) {
		NumberFormat formater = NumberFormat.getInstance();
		formater.setMaximumFractionDigits(digits);
		String strNumber = formater.format(number);
		strNumber = strNumber.replace(",", "");
		// return Double.parseDouble(strNumber);
		return strNumber;
	}

	/**
	 * Procesa un numero para la generacion de un resultado redondeado segun el numero de digitos decimales definido con el formato '###.###.##,##'
	 * @param number El numero a redondear
	 * @param digits La cantidad de digitos decimales del resultado
	 * @return El numero redondeado al decimal dado por el parametro digits con el formato '###.###.##,##'
	 */
	public static String formatearNumeroListados(double number, int digits) {
		NumberFormat formater = NumberFormat.getInstance();
		formater.setMaximumFractionDigits(digits);
		String strNumber = formater.format(number);

		strNumber = strNumber.replace(",", " ");
		strNumber = strNumber.replace(".", ",");
		strNumber = strNumber.replace(" ", ".");
		return strNumber;
	}

	public static String formatearNumeroListados(BigDecimal number, int digits) {
		NumberFormat formater = NumberFormat.getInstance();
		formater.setMaximumFractionDigits(digits);
		String strNumber = formater.format(number);

		strNumber = strNumber.replace(",", " ");
		strNumber = strNumber.replace(".", ",");
		strNumber = strNumber.replace(" ", ".");
		return strNumber;
	}

	public static String formatearNumeroListados2(double number, int digits) {
		NumberFormat formater = NumberFormat.getCurrencyInstance(Locale.US);
		formater.setMaximumFractionDigits(digits);
		String strNumber = formater.format(number);
		strNumber = strNumber.replace("$", "");
		return strNumber;
	}

	public static String formatearNumeroBigDecimal2(BigDecimal number, int digits) {
		NumberFormat formater = NumberFormat.getCurrencyInstance(Locale.US);
		formater.setMaximumFractionDigits(digits);
		String strNumber = formater.format(number);
		strNumber = strNumber.replace("$", "");
		return strNumber;
	}

	/**
	 * Metodo usado para reemplazar en una propiedad parametrizada tal como "Existen {1} registros" el valor del parametro dado por "{numero}"
	 * @param mensaje El mensaje con el parametro a reemplazar
	 * @param numeroParam El numero de parametro a reemplazar
	 * @param valor El valor con el que se va a reemplazar el parametro
	 * @return El mensaje con el parametro reemplazado por valor
	 */
	public static String replaceParam(String mensaje, int numeroParam, String valor) {
		// Para evitar aplicar como expresion regular
		mensaje = mensaje.replace('{', '<');
		mensaje = mensaje.replace('}', '>');
		mensaje = mensaje.replaceAll("<" + numeroParam + ">", valor);
		return mensaje;
	}

	/**
	 * Metodo para transformar fechas a formato texto. Aplica para los annos entre 2000 y 2100
	 * @param fecha La fecha a transformar
	 * @return La fecha en formato texto: [02/01/2010] => dos de enero del anno dos mil diez.
	 */
	public static String formatearFechaATexto(Date fecha) {
		String result = "dia de mes del a\u00F1o anno.";
		String[] unidades = new String[] { "cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez", "once", "doce", "trece", "catorce", "quince", "dieciseis", "diecisiete", "dieciocho", "diecinueve", "veinte", "veintiuno", "veintidos", "veintitres", "veinticuatro", "veinticinco", "veintiseis", "veintisiete", "veintiocho", "veintinueve" };
		String[] meses = new String[] { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "setiembre", "octubre", "noviembre", "diciembre" };
		String[] decenas = new String[] { "cero", "diez", "veinte", "treinta", "cuarenta", "cinciuenta", "sesenta", "setenta", "ochenta", "noventa" };
		String[] dias = unidades;
		String[] annos = unidades;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

		// Formatea el dia
		int dia = calendar.get(Calendar.DATE);
		if (dia > 10 && dia < 30) {
			result = result.replaceFirst("dia", dias[calendar.get(Calendar.DATE)]);
		} else {
			int decena = (calendar.get(Calendar.DATE)) / 10;
			int unidad = (calendar.get(Calendar.DATE)) % 10;

			if (decenas[decena].equals("cero")) {
				result = result.replaceFirst("dia", unidades[unidad]);
				if (unidades[unidad].equals("uno")) {
					result = result.replaceFirst("uno", "primero");
				}
			} else {
				result = result.replaceFirst("dia", decenas[decena] + " y " + unidades[unidad]);
			}
		}

		// Formatea el mes
		result = result.replaceFirst("mes", meses[calendar.get(Calendar.MONTH)]);

		// Formatea el anno
		int anno = calendar.get(Calendar.YEAR);
		if (anno > 2010 && anno < 2030) {
			result = result.replaceFirst("anno", "dos mil " + annos[calendar.get(Calendar.YEAR) - 2000]);
		} else {
			int decena = (calendar.get(Calendar.YEAR) - 2000) / 10;
			int unidad = (calendar.get(Calendar.YEAR) - 2000) % 10;
			result = result.replaceFirst("anno", "dos mil " + decenas[decena] + " y " + unidades[unidad]);
		}
		return result;
	}

	/**
	 * Clase para el manejo de strings a colocar en una linea organizado por columnas
	 * @author Manuel Rivera
	 */
	public class Tabulador {

		StringBuilder	builder;
		int						largoLinea;
		int						numeroColumnas;
		int						contador;
		int						anchoColumna;
		int						margenDerecho;
		boolean				isHTMLBreakLine;

		/**
		 * Constructor con los valores de las propiedades a setear
		 * @param largoLinea Longitud de la linea en la que se van a poner los strings
		 * @param numeroColumnas Numero de columnas en las que se organiza la informacion
		 * @param margenDerecho El margen a la derecha
		 */
		public Tabulador(int largoLinea, int numeroColumnas, int margenDerecho, boolean isHTMLBreakLine) {
			this.setProperties(largoLinea, numeroColumnas, margenDerecho, isHTMLBreakLine);
		}

		private Tabulador(int largoLinea, int numeroColumnas, int margenDerecho) {
			this.setProperties(largoLinea, numeroColumnas, margenDerecho, false);
		}

		private void setProperties(int largoLinea, int numeroColumnas, int margenDerecho, boolean isHTMLBreakLine) {
			this.builder = new StringBuilder();
			this.largoLinea = largoLinea;
			this.numeroColumnas = numeroColumnas;
			this.anchoColumna = largoLinea / numeroColumnas - "\t".length();
			this.margenDerecho = margenDerecho;
			this.contador = 1;
			this.isHTMLBreakLine = isHTMLBreakLine;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return builder.toString();
		}

		/**
		 * Agrega un string a la linea corriente
		 * @param item
		 */
		public void agregarItem(String item) {
			item = item.trim();
			item = item.replace("null", "");
			int largo = item.length();
			if (largo > anchoColumna - margenDerecho) {
				item = item.substring(0, anchoColumna - margenDerecho);
				item = rellenarDerecha(item, anchoColumna);
			} else {
				item = rellenarDerecha(item, anchoColumna);
			}

			if (contador % numeroColumnas == 0) {
				if (isHTMLBreakLine) {
					item += "<br>";
				} else {
					item += "\n";
				}
			}
			builder.append(item);
			contador += 1;
		}

		/**
		 * Rellena con espacios en blanco a la derecha, un item con longitud menor que la columna donde se ubica
		 * @param item El mensaje a colocar en una columna de una linea
		 * @param ancho Longitud de la columna donde se ubica el mensaje
		 * @return Un string con una longitud igual al ancho de la columna
		 */
		private String rellenarDerecha(String item, Integer ancho) {
			for (int i = item.length(); i < ancho; i++) {
				item += " ";
			}
			return item;
		}

		/**
		 * Completa la ultima linea con espacios en blanco
		 */
		public void ultimaLinea() {
			Integer modResult = (contador - 1) % numeroColumnas;
			if (modResult != 0) {
				Integer anchoAdicional = (numeroColumnas - modResult) * anchoColumna;
				builder.append(rellenarDerecha("", anchoAdicional));
			}
		}

		/**
		 * Limpia el contenido del objeto
		 */
		public void clear() {
			this.builder = new StringBuilder();
			this.contador = 1;
		}
	}

	/**
	 * Elimina el formato de un numero de telefono
	 * @param source El numero de telefono al que se le va aliminar el formato
	 * @return El numero sin el formato telefonico
	 */
	public static String desFormatearNumeroTelefono(String source) {
		boolean isValid = false;
		StringBuffer buffer = new StringBuffer();
		char[] sourceChars = source.toCharArray();
		for (char c : sourceChars) {
			if (esDigitoNumerico(c)) {
				if (c == '0') {
					if (isValid)
						buffer.append(c);
				} else {
					buffer.append(c);
					isValid = true;
				}
			}
		}
		return buffer.toString().trim();
	}

	/**
	 * Muestra un numero telefonico con un formato dado por el parametro format
	 * @param source El numero telefonico sin formato aplicado
	 * @param target El numero telefonico con formato aplicado
	 * @return El numero telefonico con el nuevo formato
	 */
	public static String formatearTelefono(String source, String format) {
		if (source == null || Utils.isEmpty(source) || source.indexOf("null") != -1)
			return "";

		char[] sourceChars = source.toCharArray();
		char[] formatChars = format.toCharArray();
		char[] resultChars = new char[formatChars.length];

		int index2 = sourceChars.length - 1;
		for (int index1 = formatChars.length - 1; index1 >= 0; index1--) {
			if (esDigitoNumerico(formatChars[index1])) {
				if (index2 >= 0) {
					resultChars[index1] = sourceChars[index2];
					index2--;
				} else {
					resultChars[index1] = '0';
				}
			} else {
				resultChars[index1] = formatChars[index1];
			}
		}
		return new String(resultChars).trim();
	}

	/**
	 * Determina si un caracter representa un digito numerico en una mascara
	 * @param digito El digito que se desea determinar si es o no numerico
	 * @return true si el digito representa un numero en una mascara
	 */
	private static boolean esDigitoNumerico(char digito) {
		boolean result = true;
		try {
			Integer.parseInt(digito + "");
		} catch (Exception e) {
			result = digito == '#';
		}
		return result;
	}

	/**
	 * Determina si un caracter representa un digito numerico en una mascara
	 * @param digito El digito que se desea determinar si es o no numerico
	 * @return true si el digito representa un numero en una mascara
	 */
	public static boolean esNumero(Long digito) {
		boolean result = true;
		try {
			Short.parseShort(digito + "");
		} catch (Exception e) {
			return false;
		}
		return result;
	}

	/**
	 * Una alternativa para reemplazar el uso del metodo isEmpty() de la clase String en java 6
	 * @param obj El objeto a analizar (String o List)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		boolean result = false;
		if (obj instanceof Collection) {
			Collection tmp = (Collection) obj;
			result = tmp == null || tmp.isEmpty();
		} else if (obj instanceof List) {
			List tmp = (List) obj;
			result = tmp == null || tmp.isEmpty();
		} else if (obj instanceof String) {
			String tmp = (String) obj;
			result = tmp == null || tmp.length() == 0;
		}
		return result;
	}

	/**
	 * Metodo para la prueba de las funciones implementadas
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

	}

	/**
	 * Toma un numero en formato cientifico y lo transforma a un String en notacion decimal
	 * @param numero El Numero a modificar
	 * @param enteros La cantidad de enteros que se van a representar en el resultado
	 * @param decimales La cantidad de decimales que se van a representar en el resultado
	 * @param relleno Los caracteres que reemplazan los enteros nulos segun la cantidad esperada
	 * @return El resultado en formato String que representa el resultado con los caracteres de relleno en la parte entera
	 */
	public static String formatearNumero(Double numero, int enteros, int decimales, String relleno) {
		String strNumero = String.format("%." + decimales + "f", numero == null ? 0d : numero);
		StringBuffer buffer = new StringBuffer(strNumero);

		// int length = buffer.length();
		int puntoPos = buffer.indexOf(".");
		int cantTernas = puntoPos / 3;

		int modulo = puntoPos % 3;
		int inicio = modulo + 3;
		int fin = puntoPos + cantTernas - 3;

		if (cantTernas > 0 && modulo != 0) {
			buffer.insert(modulo, ",");
			inicio++;
			fin++;
		} else {
			// inicio--;
		}

		for (int index = inicio; index < fin; index += 4) {
			buffer.insert(index, ",");
		}
		return leftPadding(buffer.toString(), enteros, relleno);
	}

	/**
	 * Rellena a la izquierda con una cantidad de caracteres dados por relleno
	 * @param str El String a modificar
	 * @param integerLength La longitud de la parte entera
	 * @param relleno El caracter con el cual se va a rellenar los espacios de enteros nulos
	 * @return El nuevo String que representa el resultado con los caracteres de relleno
	 */
	public static String leftPadding(String str, int integerLength, String relleno) {
		int puntoPos = str.indexOf(".");

		if (puntoPos == -1) {
			int adicionales = integerLength - str.length();
			String tmp = "";
			for (int i = 0; i < adicionales; i++) {
				tmp += relleno;
			}
			str = tmp + str;
		} else if (integerLength > puntoPos) {
			for (int i = 0; i < integerLength - puntoPos; i++) {
				str = relleno + str;
			}
		}
		return str;
	}

	/**
	 * @param param
	 * @return
	 */
	public static List<String> csvToList(String param, String separador) {
		List<String> result = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(param.trim(), separador);
		while (tokenizer.hasMoreTokens()) {
			result.add(tokenizer.nextToken());
		}
		return result;
	}

	/**
	 * @param document
	 * @param name
	 * @return
	 */
	public static String getNodeValueByTagName(Document document, String name) {
		NodeList nodeList = document.getElementsByTagName(name);
		if (nodeList != null && nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		return "";
	}

	/**
	 * @param strXml
	 * @return
	 * @throws Exception
	 */
	public static Document getDocumentByString(String strXml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = new ByteArrayInputStream(strXml.getBytes("UTF-8"));
		return builder.parse(is);
	}

	/**
	 * Obtiene todo lo incluido dentro de un Tag en un mensaje de XML.
	 * @param XmlOrigen String conteniendo el elemento origen de donde se va a extraer un child
	 * @param Xmlinicio String conteniendo el elemento de donde inicia el child a extraer
	 * @param Xmlfin String conteniendo el elemento de donde finaliza el child a extraer
	 * @return El XML obtenido como resultado
	 */
	public static final String obtenerTagXML(String XmlOrigen, String Xmlinicio, String Xmlfin) {
		String texto = XmlOrigen.substring(XmlOrigen.indexOf(Xmlinicio) + Xmlfin.length() - 1, XmlOrigen.indexOf(Xmlfin));
		return texto;
	}

	public static Date obtenerFechaHoraValoresTrama(String fecha, String hora) {
		Date fechaHora = null;
		SimpleDateFormat f = new SimpleDateFormat("ddMMyyyyhhmmss");
		try {
			fechaHora = f.parse(fecha + hora);
		} catch (ParseException e) {
			Utils.imprimirINFO(e, Utils.class);
			return null;
		}
		return fechaHora;
	}

	/**
	 * @param fecha
	 * @param hora
	 * @param formato de la forma en que se requiere la fecha (a\u00F1o, mes, dia)
	 * @return
	 */
	public static Date obtenerFechaHoraValoresTrama(String fecha, String hora, String formato) {
		Date fechaHora = null;
		SimpleDateFormat f = new SimpleDateFormat(formato);
		try {
			fechaHora = f.parse(fecha + hora);
		} catch (ParseException e) {
			Utils.imprimirINFO(e, Utils.class);
			return null;
		}
		return fechaHora;
	}

	/**
	 * Obtiene todo lo incluido dentro de un Tag en un mensaje de XML.
	 * @param MensajeOriginal, TagInicio, TagFinal
	 * @return El XML obtenido
	 */
	public static final String obtenerTag(String XmlOrigen, int posIncial, int posFinal) {
		String texto = XmlOrigen.substring(posIncial, posFinal).trim();
		return texto;
	}

	public static boolean isNullString(String value) {
		if (value != null && value.length() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static Long checkNullLong(String value) {
		Long result = new Long(0);
		if (value != null) {
			try {
				result = Long.parseLong(value);
			} catch (Exception e) {
				Utils.imprimirINFO(e, Utils.class);
				result = new Long(0);
			}
		}
		return result;
	}

	public static Integer checkNullInteger(String value) {
		Integer result = new Integer(0);
		if (value != null) {
			try {
				result = Integer.parseInt(value);
			} catch (Exception e) {
				Utils.imprimirINFO(e, Utils.class);
				result = new Integer(0);
			}
		}
		return result;
	}

	public static Byte checkNullByte(String value) {
		Byte result = new Byte((byte) 0);
		if (value != null) {
			try {
				result = Byte.parseByte(value);
			} catch (Exception e) {
				Utils.imprimirINFO(e, Utils.class);
				result = new Byte((byte) 0);
			}
		}
		return result;
	}

	public static Short checkNullShort(String value) {
		Short result = new Short((short) 0);
		if (value != null) {
			try {
				result = Short.parseShort(value);
			} catch (Exception e) {
				Utils.imprimirINFO(e, Utils.class);
				result = new Short((short) 0);
			}
		}
		return result;
	}

	/**
	 * Metodo para imprimir en el log, un mensaje de acuerdo a su origen Si obj es una Excepcion, se determina el mensaje y el metodo donde ocurrio el error Si es un String, se determina si corresponde a una llave en el archivo de properties para los mensajes en el codigo java. Si no es asi, se envia como mensaje sin modificar Si no es ninguno de los casos anteriores, se envia a imprimir el toString del objeto
	 * @param obj La Excepcion con la informacion del Stack de llamadas, un mensaje o una llave para obtener el mensaje desde un archivo de properties
	 * @param clazz La clase desde la cual se hace la llamada
	 */
	public static void imprimirINFO(Object obj, Class<?> clazz) {
		try {
			imprimirLOGGER(obj, clazz, 0);
		} catch (Exception e) {
			imprimirLOGGER(e, Utils.class, 1);
		}
	}

	/**
	 * Metodo para imprimir en el log, un mensaje de acuerdo a su origen Si obj es una Excepcion, se determina el mensaje y el metodo donde ocurrio el error Si es un String, se determina si corresponde a una llave en el archivo de properties para los mensajes en el codigo java. Si no es asi, se envia como mensaje sin modificar Si no es ninguno de los casos anteriores, se envia a imprimir el toString del objeto
	 * @param obj La Excepcion con la informacion del Stack de llamadas, un mensaje o una llave para obtener el mensaje desde un archivo de properties
	 * @param clazz La clase desde la cual se hace la llamada
	 */
	public static void imprimirERROR(Object obj, Class<?> clazz) {
		try {
			imprimirLOGGER(obj, clazz, 1);
		} catch (Exception e) {
			imprimirLOGGER(e, Utils.class, 1);
		}
	}

	private static int QUEUE_LENGHT = 500;

	/**
	 * Metodo para imprimir en el log, un mensaje de acuerdo a su origen Si obj es una Excepcion, se determina el mensaje y el metodo donde ocurrio el error Si es un String, se determina si corresponde a una llave en el archivo de properties para los mensajes en el codigo java. Si no es asi, se envia como mensaje sin modificar Si no es ninguno de los casos anteriores, se envia a imprimir el toString del objeto
	 * @param obj La Excepcion con la informacion del Stack de llamadas, un mensaje o una llave para obtener el mensaje desde un archivo de properties
	 * @param clazz La clase desde la cual se hace la llamada
	 * @param level Si se va a imprimir como 0-INFO, 1-ERROR
	 */
	private static void imprimirLOGGER(Object obj, Class<?> clazz, int level) {
		// ResourceBundle rb = Constantes.RESOURCE_BUNDLE;
		String messageKey = null;
		String message = null;

		if (obj instanceof Exception) {
			Exception e = (Exception) obj;
			StackTraceElement element = getTraceElement(e, clazz);
			if (element != null) {
				message = "[" + element.getMethodName() + ":" + element.getLineNumber() + "] " + ((Exception) obj).getMessage();
			}
		} else if (obj instanceof String) {
			messageKey = (String) obj;
			try {
				if (messageKey != null) {
					// message = rb.getString(messageKey);
				}
			} catch (Exception e) {
				message = messageKey;
			}
		} else {
			message = obj.toString();
		}

		// Si son logs de aspectj o roo se omiten los mensajes
		boolean notDesired = message != null && (message.indexOf("_aroundBody") != -1 || message.indexOf("_Roo") != -1 || message.indexOf("No entity found for query") != -1);
		if (notDesired)
			return;

		// // Se escribe en el log segun log4j
		// Logger logger = Logger.getLogger(clazz);
		// if(level == 0){
		// logger.info(message);
		// }
		// else{
		// logger.error(message);
		// }

		// Registro en el log temporal
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		queueMessage.add(formatter.format(new Date()) + " - " + message + "\n");
		if (queueMessage.size() >= QUEUE_LENGHT) {
			int resto = queueMessage.size() - QUEUE_LENGHT;
			for (int index = 0; index < resto; index++) {
				queueMessage.remove();
			}
		}
	}

	public static Queue<String> getQueueMessage() {
		return queueMessage;
	}

	/**
	 * Metodo para determinar la ultima llamada realizada por una clase existente en la pila de llamadas de una excepcion
	 * @param e La Excepcion con la informacion del Stack de llamadas
	 * @param clazz La clase desde la cual se hace la llamada
	 * @return El elemento con la ultima llamada hecha desde la clase de referencia clazz
	 */
	private static StackTraceElement getTraceElement(Exception e, Class<?> clazz) {
		StackTraceElement[] elements = e.getStackTrace();
		for (StackTraceElement element : elements) {
			if (element.getClassName().equals(clazz.getName())) {
				return element;
			}
		}
		return null;
	}

	/**
	 * El metodo retorna el ultimo dia del mes anterior
	 * @param fechaInicio
	 * @return El primer dia del mes anterior ya procesado
	 */
	public static Date getPrimerDiaMesAnterior(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, -1);

		return getPrimerDiaDelMes(calendar.getTime());
	}

	/**
	 * El metodo retorna el primer dia del mes anterior
	 * @param fechaInicio
	 * @return El ultimo dia del mes anterior
	 */
	public static Date getUltimoDiaMesAnterior(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, -1);

		return getUltimoDiaDelMes(calendar.getTime());
	}

	/**
	 * El metodo retorna el primer dia del mes
	 * @param fechaInicio
	 * @return fecha con el primer dia del mes.
	 */
	public static Date getPrimerDiaDelMes(Date fechaInicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaInicio);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DAY_OF_MONTH), cal.getMinimum(Calendar.HOUR_OF_DAY), cal.getMinimum(Calendar.MINUTE), cal.getMinimum(Calendar.SECOND));
		return cal.getTime();
	}

	/**
	 * Este metodo retorna el ultimo dia del mes anterior
	 * @param fechaInicio
	 * @return El ultimo dia del mes
	 */
	public static Date getUltimoDiaDelMes(Date fechaInicio) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaInicio);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH), cal.getMaximum(Calendar.HOUR_OF_DAY), cal.getMaximum(Calendar.MINUTE), cal.getMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	/**
	 * Metodo encargado de validar si un valor es numerico
	 * @param cadena valor string a verificar
	 * @return true si el valor es numerico
	 */
	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static Date pasarADate(String fecha, String formatoFecha) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formatoFecha);
		Date fechaReal = null;
		try {
			fechaReal = formatoDelTexto.parse(fecha);
			return fechaReal;
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return fechaReal;
	}

	public static Integer calcularCantidadMeses(String fechaInicial, String fechaFinal, DateFormat formatter) throws ParseException {
		Date fecha1 = formatter.parse(fechaInicial);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(fecha1);
		int mes1 = calendar1.get(Calendar.MONTH);
		int anno1 = calendar1.get(Calendar.YEAR);

		Date fecha2 = formatter.parse(fechaFinal);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(fecha2);
		int mes2 = calendar2.get(Calendar.MONTH);
		int anno2 = calendar2.get(Calendar.YEAR);

		return mes2 - mes1 + 1 + 12 * (anno2 - anno1);
	}

	public static Integer calcularCantidadMeses(Date fechaInicial, Date fechaFinal) throws ParseException {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(fechaInicial);
		int mes1 = calendar1.get(Calendar.MONTH);
		int anno1 = calendar1.get(Calendar.YEAR);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(fechaFinal);
		int mes2 = calendar2.get(Calendar.MONTH);
		int anno2 = calendar2.get(Calendar.YEAR);

		return mes2 - mes1 + 1 + 12 * (anno2 - anno1);
	}

	public static Double round(Double numero, int decimales) {
		double factor = Math.pow(10, decimales);
		return (double) Math.round(numero * factor) / factor;
	}

	public static String formatearMensaje(String mensaje, Integer ancho) {
		String resultado = "";
		int length = mensaje == null ? 0 : mensaje.length();
		if (mensaje == null || length == 0 || ancho <= 0 || ancho > length) {
			return mensaje;
		}

		int lineas = length / ancho;
		for (int linea = 1; linea <= lineas; linea++) {
			resultado += mensaje.substring((linea - 1) * ancho, linea * ancho) + "\n";
		}
		resultado += mensaje.substring((lineas) * ancho, length) + "\n";
		return resultado;
	}

	/*Metodo que devuelve el Numero total de minutos que hay entre las dos Fechas */
	public static long cantidadTotalMinutos(Calendar fechaInicial ,Calendar fechaFinal){

	long totalMinutos=0;
	totalMinutos=((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000/60);
	return totalMinutos;
	}
	
	public static String normalizarMessageException(Exception e) {
		String message = e.getMessage();
		if (e instanceof NestedRuntimeException) {
			message = ((NestedRuntimeException) e).getMostSpecificCause().getMessage();
		}
		return message;
	}

	// Metodos sobre el Contexto de la aplicacion

	public static String getContextProperty(String name) {
		String property;
		try {
			Context env = (Context) new InitialContext().lookup("java:comp/env");
			property = (String) env.lookup(name);
		} catch (NamingException e) {
			property = null;
			Utils.imprimirINFO(e, Utils.class);
		}
		return property;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getBean(Class clazz) {
		return ContextLoader.getCurrentWebApplicationContext().getBean(clazz);
	}

	public static Integer convertStrin2Integer(String str) {
		Integer resultado = 0;
		try {
			resultado = Integer.parseInt(str);
		} catch (Exception e) {
			imprimirERROR(e, Utils.class);
		}
		return resultado;
	}

	public static Long convertStrin2Long(String str) {
		Long resultado = (long) 0;
		try {
			resultado = Long.parseLong(str);
		} catch (Exception e) {
			imprimirERROR(e, Utils.class);
		}
		return resultado;
	}

	/**
	 * El metodo retorna los ultimos 10 a�os
	 * @param
	 * @return
	 */
	public static Collection<Integer> getUltimosDiezAnos() {
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		Collection<Integer> anolist = new ArrayList<Integer>();
		for (int i = ano; i >= (ano - 10); i--) {
			anolist.add(i);
		}

		return anolist;
	}

	public static String getMesStr(Integer mesParam) {
		String mesDesc = "N/A";
		switch (mesParam) {
			case 1:
				mesDesc = "Enero";
				break;
			case 2:
				mesDesc = "Febrero";
				break;
			case 3:
				mesDesc = "Marzo";
				break;
			case 4:
				mesDesc = "Abril";
				break;
			case 5:
				mesDesc = "Mayo";
				break;
			case 6:
				mesDesc = "Junio";
				break;
			case 7:
				mesDesc = "Julio";
				break;
			case 8:
				mesDesc = "Agosto";
				break;
			case 9:
				mesDesc = "Setiembre";
				break;
			case 10:
				mesDesc = "Octubre";
				break;
			case 11:
				mesDesc = "Noviembre";
				break;
			case 12:
				mesDesc = "Diciembre";
				break;
			default:
				break;
		}
		return mesDesc;
	}

	/**
	 * El metodo retorna fecha con formato
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public static String getFechaFormato(Integer fecha) {
		DateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = originalFormat.parse(fecha.toString());
			String formattedDate = targetFormat.format(date);
			return formattedDate;

		} catch (ParseException e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * El metodo retorna hora con formato
	 * @param
	 * @return
	 */
	public static String getHoraFormato(Integer hora) {
		DateFormat originalFormat = new SimpleDateFormat("hhmmssmm");
		DateFormat targetFormat = new SimpleDateFormat("hh:mm:ss");
		try {
			Date date = originalFormat.parse(hora.toString());
			String formattedDate = targetFormat.format(date);
			return formattedDate;

		} catch (ParseException e) {
			e.printStackTrace();

		}
		return null;
	}

	public static String normalizarNombreArchivoExcel(String value) {
		if (value.contains("Excel")) {
			value = value.replace("Excel", " ");
		}
		return value.trim();
	}

	/**
	 * El metodo retorna fecha y hora del sistema
	 * @param
	 * @return
	 */
	public static String formatoFechaDelSistema(String dateStr) {
		SimpleDateFormat readFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

		SimpleDateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = readFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (date != null) {
			return writeFormat.format(date);
		}
		return null;
	}
	
	public static String getFechaGeneraReporte(Date date) {
		DateFormat fechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a ");
		
		return fechaCompleta.format(date);
	}
	
	
	public static String getFechaGeneraHacienda(Date date) {
		DateFormat fechaCompleta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a ");
		
		return fechaCompleta.format(date);
	}

	public static String getFechaStr(Date date) {
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		return fecha.format(date);
	}

	public static String getHoraStr(Date date) {
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		return hora.format(date);
	}
	
	public static String getFechaHoraStr(Date date) {
		DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat hora  = new SimpleDateFormat("hh:mm:ss");
		String fechaStr  = fecha.format(date);
		String horaStr   = hora.format(date);
		return fechaStr + " " + horaStr;
	}

	/**
	 * Cambiar el formato de fecha
	 * @param fecha
	 * @return
	 */
	public static XMLGregorianCalendar formatoXMLGregorianCalendar(Date fecha) {
		/* Create Date Object */
		Date date = new Date();
		XMLGregorianCalendar xmlDate = null;
		GregorianCalendar gc = new GregorianCalendar();

		gc.setTime(date);

		try {
			xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return xmlDate;

	}
	/**
	 * Redondeo de los detalles de la factura y resumen
	 * @param value
	 * @param places
	 * @return
	 */
	public static double roundFactura(double value, int places) {
	
    if (places < 0) throw new IllegalArgumentException();
 
    BigDecimal bd = new BigDecimal(Double.toString(value));
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
	}

	public static Date dateToDate(Date fecha, boolean fechaMax) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		if (fechaMax) {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		return calendar.getTime();
	}
	
	public static Boolean validarCorreo(String correo) {
		Boolean resultado = false;
		 // Patrón para validar el email
    Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    Matcher mather = pattern.matcher(correo);
		
    if (mather.find() == true) {
    	resultado = true;
    }
		
		return resultado;
	}
	
	public static String obtenerTipoDocumentoConsecutivo(String consecutivo) {		
		//001 + 00004 + 01 + 0000003888 
		return consecutivo.substring(8, 2); //Se retorna 01
	}
	
	
	public static String obtenerDescripcionTipoDocumento(String tipoDocumento) {
		String descripcion = " ";
		switch (tipoDocumento) {
			case Constantes.FACTURA_TIPO_DOC_TIQUETE:
					descripcion = Constantes.RESOURCE_BUNDLE.getString(Constantes.FACTURA_TIPO_DOC_TIQUETE_STR);
			break;
			case Constantes.FACTURA_TIPO_DOC_PROFORMAS:
				descripcion = Constantes.RESOURCE_BUNDLE.getString(Constantes.FACTURA_TIPO_DOC_PROFORMAS_STR);
			break;
			case Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO:
				descripcion = Constantes.RESOURCE_BUNDLE.getString(Constantes.FACTURA_TIPO_DOC_TIQUETE_USO_INTERNO_STR);
			break;
			case Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA:
				descripcion = Constantes.RESOURCE_BUNDLE.getString(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA_STR);
			break;
			case Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO:
				descripcion = Constantes.RESOURCE_BUNDLE.getString(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO_STR);
			break;
			case Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO:
				descripcion = Constantes.RESOURCE_BUNDLE.getString(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO_STR);
			break;
			case Constantes.FACTURA_TIPO_DOC_COMPRAS:
				descripcion = Constantes.RESOURCE_BUNDLE.getString(Constantes.FACTURA_TIPO_DOC_COMPRAS_STR);
			break;
			default:
				break;
		}
		return descripcion;		
	}
}

package com.emprendesoftcr.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.NestedRuntimeException;
import org.springframework.validation.Errors;
import org.springframework.web.context.ContextLoader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.common.base.Strings;

public final class Utils {

	public static String oTroContenido() {
		String resultado = Constantes.EMPTY;

		resultado = "<OtroContenido>" + "<ContactoDesarrollador xmlns=\"" + Constantes.DOCXMLS_CONTACTO_DESARROLLADOR_4_3 + "\">" + "<NombreContacto>Ing.Leonel Hernandez Chaverri</NombreContacto>" + "<CorreoElectronico>josehernandezchaverri@gmail.com</CorreoElectronico>" + "<TelefonoContacto>83124207</TelefonoContacto>" + "<SistemasOfrecemos>Restaurante Ferreteria Mini Super y a la medida</SistemasOfrecemos>" + "</ContactoDesarrollador>";
		resultado += "</OtroContenido>";

		return resultado;
	}
	public static byte[] read(File file) throws IOException  {
   
    ByteArrayOutputStream ous = null;
    InputStream ios = null;
    try {
        byte[] buffer = new byte[4096];
        ous = new ByteArrayOutputStream();
        ios = new FileInputStream(file);
        int read = 0;
        while ((read = ios.read(buffer)) != -1) {
            ous.write(buffer, 0, read);
        }
    }finally {
        try {
            if (ous != null)
                ous.close();
        } catch (IOException e) {
        }

        try {
            if (ios != null)
                ios.close();
        } catch (IOException e) {
        }
    }
    return ous.toByteArray();
}

	public static Integer getPorcentajeExoneracion(Integer porcentajeExoneracion, Double impuesto) {
		Integer valor = Constantes.ZEROS;

		if (porcentajeExoneracion != null) {
			if (porcentajeExoneracion > Constantes.ZEROS) {
				if (impuesto.intValue() < porcentajeExoneracion) {
					valor = impuesto.intValue();
				} else {
					valor = porcentajeExoneracion;
				}
			}

		}

		return valor;
	}

	public static ByteArrayOutputStream convertirOutStream(ByteArrayInputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		int count;
		while ((count = inputStream.read(data)) != -1) {
			baos.write(data, 0, count);
		}
		return baos;
	}

	public static String claveMigradoXML() {
		Random rnd = new Random();
		int dig3 = rnd.nextInt(900) + 100; // siempre 3 digitos
		int dig5 = rnd.nextInt(90000) + 10000; // siempre 5 digitos
		Integer valor3 = dig3;
		Integer valor5 = dig5;
		return valor3.toString() + valor5.toString();

	}

	public static Cell getCel(Cell cell, Map<String, CellStyle> styles, Double value) {

		cell.setCellStyle(styles.get("cell"));
		cell.setCellValue(value);

		return cell;

	}

	public static Cell getCelSTR(Cell cell, Map<String, CellStyle> styles, String value) {

		cell.setCellStyle(styles.get("cellTEX"));
		cell.setCellValue(value);

		return cell;

	}

	/**
	 * Create a library of cell styles
	 */
	public static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<>();
		CellStyle style;
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 14);
		titleFont.setBold(true);
		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(titleFont);
		styles.put("title", style);
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBold(true);
		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(titleFont);
		styles.put("title1", style);

		Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short) 14);
		monthFont.setColor(IndexedColors.WHITE.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);

		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFont(monthFont);
		style.setWrapText(true);
		styles.put("header", style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setDataFormat(wb.createDataFormat().getFormat("#,###.00;[RED]-#,###.00"));

		styles.put("cell", style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setWrapText(true);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setDataFormat(wb.createDataFormat().getFormat("@"));

		styles.put("cellTEX", style);

		style = wb.createCellStyle();
		titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 12);
		// titleFont.setBold(true);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setDataFormat(wb.createDataFormat().getFormat("#,###.00;[RED]-#,###.00"));
		style.setFont(titleFont);
		styles.put("formula", style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setDataFormat(wb.createDataFormat().getFormat("#,###.00;[RED]-#,###.00"));
		styles.put("formula_2", style);

		return styles;
	}

	public static String generateXml(String path, String datosXml, String name, Date fecha) throws Exception {
		// String resultado = Utils.getDirectorioPorFechaMes(fecha);
		String resultado = Constantes.EMPTY;
		if (datosXml != null && datosXml.length() == 0) {
			return resultado;
		}
		try {
			File archivo = new File(path + "/" + name + "_" + claveMigradoXML() + ".xml");
			BufferedWriter bw;
			if (archivo.exists()) {
				// bw = new BufferedWriter(new FileWriter(archivo));
				bw = new BufferedWriter((new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8)));

				bw.write(datosXml);
				resultado = archivo.getPath();
				// System.out.println("Archivo creado con éxito");
			} else {
				// bw = new BufferedWriter(new FileWriter(archivo));

				//
				bw = new BufferedWriter((new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8)));
				bw.write(datosXml);
				// System.out.println("Archivo creado con éxito");
			}
			bw.close();
			resultado = archivo.getPath();

		} catch (Exception e) {
			throw e;
		}
		return resultado;
	}

	public static String leerXMLServidor(String path) throws IOException {
		String resultado = Constantes.EMPTY;
		System.out.println(path);
		String sCadena = "";
		BufferedReader bf = new BufferedReader(new FileReader(path));
		while ((sCadena = bf.readLine()) != null) {
			resultado += sCadena;

		}
		bf.close();
		System.out.println("Archivo:" + resultado);
		return resultado;
	}

	public static String convertDocumentToString(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Crear directorio en el Servidor
	 * @param cedulaEmpresa
	 * @return
	 */
	public static File crearDirectorioServidor(String servidor, String cedulaEmpresa, Date fecha) {
		String mes = Utils.getDirectorioMes(fecha);
		String anno = Utils.getDirectorioAnno(fecha);

		String direccion = servidor + cedulaEmpresa + "/" + anno + "/" + mes;
		// log.info("directorio: {}",direccion);
		File directorio = new File(direccion);
		if (directorio.exists()) {
			return directorio;
		}
		// Ejemplo respaldos/servicio8080/11001/2018/Dedcember
		@SuppressWarnings("unused")
		File directorio_servicio80 = crearDirectorio(servidor);


		File directorio1 = crearDirectorio(direccion);
		if (!directorio1.exists()) {
			return null;
		}

		return directorio1;
	}

	public static File crearDirectorio(String directorio) {
		File carpeta = new File(directorio);
		carpeta.setReadable(true, false);
		carpeta.setWritable(true, false);
		carpeta.setExecutable(true, false);
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}
		return carpeta;
	}

	public static LocalDate getFechaLocalDate(Date date) {
		// Getting the default zone id
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// Converting the date to Instant
		Instant instant = date.toInstant();

		// Converting the Date to LocalDate
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();

		return localDate;
	}

	public static String getDirectorioMes(Date date) {
		String resultado = Constantes.EMPTY;
		LocalDate localDate = getFechaLocalDate(date);
		Month mes = localDate.getMonth();
		resultado = mes.toString();

		return resultado;

	}

	public static String getDirectorioAnno(Date date) {
		String resultado = Constantes.EMPTY;
		LocalDate localDate = getFechaLocalDate(date);

		int anno = localDate.getYear();

		resultado = resultado + anno;

		return resultado.trim();

	}

	public static String getDirectorioDia(Date date) {
		String resultado = Constantes.EMPTY;
		LocalDate localDate = getFechaLocalDate(date);

		int dia = localDate.getDayOfMonth();

		resultado = resultado + dia;

		return resultado.trim();

	}

	public static String getDirectorioAnnoAndMes(Date date) {
		String resultado = Constantes.EMPTY;
		LocalDate localDate = getFechaLocalDate(date);

		int anno = localDate.getYear();
		Month mes = localDate.getMonth();

		resultado = anno + "/" + mes + "/";

		return resultado.trim();

	}

	public static String agregarXMLServidor(String servidor, String datosXML, String name, String cedulaEmpresa, Date fecha) throws Exception {

		String path = Constantes.EMPTY;
		if (datosXML != null && datosXML.length() == 0) {
			return path;
		}
		try {
			File directorio = Utils.crearDirectorioServidor(servidor, cedulaEmpresa, fecha);
			if (directorio != null) {
				if (directorio.exists()) {
					path = generateXml(directorio.getPath(), datosXML, name, fecha);
				}

			}

		} catch (Exception e) {
			throw e;
		}

		return path;

	}

	public static void getXMLServidor(String path) throws Exception {

	}

	public static Double getPorcentajeGananciaArticulo(Double costo, Double precioVenta, Double impuesto) {
		Double porcentajeGanancia = Constantes.ZEROS_DOUBLE;
		Double precioSinImpuesto = Constantes.ZEROS_DOUBLE;
		if (costo == 0) {
			return 100d;
		}
		if (precioVenta.equals(Constantes.ZEROS_DOUBLE)) {
			return Constantes.ZEROS_DOUBLE;
		}
		if (costo == precioVenta) {
			return Constantes.ZEROS_DOUBLE;
		}
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (impuesto == 0 || impuesto == null) {
			if (costo == precioVenta) {
				resultado = Constantes.ZEROS_DOUBLE;
			} else {
				resultado = costo / precioVenta;
				resultado = 1 - resultado;
			}
			porcentajeGanancia = resultado;
		} else {
			if (costo == precioVenta) {
				porcentajeGanancia = Constantes.ZEROS_DOUBLE;
			} else {
				Double resultadoImpuesto = impuesto;
				precioSinImpuesto = precioVenta / ((resultadoImpuesto / 100d) + 1d);
				resultadoImpuesto = Constantes.ZEROS_DOUBLE;
				precioSinImpuesto = precioSinImpuesto / ((resultadoImpuesto / 100d) + 1d);
				if (precioSinImpuesto < costo) {
					return Constantes.ZEROS_DOUBLE;
				}

				if (precioSinImpuesto == costo) {
					resultado = Constantes.ZEROS_DOUBLE;
				} else {
					resultado = costo / precioSinImpuesto;
					resultado = 1 - resultado;
				}
				porcentajeGanancia = resultado;

			}
		}
		return porcentajeGanancia * 100d;

	}

	/**
	 * Total Exento
	 * @param tipoImpuesto
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param subTotal
	 * @return
	 */
	public static Double getTotalExentos(String tipoImpuesto, Double montoImpuesto, Double montoImpuesto1, Double montoTotal) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		montoImpuesto = montoImpuesto == null ? Constantes.ZEROS_DOUBLE : montoImpuesto;
		montoImpuesto1 = montoImpuesto1 == null ? Constantes.ZEROS_DOUBLE : montoImpuesto1;
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE) && montoImpuesto1.equals(Constantes.ZEROS_DOUBLE) && tipoImpuesto.equals(Constantes.EMPTY)) {
			resultado = montoTotal;
		}
		return resultado;
	}

	/**
	 * Obtiene el monto Total
	 * @param tipoImpuesto
	 * @param unidadMedida
	 * @param montoTotal
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @return
	 */
	public static Double getTotalServicioGravados(String tipoImpuesto, String unidadMedida, Double montoTotal, Double montoImpuesto, Double montoImpuesto1) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		montoImpuesto1 = montoImpuesto1 == null ? Constantes.ZEROS_DOUBLE : montoImpuesto1;
		montoImpuesto = montoImpuesto == null ? Constantes.ZEROS_DOUBLE : montoImpuesto;
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			if (!tipoImpuesto.equals(Constantes.EMPTY)) {
				resultado = montoTotal;
			}
		}
		return resultado;
	}

	public static Double getTotalServicioGravadosSubTotal(String tipoImpuesto, String unidadMedida, Double montoTotal, Double impuesto, Integer porcentajeExoneracion) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		impuesto = impuesto == null ? Constantes.ZEROS_DOUBLE : impuesto;

		porcentajeExoneracion = porcentajeExoneracion == null ? Constantes.ZEROS : porcentajeExoneracion;
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			if (!tipoImpuesto.equals(Constantes.EMPTY)) {
				if (porcentajeExoneracion > Constantes.ZEROS_DOUBLE) {
					resultado = (1 - (porcentajeExoneracion / impuesto)) * montoTotal;
				} else {
					resultado = montoTotal;
				}

			}
		}
		return resultado;
	}

	/**
	 * @param tipoDocumentoExonerado
	 * @param porcentajeExonerado
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param montoExonerado
	 * @return
	 */
	public static Double getImpuestoNetoTotal(Double montoImpuesto, Double montoExonerado) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		montoImpuesto = montoImpuesto == null ? Constantes.ZEROS_DOUBLE : montoImpuesto;
		montoExonerado = montoExonerado == null ? Constantes.ZEROS_DOUBLE : montoExonerado;
		resultado = montoImpuesto - montoExonerado;
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : Utils.Maximo5Decimales(resultado);

	}

	/**
	 * Monto de exonaracion = monto del impuesto * porcentaje de la exoneracion
	 * @param tipodocumento
	 * @param porcentajeExoneracion
	 * @param montoImpuesto
	 * @return
	 */
	public static Double getMontoExoneracion(String tipoDocumentoExonerado, Integer porcentajeExoneracion, Double montoImpuesto) {
		montoImpuesto = montoImpuesto == null ? Constantes.ZEROS_DOUBLE : montoImpuesto;
		tipoDocumentoExonerado = tipoDocumentoExonerado == null ? Constantes.EMPTY : tipoDocumentoExonerado;
		porcentajeExoneracion = porcentajeExoneracion == null ? Constantes.ZEROS : porcentajeExoneracion;

		if (tipoDocumentoExonerado.equals(Constantes.EMPTY)) {
			return Constantes.ZEROS_DOUBLE;
		}

		// Double porcentaje = Double.parseDouble(porcentajeExoneracion.toString()) / 100;
		Double resultado = montoImpuesto * porcentajeExoneracion;
		resultado = resultado / 100d;
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : Utils.Maximo5Decimales(resultado);
	}

	public static Double getMontoExoneracionSubTotal(String tipoDocumentoExonerado, Double impuesto, Integer porcentajeExoneracion, Double subTotal, Double montoImpuesto) {
		impuesto = impuesto == null ? Constantes.ZEROS_DOUBLE : impuesto;
		montoImpuesto = montoImpuesto == null ? Constantes.ZEROS_DOUBLE : montoImpuesto;

		subTotal = subTotal == null ? Constantes.ZEROS_DOUBLE : subTotal;
		tipoDocumentoExonerado = tipoDocumentoExonerado == null ? Constantes.EMPTY : tipoDocumentoExonerado;
		porcentajeExoneracion = porcentajeExoneracion == null ? Constantes.ZEROS : porcentajeExoneracion;
		if (tipoDocumentoExonerado.equals(Constantes.EMPTY)) {
			return Constantes.ZEROS_DOUBLE;
		}

		Double resultado = Constantes.ZEROS_DOUBLE;
//		if (impuesto == porcentajeExoneracion.intValue()) {
//			resultado = montoImpuesto;
//		} else {
		 
			resultado = subTotal * porcentajeExoneracion;
			resultado = resultado / 100d;

//		}
			

		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : Utils.Maximo5Decimales(resultado);
	}

	public static Boolean aplicarExoneracionSubTotal4_3() throws ParseException {
		String fechaF2 = "2020-07-01";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date fecha1 = new Date();
		Date antesCambioFormulaExoneracion = formato.parse(fechaF2);

		if (fecha1.before(antesCambioFormulaExoneracion)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	/**
	 * Es la suma del subtotal + primer impuesto por la tarifa sacamos el impuesto 13
	 * @param subTotal
	 * @param montoPrimerImpuesto
	 * @param tarifa
	 * @return
	 */
	public static Double getMontoImpuesto(Double subTotal, Double montoPrimerImpuesto, Double montoExoneracion1, Double tarifa) {
		subTotal = subTotal == null ? Constantes.ZEROS_DOUBLE : subTotal;
		montoPrimerImpuesto = montoPrimerImpuesto == null ? Constantes.ZEROS_DOUBLE : montoPrimerImpuesto;
		montoExoneracion1 = montoExoneracion1 == null ? Constantes.ZEROS_DOUBLE : montoExoneracion1;
		tarifa = tarifa == null ? Constantes.ZEROS_DOUBLE : tarifa;

		Double resultado = Constantes.ZEROS_DOUBLE;
		if (montoExoneracion1 > Constantes.ZEROS_DOUBLE) {
			resultado = montoPrimerImpuesto - montoExoneracion1;
			resultado = subTotal + resultado;
		} else {
			resultado = subTotal + montoPrimerImpuesto;
		}
		resultado = resultado * tarifa;
		resultado = resultado / 100d;

		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : Utils.Maximo5Decimales(resultado);
	}

	/**
	 * SubTotal = Monto Total - Monto Descuento
	 * @param montoDescuento
	 * @param montoTotal
	 * @return
	 */
	public static Double getSubtotal(Double montoTotal, Double montoDescuento) {
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		montoDescuento = montoDescuento == null ? Constantes.ZEROS_DOUBLE : montoDescuento;
		Double resultado = montoTotal - montoDescuento;
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : Utils.Maximo5Decimales(resultado);
	//	return resultado;
	}

	/**
	 * Total Descuento de la linea
	 * @param montoTotal
	 * @param porcentajeDescuento
	 * @return
	 */
	public static Double getDescuento(Double montoTotal, Double porcentajeDescuento) {
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		porcentajeDescuento = porcentajeDescuento == null ? Constantes.ZEROS_DOUBLE : porcentajeDescuento;
		BigDecimal bd1 = new BigDecimal(montoTotal);
		BigDecimal bd2 = new BigDecimal(porcentajeDescuento);
		bd1 = bd1.multiply(bd2);
		// Double resultado = montoTotal * porcentajeDescuento;
		BigDecimal divisor = new BigDecimal(100d);
		BigDecimal resultado = bd1.divide(divisor);
		Double valor = resultado.doubleValue();
		return Utils.aplicarRedondeo(valor) ? Utils.roundFactura(valor, 5) : Utils.Maximo5Decimales(valor);
	}

	public static Double getMontoConRedondeo(Double monto) {
		monto = monto == null?Constantes.ZEROS_DOUBLE:monto;
		return Utils.aplicarRedondeo(monto) ? Utils.roundFactura(monto, 5) : Utils.Maximo5Decimales(monto);
	}
	/**
	 * Monto Total
	 * @param precioUnitario
	 * @param cantidad
	 * @return
	 */
	public static Double getMontoTotal(Double precioUnitario, Double cantidad) {
		cantidad = cantidad == null ? Constantes.ZEROS_DOUBLE : cantidad;
		precioUnitario = precioUnitario == null ? Constantes.ZEROS_DOUBLE : precioUnitario;
		Double resultado = precioUnitario * cantidad;
    //return resultado;
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : Utils.Maximo5Decimales(resultado);
	}

	public static Double getGananciaProducto(Double precioUnitario, Double costo, Double montoDescuento) {
		montoDescuento = montoDescuento == null ? Constantes.ZEROS_DOUBLE : montoDescuento;
		costo = costo == null ? Constantes.ZEROS_DOUBLE : costo;
		precioUnitario = precioUnitario == null ? Constantes.ZEROS_DOUBLE : precioUnitario;
		// si el costo supera al precio unitario el costo es cero
		if (costo > precioUnitario) {
			costo = Constantes.ZEROS_DOUBLE;
		}
		Double resultado = Constantes.ZEROS_DOUBLE;
		Double descuento = montoDescuento != null ? montoDescuento : Constantes.ZEROS_DOUBLE;

		resultado = precioUnitario > costo ? precioUnitario - costo : Constantes.ZEROS_DOUBLE;

		return Utils.roundFactura(resultado - descuento, 5);
	}

	public static Double getPorcentajeGananciaProducto(Double precioUnitario, Double costo) {
		costo = costo == null ? Constantes.ZEROS_DOUBLE : costo;
		precioUnitario = precioUnitario == null ? Constantes.ZEROS_DOUBLE : precioUnitario;
		// si el costo supera al precio unitario el costo es cero
		if (costo > precioUnitario) {
			return 100d;
		}
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (costo > Constantes.ZEROS_DOUBLE) {
			resultado = costo / precioUnitario;
			resultado = 1 - resultado;

		}

		return Utils.roundFactura(resultado * 100, 5);
	}

	/**
	 * Total exonerado si el detalle tiene exoneracion
	 * @param SubTotal
	 * @param montoExonerado
	 * @return
	 */
	public static Double getTotalExonerado(Double totalServExonerado, Double totalMercExonerada) {
		totalMercExonerada = totalMercExonerada == null ? Constantes.ZEROS_DOUBLE : totalMercExonerada;
		totalServExonerado = totalServExonerado == null ? Constantes.ZEROS_DOUBLE : totalServExonerado;
		Double resultado = totalServExonerado + totalMercExonerada;
		return resultado;
	//	return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;
	}

	public static Double getTotalMercExonerada(String tipoImpuesto, String unidadMedida, Double montoTotal, Integer porcentajeExoneracion) {
		porcentajeExoneracion = porcentajeExoneracion == null ? Constantes.ZEROS : porcentajeExoneracion;
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;

		Double resultado = Constantes.ZEROS_DOUBLE;
		Boolean esGravado = Boolean.TRUE;
		if (tipoImpuesto.equals(Constantes.EMPTY)) {
			esGravado = Boolean.FALSE;
		}
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esGravado = Boolean.FALSE;
		}
		if (esGravado && porcentajeExoneracion > Constantes.ZEROS) {
			Double porcentaValor = porcentajeExoneracion / 100d;
			resultado = montoTotal * porcentaValor;

		}
		return resultado;
	}

	public static Double getTotalMercExoneradaSubTotal(String tipoImpuesto, String unidadMedida, Integer porcentajeExonerado, Double impuesto, Double montoTotal) {
		impuesto = impuesto == null ? Constantes.ZEROS_DOUBLE : impuesto;

		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		porcentajeExonerado = porcentajeExonerado == null ? Constantes.ZEROS : porcentajeExonerado;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		Double resultado = Constantes.ZEROS_DOUBLE;
		Boolean esMercancia = Boolean.TRUE;
		if (tipoImpuesto.equals(Constantes.EMPTY) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esMercancia = Boolean.FALSE;
		}
		if (esMercancia && porcentajeExonerado > Constantes.ZEROS_DOUBLE) {

			Double totalIVA = porcentajeExonerado / impuesto;

			resultado = montoTotal * totalIVA;

		}
		return resultado;
	}

	/**
	 * @param tipoImpuesto
	 * @param unidadMedida
	 * @param montoExonerado
	 * @return
	 */
	public static Double getTotalServExonerado(String tipoImpuesto, String unidadMedida, Double montoExonerado) {
		montoExonerado = montoExonerado == null ? Constantes.ZEROS_DOUBLE : montoExonerado;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		Double resultado = Constantes.ZEROS_DOUBLE;

		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			resultado = montoExonerado;

		}
		return resultado;
	}

	public static Double getTotalServExoneradoSubTotal(String tipoImpuesto, String unidadMedida, Integer porcentajeExonerado, Double impuesto, Double montoTotal) {
		impuesto = impuesto == null ? Constantes.ZEROS_DOUBLE : impuesto;

		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		porcentajeExonerado = porcentajeExonerado == null ? Constantes.ZEROS : porcentajeExonerado;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		Double resultado = Constantes.ZEROS_DOUBLE;

		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {

			if (porcentajeExonerado > Constantes.ZEROS_DOUBLE) {
				Double totalIVA = porcentajeExonerado / impuesto;

				resultado = montoTotal * totalIVA;
			}
		}
		return resultado;
	}

	/**
	 * Total de impuesto
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @return
	 */
	public static Double getTotalImpuesto(Double montoImpuestoNeto) {
		montoImpuestoNeto = montoImpuestoNeto == null ? Constantes.ZEROS_DOUBLE : montoImpuestoNeto;
		return Utils.Maximo5Decimales(montoImpuestoNeto);
	}

	public static Double getMontoDescuento(Double monto, Double total) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		resultado += total + monto;

		return Utils.Maximo5Decimales(resultado);
	}

	/**
	 * Monto Linea
	 * @param subTotal
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param montoImpuestoNeto
	 * @param tipoDocumentoExoneracion
	 * @return
	 */
	public static Double getMontoTotalLinea(Double subTotal, Double montoImpuestoNeto) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		montoImpuestoNeto = montoImpuestoNeto == null ? Constantes.ZEROS_DOUBLE : montoImpuestoNeto;
		subTotal = subTotal == null ? Constantes.ZEROS_DOUBLE : subTotal;
		BigDecimal subTotalB = new BigDecimal(subTotal);
		BigDecimal montoImpuestoNetoB = new BigDecimal(montoImpuestoNeto);
		subTotalB = subTotalB.add(montoImpuestoNetoB);

		resultado = subTotalB.doubleValue();
		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : Utils.Maximo5Decimales(resultado);
	}

	/**
	 * Total de servicios exentos
	 * @param tipoImpuesto
	 * @param unidadMedida
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param subTotal
	 * @return
	 */
	public static Double getTotalServExentos(String tipoImpuesto, String unidadMedida, Double montoImpuesto, Double montoImpuesto1, Double montoTotal) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		montoImpuesto = montoImpuesto == null ? Constantes.ZEROS_DOUBLE : montoImpuesto;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		Boolean esMercancia = Boolean.TRUE;
		if (!tipoImpuesto.equals(Constantes.EMPTY)) {
			esMercancia = Boolean.FALSE;
		}
		if (!unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) && !unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esMercancia = Boolean.FALSE;
		}
		if (esMercancia) {
			if (montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
				resultado = montoTotal;
			}

		}
		return resultado;
	}

	/**
	 * Total Mercancias Excentas
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param subTotal
	 * @return
	 */
	public static Double getTotalMercanciasExentas(String tipoImpuesto, String unidadMedida, Double montoImpuesto, Double montoImpuesto1, Double montoTotal) {
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		montoImpuesto1 = montoImpuesto1 == null ? Constantes.ZEROS_DOUBLE : montoImpuesto1;
		montoImpuesto = montoImpuesto == null ? Constantes.ZEROS_DOUBLE : montoImpuesto;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		Double resultado = Constantes.ZEROS_DOUBLE;
		Boolean esMercancia = Boolean.TRUE;

		if (!tipoImpuesto.equals(Constantes.EMPTY)) {
			esMercancia = Boolean.FALSE;
		}
		if (unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esMercancia = Boolean.FALSE;
		}
		if (esMercancia) {

			if (montoImpuesto1.equals(Constantes.ZEROS_DOUBLE) && montoImpuesto.equals(Constantes.ZEROS_DOUBLE)) {
				resultado = montoTotal;
			}

		}
		return resultado;
	}

	/**
	 * Total Mercancia Gravada
	 * @param tipoImpuesto
	 * @param unidadMedida
	 * @param montoImpuesto
	 * @param montoImpuesto1
	 * @param subTotal
	 * @param porcentajeExoneracion
	 * @return
	 */
	public static Double getTotalMercanciasGravadas(String tipoImpuesto, String unidadMedida, Double montoImpuesto, Double montoImpuesto1, Double montoTotal, Integer porcentajeExoneracion) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		Boolean esMercancia = Boolean.TRUE;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		montoImpuesto = montoImpuesto == null ? Constantes.ZEROS_DOUBLE : montoImpuesto;
		montoImpuesto1 = montoImpuesto1 == null ? Constantes.ZEROS_DOUBLE : montoImpuesto1;
		porcentajeExoneracion = porcentajeExoneracion == null ? Constantes.ZEROS : porcentajeExoneracion;

		if (tipoImpuesto.equals(Constantes.EMPTY) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esMercancia = Boolean.FALSE;
		}
		if (esMercancia) {
			if (montoImpuesto1 > Constantes.ZEROS_DOUBLE || montoImpuesto > Constantes.ZEROS_DOUBLE || !tipoImpuesto.equals(Constantes.EMPTY)) {
				if (porcentajeExoneracion > Constantes.ZEROS) {
					Double porcentaValor = Constantes.ZEROS_DOUBLE;
					porcentaValor = 1 - porcentaValor;
					resultado = montoTotal * porcentaValor;
				} else {
					resultado = montoTotal;
				}
			}

		}
		return resultado;
	}

//	Mercancías Gravadas= (1 - (Tarifa Exonerada IVA * 100%) / Tarifa IVA) * Monto Total de la línea
	public static Double getTotalMercanciasGravadasSubTotal(String tipoImpuesto, String unidadMedida, Double montoTotal, Double impuesto, Integer porcentajeExoneracion) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;
		unidadMedida = unidadMedida == null ? Constantes.EMPTY : unidadMedida;
		montoTotal = montoTotal == null ? Constantes.ZEROS_DOUBLE : montoTotal;
		impuesto = impuesto == null ? Constantes.ZEROS_DOUBLE : impuesto;

		porcentajeExoneracion = porcentajeExoneracion == null ? Constantes.ZEROS : porcentajeExoneracion;
		Boolean esMercancia = Boolean.TRUE;
		if (tipoImpuesto.equals(Constantes.EMPTY) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SP) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_OS) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_SPE) || unidadMedida.equals(Constantes.UNIDAD_MEDIDA_SERVICIO_ST)) {
			esMercancia = Boolean.FALSE;
		}
		if (esMercancia) {
			if (!tipoImpuesto.equals(Constantes.EMPTY)) {
				if (porcentajeExoneracion > 0) {
					resultado = (1 - (porcentajeExoneracion / impuesto)) * montoTotal;
				} else {
					resultado = montoTotal;
				}

			}
		}
		return resultado;
	}

	/**
	 * @param tipoImpuesto
	 * @param subTotal
	 * @return
	 */
	public static Double getBaseImponibleTotal(String tipoImpuesto, Double subTotal, Integer activoBaseImponible) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		subTotal = subTotal == null ? Constantes.ZEROS_DOUBLE : subTotal;
		activoBaseImponible = activoBaseImponible == null ? Constantes.ZEROS : activoBaseImponible;
		tipoImpuesto = tipoImpuesto == null ? Constantes.EMPTY : tipoImpuesto;

		if (tipoImpuesto.equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL) && activoBaseImponible.equals(Constantes.BASE_IMPONIBLE_ACTIVO)) {
			return subTotal;

		}

		return Utils.aplicarRedondeo(resultado) ? Utils.roundFactura(resultado, 5) : resultado;

	}

	public static Integer fechaDiaAnterior(Date fechaAnterior, Date fechaHoy) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String hoy = dateFormat.format(fechaHoy);
		String anterior = dateFormat.format(fechaAnterior);

		Date fechaInicial = dateFormat.parse(anterior);
		Date fechaFinal = dateFormat.parse(hoy);

		int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);

		return dias;
	}

	public static Boolean validarCedulaDiferenteCaracter(String valor) {
		Boolean resultado = Boolean.FALSE;
		if (valor == null) {
			return Boolean.FALSE;
		}
		if (valor.length() == 0) {
			return Boolean.FALSE;
		}
		char letraIgual = valor.charAt(1);
		for (int i = 0; i < valor.length(); i++) {
			char c = valor.charAt(i);
			if (letraIgual != c) {
				resultado = Boolean.TRUE;
			}
		}
		return resultado;
	}

	public static Double Maximo5Decimales(Double valor) {
		Double resultado = Constantes.ZEROS_DOUBLE;
		if (valor == null) {
			return resultado;
		}
		if (valor.equals(Constantes.ZEROS_DOUBLE)) {
			return resultado;
		}
		String cadena = Constantes.EMPTY;
		String verificar = valor.toString();
		if (verificar.contains("E")) {
			BigDecimal resultadoDecimal = new BigDecimal(valor);
			cadena = resultadoDecimal.toString();
			if (!cadena.contains(".")) {
				return valor;
			}

		} else {
			cadena = valor.toString();
		}

		String[] splitter = cadena.toString().split("\\.");
		splitter[0].length(); // Before Decimal Count
		splitter[1].length(); // After Decimal Count
		String digitos = splitter[1];
		if (splitter[1].length() >= 5) {
			String decimales = digitos.substring(0, 5);
			String valor1 = splitter[0] + "." + decimales;
			resultado = Double.parseDouble(valor1);
		} else {
			String decimales = digitos.substring(0, splitter[1].length());
			String valor1 = splitter[0] + "." + decimales;
			resultado = Double.parseDouble(valor1);

		}

		return resultado;

	}

	/**
	 * Si el sexto digito es mayor 5 o igual
	 * @param valor
	 * @return
	 */
	public static Boolean aplicarRedondeo(Double valor) {
		valor = valor == null ? Constantes.ZEROS_DOUBLE : valor;
		Boolean resultado = Boolean.FALSE;
		String cadena = Constantes.EMPTY;
		String verificar = valor.toString();
		if (verificar.contains("E")) {
			BigDecimal resultadoDecimal = new BigDecimal(valor);
			cadena = resultadoDecimal.toString();
			if (!cadena.contains(".")) {
				return resultado;
			}
		} else {
			cadena = valor.toString();
		}
		String[] splitter = cadena.toString().split("\\.");
		splitter[0].length(); // Before Decimal Count
		splitter[1].length(); // After Decimal Count
		if (splitter[1].length() > 5) {
			String digitos = splitter[1];
			int num = Character.getNumericValue(digitos.charAt(5));
			if (num >= 5) {
				resultado = Boolean.TRUE;
			}
		}

		return resultado;

	}

	public static Date sumarDiasFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}

	public static String formateadorMiles(Double valor) {
		String resultado = Constantes.EMPTY;

		if (valor == null) {
			valor = 0d;

		}

		DecimalFormat formateador = new DecimalFormat("#,##0.0");
		resultado = formateador.format(valor);

		return resultado;
	}

	public static String formateadorMilesExcel(Double valor) {
		String resultado = Constantes.EMPTY;

		if (valor == null) {
			valor = 0d;

		}

		DecimalFormat formateador = new DecimalFormat("########0,00");
		resultado = formateador.format(valor);

		return resultado;
	}

	public static String formateadorMilesSinDecimales(Double valor) {
		String resultado = Constantes.EMPTY;

		if (valor == null) {
			valor = 0d;

		}

		DecimalFormat formateador = new DecimalFormat("###,###,##0");
		resultado = formateador.format(valor);

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

	public static Date parseDateImpuestoServicio(String dateString) {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		try {
			// parse() will throw an exception if the given dateString doesn't
			// match
			// the current format
			date = dateFormat.parse(dateString);
//			date = new SimpleDateFormat("ddMMyyyyhhmmss").parse(dateString);

		} catch (ParseException e) {
			// don't do anything. just let the loop continue.
			// we may miss on 99 format attempts, but match on one format,
			// but that's all we need.
		}

		return date;
	}

	public static Date parseDateExoneracion(String dateString) {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			// parse() will throw an exception if the given dateString doesn't
			// match
			// the current format
			date = dateFormat.parse(dateString);
//			date = new SimpleDateFormat("ddMMyyyyhhmmss").parse(dateString);

		} catch (ParseException e) {
			// don't do anything. just let the loop continue.
			// we may miss on 99 format attempts, but match on one format,
			// but that's all we need.
		}

		return date;
	}

	public static Date parseDateImpuestoServicio1(String dateString) {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

		try {
			// parse() will throw an exception if the given dateString doesn't
			// match
			// the current format
			date = dateFormat.parse(dateString);
//			date = new SimpleDateFormat("ddMMyyyyhhmmss").parse(dateString);

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

	public static void rejectIfNotValidEmail(Errors errors, String campo) {
		String email = (String) errors.getFieldValue(campo);
		if (email.length() > 0) {
			if (Constantes.LONGITUD_EMAIL < email.length()) {
				errors.rejectValue(campo, Constantes.KEY_LONGITUD_INCORRECTA, new Object[] { Constantes.LONGITUD_EMAIL }, "");
			} else if (!email.matches(Constantes.PATRON_EMAIL)) {
				errors.rejectValue(campo, Constantes.KEY_EMAIL_FORMATO_INCORRECTO);
			}
		}
	}

	public static void rejectIfNotValidCedulaFisica(Errors errors, String campo) {
		String cedula = (String) errors.getFieldValue(campo);
		if (!(cedula.matches(Constantes.PATRON_CEDULA_FISICA_NACIONALES) || cedula.matches(Constantes.PATRON_CEDULA_FISICA_OTROS))) {
			errors.rejectValue(campo, Constantes.KEY_CEDULA_FISICA_FORMATO_INCORRECTO);
		}
	}

	public static void rejectIfNotValidCedulaJuridica(Errors errors, String campo) {
		String cedula = (String) errors.getFieldValue(campo);
		if (!(cedula.matches(Constantes.PATRON_CEDULA_JURIDICA_AUTONOMA) || cedula.matches(Constantes.PATRON_CEDULA_JURIDICA_GOBIERNO) || cedula.matches(Constantes.PATRON_CEDULA_JURIDICA_PERSONERIA))) {
			errors.rejectValue(campo, Constantes.KEY_CEDULA_JURIDICA_FORMATO_INCORRECTO);
		}
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

	/* Metodo que devuelve el Numero total de minutos que hay entre las dos Fechas */
	public static long cantidadTotalMinutos(Calendar fechaInicial, Calendar fechaFinal) {

		long totalMinutos = 0;
		totalMinutos = ((fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000 / 60);
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

	public static String fechaExoneracionSTR(Date fecha) {
		if (fecha == null) {
			return Constantes.EMPTY;
		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT6);
		String inicio1 = dateFormat1.format(fecha);
		return inicio1;
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

		return date != null ? fechaCompleta.format(date) : Constantes.EMPTY;
	}

	public static String getFechaGeneraHacienda(Date date) {
		DateFormat fechaCompleta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a ");

		return date != null ? fechaCompleta.format(date) : Constantes.EMPTY;
	}

	public static String getFechaStr(Date date) {
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		return date != null ? fecha.format(date) : Constantes.EMPTY;
	}

	public static String getHoraStr(Date date) {
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		return date != null ? hora.format(date) : Constantes.EMPTY;
	}

	public static String getFechaHoraStr(Date date) {
		DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat hora = new SimpleDateFormat("hh:mm:ss");
		String fechaStr = fecha.format(date);
		String horaStr = hora.format(date);
		return fechaStr + " " + horaStr;
	}

	public static String getFechat(Date date) {
		DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		String fechaStr = fecha.format(date);
		return fechaStr;
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
		if (value == 0 ){
			return 0d;
		}

		if (places < 0)
			throw new IllegalArgumentException();

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
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(correo);

		if (mather.find() == true) {
			resultado = true;
		}

		return resultado;
	}

	public static String obtenerTipoDocumentoConsecutivo(String consecutivo) {
		// 001 + 00004 + 01 + 0000003888
		return consecutivo == null?"ne":consecutivo.substring(8, 10); // Se retorna 01
	}

	public static String obtenerDescripcionTipoDocumento(String tipoDocumento) {
		tipoDocumento = tipoDocumento == null?Constantes.EMPTY:tipoDocumento;
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

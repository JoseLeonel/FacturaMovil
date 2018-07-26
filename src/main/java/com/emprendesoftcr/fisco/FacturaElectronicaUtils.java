package com.emprendesoftcr.fisco;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;

import org.json.JSONObject;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.error.SignException;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.google.common.io.Files;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

public final class FacturaElectronicaUtils {

	public static final Integer	CODIGO_PAIS														= 506;
	// Comprobante electronico para formar la clave
	public static final Integer	COMPROBANTE_ELECTRONICO_NORMAL				= 1;
	public static final Integer	COMPROBANTE_ELECTRONICO_CONTIGENCIA		= 2;
	public static final Integer	COMPROBANTE_ELECTRONICO_SIN_INTERNET	= 3;

	// Formatos
	public static final String	FORMATO_DIA														= "00";
	public static final String	FORMATO_MES														= "00";
	public static final String	FORMATO_ANO														= "00";

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
		Integer ano = fecha.get(Calendar.YEAR)-2000;
		Integer mes = fecha.get(Calendar.MONTH)+1 ;
		Integer dia = fecha.get(Calendar.DAY_OF_MONTH);

		String primeroTress = FacturaElectronicaUtils.replazarConZeros(CODIGO_PAIS.toString(),"000") ;
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
	 * Convertir a 5 decimales para la factura
	 * @param valor
	 * @return
	 */
	public static BigDecimal getConvertirBigDecimal(Double valor) {
		valor= valor == null? Constantes.ZEROS_DOUBLE : valor;
		 BigDecimal bd = new BigDecimal(Double.toString(valor));
		 bd = bd.setScale(5, RoundingMode.HALF_UP);
		return bd;
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

	 /**
   * Codifica un texto usando sha256 retornandolo en base64
   * @param txt Texto a ser codificado
   * @return Texto codificado en base64
   */
  public static String digest(String txt) {
      return BaseEncoding.base64().encode(Hashing.sha256()
          .hashString(txt, StandardCharsets.UTF_8)
          .asBytes());
  }

  /**
   * Transforma un texto a su expresi�n en base64
   * @param text Texto a ser transformado a base64
   * @return Texto transformado a base64
   */
  public static String base64Decode(String text) {
      return new String(BaseEncoding.base64().decode(text), StandardCharsets.UTF_8);
  }

  /**
   * Transforma un arreglo de bytes a su expresi�n en base64
   * @param btext Arreglo de bytes a ser transformado a base64
   * @return Arreglo de bytes transformado a base64
   */
  public static String base64Encode(byte[] btext) {
      return BaseEncoding.base64().encode(btext);
  }

  /**
   * Codifica el texto de un archivo usando sha256 retornandolo en base64
   * @param strFile Archivo que contiene el texto a ser transformado a base64
   * @return Texto codificado en base64
   * @throws IOException Excepci�n arrojada por el procesamiento del archivo
   * @throws NoSuchAlgorithmException Excepci�n arrojada si el algoritmo no existe
   */
  public static String sha256(String strFile) throws IOException, NoSuchAlgorithmException {
      File f = new File(strFile);
      InputStream stream = Files.asByteSource(f).openStream();
      final byte[] buffer = new byte[1024 * 1024];
      final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
      int bytesRead = 0;
      while ((bytesRead = stream.read(buffer)) >= 0) {
          if (bytesRead > 0) {
              sha256.update(buffer, 0, bytesRead);
          }
      }
      return BaseEncoding.base64().encode(sha256.digest());
  }

  /**
   * Compara un texto contra una expresi�n regular y retorna el primer match
   * @param txt Texto a ser comparado
   * @param reg Expresi�n regular
   * @return Primer match arrojado por la comparatica, null en caso de que no haya match
   */
  public static String match(String txt, String reg) {
      ImmutableList.Builder<String> bmtcs = ImmutableList.builder();
      Matcher matcher = Pattern.compile(reg).matcher(txt);
      while ( matcher.find() ) {
          bmtcs.add(matcher.group(1));
      }        
      ImmutableList<String> mtcs = bmtcs.build();        
      return mtcs.size() > 0 ? mtcs.get(0) : null;
  }

  /**
   * Lee un archivo y retorna su contenido
   * @param filePath Archivo a ser leido
   * @return Contenido del archivo
   * @throws IOException Excepci�n arrojada por el procesamiento del archivo
   */
  public static String readFile(String filePath) throws IOException {
      File file = new File(filePath);
      return Files.asCharSource(file, Charsets.UTF_8).read();
  }

  /**
   * Convierte una fecha a su expresi�n en ISO 8601
   * @param date Fecha a ser convertida
   * @return Fecha en su expresi�n ISO 8601
   */
  public static String toISO8601String(Date date) {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
      dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      return dateFormat.format(date);
  }

  /**
   * Firma un texto utilizando una llave privada
   * @param input Texto a ser firmado
   * @param privateKey Llave privada con que se firmar� el texto
   * @return Texto cifrado con la llave especificada
   * @throws SignException Excepci�n del componente para contener excepciones en la firma
   */
  public static String signSHA256RSA(String input, String privateKey) throws SignException {
      try {
          String realPK = privateKey.replaceAll("-----END PRIVATE KEY-----", "")
              .replaceAll("-----BEGIN PRIVATE KEY-----", "")
              .replaceAll("\n", "");
          byte[] b1 = Base64.getDecoder().decode(realPK);
          PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
          KeyFactory kf = KeyFactory.getInstance("RSA");
          Signature privateSignature = Signature.getInstance("SHA256withRSA");
          privateSignature.initSign(kf.generatePrivate(spec));
          privateSignature.update(input.getBytes("UTF-8"));
          byte[] s = privateSignature.sign();
          return Base64.getEncoder().encodeToString(s);
      } catch(NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | UnsupportedEncodingException | SignatureException exc) {
          throw SignException.instance(exc.getMessage(), exc.getCause());
      }
  }
  /**
   * Normaliza una direcci�n en disco
   * @param input Direcci�n en disco a ser normalizada
   * @return Direcci�n en disco normaliza
   */
  public static String normalize(String input) {
      Path path = Paths.get("/", input).normalize();
      String newPath = path.toString();
      return newPath.endsWith("/") || newPath.endsWith("\\") 
              ? newPath.substring(0, newPath.length() - 1) : newPath;
  }
  /**
   * Extrae el texto de un objeto Process
   * @param prc Proceso desde el que se extraer� el texto
   * @return Texto contenido en el objeto Process
   */
  public static String processToString(Process prc) { 
      Scanner s = new Scanner(prc.getInputStream()).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
  }
  
  /**
   * Da formato a un valor para ser utilizado en la impresi�n del json
   * @param value Texto a ser formateado
   * @param isKey Indica si el valor es un key o un valor en el json
   * @return Texto formateado
   */
  public static String jsonElement(String value, boolean isKey) {
      return "\"" + value + "\"" + (isKey ? ": " : "");
  }

  /**
   * Retorna un elemento de tipo objeto en su expresi�n json
   * @param object Objeto a ser retornado
   * @return Expresi�n json del objeto
   */
  public static String jsonObject(Object object) {
      return object == null ? "null" : object.toString();
  }

  /**
   * Valida si un elemento se encuentra dentro de un json y si no es null
   * @param jsonObject Objeto json
   * @param key Key a ser validado dentro del objeto json
   * @return Booleano que indica si el key existe y no es null
   */
  public static boolean getJsonObject(JSONObject jsonObject, String key) {
      return jsonObject.has(key) && !jsonObject.isNull(key);
  }
  
  
  public static Blob convertirStringToblod(String valor) throws SerialException, SQLException {
  	Blob b = new javax.sql.rowset.serial.SerialBlob(valor.getBytes());
  	return b;
  }
  
  public static String convertirBlodToString(Blob valor) throws SerialException, SQLException {
  
  	byte[] bdata = valor.getBytes(1, (int) valor.length());
  	String text = new String(bdata);
  	
  	return text;
  	
  }
  
  public static String getTipoDocumento(String valor) {
  	
  	if(valor.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
  		return "Factura electrónico";
  		
  	}else if(valor.equals(Constantes.FACTURA_TIPO_DOC_TIQUETE)) {
  		return "Tiquete electrónico";
  		
  	}else if(valor.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
  		return "Nota de crédito";
  		
  	}else if(valor.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
  		return "Nota de débito";
  		
  	}
  	
  	return Constantes.EMPTY;
  }
  
  
  /**
   * Crear el QR
   * @param claveFactura
   * @param direccion
   * @return
   */
  public static File crearQR(String claveFactura,String direccion) {
  	 
    
    File f = new File(direccion);
 
    try {

        f = generateQR(f, claveFactura, 300, 300);
      

 

    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return f;

}
  
  public static File generateQR(File file, String text, int h, int w) throws Exception {
  	 
    QRCodeWriter writer = new QRCodeWriter();
    BitMatrix matrix = writer.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, w, h);

    BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
    image.createGraphics();

    Graphics2D graphics = (Graphics2D) image.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, matrix.getWidth(), matrix.getHeight());
    graphics.setColor(Color.BLACK);

    for (int i = 0; i < matrix.getWidth(); i++) {
        for (int j = 0; j < matrix.getHeight(); j++) {
            if (matrix.get(i, j)) {
                graphics.fillRect(i, j, 1, 1);
            }
        }
    }

    ImageIO.write(image, "png", file);

    return file;

}
  
//Lee el codigo de QR

public static String decoder(File file) throws Exception {

    FileInputStream inputStream = new FileInputStream(file);

    BufferedImage image = ImageIO.read(inputStream);

    int width = image.getWidth();
    int height = image.getHeight();
    int[] pixels = new int[width * height];

    LuminanceSource source = new BufferedImageLuminanceSource(image);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

    // decode the barcode
    QRCodeReader reader = new QRCodeReader();
    Result result = reader.decode(bitmap);
    return new String(result.getText());
}


public static String getMoneda(String codigo) {
	String resultado = "CRC-Colones Costa Rica";
	if(codigo.equals(Constantes.CODIGO_MONEDA_COSTA_RICA)) {
		resultado = "CRC-Colones Costa Rica";
	}else if(codigo.equals(Constantes.CODIGO_MONEDA_DOLAR)) {
		resultado = "USD-Dolares";
	}
	
	return resultado;
}

}

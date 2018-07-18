package com.emprendesoftcr.fisco;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.emprendesoftcr.error.P12Exception;
import com.emprendesoftcr.type.CertificateInfo;
import com.emprendesoftcr.type.json.CertificateInfoJson;

/**
 * @author Luis Matamoros luis.matamoros@efika.solutions
 * @version 1.0
 */
public class P12Utils {
	

	/**
	 * Extrae de un archivo p12 los siguientes elementos: Private key Certificado X509SerialNumber X509IssuerName Hash del certificado
	 * @param p12Path Archivo p12 a ser procesado
	 * @param password Password para leer el archivo p12
	 * @param certPath Direcci�n en disco donde se encuentra el archivo p12, si esta es null se usa la variable de entorno CERT_PATH
	 * @return Objeto de tipo CertificateInfo que contiene los datos Private key, Certificado, X509SerialNumber, X509IssuerName y Hash del certificado
	 * @throws P12Exception Error interno que captura excepciones en el procesamiento del archivo p12
	 */
	public static CertificateInfo dataFromP12(String  path,String p12Path, String password) throws P12Exception {
	
		String pemPath = FacturaElectronicaUtils.normalize(path+"pem.pem");
		String cerPath = FacturaElectronicaUtils.normalize(path+"cer.cer");
		try {
			String openssl = "openssl pkcs12 -in " + p12Path + " -passin pass:" + password + " -out " + pemPath + "  -nodes";
			Runtime.getRuntime().exec(openssl);
			openssl = "openssl x509 -outform der -in " + pemPath + " -out " + cerPath;
			Runtime.getRuntime().exec(openssl);
			String certHash = FacturaElectronicaUtils.sha256(cerPath);
			openssl = "openssl x509 -noout -text -serial -in " + pemPath;
			String txt = FacturaElectronicaUtils.processToString(Runtime.getRuntime().exec(openssl));
			String str = FacturaElectronicaUtils.readFile(pemPath);
			String privateKey = FacturaElectronicaUtils.match(str, "(-----BEGIN PRIVATE KEY-----\\n(?:\\S+\\n)+-----END PRIVATE KEY-----)");
			String certificate = FacturaElectronicaUtils.match(str, "-----BEGIN CERTIFICATE-----\\n((?:\\S+\\n)+)-----END CERTIFICATE-----").replaceAll("/\n/g", "");
			String x509SerialNumber = FacturaElectronicaUtils.match(txt, "serial=([0-9A-F]*)");
			String x509IssuerName = FacturaElectronicaUtils.match(txt, "Issuer: (.*)");
			return CertificateInfoJson.instance(privateKey, certificate, x509SerialNumber, x509IssuerName, certHash);
		} catch (IOException | NoSuchAlgorithmException | NullPointerException exc) {
			throw P12Exception.instance(exc.getMessage(), exc);
		}
	}
}

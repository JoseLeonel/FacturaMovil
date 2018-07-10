package com.emprendesoftcr.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.fisco.DirectPasswordProvider;
import com.emprendesoftcr.fisco.FirstCertificateSelector;
import com.emprendesoftcr.fisco.LlaveCriptografica;
import com.emprendesoftcr.service.LlaveCriptograficaService;

import xades4j.production.XadesEpesSigningProfile;
import xades4j.production.XadesSigner;
import xades4j.properties.ObjectIdentifier;
import xades4j.properties.SignaturePolicyBase;
import xades4j.properties.SignaturePolicyIdentifierProperty;
import xades4j.providers.BasicSignatureOptionsProvider;
import xades4j.providers.KeyingDataProvider;
import xades4j.providers.SignaturePolicyInfoProvider;
import xades4j.providers.SigningCertChainException;
import xades4j.providers.impl.FileSystemKeyStoreKeyingDataProvider;
import xades4j.verification.UnexpectedJCAException;

/**
 * Lectura de la llave criptografica LlaveCriptograficaServiceImpl.
 * @author jose.
 * @since 8 jul. 2018
 */
@Service("llaveCriptograficaService")
@Transactional
public class LlaveCriptograficaServiceImpl implements LlaveCriptograficaService {

	public XadesSigner getSigner(String keyPath, String password) throws CertificateException, IOException {
		XadesSigner xadesSigner = null;
		try {
			File keyFile = new ClassPathResource(keyPath).getFile();
			if (!keyFile.exists()) {
				System.err.println("Archivo clave no encuentrado en la ruta: " + keyPath);
			}
			xadesSigner = getSigne(password, keyFile.getPath());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return xadesSigner;
	}

	private XadesSigner getSigne(String password, String pfxPath) throws Exception {

		KeyingDataProvider keyingProvider = getKeyingDataProvider(pfxPath, password);
		SignaturePolicyInfoProvider policyInfoProvider = new SignaturePolicyInfoProvider() {

			public SignaturePolicyBase getSignaturePolicy() {
				return new SignaturePolicyIdentifierProperty(new ObjectIdentifier("https://tribunet.hacienda.go.cr/docs/esquemas/2017/v4.2/facturaElectronica"), new ByteArrayInputStream("Politica de Factura Digital".getBytes()));
			}
		};
//		BasicSignatureOptionsProvider basicSignatureOptionsProvider = null;
		XadesEpesSigningProfile p = new XadesEpesSigningProfile(keyingProvider, policyInfoProvider);
//		p.withBasicSignatureOptionsProvider(basicSignatureOptionsProvider);
		return p.newSigner();
	}

	private KeyingDataProvider getKeyingDataProvider(String pfxPath, String password) throws KeyStoreException, SigningCertChainException, UnexpectedJCAException {

		KeyingDataProvider keyingProvider = new FileSystemKeyStoreKeyingDataProvider("pkcs12", pfxPath, new FirstCertificateSelector(), new DirectPasswordProvider(password), new DirectPasswordProvider(password), false);
		if (keyingProvider.getSigningCertificateChain().isEmpty()) {
			throw new IllegalArgumentException("No se ha podido inicializar el keystore con la ruta: " + pfxPath);
		}
		return keyingProvider;
	}

	/**
	 * Leer la llave criptografica XXXXX.P12
	 * @see com.emprendesoftcr.service.LlaveCriptograficaService#getKeyStore(com.emprendesoftcr.fisco.LlaveCriptografica)
	 */
	@Override
	public KeyStore getKeyStore(LlaveCriptografica llaveCriptografica) throws CertificateException, IOException {
		KeyStore ks = null;
		try {
			File file = new ClassPathResource(llaveCriptografica.getPathSignature()).getFile();

			ks = KeyStore.getInstance("PKCS12");
			ks.load(new FileInputStream(file.getPath()), llaveCriptografica.getPassSignature().toCharArray());
		} catch (KeyStoreException e) {
			throw new IOException("Error: " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			throw new IOException("Error: " + e.getMessage());
		} catch (CertificateException e) {
			throw new IOException("Error: " + e.getMessage());
		} catch (IOException e) {
			throw new IOException("Error: " + e.getMessage());
		}
		return ks;

	}

	/**
	 * PrivateKey
	 * @see com.emprendesoftcr.service.LlaveCriptograficaService#getPrivateKey(java.security.KeyStore, com.emprendesoftcr.fisco.LlaveCriptografica)
	 */
	@Override
	public PrivateKey getPrivateKey(KeyStore keyStore, LlaveCriptografica llaveCriptografica) throws CertificateException, IOException {
		PrivateKey privateKey = null;
		String alias = getAlias(keyStore);

		try {
			privateKey = (PrivateKey) keyStore.getKey(alias, llaveCriptografica.getPassSignature().toCharArray());
		} catch (UnrecoverableKeyException e) {
			throw new IOException("No existe clave privada para firmar.");
		} catch (KeyStoreException e) {
			throw new IOException("No existe clave privada para firmar.");
		} catch (NoSuchAlgorithmException e) {
			throw new IOException("No existe clave privada para firmar.");
		}

		return privateKey;
	}

	/**
	 * X509Certificate
	 * @see com.emprendesoftcr.service.LlaveCriptograficaService#getX509Certificate(java.security.KeyStore)
	 */
	@Override
	public X509Certificate getX509Certificate(KeyStore keyStore) throws CertificateException, IOException {
		X509Certificate certificate = null;
		try {
			String alias = getAlias(keyStore);
			certificate = (X509Certificate) keyStore.getCertificate(alias);
			if (certificate == null) {
				throw new IOException("No existe ningún certificado para firmar.");
			}
		} catch (KeyStoreException e1) {
			throw new IOException("Error: " + e1.getMessage());
		}
		return certificate;
	}

	private static String getAlias(KeyStore keyStore) throws IOException {
		String alias = null;

		try {
			Enumeration nombres = keyStore.aliases();
			while (nombres.hasMoreElements()) {
				String tmpAlias = (String) nombres.nextElement();
				if (keyStore.isKeyEntry(tmpAlias)) {
					alias = tmpAlias;
				}
			}
		} catch (KeyStoreException e) {
			throw new IOException("Error: " + e.getMessage());
		}
		return alias;
	}

	/**
	 * Provider
	 * @see com.emprendesoftcr.service.LlaveCriptograficaService#getProvider(java.security.KeyStore)
	 */
	@Override
	public Provider getProvider(KeyStore keyStore) throws CertificateException, IOException {
		Provider provider = keyStore.getProvider();
		return provider;

	}
}

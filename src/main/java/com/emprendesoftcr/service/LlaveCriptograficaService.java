package com.emprendesoftcr.service;

import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import com.emprendesoftcr.fisco.LlaveCriptografica;

import xades4j.production.XadesSigner;

public interface LlaveCriptograficaService {

	KeyStore getKeyStore(LlaveCriptografica llaveCriptografica) throws CertificateException, IOException;

	X509Certificate getX509Certificate(KeyStore keyStore) throws CertificateException, IOException;

	PrivateKey getPrivateKey(KeyStore keyStore, LlaveCriptografica llaveCriptografica) throws CertificateException, IOException;
	
	Provider getProvider(KeyStore keyStore	) throws CertificateException, IOException;
	
	XadesSigner getSigner(String keyPath, String password) throws CertificateException, IOException;
}

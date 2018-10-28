package com.emprendesoftcr.fisco;

import java.security.KeyStore;

/**
 * Direccion donde se almacena la llaveCriptografica .p12
 * clave
 * LlaveCriptografica.
 * @author jose.
 * @since 7 jul. 2018
 */
public class LlaveCriptografica {

	
	private String	pathSignature;
	private String	passSignature;
	private KeyStore keyStore;

	
	
	public LlaveCriptografica(String pathSignature, String passSignature, KeyStore keyStore) {
		super();
		this.pathSignature = pathSignature;
		this.passSignature = passSignature;
		this.keyStore = keyStore;
	}


	public LlaveCriptografica() {
		super();
	}


	public String getPathSignature() {
		return pathSignature;
	}

	
	public void setPathSignature(String pathSignature) {
		this.pathSignature = pathSignature;
	}

	
	public String getPassSignature() {
		return passSignature;
	}

	
	public void setPassSignature(String passSignature) {
		this.passSignature = passSignature;
	}


	
	public KeyStore getKeyStore() {
		return keyStore;
	}


	
	public void setKeyStore(KeyStore keyStore) {
		this.keyStore = keyStore;
	}

	
	
}

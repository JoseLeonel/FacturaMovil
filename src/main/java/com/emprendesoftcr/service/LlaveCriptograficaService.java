package com.emprendesoftcr.service;

import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;

import com.emprendesoftcr.fisco.LlaveCriptografica;

public interface LlaveCriptograficaService {

	KeyStore getKeyStore (LlaveCriptografica llaveCriptografica) throws CertificateException, IOException;

}

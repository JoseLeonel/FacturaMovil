package com.emprendesoftcr.fisco;

import java.security.cert.X509Certificate;

public class DirectPasswordProvider implements xades4j.providers.impl.KeyStoreKeyingDataProvider.KeyStorePasswordProvider, xades4j.providers.impl.KeyStoreKeyingDataProvider.KeyEntryPasswordProvider {

	private String password;

	public DirectPasswordProvider(String password) {
		this.password = password;
	}

	public char[] getPassword() {
		return password.toCharArray();
	}

	public char[] getPassword(String entryAlias, X509Certificate entryCert) {
		return password.toCharArray();
	}
}

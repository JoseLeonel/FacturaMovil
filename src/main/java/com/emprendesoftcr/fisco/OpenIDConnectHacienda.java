package com.emprendesoftcr.fisco;

import com.emprendesoftcr.modelo.Empresa;

public class OpenIDConnectHacienda {

	private  Empresa empresa;
	private String	token_type;

	private String	access_token;

	private String	expires_;

	private String	refresh_token;
	private String	refresh_expires_in;
	public OpenIDConnectHacienda(Empresa empresa, String token_type, String access_token, String expires_, String refresh_token, String refresh_expires_in) {
		super();
		this.empresa = empresa;
		this.token_type = token_type;
		this.access_token = access_token;
		this.expires_ = expires_;
		this.refresh_token = refresh_token;
		this.refresh_expires_in = refresh_expires_in;
	}
	
	
	
	public OpenIDConnectHacienda() {
		super();
	}



	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public String getToken_type() {
		return token_type;
	}
	
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	
	public String getAccess_token() {
		return access_token;
	}
	
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	public String getExpires_() {
		return expires_;
	}
	
	public void setExpires_(String expires_) {
		this.expires_ = expires_;
	}
	
	public String getRefresh_token() {
		return refresh_token;
	}
	
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	public String getRefresh_expires_in() {
		return refresh_expires_in;
	}
	
	public void setRefresh_expires_in(String refresh_expires_in) {
		this.refresh_expires_in = refresh_expires_in;
	}



	
	
}

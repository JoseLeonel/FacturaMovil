package com.emprendesoftcr.web.command;

public class ValidarRolCommand {

	private String	usuarioSistema;
	private String	claveSistema;
	private Integer aceptacion;

	public ValidarRolCommand() {
		super();
	}

	public String getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(String usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public String getClaveSistema() {
		return claveSistema;
	}

	public void setClaveSistema(String claveSistema) {
		this.claveSistema = claveSistema;
	}

	
	public Integer getAceptacion() {
		return aceptacion;
	}

	
	public void setAceptacion(Integer aceptacion) {
		this.aceptacion = aceptacion;
	}
	
	

}

package com.emprendesoftcr.web.command;


public class CambiarPrecioArticuloCommand {
	
	private Long id ;
	private Double precioPublico;
	public CambiarPrecioArticuloCommand() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getPrecioPublico() {
		return precioPublico;
	}
	
	public void setPrecioPublico(Double precioPublico) {
		this.precioPublico = precioPublico;
	}
	
	
	

}

package com.emprendesoftcr.web.command;


public class EtiquetasCommand {
	
	private String codigo;
	private String descripcion;
	private Double precio;
	
	
	
	public EtiquetasCommand() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	

}

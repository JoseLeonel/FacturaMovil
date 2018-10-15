package com.emprendesoftcr.web.command;


public class FacturaCocodrilo {
	
	private String cedula;
	
	private String codigo; 
	private String paquete;
	
	private Integer cantidad;
	
	private Double precio;

	public FacturaCocodrilo(String cedula, String codigo, String paquete, Integer cantidad, Double precio) {
		super();
		this.cedula = cedula;
		this.codigo = codigo;
		this.paquete = paquete;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	
	public String getCedula() {
		return cedula;
	}

	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	
	public String getCodigo() {
		return codigo;
	}

	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public String getPaquete() {
		return paquete;
	}

	
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	
	public Integer getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
	public Double getPrecio() {
		return precio;
	}

	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
}
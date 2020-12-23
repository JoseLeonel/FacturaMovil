package com.emprendesoftcr.web.command;


public class TikectImprimir {
	
	private Long id;
	private String descripcion;
	private String cantidad;
	private String precio;
	private String impuesto;
	private String total;
	

	
	
	public Long getId() {
		return id;
	}


	
	public void setId(Long id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getPrecio() {
		return precio;
	}
	
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	public String getTotal() {
		return total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}



	
	public String getImpuesto() {
		return impuesto;
	}



	
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	
	
	

}

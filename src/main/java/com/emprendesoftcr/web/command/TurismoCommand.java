package com.emprendesoftcr.web.command;


public class TurismoCommand {
	
private Integer cantidadPersonas;
private String  medioPago;
private String  fechaPago;
private String paquete;
private Double precio;
private Double total;
public TurismoCommand() {
	super();
}

public Integer getCantidadPersonas() {
	return cantidadPersonas;
}

public void setCantidadPersonas(Integer cantidadPersonas) {
	this.cantidadPersonas = cantidadPersonas;
}

public String getMedioPago() {
	return medioPago;
}

public void setMedioPago(String medioPago) {
	this.medioPago = medioPago;
}

public String getFechaPago() {
	return fechaPago;
}

public void setFechaPago(String fechaPago) {
	this.fechaPago = fechaPago;
}

public String getPaquete() {
	return paquete;
}

public void setPaquete(String paquete) {
	this.paquete = paquete;
}

public Double getPrecio() {
	return precio;
}

public void setPrecio(Double precio) {
	this.precio = precio;
}

public Double getTotal() {
	return total;
}

public void setTotal(Double total) {
	this.total = total;
}


	
	
}

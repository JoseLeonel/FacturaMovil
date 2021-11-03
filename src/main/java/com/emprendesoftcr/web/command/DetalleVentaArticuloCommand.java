package com.emprendesoftcr.web.command;

import java.util.Date;

public class DetalleVentaArticuloCommand {
	
	private String fechaEmision;
	private String codigo;
	private String descripcion;
	private Double cantidad;
	private Double descuento;
	private Double totalExoneraciones;
	private Double totalImpuesto;
	private Double totalVentas;
	
	
	
	
	public String getFechaEmision() {
		return fechaEmision;
	}

	
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Double getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getDescuento() {
		return descuento;
	}
	
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	
	public Double getTotalExoneraciones() {
		return totalExoneraciones;
	}
	
	public void setTotalExoneraciones(Double totalExoneraciones) {
		this.totalExoneraciones = totalExoneraciones;
	}
	
	public Double getTotalImpuesto() {
		return totalImpuesto;
	}
	
	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}
	
	public Double getTotalVentas() {
		return totalVentas;
	}
	
	public void setTotalVentas(Double totalVentas) {
		this.totalVentas = totalVentas;
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
	
	

}

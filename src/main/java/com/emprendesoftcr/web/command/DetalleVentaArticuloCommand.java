package com.emprendesoftcr.web.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.utils.Utils;

public class DetalleVentaArticuloCommand {


	private String fechaEmision;
	private String codigo;
	private String descripcion;
	private Double cantidad; 
	private Double descuento;
	private Double totalExoneraciones;
	private Double totalImpuesto;
	private Double totalVentas;
	
	public Date getFechaEmisionF() {
		try {
			return Utils.parseDate(fechaEmision);	
		} catch (Exception e) {
			
		}
		return null;
		
	}
	
	
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public String getCantidadSTR() {
		return Utils.formateadorMiles(this.cantidad);
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
	public String getDescuentoSTR() {
		return Utils.formateadorMiles(this.descuento);
	}
	public Double getTotalExoneraciones() {
		return totalExoneraciones;
	}
	
	public void setTotalExoneraciones(Double totalExoneraciones) {
		this.totalExoneraciones = totalExoneraciones;
	}
	public String getTotalExoneracionesSTR() {
		return Utils.formateadorMiles(this.totalExoneraciones);
	}
	public Double getTotalImpuesto() {
		return totalImpuesto;
	}
	
	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}
	public String getTotalImpuestoSTR() {
		return Utils.formateadorMiles(this.totalImpuesto);
	}
	
	
	public Double getTotalVentas() {
		return totalVentas;
	}
	
	public void setTotalVentas(Double totalVentas) {
		this.totalVentas = totalVentas;
	}
	

	public String getTotalVentasSTR() {
		return Utils.formateadorMiles(this.totalVentas);
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

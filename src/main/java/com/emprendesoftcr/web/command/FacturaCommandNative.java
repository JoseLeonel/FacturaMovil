package com.emprendesoftcr.web.command;


public class FacturaCommandNative {
	
	private String fechaEmision;
	private String consecutivo;
	private Double impuesto;
	private Double descuento;
	private Double total;
	
	
	
	public FacturaCommandNative(String fechaEmision, String consecutivo, Double impuesto, Double descuento, Double total) {
		super();
		this.fechaEmision = fechaEmision;
		this.consecutivo = consecutivo;
		this.impuesto = impuesto;
		this.descuento = descuento;
		this.total = total;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}
	
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	public String getConsecutivo() {
		return consecutivo;
	}
	
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	public Double getImpuesto() {
		return impuesto;
	}
	
	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}
	
	public Double getDescuento() {
		return descuento;
	}
	
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	
	

}

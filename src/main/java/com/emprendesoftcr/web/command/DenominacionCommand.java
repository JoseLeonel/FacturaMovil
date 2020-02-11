package com.emprendesoftcr.web.command;

public class DenominacionCommand {

	private String	denominacion;
	private Double	cantidad;
	private Double	total;
	private Integer	tipo;

	public DenominacionCommand(String denominacion, Double cantidad, Double total) {
		super();
		this.denominacion = denominacion;
		this.cantidad = cantidad;
		this.total = total;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}

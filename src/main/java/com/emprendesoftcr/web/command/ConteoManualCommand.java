package com.emprendesoftcr.web.command;

public class ConteoManualCommand {

	private Long		id;
	private String	denominacion;
	private Double	conteoTarjeta;
	private Double	conteoDolar;
	private Double	tipoCambio;

	public ConteoManualCommand() {
		super();
	}

	

	
	public String getDenominacion() {
		return denominacion;
	}



	
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}



	public Double getConteoTarjeta() {
		return conteoTarjeta;
	}

	public void setConteoTarjeta(Double conteoTarjeta) {
		this.conteoTarjeta = conteoTarjeta;
	}

	public Double getConteoDolar() {
		return conteoDolar;
	}

	public void setConteoDolar(Double conteoDolar) {
		this.conteoDolar = conteoDolar;
	}

	public Double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

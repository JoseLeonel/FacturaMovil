package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.sqlNativo.ConsultaIVANative;

public class ConsultaIvaCommand {
	
	private Long			id;
	
	private String tipoImpuesto;
	
	private String codTarifa;
	
	private Double impuesto;
	
	private Double totalImpuesto;
	
	private Double totalVentas;
	
	
	public ConsultaIvaCommand(ConsultaIVANative consultaIVANative) {
		super();
		this.id           = consultaIVANative.getId();
		this.tipoImpuesto = consultaIVANative.getTipoImpuesto();
		this.codTarifa    = consultaIVANative.getCodTarifa();
		this.impuesto     = consultaIVANative.getImpuesto();
		this.totalImpuesto= consultaIVANative.getTotalImpuesto();
		this.totalVentas  = consultaIVANative.getTotalVentas();
	}


	
	public Long getId() {
		return id;
	}


	
	public void setId(Long id) {
		this.id = id;
	}


	public String getTipoImpuesto() {
		return tipoImpuesto;
	}


	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}


	public String getCodTarifa() {
		return codTarifa;
	}


	public void setCodTarifa(String codTarifa) {
		this.codTarifa = codTarifa;
	}


	public Double getImpuesto() {
		return impuesto;
	}


	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
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

		
}

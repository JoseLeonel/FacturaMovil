package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.sqlNativo.ConsultaComprasIvaNative;

public class ConsultaComprasIvaCommand {
	

	private Long   id;

	private String tipoImpuesto;

	private String codTarifa;
			
	private Double impuesto;

	private Double totalImpuesto;

	private Double totalVentas;
	
	private Double totalExoneraciones;
	
	public ConsultaComprasIvaCommand(ConsultaComprasIvaNative consultaComprasIvaNative) {
		super();
		this.id=consultaComprasIvaNative.getId();
		this.tipoImpuesto=consultaComprasIvaNative.getTipoImpuesto();
		this.codTarifa=consultaComprasIvaNative.getCodTarifa();
		this.impuesto=consultaComprasIvaNative.getImpuesto();
		this.totalImpuesto=consultaComprasIvaNative.getTotalImpuesto();
		this.totalVentas=consultaComprasIvaNative.getTotalVentas();	
		this.totalExoneraciones=consultaComprasIvaNative.getTotalExoneraciones();
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

	public Double getTotalExoneraciones() {
		return totalExoneraciones;
	}

	public void setTotalExoneraciones(Double totalExoneraciones) {
		this.totalExoneraciones = totalExoneraciones;
	}


}

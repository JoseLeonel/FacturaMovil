package com.emprendesoftcr.web.command;

import javax.persistence.Column;
import javax.persistence.Id;

import com.emprendesoftcr.modelo.sqlNativo.ConsultaIVANative;

public class ConsultaIvaCommand {

	
	@Id
	@Column(name = "id")
	private Long		id;

	@Column(name = "tipo_impuesto")
	private String tipoImpuesto;
	
	@Column(name = "impuesto")
	private Double impuesto;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "totalImpuesto")
	private Double totalImpuesto;
	
	@Column(name = "totalVentas")
	private Double totalVentas;
	
	
	public ConsultaIvaCommand(ConsultaIVANative consultaIVANative) {
		super();
		this.id=consultaIVANative.getId();
		this.tipoImpuesto=consultaIVANative.getTipoImpuesto();
		this.impuesto=consultaIVANative.getImpuesto();
		this.descripcion=consultaIVANative.getDescripcion();
		this.totalImpuesto=consultaIVANative.getTotalImpuesto();
		this.totalVentas=consultaIVANative.getTotalVentas();
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

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

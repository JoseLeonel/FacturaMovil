package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarifa")
public class Tarifa implements Serializable{
	private static final long serialVersionUID = 3602026983675078844L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "tipo_imp")
	private String						tipoImpuesto;

	@Column(name = "cod_tarifa")
	private String						codigoTarifa;

	@Column(name = "descripcion")
	private String						descripcion;

	@Column(name = "monto")
	private Double						monto;

	

	
	public Tarifa() {
		super();
	}


	public Tarifa(Long id, String tipoImpuesto, String codigoTarifa, String descripcion, Double monto) {
		super();
		this.id = id;
		this.tipoImpuesto = tipoImpuesto;
		this.codigoTarifa = codigoTarifa;
		this.descripcion = descripcion;
		this.monto = monto;
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

	
	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	
	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Double getMonto() {
		return monto;
	}

	
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	
}

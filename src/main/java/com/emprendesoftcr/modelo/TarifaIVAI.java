package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarifaIVAI")
public class TarifaIVAI implements Serializable{
	
	
	private static final long serialVersionUID = 3705063292514609413L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "cod_tarifa")
	private String						codigoTarifa;

	@Column(name = "descripcion")
	private String						descripcion;

	@Column(name = "monto")
	private Double						monto;

	public TarifaIVAI(Integer id, String codigoTarifa, String descripcion, Double monto) {
		super();
		this.id = id;
		this.codigoTarifa = codigoTarifa;
		this.descripcion = descripcion;
		this.monto = monto;
	}

	
	public TarifaIVAI() {
		super();
	}


	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
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

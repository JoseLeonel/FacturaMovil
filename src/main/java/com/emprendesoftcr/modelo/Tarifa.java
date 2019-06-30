package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tarifa")
public class Tarifa implements Serializable {

	private static final long	serialVersionUID	= 3602026983675078844L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "tipo_imp", length=2)
	private String						tipoImpuesto;

	@Column(name = "monto")
	private Double						monto;

	@ManyToOne
	@JoinColumn(name = "tarifa_id")
	private TarifaIVAI				tarifaIVAI;

	public Tarifa() {
		super();
	}

	public Tarifa(Long id, String tipoImpuesto, Double monto, TarifaIVAI tarifaIVAI) {
		super();
		this.id = id;
		this.tipoImpuesto = tipoImpuesto;
		this.monto = monto;
		this.tarifaIVAI = tarifaIVAI;
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

	public TarifaIVAI getTarifaIVAI() {
		return tarifaIVAI;
	}

	public void setTarifaIVAI(TarifaIVAI tarifaIVAI) {
		this.tarifaIVAI = tarifaIVAI;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

}

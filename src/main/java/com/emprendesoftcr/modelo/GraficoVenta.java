package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "grafico_vent")
public class GraficoVenta implements Serializable {

	private static final long	serialVersionUID	= 6687302026193995880L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;
	@Column(name = "anno")
	private Integer						anno;

	@Column(name = "mes")
	private Integer						mes;

	@Column(name = "total")
	private Double						total;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	public GraficoVenta() {
		super();
	}

	public GraficoVenta(Long id, Integer anno, Integer mes, Double total, Date updated_at, Empresa empresa) {
		super();
		this.id = id;
		this.anno = anno;
		this.mes = mes;
		this.total = total;
		this.updated_at = updated_at;
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}

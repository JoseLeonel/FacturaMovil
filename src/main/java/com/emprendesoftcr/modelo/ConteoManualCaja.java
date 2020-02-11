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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "conteo_manual_caj")
public class ConteoManualCaja implements Serializable {

	private static final long	serialVersionUID	= 5978926164243331149L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long							id;
	@Column(name = "denomina")
	private String						denominacion;
	@Column(name = "cantidad")
	private Double						cantidad;
	@Column(name = "total")
	private Double						total;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "tipo")
	private Integer						tipo;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usua_caja_id")
	private UsuarioCaja				usuarioCaja;

	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuarioCierra;

	public ConteoManualCaja() {
		super();
	}

	public ConteoManualCaja(Long id, String denominacion, Double cantidad, Double total, Date created_at, Date updated_at, Integer tipo, UsuarioCaja usuarioCaja, Usuario usuarioCierra) {
		super();
		this.id = id;
		this.denominacion = denominacion;
		this.cantidad = cantidad;
		this.total = total;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.tipo = tipo;
		this.usuarioCaja = usuarioCaja;
		this.usuarioCierra = usuarioCierra;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public UsuarioCaja getUsuarioCaja() {
		return usuarioCaja;
	}

	public void setUsuarioCaja(UsuarioCaja usuarioCaja) {
		this.usuarioCaja = usuarioCaja;
	}

	public Usuario getUsuarioCierra() {
		return usuarioCierra;
	}

	public void setUsuarioCierra(Usuario usuarioCierra) {
		this.usuarioCierra = usuarioCierra;
	}

}

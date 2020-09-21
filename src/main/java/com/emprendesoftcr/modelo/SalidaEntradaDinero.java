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

import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sali_entra")
public class SalidaEntradaDinero implements Serializable {

	private static final long	serialVersionUID	= 5666087286919286768L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "descripcon")
	private String						descripcion;

	@Column(name = "total")
	private Double						total;

	// 1= Entrada 2= Salida
	@Column(name = "tipo")
	private Integer						tipo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuarioResponsable;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usua_caja_id")
	private UsuarioCaja				usuariocaja;

	public SalidaEntradaDinero() {
		super();
	}

	public SalidaEntradaDinero(Long id, String descripcion, Double total, Integer tipo, Date created_at, Usuario usuarioResponsable, UsuarioCaja usuariocaja) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.total = total;
		this.tipo = tipo;
		this.created_at = created_at;
		this.usuarioResponsable = usuarioResponsable;
		this.usuariocaja = usuariocaja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public String getDescripcionSTR() {
		return descripcion.length() > 30?descripcion.substring(0,30)+"....":descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getTotal() {
		return total;
	}

	public String getTotalSTR() {
		return Utils.formateadorMiles(this.total);
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

	public Date getCreated_at() {
		return created_at;
	}

	public String getCreated_atSTR() {
		if (this.created_at != null) {
			return Utils.getFechaGeneraReporte(this.created_at);
		}
		return Constantes.EMPTY;

	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Usuario getUsuarioResponsable() {
		return usuarioResponsable;
	}

	public void setUsuarioResponsable(Usuario usuarioResponsable) {
		this.usuarioResponsable = usuarioResponsable;
	}

	public UsuarioCaja getUsuariocaja() {
		return usuariocaja;
	}

	public void setUsuariocaja(UsuarioCaja usuariocaja) {
		this.usuariocaja = usuariocaja;
	}

}

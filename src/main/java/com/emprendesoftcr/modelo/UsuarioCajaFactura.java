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

/**
 * Asociacion de la factura realizada en la caja
 * UsuarioCajaFactura.
 * @author jose.
 * @since 10 jun. 2018
 */
@Entity
@Table(name = "usuariosCajas_facturas")
public class UsuarioCajaFactura implements Serializable {

	private static final long	serialVersionUID	= -8569232658094869750L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long						id;

	@ManyToOne
	@JoinColumn(name = "usuarioCaja_id")
	private UsuarioCaja				usuarioCaja;

	@ManyToOne
	@JoinColumn(name = "factura_id")
	private Factura						factura;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	
	public UsuarioCajaFactura() {
		super();
	}

	

	public UsuarioCajaFactura(Long id, UsuarioCaja usuarioCaja, Factura factura, Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.usuarioCaja = usuarioCaja;
		this.factura = factura;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}



	
	public Long getId() {
		return id;
	}



	
	public void setId(Long id) {
		this.id = id;
	}



	public UsuarioCaja getUsuarioCaja() {
		return usuarioCaja;
	}

	public void setUsuarioCaja(UsuarioCaja usuarioCaja) {
		this.usuarioCaja = usuarioCaja;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
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

}

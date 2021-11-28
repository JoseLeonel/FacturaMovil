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
@Table(name = "merma_producto")
public class MermaProducto implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long			id;

	@ManyToOne
	@JoinColumn(name = "articulo_id", nullable = false)
	private Articulo	articulo;

	@Column(name = "cantidad")
	private Double		cantidad;

	@Column(name = "note")
	private String		note;

	@Column(name = "usuario_id")
	private Usuario		usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date			created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date			updated_at;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Articulo getArticulo() {
		return articulo;
	}

	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	
	public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	
	public String getNote() {
		return note;
	}

	
	public void setNote(String note) {
		this.note = note;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

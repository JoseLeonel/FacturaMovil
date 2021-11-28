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
 * Producto que no existen en la empresa
 * @author jose
 */
@Entity
@Table(name = "producto_sin_inven")
public class ProductoSinInventario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long		id;

	@Column(name = "descripcion")
	private String	descripcion;

	@Column(name = "estado")
	private Integer	estado;					// 0 = Pendiente 1 = aceptado 3 = Anular

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date		updated_at;

	
	
	@ManyToOne
	@JoinColumn(name = "usua_crea_id", nullable = false)
	private Usuario	usuarioSolicito;

	
	
	@ManyToOne
	@JoinColumn(name = "usua_ped_id", nullable = false)
	private Usuario	usuarioPedio;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
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

	public Usuario getUsuarioSolicito() {
		return usuarioSolicito;
	}

	public void setUsuarioSolicito(Usuario usuarioSolicito) {
		this.usuarioSolicito = usuarioSolicito;
	}

	public Usuario getUsuarioPedio() {
		return usuarioPedio;
	}

	public void setUsuarioPedio(Usuario usuarioPedio) {
		this.usuarioPedio = usuarioPedio;
	}

}

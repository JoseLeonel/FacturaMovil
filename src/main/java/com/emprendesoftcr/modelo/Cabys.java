package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cabys")
public class Cabys implements Serializable {
	
	private static final long serialVersionUID = 8593933079565253992L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long		id;
	
	@Column(name = "codigo")
	private String	codigo;
	
	@Column(name = "descripcion")
	private String	descripcion;
	
	@Column(name = "impuesto")
	private Double	impuesto;
	
	@Column(name = "origen")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private Blob	origen;
	
	@Column(name = "uri")
	private String	uri;
	
	@Column(name = "estado")
	private String	estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date updated_at;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String getEstado() {
		return estado;
	}

	
	public void setEstado(String estado) {
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

	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
	

	
	
	public Blob getOrigen() {
		return origen;
	}

	
	public void setOrigen(Blob origen) {
		this.origen = origen;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	
	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	
	public String getUri() {
		return uri;
	}

	
	public void setUri(String uri) {
		this.uri = uri;
	}

	
	

	
	
}

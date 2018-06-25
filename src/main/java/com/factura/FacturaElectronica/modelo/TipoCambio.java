package com.factura.FacturaElectronica.modelo;

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
@Table(name = "tipo_cambios")
public class TipoCambio implements Serializable{

	/**
	 * Comentario para <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -3901556698081674049L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer	id;

	@Column(name = "moneda")
	private String	moneda;

	@Column(name = "total")
	private Double	total;

	@Column(name = "estado")
	private String	estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date		updated_at;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario	usuario;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa	empresa;

	
	

	public TipoCambio(Integer id, String moneda, Double total, String estado, Date created_at, Date updated_at, Usuario usuario, Empresa empresa) {
		super();
		this.id = id;
		this.moneda = moneda;
		this.total = total;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.usuario = usuario;
		this.empresa = empresa;
	}

	public TipoCambio() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	

}

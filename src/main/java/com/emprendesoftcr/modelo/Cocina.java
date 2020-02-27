package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cocina")
public class Cocina implements Serializable {

	private static final long		serialVersionUID	= 2355485194750538970L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long								id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date								created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date								updated_at;

	// 1 = Activa 2 =Inactiva
	@Column(name = "estado")
	private Integer							estado;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa							empresa;
	
	
	@Column(name = "id_usua_caj")
	private Long							idUsuarioCaja;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "coci_id", referencedColumnName = "ID")
	@OrderBy("coci_id DESC")
	private Set<CocinaComanda>	cocinaComandas;

	


	public Cocina(Long id, Date created_at, Date updated_at, Integer estado, Empresa empresa, Long idUsuarioCaja, Set<CocinaComanda> cocinaComandas) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.estado = estado;
		this.empresa = empresa;
		this.idUsuarioCaja = idUsuarioCaja;
		this.cocinaComandas = cocinaComandas;
	}

	public Cocina() {
		super();
	}

	public void addComanda(CocinaComanda cocinaComanda) {
		if (cocinaComanda != null) {
			if (cocinaComandas == null) {
				cocinaComandas = new HashSet<CocinaComanda>();
			}
			cocinaComanda.setCocina(this);
			cocinaComandas.add(cocinaComanda);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Set<CocinaComanda> getCocinaComandas() {
		return cocinaComandas;
	}

	public void setCocinaComandas(Set<CocinaComanda> cocinaComandas) {
		this.cocinaComandas = cocinaComandas;
	}

	
	public Long getIdUsuarioCaja() {
		return idUsuarioCaja;
	}

	
	public void setIdUsuarioCaja(Long idUsuarioCaja) {
		this.idUsuarioCaja = idUsuarioCaja;
	}

	
	
	

}

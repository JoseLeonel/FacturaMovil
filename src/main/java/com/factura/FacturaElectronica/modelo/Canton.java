package com.factura.FacturaElectronica.modelo;

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

@Entity
@Table(name = "cantones")
public class Canton {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;
	
	@Column(name = "codigo")
	private Integer						codigo;

	@Column(name = "descripcion")
	private String						descripcion;
	
	@ManyToOne
	@JoinColumn(name = "provincia_id")
	private Provincia			provincia;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "canton_id", referencedColumnName = "id")
	@OrderBy("id DESC")
	private Set<Distrito>				distritos;

	public Canton(Integer id, Integer codigo, String descripcion, Provincia provincia, Set<Distrito> distritos) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.provincia = provincia;
		this.distritos = distritos;
	}

	
	public Canton() {
		super();
	}


	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getCodigo() {
		return codigo;
	}

	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Provincia getProvincia() {
		return provincia;
	}

	
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	
	public Set<Distrito> getDistritos() {
		return distritos;
	}

	
	public void setDistritos(Set<Distrito> distritos) {
		this.distritos = distritos;
	}
	
	

}

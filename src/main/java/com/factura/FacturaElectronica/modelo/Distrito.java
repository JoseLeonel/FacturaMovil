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
@Table(name = "distritos")
public class Distrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer			id;

	@Column(name = "codigo")
	private Integer			codigo;

	@Column(name = "descripcion")
	private String			descripcion;

	@ManyToOne
	@JoinColumn(name = "canton_id")
	private Canton			canton;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "distrito_id", referencedColumnName = "id")
	@OrderBy("id DESC")
	private Set<Barrio>	barrios;

	public Distrito(Integer id, Integer codigo, String descripcion, Canton canton, Set<Barrio> barrios) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.canton = canton;
		this.barrios = barrios;
	}

	public Distrito() {
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

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public Set<Barrio> getBarrios() {
		return barrios;
	}

	public void setBarrios(Set<Barrio> barrios) {
		this.barrios = barrios;
	}

}

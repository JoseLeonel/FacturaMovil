package com.factura.FacturaElectronica.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 
 * Provincia. Son las provincias de CR
 * @author jose.
 * @since 6 jul. 2018
 */
@Entity
@Table(name = "provincia")
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer			id;

	@Column(name = "codigo")
	private Integer			codigo;

	@Column(name = "descripcion")
	private String			descripcion;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "provincia_id", referencedColumnName = "id")
	@OrderBy("id DESC")
	private Set<Canton>	cantones;

	public Provincia(Integer id, Integer codigo, String descripcion, Set<Canton> cantones) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.cantones = cantones;
	}
	
	

	public Provincia() {
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

	public Set<Canton> getCantones() {
		return cantones;
	}

	public void setCantones(Set<Canton> cantones) {
		this.cantones = cantones;
	}

}

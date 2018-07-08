package com.emprendesoftcr.modelo;

import java.io.Serializable;
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

@Entity
@Table(name = "cantones")
public class Canton implements Serializable  {

	private static final long serialVersionUID = 585829743682491706L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer				id;

	@Column(name = "codigo")
	private Integer				codigo;

	@Column(name = "codigo_provincia")
	private Integer				codigo_provincia;

	@Column(name = "descripcion")
	private String				descripcion;

	

	public Canton(Integer id, Integer codigo, Integer codigo_provincia, String descripcion) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.codigo_provincia = codigo_provincia;
		this.descripcion = descripcion;
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

	public Integer getCodigo_provincia() {
		return codigo_provincia;
	}

	public void setCodigo_provincia(Integer codigo_provincia) {
		this.codigo_provincia = codigo_provincia;
	}

	

}

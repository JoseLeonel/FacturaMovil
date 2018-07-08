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
@Table(name = "distritos")
public class Distrito implements Serializable  {

	private static final long serialVersionUID = -5777488135613694653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer			id;

	@Column(name = "codigo")
	private Integer			codigo;

	@Column(name = "descripcion")
	private String			descripcion;

	@Column(name = "codigo_canton")
	private Integer			codigoCanton;

	@Column(name = "codigo_provincia")
	private Integer			codigoProvincia;



	public Distrito(Integer id, Integer codigo, String descripcion, Integer codigoCanton, Integer codigoProvincia) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigoCanton = codigoCanton;
		this.codigoProvincia = codigoProvincia;
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

	public Integer getCodigoCanton() {
		return codigoCanton;
	}

	public void setCodigoCanton(Integer codigoCanton) {
		this.codigoCanton = codigoCanton;
	}

	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	

}

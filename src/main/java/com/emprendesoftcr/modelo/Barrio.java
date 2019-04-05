package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "barrios")
public class Barrio implements Serializable {

	private static final long	serialVersionUID	= 4200197447635783970L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "codigo")
	private Integer						codigo;

	@Column(name = "codigo_provincia")
	private Integer						codigoProvincia;

	@Column(name = "codigo_canton")
	private Integer						codigoCanton;

	@Column(name = "codigo_distrito")
	private Integer						codigoDistrito;

	@Column(name = "descripcion")
	private String						descripcion;

	public Barrio(Integer id, Integer codigo, Integer codigoProvincia, Integer codigoCanton, Integer codigoDistrito, String descripcion) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.codigoProvincia = codigoProvincia;
		this.codigoCanton = codigoCanton;
		this.codigoDistrito = codigoDistrito;
		this.descripcion = descripcion;
	}

	public Barrio() {
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

	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public Integer getCodigoCanton() {
		return codigoCanton;
	}

	public void setCodigoCanton(Integer codigoCanton) {
		this.codigoCanton = codigoCanton;
	}

	public Integer getCodigoDistrito() {
		return codigoDistrito;
	}

	public void setCodigoDistrito(Integer codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}

}

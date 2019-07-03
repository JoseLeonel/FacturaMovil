package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activ_comercial")
public class ActividadComercial implements Serializable{
	
	private static final long serialVersionUID = -400141137739161648L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "cod_act", length=2)
	private String						codigoActividadComercial;

	@Column(name = "descripcion", length=100)
	private String						descripcion;

	public ActividadComercial(Integer id, String codigoActividadComercial, String descripcion) {
		super();
		this.id = id;
		this.codigoActividadComercial = codigoActividadComercial;
		this.descripcion = descripcion;
	}
	
	public ActividadComercial() {
		super();
	}

	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getCodigoActividadComercial() {
		return codigoActividadComercial;
	}

	
	public void setCodigoActividadComercial(String codigoActividadComercial) {
		this.codigoActividadComercial = codigoActividadComercial;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}

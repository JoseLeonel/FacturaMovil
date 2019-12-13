package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@BaseNativeQuery(name ="lista_art_mini" ,query ="SELECT articulos.id,articulos.codigo,\n" + 
		"       articulos.descripcion,\n" + 
		"       articulos.cantidad,articulos.minimo \n" + 
		"   FROM `articulos` WHERE articulos.estado ='Activo' and articulos.contable = 'Si' and  "
		+ "articulos.minimo > articulos.cantidad "
		+ " and articulos.empresa_id = :ID_EMPRESA ORDER BY codigo,descripcion asc LIMIT 50 " )
@Entity
public class ArticuloMinimoNative implements Serializable {


	private static final long serialVersionUID = 2014279469582393977L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "descripcion")
	private String						descripcion;


	@Column(name = "cantidad")
	private Double						cantidad;

	@Column(name = "minimo")
	private Double						minimo;

	
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

	
	public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	
	public Double getMinimo() {
		return minimo;
	}

	
	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((minimo == null) ? 0 : minimo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticuloMinimoNative other = (ArticuloMinimoNative) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (minimo == null) {
			if (other.minimo != null)
				return false;
		} else if (!minimo.equals(other.minimo))
			return false;
		return true;
	}
	
	
	
	
}

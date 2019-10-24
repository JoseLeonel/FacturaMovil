package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@BaseNativeQuery(name ="graf_art_vendido" ,query ="SELECT  a.codigo, a.descripcion, "
		+ "SUM(if(facturas.tipo_doc = '03' or facturas.tipo_doc = '86' ,a.cantidad * -1,a.cantidad)) AS venta \n" + 
		"            FROM detalles a\n" + 
		"            inner join facturas on facturas.id = a.factura_id\n" + 
		" WHERE facturas.empresa_id = :ID_EMPRESA \n" + 
		"      GROUP BY a.codigo, a.descripcion \n" +
		"     ORDER BY venta desc LIMIT 50" )
@Entity
public class GraficoArticuloMasVendidoNative implements Serializable {
	
	private static final long serialVersionUID = -3344116568222615032L;
	@Id
	@Column(name = "codigo")
	private String	codigo;

	@Column(name = "descripcion")
	private String	descripcion;

	@Column(name = "venta")
	private Double	cantidadVendido;

	
	
	
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

	
	public Double getCantidadVendido() {
		return cantidadVendido;
	}

	
	public void setCantidadVendido(Double cantidadVendido) {
		this.cantidadVendido = cantidadVendido;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadVendido == null) ? 0 : cantidadVendido.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
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
		GraficoArticuloMasVendidoNative other = (GraficoArticuloMasVendidoNative) obj;
		if (cantidadVendido == null) {
			if (other.cantidadVendido != null)
				return false;
		} else if (!cantidadVendido.equals(other.cantidadVendido))
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
		return true;
	}


	

	
	
	
	
}

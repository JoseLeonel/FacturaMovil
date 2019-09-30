package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@BaseNativeQuery(name = "cons_vent_gana", 
query = "SELECT clientes.id,  clientes.cedula,clientes.nombre_completo," 
+ "categorias.descripcion as nomb_categ,detalles.codigo,detalles.descripcion as nomb_product,DATE_FORMAT(facturas.fecha_emision, \"%d-%c-%Y\") as fecha_emision," 
+ "sum(IFNULL(detalles.costo * detalles.cantidad,0)) as costo,sum(IFNULL(detalles.cantidad,0)) as cantidad ,sum(IFNULL(detalles.monto_total_linea ,0)) as total," 
+ "sum(IFNULL(detalles.monto_descuento,0)) as descuento,sum(IFNULL(detalles.monto_impuesto, 0)) as impuesto,sum(IFNULL(detalles.monto_impuesto, 0)) as monto_exonerado FROM detalles" 
+ " inner join facturas on facturas.id =detalles.factura_id " 
+ " inner join clientes on clientes.id =facturas.cliente_id " 
+ " inner join articulos on articulos.codigo =detalles.codigo " 
+ " inner join categorias on categorias.id = articulos.categoria_id "
+ " where facturas.empresa_id = :ID_EMPRESA facturas.estado in and facturas.tipo_doc !='88' and facturas.tipo_doc !='86' and facturas.tipo_doc !='03' and facturas.tipo_doc !='02' and facturas.fecha_emision >= :fechaInicial and facturas.fecha_emision <= :fechaFinal and categorias.id = and facturas.cliente_id and facturas.act_comercial and detalles.codigo " + " GROUP by clientes.id,clientes.cedula,clientes.nombre_completo,categorias.descripcion,detalles.codigo,detalles.descripcion,DATE_FORMAT(facturas.fecha_emision, \"%d-%c-%Y\") " + " ORDER BY clientes.nombre_completo,categorias.descripcion,detalles.descripcion ASC")
@Entity
public class ConsultaGananciaNative implements Serializable {

	private static final long	serialVersionUID	= 4020391061634341281L;

	@Id
	@Column(name = "id")
	private Long							id;


	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "nombre_completo")
	private String						nombreCompleto;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "fecha_emision")
	private String						fechaEmision;
	@Column(name = "nomb_product")
	private String						nombreArticulo;
	@Column(name = "nomb_categ")
	private String						nombreCategoria;
	@Column(name = "costo")
	private Double						costo;

	@Column(name = "cantidad")
	private Double						cantidad;

	@Column(name = "total")
	private Double						total;

	@Column(name = "descuento")
	private Double						descuento;

	@Column(name = "impuesto")
	private Double						impuesto;

	@Column(name = "monto_exonerado")
	private Double						montoExoneracion;

	
	
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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	
	public Double getMontoExoneracion() {
		return montoExoneracion;
	}

	
	public void setMontoExoneracion(Double montoExoneracion) {
		this.montoExoneracion = montoExoneracion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((costo == null) ? 0 : costo.hashCode());
		result = prime * result + ((descuento == null) ? 0 : descuento.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((impuesto == null) ? 0 : impuesto.hashCode());
		result = prime * result + ((montoExoneracion == null) ? 0 : montoExoneracion.hashCode());
		result = prime * result + ((nombreArticulo == null) ? 0 : nombreArticulo.hashCode());
		result = prime * result + ((nombreCategoria == null) ? 0 : nombreCategoria.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		ConsultaGananciaNative other = (ConsultaGananciaNative) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (costo == null) {
			if (other.costo != null)
				return false;
		} else if (!costo.equals(other.costo))
			return false;
		if (descuento == null) {
			if (other.descuento != null)
				return false;
		} else if (!descuento.equals(other.descuento))
			return false;
		if (fechaEmision == null) {
			if (other.fechaEmision != null)
				return false;
		} else if (!fechaEmision.equals(other.fechaEmision))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (impuesto == null) {
			if (other.impuesto != null)
				return false;
		} else if (!impuesto.equals(other.impuesto))
			return false;
		if (montoExoneracion == null) {
			if (other.montoExoneracion != null)
				return false;
		} else if (!montoExoneracion.equals(other.montoExoneracion))
			return false;
		if (nombreArticulo == null) {
			if (other.nombreArticulo != null)
				return false;
		} else if (!nombreArticulo.equals(other.nombreArticulo))
			return false;
		if (nombreCategoria == null) {
			if (other.nombreCategoria != null)
				return false;
		} else if (!nombreCategoria.equals(other.nombreCategoria))
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

	

}

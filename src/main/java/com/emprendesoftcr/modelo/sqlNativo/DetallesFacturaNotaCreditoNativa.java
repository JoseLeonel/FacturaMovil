package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.emprendesoftcr.modelo.Factura;

@BaseNativeQuery(name = "fact_nc	", query = "select detalles.id, detalles.numero_linea,detalles.cantidad,detalles.descripcion,detalles.codigo,detalles.factura_id,detalles.cant_notaC from detalles"
		+ "  inner join facturas on facturas.id = detalles.factura_id  "
		+ "  where detalles.cantidad > detalles.cant_notaC and facturas.numero_consecutivo    and facturas.empresa_id  ")
@Entity
public class DetallesFacturaNotaCreditoNativa implements Serializable {

	
	private static final long serialVersionUID = -7812943765498460673L;

	@Id
	@Column(name = "id")
	private Long		id;

	@Column(name = "numero_linea")
	private Integer	numeroLinea;
	@Column(name = "cantidad", precision = 16, scale = 3)
	private Double	cantidad;

	@Column(name = "descripcion")
	private String	descripcion;

	@Column(name = "codigo", length = 20)
	private String	codigo;

	@ManyToOne
	@JoinColumn(name = "factura_id")
	private Factura	factura;

	@Column(name = "cant_notaC", precision = 18, scale = 5)
	private Double	cantidadAplicadaNotaCredito;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	
	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	
	public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String getCodigo() {
		return codigo;
	}

	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public Factura getFactura() {
		return factura;
	}

	
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	
	public Double getCantidadAplicadaNotaCredito() {
		return cantidadAplicadaNotaCredito;
	}

	
	public void setCantidadAplicadaNotaCredito(Double cantidadAplicadaNotaCredito) {
		this.cantidadAplicadaNotaCredito = cantidadAplicadaNotaCredito;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((cantidadAplicadaNotaCredito == null) ? 0 : cantidadAplicadaNotaCredito.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroLinea == null) ? 0 : numeroLinea.hashCode());
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
		DetallesFacturaNotaCreditoNativa other = (DetallesFacturaNotaCreditoNativa) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (cantidadAplicadaNotaCredito == null) {
			if (other.cantidadAplicadaNotaCredito != null)
				return false;
		} else if (!cantidadAplicadaNotaCredito.equals(other.cantidadAplicadaNotaCredito))
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
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroLinea == null) {
			if (other.numeroLinea != null)
				return false;
		} else if (!numeroLinea.equals(other.numeroLinea))
			return false;
		return true;
	}
	
	

}

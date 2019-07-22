package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@BaseNativeQuery(name = "facturasdia", 
query = "Select clientes.cedula,clientes.nombre_completo,facturas.updated_at,facturas.created_at,facturas.estado,facturas.total_comprobante,facturas.total_impuesto,facturas.total_descuentos,facturas.sub_total," + 
		"facturas.nombre_factura,facturas.tipo_doc,facturas.plazo_credito,facturas.condicion_venta,facturas.fecha_emision,facturas.clave,facturas.consecutivo_proforma,facturas.numero_consecutivo," + 
		"facturas.id,facturas.usuario_id," + 
		"facturas.empresa_id" + 
		" from facturas" 
		+ " left join clientes on clientes.id = facturas.cliente_id " 
		+ " where facturas.empresa_id = :ID_EMPRESA and facturas.estado = :ESTADO and facturas.tipo_doc !='88'  and facturas.fecha_emision >= :FECHA and facturas.usuario_id = order by facturas.fecha_emision desc ")
@Entity
public class FacturasDelDiaNative  implements Serializable {

	

	/**
	 * Comentario para <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 7183930964301529824L;

	@Id
	@Column(name = "id")
	private Long		id;

	@Column(name = "numero_consecutivo")
	private String	numeroConsecutivo;

	@Column(name = "consecutivo_proforma")
	private String	consecutivoProforma;

	@Column(name = "clave")
	private String	clave;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date		fechaEmision;

	@Column(name = "condicion_venta")
	private String	condicionVenta;

	@Column(name = "plazo_credito")
	private Integer	plazoCredito;

	@Column(name = "tipo_doc")
	private String	tipoDoc;

	@Column(name = "nombre_factura")
	private String	nombreFactura;

	@Column(name = "sub_total", precision = 18, scale = 5)
	private Double	subTotal;

	@Column(name = "total_descuentos", precision = 18, scale = 5)
	private Double	totalDescuentos;

	@Column(name = "total_impuesto", precision = 18, scale = 5)
	private Double	totalImpuesto;

	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double	totalComprobante;

	@Column(name = "estado")
	private Integer	estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date		updated_at;

	@Column(name = "nombre_Completo")
	private String	cliente;

	@Column(name = "cedula")
	private String	cedula;
	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	
	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	
	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	
	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}

	
	
	public String getCedula() {
		return cedula;
	}


	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getClave() {
		return clave;
	}

	
	public void setClave(String clave) {
		this.clave = clave;
	}

	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	
	public String getCondicionVenta() {
		return condicionVenta;
	}

	
	public void setCondicionVenta(String condicionVenta) {
		this.condicionVenta = condicionVenta;
	}

	
	public Integer getPlazoCredito() {
		return plazoCredito;
	}

	
	public void setPlazoCredito(Integer plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	
	public String getTipoDoc() {
		return tipoDoc;
	}

	
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	
	public String getNombreFactura() {
		return nombreFactura;
	}

	
	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	
	public Double getSubTotal() {
		return subTotal;
	}

	
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	
	public Double getTotalDescuentos() {
		return totalDescuentos;
	}

	
	public void setTotalDescuentos(Double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	
	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	
	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	
	public Double getTotalComprobante() {
		return totalComprobante;
	}

	
	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	
	public Integer getEstado() {
		return estado;
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
	public Date getCreated_at() {
		return created_at;
	}

	
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	
	public Date getUpdated_at() {
		return updated_at;
	}

	
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	
	public String getCliente() {
		return cliente;
	}

	
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((condicionVenta == null) ? 0 : condicionVenta.hashCode());
		result = prime * result + ((consecutivoProforma == null) ? 0 : consecutivoProforma.hashCode());
		result = prime * result + ((created_at == null) ? 0 : created_at.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
		result = prime * result + ((plazoCredito == null) ? 0 : plazoCredito.hashCode());
		result = prime * result + ((subTotal == null) ? 0 : subTotal.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result + ((totalComprobante == null) ? 0 : totalComprobante.hashCode());
		result = prime * result + ((totalDescuentos == null) ? 0 : totalDescuentos.hashCode());
		result = prime * result + ((totalImpuesto == null) ? 0 : totalImpuesto.hashCode());
		result = prime * result + ((updated_at == null) ? 0 : updated_at.hashCode());
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
		FacturasDelDiaNative other = (FacturasDelDiaNative) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (condicionVenta == null) {
			if (other.condicionVenta != null)
				return false;
		} else if (!condicionVenta.equals(other.condicionVenta))
			return false;
		if (consecutivoProforma == null) {
			if (other.consecutivoProforma != null)
				return false;
		} else if (!consecutivoProforma.equals(other.consecutivoProforma))
			return false;
		if (created_at == null) {
			if (other.created_at != null)
				return false;
		} else if (!created_at.equals(other.created_at))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
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
		if (nombreFactura == null) {
			if (other.nombreFactura != null)
				return false;
		} else if (!nombreFactura.equals(other.nombreFactura))
			return false;
		if (numeroConsecutivo == null) {
			if (other.numeroConsecutivo != null)
				return false;
		} else if (!numeroConsecutivo.equals(other.numeroConsecutivo))
			return false;
		if (plazoCredito == null) {
			if (other.plazoCredito != null)
				return false;
		} else if (!plazoCredito.equals(other.plazoCredito))
			return false;
		if (subTotal == null) {
			if (other.subTotal != null)
				return false;
		} else if (!subTotal.equals(other.subTotal))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		if (totalComprobante == null) {
			if (other.totalComprobante != null)
				return false;
		} else if (!totalComprobante.equals(other.totalComprobante))
			return false;
		if (totalDescuentos == null) {
			if (other.totalDescuentos != null)
				return false;
		} else if (!totalDescuentos.equals(other.totalDescuentos))
			return false;
		if (totalImpuesto == null) {
			if (other.totalImpuesto != null)
				return false;
		} else if (!totalImpuesto.equals(other.totalImpuesto))
			return false;
		if (updated_at == null) {
			if (other.updated_at != null)
				return false;
		} else if (!updated_at.equals(other.updated_at))
			return false;
		return true;
	}


	
	

}

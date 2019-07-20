package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@BaseNativeQuery(name = "proformas", query = "SELECT facturas.id,facturas.consecutivo_proforma,facturas.usuario_id,facturas.fecha_emision,facturas.cliente_id,facturas.nombre_factura,facturas.total_impuesto,facturas.total_descuentos,facturas.total_comprobante,usuarios.nombre_usuario,clientes.nombre_completo,facturas.empresa_id,facturas.estado,facturas.tipo_doc,facturas.numero_consecutivo FROM facturas" + " inner join clientes on clientes.id = facturas.cliente_id " + " INNER JOIN usuarios on usuarios.id = facturas.usuario_id " + " INNER join empresas on empresas.id = facturas.empresa_id " + " where facturas.empresa_id = :ID_EMPRESA and facturas.estado = :ESTADO and facturas.tipo_doc ='88' ")
@Entity
public class ProformasByEmpresaAndEstado implements Serializable {

	private static final long	serialVersionUID	= 408492682362669603L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "consecutivo_proforma")
	private String						consecutivoProforma;

	@Column(name = "numero_consecutivo")
	private String						consecutivo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date							fechaEmision;

	@Column(name = "nombre_factura")
	private String						nombreFactura;

	@Column(name = "total_descuentos", precision = 18, scale = 5)
	private Double						totalDescuentos;

	@Column(name = "total_impuesto", precision = 18, scale = 5)
	private Double						totalImpuesto;

	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double						totalComprobante;

	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "nombre_completo")
	private String						cliente;

	@Column(name = "empresa_id")
	private Integer						empresa;

	@Column(name = "nombre_usuario")
	private String						usuarioCreacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((consecutivo == null) ? 0 : consecutivo.hashCode());
		result = prime * result + ((consecutivoProforma == null) ? 0 : consecutivoProforma.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result + ((totalComprobante == null) ? 0 : totalComprobante.hashCode());
		result = prime * result + ((totalDescuentos == null) ? 0 : totalDescuentos.hashCode());
		result = prime * result + ((totalImpuesto == null) ? 0 : totalImpuesto.hashCode());
		result = prime * result + ((usuarioCreacion == null) ? 0 : usuarioCreacion.hashCode());
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
		ProformasByEmpresaAndEstado other = (ProformasByEmpresaAndEstado) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (consecutivo == null) {
			if (other.consecutivo != null)
				return false;
		} else if (!consecutivo.equals(other.consecutivo))
			return false;
		if (consecutivoProforma == null) {
			if (other.consecutivoProforma != null)
				return false;
		} else if (!consecutivoProforma.equals(other.consecutivoProforma))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
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
		if (usuarioCreacion == null) {
			if (other.usuarioCreacion != null)
				return false;
		} else if (!usuarioCreacion.equals(other.usuarioCreacion))
			return false;
		return true;
	}
	
	

	

}

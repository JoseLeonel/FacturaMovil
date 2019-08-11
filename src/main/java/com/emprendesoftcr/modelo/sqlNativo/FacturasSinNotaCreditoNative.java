package com.emprendesoftcr.modelo.sqlNativo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@BaseNativeQuery(name = "facturasparaanulacion", query = "select facturas.nombre_factura,facturas.total_comprobante,facturas.codigo_moneda,facturas.total_descuentos,facturas.total_impuesto,clientes.nombre_completo,clientes.cedula,facturas.numero_consecutivo,facturas.fecha_emision,usuarios.nombre_usuario ,facturas.id,facturas.usuario_id,facturas.empresa_id,facturas.estado from facturas " + " left join clientes on clientes.id = facturas.cliente_id " + " left join usuarios on usuarios.id = facturas.usuario_id "
		+ " where facturas.empresa_id = :ID_EMPRESA and facturas.estado in :ESTADO and facturas.tipo_doc !='03' and facturas.tipo_doc !='02' and facturas.tipo_doc !='88' and facturas.fecha_emision >= :FECHAINICIAL and facturas.fecha_emision <= :FECHAFINAL  and facturas.usuario_id = and facturas.cliente_id =  order by facturas.fecha_emision desc ")
@Entity
public class FacturasSinNotaCreditoNative {

	@Id
	@Column(name = "id")
	private Long		id;

	@Column(name = "numero_consecutivo")
	private String	numeroConsecutivo;

	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double	totalComprobante;

	@Column(name = "codigo_moneda")
	private String	codigoMoneda;

	@Column(name = "estado")
	private Integer	estado;

	@Column(name = "total_descuentos", precision = 18, scale = 5)
	private Double	totalDescuentos;

	@Column(name = "total_impuesto", precision = 18, scale = 5)
	private Double	totalImpuesto;

	@Column(name = "nombre_completo")
	private String	nombreCompleto;

	@Column(name = "cedula")
	private String	cedula;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date		fechaEmision;

	@Column(name = "nombre_usuario")
	private String	nombreUsuario;
	
	@Column(name = "nombre_factura")
	private String  nombreFactura;

	
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

	
	
	public String getNombreFactura() {
		return nombreFactura;
	}


	
	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}


	public Double getTotalComprobante() {
		return totalComprobante;
	}

	
	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	
	public Integer getEstado() {
		return estado;
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
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

	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		result = prime * result + ((codigoMoneda == null) ? 0 : codigoMoneda.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
		result = prime * result + ((totalComprobante == null) ? 0 : totalComprobante.hashCode());
		result = prime * result + ((totalDescuentos == null) ? 0 : totalDescuentos.hashCode());
		result = prime * result + ((totalImpuesto == null) ? 0 : totalImpuesto.hashCode());
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
		FacturasSinNotaCreditoNative other = (FacturasSinNotaCreditoNative) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		if (codigoMoneda == null) {
			if (other.codigoMoneda != null)
				return false;
		} else if (!codigoMoneda.equals(other.codigoMoneda))
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
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (nombreFactura == null) {
			if (other.nombreFactura != null)
				return false;
		} else if (!nombreFactura.equals(other.nombreFactura))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (numeroConsecutivo == null) {
			if (other.numeroConsecutivo != null)
				return false;
		} else if (!numeroConsecutivo.equals(other.numeroConsecutivo))
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
		return true;
	}


		
	
}

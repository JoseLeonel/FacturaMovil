package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Lista las facturas o tiquetes que se le puede hacer una nota credito 
 * ListarFacturaNCNativa.
 * @author jose.
 * @since 5 oct. 2019
 */

@BaseNativeQuery(name = "facturas_nc", query = "SELECT DISTINCT y.id,y.fecha_emision,y.numero_consecutivo,y.tipo_doc,y.condicion_venta,y.nombre_completo,\n" + 
		"       y.nombre_factura,y.total_comprobante,y.estado,\n" + 
		"       y.act_comercial,y.cedula,y.nombre_usuario\n" + 
		"  FROM (SELECT fac.id,fac.fecha_emision,fac.numero_consecutivo,\n" + 
		"              fac.tipo_doc,fac.condicion_venta,clientes.nombre_completo,\n" + 
		"              fac.nombre_factura,fac.total_comprobante,fac.estado,fac.act_comercial,\n" + 
		"              clientes.cedula,usuarios.nombre_usuario \n" + 
		"         FROM facturas fac\n" + 
		"         inner join detalles det on det.factura_id = fac.id  \n" + 
		"         inner join clientes on clientes.id = fac.cliente_id  \n" + 
		"         inner join usuarios on usuarios.id = fac.usuario_id\n" + 
		" WHERE fac.empresa_id = :ID_EMPRESA and fac.fecha_emision >=  :fechaInicial and  "
		+ " fac.fecha_emision <=  :fechaFinal  and fac.act_comercial and fac.usuario_id and fac.cliente_id and fac.tipo_doc and fac.estado  \n" + 
		"     ) y\n" )
@Entity
public class ListarFacturaNCNativa implements Serializable {
	

	private static final long serialVersionUID = 8778998319884292327L;

	@Id
	@Column(name = "id")
	private Long							id;

	@Column(name = "tipo_doc")
	private String						tipoDoc;


	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "act_comercial")
	private String						codigoActividad;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date							fechaEmision;

	@Column(name = "condicion_venta")
	private String						condicionVenta;

	@Column(name = "numero_consecutivo")
	private String						numeroConsecutivo;


	@Column(name = "nombre_factura")
	private String						nombreFactura;

	@Column(name = "nombre_completo")
	private String						NombreCompleto;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "nombre_usuario")
	private String						nombreUsuario;

	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double						totalComprobante;

	
	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getTipoDoc() {
		return tipoDoc;
	}

	
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	
	public Integer getEstado() {
		return estado;
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
	public String getCodigoActividad() {
		return codigoActividad;
	}

	
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
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

	
	public String getNombreCompleto() {
		return NombreCompleto;
	}

	
	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}

	
	public String getCedula() {
		return cedula;
	}

	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	
	public Double getTotalComprobante() {
		return totalComprobante;
	}


	
	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NombreCompleto == null) ? 0 : NombreCompleto.hashCode());
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		result = prime * result + ((codigoActividad == null) ? 0 : codigoActividad.hashCode());
		result = prime * result + ((condicionVenta == null) ? 0 : condicionVenta.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreFactura == null) ? 0 : nombreFactura.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((numeroConsecutivo == null) ? 0 : numeroConsecutivo.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result + ((totalComprobante == null) ? 0 : totalComprobante.hashCode());
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
		ListarFacturaNCNativa other = (ListarFacturaNCNativa) obj;
		if (NombreCompleto == null) {
			if (other.NombreCompleto != null)
				return false;
		} else if (!NombreCompleto.equals(other.NombreCompleto))
			return false;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		if (codigoActividad == null) {
			if (other.codigoActividad != null)
				return false;
		} else if (!codigoActividad.equals(other.codigoActividad))
			return false;
		if (condicionVenta == null) {
			if (other.condicionVenta != null)
				return false;
		} else if (!condicionVenta.equals(other.condicionVenta))
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
		return true;
	}

	
	

	
	

	

	
	

}

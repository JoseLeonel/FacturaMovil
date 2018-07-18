package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Archivos XML enviados hacienda Hacienda.
 * @author jose.
 * @since 13 jul. 2018
 */
@Entity
@Table(name = "hacienda_xml")
public class Hacienda implements Serializable {

	private static final long	serialVersionUID	= -1338867584101859799L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd’T’HH:mm:ssZ")
	@Column(name = "fecha_emisor")
	private Date							fechaEmisor;

	@Column(name = "clave")
	private String						clave;

	@Column(name = "tipo_emisor")
	private String						tipoEmisor;

	@Column(name = "cedula_emisor")
	private String						cedulaEmisor;

	@Column(name = "tipo_receptor")
	private String						tipoReceptor;

	@Column(name = "cedula_receptor")
	private String						cedulaReceptor;

	@Column(name = "comprobante_xml")
	@Lob
	private Blob							comprobanteXML;

	@Column(name = "consecutivo")
	private String						consecutivo;
	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "status")
	private Integer						status;

	@Column(name = "xErrorCause")
	@Lob
	private Blob							xErrorCause;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	public Hacienda(Integer id, Date fechaEmisor, String clave, String tipoEmisor, String cedulaEmisor, String tipoReceptor, String cedulaReceptor, Blob comprobanteXML, String consecutivo, Integer estado, Integer status, Blob xErrorCause, Empresa empresa, Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.fechaEmisor = fechaEmisor;
		this.clave = clave;
		this.tipoEmisor = tipoEmisor;
		this.cedulaEmisor = cedulaEmisor;
		this.tipoReceptor = tipoReceptor;
		this.cedulaReceptor = cedulaReceptor;
		this.comprobanteXML = comprobanteXML;
		this.consecutivo = consecutivo;
		this.estado = estado;
		this.status = status;
		this.xErrorCause = xErrorCause;
		this.empresa = empresa;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Hacienda() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEmisor() {
		return fechaEmisor;
	}

	public void setFechaEmisor(Date fechaEmisor) {
		this.fechaEmisor = fechaEmisor;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTipoEmisor() {
		return tipoEmisor;
	}

	public void setTipoEmisor(String tipoEmisor) {
		this.tipoEmisor = tipoEmisor;
	}

	public String getCedulaEmisor() {
		return cedulaEmisor;
	}

	public void setCedulaEmisor(String cedulaEmisor) {
		this.cedulaEmisor = cedulaEmisor;
	}

	public String getTipoReceptor() {
		return tipoReceptor;
	}

	public void setTipoReceptor(String tipoReceptor) {
		this.tipoReceptor = tipoReceptor;
	}

	public String getCedulaReceptor() {
		return cedulaReceptor;
	}

	public void setCedulaReceptor(String cedulaReceptor) {
		this.cedulaReceptor = cedulaReceptor;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Blob getComprobanteXML() {
		return comprobanteXML;
	}

	public void setComprobanteXML(Blob comprobanteXML) {
		this.comprobanteXML = comprobanteXML;
	}

	public Blob getxErrorCause() {
		return xErrorCause;
	}

	public void setxErrorCause(Blob xErrorCause) {
		this.xErrorCause = xErrorCause;
	}

}

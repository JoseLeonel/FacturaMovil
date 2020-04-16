package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "archivos_xml")
public class ArchivoXML implements Serializable {

	private static final long	serialVersionUID	= -6813072832317021051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd’T’HH:mm:ssZ")
	@Column(name = "fecha_emisor")
	private Date							fechaEmisor;
	
	@Column(name = "id_factura")
	private Long							numeroFactura;

	@Column(name = "clave")
	private String						clave;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "notificacion")
	private Integer						notificacion;

	@Column(name = "consecutivo")
	private String						consecutivo;

	@Column(name = "ced_emisor")
	private String						cedulaEmisor;

	@Column(name = "nomb_emisor")
	private String						nombreEmisor;

	@Column(name = "ced_receptor")
	private String						cedulaReceptor;

	@Column(name = "nomb_receptor")
	private String						nombreReceptor;

	@Column(name = "path_migra")
	private String						pathMigracion;

	@Column(name = "path_resp")
	private String						pathMigracionRespuesta;

	@Column(name = "num_empresa")
	private Integer						numeroEmpresa;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	public ArchivoXML() {
		super();
	}



	public ArchivoXML(Long id, Date fechaEmisor, Long numeroFactura, String clave, String tipoDoc, Integer estado, Integer notificacion, String consecutivo, String cedulaEmisor, String nombreEmisor, String cedulaReceptor, String nombreReceptor, String pathMigracion, String pathMigracionRespuesta, Integer numeroEmpresa, Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.fechaEmisor = fechaEmisor;
		this.numeroFactura = numeroFactura;
		this.clave = clave;
		this.tipoDoc = tipoDoc;
		this.estado = estado;
		this.notificacion = notificacion;
		this.consecutivo = consecutivo;
		this.cedulaEmisor = cedulaEmisor;
		this.nombreEmisor = nombreEmisor;
		this.cedulaReceptor = cedulaReceptor;
		this.nombreReceptor = nombreReceptor;
		this.pathMigracion = pathMigracion;
		this.pathMigracionRespuesta = pathMigracionRespuesta;
		this.numeroEmpresa = numeroEmpresa;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}



	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Date getFechaEmisor() {
		return fechaEmisor;
	}



	
	public void setFechaEmisor(Date fechaEmisor) {
		this.fechaEmisor = fechaEmisor;
	}



	public Long getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getPathMigracion() {
		return pathMigracion;
	}

	public void setPathMigracion(String pathMigracion) {
		this.pathMigracion = pathMigracion;
	}

	public String getPathMigracionRespuesta() {
		return pathMigracionRespuesta;
	}

	public void setPathMigracionRespuesta(String pathMigracionRespuesta) {
		this.pathMigracionRespuesta = pathMigracionRespuesta;
	}

	public String getCedulaEmisor() {
		return cedulaEmisor;
	}

	public void setCedulaEmisor(String cedulaEmisor) {
		this.cedulaEmisor = cedulaEmisor;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getCedulaReceptor() {
		return cedulaReceptor;
	}

	public void setCedulaReceptor(String cedulaReceptor) {
		this.cedulaReceptor = cedulaReceptor;
	}

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	public Integer getNumeroEmpresa() {
		return numeroEmpresa;
	}

	public void setNumeroEmpresa(Integer numeroEmpresa) {
		this.numeroEmpresa = numeroEmpresa;
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

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Integer getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Integer notificacion) {
		this.notificacion = notificacion;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

}

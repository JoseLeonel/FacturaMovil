package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.emprendesoftcr.utils.Constantes;

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
	private Long							id;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

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

	@Column(name = "nombre_receptor")
	private String						nombreReceptor;

	@Column(name = "correo_receptor")
	private String						correoReceptor;

	@Column(name = "total_receptor")
	private Double						totalReceptor;

	@Column(name = "comprobante_xml")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private Blob							comprobanteXML;

	@Column(name = "consecutivo")
	private String						consecutivo;
	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "status")
	private Integer						status;

	@Column(name = "reintentos")
	private Integer						reintentos;

	@Column(name = "reintentos_aceptacion")
	private Integer						reintentosAceptacion;

	@Column(name = "mensaje_Hacienda")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private Blob							mensajeHacienda;

	@Column(name = "notificacion")
	private Integer						notificacion;

	@Column(name = "xErrorCause")
	@Lob
	@Basic(fetch = FetchType.LAZY)
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

	@Column(name = "observa")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private Blob							observacion;

	// 1= callback 0=cron
	@Column(name = "call_back", columnDefinition = "INT default '0'")
	private Integer						callBack;

	@Column(name = "id_factura")
	private Long							numeroFactura;
//////
	@Column(name = "migrado_disc", columnDefinition = "INT default '0'")
	private Integer						migradoADisco;

	@Column(name = "path_migra")
	private String						pathMigracion;

	@Column(name = "path_resp")
	private String						pathMigracionRespuesta;

	public Hacienda() {
		super();
		this.reintentos = Constantes.ZEROS;
		this.created_at = new Date();
		this.updated_at = new Date();
		this.estado = Constantes.HACIENDA_ESTADO_PENDIENTE_FIRMAR;
		this.notificacion = Constantes.HACIENDA_NOTIFICAR_CLIENTE_PENDIENTE;

	}

	public Integer getMigradoADisco() {
		return migradoADisco;
	}

	public void setMigradoADisco(Integer migradoADisco) {
		this.migradoADisco = migradoADisco;
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

	public Integer getReintentos() {
		return reintentos;
	}

	public void setReintentos(Integer reintentos) {
		this.reintentos = reintentos;
	}

	public Integer getReintentosAceptacion() {
		return reintentosAceptacion;
	}

	public void setReintentosAceptacion(Integer reintentosAceptacion) {
		this.reintentosAceptacion = reintentosAceptacion;
	}

	public Blob getMensajeHacienda() {
		return mensajeHacienda;
	}

	public void setMensajeHacienda(Blob mensajeHacienda) {
		this.mensajeHacienda = mensajeHacienda;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Integer getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Integer notificacion) {
		this.notificacion = notificacion;
	}

	public String getNombreReceptor() {
		return nombreReceptor;
	}

	public void setNombreReceptor(String nombreReceptor) {
		this.nombreReceptor = nombreReceptor;
	}

	public String getCorreoReceptor() {
		return correoReceptor;
	}

	public void setCorreoReceptor(String correoReceptor) {
		this.correoReceptor = correoReceptor;
	}

	public Double getTotalReceptor() {
		return totalReceptor;
	}

	public void setTotalReceptor(Double totalReceptor) {
		this.totalReceptor = totalReceptor;
	}

	public Blob getObservacion() {
		return observacion;
	}

	public void setObservacion(Blob observacion) {
		this.observacion = observacion;
	}

	public Integer getCallBack() {
		return callBack;
	}

	public void setCallBack(Integer callBack) {
		this.callBack = callBack;
	}

	public Long getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

}

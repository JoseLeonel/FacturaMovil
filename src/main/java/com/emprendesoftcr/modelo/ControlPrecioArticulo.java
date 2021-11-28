package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "control_precio_art")
public class ControlPrecioArticulo implements Serializable {

	private static final long	serialVersionUID	= -7361693121644891115L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@ManyToOne
	@JoinColumn(name = "articulo_id", nullable = false)
	private Articulo					articulo;

	
	

	@ManyToOne
	@JoinColumn(name = "usua_crea_id", nullable = false)
	private Usuario						responsableCambioPrecio;

	@ManyToOne
	@JoinColumn(name = "usua_actu_id", nullable = false)
	private Usuario						responbleAceptarPrecio;

	@Column(name = "estado")
	private Integer						estado;																		// 1 = Pendiente de aceptar 2 = Aceptado 3 Rechazado
	@Column(name = "precio_actual", precision = 18, scale = 5)
	private Double						precioPublicoActual;

	@Column(name = "precio_nuevo", precision = 18, scale = 5)
	private Double						precioPublicoNuevo;

	@Column(name = "costo", precision = 18, scale = 5)
	private Double						costo;

	@Column(name = "ganancia", precision = 18, scale = 5)
	private Double						ganancia;
	@Column(name = "consecutivo")
	private String						consecutivo;
	@Column(name = "ruta_archivo")
	private String						rutaArchivoCompra;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	


	public Usuario getResponsableCambioPrecio() {
		return responsableCambioPrecio;
	}

	public void setResponsableCambioPrecio(Usuario responsableCambioPrecio) {
		this.responsableCambioPrecio = responsableCambioPrecio;
	}

	public Usuario getResponbleAceptarPrecio() {
		return responbleAceptarPrecio;
	}

	public void setResponbleAceptarPrecio(Usuario responbleAceptarPrecio) {
		this.responbleAceptarPrecio = responbleAceptarPrecio;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Double getPrecioPublicoActual() {
		return precioPublicoActual;
	}

	public void setPrecioPublicoActual(Double precioPublicoActual) {
		this.precioPublicoActual = precioPublicoActual;
	}

	public Double getPrecioPublicoNuevo() {
		return precioPublicoNuevo;
	}

	public void setPrecioPublicoNuevo(Double precioPublicoNuevo) {
		this.precioPublicoNuevo = precioPublicoNuevo;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getGanancia() {
		return ganancia;
	}

	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	

	public String getRutaArchivoCompra() {
		return rutaArchivoCompra;
	}

	public void setRutaArchivoCompra(String rutaArchivoCompra) {
		this.rutaArchivoCompra = rutaArchivoCompra;
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

}

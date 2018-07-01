package com.factura.FacturaElectronica.modelo;

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

/**
 * Kardex. Registro de la trazabilidad del articulo en el inventario
 * @author jose.
 * @since 12 abr. 2018
 */
@Entity
@Table(name = "kardex")
public class Kardex implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "cantidad_solicitada")
	private Double						cantidadSolicitada;

	@Column(name = "cantidad_actual")
	private Double						cantidadActual;

	@Column(name = "costo_actual")
	private Double						costoActual;

	@Column(name = "total_costo_actual")
	private Double						totalCostoActual;

	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "observacion")
	private String						observacion;

	@Column(name = "cantidad_nueva")
	private Double						cantidadNueva;

	@Column(name = "costo_nuevo")
	private Double						costoNuevo;

	@Column(name = "total_costo_nuevo")
	private Double						totalCostoNuevo;

	@Column(name = "consecutivo")
	private String						consecutivo;

	@Column(name = "tipo")
	private String						tipo;

	@Column(name = "motivo")
	private String						motivo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@ManyToOne
	@JoinColumn(name = "articulo_id")
	private Articulo					articulo;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public Kardex() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Kardex(Integer id, Double cantidadSolicitada, Double cantidadActual, Double costoActual, Double totalCostoActual, String codigo, String observacion, Double cantidadNueva, Double costoNuevo, Double totalCostoNuevo, String consecutivo, String tipo, String motivo, Date created_at, Date updated_at, Articulo articulo, Usuario usuario) {
		super();
		this.id = id;
		this.cantidadSolicitada = cantidadSolicitada;
		this.cantidadActual = cantidadActual;
		this.costoActual = costoActual;
		this.totalCostoActual = totalCostoActual;
		this.codigo = codigo;
		this.observacion = observacion;
		this.cantidadNueva = cantidadNueva;
		this.costoNuevo = costoNuevo;
		this.totalCostoNuevo = totalCostoNuevo;
		this.consecutivo = consecutivo;
		this.tipo = tipo;
		this.motivo = motivo;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.articulo = articulo;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(Double cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public Double getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(Double cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public Double getCostoActual() {
		return costoActual;
	}

	public void setCostoActual(Double costoActual) {
		this.costoActual = costoActual;
	}

	public Double getTotalCostoActual() {
		return totalCostoActual;
	}

	public void setTotalCostoActual(Double totalCostoActual) {
		this.totalCostoActual = totalCostoActual;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Double getCantidadNueva() {
		return cantidadNueva;
	}

	public void setCantidadNueva(Double cantidadNueva) {
		this.cantidadNueva = cantidadNueva;
	}

	public Double getCostoNuevo() {
		return costoNuevo;
	}

	public void setCostoNuevo(Double costoNuevo) {
		this.costoNuevo = costoNuevo;
	}

	public Double getTotalCostoNuevo() {
		return totalCostoNuevo;
	}

	public void setTotalCostoNuevo(Double totalCostoNuevo) {
		this.totalCostoNuevo = totalCostoNuevo;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
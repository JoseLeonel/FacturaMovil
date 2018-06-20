package com.factura.FacturaElectronica.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private BigDecimal				cantidadSolicitada;

	@Column(name = "cantidad_actual")
	private BigDecimal				cantidadActual;

	@Column(name = "costo_actual")
	private BigDecimal				costoActual;

	@Column(name = "total_costo_actual")
	private BigDecimal				totalCostoActual;

	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "observacion")
	private String						observacion;

	@Column(name = "cantidad_nueva")
	private BigDecimal				cantidadNueva;

	@Column(name = "costo_nuevo")
	private BigDecimal				costoNuevo;

	@Column(name = "total_costo_nuevo")
	private BigDecimal				totalCostoNuevo;

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
	@JoinColumn(name = "inventario_id")
	private Inventario				inventario;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public Kardex() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Kardex(Integer id, BigDecimal cantidadSolicitada, BigDecimal cantidadActual, BigDecimal costoActual, BigDecimal totalCostoActual, String codigo, String observacion, BigDecimal cantidadNueva, BigDecimal costoNuevo, BigDecimal totalCostoNuevo, String consecutivo, String tipo, String motivo, Date created_at, Date updated_at, Inventario inventario, Usuario usuario) {
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
		this.inventario = inventario;
		this.usuario = usuario;
	}

	public BigDecimal getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(BigDecimal cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public BigDecimal getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(BigDecimal cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public BigDecimal getCostoActual() {
		return costoActual;
	}

	public void setCostoActual(BigDecimal costoActual) {
		this.costoActual = costoActual;
	}

	public BigDecimal getTotalCostoActual() {
		return totalCostoActual;
	}

	public void setTotalCostoActual(BigDecimal totalCostoActual) {
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

	public BigDecimal getCantidadNueva() {
		return cantidadNueva;
	}

	public void setCantidadNueva(BigDecimal cantidadNueva) {
		this.cantidadNueva = cantidadNueva;
	}

	public BigDecimal getCostoNuevo() {
		return costoNuevo;
	}

	public void setCostoNuevo(BigDecimal costoNuevo) {
		this.costoNuevo = costoNuevo;
	}

	public BigDecimal getTotalCostoNuevo() {
		return totalCostoNuevo;
	}

	public void setTotalCostoNuevo(BigDecimal totalCostoNuevo) {
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

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
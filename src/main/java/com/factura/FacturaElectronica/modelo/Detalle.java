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
 * Articulos relacionados a la venta Detalle.
 * @author jose.
 * @since 22 abr. 2018
 */

@Entity
@Table(name = "detalles")
public class Detalle implements Serializable {

	private static final long	serialVersionUID	= 5443162013611771917L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "numero_linea")
	private Integer						numeroLinea;

	@Column(name = "costo")
	private Float							costo;

	@Column(name = "precio_unitario")
	private Float							precioUnitario;

	@Column(name = "cantidad")
	private Float							cantidad;

	@Column(name = "monto_total")
	private Float							montoTotal;

	@Column(name = "Monto_descuento")
	private Float							montoDescuento;

	@Column(name = "naturaleza_descuento")
	private Float							naturalezaDescuento;

	@Column(name = "sub_total")
	private Float							subTotal;

	@Column(name = "impuesto")
	private Float							impuesto;

	@Column(name = "monto_total_linea")
	private Float							montoTotalLinea;

	@Column(name = "ganancia")
	private Float							ganancia;

	@Column(name = "porcentaje_desc")
	private Float							porcentajeDesc;

	@Column(name = "observacion")
	private String						observacion;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@ManyToOne
	@JoinColumn(name = "factura_id")
	private Factura						factura;

	@ManyToOne
	@JoinColumn(name = "inventario_id")
	private Inventario				inventario;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public Detalle() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Detalle(Integer id, Integer numeroLinea, Float costo, Float precioUnitario, Float cantidad, Float montoTotal, Float montoDescuento, Float naturalezaDescuento, Float subTotal, Float impuesto, Float montoTotalLinea, Float ganancia, Float porcentajeDesc, String observacion, Date created_at, Date updated_at, Factura factura, Inventario inventario, Usuario usuario) {
		super();
		this.id = id;
		this.numeroLinea = numeroLinea;
		this.costo = costo;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.montoTotal = montoTotal;
		this.montoDescuento = montoDescuento;
		this.naturalezaDescuento = naturalezaDescuento;
		this.subTotal = subTotal;
		this.impuesto = impuesto;
		this.montoTotalLinea = montoTotalLinea;
		this.ganancia = ganancia;
		this.porcentajeDesc = porcentajeDesc;
		this.observacion = observacion;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.factura = factura;
		this.inventario = inventario;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public Float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Float getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(Float montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public Float getNaturalezaDescuento() {
		return naturalezaDescuento;
	}

	public void setNaturalezaDescuento(Float naturalezaDescuento) {
		this.naturalezaDescuento = naturalezaDescuento;
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	public Float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

	public Float getMontoTotalLinea() {
		return montoTotalLinea;
	}

	public void setMontoTotalLinea(Float montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}

	public Float getGanancia() {
		return ganancia;
	}

	public void setGanancia(Float ganancia) {
		this.ganancia = ganancia;
	}

	public Float getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(Float porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
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
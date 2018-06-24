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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.factura.FacturaElectronica.web.command.DetalleFacturaCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "precio_unitario")
	private Double						precioUnitario;

	@Column(name = "cantidad")
	private Double						cantidad;

	@Column(name = "monto_total")
	private Double						montoTotal;

	@Column(name = "Monto_descuento")
	private Double						montoDescuento;

	@Column(name = "naturaleza_descuento")
	private Double						naturalezaDescuento;

	@Column(name = "sub_total")
	private Double						subTotal;

	@Column(name = "impuesto")
	private Double						impuesto;

	@Column(name = "monto_impuesto")
	private Double						montoImpuesto;

	@Column(name = "monto_total_linea")
	private Double						montoTotalLinea;

	@Column(name = "ganancia")
	private Double						ganancia;

	@Column(name = "porcentaje_desc")
	private Double						porcentajeDesc;

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

	@JsonIgnore
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "factura_id")
	private Factura						factura;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "articulo_id")
	private Articulo					articulo;

	@JsonIgnore
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public Detalle() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Detalle(Integer id, Integer numeroLinea, Double precioUnitario, Double cantidad, Double montoTotal, Double montoDescuento, Double naturalezaDescuento, Double subTotal, Double impuesto, Double montoImpuesto, Double montoTotalLinea, Double ganancia, Double porcentajeDesc, String observacion, Date created_at, Date updated_at, Factura factura, Articulo articulo, Usuario usuario) {
		super();
		this.id = id;
		this.numeroLinea = numeroLinea;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.montoTotal = montoTotal;
		this.montoDescuento = montoDescuento;
		this.naturalezaDescuento = naturalezaDescuento;
		this.subTotal = subTotal;
		this.impuesto = impuesto;
		this.montoImpuesto = montoImpuesto;
		this.montoTotalLinea = montoTotalLinea;
		this.ganancia = ganancia;
		this.porcentajeDesc = porcentajeDesc;
		this.observacion = observacion;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.factura = factura;
		this.articulo = articulo;
		this.usuario = usuario;
	}

	public Detalle(DetalleFacturaCommand detalleFacturaCommand) {
		super();
		this.id = detalleFacturaCommand.getId();
		this.numeroLinea = detalleFacturaCommand.getNumeroLinea();
		this.precioUnitario = detalleFacturaCommand.getPrecioUnitario();
    this.cantidad = detalleFacturaCommand.getCantidad();
		this.montoTotal =detalleFacturaCommand.getMontoTotal();
    this.montoDescuento = detalleFacturaCommand.getMontoDescuento();
		this.naturalezaDescuento =  detalleFacturaCommand.getNaturalezaDescuento();
    this.subTotal = detalleFacturaCommand.getSubTotal();
    this.impuesto = detalleFacturaCommand.getImpuesto();
    this.montoImpuesto = detalleFacturaCommand.getMontoImpuesto();
    this.montoTotalLinea = detalleFacturaCommand.getMontoTotalLinea();
    this.ganancia = detalleFacturaCommand.getGanancia();
    this.porcentajeDesc = detalleFacturaCommand.getPorcentajeDesc();
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

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Double getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(Double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public Double getNaturalezaDescuento() {
		return naturalezaDescuento;
	}

	public void setNaturalezaDescuento(Double naturalezaDescuento) {
		this.naturalezaDescuento = naturalezaDescuento;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public Double getMontoImpuesto() {
		return montoImpuesto;
	}

	public void setMontoImpuesto(Double montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
	}

	public Double getMontoTotalLinea() {
		return montoTotalLinea;
	}

	public void setMontoTotalLinea(Double montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}

	public Double getGanancia() {
		return ganancia;
	}

	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	public Double getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(Double porcentajeDesc) {
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
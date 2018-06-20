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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.factura.FacturaElectronica.Utils.Constantes;
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
	private BigDecimal				precioUnitario;

	@Column(name = "cantidad")
	private BigDecimal				cantidad;

	@Column(name = "monto_total")
	private BigDecimal				montoTotal;

	@Column(name = "Monto_descuento")
	private BigDecimal				montoDescuento;

	@Column(name = "naturaleza_descuento")
	private BigDecimal				naturalezaDescuento;

	@Column(name = "sub_total")
	private BigDecimal				subTotal;

	@Column(name = "impuesto")
	private BigDecimal				impuesto;

	@Column(name = "monto_impuesto")
	private BigDecimal				montoImpuesto;

	@Column(name = "monto_total_linea")
	private BigDecimal				montoTotalLinea;

	@Column(name = "ganancia")
	private BigDecimal				ganancia;

	@Column(name = "porcentaje_desc")
	private BigDecimal				porcentajeDesc;

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

	public Detalle(Integer id, Integer numeroLinea, BigDecimal precioUnitario, BigDecimal cantidad, BigDecimal montoTotal, BigDecimal montoDescuento, BigDecimal naturalezaDescuento, BigDecimal subTotal, BigDecimal impuesto, BigDecimal montoImpuesto, BigDecimal montoTotalLinea, BigDecimal ganancia, BigDecimal porcentajeDesc, String observacion, Date created_at, Date updated_at, Factura factura, Articulo articulo, Usuario usuario) {
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
		this.created_at = new Date();
		this.updated_at = new Date();
		this.numeroLinea = detalleFacturaCommand.getNumeroLinea();
		this.precioUnitario = detalleFacturaCommand.getPrecioUnitario();
		this.cantidad = detalleFacturaCommand.getCantidad();
		this.montoTotal = detalleFacturaCommand.getMontoTotal();
		this.montoDescuento = detalleFacturaCommand.getMontoDescuento();
		this.naturalezaDescuento = detalleFacturaCommand.getNaturalezaDescuento();
		this.subTotal = detalleFacturaCommand.getSubTotal();
		this.impuesto = detalleFacturaCommand.getImpuesto();
		this.montoImpuesto = detalleFacturaCommand.getMontoImpuesto();
		this.montoTotalLinea = detalleFacturaCommand.getMontoTotalLinea();
		this.ganancia = detalleFacturaCommand.getGanancia();
		this.porcentajeDesc = detalleFacturaCommand.getPorcentajeDesc();
		this.observacion = Constantes.EMPTY;

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

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(BigDecimal montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public BigDecimal getNaturalezaDescuento() {
		return naturalezaDescuento;
	}

	public void setNaturalezaDescuento(BigDecimal naturalezaDescuento) {
		this.naturalezaDescuento = naturalezaDescuento;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}

	public BigDecimal getMontoImpuesto() {
		return montoImpuesto;
	}

	public void setMontoImpuesto(BigDecimal montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
	}

	public BigDecimal getMontoTotalLinea() {
		return montoTotalLinea;
	}

	public void setMontoTotalLinea(BigDecimal montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}

	public BigDecimal getGanancia() {
		return ganancia;
	}

	public void setGanancia(BigDecimal ganancia) {
		this.ganancia = ganancia;
	}

	public BigDecimal getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(BigDecimal porcentajeDesc) {
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
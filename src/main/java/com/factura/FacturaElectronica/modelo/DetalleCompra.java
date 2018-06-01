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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.factura.FacturaElectronica.web.command.DetalleCompraCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modelo de los productos ingresados al inventario DetalleCompra.
 * @author jose.
 * @since 21 may. 2018
 */
@Entity
@Table(name = "detalles_compras")
public class DetalleCompra implements Serializable {

	

	private static final long serialVersionUID = -7379156339923371149L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "numero_linea")
	private Integer						numeroLinea;

	@Column(name = "costo")
	private Double							costo;

	@Column(name = "cantidad")
	private Double							cantidad;

	@Column(name = "impuesto")
	private Double							impuesto;

	@Column(name = "descuento")
	private Double							descuento;

	@Column(name = "sub_total")
	private Double							subTotal;

	@CreatedDate
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date							created_at;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;
  
	@JsonIgnore
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "compra_id", nullable = false)
	private Compra						compra;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "articulo_id", nullable = false)
	private Articulo					articulo;

	public DetalleCompra(Integer id, Integer numeroLinea, Double costo, Double cantidad, Double impuesto, Double descuento, Double subTotal, Date created_at, Date updated_at, Compra compra, Articulo articulo) {
		super();
		this.id = id;
		this.numeroLinea = numeroLinea;
		this.costo = costo;
		this.cantidad = cantidad;
		this.impuesto = impuesto;
		this.descuento = descuento;
		this.subTotal = subTotal;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.compra = compra;
		this.articulo = articulo;
	}

	public DetalleCompra(DetalleCompraCommand detalleCompraCommand) {
		super();
		this.numeroLinea = detalleCompraCommand.getLinea();
		this.costo = detalleCompraCommand.getCosto();
		this.cantidad = detalleCompraCommand.getCantidad();
		this.articulo = detalleCompraCommand.getArticulo();
		this.descuento = detalleCompraCommand.getDescuento();
		this.impuesto = detalleCompraCommand.getImpuesto();
		this.subTotal = detalleCompraCommand.getSubTotal();
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	
	
	
	public DetalleCompra() {
		super();
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

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}

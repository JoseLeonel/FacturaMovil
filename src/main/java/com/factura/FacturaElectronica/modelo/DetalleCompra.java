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

	private static final long	serialVersionUID	= -7379156339923371149L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "numero_linea")
	private Integer						numeroLinea;

	@Column(name = "costo")
	private BigDecimal				costo;

	@Column(name = "cantidad")
	private BigDecimal				cantidad;

	@Column(name = "impuesto")
	private BigDecimal				impuesto;

	@Column(name = "descuento")
	private BigDecimal				descuento;

	@Column(name = "sub_total")
	private BigDecimal				subTotal;

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

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
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

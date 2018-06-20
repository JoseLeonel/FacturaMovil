package com.factura.FacturaElectronica.web.command;

import java.math.BigDecimal;

import com.factura.FacturaElectronica.modelo.Articulo;

public class DetalleCompraCommand {

	private Integer			linea;
	private Integer			articulo_id;
	private String			codigo;
	private String			descripcion;
	private BigDecimal	cantidad;
	private BigDecimal	costo;
	private BigDecimal	impuesto;
	private BigDecimal	descuento;
	private BigDecimal	subTotal;
	private Articulo		articulo;

	public DetalleCompraCommand() {
		super();
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

	public Integer getArticulo_id() {
		return articulo_id;
	}

	public void setArticulo_id(Integer articulo_id) {
		this.articulo_id = articulo_id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
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

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}

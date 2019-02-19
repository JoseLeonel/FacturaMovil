package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;

public class DetalleCompraEsperaCommand {
	
	private Long							id;

	private Integer						numeroLinea;

	private Double						costo;

	private Double						precio;

	private Double						cantidad;


	private Double						totalImpuesto;

	private Double						totalDescuento;

	private Double						impuesto;

	private Double						descuento;

	private Double						montoTotalLinea;

	private Date							created_at;

	private Date							updated_at;

	private Compra						compra;

	private Articulo					articulo;

	public DetalleCompraEsperaCommand(DetalleCompra detalleCompra) {
		super();
		this.id = detalleCompra.getId();
		this.numeroLinea = detalleCompra.getNumeroLinea();
		this.costo = detalleCompra.getCosto();
		this.precio = detalleCompra.getPrecio();
		this.cantidad = detalleCompra.getCantidad();
		this.totalImpuesto = detalleCompra.getTotalImpuesto();
		this.totalDescuento = detalleCompra.getTotalDescuento();
		this.impuesto = detalleCompra.getImpuesto();
		this.descuento = detalleCompra.getDescuento();
		this.montoTotalLinea = detalleCompra.getMontoTotalLinea();
		this.created_at = detalleCompra.getCreated_at();
		this.updated_at = detalleCompra.getUpdated_at();
		this.compra = detalleCompra.getCompra();
		this.articulo = detalleCompra.getArticulo();
	}

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
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

	
	public Double getPrecio() {
		return precio;
	}

	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	
	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	
	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	
	public Double getTotalDescuento() {
		return totalDescuento;
	}

	
	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
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

	
	public Double getMontoTotalLinea() {
		return montoTotalLinea;
	}

	
	public void setMontoTotalLinea(Double montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
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

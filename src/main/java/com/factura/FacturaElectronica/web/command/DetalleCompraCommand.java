package com.factura.FacturaElectronica.web.command;

import com.factura.FacturaElectronica.modelo.Articulo;

public class DetalleCompraCommand {

	private Integer	linea;
	private Integer	articulo_id;
	private String	codigo;
	private String	descripcion;
	private Double	cantidad;
	private Double	costo;
	private Double	impuesto;
	private Double	descuento;
	private Double	subTotal;
	private Articulo articulo;

	
	

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

	
	public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	
	public Double getCosto() {
		return costo;
	}

	
	public void setCosto(Double costo) {
		this.costo = costo;
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


	
	public Articulo getArticulo() {
		return articulo;
	}


	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	

}

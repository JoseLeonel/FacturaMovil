package com.factura.FacturaElectronica.web.command;

import java.math.BigDecimal;

/**
 * Linea de detalles que viene en la venta desde el front end
 * DetalleFacturaCommand.
 * @author jose.
 * @since 5 jun. 2018
 */
public class DetalleFacturaCommand {

	private Integer	id;
	private Integer	articulo_id;

	private Integer	numeroLinea;

	private BigDecimal		precioUnitario;

	private BigDecimal		cantidad;

	private BigDecimal		montoTotal;

	private BigDecimal		montoDescuento;

	private BigDecimal		naturalezaDescuento;

	private BigDecimal		subTotal;

	private BigDecimal		impuesto;

	private BigDecimal		montoImpuesto;

	private BigDecimal		montoTotalLinea;

	private BigDecimal		ganancia;

	private BigDecimal		porcentajeDesc;

	public DetalleFacturaCommand() {
		super();
	}

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getArticulo_id() {
		return articulo_id;
	}

	
	public void setArticulo_id(Integer articulo_id) {
		this.articulo_id = articulo_id;
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



}

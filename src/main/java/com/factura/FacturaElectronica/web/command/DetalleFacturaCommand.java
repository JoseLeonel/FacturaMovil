package com.factura.FacturaElectronica.web.command;

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

	private Double		precioUnitario;

	private Double		cantidad;

	private Double		montoTotal;

	private Double		montoDescuento;

	private Double		naturalezaDescuento;

	private Double		subTotal;

	private Double		impuesto;

	private Double		montoImpuesto;

	private Double		montoTotalLinea;

	private Double		ganancia;

	private Double		porcentajeDesc;

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



}

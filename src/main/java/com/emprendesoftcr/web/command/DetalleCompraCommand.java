package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Articulo;

public class DetalleCompraCommand {

	private Integer		numeroLinea;
	private Double		porcentajeImpuesto;
	private Long			articulo_id;
	private String		codigo;
	private String		descripcion;
	private Double		cantidad;
	private Double		costo;
	private Double		ganancia;
	private Double		precio;
	private Double		totalImpuesto;
	private Double		totalDescuento;
	private Double		impuesto;
	private Double		descuento;
	private Double		montoTotalLinea;
	private Articulo	articulo;

	private String		codigoComercialTipo1;

	private String		codigoComercial1;

	private String		codigoComercialTipo2;

	private String		codigoComercial2;

	private String		codigoComercialTipo3;

	private String		codigoComercial3;

	private String		codigoCabys;
	private String						codigoTipoImpuesto;
	
	private String						tarifaImpuesto;
	private Long  ubicacion;

	public DetalleCompraCommand() {
		super();
	}

	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public Long getArticulo_id() {
		return articulo_id;
	}

	public void setArticulo_id(Long articulo_id) {
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

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getPorcentajeImpuesto() {
		return porcentajeImpuesto;
	}

	public void setPorcentajeImpuesto(Double porcentajeImpuesto) {
		this.porcentajeImpuesto = porcentajeImpuesto;
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

	public String getCodigoComercialTipo1() {
		return codigoComercialTipo1;
	}

	public void setCodigoComercialTipo1(String codigoComercialTipo1) {
		this.codigoComercialTipo1 = codigoComercialTipo1;
	}

	public String getCodigoComercial1() {
		return codigoComercial1;
	}

	public void setCodigoComercial1(String codigoComercial1) {
		this.codigoComercial1 = codigoComercial1;
	}

	public String getCodigoComercialTipo2() {
		return codigoComercialTipo2;
	}

	public void setCodigoComercialTipo2(String codigoComercialTipo2) {
		this.codigoComercialTipo2 = codigoComercialTipo2;
	}

	public String getCodigoComercial2() {
		return codigoComercial2;
	}

	public void setCodigoComercial2(String codigoComercial2) {
		this.codigoComercial2 = codigoComercial2;
	}

	public String getCodigoComercialTipo3() {
		return codigoComercialTipo3;
	}

	public void setCodigoComercialTipo3(String codigoComercialTipo3) {
		this.codigoComercialTipo3 = codigoComercialTipo3;
	}

	public String getCodigoComercial3() {
		return codigoComercial3;
	}

	public void setCodigoComercial3(String codigoComercial3) {
		this.codigoComercial3 = codigoComercial3;
	}

	public String getCodigoCabys() {
		return codigoCabys;
	}

	public void setCodigoCabys(String codigoCabys) {
		this.codigoCabys = codigoCabys;
	}

	
	public String getCodigoTipoImpuesto() {
		return codigoTipoImpuesto;
	}

	
	public void setCodigoTipoImpuesto(String codigoTipoImpuesto) {
		this.codigoTipoImpuesto = codigoTipoImpuesto;
	}

	
	public String getTarifaImpuesto() {
		return tarifaImpuesto;
	}

	
	public void setTarifaImpuesto(String tarifaImpuesto) {
		this.tarifaImpuesto = tarifaImpuesto;
	}

	
	public Long getUbicacion() {
		return ubicacion;
	}

	
	public void setUbicacion(Long ubicacion) {
		this.ubicacion = ubicacion;
	}

}

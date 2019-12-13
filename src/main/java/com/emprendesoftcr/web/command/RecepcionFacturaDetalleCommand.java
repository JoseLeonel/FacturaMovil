package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.RecepcionFactura;


public class RecepcionFacturaDetalleCommand {

	private Long id;

	private Integer numeroLinea;

	private Double cantidad;

	private String unidadMedida;

	private String detalle;

	private Double precioUnitario;

	private Double montoTotal;

	private Double subTotal;

	private Double montoTotalLinea;

	private Double impuestoNeto;

	private String codigoComercialTipo;

	private String codigoComercialCodigo;

	private Double descuentoMonto;

	private String descuentoNaturaleza;

	private String impuestoCodigo;

	private String impuestoCodigoTarifa;

	private Double impuestoTarifa;

	private Double impuestoMonto;

	private String impuestoExoneracionTipoDocumento;

	private String impuestoExoneracionNumeroDocumento;

	private String impuestoExoneracionNombreInstitucion;

	private String impuestoExoneracionFechaEmision;

	private Double impuestoExoneracionPorcentaje;

	private Double impuestoExoneracionMonto;

	private Double baseImponible;

	private RecepcionFactura recepcionFactura;
  private String detalleImpuestos;
	
	
	
	
	public RecepcionFacturaDetalleCommand() {
		super();
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


	
	public Double getCantidad() {
		return cantidad;
	}


	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}


	
	public String getUnidadMedida() {
		return unidadMedida;
	}


	
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}


	
	public String getDetalle() {
		return detalle;
	}


	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	
	public Double getPrecioUnitario() {
		return precioUnitario;
	}


	
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	
	public Double getMontoTotal() {
		return montoTotal;
	}


	
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}


	
	public Double getSubTotal() {
		return subTotal;
	}


	
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}


	
	public Double getMontoTotalLinea() {
		return montoTotalLinea;
	}


	
	public void setMontoTotalLinea(Double montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}


	
	public Double getImpuestoNeto() {
		return impuestoNeto;
	}


	
	public void setImpuestoNeto(Double impuestoNeto) {
		this.impuestoNeto = impuestoNeto;
	}


	
	public String getCodigoComercialTipo() {
		return codigoComercialTipo;
	}


	
	public void setCodigoComercialTipo(String codigoComercialTipo) {
		this.codigoComercialTipo = codigoComercialTipo;
	}


	
	public String getCodigoComercialCodigo() {
		return codigoComercialCodigo;
	}


	
	public void setCodigoComercialCodigo(String codigoComercialCodigo) {
		this.codigoComercialCodigo = codigoComercialCodigo;
	}


	
	public Double getDescuentoMonto() {
		return descuentoMonto;
	}


	
	public void setDescuentoMonto(Double descuentoMonto) {
		this.descuentoMonto = descuentoMonto;
	}


	
	public String getDescuentoNaturaleza() {
		return descuentoNaturaleza;
	}


	
	public void setDescuentoNaturaleza(String descuentoNaturaleza) {
		this.descuentoNaturaleza = descuentoNaturaleza;
	}


	
	public String getImpuestoCodigo() {
		return impuestoCodigo;
	}


	
	public void setImpuestoCodigo(String impuestoCodigo) {
		this.impuestoCodigo = impuestoCodigo;
	}


	
	public String getImpuestoCodigoTarifa() {
		return impuestoCodigoTarifa;
	}


	
	public void setImpuestoCodigoTarifa(String impuestoCodigoTarifa) {
		this.impuestoCodigoTarifa = impuestoCodigoTarifa;
	}


	
	public Double getImpuestoTarifa() {
		return impuestoTarifa;
	}

	
	
	public void setImpuestoTarifa(Double impuestoTarifa) {
		this.impuestoTarifa = impuestoTarifa;
	}


	
	public Double getImpuestoMonto() {
		return impuestoMonto;
	}


	
	public void setImpuestoMonto(Double impuestoMonto) {
		this.impuestoMonto = impuestoMonto;
	}


	
	public String getImpuestoExoneracionTipoDocumento() {
		return impuestoExoneracionTipoDocumento;
	}


	
	public void setImpuestoExoneracionTipoDocumento(String impuestoExoneracionTipoDocumento) {
		this.impuestoExoneracionTipoDocumento = impuestoExoneracionTipoDocumento;
	}


	
	public String getImpuestoExoneracionNumeroDocumento() {
		return impuestoExoneracionNumeroDocumento;
	}


	
	public void setImpuestoExoneracionNumeroDocumento(String impuestoExoneracionNumeroDocumento) {
		this.impuestoExoneracionNumeroDocumento = impuestoExoneracionNumeroDocumento;
	}


	
	public String getImpuestoExoneracionNombreInstitucion() {
		return impuestoExoneracionNombreInstitucion;
	}


	
	public void setImpuestoExoneracionNombreInstitucion(String impuestoExoneracionNombreInstitucion) {
		this.impuestoExoneracionNombreInstitucion = impuestoExoneracionNombreInstitucion;
	}


	
	public String getImpuestoExoneracionFechaEmision() {
		return impuestoExoneracionFechaEmision;
	}


	
	public void setImpuestoExoneracionFechaEmision(String impuestoExoneracionFechaEmision) {
		this.impuestoExoneracionFechaEmision = impuestoExoneracionFechaEmision;
	}


	
	public Double getImpuestoExoneracionPorcentaje() {
		return impuestoExoneracionPorcentaje;
	}


	
	public void setImpuestoExoneracionPorcentaje(Double impuestoExoneracionPorcentaje) {
		this.impuestoExoneracionPorcentaje = impuestoExoneracionPorcentaje;
	}


	
	public Double getImpuestoExoneracionMonto() {
		return impuestoExoneracionMonto;
	}


	
	public void setImpuestoExoneracionMonto(Double impuestoExoneracionMonto) {
		this.impuestoExoneracionMonto = impuestoExoneracionMonto;
	}


	
	public Double getBaseImponible() {
		return baseImponible;
	}


	
	public void setBaseImponible(Double baseImponible) {
		this.baseImponible = baseImponible;
	}


	
	public RecepcionFactura getRecepcionFactura() {
		return recepcionFactura;
	}


	
	public void setRecepcionFactura(RecepcionFactura recepcionFactura) {
		this.recepcionFactura = recepcionFactura;
	}


	
	public String getDetalleImpuestos() {
		return detalleImpuestos;
	}


	
	public void setDetalleImpuestos(String detalleImpuestos) {
		this.detalleImpuestos = detalleImpuestos;
	}

	
	


}
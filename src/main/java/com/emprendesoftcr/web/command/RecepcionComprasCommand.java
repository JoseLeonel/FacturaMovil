package com.emprendesoftcr.web.command;

import java.sql.Date;
/**
 * Aceptar las compras desde 
 * @author jose
 *
 */
public class RecepcionComprasCommand {
	
	private Long id;

	private String tipoGasto;

	private String correoFrom;

	private String emisorFactura;

	private String emisorTipoIdentificacion;

	private String emisorIdentificacion;

	private String fechaEmision;

	private String moneda;

	private String tipoCambio;

	private String totalImpuestos;

	private String totalComprobante;

	private String receptorTipoIdentificacion;

	private String receptorIdentificacion;

	private String clave;
	private String consecutivo;

	private String facturaXml;

	private String facturaPdf;

	private Date fechaCreacion;

	
	
	
	
	public RecepcionComprasCommand() {
		super();
	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getTipoGasto() {
		return tipoGasto;
	}

	
	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	
	public String getCorreoFrom() {
		return correoFrom;
	}

	
	public void setCorreoFrom(String correoFrom) {
		this.correoFrom = correoFrom;
	}

	
	public String getEmisorFactura() {
		return emisorFactura;
	}

	
	public void setEmisorFactura(String emisorFactura) {
		this.emisorFactura = emisorFactura;
	}

	
	public String getEmisorTipoIdentificacion() {
		return emisorTipoIdentificacion;
	}

	
	public void setEmisorTipoIdentificacion(String emisorTipoIdentificacion) {
		this.emisorTipoIdentificacion = emisorTipoIdentificacion;
	}

	
	public String getEmisorIdentificacion() {
		return emisorIdentificacion;
	}

	
	public void setEmisorIdentificacion(String emisorIdentificacion) {
		this.emisorIdentificacion = emisorIdentificacion;
	}

	
	public String getFechaEmision() {
		return fechaEmision;
	}

	
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	
	public String getMoneda() {
		return moneda;
	}

	
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	
	public String getTipoCambio() {
		return tipoCambio;
	}

	
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	
	public String getTotalImpuestos() {
		return totalImpuestos;
	}

	
	public void setTotalImpuestos(String totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	
	public String getTotalComprobante() {
		return totalComprobante;
	}

	
	public void setTotalComprobante(String totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	
	public String getReceptorTipoIdentificacion() {
		return receptorTipoIdentificacion;
	}

	
	public void setReceptorTipoIdentificacion(String receptorTipoIdentificacion) {
		this.receptorTipoIdentificacion = receptorTipoIdentificacion;
	}

	
	public String getReceptorIdentificacion() {
		return receptorIdentificacion;
	}

	
	public void setReceptorIdentificacion(String receptorIdentificacion) {
		this.receptorIdentificacion = receptorIdentificacion;
	}

	
	public String getClave() {
		return clave;
	}

	
	public void setClave(String clave) {
		this.clave = clave;
	}

	
	public String getFacturaXml() {
		return facturaXml;
	}

	
	public void setFacturaXml(String facturaXml) {
		this.facturaXml = facturaXml;
	}

	
	public String getFacturaPdf() {
		return facturaPdf;
	}

	
	public void setFacturaPdf(String facturaPdf) {
		this.facturaPdf = facturaPdf;
	}

	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	
	public String getConsecutivo() {
		return consecutivo;
	}


	
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	
	
}

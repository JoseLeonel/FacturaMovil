package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;

public class FEMensajeReceptorAutomaticoCommand {

	private Long		id;

	private String	tipoGasto;

	private String	correoFrom;

	private String	emisorFactura;

	private String	emisorTipoIdentificacion;

	private String	emisorIdentificacion;

	private String	fechaEmision;

	private String	moneda;

	private String	tipoCambio;

	private String	totalImpuestos;

	private String	totalComprobante;

	private String	receptorTipoIdentificacion;

	private String	receptorIdentificacion;

	private String	clave;

	private String	consecutivo;

	private String	tipoDoc;

	private String	condicionVenta;

	private String	facturaXml;

	private String	facturaPdf;

	private Date		fechaCreacion;

	private String	estado;

	private String	correoCompras;

	private String	facturaXML;

	public FEMensajeReceptorAutomaticoCommand(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico) {
		this.id = fEMensajeReceptorAutomatico.getId();
		this.tipoGasto = fEMensajeReceptorAutomatico.getTipoGasto();
		this.correoFrom = fEMensajeReceptorAutomatico.getCorreoFrom();
		this.emisorFactura = fEMensajeReceptorAutomatico.getEmisorFactura();
		this.emisorTipoIdentificacion = fEMensajeReceptorAutomatico.getEmisorIdentificacion();
		this.emisorIdentificacion = fEMensajeReceptorAutomatico.getEmisorIdentificacion();
		this.fechaEmision = fEMensajeReceptorAutomatico.getFechaEmision();
		this.moneda = fEMensajeReceptorAutomatico.getMoneda();
		this.tipoCambio = fEMensajeReceptorAutomatico.getTipoCambio();
		this.totalImpuestos = fEMensajeReceptorAutomatico.getTotalImpuestos();
		this.totalComprobante = fEMensajeReceptorAutomatico.getTotalComprobante();
		this.receptorTipoIdentificacion = fEMensajeReceptorAutomatico.getReceptorIdentificacion();
		this.receptorIdentificacion = fEMensajeReceptorAutomatico.getReceptorIdentificacion();
		this.clave = fEMensajeReceptorAutomatico.getClave();
		this.consecutivo = fEMensajeReceptorAutomatico.getConsecutivo();
		this.tipoDoc = fEMensajeReceptorAutomatico.getTipoDoc();
		this.condicionVenta = fEMensajeReceptorAutomatico.getCondicionVenta();
		this.facturaXml = fEMensajeReceptorAutomatico.getFacturaXml();
		this.facturaPdf = fEMensajeReceptorAutomatico.getFacturaPdf();
		this.fechaCreacion = fEMensajeReceptorAutomatico.getFechaCreacion();
		this.estado = fEMensajeReceptorAutomatico.getEstado();
		this.correoCompras = fEMensajeReceptorAutomatico.getCorreoCompras();
		this.facturaXML = fEMensajeReceptorAutomatico.getFacturaXml();
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

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getCondicionVenta() {
		return condicionVenta;
	}

	public void setCondicionVenta(String condicionVenta) {
		this.condicionVenta = condicionVenta;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCorreoCompras() {
		return correoCompras;
	}

	public void setCorreoCompras(String correoCompras) {
		this.correoCompras = correoCompras;
	}

	public String getFacturaXML() {
		return facturaXML;
	}

	public void setFacturaXML(String facturaXML) {
		this.facturaXML = facturaXML;
	}

}
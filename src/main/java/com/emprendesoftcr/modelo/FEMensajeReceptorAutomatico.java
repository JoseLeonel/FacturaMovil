package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "fe_mensaje_receptor_automatico")
public class FEMensajeReceptorAutomatico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4620130448381526880L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_gasto", length = 1)
	private String tipoGasto;

	@Column(name = "correo_from", length = 255)
	private String correoFrom;

	@Column(name = "emisor_factura", length = 160)
	private String emisorFactura;

	@Column(name = "emisor_tipo_identificacion", length = 2)
	private String emisorTipoIdentificacion;

	@Column(name = "emisor_identificacion", length = 20)
	private String emisorIdentificacion;

	@Column(name = "fecha_emision", length = 45)
	private String fechaEmision;

	@Column(length = 5)
	private String moneda;

	@Column(name = "tipo_cambio", length = 10)
	private String tipoCambio;

	@Column(name = "total_impuestos", length = 20)
	private String totalImpuestos;

	@Column(name = "total_comprobante", length = 20)
	private String totalComprobante;

	@Column(name = "receptor_tipo_identificacion", length = 2)
	private String receptorTipoIdentificacion;

	@Column(name = "receptor_identificacion", length = 25)
	private String receptorIdentificacion;

	@Column(length = 50)
	private String clave;
	
	@Column(name = "consecutivo",length = 25)
	private String consecutivo;

	@Column(name = "tipo_doc")
	private String						tipoDoc;
	
	
	@Column(name = "condicion_venta")
	private String						condicionVenta;



	@Column(name = "factura_xml", length = 250)
	private String facturaXml;

	@Column(name = "factura_pdf", length = 250)
	private String facturaPdf;


	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	private Date fechaCreacion;

	@Column(length = 1)
	private String estado;
	
	

	public FEMensajeReceptorAutomatico() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
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
	
	
//	
//	public String getFechaCreditoSTR() {
//		if (this.fechaEmision != null) {
//			return Utils.getFechaGeneraReporte(this.getFechaCredito());
//		}
//		return Constantes.EMPTY;
//	}

}
package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.RecepcionFactura;

public class RecepcionFacturaCommandRespaldo {

	private Long		id;
	private String	clave;
	private String	cedulaEmisor;
	private String	tipoCedulaEmisor;
	private Date		fechaEmision;
	// 1 aceptado, 2 aceptado parcialmente, 3 rechazado.
	private String	mensaje;
	private String	detalleMensaje;
	private Double	totalImpuestos;
	private Double	totalFactura;
	private String	cedulaReceptor;
	private String	numeroConsecutivoReceptor;
	private Integer	estadoFirma;
	private String	created_at;
	private String facturaConsecutivo;

	public RecepcionFacturaCommandRespaldo() {
		super();
	}

	public RecepcionFacturaCommandRespaldo(RecepcionFactura recepcionFactura) {
		super();
		this.id = recepcionFactura.getId();
		this.clave = recepcionFactura.getFacturaClave();
		this.cedulaEmisor = recepcionFactura.getEmisorCedula();
		this.tipoCedulaEmisor = recepcionFactura.getEmisorTipoCedula();
		this.fechaEmision = recepcionFactura.getFacturaFechaEmision();
		this.mensaje = MapEnums.ENUM_TIPOS_MENSAJES.get(recepcionFactura.getMensaje());
		this.detalleMensaje = recepcionFactura.getDetalleMensaje();
		this.totalImpuestos = recepcionFactura.getFacturaTotalImpuestos() == null ? 0 : recepcionFactura.getFacturaTotalImpuestos();
		this.totalFactura = recepcionFactura.getFacturaTotalComprobante() == null ? 0 : recepcionFactura.getFacturaTotalComprobante();
		this.cedulaReceptor = recepcionFactura.getReceptorCedula();
		this.numeroConsecutivoReceptor = recepcionFactura.getNumeroConsecutivoReceptor();
		this.estadoFirma = recepcionFactura.getEstadoFirma();
		this.created_at = recepcionFactura.getCreated_atSTR();
		this.facturaConsecutivo = recepcionFactura.getFacturaConsecutivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getCreated_at() {
		return created_at;
	}

	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCedulaEmisor() {
		return cedulaEmisor;
	}

	public void setCedulaEmisor(String cedulaEmisor) {
		this.cedulaEmisor = cedulaEmisor;
	}

	public String getTipoCedulaEmisor() {
		return tipoCedulaEmisor;
	}

	public void setTipoCedulaEmisor(String tipoCedulaEmisor) {
		this.tipoCedulaEmisor = tipoCedulaEmisor;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDetalleMensaje() {
		return detalleMensaje;
	}

	public void setDetalleMensaje(String detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}

	public Double getTotalImpuestos() {
		return totalImpuestos;
	}

	public void setTotalImpuestos(Double totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public Double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(Double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public String getCedulaReceptor() {
		return cedulaReceptor;
	}

	public void setCedulaReceptor(String cedulaReceptor) {
		this.cedulaReceptor = cedulaReceptor;
	}

	public String getNumeroConsecutivoReceptor() {
		return numeroConsecutivoReceptor;
	}

	public void setNumeroConsecutivoReceptor(String numeroConsecutivoReceptor) {
		this.numeroConsecutivoReceptor = numeroConsecutivoReceptor;
	}

	public Integer getEstadoFirma() {
		return estadoFirma;
	}

	public void setEstadoFirma(Integer estadoFirma) {
		this.estadoFirma = estadoFirma;
	}

	
	public String getFacturaConsecutivo() {
		return facturaConsecutivo;
	}

	
	public void setFacturaConsecutivo(String facturaConsecutivo) {
		this.facturaConsecutivo = facturaConsecutivo;
	}

}

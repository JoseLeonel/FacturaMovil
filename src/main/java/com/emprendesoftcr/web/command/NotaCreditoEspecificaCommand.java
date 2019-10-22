package com.emprendesoftcr.web.command;

public class NotaCreditoEspecificaCommand {

	private String	consecutivo;

	private String	referenciaRazon;

	private String	detalleFactura;
	
	private Integer completa;

	public NotaCreditoEspecificaCommand() {
		super();
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getDetalleFactura() {
		return detalleFactura;
	}

	public void setDetalleFactura(String detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public String getReferenciaRazon() {
		return referenciaRazon;
	}

	public void setReferenciaRazon(String referenciaRazon) {
		this.referenciaRazon = referenciaRazon;
	}

	
	public Integer getCompleta() {
		return completa;
	}

	
	public void setCompleta(Integer completa) {
		this.completa = completa;
	}
	
	
}

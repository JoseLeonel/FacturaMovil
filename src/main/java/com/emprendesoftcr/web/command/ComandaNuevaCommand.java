package com.emprendesoftcr.web.command;


public class ComandaNuevaCommand {
	
	private String impresora;
	private String detalles;
	private Long idFactura;
	private Long idMesa;
	private String descMesa;

	
	public ComandaNuevaCommand() {
		super();
		
	}


	
	public String getImpresora() {
		return impresora;
	}


	
	public void setImpresora(String impresora) {
		this.impresora = impresora;
	}


	
	public String getDetalles() {
		return detalles;
	}


	
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}


	
	public Long getIdFactura() {
		return idFactura;
	}


	
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}


	
	public Long getIdMesa() {
		return idMesa;
	}


	
	public void setIdMesa(Long idMesa) {
		this.idMesa = idMesa;
	}


	
	public String getDescMesa() {
		return descMesa;
	}


	
	public void setDescMesa(String descMesa) {
		this.descMesa = descMesa;
	}

	

	
	
	
	
	
}

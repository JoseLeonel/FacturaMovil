package com.emprendesoftcr.web.command;

public class SalidaEntradaDineroCommand {

	private String	descripcion;

	private Double	total;

	private Double	tipo;



	public SalidaEntradaDineroCommand() {
		super();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTipo() {
		return tipo;
	}

	public void setTipo(Double tipo) {
		this.tipo = tipo;
	}

	

}

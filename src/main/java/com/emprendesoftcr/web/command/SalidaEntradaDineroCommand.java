package com.emprendesoftcr.web.command;

public class SalidaEntradaDineroCommand {

	private String	descripcion;

	private Double	total;

	private Integer	tipo;



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

	
	public Integer getTipo() {
		return tipo;
	}

	
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	

	

}

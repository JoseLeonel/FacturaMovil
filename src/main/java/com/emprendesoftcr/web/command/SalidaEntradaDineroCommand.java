package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.SalidaEntradaDinero;

public class SalidaEntradaDineroCommand {
  private Long id;
	private String	descripcion;

	private Double	total;

	private Integer	tipo;
	
	private String	totalSTR;

	public SalidaEntradaDineroCommand(SalidaEntradaDinero salidaEntradaDinero) {
		super();
		this.setDescripcion(salidaEntradaDinero.getDescripcion());
		this.setTipo(salidaEntradaDinero.getTipo());
		this.setTotal(salidaEntradaDinero.getTotal());
		this.setId(salidaEntradaDinero.getId());
		this.setTotalSTR(salidaEntradaDinero.getTotalSTR());
	}

	public SalidaEntradaDineroCommand() {
		super();
	}
	

	
	public String getTotalSTR() {
		return totalSTR;
	}

	
	public void setTotalSTR(String totalSTR) {
		this.totalSTR = totalSTR;
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

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	

	

}

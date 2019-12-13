package com.emprendesoftcr.web.command;


public class FacturaIdNativeCommand {
	
	private Long id;
	private Integer estado;
	
	
	
	public FacturaIdNativeCommand() {
		super();
	}

	public FacturaIdNativeCommand(Long id, Integer estado) {
		super();
		this.id = id;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getEstado() {
		return estado;
	}
	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	

}

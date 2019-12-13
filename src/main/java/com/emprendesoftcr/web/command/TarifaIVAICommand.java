package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.TarifaIVAI;

public class TarifaIVAICommand {

	private Integer	id;

	private String	codigoTarifa;

	private String	descripcion;

	private Double	monto;

		
	public TarifaIVAICommand(TarifaIVAI tarifaIVAI) {
		super();
		this.id = tarifaIVAI.getId();
		this.codigoTarifa = tarifaIVAI.getCodigoTarifa();
		this.descripcion = tarifaIVAI.getDescripcion();
		this.monto = tarifaIVAI.getMonto();
	}


	public TarifaIVAICommand(Integer id, String codigoTarifa, String descripcion, Double monto) {
		super();
		this.id = id;
		this.codigoTarifa = codigoTarifa;
		this.descripcion = descripcion;
		this.monto = monto;
	}


	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	
	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Double getMonto() {
		return monto;
	}

	
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	

}

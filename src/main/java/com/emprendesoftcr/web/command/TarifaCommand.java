package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Tarifa;

public class TarifaCommand {

	private Long							id;

	private String						tipoImpuesto;

	private String						codigoTarifa;

	private String						descripcion;

	private Double						monto;

	public TarifaCommand(Tarifa tarifa) {
		super();
		this.id= tarifa.getId();
		this.tipoImpuesto = tarifa.getTipoImpuesto();
		this.codigoTarifa = tarifa.getCodigoTarifa();
		this.descripcion= tarifa.getDescripcion();
	  this.monto = tarifa.getMonto();

	}

	
	public TarifaCommand() {
		super();
	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	
	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
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

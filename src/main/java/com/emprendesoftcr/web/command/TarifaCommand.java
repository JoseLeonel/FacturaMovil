package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Tarifa;
import com.emprendesoftcr.modelo.TarifaIVAI;

public class TarifaCommand {

	private Long				id;

	private String			tipoImpuesto;

	
	private Double			monto;

	private TarifaIVAI	tarifaIVAI;

	public TarifaCommand(Tarifa tarifa) {
		super();
		this.id = tarifa.getId();
		this.tipoImpuesto = tarifa.getTipoImpuesto();
		this.tarifaIVAI = tarifa.getTarifaIVAI();
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

	public TarifaIVAI getTarifaIVAI() {
		return tarifaIVAI;
	}

	public void setTarifaIVAI(TarifaIVAI tarifaIVAI) {
		this.tarifaIVAI = tarifaIVAI;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	

	

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

}

package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

public class TotalComprasAceptadasCommand {

	private Double	total;
	private Double	totalImpuesto;

	public TotalComprasAceptadasCommand(Double total, Double totalImpuesto) {
		super();
		this.total = total;
		this.totalImpuesto = totalImpuesto;
	}

	public TotalComprasAceptadasCommand() {
		super();
	}

	public Double getTotal() {
		return total;
	}
	public String getTotalSTR() {
		return Utils.formateadorMiles(total !=null?total:Constantes.ZEROS_DOUBLE);
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}
	public String getTotalImpuestoSTR() {
		return Utils.formateadorMiles(totalImpuesto !=null?totalImpuesto:Constantes.ZEROS_DOUBLE);
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

}

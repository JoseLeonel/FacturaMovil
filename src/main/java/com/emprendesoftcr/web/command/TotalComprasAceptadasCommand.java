package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

public class TotalComprasAceptadasCommand {

	private Double	total;
	private Double	totalNotaCredito;
	private Double	totalNotaDebito;
	private Double	totalImpuesto;
	private Double	totalImpuestoNotaCredito;
	private Double	totalImpuestoNotaDebito;

	

	public TotalComprasAceptadasCommand(Double total, Double totalNotaCredito, Double totalNotaDebito, Double totalImpuesto, Double totalImpuestoNotaCredito, Double totalImpuestoNotaDebito) {
		super();
		this.total = total;
		this.totalNotaCredito = totalNotaCredito;
		this.totalNotaDebito = totalNotaDebito;
		this.totalImpuesto = totalImpuesto;
		this.totalImpuestoNotaCredito = totalImpuestoNotaCredito;
		this.totalImpuestoNotaDebito = totalImpuestoNotaDebito;
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

	public String gettotalNotaCreditoSTR() {
		return Utils.formateadorMiles(totalNotaCredito !=null?totalNotaCredito:Constantes.ZEROS_DOUBLE);
	}
	public String getTotalNotaDebitoSTR() {
		return Utils.formateadorMiles(totalNotaDebito !=null?totalNotaDebito:Constantes.ZEROS_DOUBLE);
	}
	public String getTotalImpuestoNotaCreditoSTR() {
		return Utils.formateadorMiles(totalImpuestoNotaCredito !=null?totalImpuestoNotaCredito:Constantes.ZEROS_DOUBLE);
	}
	public String gettotalImpuestoNotaDebitoSTR() {
		return Utils.formateadorMiles(totalImpuestoNotaDebito !=null?totalImpuestoNotaDebito:Constantes.ZEROS_DOUBLE);
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

	
	public Double getTotalNotaCredito() {
		return totalNotaCredito;
	}

	
	public void setTotalNotaCredito(Double totalNotaCredito) {
		this.totalNotaCredito = totalNotaCredito;
	}

	
	public Double getTotalNotaDebito() {
		return totalNotaDebito;
	}

	
	public void setTotalNotaDebito(Double totalNotaDebito) {
		this.totalNotaDebito = totalNotaDebito;
	}

	
	public Double getTotalImpuestoNotaCredito() {
		return totalImpuestoNotaCredito;
	}

	
	public void setTotalImpuestoNotaCredito(Double totalImpuestoNotaCredito) {
		this.totalImpuestoNotaCredito = totalImpuestoNotaCredito;
	}

	
	public Double getTotalImpuestoNotaDebito() {
		return totalImpuestoNotaDebito;
	}

	
	public void setTotalImpuestoNotaDebito(Double totalImpuestoNotaDebito) {
		this.totalImpuestoNotaDebito = totalImpuestoNotaDebito;
	}
	

}

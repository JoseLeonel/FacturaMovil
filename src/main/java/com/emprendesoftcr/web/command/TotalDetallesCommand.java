package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Utils;

/**
 * TotalDetallesCommand. Para totalizar las ventas por detalle
 * @author jose.
 * @since 31 ene. 2019
 */
public class TotalDetallesCommand {

	private Double totalGravado;
	private Double	totalDescuento;
	private Double	totalImpuesto;
	private Double	totalExento;
	private Double total;
	private Double	totalGanancia;

	public TotalDetallesCommand() {
		super();
	}

	

	



	public TotalDetallesCommand(Double totalGravado, Double totalDescuento, Double totalImpuesto, Double totalExento, Double total, Double totalGanancia) {
		super();
		this.totalGravado = totalGravado;
		this.totalDescuento = totalDescuento;
		this.totalImpuesto = totalImpuesto;
		this.totalExento = totalExento;
		this.total = total;
		this.totalGanancia = totalGanancia;
	}







	public Double getTotal() {
		return total;
	}
	public String getTotalSTR() {
		return Utils.formateadorMiles(this.total);
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}
	
	public String getTotalDescuentoSTR() {
		return Utils.formateadorMiles(this.totalDescuento);
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}
	public String getTotalImpuestoSTR() {
		return Utils.formateadorMiles(this.totalImpuesto);
	}
	
	

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalGanancia() {
		return totalGanancia;
	}
	public String getTotalGananciaSTR() {
		return Utils.formateadorMiles(this.totalGanancia);
	}
	

	public void setTotalGanancia(Double totalGanancia) {
		this.totalGanancia = totalGanancia;
	}

	public Double getTotalExento() {
		return totalExento;
	}
	public String getTotalExentoSTR() {
		return  Utils.formateadorMiles(this.totalExento);
	}

	public void setTotalExento(Double totalExento) {
		this.totalExento = totalExento;
	}



	
	public Double getTotalGravado() {
		return totalGravado;
	}
	public String getTotalGravadoSTR() {
		return Utils.formateadorMiles(this.totalGravado);
	}


	
	public void setTotalGravado(Double totalGravado) {
		this.totalGravado = totalGravado;
	}

}

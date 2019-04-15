package com.emprendesoftcr.web.command;

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

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalGanancia() {
		return totalGanancia;
	}

	public void setTotalGanancia(Double totalGanancia) {
		this.totalGanancia = totalGanancia;
	}

	public Double getTotalExento() {
		return totalExento;
	}

	public void setTotalExento(Double totalExento) {
		this.totalExento = totalExento;
	}



	
	public Double getTotalGravado() {
		return totalGravado;
	}



	
	public void setTotalGravado(Double totalGravado) {
		this.totalGravado = totalGravado;
	}

}

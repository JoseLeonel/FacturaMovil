package com.emprendesoftcr.web.command;

public class TotalFacturaCommand {

	private Double	total;
	private Double	totalDescuentos;
	private Double	totalImpuestos;
	private Double	totalVentasNetas;
	private Double	totalVentasExentas;
	private Double	totalVentasGravadas;

	public TotalFacturaCommand() {
		super();
	}

	public TotalFacturaCommand(Double total, Double totalDescuentos, Double totalImpuestos, Double totalVentasNetas, Double totalVentasExentas, Double totalVentasGravadas) {
		super();
		this.total = total;
		this.totalDescuentos = totalDescuentos;
		this.totalImpuestos = totalImpuestos;
		this.totalVentasNetas = totalVentasNetas;
		this.totalVentasExentas = totalVentasExentas;
		this.totalVentasGravadas = totalVentasGravadas;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalDescuentos() {
		return totalDescuentos;
	}

	public void setTotalDescuentos(Double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public Double getTotalImpuestos() {
		return totalImpuestos;
	}

	public void setTotalImpuestos(Double totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public Double getTotalVentasNetas() {
		return totalVentasNetas;
	}

	public void setTotalVentasNetas(Double totalVentasNetas) {
		this.totalVentasNetas = totalVentasNetas;
	}

	public Double getTotalVentasExentas() {
		return totalVentasExentas;
	}

	public void setTotalVentasExentas(Double totalVentasExentas) {
		this.totalVentasExentas = totalVentasExentas;
	}

	public Double getTotalVentasGravadas() {
		return totalVentasGravadas;
	}

	public void setTotalVentasGravadas(Double totalVentasGravadas) {
		this.totalVentasGravadas = totalVentasGravadas;
	}

}

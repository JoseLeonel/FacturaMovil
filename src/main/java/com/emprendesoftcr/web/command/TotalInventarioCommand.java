package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Utils;

public class TotalInventarioCommand {

	private Double	totalCosto;
	private Double	totalVenta;
	private String	totalCostoSTR;
	private String	totalVentasSTR;

	public TotalInventarioCommand() {
		super();
	}

	public TotalInventarioCommand(Double totalCosto, Double totalVenta) {
		super();
		this.totalCosto = totalCosto;
		this.totalVenta = totalVenta;
		this.totalCostoSTR = Utils.formateadorMiles(totalCosto);
		this.totalVentasSTR = Utils.formateadorMiles(totalVenta);
	}

	
	public Double getTotalCosto() {
		return totalCosto;
	}

	
	public void setTotalCosto(Double totalCosto) {
		this.totalCosto = totalCosto;
	}

	
	public Double getTotalVenta() {
		return totalVenta;
	}

	
	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	
	public String getTotalCostoSTR() {
		return totalCostoSTR;
	}

	
	public void setTotalCostoSTR(String totalCostoSTR) {
		this.totalCostoSTR = totalCostoSTR;
	}

	
	public String getTotalVentasSTR() {
		return totalVentasSTR;
	}

	
	public void setTotalVentasSTR(String totalVentasSTR) {
		this.totalVentasSTR = totalVentasSTR;
	}

	

}

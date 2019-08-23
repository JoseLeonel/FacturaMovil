package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

public class TotalFacturaCommand {

	private Double	total;
	private Double	totalDescuentos;
	private Double	totalImpuestos;
	private Double	totalVentasNetas;
	private Double	totalVentasExentas;
	private Double	totalVentasGravadas;
	private Double	totalOtrosCargos;

	public TotalFacturaCommand() {
		super();
	}

	public TotalFacturaCommand(Double total, Double totalDescuentos, Double totalImpuestos, Double totalVentasNetas, Double totalVentasExentas, Double totalVentasGravadas,Double totalOtrosCargos) {
		super();
		this.total = total;
		this.totalDescuentos = totalDescuentos;
		this.totalImpuestos = totalImpuestos;
		this.totalVentasNetas = totalVentasNetas;
		this.totalVentasExentas = totalVentasExentas;
		this.totalVentasGravadas = totalVentasGravadas;
		this.totalOtrosCargos = totalOtrosCargos;
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

	public Double getTotalDescuentos() {
		return totalDescuentos;
	}
	public String getTotalDescuentosSTR() {
		return Utils.formateadorMiles(totalDescuentos !=null?totalDescuentos:Constantes.ZEROS_DOUBLE);
	}

	public void setTotalDescuentos(Double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	
	public Double getTotalImpuestos() {
		return totalImpuestos;
	}
	public String getTotalImpuestosSTR() {
		return Utils.formateadorMiles(totalImpuestos !=null?totalImpuestos:Constantes.ZEROS_DOUBLE);
	}
	public void setTotalImpuestos(Double totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public Double getTotalVentasNetas() {
		return totalVentasNetas;
	}
	public String getTotalVentasNetasSTR() {
		return Utils.formateadorMiles(totalVentasNetas !=null?totalVentasNetas:Constantes.ZEROS_DOUBLE);
	}

	public void setTotalVentasNetas(Double totalVentasNetas) {
		this.totalVentasNetas = totalVentasNetas;
	}

	public Double getTotalVentasExentas() {
		return totalVentasExentas;
	}
	public String getTotalVentasExentasSTR() {
		return Utils.formateadorMiles(totalVentasExentas !=null?totalVentasExentas:Constantes.ZEROS_DOUBLE);
	}

	public void setTotalVentasExentas(Double totalVentasExentas) {
		this.totalVentasExentas = totalVentasExentas;
	}
	
	public Double getTotalVentasGravadas() {
		return totalVentasGravadas;
	}

	public String getTotalVentasGravadasSTR() {
		return Utils.formateadorMiles(totalVentasGravadas != null ? totalVentasGravadas : Constantes.ZEROS_DOUBLE);
	}

	public void setTotalVentasGravadas(Double totalVentasGravadas) {
		this.totalVentasGravadas = totalVentasGravadas;
	}

	
	public Double getTotalOtrosCargos() {
		return totalOtrosCargos;
	}

	public String getTotalOtrosCargosSTR() {
		return Utils.formateadorMiles(totalOtrosCargos != null ? totalOtrosCargos : Constantes.ZEROS_DOUBLE);
	}

	
	public void setTotalOtrosCargos(Double totalOtrosCargos) {
		this.totalOtrosCargos = totalOtrosCargos;
	}

}

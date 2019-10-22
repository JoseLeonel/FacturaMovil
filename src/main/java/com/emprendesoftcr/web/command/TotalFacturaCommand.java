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
	private Double	totalEfectivo;
	private Double	totalTarjeta;
	private Double	totalBanco;
	private Double	totalPagos;
	private Double	totalCredito;
	
	private Double	total_n;
	private Double	totalDescuentos_n;
	private Double	totalImpuestos_n;
	private Double	totalVentasNetas_n;
	private Double	totalVentasExentas_n;
	private Double	totalVentasGravadas_n;
	private Double	totalOtrosCargos_n;
	private Double	totalEfectivo_n;
	private Double	totalTarjeta_n;
	private Double	totalBanco_n;
	private Double	totalPagos_n;
	private Double	totalCredito_n;

	


	public TotalFacturaCommand() {
		super();
	}

	public TotalFacturaCommand(Double total, Double totalDescuentos, Double totalImpuestos, Double totalVentasNetas, Double totalVentasExentas, Double totalVentasGravadas,Double totalOtrosCargos,Double totalEfectivo,Double totalTarjeta,Double totalBanco,Double totalCredito,
			Double total_n, Double totalDescuentos_n, Double totalImpuestos_n, Double totalVentasNetas_n, Double totalVentasExentas_n, Double totalVentasGravadas_n,Double totalOtrosCargos_n,Double totalEfectivo_n,Double totalTarjeta_n,Double totalBanco_n,Double totalCredito_n) {
		super();
		this.total = total;
		this.totalDescuentos = totalDescuentos;
		this.totalImpuestos = totalImpuestos;
		this.totalVentasNetas = totalVentasNetas;
		this.totalVentasExentas = totalVentasExentas;
		this.totalVentasGravadas = totalVentasGravadas;
		this.totalOtrosCargos = totalOtrosCargos;
		this.totalEfectivo = totalEfectivo;
		this.totalBanco = totalBanco;
		this.totalTarjeta = totalTarjeta;
		this.totalCredito = totalCredito;
		this.totalPagos = this.totalBanco + this.totalEfectivo + this.totalTarjeta + this.totalCredito;
		this.total = total;
		
		//nota de credito
		this.total_n = total_n * -1 ;
		this.totalDescuentos_n = totalDescuentos_n * -1;
		this.totalImpuestos_n = totalImpuestos_n * -1;
		this.totalVentasNetas_n = totalVentasNetas_n * -1;
		this.totalVentasExentas_n = totalVentasExentas_n * -1;
		this.totalVentasGravadas_n = totalVentasGravadas_n * -1;
		this.totalOtrosCargos_n = totalOtrosCargos_n * -1;
		this.totalEfectivo_n = totalEfectivo_n * -1;
		this.totalBanco_n = totalBanco_n * -1;
		this.totalTarjeta_n = totalTarjeta_n * -1;
		this.totalCredito_n = totalCredito_n * -1;
		this.totalPagos_n = (this.totalBanco_n + this.totalEfectivo_n + this.totalTarjeta_n + this.totalCredito_n) * -1;
		
		
	}

	public Double getTotal() {
		return total;
	}
	public String getTotalSTR() {
		return Utils.formateadorMiles(total !=null?total:Constantes.ZEROS_DOUBLE);
	}
	
	public String getTotalGeneralSTR() {
		return Utils.formateadorMiles(total !=null?total + (this.total_n ==null? Constantes.ZEROS_DOUBLE:this.total_n ):Constantes.ZEROS_DOUBLE);
	}

	public String getTotalNC() {
		return Utils.formateadorMiles(total_n !=null?total_n:Constantes.ZEROS_DOUBLE);
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getTotalCredito() {
		return totalCredito;
	}
	public String getTotalCreditoSTR() {
		return Utils.formateadorMiles(totalCredito !=null?totalCredito:Constantes.ZEROS_DOUBLE);
	}
	public String getTotalCreditoNC() {
		return Utils.formateadorMiles(totalCredito_n !=null?totalCredito_n:Constantes.ZEROS_DOUBLE);
	}
	
	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}
	public Double getTotalDescuentos() {
		return totalDescuentos;
	}
	public String getTotalDescuentosSTR() {
		return Utils.formateadorMiles(totalDescuentos !=null?totalDescuentos:Constantes.ZEROS_DOUBLE);
	}
	public String getTotalDescuentosNC() {
		return Utils.formateadorMiles(totalDescuentos_n !=null?totalDescuentos_n:Constantes.ZEROS_DOUBLE);
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
	public String getTotalImpuestosNC() {
		return Utils.formateadorMiles(totalImpuestos_n !=null?totalImpuestos_n:Constantes.ZEROS_DOUBLE);
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
	public String getTotalVentasNetasNC() {
		return Utils.formateadorMiles(totalVentasNetas_n !=null?totalVentasNetas_n:Constantes.ZEROS_DOUBLE);
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
	public String getTotalVentasExentasNC() {
		return Utils.formateadorMiles(totalVentasExentas_n !=null?totalVentasExentas_n:Constantes.ZEROS_DOUBLE);
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
	public String getTotalVentasGravadasNC() {
		return Utils.formateadorMiles(totalVentasGravadas_n != null ? totalVentasGravadas_n : Constantes.ZEROS_DOUBLE);
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
	public String getTotalOtrosCargosNC() {
		return Utils.formateadorMiles(totalOtrosCargos_n != null ? totalOtrosCargos_n : Constantes.ZEROS_DOUBLE);
	}
	
	public void setTotalOtrosCargos(Double totalOtrosCargos) {
		this.totalOtrosCargos = totalOtrosCargos;
	}

	
	public Double getTotalEfectivo() {
		return totalEfectivo;
	}
	public String getTotalEfectivoSTR() {
		return Utils.formateadorMiles(totalEfectivo != null ? totalEfectivo : Constantes.ZEROS_DOUBLE);
	}
	public String getTotalEfectivoNC() {
		return Utils.formateadorMiles(totalEfectivo_n != null ? totalEfectivo_n : Constantes.ZEROS_DOUBLE);
	}
	public void setTotalEfectivo(Double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	
	public Double getTotalTarjeta() {
		return totalTarjeta;
	}

	public String getTotalTarjetaSTR() {
		return Utils.formateadorMiles(totalTarjeta != null ? totalTarjeta : Constantes.ZEROS_DOUBLE);
	}
	public String getTotalTarjetaNC() {
		return Utils.formateadorMiles(totalTarjeta_n != null ? totalTarjeta_n : Constantes.ZEROS_DOUBLE);
	}
	
	public void setTotalTarjeta(Double totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	
	public String getTotalBancoSTR() {
		return Utils.formateadorMiles(totalBanco != null ? totalBanco : Constantes.ZEROS_DOUBLE);
	}
	

	public String getTotalBancoNC() {
		return Utils.formateadorMiles(totalBanco_n != null ? totalBanco_n : Constantes.ZEROS_DOUBLE);
	}
	public Double getTotalBanco() {
		return totalBanco;
	}
	
	public void setTotalBanco(Double totalBanco) {
		this.totalBanco = totalBanco;
	}

	
	public Double getTotalPagos() {
		return totalPagos;
	}
	
	public String getTotalPagosSTR() {
		return Utils.formateadorMiles(totalPagos != null ? totalPagos : Constantes.ZEROS_DOUBLE);
	}

	public String getTotalPagosNC() {
		return Utils.formateadorMiles(totalPagos_n != null ? totalPagos_n : Constantes.ZEROS_DOUBLE);
	}
	
	public void setTotalPagos(Double totalPagos) {
		this.totalPagos = totalPagos;
	}

	
	public Double getTotal_n() {
		return total_n;
	}

	
	public void setTotal_n(Double total_n) {
		this.total_n = total_n;
	}

	
	public Double getTotalDescuentos_n() {
		return totalDescuentos_n;
	}

	
	public void setTotalDescuentos_n(Double totalDescuentos_n) {
		this.totalDescuentos_n = totalDescuentos_n;
	}

	
	public Double getTotalImpuestos_n() {
		return totalImpuestos_n;
	}

	
	public void setTotalImpuestos_n(Double totalImpuestos_n) {
		this.totalImpuestos_n = totalImpuestos_n;
	}

	
	public Double getTotalVentasNetas_n() {
		return totalVentasNetas_n;
	}

	
	public void setTotalVentasNetas_n(Double totalVentasNetas_n) {
		this.totalVentasNetas_n = totalVentasNetas_n;
	}

	
	public Double getTotalVentasExentas_n() {
		return totalVentasExentas_n;
	}

	
	public void setTotalVentasExentas_n(Double totalVentasExentas_n) {
		this.totalVentasExentas_n = totalVentasExentas_n;
	}

	
	public Double getTotalVentasGravadas_n() {
		return totalVentasGravadas_n;
	}

	
	public void setTotalVentasGravadas_n(Double totalVentasGravadas_n) {
		this.totalVentasGravadas_n = totalVentasGravadas_n;
	}

	
	public Double getTotalOtrosCargos_n() {
		return totalOtrosCargos_n;
	}

	
	public void setTotalOtrosCargos_n(Double totalOtrosCargos_n) {
		this.totalOtrosCargos_n = totalOtrosCargos_n;
	}

	
	public Double getTotalEfectivo_n() {
		return totalEfectivo_n;
	}

	
	public void setTotalEfectivo_n(Double totalEfectivo_n) {
		this.totalEfectivo_n = totalEfectivo_n;
	}

	
	public Double getTotalTarjeta_n() {
		return totalTarjeta_n;
	}

	
	public void setTotalTarjeta_n(Double totalTarjeta_n) {
		this.totalTarjeta_n = totalTarjeta_n;
	}

	
	public Double getTotalBanco_n() {
		return totalBanco_n;
	}

	
	public void setTotalBanco_n(Double totalBanco_n) {
		this.totalBanco_n = totalBanco_n;
	}

	
	public Double getTotalPagos_n() {
		return totalPagos_n;
	}

	
	public void setTotalPagos_n(Double totalPagos_n) {
		this.totalPagos_n = totalPagos_n;
	}

	
	public Double getTotalCredito_n() {
		return totalCredito_n;
	}

	
	public void setTotalCredito_n(Double totalCredito_n) {
		this.totalCredito_n = totalCredito_n;
	}
	

}

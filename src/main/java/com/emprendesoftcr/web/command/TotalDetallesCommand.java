package com.emprendesoftcr.web.command;

import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

/**
 * TotalDetallesCommand. Para totalizar las ventas por detalle
 * @author jose.
 * @since 31 ene. 2019
 */
public class TotalDetallesCommand {

	private Double	totalGravado;
	private Double	totalDescuento;
	private Double	totalImpuesto;
	private Double	totalExento;
	private Double	total;
	private Double	totalGanancia;

	private Double	totalGravado_n;
	private Double	totalDescuento_n;
	private Double	totalImpuesto_n;
	private Double	totalExento_n;
	private Double	total_n;
	private Double	totalGanancia_n;

	public TotalDetallesCommand() {
		super();
	}

	public TotalDetallesCommand(Double totalGravado, Double totalDescuento, Double totalImpuesto, Double totalExento, Double total, Double totalGanancia, Double totalGravado_n, Double totalDescuento_n, Double totalImpuesto_n, Double totalExento_n, Double total_n, Double totalGanancia_n) {
		super();
		this.totalGravado = totalGravado == null ? Constantes.ZEROS_DOUBLE : totalGravado;
		this.totalDescuento = totalDescuento == null ? Constantes.ZEROS_DOUBLE : totalDescuento;
		this.totalImpuesto = totalImpuesto == null ? Constantes.ZEROS_DOUBLE : totalImpuesto;
		this.totalExento = totalExento == null ? Constantes.ZEROS_DOUBLE : totalExento;
		this.total = total == null ? Constantes.ZEROS_DOUBLE : total;
		this.totalGanancia = totalGanancia == null ? Constantes.ZEROS_DOUBLE : totalGanancia;

		this.totalGravado_n = totalGravado_n == null ? Constantes.ZEROS_DOUBLE : totalGravado_n * -1;
		this.totalDescuento_n = totalDescuento_n == null ? Constantes.ZEROS_DOUBLE : totalDescuento_n * -1;
		this.totalImpuesto_n = totalImpuesto_n == null ? Constantes.ZEROS_DOUBLE : totalImpuesto_n * -1;
		this.totalExento_n = totalExento_n == null ? Constantes.ZEROS_DOUBLE : totalExento_n * -1;
		this.total_n = total_n == null ? Constantes.ZEROS_DOUBLE : total_n * -1;
		this.totalGanancia_n = totalGanancia_n == null ? Constantes.ZEROS_DOUBLE : totalGanancia_n * -1;

	}

	public Double getTotal() {
		return total;
	}

	public String getTotalSTR() {
		return Utils.formateadorMiles(this.total);
	}

	public String getTotalMenosIVA() {
		return Utils.formateadorMiles(this.total - this.totalImpuesto);
	}

	public String getTotalG() {
		Double resultado = this.total_n == null ? Constantes.ZEROS_DOUBLE : this.total_n;
		return Utils.formateadorMiles(this.total + resultado);
	}

	public String getTotalMenosIVAG() {
		Double resultadoImpuesto = this.totalImpuesto + this.totalImpuesto_n;
		Double resultadoTotal = this.total + this.total_n;
		return Utils.formateadorMiles(resultadoTotal - resultadoImpuesto);
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public String getTotalDescuentoG() {
		Double resultado = this.totalDescuento_n == null ? Constantes.ZEROS_DOUBLE : this.totalDescuento_n;
		return Utils.formateadorMiles(this.totalDescuento + resultado);
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

	public String getTotalImpuestoG() {
		Double resultado = this.totalImpuesto_n == null ? Constantes.ZEROS_DOUBLE : this.totalImpuesto_n;
		return Utils.formateadorMiles(this.totalImpuesto + resultado);
	}

	public String getTotalImpuestoSTR() {
		return Utils.formateadorMiles(this.totalImpuesto);
	}

	public String getTotalImpuestoNC() {
		return Utils.formateadorMiles(this.totalImpuesto_n);
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
		return Utils.formateadorMiles(this.totalExento);
	}

	public String getTotalExentoG() {
		Double resultado = this.totalExento_n == null ? Constantes.ZEROS_DOUBLE : this.totalExento_n;
		return Utils.formateadorMiles(this.totalExento + resultado);
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

	public String getTotalGravadoG() {
		Double resultado = this.totalGravado_n == null ? Constantes.ZEROS_DOUBLE : this.totalGravado_n;
		return Utils.formateadorMiles(this.totalGravado + resultado);
	}

	public void setTotalGravado(Double totalGravado) {
		this.totalGravado = totalGravado;
	}

	public Double getTotalGravado_n() {
		return totalGravado_n;
	}

	public String getTotalGravadoNC() {

		return Utils.formateadorMiles(this.totalGravado_n);
	}

	public void setTotalGravado_n(Double totalGravado_n) {
		this.totalGravado_n = totalGravado_n;
	}

	public Double getTotalDescuento_n() {
		return totalDescuento_n;
	}

	public String getTotalDescuentoNC() {
		return Utils.formateadorMiles(this.totalDescuento_n);
	}

	public void setTotalDescuento_n(Double totalDescuento_n) {
		this.totalDescuento_n = totalDescuento_n;
	}

	public Double getTotalImpuesto_n() {
		return totalImpuesto_n;
	}

	public void setTotalImpuesto_n(Double totalImpuesto_n) {
		this.totalImpuesto_n = totalImpuesto_n;
	}

	public Double getTotalExento_n() {
		return totalExento_n;
	}

	public String getTotalExentoNC() {
		return Utils.formateadorMiles(this.totalExento_n);
	}

	public void setTotalExento_n(Double totalExento_n) {
		this.totalExento_n = totalExento_n;
	}

	public Double getTotal_n() {
		return total_n;
	}

	public String getTotalNC() {
		return Utils.formateadorMiles(this.total_n);
	}

	public String getTotalMenosIVANC() {
		Double resultado1 = this.total_n * -1;
		Double resultado2 = this.totalImpuesto_n * -1;
		return Utils.formateadorMiles(resultado1 - resultado2);
	}

	public void setTotal_n(Double total_n) {
		this.total_n = total_n;
	}

	public Double getTotalGanancia_n() {
		return totalGanancia_n;
	}

	public String getTotalGananciaNC() {
		return Utils.formateadorMiles(this.totalGanancia_n);
	}

	public void setTotalGanancia_n(Double totalGanancia_n) {
		this.totalGanancia_n = totalGanancia_n;
	}

}

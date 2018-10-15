package com.emprendesoftcr.web.command;

public class TotalCuentaPorCobrarCommand {

	private Double	total;
	private Double	saldo;
	private Double	abono;

	public TotalCuentaPorCobrarCommand() {
		super();
	}

	public TotalCuentaPorCobrarCommand(Double total, Double saldo, Double abono) {
		super();
		this.total = total;
		this.saldo = saldo;
		this.abono = abono;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getAbono() {
		return abono;
	}

	public void setAbono(Double abono) {
		this.abono = abono;
	}

}

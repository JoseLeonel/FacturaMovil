package com.factura.FacturaElectronica.web.command;

import java.math.BigDecimal;
import java.util.Date;

import com.factura.FacturaElectronica.modelo.Abono;
import com.factura.FacturaElectronica.modelo.CuentaCobrar;

/**
 * ClienteCommand.
 * @author jose.
 * @since 17 mar. 2018
 */
public class AbonoCommand {

	private Integer				id;

	private String				nota;

	private String				recibo;

	private String				transferencia;

	private Date					fechaPago;

	private BigDecimal		totalEfectivo;

	private BigDecimal		totalTarjeta;
	private BigDecimal		totalBanco;

	private BigDecimal		total;

	private String				estado;

	private Date					created_at;

	private Date					updated_at;

	private CuentaCobrar	cuentaCobrar;

	public AbonoCommand(Abono abono) {
		super();
		this.id = abono.getId();

		this.nota = abono.getNota();
		this.recibo = abono.getRecibo();
		this.transferencia = abono.getTransferencia();
		this.fechaPago = abono.getFechaPago();
		this.totalEfectivo = abono.getTotalEfectivo();
		this.totalTarjeta = abono.getTotalTarjeta();
		this.totalBanco = abono.getTotalBanco();
		this.total = abono.getTotal();
		this.estado = abono.getEstado();
		this.created_at = abono.getCreated_at();
		this.updated_at = abono.getUpdated_at();
		this.cuentaCobrar = abono.getCuentaCobrar();
	}

	public AbonoCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getRecibo() {
		return recibo;
	}

	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}

	public String getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(String transferencia) {
		this.transferencia = transferencia;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public BigDecimal getTotalEfectivo() {
		return totalEfectivo;
	}

	public void setTotalEfectivo(BigDecimal totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	public BigDecimal getTotalTarjeta() {
		return totalTarjeta;
	}

	public void setTotalTarjeta(BigDecimal totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	public BigDecimal getTotalBanco() {
		return totalBanco;
	}

	public void setTotalBanco(BigDecimal totalBanco) {
		this.totalBanco = totalBanco;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public CuentaCobrar getCuentaCobrar() {
		return cuentaCobrar;
	}

	public void setCuentaCobrar(CuentaCobrar cuentaCobrar) {
		this.cuentaCobrar = cuentaCobrar;
	}

}

package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Abono;
import com.emprendesoftcr.modelo.CuentaCobrar;

/**
 * ClienteCommand.
 * @author jose.
 * @since 17 mar. 2018
 */
public class AbonoCommand {

	private Long					id;

	private String				nota;

	private String				recibo;

	private String				transferencia;

	private String					fechaPago;

	private Double				totalEfectivo;

	private Double				totalTarjeta;
	private Double				totalBanco;

	private Double				total;

	private String				totalEfectivoSTR;

	private String				totalTarjetaSTR;
	private String				totalBancoSTR;

	private String				totalSTR;

	private String				estado;

	private Date					created_at;

	private Date					updated_at;

	private String				created_atSTR;
	private String				updated_atSTR;

	private CuentaCobrar	cuentaCobrar;

	public AbonoCommand() {
		super();
	}

	public AbonoCommand(Abono abono) {
		super();
		this.id = abono.getId();
		this.nota = abono.getNota();
		this.recibo = abono.getRecibo();
		this.transferencia = abono.getTransferencia();
		this.fechaPago = abono.getFechaPago().toString();
		this.totalEfectivo = abono.getTotalEfectivo();
		this.totalTarjeta = abono.getTotalTarjeta();
		this.totalBanco = abono.getTotalBanco();
		this.total = abono.getTotal();
		this.estado = abono.getEstado();
		this.created_at = abono.getCreated_at();
		this.updated_at = abono.getUpdated_at();
		this.cuentaCobrar = abono.getCuentaCobrar();
		this.created_atSTR = Utils.getFechaGeneraReporte(abono.getCreated_at());
		this.updated_atSTR = Utils.getFechaGeneraReporte(abono.getUpdated_at());
		this.totalBancoSTR = abono.getTotalBancoSTR();
		this.totalEfectivoSTR = abono.getTotalEfectivoSTR();
		this.totalTarjetaSTR = abono.getTotalTarjetaSTR();
		this.totalSTR = abono.getTotalSTR();

	}


	public String getCreated_atSTR() {
		return created_atSTR;
	}

	public void setCreated_atSTR(String created_atSTR) {
		this.created_atSTR = created_atSTR;
	}

	public String getUpdated_atSTR() {
		return updated_atSTR;
	}

	public void setUpdated_atSTR(String updated_atSTR) {
		this.updated_atSTR = updated_atSTR;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	
	
	public String getFechaPago() {
		return fechaPago;
	}

	
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Double getTotalEfectivo() {
		return totalEfectivo;
	}

	public void setTotalEfectivo(Double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	public Double getTotalTarjeta() {
		return totalTarjeta;
	}

	public void setTotalTarjeta(Double totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	public Double getTotalBanco() {
		return totalBanco;
	}

	public void setTotalBanco(Double totalBanco) {
		this.totalBanco = totalBanco;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
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

	public String getTotalEfectivoSTR() {
		return totalEfectivoSTR;
	}

	public void setTotalEfectivoSTR(String totalEfectivoSTR) {
		this.totalEfectivoSTR = totalEfectivoSTR;
	}

	public String getTotalTarjetaSTR() {
		return totalTarjetaSTR;
	}

	public void setTotalTarjetaSTR(String totalTarjetaSTR) {
		this.totalTarjetaSTR = totalTarjetaSTR;
	}

	public String getTotalBancoSTR() {
		return totalBancoSTR;
	}

	public void setTotalBancoSTR(String totalBancoSTR) {
		this.totalBancoSTR = totalBancoSTR;
	}

	public String getTotalSTR() {
		return totalSTR;
	}

	public void setTotalSTR(String totalSTR) {
		this.totalSTR = totalSTR;
	}

}

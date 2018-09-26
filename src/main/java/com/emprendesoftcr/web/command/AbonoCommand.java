package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Abono;
import com.emprendesoftcr.modelo.CuentaCobrar;

/**
 * ClienteCommand.
 * @author jose.
 * @since 17 mar. 2018
 */
public class AbonoCommand {

	private Long				id;

	private String				nota;

	private String				recibo;

	private String				transferencia;

	private Date					fechaPago;

	private Double		totalEfectivo;

	private Double		totalTarjeta;
	private Double		totalBanco;

	private Double		total;

	private String				estado;

	private Date					created_at;

	private Date					updated_at;

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
		this.fechaPago = abono.getFechaPago();
    this.totalEfectivo =  abono.getTotalEfectivo();
    this.totalTarjeta = abono.getTotalTarjeta();
		this.totalBanco = abono.getTotalBanco();
		this.total = abono.getTotal();
    this.estado = abono.getEstado();
    this.created_at = abono.getCreated_at();
    this.updated_at = abono.getUpdated_at();
    this.cuentaCobrar =abono.getCuentaCobrar();
		
	}

	
	
	
	
	
	public AbonoCommand(Long id, String nota, String recibo, String transferencia, Date fechaPago, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double total, String estado, Date created_at, Date updated_at, CuentaCobrar cuentaCobrar) {
		super();
		this.id = id;
		this.nota = nota;
		this.recibo = recibo;
		this.transferencia = transferencia;
		this.fechaPago = fechaPago;
		this.totalEfectivo = totalEfectivo;
		this.totalTarjeta = totalTarjeta;
		this.totalBanco = totalBanco;
		this.total = total;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.cuentaCobrar = cuentaCobrar;
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

	
	public Date getFechaPago() {
		return fechaPago;
	}

	
	public void setFechaPago(Date fechaPago) {
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

	

}

package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.AbonoPagar;
import com.emprendesoftcr.modelo.CuentaPagar;

/**
 * ClienteCommand.
 * @author jose.
 * @since 17 mar. 2018
 */
public class AbonoPagarCommand {

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

	private CuentaPagar	cuentaPagar;

	public AbonoPagarCommand() {
		super();
	}

	public AbonoPagarCommand(AbonoPagar abonoPagar) {
		super();
		this.id = abonoPagar.getId();
		this.nota = abonoPagar.getNota();
		this.recibo = abonoPagar.getRecibo();
		this.transferencia = abonoPagar.getTransferencia();
		this.fechaPago = abonoPagar.getFechaPago().toString();
		this.totalEfectivo = abonoPagar.getTotalEfectivo();
		this.totalTarjeta = abonoPagar.getTotalTarjeta();
		this.totalBanco = abonoPagar.getTotalBanco();
		this.total = abonoPagar.getTotal();
		this.estado = abonoPagar.getEstado();
		this.created_at = abonoPagar.getCreated_at();
		this.updated_at = abonoPagar.getUpdated_at();
		this.cuentaPagar = abonoPagar.getCuentaPagar();
		this.created_atSTR = Utils.getFechaGeneraReporte(abonoPagar.getCreated_at());
		this.updated_atSTR = Utils.getFechaGeneraReporte(abonoPagar.getUpdated_at());
		this.totalBancoSTR = abonoPagar.getTotalBancoSTR();
		this.totalEfectivoSTR = abonoPagar.getTotalEfectivoSTR();
		this.totalTarjetaSTR = abonoPagar.getTotalTarjetaSTR();
		this.totalSTR = abonoPagar.getTotalSTR();

	}

	

	
	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public CuentaPagar getCuentaPagar() {
		return cuentaPagar;
	}

	
	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
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

package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;

public class UsuarioCajaCommand {

	private Long	id;
	private Double	totalFondoInicial;

	private Double	totalEfectivo;

	private Double	totalTarjeta;

	private Double	totalBanco;

	private Double	totalCredito;

	private Double	totalNeto;

	private Double	totalAbono;
	
	private String	totalFondoInicialSTR;

	private String	totalEfectivoSTR;

	private String	totalTarjetaSTR;

	private String	totalBancoSTR;

	private String	totalCreditoSTR;

	private String	totalNetoSTR;

	private String	totalAbonoSTR;

	private Usuario	usuario;

	private Caja		caja;

	private Date		created_at;

	private Date		updated_at;
	

	private String		created_atSTR;

	private String		updated_atSTR;

	private String	estado;

	public UsuarioCajaCommand() {
		super();
	}

	public UsuarioCajaCommand(UsuarioCaja usuarioCaja) {
		super();
		this.id = usuarioCaja.getId();
		this.totalFondoInicial = usuarioCaja.getTotalFondoInicial();
		this.totalEfectivo = usuarioCaja.getTotalEfectivo();
		this.totalTarjeta = usuarioCaja.getTotalTarjeta();
		this.totalBanco = usuarioCaja.getTotalBanco();
		this.totalCredito = usuarioCaja.getTotalCredito();
		this.totalNeto = usuarioCaja.getTotalNeto();
		this.usuario = usuarioCaja.getUsuario();
		this.caja = usuarioCaja.getCaja();
		this.created_at = usuarioCaja.getCreated_at();
		this.updated_at = usuarioCaja.getUpdated_at();
		this.estado = usuarioCaja.getEstado();
		this.totalAbono = usuarioCaja.getTotalAbono();
		this.created_atSTR = Utils.getFechaGeneraReporte(usuarioCaja.getCreated_at());
    this.updated_atSTR = Utils.getFechaGeneraReporte(usuarioCaja.getUpdated_at());
    this.totalFondoInicialSTR = usuarioCaja.getTotalFondoInicialSTR();
  	this.totalEfectivoSTR = usuarioCaja.getTotalEfectivoSTR();
  	this.totalTarjetaSTR = usuarioCaja.getTotalTarjetaSTR();
 	  this.totalBancoSTR = usuarioCaja.getTotalBancoSTR();
  	this.totalCreditoSTR = usuarioCaja.getTotalCreditoSTR();
  	this.totalNetoSTR = usuarioCaja.getTotalNetoSTR();
  	this.totalAbonoSTR = usuarioCaja.getTotalAbonoSTR();

	}

	

	
	
	public String getCreated_atSTR() {
		return created_atSTR;
	}

	
	public void setCreated_atSTR(String created_atSTR) {
		this.created_atSTR = created_atSTR;
	}

	
	
	public String getTotalFondoInicialSTR() {
		return totalFondoInicialSTR;
	}

	
	public void setTotalFondoInicialSTR(String totalFondoInicialSTR) {
		this.totalFondoInicialSTR = totalFondoInicialSTR;
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

	
	public String getTotalCreditoSTR() {
		return totalCreditoSTR;
	}

	
	public void setTotalCreditoSTR(String totalCreditoSTR) {
		this.totalCreditoSTR = totalCreditoSTR;
	}

	
	public String getTotalNetoSTR() {
		return totalNetoSTR;
	}

	
	public void setTotalNetoSTR(String totalNetoSTR) {
		this.totalNetoSTR = totalNetoSTR;
	}

	
	public String getTotalAbonoSTR() {
		return totalAbonoSTR;
	}

	
	public void setTotalAbonoSTR(String totalAbonoSTR) {
		this.totalAbonoSTR = totalAbonoSTR;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	public Double getTotalFondoInicial() {
		return totalFondoInicial;
	}

	
	public void setTotalFondoInicial(Double totalFondoInicial) {
		this.totalFondoInicial = totalFondoInicial;
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

	
	public Double getTotalCredito() {
		return totalCredito;
	}

	
	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}

	
	public Double getTotalNeto() {
		return totalNeto;
	}

	
	public void setTotalNeto(Double totalNeto) {
		this.totalNeto = totalNeto;
	}

	
	public Double getTotalAbono() {
		return totalAbono;
	}

	
	public void setTotalAbono(Double totalAbono) {
		this.totalAbono = totalAbono;
	}

	
	

}

package com.factura.FacturaElectronica.web.command;

import java.util.Date;

import com.factura.FacturaElectronica.modelo.Caja;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;

public class UsuarioCajaCommand {

	private Integer	id;
	private Double	totalFondoInicial;

	private Double	totalEfectivo;

	private Double	totalTarjeta;

	private Double	totalBanco;

	private Double	totalCredito;

	private Double	totalNeto;

	private Double	totalAbono;

	private Usuario	usuario;

	private Caja		caja;

	private Date		created_at;

	private Date		updated_at;

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

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

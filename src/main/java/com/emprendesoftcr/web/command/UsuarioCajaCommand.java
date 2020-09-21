package com.emprendesoftcr.web.command;

import java.util.Date;
import java.util.Set;

import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.jsonDeserializer.ClienteDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class UsuarioCajaCommand {

	private Long									id;
	private Double								totalFondoInicial;

	private Double								totalEfectivo;

	private Double								totalTarjeta;

	private Double								totalBanco;

	private Double								totalCredito;

	private Double								totalNeto;

	private Double								totalAbono;

	private Double								totalServicio;

	private Double								totalDolares;

	private String								totalFondoInicialSTR;

	private String								totalEfectivoSTR;

	private String								totalTarjetaSTR;

	private String								totalBancoSTR;

	private String								totalCreditoSTR;

	private String								totalNetoSTR;

	private String								totalAbonoSTR;

	private String								totalServicioSTR;

	private String								totalDolaresSTR;

	private Usuario								usuario;

	private Caja									caja;

	private Date									created_at;

	private Date									updated_at;

	private Date									cierreCaja;

	private String								created_atSTR;

	private String								updated_atSTR;

	private String								estado;

	private Double								sumaEntradas;
	private Double								sumaSalida;
	private Double								conteoManual;
	private String								sumaConteoManualCierreSTR;

	private String								sumaConteoManualAperturaSTR;
	private String								tipoCambioSTR;

	private String								conteoDolarSTR;

	private Double								totalCierre;

	private String								diferenciaSTR;
	private String								diferenciaFinalSTR;
	private String								datafonoSTR;

	private String								conteoDolarConversionSTR;

	@JsonDeserialize(using = ClienteDeserializer.class)
	private Set<ConteoManualCaja>	conteoManualCajas;
	
	@JsonDeserialize(using = ClienteDeserializer.class)
	private Set<SalidaEntradaDinero>	salidaEntradaDineros;

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
		this.totalDolares = usuarioCaja.getTotalDolares();
		this.usuario = usuarioCaja.getUsuario();
		this.caja = usuarioCaja.getCaja();
		this.created_at = usuarioCaja.getCreated_at();
		this.updated_at = usuarioCaja.getUpdated_at();
		this.cierreCaja = usuarioCaja.getCierreCaja();
		this.estado = usuarioCaja.getEstado();
		this.totalAbono = usuarioCaja.getTotalAbono() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalAbono();
		this.totalServicio = usuarioCaja.getTotalServicio() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTotalServicio();
		this.created_atSTR = Utils.getFechaGeneraReporte(usuarioCaja.getCreated_at());
		this.updated_atSTR = Utils.getFechaGeneraReporte(usuarioCaja.getUpdated_at());
		this.totalFondoInicialSTR = usuarioCaja.getTotalFondoInicialSTR();
		this.totalEfectivoSTR = usuarioCaja.getTotalEfectivoSTR();
		this.totalTarjetaSTR = usuarioCaja.getTotalTarjetaSTR();
		this.totalBancoSTR = usuarioCaja.getTotalBancoSTR();
		this.totalCreditoSTR = usuarioCaja.getTotalCreditoSTR();
		this.totalNetoSTR = usuarioCaja.getTotalNetoSTR();
		this.totalAbonoSTR = usuarioCaja.getTotalAbonoSTR();
		this.totalServicioSTR = usuarioCaja.getTotalServicioSTR();
		this.totalDolaresSTR = usuarioCaja.getTotalDolaresSTR();
		this.conteoManualCajas = usuarioCaja.getConteoManualCajas();
		this.salidaEntradaDineros = usuarioCaja.getSalidaEntradaDineros();
		this.sumaEntradas = usuarioCaja.getSumaEntradas() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getSumaEntradas();
		this.sumaSalida = usuarioCaja.getSumaSalida() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getSumaSalida();
		this.conteoManual = usuarioCaja.getConteoManual() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getConteoManual();
		this.tipoCambioSTR = Utils.formateadorMiles(usuarioCaja.getTipoCambio());
		this.conteoDolarSTR = Utils.formateadorMiles(usuarioCaja.getConteoDolar());
		Double conteoDolar = usuarioCaja.getConteoDolar() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getConteoDolar();
		Double tipoCambio = usuarioCaja.getTipoCambio() == null ? Constantes.ZEROS_DOUBLE : usuarioCaja.getTipoCambio();
		this.conteoDolarConversionSTR = Utils.formateadorMiles(conteoDolar * tipoCambio);
		this.totalCierre = usuarioCaja.getTotalCierre();
		this.diferenciaSTR = usuarioCaja.getDiferenciaSTR();
		this.datafonoSTR = usuarioCaja.getDatafonoSTR();
		Double diferencia = usuarioCaja.getDiferencia() == null?Constantes.ZEROS_DOUBLE:usuarioCaja.getDiferencia();
		
		this.diferenciaFinalSTR = Utils.formateadorMiles(diferencia.equals(Constantes.ZEROS_DOUBLE) ? Constantes.ZEROS_DOUBLE : diferencia - usuarioCaja.getTotalFondoInicial() );
	}
	


	
	public String getDiferenciaFinalSTR() {
		return diferenciaFinalSTR;
	}

	
	public void setDiferenciaFinalSTR(String diferenciaFinalSTR) {
		this.diferenciaFinalSTR = diferenciaFinalSTR;
	}

	public String getDiferenciaSTR() {
		return diferenciaSTR;
	}

	public void setDiferenciaSTR(String diferenciaSTR) {
		this.diferenciaSTR = diferenciaSTR;
	}

	
	public String getDatafonoSTR() {
		return datafonoSTR;
	}

	
	public void setDatafonoSTR(String datafonoSTR) {
		this.datafonoSTR = datafonoSTR;
	}

	public Double getTotalCierre() {
		return totalCierre;
	}

	public void setTotalCierre(Double totalCierre) {
		this.totalCierre = totalCierre;
	}

	public String getTotalCierreSTR() {
		return Utils.formateadorMiles(totalCierre);
	}

	public String getConteoDolarConversionSTR() {
		return conteoDolarConversionSTR;
	}

	public void setConteoDolarConversionSTR(String conteoDolarConversionSTR) {
		this.conteoDolarConversionSTR = conteoDolarConversionSTR;
	}

	public String getConteoDolarSTR() {
		return conteoDolarSTR;
	}

	public void setConteoDolarSTR(String conteoDolarSTR) {
		this.conteoDolarSTR = conteoDolarSTR;
	}

	public String getTipoCambioSTR() {
		return tipoCambioSTR;
	}

	public void setTipoCambioSTR(String tipoCambioSTR) {
		this.tipoCambioSTR = tipoCambioSTR;
	}

	public String getTotalGeneralSTR() {
		Double totalNeto = this.totalNeto == null ? Constantes.ZEROS_DOUBLE : this.totalNeto + this.sumaEntradas;
		return Utils.formateadorMiles(totalNeto );

	}

	public String getSumaEntradasSTR() {
		return Utils.formateadorMiles(sumaEntradas);
	}

	public String getSumaSalidaSTR() {
		return Utils.formateadorMiles(sumaSalida);
	}

	public String getConteoManualSTR() {
		return Utils.formateadorMiles(this.conteoManual);
	}

	public Double getSumaEntradas() {
		return sumaEntradas;
	}

	public void setSumaEntradas(Double sumaEntradas) {
		this.sumaEntradas = sumaEntradas;
	}

	public Double getSumaSalida() {
		return sumaSalida;
	}

	public void setSumaSalida(Double sumaSalida) {
		this.sumaSalida = sumaSalida;
	}

	public Double getConteoManual() {
		return conteoManual;
	}

	public void setConteoManual(Double conteoManual) {
		this.conteoManual = conteoManual;
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

	public String getTotalServicioSTR() {
		return totalServicioSTR;
	}

	public void setTotalServicioSTR(String totalServicioSTR) {
		this.totalServicioSTR = totalServicioSTR;
	}

	public Double getTotalServicio() {
		return totalServicio;
	}

	public void setTotalServicio(Double totalServicio) {
		this.totalServicio = totalServicio;
	}

	public Double getTotalDolares() {
		return totalDolares;
	}

	public void setTotalDolares(Double totalDolares) {
		this.totalDolares = totalDolares;
	}

	public String getTotalDolaresSTR() {
		return totalDolaresSTR;
	}

	public void setTotalDolaresSTR(String totalDolaresSTR) {
		this.totalDolaresSTR = totalDolaresSTR;
	}

	public Set<ConteoManualCaja> getConteoManualCajas() {
		return conteoManualCajas;
	}

	public void setConteoManualCajas(Set<ConteoManualCaja> conteoManualCajas) {
		this.conteoManualCajas = conteoManualCajas;
	}

	public Date getCierreCaja() {
		return cierreCaja;
	}

	public String getCierreCajaSTR() {
		return Utils.getFechaGeneraReporte(this.getCierreCaja());
	}

	public void setCierreCaja(Date cierreCaja) {
		this.cierreCaja = cierreCaja;
	}

	public String getSumaConteoManualCierreSTR() {
		return sumaConteoManualCierreSTR;
	}

	public void setSumaConteoManualCierreSTR(String sumaConteoManualCierreSTR) {
		this.sumaConteoManualCierreSTR = sumaConteoManualCierreSTR;
	}

	public String getSumaConteoManualAperturaSTR() {
		return sumaConteoManualAperturaSTR;
	}

	public void setSumaConteoManualAperturaSTR(String sumaConteoManualAperturaSTR) {
		this.sumaConteoManualAperturaSTR = sumaConteoManualAperturaSTR;
	}

	
	public Set<SalidaEntradaDinero> getSalidaEntradaDineros() {
		return salidaEntradaDineros;
	}

	
	public void setSalidaEntradaDineros(Set<SalidaEntradaDinero> salidaEntradaDineros) {
		this.salidaEntradaDineros = salidaEntradaDineros;
	}

}

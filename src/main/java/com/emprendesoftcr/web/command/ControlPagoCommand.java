package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.ControlPago;
import com.emprendesoftcr.modelo.Empresa;

public class ControlPagoCommand {

	private Long		id;
	
	private Date		fechaPago;
	
	private Date		fechaLimite;
	
  private String		fechaPagoT;
	
	private String		fechaLimiteT;

	private String	mensaje;

	private Integer	tipoPago;

	private Double	totalDolar;

	private Double	totalColones;

	private Double	tipoCambio;

	private Integer	cantidadNotificacion;

	private Integer	estado;
	private Empresa	empresa;

	
	
	
	public ControlPagoCommand(ControlPago controlPago) {
		super();
		this.id = controlPago.getId();
		
		this.fechaPago = controlPago.getFechaPago();
		
		this.fechaLimite = controlPago.getFechaLimite();

		this.mensaje = controlPago.getMensaje();

		this.tipoPago = controlPago.getTipoPago();

		this.totalDolar = controlPago.getTotalDolar();

		this.totalColones = controlPago.getTotalColones();

		this.tipoCambio = controlPago.getTipoCambio();

		this.cantidadNotificacion = controlPago.getCantidadNotificacion();

		this.estado = controlPago.getEstado();
		this.empresa = controlPago.getEmpresa();
	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Date getFechaPago() {
		return fechaPago;
	}

	public String getFechaPagoSTR() {
		return Utils.getFechaGeneraReporte(this.fechaPago);
	}

	
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	
	public Date getFechaLimite() {
		return fechaLimite;
	}
	public String getFechaLimiteSTR() {
		return Utils.getFechaGeneraReporte(this.fechaLimite);
	}

	
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	
	
	
	
	public String getFechaPagoT() {
		return Utils.getFechat(this.fechaPago);
	}


	
	public void setFechaPagoT(String fechaPagoT) {
		this.fechaPagoT = fechaPagoT;
	}


	
	public String getFechaLimiteT() {
		return Utils.getFechat(this.fechaLimite);
	}


	
	public void setFechaLimiteT(String fechaLimiteT) {
		this.fechaLimiteT = fechaLimiteT;
	}


	public String getMensaje() {
		return mensaje;
	}

	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	public Integer getTipoPago() {
		return tipoPago;
	}

	public String getTipoPagoSTR() {
		return MapEnums.ENUM_TIPO_PAGO_CONTROL_PAGO.get(tipoPago);
	}

	
	public void setTipoPago(Integer tipoPago) {
		this.tipoPago = tipoPago;
	}

	
	public Double getTotalDolar() {
		return totalDolar;
	}
	
	public String getTotalDolarSTR() {
		return Utils.formateadorMiles(totalDolar);
	}

	 
	
	public void setTotalDolar(Double totalDolar) {
		this.totalDolar = totalDolar;
	}

	
	public Double getTotalColones() {
		return totalColones;
	}

	public String getTotalColonesSTR() {
		return Utils.formateadorMiles(totalColones);
	}

	
	
	public void setTotalColones(Double totalColones) {
		this.totalColones = totalColones;
	}

	
	public Double getTipoCambio() {
		return tipoCambio;
	}
	
	public String getTipoCambioSTR() {
		return Utils.formateadorMiles(tipoCambio);
	}

	
	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	
	public Integer getCantidadNotificacion() {
		return cantidadNotificacion;
	}

	
	public void setCantidadNotificacion(Integer cantidadNotificacion) {
		this.cantidadNotificacion = cantidadNotificacion;
	}

	
	public Integer getEstado() {
		return estado;
	}

	public String getEstadoSTR() {
		return MapEnums.ENUM_ESTADO_.get(estado);
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}


	
	public Empresa getEmpresa() {
		return empresa;
	}


	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	
}

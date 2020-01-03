package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Utils;
@Entity
@Table(name = "contr_pago")
public class ControlPago implements Serializable {

	private static final long serialVersionUID = 9181679160425847905L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long		id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_pago")
	private Date		fechaPago;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_limite")
	private Date		fechaLimite;

	@Column(name = "mensaje")
	private String	mensaje;

	@Column(name = "tipo_pago",columnDefinition = "INT default '0'")
	private Integer	tipoPago;

	@Column(name = "total_d",precision = 18, scale = 5)
	private Double	totalDolar;

	@Column(name = "total_c",precision = 18, scale = 5)
	private Double	totalColones;

	@Column(name = "tipo_cambio",precision = 18, scale = 5)
	private Double	tipoCambio;

	@Column(name = "cant_notif",columnDefinition = "INT default '0'")
	private Integer	cantidadNotificacion;

	@Column(name = "estado",columnDefinition = "INT default '0'")
	private Integer	estado;
	
	@Column(name = "bloqueo", columnDefinition = "INT default '0'")
	private Integer						bloqueo;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa	empresa;
	
	

	
	public ControlPago() {
		super();
	}




	
	
	

	public ControlPago(Long id, Date fechaPago, Date fechaLimite, String mensaje, Integer tipoPago, Double totalDolar, Double totalColones, Double tipoCambio, Integer cantidadNotificacion, Integer estado, Integer bloqueo, Empresa empresa) {
		super();
		this.id = id;
		this.fechaPago = fechaPago;
		this.fechaLimite = fechaLimite;
		this.mensaje = mensaje;
		this.tipoPago = tipoPago;
		this.totalDolar = totalDolar;
		this.totalColones = totalColones;
		this.tipoCambio = tipoCambio;
		this.cantidadNotificacion = cantidadNotificacion;
		this.estado = estado;
		this.bloqueo = bloqueo;
		this.empresa = empresa;
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
		return Utils.getFechat(this.fechaPago);
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	
	public Date getFechaLimite() {
		return fechaLimite;
	}

	public String getFechaLimiteSTR() {
		
		return fechaLimite == null?"":Utils.getFechat(this.fechaLimite);
	}
	
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
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

	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}








	
	public Integer getBloqueo() {
		return bloqueo;
	}








	
	public void setBloqueo(Integer bloqueo) {
		this.bloqueo = bloqueo;
	}
	
	
	

}

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

/**
 * Recepcion de la factura, aceptacion de facturas de un emisor.
 * @author Jairo Cisneros.
 * @since 15 de setiembre del 2018
 */
@Entity
@Table(name = "recepcion_factura")
public class RecepcionFactura implements Serializable {

	private static final long serialVersionUID = 544316204546771917L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "clave")
	private String clave;

	@Column(name = "cedula_emisor")
	private String cedulaEmisor;

	@Column(name = "tipo_cedula_emisor")
	private String tipoCedulaEmisor;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date fechaEmision;

	// 1 aceptado, 2 aceptado parcialmente, 3 rechazado.
	@Column(name = "mensaje")
	private String mensaje;

	@Column(name = "detalle_Mensaje")
	private String detalleMensaje;

	@Column(name = "total_impuestos")
	private Double totalImpuestos;

	@Column(name = "total_factura")
	private Double totalFactura;

	@Column(name = "cedula_receptor")
	private String cedulaReceptor;

	@Column(name = "numero_conse_receptor")
	private String numeroConsecutivoReceptor;

	@Column(name = "estado_firma")
	private Integer estadoFirma;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public RecepcionFactura() {
		super();
	}

	public String getTipoCedulaEmisor() {
		return tipoCedulaEmisor;
	}

	public void setTipoCedulaEmisor(String tipoCedulaEmisor) {
		this.tipoCedulaEmisor = tipoCedulaEmisor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCedulaEmisor() {
		return cedulaEmisor;
	}

	public void setCedulaEmisor(String cedulaEmisor) {
		this.cedulaEmisor = cedulaEmisor;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDetalleMensaje() {
		return detalleMensaje;
	}

	public void setDetalleMensaje(String detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}

	public Double getTotalImpuestos() {
		return totalImpuestos;
	}

	public void setTotalImpuestos(Double totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public Double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(Double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public String getCedulaReceptor() {
		return cedulaReceptor;
	}

	public void setCedulaReceptor(String cedulaReceptor) {
		this.cedulaReceptor = cedulaReceptor;
	}

	public String getNumeroConsecutivoReceptor() {
		return numeroConsecutivoReceptor;
	}

	public void setNumeroConsecutivoReceptor(String numeroConsecutivoReceptor) {
		this.numeroConsecutivoReceptor = numeroConsecutivoReceptor;
	}

	public Integer getEstadoFirma() {
		return estadoFirma;
	}

	public void setEstadoFirma(Integer estadoFirma) {
		this.estadoFirma = estadoFirma;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
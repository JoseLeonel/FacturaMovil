package com.emprendesoftcr.web.command;

import java.util.Date;

import javax.persistence.Column;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstado;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndEstadoAndUsuario;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturada;
import com.emprendesoftcr.modelo.sqlNativo.ProformasByEmpresaAndFacturadaAndUsuario;

public class ProformasByEmpresaAndEstadoCommand {

	private Long		id;

	private String	consecutivoProforma;
	
	private String						consecutivo;

	private Date		fechaEmision;
	private String	nombreFactura;

	private Double	totalDescuentos;

	private Double	totalImpuesto;

	private Double	totalComprobante;

	private Integer	estado;

	private String	cliente;

	private Integer	empresa;

	private String	usuarioCreacion;

	private String	fechaEmisionSTR;

	private String	totalImpuestoSTR;

	private String	totalDescuentosSTR;

	private String	totalComprobanteSTR;

	public ProformasByEmpresaAndEstadoCommand(ProformasByEmpresaAndEstadoAndUsuario proformasByEmpresaAndEstado) {
		super();
		this.id = proformasByEmpresaAndEstado.getId();
		this.consecutivoProforma = proformasByEmpresaAndEstado.getConsecutivoProforma();
		this.consecutivo = proformasByEmpresaAndEstado.getConsecutivo();
		this.fechaEmision = proformasByEmpresaAndEstado.getFechaEmision();
		this.nombreFactura = proformasByEmpresaAndEstado.getNombreFactura();
		this.totalDescuentos = proformasByEmpresaAndEstado.getTotalDescuentos();
		this.totalImpuesto = proformasByEmpresaAndEstado.getTotalImpuesto();
		this.totalComprobante = proformasByEmpresaAndEstado.getTotalComprobante();
		this.estado = proformasByEmpresaAndEstado.getEstado();
		this.cliente = proformasByEmpresaAndEstado.getCliente();
		this.usuarioCreacion = proformasByEmpresaAndEstado.getUsuarioCreacion();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(proformasByEmpresaAndEstado.getFechaEmision());
		this.totalImpuestoSTR = Utils.formateadorMiles(proformasByEmpresaAndEstado.getTotalImpuesto());
		this.totalDescuentosSTR = Utils.formateadorMiles(proformasByEmpresaAndEstado.getTotalDescuentos());
		this.totalComprobanteSTR = Utils.formateadorMiles(proformasByEmpresaAndEstado.getTotalComprobante());
	}

	public ProformasByEmpresaAndEstadoCommand(ProformasByEmpresaAndFacturadaAndUsuario proformasByEmpresaAndFacturada) {
		super();
		this.id = proformasByEmpresaAndFacturada.getId();
		this.consecutivoProforma = proformasByEmpresaAndFacturada.getConsecutivoProforma();
		this.consecutivo = proformasByEmpresaAndFacturada.getConsecutivo();
		this.fechaEmision = proformasByEmpresaAndFacturada.getFechaEmision();
		this.nombreFactura = proformasByEmpresaAndFacturada.getNombreFactura();
		this.totalDescuentos = proformasByEmpresaAndFacturada.getTotalDescuentos();
		this.totalImpuesto = proformasByEmpresaAndFacturada.getTotalImpuesto();
		this.totalComprobante = proformasByEmpresaAndFacturada.getTotalComprobante();
		this.estado = proformasByEmpresaAndFacturada.getEstado();
		this.cliente = proformasByEmpresaAndFacturada.getCliente();
		this.usuarioCreacion = proformasByEmpresaAndFacturada.getUsuarioCreacion();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(proformasByEmpresaAndFacturada.getFechaEmision());
		this.totalImpuestoSTR = Utils.formateadorMiles(proformasByEmpresaAndFacturada.getTotalImpuesto());
		this.totalDescuentosSTR = Utils.formateadorMiles(proformasByEmpresaAndFacturada.getTotalDescuentos());
		this.totalComprobanteSTR = Utils.formateadorMiles(proformasByEmpresaAndFacturada.getTotalComprobante());

	}
	public ProformasByEmpresaAndEstadoCommand(ProformasByEmpresaAndEstado proformasByEmpresaAndEstado) {
		super();
		this.id = proformasByEmpresaAndEstado.getId();
		this.consecutivoProforma = proformasByEmpresaAndEstado.getConsecutivoProforma();
		this.consecutivo = proformasByEmpresaAndEstado.getConsecutivo();
		this.fechaEmision = proformasByEmpresaAndEstado.getFechaEmision();
		this.nombreFactura = proformasByEmpresaAndEstado.getNombreFactura();
		this.totalDescuentos = proformasByEmpresaAndEstado.getTotalDescuentos();
		this.totalImpuesto = proformasByEmpresaAndEstado.getTotalImpuesto();
		this.totalComprobante = proformasByEmpresaAndEstado.getTotalComprobante();
		this.estado = proformasByEmpresaAndEstado.getEstado();
		this.cliente = proformasByEmpresaAndEstado.getCliente();
		this.usuarioCreacion = proformasByEmpresaAndEstado.getUsuarioCreacion();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(proformasByEmpresaAndEstado.getFechaEmision());
		this.totalImpuestoSTR = Utils.formateadorMiles(proformasByEmpresaAndEstado.getTotalImpuesto());
		this.totalDescuentosSTR = Utils.formateadorMiles(proformasByEmpresaAndEstado.getTotalDescuentos());
		this.totalComprobanteSTR = Utils.formateadorMiles(proformasByEmpresaAndEstado.getTotalComprobante());
	}

	public ProformasByEmpresaAndEstadoCommand(ProformasByEmpresaAndFacturada proformasByEmpresaAndFacturada) {
		super();
		this.id = proformasByEmpresaAndFacturada.getId();
		this.consecutivoProforma = proformasByEmpresaAndFacturada.getConsecutivoProforma();
		this.consecutivo = proformasByEmpresaAndFacturada.getConsecutivo();
		this.fechaEmision = proformasByEmpresaAndFacturada.getFechaEmision();
		this.nombreFactura = proformasByEmpresaAndFacturada.getNombreFactura();
		this.totalDescuentos = proformasByEmpresaAndFacturada.getTotalDescuentos();
		this.totalImpuesto = proformasByEmpresaAndFacturada.getTotalImpuesto();
		this.totalComprobante = proformasByEmpresaAndFacturada.getTotalComprobante();
		this.estado = proformasByEmpresaAndFacturada.getEstado();
		this.cliente = proformasByEmpresaAndFacturada.getCliente();
		this.usuarioCreacion = proformasByEmpresaAndFacturada.getUsuarioCreacion();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(proformasByEmpresaAndFacturada.getFechaEmision());
		this.totalImpuestoSTR = Utils.formateadorMiles(proformasByEmpresaAndFacturada.getTotalImpuesto());
		this.totalDescuentosSTR = Utils.formateadorMiles(proformasByEmpresaAndFacturada.getTotalDescuentos());
		this.totalComprobanteSTR = Utils.formateadorMiles(proformasByEmpresaAndFacturada.getTotalComprobante());

	}
	public String getFechaEmisionSTR() {
		return fechaEmisionSTR;
	}

	public void setFechaEmisionSTR(String fechaEmisionSTR) {
		this.fechaEmisionSTR = fechaEmisionSTR;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getConsecutivo() {
		return consecutivo;
	}

	
	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getTotalImpuestoSTR() {
		return totalImpuestoSTR;
	}

	public void setTotalImpuestoSTR(String totalImpuestoSTR) {
		this.totalImpuestoSTR = totalImpuestoSTR;
	}

	public String getTotalDescuentosSTR() {
		return totalDescuentosSTR;
	}

	public void setTotalDescuentosSTR(String totalDescuentosSTR) {
		this.totalDescuentosSTR = totalDescuentosSTR;
	}

	public String getTotalComprobanteSTR() {
		return totalComprobanteSTR;
	}

	public void setTotalComprobanteSTR(String totalComprobanteSTR) {
		this.totalComprobanteSTR = totalComprobanteSTR;
	}

	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	public Double getTotalDescuentos() {
		return totalDescuentos;
	}

	public void setTotalDescuentos(Double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalComprobante() {
		return totalComprobante;
	}

	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

}

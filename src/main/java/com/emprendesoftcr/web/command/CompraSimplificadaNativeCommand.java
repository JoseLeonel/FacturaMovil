package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.sqlNativo.CompraSimplificadaNative;

public class CompraSimplificadaNativeCommand {

	private Long		id;

	private String	numeroConsecutivo;

	private String	clave;

	private Date		fechaEmision;
	private String		fechaEmisionSTR;

	private Double	totalDescuento;
	private Double	totalImpuesto;

	private Double	totalComprobante;
	
	private String	totalDescuentoSTR;
	private String	totalImpuestoSTR;

	private String	totalComprobanteSTR;

	private Integer	estado;

	private Date		created_at;
	
	private String		created_atSTR;

	private String	nombreProveedor;

	private String	nombreUsuario;

	private String	codigoActividad;

	public CompraSimplificadaNativeCommand() {
		super();
	}

	public CompraSimplificadaNativeCommand(CompraSimplificadaNative compraSimplificadaNative) {
		super();
		this.id = compraSimplificadaNative.getId();
		this.numeroConsecutivo = compraSimplificadaNative.getNumeroConsecutivo();
		this.clave = compraSimplificadaNative.getClave();
		this.fechaEmision = compraSimplificadaNative.getFechaEmision();
		this.totalImpuesto = compraSimplificadaNative.getTotalImpuesto();
		this.totalComprobante = compraSimplificadaNative.getTotalComprobante();
		this.estado = compraSimplificadaNative.getEstado();
		this.created_at = compraSimplificadaNative.getCreated_at();
		this.nombreProveedor = compraSimplificadaNative.getNombreProveedor();
		this.nombreUsuario = compraSimplificadaNative.getNombreUsuario();
		this.codigoActividad = compraSimplificadaNative.getCodigoActividad();
		this.totalDescuento = compraSimplificadaNative.getTotalDescuentos();
		this.totalComprobanteSTR = Utils.formateadorMiles(compraSimplificadaNative.getTotalComprobante());
		this.totalDescuentoSTR  = Utils.formateadorMiles(compraSimplificadaNative.getTotalDescuentos());
		this.totalImpuestoSTR  = Utils.formateadorMiles(compraSimplificadaNative.getTotalImpuesto());
		this.created_atSTR = Utils.getFechaGeneraHacienda(compraSimplificadaNative.getCreated_at());
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(compraSimplificadaNative.getFechaEmision());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getFechaEmisionSTR() {
		return fechaEmisionSTR;
	}

	
	public void setFechaEmisionSTR(String fechaEmisionSTR) {
		this.fechaEmisionSTR = fechaEmisionSTR;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	
	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
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

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	
	public String getTotalDescuentoSTR() {
		return totalDescuentoSTR;
	}

	
	public void setTotalDescuentoSTR(String totalDescuentoSTR) {
		this.totalDescuentoSTR = totalDescuentoSTR;
	}

	
	public String getTotalImpuestoSTR() {
		return totalImpuestoSTR;
	}

	
	public void setTotalImpuestoSTR(String totalImpuestoSTR) {
		this.totalImpuestoSTR = totalImpuestoSTR;
	}

	
	public String getTotalComprobanteSTR() {
		return totalComprobanteSTR;
	}

	
	public void setTotalComprobanteSTR(String totalComprobanteSTR) {
		this.totalComprobanteSTR = totalComprobanteSTR;
	}

	
	public String getCreated_atSTR() {
		return created_atSTR;
	}

	
	public void setCreated_atSTR(String created_atSTR) {
		this.created_atSTR = created_atSTR;
	}

	
	
}

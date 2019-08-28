package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;

public class FacturaDiaCommand {

	private Long		id;
	private String	numeroConsecutivo;

	private String	consecutivoProforma;

	private String	clave;

	private Date		fechaEmision;

	private String	condicionVenta;

	private Integer	plazoCredito;

	private String	tipoDoc;

	private String	nombreFactura;

	private Double	subTotal;

	private Double	totalDescuentos;

	private Double	totalImpuesto;

	private Double	totalComprobante;

	private Integer	estado;

	private Date		created_at;

	private Date		updated_at;

	private String	cliente;

	private String	fechaEmisionSTR;
	private String	cedula;
	private String	totalImpuestoSTR;
	private String	totalDescuentosSTR;
	private String	totalComprobanteSTR;
	private String	condicionVentaSTR;

	public FacturaDiaCommand(FacturasDelDiaNative facturasDelDia) {
		super();
		this.id = facturasDelDia.getId();
		this.numeroConsecutivo = facturasDelDia.getNumeroConsecutivo();
		this.consecutivoProforma = facturasDelDia.getConsecutivoProforma();
		this.clave = facturasDelDia.getClave();
		this.fechaEmision = facturasDelDia.getFechaEmision();
		this.condicionVenta = facturasDelDia.getCondicionVenta();
		this.plazoCredito = facturasDelDia.getPlazoCredito();
		this.tipoDoc = facturasDelDia.getTipoDoc();
		this.nombreFactura = facturasDelDia.getNombreFactura();
		this.subTotal = facturasDelDia.getSubTotal();
		this.totalDescuentos = facturasDelDia.getTotalDescuentos();
		this.totalImpuesto = facturasDelDia.getTotalImpuesto();
		this.totalComprobante = facturasDelDia.getTotalComprobante();
		this.estado = facturasDelDia.getEstado();
		this.created_at = facturasDelDia.getCreated_at();
		this.updated_at = facturasDelDia.getUpdated_at();
		this.cliente = facturasDelDia.getCliente();
		this.fechaEmisionSTR = Utils.getFechaGeneraReporte(facturasDelDia.getFechaEmision());
		this.cedula = facturasDelDia.getCedula();
		this.totalComprobanteSTR = Utils.formateadorMiles(facturasDelDia.getTotalComprobante());
		this.totalImpuestoSTR = Utils.formateadorMiles(facturasDelDia.getTotalImpuesto());
		this.totalDescuentosSTR = Utils.formateadorMiles(facturasDelDia.getTotalDescuentos());
		this.condicionVentaSTR =MapEnums.ENUM_CONDICION_VENTA.get(facturasDelDia.getCondicionVenta());

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCondicionVentaSTR() {
		return condicionVentaSTR;
	}

	public void setCondicionVentaSTR(String condicionVentaSTR) {
		this.condicionVentaSTR = condicionVentaSTR;
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

	public String getTotalImpuestoSTR() {
		return totalImpuestoSTR;
	}

	public void setTotalImpuestoSTR(String totalImpuestoSTR) {
		this.totalImpuestoSTR = totalImpuestoSTR;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getFechaEmisionSTR() {
		return fechaEmisionSTR;
	}

	public void setFechaEmisionSTR(String fechaEmisionSTR) {
		this.fechaEmisionSTR = fechaEmisionSTR;
	}

	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
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

	public String getCondicionVenta() {
		return condicionVenta;
	}

	public void setCondicionVenta(String condicionVenta) {
		this.condicionVenta = condicionVenta;
	}

	public Integer getPlazoCredito() {
		return plazoCredito;
	}

	public void setPlazoCredito(Integer plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}

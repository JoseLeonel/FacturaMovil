package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasNativa;

public class FacturaAnulacionCommand {

	private Long		id;

	private String	numeroConsecutivo;

	private Double	totalComprobante;

	private String	codigoMoneda;

	private Integer	estado;

	private Double	totalDescuentos;

	private Double	totalImpuesto;

	private String	nombreCompleto;

	private String	cedula;

	private Date		fechaEmision;

	private String	nombreUsuario;

	private String	fechaEmisionSTR;

	private String	totalImpuestoSTR;

	private String	totalDescuentosSTR;

	private String	totalComprobanteSTR;

	private String	nombreFactura;
	private String tipoDocSTR;
	private String tipoDoc;
	private String condicionVentaSTR;
	private Integer						noFacturaElectronica;

	public FacturaAnulacionCommand(FacturasSinNotaCreditoNative facturasSinNotaCreditoNative) {
		super();

		this.id = facturasSinNotaCreditoNative.getId();

		this.numeroConsecutivo = facturasSinNotaCreditoNative.getNumeroConsecutivo();

		this.totalComprobante = facturasSinNotaCreditoNative.getTotalComprobante();

		this.codigoMoneda = facturasSinNotaCreditoNative.getCodigoMoneda();

		this.estado = facturasSinNotaCreditoNative.getEstado();

		this.totalDescuentos = facturasSinNotaCreditoNative.getTotalDescuentos();

		this.totalImpuesto = facturasSinNotaCreditoNative.getTotalImpuesto();

		this.nombreCompleto = facturasSinNotaCreditoNative.getNombreCompleto();

		this.cedula = facturasSinNotaCreditoNative.getCedula();
		this.fechaEmision = facturasSinNotaCreditoNative.getFechaEmision();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(facturasSinNotaCreditoNative.getFechaEmision());
		this.totalImpuestoSTR = Utils.formateadorMiles(facturasSinNotaCreditoNative.getTotalImpuesto());
		this.totalDescuentosSTR = Utils.formateadorMiles(facturasSinNotaCreditoNative.getTotalDescuentos());
		this.totalComprobanteSTR = Utils.formateadorMiles(facturasSinNotaCreditoNative.getTotalComprobante());
		this.nombreFactura = facturasSinNotaCreditoNative.getNombreFactura();
		this.nombreUsuario = facturasSinNotaCreditoNative.getNombreUsuario();
	}
	
	public FacturaAnulacionCommand(ListarFacturasNativa facturasSinNotaCreditoNative) {
		super();
		this.id = facturasSinNotaCreditoNative.getId();

		this.numeroConsecutivo = facturasSinNotaCreditoNative.getNumeroConsecutivo();

		this.totalComprobante = facturasSinNotaCreditoNative.getTotalComprobante();

		this.codigoMoneda = facturasSinNotaCreditoNative.getCodigoMoneda();

		this.estado = facturasSinNotaCreditoNative.getEstado();

		this.totalDescuentos = facturasSinNotaCreditoNative.getTotalDescuentos();

		this.totalImpuesto = facturasSinNotaCreditoNative.getTotalImpuesto();

		this.nombreCompleto = facturasSinNotaCreditoNative.getNombreCompleto();

		this.cedula = facturasSinNotaCreditoNative.getCedula();
		this.fechaEmision = facturasSinNotaCreditoNative.getFechaEmision();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(facturasSinNotaCreditoNative.getFechaEmision());
		this.totalImpuestoSTR = Utils.formateadorMiles(facturasSinNotaCreditoNative.getTotalImpuesto());
		this.totalDescuentosSTR = Utils.formateadorMiles(facturasSinNotaCreditoNative.getTotalDescuentos());
		this.totalComprobanteSTR = Utils.formateadorMiles(facturasSinNotaCreditoNative.getTotalComprobante());
		this.nombreFactura = facturasSinNotaCreditoNative.getNombreFactura();
		this.nombreUsuario = facturasSinNotaCreditoNative.getNombreUsuario();
		this.tipoDocSTR = MapEnums.ENUM_TIPO_DOC.get(facturasSinNotaCreditoNative.getTipoDoc());
		this.tipoDoc = facturasSinNotaCreditoNative.getTipoDoc();
		this.condicionVentaSTR =MapEnums.ENUM_CONDICION_VENTA.get(facturasSinNotaCreditoNative.getCondicionVenta());
		this.noFacturaElectronica = facturasSinNotaCreditoNative.getNoFacturaElectronica();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getTipoDoc() {
		return tipoDoc;
	}

	
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getTipoDocSTR() {
		return tipoDocSTR;
	}

	
	public void setTipoDocSTR(String tipoDocSTR) {
		this.tipoDocSTR = tipoDocSTR;
	}
	

	
	public String getCondicionVentaSTR() {
		return condicionVentaSTR;
	}

	
	public void setCondicionVentaSTR(String condicionVentaSTR) {
		this.condicionVentaSTR = condicionVentaSTR;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	public String getFechaEmisionSTR() {
		return fechaEmisionSTR;
	}

	public void setFechaEmisionSTR(String fechaEmisionSTR) {
		this.fechaEmisionSTR = fechaEmisionSTR;
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

	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	public Double getTotalComprobante() {
		return totalComprobante;
	}

	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	
	public Integer getNoFacturaElectronica() {
		return noFacturaElectronica;
	}

	
	public void setNoFacturaElectronica(Integer noFacturaElectronica) {
		this.noFacturaElectronica = noFacturaElectronica;
	}

}

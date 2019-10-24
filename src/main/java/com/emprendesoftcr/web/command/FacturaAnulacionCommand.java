package com.emprendesoftcr.web.command;

import java.util.Date;

import javax.persistence.Column;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.modelo.sqlNativo.FacturasSinNotaCreditoNative;
import com.emprendesoftcr.modelo.sqlNativo.ListaNotasNative;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturaNCNativa;
import com.emprendesoftcr.modelo.sqlNativo.ListarFacturasImpuestoServicioNativa;
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
	private String	tipoDocSTR;
	private String	tipoDoc;
	private String	estadoSTR;
	private String	condicionVentaSTR;
	private Integer	noFacturaElectronica;
	private String	impuestoServicioSTR;
	private Double	impuestoServicio;
	private Double	cantidadNotasCredito;
	private Double	cantidad;

	private String	referenciaNumero;

	private String	referenciaRazon;

	private String	referenciaCodigo;
	private String	referenciaTipoDoc;

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

	public FacturaAnulacionCommand(ListaNotasNative listaNotas) {
		super();
		this.id = listaNotas.getId();
		this.numeroConsecutivo = listaNotas.getNumeroConsecutivo();
		this.totalComprobante = listaNotas.getTotalComprobante();
		this.estado = listaNotas.getEstado();
		this.tipoDocSTR = MapEnums.ENUM_TIPO_DOC.get(listaNotas.getTipoDoc());
		this.tipoDoc = listaNotas.getTipoDoc();
		this.referenciaTipoDoc = MapEnums.ENUM_TIPO_DOC.get(listaNotas.getReferenciaTipoDoc());
		this.referenciaCodigo = MapEnums.ENUM_CODIGO_REFERENCIA.get(listaNotas.getReferenciaCodigo());
		this.referenciaNumero = listaNotas.getReferenciaNumero();
		this.nombreCompleto = listaNotas.getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) || listaNotas.getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO) ?listaNotas.getNombreFactura():listaNotas.getNombreCompleto();
		this.cedula = listaNotas.getCedula();
		this.fechaEmision = listaNotas.getFechaEmision();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(listaNotas.getFechaEmision());
		this.totalComprobanteSTR = Utils.formateadorMiles(listaNotas.getTotalComprobante());
		this.nombreFactura = listaNotas.getNombreFactura();
		this.nombreUsuario = listaNotas.getNombreUsuario();
		this.estadoSTR = MapEnums.ENUM_ESTADO_FACTURA.get(listaNotas.getEstado().toString());
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
		this.condicionVentaSTR = MapEnums.ENUM_CONDICION_VENTA.get(facturasSinNotaCreditoNative.getCondicionVenta());
		this.noFacturaElectronica = facturasSinNotaCreditoNative.getNoFacturaElectronica();
		this.estadoSTR = MapEnums.ENUM_ESTADO_FACTURA.get(facturasSinNotaCreditoNative.getEstado().toString());
	}

	public FacturaAnulacionCommand(ListarFacturaNCNativa facturasSinNotasCredito) {
		super();
		this.id = facturasSinNotasCredito.getId();

		this.cantidad = facturasSinNotasCredito.getCantidad();
		this.cantidadNotasCredito = facturasSinNotasCredito.getCantidadNotas();

		this.numeroConsecutivo = facturasSinNotasCredito.getNumeroConsecutivo();

		this.totalComprobante = facturasSinNotasCredito.getTotalComprobante();

		this.estado = facturasSinNotasCredito.getEstado();

		this.nombreCompleto = facturasSinNotasCredito.getNombreCompleto();

		this.cedula = facturasSinNotasCredito.getCedula();
		this.fechaEmision = facturasSinNotasCredito.getFechaEmision();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(facturasSinNotasCredito.getFechaEmision());
		this.totalComprobanteSTR = Utils.formateadorMiles(facturasSinNotasCredito.getTotalComprobante());
		this.nombreFactura = facturasSinNotasCredito.getNombreFactura();
		this.nombreUsuario = facturasSinNotasCredito.getNombreUsuario();
		this.tipoDocSTR = MapEnums.ENUM_TIPO_DOC.get(facturasSinNotasCredito.getTipoDoc());
		this.tipoDoc = facturasSinNotasCredito.getTipoDoc();
		this.condicionVentaSTR = MapEnums.ENUM_CONDICION_VENTA.get(facturasSinNotasCredito.getCondicionVenta());
		this.estadoSTR = MapEnums.ENUM_ESTADO_FACTURA.get(facturasSinNotasCredito.getEstado().toString());

	}

	public FacturaAnulacionCommand(ListarFacturasImpuestoServicioNativa facturasSinNotaCreditoNative) {
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
		this.condicionVentaSTR = MapEnums.ENUM_CONDICION_VENTA.get(facturasSinNotaCreditoNative.getCondicionVenta());
		this.noFacturaElectronica = facturasSinNotaCreditoNative.getNoFacturaElectronica();
		this.estadoSTR = MapEnums.ENUM_ESTADO_FACTURA.get(facturasSinNotaCreditoNative.getEstado().toString());
		if (facturasSinNotaCreditoNative.getTotalImpuestoServicio() != null) {
			this.impuestoServicio = facturasSinNotaCreditoNative.getTotalImpuestoServicio();
		}
		if (facturasSinNotaCreditoNative.getTotalOtrosCargos() != null) {
			this.impuestoServicio = facturasSinNotaCreditoNative.getTotalOtrosCargos();
		}
		this.impuestoServicioSTR = Utils.formateadorMiles(this.impuestoServicio);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferenciaTipoDoc() {
		return referenciaTipoDoc;
	}

	public void setReferenciaTipoDoc(String referenciaTipoDoc) {
		this.referenciaTipoDoc = referenciaTipoDoc;
	}

	public Double getCantidadNotasCredito() {
		return cantidadNotasCredito;
	}

	public void setCantidadNotasCredito(Double cantidadNotasCredito) {
		this.cantidadNotasCredito = cantidadNotasCredito;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getImpuestoServicioSTR() {
		return impuestoServicioSTR;
	}

	public void setImpuestoServicioSTR(String impuestoServicioSTR) {
		this.impuestoServicioSTR = impuestoServicioSTR;
	}

	public Double getImpuestoServicio() {
		return impuestoServicio;
	}

	public void setImpuestoServicio(Double impuestoServicio) {
		this.impuestoServicio = impuestoServicio;
	}

	public String getEstadoSTR() {
		return estadoSTR;
	}

	public void setEstadoSTR(String estadoSTR) {
		this.estadoSTR = estadoSTR;
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

	public String getReferenciaNumero() {
		return referenciaNumero;
	}

	public void setReferenciaNumero(String referenciaNumero) {
		this.referenciaNumero = referenciaNumero;
	}

	public String getReferenciaRazon() {
		return referenciaRazon;
	}

	public void setReferenciaRazon(String referenciaRazon) {
		this.referenciaRazon = referenciaRazon;
	}

	public String getReferenciaCodigo() {
		return referenciaCodigo;
	}

	public void setReferenciaCodigo(String referenciaCodigo) {
		this.referenciaCodigo = referenciaCodigo;
	}

}

package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Detalle de Recepcion de la factura, aceptacion de facturas de un emisor.
 * @author Jairo Cisneros.
 * @since 10 de julio del 2019
 */
@Entity
@Table(name = "recepcion_factura_detalle")
public class RecepcionFacturaDetalle implements Serializable {

	private static final long serialVersionUID = 544316204546771917L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "numero_linea")
	private String numeroLinea;

	@Column(name = "cantidad")
	private String cantidad;

	@Column(name = "unidad_medida")
	private String unidadMedida;

	@Column(name = "detalle")
	private String detalle;

	@Column(name = "precio_unitario")
	private String precioUnitario;

	@Column(name = "monto_total")
	private String montoTotal;

	@Column(name = "sub_total")
	private String subTotal;

	@Column(name = "monto_total_linea")
	private String montoTotalLinea;

	@Column(name = "impuesto_neto")
	private String impuestoNeto;

	@Column(name = "codigo_comercial_tipo")
	private String codigoComercialTipo;

	@Column(name = "codigo_comercial_codigo")
	private String codigoComercialCodigo;

	@Column(name = "descuento_monto")
	private String descuentoMonto;

	@Column(name = "descuento_naturaleza")
	private String descuentoNaturaleza;

	@Column(name = "impuesto_codigo")
	private String impuestoCodigo;

	@Column(name = "impuesto_codigo_tarifa")
	private String impuestoCodigoTarifa;

	@Column(name = "impuesto_tarifa")
	private String impuestoTarifa;

	@Column(name = "impuesto_monto")
	private String impuestoMonto;

	@Column(name = "imp_exo_tipo_docu")
	private String impuestoExoneracionTipoDocumento;

	@Column(name = "imp_exo_numero_docu")
	private String impuestoExoneracionNumeroDocumento;

	@Column(name = "imp_exo_nombre_institu")
	private String impuestoExoneracionNombreInstitucion;

	@Column(name = "imp_exo_fecha_emision")
	private String impuestoExoneracionFechaEmision;

	@Column(name = "imp_exo_porcentaje")
	private String impuestoExoneracionPorcentaje;

	@Column(name = "imp_exo_monto")
	private String impuestoExoneracionMonto;

	@Column(name = "base_imponible")
	private String baseImponible;

	@ManyToOne
	@JoinColumn(name = "recepcion_factura_id")
	private RecepcionFactura recepcionFactura;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNumeroLinea() {
		return numeroLinea;
	}

	
	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	
	public String getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	
	public String getUnidadMedida() {
		return unidadMedida;
	}

	
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	
	public String getDetalle() {
		return detalle;
	}

	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	
	public String getPrecioUnitario() {
		return precioUnitario;
	}

	
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	
	public String getMontoTotal() {
		return montoTotal;
	}

	
	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}

	
	public String getSubTotal() {
		return subTotal;
	}

	
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	
	public String getMontoTotalLinea() {
		return montoTotalLinea;
	}

	
	public void setMontoTotalLinea(String montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}

	
	public String getImpuestoNeto() {
		return impuestoNeto;
	}

	
	public void setImpuestoNeto(String impuestoNeto) {
		this.impuestoNeto = impuestoNeto;
	}

	
	public String getCodigoComercialTipo() {
		return codigoComercialTipo;
	}

	
	public void setCodigoComercialTipo(String codigoComercialTipo) {
		this.codigoComercialTipo = codigoComercialTipo;
	}

	
	public String getCodigoComercialCodigo() {
		return codigoComercialCodigo;
	}

	
	public void setCodigoComercialCodigo(String codigoComercialCodigo) {
		this.codigoComercialCodigo = codigoComercialCodigo;
	}

	
	public String getDescuentoMonto() {
		return descuentoMonto;
	}

	
	public void setDescuentoMonto(String descuentoMonto) {
		this.descuentoMonto = descuentoMonto;
	}

	
	public String getDescuentoNaturaleza() {
		return descuentoNaturaleza;
	}

	
	public void setDescuentoNaturaleza(String descuentoNaturaleza) {
		this.descuentoNaturaleza = descuentoNaturaleza;
	}

	
	public String getImpuestoCodigo() {
		return impuestoCodigo;
	}

	
	public void setImpuestoCodigo(String impuestoCodigo) {
		this.impuestoCodigo = impuestoCodigo;
	}

	
	public String getImpuestoCodigoTarifa() {
		return impuestoCodigoTarifa;
	}

	
	public void setImpuestoCodigoTarifa(String impuestoCodigoTarifa) {
		this.impuestoCodigoTarifa = impuestoCodigoTarifa;
	}

	
	public String getImpuestoTarifa() {
		return impuestoTarifa;
	}

	
	public void setImpuestoTarifa(String impuestoTarifa) {
		this.impuestoTarifa = impuestoTarifa;
	}

	
	public String getImpuestoMonto() {
		return impuestoMonto;
	}

	
	public void setImpuestoMonto(String impuestoMonto) {
		this.impuestoMonto = impuestoMonto;
	}

	
	public String getImpuestoExoneracionTipoDocumento() {
		return impuestoExoneracionTipoDocumento;
	}

	
	public void setImpuestoExoneracionTipoDocumento(String impuestoExoneracionTipoDocumento) {
		this.impuestoExoneracionTipoDocumento = impuestoExoneracionTipoDocumento;
	}

	
	public String getImpuestoExoneracionNumeroDocumento() {
		return impuestoExoneracionNumeroDocumento;
	}

	
	public void setImpuestoExoneracionNumeroDocumento(String impuestoExoneracionNumeroDocumento) {
		this.impuestoExoneracionNumeroDocumento = impuestoExoneracionNumeroDocumento;
	}

	
	public String getImpuestoExoneracionNombreInstitucion() {
		return impuestoExoneracionNombreInstitucion;
	}

	
	public void setImpuestoExoneracionNombreInstitucion(String impuestoExoneracionNombreInstitucion) {
		this.impuestoExoneracionNombreInstitucion = impuestoExoneracionNombreInstitucion;
	}

	
	public String getImpuestoExoneracionFechaEmision() {
		return impuestoExoneracionFechaEmision;
	}

	
	public void setImpuestoExoneracionFechaEmision(String impuestoExoneracionFechaEmision) {
		this.impuestoExoneracionFechaEmision = impuestoExoneracionFechaEmision;
	}

	
	public String getImpuestoExoneracionPorcentaje() {
		return impuestoExoneracionPorcentaje;
	}

	
	public void setImpuestoExoneracionPorcentaje(String impuestoExoneracionPorcentaje) {
		this.impuestoExoneracionPorcentaje = impuestoExoneracionPorcentaje;
	}

	
	public String getImpuestoExoneracionMonto() {
		return impuestoExoneracionMonto;
	}

	
	public void setImpuestoExoneracionMonto(String impuestoExoneracionMonto) {
		this.impuestoExoneracionMonto = impuestoExoneracionMonto;
	}

	
	public String getBaseImponible() {
		return baseImponible;
	}

	
	public void setBaseImponible(String baseImponible) {
		this.baseImponible = baseImponible;
	}

	
	public RecepcionFactura getRecepcionFactura() {
		return recepcionFactura;
	}

	
	public void setRecepcionFactura(RecepcionFactura recepcionFactura) {
		this.recepcionFactura = recepcionFactura;
	}


}
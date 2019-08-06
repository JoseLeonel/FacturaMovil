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
	private Integer numeroLinea;

	@Column(name = "cantidad", precision = 18, scale = 5)
	private Double cantidad;

	@Column(name = "unidad_medida", length = 15)
	private String unidadMedida;

	@Column(name = "detalle", length = 180)
	private String detalle;

	@Column(name = "precio_unitario", precision = 18, scale = 5)
	private Double precioUnitario;

	@Column(name = "monto_total", precision = 18, scale = 5)
	private Double montoTotal;

	@Column(name = "sub_total", precision = 18, scale = 5)
	private Double subTotal;

	@Column(name = "monto_total_linea", precision = 18, scale = 5)
	private Double montoTotalLinea;

	@Column(name = "impuesto_neto", precision = 18, scale = 5)
	private Double impuestoNeto;

	@Column(name = "codigo_comercial_tipo", length = 2)
	private String codigoComercialTipo;

	@Column(name = "codigo_comercial_codigo", length = 25)
	private String codigoComercialCodigo;

	@Column(name = "descuento_monto", precision = 18, scale = 5)
	private Double descuentoMonto;

	@Column(name = "descuento_naturaleza", length = 180)
	private String descuentoNaturaleza;

	@Column(name = "impuesto_codigo", length = 2)
	private String impuestoCodigo;

	@Column(name = "impuesto_codigo_tarifa", length = 2)
	private String impuestoCodigoTarifa;

	@Column(name = "impuesto_tarifa", precision = 18, scale = 5)
	private Double impuestoTarifa;

	@Column(name = "impuesto_monto", precision = 18, scale = 5)
	private Double impuestoMonto;

	@Column(name = "imp_exo_tipo_docu", length = 2)
	private String impuestoExoneracionTipoDocumento;

	@Column(name = "imp_exo_numero_docu", length = 40)
	private String impuestoExoneracionNumeroDocumento;

	@Column(name = "imp_exo_nombre_institu", length = 160)
	private String impuestoExoneracionNombreInstitucion;

	@Column(name = "imp_exo_fecha_emision", length = 30)
	private String impuestoExoneracionFechaEmision;

	@Column(name = "imp_exo_porcentaje", precision = 18, scale = 5)
	private Double impuestoExoneracionPorcentaje;

	@Column(name = "imp_exo_monto", precision = 18, scale = 5)
	private Double impuestoExoneracionMonto;

	@Column(name = "base_imponible", precision = 18, scale = 5)
	private Double baseImponible;

	@ManyToOne
	@JoinColumn(name = "recepcion_factura_id")
	private RecepcionFactura recepcionFactura;

	
	
	
	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	
	public Integer getNumeroLinea() {
		return numeroLinea;
	}


	
	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}


	
	public Double getCantidad() {
		return cantidad;
	}


	
	public void setCantidad(Double cantidad) {
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


	
	public Double getPrecioUnitario() {
		return precioUnitario;
	}


	
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	
	public Double getMontoTotal() {
		return montoTotal;
	}


	
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}


	
	public Double getSubTotal() {
		return subTotal;
	}


	
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}


	
	public Double getMontoTotalLinea() {
		return montoTotalLinea;
	}


	
	public void setMontoTotalLinea(Double montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}


	
	public Double getImpuestoNeto() {
		return impuestoNeto;
	}


	
	public void setImpuestoNeto(Double impuestoNeto) {
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


	
	public Double getDescuentoMonto() {
		return descuentoMonto;
	}


	
	public void setDescuentoMonto(Double descuentoMonto) {
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


	
	public Double getImpuestoTarifa() {
		return impuestoTarifa;
	}


	
	public void setImpuestoTarifa(Double impuestoTarifa) {
		this.impuestoTarifa = impuestoTarifa;
	}


	
	public Double getImpuestoMonto() {
		return impuestoMonto;
	}


	
	public void setImpuestoMonto(Double impuestoMonto) {
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


	
	public Double getImpuestoExoneracionPorcentaje() {
		return impuestoExoneracionPorcentaje;
	}


	
	public void setImpuestoExoneracionPorcentaje(Double impuestoExoneracionPorcentaje) {
		this.impuestoExoneracionPorcentaje = impuestoExoneracionPorcentaje;
	}


	
	public Double getImpuestoExoneracionMonto() {
		return impuestoExoneracionMonto;
	}


	
	public void setImpuestoExoneracionMonto(Double impuestoExoneracionMonto) {
		this.impuestoExoneracionMonto = impuestoExoneracionMonto;
	}


	
	public Double getBaseImponible() {
		return baseImponible;
	}


	
	public void setBaseImponible(Double baseImponible) {
		this.baseImponible = baseImponible;
	}


	
	public RecepcionFactura getRecepcionFactura() {
		return recepcionFactura;
	}


	
	public void setRecepcionFactura(RecepcionFactura recepcionFactura) {
		this.recepcionFactura = recepcionFactura;
	}

	
	


}
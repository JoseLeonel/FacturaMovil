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

import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.utils.Constantes;

/**
 * Detalle de Recepcion de la factura, aceptacion de facturas de un emisor.
 * @author Jairo Cisneros.
 * @since 10 de julio del 2019
 */
@Entity
@Table(name = "recepcion_factura_detalle")
public class RecepcionFacturaDetalle implements Serializable {

	private static final long	serialVersionUID	= 544316204546771917L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "numero_linea")
	private Integer						numeroLinea;

	@Column(name = "cantidad", precision = 18, scale = 5)
	private Double						cantidad;

	@Column(name = "unidad_medida", length = 15)
	private String						unidadMedida;

	@Column(name = "detalle", length = 180)
	private String						detalle;

	@Column(name = "precio_unitario", precision = 18, scale = 5)
	private Double						precioUnitario;

	@Column(name = "monto_total", precision = 18, scale = 5)
	private Double						montoTotal;

	@Column(name = "sub_total", precision = 18, scale = 5)
	private Double						subTotal;

	@Column(name = "monto_total_linea", precision = 18, scale = 5)
	private Double						montoTotalLinea;

	@Column(name = "impuesto_neto", precision = 18, scale = 5)
	private Double						impuestoNeto;

	@Column(name = "codigo_comercial_tipo", length = 2)
	private String						codigoComercialTipo;

	@Column(name = "codigo_comercial_codigo", length = 25)
	private String						codigoComercialCodigo;

	@Column(name = "descuento_monto", precision = 18, scale = 5)
	private Double						descuentoMonto;

	@Column(name = "descuento_naturaleza", length = 180)
	private String						descuentoNaturaleza;

	@Column(name = "impuesto_codigo", length = 2)
	private String						impuestoCodigo;

	@Column(name = "impuesto_codigo_tarifa", length = 2)
	private String						impuestoCodigoTarifa;

	@Column(name = "impuesto_tarifa", precision = 18, scale = 5)
	private Double						impuestoTarifa;

	@Column(name = "impuesto_monto", precision = 18, scale = 5)
	private Double						impuestoMonto;

	@Column(name = "imp_exo_tipo_docu", length = 2)
	private String						impuestoExoneracionTipoDocumento;

	@Column(name = "imp_exo_numero_docu", length = 40)
	private String						impuestoExoneracionNumeroDocumento;

	@Column(name = "imp_exo_nombre_institu", length = 160)
	private String						impuestoExoneracionNombreInstitucion;

	@Column(name = "imp_exo_fecha_emision", length = 30)
	private String						impuestoExoneracionFechaEmision;

	@Column(name = "imp_exo_porcentaje", precision = 18, scale = 5)
	private Double						impuestoExoneracionPorcentaje;

	@Column(name = "imp_exo_monto", precision = 18, scale = 5)
	private Double						impuestoExoneracionMonto;

	@Column(name = "base_imponible", precision = 18, scale = 5)
	private Double						baseImponible;

	@Column(name = "impuesto_codigo1", length = 2)
	private String						impuestoCodigo1;

	@Column(name = "impuesto_codigo_tarifa1", length = 2)
	private String						impuestoCodigoTarifa1;

	@Column(name = "impuesto_tarifa1", precision = 18, scale = 5)
	private Double						impuestoTarifa1;

	@Column(name = "impuesto_monto1", precision = 18, scale = 5)
	private Double						impuestoMonto1;

	@Column(name = "impuesto_codigo2", length = 2)
	private String						impuestoCodigo2;

	@Column(name = "impuesto_codigo_tarifa2", length = 2)
	private String						impuestoCodigoTarifa2;

	@Column(name = "impuesto_tarifa2", precision = 18, scale = 5)
	private Double						impuestoTarifa2;

	@Column(name = "impuesto_monto2", precision = 18, scale = 5)
	private Double						impuestoMonto2;

	@Column(name = "impuesto_codigo3", length = 2)
	private String						impuestoCodigo3;

	@Column(name = "impuesto_codigo_tarifa3", length = 2)
	private String						impuestoCodigoTarifa3;

	@Column(name = "impuesto_tarifa3", precision = 18, scale = 5)
	private Double						impuestoTarifa3;

	@Column(name = "impuesto_monto3", precision = 18, scale = 5)
	private Double						impuestoMonto3;

	@Column(name = "impuesto_codigo4", length = 2)
	private String						impuestoCodigo4;

	@Column(name = "impuesto_codigo_tarifa4", length = 2)
	private String						impuestoCodigoTarifa4;

	@Column(name = "impuesto_tarifa4", precision = 18, scale = 5)
	private Double						impuestoTarifa4;

	@Column(name = "impuesto_monto4", precision = 18, scale = 5)
	private Double						impuestoMonto4;

	@Column(name = "impuesto_codigo5", length = 2)
	private String						impuestoCodigo5;

	@Column(name = "impuesto_codigo_tarifa5", length = 2)
	private String						impuestoCodigoTarifa5;

	@Column(name = "impuesto_tarifa5", precision = 18, scale = 5)
	private Double						impuestoTarifa5;

	@Column(name = "impuesto_monto5", precision = 18, scale = 5)
	private Double						impuestoMonto5;

	@Column(name = "impuesto_codigo6", length = 2)
	private String						impuestoCodigo6;

	@Column(name = "impuesto_codigo_tarifa6", length = 2)
	private String						impuestoCodigoTarifa6;

	@Column(name = "impuesto_tarifa6", precision = 18, scale = 5)
	private Double						impuestoTarifa6;

	@Column(name = "impuesto_monto6", precision = 18, scale = 5)
	private Double						impuestoMonto6;

	@ManyToOne
	@JoinColumn(name = "recepcion_factura_id")
	private RecepcionFactura	recepcionFactura;

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

	public String getImpuestoCodigoSTR() {
		return impuestoCodigo != null ? MapEnums.ENUM_TIPOS_IMPUESTOS.get(impuestoCodigo) : impuestoCodigo;
	}

	public String getImpuestoCodigoTarifaSTR() {
		return impuestoCodigoTarifa != null ? MapEnums.ENUM_TARIFAS_IMPUESTOS.get(impuestoCodigoTarifa.toString()) : impuestoCodigoTarifa.toString();
	}

	public void setImpuestoTarifa(Double impuestoTarifa) {
		this.impuestoTarifa = impuestoTarifa;
	}

	public Double getImpuestoMontoSTRTimpoCambio() {
		Double resultadoTipoCambio = this.recepcionFactura.getFacturaTipoCambio() == null ? 1 : this.recepcionFactura.getFacturaTipoCambio();
		Double resultaMontoImpuesto = Constantes.ZEROS_DOUBLE;
		if (this.impuestoNeto != null) {
			if (this.impuestoNeto > Constantes.ZEROS_DOUBLE) {
				if (this.recepcionFactura.getTipoDoc() != null) {
					if (this.recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
						return this.impuestoNeto != null ? (this.impuestoNeto * resultadoTipoCambio) * -1 : (this.impuestoNeto * resultadoTipoCambio);
					}
				} else {
					return this.impuestoNeto != null ? (this.impuestoNeto * resultadoTipoCambio) : Constantes.ZEROS_DOUBLE;
				}

			}
		}
		resultaMontoImpuesto = this.impuestoMonto == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto;
		resultaMontoImpuesto += this.impuestoMonto1 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto1;
		resultaMontoImpuesto += this.impuestoMonto2 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto2;
		resultaMontoImpuesto += this.impuestoMonto3 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto3;
		resultaMontoImpuesto += this.impuestoMonto4 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto4;
		resultaMontoImpuesto += this.impuestoMonto5 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto5;
		resultaMontoImpuesto += this.impuestoMonto6 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto6;
		if (this.recepcionFactura.getTipoDoc() != null) {
			if (this.recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				return resultaMontoImpuesto != null ? (resultaMontoImpuesto * resultadoTipoCambio) * -1 : (resultaMontoImpuesto * resultadoTipoCambio);
			}
		} else {
			return resultaMontoImpuesto != null ? (resultaMontoImpuesto * resultadoTipoCambio) : Constantes.ZEROS_DOUBLE;
		}

		return Constantes.ZEROS_DOUBLE;

	}

	public Double getImpuestoMontoSTR() {
		Double resultaMontoImpuesto = Constantes.ZEROS_DOUBLE;
		if (this.impuestoNeto != null) {
			if (this.impuestoNeto > Constantes.ZEROS_DOUBLE) {
				if (this.recepcionFactura.getTipoDoc() != null) {
					if (this.recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
						return this.impuestoNeto != null ? this.impuestoNeto * -1 : this.impuestoNeto;
					} else {
						return this.impuestoNeto != null ? this.impuestoNeto : Constantes.ZEROS_DOUBLE;
					}
				}

			}
		}
		resultaMontoImpuesto = this.impuestoMonto == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto;
		resultaMontoImpuesto += this.impuestoMonto1 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto1;
		resultaMontoImpuesto += this.impuestoMonto2 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto2;
		resultaMontoImpuesto += this.impuestoMonto3 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto3;
		resultaMontoImpuesto += this.impuestoMonto4 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto4;
		resultaMontoImpuesto += this.impuestoMonto5 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto5;
		resultaMontoImpuesto += this.impuestoMonto6 == null ? Constantes.ZEROS_DOUBLE : this.impuestoMonto6;
		if (this.recepcionFactura.getTipoDoc() != null) {
			if (this.recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				return resultaMontoImpuesto != null ? resultaMontoImpuesto * -1 : resultaMontoImpuesto;
			} else {
				return resultaMontoImpuesto != null ? resultaMontoImpuesto : Constantes.ZEROS_DOUBLE;
			}
		}
		return Constantes.ZEROS_DOUBLE;

	}

	public Double getMontoLineaSTRTimpoCambio() {
		Double resultadoTipoCambio = this.recepcionFactura.getFacturaTipoCambio() == null ? 1 : this.recepcionFactura.getFacturaTipoCambio();
		if (this.getMontoTotalLinea() != null) {
			if (this.getMontoTotalLinea() > Constantes.ZEROS_DOUBLE) {
				if (this.recepcionFactura.getTipoDoc() != null) {
					if (this.recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
						return this.getMontoTotalLinea() != null ? (this.getMontoTotalLinea() * resultadoTipoCambio) * -1 : (this.getMontoTotalLinea() * resultadoTipoCambio);
					} else {
						return this.getMontoTotalLinea() != null ? (this.getMontoTotalLinea() * resultadoTipoCambio) : Constantes.ZEROS_DOUBLE;
					}
				}
			}
		}

		return Constantes.ZEROS_DOUBLE;

	}

	public Double getMontoLineaSTR() {
		if (this.getMontoTotalLinea() != null) {
			if (this.getMontoTotalLinea() > Constantes.ZEROS_DOUBLE) {
				if (this.recepcionFactura.getTipoDoc() != null) {
					if (this.recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
						return this.getMontoTotalLinea() != null ? this.getMontoTotalLinea() * -1 : this.getMontoTotalLinea();
					} else {
						return this.getMontoTotalLinea() != null ? this.getMontoTotalLinea() : Constantes.ZEROS_DOUBLE;
					}
				}

			}
		}

		return Constantes.ZEROS_DOUBLE;

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

	public String getImpuestoCodigo1() {
		return impuestoCodigo1;
	}

	public void setImpuestoCodigo1(String impuestoCodigo1) {
		this.impuestoCodigo1 = impuestoCodigo1;
	}

	public String getImpuestoCodigoTarifa1() {
		return impuestoCodigoTarifa1;
	}

	public void setImpuestoCodigoTarifa1(String impuestoCodigoTarifa1) {
		this.impuestoCodigoTarifa1 = impuestoCodigoTarifa1;
	}

	public Double getImpuestoTarifa1() {
		return impuestoTarifa1;
	}

	public void setImpuestoTarifa1(Double impuestoTarifa1) {
		this.impuestoTarifa1 = impuestoTarifa1;
	}

	public Double getImpuestoMonto1() {
		return impuestoMonto1;
	}

	public void setImpuestoMonto1(Double impuestoMonto1) {
		this.impuestoMonto1 = impuestoMonto1;
	}

	public String getImpuestoCodigo2() {
		return impuestoCodigo2;
	}

	public void setImpuestoCodigo2(String impuestoCodigo2) {
		this.impuestoCodigo2 = impuestoCodigo2;
	}

	public String getImpuestoCodigoTarifa2() {
		return impuestoCodigoTarifa2;
	}

	public void setImpuestoCodigoTarifa2(String impuestoCodigoTarifa2) {
		this.impuestoCodigoTarifa2 = impuestoCodigoTarifa2;
	}

	public Double getImpuestoTarifa2() {
		return impuestoTarifa2;
	}

	public void setImpuestoTarifa2(Double impuestoTarifa2) {
		this.impuestoTarifa2 = impuestoTarifa2;
	}

	public Double getImpuestoMonto2() {
		return impuestoMonto2;
	}

	public void setImpuestoMonto2(Double impuestoMonto2) {
		this.impuestoMonto2 = impuestoMonto2;
	}

	public String getImpuestoCodigo3() {
		return impuestoCodigo3;
	}

	public void setImpuestoCodigo3(String impuestoCodigo3) {
		this.impuestoCodigo3 = impuestoCodigo3;
	}

	public String getImpuestoCodigoTarifa3() {
		return impuestoCodigoTarifa3;
	}

	public void setImpuestoCodigoTarifa3(String impuestoCodigoTarifa3) {
		this.impuestoCodigoTarifa3 = impuestoCodigoTarifa3;
	}

	public Double getImpuestoTarifa3() {
		return impuestoTarifa3;
	}

	public void setImpuestoTarifa3(Double impuestoTarifa3) {
		this.impuestoTarifa3 = impuestoTarifa3;
	}

	public Double getImpuestoMonto3() {
		return impuestoMonto3;
	}

	public void setImpuestoMonto3(Double impuestoMonto3) {
		this.impuestoMonto3 = impuestoMonto3;
	}

	public String getImpuestoCodigo4() {
		return impuestoCodigo4;
	}

	public void setImpuestoCodigo4(String impuestoCodigo4) {
		this.impuestoCodigo4 = impuestoCodigo4;
	}

	public String getImpuestoCodigoTarifa4() {
		return impuestoCodigoTarifa4;
	}

	public void setImpuestoCodigoTarifa4(String impuestoCodigoTarifa4) {
		this.impuestoCodigoTarifa4 = impuestoCodigoTarifa4;
	}

	public Double getImpuestoTarifa4() {
		return impuestoTarifa4;
	}

	public void setImpuestoTarifa4(Double impuestoTarifa4) {
		this.impuestoTarifa4 = impuestoTarifa4;
	}

	public Double getImpuestoMonto4() {
		return impuestoMonto4;
	}

	public void setImpuestoMonto4(Double impuestoMonto4) {
		this.impuestoMonto4 = impuestoMonto4;
	}

	public String getImpuestoCodigo5() {
		return impuestoCodigo5;
	}

	public void setImpuestoCodigo5(String impuestoCodigo5) {
		this.impuestoCodigo5 = impuestoCodigo5;
	}

	public String getImpuestoCodigoTarifa5() {
		return impuestoCodigoTarifa5;
	}

	public void setImpuestoCodigoTarifa5(String impuestoCodigoTarifa5) {
		this.impuestoCodigoTarifa5 = impuestoCodigoTarifa5;
	}

	public Double getImpuestoTarifa5() {
		return impuestoTarifa5;
	}

	public void setImpuestoTarifa5(Double impuestoTarifa5) {
		this.impuestoTarifa5 = impuestoTarifa5;
	}

	public Double getImpuestoMonto5() {
		return impuestoMonto5;
	}

	public void setImpuestoMonto5(Double impuestoMonto5) {
		this.impuestoMonto5 = impuestoMonto5;
	}

	public String getImpuestoCodigo6() {
		return impuestoCodigo6;
	}

	public void setImpuestoCodigo6(String impuestoCodigo6) {
		this.impuestoCodigo6 = impuestoCodigo6;
	}

	public String getImpuestoCodigoTarifa6() {
		return impuestoCodigoTarifa6;
	}

	public void setImpuestoCodigoTarifa6(String impuestoCodigoTarifa6) {
		this.impuestoCodigoTarifa6 = impuestoCodigoTarifa6;
	}

	public Double getImpuestoTarifa6() {
		return impuestoTarifa6;
	}

	public void setImpuestoTarifa6(Double impuestoTarifa6) {
		this.impuestoTarifa6 = impuestoTarifa6;
	}

	public Double getImpuestoMonto6() {
		return impuestoMonto6;
	}

	public void setImpuestoMonto6(Double impuestoMonto6) {
		this.impuestoMonto6 = impuestoMonto6;
	}

}
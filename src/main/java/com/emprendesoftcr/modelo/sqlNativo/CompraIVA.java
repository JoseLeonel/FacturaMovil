package com.emprendesoftcr.modelo.sqlNativo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.fisco.MapEnums;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

@BaseNativeQuery(name = "compra_iva", query = "SELECT f.id,f.fecha_emision,f.emisor_correo,f.clave,f.factura_consecutivo,f.cedula_emisor,"
		+ "f.emisor_nombre,f.emisor_nombre_comercial,f.tipo_doc,codigo_moneda,tipo_cambio,\n" + 
		"IFNULL(f.total_impuestos,0) as total_impuestos,\n" + 
		"ifnull(f.fac_total_exento,0) as total_mercancias_exentas,\n" + 
		"ifnull(f.total_otros_cargos,0) as total_otros_cargos,\n" + 
		"ifnull(f.total_descuentos,0) as total_descuentos,\n" + 
		"ifnull(f.total_gravado,0) as total_mercancias_gravadas,\n" + 
		"ifnull(f.total_serv_gravados,0) as total_serv_gravados,f.tipo_gasto,\n" + 
		"ifnull(f.total_factura,0) as total_factura, \n" +
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo in('99','12','06','05','04','03','02') ) + (select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo1 in('99','12','06','05','04','03') ) as monto_iva_otros,\n" +
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa = '08' ) + (select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa1 = '08' ) as monto_iva_13,\n" + 
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa = '01' ) +(select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa1 = '01' ) as monto_iva_0 ,\n" + 
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa = '02' ) + (select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa1 = '02' ) as monto_iva_1,\n" + 
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa = '03' ) + (select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa1 = '03' ) as monto_iva_2,\n" + 
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa = '04' ) + (select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa1 = '04' ) as monto_iva_4    ,\n" + 
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa = '05' ) + (select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa1 = '05' ) as monto_trans_0    ,\n" + 
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa = '06' ) + (select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa1 = '06' ) as monto_trans_4    ,\n" + 
		"(select IFNULL(sum(d.impuesto_monto),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa = '07' ) + (select IFNULL(sum(d.impuesto_monto1),0)  FROM recepcion_factura_detalle d where  f.id = d.recepcion_factura_id and d.impuesto_codigo_tarifa1 = '07' )\n" + 
		"as monto_trans_8 \n" + 
		"from recepcion_factura f where f.empresa_id = :ID_EMPRESA and  f.fecha_emision >= :fechaInicial  and f.fecha_emision <= :fechaFinal")
@Entity
public class CompraIVA implements Serializable {
	
	

	private static final long serialVersionUID = -3573005793919882442L;

	@Id
	@Column(name = "id")
	private Long		id;

	@Column(name = "factura_consecutivo")
	private String	numeroConsecutivo;
	
	@Column(name = "cedula_emisor")
	private String	emisorCedula;
	
	@Column(name = "emisor_nombre")
	private String	emisorNombre;
	
	@Column(name = "emisor_nombre_comercial")
	private String	emisorNombreComercial;
	
	@Column(name = "tipo_doc")
	private String	emisorTipoDocumento;

	@Column(name = "total_mercancias_exentas")
	private Double	totalMercanciasExentas;
	
	@Column(name = "total_otros_cargos")
	private Double	total_otros_cargos;
	@Column(name = "total_descuentos")
	private Double	totalDescuento;

	@Column(name = "total_mercancias_gravadas")
	private Double	totalMercanciaGravada;
	
	@Column(name = "total_serv_gravados")
	private Double	totalServicioGravado;
	
	
	@Column(name = "total_factura")
	private Double	facturaTotalComprobante;
	@Column(name = "total_impuestos")
	private Double						facturaTotalImpuestos;
	
	@Column(name = "monto_iva_13")
	private Double	montoIva13;
	
	@Column(name = "monto_iva_0")
	private Double	montoIva0;
	
	@Column(name = "monto_iva_1")
	private Double	montoIva1;
	
	@Column(name = "monto_iva_2")
	private Double	montoIva2;
	
	@Column(name = "monto_iva_4")
	private Double	montoIva4;
	
	@Column(name = "monto_trans_0")
	private Double	montoTrans0;

	@Column(name = "monto_trans_4")
	private Double	montoTrans4;

	@Column(name = "monto_trans_8")
	private Double	montoTrans8;
	
	@Column(name = "monto_iva_otros")
	private Double	montoIVAOtros;
	
	
	
	@Column(name = "codigo_moneda")
	private String						facturaCodigoMoneda;

	@Column(name = "tipo_cambio")
	private Double						facturaTipoCambio;
	@Column(name = "clave")
	private String						facturaClave;
	
	@Column(name = "emisor_correo")
	private String						emisorCorreo;


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date							facturaFechaEmision;
	
	
	@Column(name = "tipo_gasto", columnDefinition = "INT default '1'")
	private Integer						tipoGasto;


	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	
	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	
	public String getEmisorCedula() {
		return emisorCedula;
	}

	
	public void setEmisorCedula(String emisorCedula) {
		this.emisorCedula = emisorCedula;
	}

	
	public String getEmisorNombre() {
		return emisorNombre;
	}

	
	public void setEmisorNombre(String emisorNombre) {
		this.emisorNombre = emisorNombre;
	}

	
	public String getEmisorNombreComercial() {
		return emisorNombreComercial;
	}

	
	public void setEmisorNombreComercial(String emisorNombreComercial) {
		this.emisorNombreComercial = emisorNombreComercial;
	}

	
	public String getEmisorTipoDocumento() {
		return emisorTipoDocumento;
	}

	
	public void setEmisorTipoDocumento(String emisorTipoDocumento) {
		this.emisorTipoDocumento = emisorTipoDocumento;
	}



	
	public Double getTotalMercanciasExentas() {
			return totalMercanciasExentas != null ? totalMercanciasExentas : Constantes.ZEROS_DOUBLE;

	}

	
	public void setTotalMercanciasExentas(Double totalMercanciasExentas) {
		this.totalMercanciasExentas = totalMercanciasExentas;
	}

	
	public Double getTotal_otros_cargos() {
			return total_otros_cargos != null ? total_otros_cargos : Constantes.ZEROS_DOUBLE;
	}

	
	public void setTotal_otros_cargos(Double total_otros_cargos) {
		this.total_otros_cargos = total_otros_cargos;
	}

	
	public Double getTotalDescuento() {
			return totalDescuento != null ? totalDescuento : Constantes.ZEROS_DOUBLE;

	}

	
	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	
	public Double getTotalMercanciaGravada() {
			return totalMercanciaGravada != null ? totalMercanciaGravada : Constantes.ZEROS_DOUBLE;

	}

	
	public void setTotalMercanciaGravada(Double totalMercanciaGravada) {
		this.totalMercanciaGravada = totalMercanciaGravada;
	}

	
	public Double getTotalServicioGravado() {
			return totalServicioGravado != null ? totalServicioGravado : Constantes.ZEROS_DOUBLE;

	}

	
	public void setTotalServicioGravado(Double totalServicioGravado) {
		this.totalServicioGravado = totalServicioGravado;
	}

	
	

	
	public Double getMontoIva13() {
			return montoIva13 != null ? montoIva13 : Constantes.ZEROS_DOUBLE;

	}

	
	public void setMontoIva13(Double montoIva13) {
		this.montoIva13 = montoIva13;
	}

	
	public Double getMontoIva0() {
			return montoIva0 != null ? montoIva0 : Constantes.ZEROS_DOUBLE;

	}

	
	public void setMontoIva0(Double montoIva0) {
		this.montoIva0 = montoIva0;
	}

	
	public Double getMontoIva1() {
			return montoIva1 != null ? montoIva1 : Constantes.ZEROS_DOUBLE;

	}

	
	public void setMontoIva1(Double montoIva1) {
		this.montoIva1 = montoIva1;
	}

	
	public Double getMontoIva2() {
			return montoIva2 != null ? montoIva2 : Constantes.ZEROS_DOUBLE;

	}

	
	public void setMontoIva2(Double montoIva2) {
		this.montoIva2 = montoIva2;
	}

	
	public Double getMontoIva4() {
			return montoIva4 != null ? montoIva4 : Constantes.ZEROS_DOUBLE;

	}

	
	public void setMontoIva4(Double montoIva4) {
		this.montoIva4 = montoIva4;
	}

	
	public Double getMontoTrans0() {
			return montoTrans0 != null ? montoTrans0 : Constantes.ZEROS_DOUBLE;

	}

	
	public void setMontoTrans0(Double montoTrans0) {
		this.montoTrans0 = montoTrans0;
	}

	
	public Double getMontoTrans4() {
			return montoTrans4 != null ? montoTrans4 : Constantes.ZEROS_DOUBLE;
	}

	
	public void setMontoTrans4(Double montoTrans4) {
		this.montoTrans4 = montoTrans4;
	}

	
	public Double getMontoTrans8() {
			return montoTrans8 != null ? montoTrans8 : Constantes.ZEROS_DOUBLE;

	}

	
	public void setMontoTrans8(Double montoTrans8) {
		this.montoTrans8 = montoTrans8;
	}


	
	public String getFacturaCodigoMoneda() {
		return facturaCodigoMoneda;
	}


	
	public void setFacturaCodigoMoneda(String facturaCodigoMoneda) {
		this.facturaCodigoMoneda = facturaCodigoMoneda;
	}


	
	public Double getFacturaTipoCambio() {
		return facturaTipoCambio;
	}


	
	public void setFacturaTipoCambio(Double facturaTipoCambio) {
		this.facturaTipoCambio = facturaTipoCambio;
	}

	public String getFechaEmisionSTR() {
		if (this.facturaFechaEmision != null) {
			return Utils.getFechaGeneraReporte(this.getFacturaFechaEmision());
		}
		return Constantes.EMPTY;
	}


	
	public String getFacturaClave() {
		return facturaClave;
	}


	
	public void setFacturaClave(String facturaClave) {
		this.facturaClave = facturaClave;
	}


	
	

	
	public Date getFacturaFechaEmision() {
		return facturaFechaEmision;
	}


	
	public void setFacturaFechaEmision(Date facturaFechaEmision) {
		this.facturaFechaEmision = facturaFechaEmision;
	}


	
	public String getEmisorCorreo() {
		return emisorCorreo;
	}


	
	public void setEmisorCorreo(String emisorCorreo) {
		this.emisorCorreo = emisorCorreo;
	}
	
	
	public String getTipoDocumentoStr() {
		return Utils.obtenerDescripcionTipoDocumento(this.emisorTipoDocumento);
	}


	
	public Double getFacturaTotalComprobante() {
		return facturaTotalComprobante;
	}


	
	public void setFacturaTotalComprobante(Double facturaTotalComprobante) {
		this.facturaTotalComprobante = facturaTotalComprobante;
	}


	
	public Double getFacturaTotalImpuestos() {
		return facturaTotalImpuestos;
	}


	
	public void setFacturaTotalImpuestos(Double facturaTotalImpuestos) {
		this.facturaTotalImpuestos = facturaTotalImpuestos;
	}

	public Double getTotalImpuestosSTR() {
			return this.facturaTotalImpuestos != null ? this.facturaTotalImpuestos : Constantes.ZEROS_DOUBLE;

	}

	public Double getTotalImpuestosSTRTipoCambio() {
		Double resultadoTipoCambio = this.facturaTipoCambio == null ? 1 : this.facturaTipoCambio;
		if (emisorTipoDocumento != null && emisorTipoDocumento.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.facturaTotalImpuestos != null ? (this.facturaTotalImpuestos * resultadoTipoCambio) * -1 : (this.facturaTotalImpuestos * resultadoTipoCambio);
		} else {
			return this.facturaTotalImpuestos != null ? (this.facturaTotalImpuestos * resultadoTipoCambio) : Constantes.ZEROS_DOUBLE;
		}

	}

	public Double getTotalFacturaSTR() {
		if (emisorTipoDocumento != null && emisorTipoDocumento.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.facturaTotalComprobante != null ? this.facturaTotalComprobante * -1 : this.facturaTotalComprobante;
		} else {
			return this.facturaTotalComprobante != null ? this.facturaTotalComprobante : Constantes.ZEROS_DOUBLE;
		}
	}

	public Double getTotalFacturaSTRTipoCambio() {
		Double resultadoTipoCambio = this.facturaTipoCambio == null ? 1 : this.facturaTipoCambio;
		if (emisorTipoDocumento != null && emisorTipoDocumento.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.facturaTotalComprobante != null ? (this.facturaTotalComprobante * resultadoTipoCambio) * -1 : (this.facturaTotalComprobante * resultadoTipoCambio);
		} else {
			return this.facturaTotalComprobante != null ? (this.facturaTotalComprobante * resultadoTipoCambio) : Constantes.ZEROS_DOUBLE;
		}
	}

	

	public Double getMontoIVAOtros() {
		return montoIVAOtros;
	}


	
	public void setMontoIVAOtros(Double montoIVAOtros) {
		this.montoIVAOtros = montoIVAOtros;
	}


	
	public Integer getTipoGasto() {
		return tipoGasto;
	}


	
	public void setTipoGasto(Integer tipoGasto) {
		this.tipoGasto = tipoGasto;
	}
	
	public String getTipoGastoStr() {
		return MapEnums.ENUM_TIPO_GASTO.get(this.tipoGasto.toString());
	}

	
}

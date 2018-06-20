package com.factura.FacturaElectronica.web.command;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.Utils.Utils;
import com.factura.FacturaElectronica.modelo.Compra;
import com.factura.FacturaElectronica.modelo.DetalleCompra;
import com.factura.FacturaElectronica.modelo.Proveedor;

/**
 * Espera de las solicitudes que no han ingresado al inventario CompraEsperaCommand.
 * @author jose.
 * @since 29 may. 2018
 */
public class CompraEsperaCommand {

	private Integer							id;

	private String							consecutivo;

	private Date								fechaCredito;

	private Date								fechaPago;

	private Date								fechaCompra;

	private Date								fechaIngreso;

	private Integer							tipoDocumento;

	private Integer							formaPago;
	private BigDecimal					totalImpuesto;
	private BigDecimal					totalDescuento;
	private BigDecimal					subTotal;

	private BigDecimal					totalCompra;

	private String							nota;
	private String							estadoSTR;

	private Integer							estado;
	private Proveedor						proveedor;

	private Set<DetalleCompra>	detalleCompras;

	public CompraEsperaCommand(Compra compra) {
		super();
		this.id = compra.getId();
		this.consecutivo = compra.getConsecutivo();
		this.fechaCredito = compra.getFechaPago();
		this.fechaPago = compra.getFechaPago();
		this.fechaCompra = compra.getFechaCompra();
		this.fechaIngreso = compra.getFechaIngreso();
		this.tipoDocumento = compra.getTipoDocumento();
		this.formaPago = compra.getFormaPago();
		this.totalCompra = compra.getTotalCompra();
		this.nota = compra.getNota();
		this.estado = compra.getEstado();
		this.proveedor = compra.getProveedor();
		this.detalleCompras = compra.getDetalleCompras();
		this.totalImpuesto = compra.getTotalImpuesto();
		this.totalDescuento = compra.getTotalDescuento();
		this.subTotal = compra.getSubTotal();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Date getFechaCredito() {
		return fechaCredito;
	}

	public void setFechaCredito(Date fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Set<DetalleCompra> getDetalleCompras() {
		return detalleCompras;
	}

	public void setDetalleCompras(Set<DetalleCompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public BigDecimal getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(BigDecimal totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public BigDecimal getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(BigDecimal totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}

	public String getEstadoSTR() {
		return estadoSTR;
	}

	public void setEstadoSTR(String estadoSTR) {
		this.estadoSTR = estadoSTR;
	}

	public String getDescripcionEstado() {
		if (this.estado.equals(Constantes.COMPRA_ESTADO_PENDIENTE)) {
			return Constantes.COMPRA_ESTADO_PENDIENTE_STR;
		}

		if (this.estado.equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
			return Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO_STR;
		}

		if (this.estado.equals(Constantes.COMPRA_ESTADO_CHEQUEDO_MERCANCIA)) {
			return Constantes.COMPRA_ESTADO_CHEQUEDO_MERCANCIA_STR;
		}
		if (this.estado.equals(Constantes.COMPRA_ESTADO_ANULADA)) {
			return Constantes.COMPRA_ESTADO_ANULADA_STR;
		}

		return Constantes.BLANK;

	}

	public String getDescripcionTipoDocumento() {
		if (this.formaPago.equals(Constantes.COMPRA_TIPO_DOCUMENTO_FACTURA)) {
			return Constantes.COMPRA_TIPO_DOCUMENTO_FACTURA_STR;
		}
		if (this.formaPago.equals(Constantes.COMPRA_TIPO_DOCUMENTO_BOLETA)) {
			return Constantes.COMPRA_TIPO_DOCUMENTO_FACTURA_STR;
		}
		return Constantes.BLANK;
	}

	public String getDescripcionFormaPago() {
		if (this.formaPago.equals(Constantes.COMPRA_FORMA_PAGO_CONTADO)) {
			return Constantes.COMPRA_FORMA_PAGO_CONTADO_STR;
		}
		if (this.formaPago.equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
			return Constantes.COMPRA_FORMA_PAGO_CREDITO_STR;
		}
		return Constantes.BLANK;
	}

	public String getFormatoCompraTotal() {
		return Utils.formatearNumeroListados(this.totalCompra, 2);
	}

}

package com.emprendesoftcr.web.command;

import java.util.Date;
import java.util.Set;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Espera de las solicitudes que no han ingresado al inventario CompraEsperaCommand.
 * @author jose.
 * @since 29 may. 2018
 */
public class CompraEsperaCommand {

	private Long								id;

	private String							consecutivo;

	private Date								fechaCredito;

	private Date								fechaPago;

	private Date								fechaCompra;

	private Date								fechaIngreso;

	private String							fechaPagoSTR;

	private String							fechaCompraSTR;

	private String							fechaIngresoSTR;
	private String							fechaCreditoSTR;

	private Integer							tipoDocumento;

	private Integer							formaPago;
	private Double							totalImpuesto;
	private Double							totalDescuento;
	private Double							subTotal;
	private String							totalImpuestoSTR;
	private String							totalDescuentoSTR;
	private String							subTotalSTR;
	private String							totalCompraSTR;
	private Double							totalCompra;

	private String							nota;
	private String							estadoSTR;

	private Integer							estado;
	private Proveedor						proveedor;
	private Usuario							usuarioIngresoInventario;

	private Set<DetalleCompra>	detalleCompras;

	public CompraEsperaCommand(Compra compra) {
		super();
		this.id = compra.getId();
		this.consecutivo = compra.getConsecutivo();
		this.fechaCredito = compra.getFechaCredito();
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
		this.fechaCompraSTR = compra.getFechaCompraSTR();
		this.fechaIngresoSTR = compra.getFechaIngresoSTR();
		this.fechaCreditoSTR = compra.getFechaCreditoSTR();
		this.fechaPagoSTR = compra.getFechaPagoSTR();
		this.totalDescuentoSTR = compra.getTotalDescuentoSTR();
		this.totalImpuestoSTR = compra.getTotalImpuestoSTR();
		this.subTotalSTR = compra.getSubTotalSTR();
		this.totalCompraSTR = compra.getTotalCompraSTR();
		this.usuarioIngresoInventario = compra.getUsuarioIngresoInventario();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarioIngresoInventario() {
		return usuarioIngresoInventario;
	}

	public void setUsuarioIngresoInventario(Usuario usuarioIngresoInventario) {
		this.usuarioIngresoInventario = usuarioIngresoInventario;
	}

	public String getTotalCompraSTR() {
		return totalCompraSTR;
	}

	public void setTotalCompraSTR(String totalCompraSTR) {
		this.totalCompraSTR = totalCompraSTR;
	}

	public String getTotalImpuestoSTR() {
		return totalImpuestoSTR;
	}

	public void setTotalImpuestoSTR(String totalImpuestoSTR) {
		this.totalImpuestoSTR = totalImpuestoSTR;
	}

	public String getTotalDescuentoSTR() {
		return totalDescuentoSTR;
	}

	public void setTotalDescuentoSTR(String totalDescuentoSTR) {
		this.totalDescuentoSTR = totalDescuentoSTR;
	}

	public String getSubTotalSTR() {
		return subTotalSTR;
	}

	public void setSubTotalSTR(String subTotalSTR) {
		this.subTotalSTR = subTotalSTR;
	}

	public String getFechaPagoSTR() {
		return fechaPagoSTR;
	}

	public String getFechaCreditoSTR() {
		return fechaCreditoSTR;
	}

	public void setFechaCreditoSTR(String fechaCreditoSTR) {
		this.fechaCreditoSTR = fechaCreditoSTR;
	}

	public void setFechaPagoSTR(String fechaPagoSTR) {
		this.fechaPagoSTR = fechaPagoSTR;
	}

	public String getFechaCompraSTR() {
		return fechaCompraSTR;
	}

	public void setFechaCompraSTR(String fechaCompraSTR) {
		this.fechaCompraSTR = fechaCompraSTR;
	}

	public String getFechaIngresoSTR() {
		return fechaIngresoSTR;
	}

	public void setFechaIngresoSTR(String fechaIngresoSTR) {
		this.fechaIngresoSTR = fechaIngresoSTR;
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

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Double totalCompra) {
		this.totalCompra = totalCompra;
	}

}

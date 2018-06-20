package com.factura.FacturaElectronica.web.command;

import java.math.BigDecimal;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Proveedor;
import com.factura.FacturaElectronica.modelo.Usuario;

public class CompraCommand {

	private Integer		id;
	private String		consecutivo;

	private String		fechaCredito;

	private String		fechaCompra;

	private Integer		tipoDocumento;

	private Integer		formaPago;

	private BigDecimal		totalImpuesto;

	private BigDecimal		totalDescuento;
	private BigDecimal		subTotal;

	private BigDecimal		totalCompra;

	private String		nota;

	private Integer		estado;

	private Proveedor	proveedor;

	private String		detalleCompra;

	private Empresa		empresa;
	private Usuario		usuarioCreacion;

	public CompraCommand() {
		super();

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

	
	public String getFechaCredito() {
		return fechaCredito;
	}

	
	public void setFechaCredito(String fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	
	public String getFechaCompra() {
		return fechaCompra;
	}

	
	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
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

	
	public Proveedor getProveedor() {
		return proveedor;
	}

	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	
	public String getDetalleCompra() {
		return detalleCompra;
	}

	
	public void setDetalleCompra(String detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	
	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	

	
}
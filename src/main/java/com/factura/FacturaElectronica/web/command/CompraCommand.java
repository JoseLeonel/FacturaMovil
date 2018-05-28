package com.factura.FacturaElectronica.web.command;

import java.util.Date;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Proveedor;
import com.factura.FacturaElectronica.modelo.Usuario;

public class CompraCommand {

	private String		consecutivo;

	private Date			fechaCredito;

	
	private Date			fechaCompra;

	private Integer		tipoDocumento;

	private Integer		formaPago;

	private Double		totalImpuesto;

	private Double		totalDescuento;

	private Double		totalCompra;

	private String		nota;

	private Integer		estado;

	private Proveedor	proveedor;

	private String		detalleCompra;
	
	private Empresa empresa;
	private Usuario usuarioCreacion;

	
	
	
	public CompraCommand(String consecutivo, Date fechaCredito, Date fechaCompra, Integer tipoDocumento, Integer formaPago, Double totalImpuesto, Double totalDescuento, Double totalCompra, String nota, Integer estado, Proveedor proveedor, String detalleCompra,Empresa empresa, Usuario usuarioCreacion) {
		super();
		this.consecutivo = consecutivo;
		this.fechaCredito = fechaCredito == null?new Date():fechaCredito;
		this.fechaCompra = fechaCompra;
		this.tipoDocumento = tipoDocumento;
		this.formaPago = formaPago;
		this.totalImpuesto = totalImpuesto;
		this.totalDescuento = totalDescuento;
		this.totalCompra = totalCompra;
		this.nota = nota;
		this.estado = estado;
		this.proveedor = proveedor;
		this.detalleCompra = detalleCompra;
		this.empresa = empresa;
		this.usuarioCreacion = usuarioCreacion;
	}

	public CompraCommand() {
		super();
		
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

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
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

	public Double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Double totalCompra) {
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

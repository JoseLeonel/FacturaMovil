package com.factura.FacturaElectronica.web.command;

import java.util.Date;
import java.util.Set;

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

	private Double							totalCompra;

	private String							nota;

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
	
	

}

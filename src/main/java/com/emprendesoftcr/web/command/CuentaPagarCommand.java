package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.Usuario;

public class CuentaPagarCommand {

	private Long			id;

	private String		consecutivo;

	private Double		total;

	private Double		totalAbono;

	private Double		totalSaldo;

	private Date			fechaCredito;
	private String		fechaCreditoSTR;

	private Date			created_at;

	private Date			updated_at;

	private Proveedor	proveedor;

	private Empresa		empresa;

	private Usuario		usuarioCreacion;

	private Integer		estado;

	public CuentaPagarCommand(CuentaPagar cuentaPagar) {
		super();
		this.id = cuentaPagar.getId();
		this.proveedor = cuentaPagar.getProveedor();
		this.usuarioCreacion = cuentaPagar.getUsuarioCreacion();
		this.fechaCredito = cuentaPagar.getFechaCredito();
		this.fechaCreditoSTR = Utils.getFechaGeneraReporte(cuentaPagar.getFechaCredito());
		this.estado = cuentaPagar.getEstado();
		this.total = cuentaPagar.getTotal();
		this.totalAbono = cuentaPagar.getTotalAbono();
		this.totalSaldo = cuentaPagar.getTotalSaldo();
		this.estado = cuentaPagar.getEstado();
		this.created_at = cuentaPagar.getCreated_at();
		this.updated_at = cuentaPagar.getUpdated_at();
		this.empresa = cuentaPagar.getEmpresa();
	}

	public CuentaPagarCommand() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalAbono() {
		return totalAbono;
	}

	public void setTotalAbono(Double totalAbono) {
		this.totalAbono = totalAbono;
	}

	public Double getTotalSaldo() {
		return totalSaldo;
	}

	public void setTotalSaldo(Double totalSaldo) {
		this.totalSaldo = totalSaldo;
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getFechaCreditoSTR() {
		return fechaCreditoSTR;
	}

	public void setFechaCreditoSTR(String fechaCreditoSTR) {
		this.fechaCreditoSTR = fechaCreditoSTR;
	}

}

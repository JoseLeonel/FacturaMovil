package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.sqlNativo.GraficoCuentasPorPagarNative;
import com.emprendesoftcr.utils.Utils;

public class CuentaPagarCommand {

	private Long			id;

	private String		consecutivo;

	private Double		total;

	private Double		totalAbono;

	private Double		totalSaldo;

	private String		totalSTR;

	private String		totalAbonoSTR;

	private String		totalSaldoSTR;

	private Date			fechaCredito;
	private String		fechaCreditoSTR;
	private String		created_atSTR;
	private String		updated_atSTR;

	private Date			created_at;

	private Date			updated_at;

	private Proveedor	proveedor;

	private Empresa		empresa;

	private Usuario		usuarioCreacion;
	private String nombreCompleto;

	private String		estado;
	
	private Integer totalDiasMoroso;

	public CuentaPagarCommand(CuentaPagar cuentaPagar) {
		super();
		this.id = cuentaPagar.getId();
		this.proveedor = cuentaPagar.getProveedor();
		this.usuarioCreacion = cuentaPagar.getUsuarioCreacion();
		this.fechaCredito = cuentaPagar.getFechaCredito();
		this.fechaCreditoSTR = Utils.getFechaGeneraReporte(cuentaPagar.getFechaCredito());
		this.created_atSTR = Utils.getFechaGeneraReporte(cuentaPagar.getCreated_at());
		this.updated_atSTR = Utils.getFechaGeneraReporte(cuentaPagar.getUpdated_at());
		this.estado = cuentaPagar.getEstado();
		this.total = cuentaPagar.getTotal();
		this.totalAbono = cuentaPagar.getTotalAbono();
		this.totalSaldo = cuentaPagar.getTotalSaldo();
		this.estado = cuentaPagar.getEstado();
		this.created_at = cuentaPagar.getCreated_at();
		this.updated_at = cuentaPagar.getUpdated_at();
		this.empresa = cuentaPagar.getEmpresa();
		this.totalAbonoSTR = cuentaPagar.getTotalAbonoSTR();
		this.totalSaldoSTR = cuentaPagar.getTotalSaldoSTR();
		this.totalSTR = cuentaPagar.getTotalSTR();
		this.consecutivo = cuentaPagar.getConsecutivo();
	}
	
	

	public CuentaPagarCommand( GraficoCuentasPorPagarNative graficoCuentasPorPagarNative) {
		super();
		this.id = graficoCuentasPorPagarNative.getId();
		this.consecutivo = graficoCuentasPorPagarNative.getConsecutivo();
		this.totalSaldo = graficoCuentasPorPagarNative.getTotalSaldo();
		this.totalDiasMoroso = graficoCuentasPorPagarNative.getTotalDiasMorosos();
		this.fechaCredito = graficoCuentasPorPagarNative.getFechaCredito();
		this.nombreCompleto = graficoCuentasPorPagarNative.getNombreCompleto();
		this.fechaCreditoSTR = Utils.getFechaStr(graficoCuentasPorPagarNative.getFechaCredito());
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

	
	
	public Integer getTotalDiasMoroso() {
		return totalDiasMoroso;
	}



	
	public void setTotalDiasMoroso(Integer totalDiasMoroso) {
		this.totalDiasMoroso = totalDiasMoroso;
	}



	public String getNombreCompleto() {
		return nombreCompleto;
	}



	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}



	public String getTotalSTR() {
		return totalSTR;
	}

	public void setTotalSTR(String totalSTR) {
		this.totalSTR = totalSTR;
	}

	public String getTotalAbonoSTR() {
		return totalAbonoSTR;
	}

	public void setTotalAbonoSTR(String totalAbonoSTR) {
		this.totalAbonoSTR = totalAbonoSTR;
	}

	public String getTotalSaldoSTR() {
		return totalSaldoSTR;
	}

	public void setTotalSaldoSTR(String totalSaldoSTR) {
		this.totalSaldoSTR = totalSaldoSTR;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaCreditoSTR() {
		return fechaCreditoSTR;
	}

	public void setFechaCreditoSTR(String fechaCreditoSTR) {
		this.fechaCreditoSTR = fechaCreditoSTR;
	}

	public String getCreated_atSTR() {
		return created_atSTR;
	}

	public void setCreated_atSTR(String created_atSTR) {
		this.created_atSTR = created_atSTR;
	}

	public String getUpdated_atSTR() {
		return updated_atSTR;
	}

	public void setUpdated_atSTR(String updated_atSTR) {
		this.updated_atSTR = updated_atSTR;
	}

}

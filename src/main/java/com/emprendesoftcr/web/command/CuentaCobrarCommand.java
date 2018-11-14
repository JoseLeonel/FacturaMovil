package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Vendedor;

public class CuentaCobrarCommand {

	private Long		id;

	private String		recibo;

	private String		letraCambio;

	private String		factura;

	private Integer		facturaManual;

	private Double		totalComision;

	private Double		descuento;

	private Double		cantidadPagos;

	private Double		montoCouta;

	private Double		total;

	private Double		totalAbono;

	private Double		totalSaldo;
	
	private String		totalSTR;

	private String		totalAbonoSTR;

	private String		totalSaldoSTR;

	private String		descripcionArticulo;

	private String		nota;

	private String		tipo;

	private String		estado;
	private String		codigoMoneda;

	private Date			fechaPlazo;

	private Date			fechaEntrega;

	private Date			created_at;

	private Date			updated_at;

	private String		created_atSTR;
	private String		updated_atSTR;
	
	private Integer plazoCredito;

	private Cliente		cliente;
	private Vendedor	vendedor;

	private Empresa		empresa;

	public CuentaCobrarCommand(CuentaCobrar cuentaCobrar) {
		super();
		this.id = cuentaCobrar.getId();
		this.recibo = cuentaCobrar.getRecibo();
		this.letraCambio = cuentaCobrar.getLetraCambio();
		this.factura = cuentaCobrar.getFactura();
		this.facturaManual = cuentaCobrar.getFacturaManual();
		this.totalComision = cuentaCobrar.getTotalComision();
		this.descuento = cuentaCobrar.getDescuento();
		this.cantidadPagos = cuentaCobrar.getCantidadPagos();
		this.montoCouta = cuentaCobrar.getMontoCouta();
		this.total = cuentaCobrar.getTotal();
		this.totalAbono = cuentaCobrar.getTotalAbono();
		this.totalSaldo = cuentaCobrar.getTotalSaldo();
		this.descripcionArticulo = cuentaCobrar.getDescripcionArticulo();
		this.nota = cuentaCobrar.getNota();
		this.tipo = cuentaCobrar.getTipo();
		this.estado = cuentaCobrar.getEstado();
		this.fechaPlazo = cuentaCobrar.getFechaPlazo();
		this.fechaEntrega = cuentaCobrar.getFechaEntrega();
		this.created_at = cuentaCobrar.getCreated_at();
		this.updated_at = cuentaCobrar.getUpdated_at();
		this.cliente = cuentaCobrar.getCliente();
		this.vendedor = cuentaCobrar.getVendedor();
		this.empresa = cuentaCobrar.getEmpresa();
		this.created_atSTR = Utils.getFechaGeneraReporte(cuentaCobrar.getCreated_at());
		this.updated_atSTR = Utils.getFechaGeneraReporte(cuentaCobrar.getUpdated_at());
		this.plazoCredito = cuentaCobrar.getPlazoCredito();
		this.totalAbonoSTR = cuentaCobrar.getTotalAbonoSTR();
		this.totalSaldoSTR = cuentaCobrar.getTotalSaldoSTR();
		this.totalSTR = cuentaCobrar.getTotalSTR();
		this.codigoMoneda =cuentaCobrar.getCodigoMoneda();
	}

	public CuentaCobrarCommand() {
		super();
	}

	

	
	public Long getId() {
		return id;
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

	public Integer getPlazoCredito() {
		return plazoCredito;
	}

	
	public void setPlazoCredito(Integer plazoCredito) {
		this.plazoCredito = plazoCredito;
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecibo() {
		return recibo;
	}

	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}

	public String getLetraCambio() {
		return letraCambio;
	}

	public void setLetraCambio(String letraCambio) {
		this.letraCambio = letraCambio;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public Integer getFacturaManual() {
		return facturaManual;
	}

	public void setFacturaManual(Integer facturaManual) {
		this.facturaManual = facturaManual;
	}

	public String getDescripcionArticulo() {
		return descripcionArticulo;
	}

	public void setDescripcionArticulo(String descripcionArticulo) {
		this.descripcionArticulo = descripcionArticulo;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaPlazo() {
		return fechaPlazo;
	}

	public void setFechaPlazo(Date fechaPlazo) {
		this.fechaPlazo = fechaPlazo;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getTotalComision() {
		return totalComision;
	}

	public void setTotalComision(Double totalComision) {
		this.totalComision = totalComision;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getCantidadPagos() {
		return cantidadPagos;
	}

	public void setCantidadPagos(Double cantidadPagos) {
		this.cantidadPagos = cantidadPagos;
	}

	public Double getMontoCouta() {
		return montoCouta;
	}

	public void setMontoCouta(Double montoCouta) {
		this.montoCouta = montoCouta;
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

	
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	

}

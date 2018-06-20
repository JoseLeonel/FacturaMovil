package com.factura.FacturaElectronica.web.command;

import java.math.BigDecimal;
import java.util.Date;

import com.factura.FacturaElectronica.modelo.Cliente;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.modelo.Vendedor;

public class FacturaEsperaCommand {

	private Integer		id;

	private Date			fechaCredito;

	private String		numeroConsecutivo;
	                  

	private Date			fechaEmision;

	private String		condicionVenta;

	private Integer		plazoCredito;

	private String		tipoDoc;

	private String		medioPago;

	private String		nombreFactura;

	private String		direccion;

	private String		nota;

	private String		comanda;

	private BigDecimal		subTotal;

	private BigDecimal		totalTransporte;

	private BigDecimal		total;

	private BigDecimal		totalServGravados;

	private BigDecimal		totalServExentos;

	private BigDecimal		totalMercanciasGravadas;

	private BigDecimal		totalMercanciasExentas;

	private BigDecimal		totalGravado;
	private BigDecimal		totalExento;

	private BigDecimal		totalVenta;

	private BigDecimal		totalDescuentos;
	private BigDecimal		totalVentaNeta;

	private BigDecimal		totalImpuesto;

	private BigDecimal		totalComprobante;

	private BigDecimal		totalEfectivo;

	private BigDecimal		totalTarjeta;

	private BigDecimal		totalBanco;

	private BigDecimal		totalCredito;

	private BigDecimal		montoCambio;

	private BigDecimal		totalCambio;

	private BigDecimal		totalCambioPagar;

	private String		codigoMoneda;

	private Integer		estado;

	private Date			created_at;

	private Date			updated_at;

	private Cliente		cliente;

	private Empresa		empresa;

	private Vendedor	vendedor;

	private Usuario		usuarioCreacion;

	public FacturaEsperaCommand(Factura factura) {
		super();
		this.id = factura.getId();
		this.fechaCredito = factura.getFechaCredito();
		this.numeroConsecutivo = factura.getNumeroConsecutivo();
		this.fechaEmision = factura.getFechaEmision();
		this.condicionVenta = factura.getCondicionVenta();
		this.plazoCredito = factura.getPlazoCredito();
		this.tipoDoc = factura.getTipoDoc();
		this.medioPago = factura.getMedioPago();
		this.nombreFactura = factura.getNombreFactura();
		this.direccion = factura.getDireccion();
		this.nota = factura.getNota();
		this.comanda = factura.getComanda();
		this.subTotal = factura.getSubTotal();
		this.totalTransporte = factura.getTotalTransporte();
		this.total = factura.getTotal();
		this.totalServGravados = factura.getTotalServGravados();
		this.totalServExentos = factura.getTotalServExentos();
		this.totalMercanciasGravadas = factura.getTotalMercanciasGravadas();
		this.totalMercanciasExentas = factura.getTotalMercanciasExentas();
		this.totalGravado = factura.getTotalGravado();
		this.totalExento = factura.getTotalExento();
		this.totalVenta = factura.getTotalVenta();
		this.totalDescuentos = factura.getTotalDescuentos();
		this.totalVentaNeta = factura.getTotalVentaNeta();
		this.totalImpuesto = factura.getTotalImpuesto();
		this.totalComprobante = factura.getTotalComprobante();
		this.totalEfectivo = factura.getTotalEfectivo();
		this.totalTarjeta = factura.getTotalTarjeta();
		this.totalBanco = factura.getTotalBanco();
		this.totalCredito = factura.getTotalCredito();
		this.montoCambio = factura.getMontoCambio();
		this.totalCambio = factura.getTotalCambio();
		this.totalCambioPagar = factura.getTotalCambioPagar();
		this.codigoMoneda = factura.getCodigoMoneda();
		this.estado = factura.getEstado();
		this.created_at = factura.getCreated_at();
		this.updated_at = factura.getUpdated_at();
		this.cliente = factura.getCliente();
		this.empresa = factura.getEmpresa();
		this.vendedor = factura.getVendedor();
		this.usuarioCreacion = factura.getUsuarioCreacion();
	}

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Date getFechaCredito() {
		return fechaCredito;
	}

	
	public void setFechaCredito(Date fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	
	public String getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	
	public void setNumeroConsecutivo(String numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	
	public String getCondicionVenta() {
		return condicionVenta;
	}

	
	public void setCondicionVenta(String condicionVenta) {
		this.condicionVenta = condicionVenta;
	}

	
	public Integer getPlazoCredito() {
		return plazoCredito;
	}

	
	public void setPlazoCredito(Integer plazoCredito) {
		this.plazoCredito = plazoCredito;
	}

	
	public String getTipoDoc() {
		return tipoDoc;
	}

	
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	
	public String getMedioPago() {
		return medioPago;
	}

	
	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}

	
	public String getNombreFactura() {
		return nombreFactura;
	}

	
	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	
	public String getDireccion() {
		return direccion;
	}

	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	public String getNota() {
		return nota;
	}

	
	public void setNota(String nota) {
		this.nota = nota;
	}

	
	public String getComanda() {
		return comanda;
	}

	
	public void setComanda(String comanda) {
		this.comanda = comanda;
	}

	
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	
	public BigDecimal getTotalTransporte() {
		return totalTransporte;
	}

	
	public void setTotalTransporte(BigDecimal totalTransporte) {
		this.totalTransporte = totalTransporte;
	}

	
	public BigDecimal getTotal() {
		return total;
	}

	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
	public BigDecimal getTotalServGravados() {
		return totalServGravados;
	}

	
	public void setTotalServGravados(BigDecimal totalServGravados) {
		this.totalServGravados = totalServGravados;
	}

	
	public BigDecimal getTotalServExentos() {
		return totalServExentos;
	}

	
	public void setTotalServExentos(BigDecimal totalServExentos) {
		this.totalServExentos = totalServExentos;
	}

	
	public BigDecimal getTotalMercanciasGravadas() {
		return totalMercanciasGravadas;
	}

	
	public void setTotalMercanciasGravadas(BigDecimal totalMercanciasGravadas) {
		this.totalMercanciasGravadas = totalMercanciasGravadas;
	}

	
	public BigDecimal getTotalMercanciasExentas() {
		return totalMercanciasExentas;
	}

	
	public void setTotalMercanciasExentas(BigDecimal totalMercanciasExentas) {
		this.totalMercanciasExentas = totalMercanciasExentas;
	}

	
	public BigDecimal getTotalGravado() {
		return totalGravado;
	}

	
	public void setTotalGravado(BigDecimal totalGravado) {
		this.totalGravado = totalGravado;
	}

	
	public BigDecimal getTotalExento() {
		return totalExento;
	}

	
	public void setTotalExento(BigDecimal totalExento) {
		this.totalExento = totalExento;
	}

	
	public BigDecimal getTotalVenta() {
		return totalVenta;
	}

	
	public void setTotalVenta(BigDecimal totalVenta) {
		this.totalVenta = totalVenta;
	}

	
	public BigDecimal getTotalDescuentos() {
		return totalDescuentos;
	}

	
	public void setTotalDescuentos(BigDecimal totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	
	public BigDecimal getTotalVentaNeta() {
		return totalVentaNeta;
	}

	
	public void setTotalVentaNeta(BigDecimal totalVentaNeta) {
		this.totalVentaNeta = totalVentaNeta;
	}

	
	public BigDecimal getTotalImpuesto() {
		return totalImpuesto;
	}

	
	public void setTotalImpuesto(BigDecimal totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	
	public BigDecimal getTotalComprobante() {
		return totalComprobante;
	}

	
	public void setTotalComprobante(BigDecimal totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	
	public BigDecimal getTotalEfectivo() {
		return totalEfectivo;
	}

	
	public void setTotalEfectivo(BigDecimal totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	
	public BigDecimal getTotalTarjeta() {
		return totalTarjeta;
	}

	
	public void setTotalTarjeta(BigDecimal totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	
	public BigDecimal getTotalBanco() {
		return totalBanco;
	}

	
	public void setTotalBanco(BigDecimal totalBanco) {
		this.totalBanco = totalBanco;
	}

	
	public BigDecimal getTotalCredito() {
		return totalCredito;
	}

	
	public void setTotalCredito(BigDecimal totalCredito) {
		this.totalCredito = totalCredito;
	}

	
	public BigDecimal getMontoCambio() {
		return montoCambio;
	}

	
	public void setMontoCambio(BigDecimal montoCambio) {
		this.montoCambio = montoCambio;
	}

	
	public BigDecimal getTotalCambio() {
		return totalCambio;
	}

	
	public void setTotalCambio(BigDecimal totalCambio) {
		this.totalCambio = totalCambio;
	}

	
	public BigDecimal getTotalCambioPagar() {
		return totalCambioPagar;
	}

	
	public void setTotalCambioPagar(BigDecimal totalCambioPagar) {
		this.totalCambioPagar = totalCambioPagar;
	}

	
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	
	public Integer getEstado() {
		return estado;
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
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

	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
	public Vendedor getVendedor() {
		return vendedor;
	}

	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	
	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	
	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	
}

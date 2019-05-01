package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.Vendedor;

public class FacturaEsperaCommand {

	private Long			id;

	private Date			fechaCredito;

	private String		numeroConsecutivo;
	
	

	private Date			fechaEmision;
	private String		fechaEmisionSTR;

	private String		condicionVenta;

	private Integer		plazoCredito;

	private String		tipoDoc;

	private String		medioEfectivo;

	private String		medioTarjeta;

	private String		medioBanco;

	private String		nombreFactura;

	private String		direccion;

	private String		nota;

	private String		comanda;

	private Double		subTotal;

	private Double		totalTransporte;

	private Double		totalServGravados;

	private Double		totalServExentos;

	private Double		totalMercanciasGravadas;

	private Double		totalMercanciasExentas;

	private Double		totalGravado;
	private Double		totalExento;

	private Double		totalVenta;

	private Double		totalDescuentos;

	private String		totalDescuentosSTR;

	private Double		totalVentaNeta;

	private Double		totalImpuesto;
	private String		totalImpuestoSTR;

	private Double		totalComprobante;
	private String		totalComprobanteSTR;

	private Double		totalEfectivo;

	private Double		totalTarjeta;

	private Double		totalBanco;

	private Double		totalCredito;

	private Double		montoCambio;

	private Double		totalCambio;

	private Double		totalCambioPagar;
	private String		impuestoServicioISSTR;
	private Double		impuestoServicioIS;

	private String		codigoMoneda;

	private Integer		estado;

	private Date			created_at;

	private Date			updated_at;

	private Cliente		cliente;

	private Empresa		empresa;

	private Vendedor	vendedor;

	private Usuario		usuarioCreacion;

	private String		referenciaTipoDoc;

	private String		referenciaNumero;

	private String		referenciaCodigo;

	private String		referenciaRazon;
	private String consecutivoProforma;
	
	

	private Date			referenciaFechaEmision;
	
	public FacturaEsperaCommand(Factura factura) {
		super();
		this.id = factura.getId();
		this.fechaCredito = factura.getFechaCredito();
		this.fechaEmisionSTR = Utils.getFechaGeneraReporte(factura.getFechaEmision());
		this.numeroConsecutivo = factura.getNumeroConsecutivo();
		this.fechaEmision = factura.getFechaEmision();
		this.condicionVenta = factura.getCondicionVenta();
		this.plazoCredito = factura.getPlazoCredito();
		this.tipoDoc = factura.getTipoDoc();
		this.medioBanco = factura.getMedioBanco();
		this.medioEfectivo = factura.getMedioEfectivo();
		this.medioTarjeta = factura.getMedioTarjeta();
		this.nombreFactura = factura.getNombreFactura();
		this.direccion = factura.getDireccion();
		this.nota = factura.getNota();
		this.comanda = factura.getComanda();
		this.subTotal = factura.getSubTotal();
		this.totalTransporte = factura.getTotalTransporte();
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
		this.referenciaTipoDoc = factura.getReferenciaTipoDoc();
		this.referenciaNumero = factura.getReferenciaNumero();
		this.referenciaCodigo = factura.getReferenciaCodigo();
		this.referenciaRazon = factura.getReferenciaRazon();
		this.referenciaFechaEmision = factura.getReferenciaFechaEmision();
		this.totalComprobanteSTR = factura.getTotalComprobanteSTR();
		this.totalImpuestoSTR = factura.getTotalImpuestoSTR();
		this.totalDescuentosSTR = factura.getTotalDescuentoSTR();
		this.consecutivoProforma = factura.getConsecutivoProforma() !=null?factura.getConsecutivoProforma():Constantes.EMPTY;
	}

	public FacturaEsperaCommand(Detalle detalle) {
		super();
		this.id = detalle.getFactura().getId();
		this.fechaCredito = detalle.getFactura().getFechaCredito();
		this.fechaEmisionSTR = Utils.getFechaGeneraReporte(detalle.getFactura().getFechaEmision());
		this.numeroConsecutivo = detalle.getFactura().getNumeroConsecutivo();
		this.fechaEmision = detalle.getFactura().getFechaEmision();
		this.condicionVenta = detalle.getFactura().getCondicionVenta();
		this.plazoCredito = detalle.getFactura().getPlazoCredito();
		this.tipoDoc = detalle.getFactura().getTipoDoc();
		this.medioBanco = detalle.getFactura().getMedioBanco();
		this.medioEfectivo = detalle.getFactura().getMedioEfectivo();
		this.medioTarjeta = detalle.getFactura().getMedioTarjeta();
		this.nombreFactura = detalle.getFactura().getNombreFactura();
		this.direccion = detalle.getFactura().getDireccion();
		this.nota = detalle.getFactura().getNota();
		this.comanda = detalle.getFactura().getComanda();
		this.subTotal = detalle.getFactura().getSubTotal();
		this.totalTransporte = detalle.getFactura().getTotalTransporte();
		this.totalServGravados = detalle.getFactura().getTotalServGravados();
		this.totalServExentos = detalle.getFactura().getTotalServExentos();
		this.totalMercanciasGravadas = detalle.getFactura().getTotalMercanciasGravadas();
		this.totalMercanciasExentas = detalle.getFactura().getTotalMercanciasExentas();
		this.totalGravado = detalle.getFactura().getTotalGravado();
		this.totalExento = detalle.getFactura().getTotalExento();
		this.totalVenta = detalle.getFactura().getTotalVenta();
		this.totalDescuentos = detalle.getFactura().getTotalDescuentos();
		this.totalVentaNeta = detalle.getFactura().getTotalVentaNeta();
		this.totalImpuesto = detalle.getFactura().getTotalImpuesto();
		this.totalComprobante = detalle.getFactura().getTotalComprobante();
		this.totalEfectivo = detalle.getFactura().getTotalEfectivo();
		this.totalTarjeta = detalle.getFactura().getTotalTarjeta();
		this.totalBanco = detalle.getFactura().getTotalBanco();
		this.totalCredito = detalle.getFactura().getTotalCredito();
		this.montoCambio = detalle.getFactura().getMontoCambio();
		this.totalCambio = detalle.getFactura().getTotalCambio();
		this.totalCambioPagar = detalle.getFactura().getTotalCambioPagar();
		this.codigoMoneda = detalle.getFactura().getCodigoMoneda();
		this.estado = detalle.getFactura().getEstado();
		this.created_at = detalle.getFactura().getCreated_at();
		this.updated_at = detalle.getFactura().getUpdated_at();
		this.cliente = detalle.getFactura().getCliente();
		this.empresa = detalle.getFactura().getEmpresa();
		this.vendedor = detalle.getFactura().getVendedor();
		this.usuarioCreacion = detalle.getFactura().getUsuarioCreacion();
		this.referenciaTipoDoc = detalle.getFactura().getReferenciaTipoDoc();
		this.referenciaNumero = detalle.getFactura().getReferenciaNumero();
		this.referenciaCodigo = detalle.getFactura().getReferenciaCodigo();
		this.referenciaRazon = detalle.getFactura().getReferenciaRazon();
		this.impuestoServicioISSTR = Utils.formateadorMiles(!detalle.getCodigo().equals(Constantes.CODIGO_ARTICULO_IMPUESTO_SERVICIO) ? Constantes.ZEROS_DOUBLE : detalle.getMontoTotalLinea());
		this.impuestoServicioIS = !detalle.getCodigo().equals(Constantes.CODIGO_ARTICULO_IMPUESTO_SERVICIO) ? Constantes.ZEROS_DOUBLE : detalle.getMontoTotalLinea();
		this.totalComprobanteSTR = detalle.getFactura().getTotalComprobanteSTR();
		this.totalImpuestoSTR = detalle.getFactura().getTotalImpuestoSTR();
		this.totalDescuentosSTR = detalle.getFactura().getTotalDescuentoSTR();

	}

	public Double getImpuestoServicioIS() {
		return impuestoServicioIS;
	}

	public String getTotalDescuentosSTR() {
		return totalDescuentosSTR;
	}

	public void setTotalDescuentosSTR(String totalDescuentosSTR) {
		this.totalDescuentosSTR = totalDescuentosSTR;
	}

	public String getTotalImpuestoSTR() {
		return totalImpuestoSTR;
	}

	public void setTotalImpuestoSTR(String totalImpuestoSTR) {
		this.totalImpuestoSTR = totalImpuestoSTR;
	}

	public String getTotalComprobanteSTR() {
		return totalComprobanteSTR;
	}

	public void setTotalComprobanteSTR(String totalComprobanteSTR) {
		this.totalComprobanteSTR = totalComprobanteSTR;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFechaEmisionSTR() {
		return fechaEmisionSTR;
	}

	public String getImpuestoServicioISSTR() {
		return impuestoServicioISSTR;
	}

	public void setImpuestoServicioISSTR(String impuestoServicioISSTR) {
		this.impuestoServicioISSTR = impuestoServicioISSTR;
	}

	public void setImpuestoServicioIS(Double impuestoServicioIS) {
		this.impuestoServicioIS = impuestoServicioIS;
	}

	public void setFechaEmisionSTR(String fechaEmisionSTR) {
		this.fechaEmisionSTR = fechaEmisionSTR;
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

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getTotalTransporte() {
		return totalTransporte;
	}

	public void setTotalTransporte(Double totalTransporte) {
		this.totalTransporte = totalTransporte;
	}

	public Double getTotalServGravados() {
		return totalServGravados;
	}

	public void setTotalServGravados(Double totalServGravados) {
		this.totalServGravados = totalServGravados;
	}

	public Double getTotalServExentos() {
		return totalServExentos;
	}

	public void setTotalServExentos(Double totalServExentos) {
		this.totalServExentos = totalServExentos;
	}

	public Double getTotalMercanciasGravadas() {
		return totalMercanciasGravadas;
	}

	public void setTotalMercanciasGravadas(Double totalMercanciasGravadas) {
		this.totalMercanciasGravadas = totalMercanciasGravadas;
	}

	public Double getTotalMercanciasExentas() {
		return totalMercanciasExentas;
	}

	public void setTotalMercanciasExentas(Double totalMercanciasExentas) {
		this.totalMercanciasExentas = totalMercanciasExentas;
	}

	public Double getTotalGravado() {
		return totalGravado;
	}

	public void setTotalGravado(Double totalGravado) {
		this.totalGravado = totalGravado;
	}

	public Double getTotalExento() {
		return totalExento;
	}

	public void setTotalExento(Double totalExento) {
		this.totalExento = totalExento;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public Double getTotalDescuentos() {
		return totalDescuentos;
	}

	public void setTotalDescuentos(Double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public Double getTotalVentaNeta() {
		return totalVentaNeta;
	}

	public void setTotalVentaNeta(Double totalVentaNeta) {
		this.totalVentaNeta = totalVentaNeta;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalComprobante() {
		return totalComprobante;
	}

	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	public Double getTotalEfectivo() {
		return totalEfectivo;
	}

	public void setTotalEfectivo(Double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	public Double getTotalTarjeta() {
		return totalTarjeta;
	}

	public void setTotalTarjeta(Double totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	public Double getTotalBanco() {
		return totalBanco;
	}

	public void setTotalBanco(Double totalBanco) {
		this.totalBanco = totalBanco;
	}

	public Double getTotalCredito() {
		return totalCredito;
	}

	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}

	public Double getMontoCambio() {
		return montoCambio;
	}

	public void setMontoCambio(Double montoCambio) {
		this.montoCambio = montoCambio;
	}

	public Double getTotalCambio() {
		return totalCambio;
	}

	public void setTotalCambio(Double totalCambio) {
		this.totalCambio = totalCambio;
	}

	public Double getTotalCambioPagar() {
		return totalCambioPagar;
	}

	public void setTotalCambioPagar(Double totalCambioPagar) {
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

	public String getReferenciaTipoDoc() {
		return referenciaTipoDoc;
	}

	public void setReferenciaTipoDoc(String referenciaTipoDoc) {
		this.referenciaTipoDoc = referenciaTipoDoc;
	}

	public String getReferenciaNumero() {
		return referenciaNumero;
	}

	public void setReferenciaNumero(String referenciaNumero) {
		this.referenciaNumero = referenciaNumero;
	}

	public String getReferenciaCodigo() {
		return referenciaCodigo;
	}

	public void setReferenciaCodigo(String referenciaCodigo) {
		this.referenciaCodigo = referenciaCodigo;
	}

	public String getReferenciaRazon() {
		return referenciaRazon;
	}

	public void setReferenciaRazon(String referenciaRazon) {
		this.referenciaRazon = referenciaRazon;
	}

	public Date getReferenciaFechaEmision() {
		return referenciaFechaEmision;
	}

	public void setReferenciaFechaEmision(Date referenciaFechaEmision) {
		this.referenciaFechaEmision = referenciaFechaEmision;
	}

	public String getMedioEfectivo() {
		return medioEfectivo;
	}

	public void setMedioEfectivo(String medioEfectivo) {
		this.medioEfectivo = medioEfectivo;
	}

	public String getMedioTarjeta() {
		return medioTarjeta;
	}

	public void setMedioTarjeta(String medioTarjeta) {
		this.medioTarjeta = medioTarjeta;
	}

	public String getMedioBanco() {
		return medioBanco;
	}

	public void setMedioBanco(String medioBanco) {
		this.medioBanco = medioBanco;
	}

	
	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	
	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}
	
	
}

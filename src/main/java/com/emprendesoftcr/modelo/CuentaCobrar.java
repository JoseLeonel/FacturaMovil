package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Constantes;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modelo de cuenta de cobrar de los clientes CuentaCobrar.
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "cuentas_cobrar")
public class CuentaCobrar implements Serializable {

	private static final long	serialVersionUID	= 6669882983177068948L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long						id;

	@Column(name = "recibo")
	private String						recibo;

	@Column(name = "letra_Cambio")
	private String						letraCambio;

	@Column(name = "factura")
	private String						factura;

	@Column(name = "factura_manual")
	private Integer						facturaManual;

	@Column(name = "total_comision")
	private Double						totalComision;

	@Column(name = "descuento")
	private Double						descuento;

	@Column(name = "cantidad_pagos")
	private Double						cantidadPagos;

	@Column(name = "monto_couta")
	private Double						montoCouta;

	@Column(name = "total")
	private Double						total;

	@Column(name = "total_abono")
	private Double						totalAbono;
	
	@Column(name = "plazo_credito")
	private Integer						plazoCredito;

	@Column(name = "total_saldo")
	private Double						totalSaldo;

	@Column(name = "descripcion_articulo")
	private String						descripcionArticulo;

	@Column(name = "nota")
	private String						nota;

	@Column(name = "tipo")
	private String						tipo;

	@Column(name = "estado")
	private String						estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_plazo")
	private Date							fechaPlazo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@Column(name = "fecha_entrega")
	private Date							fechaEntrega;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente						cliente;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Vendedor					vendedor;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "cuentas_cobrar_id", referencedColumnName = "id")
	@OrderBy("id DESC")
	private Set<Abono>				abonos;

	public CuentaCobrar() {
		super();
		this.estado = Constantes.ESTADO_ACTIVO;
		this.tipo = Constantes.CUENTA_POR_COBRAR_TIPO_Automatica;

		this.created_at = new Date();
		this.updated_at = new Date();
		this.abonos = null;

	}



	public CuentaCobrar(Long id, String recibo, String letraCambio, String factura, Integer facturaManual, Double totalComision, Double descuento, Double cantidadPagos, Double montoCouta, Double total, Double totalAbono, Double totalSaldo, String descripcionArticulo, String nota, String tipo, String estado, Date fechaPlazo, Date fechaEntrega, Date created_at, Date updated_at, Cliente cliente, Usuario usuario, Empresa empresa, Vendedor vendedor, Set<Abono> abonos,Integer plazoCredito) {
		super();
		this.id = id;
		this.recibo = recibo;
		this.letraCambio = letraCambio;
		this.factura = factura;
		this.facturaManual = facturaManual;
		this.totalComision = totalComision;
		this.descuento = descuento;
		this.cantidadPagos = cantidadPagos;
		this.montoCouta = montoCouta;
		this.total = total;
		this.totalAbono = totalAbono;
		this.totalSaldo = totalSaldo;
		this.descripcionArticulo = descripcionArticulo;
		this.nota = nota;
		this.tipo = tipo;
		this.estado = estado;
		this.fechaPlazo = fechaPlazo;
		this.fechaEntrega = fechaEntrega;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.cliente = cliente;
		this.usuario = usuario;
		this.empresa = empresa;
		this.vendedor = vendedor;
		this.abonos = abonos;
		this.plazoCredito = plazoCredito;
	}



	
	public Long getId() {
		return id;
	}



	
	public void setId(Long id) {
		this.id = id;
	}



	
	public Integer getPlazoCredito() {
		return plazoCredito;
	}



	
	public void setPlazoCredito(Integer plazoCredito) {
		this.plazoCredito = plazoCredito;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Set<Abono> getAbonos() {
		return abonos;
	}

	public void setAbonos(Set<Abono> abonos) {
		this.abonos = abonos;
	}

}
package com.factura.FacturaElectronica.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.factura.FacturaElectronica.Utils.Constantes;

/**
 * Factura de ventas a los clientes.
 * @author jose.
 * @since 22 abr. 2018
 */
@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	private static final long	serialVersionUID	= 5443162013611771917L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_credito")
	private Date							fechaCredito;

	@Column(name = "clave")
	private String						clave;

	@Column(name = "numero_consecutivo")
	private Integer						numeroConsecutivo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date							fechaEmision;

	@Column(name = "condicion_venta")
	private String						condicionVenta;

	@Column(name = "plazo_credito")
	private Integer						plazoCredito;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "medio_pago")
	private String						medioPago;

	@Column(name = "nombre_factura")
	private String						nombreFactura;

	@Column(name = "direccion")
	private String						direccion;

	@Column(name = "nota")
	private String						nota;

	@Column(name = "comanda")
	private String						comanda;

	@Column(name = "subTotal")
	private Double							subTotal;

	@Column(name = "total_transporte")
	private Double							totalTransporte;

	@Column(name = "total")
	private Double							total;

	@Column(name = "total_serv_gravados")
	private Double							totalServGravados;

	@Column(name = "total_serv_exentos")
	private Double							totalServExentos;

	@Column(name = "total_mercancias_gravadas")
	private Double							totalMercanciasGravadas;

	@Column(name = "total_mercancias_exentas")
	private Double							totalMercanciasExentas;

	@Column(name = "total_gravado")
	private Double							totalGravado;

	@Column(name = "total_exento")
	private Double							totalExento;

	@Column(name = "total_venta")
	private Double							totalVenta;

	@Column(name = "total_descuentos")
	private Double							totalDescuentos;

	@Column(name = "total_venta_neta")
	private Double							totalVentaNeta;

	@Column(name = "total_impuesto")
	private Double							totalImpuesto;

	@Column(name = "total_comprobante")
	private Double							totalComprobante;

	@Column(name = "total_efectivo")
	private Double							totalEfectivo;

	@Column(name = "total_tarjeta")
	private Double							totalTarjeta;

	@Column(name = "total_banco")
	private Double							totalBanco;

	@Column(name = "total_credito")
	private Double							totalCredito;

	@Column(name = "monto_cambio")
	private Double							montoCambio;

	@Column(name = "total_cambio")
	private Double							totalCambio;

	@Column(name = "codigo_moneda")
	private String						codigoMoneda;

	@Column(name = "estado")
	private String						estado;

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
	@JoinColumn(name = "vendedor_id")
	private Vendedor					vendedor;

	public Factura() {
		super();
		this.estado = Constantes.ESTADO_ACTIVO;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Factura(Integer id, Date fechaCredito, String clave, Integer numeroConsecutivo, Date fechaEmision, String condicionVenta, Integer plazoCredito, String tipoDoc, String medioPago, String nombreFactura, String direccion, String nota, String comanda, Double subTotal, Double totalTransporte, Double total, Double totalServGravados, Double totalServExentos, Double totalMercanciasGravadas, Double totalMercanciasExentas, Double totalGravado, Double totalExento, Double totalVenta, Double totalDescuentos, Double totalVentaNeta, Double totalImpuesto, Double totalComprobante, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double montoCambio, Double totalCambio, String codigoMoneda, String estado, Date created_at, Date updated_at, Cliente cliente,
			Vendedor vendedor) {
		super();
		this.id = id;
		this.fechaCredito = fechaCredito;
		this.clave = clave;
		this.numeroConsecutivo = numeroConsecutivo;
		this.fechaEmision = fechaEmision;
		this.condicionVenta = condicionVenta;
		this.plazoCredito = plazoCredito;
		this.tipoDoc = tipoDoc;
		this.medioPago = medioPago;
		this.nombreFactura = nombreFactura;
		this.direccion = direccion;
		this.nota = nota;
		this.comanda = comanda;
		this.subTotal = subTotal;
		this.totalTransporte = totalTransporte;
		this.total = total;
		this.totalServGravados = totalServGravados;
		this.totalServExentos = totalServExentos;
		this.totalMercanciasGravadas = totalMercanciasGravadas;
		this.totalMercanciasExentas = totalMercanciasExentas;
		this.totalGravado = totalGravado;
		this.totalExento = totalExento;
		this.totalVenta = totalVenta;
		this.totalDescuentos = totalDescuentos;
		this.totalVentaNeta = totalVentaNeta;
		this.totalImpuesto = totalImpuesto;
		this.totalComprobante = totalComprobante;
		this.totalEfectivo = totalEfectivo;
		this.totalTarjeta = totalTarjeta;
		this.totalBanco = totalBanco;
		this.totalCredito = totalCredito;
		this.montoCambio = montoCambio;
		this.totalCambio = totalCambio;
		this.codigoMoneda = codigoMoneda;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.cliente = cliente;
		this.vendedor = vendedor;
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

	
	public String getClave() {
		return clave;
	}

	
	public void setClave(String clave) {
		this.clave = clave;
	}

	
	public Integer getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	
	public void setNumeroConsecutivo(Integer numeroConsecutivo) {
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

	
	public Double getTotal() {
		return total;
	}

	
	public void setTotal(Double total) {
		this.total = total;
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

	
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	
	public String getEstado() {
		return estado;
	}

	
	public void setEstado(String estado) {
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

	
	public Vendedor getVendedor() {
		return vendedor;
	}

	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
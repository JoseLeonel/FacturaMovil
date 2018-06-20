package com.factura.FacturaElectronica.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import com.factura.FacturaElectronica.Utils.Constantes;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	@Column(name = "numero_consecutivo")
	private String						numeroConsecutivo;

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

	@Column(name = "tipo_cambio")
	private BigDecimal				tipoCambio;

	@Column(name = "subTotal")
	private BigDecimal				subTotal;

	@Column(name = "total_transporte")
	private BigDecimal				totalTransporte;

	@Column(name = "total")
	private BigDecimal				total;

	@Column(name = "total_serv_gravados")
	private BigDecimal				totalServGravados;

	@Column(name = "total_serv_exentos")
	private BigDecimal				totalServExentos;

	@Column(name = "total_mercancias_gravadas")
	private BigDecimal				totalMercanciasGravadas;

	@Column(name = "total_mercancias_exentas")
	private BigDecimal				totalMercanciasExentas;

	@Column(name = "total_gravado")
	private BigDecimal				totalGravado;

	@Column(name = "total_exento")
	private BigDecimal				totalExento;

	@Column(name = "total_venta")
	private BigDecimal				totalVenta;

	@Column(name = "total_descuentos")
	private BigDecimal				totalDescuentos;

	@Column(name = "total_venta_neta")
	private BigDecimal				totalVentaNeta;

	@Column(name = "total_impuesto")
	private BigDecimal				totalImpuesto;

	@Column(name = "total_comprobante")
	private BigDecimal				totalComprobante;

	@Column(name = "total_efectivo")
	private BigDecimal				totalEfectivo;

	@Column(name = "total_tarjeta")
	private BigDecimal				totalTarjeta;

	@Column(name = "total_banco")
	private BigDecimal				totalBanco;

	@Column(name = "total_credito")
	private BigDecimal				totalCredito;

	@Column(name = "monto_cambio")
	private BigDecimal				montoCambio;

	@Column(name = "total_cambio")
	private BigDecimal				totalCambio;

	@Column(name = "total_cambioPagar")
	private BigDecimal				totalCambioPagar;

	@Column(name = "codigo_moneda")
	private String						codigoMoneda;

	@Column(name = "estado")
	private Integer						estado;

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
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Vendedor					vendedor;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario						usuarioCreacion;

	@JsonProperty("detalles")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "factura_id", referencedColumnName = "id")
	@OrderBy("factura_id DESC")
	private Set<Detalle>			detalles;

	public Factura() {
		super();
		this.estado = Constantes.FACTURA_ESTADO_PENDIENTE;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Factura(Integer id, Date fechaCredito, String numeroConsecutivo, Date fechaEmision, String condicionVenta, Integer plazoCredito, String tipoDoc, String medioPago, String nombreFactura, String direccion, String nota, String comanda, BigDecimal tipoCambio, BigDecimal subTotal, BigDecimal totalTransporte, BigDecimal total, BigDecimal totalServGravados, BigDecimal totalServExentos, BigDecimal totalMercanciasGravadas, BigDecimal totalMercanciasExentas, BigDecimal totalGravado, BigDecimal totalExento, BigDecimal totalVenta, BigDecimal totalDescuentos, BigDecimal totalVentaNeta, BigDecimal totalImpuesto, BigDecimal totalComprobante, BigDecimal totalEfectivo, BigDecimal totalTarjeta, BigDecimal totalBanco, BigDecimal totalCredito, BigDecimal montoCambio, BigDecimal totalCambio,
			BigDecimal totalCambioPagar, String codigoMoneda, Integer estado, Date created_at, Date updated_at, Cliente cliente, Empresa empresa, Vendedor vendedor, Usuario usuarioCreacion, Set<Detalle> detalles) {
		super();
		this.id = id;
		this.fechaCredito = fechaCredito;
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
		this.tipoCambio = tipoCambio;
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
		this.totalCambioPagar = totalCambioPagar;
		this.codigoMoneda = codigoMoneda;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.cliente = cliente;
		this.empresa = empresa;
		this.vendedor = vendedor;
		this.usuarioCreacion = usuarioCreacion;
		this.detalles = detalles;
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

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
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

	public Set<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(Set<Detalle> detalles) {
		this.detalles = detalles;
	}

	

}
package com.emprendesoftcr.modelo;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Articulos relacionados a la venta Detalle.
 * @author jose.
 * @since 22 abr. 2018 Tomo la decision de hacer la relacion de articulo porque pensando en manejar la factura como un servicio aparte en el futuro dividir el proyecto en administrativo y ventas
 */

@Entity
@Table(name = "detalles")
public class Detalle implements Serializable {

	private static final long	serialVersionUID	= 5443162013611771917L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "numero_linea")
	private Integer						numeroLinea;

	@Column(name = "codigo")
	private String						codigo;

	@Column(name = "descripcion")
	private String						descripcion;

	@Column(name = "tipo_codigo")
	private String						tipoCodigo;

	@Column(name = "tipo_impuesto")
	private String						tipoImpuesto;

	@Column(name = "unidad_medida")
	private String						unidadMedida;

	@Column(name = "precio_unitario")
	private Double						precioUnitario;

	@Column(name = "cantidad")
	private Double						cantidad;

	@Column(name = "monto_total")
	private Double						montoTotal;

	@Column(name = "Monto_descuento")
	private Double						montoDescuento;

	@Column(name = "naturaleza_descuento")
	private String						naturalezaDescuento;

	@Column(name = "sub_total")
	private Double						subTotal;

	@Column(name = "impuesto")
	private Double						impuesto;

	@Column(name = "monto_impuesto")
	private Double						montoImpuesto;

	@Column(name = "monto_total_linea")
	private Double						montoTotalLinea;

	@Column(name = "ganancia")
	private Double						ganancia;

	@Column(name = "porcentaje_desc")
	private Double						porcentajeDesc;

	@Column(name = "observacion")
	private String						observacion;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@JsonIgnore
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "factura_id")
	private Factura						factura;

	@JsonIgnore
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public Detalle() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Detalle(DetalleFacturaCommand detalleFacturaCommand) {
		super();
		this.id = detalleFacturaCommand.getId();
		this.numeroLinea = detalleFacturaCommand.getNumeroLinea();
		this.precioUnitario = detalleFacturaCommand.getPrecioUnitario();
		this.cantidad = detalleFacturaCommand.getCantidad();
		this.montoTotal = detalleFacturaCommand.getMontoTotal();
		this.montoDescuento = detalleFacturaCommand.getMontoDescuento() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getMontoDescuento();
		this.naturalezaDescuento = detalleFacturaCommand.getNaturalezaDescuento() == null ? Constantes.EMPTY : detalleFacturaCommand.getNaturalezaDescuento();
		this.subTotal = detalleFacturaCommand.getSubTotal();
		this.impuesto = detalleFacturaCommand.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getImpuesto();
		this.montoImpuesto = detalleFacturaCommand.getMontoImpuesto() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getMontoImpuesto();
		this.montoTotalLinea = detalleFacturaCommand.getMontoTotalLinea();
		this.ganancia = Constantes.ZEROS_DOUBLE;
		this.porcentajeDesc = detalleFacturaCommand.getPorcentajeDesc() != null ? detalleFacturaCommand.getPorcentajeDesc() : Constantes.ZEROS_DOUBLE;
		this.descripcion = detalleFacturaCommand.getDescripcion();
		this.tipoCodigo = detalleFacturaCommand.getTipoCodigo();
		this.codigo = detalleFacturaCommand.getCodigo();
		this.unidadMedida = detalleFacturaCommand.getUnidadMedida();
		this.tipoImpuesto = detalleFacturaCommand.getTipoImpuesto() == null ? Constantes.EMPTY : detalleFacturaCommand.getTipoImpuesto();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoCodigo() {
		return tipoCodigo;
	}

	public void setTipoCodigo(String tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}

	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getPrecioUnitarioSTR() {
		return Utils.formateadorMiles(this.precioUnitario);
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getCantidadSTR() {
		return Utils.formateadorMiles(this.cantidad);
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getMontoTotalSTR() {
		return Utils.formateadorMiles(this.montoTotal);
	}

	public Double getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(Double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public String getMontoDescuentoSTR() {
		return Utils.formateadorMiles(this.montoDescuento);
	}

	public String getNaturalezaDescuento() {
		return naturalezaDescuento;
	}

	public void setNaturalezaDescuento(String naturalezaDescuento) {
		this.naturalezaDescuento = naturalezaDescuento;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public String getSubTotalSTR() {
		return Utils.formateadorMiles(this.subTotal);
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}
	public String getImpuestoSTR() {
		return Utils.formateadorMiles(this.impuesto);
	}

	public Double getMontoImpuesto() {
		return montoImpuesto;
	}

	public void setMontoImpuesto(Double montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
	}

	public String getMontoImpuestoSTR() {
		return Utils.formateadorMiles(this.montoImpuesto);
	}

	public Double getMontoTotalLinea() {
		return montoTotalLinea;
	}

	public void setMontoTotalLinea(Double montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}

	public String getMontoTotalLineaSTR() {
		return Utils.formateadorMiles(this.montoTotalLinea);
	}

	public Double getGanancia() {
		return ganancia;
	}

	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	public Double getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(Double porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
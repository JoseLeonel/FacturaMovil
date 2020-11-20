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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.DetalleCompraCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Modelo de los productos ingresados al inventario DetalleCompra.
 * @author jose.
 * @since 21 may. 2018
 */
@Entity
@Table(name = "detalles_compras")
public class DetalleCompra implements Serializable {

	private static final long	serialVersionUID	= -7379156339923371149L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "numero_linea")
	private Integer						numeroLinea;

	@Column(name = "costo")
	private Double						costo;

	@Column(name = "ganancia")
	private Double						ganancia;

	@Column(name = "precio")
	private Double						precio;

	@Column(name = "cantidad")
	private Double						cantidad;

	@Column(name = "total_impuesto")
	private Double						totalImpuesto;

	@Column(name = "total_descuento")
	private Double						totalDescuento;

	@Column(name = "impuesto")
	private Double						impuesto;

	@Column(name = "descuento")
	private Double						descuento;

	@Column(name = "monto_total_linea")
	private Double						montoTotalLinea;

	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "codigo_comercial_tipo1")
	private String						codigoComercialTipo1;

	@Column(name = "codigo_comercial_codigo1")
	private String						codigoComercial1;

	@Column(name = "codigo_comercial_tipo2")
	private String						codigoComercialTipo2;

	@Column(name = "codigo_comercial_codigo2")
	private String						codigoComercial2;

	@Column(name = "codigo_comercial_tipo3")
	private String						codigoComercialTipo3;

	@Column(name = "codigo_comercial_codigo3")
	private String						codigoComercial3;

	@Column(name = "codigo_cabys")
	private String						codigoCabys;

	@CreatedDate
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date							created_at;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@JsonIgnore
	@ManyToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "compra_id", nullable = false)
	private Compra						compra;

	@ManyToOne()
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "articulo_id", nullable = false)
	private Articulo					articulo;

	@Column(name = "descripcion")
	private String						descripcion;

	@Column(name = "codigo")
	private String						codigo;
	@Column(name = "cod_tipo_impuesto")
	private String						codigoTipoImpuesto;
	@Column(name = "tari_impuesto")
	private String						tarifaImpuesto;

	public DetalleCompra() {
		super();
	}

	public DetalleCompra(DetalleCompraCommand detalleCompraCommand) {
		super();

		this.numeroLinea = detalleCompraCommand.getNumeroLinea();
		this.costo = Utils.roundFactura(detalleCompraCommand.getCosto() != null ? detalleCompraCommand.getCosto() : Constantes.ZEROS_DOUBLE, 5);
		this.cantidad = detalleCompraCommand.getCantidad();
		this.ganancia = detalleCompraCommand.getGanancia() != null ? detalleCompraCommand.getGanancia() : Constantes.ZEROS_DOUBLE;
		this.impuesto = Utils.roundFactura(detalleCompraCommand.getImpuesto() != null ? detalleCompraCommand.getImpuesto() : Constantes.ZEROS_DOUBLE, 5);
		this.descuento = Utils.roundFactura(detalleCompraCommand.getDescuento() != null ? detalleCompraCommand.getDescuento() : Constantes.ZEROS_DOUBLE, 5);
		this.totalDescuento = Utils.roundFactura(detalleCompraCommand.getTotalDescuento() != null ? detalleCompraCommand.getTotalDescuento() : Constantes.ZEROS_DOUBLE, 5);
		this.totalImpuesto = Utils.roundFactura(detalleCompraCommand.getTotalImpuesto() != null ? detalleCompraCommand.getTotalImpuesto() : Constantes.ZEROS_DOUBLE, 5);
		this.montoTotalLinea = Utils.roundFactura(detalleCompraCommand.getMontoTotalLinea() != null ? detalleCompraCommand.getMontoTotalLinea() : Constantes.ZEROS_DOUBLE, 5);
		this.descripcion = detalleCompraCommand.getDescripcion();
		this.codigo = detalleCompraCommand.getCodigo();

		this.precio = detalleCompraCommand.getPrecio();
		this.codigoComercialTipo1 = detalleCompraCommand.getCodigoComercialTipo1() == null ? Constantes.EMPTY : detalleCompraCommand.getCodigoComercialTipo1();
		this.codigoComercial1 = detalleCompraCommand.getCodigoComercial1() == null ? Constantes.EMPTY : detalleCompraCommand.getCodigoComercial1();

		this.codigoComercialTipo2 = detalleCompraCommand.getCodigoComercialTipo2() == null ? Constantes.EMPTY : detalleCompraCommand.getCodigoComercialTipo2();
		this.codigoComercial2 = detalleCompraCommand.getCodigoComercial2() == null ? Constantes.EMPTY : detalleCompraCommand.getCodigoComercial2();

		this.codigoComercialTipo3 = detalleCompraCommand.getCodigoComercialTipo3() == null ? Constantes.EMPTY : detalleCompraCommand.getCodigoComercialTipo3();
		this.codigoComercial3 = detalleCompraCommand.getCodigoComercial3() == null ? Constantes.EMPTY : detalleCompraCommand.getCodigoComercial3();

		this.codigoCabys = detalleCompraCommand.getCodigoCabys() == null ? Constantes.EMPTY : detalleCompraCommand.getCodigoCabys();
		this.tarifaImpuesto = detalleCompraCommand.getTarifaImpuesto() == null ? Constantes.EMPTY : detalleCompraCommand.getTarifaImpuesto();
		this.codigoTipoImpuesto = detalleCompraCommand.getCodigoTipoImpuesto() == null ? Constantes.EMPTY : detalleCompraCommand.getCodigoTipoImpuesto();
	}

	public DetalleCompra(RecepcionFacturaDetalle recepcionFacturaDetalle) {
		super();

		this.numeroLinea = recepcionFacturaDetalle.getNumeroLinea();
		this.cantidad = recepcionFacturaDetalle.getCantidad() != null && !recepcionFacturaDetalle.getCantidad().equals(Constantes.ZEROS_DOUBLE) ? recepcionFacturaDetalle.getCantidad() : Constantes.ZEROS_DOUBLE;
		this.costo = recepcionFacturaDetalle.getMontoTotal() != null ? recepcionFacturaDetalle.getMontoTotal() : Constantes.ZEROS_DOUBLE;
		this.costo = this.cantidad > Constantes.ZEROS_DOUBLE ? this.costo / this.cantidad : this.costo;
		this.costo = Utils.round(this.costo, 2);
		this.ganancia = Constantes.ZEROS_DOUBLE;
		this.impuesto = Constantes.ZEROS_DOUBLE;
		this.descuento = Constantes.ZEROS_DOUBLE;
		this.totalDescuento = recepcionFacturaDetalle.getDescuentoMonto() != null ? recepcionFacturaDetalle.getDescuentoMonto() : Constantes.ZEROS_DOUBLE;
		this.totalImpuesto = Constantes.ZEROS_DOUBLE;
		this.montoTotalLinea = recepcionFacturaDetalle.getMontoTotalLinea();
		this.precio = Constantes.ZEROS_DOUBLE;

		this.codigoComercialTipo1 = recepcionFacturaDetalle.getCodigoComercialTipo1() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getCodigoComercialTipo1();
		this.codigoComercial1 = recepcionFacturaDetalle.getCodigoComercial1() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getCodigoComercial1();

		this.codigoComercialTipo2 = recepcionFacturaDetalle.getCodigoComercialTipo2() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getCodigoComercialTipo2();
		this.codigoComercial2 = recepcionFacturaDetalle.getCodigoComercial2() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getCodigoComercial2();

		this.codigoComercialTipo3 = recepcionFacturaDetalle.getCodigoComercialTipo3() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getCodigoComercialTipo3();
		this.codigoComercial3 = recepcionFacturaDetalle.getCodigoComercial3() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getCodigoComercial3();

		this.codigoCabys = recepcionFacturaDetalle.getCodigoCabys() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getCodigoCabys();
		this.tarifaImpuesto = recepcionFacturaDetalle.getImpuestoCodigoTarifa() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getImpuestoCodigoTarifa();
		this.codigoTipoImpuesto = recepcionFacturaDetalle.getImpuestoCodigo() == null ? Constantes.EMPTY : recepcionFacturaDetalle.getImpuestoCodigo();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getCostoSTR() {
		return Utils.formateadorMiles(this.costo);
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
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

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public String getImpuestoSTR() {
		return Utils.formateadorMiles(this.impuesto);
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public String getDescuentoSTR() {
		return Utils.formateadorMiles(this.descuento);
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public String getTotalImpuestoSTR() {
		return Utils.formateadorMiles(this.totalImpuesto);
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public String getTotalDescuentoSTR() {
		return Utils.formateadorMiles(this.totalDescuento);
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getGanancia() {
		return ganancia;
	}

	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoComercialTipo1() {
		return codigoComercialTipo1;
	}

	public void setCodigoComercialTipo1(String codigoComercialTipo1) {
		this.codigoComercialTipo1 = codigoComercialTipo1;
	}

	public String getCodigoComercial1() {
		return codigoComercial1;
	}

	public void setCodigoComercial1(String codigoComercial1) {
		this.codigoComercial1 = codigoComercial1;
	}

	public String getCodigoComercialTipo2() {
		return codigoComercialTipo2;
	}

	public void setCodigoComercialTipo2(String codigoComercialTipo2) {
		this.codigoComercialTipo2 = codigoComercialTipo2;
	}

	public String getCodigoComercial2() {
		return codigoComercial2;
	}

	public void setCodigoComercial2(String codigoComercial2) {
		this.codigoComercial2 = codigoComercial2;
	}

	public String getCodigoComercialTipo3() {
		return codigoComercialTipo3;
	}

	public void setCodigoComercialTipo3(String codigoComercialTipo3) {
		this.codigoComercialTipo3 = codigoComercialTipo3;
	}

	public String getCodigoComercial3() {
		return codigoComercial3;
	}

	public void setCodigoComercial3(String codigoComercial3) {
		this.codigoComercial3 = codigoComercial3;
	}

	public String getCodigoCabys() {
		return codigoCabys;
	}

	public void setCodigoCabys(String codigoCabys) {
		this.codigoCabys = codigoCabys;
	}

	public String getCodigoTipoImpuesto() {
		return codigoTipoImpuesto;
	}

	public void setCodigoTipoImpuesto(String codigoTipoImpuesto) {
		this.codigoTipoImpuesto = codigoTipoImpuesto;
	}

	public String getTarifaImpuesto() {
		return tarifaImpuesto;
	}

	public void setTarifaImpuesto(String tarifaImpuesto) {
		this.tarifaImpuesto = tarifaImpuesto;
	}

}

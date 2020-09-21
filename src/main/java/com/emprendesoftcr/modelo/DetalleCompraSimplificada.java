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

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.DetalleCompraSimplificadaCommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detall_simpl")
public class DetalleCompraSimplificada implements Serializable {


	/**
	 * Comentario para <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 972950950980269271L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long								id;

	@Column(name = "numero_linea")
	private Integer							numeroLinea;

	@Column(name = "codigo", length = 20)
	private String							codigo;

	@Column(name = "descripcion", length = 180)
	private String							descripcion;

	@Column(name = "tipo_codigo", length = 2)
	private String							tipoCodigo;

	@Column(name = "tipo_impuesto", length = 2)
	private String							tipoImpuesto;

	@Column(name = "unidad_medida", length = 15)
	private String							unidadMedida;

	@Column(name = "precio_unitario", precision = 18, scale = 5)
	private Double							precioUnitario;

	@Column(name = "cantidad", precision = 16, scale = 3)
	private Double							cantidad;

	@Column(name = "monto_total", precision = 18, scale = 5)
	private Double							montoTotal;

	@Column(name = "Monto_descuento", precision = 18, scale = 5)
	private Double							montoDescuento;

	@Column(name = "naturaleza_descuento", length = 80)
	private String							naturalezaDescuento;

	@Column(name = "sub_total", precision = 18, scale = 5)
	private Double							subTotal;

	@Column(name = "impuesto", precision = 18, scale = 5)
	private Double							impuesto;

	@Column(name = "monto_impuesto", precision = 18, scale = 5)
	private Double							montoImpuesto;

	@Column(name = "monto_total_linea", precision = 18, scale = 5)
	private Double							montoTotalLinea;

	@Column(name = "porcentaje_desc", precision = 18, scale = 5)
	private Double							porcentajeDesc;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date								created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date								updated_at;

	@Column(name = "imp_neto", precision = 18, scale = 5)
	private Double							ImpuestoNeto;

	@Column(name = "base_imponible",  precision = 18, scale = 5)
	private Double							baseImponible;

	@Column(name = "cod_tarifa", length = 2)
	private String							codigoTarifa;

	@ManyToOne
	@JoinColumn(name = "comp_simpl_id")
	private CompraSimplificada	compraSimplificada;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario							usuario;

	public DetalleCompraSimplificada() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	
	



	public DetalleCompraSimplificada(Long id, Integer numeroLinea, String codigo, String descripcion, String tipoCodigo, String tipoImpuesto, String unidadMedida, Double precioUnitario, Double cantidad, Double montoTotal, Double montoDescuento, String naturalezaDescuento, Double subTotal, Double impuesto, Double montoImpuesto, Double montoTotalLinea, Double porcentajeDesc, Date created_at, Date updated_at, Double impuestoNeto, Double baseImponible, String codigoTarifa, CompraSimplificada compraSimplificada, Usuario usuario) {
		super();
		this.id = id;
		this.numeroLinea = numeroLinea;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipoCodigo = tipoCodigo;
		this.tipoImpuesto = tipoImpuesto;
		this.unidadMedida = unidadMedida;
		this.precioUnitario = precioUnitario;
		this.cantidad = cantidad;
		this.montoTotal = montoTotal;
		this.montoDescuento = montoDescuento;
		this.naturalezaDescuento = naturalezaDescuento;
		this.subTotal = subTotal;
		this.impuesto = impuesto;
		this.montoImpuesto = montoImpuesto;
		this.montoTotalLinea = montoTotalLinea;
		this.porcentajeDesc = porcentajeDesc;
		this.created_at = created_at;
		this.updated_at = updated_at;
		ImpuestoNeto = impuestoNeto;
		this.baseImponible = baseImponible;
		this.codigoTarifa = codigoTarifa;
		this.compraSimplificada = compraSimplificada;
		this.usuario = usuario;
	}




	public DetalleCompraSimplificada(DetalleCompraSimplificadaCommand detalleCompraSimplificadaCommand) {
		super();
		this.id = detalleCompraSimplificadaCommand.getId();
		this.numeroLinea = detalleCompraSimplificadaCommand.getNumeroLinea();
		this.precioUnitario = detalleCompraSimplificadaCommand.getPrecioUnitario();
		this.cantidad = detalleCompraSimplificadaCommand.getCantidad();
		this.montoTotal = detalleCompraSimplificadaCommand.getMontoTotal();
		this.montoDescuento = detalleCompraSimplificadaCommand.getMontoDescuento() == null ? Constantes.ZEROS_DOUBLE : detalleCompraSimplificadaCommand.getMontoDescuento();
		this.naturalezaDescuento = detalleCompraSimplificadaCommand.getNaturalezaDescuento() == null ? Constantes.EMPTY : detalleCompraSimplificadaCommand.getNaturalezaDescuento();
		this.subTotal = detalleCompraSimplificadaCommand.getSubTotal();
		this.impuesto = detalleCompraSimplificadaCommand.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : detalleCompraSimplificadaCommand.getImpuesto();
		this.montoImpuesto = detalleCompraSimplificadaCommand.getMontoImpuesto() == null ? Constantes.ZEROS_DOUBLE : detalleCompraSimplificadaCommand.getMontoImpuesto();
		this.montoTotalLinea = detalleCompraSimplificadaCommand.getMontoTotalLinea();
		this.porcentajeDesc = detalleCompraSimplificadaCommand.getPorcentajeDesc() != null ? detalleCompraSimplificadaCommand.getPorcentajeDesc() : Constantes.ZEROS_DOUBLE;
		this.descripcion = detalleCompraSimplificadaCommand.getDescripcion();
		this.tipoCodigo = detalleCompraSimplificadaCommand.getTipoCodigo();
		this.codigo = detalleCompraSimplificadaCommand.getCodigo();
		this.unidadMedida = detalleCompraSimplificadaCommand.getUnidadMedida();
		this.tipoImpuesto = detalleCompraSimplificadaCommand.getTipoImpuesto() == null ? Constantes.EMPTY : detalleCompraSimplificadaCommand.getTipoImpuesto();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
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

	public Double getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(Double porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
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

	public CompraSimplificada getCompraSimplificada() {
		return compraSimplificada;
	}

	public void setCompraSimplificada(CompraSimplificada compraSimplificada) {
		this.compraSimplificada = compraSimplificada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getImpuestoNeto() {
		return ImpuestoNeto;
	}

	public void setImpuestoNeto(Double impuestoNeto) {
		ImpuestoNeto = impuestoNeto;
	}

	public Double getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(Double baseImponible) {
		this.baseImponible = baseImponible;
	}

}
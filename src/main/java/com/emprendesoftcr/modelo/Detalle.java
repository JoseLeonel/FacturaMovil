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

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.fisco.MapEnums;
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

	@Column(name = "codigo", length = 20)
	private String						codigo;

	@Column(name = "descripcion")
	private String						descripcion;

	@Column(name = "tipo_codigo", length = 2)
	private String						tipoCodigo;

	@Column(name = "tipo_impuesto", length = 2)
	private String						tipoImpuesto;

	@Column(name = "unidad_medida")
	private String						unidadMedida;

	@Column(name = "precio_unitario", precision = 18, scale = 5)
	private Double						precioUnitario;

	@Column(name = "cantidad", precision = 16, scale = 3)
	private Double						cantidad;

	@Column(name = "monto_total", precision = 18, scale = 5)
	private Double						montoTotal;

	@Column(name = "Monto_descuento", precision = 18, scale = 5)
	private Double						montoDescuento;

	@Column(name = "naturaleza_descuento")
	private String						naturalezaDescuento;

	@Column(name = "sub_total", precision = 18, scale = 5)
	private Double						subTotal;

	@Column(name = "impuesto", precision = 18, scale = 5)
	private Double						impuesto;

	@Column(name = "monto_impuesto", precision = 18, scale = 5)
	private Double						montoImpuesto;

	@Column(name = "monto_total_linea", precision = 18, scale = 5)
	private Double						montoTotalLinea;

	@Column(name = "ganancia", precision = 18, scale = 5)
	private Double						ganancia;

	@Column(name = "porcentaje_desc", precision = 18, scale = 5)
	private Double						porcentajeDesc;

	@Column(name = "porcentaje_ganan", columnDefinition = "default '0.00'", precision = 18, scale = 5)
	private Double						porcentajeGanancia;

	@Column(name = "costo", columnDefinition = " default '0.00'", precision = 18, scale = 5)
	private Double						costo;

	@Column(name = "observacion", length = 60)
	private String						observacion;

	@Column(name = "tipo_impuesto1")
	private String						tipoImpuesto1;
	@Column(name = "impuesto1", precision = 18, scale = 5)
	private Double						impuesto1;

	@Column(name = "monto_impuesto1", precision = 18, scale = 5)
	private Double						montoImpuesto1;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "monto_gananc", precision = 18, scale = 5)
	private Double						montoGanancia;

	@Column(name = "peso_transporte", precision = 18, scale = 5)
	private Double						pesoTransporte;

	@Column(name = "peso_transTotal", precision = 18, scale = 5)
	private Double						pesoTransporteTotal;

	@Column(name = "imp_neto", precision = 18, scale = 5)
	private Double						ImpuestoNeto;

	@Column(name = "base_imposible", columnDefinition = "default '0.00'", precision = 18, scale = 5)
	private Double						baseImponible;

	@Column(name = "cod_tarifa", length = 2)
	private String						codigoTarifa;

	@Column(name = "cod_tarifa1", length = 2)
	private String						codigoTarifa1;

	@Column(name = "tipo_doc_exo", length = 2)
	private String						tipoDocumentoExoneracion;

	@Column(name = "nume_doc_exo", length = 40)
	private String						numeroDocumentoExoneracion;

	@Column(name = "nomb_inst_exo", length = 160)
	private String						nombreInstitucionExoneracion;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision_exo")
	private Date							fechaEmisionExoneracion;

	@Column(name = "porcentaje_exo", columnDefinition = "INT default '0'")
	private Integer						porcentajeExoneracion;

	@Column(name = "mont_exone", precision = 18, scale = 5)
	private Double						montoExoneracion;

	@Column(name = "mont_exone1", precision = 18, scale = 5)
	private Double						montoExoneracion1;
	
	@Column(name = "cant_notaC", precision = 18, scale = 5)
	private Double						cantidadAplicadaNotaCredito;

	@ManyToOne
	@JoinColumn(name = "factura_id")
	private Factura						factura;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	

	public Detalle(Long id, Integer numeroLinea, String codigo, String descripcion, String tipoCodigo, String tipoImpuesto, String unidadMedida, Double precioUnitario, Double cantidad, Double montoTotal, Double montoDescuento, String naturalezaDescuento, Double subTotal, Double impuesto, Double montoImpuesto, Double montoTotalLinea, Double ganancia, Double porcentajeDesc, Double porcentajeGanancia, Double costo, String observacion, String tipoImpuesto1, Double impuesto1, Double montoImpuesto1, Date created_at, Date updated_at, Double montoGanancia, Double pesoTransporte, Double pesoTransporteTotal, Double impuestoNeto, Double baseImponible, String codigoTarifa, String codigoTarifa1, String tipoDocumentoExoneracion, String numeroDocumentoExoneracion, String nombreInstitucionExoneracion,
			Date fechaEmisionExoneracion, Integer porcentajeExoneracion, Double montoExoneracion, Double montoExoneracion1, Double cantidadAplicadaNotaCredito, Factura factura, Usuario usuario) {
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
		this.ganancia = ganancia;
		this.porcentajeDesc = porcentajeDesc;
		this.porcentajeGanancia = porcentajeGanancia;
		this.costo = costo;
		this.observacion = observacion;
		this.tipoImpuesto1 = tipoImpuesto1;
		this.impuesto1 = impuesto1;
		this.montoImpuesto1 = montoImpuesto1;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.montoGanancia = montoGanancia;
		this.pesoTransporte = pesoTransporte;
		this.pesoTransporteTotal = pesoTransporteTotal;
		ImpuestoNeto = impuestoNeto;
		this.baseImponible = baseImponible;
		this.codigoTarifa = codigoTarifa;
		this.codigoTarifa1 = codigoTarifa1;
		this.tipoDocumentoExoneracion = tipoDocumentoExoneracion;
		this.numeroDocumentoExoneracion = numeroDocumentoExoneracion;
		this.nombreInstitucionExoneracion = nombreInstitucionExoneracion;
		this.fechaEmisionExoneracion = fechaEmisionExoneracion;
		this.porcentajeExoneracion = porcentajeExoneracion;
		this.montoExoneracion = montoExoneracion;
		this.montoExoneracion1 = montoExoneracion1;
		this.cantidadAplicadaNotaCredito = cantidadAplicadaNotaCredito;
		this.factura = factura;
		this.usuario = usuario;
	}

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
		this.impuesto1 = detalleFacturaCommand.getImpuesto1() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getImpuesto1();
		this.montoImpuesto = detalleFacturaCommand.getMontoImpuesto() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getMontoImpuesto();
		this.montoImpuesto1 = detalleFacturaCommand.getMontoImpuesto1() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getMontoImpuesto1();
		this.montoTotalLinea = detalleFacturaCommand.getMontoTotalLinea();
		this.ganancia = Constantes.ZEROS_DOUBLE;
		this.porcentajeDesc = detalleFacturaCommand.getPorcentajeDesc() != null ? detalleFacturaCommand.getPorcentajeDesc() : Constantes.ZEROS_DOUBLE;
		this.descripcion = detalleFacturaCommand.getDescripcion();
		this.tipoCodigo = detalleFacturaCommand.getTipoCodigo();
		this.codigo = detalleFacturaCommand.getCodigo();
		this.unidadMedida = detalleFacturaCommand.getUnidadMedida();
		this.tipoImpuesto = detalleFacturaCommand.getTipoImpuesto() == null ? Constantes.EMPTY : detalleFacturaCommand.getTipoImpuesto();
		this.tipoImpuesto1 = detalleFacturaCommand.getTipoImpuesto1() == null ? Constantes.EMPTY : detalleFacturaCommand.getTipoImpuesto1();
		this.montoGanancia = detalleFacturaCommand.getMontoGanancia();
		this.pesoTransporte = detalleFacturaCommand.getPesoTransporte();
		this.pesoTransporteTotal = detalleFacturaCommand.getPesoTransporteTotal();
		this.tipoDocumentoExoneracion = detalleFacturaCommand.getTipoDocumentoExoneracion();
		this.numeroDocumentoExoneracion = detalleFacturaCommand.getNumeroDocumentoExoneracion();
		this.nombreInstitucionExoneracion = detalleFacturaCommand.getNombreInstitucionExoneracion();
		this.fechaEmisionExoneracion = detalleFacturaCommand.getFechaEmisionExoneracion();
		this.porcentajeExoneracion = detalleFacturaCommand.getPorcentajeExoneracion();
		this.montoExoneracion = detalleFacturaCommand.getMontoExoneracion();
		this.montoExoneracion1 = detalleFacturaCommand.getMontoExoneracion1();
		this.cantidadAplicadaNotaCredito = detalleFacturaCommand.getCantidadAplicadaNotaCredito();
		
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

	public String getCodigoTarifaSTR() {
		return codigoTarifa !=null?MapEnums.ENUM_TARIFAS_IMPUESTOS.get(codigoTarifa):codigoTarifa;
	}
	public String getCodigoTarifa1() {
		return codigoTarifa1;
	}

	public void setCodigoTarifa1(String codigoTarifa1) {
		this.codigoTarifa1 = codigoTarifa1;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}
	
	
	public String getTipoImpuestoSTR() {
		return  tipoImpuesto !=null?MapEnums.ENUM_TIPOS_IMPUESTOS.get(tipoImpuesto):tipoImpuesto;
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

	public String getMontoImpuesto1STR() {
		return Utils.formateadorMiles(this.montoImpuesto1);
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

	public Double getMontoGanancia() {
		return montoGanancia;
	}

	public void setMontoGanancia(Double montoGanancia) {
		this.montoGanancia = montoGanancia;
	}

	public Double getPorcentajeGanancia() {
		return porcentajeGanancia;
	}

	public void setPorcentajeGanancia(Double porcentajeGanancia) {
		this.porcentajeGanancia = porcentajeGanancia;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getTipoImpuesto1() {
		return tipoImpuesto1;
	}

	public void setTipoImpuesto1(String tipoImpuesto1) {
		this.tipoImpuesto1 = tipoImpuesto1;
	}

	public Double getImpuesto1() {
		return impuesto1;
	}

	public void setImpuesto1(Double impuesto1) {
		this.impuesto1 = impuesto1;
	}

	public Double getMontoImpuesto1() {
		return montoImpuesto1;
	}

	public void setMontoImpuesto1(Double montoImpuesto1) {
		this.montoImpuesto1 = montoImpuesto1;
	}

	public Double getPesoTransporte() {
		return pesoTransporte;
	}

	public void setPesoTransporte(Double pesoTransporte) {
		this.pesoTransporte = pesoTransporte;
	}

	public Double getPesoTransporteTotal() {
		return pesoTransporteTotal;
	}

	public void setPesoTransporteTotal(Double pesoTransporteTotal) {
		this.pesoTransporteTotal = pesoTransporteTotal;
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

	public String getTipoDocumentoExoneracion() {
		return tipoDocumentoExoneracion;
	}

	public void setTipoDocumentoExoneracion(String tipoDocumentoExoneracion) {
		this.tipoDocumentoExoneracion = tipoDocumentoExoneracion;
	}

	public String getNumeroDocumentoExoneracion() {
		return numeroDocumentoExoneracion;
	}

	public void setNumeroDocumentoExoneracion(String numeroDocumentoExoneracion) {
		this.numeroDocumentoExoneracion = numeroDocumentoExoneracion;
	}

	public String getNombreInstitucionExoneracion() {
		return nombreInstitucionExoneracion;
	}

	public void setNombreInstitucionExoneracion(String nombreInstitucionExoneracion) {
		this.nombreInstitucionExoneracion = nombreInstitucionExoneracion;
	}

	public Date getFechaEmisionExoneracion() {
		return fechaEmisionExoneracion;
	}

	public void setFechaEmisionExoneracion(Date fechaEmisionExoneracion) {
		this.fechaEmisionExoneracion = fechaEmisionExoneracion;
	}

	public Integer getPorcentajeExoneracion() {
		return porcentajeExoneracion;
	}

	public void setPorcentajeExoneracion(Integer porcentajeExoneracion) {
		this.porcentajeExoneracion = porcentajeExoneracion;
	}

	public Double getMontoExoneracion() {
		return montoExoneracion;
	}

	public void setMontoExoneracion(Double montoExoneracion) {
		this.montoExoneracion = montoExoneracion;
	}

	public Double getMontoExoneracion1() {
		return montoExoneracion1;
	}

	public void setMontoExoneracion1(Double montoExoneracion1) {
		this.montoExoneracion1 = montoExoneracion1;
	}

	
	public Double getCantidadAplicadaNotaCredito() {
		return cantidadAplicadaNotaCredito;
	}

	
	public void setCantidadAplicadaNotaCredito(Double cantidadAplicadaNotaCredito) {
		this.cantidadAplicadaNotaCredito = cantidadAplicadaNotaCredito;
	}
	
	

}
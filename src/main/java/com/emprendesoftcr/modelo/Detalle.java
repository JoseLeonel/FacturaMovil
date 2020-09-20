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
	private String						tipoImpuestoMag;
	@Column(name = "impuesto1", precision = 18, scale = 5)
	private Double						impuestoMag;

	@Column(name = "monto_impuesto1", precision = 18, scale = 5)
	private Double						montoImpuestoMag;

	@Column(name = "precio", precision = 18, scale = 5, columnDefinition = "default '0.00'")
	private Double						precio;

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
	private String						codigoTarifaMag;

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
		this.impuestoMag = detalleFacturaCommand.getImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getImpuestoMag();
		this.montoImpuesto = detalleFacturaCommand.getMontoImpuesto() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getMontoImpuesto();
		this.montoImpuestoMag = detalleFacturaCommand.getMontoImpuestoMag() == null ? Constantes.ZEROS_DOUBLE : detalleFacturaCommand.getMontoImpuestoMag();
		this.montoTotalLinea = detalleFacturaCommand.getMontoTotalLinea();
		this.ganancia = Constantes.ZEROS_DOUBLE;
		this.porcentajeDesc = detalleFacturaCommand.getPorcentajeDesc() != null ? detalleFacturaCommand.getPorcentajeDesc() : Constantes.ZEROS_DOUBLE;
		this.descripcion = detalleFacturaCommand.getDescripcion();
		this.tipoCodigo = detalleFacturaCommand.getTipoCodigo();
		this.codigo = detalleFacturaCommand.getCodigo();
		this.unidadMedida = detalleFacturaCommand.getUnidadMedida();
		this.tipoImpuesto = detalleFacturaCommand.getTipoImpuesto() == null ? Constantes.EMPTY : detalleFacturaCommand.getTipoImpuesto();
		this.tipoImpuestoMag = detalleFacturaCommand.getTipoImpuestoMag() == null ? Constantes.EMPTY : detalleFacturaCommand.getTipoImpuestoMag();
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
		return codigoTarifa != null ? MapEnums.ENUM_TARIFAS_IMPUESTOS.get(codigoTarifa) : codigoTarifa;
	}

	public String getCodigoTarifaMag() {
		return codigoTarifaMag;
	}

	public void setCodigoTarifaMag(String codigoTarifaMag) {
		this.codigoTarifaMag = codigoTarifaMag;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public String getTipoImpuestoSTR() {
		return tipoImpuesto != null ? MapEnums.ENUM_TIPOS_IMPUESTOS.get(tipoImpuesto) : tipoImpuesto;
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

	public Double getMontoTotalNC() {
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return this.montoTotal != null ? this.montoTotal * -1 : this.montoTotal;
			}
		}
		return this.montoTotal;
	}

	

	public Double getTotalServicioExentos() {
		Double resultado = Utils.getTotalServExentos(this.tipoImpuesto, this.unidadMedida, this.montoImpuesto, Constantes.ZEROS_DOUBLE, this.montoTotal);
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return resultado == null ? Constantes.ZEROS_DOUBLE : (resultado * this.factura.getTipoCambio()) * -1;
			}
		}
		return resultado * this.factura.getTipoCambio();
	}

	public Double getTotalServicioExonerados() {
		Double resultado = Utils.getTotalServExonerado(this.tipoImpuesto, this.unidadMedida, this.montoExoneracion);
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return resultado == null ? Constantes.ZEROS_DOUBLE : (resultado * this.factura.getTipoCambio()) * -1;
			}
		}
		return resultado * this.factura.getTipoCambio();
	}

	public Double getTotalServicioGravados() {

		Double resultado = Utils.getTotalServicioGravados(this.tipoImpuesto, this.unidadMedida, this.montoTotal, this.montoImpuesto, Constantes.ZEROS_DOUBLE);
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return resultado == null ? Constantes.ZEROS_DOUBLE : (resultado * this.factura.getTipoCambio()) * -1;
			}
		}
		return resultado * this.factura.getTipoCambio();
	}

	public Double getTotalMercanciaExonerada() {
		Double resultado = Utils.getTotalMercExonerada(this.tipoImpuesto, this.unidadMedida, this.montoTotal, this.porcentajeExoneracion);

		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return resultado == null ? Constantes.ZEROS_DOUBLE : (resultado * this.factura.getTipoCambio()) * -1;
			}
		}
		return resultado * this.factura.getTipoCambio();
	}

	public Double getTotalMercanciaGravada() {
		Double resultado = Utils.getTotalMercanciasGravadas(this.tipoImpuesto, this.unidadMedida, this.montoImpuesto, Constantes.ZEROS_DOUBLE, this.montoTotal, this.porcentajeExoneracion);

		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return resultado == null ? Constantes.ZEROS_DOUBLE : (resultado * this.factura.getTipoCambio()) * -1;
			}
		}
		return resultado * this.factura.getTipoCambio();
	}

	public Double getTotalMercanciaExenta() {

		Double resultado = Constantes.ZEROS_DOUBLE;
		resultado = Utils.getTotalMercanciasExentas(this.tipoImpuesto, this.unidadMedida, this.montoImpuesto, Constantes.ZEROS_DOUBLE, this.montoTotal);

		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return resultado == null ? Constantes.ZEROS_DOUBLE : (resultado * this.factura.getTipoCambio()) * -1;
			}
		}
		return resultado * this.factura.getTipoCambio();
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

	public Double getMontoDescuentoNC() {
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return this.montoDescuento != null ? this.montoDescuento * -1 : this.montoDescuento;
			}
		}
		return this.montoDescuento;
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

	public Double getSubTotalNC() {
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return this.subTotal != null ? this.subTotal * -1 : this.subTotal;
			}
		}
		return this.subTotal;
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

	public Double getMontoImpuestoNC() {
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return this.montoImpuesto != null ? this.montoImpuesto * -1 : Constantes.ZEROS_DOUBLE;
			}
		}
		return this.montoImpuesto;
	}

	public Double getMontoImpuestoNeto() {
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return this.ImpuestoNeto != null ? this.ImpuestoNeto * -1 : Constantes.ZEROS_DOUBLE;
			}
		}
		return this.ImpuestoNeto;
	}

	public void setMontoImpuesto(Double montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
	}

	public String getMontoImpuestoSTR() {
		return Utils.formateadorMiles(this.montoImpuesto);
	}

	public String getMontoImpuesto1STR() {
		return Utils.formateadorMiles(Constantes.ZEROS_DOUBLE);
	}

	public Double getMontoTotalLinea() {
		return montoTotalLinea;
	}

	public Double getMontoTotalLineaNC() {
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return this.montoTotalLinea != null ? this.montoTotalLinea * -1 : this.montoTotalLinea;
			}
		}
		return this.montoTotalLinea;
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

	public Double getMontoGananciaNC() {
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return this.montoGanancia != null ? this.montoGanancia * -1 : this.montoGanancia;
			}
		}
		return this.montoGanancia;
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

	public String getTipoImpuestoMag() {
		return tipoImpuestoMag;
	}

	public void setTipoImpuestoMag(String tipoImpuestoMag) {
		this.tipoImpuestoMag = tipoImpuestoMag;
	}

	public Double getImpuestoMag() {
		return impuestoMag;
	}

	public void setImpuestoMag(Double impuestoMag) {
		this.impuestoMag = impuestoMag;
	}

	public Double getMontoImpuestoMag() {
		return montoImpuestoMag;
	}

	public void setMontoImpuestoMag(Double montoImpuestoMag) {
		this.montoImpuestoMag = montoImpuestoMag;
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

	public Double getMontoExoneracionNC() {
		if (this.factura.getTipoDoc() != null) {
			if (this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) || this.factura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				return this.montoExoneracion != null ? this.montoExoneracion * -1 : this.montoExoneracion;
			}
		}
		return this.montoExoneracion;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
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

@Entity
@Table(name = "compra_simpli")
public class CompraSimplificada implements Serializable {

	private static final long			serialVersionUID	= 826299407924905733L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long									id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_credito")
	private Date									fechaCredito;

	@Column(name = "numero_consecutivo", length = 20)
	private String								numeroConsecutivo;

	@Column(name = "clave", length = 50)
	private String								clave;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date									fechaEmision;

	@Column(name = "condicion_venta", length = 2)
	private String								condicionVenta;

	@Column(name = "plazo_credito")
	private Integer								plazoCredito;

	@Column(name = "tipo_doc", length = 2)
	private String								tipoDoc;

	@Column(name = "medio_efectivo", length = 2)
	private String								medioEfectivo;

	@Column(name = "medio_tarjeta", length = 2)
	private String								medioTarjeta;

	@Column(name = "medio_banco", length = 2)
	private String								medioBanco;

	@Column(name = "tipo_cambio", precision = 18, scale = 5)
	private Double								tipoCambio;

	@Column(name = "subTotal", precision = 18, scale = 5)
	private Double								subTotal;

	@Column(name = "total_serv_gravados", precision = 18, scale = 5)
	private Double								totalServGravados;

	@Column(name = "total_serv_exentos", precision = 18, scale = 5)
	private Double								totalServExentos;

	@Column(name = "total_mercancias_gravadas", precision = 18, scale = 5)
	private Double								totalMercanciasGravadas;

	@Column(name = "total_mercancias_exentas", precision = 18, scale = 5)
	private Double								totalMercanciasExentas;

	@Column(name = "total_gravado", precision = 18, scale = 5)
	private Double								totalGravado;

	@Column(name = "total_exento", precision = 18, scale = 5)
	private Double								totalExento;

	@Column(name = "total_venta", precision = 18, scale = 5)
	private Double								totalVenta;

	@Column(name = "total_descuentos", precision = 18, scale = 5)
	private Double								totalDescuentos;

	@Column(name = "total_venta_neta", precision = 18, scale = 5)
	private Double								totalVentaNeta;

	@Column(name = "total_impuesto", precision = 18, scale = 5)
	private Double								totalImpuesto;

	@Column(name = "total_comprobante", precision = 18, scale = 5)
	private Double								totalComprobante;

	@Column(name = "total_efectivo", precision = 18, scale = 5)
	private Double								totalEfectivo;

	@Column(name = "total_tarjeta", precision = 18, scale = 5)
	private Double								totalTarjeta;

	@Column(name = "total_banco", precision = 18, scale = 5)
	private Double								totalBanco;

	@Column(name = "total_credito", precision = 18, scale = 5)
	private Double								totalCredito;

	@Column(name = "monto_cambio", precision = 18, scale = 5)
	private Double								montoCambio;

	@Column(name = "total_cambio", precision = 18, scale = 5)
	private Double								totalCambio;

	// Impuesto del servicio cuando aplica
	@Column(name = "total_servicio", precision = 18, scale = 5)
	private Double								totalImpuestoServicio;

	@Column(name = "total_cambioPagar", precision = 18, scale = 5)
	private Double								totalCambioPagar;

	@Column(name = "codigo_moneda", length = 3)
	private String								codigoMoneda;

	@Column(name = "estado")
	private Integer								estado;

	@Column(name = "estado_firma")
	private Integer								estadoFirma;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date									created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date									updated_at;

	@Column(name = "version_esquema", columnDefinition = "INT default '0'")
	private Integer								versionEsquemaXML;

	@ManyToOne
	@JoinColumn(name = "proveedorsimpl_id")
	private ProveedorSimplificado	ProveedorSimplificado;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa								empresa;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario								usuarioCreacion;

	@Column(name = "act_comercial", length = 6)
	private String								codigoActividad;
	@Column(name = "ref_tipo_doc", length = 2)
	private String								referenciaTipoDoc;

	@Column(name = "ref_numero", length = 20)
	private String								referenciaNumero;

	@Column(name = "ref_codigo", length = 2)
	private String								referenciaCodigo;

	@Column(name = "ref_razon", length = 60)
	private String								referenciaRazon;

	public CompraSimplificada() {
		super();
		this.estado = Constantes.FACTURA_ESTADO_PENDIENTE;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public CompraSimplificada(Long id, Date fechaCredito, String numeroConsecutivo, String clave, Date fechaEmision, String condicionVenta, Integer plazoCredito, String tipoDoc, String medioEfectivo, String medioTarjeta, String medioBanco, Double tipoCambio, Double subTotal, Double totalServGravados, Double totalServExentos, Double totalMercanciasGravadas, Double totalMercanciasExentas, Double totalGravado, Double totalExento, Double totalVenta, Double totalDescuentos, Double totalVentaNeta, Double totalImpuesto, Double totalComprobante, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double montoCambio, Double totalCambio, Double totalImpuestoServicio, Double totalCambioPagar, String codigoMoneda, Integer estado, Integer estadoFirma, Date created_at,
			Date updated_at, Integer versionEsquemaXML, com.emprendesoftcr.modelo.ProveedorSimplificado proveedorSimplificado, Empresa empresa, Usuario usuarioCreacion, String codigoActividad, String referenciaTipoDoc, String referenciaNumero, String referenciaCodigo, String referenciaRazon) {
		super();
		this.id = id;
		this.fechaCredito = fechaCredito;
		this.numeroConsecutivo = numeroConsecutivo;
		this.clave = clave;
		this.fechaEmision = fechaEmision;
		this.condicionVenta = condicionVenta;
		this.plazoCredito = plazoCredito;
		this.tipoDoc = tipoDoc;
		this.medioEfectivo = medioEfectivo;
		this.medioTarjeta = medioTarjeta;
		this.medioBanco = medioBanco;
		this.tipoCambio = tipoCambio;
		this.subTotal = subTotal;
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
		this.totalImpuestoServicio = totalImpuestoServicio;
		this.totalCambioPagar = totalCambioPagar;
		this.codigoMoneda = codigoMoneda;
		this.estado = estado;
		this.estadoFirma = estadoFirma;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.versionEsquemaXML = versionEsquemaXML;
		ProveedorSimplificado = proveedorSimplificado;
		this.empresa = empresa;
		this.usuarioCreacion = usuarioCreacion;
		this.codigoActividad = codigoActividad;
		this.referenciaTipoDoc = referenciaTipoDoc;
		this.referenciaNumero = referenciaNumero;
		this.referenciaCodigo = referenciaCodigo;
		this.referenciaRazon = referenciaRazon;
	}

	public Integer getVersionEsquemaXML() {
		return versionEsquemaXML;
	}

	public void setVersionEsquemaXML(Integer versionEsquemaXML) {
		this.versionEsquemaXML = versionEsquemaXML;
	}

	public Double getTotalImpuestoServicio() {
		return totalImpuestoServicio;
	}

	public void setTotalImpuestoServicio(Double totalImpuestoServicio) {
		this.totalImpuestoServicio = totalImpuestoServicio;
	}

	public String getTotalImpuestoServicioSTR() {
		return Utils.formateadorMiles(this.totalImpuestoServicio);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Double getTipoCambio() {
		return tipoCambio;
	}

	public Double getTotalColones() {
		return totalComprobante * tipoCambio;
	}

	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public String getSubTotalGeneralSTR() {
		Double valorSubTotal = this.subTotal != null ? this.subTotal : Constantes.ZEROS;
		Double valorDescuento = this.totalDescuentos != null ? this.totalDescuentos : Constantes.ZEROS;
		return Utils.formateadorMiles(valorSubTotal + valorDescuento);
	}

	public String getSubTotalSTR() {
		return Utils.formateadorMiles(this.subTotal);
	}

	public String getTipoCambioSTR() {
		return Utils.formateadorMiles(this.tipoCambio);
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

	public String getTotalMercanciasExentasSTR() {
		return Utils.formateadorMiles(this.totalMercanciasExentas);
	}

	public Double getTotalGravado() {
		return totalGravado;
	}

	public void setTotalGravado(Double totalGravado) {
		this.totalGravado = totalGravado;
	}

	public String getTotalGravadoSTR() {
		return Utils.formateadorMiles(this.totalGravado);
	}

	public Double getTotalExento() {
		return totalExento;
	}

	public void setTotalExento(Double totalExento) {
		this.totalExento = totalExento;
	}

	public String getTotalExentoSTR() {
		return Utils.formateadorMiles(this.totalExento);
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public String getTotalVentaSTR() {
		return Utils.formateadorMiles(this.totalVenta);
	}

	public Double getTotalDescuentos() {
		return totalDescuentos;
	}

	public void setTotalDescuentos(Double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public String getTotalDescuentosSTR() {
		return Utils.formateadorMiles(this.totalDescuentos);
	}

	public Double getTotalVentaNeta() {
		return totalVentaNeta;
	}

	public void setTotalVentaNeta(Double totalVentaNeta) {
		this.totalVentaNeta = totalVentaNeta;
	}

	public String getTotalVentaNetaSTR() {
		return Utils.formateadorMiles(this.totalVentaNeta);
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

	public String getTotalEfectivoSTR() {
		return Utils.formateadorMiles(this.totalEfectivo);
	}

	public Double getTotalTarjeta() {
		return totalTarjeta;
	}

	public void setTotalTarjeta(Double totalTarjeta) {
		this.totalTarjeta = totalTarjeta;
	}

	public String getTotalTarjetaSTR() {
		return Utils.formateadorMiles(this.totalTarjeta);
	}

	public Double getTotalBanco() {
		return totalBanco;
	}

	public void setTotalBanco(Double totalBanco) {
		this.totalBanco = totalBanco;
	}

	public String getTotalBancoSTR() {
		return Utils.formateadorMiles(this.totalBanco);
	}

	public Double getTotalCredito() {
		return totalCredito;
	}

	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}

	public String getTotalCreditoSTR() {
		return Utils.formateadorMiles(this.totalCredito);
	}

	public Double getMontoCambio() {
		return montoCambio;
	}

	public void setMontoCambio(Double montoCambio) {
		this.montoCambio = montoCambio;
	}

	public String getMontoCambioSTR() {
		return Utils.formateadorMiles(this.montoCambio);
	}

	public Double getTotalCambio() {
		return totalCambio;
	}

	public void setTotalCambio(Double totalCambio) {
		this.totalCambio = totalCambio;
	}

	public String getTotalCambioSTR() {
		return Utils.formateadorMiles(this.totalCambio);
	}

	public Double getTotalCambioPagar() {
		return totalCambioPagar;
	}

	public void setTotalCambioPagar(Double totalCambioPagar) {
		this.totalCambioPagar = totalCambioPagar;
	}

	public String getTotalCambioPagarSTR() {
		return Utils.formateadorMiles(this.totalCambioPagar);
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public Integer getEstadoFirma() {
		return estadoFirma;
	}

	public void setEstadoFirma(Integer estadoFirma) {
		this.estadoFirma = estadoFirma;
	}

	public String getFechaEmisionSTR() {
		if (this.fechaEmision != null) {
			return Utils.getFechaGeneraReporte(this.getFechaEmision());
		}
		return Constantes.EMPTY;
	}

	public String getTotalComprobanteSTR() {
		return Utils.formateadorMiles(this.totalComprobante);
	}

	public String getTotalDescuentoSTR() {
		return Utils.formateadorMiles(this.totalDescuentos);
	}

	public ProveedorSimplificado getProveedorSimplificado() {
		return ProveedorSimplificado;
	}

	public void setProveedorSimplificado(ProveedorSimplificado proveedorSimplificado) {
		ProveedorSimplificado = proveedorSimplificado;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
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

}

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
	private Long							id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_credito")
	private Date							fechaCredito;

	@Column(name = "numero_consecutivo")
	private String						numeroConsecutivo;

	@Column(name = "consecutivo_proforma")
	private String						consecutivoProforma;

	@Column(name = "clave")
	private String						clave;

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

	@Column(name = "ref_tipo_doc")
	private String						referenciaTipoDoc;

	@Column(name = "ref_numero")
	private String						referenciaNumero;

	@Column(name = "ref_codigo")
	private String						referenciaCodigo;

	@Column(name = "ref_razon")
	private String						referenciaRazon;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "ref_fecha_emision")
	private Date							referenciaFechaEmision;

	@Column(name = "medio_efectivo")
	private String						medioEfectivo;

	@Column(name = "medio_tarjeta")
	private String						medioTarjeta;

	@Column(name = "medio_banco")
	private String						medioBanco;

	@Column(name = "nombre_factura")
	private String						nombreFactura;

	@Column(name = "correo_alternativo")
	private String						correoAlternativo;

	@Column(name = "direccion")
	private String						direccion;

	@Column(name = "nota")
	private String						nota;

	@Column(name = "comanda")
	private String						comanda;

	@Column(name = "tipo_cambio")
	private Double						tipoCambio;

	@Column(name = "subTotal")
	private Double						subTotal;

	@Column(name = "total_transporte")
	private Double						totalTransporte;

	@Column(name = "total_serv_gravados")
	private Double						totalServGravados;

	@Column(name = "total_serv_exentos")
	private Double						totalServExentos;

	@Column(name = "total_mercancias_gravadas")
	private Double						totalMercanciasGravadas;

	@Column(name = "total_mercancias_exentas")
	private Double						totalMercanciasExentas;

	@Column(name = "total_gravado")
	private Double						totalGravado;

	@Column(name = "total_exento")
	private Double						totalExento;

	@Column(name = "total_venta")
	private Double						totalVenta;

	@Column(name = "total_descuentos")
	private Double						totalDescuentos;

	@Column(name = "total_venta_neta")
	private Double						totalVentaNeta;

	@Column(name = "total_impuesto")
	private Double						totalImpuesto;

	@Column(name = "total_comprobante")
	private Double						totalComprobante;

	@Column(name = "total_efectivo")
	private Double						totalEfectivo;

	@Column(name = "total_tarjeta")
	private Double						totalTarjeta;

	@Column(name = "total_banco")
	private Double						totalBanco;

	@Column(name = "total_credito")
	private Double						totalCredito;

	@Column(name = "monto_cambio")
	private Double						montoCambio;

	@Column(name = "total_cambio")
	private Double						totalCambio;

	// Impuesto del servicio cuando aplica
	@Column(name = "total_servicio")
	private Double						totalImpuestoServicio;

	@Column(name = "total_cambioPagar")
	private Double						totalCambioPagar;

	@Column(name = "cambio_moneda")
	private Double						cambioMoneda;

	@Column(name = "codigo_moneda")
	private String						codigoMoneda;

	@Column(name = "estado")
	private Integer						estado;

	@Column(name = "estado_firma")
	private Integer						estadoFirma;

	@Column(name = "tiene_is")
	private Integer						tieneIS;

	@Column(name = "peso_transTotal", columnDefinition = "Decimal(10,5) default '0.00'")
	private Double						pesoTransporteTotal;

	@Column(name = "total_serv_exo", columnDefinition = "Decimal(10,5) default '0.00'")
	private Double						totalServExonerado;

	@Column(name = "total_merc_exo", columnDefinition = "Decimal(10,5) default '0.00'")
	private Double						totalMercExonerada;

	@Column(name = "total_exo", columnDefinition = "Decimal(10,5) default '0.00'")
	private Double						totalExonerado;

	@Column(name = "total_iva_dev", columnDefinition = "Decimal(10,5) default '0.00'")
	private Double						totalIVADevuelto;

	@Column(name = "total_otros_cargos", columnDefinition = "Decimal(10,5) default '0.00'")
	private Double						totalOtrosCargos;

	@Column(name = "tipo_otros_doc", columnDefinition = "varchar(2)", length = 2)
	private String						tipoDocumentoOtroCargo;
	@Column(name = "deta_otros_doc", columnDefinition = "varchar(60)", length = 2)
	private String						detalleOtroCargo;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "version_esquema", columnDefinition = "INT default '0'")
	private Integer						versionEsquemaXML;

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

	@ManyToOne
	@JoinColumn(name = "mesa_id")
	private Mesa							mesa;

	@Column(name = "act_comercial", length = 6)
	private String						codigoActividad;

	public Factura() {
		super();
		this.estado = Constantes.FACTURA_ESTADO_PENDIENTE;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Factura(Long id, Date fechaCredito, String numeroConsecutivo, String consecutivoProforma, String clave, Date fechaEmision, String condicionVenta, Integer plazoCredito, String tipoDoc, String referenciaTipoDoc, String referenciaNumero, String referenciaCodigo, String referenciaRazon, Date referenciaFechaEmision, String medioEfectivo, String medioTarjeta, String medioBanco, String nombreFactura, String correoAlternativo, String direccion, String nota, String comanda, Double tipoCambio, Double subTotal, Double totalTransporte, Double totalServGravados, Double totalServExentos, Double totalMercanciasGravadas, Double totalMercanciasExentas, Double totalGravado, Double totalExento, Double totalVenta, Double totalDescuentos, Double totalVentaNeta, Double totalImpuesto,
			Double totalComprobante, Double totalEfectivo, Double totalTarjeta, Double totalBanco, Double totalCredito, Double montoCambio, Double totalCambio, Double totalImpuestoServicio, Double totalCambioPagar, Double cambioMoneda, String codigoMoneda, Integer estado, Integer estadoFirma, Integer tieneIS, Double pesoTransporteTotal, Double totalServExonerado, Double totalMercExonerada, Double totalExonerado, Double totalIVADevuelto, Double totalOtrosCargos, String tipoDocumentoOtroCargo, String detalleOtroCargo, Date created_at, Date updated_at, Integer versionEsquemaXML, Cliente cliente, Empresa empresa, Vendedor vendedor, Usuario usuarioCreacion, Mesa mesa, String codigoActividad) {
		super();
		this.id = id;
		this.fechaCredito = fechaCredito;
		this.numeroConsecutivo = numeroConsecutivo;
		this.consecutivoProforma = consecutivoProforma;
		this.clave = clave;
		this.fechaEmision = fechaEmision;
		this.condicionVenta = condicionVenta;
		this.plazoCredito = plazoCredito;
		this.tipoDoc = tipoDoc;
		this.referenciaTipoDoc = referenciaTipoDoc;
		this.referenciaNumero = referenciaNumero;
		this.referenciaCodigo = referenciaCodigo;
		this.referenciaRazon = referenciaRazon;
		this.referenciaFechaEmision = referenciaFechaEmision;
		this.medioEfectivo = medioEfectivo;
		this.medioTarjeta = medioTarjeta;
		this.medioBanco = medioBanco;
		this.nombreFactura = nombreFactura;
		this.correoAlternativo = correoAlternativo;
		this.direccion = direccion;
		this.nota = nota;
		this.comanda = comanda;
		this.tipoCambio = tipoCambio;
		this.subTotal = subTotal;
		this.totalTransporte = totalTransporte;
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
		this.cambioMoneda = cambioMoneda;
		this.codigoMoneda = codigoMoneda;
		this.estado = estado;
		this.estadoFirma = estadoFirma;
		this.tieneIS = tieneIS;
		this.pesoTransporteTotal = pesoTransporteTotal;
		this.totalServExonerado = totalServExonerado;
		this.totalMercExonerada = totalMercExonerada;
		this.totalExonerado = totalExonerado;
		this.totalIVADevuelto = totalIVADevuelto;
		this.totalOtrosCargos = totalOtrosCargos;
		this.tipoDocumentoOtroCargo = tipoDocumentoOtroCargo;
		this.detalleOtroCargo = detalleOtroCargo;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.versionEsquemaXML = versionEsquemaXML;
		this.cliente = cliente;
		this.empresa = empresa;
		this.vendedor = vendedor;
		this.usuarioCreacion = usuarioCreacion;
		this.mesa = mesa;
		this.codigoActividad = codigoActividad;
	}

	public String getTipoDocumentoOtroCargo() {
		return tipoDocumentoOtroCargo;
	}

	public void setTipoDocumentoOtroCargo(String tipoDocumentoOtroCargo) {
		this.tipoDocumentoOtroCargo = tipoDocumentoOtroCargo;
	}

	public String getDetalleOtroCargo() {
		return detalleOtroCargo;
	}

	public void setDetalleOtroCargo(String detalleOtroCargo) {
		this.detalleOtroCargo = detalleOtroCargo;
	}

	public Integer getVersionEsquemaXML() {
		return versionEsquemaXML;
	}

	public void setVersionEsquemaXML(Integer versionEsquemaXML) {
		this.versionEsquemaXML = versionEsquemaXML;
	}

	public Double getPesoTransporteTotal() {
		return pesoTransporteTotal;
	}

	public void setPesoTransporteTotal(Double pesoTransporteTotal) {
		this.pesoTransporteTotal = pesoTransporteTotal;
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

	public Double getCambioMoneda() {
		return cambioMoneda;
	}

	public void setCambioMoneda(Double cambioMoneda) {
		this.cambioMoneda = cambioMoneda;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public Integer getEstadoFirma() {
		return estadoFirma;
	}

	public void setEstadoFirma(Integer estadoFirma) {
		this.estadoFirma = estadoFirma;
	}

	public String getCorreoAlternativo() {
		return correoAlternativo;
	}

	public void setCorreoAlternativo(String correoAlternativo) {
		this.correoAlternativo = correoAlternativo;
	}

	public Integer getTieneIS() {
		return tieneIS;
	}

	public void setTieneIS(Integer tieneIS) {
		this.tieneIS = tieneIS;
	}

	public String getFechaEmisionSTR() {
		if (this.fechaEmision != null) {
			return Utils.getFechaGeneraReporte(this.getFechaEmision());
		}
		return Constantes.EMPTY;
	}

	public String getNombreCliente() {
		if (this.cliente != null) {
			return this.getCliente().getNombreCompleto();
		}
		return Constantes.EMPTY;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public String getTotalComprobanteSTR() {
		return Utils.formateadorMiles(this.totalComprobante);
	}

	public String getTotalDescuentoSTR() {
		return Utils.formateadorMiles(this.totalDescuentos);
	}

	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}

	public Double getTotalServExonerado() {
		return totalServExonerado;
	}

	public void setTotalServExonerado(Double totalServExonerado) {
		this.totalServExonerado = totalServExonerado;
	}

	public Double getTotalMercExonerada() {
		return totalMercExonerada;
	}

	public void setTotalMercExonerada(Double totalMercExonerada) {
		this.totalMercExonerada = totalMercExonerada;
	}

	public Double getTotalExonerado() {
		return totalExonerado;
	}

	public void setTotalExonerado(Double totalExonerado) {
		this.totalExonerado = totalExonerado;
	}

	public Double getTotalIVADevuelto() {
		return totalIVADevuelto;
	}

	public void setTotalIVADevuelto(Double totalIVADevuelto) {
		this.totalIVADevuelto = totalIVADevuelto;
	}

	public Double getTotalOtrosCargos() {
		return totalOtrosCargos;
	}

	public void setTotalOtrosCargos(Double totalOtrosCargos) {
		this.totalOtrosCargos = totalOtrosCargos;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

}
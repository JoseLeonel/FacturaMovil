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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

/**
 * Recepcion de la factura, aceptacion de facturas de un emisor.
 * @author Jairo Cisneros.
 * @since 15 de setiembre del 2018
 */
@Entity
@Table(name = "recepcion_factura")
public class RecepcionFactura implements Serializable {

	private static final long	serialVersionUID	= 544316204546771917L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	// 1 aceptado, 2 aceptado parcialmente, 3 rechazado.
	@Column(name = "mensaje")
	private String						mensaje;

	@Column(name = "detalle_Mensaje")
	private String						detalleMensaje;

	@Column(name = "numero_conse_receptor")
	private String						numeroConsecutivoReceptor;

	@Column(name = "estado_firma")
	private Integer						estadoFirma;

	@Column(name = "cedula_emisor")
	private String						emisorCedula;

	@Column(name = "emisor_nombre")
	private String						emisorNombre;

	@Column(name = "tipo_cedula_emisor")
	private String						emisorTipoCedula;

	@Column(name = "emisor_correo")
	private String						emisorCorreo;

	@Column(name = "emisor_telefono")
	private String						emisorTelefono;

	@Column(name = "emisor_codigo_provincia")
	private String						emisorCodigoProvincia;

	@Column(name = "emisor_provincia")
	private String						emisorProvincia;

	@Column(name = "emisor_canton")
	private String						emisorCanton;

	@Column(name = "emisor_codigo_canton")
	private String						emisorCodigoCanton;

	@Column(name = "emisor_distrito")
	private String						emisorDistrito;

	@Column(name = "emisor_codigo_distrito")
	private String						emisorCodigoDistrito;

	@Column(name = "emisor_otra_sena")
	private String						emisorOtraSena;

	@Column(name = "emisor_nombre_comercial")
	private String						emisorNombreComercial;

	@Column(name = "receptor_nombre")
	private String						receptorNombre;

	@Column(name = "cedula_receptor")
	private String						receptorCedula;

	@Column(name = "receptor_tipo_cedula")
	private String						receptorTipoCedula;

	@Column(name = "receptor_correo")
	private String						receptorCorreo;

	@Column(name = "receptor_provincia")
	private String						receptorProvincia;

	@Column(name = "receptor_codigo_provincia")
	private String						receptorCodigoProvincia;

	@Column(name = "receptor_canton")
	private String						receptorCanton;

	@Column(name = "receptor_codigo_canton")
	private String						receptorCodigoCanton;

	@Column(name = "receptor_distrito")
	private String						receptorDistrito;

	@Column(name = "receptor_codigo_distrito")
	private String						receptorCodigoDistrito;

	@Column(name = "receptor_otra_sena")
	private String						receptorOtraSena;

	@Column(name = "receptor_telefono")
	private String						receptorTelefono;

	@Column(name = "receptor_nombre_comercial")
	private String						receptorNombreComercial;

	@Column(name = "factura_consecutivo")
	private String						facturaConsecutivo;

	@Column(name = "clave")
	private String						facturaClave;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision")
	private Date							facturaFechaEmision;

	@Column(name = "fac_condicion_venta")
	private String						facturaCondicionVenta;

	@Column(name = "fac_medio_pago")
	private String						facturaMedioPago;

	@Column(name = "codigo_moneda")
	private String						facturaCodigoMoneda;

	@Column(name = "tipo_cambio")
	private Double						facturaTipoCambio;

	@Column(name = "fac_total_serv_exentos")
	private Double						facturaTotalServExentos;

	@Column(name = "fac_total_exento")
	private Double						facturaTotalExento;

	@Column(name = "fac_total_venta")
	private Double						facturaTotalVenta;

	@Column(name = "fac_total_venta_neta")
	private Double						facturaTotalVentaNeta;

	@Column(name = "total_factura")
	private Double						facturaTotalComprobante;

	@Column(name = "total_impuestos")
	private Double						facturaTotalImpuestos;

	@Column(name = "codigo_actividad")
	private String						facturaCodigoActividad;
	@Column(name = "plazo_credito")
	private String						facturaPlazoCredito;
	@Column(name = "total_serv_gravados")
	private Double						facturaTotalServGravados;
	@Column(name = "total_serv_exonerado")
	private Double						facturaTotalServExonerado;
	@Column(name = "total_mercancias_gravadas")
	private Double						facturaTotalMercanciasGravadas;
	@Column(name = "total_mercancias_exentas")
	private Double						facturaTotalMercanciasExentas;
	@Column(name = "total_mercExonerada")
	private Double						facturaTotalMercExonerada;
	@Column(name = "total_gravado")
	private Double						facturaTotalGravado;
	@Column(name = "total_exonerado")
	private Double						facturaTotalExonerado;
	@Column(name = "total_iva_devuelto")
	private Double						facturaTotalIVADevuelto;
	@Column(name = "total_otros_cargos")
	private Double						facturaTotalOtrosCargos;
	@Column(name = "total_descuentos")
	private Double						facturaTotalDescuentos;

	@Column(name = "version_doc")
	private String						version_doc;

	@Column(name = "total_impuesto_acreditar")
	private Double						totalImpuestoAcreditar;

	@Column(name = "total_gasto_aplicable")
	private Double						totalDeGastoAplicable;

	@Column(name = "condicion_impuesto")
	private String						condicionImpuesto;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@Column(name = "tipo_doc")
	private String						tipoDoc;

	@Column(name = "cod_actividad", length = 6)
	private String						codigoActividad;

	// 1 = Gasto de Inventario 2 = Gastos
	@Column(name = "tipo_gasto", columnDefinition = "INT default '1'")
	private Integer						tipoGasto;

	@Transient
	private String						detalles;

	@Column(name = "estado", columnDefinition = "INT default '1'")
	private Integer						estado;

	public RecepcionFactura(Long id, String mensaje, String detalleMensaje, String numeroConsecutivoReceptor, Integer estadoFirma, String emisorCedula, String emisorNombre, String emisorTipoCedula, String emisorCorreo, String emisorTelefono, String emisorCodigoProvincia, String emisorProvincia, String emisorCanton, String emisorCodigoCanton, String emisorDistrito, String emisorCodigoDistrito, String emisorOtraSena, String emisorNombreComercial, String receptorNombre, String receptorCedula, String receptorTipoCedula, String receptorCorreo, String receptorProvincia, String receptorCodigoProvincia, String receptorCanton, String receptorCodigoCanton, String receptorDistrito, String receptorCodigoDistrito, String receptorOtraSena, String receptorTelefono, String receptorNombreComercial,
			String facturaConsecutivo, String facturaClave, Date facturaFechaEmision, String facturaCondicionVenta, String facturaMedioPago, String facturaCodigoMoneda, Double facturaTipoCambio, Double facturaTotalServExentos, Double facturaTotalExento, Double facturaTotalVenta, Double facturaTotalVentaNeta, Double facturaTotalComprobante, Double facturaTotalImpuestos, String facturaCodigoActividad, String facturaPlazoCredito, Double facturaTotalServGravados, Double facturaTotalServExonerado, Double facturaTotalMercanciasGravadas, Double facturaTotalMercanciasExentas, Double facturaTotalMercExonerada, Double facturaTotalGravado, Double facturaTotalExonerado, Double facturaTotalIVADevuelto, Double facturaTotalOtrosCargos, Double facturaTotalDescuentos, String version_doc,
			Double totalImpuestoAcreditar, Double totalDeGastoAplicable, String condicionImpuesto, Date created_at, Date updated_at, Empresa empresa, String tipoDoc, String codigoActividad, Integer tipoGasto, String detalles, Integer estado) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.detalleMensaje = detalleMensaje;
		this.numeroConsecutivoReceptor = numeroConsecutivoReceptor;
		this.estadoFirma = estadoFirma;
		this.emisorCedula = emisorCedula;
		this.emisorNombre = emisorNombre;
		this.emisorTipoCedula = emisorTipoCedula;
		this.emisorCorreo = emisorCorreo;
		this.emisorTelefono = emisorTelefono;
		this.emisorCodigoProvincia = emisorCodigoProvincia;
		this.emisorProvincia = emisorProvincia;
		this.emisorCanton = emisorCanton;
		this.emisorCodigoCanton = emisorCodigoCanton;
		this.emisorDistrito = emisorDistrito;
		this.emisorCodigoDistrito = emisorCodigoDistrito;
		this.emisorOtraSena = emisorOtraSena;
		this.emisorNombreComercial = emisorNombreComercial;
		this.receptorNombre = receptorNombre;
		this.receptorCedula = receptorCedula;
		this.receptorTipoCedula = receptorTipoCedula;
		this.receptorCorreo = receptorCorreo;
		this.receptorProvincia = receptorProvincia;
		this.receptorCodigoProvincia = receptorCodigoProvincia;
		this.receptorCanton = receptorCanton;
		this.receptorCodigoCanton = receptorCodigoCanton;
		this.receptorDistrito = receptorDistrito;
		this.receptorCodigoDistrito = receptorCodigoDistrito;
		this.receptorOtraSena = receptorOtraSena;
		this.receptorTelefono = receptorTelefono;
		this.receptorNombreComercial = receptorNombreComercial;
		this.facturaConsecutivo = facturaConsecutivo;
		this.facturaClave = facturaClave;
		this.facturaFechaEmision = facturaFechaEmision;
		this.facturaCondicionVenta = facturaCondicionVenta;
		this.facturaMedioPago = facturaMedioPago;
		this.facturaCodigoMoneda = facturaCodigoMoneda;
		this.facturaTipoCambio = facturaTipoCambio;
		this.facturaTotalServExentos = facturaTotalServExentos;
		this.facturaTotalExento = facturaTotalExento;
		this.facturaTotalVenta = facturaTotalVenta;
		this.facturaTotalVentaNeta = facturaTotalVentaNeta;
		this.facturaTotalComprobante = facturaTotalComprobante;
		this.facturaTotalImpuestos = facturaTotalImpuestos;
		this.facturaCodigoActividad = facturaCodigoActividad;
		this.facturaPlazoCredito = facturaPlazoCredito;
		this.facturaTotalServGravados = facturaTotalServGravados;
		this.facturaTotalServExonerado = facturaTotalServExonerado;
		this.facturaTotalMercanciasGravadas = facturaTotalMercanciasGravadas;
		this.facturaTotalMercanciasExentas = facturaTotalMercanciasExentas;
		this.facturaTotalMercExonerada = facturaTotalMercExonerada;
		this.facturaTotalGravado = facturaTotalGravado;
		this.facturaTotalExonerado = facturaTotalExonerado;
		this.facturaTotalIVADevuelto = facturaTotalIVADevuelto;
		this.facturaTotalOtrosCargos = facturaTotalOtrosCargos;
		this.facturaTotalDescuentos = facturaTotalDescuentos;
		this.version_doc = version_doc;
		this.totalImpuestoAcreditar = totalImpuestoAcreditar;
		this.totalDeGastoAplicable = totalDeGastoAplicable;
		this.condicionImpuesto = condicionImpuesto;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.empresa = empresa;
		this.tipoDoc = Utils.obtenerTipoDocumentoConsecutivo(facturaConsecutivo);
		this.codigoActividad = codigoActividad;
		this.tipoGasto = tipoGasto;
		this.detalles = detalles;
		this.estado = estado;
	}

	public RecepcionFactura() {
		super();
	}

	public String getCreated_atSTR() {
		if (this.created_at != null) {
			return Utils.getFechaGeneraReporte(this.getCreated_at());
		}
		return Constantes.EMPTY;
	}

	public String getFechaEmisionSTR() {
		if (this.facturaFechaEmision != null) {
			return Utils.getFechaGeneraReporte(this.getFacturaFechaEmision());
		}
		return Constantes.EMPTY;
	}

	public String getTotalImpuestosSTR() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.facturaTotalImpuestos != null ? Utils.formateadorMiles(this.facturaTotalImpuestos * -1) : Utils.formateadorMiles(this.facturaTotalImpuestos);
		} else {
			return Utils.formateadorMiles(this.facturaTotalImpuestos);
		}

	}

	public String getTotalFacturaSTR() {
		if (tipoDoc != null && tipoDoc.equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
			return this.facturaTotalComprobante != null ? Utils.formateadorMiles(this.facturaTotalComprobante * -1) : Utils.formateadorMiles(this.facturaTotalComprobante);
		} else {
			return Utils.formateadorMiles(this.facturaTotalComprobante);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Integer getEstado() {
		return estado;
	}

	
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(Integer tipoGasto) {
		this.tipoGasto = tipoGasto;
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

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDetalleMensaje() {
		return detalleMensaje;
	}

	public void setDetalleMensaje(String detalleMensaje) {
		this.detalleMensaje = detalleMensaje;
	}

	public Integer getEstadoFirma() {
		return estadoFirma;
	}

	public void setEstadoFirma(Integer estadoFirma) {
		this.estadoFirma = estadoFirma;
	}

	public String getEmisorCedula() {
		return emisorCedula;
	}

	public void setEmisorCedula(String emisorCedula) {
		this.emisorCedula = emisorCedula;
	}

	public String getEmisorTelefono() {
		return emisorTelefono;
	}

	public void setEmisorTelefono(String emisorTelefono) {
		this.emisorTelefono = emisorTelefono;
	}

	public String getEmisorNombre() {
		return emisorNombre;
	}

	public void setEmisorNombre(String emisorNombre) {
		this.emisorNombre = emisorNombre;
	}

	public String getEmisorTipoCedula() {
		return emisorTipoCedula;
	}

	public void setEmisorTipoCedula(String emisorTipoCedula) {
		this.emisorTipoCedula = emisorTipoCedula;
	}

	public String getEmisorCorreo() {
		return emisorCorreo;
	}

	public void setEmisorCorreo(String emisorCorreo) {
		this.emisorCorreo = emisorCorreo;
	}

	public String getEmisorCodigoProvincia() {
		return emisorCodigoProvincia;
	}

	public void setEmisorCodigoProvincia(String emisorCodigoProvincia) {
		this.emisorCodigoProvincia = emisorCodigoProvincia;
	}

	public String getEmisorProvincia() {
		return emisorProvincia;
	}

	public void setEmisorProvincia(String emisorProvincia) {
		this.emisorProvincia = emisorProvincia;
	}

	public String getEmisorCanton() {
		return emisorCanton;
	}

	public void setEmisorCanton(String emisorCanton) {
		this.emisorCanton = emisorCanton;
	}

	public String getEmisorCodigoCanton() {
		return emisorCodigoCanton;
	}

	public void setEmisorCodigoCanton(String emisorCodigoCanton) {
		this.emisorCodigoCanton = emisorCodigoCanton;
	}

	public String getEmisorDistrito() {
		return emisorDistrito;
	}

	public void setEmisorDistrito(String emisorDistrito) {
		this.emisorDistrito = emisorDistrito;
	}

	public String getEmisorCodigoDistrito() {
		return emisorCodigoDistrito;
	}

	public void setEmisorCodigoDistrito(String emisorCodigoDistrito) {
		this.emisorCodigoDistrito = emisorCodigoDistrito;
	}

	public String getEmisorOtraSena() {
		return emisorOtraSena;
	}

	public void setEmisorOtraSena(String emisorOtraSena) {
		this.emisorOtraSena = emisorOtraSena;
	}

	public String getReceptorNombre() {
		return receptorNombre;
	}

	public void setReceptorNombre(String receptorNombre) {
		this.receptorNombre = receptorNombre;
	}

	public String getReceptorCedula() {
		return receptorCedula;
	}

	public void setReceptorCedula(String receptorCedula) {
		this.receptorCedula = receptorCedula;
	}

	public String getReceptorTipoCedula() {
		return receptorTipoCedula;
	}

	public void setReceptorTipoCedula(String receptorTipoCedula) {
		this.receptorTipoCedula = receptorTipoCedula;
	}

	public String getReceptorCorreo() {
		return receptorCorreo;
	}

	public void setReceptorCorreo(String receptorCorreo) {
		this.receptorCorreo = receptorCorreo;
	}

	public String getReceptorProvincia() {
		return receptorProvincia;
	}

	public void setReceptorProvincia(String receptorProvincia) {
		this.receptorProvincia = receptorProvincia;
	}

	public String getReceptorCodigoProvincia() {
		return receptorCodigoProvincia;
	}

	public void setReceptorCodigoProvincia(String receptorCodigoProvincia) {
		this.receptorCodigoProvincia = receptorCodigoProvincia;
	}

	public String getReceptorCanton() {
		return receptorCanton;
	}

	public void setReceptorCanton(String receptorCanton) {
		this.receptorCanton = receptorCanton;
	}

	public String getReceptorCodigoCanton() {
		return receptorCodigoCanton;
	}

	public void setReceptorCodigoCanton(String receptorCodigoCanton) {
		this.receptorCodigoCanton = receptorCodigoCanton;
	}

	public String getReceptorDistrito() {
		return receptorDistrito;
	}

	public void setReceptorDistrito(String receptorDistrito) {
		this.receptorDistrito = receptorDistrito;
	}

	public String getReceptorCodigoDistrito() {
		return receptorCodigoDistrito;
	}

	public void setReceptorCodigoDistrito(String receptorCodigoDistrito) {
		this.receptorCodigoDistrito = receptorCodigoDistrito;
	}

	public String getReceptorOtraSena() {
		return receptorOtraSena;
	}

	public void setReceptorOtraSena(String receptorOtraSena) {
		this.receptorOtraSena = receptorOtraSena;
	}

	public String getReceptorTelefono() {
		return receptorTelefono;
	}

	public void setReceptorTelefono(String receptorTelefono) {
		this.receptorTelefono = receptorTelefono;
	}

	public String getReceptorNombreComercial() {
		return receptorNombreComercial;
	}

	public void setReceptorNombreComercial(String receptorNombreComercial) {
		this.receptorNombreComercial = receptorNombreComercial;
	}

	public String getFacturaConsecutivo() {
		return facturaConsecutivo;
	}

	public void setFacturaConsecutivo(String facturaConsecutivo) {
		this.facturaConsecutivo = facturaConsecutivo;
	}

	public String getFacturaClave() {
		return facturaClave;
	}

	public String getNumeroConsecutivoReceptor() {
		return numeroConsecutivoReceptor;
	}

	public void setNumeroConsecutivoReceptor(String numeroConsecutivoReceptor) {
		this.numeroConsecutivoReceptor = numeroConsecutivoReceptor;
	}

	public void setFacturaClave(String facturaClave) {
		this.facturaClave = facturaClave;
	}

	public Date getFacturaFechaEmision() {
		return facturaFechaEmision;
	}

	public void setFacturaFechaEmision(Date facturaFechaEmision) {
		this.facturaFechaEmision = facturaFechaEmision;
	}

	public String getFacturaCondicionVenta() {
		return facturaCondicionVenta;
	}

	public void setFacturaCondicionVenta(String facturaCondicionVenta) {
		this.facturaCondicionVenta = facturaCondicionVenta;
	}

	public String getFacturaMedioPago() {
		return facturaMedioPago;
	}

	public void setFacturaMedioPago(String facturaMedioPago) {
		this.facturaMedioPago = facturaMedioPago;
	}

	public String getFacturaCodigoMoneda() {
		return facturaCodigoMoneda;
	}

	public void setFacturaCodigoMoneda(String facturaCodigoMoneda) {
		this.facturaCodigoMoneda = facturaCodigoMoneda;
	}

	public Double getFacturaTipoCambio() {
		return facturaTipoCambio;
	}

	public void setFacturaTipoCambio(Double facturaTipoCambio) {
		this.facturaTipoCambio = facturaTipoCambio;
	}

	public Double getFacturaTotalServExentos() {
		return facturaTotalServExentos;
	}

	public void setFacturaTotalServExentos(Double facturaTotalServExentos) {
		this.facturaTotalServExentos = facturaTotalServExentos;
	}

	public Double getFacturaTotalExento() {
		return facturaTotalExento;
	}

	public void setFacturaTotalExento(Double facturaTotalExento) {
		this.facturaTotalExento = facturaTotalExento;
	}

	public Double getFacturaTotalVenta() {
		return facturaTotalVenta;
	}

	public void setFacturaTotalVenta(Double facturaTotalVenta) {
		this.facturaTotalVenta = facturaTotalVenta;
	}

	public Double getFacturaTotalVentaNeta() {
		return facturaTotalVentaNeta;
	}

	public void setFacturaTotalVentaNeta(Double facturaTotalVentaNeta) {
		this.facturaTotalVentaNeta = facturaTotalVentaNeta;
	}

	public Double getFacturaTotalComprobante() {
		return facturaTotalComprobante;
	}

	public void setFacturaTotalComprobante(Double facturaTotalComprobante) {
		this.facturaTotalComprobante = facturaTotalComprobante;
	}

	public Double getFacturaTotalImpuestos() {
		return facturaTotalImpuestos;
	}

	public void setFacturaTotalImpuestos(Double facturaTotalImpuestos) {
		this.facturaTotalImpuestos = facturaTotalImpuestos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getEmisorNombreComercial() {
		return emisorNombreComercial;
	}

	public void setEmisorNombreComercial(String emisorNombreComercial) {
		this.emisorNombreComercial = emisorNombreComercial;
	}

	public String getFacturaPlazoCredito() {
		return facturaPlazoCredito;
	}

	public void setFacturaPlazoCredito(String facturaPlazoCredito) {
		this.facturaPlazoCredito = facturaPlazoCredito;
	}

	public String getFacturaCodigoActividad() {
		return facturaCodigoActividad;
	}

	public Double getFacturaTotalServGravados() {
		return facturaTotalServGravados;
	}

	public void setFacturaTotalServGravados(Double facturaTotalServGravados) {
		this.facturaTotalServGravados = facturaTotalServGravados;
	}

	public Double getFacturaTotalServExonerado() {
		return facturaTotalServExonerado;
	}

	public void setFacturaTotalServExonerado(Double facturaTotalServExonerado) {
		this.facturaTotalServExonerado = facturaTotalServExonerado;
	}

	public Double getFacturaTotalMercanciasGravadas() {
		return facturaTotalMercanciasGravadas;
	}

	public void setFacturaTotalMercanciasGravadas(Double facturaTotalMercanciasGravadas) {
		this.facturaTotalMercanciasGravadas = facturaTotalMercanciasGravadas;
	}

	public Double getFacturaTotalMercanciasExentas() {
		return facturaTotalMercanciasExentas;
	}

	public void setFacturaTotalMercanciasExentas(Double facturaTotalMercanciasExentas) {
		this.facturaTotalMercanciasExentas = facturaTotalMercanciasExentas;
	}

	public Double getFacturaTotalMercExonerada() {
		return facturaTotalMercExonerada;
	}

	public void setFacturaTotalMercExonerada(Double facturaTotalMercExonerada) {
		this.facturaTotalMercExonerada = facturaTotalMercExonerada;
	}

	public Double getFacturaTotalGravado() {
		return facturaTotalGravado;
	}

	public void setFacturaTotalGravado(Double facturaTotalGravado) {
		this.facturaTotalGravado = facturaTotalGravado;
	}

	public Double getFacturaTotalExonerado() {
		return facturaTotalExonerado;
	}

	public void setFacturaTotalExonerado(Double facturaTotalExonerado) {
		this.facturaTotalExonerado = facturaTotalExonerado;
	}

	public Double getFacturaTotalIVADevuelto() {
		return facturaTotalIVADevuelto;
	}

	public void setFacturaTotalIVADevuelto(Double facturaTotalIVADevuelto) {
		this.facturaTotalIVADevuelto = facturaTotalIVADevuelto;
	}

	public Double getFacturaTotalOtrosCargos() {
		return facturaTotalOtrosCargos;
	}

	public void setFacturaTotalOtrosCargos(Double facturaTotalOtrosCargos) {
		this.facturaTotalOtrosCargos = facturaTotalOtrosCargos;
	}

	public Double getFacturaTotalDescuentos() {
		return facturaTotalDescuentos;
	}

	public void setFacturaTotalDescuentos(Double facturaTotalDescuentos) {
		this.facturaTotalDescuentos = facturaTotalDescuentos;
	}

	public String getTipoDocumentoStr() {
		return Utils.obtenerDescripcionTipoDocumento(this.tipoDoc);
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public String getVersion_doc() {
		return version_doc;
	}

	public void setVersion_doc(String version_doc) {
		this.version_doc = version_doc;
	}

	public Double getTotalImpuestoAcreditar() {
		return totalImpuestoAcreditar;
	}

	public void setTotalImpuestoAcreditar(Double totalImpuestoAcreditar) {
		this.totalImpuestoAcreditar = totalImpuestoAcreditar;
	}

	public Double getTotalDeGastoAplicable() {
		return totalDeGastoAplicable;
	}

	public void setTotalDeGastoAplicable(Double totalDeGastoAplicable) {
		this.totalDeGastoAplicable = totalDeGastoAplicable;
	}

	public String getCondicionImpuesto() {
		return condicionImpuesto;
	}

	public void setCondicionImpuesto(String condicionImpuesto) {
		this.condicionImpuesto = condicionImpuesto;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public void setFacturaCodigoActividad(String facturaCodigoActividad) {
		this.facturaCodigoActividad = facturaCodigoActividad;
	}

}
package com.emprendesoftcr.web.command;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Empresa;

public class RecepcionFacturaAceptacionCommand {

	private Long		id;

	private String	mensaje;

	private String	detalleMensaje;

	private String	numeroConsecutivoReceptor;

	private Integer	estadoFirma;

	private String	emisorCedula;

	private String	emisorNombre;

	private String	emisorTipoCedula;

	private String	emisorCorreo;

	private String	emisorTelefono;

	private String	emisorCodigoProvincia;

	private String	emisorProvincia;

	private String	emisorCanton;

	private String	emisorCodigoCanton;

	private String	emisorDistrito;

	private String	emisorCodigoDistrito;

	private String	emisorOtraSena;

	private String	receptorNombre;

	private String	receptorCedula;

	private String	receptorTipoCedula;

	private String	receptorCorreo;

	private String	receptorProvincia;

	private String	receptorCodigoProvincia;

	private String	receptorCanton;

	private String	receptorCodigoCanton;

	private String	receptorDistrito;

	private String	receptorCodigoDistrito;

	private String	receptorOtraSena;

	private String	receptorTelefono;

	private String	receptorNombreComercial;

	private String	facturaConsecutivo;

	private String	facturaClave;

	private Date		facturaFechaEmision;

	private String	facturaCondicionVenta;

	private String	facturaMedioPago;

	private String	facturaCodigoMoneda;

	private Double	facturaTipoCambio;

	private Double	facturaTotalServExentos;

	private Double	facturaTotalExento;

	private Double	facturaTotalVenta;

	private Double	facturaTotalVentaNeta;

	private Double	facturaTotalComprobante;

	private Double	facturaTotalImpuestos;

	private Date		created_at;

	private Date		updated_at;

	private String	tipoDoc;

	private String	codigoActividad;

	private String	facturaTipoDocumentoOtroCargo;

	private String	facturaDetalleOtroCargo;

	private Double	facturaMontoCargoOtroCargo;

	private Double	facturaTotalServGravados;

	private Double	facturaTotalMercanciasGravadas;

	private Double	facturaTotalMercanciasExentas;

	private Double	facturaTotalMercExonerada;

	private Double	facturaTotalGravado;

	private Double	facturaTotalExonerado;

	private Double	facturaTotalDescuentos;

	private Double	facturaTotalIVADevuelto;

	private Double	facturaTotalOtrosCargos;
	
	private String	detallesFactura;

	private Empresa	empresa;

	public RecepcionFacturaAceptacionCommand(Long id, String mensaje, String detalleMensaje, String numeroConsecutivoReceptor, Integer estadoFirma, String emisorCedula, String emisorNombre, String emisorTipoCedula, String emisorCorreo, String emisorTelefono, String emisorCodigoProvincia, String emisorProvincia, String emisorCanton, String emisorCodigoCanton, String emisorDistrito, String emisorCodigoDistrito, String emisorOtraSena, String receptorNombre, String receptorCedula, String receptorTipoCedula, String receptorCorreo, String receptorProvincia, String receptorCodigoProvincia, String receptorCanton, String receptorCodigoCanton, String receptorDistrito, String receptorCodigoDistrito, String receptorOtraSena, String receptorTelefono, String receptorNombreComercial,
			String facturaConsecutivo, String facturaClave, Date facturaFechaEmision, String facturaCondicionVenta, String facturaMedioPago, String facturaCodigoMoneda, Double facturaTipoCambio, Double facturaTotalServExentos, Double facturaTotalExento, Double facturaTotalVenta, Double facturaTotalVentaNeta, Double facturaTotalComprobante, Double facturaTotalImpuestos, Date created_at, Date updated_at, String tipoDoc, String codigoActividad, String facturaTipoDocumentoOtroCargo, String facturaDetalleOtroCargo, Double facturaMontoCargoOtroCargo, Double facturaTotalServGravados, Double facturaTotalMercanciasGravadas, Double facturaTotalMercanciasExentas, Double facturaTotalMercExonerada, Double facturaTotalGravado, Double facturaTotalExonerado, Double facturaTotalDescuentos,
			Double facturaTotalIVADevuelto, Double facturaTotalOtrosCargos, Empresa empresa , String detallesFactura) {
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
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.tipoDoc = Utils.obtenerTipoDocumentoConsecutivo(facturaConsecutivo);
		this.codigoActividad = codigoActividad;
		this.facturaTipoDocumentoOtroCargo = facturaTipoDocumentoOtroCargo;
		this.facturaDetalleOtroCargo = facturaDetalleOtroCargo;
		this.facturaMontoCargoOtroCargo = facturaMontoCargoOtroCargo;
		this.facturaTotalServGravados = facturaTotalServGravados;
		this.facturaTotalMercanciasGravadas = facturaTotalMercanciasGravadas;
		this.facturaTotalMercanciasExentas = facturaTotalMercanciasExentas;
		this.facturaTotalMercExonerada = facturaTotalMercExonerada;
		this.facturaTotalGravado = facturaTotalGravado;
		this.facturaTotalExonerado = facturaTotalExonerado;
		this.facturaTotalDescuentos = facturaTotalDescuentos;
		this.facturaTotalIVADevuelto = facturaTotalIVADevuelto;
		this.facturaTotalOtrosCargos = facturaTotalOtrosCargos;
		this.empresa = empresa;
		this.detallesFactura = detallesFactura;
	}

	public RecepcionFacturaAceptacionCommand() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getDetallesFactura() {
		return detallesFactura;
	}

	
	public void setDetallesFactura(String detallesFactura) {
		this.detallesFactura = detallesFactura;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public String getNumeroConsecutivoReceptor() {
		return numeroConsecutivoReceptor;
	}

	public void setNumeroConsecutivoReceptor(String numeroConsecutivoReceptor) {
		this.numeroConsecutivoReceptor = numeroConsecutivoReceptor;
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

	public String getEmisorTelefono() {
		return emisorTelefono;
	}

	public void setEmisorTelefono(String emisorTelefono) {
		this.emisorTelefono = emisorTelefono;
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

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public String getFacturaTipoDocumentoOtroCargo() {
		return facturaTipoDocumentoOtroCargo;
	}

	public void setFacturaTipoDocumentoOtroCargo(String facturaTipoDocumentoOtroCargo) {
		this.facturaTipoDocumentoOtroCargo = facturaTipoDocumentoOtroCargo;
	}

	public String getFacturaDetalleOtroCargo() {
		return facturaDetalleOtroCargo;
	}

	public void setFacturaDetalleOtroCargo(String facturaDetalleOtroCargo) {
		this.facturaDetalleOtroCargo = facturaDetalleOtroCargo;
	}

	public Double getFacturaMontoCargoOtroCargo() {
		return facturaMontoCargoOtroCargo;
	}

	public void setFacturaMontoCargoOtroCargo(Double facturaMontoCargoOtroCargo) {
		this.facturaMontoCargoOtroCargo = facturaMontoCargoOtroCargo;
	}

	public Double getFacturaTotalServGravados() {
		return facturaTotalServGravados;
	}

	public void setFacturaTotalServGravados(Double facturaTotalServGravados) {
		this.facturaTotalServGravados = facturaTotalServGravados;
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

	public Double getFacturaTotalDescuentos() {
		return facturaTotalDescuentos;
	}

	public void setFacturaTotalDescuentos(Double facturaTotalDescuentos) {
		this.facturaTotalDescuentos = facturaTotalDescuentos;
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

}

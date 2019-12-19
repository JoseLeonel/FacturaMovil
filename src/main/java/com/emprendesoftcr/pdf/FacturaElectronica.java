/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emprendesoftcr.pdf;

import java.util.ArrayList;
import java.util.List;

import com.emprendesoftcr.modelo.Empresa;

public class FacturaElectronica {

	private String													_logo;
	private String													_emisorNombre;
	private String													_emisorNombreComercial;
	private String													_emisorDireccion;
	private String													_emisorCedula;
	private String													_emisorTelefono;
	private String													_emisorCorreo;
	private String													_emisorFax;
	private String													_emisorProvincia;
	private String													_emisorDistrito;
	private String													_emisorCanton;

	private String													_clienteNombre;
	private String													_clienteNombreComercial;
	private String													_clienteCorreo;
	private String													_clienteCedula;
	private String													_clienteTelefono;
	private String													_clienteProvincia;
	private String													_clienteCanton;
	private String													_clienteDistrito;
	private String													_clienteBarrio;
	private String													_clienteDireccion;
	

	private String													_nota;
	private String													_codigoActividadComercial;

	private String													_tipoDocumento;
	private String													_clave;
	private String													_consecutivo;
	private String													consecutivoProforma;
	private String													_fechaEmision;
	private String													_plazoCredito;
	private String													_condicionVenta;
	private String													medioEfectivo;

	private String													medioTarjeta;
	private Integer													esquemaXML;

	private String													medioBanco;
	private String													_moneda;
	private String													_tipoCambio;
	private String													_referencia;
	private String													referenciaTipoDoc;

	private String													referenciaNumero;

	private String													referenciaCodigo;
	private String													referenciaRazon;
	private String													referenciaFechaEmision;
	private Double													totalOtrosCargos;

	private String													_footerObservaciones;
	private String													_footerFormaPago;
	private String													_footerMoneda;
	private String													_footerCondicionVenta;

	private double													_footerTipoCambio;
	private Double													_footerTotalServiciosGravados;
	private Double													_footerTotalMercanciasGravadas;
	private Double													_footerTotalServiciosExentos;
	private Double													_footerTotalGravado;
	private Double													_footerTotalExento;
	private Double													_footerTotalVenta;
	private Double													_footerTotalDescuento;
	private Double													_footerTotalImpuesto;
	private Double													_footerTotalVentaNeta;
	private Double													_footerTotalComprobante;
	private Double                           montoExoneracion;
	private String                           numeroDocumentoExoneracion;
	private String                           fechaExoneracion;
	private String                           cuenta1;
	private String                           cuenta2;
	private String                           cuenta3;
	private String                           cuenta4;
	private String                           cuenta5;
	private String                           cuenta6;
	private String                           cuenta7;
	private String                           cuenta8;
	private Integer                          aplicaFacturaElectronica;
	
	private Empresa  empresa;

	private List<DetalleFacturaElectronica>	_detalleFacturaElectronica;

	public FacturaElectronica() {
		this._detalleFacturaElectronica = new ArrayList<DetalleFacturaElectronica>();
	}

	public String getReferenciaFechaEmision() {
		return referenciaFechaEmision;
	}

	public void setReferenciaFechaEmision(String referenciaFechaEmision) {
		this.referenciaFechaEmision = referenciaFechaEmision;
	}
	
	

	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getEmisorNombre() {
		return _emisorNombre;
	}

	public void setEmisorNombre(String _emisorNombre) {
		this._emisorNombre = _emisorNombre;
	}
	
	public String getEmisorNombreComercial() {
		return _emisorNombreComercial;
	}

	public void setEmisorNombreComercial(String _emisorNombreComercial) {
		this._emisorNombreComercial = _emisorNombreComercial;
	}

	public String getEmisorCorreo() {
		return _emisorCorreo;
	}

	public void setEmisorCorreo(String _emisorCorreo) {
		this._emisorCorreo = _emisorCorreo;
	}
	
	public String getEmisorDireccion() {
		return _emisorDireccion;
	}

	public void setEmisorDireccion(String _emisorDireccion) {
		this._emisorDireccion = _emisorDireccion;
	}

	public String getEmisorProvincia() {
		return _emisorProvincia;
	}

	public void setEmisorProvincia(String _emisorProvincia) {
		this._emisorProvincia = _emisorProvincia;
	}

	public String getEmisorDistrito() {
		return _emisorDistrito;
	}

	public void setEmisorDistrito(String _emisorDistrito) {
		this._emisorDistrito = _emisorDistrito;
	}

	public String getEmisorCedula() {
		return _emisorCedula;
	}

	public void setEmisorCedula(String _emisorCedula) {
		this._emisorCedula = _emisorCedula;
	}

	public String getEmisorTelefono() {
		return _emisorTelefono;
	}

	public void setEmisorTelefono(String _emisorTelefono) {
		this._emisorTelefono = _emisorTelefono;
	}

	public String getEmisorCanton() {
		return _emisorCanton;
	}

	public void setEmisorCanton(String _emisorCanton) {
		this._emisorCanton = _emisorCanton;
	}

	public String getClienteNombre() {
		return _clienteNombre;
	}

	public void setClienteNombre(String _clienteNombre) {
		this._clienteNombre = _clienteNombre;
	}

	public String getClienteCedula() {
		return _clienteCedula;
	}

	public void setClienteCedula(String _clienteCedula) {
		this._clienteCedula = _clienteCedula;
	}

	public String getFooterObservaciones() {
		return _footerObservaciones;
	}

	public void setFooterObservaciones(String _footerObservaciones) {
		this._footerObservaciones = _footerObservaciones;
	}

	public String getFooterFormaPago() {
		return _footerFormaPago;
	}

	public void setFooterFormaPago(String _footerFormaPago) {
		this._footerFormaPago = _footerFormaPago;
	}

	public String getFooterMoneda() {
		return _footerMoneda;
	}

	public void setFooterMoneda(String _footerMoneda) {
		this._footerMoneda = _footerMoneda;
	}

	public Double getFooterTipoCambio() {
		return _footerTipoCambio;
	}

	public void setFooterTipoCambio(Double _footerTipoCambio) {
		this._footerTipoCambio = _footerTipoCambio;
	}

	public Double getFooterTotalServiciosGravados() {
		return _footerTotalServiciosGravados;
	}

	public void setFooterTotalServiciosGravados(Double _footerTotalServiciosGravados) {
		this._footerTotalServiciosGravados = _footerTotalServiciosGravados;
	}

	public Double getFooterTotalMercanciasGravadas() {
		return _footerTotalMercanciasGravadas;
	}

	public void setFooterTotalMercanciasGravadas(Double _footerTotalMercanciasGravadas) {
		this._footerTotalMercanciasGravadas = _footerTotalMercanciasGravadas;
	}

	public Double getFooterTotalServiciosExentos() {
		return _footerTotalServiciosExentos;
	}

	public void setFooterTotalServiciosExentos(Double _footerTotalServiciosExentos) {
		this._footerTotalServiciosExentos = _footerTotalServiciosExentos;
	}

	public Double getFooterTotalGravado() {
		return _footerTotalGravado;
	}

	public void setFooterTotalGravado(Double _footerTotalGravado) {
		this._footerTotalGravado = _footerTotalGravado;
	}

	public Double getFooterTotalExento() {
		return _footerTotalExento;
	}

	public void setFooterTotalExento(Double _footerTotalExento) {
		this._footerTotalExento = _footerTotalExento;
	}

	public Double getFooterTotalVenta() {
		return _footerTotalVenta;
	}

	public void setFooterTotalVenta(Double _footerTotalVenta) {
		this._footerTotalVenta = _footerTotalVenta;
	}

	public Double getFooterTotalDescuento() {
		return _footerTotalDescuento;
	}

	public void setFooterTotalDescuento(Double _footerTotalDescuento) {
		this._footerTotalDescuento = _footerTotalDescuento;
	}

	public Double getFooterTotalImpuesto() {
		return _footerTotalImpuesto;
	}

	public void setFooterTotalImpuesto(Double _footerTotalImpuesto) {
		this._footerTotalImpuesto = _footerTotalImpuesto;
	}

	public Double getFooterTotalVentaNeta() {
		return _footerTotalVentaNeta;
	}

	public void setFooterTotalVentaNeta(Double _footerTotalVentaNeta) {
		this._footerTotalVentaNeta = _footerTotalVentaNeta;
	}

	public Double getFooterTotalComprobante() {
		return _footerTotalComprobante;
	}

	public void setFooterTotalComprobante(Double _footerTotalComprobante) {
		this._footerTotalComprobante = _footerTotalComprobante;
	}

	public List<DetalleFacturaElectronica> getDetalleFacturaElectronica() {
		return _detalleFacturaElectronica;
	}

	public void setDetalleFacturaElectronica(List<DetalleFacturaElectronica> _detalleFacturaElectronica) {
		this._detalleFacturaElectronica = _detalleFacturaElectronica;
	}

	public String getFooterCondicionVenta() {
		return _footerCondicionVenta;
	}

	public void setFooterCondicionVenta(String _footerCondicionVenta) {
		this._footerCondicionVenta = _footerCondicionVenta;
	}

	public String getEmisorFax() {
		return _emisorFax;
	}

	public void setEmisorFax(String _emisorFax) {
		this._emisorFax = _emisorFax;
	}

	public String getClienteNombreComercial() {
		return _clienteNombreComercial;
	}

	public void setClienteNombreComercial(String _clienteNombreComercial) {
		this._clienteNombreComercial = _clienteNombreComercial;
	}

	public String getClienteCorreo() {
		return _clienteCorreo;
	}

	public void setClienteCorreo(String _clienteCorreo) {
		this._clienteCorreo = _clienteCorreo;
	}

	public String getClienteTelefono() {
		return _clienteTelefono;
	}

	public void setClienteTelefono(String _clienteTelefono) {
		this._clienteTelefono = _clienteTelefono;
	}

	public String get_clienteProvincia() {
		return _clienteProvincia;
	}

	public void set_clienteProvincia(String _clienteProvincia) {
		this._clienteProvincia = _clienteProvincia;
	}

	public String get_clienteCanton() {
		return _clienteCanton;
	}

	public void set_clienteCanton(String _clienteCanton) {
		this._clienteCanton = _clienteCanton;
	}

	public String get_clienteDistrito() {
		return _clienteDistrito;
	}

	public void set_clienteDistrito(String _clienteDistrito) {
		this._clienteDistrito = _clienteDistrito;
	}

	public String get_clienteBarrio() {
		return _clienteBarrio;
	}

	public void set_clienteBarrio(String _clienteBarrio) {
		this._clienteBarrio = _clienteBarrio;
	}

	public String get_clienteDireccion() {
		return _clienteDireccion;
	}

	public void set_clienteDireccion(String _clienteDireccion) {
		this._clienteDireccion = _clienteDireccion;
	}

	public String getTipoDocumento() {
		return _tipoDocumento;
	}

	public void setTipoDocumento(String _tipoDocumento) {
		this._tipoDocumento = _tipoDocumento;
	}

	public String getClave() {
		return _clave;
	}

	public void setClave(String _clave) {
		this._clave = _clave;
	}

	public String getConsecutivo() {
		return _consecutivo;
	}

	public void setConsecutivo(String _consecutivo) {
		this._consecutivo = _consecutivo;
	}

	
	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	
	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}

	public String getFechaEmision() {
		return _fechaEmision;
	}

	public void setFechaEmision(String _fechaEmision) {
		this._fechaEmision = _fechaEmision;
	}

	public String getPlazoCredito() {
		return _plazoCredito;
	}

	public void setPlazoCredito(String _plazoCredito) {
		this._plazoCredito = _plazoCredito;
	}

	public String getCondicionVenta() {
		return _condicionVenta;
	}

	public void setCondicionVenta(String _condicionVenta) {
		this._condicionVenta = _condicionVenta;
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

	public String getMoneda() {
		return _moneda;
	}

	public void setMoneda(String _moneda) {
		this._moneda = _moneda;
	}

	
	public Integer getEsquemaXML() {
		return esquemaXML;
	}

	
	public void setEsquemaXML(Integer esquemaXML) {
		this.esquemaXML = esquemaXML;
	}

	public String getTipoCambio() {
		return _tipoCambio;
	}

	public void setTipoCambio(String _tipoCambio) {
		this._tipoCambio = _tipoCambio;
	}

	public String getReferencia() {
		return _referencia;
	}

	public void setReferencia(String _referencia) {
		this._referencia = _referencia;
	}

	public String get_logo() {
		return _logo;
	}

	public void set_logo(String _logo) {
		this._logo = _logo;
	}

	public String get_nota() {
		return _nota;
	}

	public void set_nota(String _nota) {
		this._nota = _nota;
	}
	

	
	public Double getTotalOtrosCargos() {
		return totalOtrosCargos;
	}

	
	public void setTotalOtrosCargos(Double totalOtrosCargos) {
		this.totalOtrosCargos = totalOtrosCargos;
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

	
	public String get_codigoActividadComercial() {
		return _codigoActividadComercial;
	}

	
	public void set_codigoActividadComercial(String _codigoActividadComercial) {
		this._codigoActividadComercial = _codigoActividadComercial;
	}

	
	public Double getMontoExoneracion() {
		return montoExoneracion;
	}

	
	public void setMontoExoneracion(Double montoExoneracion) {
		this.montoExoneracion = montoExoneracion;
	}

	
	public String getNumeroDocumentoExoneracion() {
		return numeroDocumentoExoneracion;
	}

	
	public void setNumeroDocumentoExoneracion(String numeroDocumentoExoneracion) {
		this.numeroDocumentoExoneracion = numeroDocumentoExoneracion;
	}

	
	public String getFechaExoneracion() {
		return fechaExoneracion;
	}

	
	public void setFechaExoneracion(String fechaExoneracion) {
		this.fechaExoneracion = fechaExoneracion;
	}

	
	public String getCuenta1() {
		return cuenta1;
	}

	
	public void setCuenta1(String cuenta1) {
		this.cuenta1 = cuenta1;
	}

	
	public String getCuenta2() {
		return cuenta2;
	}

	
	public void setCuenta2(String cuenta2) {
		this.cuenta2 = cuenta2;
	}

	
	public String getCuenta3() {
		return cuenta3;
	}

	
	public void setCuenta3(String cuenta3) {
		this.cuenta3 = cuenta3;
	}

	
	public String getCuenta4() {
		return cuenta4;
	}

	
	public void setCuenta4(String cuenta4) {
		this.cuenta4 = cuenta4;
	}

	
	public String getCuenta5() {
		return cuenta5;
	}

	
	public void setCuenta5(String cuenta5) {
		this.cuenta5 = cuenta5;
	}

	
	public String getCuenta6() {
		return cuenta6;
	}

	
	public void setCuenta6(String cuenta6) {
		this.cuenta6 = cuenta6;
	}

	
	public String getCuenta7() {
		return cuenta7;
	}

	
	public void setCuenta7(String cuenta7) {
		this.cuenta7 = cuenta7;
	}

	
	public String getCuenta8() {
		return cuenta8;
	}

	
	public void setCuenta8(String cuenta8) {
		this.cuenta8 = cuenta8;
	}

	
	public Integer getAplicaFacturaElectronica() {
		return aplicaFacturaElectronica;
	}

	
	public void setAplicaFacturaElectronica(Integer aplicaFacturaElectronica) {
		this.aplicaFacturaElectronica = aplicaFacturaElectronica;
	}

	
	
}

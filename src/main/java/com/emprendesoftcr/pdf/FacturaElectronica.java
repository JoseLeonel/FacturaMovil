/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emprendesoftcr.pdf;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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

	private String													_tipoDocumento;
	private String													_clave;
	private String													_consecutivo;
	private String													_fechaEmision;
	private String													_plazoCredito;
	private String													_condicionVenta;
	private String													medioEfectivo;

	private String													medioTarjeta;

	private String													medioBanco;
	private String													_moneda;
	private String													_tipoCambio;
	private String													_referencia;
	private String													referenciaTipoDoc;

	private String													referenciaNumero;

	private String													referenciaCodigo;
	private String													referenciaRazon;
	private String													referenciaFechaEmision;

	private String													_footerObservaciones;
	private String													_footerFormaPago;
	private String													_footerMoneda;
	private String													_footerCondicionVenta;

	private double													_footerTipoCambio;
	private double													_footerTotalServiciosGravados;
	private double													_footerTotalMercanciasGravadas;
	private double													_footerTotalServiciosExentos;
	private double													_footerTotalGravado;
	private double													_footerTotalExento;
	private double													_footerTotalVenta;
	private Double													_footerTotalDescuento;
	private double													_footerTotalImpuesto;
	private double													_footerTotalVentaNeta;
	private double													_footerTotalComprobante;

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

	public double getFooterTipoCambio() {
		return _footerTipoCambio;
	}

	public void setFooterTipoCambio(double _footerTipoCambio) {
		this._footerTipoCambio = _footerTipoCambio;
	}

	public double getFooterTotalServiciosGravados() {
		return _footerTotalServiciosGravados;
	}

	public void setFooterTotalServiciosGravados(double _footerTotalServiciosGravados) {
		this._footerTotalServiciosGravados = _footerTotalServiciosGravados;
	}

	public double getFooterTotalMercanciasGravadas() {
		return _footerTotalMercanciasGravadas;
	}

	public void setFooterTotalMercanciasGravadas(double _footerTotalMercanciasGravadas) {
		this._footerTotalMercanciasGravadas = _footerTotalMercanciasGravadas;
	}

	public double getFooterTotalServiciosExentos() {
		return _footerTotalServiciosExentos;
	}

	public void setFooterTotalServiciosExentos(double _footerTotalServiciosExentos) {
		this._footerTotalServiciosExentos = _footerTotalServiciosExentos;
	}

	public double getFooterTotalGravado() {
		return _footerTotalGravado;
	}

	public void setFooterTotalGravado(double _footerTotalGravado) {
		this._footerTotalGravado = _footerTotalGravado;
	}

	public double getFooterTotalExento() {
		return _footerTotalExento;
	}

	public void setFooterTotalExento(double _footerTotalExento) {
		this._footerTotalExento = _footerTotalExento;
	}

	public double getFooterTotalVenta() {
		return _footerTotalVenta;
	}

	public void setFooterTotalVenta(double _footerTotalVenta) {
		this._footerTotalVenta = _footerTotalVenta;
	}

	public Double getFooterTotalDescuento() {
		return _footerTotalDescuento;
	}

	public void setFooterTotalDescuento(Double _footerTotalDescuento) {
		this._footerTotalDescuento = _footerTotalDescuento;
	}

	public double getFooterTotalImpuesto() {
		return _footerTotalImpuesto;
	}

	public void setFooterTotalImpuesto(double _footerTotalImpuesto) {
		this._footerTotalImpuesto = _footerTotalImpuesto;
	}

	public double getFooterTotalVentaNeta() {
		return _footerTotalVentaNeta;
	}

	public void setFooterTotalVentaNeta(double _footerTotalVentaNeta) {
		this._footerTotalVentaNeta = _footerTotalVentaNeta;
	}

	public double getFooterTotalComprobante() {
		return _footerTotalComprobante;
	}

	public void setFooterTotalComprobante(double _footerTotalComprobante) {
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

	public TableModel getDataArray() {

		DefaultTableModel modelo = new DefaultTableModel();

		List<Object> nombreColumnas = new ArrayList<Object>();
		nombreColumnas.add("EmisorName");
		nombreColumnas.add("EmisorMail");
		nombreColumnas.add("EmisorProvince");
		nombreColumnas.add("EmisorDistrict");
		nombreColumnas.add("EmisorCedula");
		nombreColumnas.add("EmisorPhone");
		nombreColumnas.add("EmisorCanton");
		nombreColumnas.add("ClienteName");
		nombreColumnas.add("ClienteCedula");
		nombreColumnas.add("DetLinea");
		nombreColumnas.add("DetCodigo");
		nombreColumnas.add("DetDescripcion");
		nombreColumnas.add("DetPrecioU");
		nombreColumnas.add("DetCantidad");
		nombreColumnas.add("DetDescuento");
		nombreColumnas.add("DetImpuesto");
		nombreColumnas.add("DetTotal");
		nombreColumnas.add("Observacion");
		nombreColumnas.add("FormaPago");
		nombreColumnas.add("CondicionVenta");
		nombreColumnas.add("Moneda");
		nombreColumnas.add("TipoCambio");
		nombreColumnas.add("TotalServGravado");
		nombreColumnas.add("TotalMercGravada");
		nombreColumnas.add("TotalServExentas");
		nombreColumnas.add("TotalGravado");
		nombreColumnas.add("TotalExento");
		nombreColumnas.add("TotalVenta");
		nombreColumnas.add("TotalDescuento");
		nombreColumnas.add("TotalImpuesto");
		nombreColumnas.add("TotalVentaNeta");
		nombreColumnas.add("TotalComprobante");

		modelo.setColumnIdentifiers(nombreColumnas.toArray());
		modelo.setNumRows(this.getDetalleFacturaElectronica().size());
		int row = 0;
		for (DetalleFacturaElectronica det : this.getDetalleFacturaElectronica()) {
			modelo.setValueAt(this.getEmisorNombre(), row, 0);
			modelo.setValueAt(this.getEmisorCedula(), row, 1);
			modelo.setValueAt(this.getEmisorProvincia(), row, 2);
			modelo.setValueAt(this.getEmisorDistrito(), row, 3);
			modelo.setValueAt(this.getEmisorCedula(), row, 4);
			modelo.setValueAt(this.getEmisorTelefono(), row, 5);
			modelo.setValueAt(this.getEmisorCanton(), row, 6);
			modelo.setValueAt(this.getClienteNombre(), row, 7);
			modelo.setValueAt(this.getClienteCedula(), row, 8);

			modelo.setValueAt(det.getLinea(), row, 9);
			modelo.setValueAt(det.getCodigo(), row, 10);
			modelo.setValueAt(det.getDescripcion(), row, 11);
			modelo.setValueAt(det.getPrecioU(), row, 12);
			modelo.setValueAt(det.getCantidad(), row, 13);
			modelo.setValueAt(det.getDescuento(), row, 14);
			modelo.setValueAt(det.getImpuesto(), row, 15);
			modelo.setValueAt(det.getTotal(), row, 16);

			modelo.setValueAt(this.getFooterObservaciones(), row, 17);
			modelo.setValueAt(this.getFooterFormaPago(), row, 18);
			modelo.setValueAt(this.getFooterCondicionVenta(), row, 19);
			modelo.setValueAt(this.getFooterMoneda(), row, 20);
			modelo.setValueAt(this.getFooterTipoCambio(), row, 21);
			modelo.setValueAt(this.getFooterTotalServiciosGravados(), row, 22);
			modelo.setValueAt(this.getFooterTotalMercanciasGravadas(), row, 23);
			modelo.setValueAt(this.getFooterTotalServiciosExentos(), row, 24);
			modelo.setValueAt(this.getFooterTotalGravado(), row, 25);
			modelo.setValueAt(this.getFooterTotalExento(), row, 26);
			modelo.setValueAt(this.getFooterTotalVenta(), row, 27);
			modelo.setValueAt(this.getFooterTotalDescuento(), row, 28);
			modelo.setValueAt(this.getFooterTotalImpuesto(), row, 29);
			modelo.setValueAt(this.getFooterTotalVentaNeta(), row, 30);
			modelo.setValueAt(this.getFooterTotalComprobante(), row, 31);
			row++;
		}

		return modelo;
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

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
    private String _emisorNombre;
    private String _emisorCedula;
    private String _emisorTelefono;
    private String _emisorCorreo;
    private String _emisorFax;
    private String _emisorProvincia;
    private String _emisorDistrito;
    private String _emisorCanton;
    
    private String _clienteNombre;
    private String _clienteNombreComercial;
    private String _clienteCorreo;
    private String _clienteCedula;
    private String _clienteTelefono;
    private String _clienteCtaGrupo;
    private String _clienteMesCobro;
    private String _clientePeriodoCobro;
    private String _clienteFechaVence;
    private String _clientePaqueteComercial;
    
    private String _tipoDocumento;
    private String _clave;
    private String _consecutivo;
    private String _fechaEmision;
    private String _plazoCredito;
    private String _condicionVenta;
    private String _medioPago;
    private String _moneda;
    private String _tipoCambio;
    private String _referencia;
    
    private String _footerObservaciones;
    private String _footerFormaPago;
    private String _footerMoneda;
    private String _footerCondicionVenta;
    
    private double _footerTipoCambio;
    private double _footerTotalServiciosGravados;
    private double _footerTotalMercanciasGravadas;
    private double _footerTotalServiciosExentos;
    private double _footerTotalGravado;
    private double _footerTotalExento;
    private double _footerTotalVenta;
    private double _footerTotalDescuento;
    private double _footerTotalImpuesto;
    private double _footerTotalVentaNeta;
    private double _footerTotalComprobante;
    
    private List<DetalleFacturaElectronica> _detalleFacturaElectronica;

    public FacturaElectronica(){
        this._detalleFacturaElectronica = new ArrayList<DetalleFacturaElectronica>();
    }
    
    public String getEmisorNombre() {
        return _emisorNombre;
    }

    public void setEmisorNombre(String _emisorNombre) {
        this._emisorNombre = _emisorNombre;
    }

    public String getEmisorCorreo() {
        return _emisorCorreo;
    }

    public void setEmisorCorreo(String _emisorCorreo) {
        this._emisorCorreo = _emisorCorreo;
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

    public double getFooterTotalDescuento() {
        return _footerTotalDescuento;
    }

    public void setFooterTotalDescuento(double _footerTotalDescuento) {
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

		
		public String getClienteCtaGrupo() {
			return _clienteCtaGrupo;
		}

		
		public void setClienteCtaGrupo(String _clienteCtaGrupo) {
			this._clienteCtaGrupo = _clienteCtaGrupo;
		}

		
		public String getClienteMesCobro() {
			return _clienteMesCobro;
		}

		
		public void setClienteMesCobro(String _clienteMesCobro) {
			this._clienteMesCobro = _clienteMesCobro;
		}

		
		public String getClientePeriodoCobro() {
			return _clientePeriodoCobro;
		}

		
		public void setClientePeriodoCobro(String _clientePeriodoCobro) {
			this._clientePeriodoCobro = _clientePeriodoCobro;
		}

		
		public String getClienteFechaVence() {
			return _clienteFechaVence;
		}

		
		public void setClienteFechaVence(String _clienteFechaVence) {
			this._clienteFechaVence = _clienteFechaVence;
		}

		
		public String getClientePaqueteComercial() {
			return _clientePaqueteComercial;
		}

		
		public void setClientePaqueteComercial(String _clientePaqueteComercial) {
			this._clientePaqueteComercial = _clientePaqueteComercial;
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

		
		public String getMedioPago() {
			return _medioPago;
		}

		
		public void setMedioPago(String _medioPago) {
			this._medioPago = _medioPago;
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

		public static FacturaElectronica Foo(){
        FacturaElectronica f = new FacturaElectronica();
        f.setEmisorNombre("EFIKASOLUTIONS SOCIEDAD ANONIMA");
        f.setEmisorCorreo("info@efka.com");;
        f.setEmisorProvincia("Heredia");
        f.setEmisorDistrito("Ulloa");
        f.setEmisorCedula("3101733521");
        f.setEmisorTelefono("84661931");
        f.setEmisorCanton("Heredia");
        
        f.setClienteNombre("Sysco Consultores de Sistemas S.A");
        f.setClienteCedula("3101195395");
        
        f.setFooterObservaciones("FOO Testing Tools");
        
        f.setFooterFormaPago("Transf");
        f.setFooterCondicionVenta("Contado");
        f.setFooterMoneda("USD");
        f.setFooterTipoCambio(567);
        f.setFooterTotalServiciosGravados(5500);
        f.setFooterTotalMercanciasGravadas(0);
        f.setFooterTotalServiciosExentos(0);
        f.setFooterTotalGravado(0);
        f.setFooterTotalExento(0);
        f.setFooterTotalVenta(5500);
        f.setFooterTotalDescuento(0);
        f.setFooterTotalImpuesto(0);
        f.setFooterTotalVentaNeta(0);
        f.setFooterTotalComprobante(5500);
    
        for(int x =1; x <=25; x++){
            f.getDetalleFacturaElectronica().add(DetalleFacturaElectronica.Foo(x));
        }

        return f;
    }
    
    public TableModel getDataArray(){
        
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
        int row=0;
        for(DetalleFacturaElectronica det : this.getDetalleFacturaElectronica())
        {
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
    
}


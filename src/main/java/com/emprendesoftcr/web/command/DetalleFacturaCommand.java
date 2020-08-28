package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Factura;

/**
 * Linea de detalles que viene en la venta desde el front end DetalleFacturaCommand.
 * @author jose.
 * @since 5 jun. 2018
 */
public class DetalleFacturaCommand {

	private Long		id;

	private Integer	numeroLinea;

	private Double	precioUnitario;

	private Double	cantidad;

	private Double	montoTotal;

	private Double	montoDescuento;

	private String	naturalezaDescuento;

	private Double	subTotal;

	private Double	impuesto;

	private Double	montoImpuesto;

	private Double	montoTotalLinea;

	private Double	ganancia;

	private Double	porcentajeDesc;
	private String	descripcion;

	private String	tipoCodigo;

	private String	codigo;

	private String	unidadMedida;

	private String	tipoImpuesto;

	private String	fechaEmisionSTR;
	private String	consecutivo;
	private String	consecutivoProforma;

	private String	tipoDoc;

	private String	nombreUsuario;

	private Double	montoGanancia;

	private Factura	factura;
	private Double	porcentajeGanancia;

	private Double	costo;
	private String	tipoImpuestoMag;
	private Double	impuestoMag;

	private Double	montoImpuestoMag;
	private Double	pesoTransporte;

	private Double	pesoTransporteTotal;

	private String	tipoDocumentoExoneracion;

	private String	numeroDocumentoExoneracion;

	private String	nombreInstitucionExoneracion;

	private Date		fechaEmisionExoneracion;

	private Integer	porcentajeExoneracion;

	private Double	montoExoneracion;
	private Double	montoExoneracion1;

	private Double	cantidadAplicadaNotaCredito;
	private String						codigoTarifa;
  private Double precio;

	

	public DetalleFacturaCommand() {
		super();
	}

	public DetalleFacturaCommand(Detalle detalle) {
		
		this.id = detalle.getId();
		this.numeroLinea = detalle.getNumeroLinea();
		this.precioUnitario = detalle.getPrecioUnitario();
		this.cantidad = detalle.getCantidad();
		this.montoTotal = detalle.getMontoTotal();
		this.montoDescuento = detalle.getMontoDescuento();
		this.naturalezaDescuento = detalle.getNaturalezaDescuento();
		this.subTotal = detalle.getSubTotal();
		this.impuesto = detalle.getImpuesto();
		this.montoImpuesto = detalle.getMontoImpuesto();
		this.montoTotalLinea = detalle.getMontoTotalLinea();
		this.ganancia = detalle.getGanancia();
		this.porcentajeDesc = detalle.getPorcentajeDesc();
		this.descripcion = detalle.getDescripcion();
		this.tipoCodigo = detalle.getTipoCodigo();
		this.codigo = detalle.getCodigo();
		this.unidadMedida = detalle.getUnidadMedida();
		this.tipoImpuesto = detalle.getTipoImpuesto();
		this.fechaEmisionSTR = detalle.getFactura().getFechaEmisionSTR();
		this.consecutivo = detalle.getFactura().getNumeroConsecutivo();
		this.tipoDoc = detalle.getFactura().getTipoDoc();
		this.nombreUsuario = detalle.getFactura().getUsuarioCreacion().getNombreUsuario();
		this.montoGanancia = detalle.getMontoGanancia();
		this.factura = detalle.getFactura();
		this.consecutivoProforma = detalle.getFactura().getConsecutivoProforma() != null ? detalle.getFactura().getConsecutivoProforma() : Constantes.EMPTY;
		this.costo = detalle.getCosto();
		this.porcentajeGanancia = detalle.getPorcentajeGanancia();
		this.tipoImpuestoMag = detalle.getTipoImpuestoMag();
		this.impuestoMag = detalle.getImpuestoMag();
		this.montoImpuestoMag = detalle.getMontoImpuestoMag();
		this.pesoTransporte = detalle.getPesoTransporte();
		this.pesoTransporteTotal = detalle.getPesoTransporteTotal();
		this.tipoDocumentoExoneracion = detalle.getTipoDocumentoExoneracion();
		this.numeroDocumentoExoneracion = detalle.getNumeroDocumentoExoneracion();
		this.nombreInstitucionExoneracion = detalle.getNombreInstitucionExoneracion();
		this.fechaEmisionExoneracion = detalle.getFechaEmisionExoneracion();
		this.montoExoneracion = detalle.getMontoExoneracion();
		this.porcentajeExoneracion = detalle.getPorcentajeExoneracion();
		this.montoExoneracion1 = detalle.getMontoExoneracion1();
		this.cantidadAplicadaNotaCredito = detalle.getCantidadAplicadaNotaCredito() == null ? Constantes.ZEROS_DOUBLE : detalle.getCantidadAplicadaNotaCredito();
		this.codigoTarifa = detalle.getCodigoTarifa();
		this.precio = detalle.getPrecio();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	
	
	public Double getPrecio() {
		return precio;
	}

	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	
	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	public Double getCantidadAplicadaNotaCredito() {
		return cantidadAplicadaNotaCredito;
	}

	public void setCantidadAplicadaNotaCredito(Double cantidadAplicadaNotaCredito) {
		this.cantidadAplicadaNotaCredito = cantidadAplicadaNotaCredito;
	}

	public Double getMontoExoneracion1() {
		return montoExoneracion1;
	}

	public void setMontoExoneracion1(Double montoExoneracion1) {
		this.montoExoneracion1 = montoExoneracion1;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getFechaEmisionSTR() {
		return fechaEmisionSTR;
	}

	public void setFechaEmisionSTR(String fechaEmisionSTR) {
		this.fechaEmisionSTR = fechaEmisionSTR;
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
		return Utils.formateadorMiles(Utils.round(this.montoTotal, 2));
	}

	public String getMontoTotalSinImpuestoSTR() {
		Double totalIVA = this.montoImpuesto == null ? Constantes.ZEROS_DOUBLE : this.montoImpuesto;
		Double resultado = this.montoTotalLinea - totalIVA;
		return Utils.formateadorMiles(Utils.round(resultado, 2));
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

	public String getMontoImpuestoMagSTR() {
		return Utils.formateadorMiles(this.montoImpuestoMag);
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

	public Double getMontoGanancia() {
		return montoGanancia;
	}

	public void setMontoGanancia(Double montoGanancia) {
		this.montoGanancia = montoGanancia;
	}

	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}

	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
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

}

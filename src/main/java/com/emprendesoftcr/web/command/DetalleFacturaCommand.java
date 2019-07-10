package com.emprendesoftcr.web.command;

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
	
	private Factura factura;
	private Double						porcentajeGanancia;

	private Double						costo;
	private String						tipoImpuesto1;
	private Double						impuesto1;

	private Double						montoImpuesto1;
	private Double						pesoTransporte;
	
	private Double						pesoTransporteTotal;
	


	public DetalleFacturaCommand(Long id, Integer numeroLinea, Double precioUnitario, Double cantidad, Double montoTotal, Double montoDescuento, String naturalezaDescuento, Double subTotal, Double impuesto, Double montoImpuesto, Double montoTotalLinea, Double ganancia, Double porcentajeDesc, String descripcion, String tipoCodigo, String codigo, String unidadMedida, String tipoImpuesto, String fechaEmisionSTR, String consecutivo, String consecutivoProforma, String tipoDoc, String nombreUsuario, Double montoGanancia, Factura factura, Double porcentajeGanancia, Double costo, String tipoImpuesto1, Double impuesto1, Double montoImpuesto1, Double pesoTransporte, Double pesoTransporteTotal) {
		super();
		this.id = id;
		this.numeroLinea = numeroLinea;
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
		this.descripcion = descripcion;
		this.tipoCodigo = tipoCodigo;
		this.codigo = codigo;
		this.unidadMedida = unidadMedida;
		this.tipoImpuesto = tipoImpuesto;
		this.fechaEmisionSTR = fechaEmisionSTR;
		this.consecutivo = consecutivo;
		this.consecutivoProforma = consecutivoProforma;
		this.tipoDoc = tipoDoc;
		this.nombreUsuario = nombreUsuario;
		this.montoGanancia = montoGanancia;
		this.factura = factura;
		this.porcentajeGanancia = porcentajeGanancia;
		this.costo = costo;
		this.tipoImpuesto1 = tipoImpuesto1;
		this.impuesto1 = impuesto1;
		this.montoImpuesto1 = montoImpuesto1;
		this.pesoTransporte = pesoTransporte;
		this.pesoTransporteTotal = pesoTransporteTotal;
	}

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
		this.consecutivoProforma = detalle.getFactura().getConsecutivoProforma() !=null?detalle.getFactura().getConsecutivoProforma():Constantes.EMPTY;
		this.costo = detalle.getCosto();
		this.porcentajeGanancia = detalle.getPorcentajeGanancia();
		this.tipoImpuesto1 = detalle.getTipoImpuesto1();
		this.impuesto1 = detalle.getImpuesto1();
		this.montoImpuesto1 = detalle.getMontoImpuesto1();
		this.pesoTransporte = detalle.getPesoTransporte();
		this.pesoTransporteTotal = detalle.getPesoTransporteTotal();
		
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	

}

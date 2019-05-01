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

	
	
	
	

}

package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;


public class DetalleCompraSimplificadaCommand {

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


	private Double	porcentajeDesc;
	private String	descripcion;

	private String	tipoCodigo;

	private String	codigo;

	private String	unidadMedida;

	private String	tipoImpuesto;

	private String	fechaEmisionSTR;
	private String	consecutivo;

	private String	tipoDoc;

	private String	nombreUsuario;


	private CompraSimplificada	compraSimplificada;


		
	private String						codigoTarifa;

	

	

	public DetalleCompraSimplificadaCommand() {
		super();
	}

	public DetalleCompraSimplificadaCommand(DetalleCompraSimplificada detalleCompraSimplificada) {

		this.id = detalleCompraSimplificada.getId();
		this.numeroLinea = detalleCompraSimplificada.getNumeroLinea();
		this.precioUnitario = detalleCompraSimplificada.getPrecioUnitario() == null?Constantes.ZEROS_DOUBLE:detalleCompraSimplificada.getPrecioUnitario();
		this.cantidad = detalleCompraSimplificada.getCantidad() != null?detalleCompraSimplificada.getCantidad():Constantes.ZEROS_DOUBLE;
		this.montoTotal = detalleCompraSimplificada.getMontoTotal() !=null?detalleCompraSimplificada.getMontoTotal():Constantes.ZEROS_DOUBLE;
		this.montoDescuento = detalleCompraSimplificada.getMontoDescuento() !=null?detalleCompraSimplificada.getMontoDescuento():Constantes.ZEROS_DOUBLE;
		this.naturalezaDescuento = detalleCompraSimplificada.getNaturalezaDescuento() !=null?detalleCompraSimplificada.getNaturalezaDescuento():Constantes.KEY_EMAIL_FORMATO_INCORRECTO;
		this.subTotal = detalleCompraSimplificada.getSubTotal() !=null?detalleCompraSimplificada.getSubTotal():Constantes.ZEROS_DOUBLE;
		this.impuesto = detalleCompraSimplificada.getImpuesto() !=null?detalleCompraSimplificada.getImpuesto():Constantes.ZEROS_DOUBLE;
		this.montoImpuesto = detalleCompraSimplificada.getMontoImpuesto() !=null?detalleCompraSimplificada.getMontoImpuesto():Constantes.ZEROS_DOUBLE;
		this.montoTotalLinea = detalleCompraSimplificada.getMontoTotalLinea() !=null?detalleCompraSimplificada.getMontoTotalLinea():Constantes.ZEROS_DOUBLE;
		this.porcentajeDesc = detalleCompraSimplificada.getPorcentajeDesc() !=null?detalleCompraSimplificada.getPorcentajeDesc():Constantes.ZEROS_DOUBLE;
		this.descripcion = detalleCompraSimplificada.getDescripcion() !=null?detalleCompraSimplificada.getDescripcion():Constantes.EMPTY;
		this.tipoCodigo = detalleCompraSimplificada.getTipoCodigo() !=null?detalleCompraSimplificada.getTipoCodigo():Constantes.EMPTY;
		this.codigo = detalleCompraSimplificada.getCodigo() !=null?detalleCompraSimplificada.getCodigo():Constantes.EMPTY;
		this.unidadMedida = detalleCompraSimplificada.getUnidadMedida() !=null?detalleCompraSimplificada.getUnidadMedida():Constantes.EMPTY;
		this.tipoImpuesto = detalleCompraSimplificada.getTipoImpuesto() !=null?detalleCompraSimplificada.getTipoImpuesto():Constantes.EMPTY;
		this.fechaEmisionSTR = detalleCompraSimplificada.getCompraSimplificada().getFechaEmisionSTR() !=null?detalleCompraSimplificada.getCompraSimplificada().getFechaEmisionSTR():Constantes.EMPTY;
		this.consecutivo = detalleCompraSimplificada.getCompraSimplificada().getNumeroConsecutivo() !=null?detalleCompraSimplificada.getCompraSimplificada().getNumeroConsecutivo():Constantes.EMPTY;
		this.tipoDoc = detalleCompraSimplificada.getCompraSimplificada().getTipoDoc() !=null?detalleCompraSimplificada.getCompraSimplificada().getTipoDoc():Constantes.EMPTY;
		this.nombreUsuario = detalleCompraSimplificada.getCompraSimplificada().getUsuarioCreacion().getNombreUsuario() !=null?detalleCompraSimplificada.getCompraSimplificada().getUsuarioCreacion().getNombreUsuario():Constantes.EMPTY;
		this.compraSimplificada = detalleCompraSimplificada.getCompraSimplificada();
		this.unidadMedida = Constantes.UNIDAD_MEDIDA;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	

	public Double getPorcentajeDesc() {
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(Double porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}

	
	
	
	public CompraSimplificada getCompraSimplificada() {
		return compraSimplificada;
	}

	
	public void setCompraSimplificada(CompraSimplificada compraSimplificada) {
		this.compraSimplificada = compraSimplificada;
	}

	
	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	
	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	
	
}

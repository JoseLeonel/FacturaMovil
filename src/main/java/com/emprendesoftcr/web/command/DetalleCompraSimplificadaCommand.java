package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;


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

	private CompraSimplificada	compraSimplificada;
	private Double	porcentajeGanancia;

	private Double	costo;
	private Double	impuesto1;

	private Double	pesoTransporte;

	private Double	pesoTransporteTotal;

	private String	tipoDocumentoExoneracion;

	private String	numeroDocumentoExoneracion;

	private String	nombreInstitucionExoneracion;

	private Date		fechaEmisionExoneracion;

	private Integer	porcentajeExoneracion;

	private Double	montoExoneracion;
	
	private String						codigoTarifa;

	

	

	public DetalleCompraSimplificadaCommand() {
		super();
	}

	public DetalleCompraSimplificadaCommand(DetalleCompraSimplificada detalleCompraSimplificada) {

		this.id = detalleCompraSimplificada.getId();
		this.numeroLinea = detalleCompraSimplificada.getNumeroLinea();
		this.precioUnitario = detalleCompraSimplificada.getPrecioUnitario();
		this.cantidad = detalleCompraSimplificada.getCantidad();
		this.montoTotal = detalleCompraSimplificada.getMontoTotal();
		this.montoDescuento = detalleCompraSimplificada.getMontoDescuento();
		this.naturalezaDescuento = detalleCompraSimplificada.getNaturalezaDescuento();
		this.subTotal = detalleCompraSimplificada.getSubTotal();
		this.impuesto = detalleCompraSimplificada.getImpuesto();
		this.montoImpuesto = detalleCompraSimplificada.getMontoImpuesto();
		this.montoTotalLinea = detalleCompraSimplificada.getMontoTotalLinea();
		this.ganancia = detalleCompraSimplificada.getGanancia();
		this.porcentajeDesc = detalleCompraSimplificada.getPorcentajeDesc();
		this.descripcion = detalleCompraSimplificada.getDescripcion();
		this.tipoCodigo = detalleCompraSimplificada.getTipoCodigo();
		this.codigo = detalleCompraSimplificada.getCodigo();
		this.unidadMedida = detalleCompraSimplificada.getUnidadMedida();
		this.tipoImpuesto = detalleCompraSimplificada.getTipoImpuesto();
		this.fechaEmisionSTR = detalleCompraSimplificada.getCompraSimplificada().getFechaEmisionSTR();
		this.consecutivo = detalleCompraSimplificada.getCompraSimplificada().getNumeroConsecutivo();
		this.tipoDoc = detalleCompraSimplificada.getCompraSimplificada().getTipoDoc();
		this.nombreUsuario = detalleCompraSimplificada.getCompraSimplificada().getUsuarioCreacion().getNombreUsuario();
		this.montoGanancia = detalleCompraSimplificada.getMontoGanancia();
		this.compraSimplificada = detalleCompraSimplificada.getCompraSimplificada();
		this.consecutivoProforma = detalleCompraSimplificada.getCompraSimplificada().getConsecutivoProforma() != null ? detalleCompraSimplificada.getCompraSimplificada().getConsecutivoProforma() : Constantes.EMPTY;
		this.costo = detalleCompraSimplificada.getCosto();
		this.porcentajeGanancia = detalleCompraSimplificada.getPorcentajeGanancia();
		this.tipoDocumentoExoneracion = detalleCompraSimplificada.getTipoDocumentoExoneracion();
		this.numeroDocumentoExoneracion = detalleCompraSimplificada.getNumeroDocumentoExoneracion();
		this.nombreInstitucionExoneracion = detalleCompraSimplificada.getNombreInstitucionExoneracion();
		this.fechaEmisionExoneracion = detalleCompraSimplificada.getFechaEmisionExoneracion();
		this.montoExoneracion = detalleCompraSimplificada.getMontoExoneracion();
		this.porcentajeExoneracion = detalleCompraSimplificada.getPorcentajeExoneracion();

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

	
	public Double getImpuesto1() {
		return impuesto1;
	}

	public void setImpuesto1(Double impuesto1) {
		this.impuesto1 = impuesto1;
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

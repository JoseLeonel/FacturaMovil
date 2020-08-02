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
public class DetalleFacturaParaNotaCreditoCommand {

	private Long		id;

	private Integer	numeroLinea;

	private Double	cantidad;

	private String	descripcion;


	private String	codigo;



	private String	consecutivo;

	private String	tipoDoc;
	
	
	private Factura	factura;
	
	
	private Double cantidadAplicadaNotaCredito;

	


	public DetalleFacturaParaNotaCreditoCommand() {
		super();
	}

	public DetalleFacturaParaNotaCreditoCommand(Detalle detalle) {

		this.id = detalle.getId();
		this.numeroLinea = detalle.getNumeroLinea();
		this.cantidad = detalle.getCantidad();
		this.descripcion = detalle.getDescripcion();
		this.codigo = detalle.getCodigo();
		this.consecutivo = detalle.getFactura().getNumeroConsecutivo();
		this.tipoDoc = detalle.getFactura().getTipoDoc();
		this.factura = detalle.getFactura();
		this.cantidadAplicadaNotaCredito = detalle.getCantidadAplicadaNotaCredito() ==null?Constantes.ZEROS_DOUBLE:detalle.getCantidadAplicadaNotaCredito();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	
	public Double getCantidadAplicadaNotaCredito() {
		return cantidadAplicadaNotaCredito;
	}

	
	public void setCantidadAplicadaNotaCredito(Double cantidadAplicadaNotaCredito) {
		this.cantidadAplicadaNotaCredito = cantidadAplicadaNotaCredito;
	}


	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
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


	public String getDescripcion() {
		return descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
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





}

package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;

public class ProformasSQLNativeCommand {
	
	private Long id;
	private String consecutivoProforma;
	private Date fechaEmision;
	private String nombreUsuario;
	private String nombreCliente;
	private String nombreFactura;
	private Double totalImpuesto;
	private Double totalDescuento;
	private Double totalComprobante;
		
	public ProformasSQLNativeCommand(Long id, String consecutivoProforma, Date fechaEmision, String nombreCliente, String nombreFactura, Double totalImpuesto, Double totalDescuento, Double totalComprobante) {
		super();
		this.id = id;
		this.consecutivoProforma = consecutivoProforma;
		this.fechaEmision = fechaEmision;
		this.nombreCliente = nombreCliente;
		this.nombreFactura = nombreFactura;
		this.totalImpuesto = totalImpuesto;
		this.totalDescuento = totalDescuento;
		this.totalComprobante = totalComprobante;
	}

	public ProformasSQLNativeCommand() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getConsecutivoProforma() {
		return consecutivoProforma;
	}
	
	public void setConsecutivoProforma(String consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}
	
	public String getfechaEmisionSTR() {
		return Utils.getFechaGeneraReporte(this.getFechaEmision());
	}
	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}
	
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	public String getNombreFactura() {
		return nombreFactura;
	}
	
	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}
	
	public Double getTotalImpuesto() {
		return totalImpuesto;
	}
	public String getTotalImpuestoSTR() {
		return Utils.formateadorMiles(this.totalImpuesto !=null?this.totalImpuesto :Constantes.ZEROS_DOUBLE);
	}
	
	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}
	
	public Double getTotalDescuento() {
		return totalDescuento;
	}
	public String getTotalDescuentoSTR() {
		return Utils.formateadorMiles(this.totalDescuento !=null?this.totalDescuento :Constantes.ZEROS_DOUBLE);
	}
	
	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}
	
	
	public Double getTotalComprobante() {
		return totalComprobante;
	}
	
	public String getTotalComprobanteSTR() {
		return Utils.formateadorMiles(this.totalComprobante !=null?this.totalComprobante :Constantes.ZEROS_DOUBLE);
	}
	
	public void setTotalComprobante(Double totalComprobante) {
		this.totalComprobante = totalComprobante;
	}

	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
	

}

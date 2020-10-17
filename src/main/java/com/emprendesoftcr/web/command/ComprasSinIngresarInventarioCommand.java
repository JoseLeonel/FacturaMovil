package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

public class ComprasSinIngresarInventarioCommand {
private Long id;
private String consecutivo;
private Date fecha_compra;
private Double total_impuesto;
private Double total_compra;
private String nombre_completo;
private String factura_pdf;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}
public String getFechaEmisionSTR() {
	if (this.fecha_compra != null) {
		return Utils.getFechaGeneraReporte(this.getFecha_compra());
	}
	return Constantes.EMPTY;
}

public String getTotalImpuestoSTR() {
	return Utils.formateadorMiles(this.total_impuesto);
}

public String getTotalCompraSTR() {
	return Utils.formateadorMiles(this.total_compra);
}


public String getConsecutivo() {
	return consecutivo;
}

public void setConsecutivo(String consecutivo) {
	this.consecutivo = consecutivo;
}

public Date getFecha_compra() {
	return fecha_compra;
}

public void setFecha_compra(Date fecha_compra) {
	this.fecha_compra = fecha_compra;
}

public Double getTotal_impuesto() {
	return total_impuesto;
}

public void setTotal_impuesto(Double total_impuesto) {
	this.total_impuesto = total_impuesto;
}

public Double getTotal_compra() {
	return total_compra;
}

public void setTotal_compra(Double total_compra) {
	this.total_compra = total_compra;
}

public String getNombre_completo() {
	return nombre_completo;
}

public void setNombre_completo(String nombre_completo) {
	this.nombre_completo = nombre_completo;
}

public String getFactura_pdf() {
	return factura_pdf;
}

public void setFactura_pdf(String factura_pdf) {
	this.factura_pdf = factura_pdf;
}



}

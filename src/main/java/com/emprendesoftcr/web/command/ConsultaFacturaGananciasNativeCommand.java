package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaGananciaNative;

public class ConsultaFacturaGananciasNativeCommand {

	private Integer	estado;

	private String	codigo;

	
	private String	nombreCompleto;

	private String	cedula;

	private Date		fechaEmision;
	private String	fechaEmisionSTR;
	private String	nombreArticulo;
	private String	nombreCategoria;
	private Double	costo;
	private Double	cantidad;
	private Double	total;
	private Double	descuento;
	private Double	impuesto;
	private String	costoSTR;
	private String	cantidadSTR;
	private String	totalSTR;
	private String	descuentoSTR;
	private String	impuestoSTR;

	public ConsultaFacturaGananciasNativeCommand(ConsultaGananciaNative consultaGananciaNative) {
		super();

		this.estado = consultaGananciaNative.getEstado();
		this.codigo = consultaGananciaNative.getCodigo();
		this.nombreCompleto = consultaGananciaNative.getNombreCompleto();
		this.cedula = consultaGananciaNative.getCedula();
		this.fechaEmision = consultaGananciaNative.getFechaEmision();
		this.nombreArticulo = consultaGananciaNative.getNombreArticulo();
		this.nombreCategoria = consultaGananciaNative.getNombreCategoria();
		this.costo = consultaGananciaNative.getCosto() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getCosto();
		this.cantidad = consultaGananciaNative.getCantidad() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getCantidad();
		this.total = consultaGananciaNative.getTotal() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getTotal();
		this.total = this.total - this.impuesto;
		this.total = this.total - this.descuento;
		this.costo = this.costo * this.cantidad;
		this.descuento = consultaGananciaNative.getDescuento() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getDescuento();
		this.impuesto = consultaGananciaNative.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getImpuesto();
		this.fechaEmisionSTR = Utils.getFechaGeneraHacienda(consultaGananciaNative.getFechaEmision());
		this.impuestoSTR = Utils.formateadorMiles(consultaGananciaNative.getImpuesto());
		this.descuentoSTR = Utils.formateadorMiles(consultaGananciaNative.getDescuento());
		this.totalSTR = Utils.formateadorMiles(this.total);
		this.costoSTR = Utils.formateadorMiles(this.costo);
		this.cantidadSTR = Utils.formateadorMiles(this.cantidad);
	}

	public String getFechaEmisionSTR() {
		return fechaEmisionSTR;
	}

	public void setFechaEmisionSTR(String fechaEmisionSTR) {
		this.fechaEmisionSTR = fechaEmisionSTR;
	}

	public String getCostoSTR() {
		return costoSTR;
	}

	public void setCostoSTR(String costoSTR) {
		this.costoSTR = costoSTR;
	}

	public String getCantidadSTR() {
		return cantidadSTR;
	}

	public void setCantidadSTR(String cantidadSTR) {
		this.cantidadSTR = cantidadSTR;
	}

	public String getTotalSTR() {
		return totalSTR;
	}

	public void setTotalSTR(String totalSTR) {
		this.totalSTR = totalSTR;
	}

	public String getDescuentoSTR() {
		return descuentoSTR;
	}

	public void setDescuentoSTR(String descuentoSTR) {
		this.descuentoSTR = descuentoSTR;
	}

	public String getImpuestoSTR() {
		return impuestoSTR;
	}

	public void setImpuestoSTR(String impuestoSTR) {
		this.impuestoSTR = impuestoSTR;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

}

package com.emprendesoftcr.web.command;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.sqlNativo.ConsultaGananciaNative;

public class ConsultaFacturaGananciasNativeCommand {

	private String	codigo;

	private String	nombreCompleto;

	private String	cedula;

	private String	fechaEmisionSTR;
	private String	nombreArticulo;
	private String	nombreCategoria;
	private Double	costo;
	private Double	cantidad;
	private Double	total;
	private Double	descuento;
	private Double	impuesto;
	private Double	ganancia;
	private String	gananciaSTR;
	private String	costoSTR;
	private String	cantidadSTR;
	private String	totalSTR;
	private String	descuentoSTR;
	private String	impuestoSTR;

	public ConsultaFacturaGananciasNativeCommand(ConsultaGananciaNative consultaGananciaNative) {
		this.codigo = consultaGananciaNative.getCodigo();
		this.nombreCompleto = consultaGananciaNative.getNombreCompleto().length() > 35 ? consultaGananciaNative.getNombreCompleto().substring(0, 35) + "..." : consultaGananciaNative.getNombreCompleto();
		this.cedula = consultaGananciaNative.getCedula();
		this.fechaEmisionSTR = consultaGananciaNative.getFechaEmision();
		this.nombreArticulo = consultaGananciaNative.getNombreArticulo().length() > 35 ? consultaGananciaNative.getNombreArticulo().substring(0, 35) + "..." : consultaGananciaNative.getNombreArticulo();
		this.nombreCategoria = consultaGananciaNative.getNombreCategoria().length() > 35 ? consultaGananciaNative.getNombreCategoria().substring(0, 35) + "..." : consultaGananciaNative.getNombreCategoria();
		this.costo = consultaGananciaNative.getCosto() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getCosto();
		this.cantidad = consultaGananciaNative.getCantidad() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getCantidad();
		this.total = consultaGananciaNative.getTotal() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getTotal();
		this.descuento = consultaGananciaNative.getDescuento() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getDescuento();
		this.impuesto = consultaGananciaNative.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getImpuesto();
		this.impuestoSTR = Utils.formateadorMiles(consultaGananciaNative.getImpuesto());
		this.descuentoSTR = Utils.formateadorMiles(consultaGananciaNative.getDescuento() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getDescuento());
		this.totalSTR = Utils.formateadorMiles((consultaGananciaNative.getTotal() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getTotal()));
		this.costoSTR = Utils.formateadorMiles(consultaGananciaNative.getCosto() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getCosto());
		this.cantidadSTR = Utils.formateadorMiles(consultaGananciaNative.getCantidad() == null ? Constantes.ZEROS_DOUBLE : consultaGananciaNative.getCantidad());
	}

	public String getGananciaSTR() {
		if (this.total != null && this.costo !=null) {
      this.gananciaSTR =  Utils.formateadorMiles((this.total - this.costo));
		}else {
			this.gananciaSTR = Utils.formateadorMiles(Constantes.ZEROS_DOUBLE);
		}
		return gananciaSTR;
	}

	public void setGananciaSTR(String gananciaSTR) {
		if (this.total != null && this.costo !=null) {
       this.gananciaSTR =  Utils.formateadorMiles(this.total - this.costo);
		}else {
			this.gananciaSTR = Utils.formateadorMiles(Constantes.ZEROS_DOUBLE);
		}
		this.gananciaSTR = gananciaSTR;
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

	public Double getGanancia() {
		if (this.total != null && this.costo !=null) {
      this.ganancia =  this.total - this.costo;
		}else {
			this.ganancia = Constantes.ZEROS_DOUBLE;
		}
		return ganancia;
	}

	public void setGanancia(Double ganancia) {
		if (this.total != null && this.costo !=null) {
      this.ganancia =  this.total - this.costo;
		}else {
			this.ganancia = Constantes.ZEROS_DOUBLE;
		}
		
	}

}

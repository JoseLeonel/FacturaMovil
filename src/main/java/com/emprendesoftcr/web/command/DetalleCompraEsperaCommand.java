package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

public class DetalleCompraEsperaCommand {

	private Long			id;
private String codigo;
private String descripcion;
	private Integer		numeroLinea;

	private Double		costo;
	private Double		costoInventario;
	private Double		ganancia;

	private Double		precio;

	private Double		cantidad;

	private Double		totalImpuesto;

	private Double		totalDescuento;

	private Double		impuesto;

	private Double		descuento;

	private Double		montoTotalLinea;

	private Date			created_at;

	private Date			updated_at;

	private Compra		compra;
	
	

private Articulo	articulo;
private Usuario usuarioActualizacion;
	public DetalleCompraEsperaCommand(DetalleCompra detalleCompra) {
		super();
		this.id = detalleCompra.getId();
		this.numeroLinea = detalleCompra.getNumeroLinea();
		this.costo = detalleCompra.getCosto();
		this.precio = detalleCompra.getPrecio();
		this.cantidad = detalleCompra.getCantidad();
		this.totalImpuesto = detalleCompra.getTotalImpuesto();
		this.totalDescuento = detalleCompra.getTotalDescuento();
		this.impuesto = detalleCompra.getImpuesto();
		this.descuento = detalleCompra.getDescuento();
		this.montoTotalLinea = detalleCompra.getMontoTotalLinea();
		this.created_at = detalleCompra.getCreated_at();
		this.updated_at = detalleCompra.getUpdated_at();
		this.compra = detalleCompra.getCompra();
		this.articulo = detalleCompra.getArticulo();
		this.ganancia = detalleCompra.getGanancia();
		this.codigo = detalleCompra.getCodigo();
		this.descripcion = detalleCompra.getDescripcion();
		this.usuarioActualizacion = detalleCompra.getUsuarioActualizacion();
		this.costoInventario = detalleCompra.getCostoIventario() != null?detalleCompra.getCostoIventario():Constantes.ZEROS_DOUBLE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public String getTotalDescuentoSTR() {
		return Utils.formateadorMiles(this.totalDescuento);
	}

	public String getMontoTotalLineaSTR() {
		return Utils.formateadorMiles(this.montoTotalLinea);
	}

	public String getTotalImpuestoSTR() {
		return Utils.formateadorMiles(this.totalImpuesto);
	}

	public String getDescuentoSTR() {
		return Utils.formateadorMiles(this.descuento);
	}

	public String getImpuestoSTR() {
		return Utils.formateadorMiles(this.impuesto);
	}

	public String getCantidadSTR() {
		return Utils.formateadorMiles(this.cantidad);
	}

	public String getCostoSTR() {
		return Utils.formateadorMiles(this.costo);
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getMontoTotalLinea() {
		return montoTotalLinea;
	}

	public void setMontoTotalLinea(Double montoTotalLinea) {
		this.montoTotalLinea = montoTotalLinea;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	
	public Double getGanancia() {
		return ganancia;
	}

	
	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	
	public String getCodigo() {
		return codigo;
	}

	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Usuario getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	
	public void setUsuarioActualizacion(Usuario usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	
	public Double getCostoInventario() {
		return costoInventario;
	}
	public String getCostoInventarioSTR() {
		return Utils.formateadorMiles(this.costoInventario);
	}


	
	public void setCostoInventario(Double costoInventario) {
		this.costoInventario = costoInventario;
	}
	
	

}

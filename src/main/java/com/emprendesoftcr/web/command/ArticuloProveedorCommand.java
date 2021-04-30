package com.emprendesoftcr.web.command;

import com.emprendesoftcr.utils.Utils;

/**
 * Listar los productos de un proveedor.
 * @author jose
 *
 */
public class ArticuloProveedorCommand {
	
	private Integer id;
	private String cedula;
	private String nombreCompleto;
	private String descripcion;
	private Double costoInventario;
	private Double costoProveedor;
	private Double cantidad;
	private String codigo;
	private Double ganancia;
	private Double precio;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public Double getCostoProveedor() {
		return costoProveedor;
	}
	
	public String getCostoProveedorSTR() {
		return Utils.formateadorMiles(this.costoProveedor);
	}

	
	public void setCostoProveedor(Double costoProveedor) {
		this.costoProveedor = costoProveedor;
	}
	
	public Double getCantidad() {
		return cantidad;
	}
	public String getCantidadSTR() {
		return Utils.formateadorMiles(this.cantidad);
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Double getGanancia() {
		return ganancia;
	}
	public String getGananciaSTR() {
		return Utils.formateadorMiles(this.ganancia);
	}
	
	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getPrecioSTR() {
		return Utils.formateadorMiles(this.precio);
	}
	
	
	
	

}

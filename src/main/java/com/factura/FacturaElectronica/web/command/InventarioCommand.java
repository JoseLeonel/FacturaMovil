package com.factura.FacturaElectronica.web.command;

import java.util.Date;

import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Inventario;

public class InventarioCommand {

	private Integer		id;
	private Double			cantidad;

	private Double			minimo;

	private Double			maximo;

	private String		estado;

	private Date			created_at;

	private Date			updated_at;

	private Articulo	articulo;

	public InventarioCommand(Inventario inventario) {
		super();
		this.id = inventario.getId();
		this.estado = inventario.getEstado();
		this.created_at = inventario.getCreated_at();
		this.updated_at = inventario.getUpdated_at();
		this.articulo = inventario.getArticulo() != null ? inventario.getArticulo() : null;
		this.cantidad = inventario.getCantidad();
		this.minimo = inventario.getMinimo();
		this.maximo = inventario.getMaximo();
	}

	public InventarioCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}

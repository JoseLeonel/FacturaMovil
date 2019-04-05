package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

public class ProveedorArticuloCommand {


	private Long							id;

	private String						codigo;
	
	private Double						costo;

	private Articulo					articulo;

	private Proveedor					proveedor;

	private Date							created_at;

	private Date							updated_at;

	public ProveedorArticuloCommand(ProveedorArticulo proveedorArticulo) {
		super();
		this.id = proveedorArticulo.getId();
		this.codigo = proveedorArticulo.getCodigo();
		this.created_at = proveedorArticulo.getCreated_at();
		this.updated_at = proveedorArticulo.getUpdated_at();
		this.proveedor = proveedorArticulo.getProveedor();
		this.articulo = proveedorArticulo.getArticulo();
		this.costo = proveedorArticulo.getCosto();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	
	public String getCodigo() {
		return codigo;
	}

	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public Double getCosto() {
		return costo;
	}

	
	public void setCosto(Double costo) {
		this.costo = costo;
	}

	
	public Articulo getArticulo() {
		return articulo;
	}

	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	
	public Proveedor getProveedor() {
		return proveedor;
	}

	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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

}

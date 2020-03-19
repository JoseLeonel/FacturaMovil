package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.ClienteArticulo;

public class ClienteArticuloCommand {
	private Long							id;

	private String						codigo;
	
	private String						descripcion;
	
	private Double						cantidad;

	
	private Double						precio;
	
		
	private Articulo					articulo;

	private Cliente					cliente;

	private Date							created_at;

	private Date							updated_at;

	public ClienteArticuloCommand() {
		
	}
	public ClienteArticuloCommand(ClienteArticulo clienteArticulo){
		super();
		this.articulo = clienteArticulo.getArticulo();
		this.cliente = clienteArticulo.getCliente();
		this.codigo = clienteArticulo.getCodigo();
		this.created_at = clienteArticulo.getCreated_at();
		this.updated_at = clienteArticulo.getUpdated_at();
		this.precio = clienteArticulo.getPrecio();
		this.cantidad = clienteArticulo.getCantidad();
		this.id = clienteArticulo.getId();
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

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Double getCantidad() {
		return cantidad;
	}

	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	
	public Double getPrecio() {
		return precio;
	}

	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	public Articulo getArticulo() {
		return articulo;
	}

	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	
	public Cliente getCliente() {
		return cliente;
	}

	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

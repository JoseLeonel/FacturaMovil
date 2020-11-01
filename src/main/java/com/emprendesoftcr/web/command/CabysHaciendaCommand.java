package com.emprendesoftcr.web.command;

import java.util.List;

public class CabysHaciendaCommand {
	
	private String codigo;
	private String descripcion;
	private List<String> categorias;
	private Double impuesto;
	private String uri;
	
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
	
	public List<String> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
	
	public Double getImpuesto() {
		return impuesto;
	}
	
	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	

}

package com.emprendesoftcr.web.command;

import java.util.List;

public class ListCabysCodigo {
	private String codigo;
	private String descripcion;
	private List<String> categorias;
	private Integer impuesto;
	
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

	
	public Integer getImpuesto() {
		return impuesto;
	}

	
	public void setImpuesto(Integer impuesto) {
		this.impuesto = impuesto;
	}
	
	
	
	

}

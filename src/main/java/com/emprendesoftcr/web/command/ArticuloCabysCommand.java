package com.emprendesoftcr.web.command;


public class ArticuloCabysCommand {
	private Long id;
 private String codigo;
 private String descripcion;
 private String nomb_cate;
 private String cod_cabys;
 private Double impuesto;
 private String estado;


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

public String getNomb_cate() {
	return nomb_cate;
}

public void setNomb_cate(String nomb_cate) {
	this.nomb_cate = nomb_cate;
}

public String getCod_cabys() {
	return cod_cabys;
}

public void setCod_cabys(String cod_cabys) {
	this.cod_cabys = cod_cabys;
}

public Double getImpuesto() {
	return impuesto;
}

public void setImpuesto(Double impuesto) {
	this.impuesto = impuesto;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}
 
}

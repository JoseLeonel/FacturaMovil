package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Articulo;

public class FiltroKardexCommand {
private String fechaInicio;
private String fechaFin;
private Articulo articulo;
public FiltroKardexCommand() {
	super();
}

public String getFechaInicio() {
	return fechaInicio;
}

public void setFechaInicio(String fechaInicio) {
	this.fechaInicio = fechaInicio;
}

public String getFechaFin() {
	return fechaFin;
}

public void setFechaFin(String fechaFin) {
	this.fechaFin = fechaFin;
}

public Articulo getArticulo() {
	return articulo;
}

public void setArticulo(Articulo articulo) {
	this.articulo = articulo;
}


}

package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.ActividadComercial;

public class ActividadComercialCommand {
	
 private Integer id;
	private String						codigoActividadComercial;

	private String						descripcion;

	public ActividadComercialCommand(Integer id, String codigoActividadComercial, String descripcion) {
		super();
		this.id = id;
		this.codigoActividadComercial = codigoActividadComercial;
		this.descripcion = descripcion;
	}

	
	public ActividadComercialCommand(ActividadComercial actividadComercial) {
		super();
		this.id = actividadComercial.getId();
		this.descripcion = actividadComercial.getDescripcion();
		this.codigoActividadComercial = actividadComercial.getCodigoActividadComercial();
	}


	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getCodigoActividadComercial() {
		return codigoActividadComercial;
	}

	
	public void setCodigoActividadComercial(String codigoActividadComercial) {
		this.codigoActividadComercial = codigoActividadComercial;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}

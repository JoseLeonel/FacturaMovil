package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Rol;

public class RolCommand {
	private Integer						id;

	private String						nombre;

	private String						descripcion;

	public RolCommand() {
		super();
	}
	
	public RolCommand(Rol rol) {
		this.id = rol.getId();
		this.nombre = rol.getNombre();
		this.descripcion = rol.getDescripcion();
		
	}

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

}

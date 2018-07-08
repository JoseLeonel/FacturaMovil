package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Provincia;



/**
 * 
 * ProvinciaCommand.
 * @author jose.
 * @since 7 jul. 2018
 */
public class ProvinciaCommand {

	private Integer	id;

	private Integer	codigo;

	private String	descripcion;

	public ProvinciaCommand() {
		super();
	}

	public ProvinciaCommand(Provincia provincia) {
		super();
		this.id = provincia.getId();
		this.codigo = provincia.getCodigo();
		this.descripcion = provincia.getDescripcion();

	}

	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getCodigo() {
		return codigo;
	}

	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
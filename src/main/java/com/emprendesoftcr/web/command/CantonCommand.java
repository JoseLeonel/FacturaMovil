package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Canton;

/**
 * 
 * CantonCommand.
 * @author jose.
 * @since 7 jul. 2018
 */
public class CantonCommand {

	private Integer	id;

	private Integer	codigo;

	private Integer	codigo_provincia;

	private String	descripcion;

	public CantonCommand(Canton canton) {
		super();
		this.id = canton.getId();
		this.codigo = canton.getCodigo();
		this.codigo_provincia = canton.getCodigo_provincia();
		this.descripcion = canton.getDescripcion();
	}

	public CantonCommand() {
		super();

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

	public Integer getCodigo_provincia() {
		return codigo_provincia;
	}

	public void setCodigo_provincia(Integer codigo_provincia) {
		this.codigo_provincia = codigo_provincia;
	}

}
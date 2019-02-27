package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Distrito;

/**
 * DistritoCommand.
 * @author jose.
 * @since 7 jul. 2018
 */
public class DistritoCommand {

	private Integer	id;

	private Integer	codigo;

	private String	descripcion;

	private Integer	codigoCanton;

	private Integer	codigoProvincia;

	public DistritoCommand(Distrito distrito) {
		super();
		this.id = distrito.getId();
		this.codigo = distrito.getCodigo();
		this.descripcion = distrito.getDescripcion();
		this.codigoCanton = distrito.getCodigo();
		this.codigoProvincia = distrito.getCodigoProvincia();
	}

	public DistritoCommand() {
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

	public Integer getCodigoCanton() {
		return codigoCanton;
	}

	public void setCodigoCanton(Integer codigoCanton) {
		this.codigoCanton = codigoCanton;
	}

	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

}
package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Barrio;

/**
 * CantonCommand.
 * @author jose.
 * @since 7 jul. 2018
 */
public class BarriosCommand {

	private Integer	id;

	private Integer	codigo;

	private Integer	codigoProvincia;

	private Integer	codigoCanton;

	private Integer	codigoDistrito;

	private String	descripcion;

	public BarriosCommand(Barrio barrio) {
		super();
		this.id = barrio.getId();
		this.codigo = barrio.getCodigo();
		this.descripcion = barrio.getDescripcion();
		this.codigoProvincia = barrio.getCodigoProvincia();
		this.codigoCanton = barrio.getCodigoCanton();
		this.codigoDistrito = barrio.getCodigoDistrito();
	}

	public BarriosCommand() {
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

	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public Integer getCodigoCanton() {
		return codigoCanton;
	}

	public void setCodigoCanton(Integer codigoCanton) {
		this.codigoCanton = codigoCanton;
	}

	public Integer getCodigoDistrito() {
		return codigoDistrito;
	}

	public void setCodigoDistrito(Integer codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}

}
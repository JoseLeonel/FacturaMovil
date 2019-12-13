package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.EmpresaActividadComercial;

public class EmpresaActividadComercialCommand {

	private Integer	id;

	private String	codigo;

	private String	descripcion;

	private Integer	principal;
	

	public EmpresaActividadComercialCommand() {
		super();
	}

	public EmpresaActividadComercialCommand(EmpresaActividadComercial empresaActividadComercial) {
		super();
		this.id = empresaActividadComercial.getId();
		this.codigo = empresaActividadComercial.getCodigo();
		this.descripcion = empresaActividadComercial.getDescripcion();
		this.principal = empresaActividadComercial.getPrincipal();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrincipal() {
		return principal;
	}

	public void setPrincipal(Integer principal) {
		this.principal = principal;
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

}

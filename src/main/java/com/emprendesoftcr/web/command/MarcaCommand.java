package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Marca;

/**
 * MarcaCommand.
 * @author lhernandez.
 * @since 7 abr. 2018
 */
public class MarcaCommand {

	private Integer	id;

	private String	descripcion;

	private String	estado;

	private Date		created_at;

	private Date		updated_at;

	private Empresa	empresa;

	public MarcaCommand(Marca marca) {
		super();
		this.id = marca.getId();
		this.descripcion = marca.getDescripcion();
		this.estado = marca.getEstado();
		this.created_at = marca.getCreated_at();
		this.updated_at = marca.getUpdated_at();
		this.empresa = marca.getEmpresa();
	
	}

	public MarcaCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	

}

package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.TipoUnidad;

public class TipoUnidadCommand {

	private Integer id;

	private String descripcion;

	private String codigo;

	private String estado;

	private Date created_at;

	private Date updated_at;

	public TipoUnidadCommand(TipoUnidad tipoUnidad) {
		super();
		this.id = tipoUnidad.getId();
		this.descripcion = tipoUnidad.getDescripcion();
		this.codigo = tipoUnidad.getCodigo();
		this.estado = tipoUnidad.getEstado();
		this.created_at = tipoUnidad.getCreated_at();
		this.updated_at = tipoUnidad.getUpdated_at();

	}

	public TipoUnidadCommand() {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

}

package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Ubicacion;

/**
 * ubicacion del inventario
 * @author jose
 *
 */
public class UbicacionCommand {

	private Long	id;

	private String	descripcion;

	private String	estado;

	private Date		created_at;

	private Date		updated_at;

	private Empresa	empresa;

	public UbicacionCommand(Ubicacion ubicacion) {
		super();
		this.id = ubicacion.getId();
		this.descripcion = ubicacion.getDescripcion();
		this.estado = ubicacion.getEstado();
		this.created_at = ubicacion.getCreated_at();
		this.updated_at = ubicacion.getUpdated_at();
		this.empresa = ubicacion.getEmpresa();
	
	}

	public UbicacionCommand() {
		super();
	}

	

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
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

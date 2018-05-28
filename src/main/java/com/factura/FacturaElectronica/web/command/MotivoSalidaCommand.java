package com.factura.FacturaElectronica.web.command;

import java.util.Date;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.MotivoSalida;

public class MotivoSalidaCommand {

	private Integer id;

	private String descripcion;

	private String estado;

	private Date created_at;

	private Date updated_at;

	private Empresa empresa;

	public MotivoSalidaCommand(MotivoSalida motivoSalida) {
		super();
		this.id = motivoSalida.getId();
		this.descripcion = motivoSalida.getDescripcion();
		this.estado = motivoSalida.getEstado();
		this.created_at = motivoSalida.getCreated_at();
		this.updated_at = motivoSalida.getUpdated_at();
		this.empresa = motivoSalida.getEmpresa();

	}

	public MotivoSalidaCommand() {
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

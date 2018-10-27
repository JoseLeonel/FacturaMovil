package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Mesa;

/**
 * MesaCommand.
 * @author jose.
 * @since 4 sep. 2018
 */
public class MesaCommand {

	private Long id;

	private String descripcion;

	private String estado;

	private Date created_at;

	private Date updated_at;

	private Empresa empresa;

	private Boolean impuestoServicio;

	private Integer prioridad;

	private Integer tieneFacturas;

	public MesaCommand(Mesa mesa) {
		super();
		this.id = mesa.getId();
		this.descripcion = mesa.getDescripcion();
		this.estado = mesa.getEstado();
		this.created_at = mesa.getCreated_at();
		this.updated_at = mesa.getUpdated_at();
		this.empresa = mesa.getEmpresa();
		this.prioridad = mesa.getPrioridad();
		this.impuestoServicio = mesa.getImpuestoServicio();
		this.tieneFacturas = mesa.getTieneFacturas();
	}

	public MesaCommand() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Integer getTieneFacturas() {
		return tieneFacturas;
	}

	public void setTieneFacturas(Integer tieneFacturas) {
		this.tieneFacturas = tieneFacturas;
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

	public Boolean getImpuestoServicio() {
		return impuestoServicio;
	}

	public String getImpuestoServicioStr() {
		return impuestoServicio ? "10% serv" : "0% serv";
	}

	public void setImpuestoServicio(Boolean impuestoServicio) {
		this.impuestoServicio = impuestoServicio;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

}

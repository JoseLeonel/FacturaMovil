package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Mesa;

public class ParametrosPaginacionMesa {

	private Integer	id;
	private Integer	paginaActual;
	private Integer	cantidadPorPagina;
	private Integer	total;
	private Mesa		mesa;

	public ParametrosPaginacionMesa() { 
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(Integer paginaActual) {
		this.paginaActual = paginaActual;
	}

	public Integer getCantidadPorPagina() {
		return cantidadPorPagina;
	}

	public void setCantidadPorPagina(Integer cantidadPorPagina) {
		this.cantidadPorPagina = cantidadPorPagina;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}

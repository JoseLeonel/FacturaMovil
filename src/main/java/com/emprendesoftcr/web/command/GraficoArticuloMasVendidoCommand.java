package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.sqlNativo.GraficoArticuloMasVendidoNative;

public class GraficoArticuloMasVendidoCommand {

	private Long		id;

	private String	codigo;

	private String	descripcion;

	private Double	cantidadVendido;

	public GraficoArticuloMasVendidoCommand(GraficoArticuloMasVendidoNative graficoArticuloMasVendidoNative) {
		super();
		this.descripcion = graficoArticuloMasVendidoNative.getDescripcion();
		this.cantidadVendido = graficoArticuloMasVendidoNative.getCantidadVendido();
		this.codigo = graficoArticuloMasVendidoNative.getCodigo();
	}

	public GraficoArticuloMasVendidoCommand(Long id, String codigo, String descripcion, Double cantidadVendido) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.cantidadVendido = cantidadVendido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getCantidadVendido() {
		return cantidadVendido;
	}

	public void setCantidadVendido(Double cantidadVendido) {
		this.cantidadVendido = cantidadVendido;
	}

}

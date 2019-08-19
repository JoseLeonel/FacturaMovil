package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

public class KardexCommand {

	private Long		id;

	private Double		cantidadSolicitada;
	
	private String		cantidadSolicitadaSTR;

	private Double		cantidadActual;
	
	private String		cantidadActualSTR;

	private Double		costoActual;
	private String		costoActualSTR;

	private Double		totalCostoActual;
	private String		totalCostoActualSTR;

	private String		codigo;

	private String		observacion;

	private Double		cantidadNueva;
	private String		cantidadNuevaSTR;

	private Double		costoNuevo;
	private String		costoNuevoSTR;

	private Double		totalCostoNuevo;
	
	private String		totalCostoNuevoSTR;

	private String		consecutivo;

	private String		tipo;

	private String		motivo;

	private Date			created_at;

	private Date			updated_at;
	private Articulo	articulo;
	private Usuario		usuario;

	public KardexCommand(Kardex kardex) {
		super();
		this.id = kardex.getId();

		this.cantidadSolicitada = kardex.getCantidadSolicitada();
		this.cantidadActual = kardex.getCantidadActual();
		this.costoActual = kardex.getCostoActual();
		this.totalCostoActual = kardex.getTotalCostoActual();
		this.codigo = kardex.getCodigo();
		this.observacion = kardex.getObservacion();
		this.cantidadNueva = kardex.getCantidadNueva();
		this.costoNuevo = kardex.getCostoNuevo();
		this.totalCostoNuevo = kardex.getTotalCostoNuevo();
		this.consecutivo = kardex.getConsecutivo();
		this.tipo = kardex.getTipo();
		this.motivo = kardex.getMotivo();
		this.created_at = kardex.getCreated_at();
		this.updated_at = kardex.getUpdated_at();
		this.articulo = kardex.getArticulo();
		this.usuario = kardex.getUsuario();
		
		this.cantidadActualSTR = kardex.getCantidadActualSTR();
		this.cantidadNuevaSTR = kardex.getCantidadNuevaSTR();
		this.cantidadSolicitadaSTR = kardex.getCantidadSolicitadaSTR();
		this.costoActualSTR = kardex.getCostoActualSTR();
		this.costoNuevoSTR = kardex.getCostoNuevoSTR();
		this.totalCostoActualSTR = kardex.getTotalCostoActualSTR();
		this.totalCostoNuevoSTR = kardex.getTotalCostoNuevoSTR();
		

	}

	

	
	public Long getId() {
		return id;
	}



	
	public void setId(Long id) {
		this.id = id;
	}



	
	public String getCantidadSolicitadaSTR() {
		return cantidadSolicitadaSTR;
	}




	
	public void setCantidadSolicitadaSTR(String cantidadSolicitadaSTR) {
		this.cantidadSolicitadaSTR = cantidadSolicitadaSTR;
	}




	
	public String getCantidadActualSTR() {
		return cantidadActualSTR;
	}




	
	public void setCantidadActualSTR(String cantidadActualSTR) {
		this.cantidadActualSTR = cantidadActualSTR;
	}




	
	public String getCostoActualSTR() {
		return costoActualSTR;
	}




	
	public void setCostoActualSTR(String costoActualSTR) {
		this.costoActualSTR = costoActualSTR;
	}




	
	public String getTotalCostoActualSTR() {
		return totalCostoActualSTR;
	}




	
	public void setTotalCostoActualSTR(String totalCostoActualSTR) {
		this.totalCostoActualSTR = totalCostoActualSTR;
	}




	
	public String getCantidadNuevaSTR() {
		return cantidadNuevaSTR;
	}




	
	public void setCantidadNuevaSTR(String cantidadNuevaSTR) {
		this.cantidadNuevaSTR = cantidadNuevaSTR;
	}




	
	public String getCostoNuevoSTR() {
		return costoNuevoSTR;
	}




	
	public void setCostoNuevoSTR(String costoNuevoSTR) {
		this.costoNuevoSTR = costoNuevoSTR;
	}




	
	public String getTotalCostoNuevoSTR() {
		return totalCostoNuevoSTR;
	}




	
	public void setTotalCostoNuevoSTR(String totalCostoNuevoSTR) {
		this.totalCostoNuevoSTR = totalCostoNuevoSTR;
	}




	public Double getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(Double cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public Double getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(Double cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public Double getCostoActual() {
		return costoActual;
	}

	public void setCostoActual(Double costoActual) {
		this.costoActual = costoActual;
	}

	public Double getTotalCostoActual() {
		return totalCostoActual;
	}

	public void setTotalCostoActual(Double totalCostoActual) {
		this.totalCostoActual = totalCostoActual;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Double getCantidadNueva() {
		return cantidadNueva;
	}

	public void setCantidadNueva(Double cantidadNueva) {
		this.cantidadNueva = cantidadNueva;
	}

	public Double getCostoNuevo() {
		return costoNuevo;
	}

	public void setCostoNuevo(Double costoNuevo) {
		this.costoNuevo = costoNuevo;
	}

	public Double getTotalCostoNuevo() {
		return totalCostoNuevo;
	}

	public void setTotalCostoNuevo(Double totalCostoNuevo) {
		this.totalCostoNuevo = totalCostoNuevo;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

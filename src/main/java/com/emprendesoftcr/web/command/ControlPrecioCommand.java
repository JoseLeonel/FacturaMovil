package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.ControlPrecioArticulo;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.Utils;

public class ControlPrecioCommand {

	private Long			id;

	private Articulo	articulo;

	private Usuario		responsableCambioPrecio;

	private Usuario		responbleAceptarPrecio;

	private Integer		estado;									// 1 = Pendiente de aceptar 2 = Aceptado 3 Rechazado

	private Double		precioPublicoActual;

	private Double		precioPublicoNuevo;

	private Double		costo;

	private Double		ganancia;

	private String		consecutivo;

	private String		rutaArchivoCompra;

	private Date			created_at;

	private Date			updated_at;

	public ControlPrecioCommand(ControlPrecioArticulo controlPrecioArticulo) {
		super();
		this.id = controlPrecioArticulo.getId();

		this.articulo = controlPrecioArticulo.getArticulo();

		this.responsableCambioPrecio = controlPrecioArticulo.getResponbleAceptarPrecio();

		this.responbleAceptarPrecio = controlPrecioArticulo.getResponbleAceptarPrecio();

		this.estado = controlPrecioArticulo.getEstado(); // 1 = Pendiente de aceptar 2 = Aceptado 3 Rechazado

		this.precioPublicoActual = controlPrecioArticulo.getPrecioPublicoActual();

		this.precioPublicoNuevo = controlPrecioArticulo.getPrecioPublicoNuevo();

		this.costo = controlPrecioArticulo.getCosto();

		this.ganancia = controlPrecioArticulo.getGanancia();

		this.consecutivo = controlPrecioArticulo.getConsecutivo();

		this.rutaArchivoCompra = controlPrecioArticulo.getRutaArchivoCompra();

		this.created_at = controlPrecioArticulo.getUpdated_at();

		this.updated_at = controlPrecioArticulo.getUpdated_at();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public String getDescripcion() {
		return articulo != null && articulo.getDescripcion().length() > 40 ? articulo.getDescripcion().substring(0, 40) : articulo.getDescripcion();
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Usuario getResponsableCambioPrecio() {
		return responsableCambioPrecio;
	}

	public void setResponsableCambioPrecio(Usuario responsableCambioPrecio) {
		this.responsableCambioPrecio = responsableCambioPrecio;
	}

	public Usuario getResponbleAceptarPrecio() {
		return responbleAceptarPrecio;
	}

	public void setResponbleAceptarPrecio(Usuario responbleAceptarPrecio) {
		this.responbleAceptarPrecio = responbleAceptarPrecio;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Double getPrecioPublicoActual() {
		return precioPublicoActual;
	}

	public void setPrecioPublicoActual(Double precioPublicoActual) {
		this.precioPublicoActual = precioPublicoActual;
	}

	public Double getPrecioPublicoNuevo() {
		return precioPublicoNuevo;
	}

	public void setPrecioPublicoNuevo(Double precioPublicoNuevo) {
		this.precioPublicoNuevo = precioPublicoNuevo;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getGanancia() {
		return ganancia;
	}

	public void setGanancia(Double ganancia) {
		this.ganancia = ganancia;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getRutaArchivoCompra() {
		return rutaArchivoCompra;
	}

	public void setRutaArchivoCompra(String rutaArchivoCompra) {
		this.rutaArchivoCompra = rutaArchivoCompra;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public String getCreated_atSTR() {
		if (this.created_at != null) {
			return Utils.getFechaGeneraReporte(this.created_at);
		}
		return Constantes.EMPTY;

	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public String getUpdated_atSTR() {
		if (this.updated_at != null) {
			return Utils.getFechaGeneraReporte(this.updated_at);
		}
		return Constantes.EMPTY;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

}

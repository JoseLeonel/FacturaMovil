package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * Controla los precios que se cambian en el sistema, Se pueden reversar o cambiar
 * @author jose
 *
 */
@Entity
@Table(name = "control_precio_art")
public class ControlPrecioArticulo implements Serializable {

	private static final long	serialVersionUID	= -7361693121644891115L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@ManyToOne
	@JoinColumn(name = "articulo_id", nullable = false)
	private Articulo					articulo;

	@Column(name = "codigo")
	private String						codigo;		
	@Column(name = "codigo_nuevo")
	private String						codigoNuevo;		
	

	@ManyToOne
	@JoinColumn(name = "usua_crea_id", nullable = false)
	private Usuario						responsableCambioPrecio;

	@ManyToOne
	@JoinColumn(name = "usua_actu_id", nullable = false)
	private Usuario						responbleAceptarPrecio;

	@Column(name = "estado")
	private Integer						estado;																		// 1 = Pendiente de aceptar 2 = Aceptado 3 Rechazado
	@Column(name = "precio_nuev", precision = 18, scale = 5)
	private Double						precioPublicoNuevo;

	@Column(name = "precio_ant", precision = 18, scale = 5)
	private Double						precioPublicoAnterior;

	@Column(name = "costo_ant", precision = 18, scale = 5)
	private Double						costoAnterior;
	
	@Column(name = "costo_nuevo", precision = 18, scale = 5)
	private Double						costoNuevo;

	@Column(name = "gana_ant", precision = 18, scale = 5)
	private Double						gananciaAnterior;
	
	@Column(name = "gana_nuev", precision = 18, scale = 5)
	private Double						gananciaNueva;
	
	
	@Column(name = "consecutivo")
	private String						consecutivo;
	@Column(name = "ruta_archivo")
	private String						rutaArchivoCompra;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;
	@Column(name = "clave", precision = 18, scale = 5)
	private String						clave;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Articulo getArticulo() {
		return articulo;
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


	public Double getPrecioPublicoNuevo() {
		return precioPublicoNuevo;
	}

	public void setPrecioPublicoNuevo(Double precioPublicoNuevo) {
		this.precioPublicoNuevo = precioPublicoNuevo;
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

	
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	
	public Date getUpdated_at() {
		return updated_at;
	}

	
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	
	public Double getPrecioPublicoAnterior() {
		return precioPublicoAnterior;
	}

	
	public void setPrecioPublicoAnterior(Double precioPublicoAnterior) {
		this.precioPublicoAnterior = precioPublicoAnterior;
	}

	
	public Double getCostoAnterior() {
		return costoAnterior;
	}

	
	public void setCostoAnterior(Double costoAnterior) {
		this.costoAnterior = costoAnterior;
	}

	
	public Double getCostoNuevo() {
		return costoNuevo;
	}

	
	public void setCostoNuevo(Double costoNuevo) {
		this.costoNuevo = costoNuevo;
	}

	
	public Double getGananciaAnterior() {
		return gananciaAnterior;
	}

	
	public void setGananciaAnterior(Double gananciaAnterior) {
		this.gananciaAnterior = gananciaAnterior;
	}

	
	public Double getGananciaNueva() {
		return gananciaNueva;
	}

	
	public void setGananciaNueva(Double gananciaNueva) {
		this.gananciaNueva = gananciaNueva;
	}

	
	public String getCodigo() {
		return codigo;
	}

	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public String getClave() {
		return clave;
	}

	
	public void setClave(String clave) {
		this.clave = clave;
	}

	
	public String getCodigoNuevo() {
		return codigoNuevo;
	}

	
	public void setCodigoNuevo(String codigoNuevo) {
		this.codigoNuevo = codigoNuevo;
	}

}

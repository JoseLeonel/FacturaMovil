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

import com.emprendesoftcr.Utils.Constantes;

/**
 * Modelo de los clientes de una empresa Cliente.
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long	serialVersionUID	= 3716590145404048451L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;

	@Column(name = "nombre_completo")
	private String						nombreCompleto;

	@Column(name = "nombre_comercial")
	private String						nombreComercial;

	@Column(name = "tipo_cedula")
	private String						tipoCedula;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "identificacion_Extranjero")
	private String						identificacionExtranjero;

	@Column(name = "provincia")
	private String						provincia;

	@Column(name = "canton")
	private String						canton;

	@Column(name = "distrito")
	private String						distrito;

	@Column(name = "barrio")
	private String						barrio;

	@Column(name = "celular")
	private Integer						celular;

	@Column(name = "codigo_pais")
	private Integer						codigoPais;

	@Column(name = "telefono")
	private Integer						telefono;

	@Column(name = "otraSena")
	private String						otraSena;

	@Column(name = "correo_electronico")
	private String						correoElectronico;

	@Column(name = "correo_electronico1")
	private String						correoElectronico1;
	@Column(name = "correo_electronico2")
	private String						correoElectronico2;
	@Column(name = "correo_electronico3")
	private String						correoElectronico3;

	@Column(name = "descuento")
	private Integer						descuento;

	@Column(name = "estado")
	private String						estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "obse_venta", columnDefinition = "varchar(80) default ' '")
	private String						observacionVenta;

	@Column(name = "tipo_doc_exo", length = 2)
	private String						tipoDocumentoExoneracion;

	@Column(name = "nume_doc_exo", length = 40)
	private String						numeroDocumentoExoneracion;

	@Column(name = "nomb_inst_exo", length = 160)
	private String						nombreInstitucionExoneracion;

	@Column(name = "porcentaje_exo", columnDefinition = "INT default '0'")
	private Integer						porcentajeExoneracion;

	@Column(name = "libre_imp", columnDefinition = "INT default '0'")
	private Integer						libreImpuesto;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "fecha_emision_exo")
	private Date							fechaEmisionExoneracion;

	@Column(name = "tipo_mag", columnDefinition = "INT default '0'")
	private Integer						tipoMag;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	

	public Cliente(Long id, String nombreCompleto, String nombreComercial, String tipoCedula, String cedula, String identificacionExtranjero, String provincia, String canton, String distrito, String barrio, Integer celular, Integer codigoPais, Integer telefono, String otraSena, String correoElectronico, String correoElectronico1, String correoElectronico2, String correoElectronico3, Integer descuento, String estado, Date created_at, Date updated_at, String observacionVenta, String tipoDocumentoExoneracion, String numeroDocumentoExoneracion, String nombreInstitucionExoneracion, Integer porcentajeExoneracion, Integer libreImpuesto, Date fechaEmisionExoneracion, Integer tipoMag, Empresa empresa, Usuario usuario) {
		super();
		this.id = id;
		this.nombreCompleto = nombreCompleto;
		this.nombreComercial = nombreComercial;
		this.tipoCedula = tipoCedula;
		this.cedula = cedula;
		this.identificacionExtranjero = identificacionExtranjero;
		this.provincia = provincia;
		this.canton = canton;
		this.distrito = distrito;
		this.barrio = barrio;
		this.celular = celular;
		this.codigoPais = codigoPais;
		this.telefono = telefono;
		this.otraSena = otraSena;
		this.correoElectronico = correoElectronico;
		this.correoElectronico1 = correoElectronico1;
		this.correoElectronico2 = correoElectronico2;
		this.correoElectronico3 = correoElectronico3;
		this.descuento = descuento;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.observacionVenta = observacionVenta;
		this.tipoDocumentoExoneracion = tipoDocumentoExoneracion;
		this.numeroDocumentoExoneracion = numeroDocumentoExoneracion;
		this.nombreInstitucionExoneracion = nombreInstitucionExoneracion;
		this.porcentajeExoneracion = porcentajeExoneracion;
		this.libreImpuesto = libreImpuesto;
		this.fechaEmisionExoneracion = fechaEmisionExoneracion;
		this.tipoMag = tipoMag;
		this.empresa = empresa;
		this.usuario = usuario;
	}

	public Integer getLibreImpuesto() {
		return libreImpuesto;
	}

	public void setLibreImpuesto(Integer libreImpuesto) {
		this.libreImpuesto = libreImpuesto;
	}

	public Cliente() {
		super();
		this.estado = Constantes.ESTADO_ACTIVO;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumentoExoneracion() {
		return tipoDocumentoExoneracion;
	}

	public void setTipoDocumentoExoneracion(String tipoDocumentoExoneracion) {
		this.tipoDocumentoExoneracion = tipoDocumentoExoneracion;
	}

	public String getNumeroDocumentoExoneracion() {
		return numeroDocumentoExoneracion;
	}

	public void setNumeroDocumentoExoneracion(String numeroDocumentoExoneracion) {
		this.numeroDocumentoExoneracion = numeroDocumentoExoneracion;
	}

	public String getNombreInstitucionExoneracion() {
		return nombreInstitucionExoneracion;
	}

	public void setNombreInstitucionExoneracion(String nombreInstitucionExoneracion) {
		this.nombreInstitucionExoneracion = nombreInstitucionExoneracion;
	}

	public Date getFechaEmisionExoneracion() {
		return fechaEmisionExoneracion;
	}

	public void setFechaEmisionExoneracion(Date fechaEmisionExoneracion) {
		this.fechaEmisionExoneracion = fechaEmisionExoneracion;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getOtraSena() {
		return otraSena;
	}

	public void setOtraSena(String otraSena) {
		this.otraSena = otraSena;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTipoCedula() {
		return tipoCedula;
	}

	public void setTipoCedula(String tipoCedula) {
		this.tipoCedula = tipoCedula;
	}

	public String getIdentificacionExtranjero() {
		return identificacionExtranjero;
	}

	public void setIdentificacionExtranjero(String identificacionExtranjero) {
		this.identificacionExtranjero = identificacionExtranjero;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Integer getDescuento() {
		return descuento;
	}

	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public Integer getCelular() {
		return celular;
	}

	public String getCorreoElectronico1() {
		return correoElectronico1;
	}

	public void setCorreoElectronico1(String correoElectronico1) {
		this.correoElectronico1 = correoElectronico1;
	}

	public String getCorreoElectronico2() {
		return correoElectronico2;
	}

	public void setCorreoElectronico2(String correoElectronico2) {
		this.correoElectronico2 = correoElectronico2;
	}

	public String getCorreoElectronico3() {
		return correoElectronico3;
	}

	public void setCorreoElectronico3(String correoElectronico3) {
		this.correoElectronico3 = correoElectronico3;
	}

	public String getObservacionVenta() {
		return observacionVenta;
	}

	public void setObservacionVenta(String observacionVenta) {
		this.observacionVenta = observacionVenta;
	}

	public Integer getPorcentajeExoneracion() {
		return porcentajeExoneracion;
	}

	public void setPorcentajeExoneracion(Integer porcentajeExoneracion) {
		this.porcentajeExoneracion = porcentajeExoneracion;
	}

	
	public Integer getTipoMag() {
		return tipoMag;
	}

	
	public void setTipoMag(Integer tipoMag) {
		this.tipoMag = tipoMag;
	}
	

}
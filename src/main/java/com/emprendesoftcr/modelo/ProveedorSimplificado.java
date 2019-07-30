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

@Entity
@Table(name = "provee_simpli")
public class ProveedorSimplificado implements Serializable {

	private static final long	serialVersionUID	= 359421617446093903L;

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
	@Column(name = "codigo_pais")
	private Integer						codigoPais;

	@Column(name = "telefono")
	private Integer						telefono;

	@Column(name = "identificacion_Extranjero")
	private String						identificacionExtranjero;

	@Column(name = "correo_electronico")
	private String						correoElectronico;

	@Column(name = "estado")
	private Integer						estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "cod_actividad", length = 6)
	private String						codigoActividad;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public ProveedorSimplificado() {
		super();
	}

	public ProveedorSimplificado(Long id, String nombreCompleto, String nombreComercial, String tipoCedula, String cedula, Integer codigoPais, Integer telefono, String identificacionExtranjero, String correoElectronico, Integer estado, Date created_at, Date updated_at, String codigoActividad, Empresa empresa, Usuario usuario) {
		super();
		this.id = id;
		this.nombreCompleto = nombreCompleto;
		this.nombreComercial = nombreComercial;
		this.tipoCedula = tipoCedula;
		this.cedula = cedula;
		this.codigoPais = codigoPais;
		this.telefono = telefono;
		this.identificacionExtranjero = identificacionExtranjero;
		this.correoElectronico = correoElectronico;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.codigoActividad = codigoActividad;
		this.empresa = empresa;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getIdentificacionExtranjero() {
		return identificacionExtranjero;
	}

	public void setIdentificacionExtranjero(String identificacionExtranjero) {
		this.identificacionExtranjero = identificacionExtranjero;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

}

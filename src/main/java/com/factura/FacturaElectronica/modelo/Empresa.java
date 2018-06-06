package com.factura.FacturaElectronica.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.factura.FacturaElectronica.Utils.Constantes;

@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

	private static final long	serialVersionUID	= 2782215506581188984L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;
	
	@Column(name = "numero_consecutivo")
	private Double numeroConsecutivo;

	@Column(name = "nombre")
	private String						nombre;

	@Column(name = "nombre_comercial")
	private String						nombreComercial;

	@Column(name = "clave")
	private String						clave;

	@Column(name = "tipo_cedula")
	private String						tipoCedula;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "otra_senas")
	private String						otraSenas;

	@Column(name = "codigo_pais")
	private String						codigoPais;

	@Column(name = "telefono")
	private String						telefono;

	@Column(name = "correo_electronico")
	private String						correoElectronico;

	@Column(name = "provincia")
	private String						provincia;

	@Column(name = "distrito")
	private String						distrito;

	@Column(name = "barrio")
	private String						barrio;

	@Column(name = "logo")
	private String						logo;

	@Column(name = "representante")
	private String						representante;

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

	public Empresa(Integer id, String nombre, String nombreComercial, String clave, String tipoCedula, String otraSenas, String codigoPais, String telefono, String correoElectronico, String provincia, String distrito, String barrio, String logo, String representante, String estado, String cedula, Date updated_at, Date created_at,Double numeroConsecutivo) {
		super();
		this.id = id;
		this.numeroConsecutivo = numeroConsecutivo;
		this.nombre = nombre;
		this.nombreComercial = nombreComercial;
		this.clave = clave;
		this.tipoCedula = tipoCedula;
		this.otraSenas = otraSenas;
		this.codigoPais = codigoPais;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.provincia = provincia;
		this.distrito = distrito;
		this.barrio = barrio;
		this.logo = logo;
		this.representante = representante;
		this.estado = Constantes.ESTADO_ACTIVO;
		this.cedula = cedula;
		this.created_at = new Date();
		this.updated_at = new Date();
	}

	public Empresa() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();
		this.estado = Constantes.ESTADO_ACTIVO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Double getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	
	public void setNumeroConsecutivo(Double numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getTipoCedula() {
		return tipoCedula;
	}

	public void setTipoCedula(String tipoCedula) {
		this.tipoCedula = tipoCedula;
	}

	public String getOtraSenas() {
		return otraSenas;
	}

	public void setOtraSenas(String otraSenas) {
		this.otraSenas = otraSenas;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
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

}
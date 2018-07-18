package com.emprendesoftcr.modelo;

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

import com.emprendesoftcr.Utils.Constantes;

@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

	private static final long	serialVersionUID	= 2782215506581188984L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "numero_consecutivo")
	private Integer						numeroConsecutivo;

	@Column(name = "caza_matriz")
	private String						cazaMatriz;
	
	@Column(name = "codigo_seguridad")
	private Integer						codigoSeguridad;


	@Column(name = "nombre")
	private String						nombre;

	@Column(name = "nombre_comercial")
	private String						nombreComercial;

	

	@Column(name = "tipo_cedula")
	private String						tipoCedula;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "otra_senas")
	private String						otraSenas;

	@Column(name = "codigo_pais")
	private Integer						codigoPais;

	@Column(name = "telefono")
	private Integer						telefono;

	@Column(name = "correo_electronico")
	private String						correoElectronico;

	@Column(name = "provincia")
	private String						provincia;

	@Column(name = "canton")
	private String						canton;

	@Column(name = "distrito")
	private String						distrito;

	@Column(name = "barrio")
	private String						barrio;

	@Column(name = "logo")
	private String						logo;

	@Column(name = "representante")
	private String						representante;

	@Column(name = "nombre_llaveCriptografica")
	private String						nombreLlaveCriptografica;

	@Column(name = "clave_llaveCriptografica")
	private Integer						claveLlaveCriptografica;
	
	@Column(name = "usuario_envio_comprobante")
	private String						usuarioEnvioComprobante;

	@Column(name = "password_envio_comprobante")
	private String						passwordEnvioComprobante;

	
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

	public Empresa(Integer id, String nombre, String nombreComercial, String tipoCedula, String otraSenas, Integer codigoPais, Integer telefono, String correoElectronico, String provincia, String distrito, String barrio, String logo, String representante, String estado, String cedula, Date updated_at, Date created_at, Integer numeroConsecutivo, String casaMatriz, String canton, String nombreLlaveCriptografica, Integer claveLlaveCriptografica,Integer codigoSeguridad,String usuarioEnvioComprobante,String passwordEnvioComprobante) {
		super();
		this.id = id;
		this.numeroConsecutivo = numeroConsecutivo;
		this.nombre = nombre;
		this.nombreComercial = nombreComercial;
	
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
		this.cazaMatriz = Constantes.CASA_MATRIZ_INICIAL_FACTURA;
		this.canton = canton;
		this.codigoSeguridad = codigoSeguridad;
		this.nombreLlaveCriptografica = nombreLlaveCriptografica;
		this.claveLlaveCriptografica = claveLlaveCriptografica;
		
		this.passwordEnvioComprobante = passwordEnvioComprobante;
		this.usuarioEnvioComprobante = usuarioEnvioComprobante;
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

	public Integer getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(Integer numeroConsecutivo) {
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

	public String getCazaMatriz() {
		return cazaMatriz;
	}

	public void setCazaMatriz(String cazaMatriz) {
		this.cazaMatriz = cazaMatriz;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getNombreLlaveCriptografica() {
		return nombreLlaveCriptografica;
	}

	public void setNombreLlaveCriptografica(String nombreLlaveCriptografica) {
		this.nombreLlaveCriptografica = nombreLlaveCriptografica;
	}

	public Integer getClaveLlaveCriptografica() {
		return claveLlaveCriptografica;
	}

	public void setClaveLlaveCriptografica(Integer claveLlaveCriptografica) {
		this.claveLlaveCriptografica = claveLlaveCriptografica;
	}

	
	public Integer getCodigoSeguridad() {
		return codigoSeguridad;
	}

	
	public void setCodigoSeguridad(Integer codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	
	public String getUsuarioEnvioComprobante() {
		return usuarioEnvioComprobante;
	}

	
	public void setUsuarioEnvioComprobante(String usuarioEnvioComprobante) {
		this.usuarioEnvioComprobante = usuarioEnvioComprobante;
	}

	
	public String getPasswordEnvioComprobante() {
		return passwordEnvioComprobante;
	}

	
	public void setPasswordEnvioComprobante(String passwordEnvioComprobante) {
		this.passwordEnvioComprobante = passwordEnvioComprobante;
	}
	
	

}
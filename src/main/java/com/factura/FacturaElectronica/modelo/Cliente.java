package com.factura.FacturaElectronica.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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

import com.factura.FacturaElectronica.Utils.Constantes;

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
	private Integer						id;

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
	private String						celular;

	@Column(name = "codigo_pais")
	private BigInteger				codigoPais;

	@Column(name = "telefono")
	private BigInteger				telefono;

	@Column(name = "otraSena")
	private String						otraSena;

	@Column(name = "correo_electronico")
	private String						correoElectronico;

	@Column(name = "descuento")
	private BigDecimal				descuento;

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

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario						usuario;

	public Cliente(Integer id, String nombreCompleto, String nombreComercial, String tipoCedula, String cedula, String identificacionExtranjero, String provincia, String canton, String distrito, String barrio, String celular, BigInteger codigoPais, BigInteger telefono, String otraSena, String correoElectronico, BigDecimal descuento, String estado, Date created_at, Date updated_at, Empresa empresa, Usuario usuario) {
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
		this.descuento = descuento;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.empresa = empresa;
		this.usuario = usuario;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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

	public BigInteger getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(BigInteger codigoPais) {
		this.codigoPais = codigoPais;
	}

	public BigInteger getTelefono() {
		return telefono;
	}

	public void setTelefono(BigInteger telefono) {
		this.telefono = telefono;
	}

}
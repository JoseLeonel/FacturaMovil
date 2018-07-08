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
 * Modelo
 * Proveedor.
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 4263456567318625235L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "razon_social")
	private String						razonSocial;

	@Column(name = "nombre_completo")
	private String						nombreCompleto;

	@Column(name = "representante")
	private String						representante;

	@Column(name = "email")
	private String						email;

	@Column(name = "telefono")
	private String						telefono;

	@Column(name = "movil")
	private String						movil;

	@Column(name = "direccion")
	private String						direccion;

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

	public Proveedor() {
		super();
		this.estado = Constantes.ESTADO_ACTIVO;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	
	public Proveedor(Integer id, String cedula, String razonSocial, String nombreCompleto, String representante, String email, String telefono, String movil, String direccion, String estado, Date created_at, Date updated_at, Empresa empresa) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.razonSocial = razonSocial;
		this.nombreCompleto = nombreCompleto;
		this.representante = representante;
		this.email = email;
		this.telefono = telefono;
		this.movil = movil;
		this.direccion = direccion;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.empresa = empresa;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	

	
	public String getNombreCompleto() {
		return nombreCompleto;
	}


	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

}
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

import com.emprendesoftcr.utils.Constantes;

/**
 * Vendedor.
 * @author jose.
 * @since 19 abr. 2018
 */
@Entity
@Table(name = "vendedores")
public class Vendedor implements Serializable {

	private static final long	serialVersionUID	= -2810965715944976509L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long						id;

	@Column(name = "nombre_completo")
	private String						nombreCompleto;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "celular")
	private Integer						celular;

	@Column(name = "telefono")
	private Integer						telefono;

	@Column(name = "correo_electronico")
	private String						correoElectronico;

	@Column(name = "otra_sena")
	private String						otraSena;

	@Column(name = "descuento")
	private Double							descuento;

	@Column(name = "porcentaje_comision")
	private Double							porcentajeComision;

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

	public Vendedor() {

		super();
		this.estado = Constantes.ESTADO_ACTIVO;
		this.created_at = new Date();
		this.updated_at = new Date();

	}

	

	


	public Vendedor(Long id, String nombreCompleto, String cedula, Integer celular, Integer telefono, String correoElectronico, String otraSena, Double descuento, Double porcentajeComision, String estado, Date created_at, Date updated_at, Empresa empresa, Usuario usuario) {
		super();
		this.id = id;
		this.nombreCompleto = nombreCompleto;
		this.cedula = cedula;
		this.celular = celular;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.otraSena = otraSena;
		this.descuento = descuento;
		this.porcentajeComision = porcentajeComision;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.empresa = empresa;
		this.usuario = usuario;
	}






	

	
	public Long getId() {
		return id;
	}






	
	public void setId(Long id) {
		this.id = id;
	}






	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Double getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(Double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
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

	public String getOtraSena() {
		return otraSena;
	}

	public void setOtraSena(String otraSena) {
		this.otraSena = otraSena;
	}



	
	public Integer getCelular() {
		return celular;
	}



	
	public void setCelular(Integer celular) {
		this.celular = celular;
	}



	
	public Integer getTelefono() {
		return telefono;
	}



	
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

}
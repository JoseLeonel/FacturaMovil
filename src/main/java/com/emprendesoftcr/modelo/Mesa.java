package com.emprendesoftcr.modelo;

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

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Mesa. Mesas por empresas
 * @author jose.
 * @since 4 sep. 2018
 */
@Entity
@Table(name = "mesas")
public class Mesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estado")
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date updated_at;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	@Column(name = "impuestos_servicio")
	private Boolean impuestoServicio;

	@Column(name = "prioridad")
	private Integer prioridad;

	@Formula("(select count(*) from facturas fact where fact.mesa_id = id and fact.estado = 1)")
	private Integer tieneFacturas;

	@Formula("(select count(*) from comanda_mesa comanda where comanda.mesa_id = id and comanda.empresa_id = empresa_id and comanda.estado = 1)")
	private Integer comandasPendientes;

	public Mesa() {
		super();
	}

	public Mesa(Long id, String descripcion, String estado, Date created_at, Date updated_at, Empresa empresa, Boolean impuestoServicio, Integer prioridad) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.empresa = empresa;
		this.prioridad = prioridad;
		this.impuestoServicio = impuestoServicio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Boolean getImpuestoServicio() {
		return impuestoServicio;
	}

	public void setImpuestoServicio(Boolean impuestoServicio) {
		this.impuestoServicio = impuestoServicio;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Integer getTieneFacturas() {
		return tieneFacturas;
	}

	public void setTieneFacturas(Integer tieneFacturas) {
		this.tieneFacturas = tieneFacturas;
	}

	public Integer getComandasPendientes() {
		return comandasPendientes;
	}

	public void setComandasPendientes(Integer comandasPendientes) {
		this.comandasPendientes = comandasPendientes;
	}

}

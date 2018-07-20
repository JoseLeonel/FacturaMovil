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
 * 
 * Semaforo.  Si una empresa tiene problemas con los envios de tributacion se bloquea y no se envia hasta que se solucione el problema
 * @author jose.
 * @since 19 jul. 2018
 */
@Entity
@Table(name = "semaforos")
public class Semaforo implements Serializable {

	
	private static final long serialVersionUID = -3968492647575905798L;

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer	id;

	@Column(name = "descripcion")
	private String	descripcion;

	@Column(name = "estado")
	private Integer	estado;
	

	@Column(name = "maxi_reintentos")
	private Integer	maximoReintentosEnviar;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date		created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date		updated_at;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa	empresa;

	public Semaforo(Integer id, String descripcion, Integer estado, Date created_at, Date updated_at, Empresa empresa,Integer maximoReintentosEnviar) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estado = Constantes.SEMAFORO_ESTADO_ACTIVO;
		this.created_at = new Date();
		this.updated_at = new Date();
		this.empresa = empresa;
		this.maximoReintentosEnviar = Constantes.SEMAFORO_ESTADO_REINTENTOS;
	}

	public Semaforo() {
		super();
		this.estado = Constantes.SEMAFORO_ESTADO_ACTIVO;
		this.created_at = new Date();
		this.updated_at = new Date();
		this.maximoReintentosEnviar = Constantes.SEMAFORO_ESTADO_REINTENTOS;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	
	public Integer getMaximoReintentosEnviar() {
		return maximoReintentosEnviar;
	}

	
	public void setMaximoReintentosEnviar(Integer maximoReintentosEnviar) {
		this.maximoReintentosEnviar = maximoReintentosEnviar;
	}

	
	
}

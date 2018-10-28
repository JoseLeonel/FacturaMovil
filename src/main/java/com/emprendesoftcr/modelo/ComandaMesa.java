package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Modelo para llevar las facturas pendientes de una empresa
 * @author Jairo Cisneros
 */
@Entity
@Table(name = "comandaMesa")
public class ComandaMesa implements Serializable {

	private static final long serialVersionUID = 3236006908675739266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "mesa_id")
	private Mesa mesa;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "unidad_medida")
	private String unidadMedida;

	@Column(name = "comentario")
	private String comentario;

	// 1 = Pendiente 0 = Enviado
	@Column(name = "estado")
	private Integer estado;

	public ComandaMesa() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
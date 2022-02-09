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
 * Configuracion de la Romada
 * @author jose
 */
@Entity
@Table(name = "roma_balanza")
public class RomadaBalanza implements Serializable {

	private static final long	serialVersionUID	= -1162864718309095796L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long							id;
	@Column(name = "inicio_precio")
	private Integer						inicioPrecio;
	@Column(name = "final_precio")
	private Integer						finalPrecio;
	@Column(name = "inicio_codigo")
	private Integer						inicioCodigo;
	@Column(name = "final_codigo")
	private Integer						finalCodigo;
	@Column(name = "inic_peso_K")
	private Integer						inicioPesoKilos;
	@Column(name = "final_peso_K")
	private Integer						finalPesoKilos;
	@Column(name = "inic_peso_g")
	private Integer						inicioPesoGramos;
	@Column(name = "final_peso_g")
	private Integer						finalPesoGramos;
	@Column(name = "tipo")
	private Integer						tipo;																			// 1= precio 2 = peso
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	public Integer getInicioPrecio() {
		return inicioPrecio;
	}

	public void setInicioPrecio(Integer inicioPrecio) {
		this.inicioPrecio = inicioPrecio;
	}

	public Integer getFinalPrecio() {
		return finalPrecio;
	}

	public void setFinalPrecio(Integer finalPrecio) {
		this.finalPrecio = finalPrecio;
	}

	public Integer getInicioCodigo() {
		return inicioCodigo;
	}

	public void setInicioCodigo(Integer inicioCodigo) {
		this.inicioCodigo = inicioCodigo;
	}

	public Integer getFinalCodigo() {
		return finalCodigo;
	}

	public void setFinalCodigo(Integer finalCodigo) {
		this.finalCodigo = finalCodigo;
	}

	public Integer getInicioPesoKilos() {
		return inicioPesoKilos;
	}

	public void setInicioPesoKilos(Integer inicioPesoKilos) {
		this.inicioPesoKilos = inicioPesoKilos;
	}

	public Integer getFinalPesoKilos() {
		return finalPesoKilos;
	}

	public void setFinalPesoKilos(Integer finalPesoKilos) {
		this.finalPesoKilos = finalPesoKilos;
	}

	public Integer getInicioPesoGramos() {
		return inicioPesoGramos;
	}

	public void setInicioPesoGramos(Integer inicioPesoGramos) {
		this.inicioPesoGramos = inicioPesoGramos;
	}

	public Integer getFinalPesoGramos() {
		return finalPesoGramos;
	}

	public void setFinalPesoGramos(Integer finalPesoGramos) {
		this.finalPesoGramos = finalPesoGramos;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Empresa getEmpresa() {
		return empresa;
	}

	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}

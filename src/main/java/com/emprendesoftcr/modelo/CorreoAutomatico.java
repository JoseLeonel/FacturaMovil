package com.emprendesoftcr.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "correo_compras")
public class CorreoAutomatico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2275532719961075171L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long		id;
	@Column(name = "dirc_carpeta")
	private String	direcionDirectorio;
	@Column(name = "contrasena")
	private String	clave;
	@Column(name = "usuario")
	private String	usuario;
	@Column(name = "correo")
	private String	correoAceptacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDirecionDirectorio() {
		return direcionDirectorio;
	}

	public void setDirecionDirectorio(String direcionDirectorio) {
		this.direcionDirectorio = direcionDirectorio;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCorreoAceptacion() {
		return correoAceptacion;
	}

	public void setCorreoAceptacion(String correoAceptacion) {
		this.correoAceptacion = correoAceptacion;
	}

}

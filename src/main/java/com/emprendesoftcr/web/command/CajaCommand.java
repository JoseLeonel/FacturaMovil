package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;

/**
 * CajaCommand.
 * @author jose.
 * @since 10 jun. 2018
 */
public class CajaCommand {

	private Long	id;

	private String	descripcion;
	private String	terminal;

	private String	estado;

	private Date		created_at;

	private Date		updated_at;

	private Empresa	empresa;
	private Usuario	usuario;

	public CajaCommand(Caja caja) {
		super();
		this.id = caja.getId();
		this.descripcion = caja.getDescripcion();
		this.estado = caja.getEstado();
		this.created_at = caja.getCreated_at();
		this.updated_at = caja.getUpdated_at();
		this.empresa = caja.getEmpresa();
		this.terminal = caja.getTerminal();
		this.usuario = caja.getUsuario();

	}

	public CajaCommand() {
		super();
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

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

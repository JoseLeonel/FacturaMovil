package com.emprendesoftcr.web.command;

import java.util.Date;

import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;

/**
 * CajaCommand.
 * @author jose.
 * @since 10 jun. 2018
 */
public class TipoCambiosCommand {

	private Long	id;

	private String	moneda;

	private Double	total;

	private String	estado;

	private Date		created_at;

	private Date		updated_at;

	private Usuario	usuario;

	public TipoCambiosCommand(TipoCambio tipoCambio) {
		super();
		
		this.id = tipoCambio.getId();
		this.moneda = tipoCambio.getMoneda();
		this.estado = tipoCambio.getEstado();
		this.created_at = tipoCambio.getCreated_at();
		this.updated_at = tipoCambio.getUpdated_at();
		this.usuario = tipoCambio.getUsuario();
		this.total = tipoCambio.getTotal();
	}

	
	

	
	
	public Long getId() {
		return id;
	}





	
	public void setId(Long id) {
		this.id = id;
	}





	public String getMoneda() {
		return moneda;
	}

	
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	
	public Double getTotal() {
		return total;
	}

	
	public void setTotal(Double total) {
		this.total = total;
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
	
	
	

	

}

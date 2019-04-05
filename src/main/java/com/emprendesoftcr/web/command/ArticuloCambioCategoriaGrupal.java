package com.emprendesoftcr.web.command;

import com.emprendesoftcr.modelo.Categoria;

public class ArticuloCambioCategoriaGrupal {
	
	private Long id;
	
	private Categoria categoria;

	public ArticuloCambioCategoriaGrupal() {
		super();
	}

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	
	public Categoria getCategoria() {
		return categoria;
	}


	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

}

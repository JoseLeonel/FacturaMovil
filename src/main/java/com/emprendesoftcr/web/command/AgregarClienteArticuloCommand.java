package com.emprendesoftcr.web.command;

public class AgregarClienteArticuloCommand {

	private Long	idCliente;
	private Long	idArticulo;

	public AgregarClienteArticuloCommand(Long idCliente, Long idArticulo) {
		super();
		this.idCliente = idCliente;
		this.idArticulo = idArticulo;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}

}

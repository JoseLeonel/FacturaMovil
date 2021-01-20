package com.emprendesoftcr.Bo;

import java.util.List;

import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;

public interface IFEMensajeReceptorAutomaticoBo {

	public void save(FEMensajeReceptorAutomatico entity);
	
	public FEMensajeReceptorAutomatico findAll();
	
	public List<FEMensajeReceptorAutomatico> getAll(String estado, String identifiacionCliente,String correoCompra);
	
	public void updateEstado(String estado, Long id);
	public void updateEstadoPorIdentificion(String estado, String cedulaEmisor);

}

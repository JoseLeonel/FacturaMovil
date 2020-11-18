package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;

public interface FEMensajeReceptorAutomaticoDao {
	void agregar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico);

	void modificar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico);

	void eliminar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico);
	
	FEMensajeReceptorAutomatico buscar(Long id);
	
	
}

package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;

public interface FEMensajeReceptorAutomaticoBo {
	void agregar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico);

	void modificar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico);

	void eliminar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico);
	
	FEMensajeReceptorAutomatico buscar(Long id);
}

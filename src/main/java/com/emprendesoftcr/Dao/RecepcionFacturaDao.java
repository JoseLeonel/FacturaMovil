package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.RecepcionFactura;

public interface RecepcionFacturaDao {

	void agregar(RecepcionFactura recepcionFactura);

	void modificar(RecepcionFactura recepcionFactura);
	
	RecepcionFactura findById(Long id);

	Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma);	

	Collection<RecepcionFactura> findByClave(String cedulaEmisor, String clave);
}
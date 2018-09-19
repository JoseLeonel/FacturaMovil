package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.RecepcionFactura;

public interface RecepcionFacturaBo {

	void agregar(RecepcionFactura recepcionFactura);

	void modificar(RecepcionFactura recepcionFactura);

	RecepcionFactura findById(Long id);

	Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma);
	
	Collection<RecepcionFactura> findByClave(String cedulaEmisor, String clave);
}
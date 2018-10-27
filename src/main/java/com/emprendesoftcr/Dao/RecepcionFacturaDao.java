package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;

public interface RecepcionFacturaDao {

	void agregar(RecepcionFactura recepcionFactura);

	void modificar(RecepcionFactura recepcionFactura);
	
	RecepcionFactura findById(Long id);

	RecepcionFactura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception;

	Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma);	

	Collection<RecepcionFactura> findByClave(String cedulaEmisor, String clave);
}
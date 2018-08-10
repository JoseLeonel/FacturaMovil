package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;

public interface FacturaDao {

	void agregar(Factura factura);

	void modificar(Factura factura);

	void eliminar(Factura factura);

	Factura findById(Long id);

	Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	void eliminarDetalleFacturaPorSP(Factura factura);
	
	Collection<Factura> findByEstadoFirma(Integer estadoFirma);
	
	

	

}
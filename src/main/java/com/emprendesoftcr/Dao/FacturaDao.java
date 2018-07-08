package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;

public interface FacturaDao {

	void agregar(Factura factura);

	void modificar(Factura factura);

	void eliminar(Factura factura);

	Factura findById(Integer id);

	Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	void eliminarDetalleFacturaPorSP(Factura factura);

	

}
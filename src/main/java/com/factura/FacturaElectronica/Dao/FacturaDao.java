package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;

public interface FacturaDao {

	void agregar(Factura factura);

	void modificar(Factura factura);

	void eliminar(Factura factura);

	Factura findById(Integer id);

	Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	void eliminarDetalleFacturaPorSP(Factura factura);

	

}
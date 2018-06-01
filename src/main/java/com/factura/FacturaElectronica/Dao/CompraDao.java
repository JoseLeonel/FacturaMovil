package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Compra;
import com.factura.FacturaElectronica.modelo.Empresa;

public interface CompraDao {

	void agregar(Compra compra);

	void modificar(Compra compra);

	void eliminar(Compra compra);

	Compra findById(Integer id);

	Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	void eliminarDetalleComprasPorSP(Compra compra);

	

}
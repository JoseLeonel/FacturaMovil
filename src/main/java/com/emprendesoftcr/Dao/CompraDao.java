package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.Empresa;

public interface CompraDao {

	void agregar(Compra compra);

	void modificar(Compra compra);

	void eliminar(Compra compra);

	Compra findById(Long id);

	Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	void eliminarDetalleComprasPorSP(Compra compra);

	

}
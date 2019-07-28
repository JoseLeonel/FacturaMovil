package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;

public interface CompraSimplificadaDao {

	void agregar(CompraSimplificada compraSimplificada);

	void modificar(CompraSimplificada compraSimplificada);

	void eliminar(CompraSimplificada compraSimplificada);

	CompraSimplificada findById(Long id);

	CompraSimplificada findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	void eliminarDetalleComprasPorSP(CompraSimplificada compraSimplificada);
	
	CompraSimplificada findByClaveAndEmpresa(String clave, Empresa empresa) throws Exception;
	

	

}
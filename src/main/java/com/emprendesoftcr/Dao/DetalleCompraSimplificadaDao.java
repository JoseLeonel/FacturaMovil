package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;

public interface DetalleCompraSimplificadaDao {

	void agregar(DetalleCompraSimplificada detalleCompraSimplificada);

	void modificar(DetalleCompraSimplificada detalleCompraSimplificada);

	void eliminar(DetalleCompraSimplificada detalleCompraSimplificada);

	Integer eliminarDetalleFactura(CompraSimplificada compraSimplificada) throws Exception;

	DetalleCompraSimplificada findByCodigoAndEmpresa(String codigo, Empresa empresa);
	
	Collection<DetalleCompraSimplificada> findByCompraSimplificada(CompraSimplificada compraSimplificada);

}
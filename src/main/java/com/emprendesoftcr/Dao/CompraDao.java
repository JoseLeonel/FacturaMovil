package com.emprendesoftcr.Dao;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.web.command.TotalComprasAceptadasCommand;

public interface CompraDao {

	void agregar(Compra compra);

	void modificar(Compra compra);

	void eliminar(Compra compra);

	Compra findById(Long id);

	Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);
	
	void eliminarDetalleComprasPorSP(Compra compra);
	
	Collection<Compra> findByFechaInicioAndFechaFinalAndProveedor(Date fechaInicio, Date fechaFin, Empresa empresa,  Proveedor proveedor);
	
	TotalComprasAceptadasCommand sumarComprasAceptadas(Date fechaInicio, Date fechaFinal, Integer idEmpresa,Integer estado);

	

}
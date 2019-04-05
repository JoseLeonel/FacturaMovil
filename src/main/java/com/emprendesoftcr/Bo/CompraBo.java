package com.emprendesoftcr.Bo;

import java.util.Collection;
import java.util.Date;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.CompraCommand;
import com.emprendesoftcr.web.command.TotalComprasAceptadasCommand;

public interface CompraBo {

	void agregar(Compra compra);

	void modificar(Compra compra);

	void eliminar(Compra compra);

	Compra findById(Long id);

	Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);

	void crearCompra(CompraCommand compraCommand,Usuario usuario) throws Exception;

	void aplicarInventario(Compra compra,  DetalleCompra detalleCompra, Articulo articulo)throws Exception;
	
	void eliminarDetalleComprasPorSP(Compra compra)throws Exception;
	
	Collection<Compra> findByFechaInicioAndFechaFinalAndProveedor(Date fechaInicio, Date fechaFin, Empresa empresa,  Proveedor proveedor);
	
	 TotalComprasAceptadasCommand sumarComprasAceptadas(Date fechaInicio, Date fechaFinal, Integer idEmpresa);


}
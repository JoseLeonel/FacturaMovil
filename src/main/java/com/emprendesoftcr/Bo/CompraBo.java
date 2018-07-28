package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Inventario;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.CompraCommand;

public interface CompraBo {

	void agregar(Compra compra);

	void modificar(Compra compra);

	void eliminar(Compra compra);

	Compra findById(Long id);

	Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);

	void crearCompra(CompraCommand compraCommand,Usuario usuario) throws Exception;

	void aplicarInventario(Compra compra, Inventario inventario, DetalleCompra detalleCompra, Articulo articulo);
	
	void eliminarDetalleComprasPorSP(Compra compra);

}
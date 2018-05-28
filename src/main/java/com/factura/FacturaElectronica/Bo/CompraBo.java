package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Compra;
import com.factura.FacturaElectronica.modelo.DetalleCompra;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.web.command.CompraCommand;

public interface CompraBo {

	void agregar(Compra compra);

	void modificar(Compra compra);

	void eliminar(Compra compra);

	Compra findById(Integer id);

	Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa);

	void crearCompra(CompraCommand compraCommand) throws Exception;

	void aplicarInventario(Compra compra, Inventario inventario, DetalleCompra detalleCompra, Articulo articulo);

}
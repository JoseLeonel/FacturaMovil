package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.DetalleCompra;

public interface DetalleCompraDao {

	void agregar(DetalleCompra detalleCompra);

	void modificar(DetalleCompra detalleCompra);

	void eliminar(DetalleCompra detalleCompra);

	


}
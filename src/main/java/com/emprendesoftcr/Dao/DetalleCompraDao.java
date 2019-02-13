package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Factura;

public interface DetalleCompraDao {

	void agregar(DetalleCompra detalleCompra);

	void modificar(DetalleCompra detalleCompra);

	void eliminar(DetalleCompra detalleCompra);
	
	Collection<DetalleCompra> findByCompra(Compra compra);
	

	


}
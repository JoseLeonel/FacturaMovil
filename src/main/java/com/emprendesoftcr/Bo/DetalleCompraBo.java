package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;

public interface DetalleCompraBo {
	

	void agregar(DetalleCompra detalleCompra);

	void modificar(DetalleCompra detalleCompra);

	void eliminar(DetalleCompra detalleCompra);
	
	Collection<DetalleCompra> findByCompra(Compra compra);
	
	

}

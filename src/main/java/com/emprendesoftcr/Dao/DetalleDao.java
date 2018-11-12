package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Factura;

public interface DetalleDao {

	void agregar(Detalle detalle);

	void modificar(Detalle detalle);

	void eliminar(Detalle detalle);
	
	
	Integer eliminarDetalleFactura(Factura factura)throws Exception;
	
		


}
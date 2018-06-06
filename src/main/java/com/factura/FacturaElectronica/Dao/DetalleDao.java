package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Detalle;

public interface DetalleDao {

	void agregar(Detalle detalle);

	void modificar(Detalle detalle);

	void eliminar(Detalle detalle);

	


}
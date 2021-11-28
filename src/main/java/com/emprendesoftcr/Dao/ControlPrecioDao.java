package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.ControlPrecioArticulo;

public interface ControlPrecioDao {
	void agregar(ControlPrecioArticulo controlPrecioArticulo);

	void modificar(ControlPrecioArticulo controlPrecioArticulo);

	void eliminar(ControlPrecioArticulo controlPrecioArticulo);

	ControlPrecioArticulo buscar(Long id);
}

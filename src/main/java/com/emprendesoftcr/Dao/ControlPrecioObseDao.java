package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.ControlPrecioObse;

public interface ControlPrecioObseDao {

	void agregar(ControlPrecioObse controlPrecioObse);

	void modificar(ControlPrecioObse controlPrecioObse);

	void eliminar(ControlPrecioObse controlPrecioObse);

	ControlPrecioObse buscar(Long id);
}

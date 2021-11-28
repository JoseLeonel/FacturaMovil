package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.ControlPrecioObse;

public interface ControlPrecioObseBo {
	void agregar(ControlPrecioObse controlPrecioObse);

	void modificar(ControlPrecioObse controlPrecioObse);

	void eliminar(ControlPrecioObse controlPrecioObse);

	ControlPrecioObse buscar(Long id);


}

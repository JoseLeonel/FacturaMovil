package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Tarifa;

public interface TarifaBo {

	void agregar(Tarifa tarifa);

	void modificar(Tarifa tarifa);

	void eliminar(Tarifa tarifa);

	Tarifa buscar(Long id);

	

}
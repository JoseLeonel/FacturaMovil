package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Tarifa;

public interface TarifaDao {

	void agregar(Tarifa tarifa);

	void modificar(Tarifa tarifa);

	void eliminar(Tarifa tarifa);

	Tarifa buscar(Long id);


	
}
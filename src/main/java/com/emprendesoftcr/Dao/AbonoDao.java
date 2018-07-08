package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Abono;

public interface AbonoDao {

	void agregar(Abono abono);

	void modificar(Abono abono);

	void eliminar(Abono abono);

	Abono buscar(Integer id);	
	
	Collection<Abono> buscarPorCuentaCobrar();

}
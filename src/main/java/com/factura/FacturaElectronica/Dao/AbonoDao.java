package com.factura.FacturaElectronica.Dao;

import java.util.Collection;

import com.factura.FacturaElectronica.modelo.Abono;

public interface AbonoDao {

	void agregar(Abono abono);

	void modificar(Abono abono);

	void eliminar(Abono abono);

	Abono buscar(Integer id);	
	
	Collection<Abono> buscarPorCuentaCobrar();

}
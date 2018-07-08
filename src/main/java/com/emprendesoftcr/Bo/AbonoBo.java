package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Abono;

public interface AbonoBo {

	void agregar(Abono abono);

	void modificar(Abono abono);

	void eliminar(Abono abono);

	Abono buscar(Integer id);

	Collection<Abono> buscarPorCuentaCobrar();

}
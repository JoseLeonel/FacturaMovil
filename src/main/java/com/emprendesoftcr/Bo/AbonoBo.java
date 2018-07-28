package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Abono;

public interface AbonoBo {

	void agregar(Abono abono);

	void modificar(Abono abono);

	void eliminar(Abono abono);

	Abono buscar(Long id);

	Collection<Abono> buscarPorCuentaCobrar();

}
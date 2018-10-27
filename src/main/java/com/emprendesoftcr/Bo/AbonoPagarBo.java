package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.AbonoPagar;

public interface AbonoPagarBo {

	void agregar(AbonoPagar abonoPagar);

	void modificar(AbonoPagar abonoPagar);

	void eliminar(AbonoPagar abonoPagar);

	AbonoPagar buscar(Long id);

	Collection<AbonoPagar> buscarPorCuentaPagar();

}
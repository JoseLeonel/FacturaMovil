package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.UsuarioCaja;

public interface ConteoManualCajaDao {

	void agregar(ConteoManualCaja conteoManualCaja);

	void modificar(ConteoManualCaja conteoManualCaja);

	void eliminar(ConteoManualCaja conteoManualCaja);

	Collection<ConteoManualCaja> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja);
	
	ConteoManualCaja buscar(Long id);

	Collection<ConteoManualCaja> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja,Integer tipo);
}

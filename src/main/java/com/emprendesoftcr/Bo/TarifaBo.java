package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Tarifa;
import com.emprendesoftcr.web.command.TarifaCommand;

public interface TarifaBo {

	void agregar(Tarifa tarifa);

	void modificar(Tarifa tarifa);

	void eliminar(Tarifa tarifa);

	Tarifa buscar(Long id);

	Collection<TarifaCommand> findByTipoImpuesto(String tipoImpuesto);

}
package com.emprendesoftcr.Bo;

import java.util.ArrayList;
import java.util.Collection;

import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.DenominacionCommand;

public interface ConteoManualCajaBo {

	void agregar(ConteoManualCaja conteoManualCaja);

	void modificar(ConteoManualCaja conteoManualCaja);

	void eliminar(ConteoManualCaja conteoManualCaja);

	Collection<ConteoManualCaja> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja);

	void agregarConteo(ArrayList<DenominacionCommand> listaCoteo, UsuarioCaja usuarioCaja,Usuario usuarioCierra);
	
	ConteoManualCaja buscar(Long id);

}

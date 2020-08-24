package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.UsuarioCaja;

public interface SalidaEntradaDineroBo {

	void agregar(SalidaEntradaDinero salidaEntradaDinero);
	void eliminar(SalidaEntradaDinero salidaEntradaDinero);
	SalidaEntradaDinero findById(Long id) ;

	Collection<SalidaEntradaDinero> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja);
	
	Collection<SalidaEntradaDinero> buscarPorUsuarioCajaAndTipo(UsuarioCaja usuarioCaja, Integer tipo);

}

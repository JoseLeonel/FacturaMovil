package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.UsuarioCaja;
/**
 * Salidas  y entradas de dinero
 * @author jose
 *
 */
public interface SalidaEntradaDineroDao {
	
	void agregar(SalidaEntradaDinero salidaEntradaDinero);


	
	Collection<SalidaEntradaDinero> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja);
	
	Collection<SalidaEntradaDinero> buscarPorUsuarioCajaAndTipo(UsuarioCaja usuarioCaja,Integer tipo);

}

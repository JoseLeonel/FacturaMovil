package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Cocina;
import com.emprendesoftcr.modelo.UsuarioCaja;

public interface CocinaDao {

	void agregar(Cocina cocina);

	void modificar(Cocina cocina);

	void eliminar(Cocina cocina);

	Cocina buscar(Long id);
	Cocina buscarCocinaActiva(UsuarioCaja usuarioCaja);

	
	

}
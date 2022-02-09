package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RomadaBalanza;

public interface RomanaBalanzaDao {

	void agregar(RomadaBalanza romadaBalanza);

	void modificar(RomadaBalanza romadaBalanza);

	void eliminar(RomadaBalanza romadaBalanza);

	RomadaBalanza buscar(Long id);

	RomadaBalanza buscarPorEmpresa( Empresa empresa);
	
	
}
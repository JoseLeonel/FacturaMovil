package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;

public interface CajaDao {

	void agregar(Caja caja);

	void modificar(Caja caja);

	void eliminar(Caja caja);

	Caja buscar(Long id);

	Caja buscarCajaActiva(Empresa empresa);
	Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa);

	Caja findByTerminalAndEmpresa(String terminal, Empresa empresa);

}
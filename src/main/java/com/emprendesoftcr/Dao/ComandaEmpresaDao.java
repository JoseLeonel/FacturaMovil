package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.ComandaEmpresa;

public interface ComandaEmpresaDao {

	void agregar(ComandaEmpresa comandaMesa);

	void modificar(ComandaEmpresa comandaMesa);
	
	ComandaEmpresa buscar(Long id);

}
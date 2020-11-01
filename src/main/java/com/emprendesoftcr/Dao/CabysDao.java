package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Cabys;
import com.emprendesoftcr.modelo.Empresa;

public interface CabysDao {

	void agregar(Cabys cabys);

	void modificar(Cabys cabys);

	void eliminar(Cabys cabys);

	Cabys buscar(Long id);

	Cabys buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);
	
	Cabys findByCodigo(String codigo, Empresa empresa);
	
	Collection<Cabys> findByEmpresaAll(Integer idEmpresa);

	
}
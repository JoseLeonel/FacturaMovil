package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;

public interface CategoriaDao {

	void agregar(Categoria categoria);

	void modificar(Categoria categoria);

	void eliminar(Categoria categoria);

	Categoria buscar(Long id);

	Categoria buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

	
}
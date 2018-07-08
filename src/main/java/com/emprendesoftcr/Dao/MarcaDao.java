package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Marca;

public interface MarcaDao {

	void agregar(Marca marca);

	void modificar(Marca marca);

	void eliminar(Marca marca);

	Marca buscar(Integer id);

	Marca buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);
	
	
}
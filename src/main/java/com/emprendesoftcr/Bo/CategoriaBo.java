package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;

public interface CategoriaBo {

	void agregar(Categoria categoria);

	void modificar(Categoria categoria);

	void eliminar(Categoria categoria);

	Categoria buscar(Integer id);

	Categoria buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

}
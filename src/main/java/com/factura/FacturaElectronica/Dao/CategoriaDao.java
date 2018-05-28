package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Categoria;
import com.factura.FacturaElectronica.modelo.Empresa;

public interface CategoriaDao {

	void agregar(Categoria categoria);

	void modificar(Categoria categoria);

	void eliminar(Categoria categoria);

	Categoria buscar(Integer id);

	Categoria buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

	
}
package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Vendedor;

public interface VendedorDao {

	void agregar(Vendedor vendedor);

	void modificar(Vendedor vendedor);

	void eliminar(Vendedor vendedor);

	Vendedor buscar(Long id);

	Vendedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Vendedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa);

}
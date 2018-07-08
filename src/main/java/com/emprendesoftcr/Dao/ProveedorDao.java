package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;

public interface ProveedorDao {

	void agregar(Proveedor proveedor);

	void modificar(Proveedor proveedor);

	void eliminar(Proveedor proveedor);

	Proveedor buscar(Integer id);

	Proveedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Proveedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa);
}
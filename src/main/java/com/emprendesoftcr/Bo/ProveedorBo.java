package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;

public interface ProveedorBo {

	void agregar(Proveedor proveedor);

	void modificar(Proveedor proveedor);

	void eliminar(Proveedor proveedor);

	Proveedor buscar(Integer id);

	Proveedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Proveedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa);
}
package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.ProveedorSimplificado;

public interface ProveedorSimplificadoDao {

	void agregar(ProveedorSimplificado proveedorSimplificado);

	void modificar(ProveedorSimplificado proveedorSimplificado);

	void eliminar(ProveedorSimplificado proveedorSimplificado);

	ProveedorSimplificado buscar(Long id);

	ProveedorSimplificado buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	ProveedorSimplificado buscarPorCedulaYEmpresa(String cedula, Empresa empresa);

}

package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.ProveedorSimplificado;

public interface ProveedorSimplificadoBo {
	void agregar(ProveedorSimplificado proveedorSimplificado);

	void modificar(ProveedorSimplificado proveedorSimplificado);

	void eliminar(ProveedorSimplificado proveedorSimplificado);

	ProveedorSimplificado buscar(Long id);

	ProveedorSimplificado buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	ProveedorSimplificado buscarPorCedulaYEmpresa(String cedula, Empresa empresa);

}

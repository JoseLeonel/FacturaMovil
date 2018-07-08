package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.Vendedor;

public interface VendedorBo {

	void agregar(Vendedor vendedor);

	void modificar(Vendedor vendedor);

	void eliminar(Vendedor vendedor);

	Vendedor buscar(Integer id);

	Vendedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Vendedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa);
	Vendedor crearVendedorFrecuente(Empresa empresa,Usuario usuario); 
	
	
}
package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;

public interface ClienteBo {

	void agregar(Cliente cliente);

	void modificar(Cliente cliente);

	void eliminar(Cliente cliente);

	Cliente buscar(Long id);

	Cliente buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Cliente buscarPorCedulaYEmpresa(String cedula, Empresa empresa);
	
	Cliente crearClienteFrecuente(Empresa empresa,Usuario usuario); 
	Collection<Cliente> findByEmpresa(Integer idEmpresa);
}
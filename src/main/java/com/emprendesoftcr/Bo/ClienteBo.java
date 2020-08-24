package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.ClienteCommand;
import com.emprendesoftcr.web.command.ClienteMag;

public interface ClienteBo {

	void agregar(Cliente cliente);

	void modificar(Cliente cliente);

	void eliminar(Cliente cliente);

	Cliente buscar(Long id);

	Cliente buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa);

	Cliente buscarPorCedulaYEmpresa(String cedula, Empresa empresa);
	
	Cliente crearClienteFrecuente(Empresa empresa,Usuario usuario); 
	Collection<Cliente> findByEmpresa(Integer idEmpresa);
	
	Cliente buscarPorCedulaExtranjera(String cedula, Empresa empresa);
	ClienteMag clienteRegistradoMag(ClienteCommand clienteCommand);
	
}
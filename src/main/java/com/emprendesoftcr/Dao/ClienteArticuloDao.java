package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.ClienteArticulo;

public interface ClienteArticuloDao {

	void agregar(ClienteArticulo clienteArticulo);

	void modificar(ClienteArticulo clienteArticulo);

	void eliminar(ClienteArticulo clienteArticulo);

	ClienteArticulo buscar(Long id);

	ClienteArticulo findByClienteAndArticulo(Articulo articulo,Cliente cliente);
	
	Collection<ClienteArticulo> findAll(Cliente cliente);
}
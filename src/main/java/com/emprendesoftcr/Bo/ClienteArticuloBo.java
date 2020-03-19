package com.emprendesoftcr.Bo;

import java.util.Collection;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.ClienteArticulo;

public interface ClienteArticuloBo {
	void agregar(ClienteArticulo clienteArticulo);

	void modificar(ClienteArticulo clienteArticulo);

	void eliminar(ClienteArticulo clienteArticulo);

	ClienteArticulo buscar(Long id);
	
	ClienteArticulo findByClienteAndArticulo(Articulo articulo,Cliente cliente);
	Collection<ClienteArticulo> findAll(Cliente cliente);
}

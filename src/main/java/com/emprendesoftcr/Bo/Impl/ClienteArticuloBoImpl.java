package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ClienteArticuloBo;
import com.emprendesoftcr.Dao.ClienteArticuloDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.ClienteArticulo;

@EnableTransactionManagement
@Service("clienteArticuloBo")
public class ClienteArticuloBoImpl implements ClienteArticuloBo {

	@Autowired
	private ClienteArticuloDao clienteArticuloDao;

	@Transactional
	@Override
	public void agregar(ClienteArticulo clienteArticulo) {

		clienteArticuloDao.agregar(clienteArticulo);
	}

	@Transactional
	@Override
	public void modificar(ClienteArticulo clienteArticulo) {
		clienteArticuloDao.modificar(clienteArticulo);

	}

	@Transactional
	@Override
	public void eliminar(ClienteArticulo clienteArticulo) {
		clienteArticuloDao.eliminar(clienteArticulo);

	}

	@Override
	public ClienteArticulo buscar(Long id) {

		return clienteArticuloDao.buscar(id);
	}

	@Override
	public ClienteArticulo findByClienteAndArticulo(Articulo articulo, Cliente cliente) {
		
		return clienteArticuloDao.findByClienteAndArticulo(articulo, cliente);
	}

	@Override
	public Collection<ClienteArticulo> findAll(Cliente cliente) {
		
		return clienteArticuloDao.findAll(cliente);
	}

}

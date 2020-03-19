package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ClienteArticuloDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.ClienteArticulo;

@Repository("clienteArticuloDao")
public class ClienteArticuloDaoImpl implements ClienteArticuloDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(ClienteArticulo clienteArticulo) {
		entityManager.persist(clienteArticulo);

	}

	@Override
	public void modificar(ClienteArticulo clienteArticulo) {
		entityManager.merge(clienteArticulo);

	}

	@Override
	public void eliminar(ClienteArticulo clienteArticulo) {
		entityManager.remove(clienteArticulo);

	}

	@Override
	public ClienteArticulo buscar(Long id) {
		Query query = entityManager.createQuery("select obj from ClienteArticulo obj where obj.id = :id");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<ClienteArticulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ClienteArticulo) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ClienteArticulo findByClienteAndArticulo(Articulo articulo, Cliente cliente) {
		Query query = entityManager.createQuery("select obj from ClienteArticulo obj where obj.articulo.id = :idArticulo and obj.cliente.id = :idCliente");
		query.setParameter("idArticulo", articulo.getId());
		query.setParameter("idCliente", cliente.getId());
		@SuppressWarnings("unchecked")
		List<ClienteArticulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ClienteArticulo) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Collection<ClienteArticulo> findAll(Cliente cliente) {
		Query query = entityManager.createQuery("select obj from ClienteArticulo obj where  obj.cliente.id = :idCliente ");
		query.setParameter("idCliente", cliente.getId());
		return query.getResultList();
	}

}

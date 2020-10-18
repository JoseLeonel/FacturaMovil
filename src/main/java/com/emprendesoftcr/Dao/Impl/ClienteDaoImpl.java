package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ClienteDao;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;

/**
 * Clientes por sucursal de empresa ClienteDaoImpl.
 * @author jose.
 * @since 18 mar. 2018
 */
@Repository("clienteDao")
public class ClienteDaoImpl implements ClienteDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(Cliente cliente) {
		entityManager.persist(cliente);
	}

	@Override
	public void modificar(Cliente cliente) {
		entityManager.merge(cliente);
	}

	@Override
	public void eliminar(Cliente cliente) {
		entityManager.remove(cliente);
	}

	/**
	 * Buscar por id
	 * @see com.factura.dao.ClienteDao#buscar(java.lang.Integer)
	 */

	@SuppressWarnings("unchecked")
	@Override
	public Cliente buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Cliente obj where obj.id = :id");
		query.setParameter("id", id);
		List<Cliente> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cliente) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @see com.factura.dao.ClienteDao#buscarPorNombreCompletoYEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Cliente buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Cliente obj where obj.nombreCompleto = :nombreCompleto and obj.empresa = :empresa");
		query.setParameter("nombreCompleto", nombreCompleto);
		query.setParameter("empresa", empresa);
		List<Cliente> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cliente) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @param cedula
	 * @param empresa
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Cliente buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Cliente obj where obj.cedula = :cedula and obj.empresa = :empresa");
		query.setParameter("cedula", cedula);
		query.setParameter("empresa", empresa);
		List<Cliente> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cliente) results.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public Cliente buscarPorCedulaExtranjera(String cedula, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Cliente obj where obj.identificacionExtranjero = :cedula and obj.empresa = :empresa");
		query.setParameter("cedula", cedula);
		query.setParameter("empresa", empresa);
		@SuppressWarnings("unchecked")
		List<Cliente> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cliente) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Cliente> findByEmpresa(Integer idEmpresa) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from Cliente obj");
		hql.append(" where obj.empresa.id = :idEmpresa ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

}
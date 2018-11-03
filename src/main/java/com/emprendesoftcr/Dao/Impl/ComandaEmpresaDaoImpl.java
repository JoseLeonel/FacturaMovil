package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ComandaEmpresaDao;
import com.emprendesoftcr.modelo.ComandaEmpresa;

@Repository("comandaEmpresaDao")
public class ComandaEmpresaDaoImpl implements ComandaEmpresaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(ComandaEmpresa comandaEmpresa) {
		entityManager.persist(comandaEmpresa);
	}

	public void modificar(ComandaEmpresa comandaEmpresa) {
		entityManager.merge(comandaEmpresa);
	}

	public void eliminar(ComandaEmpresa comandaEmpresa) {
		entityManager.remove(comandaEmpresa);
	}

	@SuppressWarnings("unchecked")
	public ComandaEmpresa buscar(Long id) {
		Query query = entityManager.createQuery("select obj from ComandaEmpresa obj where obj.id = :id");
		query.setParameter("id", id);
		List<ComandaEmpresa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ComandaEmpresa) results.get(0);
		} else {
			return null;
		}
	}

}
package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.MesaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Mesa;

/**
 * Dao para el control de mesas
 * MesaDaoImpl.
 * @author jose.
 * @since 4 sep. 2018
 */
@Repository("mesaDao")
public class MesaDaoImpl implements MesaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(Mesa mesa) {
		entityManager.persist(mesa);

	}

	@Override
	public void modificar(Mesa mesa) {
		entityManager.merge(mesa);

	}

	@Override
	public void eliminar(Mesa mesa) {
		entityManager.remove(mesa);

	}

	@Override
	public Mesa buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Mesa obj where obj.id = :id");
		query.setParameter("id", id);
		List<Mesa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Mesa) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Mesa findByDescripcionAndEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Mesa obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<Mesa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Mesa) results.get(0);
		} else {
			return null;
		}
	}

}

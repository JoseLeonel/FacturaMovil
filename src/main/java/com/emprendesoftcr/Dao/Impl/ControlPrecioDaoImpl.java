package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ControlPrecioDao;
import com.emprendesoftcr.modelo.ControlPrecioArticulo;

@Repository("controlPrecioDao")
public class ControlPrecioDaoImpl implements ControlPrecioDao {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	@Override
	public void agregar(ControlPrecioArticulo controlPrecioArticulo) {
		entityManager.persist(controlPrecioArticulo);

	}

	@Transactional
	@Override
	public void modificar(ControlPrecioArticulo controlPrecioArticulo) {
		entityManager.merge(controlPrecioArticulo);

	}

	@Transactional
	@Override
	public void eliminar(ControlPrecioArticulo controlPrecioArticulo) {
		entityManager.remove(controlPrecioArticulo);
	}

	@Override
	public ControlPrecioArticulo buscar(Long id) {
		Query query = entityManager.createQuery("select obj from ControlPrecioArticulo obj where obj.id = :id");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<ControlPrecioArticulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ControlPrecioArticulo) results.get(0);
		} else {
			return null;
		}
	}

}

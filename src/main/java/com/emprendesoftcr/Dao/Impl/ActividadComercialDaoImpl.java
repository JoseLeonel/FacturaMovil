package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ActividadComercialDao;
import com.emprendesoftcr.modelo.ActividadComercial;

/**
 * Actividad comercial que tiene un cliente
 * TODO ActividadComercialDaoImpl.
 * @author jose.
 * @since 2 jul. 2019
 */
@Repository("actividadComercialDao")
public class ActividadComercialDaoImpl implements ActividadComercialDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(ActividadComercial actividadComercial) {
		entityManager.persist(actividadComercial);
	}

	@Override
	public void modificar(ActividadComercial actividadComercial) {
		entityManager.merge(actividadComercial);
	}

	@Override
	public void eliminar(ActividadComercial actividadComercial) {
		entityManager.remove(actividadComercial);
	}

	
	@Override
	public ActividadComercial buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from ActividadComercial obj where obj.id = :id");
		query.setParameter("id", id);
		List<ActividadComercial> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ActividadComercial) results.get(0);
		} else {
			return null;
		}
	}
	
	
}
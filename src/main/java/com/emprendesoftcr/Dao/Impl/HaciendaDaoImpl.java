package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.HaciendaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;

@Repository("haciendaDao")
public class HaciendaDaoImpl implements HaciendaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(Hacienda hacienda) {
		entityManager.persist(hacienda);

	}

	@Override
	public void modificar(Hacienda hacienda) {
		entityManager.merge(hacienda);

	}

	@Override
	public Hacienda findById(Integer id) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.id = :id");
		query.setParameter("id", id);
		List<Hacienda> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Hacienda) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa, Integer estado) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado = :estado and obj.empresa = :empresa");
		query.setParameter("estado", estado);
		query.setParameter("empresa", empresa);
		return query.getResultList();
	}

}

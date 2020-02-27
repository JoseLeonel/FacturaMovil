package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CocinaDao;
import com.emprendesoftcr.modelo.Cocina;
import com.emprendesoftcr.modelo.UsuarioCaja;

@Repository("cocinaDao")
public class CocinaDaoImpl implements CocinaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(Cocina cocina) {
		entityManager.persist(cocina);

	}

	@Override
	public void modificar(Cocina cocina) {
		entityManager.merge(cocina);

	}

	@Override
	public void eliminar(Cocina cocina) {
		entityManager.remove(cocina);

	}

	@Override
	public Cocina buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Cocina obj where obj.id = :id");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Cocina> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cocina) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Cocina buscarCocinaActiva(UsuarioCaja usuarioCaja) {
		Query query = entityManager.createQuery("select obj from Cocina obj where obj.usuarioCaja.id = :id and estado = 1");
		query.setParameter("id", usuarioCaja.getId());
		@SuppressWarnings("unchecked")
		List<Cocina> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cocina) results.get(0);
		} else {
			return null;
		}
	}

}

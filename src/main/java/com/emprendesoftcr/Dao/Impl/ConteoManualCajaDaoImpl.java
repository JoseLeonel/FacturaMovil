package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ConteoManualCajaDao;
import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.UsuarioCaja;

@Repository("conteoManualCajaDao")
public class ConteoManualCajaDaoImpl implements ConteoManualCajaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(ConteoManualCaja conteoManualCaja) {
		entityManager.persist(conteoManualCaja);

	}

	@Override
	public void modificar(ConteoManualCaja conteoManualCaja) {
		entityManager.merge(conteoManualCaja);

	}

	@Override
	public void eliminar(ConteoManualCaja conteoManualCaja) {
		entityManager.remove(conteoManualCaja);

	}

	@Override
	public Collection<ConteoManualCaja> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja) {
		Query query = entityManager.createQuery("select obj from ConteoManualCaja obj where obj.usuarioCaja.id = :idUsuarioCaja ");
		query.setParameter("idUsuarioCaja", usuarioCaja.getId());
		return query.getResultList();
	}

	@Override
	public ConteoManualCaja buscar(Long id) {
		Query query = entityManager.createQuery("select obj from ConteoManualCaja obj where obj.id = :id");
		query.setParameter("id", id);
		List<ConteoManualCaja> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ConteoManualCaja) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Collection<ConteoManualCaja> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja, Integer tipo) {
		Query query = entityManager.createQuery("select obj from ConteoManualCaja obj where obj.usuarioCaja.id = :idUsuarioCaja and obj.tipo = :tipo  order by obj.moneda desc ");
		query.setParameter("idUsuarioCaja", usuarioCaja.getId());
		query.setParameter("tipo", tipo);
		return query.getResultList();	}

}

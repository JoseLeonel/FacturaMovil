package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.FEMensajeReceptorAutomaticoDao;
import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;

@Repository("fEMensajeReceptorAutomaticoDao")
public class FEMensajeReceptorAutomaticoDaoImpl implements FEMensajeReceptorAutomaticoDao {
	@PersistenceContext
	EntityManager		entityManager;
	
	
	@Override
	public void agregar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico) {
		entityManager.persist(fEMensajeReceptorAutomatico);

	}

	@Override
	public void modificar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico) {
		entityManager.merge(fEMensajeReceptorAutomatico);

	}

	@Override
	public void eliminar(FEMensajeReceptorAutomatico fEMensajeReceptorAutomatico) {
		entityManager.remove(fEMensajeReceptorAutomatico);

	}

	@SuppressWarnings("unchecked")
	@Override
	public FEMensajeReceptorAutomatico buscar(Long id) {
		Query query = entityManager.createQuery("select obj from FEMensajeReceptorAutomatico obj where obj.id = :id");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<FEMensajeReceptorAutomatico> results = query.getResultList();
		if (!results.isEmpty()) {
			return (FEMensajeReceptorAutomatico) results.get(0);
		} else {
			return null;
		}
	}

}

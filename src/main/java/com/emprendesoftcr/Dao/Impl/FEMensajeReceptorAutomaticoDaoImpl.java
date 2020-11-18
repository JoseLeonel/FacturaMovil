package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.FEMensajeReceptorAutomaticoDao;
import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;
import com.emprendesoftcr.modelo.Factura;

@Repository("fEMensajeReceptorAutomaticoDao")
public class FEMensajeReceptorAutomaticoDaoImpl implements FEMensajeReceptorAutomaticoDao {
	@PersistenceContext
	EntityManager		entityManager;
	private Logger	log	= LoggerFactory.getLogger(this.getClass());
	
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

	@Override
	public FEMensajeReceptorAutomatico buscar(Long id) {
		Query query = entityManager.createQuery("select obj from FEMensajeReceptorAutomatico obj where obj.id = :id");
		query.setParameter("id", id);
		List<FEMensajeReceptorAutomatico> results = query.getResultList();
		if (!results.isEmpty()) {
			return (FEMensajeReceptorAutomatico) results.get(0);
		} else {
			return null;
		}
	}

}

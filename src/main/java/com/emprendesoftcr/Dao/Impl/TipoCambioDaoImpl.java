package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.TipoCambioDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.TipoCambio;

/**
 * TipoCambioDaoImpl.
 * @author jose.
 * @since 26 mar. 2018
 */
@Repository("tipoCambioDao")
public class TipoCambioDaoImpl implements TipoCambioDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(TipoCambio tipoCambio) {
		entityManager.persist(tipoCambio);
	}

	@Override
	public void modificar(TipoCambio tipoCambio) {
		entityManager.merge(tipoCambio);
	}

	@Override
	public void eliminar(TipoCambio tipoCambio) {
		entityManager.remove(tipoCambio);
	}

	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TipoCambio buscar(Long id) {
		Query query = entityManager.createQuery("select obj from TipoCambio obj where obj.id = :id");
		query.setParameter("id", id);
		List<TipoCambio> results = query.getResultList();
		if (!results.isEmpty()) {
			return (TipoCambio) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public TipoCambio findByEstadoAndEmpresa(String estado, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from TipoCambio obj where obj.estado = :estado and obj.empresa = :empresa");
		query.setParameter("estado", estado);
		query.setParameter("empresa", empresa);
		List<TipoCambio> results = query.getResultList();
		if (!results.isEmpty()) {
			return (TipoCambio) results.get(0);
		} else {
			return null;
		}
	}

}
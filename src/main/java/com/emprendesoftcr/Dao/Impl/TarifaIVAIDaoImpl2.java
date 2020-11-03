package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.TarifaIVAIDao;
import com.emprendesoftcr.modelo.TarifaIVAI;

/**
 * Mantenimiento de Marcas de los articulos del almacen MarcaDaoImpl.
 * @author lhernandez.
 * @since 7 abr. 2018
 */
@Repository("tarifaIVAIDao")
public class TarifaIVAIDaoImpl2 implements TarifaIVAIDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(TarifaIVAI tarifaIVAI) {
		entityManager.persist(tarifaIVAI);
	}

	@Override
	public void modificar(TarifaIVAI tarifaIVAI) {
		entityManager.merge(tarifaIVAI);
	}

	@Override
	public void eliminar(TarifaIVAI tarifaIVAI) {
		entityManager.remove(tarifaIVAI);
	}

	/**
	 * Buscar el objeto tarifa por id
	 * @see com.factura.dao.TarifaDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TarifaIVAI buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from TarifaIVAI obj where obj.id = :id");
		query.setParameter("id", id);
		List<TarifaIVAI> results = query.getResultList();
		if (!results.isEmpty()) {
			return (TarifaIVAI) results.get(0);
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public TarifaIVAI findByCodigoTarifa(String codigoTarifa) {
		Query query = entityManager.createQuery("select obj from TarifaIVAI obj where obj.codigoTarifa = :codigoTarifa");
		query.setParameter("codigoTarifa", codigoTarifa);
		List<TarifaIVAI> results = query.getResultList();
		if (!results.isEmpty()) {
			return (TarifaIVAI) results.get(0);
		} else {
			return null;
		}
		
	}
	
}
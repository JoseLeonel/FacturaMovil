package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.TipoCambioDao;
import com.factura.FacturaElectronica.modelo.TipoCambio;

/**
 * TipoCambioDaoImpl.
 * @author jose.
 * @since 26 mar. 2018
 */
@Repository("tipoCambioDao")
public class TipoCambioDaoImpl implements TipoCambioDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(TipoCambio tipoCambio) {
		entityManager.persist(tipoCambio);
	}

	public void modificar(TipoCambio tipoCambio) {
		entityManager.merge(tipoCambio);
	}

	public void eliminar(TipoCambio tipoCambio) {
		entityManager.remove(tipoCambio);
	}

	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TipoCambio buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from TipoCambio obj where obj.id = :id");
		query.setParameter("id", id);
		List<TipoCambio> results = query.getResultList();
		if (!results.isEmpty()) {
			return (TipoCambio) results.get(0);
		} else {
			return null;
		}
	}

}
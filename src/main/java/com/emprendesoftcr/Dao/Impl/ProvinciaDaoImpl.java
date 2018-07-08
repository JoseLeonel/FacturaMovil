package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ProvinciaDao;
import com.emprendesoftcr.modelo.Provincia;

@Repository("provinciaDao")
public class ProvinciaDaoImpl implements ProvinciaDao {

	@PersistenceContext
	EntityManager entityManager;

	
	/**
	 * Buscar una Caja
	 * @see com.emprendesoftcr.Dao.CajaDao#buscar(java.lang.Long)
	 */
	@Override
	public Provincia findByCodigo(Integer codigo) {
		Query query = entityManager.createQuery("select obj from Provincia obj where obj.codigo = :codigo");
		query.setParameter("codigo", codigo);
		List<Provincia> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Provincia) results.get(0);
		} else {
			return null;
		}
	}

	
}
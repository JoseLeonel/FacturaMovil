package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.TarifaDao;
import com.emprendesoftcr.modelo.Tarifa;

/**
 * Mantenimiento de Marcas de los articulos del almacen MarcaDaoImpl.
 * @author lhernandez.
 * @since 7 abr. 2018
 */
@Repository("tarifaDao")
public class TarifaDaoImpl implements TarifaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(Tarifa tarifa) {
		entityManager.persist(tarifa);
	}

	@Override
	public void modificar(Tarifa tarifa) {
		entityManager.merge(tarifa);
	}

	@Override
	public void eliminar(Tarifa tarifa) {
		entityManager.remove(tarifa);
	}

	/**
	 * Buscar el objeto tarifa por id
	 * @see com.factura.dao.TarifaDao#buscar(java.lang.Integer)
	 */
	@Override
	public Tarifa buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Tarifa obj where obj.id = :id");
		query.setParameter("id", id);
		List<Tarifa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Tarifa) results.get(0);
		} else {
			return null;
		}
	}

	
}
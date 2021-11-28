package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ControlPrecioObseDao;
import com.emprendesoftcr.modelo.ControlPrecioObse;
@Repository("controlPrecioObseDao")
public class ControlPrecioObseDaoImpl implements ControlPrecioObseDao{
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void agregar(ControlPrecioObse controlPrecioObse) {
		entityManager.persist(controlPrecioObse);

		
	}

	@Override
	public void modificar(ControlPrecioObse controlPrecioObse) {
		entityManager.merge(controlPrecioObse);
		
	}

	@Override
	public void eliminar(ControlPrecioObse controlPrecioObse) {
		entityManager.remove(controlPrecioObse);
		
	}

	@Override
	public ControlPrecioObse buscar(Long id) {
		Query query = entityManager.createQuery("select obj from ControlPrecioObse obj where obj.id = :id");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<ControlPrecioObse> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ControlPrecioObse) results.get(0);
		} else {
			return null;
		}
	}

}

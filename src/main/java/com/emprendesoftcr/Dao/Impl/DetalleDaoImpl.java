package com.emprendesoftcr.Dao.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.modelo.Detalle;

@Repository("detalleDao")
public class DetalleDaoImpl implements DetalleDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Detalle detalle) {
		entityManager.persist(detalle);
	}
	
	public void modificar(Detalle detalle) {
		entityManager.merge(detalle);
	}

	public void eliminar(Detalle detalle) {
		entityManager.remove(detalle);
	}



}
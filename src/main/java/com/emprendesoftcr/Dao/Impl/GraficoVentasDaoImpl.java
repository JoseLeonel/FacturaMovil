package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.GraficoVentasDao;
import com.emprendesoftcr.modelo.GraficoVenta;

@Repository("graficoVentasDao")
public class GraficoVentasDaoImpl implements GraficoVentasDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Collection<GraficoVenta> findByAll() {
		Query query = entityManager.createQuery("select obj from GraficoVenta obj ");
		return query.getResultList();
	}

	
}
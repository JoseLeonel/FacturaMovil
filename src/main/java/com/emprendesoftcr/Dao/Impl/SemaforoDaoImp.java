package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.SemaforoDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Semaforo;

@Repository("semaforoDao")
public class SemaforoDaoImp implements SemaforoDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Semaforo findByEmpresa(Empresa empresa,Integer estado) {

		Query query = entityManager.createQuery("select obj from Semaforo obj where obj.empresa = :empresa and obj.estado = :estado");
		query.setParameter("empresa", empresa);
		query.setParameter("estado", estado);
		List<Semaforo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Semaforo) results.get(0);
		} else {
			return null;
		}
	}

}

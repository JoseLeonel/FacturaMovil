package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CorreoAutomaticoDao;
import com.emprendesoftcr.modelo.CorreoAutomatico;

@Repository("correoAutomaticoDao")
public class CorreoAutomaticoDaoImpl implements CorreoAutomaticoDao {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Collection<CorreoAutomatico> allEmails() {
		Query query = entityManager.createQuery("select obj from CorreoAutomatico obj");
		return query.getResultList();

	}

}

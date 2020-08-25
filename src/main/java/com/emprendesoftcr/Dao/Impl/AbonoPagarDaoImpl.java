package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.AbonoPagarDao;
import com.emprendesoftcr.modelo.AbonoPagar;

/**
 * AbonoPagarDaoImpl.
 * @author jose.
 * @since 26 mar. 2018
 */
@Repository("abonoPagarDao")
public class AbonoPagarDaoImpl implements AbonoPagarDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(AbonoPagar abonoPagar) {
		entityManager.persist(abonoPagar);
	}
	
	public void modificar(AbonoPagar abonoPagar) {
		entityManager.merge(abonoPagar);
	}

	public void eliminar(AbonoPagar abonoPagar) {
		entityManager.remove(abonoPagar);
	}

	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AbonoPagar buscar(Long id) {
		Query query = entityManager.createQuery("select obj from AbonoPagar obj where obj.id = :id");
		query.setParameter("id", id);
		List<AbonoPagar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (AbonoPagar) results.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<AbonoPagar> buscarPorCuentaPagar() {
		Query query = entityManager.createQuery("select obj from AbonoPagar obj order by obj.id");
		return query.getResultList();
	}

}
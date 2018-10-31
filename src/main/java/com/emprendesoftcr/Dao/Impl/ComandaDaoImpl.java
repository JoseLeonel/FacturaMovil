package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ComandaDao;
import com.emprendesoftcr.modelo.ComandaMesa;

@Repository("comandaDao")
public class ComandaDaoImpl implements ComandaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(ComandaMesa comandaMesa) {
		entityManager.persist(comandaMesa);
	}
	
	public void modificar(ComandaMesa comandaMesa) {
		entityManager.merge(comandaMesa);
	}

	public void eliminar(ComandaMesa comandaMesa) {
		entityManager.remove(comandaMesa);
	}

	@SuppressWarnings("unchecked")
	public ComandaMesa buscar(Long id) {
		Query query = entityManager.createQuery("select obj from ComandaMesa obj where obj.id = :id");
		query.setParameter("id", id);
		List<ComandaMesa> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ComandaMesa) results.get(0);
		} else {
			return null;
		}
	}	

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ComandaMesa> buscarPorFacturaMesa(Long idFactura, Long idMesa) {
		Query query = entityManager.createQuery("select obj from ComandaMesa obj where obj.mesa.id = :idMesa and obj.idFactura = :idFactura order by obj.id");
		query.setParameter("idMesa", idMesa);
		query.setParameter("idFactura", idFactura);	
		return query.getResultList();
	}
	
}
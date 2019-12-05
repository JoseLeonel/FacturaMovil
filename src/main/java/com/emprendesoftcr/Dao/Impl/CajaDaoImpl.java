package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CajaDao;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;

@Repository("cajaDao")
public class CajaDaoImpl implements CajaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Caja caja) {
		entityManager.persist(caja);
	}

	public void modificar(Caja caja) {
		entityManager.merge(caja);
	}

	public void eliminar(Caja caja) {
		entityManager.remove(caja);
	}

	/**
	 * Buscar una Caja
	 * @see com.emprendesoftcr.Dao.CajaDao#buscar(java.lang.Long)
	 */
	@Override
	public Caja buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Caja obj where obj.id = :id");
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Caja> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Caja) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por descripcion
	 * @param descripcion
	 * @param empresa
	 * @return
	 */
	@Override
	public Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Caja obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<Caja> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Caja) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por terminal
	 * @see com.emprendesoftcr.Dao.CajaDao#findByTerminalAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Caja findByTerminalAndEmpresa(String terminal, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Caja obj where obj.terminal = :terminal and obj.empresa = :empresa");
		query.setParameter("terminal", terminal);
		query.setParameter("empresa", empresa);
		List<Caja> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Caja) results.get(0);
		} else {
			return null;
		}
	}

}
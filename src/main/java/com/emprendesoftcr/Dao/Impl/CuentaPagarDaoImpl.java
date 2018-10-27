package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CuentaPagarDao;
import com.emprendesoftcr.modelo.CuentaPagar;

/**
 * Clientes por sucursal de empresa ClienteDaoImpl.
 * @author jose.
 * @since 18 mar. 2018
 */
@Repository("cuentaPagarDao")
public class CuentaPagarDaoImpl implements CuentaPagarDao {

	@PersistenceContext
	EntityManager		entityManager;


	public void agregar(CuentaPagar cuentaCobrar) {
		entityManager.persist(cuentaCobrar);
	}

	public void modificar(CuentaPagar cuentaCobrar) {
		entityManager.merge(cuentaCobrar);
	}

	public void eliminar(CuentaPagar cuentaCobrar) {
		entityManager.remove(cuentaCobrar);
	}

	/**
	 * Busca por id y retorna la cuenta de cobrar
	 * @see com.factura.dao.CuentaCobrarDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public CuentaPagar buscar(Long id) {
		Query query = entityManager.createQuery("select obj from CuentaPagar obj where obj.id = :id");
		query.setParameter("id", id);
		List<CuentaPagar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaPagar) results.get(0);
		} else {
			return null;
		}
	}
	
	
}
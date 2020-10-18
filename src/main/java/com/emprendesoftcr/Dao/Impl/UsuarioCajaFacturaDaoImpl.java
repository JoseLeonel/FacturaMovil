package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.UsuarioCajaFacturaDao;
import com.emprendesoftcr.modelo.UsuarioCajaFactura;

@Repository("usuarioCajaFacturaDao")
public class UsuarioCajaFacturaDaoImpl implements UsuarioCajaFacturaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(UsuarioCajaFactura usuarioCajaFactura) {
		entityManager.persist(usuarioCajaFactura);
	}

	@Override
	public void modificar(UsuarioCajaFactura usuarioCajaFactura) {
		entityManager.merge(usuarioCajaFactura);
	}

	@Override
	public void eliminar(UsuarioCajaFactura usuarioCajaFactura) {
		entityManager.remove(usuarioCajaFactura);
	}

	/**
	 * Buscar una UsuarioCajaFactura
	 * @see com.emprendesoftcr.Dao.UsuarioCajaFacturaDao#buscar(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UsuarioCajaFactura findById(Long id) {
		Query query = entityManager.createQuery("select obj from UsuarioCajaFactura obj where obj.id = :id");
		query.setParameter("id", id);
		List<UsuarioCajaFactura> results = query.getResultList();
		if (!results.isEmpty()) {
			return (UsuarioCajaFactura) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar una UsuarioCajaFactura
	 * @see com.emprendesoftcr.Dao.UsuarioCajaFacturaDao#buscar(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UsuarioCajaFactura findByFacturaId(Long idFactura) {
		Query query = entityManager.createQuery("select obj from UsuarioCajaFactura obj where obj.factura.id = :id");
		query.setParameter("id", idFactura);
		List<UsuarioCajaFactura> results = query.getResultList();
		if (!results.isEmpty()) {
			return (UsuarioCajaFactura) results.get(0);
		} else {
			return null;
		}
	}

}
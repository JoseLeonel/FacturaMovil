package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.UsuarioCajaFacturaDao;
import com.factura.FacturaElectronica.modelo.UsuarioCajaFactura;

@Repository("usuarioCajaFacturaDao")
public class UsuarioCajaFacturaDaoImpl implements UsuarioCajaFacturaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(UsuarioCajaFactura usuarioCajaFactura) {
		entityManager.persist(usuarioCajaFactura);
	}

	public void modificar(UsuarioCajaFactura usuarioCajaFactura) {
		entityManager.merge(usuarioCajaFactura);
	}

	public void eliminar(UsuarioCajaFactura usuarioCajaFactura) {
		entityManager.remove(usuarioCajaFactura);
	}

	/**
	 * Buscar una UsuarioCajaFactura
	 * @see com.factura.FacturaElectronica.Dao.UsuarioCajaFacturaDao#buscar(java.lang.Long)
	 */
	@Override
	public UsuarioCajaFactura buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from UsuarioCajaFactura obj where obj.id = :id");
		query.setParameter("id", id);
		List<UsuarioCajaFactura> results = query.getResultList();
		if (!results.isEmpty()) {
			return (UsuarioCajaFactura) results.get(0);
		} else {
			return null;
		}
	}

}
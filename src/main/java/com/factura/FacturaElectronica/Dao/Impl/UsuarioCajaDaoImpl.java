package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.UsuarioCajaDao;
import com.factura.FacturaElectronica.modelo.UsuarioCaja;

@Repository("usuarioCajaDao")
public class UsuarioCajaDaoImpl implements UsuarioCajaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(UsuarioCaja marca) {
		entityManager.persist(marca);
	}

	public void modificar(UsuarioCaja marca) {
		entityManager.merge(marca);
	}

	public void eliminar(UsuarioCaja marca) {
		entityManager.remove(marca);
	}

	/**
	 * Buscar una UsuarioCaja
	 * @see com.factura.FacturaElectronica.Dao.UsuarioCajaDao#buscar(java.lang.Long)
	 */
	@Override
	public UsuarioCaja buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from UsuarioCaja obj where obj.id = :id");
		query.setParameter("id", id);
		List<UsuarioCaja> results = query.getResultList();
		if (!results.isEmpty()) {
			return (UsuarioCaja) results.get(0);
		} else {
			return null;
		}
	}

}
package com.emprendesoftcr.Dao.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.DetalleCompraDao;
import com.emprendesoftcr.modelo.DetalleCompra;

/**
 * AbonoDaoImpl.
 * @author jose.
 * @since 26 mar. 2018
 */
@Repository("detalleCompraDao")
public class DetalleCompraDaoImpl implements DetalleCompraDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(DetalleCompra detalleCompra) {
		entityManager.persist(detalleCompra);
	}
	
	public void modificar(DetalleCompra detalleCompra) {
		entityManager.merge(detalleCompra);
	}

	public void eliminar(DetalleCompra detalleCompra) {
		entityManager.remove(detalleCompra);
	}



}
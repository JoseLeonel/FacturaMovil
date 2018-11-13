package com.emprendesoftcr.Dao.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.DetalleCompraDao;
import com.emprendesoftcr.modelo.DetalleCompra;

/**
 * Detalles compras de proveedores
 * DetalleCompraDaoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@Repository("detalleCompraDao")
public class DetalleCompraDaoImpl implements DetalleCompraDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(DetalleCompra detalleCompra) {
		entityManager.persist(detalleCompra);
	}
	
	@Override
	public void modificar(DetalleCompra detalleCompra) {
		entityManager.merge(detalleCompra);
	}

	@Override
	public void eliminar(DetalleCompra detalleCompra) {
		entityManager.remove(detalleCompra);
	}



}
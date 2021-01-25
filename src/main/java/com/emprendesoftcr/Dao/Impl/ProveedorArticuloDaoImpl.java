package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ProveedorArticuloDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

@Repository("proveedorArticuloDao")
public class ProveedorArticuloDaoImpl implements ProveedorArticuloDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(ProveedorArticulo proveedorArticulo) {
		entityManager.persist(proveedorArticulo);

	}

	@Override
	public void modificar(ProveedorArticulo proveedorArticulo) {
		entityManager.merge(proveedorArticulo);

	}

	@Override
	public void eliminar(ProveedorArticulo proveedorArticulo) {
		entityManager.remove(proveedorArticulo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ProveedorArticulo findById(Long id) {
		Query query = entityManager.createQuery("select obj from ProveedorArticulo obj where obj.id = :id");
		query.setParameter("id", id);
		List<ProveedorArticulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ProveedorArticulo) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProveedorArticulo findByCodigo(String codigo, Proveedor proveedor) {
		Query query = entityManager.createQuery("select obj from ProveedorArticulo obj where obj.codigoProveedor = :codigo and obj.proveedor = :proveedor");
		query.setParameter("proveedor", proveedor);
		query.setParameter("codigo",codigo);
		List<ProveedorArticulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ProveedorArticulo) results.get(0);
		} else {
			return null;
		}

	}

}

package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ProveedorDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;

/**
 * Proveedores que se le aplican compras para alimentar el almacen
 * ProveedorDaoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Repository("proveedorDao")
public class ProveedorDaoImpl implements ProveedorDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Proveedor proveedor) {
		entityManager.persist(proveedor);
	}

	public void modificar(Proveedor proveedor) {
		entityManager.merge(proveedor);
	}

	public void eliminar(Proveedor proveedor) {
		entityManager.remove(proveedor);
	}

	@SuppressWarnings("unchecked")
	public Proveedor buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Proveedor obj where obj.id = :id");
		query.setParameter("id", id);
		List<Proveedor> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Proveedor) results.get(0);
		} else {
			return null;
		}
	}
/**
 * Buscar por nombre completo y empresa
 * @see com.factura.dao.ProveedorDao#buscarPorNombreCompletoYEmpresa(java.lang.String, com.factura.domain.Empresa)
 */
	@SuppressWarnings("unchecked")
	public Proveedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Proveedor obj where obj.nombreCompleto = :nombreCompleto and obj.empresa = :empresa");
		query.setParameter("nombreCompleto", nombreCompleto);
		query.setParameter("empresa", empresa);
		List<Proveedor> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Proveedor) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por cedula  y empresa
	 * @param cedula
	 * @param empresa
	 * @return
	 */
	public Proveedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Proveedor obj where obj.cedula = :cedula and obj.empresa = :empresa");
		query.setParameter("cedula", cedula);
		query.setParameter("empresa", empresa);
		List<Proveedor> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Proveedor) results.get(0);
		} else {
			return null;
		}
	}

}
package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.VendedorDao;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Vendedor;

/**
 * vendedores de una sucursal de una empresa
 * @author jose.
 * @since 18 mar. 2018
 */
@Repository("vendedorDao")
public class VendedorDaoImpl implements VendedorDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Vendedor vendedor) {
		entityManager.persist(vendedor);
	}

	public void modificar(Vendedor vendedor) {
		entityManager.merge(vendedor);
	}

	public void eliminar(Vendedor vendedor) {
		entityManager.remove(vendedor);
	}

	/**
	 * buscar por id
	 * @see com.factura.dao.VendedorDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Vendedor buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from Vendedor obj where obj.id = :id");
		query.setParameter("id", id);
		List<Vendedor> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Vendedor) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * buscar por nombre completo
	 * @see com.factura.dao.VendedorDao#buscarPorNombreCompletoYSucursal(java.lang.String, com.factura.domain.Sucursal)
	 */
	@SuppressWarnings("unchecked")
	public Vendedor buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Vendedor obj where obj.nombreCompleto = :nombreCompleto and obj.empresa = :empresa");
		query.setParameter("nombreCompleto", nombreCompleto);
		query.setParameter("empresa", empresa);
		List<Vendedor> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Vendedor) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar cedula y sucursal
	 * @param cedula
	 * @param sucursal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vendedor buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Vendedor obj where obj.cedula = :cedula and obj.empresa = :empresa");
		query.setParameter("cedula", cedula);
		query.setParameter("empresa", empresa);
		List<Vendedor> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Vendedor) results.get(0);
		} else {
			return null;
		}
	}

}
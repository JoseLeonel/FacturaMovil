package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ProveedorSimplificadoDao;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.ProveedorSimplificado;
@Repository("proveedorSimplificadoDao")
public class ProveedorSimplificadoDaoImpl implements ProveedorSimplificadoDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void agregar(ProveedorSimplificado proveedorSimplificado) {
		entityManager.persist(proveedorSimplificado);
	}

	@Override
	public void modificar(ProveedorSimplificado proveedorSimplificado) {
		entityManager.merge(proveedorSimplificado);
	}

	@Override
	public void eliminar(ProveedorSimplificado proveedorSimplificado) {
		entityManager.remove(proveedorSimplificado);
	}


	@Override
	public ProveedorSimplificado buscar(Long id) {
		Query query = entityManager.createQuery("select obj from ProveedorSimplificado obj where obj.id = :id");
		query.setParameter("id", id);
		List<ProveedorSimplificado> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ProveedorSimplificado) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ProveedorSimplificado buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from ProveedorSimplificado obj where obj.nombreCompleto = :nombreCompleto and obj.empresa = :empresa");
		query.setParameter("nombreCompleto", nombreCompleto);
		query.setParameter("empresa", empresa);
		List<ProveedorSimplificado> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ProveedorSimplificado) results.get(0);
		} else {
			return null;}
	}

	@Override
	public ProveedorSimplificado buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from ProveedorSimplificado obj where obj.cedula = :cedula and obj.empresa = :empresa");
		query.setParameter("cedula", cedula);
		query.setParameter("empresa", empresa);
		List<ProveedorSimplificado> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ProveedorSimplificado) results.get(0);
		} else {
			return null;
		}
	}

}

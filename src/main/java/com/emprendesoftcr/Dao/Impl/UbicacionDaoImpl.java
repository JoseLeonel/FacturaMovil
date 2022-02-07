package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.UbicacionDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Ubicacion;


@Repository("ubicacionDao")
public class UbicacionDaoImpl implements UbicacionDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(Ubicacion ubicacion) {
		entityManager.persist(ubicacion);
	}

	@Override
	public void modificar(Ubicacion ubicacion) {
		entityManager.merge(ubicacion);
	}

	@Override
	public void eliminar(Ubicacion ubicacion) {
		entityManager.remove(ubicacion);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Ubicacion buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Ubicacion obj where obj.id = :id");
		query.setParameter("id", id);
		List<Ubicacion> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Ubicacion) results.get(0);
		} else {
			return null;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Ubicacion buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Ubicacion obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<Ubicacion> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Ubicacion) results.get(0);
		} else {
			return null;
		}
	}

}
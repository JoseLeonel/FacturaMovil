package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.EmpresaActividadComercialDao;
import com.emprendesoftcr.modelo.EmpresaActividadComercial;

@Repository("empresaActividadComercialDao")
public class EmpresaActividadComercialDaoImpl implements EmpresaActividadComercialDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void agregar(EmpresaActividadComercial empresaActividadComercial) {
		entityManager.persist(empresaActividadComercial);

	}

	@Override
	public void modificar(EmpresaActividadComercial empresaActividadComercial) {
		entityManager.merge(empresaActividadComercial);

	}

	@Override
	public void eliminar(EmpresaActividadComercial empresaActividadComercial) {
		entityManager.remove(empresaActividadComercial);

	}

	@Override
	public EmpresaActividadComercial buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from EmpresaActividadComercial obj where obj.id = :id");
		query.setParameter("id", id);
		List<EmpresaActividadComercial> results = query.getResultList();
		if (!results.isEmpty()) {
			return (EmpresaActividadComercial) results.get(0);
		} else {
			return null;
		}
	}

}

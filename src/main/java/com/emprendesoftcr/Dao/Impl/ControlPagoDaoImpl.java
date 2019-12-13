package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ControlPagoDao;
import com.emprendesoftcr.modelo.ControlPago;
import com.emprendesoftcr.modelo.Empresa;

@Repository("controlPagoDao")
public class ControlPagoDaoImpl implements ControlPagoDao {

	@PersistenceContext
	EntityManager entityManager;

	
	@Override
	public void agregar(ControlPago controlPago) {
		entityManager.persist(controlPago);
	}

	@Override
	public void modificar(ControlPago controlPago) {
		entityManager.merge(controlPago);

	}

	@Override
	public void eliminar(ControlPago controlPago) {
		entityManager.remove(controlPago);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ControlPago buscar(Long id) {
		Query query = entityManager.createQuery("select obj from ControlPago obj where obj.id = :id");
		query.setParameter("id", id);
		List<ControlPago> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ControlPago) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ControlPago findByEstadoAndEmpresa(Integer estado, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from ControlPago obj where obj.estado = :estado and obj.empresa = :empresa");
		query.setParameter("estado", estado);
		query.setParameter("empresa", empresa);
		List<ControlPago> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ControlPago) results.get(0);
		} else {
			return null;
		}
	}

}

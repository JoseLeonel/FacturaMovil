package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.MotivoSalidaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.MotivoSalida;

/**
 * Motivo de salidas  que se registra en el kardex
 * MotivoSalidaDaoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Repository("motivoSalidaDao")
public class MotivoSalidaDaoImpl implements MotivoSalidaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(MotivoSalida motivoSalida) {
		entityManager.persist(motivoSalida);
	}

	public void modificar(MotivoSalida motivoSalida) {
		entityManager.merge(motivoSalida);
	}

	public void eliminar(MotivoSalida motivoSalida) {
		entityManager.remove(motivoSalida);
	}

	/**
	 * Buscar el objeto motivoSalida por id
	 * @see com.factura.dao.MotivoSalidaDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public MotivoSalida buscar(Long id) {
		Query query = entityManager.createQuery("select obj from MotivoSalida obj where obj.id = :id");
		query.setParameter("id", id);
		List<MotivoSalida> results = query.getResultList();
		if (!results.isEmpty()) {
			return (MotivoSalida) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @see com.factura.dao.MotivoSalidaDao#buscarPorDescripcionYEmpresa(java.lang.String, com.factura.domain.Sucursal)
	 */
	public MotivoSalida buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from MotivoSalida obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<MotivoSalida> results = query.getResultList();
		if (!results.isEmpty()) {
			return (MotivoSalida) results.get(0);
		} else {
			return null;
		}
	}

}
package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.MotivoEntradaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.MotivoEntrada;

/**
 * Para aplicarlo en el kardex MotivoEntradaDaoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Repository("motivoEntradaDao")
public class MotivoEntradaDaoImpl implements MotivoEntradaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(MotivoEntrada motivoEntrada) {
		entityManager.persist(motivoEntrada);
	}

	public void modificar(MotivoEntrada motivoEntrada) {
		entityManager.merge(motivoEntrada);
	}

	public void eliminar(MotivoEntrada motivoEntrada) {
		entityManager.remove(motivoEntrada);
	}

	/**
	 * Buscar el objeto motivoEntrada por id
	 * @see com.factura.dao.MotivoEntradaDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public MotivoEntrada buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from MotivoEntrada obj where obj.id = :id");
		query.setParameter("id", id);
		List<MotivoEntrada> results = query.getResultList();
		if (!results.isEmpty()) {
			return (MotivoEntrada) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por descripcion la motivoEntrada y Empresa
	 * @see com.factura.dao.MotivoEntradaDao#buscarByDescripcionAndEmpresa(java.lang.String, com.factura.domain.Sucursal)
	 */
	@SuppressWarnings("unchecked")
	public MotivoEntrada buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from MotivoEntrada obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<MotivoEntrada> results = query.getResultList();
		if (!results.isEmpty()) {
			return (MotivoEntrada) results.get(0);
		} else {
			return null;
		}
	}

}
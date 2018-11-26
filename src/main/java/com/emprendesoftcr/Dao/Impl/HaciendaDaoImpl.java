package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.HaciendaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Hacienda;

/**
 * Hacienda de los xml que se envian a tributacion.
 * @author jose.
 * @since 3 nov. 2018
 */
@Repository("haciendaDao")
public class HaciendaDaoImpl implements HaciendaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(Hacienda hacienda) {
		entityManager.persist(hacienda);

	}

	@Override
	public void modificar(Hacienda hacienda) {
		entityManager.merge(hacienda);

	}

	@Override
	public Hacienda findById(Long id) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.id = :id");
		query.setParameter("id", id);
		List<Hacienda> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Hacienda) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Hacienda findByEmpresaAndClave(Empresa empresa, String clave) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.empresa = :empresa and obj.clave = :clave ");
		query.setParameter("empresa", empresa);
		query.setParameter("clave", clave);
		List<Hacienda> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Hacienda) results.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Collection<Hacienda> findByEmpresaAndEstado(Empresa empresa, Integer estado) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado = :estado and obj.empresa = :empresa");
		query.setParameter("estado", estado);
		query.setParameter("empresa", empresa);
		return query.getResultList();
	}

	@Override
	public Collection<Hacienda> findByEstado(Integer estado, Integer estadoError) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado = :estado or obj.estado = :estadoError ");
		query.setParameter("estado", estado);
		query.setParameter("estadoError", estadoError);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);

		return query.getResultList();
	}

	@Override
	public Collection<Hacienda> findByEstadoOrEstadoErrorAndEmpresa(Empresa empresa ,Integer estado,Integer estadoError){
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado in(:estado,:estadoError) and obj.empresa = :empresa ");
		query.setParameter("estado", estado);
		query.setParameter("estadoError", estadoError);
		query.setParameter("empresa", empresa);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);

		return query.getResultList();
		
	}
	@Override
	public Collection<Hacienda> findByEstadoAndNotificacion(Integer estado, Integer notificacion) {
		Query query = entityManager.createQuery("select obj from Hacienda obj where obj.estado = :estado and obj.notificacion = :notificacion ");
		query.setParameter("estado", estado);
		query.setParameter("notificacion", notificacion);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR_CORREOS);

		return query.getResultList();
	}

}

package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.SalidaEntradaDineroDao;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.UsuarioCaja;

@Repository("salidaEntradaDineroDao")
public class SalidaEntradaDineroDaoImpl implements SalidaEntradaDineroDao {
	@PersistenceContext
	EntityManager entityManager;
	

	@Override
	public void agregar(SalidaEntradaDinero salidaEntradaDinero) {
		entityManager.persist(salidaEntradaDinero);

	}

	@SuppressWarnings("unchecked")
	@Override
	public SalidaEntradaDinero findById(Long id) {
		Query query = entityManager.createQuery("select obj from SalidaEntradaDinero obj where obj.id = :id");
		query.setParameter("id", id);
		List<SalidaEntradaDinero> results = query.getResultList();
		if (!results.isEmpty()) {
			return (SalidaEntradaDinero) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<SalidaEntradaDinero> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja) {
		Query query = entityManager.createQuery("select obj from SalidaEntradaDinero obj where obj.usuarioCaja = :usuarioCaja ");
		query.setParameter("usuarioCaja", usuarioCaja);
		return query.getResultList();
	}



	@SuppressWarnings("unchecked")
	@Override
	public Collection<SalidaEntradaDinero> buscarPorUsuarioCajaAndTipo(UsuarioCaja usuarioCaja, Integer tipo) {
		Query query = entityManager.createQuery("select obj from SalidaEntradaDinero obj where obj.usuariocaja.id = :idUsuarioCaja and obj.tipo = :tipo ");
		query.setParameter("idUsuarioCaja", usuarioCaja.getId());
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}



	@Override
	public void eliminar(SalidaEntradaDinero salidaEntradaDinero) {
		 entityManager.remove(salidaEntradaDinero);
		
	}

}

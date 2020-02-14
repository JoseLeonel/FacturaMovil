package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.SalidaEntradaDineroDao;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.UsuarioCaja;

@Repository("salidaEntradaDineroDao")
public class SalidaEntradaDineroDaoImpl implements SalidaEntradaDineroDao {
	@PersistenceContext
	EntityManager entityManager;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(SalidaEntradaDinero salidaEntradaDinero) {
		entityManager.persist(salidaEntradaDinero);

	}

	

	@Override
	public Collection<SalidaEntradaDinero> buscarPorUsuarioCaja(UsuarioCaja usuarioCaja) {
		Query query = entityManager.createQuery("select obj from SalidaEntradaDinero obj where obj.usuarioCaja = :usuarioCaja ");
		query.setParameter("usuarioCaja", usuarioCaja);
		return query.getResultList();
	}

}

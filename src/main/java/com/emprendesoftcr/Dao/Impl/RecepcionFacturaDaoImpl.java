package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.RecepcionFacturaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.RecepcionFactura;

@Repository("recepcionFacturaDao")
public class RecepcionFacturaDaoImpl implements RecepcionFacturaDao {

	@PersistenceContext
	EntityManager		entityManager;
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(RecepcionFactura recepcionFactura) {
		entityManager.persist(recepcionFactura);
	}

	@Override
	public void modificar(RecepcionFactura recepcionFactura) {
		entityManager.merge(recepcionFactura);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RecepcionFactura findById(Long id) {
		Query query = entityManager.createQuery("select obj from RecepcionFactura obj where obj.id = :id");
		query.setParameter("id", id);
		List<RecepcionFactura> results = query.getResultList();
		if (!results.isEmpty()) {
			return (RecepcionFactura) results.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Todas las facturas que no se le a creado la firma
	 * @see com.emprendesoftcr.Dao.FacturaDao#findByEstadoFirma(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma){
		Query query = entityManager.createQuery("select obj from RecepcionFactura obj where  obj.estadoFirma = :estadoFirma or  obj.estadoFirma = :reEstadoFirma");
		query.setParameter("estadoFirma", estadoFirma);
		query.setParameter("reEstadoFirma", reEstadoFirma);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);		
		return query.getResultList();
	}

	@Override
	public Collection<RecepcionFactura> findByClave(String cedulaEmisor, String clave) {
		Query query = entityManager.createQuery("select obj from RecepcionFactura obj where  obj.clave = :clave and obj.cedulaEmisor = :cedulaEmisor");
		query.setParameter("clave", clave);
		query.setParameter("cedulaEmisor", cedulaEmisor);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);		
		return query.getResultList();
	}

}
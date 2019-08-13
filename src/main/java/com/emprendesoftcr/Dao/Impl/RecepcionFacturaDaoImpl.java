package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.RecepcionFacturaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;

@Repository("recepcionFacturaDao")
public class RecepcionFacturaDaoImpl implements RecepcionFacturaDao {

	@PersistenceContext
	EntityManager		entityManager;

	@Override
	public void agregar(RecepcionFactura recepcionFactura) {
		entityManager.persist(recepcionFactura);
	}

	@Override
	public void agregar(RecepcionFacturaDetalle recepcionFacturaDetalle) {
		entityManager.persist(recepcionFacturaDetalle);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public RecepcionFactura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) throws Exception {
		Query query = entityManager.createQuery("select obj from RecepcionFactura obj where obj.numeroConsecutivoReceptor = :consecutivo and obj.empresa = :empresa");
		query.setParameter("consecutivo", consecutivo);
		query.setParameter("empresa", empresa);
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
		Query query = entityManager.createQuery("select obj from RecepcionFactura obj where  obj.facturaClave = :clave and obj.emisorCedula = :cedulaEmisor");
		query.setParameter("clave", clave);
		query.setParameter("cedulaEmisor", cedulaEmisor);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<RecepcionFactura> findByFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa,  String cedula , Integer estado) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from RecepcionFactura obj");
		hql.append(" where obj.empresa = :empresa ");
		if (cedula != null) {
			if (!cedula.equals(Constantes.EMPTY)) {
				hql.append("and obj.receptorCedula = :cedula ");
			}
		}
		hql.append("and obj.facturaFechaEmision >= :fechaInicio and obj.facturaFechaEmision <= :fechaFin and obj.estado = :estado");
		Query query = entityManager.createQuery(hql.toString());
		if (cedula != null) {
			if (!cedula.equals(Constantes.EMPTY)) {
				query.setParameter("cedula", cedula);
			}
		}
		query.setParameter("empresa", empresa);
		query.setParameter("estado", estado);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}


}
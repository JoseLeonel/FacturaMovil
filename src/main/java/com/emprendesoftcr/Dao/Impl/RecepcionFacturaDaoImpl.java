package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.RecepcionFacturaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;
import com.emprendesoftcr.utils.Constantes;

@Repository("recepcionFacturaDao")
public class RecepcionFacturaDaoImpl implements RecepcionFacturaDao {

	@PersistenceContext
	EntityManager entityManager;

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
	public Collection<RecepcionFactura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma) {
		Query query = entityManager.createQuery("select obj from RecepcionFactura obj where  obj.estadoFirma = :estadoFirma or  obj.estadoFirma = :reEstadoFirma");
		query.setParameter("estadoFirma", estadoFirma);
		query.setParameter("reEstadoFirma", reEstadoFirma);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
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
	public Collection<RecepcionFactura> findByFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa, String cedula, Integer estado,Integer tipoGasto,String actividadEconocimica) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from RecepcionFactura obj");
		hql.append(" where obj.empresa = :empresa ");
		if (cedula != null) {
			if (!cedula.equals(Constantes.EMPTY)) {
				hql.append("and obj.receptorCedula = :cedula ");
			}
		}
		if (estado != null) {
			if (!estado.equals(0)) {
				hql.append("and obj.estado = :estado ");
			}
		}
		if (tipoGasto != null) {
			if (!tipoGasto.equals(0)) {
				hql.append("and obj.tipoGasto = :tipoGasto ");
			}
		}
		if(!actividadEconocimica.equals(Constantes.COMBO_TODOS)) {
			hql.append("and obj.codigoActividad in (:codigoActividad)");
		}
		
		
	
		hql.append("and obj.facturaFechaEmision >= :fechaInicio and obj.facturaFechaEmision <= :fechaFin ");
		Query query = entityManager.createQuery(hql.toString());
		if (cedula != null) {
			if (!cedula.equals(Constantes.EMPTY)) {
				query.setParameter("cedula", cedula);
			}
		}
		if(!actividadEconocimica.equals(Constantes.COMBO_TODOS)) {
			query.setParameter("codigoActividad", actividadEconocimica);
		}
		query.setParameter("empresa", empresa);
		if (!estado.equals(0)) {
			query.setParameter("estado", estado);
		}
		if (!tipoGasto.equals(0)) { 
			query.setParameter("tipoGasto", tipoGasto);
		}
	
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<RecepcionFacturaDetalle> findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(Date fechaInicio, Date fechaFin, Empresa empresa, String cedula, Integer estado,Integer tipoGasto,String actividadEconocimica) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from RecepcionFacturaDetalle obj ");
		hql.append(" where obj.recepcionFactura.empresa = :empresa ");
		if (cedula != null) {
			if (!cedula.equals(Constantes.EMPTY)) {
				hql.append("and obj.recepcionFactura.receptorCedula = :cedula ");
			}
		}
		if(!actividadEconocimica.equals(Constantes.COMBO_TODOS)) {
			hql.append("and obj.recepcionFactura.codigoActividad in (:codigoActividad) ");
		}
		
		
		if (estado != null) {
			if (!estado.equals(0)) {
				hql.append("and obj.recepcionFactura.estado = :estado ");
			}
		}
		if (tipoGasto != null) {
			if (!tipoGasto.equals(0)) {
				hql.append("and obj.recepcionFactura.tipoGasto = :tipoGasto ");
			}
		}

		hql.append("and obj.recepcionFactura.facturaFechaEmision >= :fechaInicio and obj.recepcionFactura.facturaFechaEmision <= :fechaFin ");
		Query query = entityManager.createQuery(hql.toString());
		if (cedula != null) {
			if (!cedula.equals(Constantes.EMPTY)) {
				query.setParameter("cedula", cedula);
			}
		}
		query.setParameter("empresa", empresa);
		if (estado != null) {
			if (!estado.equals(0)) {
				query.setParameter("estado", estado);
			}
		}
		if(!actividadEconocimica.equals(Constantes.COMBO_TODOS)) {
			query.setParameter("codigoActividad", actividadEconocimica);
		}
		if (tipoGasto != null) {
			if (!tipoGasto.equals(0)) {
				query.setParameter("tipoGasto", tipoGasto);
			}
		}

		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<RecepcionFacturaDetalle> findByIdRecepcionFactura(Long id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from RecepcionFacturaDetalle obj ");
		hql.append(" where obj.recepcionFactura.id = :id ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public RecepcionFactura findByClaveAndCedulaEmisor(String clave, String cedula) throws Exception {
		Query query = entityManager.createQuery("select obj from RecepcionFactura obj where obj.facturaClave = :clave and obj.emisorCedula = :cedula");
		query.setParameter("clave", clave);
		query.setParameter("cedula", cedula);
		List<RecepcionFactura> results = query.getResultList();
		if (!results.isEmpty()) {
			return (RecepcionFactura) results.get(0);
		} else {
			return null;
		}
	}


}
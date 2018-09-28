package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.FacturaDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;

@Repository("facturaDao")
public class FacturaDaoImpl implements FacturaDao {

	@PersistenceContext
	EntityManager		entityManager;
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	public void agregar(Factura factura) {
		entityManager.persist(factura);
	}

	public void modificar(Factura factura) {
		entityManager.merge(factura);
	}

	public void eliminar(Factura factura) {
		entityManager.remove(factura);
	}

	/**
	 * buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public Factura findById(Long id) {
		Query query = entityManager.createQuery("select obj from Factura obj where obj.id = :id");
		query.setParameter("id", id);
		List<Factura> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Factura) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Busca por consecutivo y empresa
	 * @see com.emprendesoftcr.Dao.CompraDao#findByConsecutivoAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Factura obj where obj.numeroConsecutivo = :consecutivo and obj.empresa = :empresa");
		query.setParameter("consecutivo", consecutivo.toString());
		query.setParameter("empresa", empresa);
		List<Factura> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Factura) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Elimina los detalles de una factura para ser reemplazos por detalles nuevos Comparas Pendientes de ingresar el inventario
	 * @see com.emprendesoftcr.Dao.FacturaDao#eliminarDetalleFacturaPorSP(com.emprendesoftcr.modelo.Factura)
	 */
	@Override
	public void eliminarDetalleFacturaPorSP(Factura factura) {
		try {
			log.info("** Inicio de la ejecucion del procedimiento almacendos eliminar detalles de la factura : " + " fecha " + new Date());
			Query query = entityManager.createQuery("Delete from Detalle obj where obj.factura = :factura ");
			query.setParameter("factura", factura);
			int deletedCount = query.executeUpdate();
			
//			StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_ELIMINAR_DETALLES_FACTURA);
//			storedProcedure.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
//			storedProcedure.setParameter(0, factura.getId().intValue());
//			
//			storedProcedure.execute();

			log.info("** find de la ejecucion del procedimiento almacendos eliminar detalles de la factura : " + " fecha " + new Date());

		} catch (Exception e) {
			log.error("** Error ejecutar el procedimineto almacenados de eliminar detalles de la factura : " + e.getMessage() + " fecha " + new Date());
		}

	}

	/**
	 * Todas las facturas que no se le a creado la firma
	 * @see com.emprendesoftcr.Dao.FacturaDao#findByEstadoFirma(java.lang.Integer)
	 */
	@Override
	public Collection<Factura> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma){
		Query query = entityManager.createQuery("select obj from Factura obj where  obj.estadoFirma = :estadoFirma or  obj.estadoFirma = :reEstadoFirma");
		query.setParameter("estadoFirma", estadoFirma);
		query.setParameter("reEstadoFirma", reEstadoFirma);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);
		
		return query.getResultList();
	}

}
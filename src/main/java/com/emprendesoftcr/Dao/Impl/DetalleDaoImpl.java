package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.TotalDetallesCommand;

/**
 * Detalles de ventas DetalleDaoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@Repository("detalleDao")
public class DetalleDaoImpl implements DetalleDao {

	@PersistenceContext
	EntityManager		entityManager;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(Detalle detalle) {
		entityManager.persist(detalle);
	}

	@Override
	public void modificar(Detalle detalle) {
		entityManager.merge(detalle);
	}

	@Override
	public void eliminar(Detalle detalle) {
		entityManager.remove(detalle);
		entityManager.flush();
	}

	@Override
	public Integer eliminarDetalleFactura(Factura factura) throws Exception {
		try {

			Query query = entityManager.createQuery("DELETE FROM Detalle obj where obj.factura = :factura");
			query.setParameter("factura", factura);
			int deletedCount = query.setParameter("factura", factura).executeUpdate();

			return deletedCount;

		} catch (Exception e) {
			log.error("** Error ejecutar eliminar detalles de la factura : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from Detalle obj");
		hql.append(" where obj.factura.estado = :estado ");
		hql.append("and obj.factura.empresa = :empresa ");
		
		hql.append("and obj.factura.created_at >= :fechaInicio and obj.factura.created_at <= :fechaFin and obj.factura.referenciaCodigo != :referenciaCodigo");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("estado", estado);
		
		query.setParameter("referenciaCodigo", Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}
	
	@Override
	public TotalDetallesCommand totalVentasPorDetalle(Empresa empresa , Date fechaInicio,Date FechaFinal) {
		
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_VENTASXDETALLE);

		// set parametros entrada
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_IN_FECHA_INICIAL, Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_IN_FECHA_FINAL, Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_IN_ID_EMPRESA, Integer.class, ParameterMode.IN);

		// set parametros salida
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_GRAVADO, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_DESCUENTO, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_IMPUESTO, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_EXENTOS, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_OUT_TOTAL, Double.class, ParameterMode.OUT);
		
		
		
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_GANANCIA, Double.class, ParameterMode.OUT);
		

		// Valores de entrada
		storedProcedure.setParameter(Constantes.SP_VENTASXDETALLE_IN_FECHA_INICIAL, fechaInicio);
		storedProcedure.setParameter(Constantes.SP_VENTASXDETALLE_IN_FECHA_FINAL, FechaFinal);
		storedProcedure.setParameter(Constantes.SP_VENTASXDETALLE_IN_ID_EMPRESA, empresa.getId());
		storedProcedure.execute();


		return new TotalDetallesCommand((Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_GRAVADO), 
				                        (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_DESCUENTO), 
				                        (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_IMPUESTO),
				                        (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_EXENTOS), 
				                        (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL),
				                        (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_GANANCIA));
	}

}
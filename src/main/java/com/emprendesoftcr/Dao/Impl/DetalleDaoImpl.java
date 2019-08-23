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

import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
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
	public Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, Empresa empresa) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from Detalle obj");
		hql.append(" where obj.factura.estado = :estado ");
		hql.append("and obj.factura.empresa.id = :idEmpresa ");

		hql.append("and obj.factura.created_at >= :fechaInicio and obj.factura.created_at <= :fechaFin and obj.factura.referenciaCodigo != :referenciaCodigo");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("estado", estado);

		query.setParameter("referenciaCodigo", Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("idEmpresa", empresa.getId());
		return query.getResultList();
	}
	
	public Collection<Detalle> facturasRango(Integer estado, Date fechaInicio, Date fechaFin,Empresa empresa,String tipoImpuesto){
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from Detalle obj");
		hql.append(" where obj.factura.estado = :estado ");
		hql.append("and obj.factura.empresa.id = :idEmpresa ");

		hql.append("and obj.factura.created_at >= :fechaInicio and obj.factura.created_at <= :fechaFin and obj.factura.referenciaCodigo != :referenciaCodigo ");
		if(!tipoImpuesto.equals(Constantes.COMBO_TODOS)) {
			hql.append("and obj.tipoImpuesto = :tipoImpuesto");
		}
		hql.append(" order by obj.tipoImpuesto,obj.codigoTarifa ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("estado", estado);
		if(!tipoImpuesto.equals(Constantes.COMBO_TODOS)) {
			query.setParameter("tipoImpuesto", tipoImpuesto);
		}

		query.setParameter("referenciaCodigo", Constantes.FACTURA_CODIGO_REFERENCIA_ANULA_DOCUMENTO);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		query.setParameter("idEmpresa", empresa.getId());
		return query.getResultList();
		
	}

	@Override
	public Collection<Detalle> findByFactura(Factura factura) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from Detalle obj");
		hql.append(" where obj.factura.id = :idFactura ");
		hql.append("and obj.factura.empresa.id = :idEmpresa ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("idFactura", factura.getId());
		query.setParameter("idEmpresa", factura.getEmpresa().getId());
		return query.getResultList();

	}

	@Override
	public TotalDetallesCommand totalVentasPorDetalle(Empresa empresa, Date fechaInicio, Date FechaFinal, String tipoImpuesto,Integer estado) {
		StoredProcedureQuery storedProcedure =null;
		if(tipoImpuesto.equals(Constantes.COMBO_TODOS)){
			storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_VENTASXDETALLE_TIPO_IMPUESTO);	
		}else if(!tipoImpuesto.equals(Constantes.COMBO_TODOS)&& !tipoImpuesto.equals(Constantes.EMPTY)){
			storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_VENTASXDETALLE);
		}else {
			storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_VENTASXDETALLE_EXCEPTO);
		}
		
		

		// set parametros entrada
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_IN_FECHA_INICIAL, Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_IN_FECHA_FINAL, Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_IN_ID_EMPRESA, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_IN_ESTADO, Integer.class, ParameterMode.IN);
		if(!tipoImpuesto.equals(Constantes.COMBO_TODOS)&& !tipoImpuesto.equals(Constantes.EMPTY)) {
			storedProcedure.registerStoredProcedureParameter(Constantes.SP_VENTASXDETALLE_IN_TIPO_IMPUESTO, String.class, ParameterMode.IN);	
		}
		

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
		storedProcedure.setParameter(Constantes.SP_VENTASXDETALLE_IN_ESTADO, estado);
		
		if(!tipoImpuesto.equals(Constantes.COMBO_TODOS)&& !tipoImpuesto.equals(Constantes.EMPTY)) {
			storedProcedure.setParameter(Constantes.SP_VENTASXDETALLE_IN_TIPO_IMPUESTO, tipoImpuesto );	
		}
		
		storedProcedure.execute();

		return new TotalDetallesCommand((Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_GRAVADO), 
				       (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_DESCUENTO), 
				       (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_IMPUESTO), 
				       (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_EXENTOS),
				       (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL),
				       (Double) storedProcedure.getOutputParameterValue(Constantes.SP_VENTASXDETALLE_OUT_TOTAL_GANANCIA));
	}

	@Override
	public Detalle findByCodigoAndEmpresa(String codigo, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Detalle obj where obj.codigo = :codigo and obj.empresa = :empresa");
		query.setParameter("codigo", codigo);
		query.setParameter("empresa", empresa);
		List<Detalle> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Detalle) results.get(0);
		} else {
			return null;
		}
	}

}
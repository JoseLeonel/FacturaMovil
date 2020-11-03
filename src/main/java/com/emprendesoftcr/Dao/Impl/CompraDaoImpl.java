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

import com.emprendesoftcr.Dao.CompraDao;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.web.command.TotalComprasAceptadasCommand;

@Repository("compraDao")
public class CompraDaoImpl implements CompraDao {

	@PersistenceContext
	EntityManager		entityManager;
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(Compra compra) {
		entityManager.persist(compra);
	}

	@Override
	public void modificar(Compra compra) {
		entityManager.merge(compra);
	}

	@Override
	public void eliminar(Compra compra) {
		entityManager.remove(compra);
	}

	/**
	 * buscar por id
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Compra findById(Long id) {
		Query query = entityManager.createQuery("select obj from Compra obj where obj.id = :id");
		query.setParameter("id", id);
		List<Compra> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Compra) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Busca por consecutivo y empresa
	 * @see com.emprendesoftcr.Dao.CompraDao#findByConsecutivoAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Compra findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Compra obj where obj.consecutivo = :consecutivo and obj.empresa = :empresa");
		query.setParameter("consecutivo", consecutivo);
		query.setParameter("empresa", empresa);
		List<Compra> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Compra) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Elimina los detalles de una compra para ser reemplazos por detalles nuevos Comparas Pendientes de ingresar el inventario
	 * @see com.emprendesoftcr.Dao.CompraDao#eliminarDetalleComprasPorSP(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	public void eliminarDetalleComprasPorSP(Compra compra) {
		try {
			log.info("** Inicio de la ejecucion del procedimiento almacendos eliminar detalles de compra : " + " fecha " + new Date());
			Query query = entityManager.createQuery("Delete from DetalleCompra obj where obj.compra = :compra ");
			query.setParameter("compra", compra);
			int deletedCount = query.executeUpdate();

			log.info("** Fin de la ejecucion del procedimiento almacendos eliminar detalles de compra : " + " fecha " + new Date() + " " + deletedCount);

		} catch (Exception e) {
			log.error("** Error ejecutar el procedimineto almacenados de eliminar detalles de una compra : " + e.getMessage() + " fecha " + new Date());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Compra> findByFechaInicioAndFechaFinalAndProveedor(Date fechaInicio, Date fechaFin, Empresa empresa, Proveedor proveedor) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from Compra obj");
		hql.append(" where obj.empresa = :empresa ");
		if (proveedor != null) {
			hql.append("and obj.proveedor = :proveedor ");
		}
		hql.append("and obj.fechaIngreso >= :fechaInicio and obj.fechaIngreso <= :fechaFin ");
		Query query = entityManager.createQuery(hql.toString());
		if (proveedor != null) {
			query.setParameter("proveedor", proveedor);
		}
		query.setParameter("empresa", empresa);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}

	@Override
	public TotalComprasAceptadasCommand sumarComprasAceptadas(Date fechaInicio, Date fechaFinal, Integer idEmpresa, Integer estado, String actividadEconocimica, String tipoGasto) {

		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS);
		if(tipoGasto.equals(Constantes.COMBO_TODOS)) {
			tipoGasto = "1,2";
		} 

		// set parametros entrada
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_ACEPTADAS_IN_FECHA_INICIO, Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_ACEPTADAS_IN_FECHA_FIN, Date.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_ID_EMPRESA_IN, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_ESTADO_IN, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_TIPO_GASTO_IN, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_ACTIVIDAD_ECONOMICA_IN, String.class, ParameterMode.IN);

		// set parametros salida
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_OUT, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_NOTAS_CREDITO_OUT, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_NOTAS_DEBITO_OUT, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_IMPUESTOS_COMPRAS_ACEPTADAS_OUT, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_IMPUESTOS_COMPRAS_NOTA_CREDITO_ACEPTADAS_OUT, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_IMPUESTOS_COMPRAS_NOTA_DEBITO_ACEPTADAS_OUT, Double.class, ParameterMode.OUT);
		if (!actividadEconocimica.equals(Constantes.COMBO_TODOS)) {
			storedProcedure.setParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_ACTIVIDAD_ECONOMICA_IN, actividadEconocimica);
		}
		// Valores de entrada
		storedProcedure.setParameter(Constantes.SP_TOTAL_ACEPTADAS_IN_FECHA_INICIO, fechaInicio);
		storedProcedure.setParameter(Constantes.SP_TOTAL_ACEPTADAS_IN_FECHA_FIN, fechaFinal);
		storedProcedure.setParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_ID_EMPRESA_IN, idEmpresa);
		storedProcedure.setParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_TIPO_GASTO_IN, tipoGasto);
		storedProcedure.setParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_ACTIVIDAD_ECONOMICA_IN, actividadEconocimica);
		
		String estados = Constantes.EMPTY;
		if (estado.equals(0)) {
			estados = "2" + "," + "6" + "," + "7";

		} else {
			estados = estado.toString();
		}
		storedProcedure.setParameter(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_ESTADO_IN, estados);
		storedProcedure.execute();

		// Se toma la respuesta
		return new TotalComprasAceptadasCommand((Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_OUT),
				                                   (Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_NOTAS_CREDITO_OUT),
				                                   (Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_COMPRAS_ACEPTADAS_NOTAS_DEBITO_OUT),
				                                   (Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_IMPUESTOS_COMPRAS_ACEPTADAS_OUT),
				                                   (Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_IMPUESTOS_COMPRAS_NOTA_CREDITO_ACEPTADAS_OUT),
				                                   (Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_IMPUESTOS_COMPRAS_NOTA_DEBITO_ACEPTADAS_OUT)
				                                   );
	}

}
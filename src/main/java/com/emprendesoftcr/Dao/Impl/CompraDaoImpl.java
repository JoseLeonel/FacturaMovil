package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CompraDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;

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
//			StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_ELIMINAR_DETALLES_COMPRAS);
//			storedProcedure.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
//			storedProcedure.setParameter(0, compra.getId());
//			storedProcedure.execute();

			log.info("** Fin de la ejecucion del procedimiento almacendos eliminar detalles de compra : " + " fecha " + new Date());

		} catch (Exception e) {
			log.error("** Error ejecutar el procedimineto almacenados de eliminar detalles de una compra : " + e.getMessage() + " fecha " + new Date());
		}

	}
	
	@Override
	public Collection<Compra> findByFechaInicioAndFechaFinalAndProveedor(Date fechaInicio, Date fechaFin, Empresa empresa,  Proveedor proveedor){
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
	
	

}
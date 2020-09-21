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

import com.emprendesoftcr.Dao.CompraSimplificadaDao;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.utils.Constantes;

@Repository("compraSimplificadaDao")
public class CompraSimplificadaDaoImpl implements CompraSimplificadaDao {

	@PersistenceContext
	EntityManager		entityManager;
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(CompraSimplificada compraSimplificada) {
		entityManager.persist(compraSimplificada);
	}

	@Override
	public void modificar(CompraSimplificada compraSimplificada) {
		entityManager.merge(compraSimplificada);
	}

	@Override
	public void eliminar(CompraSimplificada compraSimplificada) {
		entityManager.remove(compraSimplificada);
	}

	/**
	 * buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public CompraSimplificada findById(Long id) {
		Query query = entityManager.createQuery("select obj from CompraSimplificada obj where obj.id = :id");
		query.setParameter("id", id);
		List<CompraSimplificada> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CompraSimplificada) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Busca por consecutivo y empresa
	 * @see com.emprendesoftcr.Dao.CompraDao#findByConsecutivoAndEmpresa(java.lang.String, com.emprendesoftcr.modelo.Empresa)
	 */
	@Override
	public CompraSimplificada findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from CompraSimplificada obj where obj.numeroConsecutivo = :consecutivo and obj.empresa = :empresa");
		query.setParameter("consecutivo", consecutivo);
		query.setParameter("empresa", empresa);
		List<CompraSimplificada> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CompraSimplificada) results.get(0);
		} else {
			return null;
		}

	}
	@Override
	public CompraSimplificada findByClaveAndEmpresa(String clave, Empresa empresa) throws Exception{
		Query query = entityManager.createQuery("select obj from CompraSimplificada obj where obj.clave = :clave and obj.empresa = :empresa");
		query.setParameter("clave", clave.toString());
		query.setParameter("empresa", empresa);
		List<CompraSimplificada> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CompraSimplificada) results.get(0);
		} else {
			return null;
		}
		
	}

	/**
	 * Elimina los detalles de una compra para ser reemplazos por detalles nuevos Comparas Pendientes de ingresar el inventario
	 * @see com.emprendesoftcr.Dao.CompraDao#eliminarDetalleComprasPorSP(com.emprendesoftcr.modelo.Compra)
	 */
	@Override
	public void eliminarDetalleComprasPorSP(CompraSimplificada compra) {
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
	public Collection<CompraSimplificada> findByEstadoFirma(Integer estadoFirma, Integer reEstadoFirma) {
		//Query query = entityManager.createQuery("select obj from Factura obj where  obj.estadoFirma in(:estadoFirma ,:reEstadoFirma) and obj.estado =  :estado and obj.empresa.id =  :idEmpresa order by obj.empresa.id");
		Query query = entityManager.createQuery("select obj from CompraSimplificada obj where  obj.estadoFirma in(:estadoFirma ,:reEstadoFirma) and obj.estado =  :estado order by obj.empresa.id");
		query.setParameter("estadoFirma", estadoFirma);
	//	query.setParameter("idEmpresa", Constantes.EMPRESA_VIVIANA_MARTINEZ_8085);
		query.setParameter("reEstadoFirma", reEstadoFirma);
		query.setParameter("estado", Constantes.FACTURA_ESTADO_FACTURADO);
		query.setMaxResults(Constantes.BLOQUES_DOCUMENTOS_A_PROCESAR);

		return query.getResultList();
	}

}
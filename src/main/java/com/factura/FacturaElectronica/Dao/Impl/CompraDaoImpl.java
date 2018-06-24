package com.factura.FacturaElectronica.Dao.Impl;

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

import com.factura.FacturaElectronica.Dao.CompraDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Compra;
import com.factura.FacturaElectronica.modelo.Empresa;

@Repository("compraDao")
public class CompraDaoImpl implements CompraDao {

	@PersistenceContext
	EntityManager		entityManager;
	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	public void agregar(Compra compra) {
		entityManager.persist(compra);
	}

	public void modificar(Compra compra) {
		entityManager.merge(compra);
	}

	public void eliminar(Compra compra) {
		entityManager.remove(compra);
	}

	/**
	 * buscar por id
	 * @param id
	 * @return
	 */
	@Override
	public Compra findById(Integer id) {
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
	 * @see com.factura.FacturaElectronica.Dao.CompraDao#findByConsecutivoAndEmpresa(java.lang.String, com.factura.FacturaElectronica.modelo.Empresa)
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
	 * @see com.factura.FacturaElectronica.Dao.CompraDao#eliminarDetalleComprasPorSP(com.factura.FacturaElectronica.modelo.Compra)
	 */
	@Override
	public void eliminarDetalleComprasPorSP(Compra compra) {
		try {
			log.info("** Inicio de la ejecucion del procedimiento almacendos eliminar detalles de compra : " + " fecha " + new Date());

			StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_ELIMINAR_DETALLES_COMPRAS);
			storedProcedure.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
			storedProcedure.setParameter(0, compra.getId());
			storedProcedure.execute();

			log.info("** Fin de la ejecucion del procedimiento almacendos eliminar detalles de compra : " + " fecha " + new Date());

		} catch (Exception e) {
			log.error("** Error ejecutar el procedimineto almacenados de eliminar detalles de una compra : " + e.getMessage() + " fecha " + new Date());
		}

	}

}
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

import com.factura.FacturaElectronica.Dao.FacturaDao;
import com.factura.FacturaElectronica.Utils.Constantes;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Factura;

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
	public Factura findById(Integer id) {
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
	 * @see com.factura.FacturaElectronica.Dao.CompraDao#findByConsecutivoAndEmpresa(java.lang.String, com.factura.FacturaElectronica.modelo.Empresa)
	 */
	@Override
	public Factura findByConsecutivoAndEmpresa(String consecutivo, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Factura obj where obj.consecutivo = :consecutivo and obj.empresa = :empresa");
		query.setParameter("consecutivo", consecutivo);
		query.setParameter("empresa", empresa);
		List<Factura> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Factura) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Elimina los detalles de una compra para ser reemplazos por detalles nuevos Comparas Pendientes de ingresar el inventario
	 * @see com.factura.FacturaElectronica.Dao.CompraDao#eliminarDetalleComprasPorSP(com.factura.FacturaElectronica.modelo.Compra)
	 */
	@Override
	public void eliminarDetalleFacturaPorSP(Factura factura) {
		try {
			log.info("** Inicio de la ejecucion del procedimiento almacendos eliminar detalles de compra : " + " fecha " + new Date());

			StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_ELIMINAR_DETALLES_COMPRAS);
			storedProcedure.registerStoredProcedureParameter(Constantes.SP_ELIMINAR_DETALLES_COMPRAS_ID_COMPRA_PARAM, Integer.class, ParameterMode.IN);
			storedProcedure.setParameter(Constantes.SP_ELIMINAR_DETALLES_COMPRAS_ID_COMPRA_PARAM, factura.getId());
			
			storedProcedure.execute();

			log.info("** Inicio de la ejecucion del procedimiento almacendos eliminar detalles de la factura : " + " fecha " + new Date());

		} catch (Exception e) {
			log.error("** Error ejecutar el procedimineto almacenados de eliminar detalles de la factura : " + e.getMessage() + " fecha " + new Date());
		}

	}

}
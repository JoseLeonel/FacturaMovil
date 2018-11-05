package com.emprendesoftcr.Dao.Impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.DetalleDao;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Factura;

/**
 * Detalles de ventas
 * DetalleDaoImpl.
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

}
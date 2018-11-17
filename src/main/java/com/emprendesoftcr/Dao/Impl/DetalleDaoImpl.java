package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	@Override
	public Collection<Detalle> facturasRangoEstado(Integer estado, Date fechaInicio, Date fechaFin, String codigo, String tipoDocumento, Cliente cliente, Empresa empresa, Usuario usuario) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from Detalle obj");
		hql.append(" where obj.factura.estado = :estado ");
		if (cliente != null) {
			hql.append("and obj.factura.cliente = :cliente ");
		}
		if (usuario != null) {
			hql.append("and obj.factura.usuarioCreacion = :usuario ");
		}
		hql.append("and obj.factura.empresa = :empresa ");
		if (tipoDocumento != null) {
			if (!tipoDocumento.equals(Constantes.COMBO_TODOS)) {
				hql.append("and obj.factura.tipoDoc = :tipoDocumento ");
			}
		}
		hql.append("and obj.codigo = :codigo ");
		hql.append("and obj.factura.created_at >= :fechaInicio and obj.factura.created_at <= :fechaFin ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("estado", estado);
		if (tipoDocumento != null) {
			if (!tipoDocumento.equals(Constantes.COMBO_TODOS)) {
				query.setParameter("tipoDocumento", tipoDocumento);
			}
		}
		if (cliente != null) {
			query.setParameter("cliente", cliente);
		}
		if (usuario != null) {
			query.setParameter("usuario", usuario);
		}
		query.setParameter("empresa", empresa);
		query.setParameter("codigo", codigo);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}

}
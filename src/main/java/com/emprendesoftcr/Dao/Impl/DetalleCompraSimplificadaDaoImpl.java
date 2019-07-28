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

import com.emprendesoftcr.Dao.DetalleCompraSimplificadaDao;
import com.emprendesoftcr.modelo.CompraSimplificada;
import com.emprendesoftcr.modelo.DetalleCompraSimplificada;
import com.emprendesoftcr.modelo.Empresa;

@Repository("detalleCompraSimplificadaDao")
public class DetalleCompraSimplificadaDaoImpl implements DetalleCompraSimplificadaDao {

	@PersistenceContext
	EntityManager		entityManager;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Override
	public void agregar(DetalleCompraSimplificada detalleCompraSimplificada) {
		entityManager.persist(detalleCompraSimplificada);
	}

	@Override
	public void modificar(DetalleCompraSimplificada detalleCompraSimplificada) {
		entityManager.merge(detalleCompraSimplificada);
	}

	@Override
	public void eliminar(DetalleCompraSimplificada detalleCompraSimplificada) {
		entityManager.remove(detalleCompraSimplificada);
		entityManager.flush();
	}

	@Override
	public Integer eliminarDetalleFactura(CompraSimplificada compraSimplificada) throws Exception {
		try {

			Query query = entityManager.createQuery("DELETE FROM Detalle obj where obj.compraSimplificada = :compraSimplificada");
			query.setParameter("compraSimplificada", compraSimplificada);
			int deletedCount = query.setParameter("compraSimplificada", compraSimplificada).executeUpdate();

			return deletedCount;

		} catch (Exception e) {
			log.error("** Error ejecutar eliminar detalles de la compraSimplificada : " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public DetalleCompraSimplificada findByCodigoAndEmpresa(String codigo, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from DetalleCompraSimplificada obj where obj.codigo = :codigo and obj.empresa = :empresa");
		query.setParameter("codigo", codigo);
		query.setParameter("empresa", empresa);
		List<DetalleCompraSimplificada> results = query.getResultList();
		if (!results.isEmpty()) {
			return (DetalleCompraSimplificada) results.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<DetalleCompraSimplificada> findByCompraSimplificada(CompraSimplificada compraSimplificada) {
		StringBuilder hql = new StringBuilder();
		hql.append("select obj from DetalleCompraSimplificada obj");
		hql.append(" where obj.compraSimplificada.id = :idFactura ");
		hql.append("and obj.compraSimplificada.empresa.id = :idEmpresa ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("idFactura", compraSimplificada.getId());
		query.setParameter("idEmpresa", compraSimplificada.getEmpresa().getId());
		return query.getResultList();

	}

}
package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.DetalleCompraDao;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;

/**
 * Detalles compras de proveedores
 * DetalleCompraDaoImpl.
 * @author jose.
 * @since 3 nov. 2018
 */
@Repository("detalleCompraDao")
public class DetalleCompraDaoImpl implements DetalleCompraDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(DetalleCompra detalleCompra) {
		entityManager.persist(detalleCompra);
	}
	
	@Override
	public void modificar(DetalleCompra detalleCompra) {
		entityManager.merge(detalleCompra);
	}

	@Override
	public void eliminar(DetalleCompra detalleCompra) {
		entityManager.remove(detalleCompra);
	}

@SuppressWarnings("unchecked")
@Override
public Collection<DetalleCompra> findByCompra(Compra compra){
	StringBuilder hql = new StringBuilder();
	hql.append("select obj from DetalleCompra obj");
	hql.append(" where obj.compra.id = :idCompra ");
	hql.append("and obj.compra.empresa.id = :idEmpresa ");
	Query query = entityManager.createQuery(hql.toString());
	query.setParameter("idCompra", compra.getId());
	query.setParameter("idEmpresa", compra.getEmpresa().getId());
	return query.getResultList();
}

@SuppressWarnings("unchecked")
@Override
public DetalleCompra findById(Long idDetalleCompra) {
	Query query = entityManager.createQuery("select obj from DetalleCompra obj where obj.id = :id");
	query.setParameter("id", idDetalleCompra);
	List<DetalleCompra> results = query.getResultList();
	if (!results.isEmpty()) {
		return (DetalleCompra) results.get(0);
	} else {
		return null;
	}
}

}
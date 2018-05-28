package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.CompraDao;
import com.factura.FacturaElectronica.modelo.Compra;
import com.factura.FacturaElectronica.modelo.Empresa;

@Repository("compraDao")
public class CompraDaoImpl implements CompraDao {

	@PersistenceContext
	EntityManager entityManager;

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

}
package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.CuentaCobrarDao;
import com.factura.FacturaElectronica.modelo.CuentaCobrar;

/**
 * Clientes por sucursal de empresa ClienteDaoImpl.
 * @author jose.
 * @since 18 mar. 2018
 */
@Repository("cuentaCobrarDao")
public class CuentaCobrarDaoImpl implements CuentaCobrarDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(CuentaCobrar cuentaCobrar) {
		entityManager.persist(cuentaCobrar);
	}

	public void modificar(CuentaCobrar cuentaCobrar) {
		entityManager.merge(cuentaCobrar);
	}

	public void eliminar(CuentaCobrar cuentaCobrar) {
		entityManager.remove(cuentaCobrar);
	}

	/**
	 * Busca por id y retorna la cuenta de cobrar
	 * @see com.factura.dao.CuentaCobrarDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public CuentaCobrar buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from CuentaCobrar obj where obj.id = :id");
		query.setParameter("id", id);
		List<CuentaCobrar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaCobrar) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar la letra de cambio retorna la cuenta por cobrar
	 * @see com.factura.dao.CuentaCobrarDao#buscarByLetraCambio(java.lang.String)
	 */
	public CuentaCobrar buscarPorLetraCambio(String letraCambio){
		Query query = entityManager.createQuery("select obj from CuentaCobrar obj where obj.letraCambio = :letraCambio");
		query.setParameter("letraCambio", letraCambio);
		List<CuentaCobrar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaCobrar) results.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Buscar factura manual  
	 * @param facturaManual
	 * @return
	 */
	public CuentaCobrar buscarPorFacturaManual(Integer facturaManual){
		Query query = entityManager.createQuery("select obj from CuentaCobrar obj where obj.facturaManual = :facturaManual");
		query.setParameter("facturaManual", facturaManual);
		List<CuentaCobrar> results = query.getResultList();
		if (!results.isEmpty()) {
			return (CuentaCobrar) results.get(0);
		} else {
			return null;
		}
		
	}
	

}
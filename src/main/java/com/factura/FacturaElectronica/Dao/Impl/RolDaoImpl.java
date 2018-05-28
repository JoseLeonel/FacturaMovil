package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.RolDao;
import com.factura.FacturaElectronica.modelo.Rol;

/**
 * Rol de usuarios RolDaoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Repository("rolDao")
public class RolDaoImpl implements RolDao {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Buscar el username
	 * @see com.factura.dao.RolDao#buscarByUserName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Rol buscarPorUserName(String username) {
		Query query = entityManager.createQuery("select obj from Rol obj where obj.username = :username");
		query.setParameter("username", username);
		List<Rol> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Rol) results.get(0);
		} else {
			return null;
		}
	}

}
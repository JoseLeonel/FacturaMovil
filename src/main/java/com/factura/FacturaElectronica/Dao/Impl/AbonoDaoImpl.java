package com.factura.FacturaElectronica.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.AbonoDao;
import com.factura.FacturaElectronica.modelo.Abono;

/**
 * AbonoDaoImpl.
 * @author jose.
 * @since 26 mar. 2018
 */
@Repository("abonoDao")
public class AbonoDaoImpl implements AbonoDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Abono abono) {
		entityManager.persist(abono);
	}
	
	public void modificar(Abono abono) {
		entityManager.merge(abono);
	}

	public void eliminar(Abono abono) {
		entityManager.remove(abono);
	}

	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Abono buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from Abono obj where obj.id = :id");
		query.setParameter("id", id);
		List<Abono> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Abono) results.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Abono> buscarPorCuentaCobrar() {
		Query query = entityManager.createQuery("select obj from Abono obj order by obj.id");
		return query.getResultList();
	}

}
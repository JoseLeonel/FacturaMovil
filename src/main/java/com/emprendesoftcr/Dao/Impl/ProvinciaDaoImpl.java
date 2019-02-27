package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ProvinciaDao;
import com.emprendesoftcr.modelo.Canton;
import com.emprendesoftcr.modelo.Distrito;
import com.emprendesoftcr.modelo.Provincia;

@Repository("provinciaDao")
public class ProvinciaDaoImpl implements ProvinciaDao {

	@PersistenceContext
	EntityManager entityManager;

	
	/**
	 * Buscar una Caja
	 * @see com.emprendesoftcr.Dao.CajaDao#buscar(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Provincia findByCodigo(Integer codigo) {
		Query query = entityManager.createQuery("select obj from Provincia obj where obj.codigo = :codigo");
		query.setParameter("codigo", codigo);
		List<Provincia> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Provincia) results.get(0);
		} else {
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Canton findCantonByCodigo(Integer codigoProvincia, Integer codigoCanton) {
		Query query = entityManager.createQuery("select obj from Canton obj where obj.codigo = :codigoCanton and obj.codigo_provincia = :codigoProvincia");
		query.setParameter("codigoCanton", codigoCanton);
		query.setParameter("codigoProvincia", codigoProvincia);
		List<Canton> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Canton) results.get(0);
		} else {
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Distrito findDistritoByCodigo(Integer codigoProvincia, Integer codigoCanton, Integer codigoDistrito) {
		Query query = entityManager.createQuery("select obj from Distrito obj where obj.codigo = :codigoDistrito and obj.codigoCanton = :codigoCanton and obj.codigoProvincia = :codigoProvincia");
		query.setParameter("codigoDistrito", codigoDistrito);
		query.setParameter("codigoCanton", codigoCanton);
		query.setParameter("codigoProvincia", codigoProvincia);
		List<Distrito> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Distrito) results.get(0);
		} else {
			return null;
		}
	}

	
}
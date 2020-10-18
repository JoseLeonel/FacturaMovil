package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ArchivoXMLDao;
import com.emprendesoftcr.modelo.ArchivoXML;
import com.emprendesoftcr.modelo.Empresa;

@Repository("archivoXMLDao")
public class ArchivoXMLDaoImpl implements ArchivoXMLDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(ArchivoXML archivoXML) {
		entityManager.persist(archivoXML);

	}

	@Override
	public void modificar(ArchivoXML archivoXML) {
		entityManager.merge(archivoXML);

	}

	@Override
	public void eliminar(ArchivoXML archivoXML) {
		entityManager.remove(archivoXML);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArchivoXML findById(Long id) {
		Query query = entityManager.createQuery("select obj from ArchivoXML obj where obj.id = :id");
		query.setParameter("id", id);
		List<ArchivoXML> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ArchivoXML) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArchivoXML findByIdFactura(Empresa empresa, Long idFactura) {
		Query query = entityManager.createQuery("select obj from ArchivoXML obj where obj.numeroFactura = :idFactura and obj.numeroEmpresa = :idEmpresa");
		query.setParameter("idFactura", idFactura);
		query.setParameter("idEmpresa", empresa.getId());
		List<ArchivoXML> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ArchivoXML) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArchivoXML findByClave(Empresa empresa, String clave) {
		Query query = entityManager.createQuery("select obj from ArchivoXML obj where obj.clave = :clave and obj.numeroEmpresa = :idEmpresa");
		query.setParameter("clave", clave);
		query.setParameter("idEmpresa", empresa.getId());
		List<ArchivoXML> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ArchivoXML) results.get(0);
		} else {
			return null;
		}
	}

}

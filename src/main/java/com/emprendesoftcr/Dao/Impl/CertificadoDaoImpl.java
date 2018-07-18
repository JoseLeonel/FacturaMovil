package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CertificadoDao;
import com.emprendesoftcr.modelo.Certificado;
import com.emprendesoftcr.modelo.Empresa;

@Repository("certificadoDao")
public class CertificadoDaoImpl implements CertificadoDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void agregar(Certificado certificado) {
		entityManager.persist(certificado);

	}

	@Override
	public Certificado findById(Integer id) {
		Query query = entityManager.createQuery("select obj from Certificado obj where obj.id = :id");
		query.setParameter("id", id);
		List<Certificado> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Certificado) results.get(0);
		} else {
			return null;
		}
		
	}

	@Override
	public Certificado findByEmpresa(Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Certificado obj where obj.empresa = :empresa");
		query.setParameter("empresa", empresa);
		List<Certificado> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Certificado) results.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Todos los certificados activos
	 * @see com.emprendesoftcr.Dao.CertificadoDao#findByEstado(java.lang.Integer)
	 */
	@Override
	public Collection<Certificado> findByAll(){
		Query query = entityManager.createQuery("select obj from Certificado obj  ");
		
		return query.getResultList();
	}

}

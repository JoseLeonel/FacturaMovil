package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.RomanaBalanzaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RomadaBalanza;

@Repository("romanaBalanzaDao")
public class RomanaBalanzaDaoImpl implements RomanaBalanzaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void agregar(RomadaBalanza romadaBalanza) {
		entityManager.persist(romadaBalanza);
	}

	@Override
	public void modificar(RomadaBalanza romadaBalanza) {
		entityManager.merge(romadaBalanza);
	}

	@Override
	public void eliminar(RomadaBalanza romadaBalanza) {
		entityManager.remove(romadaBalanza);
	}

	/**
	 * Buscar el objeto marca por id
	 * @see com.factura.dao.MarcaDao#buscar(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public RomadaBalanza buscar(Long id) {
		Query query = entityManager.createQuery("select obj from RomadaBalanza obj where obj.id = :id");
		query.setParameter("id", id);
		List<RomadaBalanza> results = query.getResultList();
		if (!results.isEmpty()) {
			return (RomadaBalanza) results.get(0);
		} else {
			return null;
		}
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public RomadaBalanza buscarPorEmpresa(Empresa empresa) {
		Query query = entityManager.createQuery("select obj from RomadaBalanza obj where obj.empresa = :empresa");

		query.setParameter("empresa", empresa);
		List<RomadaBalanza> results = query.getResultList();
		if (!results.isEmpty()) {
			return (RomadaBalanza) results.get(0);
		} else {
			return null;
		}
	}
}

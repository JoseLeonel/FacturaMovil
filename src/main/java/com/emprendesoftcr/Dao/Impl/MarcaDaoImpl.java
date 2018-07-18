package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.MarcaDao;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Marca;

/**
 * Mantenimiento de Marcas de los articulos del almacen MarcaDaoImpl.
 * @author lhernandez.
 * @since 7 abr. 2018
 */
@Repository("marcaDao")
public class MarcaDaoImpl implements MarcaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Marca marca) {
		entityManager.persist(marca);
	}

	@Override
	public void modificar(Marca marca) {
		entityManager.merge(marca);
	}

	@Override
	public void eliminar(Marca marca) {
		entityManager.remove(marca);
	}

	/**
	 * Buscar el objeto marca por id
	 * @see com.factura.dao.MarcaDao#buscar(java.lang.Integer)
	 */
	@Override
	public Marca buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from Marca obj where obj.id = :id");
		query.setParameter("id", id);
		List<Marca> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Marca) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por descripcion la marca y empresa
	 * @see com.factura.dao.MarcaDao#buscarByDescripcionAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Marca buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Marca obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<Marca> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Marca) results.get(0);
		} else {
			return null;
		}
	}

}
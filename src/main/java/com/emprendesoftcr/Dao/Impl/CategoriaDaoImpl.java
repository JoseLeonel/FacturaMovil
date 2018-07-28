package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CategoriaDao;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;

/**
 * Mantenimiento de Categorias de los articulos del almacen CategoriaDaoImpl.
 * @author lhernandez.
 * @since 7 abr. 2018
 */
@Repository("categoriaDao")
public class CategoriaDaoImpl implements CategoriaDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Categoria categoria) {
		entityManager.persist(categoria);
	}

	public void modificar(Categoria categoria) {
		entityManager.merge(categoria);
	}

	public void eliminar(Categoria categoria) {
		entityManager.remove(categoria);
	}

/**
 * Buscar el objeto categoria por id
 * @see com.factura.dao.CategoriaDao#buscar(java.lang.Integer)
 */
	@SuppressWarnings("unchecked")
	public Categoria buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Categoria obj where obj.id = :id");
		query.setParameter("id", id);
		List<Categoria> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Categoria) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por descripcion la categoria y empresa
	 * @see com.factura.dao.CategoriaDao#buscarByDescripcionAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	
	public Categoria buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Categoria obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<Categoria> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Categoria) results.get(0);
		} else {
			return null;
		}
	}

}
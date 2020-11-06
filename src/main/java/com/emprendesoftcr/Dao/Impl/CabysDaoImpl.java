package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.CabysDao;
import com.emprendesoftcr.modelo.Cabys;
import com.emprendesoftcr.modelo.Empresa;

/**
 * Mantenimiento de cabys
 * @author lhernandez.
 * @since 7 abr. 2018
 */
@Repository("cabysDao")
public class CabysDaoImpl implements CabysDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Cabys categoria) {
		entityManager.persist(categoria);
	}

	public void modificar(Cabys categoria) {
		entityManager.merge(categoria);
	}

	public void eliminar(Cabys categoria) {
		entityManager.remove(categoria);
	}

/**
 * Buscar el objeto categoria por id
 * @see com.factura.dao.CategoriaDao#buscar(java.lang.Integer)
 */
	@SuppressWarnings("unchecked")
	public Cabys buscar(Long id) {
		Query query = entityManager.createQuery("select obj from Cabys obj where obj.id = :id");
		query.setParameter("id", id);
		List<Cabys> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cabys) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por descripcion la categoria y empresa
	 * @see com.factura.dao.CategoriaDao#buscarByDescripcionAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	
	@SuppressWarnings("unchecked")
	public Cabys buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Cabys obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<Cabys> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cabys) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Cabys> findByEmpresaAll(Integer idEmpresa) {
		Query query = entityManager.createQuery("select obj from Cabys obj where  obj.empresa.id = :idEmpresa order by obj.id,obj.descripcion");
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Cabys findByCodigo(String codigo, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Cabys obj where obj.codigo = :codigo and obj.empresa = :empresa");
		query.setParameter("codigo", codigo);
		query.setParameter("empresa", empresa);
		List<Cabys> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Cabys) results.get(0);
		} else {
			return null;
		}
	}

}
package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.MarcaDao;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Marca;

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

	public void modificar(Marca marca) {
		entityManager.merge(marca);
	}

	public void eliminar(Marca marca) {
		entityManager.remove(marca);
	}

	/**
	 * Buscar el objeto marca por id
	 * @see com.factura.dao.MarcaDao#buscar(java.lang.Integer)
	 */
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
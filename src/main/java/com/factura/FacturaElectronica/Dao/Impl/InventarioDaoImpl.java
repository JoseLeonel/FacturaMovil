package com.factura.FacturaElectronica.Dao.Impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.InventarioDao;
import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Inventario;

/**
 * Inventarios para el almacen. InventarioDaoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Repository
public class InventarioDaoImpl implements InventarioDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Inventario inventario) {
		entityManager.persist(inventario);
	}

	public void modificar(Inventario inventario) {
		entityManager.merge(inventario);
	}

	public void eliminar(Inventario inventario) {
		entityManager.remove(inventario);
	}

	/**
	 * Buscar el objeto inventario por id
	 * @see com.factura.dao.InventarioDao#buscar(java.lang.Integer)
	 */

	public Inventario buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from Inventario obj where obj.id = :id");
		query.setParameter("id", id);
		List<Inventario> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Inventario) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar un articulo en el inventario en la misma sucursal
	 * @see com.factura.dao.InventarioDao#buscarByArticulo(com.factura.domain.Articulo)
	 */
	public Inventario buscarPorArticulo(Articulo articulo) {
		Query query = entityManager.createQuery("select obj from Inventario obj where obj.articulo = :articulo ");
		query.setParameter("articulo", articulo);
		List<Inventario> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Inventario) results.get(0);
		} else {
			return null;
		}
	}
	/**
	 * Buscar Inventario por articulo y estado
	 * @see com.factura.FacturaElectronica.Dao.InventarioDao#findByArticuloAndEstado(com.factura.FacturaElectronica.modelo.Articulo, java.lang.Integer)
	 */
  @Override
	public Inventario findByArticuloAndEstado(Articulo articulo,String estado){
		Query query = entityManager.createQuery("select obj from Inventario obj where obj.articulo = :articulo and obj.estado = :estado");
		query.setParameter("articulo", articulo);
		query.setParameter("estado", estado);
		List<Inventario> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Inventario) results.get(0);
		} else {
			return null;
		}
		
	}
  
  /**
	 * Obtener el costo total actual
	 * @param inventario
	 * @return
	 */
	public BigDecimal getTotalCosto(Inventario inventario, BigDecimal cantidad) {
		BigDecimal resultado = BigDecimal.ZERO;

		resultado = inventario.getArticulo().getCosto().multiply(cantidad);

		return resultado;
	}

	
}
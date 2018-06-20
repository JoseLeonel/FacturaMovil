package com.factura.FacturaElectronica.Dao.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.ArticuloDao;
import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Empresa;
import com.factura.FacturaElectronica.modelo.Inventario;

/**
 * Mantenimiento de Articulos de los articulos del almacen ArticuloDaoImpl.
 * @author lhernandez.
 * @since 7 abr. 2018
 */
@Repository("articuloDao")
public class ArticuloDaoImpl implements ArticuloDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Articulo articulo) {
		entityManager.persist(articulo);
	}

	public void modificar(Articulo articulo) {
		entityManager.merge(articulo);
	}

	public void eliminar(Articulo articulo) {
		entityManager.remove(articulo);
	}

	/**
	 * Buscar el objeto articulo por id
	 * @see com.factura.dao.ArticuloDao#buscar(java.lang.Integer)
	 */
	@Override
	public Articulo buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from Articulo obj where obj.id = :id");
		query.setParameter("id", id);
		List<Articulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Articulo) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por descripcion la articulo y empresa
	 * @see com.factura.dao.ArticuloDao#buscarByDescripcionAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Articulo buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Articulo obj where obj.descripcion = :descripcion and obj.empresa = :empresa");
		query.setParameter("descripcion", descripcion);
		query.setParameter("empresa", empresa);
		List<Articulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Articulo) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Busca por codigo del articulo
	 * @see com.factura.dao.ArticuloDao#buscarByCodigoAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Articulo buscarPorCodigoYEmpresa(String codigo, Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Articulo obj where obj.codigo = :codigo and obj.empresa = :empresa");
		query.setParameter("codigo", codigo);
		query.setParameter("empresa", empresa);

		List<Articulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Articulo) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Busca un inventario con el articulo
	 * @param articulo
	 * @return
	 */

	public Inventario buscarPorArticulo(Articulo articulo) {
		Query query = entityManager.createQuery("select obj from Articulo obj where obj.inventario = :inventario");
		query.setParameter("articulo", articulo);
		List<Inventario> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Inventario) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Porcentaje de ganancia
	 * @see com.factura.FacturaElectronica.Dao.ArticuloDao#porcentanjeDeGanancia(java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public BigDecimal porcentanjeDeGanancia(BigDecimal costo, BigDecimal iva, BigDecimal precio) {
		if(precio == null || costo == null) {
			return BigDecimal.ZERO;
		}
		BigDecimal resultado = BigDecimal.ZERO;
		BigDecimal precioSinImpuesto = BigDecimal.ZERO;
		costo = costo == null ? BigDecimal.ZERO : costo;
		precio = precio == null ? BigDecimal.ZERO : precio;
		iva = iva == null ? BigDecimal.ZERO : iva;
		if (iva.compareTo(BigDecimal.ZERO) == 0) {
			resultado = costo.divide(precio, 5, RoundingMode.HALF_UP);
			BigDecimal uno = new BigDecimal("1");
			resultado = uno.subtract(resultado);
		} else {
			BigDecimal porcentaje = new BigDecimal("100");
			BigDecimal uno = new BigDecimal("1");
			BigDecimal impuesto = iva.divide(porcentaje,5,RoundingMode.HALF_UP);
			impuesto = impuesto.add(uno);

			precioSinImpuesto = precio.divide(impuesto,5,RoundingMode.HALF_UP);

			resultado = costo.divide(precioSinImpuesto,5,RoundingMode.HALF_UP);
			resultado = uno.subtract(resultado);
		}
		BigDecimal porcentaje = new BigDecimal("100");

		return resultado.multiply(porcentaje);
	}

	/**
	 * Costo Ponderado
	 * @see com.factura.FacturaElectronica.Dao.ArticuloDao#costoPromedio(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public BigDecimal costoPromedio(BigDecimal costoActual, BigDecimal costoNuevo, BigDecimal cantidadActual, BigDecimal cantidadNueva) {
		BigDecimal resultado = BigDecimal.ZERO;

		BigDecimal totalCostoActual = costoActual.multiply(cantidadActual);
		BigDecimal totalCostoNuevo = costoNuevo.multiply(cantidadNueva);
		BigDecimal totalProductos = cantidadActual.multiply(cantidadNueva);
		resultado = (totalCostoActual.add(totalCostoNuevo));

		return resultado.divide(totalProductos);

	}

}
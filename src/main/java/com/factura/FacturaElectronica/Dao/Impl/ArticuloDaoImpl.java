package com.factura.FacturaElectronica.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.ArticuloDao;
import com.factura.FacturaElectronica.Utils.Constantes;
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
	public Double porcentanjeDeGanancia(Double costo, Double iva, Double precio) {
		if(precio == null || costo == null) {
			return Constantes.ZEROS_DOUBLE;
		}
		if(precio == 0) {
			return Constantes.ZEROS_DOUBLE;
		}
		Double resultado = Constantes.ZEROS_DOUBLE;
		Double precioSinImpuesto = Constantes.ZEROS_DOUBLE;
		costo = costo == null ? Constantes.ZEROS_DOUBLE : costo;
		precio = precio == null ? Constantes.ZEROS_DOUBLE: precio;
		iva = iva == null ? Constantes.ZEROS_DOUBLE : iva;
		if (iva == 0) {
			resultado = costo/precio;
			resultado = 1-resultado;
		} else {
			Double porcentaje = 100d;
			Double uno = 1d;
			Double impuesto = iva/porcentaje;
			impuesto = impuesto+ uno;

			precioSinImpuesto = precio/impuesto;

			resultado = costo/precioSinImpuesto;
			resultado = uno-resultado;
		}
		Double porcentaje = 100d;

		return resultado * porcentaje;
	}

	/**
	 * Costo Ponderado
	 * @see com.factura.FacturaElectronica.Dao.ArticuloDao#costoPromedio(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public Double costoPromedio(Double costoActual, Double costoNuevo, Double cantidadActual, Double cantidadNueva) {
		Double resultado = Constantes.ZEROS_DOUBLE;

		Double totalCostoActual = costoActual * cantidadActual;
		Double totalCostoNuevo = costoNuevo * cantidadNueva;
		Double totalProductos = cantidadActual + cantidadNueva;
		resultado = (totalCostoActual + totalCostoNuevo);

		return resultado / totalProductos;

	}

}
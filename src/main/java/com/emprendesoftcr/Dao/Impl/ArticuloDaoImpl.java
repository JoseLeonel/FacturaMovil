package com.emprendesoftcr.Dao.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.web.command.TotalInventarioCommand;

/**
 * Mantenimiento de Articulos de los articulos del almacen ArticuloDaoImpl.
 * @author lhernandez.
 * @since 7 abr. 2018
 */
@Repository("articuloDao")
public class ArticuloDaoImpl implements ArticuloDao {

	@PersistenceContext
	EntityManager		entityManager;

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

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
	public Articulo buscar(Long id) {
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
		Query query = entityManager.createQuery("select distinct obj from Articulo obj where obj.codigo = :codigo and obj.empresa = :empresa");
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

	public Articulo buscarPorArticulo(Articulo articulo) {
		Query query = entityManager.createQuery("select obj from Articulo obj where obj.id = :id");
		query.setParameter("id", articulo.getId());
		List<Articulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Articulo) results.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Porcentaje de ganancia
	 * @see com.emprendesoftcr.Dao.ArticuloDao#porcentanjeDeGanancia(java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public Double porcentanjeDeGanancia(Double costo, Double iva, Double precio) throws Exception {
		try {
			if (precio == null || costo == null) {
				return Constantes.ZEROS_DOUBLE;
			}
			if (precio == 0) {
				return Constantes.ZEROS_DOUBLE;
			}
			if (precio < costo) {
				return Constantes.ZEROS_DOUBLE;

			}
			Double resultado = Constantes.ZEROS_DOUBLE;
			Double precioSinImpuesto = Constantes.ZEROS_DOUBLE;
			costo = costo == null ? Constantes.ZEROS_DOUBLE : costo;
			precio = precio == null ? Constantes.ZEROS_DOUBLE : precio;
			iva = iva == null ? Constantes.ZEROS_DOUBLE : iva;
			if (iva == 0) {
				resultado = costo / precio;
				resultado = 1 - resultado;
			} else {
				Double porcentaje = 100d;
				Double uno = 1d;
				Double impuesto = iva / porcentaje;
				impuesto = impuesto + uno;
				precioSinImpuesto = precio / impuesto;
				resultado = costo / precioSinImpuesto;
				resultado = uno - resultado;
			}
			Double porcentaje = 100d;

			return Utils.roundFactura(resultado * porcentaje, 5);

		} catch (Exception e) {
			log.info("** Error  porcentanjeDeGanancia: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	/**
	 * Costo Ponderado
	 * @see com.emprendesoftcr.Dao.ArticuloDao#costoPromedio(java.lang.Double, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public Double costoPromedio(Double costoActual, Double costoNuevo, Double cantidadActual, Double cantidadNueva) throws Exception {
		try {
			Double resultado = Constantes.ZEROS_DOUBLE;
			costoActual = costoActual == null ? Constantes.ZEROS_DOUBLE : costoActual;
			costoNuevo = costoNuevo == null ? Constantes.ZEROS_DOUBLE : costoNuevo;
			cantidadNueva = cantidadNueva == null ? Constantes.ZEROS_DOUBLE : cantidadNueva;

			Double totalCostoActual = costoActual * cantidadActual;
			Double totalCostoNuevo = costoNuevo * cantidadNueva;
			Double totalProductos = cantidadActual + cantidadNueva;
			resultado = (totalCostoActual + totalCostoNuevo);

			return Utils.roundFactura(resultado / totalProductos, 5);

		} catch (Exception e) {
			log.info("** Error  costoPromedio: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public Double getTotalCosto(Articulo articulo, Double cantidad) throws Exception {
		try {
			Double resultado = Constantes.ZEROS_DOUBLE;

			cantidad = cantidad == null ? Constantes.ZEROS_DOUBLE : cantidad;

			Double costo = articulo.getCosto() == null ? Constantes.ZEROS_DOUBLE : articulo.getCosto();

			resultado = costo * cantidad;

			return Utils.roundFactura(resultado, 5);

		} catch (Exception e) {
			log.info("** Error  getTotalCosto: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@Override
	public TotalInventarioCommand sumarInventarios(Integer idEmpresa) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(Constantes.SP_TOTAL_INVENTARIO);

		// set parametros entrada
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_INVENTARIO_ID_EMPRESA, Integer.class, ParameterMode.IN);

		// set parametros salida
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_COSTO_OUT, Double.class, ParameterMode.OUT);
		storedProcedure.registerStoredProcedureParameter(Constantes.SP_TOTAL_VENTA_OUT, Double.class, ParameterMode.OUT);

		// Valores de entrada
		storedProcedure.setParameter(Constantes.SP_INVENTARIO_ID_EMPRESA, idEmpresa);
		storedProcedure.execute();

		// Se toma la respuesta
		return new TotalInventarioCommand((Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_COSTO_OUT), (Double) storedProcedure.getOutputParameterValue(Constantes.SP_TOTAL_VENTA_OUT));

	}

	@Override
	public Collection<Articulo> articulosBy(Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Articulo obj where  obj.empresa = :empresa order by obj.codigo");
		query.setParameter("empresa", empresa);
		return query.getResultList();
	}
	
	@Override
	public Collection<Articulo> articulosOrderCategoria(Empresa empresa) {
		Query query = entityManager.createQuery("select obj from Articulo obj where  obj.empresa = :empresa order by obj.categoria.id,obj.descripcion");
		query.setParameter("empresa", empresa);
		return query.getResultList();
	}

	@Override
	public Collection<Articulo> findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(Empresa empresa,Categoria categoria, String estado, String minimoMaximo){
		String sql = Constantes.EMPTY;
		sql = "select obj from Articulo obj where obj.categoria = :categoria and obj.empresa = :empresa ";
		if(minimoMaximo.equals(Constantes.ARTICULO_MINIMO)) {
			sql = sql + " and obj.cantidad <= obj.minimo ";
		}else if(minimoMaximo.equals(Constantes.ARTICULO_MAXIMO)) {
			sql = sql + "and obj.cantidad <= obj.minimo ";
		}
		if(!estado.equals(Constantes.COMBO_TODOS)) {
			sql = sql  + "and  obj.categoria = :categoria ";
		}
		sql = sql + " order by obj.categoria.id,obj.descripcion";

		Query query = entityManager.createQuery(sql);
		if(!estado.equals(Constantes.COMBO_TODOS)) {
			query.setParameter("estado", estado);
		}

		query.setParameter("categoria", categoria);
		query.setParameter("empresa", empresa);
		return query.getResultList();
		
	}
	

}
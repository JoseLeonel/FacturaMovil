package com.emprendesoftcr.Dao.Impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Mantenimiento del kardex de la trazabilidad del inventario KardexDaoImpl.
 * @author jose.
 * @since 12 abr. 2018
 */
@Repository("kardexDao")
public class KardexDaoImpl implements KardexDao {

	private Logger			log	= LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	EntityManager				entityManager;

	@Autowired
	private ArticuloDao	articuloDao;

	@Override
	public void agregar(Kardex kardex) {
		entityManager.persist(kardex);
	}

	/**
	 * Registrar una salida
	 * @throws Exception
	 * @see com.emprendesoftcr.Dao.KardexDao#salida(com.emprendesoftcr.modelo.Inventario, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.emprendesoftcr.modelo.Usuario)
	 */
	@Override
	public void salida(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) throws Exception {
		try {
			Double costoNuevo = articuloDao.getTotalCosto(articulo, cantidadActual - cantidadNueva);
			Kardex kardex = new Kardex();
			kardex.setCantidadSolicitada(cantidadNueva);
			kardex.setCantidadActual(cantidadActual);
			kardex.setCostoActual(articulo.getCosto());
			kardex.setTotalCostoActual(articuloDao.getTotalCosto(articulo, cantidadActual));
			kardex.setCodigo(articulo.getCodigo());
			kardex.setObservacion(observacion);
			kardex.setCantidadNueva(cantidadActual - cantidadNueva);
			kardex.setCostoNuevo(articulo.getCosto());
			kardex.setTotalCostoNuevo(costoNuevo);
			kardex.setConsecutivo(consecutivo);
			kardex.setTipo(tipo);
			kardex.setMotivo(motivo);
			kardex.setCreated_at(new Date());
			kardex.setUpdated_at(new Date());
			kardex.setUsuario(usuarioSesion);
			kardex.setArticulo(articulo);
			articulo.setCantidad(cantidadActual - cantidadNueva);
			articuloDao.modificar(articulo);
			agregar(kardex);
			

		} catch (Exception e) {
			log.info("** Error  salida kardex: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	/**
	 * Registrar una entrada
	 * @throws Exception
	 * @see com.emprendesoftcr.Dao.KardexDao#entrada(com.emprendesoftcr.modelo.Inventario, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.emprendesoftcr.modelo.Usuario)
	 */
	@Override
	public void entrada(Articulo articulo, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) throws Exception {
		try {
			Kardex kardex = new Kardex();
			Double costoNuevo = articuloDao.getTotalCosto(articulo, cantidadNueva + cantidadActual);
			kardex.setCantidadSolicitada(cantidadNueva);
			kardex.setCantidadActual(cantidadActual);
			kardex.setCostoActual(articulo.getCosto());
			kardex.setTotalCostoActual(articuloDao.getTotalCosto(articulo, cantidadActual));
			kardex.setCodigo(articulo.getCodigo());
			kardex.setObservacion(observacion);
			kardex.setCantidadNueva(cantidadNueva + cantidadActual);
			kardex.setCostoNuevo(articulo.getCosto());
			kardex.setTotalCostoNuevo(costoNuevo);
			kardex.setConsecutivo(consecutivo);
			kardex.setTipo(tipo);
			kardex.setMotivo(motivo);
			kardex.setCreated_at(new Date());
			kardex.setUpdated_at(new Date());
			kardex.setUsuario(usuarioSesion);
			kardex.setArticulo(articulo);
			articulo.setCantidad(cantidadActual + cantidadNueva);
			articuloDao.modificar(articulo);
			agregar(kardex);
			

		} catch (Exception e) {
			log.info("** Error  entrada kardex: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}
}
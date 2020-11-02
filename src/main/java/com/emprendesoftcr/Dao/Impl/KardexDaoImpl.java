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
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.Utils;
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
			Double resultadoCantidad = Utils.roundFactura(cantidadActual,3) - Utils.roundFactura(cantidadNueva,3);
			resultadoCantidad = resultadoCantidad < Constantes.ZEROS_DOUBLE?Constantes.ZEROS_DOUBLE:resultadoCantidad;
			Double costoNuevo = articuloDao.getTotalCosto(articulo, resultadoCantidad);
			Kardex kardex = new Kardex();
			kardex.setCantidadSolicitada(cantidadNueva);
			kardex.setCantidadActual(cantidadActual);
			kardex.setCostoActual(articulo.getCosto());
			kardex.setTotalCostoActual(articuloDao.getTotalCosto(articulo, cantidadActual));
			kardex.setCodigo(articulo.getCodigo());
			kardex.setObservacion(observacion);
			kardex.setCantidadNueva(resultadoCantidad);
			kardex.setCostoNuevo(articulo.getCosto());
			kardex.setTotalCostoNuevo(costoNuevo);
			kardex.setConsecutivo(consecutivo);
			kardex.setTipo(tipo);
			kardex.setMotivo(motivo);
			kardex.setCreated_at(new Date());
			kardex.setUpdated_at(new Date());
			kardex.setUsuario(usuarioSesion);
			kardex.setArticulo(articulo);
			articulo.setCantidad(resultadoCantidad);
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
			Double resultado = Utils.roundFactura(cantidadNueva + cantidadActual,3);
			Kardex kardex = new Kardex();
			Double costoNuevo = articuloDao.getTotalCosto(articulo, resultado);
			kardex.setCantidadSolicitada(cantidadNueva);
			kardex.setCantidadActual(cantidadActual);
			kardex.setCostoActual(articulo.getCosto());
			kardex.setTotalCostoActual(articuloDao.getTotalCosto(articulo, cantidadActual));
			kardex.setCodigo(articulo.getCodigo());
			kardex.setObservacion(observacion);
			kardex.setCantidadNueva(resultado);
			kardex.setCostoNuevo(articulo.getCosto());
			kardex.setTotalCostoNuevo(costoNuevo);
			kardex.setConsecutivo(consecutivo);
			kardex.setTipo(tipo);
			kardex.setMotivo(motivo);
			kardex.setCreated_at(new Date());
			kardex.setUpdated_at(new Date());
			kardex.setUsuario(usuarioSesion);
			kardex.setArticulo(articulo);
			articulo.setCantidad(resultado);
			articuloDao.modificar(articulo);
			agregar(kardex);
			

		} catch (Exception e) {
			log.info("** Error  entrada kardex: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public void entradaCosto(Articulo articulo, Double costoNuevo, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) throws Exception {
		try {
			Double cantidadActualProducto = articulo.getCantidad() == null?Constantes.ZEROS_DOUBLE:articulo.getCantidad();
			Double resultado = Utils.roundFactura(cantidadNueva + articulo.getCantidad(),3);
			Double costoPromedio =articuloDao.costoPromedio(articulo.getCosto(), costoNuevo, cantidadActualProducto, cantidadNueva); 
			Double costoArticuloActual = articulo.getCosto() == null? Constantes.ZEROS_DOUBLE:articulo.getCosto();
			
			Kardex kardex = new Kardex();
			Double costoTotalNuevo = resultado * costoPromedio;
			kardex.setCantidadSolicitada(cantidadNueva);
			kardex.setCantidadActual(cantidadActualProducto);
			kardex.setCostoActual(articulo.getCosto());
			kardex.setTotalCostoActual(articulo.getCosto() !=null?costoArticuloActual * cantidadActualProducto:Constantes.ZEROS_DOUBLE);
			kardex.setCodigo(articulo.getCodigo());
			kardex.setObservacion(observacion);
			kardex.setCantidadNueva(resultado);
			kardex.setCostoNuevo(costoNuevo);
			kardex.setTotalCostoNuevo(costoTotalNuevo);
			kardex.setConsecutivo(consecutivo);
			kardex.setTipo(tipo);
			kardex.setMotivo(motivo);
			kardex.setCreated_at(new Date());
			kardex.setUpdated_at(new Date());
			kardex.setUsuario(usuarioSesion);
			kardex.setArticulo(articulo);
			articulo.setCantidad(resultado);
			articulo.setCosto(costoPromedio);
			articuloDao.modificar(articulo);
			agregar(kardex);
			
		} catch (Exception e) {
			log.info("** Error  entrada kardex: " + e.getMessage() + " fecha " + new Date()+" Codigo:"+ articulo.getCodigo());
			throw e;
		}
		
		
	}

	
	
	
}
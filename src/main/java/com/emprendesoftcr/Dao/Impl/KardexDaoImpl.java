package com.emprendesoftcr.Dao.Impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.Dao.InventarioDao;
import com.emprendesoftcr.Dao.KardexDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Inventario;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Mantenimiento del kardex de la trazabilidad del inventario KardexDaoImpl.
 * @author jose.
 * @since 12 abr. 2018
 */
@Repository("kardexDao")
public class KardexDaoImpl implements KardexDao {

	@PersistenceContext
	EntityManager	entityManager;

	@Autowired
	InventarioDao	inventarioDao;
	
	@Autowired
	ArticuloDao	articuloDao;


	public void agregar(Kardex kardex) {
		entityManager.persist(kardex);
	}

	/**
	 * Registrar una salida
	 * @see com.emprendesoftcr.Dao.KardexDao#salida(com.emprendesoftcr.modelo.Inventario, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.emprendesoftcr.modelo.Usuario)
	 */
	@Override
	public void salida(Inventario inventario, Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {
    Double costoNuevo = inventarioDao.getTotalCosto(inventario, cantidadActual - cantidadNueva);
		Kardex kardex = new Kardex();
		kardex.setCantidadSolicitada(cantidadNueva);
		kardex.setCantidadActual(cantidadActual);
		kardex.setCostoActual(inventario.getArticulo().getCosto());
		kardex.setTotalCostoActual(inventarioDao.getTotalCosto(inventario, cantidadActual));
		kardex.setCodigo(inventario.getArticulo().getCodigo());
		kardex.setObservacion(observacion);
		kardex.setCantidadNueva(cantidadActual- cantidadNueva);
		kardex.setCostoNuevo(inventario.getArticulo().getCosto());
		kardex.setTotalCostoNuevo(costoNuevo);
		kardex.setConsecutivo(consecutivo);
		kardex.setTipo(tipo);
		kardex.setMotivo(motivo);
		kardex.setCreated_at(new Date());
		kardex.setUpdated_at(new Date());
		kardex.setUsuario(usuarioSesion);
		kardex.setArticulo(inventario.getArticulo());
		inventario.setCantidad(cantidadActual-cantidadNueva);
		inventarioDao.modificar(inventario);
		Articulo articulo = articuloDao.buscar(inventario.getArticulo().getId());
		articulo.addKardex(kardex);
		articuloDao.modificar(articulo);
		
	}

	/**
	 * Registrar una entrada
	 * @see com.emprendesoftcr.Dao.KardexDao#entrada(com.emprendesoftcr.modelo.Inventario, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.emprendesoftcr.modelo.Usuario)
	 */
	@Override  
	public void entrada(Inventario inventario,Double cantidadActual, Double cantidadNueva, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {
		Kardex kardex = new Kardex();
		
		Double costoNuevo = inventarioDao.getTotalCosto(inventario, cantidadNueva + cantidadActual);
		
		kardex.setCantidadSolicitada(cantidadNueva);
		kardex.setCantidadActual(cantidadActual);
		kardex.setCostoActual(inventario.getArticulo().getCosto());
		kardex.setTotalCostoActual(inventarioDao.getTotalCosto(inventario, cantidadActual));
		kardex.setCodigo(inventario.getArticulo().getCodigo());
		kardex.setObservacion(observacion);
		kardex.setCantidadNueva(cantidadNueva +  cantidadActual);
		kardex.setCostoNuevo(inventario.getArticulo().getCosto());
		kardex.setTotalCostoNuevo(costoNuevo);
		kardex.setConsecutivo(consecutivo);
		kardex.setTipo(tipo);
		kardex.setMotivo(motivo);
		kardex.setCreated_at(new Date());
		kardex.setUpdated_at(new Date());
		kardex.setUsuario(usuarioSesion);
		kardex.setArticulo(inventario.getArticulo());
		inventario.setCantidad(cantidadActual + cantidadNueva );
		inventarioDao.modificar(inventario);
		Articulo articulo = articuloDao.buscar(inventario.getArticulo().getId());
		articulo.addKardex(kardex);
		articuloDao.modificar(articulo);

	}
}
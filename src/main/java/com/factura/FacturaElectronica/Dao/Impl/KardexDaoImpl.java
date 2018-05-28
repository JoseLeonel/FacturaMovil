package com.factura.FacturaElectronica.Dao.Impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.factura.FacturaElectronica.Dao.InventarioDao;
import com.factura.FacturaElectronica.Dao.KardexDao;
import com.factura.FacturaElectronica.modelo.Inventario;
import com.factura.FacturaElectronica.modelo.Kardex;
import com.factura.FacturaElectronica.modelo.Usuario;

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

	public void agregar(Kardex kardex) {
		entityManager.persist(kardex);
	}

	/**
	 * Registrar una salida
	 * @see com.factura.FacturaElectronica.Dao.KardexDao#salida(com.factura.FacturaElectronica.modelo.Inventario, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.factura.FacturaElectronica.modelo.Usuario)
	 */
	@Override
	public void salida(Inventario inventario, Double cantidad, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {

		Kardex kardex = new Kardex();
		kardex.setCantidadSolicitada(cantidad);
		kardex.setCantidadActual(inventario.getCantidad());
		kardex.setCostoActual(inventario.getArticulo().getCosto());
		kardex.setTotalCostoActual(inventarioDao.getTotalCosto(inventario, inventario.getCantidad()));
		kardex.setCodigo(inventario.getArticulo().getCodigo());
		kardex.setObservacion(observacion);
		kardex.setCantidadNueva(inventario.getCantidad() - cantidad);
		kardex.setCostoNuevo(inventario.getArticulo().getCosto());
		kardex.setTotalCostoNuevo(inventarioDao.getTotalCosto(inventario, inventario.getCantidad() - cantidad));
		kardex.setConsecutivo(consecutivo);
		kardex.setTipo(tipo);
		kardex.setMotivo(motivo);
		kardex.setCreated_at(new Date());
		kardex.setUpdated_at(new Date());
		kardex.setUsuario(usuarioSesion);
		inventario.addKardex(kardex);
		inventarioDao.modificar(inventario);
	}

	/**
	 * Registrar una entrada
	 * @see com.factura.FacturaElectronica.Dao.KardexDao#entrada(com.factura.FacturaElectronica.modelo.Inventario, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.factura.FacturaElectronica.modelo.Usuario)
	 */
	@Override
	public void entrada(Inventario inventario, Double cantidad, String observacion, String consecutivo, String tipo, String motivo, Usuario usuarioSesion) {
		Kardex kardex = new Kardex();
		kardex.setCantidadSolicitada(cantidad);
		kardex.setCantidadActual(inventario.getCantidad());
		kardex.setCostoActual(inventario.getArticulo().getCosto());
		kardex.setTotalCostoActual(inventarioDao.getTotalCosto(inventario, inventario.getCantidad()));
		kardex.setCodigo(inventario.getArticulo().getCodigo());
		kardex.setObservacion(observacion);
		kardex.setCantidadNueva(cantidad + inventario.getCantidad());
		kardex.setCostoNuevo(inventario.getArticulo().getCosto());
		kardex.setTotalCostoNuevo(inventarioDao.getTotalCosto(inventario, cantidad + inventario.getCantidad()));
		kardex.setConsecutivo(consecutivo);
		kardex.setTipo(tipo);
		kardex.setMotivo(motivo);
		kardex.setCreated_at(new Date());
		kardex.setUpdated_at(new Date());
		kardex.setUsuario(usuarioSesion);
		inventario.addKardex(kardex);
		inventarioDao.modificar(inventario);
	}
}
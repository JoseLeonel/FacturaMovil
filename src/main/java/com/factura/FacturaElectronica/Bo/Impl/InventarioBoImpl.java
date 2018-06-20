package com.factura.FacturaElectronica.Bo.Impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.factura.FacturaElectronica.Bo.InventarioBo;
import com.factura.FacturaElectronica.Dao.InventarioDao;
import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Inventario;

/**
 * Control del inventario
 * InventarioBoImpl.
 * @author jose.
 * @since 13 abr. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("inventarioBo")
public class InventarioBoImpl implements InventarioBo {

	@Autowired
	InventarioDao inventarioDao;

	public void agregar(Inventario inventario) {
		inventarioDao.agregar(inventario);
	}

	public void modificar(Inventario inventario) {
		inventarioDao.modificar(inventario);
	}

	public void eliminar(Inventario inventario) {
		inventarioDao.eliminar(inventario);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.InventarioBo#buscar(java.lang.Integer)
	 */
	@Override
	public Inventario buscar(Integer id) {
		return inventarioDao.buscar(id);
	}
/**
 * Buscar por Articulo y sucursal
 * @see com.factura.bo.InventarioBo#buscarPorArticulo(com.factura.domain.Articulo, com.factura.domain.Sucursal)
 */
	@Override
	public Inventario buscarPorArticulo(Articulo articulo) {
		return inventarioDao.buscarPorArticulo(articulo);
	}

	/**
	 * Incrementa la cantidad del articulo
	 * @see com.factura.bo.InventarioBo#sumarCantidad(com.factura.domain.Inventario, java.lang.Float)
	 */
	@Override
	public BigDecimal sumarCantidad(Inventario inventario, BigDecimal cantidad) {
		inventario.setCantidad(inventario.getCantidad().add(cantidad));
		return inventario.getCantidad();
		
	}

	/**
	 * Restar la cantidad del inventario
	 * @see com.factura.bo.InventarioBo#restarCantidad(com.factura.domain.Inventario, java.lang.Float)
	 */
	@Override
	public BigDecimal restarCantidad(Inventario inventario, BigDecimal cantidad) {
		inventario.setCantidad(inventario.getCantidad().subtract(cantidad));
		return inventario.getCantidad();
		
	}
	

}
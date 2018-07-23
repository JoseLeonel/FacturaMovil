package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.InventarioBo;
import com.emprendesoftcr.Dao.InventarioDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Inventario;

/**
 * Control del inventario
 * InventarioBoImpl.
 * @author jose.
 * @since 13 abr. 2018
 */
@Lazy
@Transactional
@EnableTransactionManagement
@Service("inventarioBo")
public class InventarioBoImpl implements InventarioBo {

	@Lazy
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
	public Double sumarCantidad(Inventario inventario, Double cantidad) {
		inventario.setCantidad(inventario.getCantidad() +cantidad);
		return inventario.getCantidad();
		
	}

	/**
	 * Restar la cantidad del inventario
	 * @see com.factura.bo.InventarioBo#restarCantidad(com.factura.domain.Inventario, java.lang.Float)
	 */
	@Override
	public Double restarCantidad(Inventario inventario, Double cantidad) {
		inventario.setCantidad(inventario.getCantidad() - cantidad);
		return inventario.getCantidad();
		
	}
	

}
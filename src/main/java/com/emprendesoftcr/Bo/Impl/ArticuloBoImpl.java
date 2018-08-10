package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Empresa;

/**
 * ArticuloBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("articuloBo")
public class ArticuloBoImpl implements ArticuloBo {

	@Autowired
	ArticuloDao articuloDao;

	public void agregar(Articulo articulo) {
		
		articuloDao.agregar(articulo);
	}

	public void modificar(Articulo articulo) {
		articuloDao.modificar(articulo);
	}

	public void eliminar(Articulo articulo) {
		articuloDao.eliminar(articulo);
	}

	/**
	 * Busca la Articulo por descripcion y empresa
	 * @see com.factura.bo.ArticuloBo#buscarByDescripcionAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Articulo buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa) {
		return articuloDao.buscarPorDescripcionYEmpresa(descripcion, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.ArticuloBo#buscar(java.lang.Integer)
	 */
	@Override
	public Articulo buscar(Long id) {
		return articuloDao.buscar(id);
	}

	/**
	 * Busqueda de Articulo por codigo y empresa
	 * @see com.factura.bo.ArticuloBo#buscarByCodigoAndEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Articulo buscarPorCodigoYEmpresa(String codigo, Empresa empresa) {
		return articuloDao.buscarPorCodigoYEmpresa(codigo, empresa);
	}

	/**
	 * Obtener la Ganancia
	 * @see com.emprendesoftcr.Bo.ArticuloBo#porcentanjeDeGanancia(java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public Double porcentanjeDeGanancia(Double costo, Double iva, Double precio) {
		return articuloDao.porcentanjeDeGanancia(costo, iva, precio);
	}

	
	@Override
	public Double sumarCantidad(Articulo articulo, Double cantidad) {
		articulo.setCantidad(articulo.getCantidad() +cantidad);
		return articulo.getCantidad();
		
	}
	
	@Override
	public Double restarCantidad(Articulo articulo, Double cantidad) {
		articulo.setCantidad(articulo.getCantidad() - cantidad);
		return articulo.getCantidad();
		
	}
	
}
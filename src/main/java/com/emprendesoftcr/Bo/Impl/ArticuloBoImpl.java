package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.web.command.TotalInventarioCommand;

/**
 * ArticuloBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */

@EnableTransactionManagement
@Service("articuloBo")
public class ArticuloBoImpl implements ArticuloBo {

	private Logger	log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	ArticuloDao			articuloDao;

	@Transactional
	public void agregar(Articulo articulo) {

		articuloDao.agregar(articulo);
	}

	@Transactional
	public void modificar(Articulo articulo) {
		articuloDao.modificar(articulo);
	}

	@Transactional
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
	 * @throws Exception
	 * @see com.emprendesoftcr.Bo.ArticuloBo#porcentanjeDeGanancia(java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public Double porcentanjeDeGanancia(Double costo, Double iva, Double precio) throws Exception {
		try {
			return articuloDao.porcentanjeDeGanancia(costo, iva, precio);
		} catch (Exception e) {
			log.info("** Error  porcentanjeDeGanancia: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public Double sumarCantidad(Articulo articulo, Double cantidad) throws Exception {
		try {
			articulo.setCantidad(articulo.getCantidad() + cantidad);
			return articulo.getCantidad();

		} catch (Exception e) {
			log.info("** Error  sumarCantidad: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}

	}

	@Override
	public Double restarCantidad(Articulo articulo, Double cantidad) throws Exception {
		try {
			articulo.setCantidad(articulo.getCantidad() - cantidad);
			return articulo.getCantidad();

		} catch (Exception e) {
			log.info("** Error  restarCantidad: " + e.getMessage() + " fecha " + new Date());
			throw e;
		}
	}

	@Override
	public TotalInventarioCommand sumarInventarios(Integer idEmpresa,Date fechaInicial,Date FechaFinal) {
		return articuloDao.sumarInventarios(idEmpresa,fechaInicial,FechaFinal);
	}

	@Override
	public Collection<Articulo> articulosBy(Empresa empresa) {
		return articuloDao.articulosBy(empresa);
	}
	@Override
	public Collection<Articulo> articulosOrderCategoria(Empresa empresa) {
		return articuloDao.articulosOrderCategoria(empresa);
	}

	@Override
	public Collection<Articulo> findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(Empresa empresa, Categoria categoria, String estado, String minimoMaximo){
		return articuloDao.findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(empresa, categoria, estado,  minimoMaximo);
	}

	@Override
	public Collection<Articulo> articulosByCategoriaAndEmpresa(Integer idEmpresa, Long idCategoria) {
		
		return articuloDao.articulosByCategoriaAndEmpresa(idEmpresa, idCategoria);
	}
	
}
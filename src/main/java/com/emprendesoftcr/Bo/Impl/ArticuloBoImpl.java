package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Dao.ArticuloDao;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.utils.Constantes;
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
	@Autowired
  public DataSource dataSource;
	
  private JdbcTemplate jdbcTemplate;
  
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
	
	
	@Override
	public List<Map<String, Object>>  articulosByCabys(String descripcion ,String codigo,Integer tipo,Integer idEmpresa,Integer cantidad){
		jdbcTemplate = new JdbcTemplate(dataSource);
    String sql = "SELECT a.id,c.descripcion nomb_cate,a.codigo,a.cod_cabys,a.descripcion,a.impuesto,a.estado FROM articulos a\n" + 
    		"              inner join categorias c on c.id = a.categoria_id\n" + 
    		"              where a.empresa_id = :idEmpresa and a.descripcion like and a.codigo like and a.cod_cabys = '' "
    		+ " limit cantidad";
    
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("idEmpresa", idEmpresa);
    descripcion = descripcion == null ? Constantes.EMPTY:descripcion;
    tipo = tipo == null?Constantes.ZEROS:tipo;
    codigo = codigo == null? Constantes.EMPTY:codigo;
  	if ( descripcion.equals(Constantes.EMPTY)) {
  		sql = sql.replaceAll(" and a.descripcion like", "");
		} else {
			sql = sql.replaceAll(" and a.descripcion like", " and a.descripcion like '%" +descripcion +  "%'");
		}
  	if (codigo.equals(Constantes.EMPTY)) {
  		sql = sql.replaceAll(" and a.codigo like", "");
		} else {
			sql = sql.replaceAll(" and a.codigo like", " and a.codigo like '%" +codigo +  "%'");
		}
  	if(tipo.equals(1)) {
  		sql = sql.replaceAll(" and a.cod_cabys = ''", " and a.cod_cabys =''");
  	}
  	if(tipo.equals(2)) {
  		sql = sql.replaceAll(" and a.cod_cabys = ''", " and a.cod_cabys !=''");
  	}
  	if(tipo.equals(0)) {
  		sql = sql.replaceAll(" and a.cod_cabys = ''", "");
  	}
  	cantidad = cantidad == null?Constantes.ZEROS:cantidad;
  	if(cantidad.equals(Constantes.ZEROS)) {
  		sql = sql.replaceAll(" limit cantidad", "");
  	}else {
  		sql = sql.replaceAll(" limit cantidad", " limit " + cantidad);
  	}
  	
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( jdbcTemplate );
    List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sql, parameters);  
		
		return listaObjetos;
	}

	@Override
	public Articulo buscarPorCodigoSecundarioYEmpresa(String codigo, Empresa empresa) {
		
		return articuloDao.buscarPorCodigoSecundarioYEmpresa(codigo, empresa);
	}

	
	
	
}
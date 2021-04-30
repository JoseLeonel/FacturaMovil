package com.emprendesoftcr.Dao.Impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.ProveedorArticuloDao;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

@Repository("proveedorArticuloDao")
public class ProveedorArticuloDaoImpl implements ProveedorArticuloDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
  public DataSource dataSource;
	
  private JdbcTemplate jdbcTemplate;

	@Override
	public void agregar(ProveedorArticulo proveedorArticulo) {
		entityManager.persist(proveedorArticulo);

	}

	@Override
	public void modificar(ProveedorArticulo proveedorArticulo) {
		entityManager.merge(proveedorArticulo);

	}

	@Override
	public void eliminar(ProveedorArticulo proveedorArticulo) {
		entityManager.remove(proveedorArticulo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ProveedorArticulo findById(Long id) {
		Query query = entityManager.createQuery("select obj from ProveedorArticulo obj where obj.id = :id");
		query.setParameter("id", id);
		List<ProveedorArticulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ProveedorArticulo) results.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProveedorArticulo findByCodigo(String codigo, Proveedor proveedor) {
		Query query = entityManager.createQuery("select obj from ProveedorArticulo obj where obj.codigoProveedor = :codigo and obj.proveedor = :proveedor");
		query.setParameter("proveedor", proveedor);
		query.setParameter("codigo",codigo);
		List<ProveedorArticulo> results = query.getResultList();
		if (!results.isEmpty()) {
			return (ProveedorArticulo) results.get(0);
		} else {
			return null;
		}

	}
	
	@Override
	public List<Map<String, Object>>   articuloPorProveedor(Integer idProveedor) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlNative = "Select p.id,p.cedula,p.nombre_completo as nombreCompleto,a.descripcion,a.costo as costoInventario,a.cantidad,\n" + 
				"       a.codigo, pa.costo as costoProveedor ,a.ganancia_precio_publico as ganancia, \n" + 
				"       a.precio_publico  as precio from proveedores p \n" + 
				"       INNER join proveedor_articulo pa on pa.proveedor_id = p.id \n" + 
				"       inner join articulos a on a.id = pa.articulo_id "
				+ " where p.id = :idProveedor";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("idProveedor", idProveedor);
    
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( jdbcTemplate );
    List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sqlNative, parameters);  
		
		return listaObjetos;
	}
	
	
	@Override
	public List<Map<String, Object>>   articuloPorProveedor(Integer idProveedor,String codigo,Integer idEmpresa) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlNative = "Select p.id,p.cedula,p.nombre_completo as nombreCompleto,a.descripcion,a.costo as costoInventario,a.cantidad,\n" + 
				"       a.codigo, pa.costo as costoProveedor ,a.ganancia_precio_publico as ganancia, \n" + 
				"       a.precio_publico  as precio from articulos a \n" + 
				"       INNER join proveedor_articulo pa on pa.articulo_id = a.id \n" + 
				"       INNER join proveedores p on p.id = pa.proveedor_id\n" + 
				"       where a.empresa_id = :idEmpresa and a.codigo = :idArticulo and p.id != :idProveedor";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("idProveedor", idProveedor);
    parameters.addValue("idEmpresa", idEmpresa);
    parameters.addValue("idArticulo", codigo);
    
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( jdbcTemplate );
    List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sqlNative, parameters);  
		
		return listaObjetos;
	}
	
	@Override
	public List<Map<String, Object>> articuloCantidadVendido(String idCodigo,Integer idEmpresa,String fechaInicial,String fechaFinal) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlNative = "SELECT DAYOFWEEK(d.created_at) as diaSemana\n" + 
				", sum(IFNULL(if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.monto_total_linea * f.tipo_cambio * -1,d.monto_total_linea * f.tipo_cambio), 0)) as totalVenta\n" + 
				", sum(IFNULL(if(f.tipo_doc = '03' or f.tipo_doc = '86' ,d.cantidad * -1,d.cantidad), 0)) as cantidad\n" + 
				"FROM detalles d \n" + 
				"inner join facturas f on f.id = d.factura_id \n" + 
				"where d.codigo = :idCodigo and f.empresa_id = :idEmpresa and f.estado in(2,7,6) and "
				+ "d.created_at >= :fechaInicial  and d.created_at <= :fechaFinal GROUP BY DAYOFWEEK(d.created_at)";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("idCodigo", idCodigo);
    parameters.addValue("idEmpresa", idEmpresa);
    parameters.addValue("fechaInicial", fechaInicial);
    parameters.addValue("fechaFinal", fechaFinal);
    
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( jdbcTemplate );
    List<Map<String, Object>> listaObjetos = namedParameterJdbcTemplate.queryForList(sqlNative, parameters);  
		
		return listaObjetos;
	}

}

package com.emprendesoftcr.Dao;

import java.util.List;
import java.util.Map;

import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

public interface ProveedorArticuloDao {
	
	void agregar(ProveedorArticulo proveedorArticulo);

	void modificar(ProveedorArticulo proveedorArticulo);

	void eliminar(ProveedorArticulo proveedorArticulo);

	ProveedorArticulo findById(Long id);
	
	ProveedorArticulo  findByCodigo(String codigo, Proveedor proveedor);
	List<Map<String, Object>>   articuloPorProveedor(Integer idProveedor);
	List<Map<String, Object>>   articuloPorProveedor(Integer idProveedor,String codigo,Integer idEmpresa);
	List<Map<String, Object>> articuloCantidadVendido(String idCodigo,Integer idEmpresa,String fechaInicial,String fechaFinal);
}

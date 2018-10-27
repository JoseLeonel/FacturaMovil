package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

public interface ProveedorArticuloDao {
	
	void agregar(ProveedorArticulo proveedorArticulo);

	void modificar(ProveedorArticulo proveedorArticulo);

	void eliminar(ProveedorArticulo proveedorArticulo);

	ProveedorArticulo findById(Long id);
	
	ProveedorArticulo findByCodigo(Articulo articulo , Proveedor proveedor);

}

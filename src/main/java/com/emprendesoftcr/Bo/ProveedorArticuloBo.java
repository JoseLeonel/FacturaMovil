package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.ProveedorArticulo;

public interface ProveedorArticuloBo {

	void agregar(ProveedorArticulo proveedorArticulo);

	void modificar(ProveedorArticulo proveedorArticulo);

	void eliminar(ProveedorArticulo proveedorArticulo);

	ProveedorArticulo findById(Long id);

	ProveedorArticulo findByCodigo(String codigo, Proveedor proveedor);

}

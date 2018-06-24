package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Inventario;

public interface InventarioDao {

	void agregar(Inventario inventario);

	void modificar(Inventario inventario);

	void eliminar(Inventario inventario);

	Inventario buscar(Integer id);

	Inventario buscarPorArticulo(Articulo articulo);
	Inventario findByArticuloAndEstado(Articulo articulo,String estado);
	Double getTotalCosto(Inventario inventario, Double cantidad);

	

}
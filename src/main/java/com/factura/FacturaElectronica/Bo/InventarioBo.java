package com.factura.FacturaElectronica.Bo;

import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Inventario;

public interface InventarioBo {

	void agregar(Inventario inventario);

	void modificar(Inventario inventario);

	void eliminar(Inventario inventario);

	Inventario buscar(Integer id);

	Inventario buscarPorArticulo(Articulo articulo);

	Double sumarCantidad(Inventario inventario, Double cantidad);

	Double restarCantidad(Inventario inventario, Double cantidad);
}
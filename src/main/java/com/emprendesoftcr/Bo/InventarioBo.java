package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Inventario;

public interface InventarioBo {

	void agregar(Inventario inventario);

	void modificar(Inventario inventario);

	void eliminar(Inventario inventario);

	Inventario buscar(Long id);

	Inventario buscarPorArticulo(Articulo articulo);

	Double sumarCantidad(Inventario inventario, Double cantidad);

	Double restarCantidad(Inventario inventario, Double cantidad);
}
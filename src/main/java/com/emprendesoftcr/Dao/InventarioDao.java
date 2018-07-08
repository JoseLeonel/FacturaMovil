package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Inventario;

public interface InventarioDao {

	void agregar(Inventario inventario);

	void modificar(Inventario inventario);

	void eliminar(Inventario inventario);

	Inventario buscar(Integer id);

	Inventario buscarPorArticulo(Articulo articulo);
	Inventario findByArticuloAndEstado(Articulo articulo,String estado);
	Double getTotalCosto(Inventario inventario, Double cantidad);

	

}
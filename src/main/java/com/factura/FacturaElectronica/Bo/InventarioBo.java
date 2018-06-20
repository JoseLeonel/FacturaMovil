package com.factura.FacturaElectronica.Bo;

import java.math.BigDecimal;

import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Inventario;

public interface InventarioBo {

	void agregar(Inventario inventario);

	void modificar(Inventario inventario);

	void eliminar(Inventario inventario);

	Inventario buscar(Integer id);

	Inventario buscarPorArticulo(Articulo articulo);

	BigDecimal sumarCantidad(Inventario inventario, BigDecimal cantidad);

	BigDecimal restarCantidad(Inventario inventario, BigDecimal cantidad);
}
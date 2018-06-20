package com.factura.FacturaElectronica.Bo;

import java.math.BigDecimal;

import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Empresa;

public interface ArticuloBo {

	void agregar(Articulo articulo);

	void modificar(Articulo articulo);

	void eliminar(Articulo articulo);

	Articulo buscar(Integer id);

	Articulo buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

	Articulo buscarPorCodigoYEmpresa(String codigo, Empresa empresa);

	BigDecimal porcentanjeDeGanancia(BigDecimal costo, BigDecimal iva, BigDecimal precio);

}
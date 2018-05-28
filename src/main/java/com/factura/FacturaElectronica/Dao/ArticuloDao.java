package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Articulo;
import com.factura.FacturaElectronica.modelo.Empresa;

public interface ArticuloDao {

	void agregar(Articulo articulo);

	void modificar(Articulo articulo);

	void eliminar(Articulo articulo);

	Articulo buscar(Integer id);

	Articulo buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

	Articulo buscarPorCodigoYEmpresa(String codigo, Empresa empresa);
	
	Double porcentanjeDeGanancia(Double costo, Double iva, Double precio);
	
	Double costoPromedio(Double costoActual , Double costoNuevo,Double cantidadActual,Double cantidadNueva);

}
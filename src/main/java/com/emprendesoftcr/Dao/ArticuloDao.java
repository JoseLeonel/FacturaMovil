package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Empresa;

public interface ArticuloDao {

	void agregar(Articulo articulo);

	void modificar(Articulo articulo);

	void eliminar(Articulo articulo);

	Articulo buscar(Long id);

	Articulo buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

	Articulo buscarPorCodigoYEmpresa(String codigo, Empresa empresa);

	Double porcentanjeDeGanancia(Double costo, Double iva, Double precio) throws Exception;

	Double costoPromedio(Double costoActual, Double costoNuevo, Double cantidadActual, Double cantidadNueva) throws Exception;

	Double getTotalCosto(Articulo articulo, Double cantidad) throws Exception;
}
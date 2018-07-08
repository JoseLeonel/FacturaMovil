package com.emprendesoftcr.Bo;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Empresa;

public interface ArticuloBo {

	void agregar(Articulo articulo);

	void modificar(Articulo articulo);

	void eliminar(Articulo articulo);

	Articulo buscar(Integer id);

	Articulo buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

	Articulo buscarPorCodigoYEmpresa(String codigo, Empresa empresa);

	Double porcentanjeDeGanancia(Double costo, Double iva, Double precio);

}
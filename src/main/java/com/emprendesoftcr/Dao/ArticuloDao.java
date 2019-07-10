package com.emprendesoftcr.Dao;

import java.util.Collection;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.web.command.TotalInventarioCommand;

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

	TotalInventarioCommand sumarInventarios(Integer idEmpresa);

	Collection<Articulo> articulosBy(Empresa empresa);

	Collection<Articulo> articulosOrderCategoria(Empresa empresa);

	Collection<Articulo> findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(Empresa empresa,Categoria categoria, String estado, String minimoMaximo);
}
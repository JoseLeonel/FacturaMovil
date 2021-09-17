package com.emprendesoftcr.Bo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.TotalInventarioCommand;

public interface ArticuloBo {

	void agregar(Articulo articulo);

	void modificar(Articulo articulo);

	void eliminar(Articulo articulo);

	Articulo buscar(Long id);

	Articulo buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);

	Articulo buscarPorCodigoYEmpresa(String codigo, Empresa empresa);

	Double porcentanjeDeGanancia(Double costo, Double iva, Double precio) throws Exception;

	Double sumarCantidad(Articulo articulo, Double cantidad) throws Exception;

	Double restarCantidad(Articulo articulo, Double cantidad) throws Exception;

	TotalInventarioCommand sumarInventarios(Integer idEmpresa,Date fechaInicial,Date FechaFinal);
	

	Collection<Articulo> articulosBy(Empresa empresa);

	Collection<Articulo> articulosOrderCategoria(Empresa empresa);

	Collection<Articulo> findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(Empresa empresa, Categoria categoria, String estado, String minimoMaximo);
 
	Collection<Articulo> articulosByCategoriaAndEmpresa(Integer idEmpresa,Long idCategoria);
	
	List<Map<String, Object>>  articulosByCabys(String descripcion ,String codigo,Integer tipo,Integer idEmpresa,Integer cantidad);
	
	RespuestaServiceDataTable<?> listarByCodigoArticulo(HttpServletRequest request, HttpServletResponse response,  String codigoArt,String nombreUsuario);
	
	 RespuestaServiceValidator<?> modificar(HttpServletRequest request, Articulo articulo, BindingResult result);
}
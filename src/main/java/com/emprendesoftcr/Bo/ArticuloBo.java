package com.emprendesoftcr.Bo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.RomadaBalanza;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.CambiarPrecioArticuloCommand;
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

	TotalInventarioCommand sumarInventarios(Integer idEmpresa, Date fechaInicial, Date FechaFinal);

	Collection<Articulo> articulosBy(Empresa empresa);

	Collection<Articulo> articulosOrderCategoria(Empresa empresa);

	Collection<Articulo> findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(Empresa empresa, Categoria categoria, String estado, String minimoMaximo);

	Collection<Articulo> articulosByCategoriaAndEmpresa(Integer idEmpresa, Long idCategoria);

	List<Map<String, Object>> articulosByCabys(String descripcion, String codigo, Integer tipo, Integer idEmpresa, Integer cantidad);

	Articulo buscarPorCodigoSecundarioYEmpresa(String codigo, Empresa empresa);

	RespuestaServiceDataTable<?> listarByCodigoArticulo(HttpServletRequest request, HttpServletResponse response, String codigoArt, String nombreUsuario);

	RespuestaServiceValidator<?> modificar(HttpServletRequest request, Articulo articulo, BindingResult result);

	RespuestaServiceValidator<?> agregar(HttpServletRequest request, Articulo articulo, BindingResult result);

	RespuestaServiceValidator<?> cambiarPrecio(HttpServletRequest request, HttpServletResponse response, Articulo articulo, Double precioPublico, String codigo, String tipoImpuesto, Double impuesto, String descripcion, String tipoCodigo, String unidadMedida, BindingResult result);

	RespuestaServiceValidator<?> cambiarPrecioArticulo(HttpServletRequest request, HttpServletResponse response, ModelMap model, CambiarPrecioArticuloCommand cambiarPrecioArticuloCommand, BindingResult result);

	RespuestaServiceValidator<?> findArticuloByCodigojax(HttpServletRequest request, Articulo articulo, HttpServletResponse response, String codigoArticulo, BindingResult result);

	RespuestaServiceDataTable<?> listarByControlPrecioPendiente(HttpServletRequest request, HttpServletResponse response, Usuario usuario, String fechaInicioParam, String fechaFinParam, String codigoArticulo);

	public RespuestaServiceValidator<?> findBalanzaByEmpresajax(HttpServletRequest request, RomadaBalanza romadaBalanza,
				HttpServletResponse response, BindingResult result);
}
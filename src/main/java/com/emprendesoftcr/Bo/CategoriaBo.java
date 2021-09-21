package com.emprendesoftcr.Bo;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.RespuestaServiceValidator;

public interface CategoriaBo {

	void agregar(Categoria categoria);

	void modificar(Categoria categoria);

	void eliminar(Categoria categoria);

	Categoria buscar(Long id);

	Categoria buscarPorDescripcionYEmpresa(String descripcion, Empresa empresa);
	
	Collection<Categoria> findByEmpresaAll(Integer idEmpresa);
	
	RespuestaServiceValidator<?> agregar(HttpServletRequest request,   Categoria categoria, BindingResult result,Usuario usuario) ;
	
	RespuestaServiceValidator<?> modificar(HttpServletRequest request, Categoria categoria, BindingResult result,Usuario usuario);
	RespuestaServiceValidator<?> mostrar(HttpServletRequest request,  Categoria categoria, BindingResult result); 
}
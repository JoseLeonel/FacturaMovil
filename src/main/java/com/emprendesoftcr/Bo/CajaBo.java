package com.emprendesoftcr.Bo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.RespuestaServiceValidator;

public interface CajaBo {

	void agregar(Caja caja);

	void modificar(Caja caja);

	void eliminar(Caja caja);

	Caja buscar(Long id);
	
	Caja findByDescripcionAndEmpresa(String descripcion, Empresa empresa);

	Caja findByTerminalAndEmpresa(String terminal, Empresa empresa);
	Caja buscarCajaActiva(Empresa empresa,Usuario usuario); 
	RespuestaServiceValidator<?> agregar(HttpServletRequest request,   Caja caja, BindingResult result);
	 RespuestaServiceValidator<?> modificar(HttpServletRequest request, Caja caja, BindingResult result);
	 
	 RespuestaServiceValidator<?> mostrar(HttpServletRequest request,  Caja caja);
}
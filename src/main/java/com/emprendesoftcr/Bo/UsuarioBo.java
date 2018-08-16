package com.emprendesoftcr.Bo;

import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;

public interface UsuarioBo {

	void agregar(Usuario usuario);

	void modificar(Usuario usuario);

	void eliminar(Usuario usuario);

	Usuario buscar(String usuarioQAM, Integer estado);

	Usuario buscar(String nombreUsuario);

	Usuario buscar(Integer id);
	Usuario buscar(String nombreUsuario,Empresa empresa);

	Usuario buscarPorNombreYPrimerApellidoYSegundoApellido(String nombre, String primerApellido, String segundoApellido);
	
	JqGridFilter filtroPorEmpresa(String nombreUsuario);

}
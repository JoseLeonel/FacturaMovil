package com.emprendesoftcr.Dao;

import com.emprendesoftcr.modelo.Usuario;


public interface UsuarioDao {

	void agregar(Usuario usuario);

	void modificar(Usuario usuario);

	void eliminar(Usuario usuario);

	Usuario buscar(String nombreUsuario, Integer estado);
	Usuario buscar(String nombreUsuario);
	
	Usuario buscar(Integer id);

	Usuario buscarPorNombreYPrimerApellidoYSegundoApellido(String nombre, String primerApellido, String segundoApellido);
	

}
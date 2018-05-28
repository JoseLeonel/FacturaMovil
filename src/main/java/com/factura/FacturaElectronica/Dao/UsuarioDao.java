package com.factura.FacturaElectronica.Dao;

import com.factura.FacturaElectronica.modelo.Usuario;


public interface UsuarioDao {

	void agregar(Usuario usuario);

	void modificar(Usuario usuario);

	void eliminar(Usuario usuario);

	Usuario buscar(String nombreUsuario, Integer estado);
	Usuario buscar(String nombreUsuario);
	
	Usuario buscar(Integer id);

	Usuario buscarPorNombreYPrimerApellidoYSegundoApellido(String nombre, String primerApellido, String segundoApellido);
	

}
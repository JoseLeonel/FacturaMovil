package com.emprendesoftcr.service;

import java.util.List;
import java.util.Optional;

import com.emprendesoftcr.modelo.Usuario;

public interface UsuarioService {

	void saveUsuario(Usuario usuario);

	Optional<Usuario>  findByNombreUsuario(String nombreUsuario);

	List<Usuario> findAllUsuarios();

}

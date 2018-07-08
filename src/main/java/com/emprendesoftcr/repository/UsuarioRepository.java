package com.emprendesoftcr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emprendesoftcr.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario>  findByNombreUsuario(String nombreUsuario);
	

	

}

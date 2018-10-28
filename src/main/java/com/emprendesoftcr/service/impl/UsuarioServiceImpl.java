package com.emprendesoftcr.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.repository.UsuarioRepository;
import com.emprendesoftcr.service.UsuarioService;

@Service("usuarioService")
@EnableTransactionManagement
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	@Override
	public void saveUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);

	}

	@Override
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}

	@Override
	public List<Usuario> findAllUsuarios() {

		return (List<Usuario>) usuarioRepository.findAll();
	}

}

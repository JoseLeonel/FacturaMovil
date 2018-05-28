package com.factura.FacturaElectronica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.repository.UsuarioRepository;
import com.factura.FacturaElectronica.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	

	@Override
	public void saveUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);

	}

	@Override
	public Optional<Usuario>  findByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}

	@Override
	public List<Usuario> findAllUsuarios() {
		
		return (List<Usuario>) usuarioRepository.findAll();
	}

}

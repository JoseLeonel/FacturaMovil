package com.factura.FacturaElectronica.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.factura.FacturaElectronica.modelo.CustomUsuarioDetails;
import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.repository.UsuarioRepository;

@Service
public class CustomUsuariosDetailsService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByNombreUsuario(username);

		optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
	//	return new org.springframework.security.core.userdetails.User(optionalUsuario.map(CustomUsuarioDetails::new).get().getNombreUsuario(), optionalUsuario.map(CustomUsuarioDetails::new).get().getPassword(),optionalUsuario.map(CustomUsuarioDetails::new).get().getAuthorities());
		return optionalUsuario.map(CustomUsuarioDetails::new).get();
	}
}

package com.factura.FacturaElectronica.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.factura.FacturaElectronica.modelo.Usuario;
import com.factura.FacturaElectronica.service.UsuarioService;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Lista de los usuarios
	 * @return
	 */
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listUsuariosALL() {
		List<Usuario> usuarios = usuarioService.findAllUsuarios();
		if (usuarios.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Usuario>>(HttpStatus.OK);
		}
	}

}

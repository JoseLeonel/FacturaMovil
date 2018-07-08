package com.emprendesoftcr.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Usuario;

/**
 * UsuarioPropertyEditor. Objetivo Obtener el id en el modiifcar
 * @author Leonel Hernandez Chaverri.
 * @since 6 feb. 2018
 */
@Component
public class UsuarioPropertyEditor extends PropertyEditorSupport {

	@Autowired
	UsuarioBo usuarioBo;

	@Override
	public String getAsText() {
		Usuario usuario = (Usuario) getValue();
		return (usuario == null ? "" : usuario.getId().toString());
	}

	@Override
	public void setAsText(String idUsuario) throws IllegalArgumentException {
		if ((idUsuario == null) || !StringUtils.hasLength(idUsuario)) {
			setValue(null);
		} else {

			setValue(usuarioBo.buscar(Integer.parseInt(idUsuario)));

		}
	}

	
	public UsuarioBo getUsuarioBo() {
		return usuarioBo;
	}

	
	public void setUsuarioBo(UsuarioBo usuarioBo) {
		this.usuarioBo = usuarioBo;
	}
	
	
}

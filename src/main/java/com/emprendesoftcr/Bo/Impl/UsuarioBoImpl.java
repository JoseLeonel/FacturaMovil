package com.emprendesoftcr.Bo.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Dao.UsuarioDao;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.modelo.Usuario;

/**
 * Usuarios que pueden ingresar al sistema UsuarioBoImpl.
 * @author Leonel Hernandez Chaverri.
 * @since 16 ene. 2018
 */
@Transactional
@EnableTransactionManagement
@Service("usuarioBo")
public class UsuarioBoImpl implements UsuarioBo {

	@Autowired
	UsuarioDao usuarioDao;

	public void agregar(Usuario usuario) {
		usuarioDao.agregar(usuario);
	}

	public void modificar(Usuario usuario) {
		usuarioDao.modificar(usuario);
	}

	public void eliminar(Usuario usuario) {
		usuarioDao.eliminar(usuario);
	}

	/**
	 * Buscar por estado y usuario
	 * @see com.factura.bo.UsuarioBo#buscar(java.lang.String, java.lang.Integer)
	 */
	public Usuario buscar(String nombreUsuario, Integer estado) {
		return usuarioDao.buscar(nombreUsuario, estado);
	}

	/**
	 * Buscar por usuario
	 * @see com.factura.bo.UsuarioBo#buscar(java.lang.String)
	 */
	public Usuario buscar(String nombreUsuario) {
		return usuarioDao.buscar(nombreUsuario);
	};

	/**
	 * Buscar por nombre completo del usuario
	 * @see com.factura.bo.UsuarioBo#findByNombreAndPrimerApellidoAndSegundoApellido(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Usuario buscarPorNombreYPrimerApellidoYSegundoApellido(String nombre, String primerApellido, String segundoApellido) {
		return usuarioDao.buscarPorNombreYPrimerApellidoYSegundoApellido(nombre, primerApellido, segundoApellido);
	}
	/**
	 * Buscar por id
	 * @see com.factura.bo.UsuarioBo#buscar(java.lang.Integer)
	 */
	@Override
	public Usuario buscar(Integer id) {
		return usuarioDao.buscar(id);
	}
	
	/**
	 * Obtener la empresa asociada de un usuario que no es superadministrador
	 */
	@Override
	public JqGridFilter filtroPorEmpresa(String nombreUsuario) {
		   Usuario usuarioSesion = buscar(nombreUsuario); 
		  return  new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");

		
	}

}
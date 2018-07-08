package com.emprendesoftcr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.emprendesoftcr.Dao.UsuarioDao;
import com.emprendesoftcr.modelo.Usuario;

/**
 * usuarios que se registran en el sistema UsuarioDaoImpl.
 * @author jose.
 * @since 19 abr. 2018
 */
@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	EntityManager entityManager;

	public void agregar(Usuario usuario) {
		entityManager.persist(usuario);
	}

	public void modificar(Usuario usuario) {
		entityManager.merge(usuario);
	}

	public void eliminar(Usuario usuario) {
		entityManager.remove(usuario);
	}

	/**
	 * Buscar por codigo de usuario y estado
	 * @see com.factura.dao.UsuarioDao#buscar(java.lang.String, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Usuario buscar(String nombreUsuario, Integer estado) {
		Query query = entityManager.createQuery("select usuario from Usuario usuario where usuario.nombreUsuario = :nombreUsuario and usuario.estado = :estado");
		query.setParameter("nombreUsuario", nombreUsuario);
		query.setParameter("estado", estado);
		List<Usuario> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Usuario) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por codigo de usuario
	 * @see com.factura.dao.UsuarioDao#buscar(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Usuario buscar(String nombreUsuario) {
		Query query = entityManager.createQuery("select usuario from Usuario usuario where usuario.nombreUsuario = :nombreUsuario ");
		query.setParameter("nombreUsuario", nombreUsuario);
		List<Usuario> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Usuario) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Buscar por ID
	 * @see com.factura.dao.UsuarioDao#buscar(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public Usuario buscar(Integer id) {
		Query query = entityManager.createQuery("select obj from Usuario obj where obj.id = :id");
		query.setParameter("id", id);
		List<Usuario> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Usuario) results.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @see com.factura.dao.UsuarioDao#buscarPorNombreYPrimerApellidoYSegundoApellido(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Usuario buscarPorNombreYPrimerApellidoYSegundoApellido(String nombre, String primerApellido, String segundoApellido) {
		Query query = entityManager.createQuery("select usuario from Usuario usuario where usuario.nombre = :nombre and usuario.primerApellido = :primerApellido and usuario.segundoApellido = :segundoApellido");
		query.setParameter("nombre", nombre);
		query.setParameter("primerApellido", primerApellido);
		query.setParameter("segundoApellido", segundoApellido);
		List<Usuario> results = query.getResultList();
		if (!results.isEmpty()) {
			return (Usuario) results.get(0);
		} else {
			return null;
		}
	}

}
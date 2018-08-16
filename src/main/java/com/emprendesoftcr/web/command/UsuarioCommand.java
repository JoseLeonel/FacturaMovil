package com.emprendesoftcr.web.command;

import java.util.Set;

import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Rol;
import com.emprendesoftcr.modelo.Usuario;

/**
 * UsuarioCommand. Modelo para definir la presentacion en el front end
 * @author Leonel Hernandez Chaverri.
 * @since 12 ene. 2018
 */
public class UsuarioCommand {

	private Integer		id;
	private Integer		estado;
	private String		strEstado;
	private String		nombre;
	private String		nombreUsuario;
	private String		primerApellido;
	private String		segundoApellido;
	private String		password;
	private Empresa		empresa;
	private Set<Rol>	roles;;

	public UsuarioCommand(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.strEstado = usuario.getEstado().equals(Constantes.USUARIO_ESTADO_ACTIVO) ? Constantes.ESTADO_ACTIVO : Constantes.ESTADO_INACTIVO;
		this.nombre = usuario.getNombre();
		this.nombreUsuario = usuario.getNombreUsuario();
		this.primerApellido = usuario.getPrimerApellido();
		this.segundoApellido = usuario.getSegundoApellido();
		this.estado = usuario.getEstado();
		this.empresa = usuario.getEmpresa();
		this.roles = usuario.getRoles();
		this.password = usuario.getPassword();
	}

	public UsuarioCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getStrEstado() {
		return strEstado;
	}

	public void setStrEstado(String strEstado) {
		this.strEstado = strEstado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

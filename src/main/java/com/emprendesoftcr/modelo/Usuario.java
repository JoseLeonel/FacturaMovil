package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.utils.Constantes;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long	serialVersionUID	= 8841055274510897370L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "nombre_usuario")
	private String						nombreUsuario;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "nombre")
	private String						nombre;

	@Column(name = "primer_apellido")
	private String						primerApellido;

	@Column(name = "segundo_apellido")
	private String						segundoApellido;
	
	@Column(name = "terminal_facturar")
	private String						terminalFactura;

	@Column(name = "estado")
	private Integer						estado;
	@Column(name = "password")
	private String						password;
	@Column(name = "password_confirm")
	private String						passwordConfirm;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@ManyToOne 
	@JoinColumn(name = "empresa_id")
	private Empresa						empresa;

	@Column(name = "descuento_venta")
	private Double						descuentoVenta;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol>					roles;
	

	
	/*
	 * 	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinColumn(name = "user_id")
		@JsonBackReference
		private List<Role> roles;
	 * 
	 */

	
	public Usuario(Integer id, String nombreUsuario, String cedula, String nombre, String primerApellido, String segundoApellido, Integer estado, String password, String passwordConfirm, Date created_at, Date updated_at, Empresa empresa, Set<Rol> roles,String terminalFactura) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.cedula = cedula;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.estado = Constantes.USUARIO_ESTADO_ACTIVO;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.created_at = new Date();
		this.updated_at = new Date();
		this.empresa = empresa;
		this.roles = roles;
		this.terminalFactura = Constantes.TERMINAL_INICIAL_FACTURA;
	}

	public Usuario() {
		super();
		this.estado = Constantes.USUARIO_ESTADO_ACTIVO;
		this.created_at = new Date();
		this.updated_at = new Date();
	}

	public Usuario(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nombreUsuario = usuario.getNombreUsuario();
		this.cedula = usuario.getCedula();
		this.nombre = usuario.getNombre();
		this.primerApellido = usuario.getPrimerApellido();
		this.segundoApellido = usuario.getSegundoApellido();
		this.estado = usuario.getEstado();
		this.password = usuario.getPassword();
		this.passwordConfirm = usuario.getPasswordConfirm();
		this.empresa = usuario.getEmpresa();
		this.roles = usuario.getRoles();

	}
	



	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	
	public Date getCreated_at() {
		return created_at;
	}

	
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	
	public Date getUpdated_at() {
		return updated_at;
	}

	
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	
	public String getTerminalFactura() {
		return terminalFactura;
	}

	
	public void setTerminalFactura(String terminalFactura) {
		this.terminalFactura = terminalFactura;
	}

	
	public Double getDescuentoVenta() {
		return descuentoVenta;
	}

	
	public void setDescuentoVenta(Double descuentoVenta) {
		this.descuentoVenta = descuentoVenta;
	}

	
}
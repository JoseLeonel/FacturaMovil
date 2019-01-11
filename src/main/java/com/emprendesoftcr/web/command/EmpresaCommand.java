package com.emprendesoftcr.web.command;

import javax.persistence.Column;

import com.emprendesoftcr.modelo.Empresa;

public class EmpresaCommand {

	private Integer id;

	private String nombre;

	private String	nombreComercial;
	private Integer	numeroConsecutivo;

	private String tipoCedula;

	private String cedula;

	private String otraSenas;

	private Integer codigoPais;

	private Integer telefono;

	private Integer	notadConsecutivo;
	private Integer	notacConsecutivo;

	private Integer tiqueteConsecutivo;

	private String correoElectronico;

	private String	provincia;
	private String	canton;

	private String distrito;

	private String barrio;

	private String logo;

	private String representante;

	private String	estado;
	private String	cazaMatriz;

	private Integer vueltoImprimir;

	private String nombreLlaveCriptografica;

	private Integer	claveLlaveCriptografica;
	private Integer	codigoSeguridad;
	private String	usuarioEnvioComprobante;
	private String	passwordEnvioComprobante;
	private String	tieneInventario;
	private String	tieneLector;
	private String	cambiarPrecio;
	private Integer	aplicaGanancia;

	private String enviarTiquete;

	private Integer enterFacturar;

	private Integer imprimirDirecto;

	private Integer abrirSinComanda;

	private Integer	abrirConComanda;
	private String	impresoraCocina;

	private String impresoraFactura;

	private Integer separarCuenta;
	
	private Integer pantChino;

	public EmpresaCommand(Empresa empresa) {
		super();
		this.nombreLlaveCriptografica = empresa.getNombreLlaveCriptografica();
		this.claveLlaveCriptografica = empresa.getClaveLlaveCriptografica();
		this.codigoSeguridad = empresa.getCodigoSeguridad();
		this.id = empresa.getId();
		this.nombre = empresa.getNombre();
		this.nombreComercial = empresa.getNombreComercial();

		this.tipoCedula = empresa.getTipoCedula();
		this.cedula = empresa.getCedula();
		this.otraSenas = empresa.getOtraSenas();
		this.codigoPais = empresa.getCodigoPais();
		this.telefono = empresa.getTelefono();
		this.correoElectronico = empresa.getCorreoElectronico();
		this.provincia = empresa.getProvincia();
		this.distrito = empresa.getDistrito();
		this.barrio = empresa.getBarrio();
		this.canton = empresa.getBarrio();
		this.logo = empresa.getLogo();
		this.representante = empresa.getRepresentante();
		this.estado = empresa.getEstado();
		this.numeroConsecutivo = empresa.getNumeroConsecutivo();
		this.cazaMatriz = empresa.getCazaMatriz();
		this.passwordEnvioComprobante = empresa.getPasswordEnvioComprobante();
		this.usuarioEnvioComprobante = empresa.getUsuarioEnvioComprobante();
		this.notacConsecutivo = empresa.getNotacConsecutivo();
		this.notadConsecutivo = empresa.getNotadConsecutivo();

		this.tiqueteConsecutivo = empresa.getTiqueteConsecutivo();
		this.enviarTiquete = empresa.getEnviarTiquete();
		this.tieneInventario = empresa.getTieneInventario();
		this.tieneLector = empresa.getTieneLector();
		this.cambiarPrecio = empresa.getCambiarPrecio();
		this.vueltoImprimir = empresa.getVueltoImprimir();

		this.aplicaGanancia = empresa.getAplicaGanancia();

		this.enterFacturar = empresa.getEnterFacturar();
		this.imprimirDirecto = empresa.getImprimirDirecto();
		this.abrirSinComanda = empresa.getAbrirSinComanda();
		this.abrirConComanda = empresa.getAbrirConComanda();
		this.impresoraCocina = empresa.getImpresoraCocina();
		this.impresoraFactura = empresa.getImpresoraFactura();
		this.separarCuenta = empresa.getSepararCuenta();
		this.pantChino = empresa.getPantChino();
	}

	public EmpresaCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getPantChino() {
		return pantChino;
	}

	
	public void setPantChino(Integer pantChino) {
		this.pantChino = pantChino;
	}

	public String getImpresoraCocina() {
		return impresoraCocina;
	}

	public void setImpresoraCocina(String impresoraCocina) {
		this.impresoraCocina = impresoraCocina;
	}

	public String getImpresoraFactura() {
		return impresoraFactura;
	}

	public void setImpresoraFactura(String impresoraFactura) {
		this.impresoraFactura = impresoraFactura;
	}

	public Integer getAbrirConComanda() {
		return abrirConComanda;
	}

	public void setAbrirConComanda(Integer abrirConComanda) {
		this.abrirConComanda = abrirConComanda;
	}

	public Integer getAbrirSinComanda() {
		return abrirSinComanda;
	}

	public void setAbrirSinComanda(Integer abrirSinComanda) {
		this.abrirSinComanda = abrirSinComanda;
	}

	public Integer getEnterFacturar() {
		return enterFacturar;
	}

	public void setEnterFacturar(Integer enterFacturar) {
		this.enterFacturar = enterFacturar;
	}

	public Integer getAplicaGanancia() {
		return aplicaGanancia;
	}

	public void setAplicaGanancia(Integer aplicaGanancia) {
		this.aplicaGanancia = aplicaGanancia;
	}

	public String getTieneLector() {
		return tieneLector;
	}

	public void setTieneLector(String tieneLector) {
		this.tieneLector = tieneLector;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTipoCedula() {
		return tipoCedula;
	}

	public void setTipoCedula(String tipoCedula) {
		this.tipoCedula = tipoCedula;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getOtraSenas() {
		return otraSenas;
	}

	public void setOtraSenas(String otraSenas) {
		this.otraSenas = otraSenas;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(Integer numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
	}

	public String getCazaMatriz() {
		return cazaMatriz;
	}

	public void setCazaMatriz(String cazaMatriz) {
		this.cazaMatriz = cazaMatriz;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getNombreLlaveCriptografica() {
		return nombreLlaveCriptografica;
	}

	public void setNombreLlaveCriptografica(String nombreLlaveCriptografica) {
		this.nombreLlaveCriptografica = nombreLlaveCriptografica;
	}

	public Integer getClaveLlaveCriptografica() {
		return claveLlaveCriptografica;
	}

	public void setClaveLlaveCriptografica(Integer claveLlaveCriptografica) {
		this.claveLlaveCriptografica = claveLlaveCriptografica;
	}

	public Integer getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(Integer codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public String getUsuarioEnvioComprobante() {
		return usuarioEnvioComprobante;
	}

	public void setUsuarioEnvioComprobante(String usuarioEnvioComprobante) {
		this.usuarioEnvioComprobante = usuarioEnvioComprobante;
	}

	public String getPasswordEnvioComprobante() {
		return passwordEnvioComprobante;
	}

	public void setPasswordEnvioComprobante(String passwordEnvioComprobante) {
		this.passwordEnvioComprobante = passwordEnvioComprobante;
	}

	public Integer getNotadConsecutivo() {
		return notadConsecutivo;
	}

	public void setNotadConsecutivo(Integer notadConsecutivo) {
		this.notadConsecutivo = notadConsecutivo;
	}

	public Integer getNotacConsecutivo() {
		return notacConsecutivo;
	}

	public void setNotacConsecutivo(Integer notacConsecutivo) {
		this.notacConsecutivo = notacConsecutivo;
	}

	public Integer getTiqueteConsecutivo() {
		return tiqueteConsecutivo;
	}

	public void setTiqueteConsecutivo(Integer tiqueteConsecutivo) {
		this.tiqueteConsecutivo = tiqueteConsecutivo;
	}

	public String getTieneInventario() {
		return tieneInventario;
	}

	public void setTieneInventario(String tieneInventario) {
		this.tieneInventario = tieneInventario;
	}

	public String getEnviarTiquete() {
		return enviarTiquete;
	}

	public void setEnviarTiquete(String enviarTiquete) {
		this.enviarTiquete = enviarTiquete;
	}

	public String getCambiarPrecio() {
		return cambiarPrecio;
	}

	public void setCambiarPrecio(String cambiarPrecio) {
		this.cambiarPrecio = cambiarPrecio;
	}

	public Integer getVueltoImprimir() {
		return vueltoImprimir;
	}

	public void setVueltoImprimir(Integer vueltoImprimir) {
		this.vueltoImprimir = vueltoImprimir;
	}

	public Integer getImprimirDirecto() {
		return imprimirDirecto;
	}

	public void setImprimirDirecto(Integer imprimirDirecto) {
		this.imprimirDirecto = imprimirDirecto;
	}

	public Integer getSepararCuenta() {
		return separarCuenta;
	}

	public void setSepararCuenta(Integer separarCuenta) {
		this.separarCuenta = separarCuenta;
	}

}

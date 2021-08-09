package com.emprendesoftcr.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.emprendesoftcr.utils.Constantes;

@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

	private static final long	serialVersionUID	= 2782215506581188984L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer						id;

	@Column(name = "abreviatura")
	private String						abreviaturaEmpresa;

	@Column(name = "numero_consecutivo")
	private Integer						numeroConsecutivo;

	@Column(name = "notac_consecutivo")
	private Integer						notacConsecutivo;

	@Column(name = "notad_consecutivo")
	private Integer						notadConsecutivo;

	@Column(name = "tiq_consecutivo")
	private Integer						tiqueteConsecutivo;

	@Column(name = "caza_matriz")
	private String						cazaMatriz;

	@Column(name = "codigo_seguridad")
	private Integer						codigoSeguridad;

	@Column(name = "nombre")
	private String						nombre;

	@Column(name = "nombre_comercial")
	private String						nombreComercial;

	@Column(name = "tipo_cedula")
	private String						tipoCedula;

	@Column(name = "cedula")
	private String						cedula;

	@Column(name = "otra_senas")
	private String						otraSenas;

	@Column(name = "codigo_pais")
	private Integer						codigoPais;

	@Column(name = "telefono")
	private Integer						telefono;

	@Column(name = "correo_electronico")
	private String						correoElectronico;

	@Column(name = "provincia")
	private String						provincia;

	@Column(name = "canton")
	private String						canton;

	@Column(name = "distrito")
	private String						distrito;

	@Column(name = "barrio")
	private String						barrio;

	@Column(name = "logo")
	private String						logo;

	@Column(name = "tiene_inventario")
	private String						tieneInventario;

	@Column(name = "tiene_lector")
	private String						tieneLector;

	@Column(name = "cambiar_precio")
	private String						cambiarPrecio;

	@Column(name = "estado_produccion")
	private String						estadoProduccion;

	@Column(name = "enviar_tiquete")
	private String						enviarTiquete;

	@Column(name = "representante")
	private String						representante;

	@Column(name = "nombre_llaveCriptografica")
	private String						nombreLlaveCriptografica;

	@Column(name = "clave_llaveCriptografica")
	private Integer						claveLlaveCriptografica;

	@Column(name = "usuario_envio_comprobante")
	private String						usuarioEnvioComprobante;

	@Column(name = "password_envio_comprobante")
	private String						passwordEnvioComprobante;

	@Column(name = "estado")
	private String						estado;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "created_at")
	private Date							created_at;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	@Column(name = "updated_at")
	private Date							updated_at;

	@Column(name = "acept_consecutivo")
	private Integer						aceptadoConsecutivo;

	@Column(name = "acept_parcial_consecutivo")
	private Integer						aceptadoParcialConsecutivo;

	@Column(name = "recha_consecutivo")
	private Integer						rechazadoConsecutivo;

	@Column(name = "vuelto_imprimir")
	private Integer						vueltoImprimir;

	@Column(name = "nofactura_elec", columnDefinition = "INT default '0'")
	private Integer						noFacturaElectronica;

	@Column(name = "correo_frecuente")
	private Integer						correoFrecuente;

	@Column(name = "comanda_empresa_id")
	private Long							comandaEmpresa;

	@Column(name = "aplic_ganancia", columnDefinition = "INT default '0'")
	private Integer						aplicaGanancia;

	@Column(name = "enter_facturar", columnDefinition = "INT default '0'")
	private Integer						enterFacturar;

	// Para no mostrar la pantalla de impresion
	@Column(name = "imprimir_direc", columnDefinition = "INT default '0'")
	private Integer						imprimirDirecto;
	// abrir sin comanda
	@Column(name = "abrirsin_com", columnDefinition = "INT default '0'")
	private Integer						abrirSinComanda;
	// abrir con comanda
	@Column(name = "abrircon_com", columnDefinition = "INT default '0'")
	private Integer						abrirConComanda;

	@Column(name = "pant_chino", columnDefinition = "INT default '0'")
	private Integer						pantChino;

	@Column(name = "impre_cocina")
	private String						impresoraCocina;

	@Column(name = "impre_factura")
	private String						impresoraFactura;

	@Column(name = "separarCuenta", columnDefinition = "INT default '0'")
	private Integer						separarCuenta;

	// 0= Factura tiquetes 1 = Facturas
	// en las ventas de venta nueva y post desea que les salga la opcion facturar facturas electronica no tiquetes
	@Column(name = "prio_tiquete", columnDefinition = "INT default '0'")
	private Integer						prioridadFacturar;

	@Column(name = "print_cel", columnDefinition = "INT default '0'")
	private Integer						imprimirCelular;

	@Column(name = "print_siemp", columnDefinition = "INT default '0'")
	private Integer						imprimirSiempre;

	// Para ordenar los articulos y categorias en POST Y RESTAURANTE
	@Column(name = "ordena_cate_articu", columnDefinition = "INT default '0'")
	private Integer						ordenaCategoriaArticulos;

//0 = No valida seguridad 1 = si valida seguridad
	@Column(name = "securida_vent", columnDefinition = "INT default '0'")
	private Integer						seguridadEnVentas;

	// Descargar el inventario 1 = puede descargar 0 = no puede descargar
	@Column(name = "descarg_inven", columnDefinition = "INT default '0'")
	private Integer						descargarInventario;

	@Column(name = "conse_proforma", columnDefinition = "INT default '1'")
	private Integer						consecutivoProforma;

	@Column(name = "cod_actividad", length = 6)
	private String						codigoActividad;

	@Column(name = "cons_simpl_compr", columnDefinition = "INT default '1'")
	private Integer						consecutivoCompraSimplificada;

	@Column(name = "nota_cred_intern", columnDefinition = "INT default '1'")
	private Integer						notaCreditoConsecutivo;

	@Column(name = "nota_debe_intern", columnDefinition = "INT default '1'")
	private Integer						notaDebitoConsecutivo;

	@Column(name = "correo_pdf")
	private String						correoPDF;

	@Column(name = "correo_credito")
	private String						correoCredito;

	@Column(name = "cuenta1")
	private String						cuenta1;

	@Column(name = "cuenta2")
	private String						cuenta2;

	@Column(name = "cuenta3")
	private String						cuenta3;

	@Column(name = "cuenta4")
	private String						cuenta4;

	@Column(name = "cuenta5")
	private String						cuenta5;

	@Column(name = "cuenta6")
	private String						cuenta6;

	@Column(name = "cuenta7")
	private String						cuenta7;

	@Column(name = "cuenta8")
	private String						cuenta8;

	@Column(name = "ip_impresion", length = 12)
	private String						ipImprimirComanda;
	
	@Column(name = "correo_caj1" )
	private String						correoCaja1;

	@Column(name = "correo_caj2" )
	private String						correoCaja2;
	
	@Column(name = "enviar_credito", columnDefinition = "INT default '0'")
	private Integer				enviarCredito		;


	@Column(name = "correo_compra" )
	private String			correoAceptacionCompra;

	@Column(name = "es_simpli" )
	private Integer			esSimplificado; //0 = No 1 = Si  
	
	@Column(name = "es_ponderado" )
	private Integer			esPonderado; //0 = No 1 = Si  
	
	
	@Column(name = "form_epson" )
	private Integer			formatoImpresoraEpsonTM; //0 = No 1 = Si  
	
	

	public Empresa() {
		super();
		this.created_at = new Date();
		this.updated_at = new Date();
		this.estado = Constantes.ESTADO_ACTIVO;
		this.enviarTiquete = Constantes.ESTADO_ACTIVO;
		this.tieneInventario = Constantes.ESTADO_ACTIVO;
		this.tieneLector = Constantes.ESTADO_ACTIVO;
		this.cambiarPrecio = Constantes.ESTADO_INACTIVO;
		this.vueltoImprimir = Constantes.ZEROS;
		this.formatoImpresoraEpsonTM = Constantes.ZEROS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	
	public String getCorreoCaja1() {
		return correoCaja1;
	}

	
	public void setCorreoCaja1(String correoCaja1) {
		this.correoCaja1 = correoCaja1;
	}

	
	public String getCorreoCaja2() {
		return correoCaja2;
	}

	
	public void setCorreoCaja2(String correoCaja2) {
		this.correoCaja2 = correoCaja2;
	}

	public String getIpImprimirComanda() {
		return ipImprimirComanda;
	}

	public void setIpImprimirComanda(String ipImprimirComanda) {
		this.ipImprimirComanda = ipImprimirComanda;
	}

	public Integer getNotaCreditoConsecutivo() {
		return notaCreditoConsecutivo;
	}

	public void setNotaCreditoConsecutivo(Integer notaCreditoConsecutivo) {
		this.notaCreditoConsecutivo = notaCreditoConsecutivo;
	}

	public Integer getConsecutivoCompraSimplificada() {
		return consecutivoCompraSimplificada;
	}

	public void setConsecutivoCompraSimplificada(Integer consecutivoCompraSimplificada) {
		this.consecutivoCompraSimplificada = consecutivoCompraSimplificada;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public Integer getPantChino() {
		return pantChino;
	}

	public Integer getImprimirSiempre() {
		return imprimirSiempre;
	}

	public void setImprimirSiempre(Integer imprimirSiempre) {
		this.imprimirSiempre = imprimirSiempre;
	}

	public void setPantChino(Integer pantChino) {
		this.pantChino = pantChino;
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

	public Integer getCorreoFrecuente() {
		return correoFrecuente;
	}

	public void setCorreoFrecuente(Integer correoFrecuente) {
		this.correoFrecuente = correoFrecuente;
	}

	public String getAbreviaturaEmpresa() {
		return abreviaturaEmpresa;
	}

	public void setAbreviaturaEmpresa(String abreviaturaEmpresa) {
		this.abreviaturaEmpresa = abreviaturaEmpresa;
	}

	public Integer getVueltoImprimir() {
		return vueltoImprimir;
	}

	public void setVueltoImprimir(Integer vueltoImprimir) {
		this.vueltoImprimir = vueltoImprimir;
	}

	public Integer getNumeroConsecutivo() {
		return numeroConsecutivo;
	}

	public void setNumeroConsecutivo(Integer numeroConsecutivo) {
		this.numeroConsecutivo = numeroConsecutivo;
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

	public Integer getNotacConsecutivo() {
		return notacConsecutivo;
	}

	public void setNotacConsecutivo(Integer notacConsecutivo) {
		this.notacConsecutivo = notacConsecutivo;
	}

	public Integer getNotadConsecutivo() {
		return notadConsecutivo;
	}

	public void setNotadConsecutivo(Integer notadConsecutivo) {
		this.notadConsecutivo = notadConsecutivo;
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

	public String getEstadoProduccion() {
		return estadoProduccion;
	}

	public void setEstadoProduccion(String estadoProduccion) {
		this.estadoProduccion = estadoProduccion;
	}

	public String getTieneLector() {
		return tieneLector;
	}

	public void setTieneLector(String tieneLector) {
		this.tieneLector = tieneLector;
	}

	public String getCambiarPrecio() {
		return cambiarPrecio;
	}

	public void setCambiarPrecio(String cambiarPrecio) {
		this.cambiarPrecio = cambiarPrecio;
	}

	public Integer getAceptadoConsecutivo() {
		return aceptadoConsecutivo;
	}

	public void setAceptadoConsecutivo(Integer aceptadoConsecutivo) {
		this.aceptadoConsecutivo = aceptadoConsecutivo;
	}

	public Integer getAceptadoParcialConsecutivo() {
		return aceptadoParcialConsecutivo;
	}

	public void setAceptadoParcialConsecutivo(Integer aceptadoParcialConsecutivo) {
		this.aceptadoParcialConsecutivo = aceptadoParcialConsecutivo;
	}

	public Integer getRechazadoConsecutivo() {
		return rechazadoConsecutivo;
	}

	public void setRechazadoConsecutivo(Integer rechazadoConsecutivo) {
		this.rechazadoConsecutivo = rechazadoConsecutivo;
	}

	public Integer getNoFacturaElectronica() {
		return noFacturaElectronica;
	}

	public void setNoFacturaElectronica(Integer noFacturaElectronica) {
		this.noFacturaElectronica = noFacturaElectronica;
	}

	public Long getComandaEmpresa() {
		return comandaEmpresa;
	}

	public void setComandaEmpresa(Long comandaEmpresa) {
		this.comandaEmpresa = comandaEmpresa;
	}

	public Integer getAplicaGanancia() {
		return aplicaGanancia;
	}

	public void setAplicaGanancia(Integer aplicaGanancia) {
		this.aplicaGanancia = aplicaGanancia;
	}

	public Integer getEnterFacturar() {
		return enterFacturar;
	}

	public void setEnterFacturar(Integer enterFacturar) {
		this.enterFacturar = enterFacturar;
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

	public Integer getPrioridadFacturar() {
		return prioridadFacturar;
	}

	public void setPrioridadFacturar(Integer prioridadFacturar) {
		this.prioridadFacturar = prioridadFacturar;
	}

	public Integer getImprimirCelular() {
		return imprimirCelular;
	}

	public void setImprimirCelular(Integer imprimirCelular) {
		this.imprimirCelular = imprimirCelular;
	}

	public Integer getOrdenaCategoriaArticulos() {
		return ordenaCategoriaArticulos;
	}

	public void setOrdenaCategoriaArticulos(Integer ordenaCategoriaArticulos) {
		this.ordenaCategoriaArticulos = ordenaCategoriaArticulos;
	}

	public Integer getSeguridadEnVentas() {
		return seguridadEnVentas;
	}

	public void setSeguridadEnVentas(Integer seguridadEnVentas) {
		this.seguridadEnVentas = seguridadEnVentas;
	}

	public Integer getDescargarInventario() {
		return descargarInventario;
	}

	public void setDescargarInventario(Integer descargarInventario) {
		this.descargarInventario = descargarInventario;
	}

	public Integer getConsecutivoProforma() {
		return consecutivoProforma;
	}

	public void setConsecutivoProforma(Integer consecutivoProforma) {
		this.consecutivoProforma = consecutivoProforma;
	}

	public String getCorreoPDF() {
		return correoPDF;
	}

	public void setCorreoPDF(String correoPDF) {
		this.correoPDF = correoPDF;
	}

	public String getCorreoCredito() {
		return correoCredito;
	}

	public void setCorreoCredito(String correoCredito) {
		this.correoCredito = correoCredito;
	}

	public String getCuenta1() {
		return cuenta1;
	}

	public void setCuenta1(String cuenta1) {
		this.cuenta1 = cuenta1;
	}

	public String getCuenta2() {
		return cuenta2;
	}

	public void setCuenta2(String cuenta2) {
		this.cuenta2 = cuenta2;
	}

	public String getCuenta3() {
		return cuenta3;
	}

	public void setCuenta3(String cuenta3) {
		this.cuenta3 = cuenta3;
	}

	public String getCuenta4() {
		return cuenta4;
	}

	public void setCuenta4(String cuenta4) {
		this.cuenta4 = cuenta4;
	}

	public String getCuenta5() {
		return cuenta5;
	}

	public void setCuenta5(String cuenta5) {
		this.cuenta5 = cuenta5;
	}

	public String getCuenta6() {
		return cuenta6;
	}

	public void setCuenta6(String cuenta6) {
		this.cuenta6 = cuenta6;
	}

	public String getCuenta7() {
		return cuenta7;
	}

	public void setCuenta7(String cuenta7) {
		this.cuenta7 = cuenta7;
	}

	public String getCuenta8() {
		return cuenta8;
	}

	public void setCuenta8(String cuenta8) {
		this.cuenta8 = cuenta8;
	}

	public Integer getNotaDebitoConsecutivo() {
		return notaDebitoConsecutivo;
	}

	public void setNotaDebitoConsecutivo(Integer notaDebitoConsecutivo) {
		this.notaDebitoConsecutivo = notaDebitoConsecutivo;
	}

	
	public Integer getEnviarCredito() {
		return enviarCredito;
	}

	
	public void setEnviarCredito(Integer enviarCredito) {
		this.enviarCredito = enviarCredito;
	}

	
	public String getCorreoAceptacionCompra() {
		return correoAceptacionCompra;
	}

	
	public void setCorreoAceptacionCompra(String correoAceptacionCompra) {
		this.correoAceptacionCompra = correoAceptacionCompra;
	}

	
	public Integer getEsSimplificado() {
		return esSimplificado;
	}

	
	public void setEsSimplificado(Integer esSimplificado) {
		this.esSimplificado = esSimplificado;
	}

	
	public Integer getEsPonderado() {
		return esPonderado;
	}

	
	public void setEsPonderado(Integer esPonderado) {
		this.esPonderado = esPonderado;
	}

	
	public Integer getFormatoImpresoraEpsonTM() {
		return formatoImpresoraEpsonTM;
	}

	
	public void setFormatoImpresoraEpsonTM(Integer formatoImpresoraEpsonTM) {
		this.formatoImpresoraEpsonTM = formatoImpresoraEpsonTM;
	}
	
	

}
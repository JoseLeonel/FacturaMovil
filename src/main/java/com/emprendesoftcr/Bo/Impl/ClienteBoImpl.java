package com.emprendesoftcr.Bo.Impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Dao.ClienteDao;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ClienteCommand;
import com.emprendesoftcr.web.command.ClienteMag;
import com.emprendesoftcr.web.command.ClienteMagList;

/**
 * Clientes asociados a una empresa ClienteBoImpl.
 * @author jose.
 * @since 17 mar. 2018
 */
@EnableTransactionManagement
@Service("clienteBo")
public class ClienteBoImpl implements ClienteBo {

	private Logger			log	= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClienteDao	clienteDao;
	@Autowired
	private FacturaBo facturaBo;
	@Autowired
	private UsuarioBo usuarioBo;

	@Transactional
	@Override
	public void agregar(Cliente cliente) {
		clienteDao.agregar(cliente);
	}

	@Transactional
	@Override
	public void modificar(Cliente cliente) {
		clienteDao.modificar(cliente);
	}

	@Override
	@Transactional
	public void eliminar(Cliente cliente) {
		clienteDao.eliminar(cliente);
	}

	/**
	 * @see com.factura.bo.ClienteBo#buscarPorNombreCompletoYEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Cliente buscarPorNombreCompletoYEmpresa(String nombreCompleto, Empresa empresa) {
		return clienteDao.buscarPorNombreCompletoYEmpresa(nombreCompleto, empresa);
	}

	/**
	 * @see com.factura.bo.ClienteBo#buscarPorCedulaYEmpresa(java.lang.String, com.factura.domain.Empresa)
	 */
	@Override
	public Cliente buscarPorCedulaYEmpresa(String cedula, Empresa empresa) {
		return clienteDao.buscarPorCedulaYEmpresa(cedula, empresa);
	}

	/**
	 * Buscar por id
	 * @see com.factura.bo.ClienteBo#buscar(java.lang.Integer)
	 */
	@Override
	public Cliente buscar(Long id) {
		return clienteDao.buscar(id);
	}

	/**
	 * Crear el cliente frecuente
	 * @see com.emprendesoftcr.Bo.ClienteBo#crearClienteFrecuente(com.emprendesoftcr.modelo.Empresa, com.emprendesoftcr.modelo.Usuario)
	 */
	@Transactional
	@Override
	public Cliente crearClienteFrecuente(Empresa empresa, Usuario usuario) {
		Cliente cliente = new Cliente();
		cliente.setCedula(Constantes.CEDULA_CLIENTE_FRECUENTE);
		cliente.setTipoCedula(Constantes.TIPO_CEDULA_FISICA);
		cliente.setCodigoPais(506);
		cliente.setCorreoElectronico(Constantes.CORREO_CLIENTE_FRECUENTE);
		cliente.setNombreCompleto(Constantes.NOMBRE_CLIENTE_FRECUENTE);
		cliente.setCelular(Constantes.ZEROS);
		cliente.setTelefono(Constantes.ZEROS);
		cliente.setEmpresa(empresa);
		cliente.setTipoMag(Constantes.CLIENTE_MAG_INACTIVO);
		cliente.setLimiteCredito(0d);
		cliente.setDescuento(Constantes.ZEROS);
		cliente.setEstado(Constantes.ESTADO_ACTIVO);
		cliente.setTipoDocumentoExoneracion(Constantes.EMPTY);
		cliente.setFechaEmisionExoneracion(null);
		cliente.setPorcentajeExoneracion(Constantes.ZEROS);
		cliente.setNombreInstitucionExoneracion(Constantes.EMPTY);
		cliente.setLibreImpuesto(Constantes.LIBRE_IMPUESTOS_INACTIVO);
		cliente.setOtraSena(Constantes.EMPTY);
		cliente.setCreated_at(new Date());
		cliente.setUpdated_at(new Date());
		cliente.setUsuario(usuario);
		cliente.setProvincia(Constantes.ZEROS.toString());
		cliente.setDistrito(Constantes.ZEROS.toString());
		cliente.setCanton(Constantes.ZEROS.toString());
		cliente.setBarrio(Constantes.ZEROS.toString());
		cliente.setIdentificacionExtranjero(Constantes.EMPTY);
		cliente.setNombreComercial(Constantes.EMPTY);
		agregar(cliente);
		return cliente;
	}

	@Override
	public Collection<Cliente> findByEmpresa(Integer idEmpresa) {

		return clienteDao.findByEmpresa(idEmpresa);
	}

	@Override
	public Cliente buscarPorCedulaExtranjera(String cedula, Empresa empresa) {

		return clienteDao.buscarPorCedulaExtranjera(cedula, empresa);
	}

	@Override
	public ClienteMag clienteRegistradoMag(ClienteCommand clienteCommand) {
		ClienteMag clienteMag = new ClienteMag();

		String cedula = clienteCommand.getCedula() != null && !clienteCommand.getCedula().equals(Constantes.EMPTY) ? clienteCommand.getCedula() : clienteCommand.getIdentificacionExtranjero();

		try {
			// request url
			clienteCommand.setTipoMag(clienteCommand.getTipoMag() == null ? Constantes.CLIENTE_MAG_INACTIVO : clienteCommand.getTipoMag());
			String url = clienteCommand.getTipoMag().equals(Constantes.CLIENTE_MAG_AGRO) ? Constantes.API_MAG_AGRO + cedula : Constantes.API_MAG_PESCA + cedula;
			// create an instance of RestTemplate
			RestTemplate restTemplate = new RestTemplate();
			// make an HTTP GET request
			ClienteMagList response = restTemplate.getForObject(url, ClienteMagList.class);
			System.out.println(response.toString());
			List<ClienteMag> employees = response.getListaDatosMAG();
			if (employees != null && !employees.isEmpty()) {
				for (int i = 0; i < employees.size(); i++) {
					if (employees.get(i).getIndicadorActivoMAG().equals(Constantes.INDICADOR_ACTIVO_MAG)) {
						clienteMag = employees.get(i);
					}
				}
			}

		} catch (Exception e) {
			log.error(String.format("--error consultar APi de hacienda de agro o pesca :" + e.getMessage() + new Date()));
			throw e;

		}
		return clienteMag;
	}

	@Override
	public RespuestaServiceValidator<?> clienteHaciendaByCedula(String cedula) {
	// request url
		try {
				String url = "https://api.hacienda.go.cr/fe/ae?identificacion=" + cedula;

				// create an instance of RestTemplate
				RestTemplate restTemplate = new RestTemplate();

				// make an HTTP GET request
				JSONObject json = restTemplate.getForObject(url, JSONObject.class);

				// print json
				System.out.println(json);

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", json);
			} catch (Exception e) {
				return RespuestaServiceValidator.ERROR(e);
			}
	}

	@Transactional
	@Override
	public RespuestaServiceValidator<?> agregar(HttpServletRequest request, ClienteCommand clienteCommand, BindingResult result,Usuario usuarioSesion) {
		try {

			
			clienteCommand.setTipoMag(clienteCommand.getTipoMag() == null ? Constantes.ZEROS : clienteCommand.getTipoMag());
			clienteCommand.setLimiteCredito(clienteCommand.getLimiteCredito() == null ? Constantes.ZEROS : clienteCommand.getLimiteCredito());
			clienteCommand.setCedula(clienteCommand.getCedula() == null ? Constantes.EMPTY : clienteCommand.getCedula());
			clienteCommand.setNombreCompleto(clienteCommand.getNombreCompleto() == null ? Constantes.EMPTY : clienteCommand.getNombreCompleto());
			clienteCommand.setCorreoElectronico(clienteCommand.getCorreoElectronico() == null ? Constantes.EMPTY : clienteCommand.getCorreoElectronico());
			clienteCommand.setNombreComercial(clienteCommand.getNombreComercial() == null ? Constantes.EMPTY : clienteCommand.getNombreComercial());
			clienteCommand.setOtraSena(clienteCommand.getOtraSena() == null ? Constantes.EMPTY : clienteCommand.getOtraSena());
			clienteCommand.setDescuento(clienteCommand.getDescuento() == null ? Constantes.ZEROS : clienteCommand.getDescuento());
			clienteCommand.setEstado(clienteCommand.getEstado() == null ? Constantes.ESTADO_ACTIVO : clienteCommand.getEstado());
			clienteCommand.setIdentificacionExtranjero(clienteCommand.getIdentificacionExtranjero() == null ? Constantes.EMPTY : clienteCommand.getIdentificacionExtranjero());
			clienteCommand.setCorreoElectronico1(clienteCommand.getCorreoElectronico1() == null ? Constantes.EMPTY : clienteCommand.getCorreoElectronico1());
			clienteCommand.setCorreoElectronico2(clienteCommand.getCorreoElectronico2() == null ? Constantes.EMPTY : clienteCommand.getCorreoElectronico2());
			clienteCommand.setCorreoElectronico3(clienteCommand.getCorreoElectronico3() == null ? Constantes.EMPTY : clienteCommand.getCorreoElectronico3());
			clienteCommand.setObservacionVenta(clienteCommand.getObservacionVenta() == null ? Constantes.EMPTY : clienteCommand.getObservacionVenta());
			clienteCommand.setNombreInstitucionExoneracion(clienteCommand.getNombreInstitucionExoneracion() == null ? Constantes.EMPTY : clienteCommand.getNombreInstitucionExoneracion());
			clienteCommand.setNumeroDocumentoExoneracion(clienteCommand.getNumeroDocumentoExoneracion() == null ? Constantes.EMPTY : clienteCommand.getNumeroDocumentoExoneracion());
			clienteCommand.setTipoDocumentoExoneracion(clienteCommand.getTipoDocumentoExoneracion() == null ? Constantes.EMPTY : clienteCommand.getTipoDocumentoExoneracion());
			clienteCommand.setPorcentajeExoneracion(clienteCommand.getPorcentajeExoneracion() == null ? Constantes.ZEROS : clienteCommand.getPorcentajeExoneracion());
			clienteCommand.setLibreImpuesto(clienteCommand.getLibreImpuesto() == null ? Constantes.LIBRE_IMPUESTOS_INACTIVO : clienteCommand.getLibreImpuesto());
			if (!clienteCommand.getIdentificacionExtranjero().equals(Constantes.EMPTY)) {
				clienteCommand.setCedula(Constantes.EMPTY);
				clienteCommand.setTipoCedula(Constantes.EMPTY);
			} else {
				if (clienteCommand.getCedula().equals(Constantes.EMPTY)) {
					result.rejectValue("cedula", Constantes.KEY_REQUERIDO);
				}
				if (Utils.validarCedulaDiferenteCaracter(clienteCommand.getCedula()).equals(Boolean.FALSE)) {
					result.rejectValue("cedula", "error.cliente.cedula.tiene.mismo.digito");

				}

			}

			if (clienteCommand.getNombreCompleto().equals(Constantes.EMPTY)) {
				result.rejectValue("nombreCompleto", Constantes.KEY_REQUERIDO);
			}
			if (clienteCommand.getCorreoElectronico().equals(Constantes.EMPTY)) {
				result.rejectValue("correoElectronico", Constantes.KEY_REQUERIDO);
			}
			if (clienteCommand.getTipoCedula().equals(Constantes.TIPO_CEDULA_FISICA)) {

				if (clienteCommand.getCedula().length() > 9) {
					result.rejectValue("cedula", "error.cliente.cedula.fisica.tamano.incorrecto");
				}
			}
			if (clienteCommand.getTipoCedula().equals(Constantes.TIPO_CEDULA_JURIDICA)) {

				if (clienteCommand.getCedula().length() > 10) {
					result.rejectValue("cedula", "error.cliente.cedula.juridica.tamano.incorrecto");
				}

			}
			Cliente clienteValidar = null;

			if (!clienteCommand.getCedula().equals(Constantes.EMPTY)) {
				clienteValidar = buscarPorCedulaYEmpresa(clienteCommand.getCedula().trim(), usuarioSesion.getEmpresa());
				if (clienteValidar != null) {
					result.rejectValue("cedula", "error.cliente.existe.cedula");
				}

			} else {
				clienteValidar = buscarPorCedulaExtranjera(clienteCommand.getIdentificacionExtranjero(), usuarioSesion.getEmpresa());
				if (clienteValidar != null) {
					result.rejectValue("cedula", "error.cliente.existe.cedula");
				}

			}

			if (clienteCommand.getFechaEmisionExoneracionSTR() != null) {

				if (!clienteCommand.getFechaEmisionExoneracionSTR().equals(Constantes.EMPTY)) {
					if (clienteCommand.getNombreInstitucionExoneracion() == null) {
						result.rejectValue("nombreInstitucionExoneracion", "error.cliente.nombre.institucion.vacio");
					} else if (clienteCommand.getNombreInstitucionExoneracion().equals(Constantes.EMPTY)) {
						result.rejectValue("nombreInstitucionExoneracion", "error.cliente.nombre.institucion.vacio");
					}
					if (clienteCommand.getNumeroDocumentoExoneracion() != null) {
						if (clienteCommand.getNumeroDocumentoExoneracion().equals(Constantes.EMPTY)) {
							result.rejectValue("numeroDocumentoExoneracion", "error.cliente.empty.numeroDocumentoExoneracion");
						}
					}
					if (clienteCommand.getPorcentajeExoneracion() != null) {
						if (clienteCommand.getPorcentajeExoneracion().equals(Constantes.ZEROS_DOUBLE)) {
							result.rejectValue("porcentajeExoneracion", "error.cliente.zeros.porcentajeExoneracion");
						}
						if (clienteCommand.getPorcentajeExoneracion() > Constantes.PORCENTAJE_MAXIMO_EXONERACION) {
							result.rejectValue("porcentajeExoneracion", "error.cliente.maximo.porcentajeExoneracion");
						}
					}

				}
			}
			if (!clienteCommand.getTipoMag().equals(Constantes.CLIENTE_MAG_INACTIVO)) {
				ClienteMag clienteMag = clienteRegistradoMag(clienteCommand);
				if (clienteMag != null && clienteMag.getIndicadorActivoMAG() != null) {
					clienteMag.setIndicadorActivoMAG(clienteMag.getIndicadorActivoMAG() == null ? Constantes.EMPTY : clienteMag.getIndicadorActivoMAG());
					if (clienteMag.getIndicadorActivoMAG().equals(Constantes.EMPTY)) {
						result.rejectValue("tipoMag", "cliente.mag.no.existe");
					} else {
						if (!clienteMag.getIndicadorActivoMAG().equals(Constantes.INDICADOR_ACTIVO_MAG)) {
							result.rejectValue("tipoMag", "cliente.mag.no.esta.activo");
						}
					}

				} else {
					result.rejectValue("tipoMag", "cliente.mag.no.esta.activo");
				}

			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Cliente cliente = new Cliente();
			cliente.setLimiteCredito(clienteCommand.getLimiteCredito());
			cliente.setCedula(clienteCommand.getCedula());
			cliente.setTipoCedula(clienteCommand.getTipoCedula());
			cliente.setDescuento(clienteCommand.getDescuento());
			cliente.setNombreCompleto(clienteCommand.getNombreCompleto());
			cliente.setCorreoElectronico(clienteCommand.getCorreoElectronico());
			cliente.setCorreoElectronico3(clienteCommand.getCorreoElectronico3());
			cliente.setCorreoElectronico1(clienteCommand.getCorreoElectronico1());
			cliente.setCorreoElectronico2(clienteCommand.getCorreoElectronico2());
			if (clienteCommand.getFechaEmisionExoneracionSTR() != null) {
				if (!clienteCommand.getFechaEmisionExoneracionSTR().equals(Constantes.EMPTY)) {
					Date fechaInicio = Utils.parseDate(clienteCommand.getFechaEmisionExoneracionSTR());
					cliente.setFechaEmisionExoneracion(fechaInicio);
					cliente.setNombreInstitucionExoneracion(clienteCommand.getNombreInstitucionExoneracion());
					cliente.setNumeroDocumentoExoneracion(clienteCommand.getNumeroDocumentoExoneracion());
					cliente.setTipoDocumentoExoneracion(clienteCommand.getTipoDocumentoExoneracion());
					cliente.setPorcentajeExoneracion(clienteCommand.getPorcentajeExoneracion());

				} else {
					cliente.setFechaEmisionExoneracion(null);
					cliente.setNombreInstitucionExoneracion(Constantes.EMPTY);
					cliente.setNumeroDocumentoExoneracion(Constantes.EMPTY);
					cliente.setTipoDocumentoExoneracion(Constantes.EMPTY);
					cliente.setPorcentajeExoneracion(Constantes.ZEROS);

				}
			} else {
				cliente.setFechaEmisionExoneracion(null);
				cliente.setNombreInstitucionExoneracion(Constantes.EMPTY);
				cliente.setNumeroDocumentoExoneracion(Constantes.EMPTY);
				cliente.setTipoDocumentoExoneracion(Constantes.EMPTY);
				cliente.setPorcentajeExoneracion(Constantes.ZEROS);

			}
			cliente.setTipoMag(clienteCommand.getTipoMag());
			cliente.setCodigoPais(clienteCommand.getCodigoPais());
			cliente.setIdentificacionExtranjero(clienteCommand.getIdentificacionExtranjero());
			cliente.setEstado(clienteCommand.getEstado());
			cliente.setOtraSena(clienteCommand.getOtraSena());
			cliente.setObservacionVenta(clienteCommand.getObservacionVenta());
			cliente.setNombreComercial(clienteCommand.getNombreComercial());
			cliente.setProvincia(Constantes.EMPTY);
			cliente.setDistrito(Constantes.EMPTY);
			cliente.setCanton(Constantes.EMPTY);
			cliente.setBarrio(Constantes.EMPTY);
			cliente.setCelular(Constantes.ZEROS);
			cliente.setTelefono(clienteCommand.getTelefono() == null ? Constantes.ZEROS : clienteCommand.getTelefono());

			cliente.setEmpresa(usuarioSesion.getEmpresa());
			cliente.setCreated_at(new Date());
			cliente.setUpdated_at(new Date());
			cliente.setUsuario(usuarioSesion);
			cliente.setLibreImpuesto(clienteCommand.getLibreImpuesto());
			agregar(cliente);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cliente.agregar.correctamente", cliente);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	@Override
	public RespuestaServiceValidator modificar(HttpServletRequest request, ClienteCommand clienteCommand, BindingResult result, Usuario usuarioSesion) {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			clienteCommand.setLimiteCredito(clienteCommand.getLimiteCredito() == null ? Constantes.ZEROS : clienteCommand.getLimiteCredito());
			clienteCommand.setTipoMag(clienteCommand.getTipoMag() == null ? Constantes.ZEROS : clienteCommand.getTipoMag());
			clienteCommand.setCedula(clienteCommand.getCedula() == null ? Constantes.EMPTY : clienteCommand.getCedula());
			clienteCommand.setNombreCompleto(clienteCommand.getNombreCompleto() == null ? Constantes.EMPTY : clienteCommand.getNombreCompleto());
			clienteCommand.setCorreoElectronico(clienteCommand.getCorreoElectronico() == null ? Constantes.EMPTY : clienteCommand.getCorreoElectronico());
			clienteCommand.setNombreComercial(clienteCommand.getNombreComercial() == null ? Constantes.EMPTY : clienteCommand.getNombreComercial());
			clienteCommand.setOtraSena(clienteCommand.getOtraSena() == null ? Constantes.EMPTY : clienteCommand.getOtraSena());
			clienteCommand.setDescuento(clienteCommand.getDescuento() == null ? Constantes.ZEROS : clienteCommand.getDescuento());
			clienteCommand.setEstado(clienteCommand.getEstado() == null ? Constantes.ESTADO_ACTIVO : clienteCommand.getEstado());
			clienteCommand.setIdentificacionExtranjero(clienteCommand.getIdentificacionExtranjero() == null ? Constantes.EMPTY : clienteCommand.getIdentificacionExtranjero());
			clienteCommand.setCorreoElectronico1(clienteCommand.getCorreoElectronico1() == null ? Constantes.EMPTY : clienteCommand.getCorreoElectronico1());
			clienteCommand.setCorreoElectronico2(clienteCommand.getCorreoElectronico2() == null ? Constantes.EMPTY : clienteCommand.getCorreoElectronico2());
			clienteCommand.setCorreoElectronico3(clienteCommand.getCorreoElectronico3() == null ? Constantes.EMPTY : clienteCommand.getCorreoElectronico3());
			clienteCommand.setObservacionVenta(clienteCommand.getObservacionVenta() == null ? Constantes.EMPTY : clienteCommand.getObservacionVenta());
			clienteCommand.setNombreInstitucionExoneracion(clienteCommand.getNombreInstitucionExoneracion() == null ? Constantes.EMPTY : clienteCommand.getNombreInstitucionExoneracion());
			clienteCommand.setNumeroDocumentoExoneracion(clienteCommand.getNumeroDocumentoExoneracion() == null ? Constantes.EMPTY : clienteCommand.getNumeroDocumentoExoneracion());
			clienteCommand.setTipoDocumentoExoneracion(clienteCommand.getTipoDocumentoExoneracion() == null ? Constantes.EMPTY : clienteCommand.getTipoDocumentoExoneracion());
			clienteCommand.setPorcentajeExoneracion(clienteCommand.getPorcentajeExoneracion() == null ? Constantes.ZEROS : clienteCommand.getPorcentajeExoneracion());
			clienteCommand.setLibreImpuesto(clienteCommand.getLibreImpuesto() == null ? Constantes.LIBRE_IMPUESTOS_INACTIVO : clienteCommand.getLibreImpuesto());

			if (!clienteCommand.getIdentificacionExtranjero().equals(Constantes.EMPTY)) {
				clienteCommand.setCedula(Constantes.EMPTY);
				clienteCommand.setTipoCedula(Constantes.EMPTY);
			} else {
				if (clienteCommand.getCedula().equals(Constantes.EMPTY)) {
					result.rejectValue("cedula", Constantes.KEY_REQUERIDO);
				}
				if (Utils.validarCedulaDiferenteCaracter(clienteCommand.getCedula()).equals(Boolean.FALSE)) {
					result.rejectValue("cedula", "error.cliente.cedula.tiene.mismo.digito");

				}
				if (Utils.validarCedulaDiferenteCaracter(clienteCommand.getCedula()).equals(Boolean.FALSE)) {
					result.rejectValue("cedula", "error.cliente.cedula.tiene.mismo.digito");
				}

			}

			if (clienteCommand.getNombreCompleto().equals(Constantes.EMPTY)) {
				result.rejectValue("nombreCompleto", Constantes.KEY_REQUERIDO);
			}
			if (clienteCommand.getCorreoElectronico().equals(Constantes.EMPTY)) {
				result.rejectValue("correoElectronico", Constantes.KEY_REQUERIDO);
			}
			if (clienteCommand.getTipoCedula().equals(Constantes.TIPO_CEDULA_FISICA)) {
				if (clienteCommand.getCedula().length() > 9) {
					result.rejectValue("cedula", "error.cliente.cedula.fisica.tamano.incorrecto");
				}
			}
			if (clienteCommand.getTipoCedula().equals(Constantes.TIPO_CEDULA_JURIDICA)) {
				if (clienteCommand.getCedula().length() > 10) {
					result.rejectValue("cedula", "error.cliente.cedula.juridica.tamano.incorrecto");
				}
			}
		
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("cliente.no.modificado", result.getAllErrors());
			}
			Cliente clienteBD = buscar(clienteCommand.getId());

			if (clienteBD == null) {
				result.rejectValue("cedula", "error.cliente.noExiste");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("cliente.no.modificado", result.getAllErrors());
			}
			Cliente clienteValidar = null;
			if (!clienteCommand.getCedula().equals(clienteBD.getCedula())) {
				Boolean verificarFacturas = false;
				Collection<Factura> facturas = facturaBo.findByClienteAndEmpresa(clienteBD, usuarioSesion.getEmpresa());
				if (facturas != null) {
					if (!facturas.isEmpty()) {
						result.rejectValue("cedula", "error.cliente.cedula.tiene.facturas");
						verificarFacturas = true;
					}
				}
				if (verificarFacturas == false) {
					if (!clienteCommand.getCedula().equals(Constantes.EMPTY)) {
						clienteValidar = buscarPorCedulaYEmpresa(clienteCommand.getCedula().trim(), usuarioSesion.getEmpresa());
						if (clienteValidar != null) {
							result.rejectValue("cedula", "error.cliente.existe.cedula");
						}

					} else {
						clienteValidar = buscarPorCedulaExtranjera(clienteCommand.getIdentificacionExtranjero(), usuarioSesion.getEmpresa());
						if (clienteValidar != null) {
							result.rejectValue("cedula", "error.cliente.existe.cedula");
						}

					}

				}
			}
			if (clienteCommand.getNumeroDocumentoExoneracion() != null) {
				if (!clienteCommand.getNumeroDocumentoExoneracion().equals(Constantes.EMPTY)) {
					if (clienteCommand.getNombreInstitucionExoneracion() == null) {
						result.rejectValue("nombreInstitucionExoneracion", "error.cliente.nombre.institucion.vacio");
					} else if (clienteCommand.getNombreInstitucionExoneracion().equals(Constantes.EMPTY)) {
						result.rejectValue("nombreInstitucionExoneracion", "error.cliente.nombre.institucion.vacio");
					}

						if (clienteCommand.getNumeroDocumentoExoneracion() != null && clienteCommand.getNumeroDocumentoExoneracion().equals(Constantes.EMPTY)) {
							result.rejectValue("numeroDocumentoExoneracion", "error.cliente.empty.numeroDocumentoExoneracion");
						}
					if (clienteCommand.getPorcentajeExoneracion() != null) {
						if (clienteCommand.getPorcentajeExoneracion().equals(Constantes.ZEROS)) {
							result.rejectValue("porcentajeExoneracion", "error.cliente.zeros.porcentajeExoneracion");
						}
						if (clienteCommand.getPorcentajeExoneracion() > Constantes.PORCENTAJE_MAXIMO_EXONERACION) {
							result.rejectValue("porcentajeExoneracion", "error.cliente.maximo.porcentajeExoneracion");
						}

					}

				}
			}
			if (!clienteCommand.getTipoMag().equals(Constantes.CLIENTE_MAG_INACTIVO)) {
				ClienteMag clienteMag = clienteRegistradoMag(clienteCommand);
				if (clienteMag != null && clienteMag.getIndicadorActivoMAG() != null) {
					clienteMag.setIndicadorActivoMAG(clienteMag.getIndicadorActivoMAG() == null ? Constantes.EMPTY : clienteMag.getIndicadorActivoMAG());
					if (clienteMag.getIndicadorActivoMAG().equals(Constantes.EMPTY)) {
						result.rejectValue("tipoMag", "cliente.mag.no.existe");
					} else {
						if (!clienteMag.getIndicadorActivoMAG().equals(Constantes.INDICADOR_ACTIVO_MAG)) {
							result.rejectValue("tipoMag", "cliente.mag.no.esta.activo");

						}
					}

				} else {
					result.rejectValue("tipoMag", "cliente.mag.no.esta.activo");
				}

			}
			// Validar el limite de credito de un cliente
			if (clienteBD.getLimiteCredito() != null && !clienteBD.getLimiteCredito().equals(clienteCommand.getLimiteCredito())) {
				if (usuarioBo.isAdministrador_cajero(usuario) || usuarioBo.isAdministrador_empresa(usuario) || usuarioBo.isAdministrador_restaurante(usuario)) {
					clienteBD.setLimiteCredito(clienteCommand.getLimiteCredito());
				} else {
					// No Permitido
					result.rejectValue("limiteCredito", "cliente.limiteCredito.no.autorizado");
				}
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (clienteCommand.getNumeroDocumentoExoneracion() != null) {
				if (!clienteCommand.getNumeroDocumentoExoneracion().equals(Constantes.EMPTY)) {
					Date fechaInicio = Utils.parseDate(clienteCommand.getFechaEmisionExoneracionSTR());
					clienteBD.setFechaEmisionExoneracion(fechaInicio);
					clienteBD.setNombreInstitucionExoneracion(clienteCommand.getNombreInstitucionExoneracion());
					clienteBD.setNumeroDocumentoExoneracion(clienteCommand.getNumeroDocumentoExoneracion());
					clienteBD.setTipoDocumentoExoneracion(clienteCommand.getTipoDocumentoExoneracion());
					clienteBD.setPorcentajeExoneracion(clienteCommand.getPorcentajeExoneracion());
				} else {
					clienteBD.setFechaEmisionExoneracion(null);
					clienteBD.setNombreInstitucionExoneracion(Constantes.EMPTY);
					clienteBD.setNumeroDocumentoExoneracion(Constantes.EMPTY);
					clienteBD.setTipoDocumentoExoneracion(Constantes.EMPTY);
					clienteBD.setPorcentajeExoneracion(Constantes.ZEROS);
				}
			} else {
				clienteBD.setFechaEmisionExoneracion(null);
				clienteBD.setNombreInstitucionExoneracion(Constantes.EMPTY);
				clienteBD.setNumeroDocumentoExoneracion(Constantes.EMPTY);
				clienteBD.setTipoDocumentoExoneracion(Constantes.EMPTY);
				clienteBD.setPorcentajeExoneracion(Constantes.ZEROS);

			}

			clienteBD.setProvincia(Constantes.EMPTY);
			clienteBD.setDistrito(Constantes.EMPTY);
			clienteBD.setCanton(Constantes.EMPTY);
			clienteBD.setBarrio(Constantes.EMPTY);
			clienteBD.setCorreoElectronico1(clienteCommand.getCorreoElectronico1());
			clienteBD.setCorreoElectronico2(clienteCommand.getCorreoElectronico2());
			clienteBD.setCorreoElectronico3(clienteCommand.getCorreoElectronico3());

			clienteBD.setCedula(clienteCommand.getCedula());
			clienteBD.setNombreCompleto(clienteCommand.getNombreCompleto());
			clienteBD.setCorreoElectronico(clienteCommand.getCorreoElectronico());
			clienteBD.setDescuento(clienteCommand.getDescuento());
			clienteBD.setOtraSena(clienteCommand.getOtraSena());
			clienteBD.setTipoCedula(clienteCommand.getTipoCedula());
			clienteBD.setNombreComercial(clienteCommand.getNombreComercial());
			clienteBD.setObservacionVenta(clienteCommand.getObservacionVenta());
			clienteBD.setUpdated_at(new Date());
			clienteBD.setEstado(clienteCommand.getEstado());
			clienteBD.setTelefono(clienteCommand.getTelefono());
			clienteBD.setCelular(clienteCommand.getCelular());
			clienteBD.setUsuario(usuarioSesion);
			clienteBD.setCodigoPais(clienteCommand.getCodigoPais());
			clienteBD.setIdentificacionExtranjero(clienteCommand.getIdentificacionExtranjero());
			clienteBD.setObservacionVenta(clienteCommand.getObservacionVenta() == null ? Constantes.EMPTY : clienteCommand.getObservacionVenta());
			clienteBD.setLibreImpuesto(clienteCommand.getLibreImpuesto());
			clienteBD.setTipoMag(clienteCommand.getTipoMag());
			modificar(clienteBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cliente.modificado.correctamente", clienteBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@Override
	public RespuestaServiceValidator<?> mostrar(HttpServletRequest request, Cliente cliente, BindingResult result) {
		try {
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			Cliente clienteBD = null;
			if (cliente.getId() != null) {
				clienteBD = buscar(cliente.getId());
			} else {
				if (clienteBD == null && !cliente.getCedula().isEmpty()) {
					clienteBD = buscarPorCedulaYEmpresa(cliente.getCedula(), usuarioSesion.getEmpresa());
				}
				if (clienteBD == null && !cliente.getIdentificacionExtranjero().isEmpty()) {
					clienteBD = buscarPorCedulaExtranjera(cliente.getIdentificacionExtranjero(), usuarioSesion.getEmpresa());
				}

			}

			ClienteCommand clienteCommand = new ClienteCommand(clienteBD);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", clienteCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

}
package com.emprendesoftcr.web.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ClienteCommand;
import com.emprendesoftcr.web.command.ClienteMag;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Para el mantenimiento y consulta de los clientes asociados a una sucursal ClientesController.
 * @author jose.
 * @since 17 mar. 2018
 */
@CrossOrigin
@Controller
public class ClientesController {

	private static final Function<Object, ClienteCommand>	TO_COMMAND	= new Function<Object, ClienteCommand>() {

																																			@Override
																																			public ClienteCommand apply(Object f) {
																																				return new ClienteCommand((Cliente) f);
																																			};
																																		};

	@Autowired
	private ClienteBo																			clienteBo;

	@Autowired
	private FacturaBo																			facturaBo;

	@Autowired
	private UsuarioBo																			usuarioBo;

	@Autowired
	private DataTableBo																		dataTableBo;

	@Autowired
	private ClientePropertyEditor													clientePropertyEditor;

	@Autowired
	private EmpresaPropertyEditor													empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor													stringPropertyEditor;
	
	@Autowired
	private ValidateTokenBo													validateTokenBo;

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/nuevoCliente.do", method = RequestMethod.GET)
	public String nuevoCliente(ModelMap model) {
		return "views/cliente/nuevoCliente";
	}

	/**
	 * Mostrar el JSP de la Clientes
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarClientes", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/cliente/ListarClientes";
	}

	/**
	 * Metodo para mostrar un listar los clientes de un llamado ajax
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarClientesAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Cliente");
		if (usuarioBo.isAdministrador_sistema(usuario).equals(Boolean.FALSE)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter dataTableFilter = new JqGridFilter("cedula", "'" + Constantes.CEDULA_CLIENTE_FRECUENTE + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@RequestMapping(value = "/movil/ListarClientesAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Collection<Cliente> listarClientesAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Integer idEmpresa) {

		return clienteBo.findByEmpresa(idEmpresa);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarClientesActivosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Cliente");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_INACTIVO + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("cedula", "'" + Constantes.CEDULA_CLIENTE_FRECUENTE + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		delimitadores = new DataTableDelimitador(request, "Cliente");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		for (Iterator<Object> iterator = objetos.iterator(); iterator.hasNext();) {
			Cliente object = (Cliente) iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				if (object.getEstado().equals(Constantes.ESTADO_ACTIVO)) {
					solicitudList.add(new ClienteCommand(object));
				}
			}
		}
		respuestaService.setRecordsTotal(total);
		respuestaService.setRecordsFiltered(total);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}

	/**
	 * Agregar cliente a una sucursal de un empresa
	 * @param request
	 * @param model
	 * @param cliente
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarClienteAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute ClienteCommand clienteCommand, BindingResult result, SessionStatus status) throws Exception {
		try {

			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
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
				clienteValidar = clienteBo.buscarPorCedulaYEmpresa(clienteCommand.getCedula().trim(), usuarioSesion.getEmpresa());
				if (clienteValidar != null) {
					result.rejectValue("cedula", "error.cliente.existe.cedula");
				}

			} else {
				clienteValidar = clienteBo.buscarPorCedulaExtranjera(clienteCommand.getIdentificacionExtranjero(), usuarioSesion.getEmpresa());
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
				ClienteMag clienteMag = clienteBo.clienteRegistradoMag(clienteCommand);
				if (clienteMag != null && clienteMag.getNombreMAG() != null) {
					clienteMag.setIndicadorActivoMAG(clienteMag.getIndicadorActivoMAG() == null ? Constantes.EMPTY : clienteMag.getIndicadorActivoMAG());
					if (clienteMag.getIndicadorActivoMAG().equals(Constantes.EMPTY)) {
						result.rejectValue("tipoMag", "cliente.mag.no.existe");
					} else {
						if (!clienteMag.getIndicadorActivoMAG().equals(Constantes.INDICADOR_ACTIVO_MAG)) {
							if (clienteCommand.getTipoMag().equals(Constantes.CLIENTE_MAG_AGRO)) {
								result.rejectValue("tipoMag", "cliente.mag.no.esta.activo");
							} else {
								result.rejectValue("tipoMag", "cliente.mag.no.esta.activo");
							}

						}
					}

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
			clienteBo.agregar(cliente);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cliente.agregar.correctamente", cliente);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar una sucursal de una empresa
	 * @param request
	 * @param model
	 * @param cliente
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarClienteAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute ClienteCommand clienteCommand, BindingResult result, SessionStatus status) throws Exception {
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
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("cliente.no.modificado", result.getAllErrors());
			}
			Cliente clienteBD = clienteBo.buscar(clienteCommand.getId());

			if (clienteBD == null) {
				return RESPONSES.ERROR.CLIENTE.NO_EXISTE;
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
						clienteValidar = clienteBo.buscarPorCedulaYEmpresa(clienteCommand.getCedula().trim(), usuarioSesion.getEmpresa());
						if (clienteValidar != null) {
							result.rejectValue("cedula", "error.cliente.existe.cedula");
						}

					} else {
						clienteValidar = clienteBo.buscarPorCedulaExtranjera(clienteCommand.getIdentificacionExtranjero(), usuarioSesion.getEmpresa());
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
				ClienteMag clienteMag = clienteBo.clienteRegistradoMag(clienteCommand);
				if (clienteMag != null && clienteMag.getNombreMAG() != null) {
					clienteMag.setIndicadorActivoMAG(clienteMag.getIndicadorActivoMAG() == null ? Constantes.EMPTY : clienteMag.getIndicadorActivoMAG());
					if (clienteMag.getIndicadorActivoMAG().equals(Constantes.EMPTY)) {
						result.rejectValue("tipoMag", "cliente.mag.no.existe");
					} else {
						if (!clienteMag.getIndicadorActivoMAG().equals(Constantes.INDICADOR_ACTIVO_MAG)) {
							if (clienteCommand.getTipoMag().equals(Constantes.CLIENTE_MAG_AGRO)) {
								result.rejectValue("tipoMag", "cliente.mag.no.esta.activo");
							} else {
								result.rejectValue("tipoMag", "cliente.mag.no.esta.activo");
							}

						}
					}

				}

			}
			// Validar el limite de credito de un cliente
				if (clienteBD.getLimiteCredito() !=null && !clienteBD.getLimiteCredito().equals(clienteCommand.getLimiteCredito())) {
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
			clienteBo.modificar(clienteBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cliente.modificado.correctamente", clienteBD);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/clienteHacienda.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrarCliente(HttpServletRequest request,  ModelMap model, @ModelAttribute Cliente cliente ,HttpServletResponse response, @RequestParam String cedula,BindingResult result, SessionStatus status) {
		try {
//			String nombreUsuario = request.getUserPrincipal().getName();
//			if(validateTokenBo.validarTokenApis(request) == false) {
//				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
//			}
			// request url
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

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/tipoCambioBancoCentral.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator tipoCambioBancoCentral(HttpServletRequest request, HttpServletResponse response) {
		try {

			// request url
			String url = "https://api.hacienda.go.cr/indicadores/tc";
			
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

	/**
	 * Buscar por id el cliente para mostrar
	 * @param request
	 * @param model
	 * @param cliente
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarClienteAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Cliente cliente, BindingResult result, SessionStatus status) throws Exception {
		try {
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			Cliente clienteBD = null;
			if (cliente.getId() != null) {
				clienteBD = clienteBo.buscar(cliente.getId());
			} else {
				if (clienteBD == null && !cliente.getCedula().isEmpty()) {
					clienteBD = clienteBo.buscarPorCedulaYEmpresa(cliente.getCedula(), usuarioSesion.getEmpresa());
				}
				if (clienteBD == null && !cliente.getIdentificacionExtranjero().isEmpty()) {
					clienteBD = clienteBo.buscarPorCedulaExtranjera(cliente.getIdentificacionExtranjero(), usuarioSesion.getEmpresa());
				}

			}

			ClienteCommand clienteCommand = new ClienteCommand(clienteBD);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", clienteCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CLIENTE {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cliente.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cliente.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CLIENTE {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.cliente.noExiste");
			}
		}
	}

}

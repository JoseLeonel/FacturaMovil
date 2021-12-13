package com.emprendesoftcr.apis;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.CuentaCobrarBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.FacturaBo;
import com.emprendesoftcr.Bo.HaciendaBo;
import com.emprendesoftcr.Bo.SalidaEntradaDineroBo;
import com.emprendesoftcr.Bo.TipoCambioBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.Bo.VendedorBo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Factura;
import com.emprendesoftcr.modelo.Hacienda;
import com.emprendesoftcr.modelo.Mesa;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.TipoCambio;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.modelo.Vendedor;
import com.emprendesoftcr.modelo.sqlNativo.FacturaIDNativa;
import com.emprendesoftcr.modelo.sqlNativo.FacturasDelDiaNative;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.emprendesoftcr.web.command.FacturaCommand;
import com.emprendesoftcr.web.command.FacturaDiaCommand;
import com.emprendesoftcr.web.command.SalidaEntradaDineroCommand;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.FechaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MesaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.VendedorPropertyEditor;
import com.google.common.base.Function;
@CrossOrigin
@RestController
@RequestMapping("/api/factura")
public class FacturaRest {

	private static final Function<Object, SalidaEntradaDineroCommand>	TO_COMMAND_ENTRADA_SALIDA	= new Function<Object, SalidaEntradaDineroCommand>() {

																																																@Override
																																																public SalidaEntradaDineroCommand apply(Object f) {
																																																	return new SalidaEntradaDineroCommand((SalidaEntradaDinero) f);
																																																};
																																															};

	@Autowired
	private ValidateTokenBo																						validateTokenBo;


	@Autowired
	private FacturaBo																									facturaBo;
	@Autowired
	private UsuarioBo																									usuarioBo;
	@Autowired
	private DetalleBo																									detalleBo;
	@Autowired
	private DataTableBo																								dataTableBo;
	@Autowired
	private UsuarioCajaBo																							usuarioCajaBo;
	@Autowired
	private HaciendaBo																								haciendaBo;
	@Autowired
	private ConsultasNativeBo																					consultasNativeBo;
	@Autowired
	private CuentaCobrarBo																						cuentaCobrarBo;
	@Autowired
	private ClienteBo																									clienteBo;
	@Autowired
	private VendedorBo																								vendedorBo;
	@Autowired
	private SalidaEntradaDineroBo																			salidaEntradaDineroBo;
	@Autowired
	private TipoCambioBo																							tipoCambioBo;
	@Autowired
	private EmpresaPropertyEditor																			empresaPropertyEditor;

	@Autowired
	private ClientePropertyEditor																			clientePropertyEditor;

	@Autowired
	private VendedorPropertyEditor																		vendedorPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@Autowired
	private FechaPropertyEditor																				fechaPropertyEditor;

	@Autowired
	private MesaPropertyEditor																				mesaPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(Vendedor.class, vendedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
		binder.registerCustomEditor(Date.class, fechaPropertyEditor);
		binder.registerCustomEditor(Mesa.class, mesaPropertyEditor);
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/ListarFacturasEsperaActivasAjax")
	public RespuestaServiceDataTable listarEsperaActivasAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (validateTokenBo.validarTokenApis(request) == false) {

			@SuppressWarnings("unused")
			DataTableDelimitador delimitadores = null;
			delimitadores = new DataTableDelimitador(request, "Factura");
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
			List<Object> solicitudList = new ArrayList<Object>();
			respuestaService.setRecordsTotal(0l);
			respuestaService.setRecordsFiltered(0l);
			respuestaService.setAaData(solicitudList);
			return respuestaService;
		}
		return facturaBo.listarEsperaActivasAjax(request, response);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/mostrarVentaEnEspera")
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idFactura) {

		@SuppressWarnings("unused")
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<Detalle> objetos = detalleBo.findbyIdFactura(idFactura);
		for (Detalle detalle : objetos) {
			solicitudList.add(new DetalleFacturaCommand(detalle));
		}
		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarSalidaEntradaDineroAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarlocal(HttpServletRequest request, ModelMap model, @ModelAttribute SalidaEntradaDineroCommand salidaEntradaDineroCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}

			return salidaEntradaDineroBo.agregar(request, salidaEntradaDineroCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listarEntradasOrSalidas.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarEntradasOrSalidasLocal(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer idTipoEntrada, @RequestParam(value = "idEntradaSalida", required = false) Long idEntradaSalida) throws IOException, ServletException {

		if (validateTokenBo.validarTokenApis(request) == false) {

			@SuppressWarnings("unused")
			DataTableDelimitador delimitadores = new DataTableDelimitador(request, "SalidaEntradaDinero");

			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
			List<Object> solicitudList = new ArrayList<Object>();
			respuestaService.setRecordsTotal(0l);
			respuestaService.setRecordsFiltered(0l);
			respuestaService.setAaData(solicitudList);
			return respuestaService;
		}
		return listarEntradasOrSalidasT(request, response, idTipoEntrada, idEntradaSalida);
	}

	private RespuestaServiceDataTable<?> listarEntradasOrSalidasT(HttpServletRequest request, HttpServletResponse response, Integer idTipoEntrada, Long idEntradaSalida) {
		DataTableDelimitador delimitadores = null;
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (idEntradaSalida != null) {
			SalidaEntradaDinero salidaEntradaDinero = salidaEntradaDineroBo.findById(idEntradaSalida);
			if (salidaEntradaDinero != null) {
				salidaEntradaDineroBo.eliminar(salidaEntradaDinero);
			}
		}
		UsuarioCaja usuarioCaja = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);
		if (usuarioCaja != null) {
			delimitadores = new DataTableDelimitador(request, "SalidaEntradaDinero");

			JqGridFilter dataTableFilter = new JqGridFilter("tipo", "'" + idTipoEntrada.toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);

			dataTableFilter = new JqGridFilter("usuariocaja.id", "'" + usuarioCaja.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);

		}

		return usuarioCaja == null ? null : UtilsForControllersAPI.process(request, dataTableBo, delimitadores, TO_COMMAND_ENTRADA_SALIDA);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/CrearFacturaServiceAjax")
	public RespuestaServiceValidator crearFacturaTurismo(HttpServletRequest request, ModelMap model, @RequestBody FacturaCommand facturaCommand, BindingResult result) throws ParseException {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

			ArrayList<DetalleFacturaCommand> detallesFacturaCommand = facturaBo.formaDetallesCommand(facturaCommand);
			ArrayList<DetalleFacturaCommand> detallesNotaCredito = new ArrayList<DetalleFacturaCommand>();
			return this.crearFactura(facturaCommand, result, usuario, detallesFacturaCommand, detallesNotaCredito);
		} catch (Exception e) {

			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}
	}

	@SuppressWarnings("rawtypes")
	private RespuestaServiceValidator<?> crearFactura(FacturaCommand facturaCommand, BindingResult result, Usuario usuario, ArrayList<DetalleFacturaCommand> detallesFacturaCommand, ArrayList<DetalleFacturaCommand> detallesNotaCredito) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			facturaCommand.setTotalBanco(facturaCommand.getTotalBanco() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalBanco());
			facturaCommand.setTotalEfectivo(facturaCommand.getTotalEfectivo() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalEfectivo());
			facturaCommand.setTotalTarjeta(facturaCommand.getTotalTarjeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTarjeta());
			facturaCommand.setTotalTransporte(facturaCommand.getTotalTransporte() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalTransporte());
			facturaCommand.setTotalDescuentos(facturaCommand.getTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalDescuentos());
			facturaCommand.setTotalExento(facturaCommand.getTotalExento() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalExento());
			facturaCommand.setTotalGravado(facturaCommand.getTotalGravado() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalGravado());
			facturaCommand.setTotalCambioPagar(facturaCommand.getTotalCambioPagar() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCambioPagar());
			facturaCommand.setTotalMercanciasExentas(facturaCommand.getTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasExentas());
			facturaCommand.setTotalMercanciasGravadas(facturaCommand.getTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalMercanciasGravadas());
			facturaCommand.setTotalCredito(facturaCommand.getTotalCredito() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalCredito());
			facturaCommand.setTotalServExentos(facturaCommand.getTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServExentos());
			facturaCommand.setTotalServGravados(facturaCommand.getTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalServGravados());
			facturaCommand.setTotalVenta(facturaCommand.getTotalVenta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalVenta());
			facturaCommand.setTotalVentaNeta(facturaCommand.getTotalVentaNeta() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalVentaNeta());
			facturaCommand.setTipoDoc(facturaCommand.getTipoDoc() != null ? facturaCommand.getTipoDoc() : Constantes.EMPTY);
			facturaCommand.setCondicionVenta(facturaCommand.getCondicionVenta() == null ? Constantes.EMPTY : facturaCommand.getCondicionVenta());
			UsuarioCaja usuarioCajaBd = null;
			// Si esta en estado facturada en base de datos se retornae un mensaje que ya fue procesada
			if (facturaCommand != null) {
				if (facturaCommand.getId() != null) {
					if (facturaCommand.getId() > Constantes.ZEROS_LONG) {
						FacturaIDNativa facturaRevision = consultasNativeBo.findIdFactura(facturaCommand.getId());
						if (facturaRevision != null) {
							if (facturaRevision.getEstado() != null) {
								if (facturaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO) || facturaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_ACEPTADA) || facturaRevision.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO) || facturaRevision.getEstado().equals(Constantes.FACTURA_ESTADO_ANULADA)) {
									return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.ya.esta.procesada", result.getAllErrors());
								}
							}

						}

					}
				}
			}
			if (facturaCommand.getNota() != null) {
				if (facturaCommand.getNota().length() > 250) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.nota.lenght", result.getAllErrors());
				}
			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				if (facturaCommand.getCorreoAlternativo() != null) {
					if (!facturaCommand.getCorreoAlternativo().equals(Constantes.EMPTY)) {
						if (!Utils.validarCorreo(facturaCommand.getCorreoAlternativo())) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.correo.alternativo.invalido", result.getAllErrors());
						}
					}
				}

			}

			if (facturaCommand.getCondicionVenta().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.condicion.venta.vacia", result.getAllErrors());
			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO)) {
				if (facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
					if (facturaCommand.getCliente() == null) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.condicion.venta.credito.cliente.vacio", result.getAllErrors());
					} else {
						if (facturaCommand.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE)) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.condicion.venta.credito.cliente.frecuente", result.getAllErrors());
						}
					}
					if (facturaCommand.getCliente() != null) {
						Double limite = facturaCommand.getCliente().getLimiteCredito() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getCliente().getLimiteCredito();
						if (limite > Constantes.ZEROS_DOUBLE) {
							// validar si tiene disponible
							Double saldo = cuentaCobrarBo.getDisponible(usuario.getEmpresa().getId(), facturaCommand.getCliente());
							saldo = saldo == null ? Constantes.ZEROS_DOUBLE : saldo;
							Double comprobante = facturaCommand.getTotalComprobante() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTotalComprobante();
							if (comprobante > saldo) {
								return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.condicion.venta.credito.cliente.sinlimiteCredito", result.getAllErrors());
							}

						}
					}
				}
			}
			if (facturaCommand.getTipoDoc() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}
			if (facturaCommand.getTipoDoc() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}

			if (facturaCommand.getTipoDoc().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.tipo.doc", result.getAllErrors());
			}

			if (facturaCommand.getCodigoActividad() == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.actividad.comercial.no.existe", result.getAllErrors());
			} else if (facturaCommand.getCodigoActividad().equals(Constantes.EMPTY)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.actividad.comercial.no.existe", result.getAllErrors());
			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				usuarioCajaBd = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);

				if (usuarioCajaBd == null) {
					if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {

						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.cajas.abierta", result.getAllErrors());
					}
				}

			}
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_NOTA_CREDITO_INTERNO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS) && !facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				if (facturaCommand.getEstado().equals(Constantes.FACTURA_ESTADO_FACTURADO)) {
					if (facturaCommand.getTotalBanco().equals(Constantes.ZEROS_DOUBLE) && facturaCommand.getTotalEfectivo().equals(Constantes.ZEROS_DOUBLE) && facturaCommand.getTotalTarjeta().equals(Constantes.ZEROS_DOUBLE)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.ingreso.dinero", result.getAllErrors());
					}

				}
			}
			TipoCambio tipoCambio = null;
			facturaCommand.setTipoCambioMoneda(facturaCommand.getTipoCambioMoneda() == null ? Constantes.ZEROS_DOUBLE : facturaCommand.getTipoCambioMoneda());
			facturaCommand.setCodigoMoneda(facturaCommand.getCodigoMoneda() == null ? Constantes.CODIGO_MONEDA_COSTA_RICA : facturaCommand.getCodigoMoneda());
			if (!facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_PROFORMAS)) {
				if (!facturaCommand.getCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA)) {
					if (facturaCommand.getTipoCambioMoneda() == Constantes.ZEROS_DOUBLE) {
						tipoCambio = tipoCambioBo.findByEstadoAndEmpresa(Constantes.ESTADO_ACTIVO, usuario.getEmpresa());
						if (tipoCambio == null) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.no.hay.tipo.cambio.dolar.activo", result.getAllErrors());

						}
						facturaCommand.setTipoCambioMoneda(tipoCambio.getTotal());

					}

				}
			}
			if (!usuario.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA)) {
				if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA)) {
					if (facturaCommand.getCliente() == null) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.incluir.cliente", result.getAllErrors());
					}
				}

			}
			if (facturaCommand.getCliente() == null) {
				Cliente cliente = clienteBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_CLIENTE_FRECUENTE, usuario.getEmpresa());
				cliente = cliente == null ? clienteBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_CLIENTE_CREDITO, usuario.getEmpresa()) : cliente;

				if (cliente == null) {
					cliente = clienteBo.crearClienteFrecuente(usuario.getEmpresa(), usuario);
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.intente.nuevo", result.getAllErrors());
				} else {
					facturaCommand.setCliente(cliente);
				}

			}
			if (facturaCommand.getCliente() != null) {
				if (facturaCommand.getCliente().getIdentificacionExtranjero() != null) {
					if (!facturaCommand.getCliente().getIdentificacionExtranjero().equals(Constantes.EMPTY)) {
						if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA))
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.cedula.extrajera.only.tiquete", result.getAllErrors());
					}
				}

			}

			if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && !usuario.getEmpresa().getNoFacturaElectronica().equals(Constantes.NO_APLICA_FACTURA_ELECTRONICA)) {
				if (facturaCommand.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_FRECUENTE) || facturaCommand.getCliente().getNombreCompleto().equals(Constantes.NOMBRE_CLIENTE_CREDITO)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.tipo.documento.factura", result.getAllErrors());
				}
				if (facturaCommand.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_FRECUENTE) || facturaCommand.getCliente().getCedula().equals(Constantes.CEDULA_CLIENTE_CREDITO)) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.tipo.documento.factura", result.getAllErrors());
				}
			}

			if (facturaCommand.getCliente().getTipoDocumentoExoneracion() != null) {
				if (!facturaCommand.getCliente().getTipoDocumentoExoneracion().equals(Constantes.EMPTY)) {
					if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_ELECTRONICA) && facturaCommand.getCliente().getTipoDocumentoExoneracion().equals(Constantes.TIPO_EXONERACION_DIPLOTAMATICO)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.exoneracion.diplomatico", result.getAllErrors());
					}
					if (facturaCommand.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_TIQUETE) && !facturaCommand.getCliente().getTipoDocumentoExoneracion().equals(Constantes.TIPO_EXONERACION_DIPLOTAMATICO)) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.factura.exoneracion.libre.impuesto", result.getAllErrors());
					}
				}
			}

			if (facturaCommand.getVendedor() == null) {
				Vendedor vendedor = vendedorBo.buscarPorNombreCompletoYEmpresa(Constantes.NOMBRE_VENDEDOR_FRECUENTE, usuario.getEmpresa());
				if (vendedor == null) {
					vendedorBo.crearVendedorFrecuente(usuario.getEmpresa(), usuario);
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.intente.nuevo", result.getAllErrors());
				}
				facturaCommand.setVendedor(vendedor);

			}
			Factura facturaReferenciaValidar = null;
			// Validar el codigo de factura que se le va aplicar una nota de credito
			if (facturaCommand.getReferenciaNumero() != null) {
				if (!facturaCommand.getReferenciaNumero().equals(Constantes.EMPTY)) {
					facturaReferenciaValidar = facturaBo.findByConsecutivoAndEmpresa(facturaCommand.getReferenciaNumero(), usuario.getEmpresa());
					if (facturaReferenciaValidar != null) {
						if (facturaReferenciaValidar.getEmpresa().getNoFacturaElectronica().equals(Constantes.SI_APLICA_FACTURA_ELECTRONICA)) {
							Hacienda hacienda = haciendaBo.findByEmpresaAndClave(facturaReferenciaValidar.getEmpresa(), facturaReferenciaValidar.getClave());
							if (hacienda != null) {
								if (!hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA) && !hacienda.getEstado().equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
									return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.pendiente.comprobacion.hacienda", result.getAllErrors());
								}
							} else {
								return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.pendiente.comprobacion.hacienda", result.getAllErrors());
							}

						}

					}
					if (facturaReferenciaValidar == null) {
						return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("factura.error.factura.aplicar.nota.credito.o.debito.no.existe", result.getAllErrors());
					} else {
						facturaCommand.setReferenciaTipoDoc(facturaReferenciaValidar.getTipoDoc());

					}
				}
			}

			facturaCommand.setEmpresa(usuario.getEmpresa());
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (!facturaCommand.getCondicionVenta().equals(Constantes.FACTURA_CONDICION_VENTA_CREDITO)) {
				facturaCommand.setFechaCredito(null);
			}
			Factura facturaBD = facturaCommand.getId() == null || facturaCommand.getId() == Constantes.ZEROS_LONG ? null : facturaBo.findById(facturaCommand.getId());
			// Eliminar detalles si existe
			if (facturaBD != null) {
				facturaBo.eliminarDetalleFacturaPorSP(facturaBD);
				Collection<Detalle> detalles = detalleBo.findByFactura(facturaBD);
				for (Detalle detalle : detalles) {
					detalleBo.eliminar(detalle);
				}

			}

			Factura factura = facturaBo.crearFactura(facturaCommand, usuario, usuarioCajaBd, tipoCambio, detallesFacturaCommand, detallesNotaCredito);
			if (factura == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (usuarioCajaBd != null) {
				usuarioCajaBo.actualizarCaja(usuarioCajaBd);
			}
			List<Object> solicitudList = new ArrayList<Object>();
			if (factura.getEmpresa().getImprimirSiempre() == 1) {
				Collection<Detalle> objetos = detalleBo.findbyIdFactura(factura.getId());
				for (Detalle detalle : objetos) {
					solicitudList.add(new DetalleFacturaCommand(detalle));
				}

			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("factura.agregar.correctamente", solicitudList.size() > 0 ? solicitudList : factura);

		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}

	}
	

	
	@SuppressWarnings("rawtypes")
	@GetMapping("/tipoCambioBancoCentral.do")
	public RespuestaServiceValidator tipoCambioBancoCentralLocal(HttpServletRequest request, HttpServletResponse response,BindingResult result) {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
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

	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/ListarFacturasDelDiaAjax.do")
	public RespuestaServiceDataTable listarFacturasDiaAjax(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Integer idUsuario = Constantes.ZEROS;
		if (usuarioBo.isAdministrador_cajero(usuarioSesion) || usuarioBo.isAdministrador_empresa(usuarioSesion) || usuarioBo.isAdministrador_restaurante(usuarioSesion) || usuarioBo.isAdministrador_vendedor(usuarioSesion)) {
			idUsuario = Constantes.ZEROS;
		} else {
			idUsuario = usuarioSesion.getId();
		}

		Date fechahoy = new Date();
		DateFormat df = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String reportDate = df.format(fechahoy);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Collection<FacturasDelDiaNative> objetos = consultasNativeBo.findByFacturasDelDia(usuarioSesion.getEmpresa(), idUsuario, "(" + Constantes.FACTURA_ESTADO_FACTURADO + "," + Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA + "," + Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO + ")", reportDate);
		List<Object> solicitudList = new ArrayList<Object>();
		if (objetos != null) {
			for (FacturasDelDiaNative facturasDelDia : objetos) {
				if (facturasDelDia.getId().longValue() > 0L) {
					solicitudList.add(new FacturaDiaCommand(facturasDelDia));
				}
			}

		}

		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getFacturaByIdFactura")
	public RespuestaServiceDataTable getFacturaByidFacturaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idFactura) {

		@SuppressWarnings("unused")
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<Detalle> objetos = detalleBo.findbyIdFactura(idFactura);
		for (Detalle detalle : objetos) {
			solicitudList.add(new DetalleFacturaCommand(detalle));
		}
		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getFacturaByConsecutivo")
	public RespuestaServiceDataTable listarConsecutivoAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String consecutivo) {

		// Usuario de la session
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<Detalle> objetos = detalleBo.findbyConsecutivoAndEmpresa(consecutivo, usuarioSesion.getEmpresa());
		for (Detalle detalle : objetos) {
			solicitudList.add(new DetalleFacturaCommand(detalle));
		}
		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}
	
	
	
}

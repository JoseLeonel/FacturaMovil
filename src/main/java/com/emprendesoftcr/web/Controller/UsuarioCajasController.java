package com.emprendesoftcr.web.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CajaBo;
import com.emprendesoftcr.Bo.ConteoManualCajaBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.SalidaEntradaDineroBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.UsuarioCajaBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Caja;
import com.emprendesoftcr.modelo.ConteoManualCaja;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.SalidaEntradaDinero;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.UsuarioCaja;
import com.emprendesoftcr.web.command.ConteoManualCommand;
import com.emprendesoftcr.web.command.DenominacionCommand;
import com.emprendesoftcr.web.command.UsuarioCajaCommand;
import com.emprendesoftcr.web.propertyEditor.CajaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioCajaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.UsuarioPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;

/**
 * Abrir caja para facturar UsuarioCajasController.
 * @author jose.
 * @since 11 jun. 2018
 */
@Controller
public class UsuarioCajasController {

	private static final Function<Object, UsuarioCajaCommand>	TO_COMMAND_CAJAS_ABIERTAS_CERRADAS	= new Function<Object, UsuarioCajaCommand>() {

																																																	@Override
																																																	public UsuarioCajaCommand apply(Object f) {
																																																		return new UsuarioCajaCommand((UsuarioCaja) f);
																																																	};
																																																};

	@Autowired
	private DataTableBo																				dataTableBo;

	@Autowired
	private ConteoManualCajaBo																conteoManualCajaBo;

	@Autowired
	private SalidaEntradaDineroBo															salidaEntradaDineroBo;

	@Autowired
	private CajaBo																						cajaBo;

	@Autowired
	private UsuarioCajaBo																			usuarioCajaBo;

	@Autowired
	private UsuarioBo																					usuarioBo;

	@Autowired
	CorreosBo																									correosBo;

	@Autowired
	private EmpresaPropertyEditor															empresaPropertyEditor;

	@Autowired
	private CajaPropertyEditor																cajaPropertyEditor;

	@Autowired
	UsuarioCajaPropertyEditor																	usuarioCajaPropertyEditor;

	@Autowired
	UsuarioPropertyEditor																			usuarioPropertyEditor;

	@Autowired
	private StringPropertyEditor															stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Caja.class, cajaPropertyEditor);
		binder.registerCustomEditor(Usuario.class, usuarioPropertyEditor);
		binder.registerCustomEditor(UsuarioCaja.class, usuarioCajaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Pantalla para abrir la caja un usuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/AbrirCajas", method = RequestMethod.GET)
	public String abrirCajas(ModelMap model) {
		return "views/caja/abrirCajas";
	}

	@RequestMapping(value = "/Configuracion.do", method = RequestMethod.GET)
	public String configuracion(ModelMap model) {
		return "views/caja/configuracion";
	}

	@RequestMapping(value = "/CerrarCaja.do", method = RequestMethod.GET)
	public String cerrarCajas(ModelMap model) {
		return "views/caja/cerrarCajas";
	}

	@RequestMapping(value = "/ListarCajasInactivas", method = RequestMethod.GET)
	public String liasCajas(ModelMap model) {
		return "views/caja/ListarCajasInactivas";
	}

	/**
	 * Lista de las cajas creadas por usuario
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarUsuariosCajasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarUsuariosCajasAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "UsuarioCaja");
		
		
		JqGridFilter dataTableFilter = null;
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		UsuarioCaja usuarioCajaBd = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);
		if(usuarioCajaBd != null) {
			usuarioCajaBo.actualizarCaja(usuarioCajaBd);	
		}
		
		
		if (usuarioBo.isUsuario_Vendedor(usuario) || usuarioBo.isUsuario_Cajero(usuario) || usuarioBo.isUsuario_Mesero(usuario)) {
			dataTableFilter = new JqGridFilter("usuario.id", "'" + usuario.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}
		// Se incluye la empresa
		dataTableFilter = new JqGridFilter("caja.empresa.id", "'" + usuario.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<UsuarioCaja> objetos = usuarioCajaBo.usuarioCajaBy(usuario.getEmpresa(), Constantes.ESTADO_ACTIVO);
		if (objetos != null) {
			for (UsuarioCaja usuarioCaja : objetos) {
				if (usuarioCaja.getId().longValue() > 0L) {
					if (usuarioBo.isAdministrador_cajero(usuario) || usuarioBo.isAdministrador_empresa(usuario) || usuarioBo.isAdministrador_restaurante(usuario)) {
						solicitudList.add(new UsuarioCajaCommand(usuarioCaja));
					}else {
						if (usuarioCaja.getUsuario().getId().equals(usuario.getId())){
							usuarioCaja.setTotalNeto(Constantes.ZEROS_DOUBLE);
						//	usuarioCaja.settotal
							solicitudList.add(new UsuarioCajaCommand(usuarioCaja));
						}
					}

				}
			}

		}
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();

		respuestaService.setRecordsTotal((long) solicitudList.size());
		respuestaService.setRecordsFiltered((long) solicitudList.size());
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarUsuariosCajasCerradasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarUsuariosCajasCerradasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String inicio, @RequestParam String fin, @RequestParam Integer idUsuario) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "UsuarioCaja");
		JqGridFilter dataTableFilter = null;
		Usuario usuario = usuarioBo.buscar(idUsuario);
		if (usuarioBo.isUsuario_Vendedor(usuario) || usuarioBo.isUsuario_Cajero(usuario) || usuarioBo.isUsuario_Mesero(usuario)) {
			dataTableFilter = new JqGridFilter("usuario.id", "'" + usuario.getId().toString() + "'", "=");
			delimitadores.addFiltro(dataTableFilter);
		}
		// Se incluye la empresa
		dataTableFilter = new JqGridFilter("caja.empresa.id", "'" + usuario.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		Date fechaInicio = new Date();
		Date fechaFinal = new Date();
		if (!inicio.equals(Constantes.EMPTY) && !fin.equals(Constantes.EMPTY)) {
			fechaInicio = Utils.parseDate(inicio);
			fechaFinal = Utils.parseDate(fin);
			if (fechaFinal == null) {
				fechaFinal = new Date(System.currentTimeMillis());
			}
			if (fechaFinal != null && fechaFinal != null) {
				fechaFinal = Utils.sumarDiasFecha(fechaFinal, 1);
			}

			DateFormat dateFormat = new SimpleDateFormat(Constantes.DATE_FORMAT7);

			inicio = dateFormat.format(fechaInicio);
			fin = dateFormat.format(fechaFinal);

			delimitadores.addFiltro(new JqGridFilter("created_at", inicio, "date>="));
			delimitadores.addFiltro(new JqGridFilter("created_at", fin, "dateFinal<="));
		}

		dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_INACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_CAJAS_ABIERTAS_CERRADAS);
	}

	@RequestMapping(value = "/AgregarUsuarioCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute ConteoManualCommand conteoManualCommand, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.findByUsuarioAndEstado(usuario, Constantes.ESTADO_ACTIVO);
			if (usuarioCajaBd != null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.usuarioCaja.totalFondoInicial.existe.activo", result.getAllErrors());
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			ArrayList<DenominacionCommand> listaCoteo = new ArrayList<>();
			listaCoteo = denominacionCommand(conteoManualCommand);
			Caja caja = cajaBo.buscarCajaActiva(usuario.getEmpresa(),usuario);
			UsuarioCaja usuarioCaja = usuarioCajaBo.aperturaCaja(listaCoteo, usuario, caja);
			UsuarioCaja usuarioCajaBd1 = usuarioCajaBo.buscar(usuarioCaja.getId());
			UsuarioCajaCommand usuarioCajaCommand = new UsuarioCajaCommand(usuarioCajaBd1);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.agregar.correctamente", usuarioCajaCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	public ArrayList<DenominacionCommand> denominacionCommand(ConteoManualCommand conteoManualCommand) throws Exception {
		JSONObject json = null;
		ArrayList<DenominacionCommand> denominacionCommands = new ArrayList<>();
		try {
			json = (JSONObject) new JSONParser().parse(conteoManualCommand.getDenominacion());
			// Agregar Lineas
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();
			if (jsonArrayDetalleFactura != null) {
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					DenominacionCommand denominacionCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), DenominacionCommand.class);
					denominacionCommands.add(denominacionCommand);
				}
			}
		} catch (org.json.simple.parser.ParseException e) {
			throw e;
		}
		return denominacionCommands;
	}

	/**
	 * Cerrar la cja
	 * @param request
	 * @param model
	 * @param usuarioCaja
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/CerrarUsuarioCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cerrarCaja(HttpServletRequest request, ModelMap model, @ModelAttribute ConteoManualCommand conteoManualCommand, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.buscar(conteoManualCommand.getId());

			if (usuarioCajaBd == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.usuarioCaja.noExiste", result.getAllErrors());
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			ArrayList<DenominacionCommand> listaCoteo = new ArrayList<>();
			listaCoteo = denominacionCommand(conteoManualCommand);
			
			usuarioCajaBo.eliminarConteo(usuarioCajaBd,2);
			// Se acutalizan los registros
			usuarioCajaBo.actualizarCaja(usuarioCajaBd);

			usuarioCajaBd.setConteoTarjeta(conteoManualCommand.getConteoTarjeta() == null ? Constantes.ZEROS_DOUBLE : conteoManualCommand.getConteoTarjeta());

			usuarioCajaBd.setConteoDolar(conteoManualCommand.getConteoDolar() == null ? Constantes.ZEROS_DOUBLE : conteoManualCommand.getConteoDolar());

			usuarioCajaBd.setTipoCambio(conteoManualCommand.getTipoCambio() == null ? Constantes.ZEROS_DOUBLE : conteoManualCommand.getTipoCambio());

			usuarioCajaBd.setConteoManual(Constantes.ZEROS_DOUBLE);

			// Se cierra la caja
			usuarioCajaBo.cierreCaja(usuarioCajaBd, listaCoteo, usuario);

			UsuarioCajaCommand usuarioCajaCommand = new UsuarioCajaCommand(usuarioCajaBd);

			enviarCorreoCierreCaja(usuarioCajaCommand, usuario);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.cierre.correctamente", usuarioCajaCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private void enviarCorreoCierreCaja(UsuarioCajaCommand usuarioCajaCommand, Usuario usuario) throws Exception {
		Map<String, Object> modelEmail = new HashMap<>();
		ArrayList<String> listaCorreos = new ArrayList<>();

		if (usuario.getEmpresa().getCorreoCaja1() != null) {
			if(!usuario.getEmpresa().getCorreoCaja1().isEmpty()) {
				listaCorreos.add(usuario.getEmpresa().getCorreoCaja1());	
			}
			
		}
		if (usuario.getEmpresa().getCorreoCaja2() != null) {
			if(!usuario.getEmpresa().getCorreoCaja2().isEmpty()) {
				listaCorreos.add(usuario.getEmpresa().getCorreoCaja2());	
			}
			
		}

		if (!listaCorreos.isEmpty()) {

			UsuarioCaja usuarioCajaBd = usuarioCajaBo.buscar(usuarioCajaCommand.getId());
			UsuarioCajaCommand usuarioCajaCommand1 = new UsuarioCajaCommand(usuarioCajaBd);
			String nombreUsuario = usuarioCajaCommand1.getUsuario().getNombre() == null ? Constantes.EMPTY : usuarioCajaCommand1.getUsuario().getNombre().trim();
			String apellido1 = usuarioCajaCommand.getUsuario().getPrimerApellido() == null ? Constantes.EMPTY : usuarioCajaCommand.getUsuario().getPrimerApellido().trim();
			String apellido2 = usuarioCajaCommand.getUsuario().getSegundoApellido() == null ? Constantes.EMPTY : usuarioCajaCommand.getUsuario().getSegundoApellido().trim();
			modelEmail.put("usuarioResponsable", nombreUsuario + " " + apellido1 + " " + apellido2);
			modelEmail.put("fechaApertura", usuarioCajaCommand1.getCreated_atSTR());
			modelEmail.put("nombreComercial", usuarioCajaBd.getCaja().getEmpresa().getNombreComercial());
			modelEmail.put("nombreEmpresa", usuarioCajaBd.getCaja().getEmpresa().getNombre());
			modelEmail.put("cedula", usuarioCajaBd.getCaja().getEmpresa().getCedula());
			modelEmail.put("fondoInicial", usuarioCajaCommand1.getTotalFondoInicialSTR());
			modelEmail.put("fechaCierre", usuarioCajaCommand1.getCierreCajaSTR());
			modelEmail.put("conteoCierre", usuarioCajaCommand1.getConteoManualSTR());
			modelEmail.put("totalEfectivo", usuarioCajaCommand1.getTotalEfectivoSTR());
			modelEmail.put("totalTarjeta", usuarioCajaCommand1.getTotalTarjetaSTR());
			modelEmail.put("totalBanco", usuarioCajaCommand1.getTotalBancoSTR());
			modelEmail.put("totalAbonos", usuarioCajaCommand1.getTotalAbonoSTR());
			modelEmail.put("totalVentas", usuarioCajaCommand1.getTotalNetoSTR());
			modelEmail.put("totalEntradas", usuarioCajaCommand1.getSumaEntradasSTR());
			modelEmail.put("totalSalidas", usuarioCajaCommand1.getSumaSalidaSTR());
			modelEmail.put("datafonoSTR", usuarioCajaBd.getDatafonoSTR());
			modelEmail.put("conteoDolarConversionSTR", usuarioCajaCommand1.getConteoDolarConversionSTR());

			modelEmail.put("totalDolaresSTR", usuarioCajaCommand1.getTotalDolaresSTR());
			modelEmail.put("totalGeneralSTR", usuarioCajaCommand1.getTotalGeneralSTR());
			modelEmail.put("totalCierreSTR", usuarioCajaCommand1.getTotalCierreSTR());
			modelEmail.put("conteoManualSTR", usuarioCajaCommand1.getConteoManualSTR());
			modelEmail.put("diferenciaSTR", usuarioCajaCommand1.getDiferenciaSTR());
			modelEmail.put("diferenciaFinalSTR", usuarioCajaCommand1.getDiferenciaFinalSTR());
			modelEmail.put("totalDolares", usuarioCajaCommand1.getConteoDolarSTR());
			modelEmail.put("totalAbonoSTR", usuarioCajaCommand1.getTotalAbonoSTR());
			modelEmail.put("tipoCambio", usuarioCajaCommand1.getTipoCambioSTR());
			modelEmail.put("totalServicio", usuarioCajaCommand1.getTotalServicioSTR());
			modelEmail.put("idCaja", usuarioCajaCommand1.getId());
			Collection<ConteoManualCaja> conteoCierre = conteoManualCajaBo.buscarPorUsuarioCaja(usuarioCajaBd, Constantes.CONTEO_CIERRE_CAJA_TIPO);
			modelEmail.put("conteoCierres", conteoCierre);
			Collection<ConteoManualCaja> conteoApertura = conteoManualCajaBo.buscarPorUsuarioCaja(usuarioCajaBd, Constantes.CONTEO_APERTURA_CAJA_TIPO);

			modelEmail.put("conteoAperturas", conteoApertura);

			Collection<SalidaEntradaDinero> salidas = salidaEntradaDineroBo.buscarPorUsuarioCajaAndTipo(usuarioCajaBd, Constantes.ENTRADASALIDA_TIPO_SALIDA);
			Collection<SalidaEntradaDinero> entradas = salidaEntradaDineroBo.buscarPorUsuarioCajaAndTipo(usuarioCajaBd, Constantes.ENTRADASALIDA_TIPO_ENTRADA);

			modelEmail.put("entradas", entradas);
			modelEmail.put("salidas", salidas);

			Collection<Attachment> attachments = null;
			String from = "CierreCaja@emprendesoftcr.com";
			String subject = "Cierre Caja-" + usuarioCajaBd.getCaja().getEmpresa().getAbreviaturaEmpresa() + " Apertura :" + usuarioCajaCommand.getCreated_atSTR() + " Cierre: " + usuarioCajaCommand.getCierreCajaSTR();

			correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_CIERRE_CAJA, modelEmail);
		}

	}

	@RequestMapping(value = "/ActualizarUsuarioCajaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator actualizarCaja(HttpServletRequest request, ModelMap model, @ModelAttribute UsuarioCaja usuarioCaja, BindingResult result, SessionStatus status, @RequestParam Long idUsuarioCaja) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.buscar(idUsuarioCaja);

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			// Se acutalizan los registros
			usuarioCajaBo.actualizarCaja(usuarioCajaBd);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.cierre.correctamente", usuarioCajaBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Mostrar el usuario caja
	 * @param request
	 * @param model
	 * @param usuarioCaja
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarUsuarioCajaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute UsuarioCaja usuarioCaja, BindingResult result, SessionStatus status) throws Exception {
		try {
			UsuarioCaja usuarioCajaBd = usuarioCajaBo.buscar(usuarioCaja.getId());
			usuarioCajaBo.actualizarCaja(usuarioCajaBd);
			UsuarioCajaCommand usuarioCajaCommand = new UsuarioCajaCommand(usuarioCajaBo.buscar(usuarioCaja.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", usuarioCajaCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@RequestMapping(value = "/AgrupaArticulosCategoriaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	@SuppressWarnings({ "rawtypes" })
	public RespuestaServiceValidator agrupaArticulosCategoria(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idUsuarioCaja) {
		UsuarioCaja usuarioCaja = usuarioCajaBo.buscar(idUsuarioCaja);
		return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", usuarioCajaBo.agrupaArticulosCategoria(usuarioCaja.getCaja().getEmpresa().getId(), usuarioCaja.getId()));
	}

	/**
	 * RESPONSES.
	 * @author jose.
	 * @since 11 jun. 2018
	 */
	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class USUARIOCAJA {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("usuarioCaja.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class USUARIOCAJA {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.usuarioCaja.noExiste");
			}
		}
	}

}

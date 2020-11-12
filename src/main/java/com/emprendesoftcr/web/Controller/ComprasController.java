package com.emprendesoftcr.web.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.emprendesoftcr.Bo.CompraBo;
import com.emprendesoftcr.Bo.CorreosBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.DetalleCompraBo;
import com.emprendesoftcr.Bo.EmpresaBo;
import com.emprendesoftcr.Bo.IFEMensajeReceptorAutomaticoBo;
import com.emprendesoftcr.Bo.ProveedorBo;
import com.emprendesoftcr.Bo.RecepcionFacturaBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Attachment;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Compra;
import com.emprendesoftcr.modelo.DetalleCompra;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.FEMensajeReceptorAutomatico;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.RecepcionFactura;
import com.emprendesoftcr.modelo.RecepcionFacturaDetalle;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.CompraCommand;
import com.emprendesoftcr.web.command.CompraEsperaCommand;
import com.emprendesoftcr.web.command.ComprasSinIngresarInventarioCommand;
import com.emprendesoftcr.web.command.ConsultaComprasIvaCommand;
import com.emprendesoftcr.web.command.DetalleCompraEsperaCommand;
import com.emprendesoftcr.web.command.DetalleCompraSinIngresaCommand;
import com.emprendesoftcr.web.command.EtiquetasCommand;
import com.emprendesoftcr.web.command.TotalComprasAceptadasCommand;
import com.emprendesoftcr.web.command.VectorCompras;
import com.emprendesoftcr.web.propertyEditor.ClientePropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.FechaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Compras realizadas por la empresa y ingresan al inventario ComprasController.
 * @author jose.
 * @since 21 may. 2018
 */
@Controller
public class ComprasController {

	private static final Function<Object, CompraEsperaCommand>				TO_COMMAND					= new Function<Object, CompraEsperaCommand>() {

																																													@Override
																																													public CompraEsperaCommand apply(Object f) {
																																														return new CompraEsperaCommand((Compra) f);
																																													};
																																												};

	private static final Function<Object, DetalleCompraEsperaCommand>	TO_COMMAND_DETALLE	= new Function<Object, DetalleCompraEsperaCommand>() {

																																													@Override
																																													public DetalleCompraEsperaCommand apply(Object f) {
																																														return new DetalleCompraEsperaCommand((DetalleCompra) f);
																																													};
																																												};

	@Autowired
	private DataTableBo																								dataTableBo;

	@Autowired
	private RecepcionFacturaBo																				recepcionFacturaBo;
	private Logger																										log									= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioBo																									usuarioBo;

	@Autowired
	private ProveedorBo																								proveedorBo;

	@Autowired
	private CorreosBo																									correosBo;

	@Autowired
	private CompraBo																									compraBo;

	@Autowired
	private IFEMensajeReceptorAutomaticoBo														ifEMensajeReceptorAutomaticoBo;
	@Autowired
	private DetalleCompraBo																						detalleCompraBo;

	@Autowired
	private EmpresaBo																									empresaBo;
	
	
	@Autowired
	private EmpresaPropertyEditor																			empresaPropertyEditor;

	@Autowired
	private ProveedorPropertyEditor																		proveedorPropertyEditor;

	@Autowired
	private StringPropertyEditor																			stringPropertyEditor;

	@Autowired
	private FechaPropertyEditor																				fechaPropertyEditor;

	@Autowired
	private ClientePropertyEditor																			clientePropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Proveedor.class, proveedorPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
		binder.registerCustomEditor(Date.class, fechaPropertyEditor);
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);

	}

	@RequestMapping(value = "/ListaCompras", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/compras/ListarCompras";
	}

	@RequestMapping(value = "/ListaComprasAnular", method = RequestMethod.GET)
	public String listarComprasAnular(ModelMap model) {
		return "views/compras/ListarComprasAnulacion";
	}

	@RequestMapping(value = "/totalesComprasAceptadas", method = RequestMethod.GET)
	public String totalesAceptacionCompras(ModelMap model) {
		return "views/compras/totalesComprasAceptadas";
	}

	/**
	 * Listado de facturas anuladas y facturadas
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListaRecepcionFacturas", method = RequestMethod.GET)
	public String listaRecepcionFacturas(ModelMap model) {
		return "views/facturas/listaRecepcionFacturas";
	}

	@RequestMapping(value = "/ComprasSinAceptarInventario", method = RequestMethod.GET)
	public String comprasSinAceptarInventario(ModelMap model) {
		return "views/compras/comprasPendienteAceptarInventario";
	}

	/**
	 * recepcion de Facturas del servidor de revision de correos por cliente
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/recepcionComprasPorCorreo", method = RequestMethod.GET)
	public String recepcionComprasPorCorreo(ModelMap model) {
		return "views/compras/recepcionCompras";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listarRecepcionCompras.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarRecepcionCompras(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<FEMensajeReceptorAutomatico> solicitudList = ifEMensajeReceptorAutomaticoBo.getAll("P", usuarioSesion.getEmpresa().getCedula());
		respuestaService.setAaData(solicitudList);
		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		// respuestaService.setAaData(null);
		return respuestaService;
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/recepcionComprasMasivas.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarComprasMasivas(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam("listaCompras") String listaCompras, @ModelAttribute EtiquetasCommand EtiquetasCommand1, BindingResult result, SessionStatus status) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		RecepcionFactura recepcionFactura = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(listaCompras);
			String decodedString = new String(decodedBytes);
			List<VectorCompras> lista = new ArrayList<VectorCompras>();
			Gson gson = new Gson();

			JSONArray jsonArrayDetalle = obtenerJsonArray("dataFactura", decodedString);

			JSONObject json = null;

			VectorCompras comprasReceptorAutomatico = new VectorCompras();

			// comprasReceptorAutomatico = gson.fromJson(json.toString(), ComprasReceptorAutomatico.class);
			if (jsonArrayDetalle != null && !jsonArrayDetalle.isEmpty()) {
				for (int i = 0; i < jsonArrayDetalle.size(); i++) {
					System.out.println(jsonArrayDetalle.get(i).toString());
					json = (JSONObject) new JSONParser().parse(jsonArrayDetalle.get(i).toString());
					// String recepcionFacturasAutomaticas = gson.fromJson(jsonArrayDetalle.get(i).toString(), String.class);
					comprasReceptorAutomatico = gson.fromJson(json.toString(), VectorCompras.class);
					recepcionFactura = gson.fromJson(comprasReceptorAutomatico.getRecepcionFactura(), RecepcionFactura.class);
					recepcionFactura.setId(null);
					JSONArray jsonArrayDetalleCompras = obtenerJsonArray("data", recepcionFactura.getDetalles());

					respuestaServiceValidator = crearFacturaAutomaticaCompras(request, recepcionFactura, jsonArrayDetalleCompras, result, status, Constantes.APLICADO_RECEPCION_AUTOMATICA_SI);
				
					

				}
			}
			System.out.println("decodedString ============================ > " + recepcionFactura);
			return respuestaServiceValidator;
		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}

	}

	private JSONArray obtenerJsonArray(String valor, String jsonString) {

		JSONObject json = null;
		JSONArray jsonArrayDetalle = null;

		try {
			try {
				json = (JSONObject) new JSONParser().parse(jsonString);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			jsonArrayDetalle = (JSONArray) json.get(valor);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonArrayDetalle;
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarComprasSinIngresarInventarioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarComprasSinIngresarInventario(HttpServletRequest request,  SessionStatus status) throws Exception {
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		List<Map<String, Object>> listaObjetos = compraBo.comprasSinIngresarInventario(usuarioSesion.getEmpresa());
		@SuppressWarnings("rawtypes")
		ArrayList arrayList = new ArrayList();
		arrayList = (ArrayList) listaObjetos;
		JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
		ArrayList<ComprasSinIngresarInventarioCommand> detallesFacturaCommand = new ArrayList<>();
		Gson gson = new Gson();
		if (jsonArray1 != null) {
			for (int i = 0; i < jsonArray1.size(); i++) {
				ComprasSinIngresarInventarioCommand comprasSinIngresarInventarioCommand = gson.fromJson(jsonArray1.get(i).toString(), ComprasSinIngresarInventarioCommand.class);
				detallesFacturaCommand.add(comprasSinIngresarInventarioCommand);
			}
		}
		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(detallesFacturaCommand);
		return respuestaService;

	}
	/**
	 * retorna los detalles de una compra vrs articulos del proveedor para ingresar el inventario
	 * @param request
	 * @param response
	 * @param model
	 * @param compra
	 * @param idCompra
	 * @param result
	 * @param status
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarDetalleComprasSinIngresarInventarioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarDetalleComprasSinIngresarInventario(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Compra compra,  @RequestParam Long idCompra, BindingResult result, SessionStatus status){
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		List<Map<String, Object>> listaObjetos = detalleCompraBo.detalleCompraSinIngresar(idCompra);
		@SuppressWarnings("rawtypes")
		ArrayList arrayList = new ArrayList();
		arrayList = (ArrayList) listaObjetos;
		JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
		ArrayList<DetalleCompraSinIngresaCommand> detallesFacturaCommand = new ArrayList<>();
		Gson gson = new Gson();
		if (jsonArray1 != null) {
			for (int i = 0; i < jsonArray1.size(); i++) {
				DetalleCompraSinIngresaCommand detalleCompraSinIngresaCommand = gson.fromJson(jsonArray1.get(i).toString(), DetalleCompraSinIngresaCommand.class);
				detallesFacturaCommand.add(detalleCompraSinIngresaCommand);
			}
		}
		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(detallesFacturaCommand);
		return respuestaService;

	}
	
	
	
/**
 * 
 * @param request
 * @param response
 * @param model
 * @param compra
 * @param idCompra
 * @param idDetalleCompra
 * @param codigoInventario
 * @param gananciaPrecioPublico
 * @param precioPublico
 * @param codigoProveedor
 * @param result
 * @param status
 * @return
 * @throws Exception
 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/actualizarDetalleCompraPorAutomatica.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator actualizarDetalleCompraPorAutomatica(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Compra compra,  @RequestParam Long idCompra,  @RequestParam Long idDetalleCompra,  @RequestParam String codigoInventario ,  @RequestParam Double gananciaPrecioPublico,  @RequestParam Double precioPublico,@RequestParam String codigoProveedor, BindingResult result, SessionStatus status) throws Exception{
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		try {
			Integer resultado = compraBo.actualizarCompraAutomaticaPorDetallle(idCompra, idDetalleCompra, precioPublico, gananciaPrecioPublico,  codigoInventario, usuarioSesion.getEmpresa(),codigoProveedor);	
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.actualizo.detalle.correctamente", resultado);
			} catch (Exception e) {
				return RespuestaServiceValidator.ERROR(e);
			}
		
		

	}
	

	/**
	 * Recibir factura de otro emisor
	 * @param request
	 * @param model
	 * @param recepcionFactura
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarRecepcionFacturaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarRecepcionFactura(HttpServletRequest request, ModelMap model, @ModelAttribute RecepcionFactura recepcionFactura, BindingResult result, SessionStatus status) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {

			// Agregar Lineas de Detalle
			if (recepcionFactura.getFacturaConsecutivo() != null && !recepcionFactura.getFacturaConsecutivo().equals(Constantes.EMPTY)) {
				recepcionFactura.setTipoDocEmisor(Utils.obtenerTipoDocumentoConsecutivo(recepcionFactura.getFacturaConsecutivo()));
			}
			JSONArray jsonArrayDetalleCompras = obtenerJsonArray("data", recepcionFactura.getDetalles());
			return crearFacturaAutomaticaCompras(request, recepcionFactura, jsonArrayDetalleCompras, result, status, Constantes.APLICADO_RECEPCION_AUTOMATICA_NO);

		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}

	}

	/**
	 * @param request
	 * @param recepcionFactura
	 * @param jsonArrayDetalle
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	private RespuestaServiceValidator<?> crearFacturaAutomaticaCompras(HttpServletRequest request, RecepcionFactura recepcionFactura, JSONArray jsonArrayDetalle, BindingResult result, SessionStatus status, Integer tipoIngreso) throws Exception {
		@SuppressWarnings("rawtypes")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			List<RecepcionFacturaDetalle> detallesCompra = new ArrayList<RecepcionFacturaDetalle>();
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			recepcionFactura.setEmpresa(usuarioSesion.getEmpresa());
			if (recepcionFactura.getFacturaConsecutivo() != null && !recepcionFactura.getFacturaConsecutivo().equals(Constantes.EMPTY)) {
				recepcionFactura.setTipoDocEmisor(Utils.obtenerTipoDocumentoConsecutivo(recepcionFactura.getFacturaConsecutivo()));
			}

			recepcionFactura.setMensaje(recepcionFactura.getMensaje() != null && recepcionFactura.getMensaje().equals(Constantes.EMPTY) ? "Compra aceptada" : recepcionFactura.getMensaje());
			// Se validan los datos
			if (recepcionFactura.getMensaje() != null && (!recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_ACEPTADO) && !recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_ACEPTADO_PARCIAL) && !recepcionFactura.getMensaje().equals(Constantes.RECEPCION_FACTURA_MENSAJE_RECHAZADO))) {
				result.rejectValue("mensaje", "error.recepcionFactura.mensaje.requerido");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.recepcionFactura.factura.otro.receptor");
			} else if (!usuarioSesion.getEmpresa().getCedula().trim().toUpperCase().equals(recepcionFactura.getReceptorCedula().trim().toUpperCase())) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.recepcionFactura.factura.otro.receptor");
			} else {
				Collection<RecepcionFactura> resultado = recepcionFacturaBo.findByClave(recepcionFactura.getEmisorCedula(), recepcionFactura.getFacturaClave());
				if (resultado != null && resultado.size() > 0) {
					return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.recepcionFactura.ya.exite");

				}
			}
			recepcionFactura.setFacturaCodigoMoneda(recepcionFactura.getFacturaCodigoMoneda() == null ? Constantes.EMPTY : recepcionFactura.getFacturaCodigoMoneda());
			if (recepcionFactura.getFacturaCodigoMoneda().equals(Constantes.EMPTY) && recepcionFactura.getFacturaCodigoMoneda() != null) {
				recepcionFactura.setFacturaCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				recepcionFactura.setFacturaTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
			}

			recepcionFactura.setFacturaTipoCambio(recepcionFactura.getFacturaTipoCambio() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTipoCambio());

			if (recepcionFactura.getFacturaTipoCambio().equals(Constantes.ZEROS_DOUBLE)) {
				recepcionFactura.setFacturaCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				recepcionFactura.setFacturaTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
			}
			Gson gson = new Gson();
			if (jsonArrayDetalle != null && !jsonArrayDetalle.isEmpty()) {
				for (int i = 0; i < jsonArrayDetalle.size(); i++) {
					RecepcionFacturaDetalle detalle = gson.fromJson(jsonArrayDetalle.get(i).toString(), RecepcionFacturaDetalle.class);
					RecepcionFacturaDetalle detalleNuevo = new RecepcionFacturaDetalle();

					detalleNuevo.setNumeroLinea(detalle.getNumeroLinea() == null ? Constantes.ZEROS : detalle.getNumeroLinea());
					detalleNuevo.setCantidad(detalle.getCantidad() == null ? Constantes.ZEROS_DOUBLE : detalle.getCantidad());
					detalleNuevo.setUnidadMedida(detalle.getUnidadMedida() == null ? Constantes.EMPTY : detalle.getUnidadMedida());
					detalleNuevo.setDetalle(detalle.getDetalle() == null ? Constantes.EMPTY : detalle.getDetalle());
					detalleNuevo.setPrecioUnitario(detalle.getPrecioUnitario() == null ? Constantes.ZEROS_DOUBLE : detalle.getPrecioUnitario());
					detalleNuevo.setMontoTotal(detalle.getMontoTotal() == null ? Constantes.ZEROS_DOUBLE : detalle.getMontoTotal());
					detalleNuevo.setSubTotal(detalle.getSubTotal() == null ? Constantes.ZEROS_DOUBLE : detalle.getSubTotal());
					detalleNuevo.setMontoTotalLinea(detalle.getMontoTotalLinea() == null ? Constantes.ZEROS_DOUBLE : detalle.getMontoTotalLinea());
					detalleNuevo.setImpuestoNeto(detalle.getImpuestoNeto() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoNeto());
					detalleNuevo.setCodigoComercialTipo(detalle.getCodigoComercialTipo() == null ? Constantes.EMPTY : detalle.getCodigoComercialTipo());
					detalleNuevo.setCodigoComercialCodigo(detalle.getCodigoComercialCodigo() == null ? Constantes.EMPTY : detalle.getCodigoComercialCodigo());
					detalleNuevo.setDescuentoMonto(detalle.getDescuentoMonto() == null ? Constantes.ZEROS_DOUBLE : detalle.getDescuentoMonto());
					detalleNuevo.setDescuentoNaturaleza(detalle.getDescuentoNaturaleza() == null ? Constantes.EMPTY : detalle.getDescuentoNaturaleza());
					detalleNuevo.setImpuestoExoneracionTipoDocumento(detalle.getImpuestoExoneracionTipoDocumento() == null ? Constantes.EMPTY : detalle.getImpuestoExoneracionTipoDocumento());
					detalleNuevo.setImpuestoExoneracionNumeroDocumento(detalle.getImpuestoExoneracionNumeroDocumento() == null ? Constantes.EMPTY : detalle.getImpuestoExoneracionNumeroDocumento());
					detalleNuevo.setImpuestoExoneracionNombreInstitucion(detalle.getImpuestoExoneracionNombreInstitucion() == null ? Constantes.EMPTY : detalle.getImpuestoExoneracionNombreInstitucion());
					detalleNuevo.setImpuestoExoneracionFechaEmision(detalle.getImpuestoExoneracionFechaEmision() == null ? Constantes.EMPTY : detalle.getImpuestoExoneracionFechaEmision());
					detalleNuevo.setImpuestoExoneracionPorcentaje(detalle.getImpuestoExoneracionPorcentaje() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoExoneracionPorcentaje());
					detalleNuevo.setImpuestoExoneracionMonto(detalle.getImpuestoExoneracionMonto() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoExoneracionMonto());
					detalleNuevo.setBaseImponible(detalle.getBaseImponible() == null ? Constantes.ZEROS_DOUBLE : detalle.getBaseImponible());
					detalleNuevo.setRecepcionFactura(null);
					detalleNuevo.setImpuestoCodigo(detalle.getImpuestoCodigo() == null ? Constantes.EMPTY : detalle.getImpuestoCodigo());
					detalleNuevo.setImpuestoCodigoTarifa(detalle.getImpuestoCodigoTarifa() == null ? Constantes.EMPTY : detalle.getImpuestoCodigoTarifa());
					detalleNuevo.setImpuestoTarifa(detalle.getImpuestoTarifa() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoTarifa());
					detalleNuevo.setImpuestoMonto(detalle.getImpuestoMonto() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoMonto());

					detalleNuevo.setImpuestoCodigo1(detalle.getImpuestoCodigo1() == null ? Constantes.EMPTY : detalle.getImpuestoCodigo1());
					detalleNuevo.setImpuestoCodigoTarifa1(detalle.getImpuestoCodigoTarifa1() == null ? Constantes.EMPTY : detalle.getImpuestoCodigoTarifa1());
					detalleNuevo.setImpuestoTarifa1(detalle.getImpuestoTarifa1() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoTarifa1());
					detalleNuevo.setImpuestoMonto1(detalle.getImpuestoMonto1() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoMonto1());

					detalleNuevo.setImpuestoCodigo2(detalle.getImpuestoCodigo2() == null ? Constantes.EMPTY : detalle.getImpuestoCodigo2());
					detalleNuevo.setImpuestoCodigoTarifa2(detalle.getImpuestoCodigoTarifa2() == null ? Constantes.EMPTY : detalle.getImpuestoCodigoTarifa2());
					detalleNuevo.setImpuestoTarifa2(detalle.getImpuestoTarifa2() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoTarifa2());
					detalleNuevo.setImpuestoMonto2(detalle.getImpuestoMonto2() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoMonto2());

					detalleNuevo.setImpuestoCodigo3(detalle.getImpuestoCodigo3() == null ? Constantes.EMPTY : detalle.getImpuestoCodigo3());
					detalleNuevo.setImpuestoCodigoTarifa3(detalle.getImpuestoCodigoTarifa3() == null ? Constantes.EMPTY : detalle.getImpuestoCodigoTarifa3());
					detalleNuevo.setImpuestoTarifa3(detalle.getImpuestoTarifa3() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoTarifa3());
					detalleNuevo.setImpuestoMonto3(detalle.getImpuestoMonto3() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoMonto3());

					detalleNuevo.setImpuestoCodigo4(detalle.getImpuestoCodigo4() == null ? Constantes.EMPTY : detalle.getImpuestoCodigo4());
					detalleNuevo.setImpuestoCodigoTarifa4(detalle.getImpuestoCodigoTarifa4() == null ? Constantes.EMPTY : detalle.getImpuestoCodigoTarifa4());
					detalleNuevo.setImpuestoTarifa4(detalle.getImpuestoTarifa4() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoTarifa4());
					detalleNuevo.setImpuestoMonto4(detalle.getImpuestoMonto4() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoMonto4());

					detalleNuevo.setImpuestoCodigo5(detalle.getImpuestoCodigo5() == null ? Constantes.EMPTY : detalle.getImpuestoCodigo5());
					detalleNuevo.setImpuestoCodigoTarifa5(detalle.getImpuestoCodigoTarifa5() == null ? Constantes.EMPTY : detalle.getImpuestoCodigoTarifa5());
					detalleNuevo.setImpuestoTarifa5(detalle.getImpuestoTarifa5() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoTarifa5());
					detalleNuevo.setImpuestoMonto5(detalle.getImpuestoMonto5() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoMonto5());

					detalleNuevo.setImpuestoCodigo6(detalle.getImpuestoCodigo6() == null ? Constantes.EMPTY : detalle.getImpuestoCodigo6());
					detalleNuevo.setImpuestoCodigoTarifa6(detalle.getImpuestoCodigoTarifa6() == null ? Constantes.EMPTY : detalle.getImpuestoCodigoTarifa6());
					detalleNuevo.setImpuestoTarifa6(detalle.getImpuestoTarifa6() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoTarifa6());
					detalleNuevo.setImpuestoMonto6(detalle.getImpuestoMonto6() == null ? Constantes.ZEROS_DOUBLE : detalle.getImpuestoMonto6());

					detallesCompra.add(detalleNuevo);
				}
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			// Se prepara el objeto para almacenarlo
			recepcionFactura.setNumeroConsecutivoReceptor(empresaBo.generarConsecutivoRecepcionFactura(usuarioSesion.getEmpresa(), usuarioSesion, recepcionFactura));
			recepcionFactura.setEstadoFirma(Constantes.FACTURA_ESTADO_FIRMA_PENDIENTE);
			recepcionFactura.setEstado(Constantes.FACTURA_ESTADO_FACTURADO);
			recepcionFactura.setEmpresa(usuarioSesion.getEmpresa());
			recepcionFactura.setTipoDoc(Utils.obtenerTipoDocumentoConsecutivo(recepcionFactura.getFacturaConsecutivo()));
			recepcionFactura.setCreated_at(new Date());
			recepcionFactura.setUpdated_at(new Date());
			recepcionFactura.setTotalImpuestoAcreditar(recepcionFactura.getFacturaTotalImpuestos());
			recepcionFactura.setTotalDeGastoAplicable(recepcionFactura.getFacturaTotalComprobante() - recepcionFactura.getFacturaTotalImpuestos());

			if (recepcionFactura.getFacturaTipoCambio().equals(Constantes.ZEROS_DOUBLE) && recepcionFactura.getFacturaTipoCambio() != null) {
				if (recepcionFactura.getFacturaCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA)) {
					recepcionFactura.setFacturaTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);
				} else {
					recepcionFactura.setFacturaTipoCambio(570d);
				}
			}

			if (recepcionFactura.getFacturaCodigoMoneda().equals(Constantes.CODIGO_MONEDA_COSTA_RICA) || recepcionFactura.getFacturaCodigoMoneda().equals(Constantes.EMPTY)) {
				recepcionFactura.setFacturaCodigoMoneda(Constantes.CODIGO_MONEDA_COSTA_RICA);
				recepcionFactura.setFacturaTipoCambio(Constantes.CODIGO_MONEDA_COSTA_RICA_CAMBIO);

			}
			recepcionFactura.setFacturaTotalDescuentos(recepcionFactura.getFacturaTotalDescuentos() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalDescuentos());
			recepcionFactura.setFacturaTotalExento(recepcionFactura.getFacturaTotalExento() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalExento());
			recepcionFactura.setFacturaTotalExonerado(recepcionFactura.getFacturaTotalExonerado() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalExonerado());
			recepcionFactura.setFacturaTotalGravado(recepcionFactura.getFacturaTotalGravado() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalGravado());
			recepcionFactura.setFacturaTotalImpuestos(recepcionFactura.getFacturaTotalImpuestos() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalImpuestos());
			recepcionFactura.setFacturaTotalIVADevuelto(recepcionFactura.getFacturaTotalIVADevuelto() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalIVADevuelto());
			recepcionFactura.setFacturaTotalMercanciasExentas(recepcionFactura.getFacturaTotalMercanciasExentas() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalMercanciasExentas());
			recepcionFactura.setFacturaTotalMercanciasGravadas(recepcionFactura.getFacturaTotalMercanciasGravadas() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalMercanciasGravadas());
			recepcionFactura.setFacturaTotalMercExonerada(recepcionFactura.getFacturaTotalMercExonerada() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalMercExonerada());
			recepcionFactura.setFacturaTotalOtrosCargos(recepcionFactura.getFacturaTotalOtrosCargos() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalOtrosCargos());
			recepcionFactura.setFacturaTotalServExentos(recepcionFactura.getFacturaTotalServExentos() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalServExentos());
			recepcionFactura.setFacturaTotalServExonerado(recepcionFactura.getFacturaTotalServExonerado() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalServExonerado());
			recepcionFactura.setFacturaTotalServGravados(recepcionFactura.getFacturaTotalServGravados() == null ? Constantes.ZEROS_DOUBLE : recepcionFactura.getFacturaTotalServGravados());

			recepcionFactura.setTipoGasto(recepcionFactura.getTipoGasto() == null ? Constantes.TIPO_GASTO_ACEPTACION_COMPRAS_INVENTARIO : recepcionFactura.getTipoGasto());
			recepcionFactura.setVersion_doc("4.3");

			recepcionFacturaBo.agregar(recepcionFactura);
			if (detallesCompra != null && !detallesCompra.isEmpty()) {
				for (RecepcionFacturaDetalle recepcionFacturaDetalle : detallesCompra) {
					RecepcionFacturaDetalle recepcionFacturaDetalleNueva = new RecepcionFacturaDetalle();

					recepcionFacturaDetalleNueva.setNumeroLinea(recepcionFacturaDetalle.getNumeroLinea());
					recepcionFacturaDetalleNueva.setCantidad(recepcionFacturaDetalle.getCantidad());
					recepcionFacturaDetalleNueva.setUnidadMedida(recepcionFacturaDetalle.getUnidadMedida());
					recepcionFacturaDetalleNueva.setDetalle(recepcionFacturaDetalle.getDetalle());
					recepcionFacturaDetalleNueva.setPrecioUnitario(recepcionFacturaDetalle.getPrecioUnitario());
					recepcionFacturaDetalleNueva.setMontoTotal(recepcionFacturaDetalle.getMontoTotal());
					recepcionFacturaDetalleNueva.setSubTotal(recepcionFacturaDetalle.getSubTotal());
					recepcionFacturaDetalleNueva.setMontoTotalLinea(recepcionFacturaDetalle.getMontoTotalLinea());
					recepcionFacturaDetalleNueva.setImpuestoNeto(recepcionFacturaDetalle.getImpuestoNeto());
					recepcionFacturaDetalleNueva.setCodigoComercialTipo(recepcionFacturaDetalle.getCodigoComercialTipo());
					recepcionFacturaDetalleNueva.setCodigoComercialCodigo(recepcionFacturaDetalle.getCodigoComercialCodigo());
					recepcionFacturaDetalleNueva.setDescuentoMonto(recepcionFacturaDetalle.getDescuentoMonto());
					recepcionFacturaDetalleNueva.setDescuentoNaturaleza(recepcionFacturaDetalle.getDescuentoNaturaleza());
					recepcionFacturaDetalleNueva.setImpuestoCodigo(recepcionFacturaDetalle.getImpuestoCodigo());
					recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa(recepcionFacturaDetalle.getImpuestoCodigoTarifa());
					recepcionFacturaDetalleNueva.setImpuestoTarifa(recepcionFacturaDetalle.getImpuestoTarifa());
					recepcionFacturaDetalleNueva.setImpuestoMonto(recepcionFacturaDetalle.getImpuestoMonto());

					recepcionFacturaDetalleNueva.setImpuestoCodigo1(recepcionFacturaDetalle.getImpuestoCodigo1());
					recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa1(recepcionFacturaDetalle.getImpuestoCodigoTarifa1());
					recepcionFacturaDetalleNueva.setImpuestoTarifa1(recepcionFacturaDetalle.getImpuestoTarifa1());
					recepcionFacturaDetalleNueva.setImpuestoMonto1(recepcionFacturaDetalle.getImpuestoMonto1());

					recepcionFacturaDetalleNueva.setImpuestoCodigo2(recepcionFacturaDetalle.getImpuestoCodigo2());
					recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa2(recepcionFacturaDetalle.getImpuestoCodigoTarifa2());
					recepcionFacturaDetalleNueva.setImpuestoTarifa2(recepcionFacturaDetalle.getImpuestoTarifa2());
					recepcionFacturaDetalleNueva.setImpuestoMonto2(recepcionFacturaDetalle.getImpuestoMonto2());

					recepcionFacturaDetalleNueva.setImpuestoCodigo3(recepcionFacturaDetalle.getImpuestoCodigo3());
					recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa3(recepcionFacturaDetalle.getImpuestoCodigoTarifa3());
					recepcionFacturaDetalleNueva.setImpuestoTarifa3(recepcionFacturaDetalle.getImpuestoTarifa3());
					recepcionFacturaDetalleNueva.setImpuestoMonto3(recepcionFacturaDetalle.getImpuestoMonto3());

					recepcionFacturaDetalleNueva.setImpuestoCodigo4(recepcionFacturaDetalle.getImpuestoCodigo4());
					recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa4(recepcionFacturaDetalle.getImpuestoCodigoTarifa4());
					recepcionFacturaDetalleNueva.setImpuestoTarifa4(recepcionFacturaDetalle.getImpuestoTarifa4());
					recepcionFacturaDetalleNueva.setImpuestoMonto4(recepcionFacturaDetalle.getImpuestoMonto4());

					recepcionFacturaDetalleNueva.setImpuestoCodigo5(recepcionFacturaDetalle.getImpuestoCodigo5());
					recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa5(recepcionFacturaDetalle.getImpuestoCodigoTarifa5());
					recepcionFacturaDetalleNueva.setImpuestoTarifa5(recepcionFacturaDetalle.getImpuestoTarifa5());
					recepcionFacturaDetalleNueva.setImpuestoMonto5(recepcionFacturaDetalle.getImpuestoMonto5());

					recepcionFacturaDetalleNueva.setImpuestoCodigo6(recepcionFacturaDetalle.getImpuestoCodigo6());
					recepcionFacturaDetalleNueva.setImpuestoCodigoTarifa6(recepcionFacturaDetalle.getImpuestoCodigoTarifa6());
					recepcionFacturaDetalleNueva.setImpuestoTarifa6(recepcionFacturaDetalle.getImpuestoTarifa6());
					recepcionFacturaDetalleNueva.setImpuestoMonto6(recepcionFacturaDetalle.getImpuestoMonto6());

					recepcionFacturaDetalleNueva.setImpuestoExoneracionTipoDocumento(recepcionFacturaDetalle.getImpuestoExoneracionTipoDocumento());
					recepcionFacturaDetalleNueva.setImpuestoExoneracionNumeroDocumento(recepcionFacturaDetalle.getImpuestoExoneracionNumeroDocumento());
					recepcionFacturaDetalleNueva.setImpuestoExoneracionNombreInstitucion(recepcionFacturaDetalle.getImpuestoExoneracionNombreInstitucion());
					recepcionFacturaDetalleNueva.setImpuestoExoneracionFechaEmision(recepcionFacturaDetalle.getImpuestoExoneracionFechaEmision());
					recepcionFacturaDetalleNueva.setImpuestoExoneracionPorcentaje(recepcionFacturaDetalle.getImpuestoExoneracionPorcentaje());
					recepcionFacturaDetalleNueva.setImpuestoExoneracionMonto(recepcionFacturaDetalle.getImpuestoExoneracionMonto());
					recepcionFacturaDetalleNueva.setBaseImponible(recepcionFacturaDetalle.getBaseImponible());
					recepcionFacturaDetalleNueva.setRecepcionFactura(recepcionFactura);
					recepcionFacturaBo.agregar(recepcionFacturaDetalleNueva);
				}
			}
			if (tipoIngreso.equals(Constantes.APLICADO_RECEPCION_AUTOMATICA_SI)) {
				Proveedor proveedor = proveedorBo.buscarPorCedulaYEmpresa(recepcionFactura.getEmisorCedula(), usuarioSesion.getEmpresa());

				if (proveedor == null) {
					proveedor = new Proveedor();
					proveedor.setCedula(recepcionFactura.getReceptorCedula());
					proveedor.setNombreCompleto(recepcionFactura.getEmisorNombreComercial() != null && recepcionFactura.getEmisorNombreComercial().equals(Constantes.EMPTY) ? recepcionFactura.getEmisorNombreComercial() : recepcionFactura.getEmisorNombre());
					proveedor.setCreated_at(new Date());
					proveedor.setEstado(Constantes.ESTADO_ACTIVO);
					proveedor.setEmail(recepcionFactura.getEmisorCorreo());
					proveedor.setDireccion(recepcionFactura.getEmisorOtraSena());
					proveedor.setMovil(recepcionFactura.getEmisorTelefono());
					proveedor.setRazonSocial(recepcionFactura.getEmisorNombre());
					proveedor.setRepresentante(Constantes.EMPTY);
					proveedor.setUpdated_at(new Date());
					proveedor.setId(null);
					proveedor.setEmpresa(usuarioSesion.getEmpresa());
					proveedorBo.agregar(proveedor);

				}

				compraBo.crearCompra(recepcionFactura, usuarioSesion, proveedor, detallesCompra);
				ifEMensajeReceptorAutomaticoBo.updateEstadoPorIdentificion(Constantes.COMPRA_AUTOMATICA_ESTADO_APLICADA, recepcionFactura.getReceptorCedula());
				
			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("recepcionFactura.agregar.correctamente", recepcionFactura);

		} catch (Exception e) {
			respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
			respuestaServiceValidator.setMessage(e.getMessage());
			return respuestaServiceValidator;
		}

	}

	@RequestMapping(value = "/TotalComprasAceptadasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public TotalComprasAceptadasCommand totalComprasAceptadasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Integer estado, @RequestParam Integer tipoGasto, @RequestParam String actividadEconomica) {
		Date inicio = Utils.parseDate(fechaInicio);
		Date finalDate = Utils.dateToDate(Utils.parseDate(fechaFin), true);
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		TotalComprasAceptadasCommand totalCompras = new TotalComprasAceptadasCommand();
		Collection<RecepcionFactura> recepcionFacturas = recepcionFacturaBo.findByFechaInicioAndFechaFinalAndCedulaEmisor(inicio, finalDate, usuario.getEmpresa(), Constantes.EMPTY, estado, tipoGasto, actividadEconomica);
		Double totalImpuestoNotaCredito = Constantes.ZEROS_DOUBLE;
		Double totalImpuestoNotaDebito = Constantes.ZEROS_DOUBLE;
		Double totalImpuestosCompras = Constantes.ZEROS_DOUBLE;
		Double totalCompraNotaCredito = Constantes.ZEROS_DOUBLE;
		Double totalCompraNotaDebito = Constantes.ZEROS_DOUBLE;
		Double totalCompra = Constantes.ZEROS_DOUBLE;
		Double tipoCambio = Constantes.ZEROS_DOUBLE;
		for (RecepcionFactura recepcionFactura : recepcionFacturas) {
			tipoCambio = recepcionFactura.getFacturaTipoCambio() == null ? 1 : recepcionFactura.getFacturaTipoCambio();
			Double valor = Constantes.ZEROS_DOUBLE;
			if (recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
				valor = recepcionFactura.getFacturaTotalImpuestos() != null ? recepcionFactura.getFacturaTotalImpuestos() : Constantes.ZEROS_DOUBLE;
				valor = valor * tipoCambio;
				totalImpuestoNotaCredito = totalImpuestoNotaCredito + valor;
				valor = recepcionFactura.getFacturaTotalComprobante() != null ? recepcionFactura.getFacturaTotalComprobante() : Constantes.ZEROS_DOUBLE;
				valor = valor * tipoCambio;
				totalCompraNotaCredito = totalCompraNotaCredito + valor;
			} else if (recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				valor = recepcionFactura.getFacturaTotalImpuestos() != null ? recepcionFactura.getFacturaTotalImpuestos() : Constantes.ZEROS_DOUBLE;
				valor = valor * tipoCambio;
				totalImpuestoNotaDebito = totalImpuestoNotaDebito + valor;
				valor = recepcionFactura.getFacturaTotalComprobante() != null ? recepcionFactura.getFacturaTotalComprobante() : Constantes.ZEROS_DOUBLE;
				valor = valor * tipoCambio;

				totalCompraNotaDebito = totalCompraNotaDebito + valor;
			}
			if (!recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
				valor = recepcionFactura.getFacturaTotalImpuestos() != null ? recepcionFactura.getFacturaTotalImpuestos() : Constantes.ZEROS_DOUBLE;
				valor = valor * tipoCambio;

				totalImpuestosCompras = valor + totalImpuestosCompras;
				valor = recepcionFactura.getFacturaTotalComprobante() != null ? recepcionFactura.getFacturaTotalComprobante() : Constantes.ZEROS_DOUBLE;
				valor = valor * tipoCambio;

				totalCompra = valor + totalCompra;
			}
		}
		Double resultadoFinal = totalImpuestoNotaDebito + totalImpuestosCompras;
		resultadoFinal = resultadoFinal - totalImpuestoNotaCredito;
		totalCompras.setTotalImpuesto(resultadoFinal);
		totalCompras.setTotalImpuestoNotaCredito(totalImpuestoNotaCredito);
		totalCompras.setTotalImpuestoNotaDebito(totalImpuestoNotaDebito);
		totalCompras.setTotalNotaCredito(totalCompraNotaCredito);
		totalCompras.setTotalNotaDebito(totalCompraNotaDebito);
		resultadoFinal = totalCompraNotaDebito + totalCompra;
		resultadoFinal = resultadoFinal - totalCompraNotaCredito;
		totalCompras.setTotal(resultadoFinal);

		return totalCompras;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CorreoTotalComprasAceptadasAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator<?> envioTotalComprasAceptadasAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute String datos, BindingResult result, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String correoAlternativo, @RequestParam Integer estado, @RequestParam Integer tipoGasto, String actividadEconomica) {
		RespuestaServiceValidator<?> respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());

			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			// Se obtiene los totales
			Date fechaInicio = Utils.parseDate(fechaInicioParam);
			Date fechaFinal = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);

			TotalComprasAceptadasCommand totalComprasAceptadasCommand = new TotalComprasAceptadasCommand();

			Collection<RecepcionFactura> recepcionFacturas = recepcionFacturaBo.findByFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFinal, usuario.getEmpresa(), Constantes.EMPTY, estado, tipoGasto, actividadEconomica);
			Double totalImpuestoNotaCredito = Constantes.ZEROS_DOUBLE;
			Double totalImpuestoNotaDebito = Constantes.ZEROS_DOUBLE;
			Double totalImpuestosCompras = Constantes.ZEROS_DOUBLE;
			Double totalCompraNotaCredito = Constantes.ZEROS_DOUBLE;
			Double totalCompraNotaDebito = Constantes.ZEROS_DOUBLE;
			Double totalCompra = Constantes.ZEROS_DOUBLE;
			for (RecepcionFactura recepcionFactura : recepcionFacturas) {
				Double tipoCambio = recepcionFactura.getFacturaTipoCambio() == null ? 1 : recepcionFactura.getFacturaTipoCambio();
				Double valor = Constantes.ZEROS_DOUBLE;
				if (recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO)) {
					valor = recepcionFactura.getFacturaTotalImpuestos() != null ? recepcionFactura.getFacturaTotalImpuestos() : Constantes.ZEROS_DOUBLE;
					valor = valor * tipoCambio;
					totalImpuestoNotaCredito = totalImpuestoNotaCredito + valor;
					valor = recepcionFactura.getFacturaTotalComprobante() != null ? recepcionFactura.getFacturaTotalComprobante() : Constantes.ZEROS_DOUBLE;
					valor = valor * tipoCambio;
					totalCompraNotaCredito = totalCompraNotaCredito + valor;
				} else if (recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
					valor = recepcionFactura.getFacturaTotalImpuestos() != null ? recepcionFactura.getFacturaTotalImpuestos() : Constantes.ZEROS_DOUBLE;
					valor = valor * tipoCambio;
					totalImpuestoNotaDebito = totalImpuestoNotaDebito + valor;
					valor = recepcionFactura.getFacturaTotalComprobante() != null ? recepcionFactura.getFacturaTotalComprobante() : Constantes.ZEROS_DOUBLE;
					valor = valor * tipoCambio;

					totalCompraNotaDebito = totalCompraNotaDebito + valor;
				}
				if (!recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_CREDITO) && !recepcionFactura.getTipoDoc().equals(Constantes.FACTURA_TIPO_DOC_FACTURA_NOTA_DEBITO)) {
					valor = recepcionFactura.getFacturaTotalImpuestos() != null ? recepcionFactura.getFacturaTotalImpuestos() : Constantes.ZEROS_DOUBLE;
					valor = valor * tipoCambio;

					totalImpuestosCompras = valor + totalImpuestosCompras;
					valor = recepcionFactura.getFacturaTotalComprobante() != null ? recepcionFactura.getFacturaTotalComprobante() : Constantes.ZEROS_DOUBLE;
					valor = valor * tipoCambio;

					totalCompra = valor + totalCompra;
				}
			}
			Double resultadoFinal = totalImpuestoNotaDebito + totalImpuestosCompras;
			resultadoFinal = resultadoFinal - totalImpuestoNotaCredito;
			totalComprasAceptadasCommand.setTotalImpuesto(resultadoFinal);
			totalComprasAceptadasCommand.setTotalImpuestoNotaCredito(totalImpuestoNotaCredito);
			totalComprasAceptadasCommand.setTotalImpuestoNotaDebito(totalImpuestoNotaDebito);
			totalComprasAceptadasCommand.setTotalNotaCredito(totalCompraNotaCredito);
			totalComprasAceptadasCommand.setTotalNotaDebito(totalCompraNotaDebito);
			resultadoFinal = totalCompraNotaDebito + totalCompra;
			resultadoFinal = resultadoFinal - totalCompraNotaCredito;
			totalComprasAceptadasCommand.setTotal(resultadoFinal);

			// Se prepara el excell createExcelDetalleCompra(Collection<RecepcionFacturaDetalle> lista, String fechaInicio, String fechaFin, Empresa empresa)
			// Se buscan las facturas
			Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
			Collection<RecepcionFacturaDetalle> lista = recepcionFacturaBo.findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFin, usuario.getEmpresa(), "", estado, tipoGasto, actividadEconomica);

			ByteArrayOutputStream baos = Utils.convertirOutStream(detalleCompraBo.createExcelDetalleCompra(lista, fechaInicioParam, fechaFinParam, usuario.getEmpresa()));
			Collection<Attachment> attachments = createAttachments(attachment("ComprasMensuales", ".xls", new ByteArrayDataSource(baos.toByteArray(), "text/plain")));

			// Se prepara el correo
			String from = "aceptarCompraXProveedor@facturaemprendesoftcr.com";
//			if (usuario.getEmpresa().getAbreviaturaEmpresa() != null) {
//				if (!usuario.getEmpresa().getAbreviaturaEmpresa().equals(Constantes.EMPTY)) {
//					from = usuario.getEmpresa().getAbreviaturaEmpresa() + "_ComprasEmitidas" + "_No_Reply@facturaemprendesoftcr.com";
//				}
//			}
			String subject = "Compras Rango de fechas: " + fechaInicioParam + " al " + fechaFinParam;

			ArrayList<String> listaCorreos = new ArrayList<>();
			if (correoAlternativo != null && correoAlternativo.length() > 0) {
				listaCorreos.add(correoAlternativo);
			} else {
				listaCorreos.add(usuario.getEmpresa().getCorreoElectronico());
			}

			Map<String, Object> modelEmail = new HashMap<>();
			modelEmail.put("nombreEmpresa", usuario.getEmpresa().getNombre());
			if (estado.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_HACIENDA)) {
				modelEmail.put("estado", "Aceptadas");
			}
			if (estado.equals(Constantes.HACIENDA_ESTADO_ACEPTADO_RECHAZADO)) {
				modelEmail.put("estado", "No Aceptadas");
			}

			modelEmail.put("fechaInicial", Utils.getFechaStr(fechaInicio));
			modelEmail.put("fechaFinal", Utils.getFechaStr(fechaFinal));
			modelEmail.put("total", totalComprasAceptadasCommand.getTotal() != null ? totalComprasAceptadasCommand.getTotalSTR() : Constantes.ZEROS);
			modelEmail.put("totalImpuesto", totalComprasAceptadasCommand.getTotalImpuesto() != null ? totalComprasAceptadasCommand.getTotalImpuestoSTR() : Constantes.ZEROS);

			Boolean resultado = correosBo.enviarConAttach(attachments, listaCorreos, from, subject, Constantes.PLANTILLA_CORREO_COMPRAS_ACEPTADAS, modelEmail);
			if (resultado.equals(Boolean.TRUE)) {
				log.info("Enviado correctamente el correo {}", new Date());
				System.out.println("Enviado correctamente el correo");
			} else {
				log.error("** Error  Enviado correo: " + " fecha " + new Date());
				System.out.println("No enviado correctamente el correo");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("hacienda.envio.correo.reintente", result.getAllErrors());
			}
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage("");
			respuestaServiceValidator.setStatus(HttpStatus.OK.value());
			respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("hacienda.envio.correo.exitoso"));
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

		return respuestaServiceValidator;
	}

	private Collection<Attachment> createAttachments(Attachment... attachments) {
		return Arrays.asList(attachments);
	}

	private Attachment attachment(String name, String ext, ByteArrayDataSource data) {
		return new Attachment(name + ext, data);
	}

	/**
	 * @param request
	 * @param response
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param cedulaEmisor
	 * @param estado
	 * @throws Exception
	 */
	@RequestMapping(value = "/DescargarComprasAceptadasAjax.do", method = RequestMethod.GET)
	public void descargarComprasAceptadasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String cedulaEmisor, @RequestParam Integer estado, @RequestParam Integer tipoGasto, String actividadEconomica) throws Exception {
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<RecepcionFactura> recepcionFacturas = recepcionFacturaBo.findByFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFin, usuario.getEmpresa(), cedulaEmisor, estado, tipoGasto, actividadEconomica);

		String nombreArchivo = "comprasAceptadas.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		ByteArrayInputStream inputStream = compraBo.createExcelRecepcionCompra(recepcionFacturas, fechaInicioParam, fechaFinParam, usuario.getEmpresa());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	/**
	 * Modulo de compras
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/compras", method = RequestMethod.GET)
	public String crearCompras(ModelMap model) {
		return "views/compras/crearCompra";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CrearCompraAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute CompraCommand compraCommand, BindingResult result, SessionStatus status) {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			if (!compraCommand.getFormaPago().equals(Constantes.COMPRA_FORMA_PAGO_CREDITO)) {
				compraCommand.setFechaCredito(null);
			}
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (!compraCommand.getConsecutivo().equals(Constantes.EMPTY)) {
				Compra compraBD = compraBo.findByConsecutivoAndEmpresa(compraCommand.getConsecutivo(), usuarioSesion.getEmpresa());
				if (compraBD != null) {
					if (!compraBD.getId().equals(compraCommand.getId())) {
						result.rejectValue("consecutivo", "error.compra.existe.consecutivo");
					}
				}
			}
			if (compraCommand.getId() != null) {
				if (compraCommand.getId() > 0) {
					Compra combraVerificar = compraBo.findById(compraCommand.getId());
					if (combraVerificar != null) {
						if (combraVerificar.getEstado().equals(Constantes.COMPRA_ESTADO_INGRESADA_INVENTARIO)) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("compra.error.ya.se.encuentra.ingresada", result.getAllErrors());
						}
					}
				}
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			compraCommand.setEmpresa(usuarioSesion.getEmpresa());
			compraCommand.setUsuarioCreacion(usuarioSesion);
			compraBo.crearCompra(compraCommand, usuarioSesion);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.agregar.correctamente", compraCommand);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@RequestMapping(value = "/DescargarDetalladaAceptadasAjax.do", method = RequestMethod.GET)
	public void descargarDetalladasAceptadasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam String cedulaEmisor, @RequestParam Integer estado, @RequestParam Integer tipoGasto, @RequestParam String actividadEconomica) throws Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<RecepcionFacturaDetalle> recepcionFacturas = recepcionFacturaBo.findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicio, fechaFin, usuario.getEmpresa(), cedulaEmisor, estado, tipoGasto, actividadEconomica);

		String nombreArchivo = "compras.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayInputStream inputStream = detalleCompraBo.createExcelDetalleCompra(recepcionFacturas, fechaInicioParam, fechaFinParam, usuario.getEmpresa());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listarConsutaComprasIvaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarConsutaComprasIvaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Integer estado, @RequestParam Integer selectActividadComercial) {
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Date fechaFinalP = Utils.parseDate(fechaFin);
		Date fechaInicioP = Utils.parseDate(fechaInicio);
		if (!fechaInicio.equals(Constantes.EMPTY) && !fechaFin.equals(Constantes.EMPTY)) {
			if (fechaFinalP != null) {
				fechaFinalP = Utils.sumarDiasFecha(fechaFinalP, 1);
			}
		}
		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaInicio);
		String fin1 = dateFormat1.format(fechaFinalP);

		Collection<RecepcionFacturaDetalle> recepcionFacturas = recepcionFacturaBo.findByDetalleAndFechaInicioAndFechaFinalAndCedulaEmisor(fechaInicioP, fechaFinalP, usuario.getEmpresa(), Constantes.EMPTY, estado, 0, "0");

		ConsultaComprasIvaCommand tarifa_0 = new ConsultaComprasIvaCommand();

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
//		
		respuestaService.setRecordsTotal(0l);
		respuestaService.setRecordsFiltered(0l);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(null);
		return respuestaService;
	}

	/**
	 * Descargar Compras
	 * @param request
	 * @param response
	 * @param fechaInicioParam
	 * @param fechaFinParam
	 * @param idProveedor
	 * @throws IOException
	 */
	@RequestMapping(value = "/DescargarComprasIngresadasAlmacenAjax.do", method = RequestMethod.GET)
	public void descargarComprasIngresadasAlmacenAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicioParam, @RequestParam String fechaFinParam, @RequestParam Long idProveedor) throws IOException {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = null;
		if (idProveedor != null) {
			proveedor = proveedorBo.buscar(idProveedor);

		}
		// Se buscan las facturas
		Date fechaInicio = Utils.parseDate(fechaInicioParam);
		Date fechaFin = Utils.dateToDate(Utils.parseDate(fechaFinParam), true);
		Collection<Compra> compras = compraBo.findByFechaInicioAndFechaFinalAndProveedor(fechaInicio, fechaFin, usuario.getEmpresa(), proveedor);

		String nombreArchivo = "comprasIngresadasAlmacen.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = Utils.convertirOutStream(compraBo.createExcelCompras(compras, usuario.getEmpresa(), fechaInicioParam, fechaFinParam, proveedor));

		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	/**
	 * Lista las compras pendientes de ingresar al inventario
	 * @param request
	 * @param response
	 * @param idEmpresa
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarComprasEsperaActivasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Compra");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_PENDIENTE.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("empresa.id", "'" + usuarioSesion.getEmpresa().getId().toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Lista las compras ingresadas al inventario y que no estan pendiente
	 * @param request
	 * @param response
	 * @param idEmpresa
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarComprasAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarComprasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idProveedor, @RequestParam String estado) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFin, proveedor, usuarioSesion.getEmpresa(), estado);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarComprasNoAnuladasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarComprasNoAnuladasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long idProveedor) {

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		DataTableDelimitador query = DelimitadorBuilderAnuladas.get(request, fechaInicio, fechaFin, proveedor, usuarioSesion.getEmpresa());

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Mostrar la compra
	 * @param request
	 * @param model
	 * @param compra
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarCompraEsperaAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) {
		try {
			Compra compraBD = compraBo.findById(id);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", compraBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Anular un Compra
	 * @param request
	 * @param response
	 * @param result
	 * @param idCompra
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AnularCompraAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator anularCompra(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam Long idCompra, BindingResult result, SessionStatus status) {
		try {
			Compra compra = compraBo.findById(idCompra);
			if (compra == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			compraBo.anularCompra(compra);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("compra.anulado.correctamente", compra);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarDetlleByCompraAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idCompra) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "DetalleCompra");

		JqGridFilter dataTableFilter = new JqGridFilter("compra.id", "'" + idCompra + "'", "=");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND_DETALLE);
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Proveedor proveedor, Empresa empresa, String estado) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Compra");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_PENDIENTE.toString() + "'", "<>"));

			if (!estado.equals(Constantes.COMBO_TODOS)) {
				delimitador.addFiltro(new JqGridFilter("estado", "'" + estado + "'", "="));
			}

			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (proveedor != null) {
				delimitador.addFiltro(new JqGridFilter("proveedor.id", "'" + proveedor.getId().toString() + "'", "="));
			}
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

				delimitador.addFiltro(new JqGridFilter("created_at", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("created_at", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

	private static class DelimitadorBuilderAnuladas {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Proveedor proveedor, Empresa empresa) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Compra");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_PENDIENTE.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("estado", "'" + Constantes.COMPRA_ESTADO_ANULADA.toString() + "'", "<>"));
			delimitador.addFiltro(new JqGridFilter("empresa.id", "'" + empresa.getId().toString() + "'", "="));

			if (proveedor != null) {
				delimitador.addFiltro(new JqGridFilter("proveedor.id", "'" + proveedor.getId().toString() + "'", "="));
			}
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

				delimitador.addFiltro(new JqGridFilter("created_at", inicio, "date>="));
				delimitador.addFiltro(new JqGridFilter("created_at", fin, "dateFinal<="));
			}
			return delimitador;
		}
	}

}

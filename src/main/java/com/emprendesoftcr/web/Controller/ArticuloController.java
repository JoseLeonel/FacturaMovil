package com.emprendesoftcr.web.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jxls.template.SimpleExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.CategoriaBo;
import com.emprendesoftcr.Bo.ConsultasNativeBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Marca;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.modelo.sqlNativo.ArticuloByFechaNative;
import com.emprendesoftcr.pdf.GondolaArticuloPdfView;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.utils.Utils;
import com.emprendesoftcr.web.command.ArticuloCabysCommand;
import com.emprendesoftcr.web.command.ArticuloCambioCategoriaGrupal;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.command.CambiarPrecioArticuloCommand;
import com.emprendesoftcr.web.command.DetalleFacturaCommand;
import com.emprendesoftcr.web.command.EtiquetasCommand;
import com.emprendesoftcr.web.command.ParametrosPaginacion;
import com.emprendesoftcr.web.command.TotalInventarioCommand;
import com.emprendesoftcr.web.propertyEditor.ArticuloPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.CategoriaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MarcaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.itextpdf.text.DocumentException;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JsonDataSource;

/**
 * Control de los articulos de una empresa ArticuloController.
 * @author jose.
 * @since 19 abr. 2018
 */
@CrossOrigin
@Controller
public class ArticuloController {

	private static final Function<Object, ArticuloCommand>	TO_COMMAND	= new Function<Object, ArticuloCommand>() {

																																				@Override
																																				public ArticuloCommand apply(Object f) {
																																					return new ArticuloCommand((Articulo) f);
																																				};
																																			};

	@Autowired
	private DataTableBo																			dataTableBo;

	@Autowired
	private ArticuloBo																			articuloBo;

	@Autowired
	ConsultasNativeBo																				consultasNativeBo;

	@Autowired
	private DetalleBo																				detalleBo;

	@Autowired
	private CategoriaBo																			categoriaBo;

	@Autowired
	private UsuarioBo																				usuarioBo;

	@Autowired
	private ArticuloPropertyEditor													articuloPropertyEditor;

	@Autowired
	private MarcaPropertyEditor															marcaPropertyEditor;

	@Autowired
	private CategoriaPropertyEditor													categoriaPropertyEditor;

	@Autowired
	private StringPropertyEditor														stringPropertyEditor;
	@Autowired
	private ValidateTokenBo																	validateTokenBo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Articulo.class, articuloPropertyEditor);

		binder.registerCustomEditor(Marca.class, marcaPropertyEditor);
		binder.registerCustomEditor(Categoria.class, categoriaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarArticulosXCambioCategoria", method = RequestMethod.GET)
	public String listarXCambioCategoria(ModelMap model) {
		return "views/articulos/ListarArticulosCambiarCategoria";
	}

	@RequestMapping(value = "/Etiquetas.do", method = RequestMethod.GET)
	public String etiquetas(ModelMap model) {
		return "views/articulos/etiquetas";
	}

	@RequestMapping(value = "/TotalesArticulos", method = RequestMethod.GET)
	public String totalesArticulos(ModelMap model) {
		return "views/articulos/TotalesArticulos";
	}

	/**
	 * Listar JSP de los articulos
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ListarArticulos", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/articulos/ListarArticulos";
	}

	@RequestMapping(value = "/ListarArticulosMinimos", method = RequestMethod.GET)
	public String listarArticulosMinimos(ModelMap model) {
		return "views/articulos/ListarArticulosMinimos";
	}

	/**
	 * Listar JSP de los articulos
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/nuevoArticulo.do", method = RequestMethod.GET)
	public String nuevoArticulo(ModelMap model) {
		return "views/articulos/nuevoArticulo";
	}

	@RequestMapping(value = "/ListarKardex", method = RequestMethod.GET)
	public String listarKardex(ModelMap model) {
		return "views/articulos/ListarKardex";
	}

	@RequestMapping(value = "/CambiarPrecio", method = RequestMethod.GET)
	public String cambiarPrecio(ModelMap model) {
		return "views/articulos/CambioPrecio";
	}

	@RequestMapping(value = "/paqueteArticulos", method = RequestMethod.GET)
	public String paqueteArticulo(ModelMap model) {
		return "views/articulos/paqueteArticulos";
	}

	@RequestMapping(value = "/movil/ListarArticulosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Collection<Articulo> listarMovilAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Integer idEmpresa, @RequestParam Long idCategoria) {

		return articuloBo.articulosByCategoriaAndEmpresa(idEmpresa, idCategoria);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/CambiarCategoriaArticulosGrupalAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarGrupal(HttpServletRequest request, ModelMap model, @RequestParam("listaArticuloGrupales") String listaArticuloGrupales, @RequestParam("categoria") Long idCategoria, @ModelAttribute ArticuloCambioCategoriaGrupal articuloCambioCategoriaGrupaltem, BindingResult result, SessionStatus status) throws Exception {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		Articulo articuloTemp = new Articulo();
		try {
			JSONObject json = null;
			try {
				json = (JSONObject) new JSONParser().parse(listaArticuloGrupales);
				// Agregar Lineas de Detalle
				JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
				Gson gson = new Gson();
				if (jsonArrayDetalleFactura != null) {
					for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
						ArticuloCambioCategoriaGrupal articuloCambioCategoriaGrupal = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), ArticuloCambioCategoriaGrupal.class);
						Articulo articuloBD = articuloBo.buscar(articuloCambioCategoriaGrupal.getId());
						if (articuloBD == null) {
							respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
							respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.articulo.codigo.no.existe"));
							return respuestaServiceValidator;
						}
						if (result.hasErrors()) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
						}
						Categoria categoria = categoriaBo.buscar(idCategoria);
						articuloBD.setCategoria(categoria);
						articuloBD.setUpdated_at(new Date());
						articuloBo.modificar(articuloBD);
						articuloTemp = articuloBD;
					}
				}
			} catch (org.json.simple.parser.ParseException e) {
				throw e;
			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("categoria.cambio.correctamente", articuloTemp);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/GenerarEtiquetasPrecios.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public void GenerarEtiquetasPrecios(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam("listaArticuloEtiquetas") String listaArticuloEtiquetas, @ModelAttribute EtiquetasCommand EtiquetasCommand1, BindingResult result, SessionStatus status) throws Exception {
		List<EtiquetasCommand> lista = new ArrayList<>();

		byte[] decodedBytes = Base64.getDecoder().decode(listaArticuloEtiquetas);
		String decodedString = new String(decodedBytes);

		System.out.println("decodedString ============================ > " + decodedString);

//		try {
		JSONObject json = null;
		try {
			json = (JSONObject) new JSONParser().parse(decodedString);
			// Agregar Lineas de Detalle
			JSONArray jsonArrayDetalleFactura = (JSONArray) json.get("data");
			Gson gson = new Gson();

			if (jsonArrayDetalleFactura != null) {
				for (int i = 0; i < jsonArrayDetalleFactura.size(); i++) {
					EtiquetasCommand etiquetasCommand = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), EtiquetasCommand.class);
					for (int e = 0; e < etiquetasCommand.getCantidadEtiqueta(); e++) {
						lista.add(etiquetasCommand);
					}

				}
			}
		} catch (org.json.simple.parser.ParseException e) {
			throw e;
		}
		// jasper

		// JasperReport report = (JasperReport) JRLoader.loadObject(new File(
		// "/data/reportes/articulos/reporte_etiquetas.jasper" ));
		// JasperCompileManager.compileReport("/reportes/articulos/MyReports/reporte_etiquetas.jrxml");
		InputStream reportfile = getClass().getResourceAsStream("/reportes/articulos/MyReports/reporte_etiquetas.jasper");
		ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(new Gson().toJson(lista).getBytes());
		JsonDataSource ds = new JsonDataSource(jsonDataStream);
		// Map parameters = new HashMap();
		// JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, ds);

		byte[] bytes = JasperRunManager.runReportToPdf(reportfile, null, ds);
		if (bytes != null && bytes.length > 0) {
			response.setContentType("application/pdf");
			// response.setHeader("Content-Disposition",
			// "attachment;filename=etiquetas.pdf");
			ServletOutputStream outputstream = response.getOutputStream();
			outputstream.write(bytes, 0, bytes.length);
			outputstream.flush();
			outputstream.close();

		} else {
			System.out.println("NO trae nada");
		}

	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/GenerarTikectFactura.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public void GenerarTikect(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam("idFactura") Long idFactura, BindingResult result, SessionStatus status) throws Exception {
		List<DetalleFacturaCommand> lista = new ArrayList<>();

		Collection<Detalle> listaDetalles = detalleBo.findbyIdFactura(idFactura);
		for (Detalle detalle : listaDetalles) {
			DetalleFacturaCommand detalleFacturaCommand = new DetalleFacturaCommand(detalle);
			lista.add(detalleFacturaCommand);
		}

		// jasper
		Map<String, Object> parametros = new HashMap();
		Locale locale = new Locale("es");
		parametros.put(JRParameter.REPORT_LOCALE, locale);
		parametros.put("P_documento_electronico", "Factura Electronica");

		InputStream reportfile = getClass().getResourceAsStream("/reportes/factura/reporte_etiquetas.jasper");
		ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(new Gson().toJson(lista).getBytes());
		JsonDataSource ds = new JsonDataSource(jsonDataStream);

		byte[] bytes = JasperRunManager.runReportToPdf(reportfile, parametros, ds);
		if (bytes != null && bytes.length > 0) {
			response.setContentType("application/pdf");
			ServletOutputStream outputstream = response.getOutputStream();
			outputstream.write(bytes, 0, bytes.length);
			outputstream.flush();
			outputstream.close();

		} else {
			System.out.println("NO trae nada");
		}

	}

	@RequestMapping(value = "/TotalInventarioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public TotalInventarioCommand totalFacturasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam("fechaInicio") String fechaInicio) {
		// Se buscan las facturas
		Date fechaI = Utils.parseDate(fechaInicio);
		Date fechaF = Utils.parseDate(fechaInicio);
		if (fechaF == null) {
			fechaF = new Date(System.currentTimeMillis());
		}
		if (fechaF != null && fechaF != null) {
			fechaF = Utils.sumarDiasFecha(fechaF, 1);
		}
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		return articuloBo.sumarInventarios(usuario.getEmpresa().getId(), fechaI, fechaF);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticulosActivosFechaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarArticulosFechaActivosAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam("fechaInicio") String fechaInicio) {

		// Se buscan las facturas
		Date fechaI = Utils.parseDate(fechaInicio);
		Date fechaF = Utils.parseDate(fechaInicio);
		if (fechaF == null) {
			fechaF = new Date(System.currentTimeMillis());
		}
		if (fechaF != null && fechaF != null) {
			fechaF = Utils.sumarDiasFecha(fechaF, 1);
		}

		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaI);
		String fin1 = dateFormat1.format(fechaF);
		RespuestaServiceDataTable respuestaServiceDataTable = new RespuestaServiceDataTable();
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<ArticuloByFechaNative> objetos = consultasNativeBo.findByInventario(usuarioSesion.getEmpresa(), inicio1, fin1);
		for (ArticuloByFechaNative articuloByFechaNative : objetos) {
			solicitudList.add(articuloByFechaNative);
		}
		respuestaService.setRecordsTotal(Constantes.ZEROS_LONG);
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}

	// Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarInventarioAjax.do", method = RequestMethod.GET)
	public void descargarInventarioAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam("fechaInicio") String fechaInicio) throws IOException, Exception {

		// Se buscan las facturas
		Date fechaI = Utils.parseDate(fechaInicio);
		Date fechaF = Utils.parseDate(fechaInicio);
		if (fechaF == null) {
			fechaF = new Date(System.currentTimeMillis());
		}
		if (fechaF != null && fechaF != null) {
			fechaF = Utils.sumarDiasFecha(fechaF, 1);
		}

		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaI);
		String fin1 = dateFormat1.format(fechaF);

		// Se buscan las facturas
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Collection<ArticuloByFechaNative> objetos = consultasNativeBo.findByInventario(usuarioSesion.getEmpresa(), inicio1, fin1);

		String nombreArchivo = "Inventario.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelArticulos(objetos);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelArticulos(Collection<ArticuloByFechaNative> articulos) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Fecha Ultima Actualizacion", "Estado", "Categoria", "#Codigo", "Descripcion", "Cantidad", "Costo", "Total Costo(Costo X Cantidad)", "Impuesto", "Precio Publico", "Total Venta Esperada(cantidadXPrecioPublico)");
		new SimpleExporter().gridExport(headers, articulos, "updated_atSTR,estado,categoria, codigo, descripcion, cantidadActualReal, costo,totalCosto, impuesto,precioPublico,totalPrecioPublico", baos);
		return baos;
	}

//Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarInventarioExistenciasAjax.do", method = RequestMethod.GET)
	public void descargarInventarioExistenciasAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam("fechaInicio") String fechaInicio) throws IOException, Exception {

		// Se buscan las facturas
		Date fechaI = Utils.parseDate(fechaInicio);
		Date fechaF = Utils.parseDate(fechaInicio);
		if (fechaF == null) {
			fechaF = new Date(System.currentTimeMillis());
		}
		if (fechaF != null && fechaF != null) {
			fechaF = Utils.sumarDiasFecha(fechaF, 1);
		}

		DateFormat dateFormat1 = new SimpleDateFormat(Constantes.DATE_FORMAT5);
		String inicio1 = dateFormat1.format(fechaI);
		String fin1 = dateFormat1.format(fechaF);

		// Se buscan las facturas
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Collection<ArticuloByFechaNative> objetos = consultasNativeBo.findByInventario(usuarioSesion.getEmpresa(), inicio1, fin1);

		String nombreArchivo = "InventarioExistencias.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelArticulosExistencias(objetos);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelArticulosExistencias(Collection<ArticuloByFechaNative> articulos) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Categoria", "#Codigo", "Descripcion", "Estado", "Cantidad Actual", "#Cantidad Revision Fisica");
		new SimpleExporter().gridExport(headers, articulos, " categoria,estado,codigo, descripcion, cantidadActualReal", baos);
		return baos;
	}

	@RequestMapping(value = "/PDFGondolaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarPDFGondola(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idArticulo) throws Exception {

		try {
			Articulo articuloBD = articuloBo.buscar(idArticulo);

			ByteArrayOutputStream namePDF = GondolaArticuloPdfView.main(articuloBD);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(namePDF.toByteArray());
			response.setContentType("application/octet-stream");
			response.setContentLength((int) namePDF.toByteArray().length);
			String fileName = Constantes.EMPTY;

			Date fecha = new Date();
			fileName = "articulo_" + articuloBD.getCodigo().trim() + fecha.toString();

			int BUFFER_SIZE = 4096;
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName + ".pdf");
			response.setHeader(headerKey, headerValue);
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (com.google.zxing.WriterException ex) {

		}

	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticulosActivosAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarArticulosActivosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_INACTIVO + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticulosActivosUsoInternoAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarArticulosActivosTipoUsoInternoAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		dataTableFilter = new JqGridFilter("tipoCodigo", "'" + Constantes.TIPO_CODIGO_ARTICULO_USO_INTERNO + "'", "=");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticuloCabysAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarCabysAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "codigo", required = false) String codigo, @RequestParam(value = "descripcion", required = false) String descripcion, @RequestParam(value = "tipo", required = false) Integer tipo, @RequestParam(value = "cantidad", required = false) Integer cantidad) {
		RespuestaServiceDataTable<ArticuloCabysCommand> respuestaService = new RespuestaServiceDataTable<ArticuloCabysCommand>();
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");

		List<Map<String, Object>> listaObjetos = articuloBo.articulosByCabys(descripcion, codigo, tipo, usuarioSesion.getEmpresa().getId(), cantidad);

		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		@SuppressWarnings("rawtypes")
		ArrayList arrayList = new ArrayList();
		arrayList = (ArrayList<?>) listaObjetos;
		JsonArray jsonArray1 = new Gson().toJsonTree(arrayList).getAsJsonArray();
		ArrayList<ArticuloCabysCommand> detallesFacturaCommand = new ArrayList<>();
		Gson gson = new Gson();
		if (jsonArray1 != null) {
			for (int i = 0; i < jsonArray1.size(); i++) {
				ArticuloCabysCommand articuloCabysCommand = gson.fromJson(jsonArray1.get(i).toString(), ArticuloCabysCommand.class);
				detallesFacturaCommand.add(articuloCabysCommand);
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
	 * Listar Ajax de los articulos de una empresa
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "codigoArt", required = false) String codigoArt) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter categoriaFilter = null;
		if (codigoArt != null) {
			if (!codigoArt.equals(Constantes.EMPTY)) {
				categoriaFilter = new JqGridFilter("codigo", "'" + codigoArt + "'", "=");
				delimitadores.addFiltro(categoriaFilter);
			}
		}
		categoriaFilter = new JqGridFilter("cantidadPaquete", "'" + Constantes.ARTICULO_PAQUETE_TIPO_INACTIVO + "'", "=");
		delimitadores.addFiltro(categoriaFilter);
		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		for (Iterator<Object> iterator = objetos.iterator(); iterator.hasNext();) {
			Articulo object = (Articulo) iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				solicitudList.add(new ArticuloCommand(object));
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

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticuloPaquetesAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarPaquetesAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "codigoArt", required = false) String codigoArt) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter categoriaFilter = null;
		if (codigoArt != null) {
			if (!codigoArt.equals(Constantes.EMPTY)) {
				categoriaFilter = new JqGridFilter("codigo", "'" + codigoArt + "'", "=");
				delimitadores.addFiltro(categoriaFilter);
			}
		}
		categoriaFilter = new JqGridFilter("cantidadPaquete", "'" + Constantes.ARTICULO_PAQUETE_TIPO_ACTIVO + "'", "=");
		delimitadores.addFiltro(categoriaFilter);

		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		for (Iterator<Object> iterator = objetos.iterator(); iterator.hasNext();) {
			Articulo object = (Articulo) iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				solicitudList.add(new ArticuloCommand(object));
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

	@SuppressWarnings("all")
	@RequestMapping(value = "/local/ListarArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarLocalAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "codigoArt", required = false) String codigoArt) throws IOException, ServletException {
		if (validateTokenBo.validarTokenApis(request) == false) {
			DataTableDelimitador delimitadores = null;
			delimitadores = new DataTableDelimitador(request, "Articulo");
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
			List<Object> solicitudList = new ArrayList<Object>();
			respuestaService.setRecordsTotal(0l);
			respuestaService.setRecordsFiltered(0l);
			respuestaService.setAaData(solicitudList);
			return respuestaService;
		}
		DataTableDelimitador delimitadores = null;
		String nombreUsuario = request.getUserPrincipal().getName();
		return articuloBo.listarByCodigoArticulo(request, response, codigoArt, nombreUsuario);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticuloMinimosAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarMinimosAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "codigoArt", required = false) String codigoArt) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter categoriaFilter = null;
		if (codigoArt != null) {
			if (!codigoArt.equals(Constantes.EMPTY)) {
				categoriaFilter = new JqGridFilter("codigo", "'" + codigoArt + "'", "=");
				delimitadores.addFiltro(categoriaFilter);
			}
		}
		categoriaFilter = new JqGridFilter("obj.cantidad <= obj.minimo ");
		delimitadores.addFiltro(categoriaFilter);
		categoriaFilter = new JqGridFilter("obj.contable = 'Si' ");
		delimitadores.addFiltro(categoriaFilter);

		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		for (Iterator<Object> iterator = objetos.iterator(); iterator.hasNext();) {
			Articulo object = (Articulo) iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				solicitudList.add(new ArticuloCommand(object));
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

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticuloXCategoriaAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarCategoriaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "categoria", required = false) String categoria, @RequestParam(value = "estado", required = false) String estado, @RequestParam(value = "minimoMaximo", required = false) String minimoMaximo) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter categoriaFilter = null;
		if (!categoria.equals(Constantes.COMBO_TODOS)) {
			if (categoria != null) {
				if (!categoria.equals(Constantes.EMPTY)) {
					categoriaFilter = new JqGridFilter("categoria.id", "'" + categoria + "'", "=");
					delimitadores.addFiltro(categoriaFilter);
				}
			}
		}
		if (!estado.equals(Constantes.COMBO_TODOS)) {
			categoriaFilter = new JqGridFilter("estado", "'" + estado + "'", "=");
			delimitadores.addFiltro(categoriaFilter);

		}
		if (minimoMaximo != null && !minimoMaximo.equals(Constantes.COMBO_TODOS)) {
			if (minimoMaximo.equals(Constantes.ARTICULO_MINIMO)) {
				categoriaFilter = new JqGridFilter("obj.cantidad <= obj.minimo ");
				delimitadores.addFiltro(categoriaFilter);

			}
			if (minimoMaximo != null && minimoMaximo.equals(Constantes.ARTICULO_MAXIMO)) {
				categoriaFilter = new JqGridFilter("obj.cantidad >= obj.minimo ");
				delimitadores.addFiltro(categoriaFilter);
			}

		}

		Long total = dataTableBo.contar(delimitadores);
		Collection<Object> objetos = dataTableBo.listar(delimitadores);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		for (Iterator<Object> iterator = objetos.iterator(); iterator.hasNext();) {
			Articulo object = (Articulo) iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				solicitudList.add(new ArticuloCommand(object));
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
	 * Descarga del excel de Totales por categorias
	 * @param request
	 * @param response
	 * @param categoria
	 * @param estado
	 * @param minimoMaximo
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/DescargarArticuloXCategoriaAjax.do", method = RequestMethod.GET)
	public void descargarArticuloXCategoriaAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "idCategoria", required = false) String idCategoria, @RequestParam(value = "estado", required = false) String estado, @RequestParam(value = "minimoMaximo", required = false) String minimoMaximo) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Long codigoCategoria = Constantes.ZEROS_LONG;
		if (idCategoria != null) {
			if (!idCategoria.equals(Constantes.COMBO_TODOS)) {
				codigoCategoria = Long.parseLong(idCategoria);
			}

		}
		Categoria categoria = categoriaBo.buscar(codigoCategoria);

		// Se buscan las facturas
		String cate = Constantes.EMPTY;
		Collection<Articulo> articulos = articuloBo.findByCategoriaAndEmpresaAndEstadoAndMinimoMaximo(usuario.getEmpresa(), categoria, estado, minimoMaximo);
		if (categoria != null) {
			cate = categoria.getDescripcion().trim();
		} else {
			cate = "Inventario";
		}

		cate = cate.replace(" ", "");
		String nombreArchivo = "Totales_" + cate + ".xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelArticuloXCategoriaAjax(articulos);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelArticuloXCategoriaAjax(Collection<Articulo> articulos) {
		List<Object> list = new ArrayList<Object>();
		for (Iterator<Articulo> iterator = articulos.iterator(); iterator.hasNext();) {
			Articulo object = iterator.next();
			// no se carga el usuario del sistema el id -1
			if (object.getId().longValue() > 0L) {
				list.add(new ArticuloCommand(object));
			}
		}
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Categoria", "Estado", "#Codigo", "Descripcion", "Cantidad", "Minimo", "Maximo", "Costo", "Precio Publico", "Total Costo", "Impuesto Esperado", "Venta Esperada", "Ganancia Esperada");
		new SimpleExporter().gridExport(headers, list, " categoria.descripcion,estado,codigo, descripcion, cantidad,minimo,maximo,costoSTR,precioPublicoSTR,totalCostoSTR,totalImpuestoSTR,totalVentaSTR,totalGananciaSTR", baos);
		return baos;
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarPorDescripcionCodigoArticuloAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarDescripcionCodigoArticulosAjax(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, @ModelAttribute String descArticulo, @RequestParam String codigoArt) {

		DataTableDelimitador delimitadores = null;
		String valorDescripcion = request.getParameter("descArticulo");
		delimitadores = new DataTableDelimitador(request, "Articulo");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);

		}
		JqGridFilter categoriaFilter = null;
		if (codigoArt != null) {
			if (!codigoArt.equals(Constantes.EMPTY)) {
				categoriaFilter = new JqGridFilter("codigo", "'" + codigoArt + "'", "=");
				delimitadores.addFiltro(categoriaFilter);
			}
		}
		if (valorDescripcion != null) {
			if (!valorDescripcion.equals(Constantes.EMPTY)) {
				categoriaFilter = new JqGridFilter("descripcion", valorDescripcion, " like ");
				delimitadores.addFiltro(categoriaFilter);

			}

		}

		categoriaFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO + "'", "=");
		delimitadores.addFiltro(categoriaFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Paginacion de la venta
	 * @param request
	 * @param model
	 * @param parametrosPaginacion
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarPaginacionArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarArticulosAjax(HttpServletRequest request, ModelMap model, @ModelAttribute ParametrosPaginacion parametrosPaginacion) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);

		}

		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		if (delimitadores.getColumnData() == null && usuarioSesion.getEmpresa().getOrdenaCategoriaArticulos().equals(1)) {
			// Se ordena por prioridad por defecto se crearon en 9999
			delimitadores.setColumnData("prioridad, id");
			delimitadores.setColumnOrderDir("asc");
		}

		delimitadores.addFiltro(new JqGridFilter("categoria.id", "'" + parametrosPaginacion.getCategoria().getId().toString() + "'", "="));
		delimitadores.addFiltro(new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "="));
		if (parametrosPaginacion.getTipoVenta() != null) {
			if (!parametrosPaginacion.getTipoVenta().equals(Constantes.SI_MOSTRAR_IMPUESTO_10_PORCIENTO)) {
				delimitadores.addFiltro(new JqGridFilter("codigo", Constantes.CODIGO_ARTICULO_IMPUESTO_SERVICIO, "!="));
			}
		}

		delimitadores.setLength(parametrosPaginacion.getCantidadPorPagina());
		delimitadores.setStart(parametrosPaginacion.getPaginaActual());

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Crear un articulo
	 * @param request
	 * @param model
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @RequestParam(value = "idPaquete", required = false) Integer idPaquete, @RequestParam(value = "esPaquete", required = false) Integer esPaquete, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {
		try {
			esPaquete = esPaquete == null ? Constantes.ZEROS : esPaquete;
			if (esPaquete != null && esPaquete.equals(Constantes.ZEROS)) {
				articulo.setCantidadPaquete(Constantes.ZEROS);
				articulo.setCodigoSecundario(Constantes.EMPTY);
			} else {
				articulo.setCantidadPaquete(idPaquete);
			}
			return articuloBo.agregar(request, articulo, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	@RequestMapping(value = "/local/AgregarArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator<?> agregarLocal(HttpServletRequest request, ModelMap model, @RequestParam(value = "idPaquete", required = false) Integer idPaquete, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {

		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			articulo.setCantidadPaquete(idPaquete);
			return articuloBo.agregar(request, articulo, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Modificar Articulo
	 * @param request
	 * @param model
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ModificarArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @RequestParam(value = "idPaquete", required = false) Integer idPaquete, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {

		try {
			articulo.setCantidadPaquete(idPaquete);
			return articuloBo.modificar(request, articulo, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/local/ModificarArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {

		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}

			return articuloBo.modificar(request, articulo, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
		}
	}

	/**
	 * Mostrar articulo por id
	 * @param request
	 * @param model
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarArticuloAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {
		try {
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			Articulo articuloBD = articuloBo.buscar(articulo.getId());
			;
			if (articuloBD == null) {
				if (articulo.getCodigo() != null) {
					if (!articulo.getCodigo().equals(Constantes.EMPTY)) {
						articuloBD = articuloBo.buscarPorCodigoYEmpresa(articulo.getCodigo(), usuarioSesion.getEmpresa());
					}

				}

			}
			if (articuloBD == null) {
				result.rejectValue("codigo", "error.articulo.codigo.no.existe");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.codigo.no.existe", result.getAllErrors());
			}

			ArticuloCommand articuloCommand = new ArticuloCommand(articuloBD);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", articuloCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/MostrarPorCodigoAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam Double precioPublico, @RequestParam String codigo, BindingResult result, SessionStatus status) throws Exception {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBD = articuloBo.buscarPorCodigoYEmpresa(codigo, usuario.getEmpresa());

			if (articuloBD == null) {
				result.rejectValue("codigo", "error.articulo.codigo.no.existe");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", articuloBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CambiarPrecioAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cambiarPrecio(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam Double precioPublico, @RequestParam String codigo, @RequestParam String tipoImpuesto, @RequestParam Double impuesto, @RequestParam String descripcion, @RequestParam String tipoCodigo, String unidadMedida, BindingResult result, SessionStatus status) throws Exception {

		try {

			return articuloBo.cambiarPrecio(request, response, articulo, precioPublico, codigo, tipoImpuesto, impuesto, descripcion, tipoCodigo, unidadMedida, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/local/CambiarPrecioAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cambiarPrecioLocal(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam Double precioPublico, @RequestParam String codigo, @RequestParam String tipoImpuesto, @RequestParam Double impuesto, @RequestParam String descripcion, @RequestParam String tipoCodigo, String unidadMedida, BindingResult result, SessionStatus status) throws Exception {

		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return articuloBo.cambiarPrecio(request, response, articulo, precioPublico, codigo, tipoImpuesto, impuesto, descripcion, tipoCodigo, unidadMedida, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/CambiarPrecioArticulo.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cambiarPrecioArticulo(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute CambiarPrecioArticuloCommand cambiarPrecioArticuloCommand, BindingResult result, SessionStatus status) throws Exception {
		try {

			return articuloBo.cambiarPrecioArticulo(request, response, model, cambiarPrecioArticuloCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/local/CambiarPrecioArticulo.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cambiarPrecioArticuloLocal(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute CambiarPrecioArticuloCommand cambiarPrecioArticuloCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return articuloBo.cambiarPrecioArticulo(request, response, model, cambiarPrecioArticuloCommand, result);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/eliminarArticuloAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator eliminarArticulo(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam String codigo, BindingResult result, SessionStatus status) throws Exception {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBD = articuloBo.buscarPorCodigoYEmpresa(codigo, usuario.getEmpresa());

			if (articuloBD == null) {
				result.rejectValue("codigo", "error.articulo.codigo.no.existe");
			}

			Detalle detalle = detalleBo.findByCodigoAndEmpresa(codigo, usuario.getEmpresa());
			if (detalle != null) {
				result.rejectValue("descripcion", "error.articulo.con.facturas.asociadas");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			articuloBo.eliminar(articuloBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.eliminado.correctamente", articuloBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Buscar Articulo por id del inventario
	 * @param request
	 * @param model
	 * @param articulo findArticuloByCodigojax
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findArticuloByCodigojax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator findArticuloByCodigojax(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, HttpServletResponse response, @RequestParam String codigoArticulo, BindingResult result, SessionStatus status) {
		try {

			return articuloBo.findArticuloByCodigojax(request, articulo, response, codigoArticulo, result);
		} catch (Exception e) {
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.codigo.no.existe.inve", result.getAllErrors());
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/local/findArticuloByCodigojax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator findArticuloByCodigojaxLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, HttpServletResponse response, @RequestParam String codigoArticulo, BindingResult result, SessionStatus status) {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return articuloBo.findArticuloByCodigojax(request, articulo, response, codigoArticulo, result);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class ARTICULO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class ARTICULO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.noExiste");
			}
		}
	}

}

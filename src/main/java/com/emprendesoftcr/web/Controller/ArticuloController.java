package com.emprendesoftcr.web.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.CategoriaBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.DetalleBo;
import com.emprendesoftcr.Bo.KardexBo;
import com.emprendesoftcr.Bo.TarifaIVAIBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Detalle;
import com.emprendesoftcr.modelo.Marca;
import com.emprendesoftcr.modelo.TarifaIVAI;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.pdf.GondolaArticuloPdfView;
import com.emprendesoftcr.web.command.ArticuloCambioCategoriaGrupal;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.command.ParametrosPaginacion;
import com.emprendesoftcr.web.command.TotalInventarioCommand;
import com.emprendesoftcr.web.propertyEditor.ArticuloPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.CategoriaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MarcaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;

/**
 * Control de los articulos de una empresa ArticuloController.
 * @author jose.
 * @since 19 abr. 2018
 */
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
	private DetalleBo																				detalleBo;

	@Autowired
	private CategoriaBo																			categoriaBo;

	@Autowired
	private TarifaIVAIBo																		tarifaIVAIBo;

	@Autowired
	private KardexBo																				kardexBo;

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

	@RequestMapping(value = "/ListarKardex", method = RequestMethod.GET)
	public String listarKardex(ModelMap model) {
		return "views/articulos/ListarKardex";
	}

	@RequestMapping(value = "/CambiarPrecio", method = RequestMethod.GET)
	public String cambiarPrecio(ModelMap model) {
		return "views/articulos/CambioPrecio";
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

	@RequestMapping(value = "/TotalInventarioAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public TotalInventarioCommand totalFacturasAjax(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		return articuloBo.sumarInventarios(usuario.getEmpresa().getId());
	}

	// Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarInventarioAjax.do", method = RequestMethod.GET)
	public void descargarInventarioAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Collection<Articulo> articulos = articuloBo.articulosBy(usuario.getEmpresa());

		String nombreArchivo = "Inventario.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelArticulos(articulos);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelArticulos(Collection<Articulo> articulos) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Fecha Ultima Actualizacion", "Categoria", "#Codigo", "Descripcion", "Cantidad", "Costo", "Total Costo(Costo X Cantidad)", "Impuesto", "Precio Publico", "Total Venta Esperada(cantidadXPrecioPublico)");
		new SimpleExporter().gridExport(headers, articulos, "updated_atSTR,categoria.descripcion, codigo, descripcion, cantidad, costo,totalCosto, impuesto,precioPublico,totalPrecioPublico", baos);
		return baos;
	}

//Descarga de manuales de usuario de acuerdo con su perfil
	@RequestMapping(value = "/DescargarInventarioExistenciasAjax.do", method = RequestMethod.GET)
	public void descargarInventarioExistenciasAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

		// Se buscan las facturas
		Collection<Articulo> articulos = articuloBo.articulosOrderCategoria(usuario.getEmpresa());

		String nombreArchivo = "InventarioExistencias.xls";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

		// Se prepara el excell
		ByteArrayOutputStream baos = createExcelArticulosExistencias(articulos);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

		int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			response.getOutputStream().write(buffer, 0, bytesRead);
		}
	}

	private ByteArrayOutputStream createExcelArticulosExistencias(Collection<Articulo> articulos) {
		// Se prepara el excell
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String> headers = Arrays.asList("Categoria", "#Codigo", "Descripcion", "Cantidad Actual", "#Cantidad Revision Fisica");
		new SimpleExporter().gridExport(headers, articulos, " categoria.descripcion,codigo, descripcion, cantidad", baos);
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
		if (!minimoMaximo.equals(Constantes.COMBO_TODOS)) {
			if (minimoMaximo.equals(Constantes.ARTICULO_MINIMO)) {
				categoriaFilter = new JqGridFilter("obj.cantidad <= obj.minimo ");
				delimitadores.addFiltro(categoriaFilter);

			}
			if (minimoMaximo.equals(Constantes.ARTICULO_MAXIMO)) {
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
		List<String> headers = Arrays.asList("Categoria", "#Codigo", "Descripcion", "Cantidad", "Minimo", "Maximo", "Costo", "Precio Publico", "Total Costo", "Impuesto Esperado", "Venta Esperada", "Ganancia Esperada");
		new SimpleExporter().gridExport(headers, list, " categoria.descripcion,codigo, descripcion, cantidad,minimo,maximo,costoSTR,precioPublicoSTR,totalCostoSTR,totalImpuestoSTR,totalVentaSTR,totalGananciaSTR", baos);
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
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			articulo.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articulo.setTipoImpuesto1(articulo.getTipoImpuesto1() == null ? Constantes.EMPTY : articulo.getTipoImpuesto1());
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setImpuesto1(articulo.getImpuesto1() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto1());
			articulo.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articulo.setCodigoTarifa1(articulo.getCodigoTarifa1() == null ? Constantes.EMPTY : articulo.getCodigoTarifa1());
			articulo.setTipoImpuesto1(Constantes.EMPTY);
			articulo.setImpuesto1(Constantes.ZEROS_DOUBLE);
			articulo.setCodigoTarifa1(Constantes.EMPTY);
			articulo.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.ZEROS : articulo.getBaseImponible());

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());

			Articulo articuloBd = null;
			articuloBd = articuloBo.buscarPorDescripcionYEmpresa(articulo.getDescripcion().trim(), usuarioSesion.getEmpresa());
			if (articuloBd != null) {
				result.rejectValue("descripcion", "error.articulo.descripcion.existe");
			}

			articuloBd = articuloBo.buscarPorCodigoYEmpresa(articulo.getCodigo().trim(), usuarioSesion.getEmpresa());
			if (articuloBd != null) {
				result.rejectValue("codigo", "error.articulo.codigo.existe");
			}
			if (articulo.getPrecioPublico() == null) {
				result.rejectValue("costo", "error.articulo.precioPublico.mayorCero");
			}
			if (articulo.getPrecioPublico() == 0) {
				result.rejectValue("costo", "error.articulo.precioPublico.mayorCero");
			}
			if (articulo.getCantidad() != null) {
				if (articulo.getCantidad() == Constantes.ZEROS_DOUBLE) {
					articulo.setCantidad(Constantes.ZEROS_DOUBLE);
				}
			}
			if (articulo.getCantidad() == null) {
				articulo.setCantidad(Constantes.ZEROS_DOUBLE);
			}

			if (articulo.getTipoImpuesto() != null) {
				articulo.setTipoImpuesto(articulo.getTipoImpuesto().equals("Exento") ? Constantes.EMPTY : articulo.getTipoImpuesto());
			}
			if (articulo.getTipoImpuesto1() != null) {
				articulo.setTipoImpuesto1(articulo.getTipoImpuesto1().equals("Exento") ? Constantes.EMPTY : articulo.getTipoImpuesto1());
			}
			if (!articulo.getCodigoTarifa().equals(Constantes.EMPTY)) {
				TarifaIVAI tarifaIVAI = tarifaIVAIBo.findByCodigoTarifa(articulo.getCodigoTarifa());
				if (tarifaIVAI == null) {
					result.rejectValue("codigoTarifa", "error.articulo.codigo.tarifa.no.existe");
				} else {
					if (!tarifaIVAI.getMonto().equals(articulo.getImpuesto())) {
						result.rejectValue("codigoTarifa", "error.articulo.codigo.tarifa.no.tiene.porcentaje.correcto");
					} else {
						articulo.setImpuesto(tarifaIVAI.getMonto());
					}
				}
			}
			if (!articulo.getCodigoTarifa1().equals(Constantes.EMPTY)) {
				TarifaIVAI tarifaIVAI = tarifaIVAIBo.findByCodigoTarifa(articulo.getCodigoTarifa1());
				if (tarifaIVAI == null) {
					result.rejectValue("codigoTarifa1", "error.articulo.codigo.tarifa.no.existe");
				} else {
					if (!tarifaIVAI.getMonto().equals(articulo.getImpuesto1())) {
						result.rejectValue("impuesto1", "error.articulo.codigo.tarifa.no.tiene.porcentaje.correcto");
					} else {
						articulo.setImpuesto1(tarifaIVAI.getMonto());
					}
				}
			}
			if (!articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL) && !articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_ARTICULO)) {
					if (articulo.getImpuesto().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto", "error.articulo.codigo.impuesto.no.tiene.porcentaje.correcto");

					}
				}

			}
			if (!articulo.getTipoImpuesto1().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuesto1().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL) && !articulo.getTipoImpuesto1().equals(Constantes.TIPO_IMPUESTO_VENTA_ARTICULO)) {
					if (articulo.getImpuesto1().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto1", "error.articulo.codigo.impuesto.no.tiene.porcentaje.correcto");

					}
				}

			}

			if (articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				articulo.setImpuesto(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa(Constantes.EMPTY);
			}
			if (articulo.getTipoImpuesto1().equals(Constantes.EMPTY)) {
				articulo.setImpuesto1(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa1(Constantes.EMPTY);
			}
			if (!articulo.getTipoImpuesto1().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuesto1().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL)) {
					if (articulo.getImpuesto1().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto1", "error.articulo.tipoImpuesto1.cero");
					}
				}
				if (articulo.getTipoImpuesto1().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO)) {
					if (!articulo.getImpuesto1().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO_VALOR)) {
						result.rejectValue("tipoImpuesto1", "error.articulo.tipoImpuesto1.selectivoConsumo");
					}

				}
			}
			if (!articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
//				if (!articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL)) {
//					if (articulo.getImpuesto().equals(Constantes.ZEROS_DOUBLE)) {
//						result.rejectValue("impuesto1", "error.articulo.tipoImpuesto1.cero");
//					}
//				}
				if (articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO)) {
					if (!articulo.getImpuesto().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO_VALOR)) {
						result.rejectValue("tipoImpuesto1", "error.articulo.tipoImpuesto1.selectivoConsumo");
					}

				}
				if(!articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL)) {
					if(articulo.getBaseImponible().equals(Constantes.BASE_IMPONIBLE_ACTIVO)) {
						result.rejectValue("tipoImpuesto", "error.articulo.tipoImpuesto1.base.imponible.incorrecta");
					}	
				}
			
			}
		
			
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			articulo.setCreated_at(new Date());
			articulo.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articulo.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articulo.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articulo.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getGananciaPrecioEspecial());
			articulo.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getGananciaPrecioMayorista());
			articulo.setCantidad(articulo.getCantidad() == null ? Constantes.ZEROS_DOUBLE : articulo.getCantidad());
			articulo.setCosto(articulo.getCosto() == null ? Constantes.ZEROS_DOUBLE : articulo.getCosto());

			articulo.setEmpresa(usuarioSesion.getEmpresa());
			articulo.setUpdated_at(new Date());
			articulo.setEstado(Constantes.ESTADO_ACTIVO);
			articulo.setGananciaPrecioPublico(articulo.getGananciaPrecioPublico() != null ? articulo.getGananciaPrecioPublico() : Constantes.ZEROS_DOUBLE);
			articulo.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() != null ? articulo.getGananciaPrecioMayorista() : Constantes.ZEROS_DOUBLE);
			articulo.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() != null ? articulo.getGananciaPrecioEspecial() : Constantes.ZEROS_DOUBLE);
			articulo.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articulo.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setUsuario(usuarioSesion);
			articulo.setTipoImpuesto1(articulo.getTipoImpuesto1() == null ? Constantes.EMPTY : articulo.getTipoImpuesto1());
			articulo.setImpuesto1(articulo.getImpuesto1() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto1());
			articulo.setPesoTransporte(articulo.getPesoTransporte() == null ? Constantes.ZEROS_DOUBLE : articulo.getPesoTransporte());
			articulo.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articulo.setCodigoTarifa1(articulo.getCodigoTarifa1() == null ? Constantes.EMPTY : articulo.getCodigoTarifa1());
			articulo.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.ZEROS : articulo.getBaseImponible());
			articuloBo.agregar(articulo);

			if (usuarioSesion.getEmpresa().getTieneInventario().equals(Constantes.ESTADO_ACTIVO)) {
				if (!articulo.getCantidad().equals(Constantes.ZEROS_DOUBLE)) {
					kardexBo.entrada(articulo, Constantes.ZEROS_DOUBLE, articulo.getCantidad(), Constantes.OBSERVACION_INICIAL_INVENTARIO_NUEVO, Constantes.CONSECUTIVO_INICIAL_INVENTARIO_NUEVO, Constantes.KARDEX_TIPO_ENTRADA, Constantes.MOTIVO_INICIAL_INVENTARIO_NUEVO, usuarioSesion);

				}

			}

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.agregar.correctamente", articulo);

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
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, BindingResult result, SessionStatus status) throws Exception {
		try {
			articulo.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());

			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articulo.setTipoImpuesto1(Constantes.EMPTY);
			articulo.setImpuesto1(Constantes.ZEROS_DOUBLE);
			articulo.setCodigoTarifa1(Constantes.EMPTY);
			articulo.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.BASE_IMPONIBLE_INACTIVO : articulo.getBaseImponible());
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (articulo.getTipoImpuesto() != null) {
				articulo.setTipoImpuesto(articulo.getTipoImpuesto().equals("Exento") ? Constantes.EMPTY : articulo.getTipoImpuesto());
			}
			if (articulo.getTipoImpuesto1() != null) {
				articulo.setTipoImpuesto1(articulo.getTipoImpuesto1().equals("Exento") ? Constantes.EMPTY : articulo.getTipoImpuesto1());
			}
			if (articulo.getTipoCodigo() == null) {
				articulo.setTipoCodigo("04");
			}
			Articulo articuloBd = articuloBo.buscar(articulo.getId());
			Articulo articuloValidar = null;
			if (!articuloBd.getDescripcion().equals(articulo.getDescripcion().trim())) {
				articuloValidar = articuloBo.buscarPorDescripcionYEmpresa(articulo.getDescripcion().trim(), usuarioSesion.getEmpresa());
				if (articuloValidar != null) {
					result.rejectValue("descripcion", "error.articulo.descripcion.existe");
				}
			}
			if (!articuloBd.getCodigo().equals(articulo.getCodigo().trim())) {
				articuloValidar = articuloBo.buscarPorCodigoYEmpresa(articulo.getCodigo().trim(), usuarioSesion.getEmpresa());
				if (articuloValidar != null) {
					result.rejectValue("codigo", "error.articulo.codigo.existe");
				}
			}
			if (articulo.getPrecioPublico() == null) {
				result.rejectValue("precioPublico", "error.articulo.precioPublico.mayorCero");
			}
			if (articulo.getPrecioPublico() == 0) {
				result.rejectValue("precioPublico", "error.articulo.precioPublico.mayorCero");
			}

			if (!articulo.getCodigoTarifa().equals(Constantes.EMPTY)) {
				TarifaIVAI tarifaIVAI = tarifaIVAIBo.findByCodigoTarifa(articulo.getCodigoTarifa());
				if (tarifaIVAI == null) {
					result.rejectValue("codigoTarifa", "error.articulo.codigo.tarifa.no.existe");
				} else {
					if (!tarifaIVAI.getMonto().equals(articulo.getImpuesto())) {
						result.rejectValue("impuesto", "error.articulo.codigo.tarifa.no.tiene.porcentaje.correcto");
					} else {
						articulo.setImpuesto(tarifaIVAI.getMonto());
					}
				}
			}
			if (!articulo.getCodigoTarifa1().equals(Constantes.EMPTY)) {
				TarifaIVAI tarifaIVAI = tarifaIVAIBo.findByCodigoTarifa(articulo.getCodigoTarifa1());
				if (tarifaIVAI == null) {
					result.rejectValue("codigoTarifa1", "error.articulo.codigo.tarifa.no.existe");
				} else {
					if (!tarifaIVAI.getMonto().equals(articulo.getImpuesto1())) {
						result.rejectValue("impuesto1", "error.articulo.codigo.tarifa.no.tiene.porcentaje.correcto");
					} else {
						articulo.setImpuesto1(tarifaIVAI.getMonto());
					}
				}
			}
			if (!articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL) && !articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_ARTICULO)) {
					if (articulo.getImpuesto().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto", "error.articulo.codigo.impuesto.no.tiene.porcentaje.correcto");

					}
				}

			}
			if (!articulo.getTipoImpuesto1().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuesto1().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL) && !articulo.getTipoImpuesto1().equals(Constantes.TIPO_IMPUESTO_VENTA_ARTICULO)) {
					if (articulo.getImpuesto1().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto1", "error.articulo.codigo.impuesto.no.tiene.porcentaje.correcto");

					}
				}

			}

			if (articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				articulo.setImpuesto(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa(Constantes.EMPTY);
			}
			if (articulo.getTipoImpuesto1().equals(Constantes.EMPTY)) {
				articulo.setImpuesto1(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa1(Constantes.EMPTY);
			}
			if (!articulo.getTipoImpuesto1().equals(Constantes.EMPTY)) {
				if (!articulo.getTipoImpuesto1().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL)) {
					if (articulo.getImpuesto1().equals(Constantes.ZEROS_DOUBLE)) {
						result.rejectValue("impuesto1", "error.articulo.tipoImpuesto1.cero");
					}
				}
				if (articulo.getTipoImpuesto1().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO)) {
					if (!articulo.getImpuesto1().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO_VALOR)) {
						result.rejectValue("tipoImpuesto1", "error.articulo.tipoImpuesto1.selectivoConsumo");
					}

				}
			}
			if (!articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				if (articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO)) {
					if (!articulo.getImpuesto().equals(Constantes.TIPO_IMPUESTO_SELECTIVO_CONSUMO_ARTICULO_VALOR)) {
						result.rejectValue("tipoImpuesto", "error.articulo.tipoImpuesto1.selectivoConsumo");
					}

				}
				if(!articulo.getTipoImpuesto().equals(Constantes.TIPO_IMPUESTO_VENTA_IVA_CALCULO_ESPECIAL)) {
					if(articulo.getBaseImponible().equals(Constantes.BASE_IMPONIBLE_ACTIVO)) {
						result.rejectValue("tipoImpuesto", "error.articulo.tipoImpuesto1.base.imponible.incorrecta");
					}	
				}
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			articuloBd.setMaximo(articulo.getMaximo() == null ? Constantes.ZEROS_DOUBLE : articulo.getMaximo());
			articuloBd.setMinimo(articulo.getMinimo() == null ? Constantes.ZEROS_DOUBLE : articulo.getMinimo());
			articuloBd.setUpdated_at(new Date());
			articuloBd.setCosto(articulo.getCosto() == null ? Constantes.ZEROS_DOUBLE : articulo.getCosto());
			articuloBd.setMaximo(articulo.getMaximo());
			articuloBd.setMinimo(articulo.getMinimo());
			articuloBd.setMarca(articulo.getMarca());
			articuloBd.setDescripcion(articulo.getDescripcion());
			articuloBd.setContable(articulo.getContable());
			articuloBd.setCategoria(articulo.getCategoria());
			articuloBd.setUnidadMedida(articulo.getUnidadMedida());
			articuloBd.setTipoCodigo(articulo.getTipoCodigo());
			articuloBd.setEstado(articulo.getEstado());
			articuloBd.setGananciaPrecioPublico(articulo.getGananciaPrecioPublico() != null ? articulo.getGananciaPrecioPublico() : Constantes.ZEROS_DOUBLE);
			articuloBd.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() != null ? articulo.getGananciaPrecioMayorista() : Constantes.ZEROS_DOUBLE);
			articuloBd.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() != null ? articulo.getGananciaPrecioEspecial() : Constantes.ZEROS_DOUBLE);
			articuloBd.setPrecioPublico(articulo.getPrecioPublico());
			articuloBd.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articuloBd.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articuloBd.setUsuario(usuarioSesion);
			articuloBd.setCodigo(articulo.getCodigo().trim());
			articuloBd.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articuloBd.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articuloBd.setComanda(articulo.getComanda());
			articuloBd.setPrioridad(articulo.getPrioridad());
			articuloBd.setTipoImpuesto1(articulo.getTipoImpuesto1() == null ? Constantes.EMPTY : articulo.getTipoImpuesto1());
			articuloBd.setImpuesto1(articulo.getImpuesto1() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto1());
			articuloBd.setPesoTransporte(articulo.getPesoTransporte() == null ? Constantes.ZEROS_DOUBLE : articulo.getPesoTransporte());
			articuloBd.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articuloBd.setCodigoTarifa1(articulo.getCodigoTarifa1() == null ? Constantes.EMPTY : articulo.getCodigoTarifa1());
			articuloBd.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.ZEROS : articulo.getBaseImponible());
			articuloBo.modificar(articuloBd);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente", articuloBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
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
			ArticuloCommand articuloCommand = new ArticuloCommand(articuloBo.buscar(articulo.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", articuloCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

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

	@RequestMapping(value = "/CambiarPrecioAjax", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator cambiarPrecio(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute Articulo articulo, @RequestParam Double precioPublico, @RequestParam String codigo, @RequestParam String tipoImpuesto, @RequestParam Double impuesto, @RequestParam String descripcion, @RequestParam String tipoCodigo, String unidadMedida, BindingResult result, SessionStatus status) throws Exception {
		try {
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setImpuesto1(articulo.getImpuesto1() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto1());
			articulo.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articulo.setCodigoTarifa1(articulo.getCodigoTarifa1() == null ? Constantes.EMPTY : articulo.getCodigoTarifa1());
			Articulo articuloBD = articuloBo.buscarPorCodigoYEmpresa(codigo, usuario.getEmpresa());

			if (articuloBD == null) {
				result.rejectValue("codigo", "error.articulo.codigo.no.existe");
			}

			Articulo articuloValidar = null;
			if (!articuloBD.getDescripcion().equals(descripcion)) {
				articuloValidar = articuloBo.buscarPorDescripcionYEmpresa(descripcion, usuario.getEmpresa());
				if (articuloValidar != null) {
					result.rejectValue("descripcion", "error.articulo.descripcion.existe");
				}
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}

			if (tipoCodigo == null) {
				articuloBD.setTipoCodigo("04");
			} else {
				articuloBD.setTipoCodigo(tipoCodigo);
			}
			if (articulo.getTipoImpuesto().equals(Constantes.EMPTY)) {
				articulo.setImpuesto(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa(Constantes.EMPTY);
			}
			if (articulo.getTipoImpuesto1().equals(Constantes.EMPTY)) {
				articulo.setImpuesto1(Constantes.ZEROS_DOUBLE);
				articulo.setCodigoTarifa1(Constantes.EMPTY);
			}
			articuloBD.setUpdated_at(new Date());
			articuloBD.setCosto(articulo.getCosto() == null ? Constantes.ZEROS_DOUBLE : articulo.getCosto());
			articuloBD.setMarca(articulo.getMarca());
			articuloBD.setDescripcion(articulo.getDescripcion());
			articuloBD.setContable(articulo.getContable());
			articuloBD.setCategoria(articulo.getCategoria());
			articuloBD.setUnidadMedida(articulo.getUnidadMedida());
			articuloBD.setTipoCodigo(articulo.getTipoCodigo());
			articuloBD.setEstado(articulo.getEstado());
			articuloBD.setGananciaPrecioPublico(articulo.getGananciaPrecioPublico() != null ? articulo.getGananciaPrecioPublico() : Constantes.ZEROS_DOUBLE);
			articuloBD.setPrecioPublico(articulo.getPrecioPublico());
			articuloBD.setUsuario(usuario);
			articuloBD.setCodigo(articulo.getCodigo().trim());
			articuloBD.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articuloBD.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articuloBD.setComanda(articulo.getComanda());
			articuloBD.setPrioridad(articulo.getPrioridad());
			articuloBD.setTipoImpuesto1(articulo.getTipoImpuesto1() == null ? Constantes.EMPTY : articulo.getTipoImpuesto1());
			articuloBD.setImpuesto1(articulo.getImpuesto1() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto1());
			articuloBD.setPesoTransporte(articulo.getPesoTransporte() == null ? Constantes.ZEROS_DOUBLE : articulo.getPesoTransporte());
			articuloBD.setCodigoTarifa(articulo.getCodigoTarifa() == null ? Constantes.EMPTY : articulo.getCodigoTarifa());
			articuloBD.setCodigoTarifa1(articulo.getCodigoTarifa1() == null ? Constantes.EMPTY : articulo.getCodigoTarifa1());
			articuloBD.setBaseImponible(articulo.getBaseImponible() == null ? Constantes.ZEROS : articulo.getBaseImponible());
			articuloBo.modificar(articuloBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente", articuloBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

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
	 * @param articulo
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findArticuloByCodigojax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator listarAjax(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo, HttpServletResponse response, @RequestParam String codigoArticulo, BindingResult result, SessionStatus status) {
		try {
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			Articulo articuloBD = articuloBo.buscarPorCodigoYEmpresa(codigoArticulo, usuarioSesion.getEmpresa());
			ArticuloCommand articuloCommand = articuloBD == null ? null : new ArticuloCommand(articuloBD);

			if (articuloCommand == null) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.articulo.codigo.no.existe", result.getAllErrors());
			}
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", articuloCommand);
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

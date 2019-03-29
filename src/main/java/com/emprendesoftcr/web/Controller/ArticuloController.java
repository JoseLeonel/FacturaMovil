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

import org.jxls.template.SimpleExporter;
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

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.KardexBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Marca;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.pdf.GondolaArticuloPdfView;
import com.emprendesoftcr.web.command.ArticuloCommand;
import com.emprendesoftcr.web.command.ParametrosPaginacion;
import com.emprendesoftcr.web.command.TotalInventarioCommand;
import com.emprendesoftcr.web.propertyEditor.ArticuloPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.CategoriaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.MarcaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
import com.itextpdf.text.DocumentException;

/**
 * Control de los articulos de una empresa ArticuloController.
 * @author jose.
 * @since 19 abr. 2018
 */
@Controller
public class ArticuloController {

	private static final Function<Object, ArticuloCommand> TO_COMMAND = new Function<Object, ArticuloCommand>() {

		@Override
		public ArticuloCommand apply(Object f) {
			return new ArticuloCommand((Articulo) f);
		};
	};

	@Autowired
	private DataTableBo dataTableBo;

	@Autowired
	private ArticuloBo articuloBo;

	@Autowired
	private KardexBo kardexBo;

	@Autowired
	private UsuarioBo usuarioBo;

	@Autowired
	private ArticuloPropertyEditor articuloPropertyEditor;

	@Autowired
	private MarcaPropertyEditor marcaPropertyEditor;

	@Autowired
	private CategoriaPropertyEditor categoriaPropertyEditor;

	@Autowired
	private StringPropertyEditor stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Articulo.class, articuloPropertyEditor);

		binder.registerCustomEditor(Marca.class, marcaPropertyEditor);
		binder.registerCustomEditor(Categoria.class, categoriaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
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
		List<String> headers = Arrays.asList( "Fecha Ultima Actualizacion","Categoria", "#Codigo", "Descripcion", "Cantidad", "Costo","Impuesto", "Precio Publico","#Cantidad Revision Fisica");
		new SimpleExporter().gridExport(headers, articulos,"updated_atSTR,categoria.descripcion, codigo, descripcion, cantidad, costo, impuesto,precioPublico", baos);
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
		List<String> headers = Arrays.asList(  "Categoria","#Codigo", "Descripcion", "Cantidad Actual", "#Cantidad Revision Fisica");
		new SimpleExporter().gridExport(headers, articulos," categoria.descripcion,codigo, descripcion, cantidad", baos);
		return baos;
	}
	
	@RequestMapping(value = "/PDFGondolaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	public void bajarPDFGondola(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam Long idArticulo) throws Exception {
		try {
			Articulo articuloBD = articuloBo.buscar(idArticulo);
			String	fileName = "Articulo_" +articuloBD.getCodigo().toString() ;

			// ByteArrayOutputStream namePDF = App.main(factura.getNumeroConsecutivo(), factura.getTipoDoc(), facturaElectronica);
			ByteArrayOutputStream namePDF = GondolaArticuloPdfView.main(articuloBD);
			int BUFFER_SIZE = 4096;
			ByteArrayInputStream inputStream = new ByteArrayInputStream(namePDF.toByteArray());
			response.setContentType("application/octet-stream");
			response.setContentLength((int) namePDF.toByteArray().length);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (com.google.zxing.WriterException ex) {
			throw ex;
		}

	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarArticulosActivosAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarArticulosActivosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Articulo");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
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
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response,@RequestParam String codigoArt) {

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

		categoriaFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
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
		if(delimitadores.getColumnData() == null && usuarioSesion.getEmpresa().getOrdenaCategoriaArticulos().equals(1)) {
			//Se ordena por prioridad por defecto se crearon en 9999
			delimitadores.setColumnData("prioridad, id");
			delimitadores.setColumnOrderDir("asc");			
		}

		delimitadores.addFiltro(new JqGridFilter("categoria.id", "'" + parametrosPaginacion.getCategoria().getId().toString() + "'", "="));
		delimitadores.addFiltro(new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "="));
		if(parametrosPaginacion.getTipoVenta() !=null) {
			if(!parametrosPaginacion.getTipoVenta().equals(Constantes.SI_MOSTRAR_IMPUESTO_10_PORCIENTO)) {
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
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());

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

			// if (articulo.getCosto() == null) {
			// result.rejectValue("costo", "error.articulo.costo.mayorCero");
			// }
			// if (articulo.getCosto() == 0) {
			// result.rejectValue("costo", "error.articulo.costo.mayorCero");
			// }
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
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if (articulo.getTipoImpuesto() != null) {
				articulo.setTipoImpuesto(articulo.getTipoImpuesto().equals("Sin impuesto") ? Constantes.EMPTY : articulo.getTipoImpuesto());
			}
			articulo.setCreated_at(new Date());
			articulo.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articulo.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articulo.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articulo.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getGananciaPrecioEspecial());
			articulo.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getGananciaPrecioMayorista());
			articulo.setCantidad(articulo.getCantidad() == null ? Constantes.ZEROS_DOUBLE : articulo.getCantidad());

			articulo.setEmpresa(usuarioSesion.getEmpresa());
			articulo.setUpdated_at(new Date());
			articulo.setEstado(Constantes.ESTADO_ACTIVO);
			articulo.setGananciaPrecioPublico(articulo.getGananciaPrecioPublico() !=null?articulo.getGananciaPrecioPublico():Constantes.ZEROS_DOUBLE);
			articulo.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() !=null?articulo.getGananciaPrecioMayorista():Constantes.ZEROS_DOUBLE);
			articulo.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() !=null?articulo.getGananciaPrecioEspecial():Constantes.ZEROS_DOUBLE);
			articulo.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articulo.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articulo.setUsuario(usuarioSesion);
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
			articulo.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());

			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			if (articulo.getTipoImpuesto() != null) {
				articulo.setTipoImpuesto(articulo.getTipoImpuesto().equals("Sin impuesto") ? Constantes.EMPTY : articulo.getTipoImpuesto());
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
			articuloBd.setGananciaPrecioPublico(articulo.getGananciaPrecioPublico() !=null?articulo.getGananciaPrecioPublico():Constantes.ZEROS_DOUBLE);
			articuloBd.setGananciaPrecioMayorista(articulo.getGananciaPrecioMayorista() !=null?articulo.getGananciaPrecioMayorista():Constantes.ZEROS_DOUBLE);
			articuloBd.setGananciaPrecioEspecial(articulo.getGananciaPrecioEspecial() !=null?articulo.getGananciaPrecioEspecial():Constantes.ZEROS_DOUBLE);
			articuloBd.setPrecioPublico(articulo.getPrecioPublico());
			articuloBd.setPrecioEspecial(articulo.getPrecioEspecial() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioEspecial());
			articuloBd.setPrecioMayorista(articulo.getPrecioMayorista() == null ? Constantes.ZEROS_DOUBLE : articulo.getPrecioMayorista());
			articuloBd.setUsuario(usuarioSesion);
			articuloBd.setCodigo(articulo.getCodigo().trim());
			articuloBd.setTipoImpuesto(articulo.getTipoImpuesto() == null ? Constantes.EMPTY : articulo.getTipoImpuesto());
			articuloBd.setImpuesto(articulo.getImpuesto() == null ? Constantes.ZEROS_DOUBLE : articulo.getImpuesto());
			articuloBd.setComanda(articulo.getComanda());
			articuloBd.setPrioridad(articulo.getPrioridad());
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
			articuloBD.setPrecioPublico(precioPublico);
			articuloBD.setUnidadMedida(unidadMedida);
			articuloBD.setDescripcion(descripcion);
			articuloBD.setTipoImpuesto(tipoImpuesto);
			articuloBD.setImpuesto(impuesto);
      articuloBD.setUpdated_at(new Date());
			articuloBo.modificar(articuloBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("articulo.modificado.correctamente", articuloBD);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	// @RequestMapping(value = "/CambiarPrecioAjax", method = RequestMethod.POST, headers = "Accept=application/json")
	// @ResponseBody
	// public RespuestaServiceValidator mostrar(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idFactura) {
	// try {
	// //Factura facturaBD = facturaBo.findById(idFactura);
	//
	// Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
	//
	// // Se ejecuta este comando pero antes se ejecutan el comando para sacar la llave criptografica desde linux
	//// certificadoBo.agregar(usuario.getEmpresa(), usuario.getEmpresa().getClaveLlaveCriptografica().toString(), usuario.getEmpresa().getNombreLlaveCriptografica());
	// // String xml = facturaXMLServices.getCrearXMLSinFirma(facturaBD);
	// // facturaXMLServices.getFirmarXML(xml, facturaBD.getEmpresa());
	//
	// // KeyStore keyStore = null;
	// // LlaveCriptografica llaveCriptografica = new LlaveCriptografica();
	// //
	// // llaveCriptografica.setPassSignature(usuario.getEmpresa().getClaveLlaveCriptografica().toString());
	// // llaveCriptografica.setPathSignature(usuario.getEmpresa().getNombreLlaveCriptografica());
	// // XadesSigner xadesSigner = llaveCriptograficaService.getSigner(usuario.getEmpresa().getNombreLlaveCriptografica(),usuario.getEmpresa().getClaveLlaveCriptografica().toString());
	// // keyStore = llaveCriptograficaService.getKeyStore(llaveCriptografica);
	//
	// return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", facturaBD);
	// } catch (Exception e) {
	// return RespuestaServiceValidator.ERROR(e);
	// }
	// }

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

package com.emprendesoftcr.web.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Kardex;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.KardexCommand;
import com.emprendesoftcr.web.propertyEditor.ArticuloPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;

/**
 * Registrar la entrada al inventario y Registrar una salida al inventario KardexController.
 * @author jose.
 * @since 13 abr. 2018
 */
@Controller
public class KardexController {

	private static final Function<Object, KardexCommand>	TO_COMMAND	= new Function<Object, KardexCommand>() {

																																			@Override
																																			public KardexCommand apply(Object f) {
																																				return new KardexCommand((Kardex) f);
																																			};
																																		};

	@Autowired
	private KardexBo																			kardexBo;

	@Autowired
	private ArticuloBo																		articuloBo;

	@Autowired
	private DataTableBo																		dataTableBo;


	@Autowired
	private UsuarioBo																			usuarioBo;

	@Autowired
	private StringPropertyEditor													stringPropertyEditor;
	@Autowired
	private ArticuloPropertyEditor												articuloPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Articulo.class, articuloPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	/**
	 * Listar el kardex
	 * @param request
	 * @param response
	 * @param fechaInicio
	 * @param fechaFin
	 * @param idArticulo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/ListarKardexAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarFacturasActivasAndAnuladasAjax(HttpServletRequest request, HttpServletResponse response,@RequestParam String fechaInicio, @RequestParam String fechaFinal, @RequestParam Long idArticulo) {
		Articulo articulo = articuloBo.buscar(idArticulo);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFinal, articulo);

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	/**
	 * Agregar una entrada al inventario de un articulo
	 * @param request
	 * @param model
	 * @param IdInventario
	 * @param kardex
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarEntradaKardex.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarEntrada(HttpServletRequest request, ModelMap model, @RequestParam("IdArticulo") Long IdArticulo, @ModelAttribute Kardex kardex, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Articulo articulo = articuloBo.buscar(IdArticulo);
			if (articulo == null) {
				result.rejectValue("codigo", "error.kardex.articulo.no.existe");
			}
			if(articulo.getEstado().equals(Constantes.ESTADO_INACTIVO)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.kardex.articulo.inactivo", result.getAllErrors());
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			kardexBo.entrada(articulo,articulo.getCantidad(), kardex.getCantidadNueva(), kardex.getObservacion(), Constantes.CONSECUTIVO_INICIAL_INVENTARIO_NUEVO, Constantes.KARDEX_TIPO_ENTRADA, kardex.getMotivo(), usuarioSesion);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("kardex.agregar.entrada.correctamente", articulo);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	/**
	 * Registrar una salida
	 * @param request
	 * @param model
	 * @param IdInventario
	 * @param kardex
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AgregarSalidaKardex.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarSalida(HttpServletRequest request, ModelMap model, @RequestParam("IdArticulo") Long IdArticulo, @ModelAttribute Kardex kardex, BindingResult result, SessionStatus status) throws Exception {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Articulo articulo = articuloBo.buscar(IdArticulo);
			if (articulo == null) {
				result.rejectValue("codigo", "error.kardex.articulo.no.existe");
			}
			if(articulo.getEstado().equals(Constantes.ESTADO_INACTIVO)) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.kardex.articulo.inactivo", result.getAllErrors());
			}
			if (articulo.getCantidad() == Constantes.ZEROS_DOUBLE)
			{
				result.rejectValue("codigo", "error.kardex.articulo.no.existe");
			}
			if (kardex.getCantidadNueva() > articulo.getCantidad()  ) {
				result.rejectValue("cantidadNueva", "error.kardex.articulo.cantidad.mayor.cantidaDelIventario");
			}
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
			kardexBo.salida(articulo,articulo.getCantidad(), kardex.getCantidadNueva(), kardex.getObservacion(), Constantes.CONSECUTIVO_INICIAL_INVENTARIO_NUEVO, Constantes.KARDEX_TIPO_SALIDA,  kardex.getObservacion(), usuarioSesion);
			articulo = articuloBo.buscar(IdArticulo);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("kardex.agregar.salida.correctamente", articulo);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Articulo articulo) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "Kardex");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

			if (articulo != null) {
				delimitador.addFiltro(new JqGridFilter("articulo.id", "'" + articulo.getId().toString() + "'", "="));
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

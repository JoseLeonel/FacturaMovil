package com.emprendesoftcr.web.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import com.emprendesoftcr.Bo.CabysBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.fisco.FacturaElectronicaUtils;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cabys;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.ArticuloCambioCategoriaGrupal;
import com.emprendesoftcr.web.command.CabysCambioGrupal;
import com.emprendesoftcr.web.command.CabysCommand;
import com.emprendesoftcr.web.command.ListCabysHacienda;
import com.emprendesoftcr.web.propertyEditor.CabysPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
import com.google.gson.Gson;

@CrossOrigin
@Controller
public class CabysController {

	private static final Function<Object, CabysCommand>	TO_COMMAND	= new Function<Object, CabysCommand>() {

																																		@Override
																																		public CabysCommand apply(Object f) {
																																			return new CabysCommand((Cabys) f);
																																		};
																																	};

	@Autowired
	private DataTableBo																	dataTableBo;

	@Autowired
	private CabysBo																			cabysBo;

	@Autowired
	private ArticuloBo																			articuloBo;

	

	@Autowired
	private UsuarioBo																		usuarioBo;

	@Autowired
	private CabysPropertyEditor													cabysPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor												empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor												stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Cabys.class, cabysPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarCabys", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/cabys/ListarCabys";
	}
	@RequestMapping(value = "/asociarCabys", method = RequestMethod.GET)
	public String listarasociarCabys(ModelMap model) {
		return "views/cabys/asociarCabys";
	}

	
	
	
	@SuppressWarnings("all")
	@Cacheable(value = "cabysCache")
	@RequestMapping(value = "/ListarCabysDeHaciendaAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "descArticulo", required = false) String descArticulo,@RequestParam(value = "cantidad", required = false) Integer cantidad) {
    cantidad = cantidad != null? cantidad:null;
    cantidad = cantidad != null && cantidad > Constantes.ZEROS? cantidad:null;
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		ListCabysHacienda objetos =  cabysBo.obtieneListaCabysHacienda(descArticulo, cantidad);
		List<Object> solicitudList = new ArrayList<Object>();
	  solicitudList.add(objetos);
    Long total = Constantes.ZEROS_LONG;
		respuestaService.setRecordsTotal(total);
		respuestaService.setRecordsFiltered(total);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}

	
	
	@SuppressWarnings("all")
	@Cacheable(value = "cabysCache")
	@RequestMapping(value = "/ListarCabysAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Cabys");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@Cacheable(value = "cabysCache")
	@RequestMapping(value = "/ListarCabysActivasAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Cabys");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		String nombreUsuario = request.getUserPrincipal().getName();
		dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings("all")
	@CacheEvict(value = "cabysCache", allEntries = true)
	@RequestMapping(value = "/AgregarCabysAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Articulo articulo,@RequestParam(value = "codigo") String codigo,@RequestParam(value = "descripcion") String descripcion,@RequestParam(value = "impuesto") Double impuesto,@RequestParam(value = "uri", required = false) String uri,@RequestParam(value = "origenSTR") String origenSTR, BindingResult result, SessionStatus status) {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Cabys cabysBd = cabysBo.findByCodigo(codigo,usuario.getEmpresa());
			if (cabysBd != null) {
				result.rejectValue("codigo", "error.cabys.codigo.existe");
			}
			
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			Cabys cabys = new Cabys();
      cabys.setEmpresa(usuario.getEmpresa());		
			cabys.setCodigo(codigo);
			cabys.setDescripcion(descripcion);
			cabys.setOrigen(FacturaElectronicaUtils.convertirStringToblod(origenSTR) );
			cabys.setImpuesto(impuesto);
			cabys.setEstado(Constantes.ESTADO_ACTIVO);
			cabys.setUri(uri);
			
			cabys.setCreated_at(new Date());
			cabys.setUpdated_at(new Date());

			cabysBo.agregar(cabys);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cabys.agregar.correctamente", cabys);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@CacheEvict(value = "cabysCache", allEntries = true)
	@RequestMapping(value = "/ModificarCabysAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Cabys cabys, BindingResult result, SessionStatus status) {
		try {
		
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			Cabys cabysBd = cabysBo.buscar(cabys.getId());

			if (cabysBd == null) {
				return RESPONSES.ERROR.Cabys.NO_EXISTE;
			} 
				
				cabysBd.setEstado(cabys.getEstado());
				cabysBo.modificar(cabysBd);
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cabys.modificado.correctamente", cabysBd);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	/**
	 * ASociar los cabys
	 * @param request
	 * @param model
	 * @param listaArticuloGrupales
	 * @param idCabys
	 * @param articuloCambioCategoriaGrupaltem
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/AsociarCabysArticulosGrupalAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarGrupal(HttpServletRequest request, ModelMap model, @RequestParam("listaArticuloGrupales") String listaArticuloGrupales, @RequestParam("caBys") Long cabys, @ModelAttribute ArticuloCambioCategoriaGrupal articuloCambioCategoriaGrupaltem, BindingResult result, SessionStatus status) throws Exception {
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
						CabysCambioGrupal cabysCambioGrupal = gson.fromJson(jsonArrayDetalleFactura.get(i).toString(), CabysCambioGrupal.class);
						Articulo articuloBD = articuloBo.buscar(cabysCambioGrupal.getId());
						if (articuloBD == null) {
							respuestaServiceValidator.setStatus(HttpStatus.BAD_REQUEST.value());
							respuestaServiceValidator.setMessage(Constantes.RESOURCE_BUNDLE.getString("error.articulo.codigo.no.existe"));
							return respuestaServiceValidator;
						}
						if (result.hasErrors()) {
							return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
						}
						Cabys cabysBD = cabysBo.buscar(cabys);
						articuloBD.setCodigoCabys(cabysBD.getCodigo());
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
	@RequestMapping(value = "/MostrarCabysAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute Cabys cabys, BindingResult result, SessionStatus status) throws Exception {
		try {
			CabysCommand cabysCommand = new CabysCommand(cabysBo.buscar(cabys.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", cabysCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class Cabys {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cabys.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cabys.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class Cabys {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.cabys.noExiste");
			}
		}
	}
}

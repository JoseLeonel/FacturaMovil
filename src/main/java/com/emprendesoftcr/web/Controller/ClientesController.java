package com.emprendesoftcr.web.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
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
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.ClienteCommand;
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
	private ValidateTokenBo																validateTokenBo;

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

		return listarClientesByEmpresa(request);
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/local/ListarClientes.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarLocalAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		if (validateTokenBo.validarTokenApis(request) == false) {
			DataTableDelimitador delimitadores = new DataTableDelimitador(request, "Cliente");
			return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
		}

		return listarClientesByEmpresa(request);
	}

	@SuppressWarnings("rawtypes")
	private RespuestaServiceDataTable listarClientesByEmpresa(HttpServletRequest request) {
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

		return listarActivosAjax(request);

	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/local/ListarClientesActivos.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (validateTokenBo.validarTokenApis(request) == false) {
			
			DataTableDelimitador delimitadores = null;
			delimitadores = new DataTableDelimitador(request, "Cliente");
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
			List<Object> solicitudList = new ArrayList<Object>();
			respuestaService.setRecordsTotal(0l);
			respuestaService.setRecordsFiltered(0l);
			respuestaService.setAaData(solicitudList);
			return respuestaService;
		}

		return listarActivosAjax(request);

	}

	@SuppressWarnings("rawtypes")
	private RespuestaServiceDataTable listarActivosAjax(HttpServletRequest request) {
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
		RespuestaServiceDataTable<Object> respuestaService = new RespuestaServiceDataTable<Object>();
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

			return clienteBo.agregar(request, clienteCommand, result, usuarioSesion);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/local/AgregarCliente.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregarCliente(HttpServletRequest request, ModelMap model, @ModelAttribute ClienteCommand clienteCommand, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);

			return clienteBo.agregar(request, clienteCommand, result, usuarioSesion);

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
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);

			return clienteBo.modificar(request, clienteCommand, result, usuarioSesion);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/local/ModificarClienteAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator modificarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute ClienteCommand clienteCommand, BindingResult result, SessionStatus status) throws Exception {
		try {

			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);

			return clienteBo.modificar(request, clienteCommand, result, usuarioSesion);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/clienteHacienda.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrarCliente(HttpServletRequest request,  ModelMap model, @ModelAttribute Cliente cliente ,HttpServletResponse response, @RequestParam String cedula,BindingResult result, SessionStatus status) throws IOException, ServletException {
		
		
		return clienteBo.clienteHaciendaByCedula(cedula);

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/local/clienteHaciendaByCedula", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrarClienteHacienda(HttpServletRequest request, ModelMap model, @ModelAttribute Cliente cliente, HttpServletResponse response, @RequestParam String cedula, BindingResult result, SessionStatus status) throws IOException, ServletException {

		if (validateTokenBo.validarTokenApis(request) == false) {
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
		}

		return clienteBo.clienteHaciendaByCedula(cedula);

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

	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/local/tipoCambioBancoCentral.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
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

			return clienteBo.mostrar(request, cliente, result);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/local/MostrarClienteAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Cliente cliente, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			return clienteBo.mostrar(request, cliente, result);
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

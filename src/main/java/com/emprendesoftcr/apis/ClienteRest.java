package com.emprendesoftcr.apis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

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

@CrossOrigin
@RestController
@RequestMapping("/api/cliente")
public class ClienteRest {

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


	@SuppressWarnings("rawtypes")
	@GetMapping("/clienteHaciendaByCedula")
	public RespuestaServiceValidator mostrarClienteHacienda(HttpServletRequest request, ModelMap model, @ModelAttribute Cliente cliente, HttpServletResponse response, @RequestParam String cedula, BindingResult result, SessionStatus status) throws IOException, ServletException {

		if (validateTokenBo.validarTokenApis(request) == false) {
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
		}

		return clienteBo.clienteHaciendaByCedula(cedula);

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

		return UtilsForControllersAPI.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@GetMapping("/ListarClientes")
	public RespuestaServiceDataTable listarLocalAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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

		return listarClientesByEmpresa(request);
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@GetMapping("/ListarClientesActivos.do")
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
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/AgregarCliente.do")
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
	

	@SuppressWarnings("rawtypes")
	@PostMapping("/ModificarClienteAjax.do")
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
	@GetMapping("/MostrarClienteAjax.do")
	public RespuestaServiceValidator mostrarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Cliente cliente, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			return clienteBo.mostrar(request, cliente, result);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	
	
}

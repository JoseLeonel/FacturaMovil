package com.emprendesoftcr.web.Controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.DataTableFilter;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(Cliente.class, clientePropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
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

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Cliente");
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		JqGridFilter dataTableFilter = new JqGridFilter("cedula", "'" + Constantes.CEDULA_CLIENTE_FRECUENTE + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);
		

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}

	/**
	 * Listar los clientes activos
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarClientesActivosAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarActivosAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Cliente");
		JqGridFilter dataTableFilter = new JqGridFilter("estado", "'" + Constantes.ESTADO_ACTIVO.toString() + "'", "=");
		delimitadores.addFiltro(dataTableFilter);
		if (!request.isUserInRole(Constantes.ROL_ADMINISTRADOR_SISTEMA)) {
			String nombreUsuario = request.getUserPrincipal().getName();
			dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
			delimitadores.addFiltro(dataTableFilter);
		}
		dataTableFilter = new JqGridFilter("cedula", "'" + Constantes.CEDULA_CLIENTE_FRECUENTE + "'", "<>");
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllers.process(request, dataTableBo, delimitadores, TO_COMMAND);
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
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute Cliente cliente, BindingResult result, SessionStatus status) throws Exception {
		try {

			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);

			Cliente clienteValidar = null;

			clienteValidar = clienteBo.buscarPorCedulaYEmpresa(cliente.getCedula().trim(), usuarioSesion.getEmpresa());
			if (clienteValidar != null) {
				result.rejectValue("cedula", "error.cliente.existe.cedula");
			}

			

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
	
			cliente.setProvincia(cliente.getProvincia().equals("0")?Constantes.EMPTY:cliente.getProvincia());
			cliente.setDistrito(cliente.getDistrito().equals("0")?Constantes.EMPTY:cliente.getDistrito());
			cliente.setCanton(cliente.getCanton().equals("0")?Constantes.EMPTY:cliente.getCanton());
			cliente.setBarrio(cliente.getBarrio().equals("0")?Constantes.EMPTY:cliente.getBarrio());
			
      cliente.setEmpresa(usuarioSesion.getEmpresa());
			cliente.setCreated_at(new Date());
			cliente.setUpdated_at(new Date());
			cliente.setUsuario(usuarioSesion);
			clienteBo.agregar(cliente);
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cliente.agregar.correctamente", cliente);

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
	public RespuestaServiceValidator modificar(HttpServletRequest request, ModelMap model, @ModelAttribute Cliente cliente, BindingResult result, SessionStatus status) throws Exception {
		try {
			String nombreUsuario = request.getUserPrincipal().getName();
			Usuario usuarioSesion = usuarioBo.buscar(nombreUsuario);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("cliente.no.modificado", result.getAllErrors());
			}
			Cliente clienteBD = clienteBo.buscar(cliente.getId());

			if (clienteBD == null) {
				return RESPONSES.ERROR.CLIENTE.NO_EXISTE;
			}
			Cliente clienteValidar = null;
			if (!cliente.getCedula().equals(clienteBD.getCedula())) {
				clienteValidar = clienteBo.buscarPorCedulaYEmpresa(cliente.getCedula(), usuarioSesion.getEmpresa());
				if (clienteValidar != null) {
					result.rejectValue("cedula", "error.cliente.existe.cedula");
				}
			}

			
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			clienteBD.setProvincia(cliente.getProvincia().equals("0")?Constantes.EMPTY:cliente.getProvincia());
			clienteBD.setDistrito(cliente.getDistrito().equals("0")?Constantes.EMPTY:cliente.getDistrito());
			clienteBD.setCanton(cliente.getCanton().equals("0")?Constantes.EMPTY:cliente.getCanton());
			clienteBD.setBarrio(cliente.getBarrio().equals("0")?Constantes.EMPTY:cliente.getBarrio());

			clienteBD.setCedula(cliente.getCedula());
			clienteBD.setNombreCompleto(cliente.getNombreCompleto());
			clienteBD.setCorreoElectronico(cliente.getCorreoElectronico());
			clienteBD.setDescuento(cliente.getDescuento());
			clienteBD.setOtraSena(cliente.getOtraSena());
			clienteBD.setProvincia(cliente.getProvincia());
			clienteBD.setNombreComercial(cliente.getNombreComercial());
			clienteBD.setUpdated_at(new Date());
			clienteBD.setEstado(cliente.getEstado());
			clienteBD.setTelefono(cliente.getTelefono());
			clienteBD.setCelular(cliente.getCelular());
			clienteBD.setUsuario(usuarioSesion);
			clienteBD.setBarrio(cliente.getBarrio());
			clienteBD.setCanton(cliente.getCanton());
			clienteBD.setCodigoPais(cliente.getCodigoPais());
			clienteBD.setDistrito(cliente.getDistrito());
			clienteBD.setIdentificacionExtranjero(cliente.getIdentificacionExtranjero());
			
			clienteBo.modificar(clienteBD);

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cliente.modificado.correctamente", clienteBD);

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
			ClienteCommand clienteCommand = new ClienteCommand(clienteBo.buscar(cliente.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", clienteCommand);
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

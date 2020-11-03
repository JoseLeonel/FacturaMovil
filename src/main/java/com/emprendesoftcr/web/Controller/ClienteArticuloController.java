package com.emprendesoftcr.web.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.Bo.ClienteArticuloBo;
import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.modelo.Articulo;
import com.emprendesoftcr.modelo.Cliente;
import com.emprendesoftcr.modelo.ClienteArticulo;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.Constantes;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.AgregarClienteArticuloCommand;

@Controller
public class ClienteArticuloController {
	


	@Autowired
	private UsuarioBo		usuarioBo;
	
	@Autowired
	private ClienteArticuloBo clienteArticuloBo;

	@Autowired
	private ClienteBo		clienteBo;

	@Autowired
	private ArticuloBo	articuloBo;

	@RequestMapping(value = "/listarAritucloCliente.do", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/clienteArticulo/clienteArticulo";
	}
	

	@RequestMapping(value = "/facturarCondominio.do", method = RequestMethod.GET)
	public String facturaCondominio(ModelMap model) {
		return "views/clienteArticulo/facturar";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ListarClienteArticuloActivos.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute AgregarClienteArticuloCommand agregarClienteArticuloCommand, BindingResult result, SessionStatus status) {


		Cliente cliente = clienteBo.buscar(agregarClienteArticuloCommand.getIdCliente());
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<ClienteArticulo> objetos = clienteArticuloBo.findAll(cliente);
		for (ClienteArticulo clienteArticulo : objetos) {
			solicitudList.add(clienteArticulo);
		}
		respuestaService.setRecordsTotal((long) objetos.size());
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;
		
	}
	
	
	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarDetlleByClienteArticuloAjax.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam Long idCliente) {

		Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
		Cliente cliente = clienteBo.buscar(idCliente);
		RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
		List<Object> solicitudList = new ArrayList<Object>();
		Collection<ClienteArticulo> objetos = clienteArticuloBo.findAll(cliente);
		for (ClienteArticulo clienteArticulo : objetos) {
			solicitudList.add(clienteArticulo);
		}
		respuestaService.setRecordsTotal((long) objetos.size());
		respuestaService.setRecordsFiltered(Constantes.ZEROS_LONG);
		if (request.getParameter("draw") != null && !request.getParameter("draw").equals(" ")) {
			respuestaService.setDraw(Integer.parseInt(request.getParameter("draw")));
		}
		respuestaService.setAaData(solicitudList);
		return respuestaService;

	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/AgregarClienteArticulo.do", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator agregar(HttpServletRequest request, ModelMap model, @ModelAttribute AgregarClienteArticuloCommand agregarClienteArticuloCommand, BindingResult result, SessionStatus status) throws Exception {

		@SuppressWarnings("unused")
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			Articulo articulo = articuloBo.buscar(agregarClienteArticuloCommand.getIdArticulo());
			Cliente cliente = clienteBo.buscar(agregarClienteArticuloCommand.getIdCliente());
			ClienteArticulo clienteArticuloBd = clienteArticuloBo.findByClienteAndArticulo(articulo, cliente);
			if (clienteArticuloBd != null) {
//				result.rejectValue("articulo", "error.clienteArticulo.articulo.existe");
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.clienteArticulo.articulo.existe", result.getAllErrors());
			}

			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			
			ClienteArticulo clienteArticulo = new ClienteArticulo();
			
			clienteArticulo.setArticulo(articulo);
			clienteArticulo.setCliente(cliente);
			clienteArticulo.setCreated_at(new Date());
			clienteArticulo.setUpdated_at(new Date());
			clienteArticulo.setCodigo(articulo.getCodigo());
			clienteArticulo.setCantidad(1d);
			clienteArticulo.setPrecio(articulo.getPrecioPublico());
			clienteArticulo.setDescripcion(articulo.getDescripcion());
			
			clienteArticuloBo.agregar(clienteArticulo);
		
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("clienteArticulo.agregar.correctamente", clienteArticulo);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/EliminarClienteArticuloAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator eliminarAjax(HttpServletRequest request, ModelMap model, @ModelAttribute ClienteArticulo clienteArticulo, BindingResult result, SessionStatus status, @RequestParam Long idClienteArticulo) {
		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			 ClienteArticulo clienteArticuloBd = clienteArticuloBo.buscar(idClienteArticulo);
			if (result.hasErrors()) {
				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("mensajes.error.transaccion", result.getAllErrors());
			}
			if(clienteArticuloBd != null) {
				clienteArticuloBo.eliminar(clienteArticuloBd);	
			}
			

			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("clienteArticulo.eliminado.correctamente",clienteArticuloBd);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}

	}
	
	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class ABONO {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("vendedor.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("abono.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class ABONO {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.vendedor.noExiste");
			}
		}
	}

}

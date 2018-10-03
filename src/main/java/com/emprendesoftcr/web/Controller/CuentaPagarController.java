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

import com.emprendesoftcr.Bo.CuentaPagarBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.ProveedorBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Utils.Constantes;
import com.emprendesoftcr.Utils.DataTableDelimitador;
import com.emprendesoftcr.Utils.JqGridFilter;
import com.emprendesoftcr.Utils.RespuestaServiceDataTable;
import com.emprendesoftcr.Utils.RespuestaServiceValidator;
import com.emprendesoftcr.Utils.Utils;
import com.emprendesoftcr.modelo.CuentaCobrar;
import com.emprendesoftcr.modelo.CuentaPagar;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Proveedor;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.web.command.CuentaCobrarCommand;
import com.emprendesoftcr.web.command.CuentaPagarCommand;
import com.emprendesoftcr.web.propertyEditor.CuentaPagarPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.ProveedorPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.VendedorPropertyEditor;
import com.google.common.base.Function;

/**
 * Manejo de las cuentas por cobrar por los clientes , se controla las cuentas por cobrar manuales y automaticas CuentaCobrarController.
 * @author jose.
 * @since 25 mar. 2018
 */
@Controller
public class CuentaPagarController {

	private static final Function<Object, CuentaPagarCommand>	TO_COMMAND	= new Function<Object, CuentaPagarCommand>() {

																																					@Override
																																					public CuentaPagarCommand apply(Object f) {
																																						return new CuentaPagarCommand((CuentaPagar) f);
																																					};
																																				};
	@Autowired
	private UsuarioBo																					usuarioBo;

	@Autowired
	private DataTableBo																				dataTableBo;

	@Autowired
	private ProveedorBo																				proveedorBo;

	@Autowired
	private CuentaPagarBo																			cuentaPagarBo;

	@Autowired
	private EmpresaPropertyEditor															empresaPropertyEditor;
	@Autowired
	private CuentaPagarPropertyEditor													cuentaPagarPropertyEditor;
	@Autowired
	private VendedorPropertyEditor														vendedorPropertyEditor;
	@Autowired
	private ProveedorPropertyEditor														proveedorPropertyEditor;
	@Autowired
	private StringPropertyEditor															stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(CuentaPagar.class, cuentaPagarPropertyEditor);
		binder.registerCustomEditor(Proveedor.class, proveedorPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@RequestMapping(value = "/ListarCuentaPagar", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/cuentasxpagar/ListarCuentasXPagar";
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/ListarCuentaPagarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceDataTable listarAjax(HttpServletRequest request, HttpServletResponse response, @RequestParam String fechaInicio, @RequestParam String fechaFinal, @RequestParam Long idProveedor) {
		Usuario usuarioSesion = usuarioBo.buscar(request.getUserPrincipal().getName());
		Proveedor proveedor = proveedorBo.buscar(idProveedor);
		DataTableDelimitador query = DelimitadorBuilder.get(request, fechaInicio, fechaFinal, proveedor, usuarioSesion.getEmpresa());

		return UtilsForControllers.process(request, dataTableBo, query, TO_COMMAND);
	}

	private static class DelimitadorBuilder {

		static DataTableDelimitador get(HttpServletRequest request, String inicio, String fin, Proveedor proveedor, Empresa empresa) {
			// Consulta por fechas
			DataTableDelimitador delimitador = new DataTableDelimitador(request, "CuentaPagar");
			Date fechaInicio = new Date();
			Date fechaFinal = new Date();

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
	
	@SuppressWarnings("all")
	@RequestMapping(value = "/MostrarCuentaPagarAjax.do", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public RespuestaServiceValidator mostrar(HttpServletRequest request, ModelMap model, @ModelAttribute CuentaCobrar cuentaPagar, BindingResult result, SessionStatus status) throws Exception {
		try {
			CuentaPagarCommand cuentaPagarCommand = new CuentaPagarCommand(cuentaPagarBo.buscar(cuentaPagar.getId()));
			return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("mensaje.consulta.exitosa", cuentaPagarCommand);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	@SuppressWarnings("all")
	private static class RESPONSES {

		private static class OK {

			private static class CUENTACOBRAR {

				private static final RespuestaServiceValidator	AGREGADO		= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.agregar.correctamente");
				private static final RespuestaServiceValidator	MODIFICADO	= RespuestaServiceValidator.BUNDLE_MSG_SOURCE.OK("cuentaCobrar.modificado.correctamente");
			}
		}

		private static class ERROR {

			private static class CUENTACOBRAR {

				private static final RespuestaServiceValidator NO_EXISTE = RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("error.cuentaCobrar.noExiste");
			}
		}
	}

}

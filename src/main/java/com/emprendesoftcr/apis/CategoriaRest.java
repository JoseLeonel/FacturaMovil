package com.emprendesoftcr.apis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.emprendesoftcr.Bo.CategoriaBo;
import com.emprendesoftcr.Bo.DataTableBo;
import com.emprendesoftcr.Bo.UsuarioBo;
import com.emprendesoftcr.Bo.ValidateTokenBo;
import com.emprendesoftcr.modelo.Categoria;
import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.modelo.Usuario;
import com.emprendesoftcr.utils.DataTableDelimitador;
import com.emprendesoftcr.utils.JqGridFilter;
import com.emprendesoftcr.utils.RespuestaServiceDataTable;
import com.emprendesoftcr.utils.RespuestaServiceValidator;
import com.emprendesoftcr.web.command.CategoriaCommand;
import com.emprendesoftcr.web.propertyEditor.CategoriaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;
import com.google.common.base.Function;
@CrossOrigin
@RestController
@RequestMapping("/api/categoria")
public class CategoriaRest {

	private static final Function<Object, CategoriaCommand>	TO_COMMAND	= new Function<Object, CategoriaCommand>() {

																																				@Override
																																				public CategoriaCommand apply(Object f) {
																																					return new CategoriaCommand((Categoria) f);
																																				};
																																			};

	@Autowired
	private DataTableBo																			dataTableBo;

	@Autowired
	private CategoriaBo																			categoriaBo;

	@Autowired
	private UsuarioBo																				usuarioBo;

	@Autowired
	private CategoriaPropertyEditor													categoriaPropertyEditor;

	@Autowired
	private EmpresaPropertyEditor														empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor														stringPropertyEditor;
	@Autowired
	private ValidateTokenBo																	validateTokenBo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Categoria.class, categoriaPropertyEditor);
		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}

	@SuppressWarnings("all")
	@Cacheable(value = "categorialocalCache")
	@GetMapping("/ListarCategoriasAjax.do")
	public RespuestaServiceDataTable listarCategoriasLocalAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		if (validateTokenBo.validarTokenApis(request) == false) {
			DataTableDelimitador delimitadores = null;
			delimitadores = new DataTableDelimitador(request, "Categoria");
			RespuestaServiceDataTable respuestaService = new RespuestaServiceDataTable();
			List<Object> solicitudList = new ArrayList<Object>();
			respuestaService.setRecordsTotal(0l);
			respuestaService.setRecordsFiltered(0l);
			respuestaService.setAaData(solicitudList);
			return respuestaService;
		}
		return listarCategoriasAjax(request, response);
	}

	@SuppressWarnings("all")
	@CacheEvict(value = "categorialocalCache", allEntries = true)
	@PostMapping("/AgregarCategoriaAjax.do")
	public RespuestaServiceValidator agregarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) {

		RespuestaServiceValidator respuestaServiceValidator = new RespuestaServiceValidator();
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}
			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());

			return categoriaBo.agregar(request, categoria, result, usuario);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}

	private RespuestaServiceDataTable<?> listarCategoriasAjax(HttpServletRequest request, HttpServletResponse response) {

		DataTableDelimitador delimitadores = null;
		delimitadores = new DataTableDelimitador(request, "Categoria");
		String nombreUsuario = request.getUserPrincipal().getName();
		JqGridFilter dataTableFilter = usuarioBo.filtroPorEmpresa(nombreUsuario);
		delimitadores.addFiltro(dataTableFilter);

		return UtilsForControllersAPI.process(request, dataTableBo, delimitadores, TO_COMMAND);
	}
	
	
	@SuppressWarnings("all")
	@CacheEvict(value = "categorialocalCache", allEntries = true)
	@PostMapping("/ModificarCategoriaAjax.do")
	public RespuestaServiceValidator modificarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad", result.getAllErrors());
			}

			Usuario usuario = usuarioBo.buscar(request.getUserPrincipal().getName());
			return categoriaBo.modificar(request, categoria, result, usuario);

		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
	
	@SuppressWarnings("all")
	@PostMapping("/MostrarCategoriaAjax.do")
	public RespuestaServiceValidator mostrarLocal(HttpServletRequest request, ModelMap model, @ModelAttribute Categoria categoria, BindingResult result, SessionStatus status) throws Exception {
		try {
			if (validateTokenBo.validarTokenApis(request) == false) {

				return RespuestaServiceValidator.BUNDLE_MSG_SOURCE.ERROR("autenticacion.invalidad");
			}
			return categoriaBo.mostrar(request, categoria, result);
		} catch (Exception e) {
			return RespuestaServiceValidator.ERROR(e);
		}
	}
}

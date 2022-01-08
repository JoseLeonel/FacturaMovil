package com.emprendesoftcr.web.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emprendesoftcr.modelo.TipoTiempo;
import com.emprendesoftcr.service.TipoTiempoService;
import com.emprendesoftcr.utils.Constantes;

@Controller
public class TipoTiempoController {

	@Autowired
	private TipoTiempoService tipoTiempoService;

	@RequestMapping(value = "/listarTipoTiempo", method = RequestMethod.GET)
	public String listarTipoTiempo(ModelMap model) {
		model.addAttribute("titulo", "Lista de tipos de tiempos");
		List<TipoTiempo> lista = tipoTiempoService.listarTodo();
		model.addAttribute("tipoTiempos", lista);
		return "views/tipoTiempo/listar.html";
	}

	@RequestMapping(value = "/crearTipoTiempo")
	public String crear(ModelMap model) {
		model.addAttribute("titulo", "Formulario:Crear Tipo de Loterias");
		TipoTiempo tipoTiempo = new TipoTiempo();
		tipoTiempo.setEstado(Constantes.ESTADO_ACTIVO_NUMERIC);
		model.addAttribute("tipoTiempo", tipoTiempo);
		return "views/tipoTiempo/crear";
	}

	@PostMapping("/saveTipoTiempo")
	public String guardar(@ModelAttribute TipoTiempo tipoTiempo) {
		tipoTiempo.setCreated_at(new Date());
		tipoTiempo.setUpdated_at(new Date());
		
		tipoTiempoService.guardar(tipoTiempo);
		return "redirect:listarTipoTiempo";
	}

	@RequestMapping(value = "/modificarTipoTiempo/{id}")
	public String modificar(@PathVariable("id") Long idTipoTiempo, Model model) {
		model.addAttribute("titulo", "Formulario:Editar Tipo de Loterias");
		TipoTiempo tipoTiempo = tipoTiempoService.findbyId(idTipoTiempo);
		model.addAttribute("tipoTiempo", tipoTiempo);
		return "views/tipoTiempo/crear";
	}

	
	@RequestMapping(value = "/eliminarTipoTiempo/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable("id") Long idTipoTiempo, Model model) {
		model.addAttribute("titulo", "Formulario:Editar Tipo de Loterias");
		tipoTiempoService.eliminar(idTipoTiempo);

		return "redirect:/listarTipoTiempo";
	}
	
	@RequestMapping(value = "/regresarTipoCambio", method = RequestMethod.GET)
	public String regresarTipoCambio() {
	

		return "redirect:/listarTipoTiempo";
	}

}

package com.emprendesoftcr.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emprendesoftcr.modelo.Empresa;
import com.emprendesoftcr.web.propertyEditor.EmpresaPropertyEditor;
import com.emprendesoftcr.web.propertyEditor.StringPropertyEditor;

@Controller
public class ControlPrecioController {

	@Autowired
	private EmpresaPropertyEditor	empresaPropertyEditor;

	@Autowired
	private StringPropertyEditor	stringPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Empresa.class, empresaPropertyEditor);
		binder.registerCustomEditor(String.class, stringPropertyEditor);
	}
	
	@RequestMapping(value = "/ListarControlPrecio", method = RequestMethod.GET)
	public String listar(ModelMap model) {
		return "views/controlPrecio/ListarControlPrecio";
	}
	
}

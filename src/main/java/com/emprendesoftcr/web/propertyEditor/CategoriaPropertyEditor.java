package com.emprendesoftcr.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.CategoriaBo;
import com.emprendesoftcr.modelo.Categoria;

@Component
public class CategoriaPropertyEditor extends PropertyEditorSupport {

	@Autowired
	CategoriaBo categoriaBo;

	@Override
	public String getAsText() {
		Categoria categoria = (Categoria) getValue();
		return (categoria == null ? "" : categoria.getId().toString());
	}

	@Override
	public void setAsText(String idCategoria) throws IllegalArgumentException {
		if ((idCategoria == null) || !StringUtils.hasLength(idCategoria)) {
			setValue(null);
		} else {

			setValue(categoriaBo.buscar(Integer.parseInt(idCategoria)));

		}
	}

	
	public CategoriaBo getCategoriaBo() {
		return categoriaBo;
	}

	
	public void setCategoriaBo(CategoriaBo categoriaBo) {
		this.categoriaBo = categoriaBo;
	}
	
	
}

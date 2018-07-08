package com.emprendesoftcr.web.componentes;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.ArticuloBo;
import com.emprendesoftcr.modelo.Articulo;

@Component
public class ArticuloPropertyEditor extends PropertyEditorSupport {

	@Autowired
	ArticuloBo articuloBo;

	@Override
	public String getAsText() {
		Articulo articulo = (Articulo) getValue();
		return (articulo == null ? "" : articulo.getId().toString());
	}

	@Override
	public void setAsText(String idArticulo) throws IllegalArgumentException {
		if ((idArticulo == null) || !StringUtils.hasLength(idArticulo)) {
			setValue(null);
		} else {

			setValue(articuloBo.buscar(Integer.parseInt(idArticulo)));

		}
	}

	public ArticuloBo getArticuloBo() {
		return articuloBo;
	}

	public void setArticuloBo(ArticuloBo articuloBo) {
		this.articuloBo = articuloBo;
	}

}

package com.emprendesoftcr.web.jsonDeserializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.VendedorBo;
import com.emprendesoftcr.modelo.Vendedor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class VendedorDeserializer extends StdDeserializer<Object> {

	/**
	 * Comentario para <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 55645678954L;
	
	@Autowired
	VendedorBo vendedorBo;

	public VendedorDeserializer() {
		super(Vendedor.class);
	}

	@Override
	public Vendedor deserialize(JsonParser json, DeserializationContext context) throws IOException, JsonProcessingException {
		try {
			if (StringUtils.isEmpty(json.getText())) {
				return null;
			}
			return vendedorBo.buscar(Long.parseLong(json.getText()));
		} catch (ParseException e) {
			return null;
		}
	}
}

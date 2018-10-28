package com.emprendesoftcr.web.jsonDeserializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.util.StringUtils;

import com.emprendesoftcr.Bo.ClienteBo;
import com.emprendesoftcr.modelo.Cliente;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ClienteDeserializer extends StdDeserializer<Object> {

	/**
	 * Comentario para <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 5564544554L;
	
	@Autowired
	ClienteBo clienteBo;

	public ClienteDeserializer() {
		super(Cliente.class);
	}

	@Override
	public Cliente deserialize(JsonParser json, DeserializationContext context) throws IOException, JsonProcessingException {
		try {
			if (StringUtils.isEmpty(json.getText())) {
				return null;
			}
			return clienteBo.buscar(Long.parseLong(json.getText()));
		} catch (ParseException e) {
			return null;
		}
	}
}
